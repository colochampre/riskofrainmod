package com.elcolomanco.riskofrainmod.entities.goals;

import java.util.EnumSet;
import java.util.Random;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.entities.StoneGolemEntity;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.pathfinding.Path;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.world.Difficulty;
import net.minecraft.world.World;

public class StoneGolemAttackGoal extends Goal {

	private final StoneGolemEntity golem;
	// Laser
	private int attackTime;
	private int tickCounter;
	// Melee attack
	protected int attackTick;
	private final double speedTowardsTarget;
	private final boolean longMemory;
	private Path path;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	protected final int attackInterval = 20;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;

	public StoneGolemAttackGoal(StoneGolemEntity golem, double speedIn, boolean useLongMemory) {
        this.golem = golem;
        this.speedTowardsTarget = speedIn;
        this.longMemory = useLongMemory;
        this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
	}

	public boolean shouldExecute() {
		if(golem.getEntityWorld().getDifficulty() == Difficulty.PEACEFUL) {
			return false;
		}
    	LivingEntity livingentity = this.golem.getAttackTarget();
    	return livingentity != null && livingentity.isAlive() && this.golem.canAttack(livingentity);
    }

	public boolean shouldContinueExecuting() {
		LivingEntity livingentity = this.golem.getAttackTarget();
		if(livingentity == null) {
			return false;
		} else if(!livingentity.isAlive()) {
			return false;
		} else if(!this.longMemory) {
			return !this.golem.getNavigator().noPath();
		} else if(!this.golem.isWithinHomeDistanceFromPosition(livingentity.getPosition())) {
			return false;
		} else {
			return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
		}
	}
	
	public void startExecuting() {
		super.startExecuting();
    	this.golem.getNavigator().setPath(this.path, this.speedTowardsTarget);
        this.golem.setAggroed(true);
        this.delayCounter = 0;
        this.tickCounter = -10;
	}
	
	public void resetTask() {
		this.golem.setTargetedEntity(0);
		LivingEntity livingentity = this.golem.getAttackTarget();
		if(!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.golem.setAttackTarget((LivingEntity) null);
		}
    	this.golem.setAggroed(false);
		this.golem.getNavigator().clearPath();
	}
	
	private static Random rand = new Random();
	
	public void tick() {
		if (this.golem.hasTargetedEntity()) {
			if (this.golem.clientSideAttackTime < this.golem.getAttackDuration()) {
				++this.golem.clientSideAttackTime;
			}
		}
		LivingEntity livingentity1 = this.golem.getTargetedEntity();
        if (livingentity1 != null) {
           this.golem.getLookController().setLookPositionWithEntity(livingentity1, 90.0F, 90.0F);
           this.golem.getLookController().tick();
           double d5 = (double)this.golem.getAttackAnimationScale(0.0F);
           double d0 = livingentity1.getPosX() - this.golem.getPosX();
           double d1 = livingentity1.getPosYHeight(0.5D) - this.golem.getPosYEye();
           double d2 = livingentity1.getPosZ() - this.golem.getPosZ();
           double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
           d0 = d0 / d3;
           d1 = d1 / d3;
           d2 = d2 / d3;
           double d4 = rand.nextDouble();

           while(d4 < d3) {
              d4 += 1.8D - d5 + rand.nextDouble() * (1.7D - d5);
              this.golem.world.addParticle(ParticleTypes.SMOKE, this.golem.getPosX() + d0 * d4, this.golem.getPosYEye() + d1 * d4, this.golem.getPosZ() + d2 * d4, 0.0D, 0.0D, 0.0D);
           }
        }
		
		--this.attackTime;
		LivingEntity livingentity = this.golem.getAttackTarget();
		boolean flag = this.golem.getEntitySenses().canSee(livingentity);
		double d0 = this.golem.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
        this.golem.getLookController().setLookPositionWithEntity(livingentity, 90.0F, 90.0F);

        // Melee attack
        --this.delayCounter;
        if ((this.longMemory || this.golem.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.golem.getRNG().nextFloat() < 0.05F)) {
        	this.targetX = livingentity.getPosX();
        	this.targetY = livingentity.getPosY();
        	this.targetZ = livingentity.getPosZ();
        	this.delayCounter = 4 + this.golem.getRNG().nextInt(7);
        	if (this.canPenalize) {
        		this.delayCounter += failedPathFindingPenalty;
        		if (this.golem.getNavigator().getPath() != null) {
        			net.minecraft.pathfinding.PathPoint finalPathPoint = this.golem.getNavigator().getPath().getFinalPathPoint();
        			if (finalPathPoint != null && livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
        				failedPathFindingPenalty = 0;
        			else
        				failedPathFindingPenalty += 10;
        		} else {
        			failedPathFindingPenalty += 10;
        		}
        	}
        	if (d0 > 1024.0D) {
        		this.delayCounter += 10;
        	} else if (d0 > 256.0D) {
        		this.delayCounter += 5;
        	}

        	if (!this.golem.getNavigator().tryMoveToEntityLiving(livingentity, this.speedTowardsTarget)) {
        		this.delayCounter += 15;
        	}
        }
        this.attackTick = Math.max(this.attackTick - 1, 0);
        this.checkAndPerformAttack(livingentity, d0);
        // Laser attack        
        this.golem.getNavigator().tryMoveToEntityLiving(livingentity, speedTowardsTarget);
        if (!this.golem.canEntityBeSeen(livingentity)) {
        	this.golem.setAttackTarget((LivingEntity)null);
        } else {
        	if(attackTime <= 0 && flag) {
        		++this.tickCounter;
        	}
        	if (this.tickCounter == 0) {
            	this.golem.setTargetedEntity(this.golem.getAttackTarget().getEntityId());
            	this.golem.world.setEntityState(this.golem, (byte)21);
            	this.golem.playSound(this.getLaserChargeSound(), 1.0F, 1.0F);
            } else if (this.tickCounter >= this.golem.getAttackDuration()) {
            	float f = 0.5F;
            	if (this.golem.world.getDifficulty() == Difficulty.NORMAL) {
            		f += 1.0F;
            	}
            	if (this.golem.world.getDifficulty() == Difficulty.HARD) {
            		f += 2.0F;
            	}
            	World worldIn = livingentity.getEntityWorld();
            	Vector3d vec3d = this.golem.getLookVec().mul(1.0D, 1.0D, 1.0D).normalize().scale((double) 3.0D);
    			SoundCategory soundcategory = livingentity instanceof LivingEntity ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
    			
    			if (livingentity instanceof PlayerEntity) {
            		if (livingentity.getActiveItemStack().getItem().isShield(livingentity.getActiveItemStack(), livingentity)) {
            			if (livingentity.isHandActive()) {
            				ItemStack copyBeforeUse = livingentity.getActiveItemStack().copy();
                			livingentity.getActiveItemStack().damageItem(6, livingentity, (p_220048_0_) -> {
                                p_220048_0_.sendBreakAnimation(EquipmentSlotType.MAINHAND);
                            });
                			if (livingentity.getActiveItemStack().isEmpty()) {
                                Hand Hand = livingentity.getActiveHand();
                                net.minecraftforge.event.ForgeEventFactory.onPlayerDestroyItem((PlayerEntity)livingentity, copyBeforeUse, Hand);

                                if (Hand == net.minecraft.util.Hand.MAIN_HAND) {
                                	livingentity.setItemStackToSlot(EquipmentSlotType.MAINHAND, ItemStack.EMPTY);
                                } else {
                                	livingentity.setItemStackToSlot(EquipmentSlotType.OFFHAND, ItemStack.EMPTY);
                                }
                                livingentity.resetActiveHand();
                                livingentity.playSound(SoundEvents.ITEM_SHIELD_BREAK, 0.8F, 0.8F + livingentity.world.rand.nextFloat() * 0.4F);
                            }
            			}
            		}
            	}
    			worldIn.playSound((PlayerEntity)null, livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ(), RegistrySetup.STONE_GOLEM_LASER_FIRE.get(), soundcategory, 1.5F, 1.0F);
    			livingentity.attackEntityFrom(DamageSource.causeIndirectMagicDamage(this.golem, this.golem), f);
    			livingentity.attackEntityFrom(DamageSource.causeMobDamage(this.golem), (float)this.golem.getAttribute(Attributes.ATTACK_DAMAGE).getValue() /2);
    			if (livingentity instanceof PlayerEntity & RoRconfig.LASER_KNOCKBACK) {
    				livingentity.addVelocity(vec3d.x, vec3d.y, vec3d.z);
    			}
    			this.golem.playSound(this.getLaserFireSound(), 1.5F, 1.0F);
            	this.golem.setAttackTarget((LivingEntity)null);
            	this.attackTime = 85;
            }
        	super.tick();
        }
	}
	
	protected SoundEvent getLaserChargeSound() {
		return RegistrySetup.STONE_GOLEM_LASER_CHARGE.get();
	}

	protected SoundEvent getLaserFireSound() {
		return RegistrySetup.STONE_GOLEM_LASER_FIRE.get();
	}

	protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			this.attackTick = 40;
			this.golem.attackEntityAsMob(enemy);
		}
	}
	
	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return (double)(this.golem.getWidth() * 1.5F * this.golem.getWidth() * 1.5F + attackTarget.getWidth());
	}
}

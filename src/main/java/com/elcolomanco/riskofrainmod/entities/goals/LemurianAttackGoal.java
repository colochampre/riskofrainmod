package com.elcolomanco.riskofrainmod.entities.goals;

import java.util.EnumSet;

import com.elcolomanco.riskofrainmod.entities.LemurianEntity;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.SmallFireballEntity;
import net.minecraft.pathfinding.Path;
import net.minecraft.pathfinding.PathPoint;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.Difficulty;

public class LemurianAttackGoal extends Goal {

	private final LemurianEntity entity;
	private final double moveSpeedAmp;
	
	protected int attackTick;
	private final boolean longMemory;
	private Path path;
	private int delayCounter;
	private double targetX;
	private double targetY;
	private double targetZ;
	private int failedPathFindingPenalty = 0;
	private boolean canPenalize = false;
	
	private final float maxAttackDistance;
    private int attackStep;
    private int attackTime;
    private int seeTime;
    private boolean strafingClockwise;
    private boolean strafingBackwards;
    private int strafingTime = -1;

    public LemurianAttackGoal(LemurianEntity entityIn, double moveSpeedAmpIn, float maxAttackDistanceIn, boolean useLongMemory) {
    	this.entity = entityIn;
    	this.moveSpeedAmp = moveSpeedAmpIn;
    	this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
    	this.longMemory = useLongMemory;
    	this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }

    // Returns whether execution should begin. You can also read and cache any state necessary for execution in this method as well
    public boolean shouldExecute() {

    	if(entity.getEntityWorld().getDifficulty() == Difficulty.PEACEFUL) {
			return false;
		}
    	LivingEntity livingentity = this.entity.getAttackTarget();
    	return livingentity != null && livingentity.isAlive() && this.entity.canAttack(livingentity);
    }

    public boolean shouldContinueExecuting() {
    	LivingEntity livingentity = this.entity.getAttackTarget();
		if(livingentity == null) {
			return false;
		} else if(!livingentity.isAlive()) {
			return false;
		} else if(!this.longMemory) {
			return !this.entity.getNavigator().noPath();
		} else if(!this.entity.isWithinHomeDistanceFromPosition(livingentity.getPosition())) {
			return false;
		} else {
			return !(livingentity instanceof PlayerEntity) || !livingentity.isSpectator() && !((PlayerEntity) livingentity).isCreative();
		}
	}

    // Execute a one shot task or start executing a continuous task
    public void startExecuting() {
    	super.startExecuting();
    	this.entity.getNavigator().setPath(this.path, this.moveSpeedAmp);
        this.entity.setAggroed(true);
    	this.attackStep = 0;
    	this.delayCounter = 0;
    }

    // Reset the task's internal state. Called when this task is interrupted by another one
    public void resetTask() {
    	LivingEntity livingentity = this.entity.getAttackTarget();
		if(!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.entity.setAttackTarget((LivingEntity) null);
		}
    	this.entity.setAggroed(false);
		this.entity.getNavigator().clearPath();
        this.seeTime = 0;
    }

    // Keep ticking a continuous task that has already been started
    public void tick() {
    	--this.attackTime;
    	LivingEntity livingentity = this.entity.getAttackTarget();
    	if (livingentity != null) {
    		double d0 = this.entity.getDistanceSq(livingentity.getPosX(), livingentity.getPosY(), livingentity.getPosZ());
    		boolean flag = this.entity.getEntitySenses().canSee(livingentity);
    		
    		if (flag) {
    			++this.seeTime;
    		} else {
    			--this.seeTime;
    		}
    		// Melee attack
    		if (d0 < (double)(this.maxAttackDistance * 0.3)) {
    			if (!flag) {
                	this.entity.getNavigator().tryMoveToEntityLiving(livingentity, this.moveSpeedAmp);
                }
                this.entity.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
                --this.delayCounter;
                if ((this.longMemory || this.entity.getEntitySenses().canSee(livingentity)) && this.delayCounter <= 0 && (this.targetX == 0.0D && this.targetY == 0.0D && this.targetZ == 0.0D || livingentity.getDistanceSq(this.targetX, this.targetY, this.targetZ) >= 1.0D || this.entity.getRNG().nextFloat() < 0.05F)) {
                	this.targetX = livingentity.getPosX();
                	this.targetY = livingentity.getPosY();
                    this.targetZ = livingentity.getPosZ();
                    this.delayCounter = 4 + this.entity.getRNG().nextInt(7);
                    if (this.canPenalize) {
                    	this.delayCounter += failedPathFindingPenalty;
                    	if (this.entity.getNavigator().getPath() != null) {
                    		PathPoint finalPathPoint = this.entity.getNavigator().getPath().getFinalPathPoint();
                    		if (finalPathPoint != null && livingentity.getDistanceSq(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
                    			failedPathFindingPenalty = 0;
                    		else
                    			failedPathFindingPenalty += 10;
                    	} else {
                    		failedPathFindingPenalty += 10;
                    	}
                    }
                    if (d0 > 576.0D) {
                    	this.delayCounter += 10;
                    } else if (d0 > 144.0D) {
                    	 this.delayCounter += 5;
                     }
                    if (!this.entity.getNavigator().tryMoveToEntityLiving(livingentity, this.moveSpeedAmp)) {
                    	 this.delayCounter += 15;
                     }
                }
                this.attackTick = Math.max(this.attackTick - 1, 0);
                this.checkAndPerformAttack(livingentity, d0);
    		}
    		// Strafing
    		if(d0 < (double)(this.maxAttackDistance)) {
    			if (d0 > (double)(this.maxAttackDistance * 0.3) && flag) {
    				if (!(d0 > (double)this.maxAttackDistance) && this.seeTime >= 20) {
    					this.entity.getNavigator().clearPath();
    					++this.strafingTime;
    				} else {
    					this.entity.getNavigator().tryMoveToEntityLiving(livingentity, 0.8D);
    					this.strafingTime = -1;
    				}
    				if (this.strafingTime >= 20) {
    					if ((double)this.entity.getRNG().nextFloat() < 0.3D) {
    						this.strafingClockwise = !this.strafingClockwise;
    					}
    					if ((double)this.entity.getRNG().nextFloat() < 0.3D) {
    						this.strafingBackwards = !this.strafingBackwards;
    					}
    					this.strafingTime = 0;
    				}
    				if (this.strafingTime > -1) {
    					if (d0 > (double)(this.maxAttackDistance * 0.8333333333333333F)) {
    						this.strafingBackwards = false;
    					} else if (d0 < (double)(this.maxAttackDistance * 0.6F)) {
    						this.strafingBackwards = true;
    					}
    					this.entity.getMoveHelper().strafe(this.strafingBackwards ? -0.2F : 0.4F, this.strafingClockwise ? 0.4F : -0.4F);
    					this.entity.faceEntity(livingentity, 30.0F, 30.0F);
    				}else {
    					this.entity.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
    				}
    			}
    			// Fireball attack
    			if ((d0 > (double)(this.maxAttackDistance * 0.3) && flag) || ((this.entity.getNavigator().noPath()) && (d0 < (double)(this.maxAttackDistance)) && flag)) {
    				double d1 = livingentity.getPosX() - this.entity.getPosX();
    				double d2 = livingentity.getPosYHeight(0.3333333333333333D) - this.entity.getPosYHeight(0.6D);
    				double d3 = livingentity.getPosZ() - this.entity.getPosZ();
    				if (this.attackTime <= 0) {
    					++this.attackStep;
    					if (this.attackStep == 1) {
    						this.attackTime = 20;
    					} else if (this.attackStep <= 2) {
    						this.attackTime = 6;
    					} else {
    						this.attackTime = 60;
    						this.attackStep = 0;
    					}
    					if (this.attackStep > 1) {
    						float f = MathHelper.sqrt(MathHelper.sqrt(d0)) * 0.5F;
    						this.entity.world.playEvent((PlayerEntity)null, 1018, entity.getPosition(), 0);
    						
    						for(int i = 0; i < 1; ++i) {
    							SmallFireballEntity smallfireballentity = new SmallFireballEntity(this.entity.world, this.entity, d1 * (double)f, d2, d3 * (double)f);
    							smallfireballentity.setPosition(smallfireballentity.getPosX(), this.entity.getPosYHeight(0.6D) + 0.5D, smallfireballentity.getPosZ());
    							this.entity.world.addEntity(smallfireballentity);
    						}
    					}
    				}
    				this.entity.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 30.0F);
    			}
    		}
    		super.tick();
    	}
    }
    
    protected void checkAndPerformAttack(LivingEntity enemy, double distToEnemySqr) {
		double d0 = this.getAttackReachSqr(enemy);
		if (distToEnemySqr <= d0 && this.attackTick <= 0) {
			this.attackTick = 20;
			this.entity.attackEntityAsMob(enemy);
		}
	}

	protected double getAttackReachSqr(LivingEntity attackTarget) {
		return (double)(this.entity.getWidth() * 2.0F * this.entity.getWidth() * 2.0F + attackTarget.getWidth());
	}
}

package com.elcolomanco.riskofrainmod.entities.goals;

import java.util.EnumSet;

import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;
import com.elcolomanco.riskofrainmod.entities.IronNuggetEntity;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.EntityPredicates;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.vector.Vector3d;

public class GunnerDroneAttackGoal extends Goal {
	
	private final float maxAttackDistance;
	private final GunnerDroneEntity entity;
	private int	attackStep;
    private int	attackTime;
    
    public GunnerDroneAttackGoal(GunnerDroneEntity entityIn, float maxAttackDistanceIn) {
    	this.entity = entityIn;
    	this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
    	this.setMutexFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
    }
    
    public boolean shouldExecute() {
    	LivingEntity livingentity = this.entity.getAttackTarget();
    	return livingentity != null && livingentity.isAlive() && this.entity.canAttack(livingentity);
    }
    
    public void startExecuting() {
    	super.startExecuting();
        this.entity.setAggroed(true);
    	this.attackStep = 0;
    }
    
    public void resetTask() {
    	LivingEntity livingentity = this.entity.getAttackTarget();
		if(!EntityPredicates.CAN_AI_TARGET.test(livingentity)) {
			this.entity.setAttackTarget((LivingEntity)null);
		}
    	this.entity.setAggroed(false);
    	this.entity.getNavigator().clearPath();
    }
    
    public void tick() {
    	--this.attackTime;
    	LivingEntity livingentity = this.entity.getAttackTarget();
    	if (livingentity != null) {
    		double d0 = this.entity.getDistanceSq(livingentity);
    		boolean flag = this.entity.getEntitySenses().canSee(livingentity);
    		Vector3d vec3d = this.entity.getLook(1.0F);
    		Vector3d vec3d1 = this.entity.getLookVec().mul(1.0D, 1.0D, 1.0D).normalize().scale((double)0.01D).inverse();
			if (!flag) {
            	this.entity.getNavigator().tryMoveToEntityLiving(livingentity, 0.8D);
            }
			if (livingentity.getPosYEye() > this.entity.getPosYEye()) {
				Vector3d vec3d2 = this.entity.getMotion();
					this.entity.setMotion(this.entity.getMotion().add(0.0D, ((double)0.1F - vec3d2.y) * (double)0.1F, 0.0D));
					this.entity.isAirBorne = true;
			}
    		if (d0 < (double)(this.maxAttackDistance) && flag) {
    			if (d0 < (double)(this.maxAttackDistance * 0.3) && flag) {
    				this.entity.setMotion(this.entity.getMotion().add(vec3d1.x, 0.0D, vec3d1.z));
    			}
    			if (d0 > (double)(this.maxAttackDistance * 0.75) && flag) {
    				this.entity.getNavigator().tryMoveToEntityLiving(livingentity, 0.8D);
    			}
    			this.entity.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 90.0F);
    			this.entity.faceEntity(livingentity, 30.0F, 90.0F);
    		}
    		double d1 = livingentity.getPosX() - this.entity.getPosX() + vec3d.x * 4.0D;
			double d2 = livingentity.getPosYHeight(0.3333333333333333D) - this.entity.getPosYEye();
			double d3 = livingentity.getPosZ() - this.entity.getPosZ() + vec3d.z * 4.0D;
			
			if (d0 < (double)(this.maxAttackDistance) && flag) {
				if (this.attackTime <= 0) {
					++this.attackStep;
					if (this.attackStep == 1) {
						this.attackTime = 30;
					} else if (this.attackStep <= 5) {
						this.attackTime = 4;
					} else {
						this.attackTime = 30;
						this.attackStep = 0;
						this.entity.setAttackTarget((LivingEntity)null);
					}
					if (this.attackStep > 1) {
						SoundCategory soundcategory = this.entity instanceof GunnerDroneEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
						this.entity.world.playSound((PlayerEntity)null, entity.getPosX(), entity.getPosY(), entity.getPosZ(), RegistrySetup.GUNNER_DRONE_SHOOT.get(), soundcategory, 0.4F, 1.0F);
						
						for(int i = 0; i < 1; ++i) {
							IronNuggetEntity bullet = new IronNuggetEntity(this.entity, this.entity.world);
							bullet.shoot(d1, d2, d3, 3.0F, 1.0F);
							this.entity.world.addEntity(bullet);
						}
					}
					this.entity.getLookController().setLookPositionWithEntity(livingentity, 30.0F, 90.0F);
				}
			}
    		super.tick();
    	}
    }
}

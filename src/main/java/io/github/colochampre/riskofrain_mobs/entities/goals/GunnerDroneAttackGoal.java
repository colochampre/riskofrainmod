package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GunnerDroneAttackGoal extends Goal {

  private final float maxAttackDistance;
  private final GunnerDroneEntity drone;
  private int attackStep;
  private int attackTime;

  public GunnerDroneAttackGoal(GunnerDroneEntity entity, float maxAttackDistance) {
    this.drone = entity;
    this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity livingentity = this.drone.getTarget();
    return livingentity != null && livingentity.isAlive() && this.drone.canAttack(livingentity);
  }

  @Override
  public void start() {
    super.start();
    this.drone.setAggressive(true);
    this.attackStep = 0;

  }

  public void stop() {
    LivingEntity livingentity = this.drone.getTarget();
    if (!EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(livingentity)) {
      this.drone.setTarget((LivingEntity) null);
    }
    this.drone.setAggressive(false);
    this.drone.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  @Override
  public void tick() {
    --this.attackTime;
    LivingEntity livingentity = this.drone.getTarget();
    if (livingentity != null) {
      boolean flag = this.drone.getSensing().hasLineOfSight(livingentity);
      double d0 = this.drone.distanceToSqr(livingentity);
      Vec3 vec3a = this.drone.getDeltaMovement();
      Vec3 vec3b = this.drone.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) 0.05D).reverse();
      if (!flag) {
        this.drone.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 0.8D);
      } else {
        this.drone.getLookControl().setLookAt(livingentity, 30.0F, 90.0F);
        // Elevate drone above target
        if (livingentity.getEyeY() > this.drone.getEyeY()) {
          this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
          this.drone.hasImpulse = true;
        }
        if (d0 < (double) this.maxAttackDistance && flag) {
          // Keep distance with target
          if (d0 < (double) this.maxAttackDistance * 0.3) {
            this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(vec3b.x / 2, 0.0D, vec3b.z / 2));
            this.drone.hasImpulse = true;
          }
          // Get closer to target
          if (d0 > (double) this.maxAttackDistance * 0.75 && !(d0 < (double) this.maxAttackDistance * 0.5)) {
            this.drone.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 0.8D);
          }
          // Shoot target
          if (this.attackTime <= 0) {
            ++this.attackStep;
            if (this.attackStep == 1) {
              this.attackTime = 30;
            } else if (this.attackStep <= 5) {
              this.attackTime = 4;
            } else {
              this.attackTime = 30;
              this.attackStep = 0;
              this.drone.setTarget((LivingEntity) null);
            }
            if (this.attackStep > 1) {
              if (!this.drone.isSilent()) {
                this.drone.playSound(this.getDroneShootSound(), 1.5F, 1.0F);
              }
              for (int i = 0; i < 1; ++i) {
                this.drone.performRangedAttack(livingentity, this.maxAttackDistance);
              }
            }
            this.drone.getLookControl().setLookAt(livingentity, 30.0F, 90.0F);
          }
        }
      }
      super.tick();
    }
  }

  protected SoundEvent getDroneShootSound() {
    return (SoundEvent) SoundInit.GUNNER_DRONE_SHOOT.get();
  }
}

package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Arrow;
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
      double d0 = this.drone.distanceToSqr(livingentity);
      boolean flag = this.drone.getSensing().hasLineOfSight(livingentity);
      Vec3 vec3a = this.drone.getLookAngle();
      Vec3 vec3b = this.drone.getLookAngle().multiply(1.0D, 1.0D, 1.0D).normalize().scale((double) 0.01D).reverse();
      double d1 = livingentity.getX() - this.drone.getX() + vec3a.x * 4.0D;
      double d2 = livingentity.getEyeHeight() - this.drone.getEyeY();
      double d3 = livingentity.getZ() - this.drone.getZ() + vec3a.z * 4.0D;

      if (!flag) {
        this.drone.getNavigation().moveTo(livingentity, 0.8D);
      }
      // Elevate drone above target
      if (livingentity.getEyeY() > this.drone.getEyeY()) {
        Vec3 vec3c = this.drone.getDeltaMovement();
        this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(0.0D, ((double) 0.1F - vec3b.y) * (double) 0.1F, 0.0D));
      }
      if (d0 < (double) this.maxAttackDistance && flag) {
        this.drone.getLookControl().setLookAt(livingentity, 30.0F, 90.0F);
        // Keep distance with target
        if (d0 < (double) this.maxAttackDistance * 0.3 && flag) {
          this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(vec3b.x, 0.0D, vec3b.z));
        }
        // Get closer to target
        if (d0 > (double) this.maxAttackDistance * 0.75 && flag) {
          this.drone.getNavigation().moveTo(livingentity, 0.8D);
        }

        if (this.attackTime <= 0) {
          ++this.attackStep;
          if (this.attackStep == 1) {
            this.attackTime = 30;
          } else if (this.attackStep <= 5) {
            this.attackTime = 4;
          } else  {
            this.attackTime = 30;
            this.attackStep = 0;
            this.drone.setTarget((LivingEntity) null);
          }
          if (this.attackStep > 1) {
            this.drone.playSound(this.getDroneShootSound(), 0.4F, 1.0F);
            for (int i = 0; i < 1; ++i) {
              Arrow bullet = new Arrow(this.drone.level, this.drone);
              bullet.shoot(d1, d2, d3, 3.0F, 1.0F);
              this.drone.level.addFreshEntity(bullet);
            }
          }
          this.drone.getLookControl().setLookAt(livingentity, 30.0F, 90.0F);
        }
      }
      super.tick();
    }
  }

  protected SoundEvent getDroneShootSound() {
    return (SoundEvent) SoundInit.GUNNER_DRONE_SHOOT.get();
  }
}

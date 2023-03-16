package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.SmallFireball;
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
      double d1 = livingentity.getX() - this.drone.getX();
      double d2 = livingentity.getY(0.75D) - this.drone.getY(0.75D);
      double d3 = livingentity.getZ() - this.drone.getZ();
      Vec3 vec3b = this.drone.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) 0.1D).reverse();

      if (!flag) {
        this.drone.getMoveControl().setWantedPosition(livingentity.getX(), livingentity.getY(), livingentity.getZ(), 0.8D);
      } else {
        this.drone.getLookControl().setLookAt(livingentity, 30.0F, 90.0F);
        // Elevate drone above target
        if (livingentity.getEyeY() > this.drone.getEyeY()) {
          Vec3 vec3c = this.drone.getDeltaMovement();
          this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(0.0D, ((double) 0.1F - vec3c.y) * (double) 0.01F, 0.0D));
        }
        if (d0 < (double) this.maxAttackDistance && flag) {
          // Keep distance with target
          if (d0 < (double) this.maxAttackDistance * 0.5) {
            this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(vec3b.x, 0.0D, vec3b.z));
          }
          // Get closer to target
          if (d0 > (double) this.maxAttackDistance * 0.75) {
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
              double d4 = Math.sqrt(Math.sqrt(d0)) * 0.5D;
              if (!this.drone.isSilent()) {
                this.drone.playSound(this.getDroneShootSound(), 0.4F, 1.0F);
              }
              for (int i = 0; i < 1; ++i) {
                Arrow projectile = new Arrow(this.drone.level, this.drone);
                projectile.shoot(d1, d2, d3, 3.0F, 1.0F);
                this.drone.level.addFreshEntity(projectile);
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

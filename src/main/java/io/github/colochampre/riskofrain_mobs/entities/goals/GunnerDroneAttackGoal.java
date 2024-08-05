package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.allies.AbstractDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.utils.EntityUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class GunnerDroneAttackGoal extends Goal {
  private final GunnerDroneEntity drone;
  private final float maxAttackDistance;
  private int attackStep;
  private int attackTime;

  public GunnerDroneAttackGoal(GunnerDroneEntity entity, float maxAttackDistance) {
    this.drone = entity;
    this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity target = this.drone.getTarget();
    return target != null && target.isAlive() && this.drone.canAttack(target);
  }

  @Override
  public void start() {
    super.start();
    this.drone.setAggressive(true);
    this.attackStep = 0;
  }

  public void stop() {
    this.drone.setActiveAttackTarget(0);
    this.drone.setTarget(null);
    this.drone.setAggressive(false);
    this.drone.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  @Override
  public void tick() {
    super.tick();
    LivingEntity target = this.drone.getTarget();
    --this.attackTime;
    if (target != null) {
      boolean canSee = this.drone.getSensing().hasLineOfSight(target);
      double distance = this.drone.distanceToSqr(target);
      if (!canSee) {
        this.drone.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.8D);
      } else {
        this.drone.getLookControl().setLookAt(target, 30.0F, 90.0F);
        this.drone.setActiveAttackTarget(target.getId());
        if (distance < (double) this.maxAttackDistance) {
          doCombatMovements(target, distance);
          shootTarget(target);
        }
      }
    }
  }

  private void doCombatMovements(LivingEntity target, double distance) {
    Vec3 vec3a = this.drone.getDeltaMovement();
    Vec3 vec3b = this.drone.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) 0.05D).reverse();
    double heightAboveSurface = EntityUtils.getHeightAboveSurface(this.drone);
    int maxHeightAllowed = AbstractDroneEntity.MAX_FLIGHT_HEIGHT;
    // Elevate drone above target
    if (target.getEyeY() > this.drone.getEyeY() && heightAboveSurface < maxHeightAllowed) {
      this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
      this.drone.hasImpulse = true;
    }
    // Keep distance with target
    if (distance < (double) this.maxAttackDistance * 0.3) {
      this.drone.setDeltaMovement(this.drone.getDeltaMovement().add(vec3b.x / 2, 0.0D, vec3b.z / 2));
      this.drone.hasImpulse = true;
    }
    // Get closer to target
    if (distance > (double) this.maxAttackDistance * 0.75 && !(distance < (double) this.maxAttackDistance * 0.5)) {
      this.drone.getMoveControl().setWantedPosition(target.getX(), this.drone.getY(), target.getZ(), 0.8D);
    }
  }

  private void shootTarget(LivingEntity target) {
    if (this.attackTime <= 0) {
      ++this.attackStep;
      if (this.attackStep == 1) {
        this.attackTime = 30;
      } else if (this.attackStep <= 5) {
        this.attackTime = 4;
      } else {
        this.attackTime = 30;
        this.attackStep = 0;
        this.drone.setActiveAttackTarget(0);
        this.drone.setTarget(null);
      }
      if (this.attackStep > 1) {
        if (!this.drone.isSilent()) {
          this.drone.playSound(this.getDroneShootSound(), 1.5F, 1.0F);
        }
        for (int i = 0; i < 1; ++i) {
          this.drone.performRangedAttack(target, this.maxAttackDistance);
        }
      }
      this.drone.getLookControl().setLookAt(target, 30.0F, 90.0F);
    }
  }

  protected SoundEvent getDroneShootSound() {
    return SoundInit.DRONE_SHOOT.get();
  }
}

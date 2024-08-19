package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class GunnerTurretAttackGoal extends Goal {
  private final GunnerTurretEntity drone;
  private final float maxAttackDistance;
  private int attackStep;
  private int attackTime;

  public GunnerTurretAttackGoal(GunnerTurretEntity entity, float maxAttackDistance) {
    this.drone = entity;
    this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  @Override
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

  @Override
  public void stop() {
    this.drone.setActiveAttackTarget(0);
    this.drone.setTarget(null);
    this.drone.setAggressive(false);
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
      if (canSee) {
        this.drone.getLookControl().setLookAt(target, 30.0F, 90.0F);
        this.drone.setActiveAttackTarget(target.getId());
        if (distance < (double) this.maxAttackDistance) {
          shootTarget(target);
        }
      }
    }
  }

  private void shootTarget(LivingEntity target) {
    if (this.attackTime <= 0) {
      ++this.attackStep;
      if (this.attackStep == 1) {
        this.attackTime = 40;
      } else if (this.attackStep <= 40) {
        this.attackTime = 2;
      } else {
        this.attackTime = 40;
        this.attackStep = 0;
        this.drone.setActiveAttackTarget(0);
        this.drone.setTarget(null);
      }
      if (this.attackStep > 1) {
        if (!this.drone.isSilent()) {
          this.drone.playSound(this.getShootSound(), 1.5F, 1.6F);
        }
        for (int i = 0; i < 1; ++i) {
          this.drone.performRangedAttack(target, this.maxAttackDistance);
        }
      }
      this.drone.getLookControl().setLookAt(target, 30.0F, 90.0F);
    }
  }


  protected SoundEvent getShootSound() {
    return SoundInit.DRONE_SHOOT.get();
  }
}

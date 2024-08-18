package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.enemies.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class StoneGolemAttackGoal extends Goal {
  private final StoneGolemEntity golem;
  private int laserAttackTick;
  private int laserCooldown = 0;
  private final double speedModifier;
  private final boolean followingTargetEvenIfNotSeen;
  private Path path;
  private double pathedTargetX;
  private double pathedTargetY;
  private double pathedTargetZ;
  private int ticksUntilNextPathRecalculation;
  private int ticksUntilNextAttack;
  private int failedPathFindingPenalty = 0;
  private boolean canPenalize = false;

  public StoneGolemAttackGoal(StoneGolemEntity entity, double speed, boolean memory) {
    this.golem = entity;
    this.speedModifier = speed;
    this.followingTargetEvenIfNotSeen = memory;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity target = this.golem.getTarget();
    if (target == null) {
      return false;
    } else if (!target.isAlive()) {
      return false;
    } else {
      if (canPenalize) {
        if (--this.ticksUntilNextPathRecalculation <= 0) {
          this.path = this.golem.getNavigation().createPath(target, 0);
          this.ticksUntilNextPathRecalculation = 4 + this.golem.getRandom().nextInt(7);
          return this.path != null;
        } else {
          return true;
        }
      }
      this.path = this.golem.getNavigation().createPath(target, 0);
      if (this.path != null) {
        return true;
      } else {
        return this.getAttackReachSqr(target) >= this.golem.distanceToSqr(target.getX(), target.getY(), target.getZ());
      }
    }
  }

  public boolean canContinueToUse() {
    LivingEntity target = this.golem.getTarget();
    if (target == null) {
      return false;
    } else if (!target.isAlive()) {
      return false;
    } else if (!this.followingTargetEvenIfNotSeen) {
      return !this.golem.getNavigation().isDone();
    } else {
      return !(target instanceof Player) || !target.isSpectator() && !((Player) target).isCreative();
    }
  }

  public void start() {
    this.laserAttackTick = -10;
    LivingEntity livingentity = this.golem.getTarget();
    if (livingentity != null) {
      this.golem.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
    }
    this.golem.getNavigation().moveTo(this.path, this.speedModifier);
    this.golem.setAggressive(true);
    this.ticksUntilNextPathRecalculation = 0;
    this.ticksUntilNextAttack = 0;
  }

  public void stop() {
    this.golem.setActiveAttackTarget(0);
    this.golem.setTarget(null);
    this.golem.setAggressive(false);
    this.golem.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  public void tick() {
    super.tick();
    LivingEntity target = this.golem.getTarget();
    --this.laserCooldown;

    if (target != null) {
      boolean canSee = this.golem.hasLineOfSight(target);
      this.golem.getLookControl().setLookAt(target, 90.0F, 90.0F);
      meleeAttackTick(target);
      if (!canSee) {
        this.golem.setActiveAttackTarget(0);
        this.golem.setTarget(null);
      } else {
        laserTick(target);
      }
    }
  }

  private void meleeAttackTick(LivingEntity target) {
    double distance = this.golem.getPerceivedTargetDistanceSquareForMeleeAttack(target);
    this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
    if ((this.followingTargetEvenIfNotSeen || this.golem.getSensing().hasLineOfSight(target)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || target.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.golem.getRandom().nextFloat() < 0.05F)) {
      this.pathedTargetX = target.getX();
      this.pathedTargetY = target.getY();
      this.pathedTargetZ = target.getZ();
      this.ticksUntilNextPathRecalculation = 4 + this.golem.getRandom().nextInt(7);
      if (this.canPenalize) {
        this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
        if (this.golem.getNavigation().getPath() != null) {
          net.minecraft.world.level.pathfinder.Node finalPathPoint = this.golem.getNavigation().getPath().getEndNode();
          if (finalPathPoint != null && target.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
            failedPathFindingPenalty = 0;
          else
            failedPathFindingPenalty += 10;
        } else {
          failedPathFindingPenalty += 10;
        }
      }
      if (distance > 1024.0D) {
        this.ticksUntilNextPathRecalculation += 10;
      } else if (distance > 256.0D) {
        this.ticksUntilNextPathRecalculation += 5;
      }
      if (!this.golem.getNavigation().moveTo(target, this.speedModifier)) {
        this.ticksUntilNextPathRecalculation += 15;
      }
      this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
    }
    this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
    this.checkAndPerformAttack(target, distance);
  }

  protected void laserTick(LivingEntity target) {
    if (this.laserCooldown <= 0) {
      ++this.laserAttackTick;
    }
    if (this.laserAttackTick == 0) {
      this.golem.setActiveAttackTarget(target.getId());
      if (!this.golem.isSilent()) {
        this.golem.playSound(this.getLaserChargeSound(), 2.0F, 1.0F);
      }
    } else if (this.laserAttackTick >= this.golem.getAttackDuration()) {
      float f = 2.0F;
      Vec3 vec3 = this.golem.getLookAngle().multiply(1.0D, 1.0D, 1.0D).normalize().scale(3.0);

      if (this.golem.level().getDifficulty() == Difficulty.HARD) {
        f *= 2.0F;
      }
      this.golem.playSound(this.getLaserFireSound(), 3.0F, 1.0F);
      target.playSound(this.getLaserFireSound(), 3.0F, 1.0F);
      target.hurt(golem.damageSources().indirectMagic(this.golem, this.golem), f);
      target.hurt(golem.damageSources().mobAttack(this.golem), this.golem.getAttackDamage() / 2);
      target.addDeltaMovement(vec3);
      this.golem.setActiveAttackTarget(0);
      this.golem.setTarget(null);
      this.laserCooldown = 85;
    }
  }

  protected void checkAndPerformAttack(LivingEntity livingEntity, double distance) {
    double d0 = this.getAttackReachSqr(livingEntity);
    if (distance <= d0 && this.ticksUntilNextAttack <= 0) {
      this.resetAttackCooldown();
      this.golem.swing(InteractionHand.MAIN_HAND);
      this.golem.doHurtTarget(livingEntity);
      this.golem.strongKnockback(livingEntity);
    }
  }

  protected void resetAttackCooldown() {
    this.ticksUntilNextAttack = this.adjustedTickDelay(40);
  }

  protected SoundEvent getLaserChargeSound() {
    return SoundInit.STONE_GOLEM_LASER_CHARGE.get();
  }

  protected SoundEvent getLaserFireSound() {
    return SoundInit.STONE_GOLEM_LASER_FIRE.get();
  }

  protected double getAttackReachSqr(LivingEntity entity) {
    return (this.golem.getBbWidth() * 1.5F * this.golem.getBbWidth() * 1.5F + entity.getBbWidth());
  }
}

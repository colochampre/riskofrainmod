package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
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
  //private final int attackInterval = 40;
  //private static final long COOLDOWN_BETWEEN_CAN_USE_CHECKS = 40L;
  private int failedPathFindingPenalty = 0;
  private boolean canPenalize = false;

  public StoneGolemAttackGoal(StoneGolemEntity entity, double speed, boolean memory) {
    this.golem = entity;
    this.speedModifier = speed;
    this.followingTargetEvenIfNotSeen = memory;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity livingentity = this.golem.getTarget();
    if (livingentity == null) {
      return false;
    } else if (!livingentity.isAlive()) {
      return false;
    } else {
      if (canPenalize) {
        if (--this.ticksUntilNextPathRecalculation <= 0) {
          this.path = this.golem.getNavigation().createPath(livingentity, 0);
          this.ticksUntilNextPathRecalculation = 4 + this.golem.getRandom().nextInt(7);
          return this.path != null;
        } else {
          return true;
        }
      }
      this.path = this.golem.getNavigation().createPath(livingentity, 0);
      if (this.path != null) {
        return true;
      } else {
        return this.getAttackReachSqr(livingentity) >= this.golem.distanceToSqr(livingentity.getX(), livingentity.getY(), livingentity.getZ());
      }
    }
  }

  public boolean canContinueToUse() {
    LivingEntity livingentity = this.golem.getTarget();
    if (livingentity == null) {
      return false;
    } else if (!livingentity.isAlive()) {
      return false;
    } else if (!this.followingTargetEvenIfNotSeen) {
      return !this.golem.getNavigation().isDone();
    } else {
      return !(livingentity instanceof Player) || !livingentity.isSpectator() && !((Player) livingentity).isCreative();
    }
  }

  public void start() {
    // Laser
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
    this.golem.setTarget((LivingEntity) null);
    this.golem.setAggressive(false);
    this.golem.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  public void tick() {
    LivingEntity livingentity = this.golem.getTarget();
    --this.laserCooldown;

    if (livingentity != null) {
      boolean flag = this.golem.hasLineOfSight(livingentity);
      this.golem.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
      meleeAttackTick(livingentity);

      if (!flag) {
        this.golem.setTarget((LivingEntity) null);
      } else {
        laserTick(livingentity);
        super.tick();
      }
    }
  }

  private void meleeAttackTick(LivingEntity livingentity) {
    double d0 = this.golem.getMeleeAttackRangeSqr(livingentity);
    this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
    if ((this.followingTargetEvenIfNotSeen || this.golem.getSensing().hasLineOfSight(livingentity)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || livingentity.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.golem.getRandom().nextFloat() < 0.05F)) {
      this.pathedTargetX = livingentity.getX();
      this.pathedTargetY = livingentity.getY();
      this.pathedTargetZ = livingentity.getZ();
      this.ticksUntilNextPathRecalculation = 4 + this.golem.getRandom().nextInt(7);
      if (this.canPenalize) {
        this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
        if (this.golem.getNavigation().getPath() != null) {
          net.minecraft.world.level.pathfinder.Node finalPathPoint = this.golem.getNavigation().getPath().getEndNode();
          if (finalPathPoint != null && livingentity.distanceToSqr(finalPathPoint.x, finalPathPoint.y, finalPathPoint.z) < 1)
            failedPathFindingPenalty = 0;
          else
            failedPathFindingPenalty += 10;
        } else {
          failedPathFindingPenalty += 10;
        }
      }
      if (d0 > 1024.0D) {
        this.ticksUntilNextPathRecalculation += 10;
      } else if (d0 > 256.0D) {
        this.ticksUntilNextPathRecalculation += 5;
      }
      if (!this.golem.getNavigation().moveTo(livingentity, this.speedModifier)) {
        this.ticksUntilNextPathRecalculation += 15;
      }
      this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
    }
    this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
    this.checkAndPerformAttack(livingentity, d0);
  }

  protected void laserTick(LivingEntity livingentity) {
    if (this.laserCooldown <= 0) {
      ++this.laserAttackTick;
    }
    if (this.laserAttackTick == 0) {
      this.golem.setActiveAttackTarget(livingentity.getId());
      if (!this.golem.isSilent()) {
        this.golem.level.broadcastEntityEvent(this.golem, (byte) 21);
        this.golem.playSound(this.getLaserChargeSound(), 2.0F, 1.0F);
      }
    } else if (this.laserAttackTick >= this.golem.getAttackDuration()) {
      float f = 2.0F;
      Vec3 vec3 = this.golem.getLookAngle().multiply(1.0D, 1.0D, 1.0D).normalize().scale(3.0);

      if (this.golem.level.getDifficulty() == Difficulty.HARD) {
        f *= 2.0F;
      }
      this.golem.playSound(this.getLaserFireSound(), 3.0F, 1.0F);
      livingentity.playSound(this.getLaserFireSound(), 3.0F, 1.0F);
      livingentity.hurt(DamageSource.indirectMagic(this.golem, this.golem), f);
      livingentity.hurt(DamageSource.mobAttack(this.golem), this.golem.getAttackDamage() / 2);
      livingentity.setDeltaMovement(livingentity.getDeltaMovement().add(vec3));
      this.golem.setTarget((LivingEntity) null);
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
    return (SoundEvent) SoundInit.STONE_GOLEM_LASER_CHARGE.get();
  }

  protected SoundEvent getLaserFireSound() {
    return (SoundEvent) SoundInit.STONE_GOLEM_LASER_FIRE.get();
  }

  protected double getAttackReachSqr(LivingEntity p_25556_) {
    return (double) (this.golem.getBbWidth() * 1.5F * this.golem.getBbWidth() * 1.5F + p_25556_.getBbWidth());
  }
}

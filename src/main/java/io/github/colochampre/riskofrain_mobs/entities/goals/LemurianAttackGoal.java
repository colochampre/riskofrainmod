package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.entities.enemies.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.projectiles.LemurianFireballEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.pathfinder.Path;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class LemurianAttackGoal extends Goal {
  private final LemurianEntity lemurian;
  private final double speedModifier;
  private final float maxAttackDistance;
  private int attackStep;
  private int attackTime;
  private int lastSeen;
  private int strafingTime = -1;
  private boolean strafingClockwise;
  private boolean strafingBackwards;
  private Path path;
  private double pathedTargetX;
  private double pathedTargetY;
  private double pathedTargetZ;
  private int ticksUntilNextAttack;
  private int failedPathFindingPenalty = 0;
  private int ticksUntilNextPathRecalculation;
  private final boolean followingTargetEvenIfNotSeen;
  private final boolean canPenalize = false;

  public LemurianAttackGoal(LemurianEntity entity, float maxAttackDistanceIn, double speedModifierIn, boolean memory) {
    this.lemurian = entity;
    this.speedModifier = speedModifierIn;
    this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
    this.followingTargetEvenIfNotSeen = memory;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    LivingEntity target = this.lemurian.getTarget();
    if (target == null) {
      return false;
    } else if (!target.isAlive()) {
      return false;
    } else {
      if (canPenalize) {
        if (--this.ticksUntilNextPathRecalculation <= 0) {
          this.path = this.lemurian.getNavigation().createPath(target, 0);
          this.ticksUntilNextPathRecalculation = 4 + this.lemurian.getRandom().nextInt(7);
          return this.path != null;
        } else {
          return true;
        }
      }
      this.path = this.lemurian.getNavigation().createPath(target, 0);
      if (this.path != null) {
        return true;
      } else {
        return this.getAttackReachSqr(target) >= this.lemurian.distanceToSqr(target.getX(), target.getY(), target.getZ());
      }
    }
  }

  public boolean canContinueToUse() {
    LivingEntity target = this.lemurian.getTarget();
    if (target == null) {
      return false;
    } else if (!target.isAlive()) {
      return false;
    } else if (!this.followingTargetEvenIfNotSeen) {
      return !this.lemurian.getNavigation().isDone();
    } else {
      return !(target instanceof Player) || !target.isSpectator() && !((Player) target).isCreative();
    }
  }

  public void start() {
    LivingEntity livingentity = this.lemurian.getTarget();
    this.attackStep = 0;
    if (livingentity != null) {
      this.lemurian.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
    }
    this.lemurian.getNavigation().moveTo(this.path, this.speedModifier);
    this.lemurian.setAggressive(true);
    this.ticksUntilNextPathRecalculation = 0;
    this.ticksUntilNextAttack = 0;
  }

  public void stop() {
    this.lastSeen = 0;
    this.lemurian.setTarget(null);
    this.lemurian.setAggressive(false);
    this.lemurian.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  @Override
  public void tick() {
    super.tick();
    LivingEntity target = this.lemurian.getTarget();
    --this.attackTime;

    if (target != null) {
      double d0 = this.lemurian.distanceToSqr(target);
      boolean canSee = this.lemurian.getSensing().hasLineOfSight(target);
      if (canSee) {
        ++this.lastSeen;
      } else {
        --this.lastSeen;
      }
      if (lemurian.isEvolved() && RoRConfig.SERVER.ENABLE_FIREBALL_ATTACK.get()) {
        this.strafingTick(target, d0, canSee);
        this.fireballAttackTick(target, d0, canSee);
        if (d0 < this.maxAttackDistance * 0.3) {
          this.meleeAttackTick(target);
        }
      } else {
        this.meleeAttackTick(target);
      }
    }
  }

  private void strafingTick(LivingEntity entity, double distance, boolean canSee) {
    if (distance < (double) this.maxAttackDistance) {
      if (distance > (double) this.maxAttackDistance * 0.3 && canSee) {
        if (!(distance > (double) this.maxAttackDistance) && this.lastSeen >= 20) {
          this.lemurian.getNavigation().stop();
          ++this.strafingTime;
        } else {
          this.lemurian.getNavigation().moveTo(entity, this.speedModifier);
          this.strafingTime = -1;
        }
        if (this.strafingTime >= 20) {
          if ((double) this.lemurian.getRandom().nextFloat() < 0.3D) {
            this.strafingClockwise = !this.strafingClockwise;
          }
          if ((double) this.lemurian.getRandom().nextFloat() < 0.3D) {
            this.strafingBackwards = !this.strafingBackwards;
          }
          this.strafingTime = 0;
        }
        if (this.strafingTime > -1) {
          if (distance > (double) (this.maxAttackDistance * 0.8333F)) {
            this.strafingBackwards = false;
          } else if (distance < (double) (this.maxAttackDistance * 0.6F)) {
            this.strafingBackwards = true;
          }
          this.lemurian.getMoveControl().strafe(this.strafingBackwards ? -0.2F : 0.4F, this.strafingClockwise ? 0.4F : -0.4F);
          this.lemurian.lookAt(entity, 30.0F, 30.0F);
        } else {
          this.lemurian.getLookControl().setLookAt(entity, 30.0F, 30.0F);
        }
      }
    }
  }

  private void meleeAttackTick(LivingEntity target) {
    double distance = this.lemurian.distanceToSqr(target);
    this.ticksUntilNextPathRecalculation = Math.max(this.ticksUntilNextPathRecalculation - 1, 0);
    if ((this.followingTargetEvenIfNotSeen || this.lemurian.getSensing().hasLineOfSight(target)) && this.ticksUntilNextPathRecalculation <= 0 && (this.pathedTargetX == 0.0D && this.pathedTargetY == 0.0D && this.pathedTargetZ == 0.0D || target.distanceToSqr(this.pathedTargetX, this.pathedTargetY, this.pathedTargetZ) >= 1.0D || this.lemurian.getRandom().nextFloat() < 0.05F)) {
      this.pathedTargetX = target.getX();
      this.pathedTargetY = target.getY();
      this.pathedTargetZ = target.getZ();
      this.ticksUntilNextPathRecalculation = 4 + this.lemurian.getRandom().nextInt(7);
      if (this.canPenalize) {
        this.ticksUntilNextPathRecalculation += failedPathFindingPenalty;
        if (this.lemurian.getNavigation().getPath() != null) {
          net.minecraft.world.level.pathfinder.Node finalPathPoint = this.lemurian.getNavigation().getPath().getEndNode();
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
      if (!this.lemurian.getNavigation().moveTo(target, this.speedModifier)) {
        this.ticksUntilNextPathRecalculation += 15;
      }
      this.ticksUntilNextPathRecalculation = this.adjustedTickDelay(this.ticksUntilNextPathRecalculation);
    }
    this.ticksUntilNextAttack = Math.max(this.ticksUntilNextAttack - 1, 0);
    this.checkAndPerformAttack(target, distance);
  }

  private void fireballAttackTick(LivingEntity entity, double distance, boolean canSee) {
    if ((this.lemurian.getNavigation().isDone() || distance > this.maxAttackDistance * 0.3) && distance < (double) this.maxAttackDistance && canSee) {
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
          double d1 = entity.getX() - this.lemurian.getX();
          double d2 = entity.getY(0.6D) - (0.6D + this.lemurian.getY(0.6D));
          double d3 = entity.getZ() - this.lemurian.getZ();
          double d4 = Math.sqrt(Math.sqrt((float) distance)) * 0.25F;
          Vec3 vec3 = this.lemurian.getViewVector(1.0F);
          if (!this.lemurian.isSilent()) {
            this.lemurian.playSound(getFireballSound(), 1.5F, 1.0F);
          }
          LemurianFireballEntity fireball = new LemurianFireballEntity(this.lemurian.level(), this.lemurian, d1 * d4, d2, d3 * d4);
          fireball.setPos(fireball.getX() + vec3.x * 0.8D, this.lemurian.getY(0.6D) + 0.6D, fireball.getZ() + vec3.z * 0.8D);
          this.lemurian.level().addFreshEntity(fireball);
        }
      }
      this.lemurian.getLookControl().setLookAt(entity, 30.0F, 30.0F);
    }
  }

  protected void checkAndPerformAttack(LivingEntity livingEntity, double distance) {
    double d0 = this.getAttackReachSqr(livingEntity);
    if (distance <= d0 && this.ticksUntilNextAttack <= 0) {
      this.resetAttackCooldown();
      this.lemurian.swing(InteractionHand.MAIN_HAND);
      this.lemurian.doHurtTarget(livingEntity);
    }
  }

  protected void resetAttackCooldown() {
    this.ticksUntilNextAttack = this.adjustedTickDelay(20);
  }

  protected double getAttackReachSqr(LivingEntity entity) {
    return (this.lemurian.getBbWidth() * 2.0F * this.lemurian.getBbWidth() * 2.0F + entity.getBbWidth());
  }

  protected SoundEvent getFireballSound() {
    return (SoundEvent) SoundInit.LEMURIAN_FIREBALL.get();
  }
}

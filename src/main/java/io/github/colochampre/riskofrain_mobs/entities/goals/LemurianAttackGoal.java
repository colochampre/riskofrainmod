package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.LemurianFireballEntity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class LemurianAttackGoal extends Goal {
  private final LemurianEntity lemurian;
  private final double moveSpeedAmp;
  private final float maxAttackDistance;
  private int attackStep;
  private int attackTime;
  private int lastSeen;
  private boolean strafingClockwise;
  private boolean strafingBackwards;
  private int strafingTime = -1;

  public LemurianAttackGoal(LemurianEntity entity, float maxAttackDistanceIn, double moveSpeedAmpIn) {
    this.lemurian = entity;
    this.moveSpeedAmp = moveSpeedAmpIn;
    this.maxAttackDistance = maxAttackDistanceIn * maxAttackDistanceIn;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  @Override
  public boolean canUse() {
    LivingEntity livingentity = this.lemurian.getTarget();
    return livingentity != null && livingentity.isAlive() && this.lemurian.canAttack(livingentity);
  }

  public void start() {
    this.attackStep = 0;
  }

  public void stop() {
    this.lastSeen = 0;
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  @Override
  public void tick() {
    --this.attackTime;
    LivingEntity livingentity = this.lemurian.getTarget();
    if (livingentity != null) {
      double d0 = this.lemurian.distanceToSqr(livingentity);
      boolean flag = this.lemurian.getSensing().hasLineOfSight(livingentity);
      if (flag) {
        ++this.lastSeen;
      } else {
        --this.lastSeen;
      }
      /* Strafing */
      this.strafingTick(livingentity, d0, flag);
      /* Fireball attack */
      this.fireballAttackTick(livingentity, d0, flag);
      super.tick();
    }
  }

  private void strafingTick(LivingEntity entity, double distance, boolean canSee) {
    if (distance < (double) this.maxAttackDistance) {
      if (distance > (double) this.maxAttackDistance * 0.3 && canSee) {
        if (!(distance > (double) this.maxAttackDistance) && this.lastSeen >= 20) {
          this.lemurian.getNavigation().stop();
          ++this.strafingTime;
        } else {
          this.lemurian.getNavigation().moveTo(entity, this.moveSpeedAmp);
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

  private void fireballAttackTick(LivingEntity entity, double distance, boolean canSee) {
    if (((distance > this.maxAttackDistance * 0.3) || (this.lemurian.getNavigation().isDone())) && (distance < (double) this.maxAttackDistance) && canSee) {
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
            /* Lemurian fireball sound */
            this.lemurian.level.levelEvent((Player) null, 1018, this.lemurian.blockPosition(), 0);
          }
          LemurianFireballEntity fireball = new LemurianFireballEntity(this.lemurian.level, this.lemurian, d1 * d4, d2, d3 * d4);
          fireball.setPos(fireball.getX() + vec3.x * 0.8D, this.lemurian.getY(0.6D) + 0.6D, fireball.getZ() + vec3.z * 0.8D);
          this.lemurian.level.addFreshEntity(fireball);
        }
      }
      this.lemurian.getLookControl().setLookAt(entity, 30.0F, 30.0F);
    }
  }
}

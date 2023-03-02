package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.Difficulty;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

import java.util.EnumSet;

public class StoneGolemLaserGoal extends Goal {
  private final StoneGolemEntity golem;
  private int laserAttackTick;
  private int laserCooldown;

  public StoneGolemLaserGoal(StoneGolemEntity entity) {
    this.golem = entity;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity livingentity = this.golem.getTarget();
    return livingentity != null && livingentity.isAlive();
  }

  public boolean canContinueToUse() {
    return super.canContinueToUse() && (this.golem.getTarget() != null && this.golem.distanceToSqr(this.golem.getTarget()) > 9.0D);
  }

  public void start() {
    this.laserAttackTick = -10;
    LivingEntity livingentity = this.golem.getTarget();
    if (livingentity != null) {
      this.golem.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
    }
  }

  public void stop() {
    this.golem.setActiveAttackTarget(0);
    this.golem.setTarget((LivingEntity) null);
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  public void tick() {
    LivingEntity livingentity = this.golem.getTarget();
    --this.laserCooldown;
    boolean flag = this.golem.hasLineOfSight(livingentity);
    if (livingentity != null) {
      this.golem.getLookControl().setLookAt(livingentity, 30.0F, 30.0F);
      if (!flag) {
        this.golem.setTarget((LivingEntity) null);
      } else {
        laserTick(livingentity);

        super.tick();
      }
    }
  }

  protected void laserTick(LivingEntity livingentity) {
    if(this.laserCooldown <= 0) {
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
      if (this.golem.level.getDifficulty() == Difficulty.HARD) {
        f *= 2.0F;
      }

      this.golem.playSound(this.getLaserFireSound(), 2.0F, 1.0F);
      this.golem.strongKnockback(livingentity);
      livingentity.hurt(DamageSource.indirectMagic(this.golem, this.golem), f);
      livingentity.hurt(DamageSource.mobAttack(this.golem), this.golem.getAttackDamage() / 2);
      this.golem.setTarget((LivingEntity) null);
      this.laserCooldown = 85;
    }
  }

  protected SoundEvent getLaserChargeSound() {
    return (SoundEvent) SoundInit.STONE_GOLEM_LASER_CHARGE.get();
  }

  protected SoundEvent getLaserFireSound() {
    return (SoundEvent) SoundInit.STONE_GOLEM_LASER_FIRE.get();
  }
}

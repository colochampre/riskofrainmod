package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.entities.enemies.WispEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.utils.EntityUtils;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.Goal;
import net.minecraft.world.phys.Vec3;

import java.util.EnumSet;

public class WispAttackGoal extends Goal {
  private final WispEntity wisp;
  private final float maxAttackDistance;
  private int hitScanAttackTick;
  private int hitScanCooldown = 0;

  public WispAttackGoal(WispEntity entity, float maxAttackDistance) {
    this.wisp = entity;
    this.maxAttackDistance = maxAttackDistance * maxAttackDistance;
    this.setFlags(EnumSet.of(Goal.Flag.MOVE, Goal.Flag.LOOK));
  }

  public boolean canUse() {
    LivingEntity target = this.wisp.getTarget();
    return target != null && target.isAlive() && this.wisp.canAttack(target);
  }

  public void start() {
    super.start();
    // Laser
    this.hitScanAttackTick = -10;
    LivingEntity livingentity = this.wisp.getTarget();
    if (livingentity != null) {
      this.wisp.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
    }
    this.wisp.setAggressive(true);
  }

  public void stop() {
    this.wisp.setActiveAttackTarget(0);
    this.wisp.setTarget(null);
    this.wisp.setAggressive(false);
    this.wisp.getNavigation().stop();
  }

  public boolean requiresUpdateEveryTick() {
    return true;
  }

  @Override
  public void tick() {
    super.tick();
    LivingEntity target = this.wisp.getTarget();
    --this.hitScanCooldown;
    if (target != null) {
      boolean canSee = this.wisp.hasLineOfSight(target);
      double distance = this.wisp.distanceToSqr(target);
      this.wisp.getLookControl().setLookAt(target, 90.0F, 90.0F);
      if (!canSee) {
        this.wisp.setTarget(null);
      } else {
        double followRange = this.wisp.getAttributeValue(Attributes.FOLLOW_RANGE);
        double followRangeSqr = followRange * followRange;
        if (distance < followRangeSqr && distance > (double) this.maxAttackDistance / 2) {
          // Get closer to target
          this.wisp.getMoveControl().setWantedPosition(target.getX(), this.wisp.getY(), target.getZ(), 0.8D);
        }
        doCombatMovements(target);
        if (distance < (double) this.maxAttackDistance) {
          hitScanTick(target);
        }
      }
    }
  }

  private void doCombatMovements(LivingEntity target) {
    // Elevate above target
    double heightAboveSurface = EntityUtils.getHeightAboveSurface(this.wisp);
    int maxHeightAllowed = WispEntity.MAX_FLIGHT_HEIGHT;
    Vec3 vec3a = this.wisp.getDeltaMovement();
    if (target.getEyeY() > this.wisp.getEyeY() && heightAboveSurface < maxHeightAllowed) {
      this.wisp.setDeltaMovement(this.wisp.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
    }
  }

  private void hitScanTick(LivingEntity target) {
    if (this.hitScanCooldown <= 0) {
      ++this.hitScanAttackTick;
    }
    if (this.hitScanAttackTick == 0) {
      this.wisp.setActiveAttackTarget(target.getId());
      if (!this.wisp.isSilent()) {
        this.wisp.playSound(this.getAttackChargeSound(), 1.0F, 1.0F);
      }
    } else if (this.hitScanAttackTick >= this.wisp.getAttackDuration()) {
      this.wisp.playSound(this.getAttackFireSound(), 1.0F, 1.0F);
      target.playSound(this.getAttackFireSound(), 1.0F, 1.0F);
      this.wisp.doHitScanParticles(target);
      target.hurt(wisp.damageSources().mobAttack(this.wisp), this.wisp.getAttackDamage());
      this.wisp.setTarget(null);
      this.hitScanCooldown = 80;
    }
  }

  protected SoundEvent getAttackChargeSound() {
    return SoundInit.WISP_ATTACK_CHARGE.get();
  }

  protected SoundEvent getAttackFireSound() {
    return SoundInit.WISP_ATTACK_FIRE.get();
  }
}

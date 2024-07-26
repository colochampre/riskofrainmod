package io.github.colochampre.riskofrain_mobs.entities.goals;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.enemies.WispEntity;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
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
        if (distance < (double) this.maxAttackDistance) {
          doCombatMovements(target, distance);
          hitScanTick(target);
        }
      }
    }
  }

  private void doCombatMovements(LivingEntity target, double distance) {
    Vec3 vec3a = this.wisp.getDeltaMovement();
    Vec3 vec3b = this.wisp.getLookAngle().multiply(1.0D, 0.0D, 1.0D).normalize().scale((double) 0.05D).reverse();
    // Elevate above target
    if (target.getEyeY() > this.wisp.getEyeY()) {
      this.wisp.setDeltaMovement(this.wisp.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
      this.wisp.hasImpulse = true;
    }
    // Get closer to target
    if (distance > (double) this.maxAttackDistance * 0.75 && !(distance < (double) this.maxAttackDistance * 0.5)) {
      this.wisp.getMoveControl().setWantedPosition(target.getX(), target.getY(), target.getZ(), 0.8D);
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
      //if (this.wisp.level().isClientSide) {
        this.doHitParticles(target);
      //}
      target.hurt(wisp.damageSources().mobAttack(this.wisp), this.wisp.getAttackDamage());
      this.wisp.setTarget(null);
      this.hitScanCooldown = 80;
    }
  }

  private void doHitParticles(LivingEntity target) {
    for (int i = 0; i < 3; ++i) {
      double offsetX = this.wisp.getRandom().nextDouble() * 0.5D;
      double offsetY = this.wisp.getRandom().nextDouble();
      double offsetZ = this.wisp.getRandom().nextDouble() * 0.5D;
      double pX = target.getX() + offsetX;
      double pY = target.getY() + offsetY;
      double pZ = target.getZ() + offsetZ;
      RoRmod.LOGGER.info("Generando partículas en: X={} Y={} Z={}", pX, pY, pZ);
      this.wisp.level().addParticle(ParticleTypes.LAVA, pX, pY, pZ, 0.0D, 0.0D, 0.0D);
    }
  }

  protected SoundEvent getAttackChargeSound() {
    return SoundInit.WISP_ATTACK_CHARGE.get();
  }

  protected SoundEvent getAttackFireSound() {
    return SoundInit.WISP_ATTACK_FIRE.get();
  }
}

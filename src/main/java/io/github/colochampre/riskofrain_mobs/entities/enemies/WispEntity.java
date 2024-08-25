package io.github.colochampre.riskofrain_mobs.entities.enemies;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.entities.goals.WispAttackGoal;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.utils.EntityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

public class WispEntity extends Monster implements FlyingAnimal {
  private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(WispEntity.class, EntityDataSerializers.INT);
  private final HurtByTargetGoal hurtByTargetGoal = new HurtByTargetGoal(this);
  public static final int MIN_FLIGHT_HEIGHT = 3;
  public static final int MAX_FLIGHT_HEIGHT = 7;
  private LivingEntity clientSideCachedAttackTarget;
  private int clientSideAttackTime;
  private float currentBodyXRot;
  private float currentBodyZRot;
  private int loopSound;
  private int underWaterTicks;

  public WispEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.moveControl = new FlyingMoveControl(this, 16, true);
    this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_OTHER, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
    this.xpReward = 10;
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(2, new WispAttackGoal(this, 16.0F));
    this.goalSelector.addGoal(3, new WaterAvoidingRandomFlyingGoal(this, 0.5D));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ATTACK_DAMAGE, 2.0D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 24.0D)
            .add(Attributes.MAX_HEALTH, 12.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D);
  }

  protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
    FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level) {
      public boolean isStableDestination(BlockPos pos) {
        return !this.level.getBlockState(pos.below()).isAir();
      }
    };
    flyingpathnavigation.setCanOpenDoors(false);
    flyingpathnavigation.setCanFloat(false);
    flyingpathnavigation.setCanPassDoors(true);
    return flyingpathnavigation;
  }

  public float getWalkTargetValue(@NotNull BlockPos pos, LevelReader level) {
    return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
  }

  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
  }

  @Override
  public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> accessor) {
    super.onSyncedDataUpdated(accessor);
    if (DATA_ID_ATTACK_TARGET.equals(accessor)) {
      this.clientSideAttackTime = 0;
      this.clientSideCachedAttackTarget = null;
    }
  }

  public static boolean canSpawn(EntityType<WispEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return !level.getLevel().isRainingAt(pos) && checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isAlive()) {
      LivingEntity target = this.getTarget();
      if (target == null) {
        this.goalSelector.addGoal(1, this.hurtByTargetGoal);
      }
      this.doHitScanParticleEffects();
      this.doLoopSound();
      this.smokeIfWet();
    }
  }

  @Override
  public void tick() {
    super.tick();
    this.stayElevated();
    this.takeWaterDamage();
    EntityUtils.updateMovementInclinations(this, this.currentBodyXRot, this.currentBodyZRot, newBodyXRot -> this.currentBodyXRot = newBodyXRot, newBodyZRot -> this.currentBodyZRot = newBodyZRot);
  }

  private void doLoopSound() {
    loopSound = (loopSound % 16) + 1;
    if (this.isAlive() && loopSound == 1) {
      this.playSound(this.getLoopSound(), 0.1F, 1.0F);
    }
  }

  private void smokeIfWet() {
    if (this.isInWaterRainOrBubble()) {
      EntityUtils.doParticlesAtEntity(this, ParticleTypes.LARGE_SMOKE, 2);
    }
  }

  private void stayElevated() {
    LivingEntity target = this.getTarget();
    double heightAboveGround = EntityUtils.getHeightAboveSurface(this);
    Vec3 vec3a = this.getDeltaMovement();
    if (this.onGround()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) 0.1F + vec3a.y), 0.0D));
      this.hasImpulse = true;
    } else {
      if (heightAboveGround < MIN_FLIGHT_HEIGHT) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
        this.hasImpulse = true;
      } else if (target == null && heightAboveGround > MAX_FLIGHT_HEIGHT) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) -0.1F - vec3a.y), 0.0D));
        this.hasImpulse = true;
      }
    }
  }

  private void doHitScanParticleEffects() {
    if (this.isAlive()) {
      if (this.level().isClientSide) {
        if (this.hasActiveAttackTarget()) {
          if (this.clientSideAttackTime < this.getAttackDuration()) {
            ++this.clientSideAttackTime;
          }
          LivingEntity target = this.getActiveAttackTarget();
          if (target != null) {
            double distance = this.distanceToSqr(target);
            boolean canSee = this.hasLineOfSight(target);
            int maxAttackDistance = 16 * 16;
            if (canSee && distance < maxAttackDistance) {
              this.getLookControl().setLookAt(target, 90.0F, 90.0F);
              this.getLookControl().tick();
              double d5 = this.getAttackAnimationScale(0.0F);
              double d0 = target.getX() - this.getX();
              double d1 = target.getY(0.5D) - this.getEyeY();
              double d2 = target.getZ() - this.getZ();
              double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
              d0 /= d3;
              d1 /= d3;
              d2 /= d3;
              double d4 = ThreadLocalRandom.current().nextDouble();
              while (d4 < d3 - 2) {
                float f0 = ThreadLocalRandom.current().nextFloat();
                SimpleParticleType particleType = f0 > 0.3 ? ParticleTypes.SMOKE : ParticleTypes.SMALL_FLAME;
                d4 += (1.8D - d5 + ThreadLocalRandom.current().nextDouble() * (1.7D - d5) * 1.5);
                this.level().addParticle(particleType, this.getX() + d0 * d4, this.getEyeY() + d1 * d4, this.getZ() + d2 * d4, 0.0D, 0.0D, 0.0D);
              }
            }
          }
        }
      }
      if (this.hasActiveAttackTarget()) {
        this.setYRot(this.yHeadRot);
      }
    }
  }

  public void doHitScanParticles(LivingEntity target) {
    EntityUtils.doParticlesAtEntity(target, ParticleTypes.LAVA, 3);
  }

  private void takeWaterDamage() {
    if (this.isInWaterRainOrBubble()) {
      ++this.underWaterTicks;
    } else {
      this.underWaterTicks = 0;
    }
    if (this.underWaterTicks > 20) {
      this.hurt(this.damageSources().drown(), 1.0F);
    }
  }

  @Nullable
  public LivingEntity getActiveAttackTarget() {
    if (!this.hasActiveAttackTarget()) {
      return null;
    } else if (this.level().isClientSide) {
      if (this.clientSideCachedAttackTarget != null) {
        return this.clientSideCachedAttackTarget;
      } else {
        Entity entity = this.level().getEntity(this.entityData.get(DATA_ID_ATTACK_TARGET));
        if (entity instanceof LivingEntity) {
          this.clientSideCachedAttackTarget = (LivingEntity) entity;
          return this.clientSideCachedAttackTarget;
        } else {
          return null;
        }
      }
    } else {
      return this.getTarget();
    }
  }

  public void setActiveAttackTarget(int id) {
    this.entityData.set(DATA_ID_ATTACK_TARGET, id);
  }

  public boolean hasActiveAttackTarget() {
    return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
  }

  public float getAttackAnimationScale(float scale) {
    return ((float) this.clientSideAttackTime + scale) / (float) this.getAttackDuration();
  }

  public int getAttackDuration() {
    return 32;
  }

  public float getAttackDamage() {
    return this.level().getDifficulty() == Difficulty.HARD ? (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE) * 2 : (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
  }

  @Nullable
  @Override
  protected SoundEvent getAmbientSound() {
    return SoundInit.WISP_AMBIENT.get();
  }

  @Override
  public int getAmbientSoundInterval() {
    return 160;
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.WISP_DEATH.get();
  }

  @Override
  protected SoundEvent getHurtSound(@NotNull DamageSource source) {
    return SoundInit.WISP_HURT.get();
  }

  protected SoundEvent getLoopSound() {
    return SoundInit.WISP_LOOP.get();
  }

  protected SoundEvent getSpawnSound() {
    return SoundInit.WISP_SPAWN.get();
  }

  public float getBodyXRot() {
    return this.currentBodyXRot;
  }

  public float getBodyZRot() {
    return this.currentBodyZRot;
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose p_21131_, @NotNull EntityDimensions p_21132_) {
    return 0.28125F;
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
    this.playSound(this.getSpawnSound(), 0.6F, 1.0F);
    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(RoRConfig.SERVER.WISP_ATTACK_DAMAGE.get());
    Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(RoRConfig.SERVER.WISP_MAX_HEALTH.get());
    this.setHealth(this.getMaxHealth());
    return super.finalizeSpawn(level, difficulty, type, data, tag);
  }

  @Override
  public boolean hurt(@NotNull DamageSource source, float amount) {
    boolean flag = super.hurt(source, amount);
    EntityUtils.doParticlesAtEntity(this, ParticleTypes.LAVA, 3);
    return flag;
  }

  @Override
  public boolean canBeAffected(@NotNull MobEffectInstance effect) {
    if (effect.getEffect() == MobEffects.POISON) {
      return false;
    }
    return super.canBeAffected(effect);
  }

  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
  }

  public boolean causeFallDamage(float p_149683_, float p_149684_, @NotNull DamageSource source) {
    return false;
  }

  protected void checkFallDamage(double p_218316_, boolean p_218317_, @NotNull BlockState state, @NotNull BlockPos pos) {
  }

  @Override
  public boolean removeWhenFarAway(double distance) {
    return RoRConfig.SERVER.WISP_DESPAWN.get();
  }

  @Override
  public boolean isFlying() {
    return !this.onGround();
  }

  @Override
  public boolean isInvulnerableTo(@NotNull DamageSource source) {
    return source == this.damageSources().hotFloor() ||
            source == this.damageSources().inFire() ||
            source == this.damageSources().onFire() ||
            super.isInvulnerableTo(source);
  }

  @Override
  public boolean isOnFire() {
    return !this.isInWaterRainOrBubble();
  }
}

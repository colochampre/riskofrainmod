package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WispEntity extends Monster implements FlyingAnimal {

  private int loopSound;

  public WispEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.LAVA, 8.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 0.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, 0.0F);
    this.xpReward = 10;
  }

  @Override
  protected void registerGoals() {
    //super.registerGoals();
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 10.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D);
  }

  @Override
  public void tick() {
    this.doLoopSound();
    this.smokeIfWet();
    super.tick();
  }

  public static boolean canSpawn(EntityType<WispEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  private void doLoopSound() {
    loopSound = (loopSound % 16) + 1;
    if (this.isAlive() && loopSound == 1) {
      this.playSound(this.getLoopSound(), 0.1F, 1.0F);
    }
  }

  private void smokeIfWet() {
    if (this.isInWaterRainOrBubble()) {
      for (int i = 0; i < 2; ++i) {
        this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
      }
    }
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

  /*@Override
  protected SoundEvent getDeathSound() {
    return SoundInit.WISP_HURT.get();
  }*/

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

  @Override
  protected float getStandingEyeHeight(@NotNull Pose p_21131_, @NotNull EntityDimensions p_21132_) {
    return 0.28125F;
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, @Nullable SpawnGroupData data, @Nullable CompoundTag tag) {
    //double d0 = RoRConfig.SERVER.STONE_GOLEM_SPAWN_VOLUME.get();
    //if (d0 > 0) {
    this.playSound(this.getSpawnSound(), 0.5F, 1.0F);
    //}
    return super.finalizeSpawn(level, difficulty, type, data, tag);
  }

  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
  }

  protected void checkFallDamage(double p_218316_, boolean p_218317_, @NotNull BlockState state, @NotNull BlockPos pos) {
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

  public boolean isSensitiveToWater() {
    return true;
  }
}

package io.github.colochampre.riskofrain_mobs.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.Pose;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

public class WispEntity extends Monster {

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
            .add(Attributes.ATTACK_DAMAGE, 2.5D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D);
  }

  public static boolean canSpawn(EntityType<WispEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose p_21131_, @NotNull EntityDimensions p_21132_) {
    return 0.28125F;
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

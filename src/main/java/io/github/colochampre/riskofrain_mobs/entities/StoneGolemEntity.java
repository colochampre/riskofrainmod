package io.github.colochampre.riskofrain_mobs.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;

public class StoneGolemEntity extends Monster {

  public StoneGolemEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 12.0D)
            .add(Attributes.ATTACK_KNOCKBACK, 2.0D)
            .add(Attributes.FOLLOW_RANGE, 24.0D)
            .add(Attributes.KNOCKBACK_RESISTANCE, 1.0D)
            .add(Attributes.MAX_HEALTH, 100.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D);
  }

  public static boolean canSpawn(EntityType<StoneGolemEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  @Override
  public int getMaxFallDistance() {
    return 24;
  }

  @Override
  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return 3.5F;
  }

  @Override
  public boolean isInvulnerableTo(DamageSource src) {
    return src == DamageSource.FREEZE || src == DamageSource.IN_WALL || src == DamageSource.LAVA || super.isInvulnerableTo(src);
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getZ() != entity.zOld;
  }

  @Override
  public boolean isOnFire() {
    return false;
  }

  public boolean isPushedByFluid() {
    return false;
  }

  @Override
  public boolean removeWhenFarAway(double p_21542_) {
    return super.removeWhenFarAway(p_21542_);
  }
}

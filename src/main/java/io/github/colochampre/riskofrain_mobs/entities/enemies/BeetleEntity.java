package io.github.colochampre.riskofrain_mobs.entities.enemies;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class BeetleEntity extends Monster {

  private int attackTick;

  public BeetleEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.xpReward = 8;
    this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new MeleeAttackGoal(this, 1.0, true));
    this.goalSelector.addGoal(3, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.5D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.21D);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isAlive()) {
      if (this.isImmobile()) {
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.0);
      } else {
        Objects.requireNonNull(this.getAttribute(Attributes.MOVEMENT_SPEED)).setBaseValue(0.21D);
      }
      if (this.attackTick > 0) {
        --this.attackTick;
      }
    }
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor server, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag tag) {
    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(RoRConfig.SERVER.BEETLE_ATTACK_DAMAGE.get());
    Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(RoRConfig.SERVER.BEETLE_MAX_HEALTH.get());
    this.setHealth(this.getMaxHealth());
    return super.finalizeSpawn(server, difficulty, type, groupData, tag);
  }

  @Override
  public boolean doHurtTarget(Entity entity) {
    this.attackTick = 16;
    this.level().broadcastEntityEvent(this, (byte) 4);
    float f = this.getAttackDamage();
    boolean flag = entity.hurt(this.damageSources().mobAttack(this), f);
    this.playSound(this.getAttackSound(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
    return flag;
  }

  public float getAttackDamage() {
    double d0 = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    return this.level().getDifficulty() == Difficulty.HARD ? (float) d0 * 2 : (float) d0;
  }

  protected boolean isImmobile() {
    return super.isImmobile() || this.attackTick > 0;
  }

  public static boolean canSpawn(EntityType<BeetleEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, @NotNull DamageSource p_147189_) {
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    return super.causeFallDamage(p_147187_, p_147188_, p_147189_);
  }

  public int getAttackTick() {
    return this.attackTick;
  }

  @Override
  public void handleEntityEvent(byte b) {
    if (b == 4) {
      this.attackTick = 16;
    } else {
      super.handleEntityEvent(b);
    }
  }

  @Override
  public boolean removeWhenFarAway(double distance) {
    return RoRConfig.SERVER.BEETLES_DESPAWN.get();
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundInit.BEETLE_AMBIENT.get();
  }

  protected SoundEvent getAttackSound() {
    return SoundInit.BEETLE_ATTACK.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.BEETLE_DEATH.get();
  }

  @Override
  protected SoundEvent getHurtSound(@NotNull DamageSource source) {
    return SoundInit.BEETLE_HURT.get();
  }

  protected SoundEvent getStepSound() {
    return SoundInit.BEETLE_STEP.get();
  }

  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockState) {
    this.playSound(this.getStepSound(), 0.25F, 1.0F);
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 1.4375F;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getZ() != entity.zOld;
  }
}

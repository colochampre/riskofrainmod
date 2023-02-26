package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.Nullable;

public class StoneGolemEntity extends Monster {
  private static final ResourceLocation LOOT_TABLE = new ResourceLocation(RoRmod.MODID, "entities/stone_golem_entity");
  private int attackTimer;

  public StoneGolemEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.maxUpStep = 1.0F;
    this.xpReward = 24;
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

  @Override
  public void tick() {
    super.tick();
    if (this.attackTimer > 0) {
      --this.attackTimer;
    }
  }

  public int getAttackTimer() {
    return this.attackTimer;
  }

  public static boolean canSpawn(EntityType<StoneGolemEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
    this.playSound(this.getStepSound(), (p_147188_ + 2), 1.0F);
    this.playSound(this.getStepSound(), (p_147188_ + 2), 1.2F);
    return false;
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, DifficultyInstance instance, MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag compoundTag) {
    this.playSound(this.getSpawnSound(), 4.0F, 1.0F);

    return super.finalizeSpawn(level, instance, type, groupData, compoundTag);
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return null;
  }

  protected SoundEvent getAttackSound() {
    return SoundInit.STONE_GOLEM_CLAP.get();
  }

  @Override
  public void die(DamageSource src) {
    this.playSound(this.getDeathVoiceSound(), 1.5F, 1.0F);
    super.die(src);
  }


  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.STONE_GOLEM_DEATH.get();
  }

  protected SoundEvent getDeathVoiceSound() {
    return (SoundEvent)SoundInit.STONE_GOLEM_GROWL.get();
  }


  @Override
  protected SoundEvent getHurtSound(DamageSource source) {
    return SoundInit.STONE_GOLEM_HURT.get();
  }

  protected SoundEvent getSpawnSound() {
    return (SoundEvent)SoundInit.STONE_GOLEM_SPAWN.get();
  }

  protected SoundEvent getStepSound() {
    return SoundInit.STONE_GOLEM_STEP.get();
  }


  protected void playStepSound(BlockPos pos, BlockState blockState) {
    this.playSound(this.getStepSound(), 3.0F, 1.0F);
  }

  @Override
  protected ResourceLocation getDefaultLootTable() {
    return LOOT_TABLE;
  }

  @Override
  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return 3.5F;
  }

  @Override
  public int getMaxFallDistance() {
    return 24;
  }

  @Override
  public boolean isInvulnerableTo(DamageSource src) {
    return src == DamageSource.FREEZE ||
            src == DamageSource.IN_WALL ||
            src == DamageSource.IN_FIRE ||
            src == DamageSource.LAVA ||
            src == DamageSource.ON_FIRE ||
            super.isInvulnerableTo(src);
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
    return false;
  }

}

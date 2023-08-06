package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.goals.StoneGolemAttackGoal;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class StoneGolemEntity extends Monster {
  private static final ResourceLocation STONE_GOLEM_LOOT_TABLE = new ResourceLocation(RoRmod.MODID, "entities/stone_golem_entity");
  private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(StoneGolemEntity.class, EntityDataSerializers.INT);
  private final HurtByTargetGoal hurtByTargetGoal = new HurtByTargetGoal(this);
  private int attackTimer;
  public int clientSideAttackTime;
  private LivingEntity clientSideCachedAttackTarget;

  public StoneGolemEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.setMaxUpStep(1.0F);
    this.xpReward = 24;
    this.setPathfindingMalus(BlockPathTypes.LEAVES, 0.0F);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(4, new StoneGolemAttackGoal(this, 0.8D, true));
    this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(6, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
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
  public void aiStep() {
    LivingEntity target = this.getTarget();
    if (target == null) {
      this.goalSelector.addGoal(3, this.hurtByTargetGoal);
    }
    if (this.attackTimer > 0) {
      --this.attackTimer;
    }
    destroyLeavesBlocks();
    doFloorParticleEffect();
    doLaserParticleEffects();
    super.aiStep();
  }

  public static boolean canSpawn(EntityType<StoneGolemEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, @NotNull DamageSource p_147189_) {
    this.playSound(this.getStepSound(), 3, 1.0F);
    this.playSound(this.getStepSound(), 3, 1.2F);
    return false;
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
  }

  private void destroyLeavesBlocks() {
    if (this.horizontalCollision && net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), this)) {
      boolean flag = false;
      AABB aabb = this.getBoundingBox().inflate(0.2D);

      for (BlockPos blockpos : BlockPos.betweenClosed(Mth.floor(aabb.minX), Mth.floor(aabb.minY), Mth.floor(aabb.minZ), Mth.floor(aabb.maxX), Mth.floor(aabb.maxY), Mth.floor(aabb.maxZ))) {
        BlockState blockstate = this.level().getBlockState(blockpos);
        Block block = blockstate.getBlock();
        if (block instanceof LeavesBlock) {
          flag = this.level().destroyBlock(blockpos, true, this) || flag;
        }
      }
    }
  }

  @Override
  public boolean doHurtTarget(Entity entity) {
    this.attackTimer = 15;
    this.level().broadcastEntityEvent(this, (byte) 4);
    float f = this.getAttackDamage();
    boolean flag = entity.hurt(this.damageSources().mobAttack(this), f);
    this.playSound(SoundInit.STONE_GOLEM_CLAP.get(), 3.0F, 1.0F);
    return flag;
  }

  private void doFloorParticleEffect() {
    if (this.getDeltaMovement().horizontalDistanceSqr() > (double) 2.5000003E-7F && this.random.nextInt(5) == 0) {
      int i = Mth.floor(this.getX());
      int j = Mth.floor(this.getY() - (double) 0.2F);
      int k = Mth.floor(this.getZ());
      BlockPos pos = new BlockPos(i, j, k);
      BlockState blockstate = this.level().getBlockState(pos);
      if (!blockstate.isAir()) {
        this.level().addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double) this.random.nextFloat() - 0.5D) * (double) this.getBbWidth(), 4.0D * ((double) this.random.nextFloat() - 0.5D), 0.5D, ((double) this.random.nextFloat() - 0.5D) * 4.0D);
      }
    }
  }

  private void doLaserParticleEffects() {
    if (this.isAlive()) {
      if (this.level().isClientSide) {

        if (this.hasActiveAttackTarget()) {
          if (this.clientSideAttackTime < this.getAttackDuration()) {
            ++this.clientSideAttackTime;
          }

          LivingEntity livingentity = this.getActiveAttackTarget();
          if (livingentity != null) {
            this.getLookControl().setLookAt(livingentity, 90.0F, 90.0F);
            this.getLookControl().tick();
            double d5 = (double) this.getAttackAnimationScale(0.0F);
            double d0 = livingentity.getX() - this.getX();
            double d1 = livingentity.getY(0.5D) - this.getEyeY();
            double d2 = livingentity.getZ() - this.getZ();
            double d3 = Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2);
            d0 /= d3;
            d1 /= d3;
            d2 /= d3;
            double d4 = this.random.nextDouble();

            while (d4 < d3) {
              d4 += 1.8D - d5 + this.random.nextDouble() * (1.7D - d5);
              this.level().addParticle(ParticleTypes.SMOKE, this.getX() + d0 * d4, this.getEyeY() + d1 * d4, this.getZ() + d2 * d4, 0.0D, 0.0D, 0.0D);
            }
          }
        }
      }
      if (this.hasActiveAttackTarget()) {
        this.setYRot(this.yHeadRot);
      }
    }
  }

  @Nullable
  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance instance, @NotNull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag compoundTag) {
    this.playSound(this.getSpawnSound(), 4.0F, 1.0F);
    return super.finalizeSpawn(level, instance, type, groupData, compoundTag);
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

  public void setActiveAttackTarget(int p_32818_) {
    this.entityData.set(DATA_ID_ATTACK_TARGET, p_32818_);
  }

  public boolean hasActiveAttackTarget() {
    return this.entityData.get(DATA_ID_ATTACK_TARGET) != 0;
  }

  public float getAttackAnimationScale(float scale) {
    return ((float) this.clientSideAttackTime + scale) / (float) this.getAttackDuration();
  }

  public float getAttackDamage() {
    float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    if (this.level().getDifficulty() == Difficulty.HARD) {
      f *= 1.5F;
    }
    return f;
  }

  public int getAttackDuration() {
    return 75;
  }

  public int getAttackTimer() {
    return this.attackTimer;
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return null;
  }

  protected SoundEvent getAttackSound() {
    return SoundInit.STONE_GOLEM_CLAP.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.STONE_GOLEM_DEATH.get();
  }

  protected SoundEvent getDeathVoiceSound() {
    return (SoundEvent) SoundInit.STONE_GOLEM_GROWL.get();
  }

  @Override
  public void die(@NotNull DamageSource src) {
    this.playSound(this.getDeathVoiceSound(), 1.5F, 1.0F);
    super.die(src);
  }


  @Override
  protected SoundEvent getHurtSound(@NotNull DamageSource source) {
    return SoundInit.STONE_GOLEM_HURT.get();
  }

  protected SoundEvent getSpawnSound() {
    return (SoundEvent) SoundInit.STONE_GOLEM_SPAWN.get();
  }

  protected SoundEvent getStepSound() {
    return SoundInit.STONE_GOLEM_STEP.get();
  }


  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockState) {
    this.playSound(this.getStepSound(), 3.0F, 1.0F);
  }

  @Override
  protected @NotNull ResourceLocation getDefaultLootTable() {
    return STONE_GOLEM_LOOT_TABLE;
  }

  @Override
  public int getMaxFallDistance() {
    return 24;
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 3.5F;
  }

  @Override
  public void handleEntityEvent(byte b) {
    if (b == 4) {
      this.attackTimer = 15;
      this.playSound(SoundInit.STONE_GOLEM_CLAP.get(), 3.0F, 1.0F);
    } else {
      super.handleEntityEvent(b);
    }
  }

  @Override
  public boolean isInvulnerableTo(@NotNull DamageSource src) {
    return src == this.damageSources().freeze() ||
            src == this.damageSources().hotFloor() ||
            src == this.damageSources().inWall() ||
            src == this.damageSources().inFire() ||
            src == this.damageSources().lava() ||
            src == this.damageSources().onFire() ||
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
  public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> accessor) {
    super.onSyncedDataUpdated(accessor);
    if (DATA_ID_ATTACK_TARGET.equals(accessor)) {
      this.clientSideAttackTime = 0;
      this.clientSideCachedAttackTarget = null;
    }
  }

  @Override
  public boolean removeWhenFarAway(double distance) {
    return RoRConfig.SERVER.STONE_GOLEMS_DESPAWN.get();
  }

  public void strongKnockback(Entity entity) {
    double d0 = entity.getX() - this.getX();
    double d1 = entity.getZ() - this.getZ();
    double d2 = Math.max(d0 * d0 + d1 * d1, 0.001D);
    entity.push(d0 / d2 * 4.0D, 0.2D, d1 / d2 * 4.0D);
  }
}

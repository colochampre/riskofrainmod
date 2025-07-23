package io.github.colochampre.riskofrain_mobs.entities.allies;

import com.google.common.collect.Sets;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.entities.goals.DroneFollowOwnerGoal;
import io.github.colochampre.riskofrain_mobs.utils.EntityUtils;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

public abstract class AbstractDroneEntity extends TamableAnimal {
  public static final int TYPE_LAND = 0;
  public static final int TYPE_FLYING = 1;
  public static final int MIN_FLIGHT_HEIGHT = 3;
  public static final int MAX_FLIGHT_HEIGHT = 8;
  public static final Set<EntityType<?>> DO_NOT_ATTACK = Sets.newHashSet(EntityType.CREEPER, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN, EntityType.HOGLIN, EntityType.ZOGLIN);
  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.GOLD_BLOCK, Items.RAW_GOLD_BLOCK, Items.GOLD_INGOT, Items.RAW_GOLD, Items.GOLD_NUGGET);
  private static final Set<Item> REPAIR_ITEMS = Sets.newHashSet(Items.IRON_INGOT, Items.IRON_NUGGET, Items.RAW_IRON);
  private static final EntityDataAccessor<Integer> DATA_PRICE = SynchedEntityData.defineId(AbstractDroneEntity.class, EntityDataSerializers.INT);
  private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(AbstractDroneEntity.class, EntityDataSerializers.INT);
  private static final Map<Item, Integer> dronePriceMap = new HashMap<>();
  private LivingEntity clientSideCachedAttackTarget;
  private float bodyXRot;
  private float bodyZRot;
  private int flyingSound;
  private int underWaterTicks;

  static {
    dronePriceMap.put(Items.GOLD_BLOCK, 81);
    dronePriceMap.put(Items.RAW_GOLD_BLOCK, 54);
    dronePriceMap.put(Items.GOLD_INGOT, 9);
    dronePriceMap.put(Items.RAW_GOLD, 6);
    dronePriceMap.put(Items.GOLD_NUGGET, 1);
  }

  public AbstractDroneEntity(EntityType<? extends AbstractDroneEntity> type, Level level) {
    super(type, level);
    if (this.getDroneType() == TYPE_FLYING) {
      this.moveControl = new FlyingMoveControl(this, 16, true);
      this.setPathfindingMalus(PathType.COCOA, -1.0F);
      this.setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0F);
      this.setPathfindingMalus(PathType.DANGER_FIRE, -1.0F);
      this.setPathfindingMalus(PathType.DAMAGE_OTHER, -1.0F);
      this.setPathfindingMalus(PathType.FENCE, -1.0F);
      this.setPathfindingMalus(PathType.WATER, -1.0F);
      this.setPathfindingMalus(PathType.WATER_BORDER, 16.0F);
    }
  }

  protected abstract int getDroneType();

  protected abstract int getDronePrice();

  @Override
  public void setTame(boolean tamed, boolean b2) {
    super.setTame(tamed, b2);
    int type = this.getDroneType();
    this.goalSelector.addGoal(2, new SitWhenOrderedToGoal(this));
    if (type == TYPE_LAND) {
      this.addLandDroneGoals();
    }
    if (type == TYPE_FLYING) {
      this.addFlyingDroneGoals();
    }
  }

  private void addLandDroneGoals() {
    RandomLookAroundGoal randomLookAroundGoal = new RandomLookAroundGoal(this);
    this.goalSelector.addGoal(8, randomLookAroundGoal);
  }

  private void addFlyingDroneGoals() {
    DroneFollowOwnerGoal followOwnerGoal = new DroneFollowOwnerGoal(this, 1.0D, 8.0F, 4.0F, true);
    WaterAvoidingRandomFlyingGoal randomFlyingGoal = new WaterAvoidingRandomFlyingGoal(this, 0.5D);
    this.goalSelector.addGoal(4, followOwnerGoal);
    this.goalSelector.addGoal(5, randomFlyingGoal);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isTame()) {
      this.smokeIfLowHealth();
      if (this.getDroneType() == TYPE_FLYING) {
        this.doFlyingSound();
        this.landIfOrderedToSit();
      }
    }
  }

  @Override
  public void tick() {
    super.tick();
    if (this.isTame()) {
      this.takeWaterDamage();
      if (this.getDroneType() == TYPE_FLYING) {
        EntityUtils.updateMovementInclinations(this, this.bodyXRot, this.bodyZRot, newBodyXRot -> this.bodyXRot = newBodyXRot, newBodyZRot -> this.bodyZRot = newBodyZRot);
      }
    }
  }

  protected @NotNull PathNavigation createNavigation(@SuppressWarnings("null") @NotNull Level level) {
    if (this.getDroneType() == TYPE_FLYING) {
      FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level) {
        public boolean isStableDestination(@SuppressWarnings("null") BlockPos pos) {
          return !this.level.getBlockState(pos.below()).isAir();
        }
      };
      flyingPathNavigation.setCanOpenDoors(false);
      flyingPathNavigation.setCanFloat(false);
      flyingPathNavigation.setCanPassDoors(true);
      return flyingPathNavigation;
    } else {
      return super.createNavigation(level);
    }
  }

  public float getWalkTargetValue(@SuppressWarnings("null") @NotNull BlockPos pos, @SuppressWarnings("null") @NotNull LevelReader level) {
    if (this.getDroneType() == TYPE_FLYING) {
      return level.getBlockState(pos).isAir() ? 20.0F : 0.0F;
    } else {
      return super.getWalkTargetValue(pos, level);
    }
  }

  @Override
  protected void defineSynchedData(@SuppressWarnings("null") SynchedEntityData.@NotNull Builder builder) {
    super.defineSynchedData(builder);
    Difficulty difficulty = this.level().getDifficulty();
    int initialGold = difficulty == Difficulty.HARD ? (int) (this.getDronePrice() * 1.5) : this.getDronePrice();
    builder.define(DATA_PRICE, initialGold);
    builder.define(DATA_ID_ATTACK_TARGET, 0);
  }

  @Override
  public void addAdditionalSaveData(@SuppressWarnings("null") @NotNull CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putByte("GoldPrice", (byte) this.getCurrentGoldPrice());
  }

  @Override
  public void readAdditionalSaveData(@SuppressWarnings("null") @NotNull CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    if (tag.contains("GoldPrice", 99)) {
      this.setCurrentGoldPrice(tag.getInt("GoldPrice"));
    }
  }

  @Override
  public void onSyncedDataUpdated(@SuppressWarnings("null") @NotNull EntityDataAccessor<?> accessor) {
    super.onSyncedDataUpdated(accessor);
    if (DATA_ID_ATTACK_TARGET.equals(accessor)) {
      this.clientSideCachedAttackTarget = null;
    }
  }

  private void doFlyingSound() {
    if (!this.isTame() || this.onGround() || this.isInSittingPose() || this.isOrderedToSit()) {
      flyingSound = 0;
    } else {
      flyingSound = (flyingSound % 30) + 1;
    }
    if (flyingSound == 1) {
      this.playSound(this.getFlyingSound(), 0.05F, 1.0F);
    }
  }

  private void landIfOrderedToSit() {
    Vec3 vec3 = this.getDeltaMovement();
    if (this.isOrderedToSit()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) -0.1F - vec3.y), 0.0D));
      this.hasImpulse = true;
    } else {
      this.stayElevated();
    }
  }

  private void stayElevated() {
    LivingEntity target = this.getTarget();
    double heightAboveGround = EntityUtils.getHeightAboveSurface(this);
    Vec3 vec3a = this.getDeltaMovement();
    if (heightAboveGround < MIN_FLIGHT_HEIGHT) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) 0.2F - vec3a.y) * (double) 0.2F, 0.0D));
      this.hasImpulse = true;
    } else if (!(Objects.requireNonNull(this.getOwner()).getEyeY() > this.getEyeY()) && target == null && heightAboveGround > MAX_FLIGHT_HEIGHT) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) -0.1F - vec3a.y), 0.0D));
      this.hasImpulse = true;
    }
  }

  private void smokeIfLowHealth() {
    if (this.isLowHealth() && this.tickCount % 2 == 0) {
      float f0 = ThreadLocalRandom.current().nextFloat();
      SimpleParticleType particle = f0 > 0.1 ? ParticleTypes.SMOKE : ParticleTypes.ELECTRIC_SPARK;
      EntityUtils.doParticlesAtEntity(this, particle, 2);
    }
  }

  public boolean isLowHealth() {
    double health = this.getHealth();
    return health < this.getMaxHealth() / 2;
  }

  private void takeWaterDamage() {
    if (this.isInWaterOrBubble()) {
      ++this.underWaterTicks;
    } else {
      this.underWaterTicks = 0;
    }
    if (this.underWaterTicks > 20) {
      this.hurt(this.damageSources().drown(), 1.0F);
    }
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  @Override
  public SpawnGroupData finalizeSpawn(@SuppressWarnings("null") @NotNull ServerLevelAccessor level, @SuppressWarnings("null") @NotNull DifficultyInstance instance, @SuppressWarnings("null") @NotNull MobSpawnType type, @SuppressWarnings("null") @Nullable SpawnGroupData groupData) {
    if (this.getCurrentGoldPrice() > 0) {
      String price = String.valueOf(this.getCurrentGoldPrice());
      Component component = Component.literal(price).withStyle(ChatFormatting.YELLOW);
      this.setCustomName(component);
      this.setCustomNameVisible(true);
    }
    return super.finalizeSpawn(level, instance, type, groupData);
  }

  @Override
  public @NotNull InteractionResult mobInteract(@SuppressWarnings("null") Player player, @SuppressWarnings("null") @NotNull InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    if (this.isTame()) {
      // Repair
      if (this.getHealth() < this.getMaxHealth()) {
        if (REPAIR_ITEMS.contains(itemstack.getItem())) {
          if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
          }
          this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundEvents.IRON_GOLEM_REPAIR, this.getSoundSource(), 0.5F, 1.25F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
          if (itemstack.getItem().equals(Items.IRON_INGOT)) {
            this.heal(18.0F);
          } else if (itemstack.getItem().equals(Items.RAW_IRON)) {
            this.heal(12.0F);
          } else {
            this.heal(2.0F);
          }
          return InteractionResult.SUCCESS;
        }
      }
      // Set sitting
      if (!(item instanceof DyeItem) && this.isOwnedBy(player)) {
        this.setOrderedToSit(!this.isOrderedToSit());
        this.navigation.stop();
        this.setTarget(null);
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundInit.DRONE_REPAIR.get(), this.getSoundSource(), 0.2F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        return InteractionResult.SUCCESS;
      }
    } else if (!this.isTame()) {
      // Not gold
      if (!TAME_ITEMS.contains(itemstack.getItem())) {
        Component goldMessage = Component.translatable("message.riskofrain_mobs.not_gold").withStyle(ChatFormatting.YELLOW);
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundInit.INSUFFICIENT_FOUNDS_PROC.get(), this.getSoundSource(), 0.5F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        if (!this.level().isClientSide) {
          player.sendSystemMessage(goldMessage);
        }
        return InteractionResult.SUCCESS;
        // Taming
      } else if (TAME_ITEMS.contains(itemstack.getItem())) {
        if (!player.getAbilities().instabuild) {
          itemstack.shrink(1);
        }
        this.level().playSound(null, this.getX(), this.getY(), this.getZ(), SoundInit.COIN_PROC.get(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        this.updateGoldPrice(itemstack);
        String price = String.valueOf(this.getCurrentGoldPrice());
        Component priceName = Component.literal(price).withStyle(ChatFormatting.YELLOW);
        this.setCustomName(priceName);
        this.setCustomNameVisible(true);
        if (!this.level().isClientSide) {
          if (this.getCurrentGoldPrice() <= 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
            this.tame(player);
            this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.DRONE_REPAIR.get(), this.getSoundSource(), 0.6F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.level().broadcastEntityEvent(this, (byte) 7);
            this.setCustomName(null);
            this.setCustomNameVisible(false);
            this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
          } else {
            this.level().broadcastEntityEvent(this, (byte) 6);
          }
        }
        return InteractionResult.SUCCESS;
      }
    }
    return super.mobInteract(player, hand);
  }

  private void updateGoldPrice(ItemStack itemstack) {
    int priceReduction = dronePriceMap.get(itemstack.getItem());
    this.setCurrentGoldPrice(this.getCurrentGoldPrice() - priceReduction);
  }

  @Override
  protected void spawnTamingParticles(boolean success) {
    SimpleParticleType particle = success ? ParticleTypes.ELECTRIC_SPARK : ParticleTypes.SMOKE;
    EntityUtils.doParticlesAtEntity(this, particle, 5);
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

  @Override
  public void die(@SuppressWarnings("null") @NotNull DamageSource source) {
    this.playSound(this.getShutDownSound(), 1.0F, 1.0F);
    super.die(source);
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.DRONE_DEATH.get();
  }

  protected SoundEvent getShutDownSound() {
    return SoundInit.DRONE_BREAKS.get();
  }

  protected SoundEvent getFlyingSound() {
    return SoundInit.DRONE_FLYING.get();
  }

  protected SoundEvent getStepSound() {
    //return SoundInit.DRONE_STEP.get();
    return null;
  }

  /*protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
    if (this.getDroneType() == TYPE_LAND) {
      this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
  }*/

  public int getCurrentGoldPrice() {
    return this.entityData.get(DATA_PRICE);
  }

  public void setCurrentGoldPrice(int i) {
    this.entityData.set(DATA_PRICE, i);
  }

  public float getBodyXRot() {
    return this.bodyXRot;
  }

  public float getBodyZRot() {
    return this.bodyZRot;
  }

  @Override
  public boolean canBeLeashed() {
    return (this.isTame() && !this.isInSittingPose());
  }

  @Override
  public boolean hurt(@SuppressWarnings("null") @NotNull DamageSource source, float damage) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      Entity entity = source.getEntity();
      if (!this.level().isClientSide) {
        this.setOrderedToSit(false);
      }
      if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
        damage = (damage + 1.0F) / 2.0F;
      }
      return super.hurt(source, damage);
    }
  }

  @Override
  public boolean canBeAffected(@SuppressWarnings("null") MobEffectInstance effect) {
    if (effect.getEffect() == MobEffects.POISON) {
      return false;
    }
    return super.canBeAffected(effect);
  }

  public boolean isDroneMoving() {
    return this.getX() != this.xOld || this.getY() != this.yOld || this.getZ() != this.zOld;
  }

  public boolean isFlying() {
    return this.getDroneType() == TYPE_FLYING && !this.onGround();
  }

  @Override
  public AgeableMob getBreedOffspring(@SuppressWarnings("null") @NotNull ServerLevel level, @SuppressWarnings("null") @NotNull AgeableMob mob) {
    return null;
  }
}

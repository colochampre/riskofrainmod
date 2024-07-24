package io.github.colochampre.riskofrain_mobs.entities.allies;

import com.google.common.collect.Sets;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.entities.goals.DroneFollowOwnerGoal;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public abstract class AbstractDroneEntity extends TamableAnimal implements FlyingAnimal {
  public static final int TYPE_LAND = 0;
  public static final int TYPE_FLYING = 1;
  private static final EntityDataAccessor<Integer> DATA_PRICE = SynchedEntityData.defineId(AbstractDroneEntity.class, EntityDataSerializers.INT);
  private static final EntityDataAccessor<Integer> DATA_ID_ATTACK_TARGET = SynchedEntityData.defineId(AbstractDroneEntity.class, EntityDataSerializers.INT);
  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.RAW_GOLD);
  private static final Set<Item> REPAIR_ITEMS = Sets.newHashSet(Items.IRON_INGOT, Items.IRON_NUGGET, Items.RAW_IRON);
  private final DroneFollowOwnerGoal landFollowOwnerGoal = new DroneFollowOwnerGoal(this, 1.0D, 8.0F, 4.0F, false);
  private final DroneFollowOwnerGoal flyingFollowOwnerGoal = new DroneFollowOwnerGoal(this, 1.0D, 8.0F, 4.0F, true);
  private final WaterAvoidingRandomFlyingGoal randomFlyingGoal = new WaterAvoidingRandomFlyingGoal(this, 0.5D);
  private final WaterAvoidingRandomStrollGoal randomStrollGoal = new WaterAvoidingRandomStrollGoal(this, 0.6D);
  private LivingEntity clientSideCachedAttackTarget;
  private float currentBodyXRot;
  private float currentBodyZRot;
  private float targetBodyXRot;
  private float targetBodyZRot;
  private float rollAmount;
  private float rollAmountO;
  private int flyingSound;
  private int underWaterTicks;

  public AbstractDroneEntity(EntityType<? extends AbstractDroneEntity> type, Level level) {
    super(type, level);
  }

  protected abstract int getDroneType();


  @Override
  public void setTame(boolean tamed) {
    super.setTame(tamed);
    int type = this.getDroneType();
    this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
    if (type == TYPE_LAND) {
      this.addLandGoals();
    }
    if (type == TYPE_FLYING) {
      this.addFlyingGoals();
    }
  }

  private void addLandGoals() {
    this.goalSelector.addGoal(3, this.landFollowOwnerGoal);
    this.goalSelector.addGoal(4, this.randomStrollGoal);
  }

  private void addFlyingGoals() {
    this.goalSelector.addGoal(3, this.flyingFollowOwnerGoal);
    this.goalSelector.addGoal(4, this.randomFlyingGoal);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isTame()) {
      this.smokeIfLowHealth();
      this.waterDamage();
      if (this.getDroneType() == TYPE_FLYING) {
        this.doFlyingSound();
        this.landIfOrderedToSit();
      }
    }
  }

  @Override
  public void tick() {
    super.tick();
    this.detectMovementDirection();
    this.interpolateInclinations();
    this.updateRollAmount();
  }

  protected @NotNull PathNavigation createNavigation(@NotNull Level level) {
    if (this.getDroneType() == TYPE_FLYING) {
      FlyingPathNavigation flyingPathNavigation = new FlyingPathNavigation(this, level) {
        public boolean isStableDestination(BlockPos pos) {
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

  public float getWalkTargetValue(@NotNull BlockPos pos, @NotNull LevelReader level) {
    if (this.getDroneType() == TYPE_FLYING) {
      return level.getBlockState(pos).isAir() ? 20.0F : 0.0F;
    } else {
      return super.getWalkTargetValue(pos, level);
    }
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    Difficulty difficulty = this.level().getDifficulty();
    int initialGold = difficulty == Difficulty.HARD ? 54 : 36;
    this.entityData.define(DATA_PRICE, initialGold);
    this.entityData.define(DATA_ID_ATTACK_TARGET, 0);
  }

  @Override
  public void addAdditionalSaveData(@NotNull CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putByte("GoldPrice", (byte) this.getGoldPrice());
  }

  @Override
  public void readAdditionalSaveData(@NotNull CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    if (tag.contains("GoldPrice", 99)) {
      this.setGoldPrice(tag.getInt("GoldPrice"));
    }
  }

  @Override
  public void onSyncedDataUpdated(@NotNull EntityDataAccessor<?> accessor) {
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
      if (this.onGround()) {
        this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) 0.1F + vec3.y), 0.0D));
      }
    }
  }

  private void smokeIfLowHealth() {
    if (this.isLowHealth()) {
      for (int i = 0; i < 2; ++i) {
        this.level().addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
      }
    }
  }

  public boolean isLowHealth() {
    double health = this.getHealth();
    return health < 10.0D;
  }

  private void waterDamage() {
    if (this.isInWaterOrBubble()) {
      ++this.underWaterTicks;
    } else {
      this.underWaterTicks = 0;
    }
    if (this.underWaterTicks > 20) {
      this.hurt(this.damageSources().drown(), 1.0F);
    }
  }

  public boolean causeFallDamage(float p_149683_, float p_149684_, @NotNull DamageSource source) {
    return this.getDroneType() != TYPE_FLYING;
  }

  protected void checkFallDamage(double fallDistance, boolean onGround, @NotNull BlockState state, @NotNull BlockPos pos) {
    if (this.getDroneType() != TYPE_FLYING) {
      super.checkFallDamage(fallDistance, onGround, state, pos);
    }
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance instance, @NotNull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag compoundTag) {
    String price = String.valueOf(this.getGoldPrice());
    Component component = Component.literal(price).withStyle(ChatFormatting.YELLOW);
    this.setCustomName(component);
    this.setCustomNameVisible(true);
    return super.finalizeSpawn(level, instance, type, groupData, compoundTag);
  }

  @Override
  public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    if (this.isTame()) {
      // Repair
      if (this.getHealth() < this.getMaxHealth()) {
        if (REPAIR_ITEMS.contains(itemstack.getItem())) {
          if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
          }
          this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.IRON_GOLEM_REPAIR, this.getSoundSource(), 0.2F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
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
        this.setTarget((LivingEntity) null);
        this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.DRONE_REPAIR.get(), this.getSoundSource(), 0.2F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        return InteractionResult.SUCCESS;
      }
    } else if (!this.isTame()) {
      // Not gold
      if (!TAME_ITEMS.contains(itemstack.getItem())) {
        Component goldMessage = itemstack.getItem().equals(Items.GOLD_BLOCK) || itemstack.getItem().equals(Items.RAW_GOLD_BLOCK)
                ? Component.translatable("message.riskofrain_mobs.smaller_gold").withStyle(ChatFormatting.YELLOW)
                : Component.translatable("message.riskofrain_mobs.not_gold").withStyle(ChatFormatting.YELLOW);
        this.level().playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.INSUFFICIENT_FOUNDS_PROC.get(), this.getSoundSource(), 0.5F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
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
        if (itemstack.getItem().equals(Items.GOLD_INGOT)) {
          this.setGoldPrice(this.getGoldPrice() - 9);
        } else if (itemstack.getItem().equals(Items.RAW_GOLD)) {
          this.setGoldPrice(this.getGoldPrice() - 6);
        } else {
          this.setGoldPrice(this.getGoldPrice() - 1);
        }
        String price = String.valueOf(this.getGoldPrice());
        Component priceName = Component.literal(price).withStyle(ChatFormatting.YELLOW);
        this.setCustomName(priceName);
        this.setCustomNameVisible(true);
        if (!this.level().isClientSide) {
          if (this.getGoldPrice() <= 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
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
  public void die(@NotNull DamageSource source) {
    this.playSound(this.getShutDownSound(), 1.0F, 1.0F);
    super.die(source);
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.DRONE_DEATH1.get();
  }

  protected SoundEvent getShutDownSound() {
    return SoundInit.DRONE_DEATH2.get();
  }

  protected SoundEvent getFlyingSound() {
    return SoundInit.DRONE_FLYING.get();
  }

  protected SoundEvent getStepSound() {
    //return SoundInit.DRONE_STEP.get();
    return null;
  }

  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockIn) {
    if (this.getDroneType() == TYPE_LAND) {
      //this.playSound(this.getStepSound(), 0.15F, 1.0F);
    }
  }

  public int getGoldPrice() {
    return this.entityData.get(DATA_PRICE);
  }

  public void setGoldPrice(int i) {
    this.entityData.set(DATA_PRICE, i);
  }

  public void detectMovementDirection() {
    if (this.isTame()) {
      Vec3 motion = this.getDeltaMovement(); // Vector de movimiento actual
      float yaw = this.getYRot(); // Orientación del dron (yaw)
      Vec3 lookVec = Vec3.directionFromRotation(0, yaw); // Convertir la orientación del dron en un vector
      Vec3 normalizedMotion = motion.normalize(); // Normalizar el vector de movimiento
      double dotForward = normalizedMotion.dot(lookVec); // Calcular el producto punto para determinar la dirección relativa del movimiento
      double dotRight = normalizedMotion.dot(new Vec3(-lookVec.z, 0, lookVec.x)); // Vector a la derecha
      // Determinar la inclinación en base al producto punto
      this.targetBodyXRot = (float) -dotForward * (float) Math.PI / 8; // Inclinación adelante/atrás
      this.targetBodyZRot = (float) dotRight * (float) Math.PI / 8; // Inclinación a los costados
    }
  }

  private void interpolateInclinations() {
    if (this.isTame()) {
      this.currentBodyXRot = Mth.lerp(0.2f, this.currentBodyXRot, this.targetBodyXRot);
      this.currentBodyZRot = Mth.lerp(0.2f, this.currentBodyZRot, this.targetBodyZRot);
    }
  }

  public float getBodyXRot() {
    return this.currentBodyXRot;
  }

  public float getBodyZRot() {
    return this.currentBodyZRot;
  }

  public float getRollAmount(float pitch) {
    return Mth.lerp(pitch, this.rollAmountO, this.rollAmount);
  }

  private void updateRollAmount() {
    this.rollAmountO = this.rollAmount;
    if (!this.isFlying() || this.isDroneMoving()) {
      this.rollAmount = Math.min(1.0F, this.rollAmount + 0.2F);
    } else {
      this.rollAmount = Math.max(0.0F, this.rollAmount - 0.24F);
    }
  }

  public float normalizeAngle(float angle) {
    angle = angle % (2 * (float) Math.PI);
    if (angle > Math.PI) {
      angle -= 2 * (float) Math.PI;
    } else if (angle < -Math.PI) {
      angle += 2 * (float) Math.PI;
    }
    return angle;
  }

  @Override
  public boolean canBeLeashed(@NotNull Player player) {
    return (this.isTame() && !this.isInSittingPose());
  }

  @Override
  public boolean hurt(@NotNull DamageSource source, float damage) {
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

  public boolean isDroneMoving() {
    return this.getX() != this.xOld || this.getY() != this.yOld || this.getZ() != this.zOld;
  }

  @Override
  public boolean isFlying() {
    return this.getDroneType() == TYPE_FLYING && !this.onGround();
  }

  @Override
  public AgeableMob getBreedOffspring(@NotNull ServerLevel level, @NotNull AgeableMob mob) {
    return null;
  }
}

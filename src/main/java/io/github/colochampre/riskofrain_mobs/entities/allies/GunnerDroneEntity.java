package io.github.colochampre.riskofrain_mobs.entities.allies;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.entities.goals.GunnerDroneAttackGoal;
import io.github.colochampre.riskofrain_mobs.entities.projectiles.BulletEntity;
import io.github.colochampre.riskofrain_mobs.utils.EntityUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.DifficultyInstance;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class GunnerDroneEntity extends AbstractDroneEntity implements RangedAttackMob {
  private static final EntityDataAccessor<Integer> DATA_BODY_COLOR = SynchedEntityData.defineId(GunnerDroneEntity.class, EntityDataSerializers.INT);
  //private static final Set<EntityType<?>> DO_NOT_ATTACK = Sets.newHashSet(EntityType.CREEPER, EntityType.PIGLIN, EntityType.PIGLIN_BRUTE, EntityType.ZOMBIFIED_PIGLIN, EntityType.HOGLIN, EntityType.ZOGLIN);
  private static final float MAX_ROTATION_SPEED = Mth.PI * 0.3F;
  private static final float ROTATION_ACCELERATION = 0.16F;
  private static final float ROTATION_DECELERATION = 0.012F;
  private float propellerSpeed;
  private float propellerAngle;
  private float prevPropellerAngle;
  private float gunSpeed;
  private float gunAngle;
  private float prevGunAngle;

  public GunnerDroneEntity(EntityType<? extends AbstractDroneEntity> entity, Level level) {
    super(entity, level);
  }

  @Override
  protected int getDroneType() {
    return TYPE_FLYING;
  }

  @Override
  protected void registerGoals() {
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (entity)
            -> entity instanceof Enemy && !(DO_NOT_ATTACK.contains(entity.getType()))));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.0D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 16.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D);
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_BODY_COLOR, DyeColor.LIGHT_BLUE.getId());
  }

  @Override
  public void addAdditionalSaveData(@NotNull CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putByte("BodyColor", (byte) this.getBodyColor().getId());
  }

  @Override
  public void readAdditionalSaveData(@NotNull CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    if (tag.contains("BodyColor", 99)) {
      this.setBodyColor(DyeColor.byId(tag.getInt("BodyColor")));
    }
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.isTame()) {
      this.updateGun();
      this.updatePropeller();
    }
  }

  @Override
  public void setTame(boolean tamed) {
    GunnerDroneAttackGoal attackGoal = new GunnerDroneAttackGoal(this, 16.0F);
    super.setTame(tamed);
    if (tamed) {
      this.goalSelector.addGoal(3, attackGoal);
    }
  }

  public static boolean checkDroneSpawnRules(EntityType<GunnerDroneEntity> entity, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource randomSource) {
    return level.getBlockState(pos.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
  }

  @Override
  public SpawnGroupData finalizeSpawn(@NotNull ServerLevelAccessor level, @NotNull DifficultyInstance instance, @NotNull MobSpawnType type, @Nullable SpawnGroupData groupData, @Nullable CompoundTag compoundTag) {
    Objects.requireNonNull(this.getAttribute(Attributes.ATTACK_DAMAGE)).setBaseValue(RoRConfig.SERVER.BULLETS_DAMAGE.get());
    Objects.requireNonNull(this.getAttribute(Attributes.MAX_HEALTH)).setBaseValue(RoRConfig.SERVER.GUNNER_DRONE_MAX_HEALTH.get());
    this.setHealth(this.getMaxHealth());
    return super.finalizeSpawn(level, instance, type, groupData, compoundTag);
  }

  @Override
  public @NotNull InteractionResult mobInteract(Player player, @NotNull InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    Item item = itemstack.getItem();
    // Set body color
    if (item instanceof DyeItem && this.isTame()) {
      DyeColor dyecolor = ((DyeItem) item).getDyeColor();
      if (dyecolor != this.getBodyColor()) {
        this.setBodyColor(dyecolor);
        if (!player.getAbilities().instabuild) {
          itemstack.shrink(1);
        }
        return InteractionResult.SUCCESS;
      }
    }
    return super.mobInteract(player, hand);
  }

  @Override
  public void performRangedAttack(LivingEntity target, float distanceFactor) {
    BulletEntity projectile = new BulletEntity(this.level(), this);
    double d0 = target.getEyeY() - (double) 0.75F;
    double d1 = target.getX() - this.getX();
    double d2 = d0 - projectile.getY();
    double d3 = target.getZ() - this.getZ();
    double d4 = Math.sqrt(Math.sqrt(d0)) * 0.25D;
    projectile.shoot(d1, d2 + d4, d3, 6.0F, 1.0F);
    this.level().addFreshEntity(projectile);
  }

  @Override
  public boolean wantsToAttack(@NotNull LivingEntity livingentity, @NotNull LivingEntity owner) {
    if (livingentity instanceof Wolf) {
      Wolf wolf = (Wolf) livingentity;
      return !wolf.isTame() || wolf.getOwner() != owner;
    } else if (livingentity instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) livingentity)) {
      return false;
    } else if (livingentity instanceof AbstractHorse && ((AbstractHorse) livingentity).isTamed()) {
      return false;
    } else if (livingentity instanceof AbstractDroneEntity && ((AbstractDroneEntity) livingentity).isTame()) {
      return false;
    } else {
      return !(livingentity instanceof TamableAnimal) || !((TamableAnimal) livingentity).isTame();
    }
  }

  private void updatePropeller() {
    this.prevPropellerAngle = this.propellerAngle;
    if (this.isFlying()) {
      this.propellerSpeed = Math.min(this.propellerSpeed + ROTATION_ACCELERATION, MAX_ROTATION_SPEED);
    } else if (this.onGround()) {
      this.propellerSpeed = Math.max(this.propellerSpeed - ROTATION_DECELERATION, 0.0F);
      if (this.propellerSpeed == 0) {
        propellerAngle = EntityUtils.normalizeAngle(this.propellerAngle);
      }
    }
    this.propellerAngle += this.propellerSpeed;
  }

  private void updateGun() {
    LivingEntity target = this.getActiveAttackTarget();
    this.prevGunAngle = this.gunAngle;
    if (target != null) {
      this.gunSpeed = Math.min(this.gunSpeed + ROTATION_ACCELERATION, MAX_ROTATION_SPEED);
    } else {
      this.gunSpeed = Math.max(this.gunSpeed - ROTATION_DECELERATION, 0.0F);
      if (this.gunSpeed == 0) {
        gunAngle = EntityUtils.normalizeAngle(this.gunAngle);
      }
    }
    this.gunAngle += this.gunSpeed;
  }

  public float getGunAngle() {
    return this.gunAngle;
  }

  public float getPrevGunAngle() {
    return this.prevGunAngle;
  }

  public float getGunSpeed() {
    return this.gunSpeed;
  }

  public float getPropellerAngle() {
    return this.propellerAngle;
  }

  public float getPrevPropellerAngle() {
    return this.prevPropellerAngle;
  }

  public float getPropellerSpeed() {
    return this.propellerSpeed;
  }

  public DyeColor getBodyColor() {
    return DyeColor.byId(this.entityData.get(DATA_BODY_COLOR));
  }

  public void setBodyColor(DyeColor color) {
    this.entityData.set(DATA_BODY_COLOR, color.getId());
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 0.055F;
  }

  public @NotNull Vec3 getLeashOffset() {
    return new Vec3(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.2F));
  }
}

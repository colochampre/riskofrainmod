package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.entities.goals.GunnerDroneAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.animal.Wolf;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.DyeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.phys.Vec3;

public class GunnerDroneEntity extends AbstractFlyingDroneEntity implements RangedAttackMob {
  private static final EntityDataAccessor<Integer> DATA_BODY_COLOR = SynchedEntityData.defineId(GunnerDroneEntity.class, EntityDataSerializers.INT);
  private final GunnerDroneAttackGoal attackGoal = new GunnerDroneAttackGoal(this, 16.0F);
  //private int attackTimer;

  public GunnerDroneEntity(EntityType<? extends GunnerDroneEntity> entity, Level level) {
    super(entity, level);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new SitWhenOrderedToGoal(this));
    this.targetSelector.addGoal(1, new OwnerHurtByTargetGoal(this));
    this.targetSelector.addGoal(2, new OwnerHurtTargetGoal(this));
    this.targetSelector.addGoal(3, new HurtByTargetGoal(this));
    this.targetSelector.addGoal(4, new NearestAttackableTargetGoal<>(this, Mob.class, 5, false, false, (entity) -> {
      return entity instanceof Enemy && !(entity instanceof Creeper);
    }));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 20.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.0D);
  }

  public float getWalkTargetValue(BlockPos pos, LevelReader level) {
    return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
  }

  @Override
  public void aiStep() {
    if (this.isTame()) {
      this.goalSelector.addGoal(2, this.attackGoal);
    }
    /*
    if (this.attackTimer > 0) {
      --this.attackTimer;
    }
    */
    super.aiStep();
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_BODY_COLOR, DyeColor.LIGHT_BLUE.getId());
  }

  @Override
  public void addAdditionalSaveData(CompoundTag tag) {
    super.addAdditionalSaveData(tag);
    tag.putByte("BodyColor", (byte) this.getBodyColor().getId());
  }

  @Override
  public void readAdditionalSaveData(CompoundTag tag) {
    super.readAdditionalSaveData(tag);
    if (tag.contains("BodyColor", 99)) {
      this.setBodyColor(DyeColor.byId(tag.getInt("BodyColor")));
    }
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
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

  public DyeColor getBodyColor() {
    return DyeColor.byId(this.entityData.get(DATA_BODY_COLOR));
  }

  public void setBodyColor(DyeColor color) {
    this.entityData.set(DATA_BODY_COLOR, color.getId());
  }

  public static boolean checkDroneSpawnRules(EntityType<GunnerDroneEntity> drone, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource randomSource) {
    return level.getBlockState(pos.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
  }

  @Override
  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return 0.055F;
  }

  public Vec3 getLeashOffset() {
    return new Vec3(0.0D, (double) (0.6F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.2F));
  }

  @Override
  public void performRangedAttack(LivingEntity livingentity, float distanceFactor) {
    BulletEntity projectile = new BulletEntity(this.level, this);
    double d0 = livingentity.getEyeY() - (double) 1.1F;
    double d1 = livingentity.getX() - this.getX();
    double d2 = d0 - projectile.getY();
    double d3 = livingentity.getZ() - this.getZ();
    double d4 = Math.sqrt(Math.sqrt(d0)) * 0.25D;
    projectile.shoot(d1, d2 + d4, d3, 4.0F, 1.0F);
    this.level.addFreshEntity(projectile);
  }

  @Override
  public boolean wantsToAttack(LivingEntity livingentity, LivingEntity owner) {
    if (!(livingentity instanceof Creeper)) {
      if (livingentity instanceof Wolf) {
        Wolf wolf = (Wolf) livingentity;
        return !wolf.isTame() || wolf.getOwner() != owner;
      } else if (livingentity instanceof Player && owner instanceof Player && !((Player) owner).canHarmPlayer((Player) livingentity)) {
        return false;
      } else if (livingentity instanceof AbstractHorse && ((AbstractHorse) livingentity).isTamed()) {
        return false;
      } else if (livingentity instanceof AbstractFlyingDroneEntity && ((AbstractFlyingDroneEntity) livingentity).isTame()) {
        return false;
      } else {
        return !(livingentity instanceof TamableAnimal) || !((TamableAnimal) livingentity).isTame();
      }
    } else {
      return false;
    }
  }
}

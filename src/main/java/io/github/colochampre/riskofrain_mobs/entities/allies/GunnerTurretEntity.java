package io.github.colochampre.riskofrain_mobs.entities.allies;

import io.github.colochampre.riskofrain_mobs.entities.goals.GunnerTurretAttackGoal;
import io.github.colochampre.riskofrain_mobs.entities.projectiles.BulletEntity;
import io.github.colochampre.riskofrain_mobs.init.ItemInit;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

public class GunnerTurretEntity extends AbstractDroneEntity implements RangedAttackMob {

  public GunnerTurretEntity(EntityType<? extends AbstractDroneEntity> type, Level level) {
    super(type, level);
  }

  @Override
  protected int getDroneType() {
    return TYPE_LAND;
  }

  @Override
  protected int getPrice() {
    return 36;
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.0D)
            .add(Attributes.FLYING_SPEED, 1.0D)
            .add(Attributes.FOLLOW_RANGE, 16.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.23D);
  }

  @Override
  protected void defineSynchedData() {
    super.defineSynchedData();
  }

  @Override
  public void setTame(boolean tamed) {
    GunnerTurretAttackGoal attackGoal = new GunnerTurretAttackGoal(this, 16.0F);
    super.setTame(tamed);
    if (tamed) {
      this.goalSelector.addGoal(3, attackGoal);
    }
  }

  public static boolean checkDroneSpawnRules(EntityType<GunnerTurretEntity> entity, LevelAccessor level, MobSpawnType type, BlockPos pos, RandomSource randomSource) {
    return level.getBlockState(pos.below()).is(BlockTags.RABBITS_SPAWNABLE_ON) && isBrightEnoughToSpawn(level, pos);
  }

  @Override
  public void performRangedAttack(@NotNull LivingEntity target, float distanceFactor) {
    BulletEntity projectile = new BulletEntity(this.level(), this);
    double d0 = target.getEyeY() - (double) 0.75F;
    double d1 = target.getX() - this.getX();
    double d2 = d0 - projectile.getY();
    double d3 = target.getZ() - this.getZ();
    double d4 = Math.sqrt(Math.sqrt(d0)) * 0.25D;
    projectile.shoot(d1, d2 + d4, d3, 6.0F, 1.0F);
    this.level().addFreshEntity(projectile);
  }

  public boolean hurt(@NotNull DamageSource source, float amount) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else if (source.getEntity() instanceof Player && this.isTame()) {
      if (!this.level().isClientSide && !this.isRemoved()) {
        boolean isCreativeMode = ((Player) Objects.requireNonNull(source.getEntity())).getAbilities().instabuild;
        if (isCreativeMode || amount > 1.0F) {
          if (!isCreativeMode && this.level().getGameRules().getBoolean(GameRules.RULE_DOENTITYDROPS)) {
            this.getDestroyed(source);
          }
          this.discard();
        }
      }
      return true;
    } else {
      return super.hurt(source, amount);
    }
  }

  protected void getDestroyed(DamageSource source) {
    ItemStack stack = new ItemStack(getDropItem());
    CompoundTag tag = new CompoundTag();
    tag.putFloat("TurretHealth", this.getHealth());
    if (this.getOwnerUUID() != null) {
      tag.putUUID("OwnerUUID", this.getOwnerUUID());
    }
    stack.setTag(tag);
    this.spawnAtLocation(stack);
  }

  private Item getDropItem() {
    return ItemInit.GUNNER_TURRET_ITEM.get();
  }

  public ItemStack getPickResult() {
    return new ItemStack(this.getDropItem());
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 1.075F;
  }

  public @NotNull Vec3 getLeashOffset() {
    return new Vec3(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.2F));
  }

  public boolean isPushable() {
    return false;
  }
}

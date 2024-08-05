package io.github.colochampre.riskofrain_mobs.entities.allies;

import io.github.colochampre.riskofrain_mobs.entities.projectiles.BulletEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class GunnerTurretEntity extends AbstractDroneEntity implements RangedAttackMob {

  public GunnerTurretEntity(EntityType<? extends AbstractDroneEntity> type, Level level) {
    super(type, level);
  }

  @Override
  protected int getDroneType() {
    return TYPE_LAND;
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
  public void setTame(boolean tamed) {
    //GunnerTurretAttackGoal attackGoal = new GunnerTurretAttackGoal(this, 16.0F);
    super.setTame(tamed);
    if (tamed) {
      //this.goalSelector.addGoal(3, attackGoal);
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

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 1.075F;
  }

  public @NotNull Vec3 getLeashOffset() {
    return new Vec3(0.0D, (0.6F * this.getEyeHeight()), (this.getBbWidth() * 0.2F));
  }
}

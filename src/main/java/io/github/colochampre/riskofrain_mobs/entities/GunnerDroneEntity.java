package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.entities.goals.GunnerDroneAttackGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.SitWhenOrderedToGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.OwnerHurtTargetGoal;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

public class GunnerDroneEntity extends AbstractFlyingDroneEntity implements RangedAttackMob {
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
            .add(Attributes.FLYING_SPEED, 1.0D) //
            .add(Attributes.FOLLOW_RANGE, 16.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.4D);
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

  public static boolean canSpawn(EntityType<GunnerDroneEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkAnimalSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
    return 0.055F;
  }

  public Vec3 getLeashOffset() {
    return new Vec3(0.0D, (double) (0.6F * this.getEyeHeight()), (double) (this.getBbWidth() * 0.4F));
  }

  @Override
  public void performRangedAttack(LivingEntity p_33317_, float distanceFactor) {
  }
}

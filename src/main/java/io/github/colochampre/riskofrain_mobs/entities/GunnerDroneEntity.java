package io.github.colochampre.riskofrain_mobs.entities;

import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.pathfinder.BlockPathTypes;

public class GunnerDroneEntity extends AbstractFlyingDroneEntity implements RangedAttackMob {
  //private final GunnerDroneAttackGoal attackGoal = new GunnerDroneAttackGoal(this, 16.0F);
  public GunnerDroneEntity(EntityType<? extends GunnerDroneEntity> entity, Level level) {
    super(entity, level);
    /*
    this.moveControl = new FlyingMoveControl(this, 16, false);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_CACTUS, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    */
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Mob.createMobAttributes()
            .add(Attributes.ATTACK_DAMAGE, 1.0D)
            .add(Attributes.FLYING_SPEED, 0.8D)
            .add(Attributes.FOLLOW_RANGE, 20.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.4D);
  }

  @Override
  public void aiStep() {
    if (this.isTame()) {
      //this.goalSelector.addGoal(2, this.attackGoal);
    }
    super.aiStep();
  }

  @Override
  protected float getStandingEyeHeight(Pose p_21131_, EntityDimensions p_21132_) {
    return 0.055F;
  }

  @Override
  public void performRangedAttack(LivingEntity p_33317_, float distanceFactor) {
  }
}

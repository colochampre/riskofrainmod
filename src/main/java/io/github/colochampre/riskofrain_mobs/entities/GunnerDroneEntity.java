package io.github.colochampre.riskofrain_mobs.entities;

import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Pose;
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

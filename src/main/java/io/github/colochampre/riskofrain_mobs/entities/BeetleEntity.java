package io.github.colochampre.riskofrain_mobs.entities;

import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

public class BeetleEntity extends Monster {

  public BeetleEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.xpReward = 8;
    this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 1.0D));
    //this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    //this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ARMOR, 2.0D)
            .add(Attributes.ATTACK_DAMAGE, 2.0D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.23D);
  }

  public static boolean canSpawn(EntityType<BeetleEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 1.4375F;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getZ() != entity.zOld;
  }
}

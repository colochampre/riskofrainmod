package io.github.colochampre.riskofrainmod.entities;

import io.github.colochampre.riskofrainmod.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;

public class LemurianEntity extends Monster {
  public LemurianEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    //this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, IronGolem.class, 8.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Creeper.class, 6.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ARMOR, 4.0D)
            .add(Attributes.ATTACK_DAMAGE, 3.5D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.3D);
  }

  public static boolean canSpawn(EntityType<LemurianEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  protected SoundEvent getAmbientSound() {
    return SoundInit.LEMURIAN_AMBIENT.get();
  }

  protected SoundEvent getAttackSound() {
    return SoundInit.LEMURIAN_ATTACK.get();
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.LEMURIAN_DEATH.get();
  }

  @Override
  protected SoundEvent getHurtSound(DamageSource source) {
    return SoundInit.LEMURIAN_HURT.get();
  }

  protected SoundEvent getStepSound() {
    return SoundInit.LEMURIAN_STEP.get();
  }

  protected void playStepSound(BlockPos pos, BlockState blockState) {
    this.playSound(this.getStepSound(), 0.15F, 1.0F);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    return super.causeFallDamage(p_147187_, p_147188_, p_147189_);
  }

  @Override
  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return 1.62F;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getY() != entity.yOld;
  }
}

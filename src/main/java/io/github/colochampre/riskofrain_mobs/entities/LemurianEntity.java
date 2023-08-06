package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
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
import net.minecraft.world.entity.npc.WanderingTrader;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.ServerLevelAccessor;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

public class LemurianEntity extends Monster {
  private int attackTimer;
  private boolean selectingHand = true;
  private boolean rightHandSelected = true;

  public LemurianEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.xpReward = 12;
    this.setPathfindingMalus(BlockPathTypes.POWDER_SNOW, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_POWDER_SNOW, -1.0F);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, IronGolem.class, 8.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Creeper.class, 6.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(4, new MeleeAttackGoal(this, 1.0D, true));
    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, (new HurtByTargetGoal(this)).setAlertOthers());
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
  }

  public static AttributeSupplier.Builder createAttributes() {
    return Monster.createMonsterAttributes()
            .add(Attributes.ARMOR, 4.0D)
            .add(Attributes.ATTACK_DAMAGE, 3.5D)
            .add(Attributes.FOLLOW_RANGE, 32.0D)
            .add(Attributes.MAX_HEALTH, 20.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.3D);
  }

  @Override
  public void aiStep() {
    super.aiStep();
    if (this.attackTimer > 0) {
      --this.attackTimer;
    }
  }

  public static boolean canSpawn(EntityType<LemurianEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, @NotNull DamageSource p_147189_) {
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    return super.causeFallDamage(p_147187_, p_147188_, p_147189_);
  }

  @Override
  public boolean doHurtTarget(Entity entity) {
    this.attackTimer = 10;
    this.level.broadcastEntityEvent(this, (byte) 4);
    float f = this.getAttackDamage();
    boolean flag = entity.hurt(this.damageSources().mobAttack(this), f);
    this.playSound(SoundInit.LEMURIAN_ATTACK.get(), 1.0F, 1.0F);
    return flag;
  }

  public float getAttackDamage() {
    float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    if (this.level.getDifficulty() == Difficulty.HARD) {
      f *= 2.0F;
    }
    return f;
  }

  public int getAttackTimer() {
    return this.attackTimer;
  }

  public boolean getIsRightHandSelected() {
    //this.armSelected = ++this.armSelected % 2;
    this.rightHandSelected = !this.rightHandSelected;
    return this.rightHandSelected;
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
  protected SoundEvent getHurtSound(@NotNull DamageSource source) {
    return SoundInit.LEMURIAN_HURT.get();
  }

  protected SoundEvent getStepSound() {
    return SoundInit.LEMURIAN_STEP.get();
  }

  protected void playStepSound(@NotNull BlockPos pos, @NotNull BlockState blockState) {
    this.playSound(this.getStepSound(), 0.15F, 1.0F);
  }

  public boolean getIsSelectedHand() {
    return this.selectingHand;
  }

  @Override
  protected float getStandingEyeHeight(@NotNull Pose pose, @NotNull EntityDimensions dimensions) {
    return 1.62F;
  }

  @Override
  public void handleEntityEvent(byte b) {
    if (b == 4) {
      this.attackTimer = 10;
      this.playSound(SoundInit.LEMURIAN_ATTACK.get(), 1.0F, 1.0F);
    } else {
      super.handleEntityEvent(b);
    }
  }

  @Override
  public boolean removeWhenFarAway(double distance) {
    return RoRConfig.SERVER.LEMURIANS_DESPAWN.get();
  }

  public void setIsSelectingHand(boolean value) {
    this.selectingHand = value;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getZ() != entity.zOld;
  }
}

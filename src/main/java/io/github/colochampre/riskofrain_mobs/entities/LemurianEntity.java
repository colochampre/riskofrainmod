package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
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

public class LemurianEntity extends Monster {
  private int attackTimer;
  private boolean rightHandSelected = true;
  private int armSelected = 0;

  public LemurianEntity(EntityType<? extends Monster> type, Level level) {
    super(type, level);
    this.xpReward = 12;
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
    // Walking particles effect
    if (this.getDeltaMovement().horizontalDistanceSqr() > (double)2.5000003E-7F && this.random.nextInt(5) == 0) {
      int i = Mth.floor(this.getX());
      int j = Mth.floor(this.getY() - (double)0.2F);
      int k = Mth.floor(this.getZ());
      BlockPos pos = new BlockPos(i, j, k);
      BlockState blockstate = this.level.getBlockState(pos);
      if (!blockstate.isAir()) {
        this.level.addParticle(new BlockParticleOption(ParticleTypes.BLOCK, blockstate).setPos(pos), this.getX() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), this.getY() + 0.1D, this.getZ() + ((double)this.random.nextFloat() - 0.5D) * (double)this.getBbWidth(), 4.0D * ((double)this.random.nextFloat() - 0.5D), 0.5D, ((double)this.random.nextFloat() - 0.5D) * 4.0D);
      }
    }
  }

  public int getAttackTimer() {
    return this.attackTimer;
  }

  public static boolean canSpawn(EntityType<LemurianEntity> entityType, ServerLevelAccessor level, MobSpawnType spawnType, BlockPos pos, RandomSource random) {
    return checkMonsterSpawnRules(entityType, level, spawnType, pos, random);
  }

  @Override
  public boolean causeFallDamage(float p_147187_, float p_147188_, DamageSource p_147189_) {
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    this.playSound(this.getStepSound(), 0.8F, 1.0F);
    return super.causeFallDamage(p_147187_, p_147188_, p_147189_);
  }

  @Override
  public boolean doHurtTarget(Entity entity) {
    this.attackTimer = 10;
    this.level.broadcastEntityEvent(this, (byte)4);
    float f = this.getAttackDamage();
    boolean flag = entity.hurt(DamageSource.mobAttack(this), f);
    this.playSound(SoundInit.LEMURIAN_ATTACK.get(), 1.0F, 1.0F);
    return flag;
  }

  private float getAttackDamage() {
    return (float)this.getAttributeValue(Attributes.ATTACK_DAMAGE);
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
  protected float getStandingEyeHeight(Pose pose, EntityDimensions dimensions) {
    return 1.62F;
  }
  /*
  public BlockPos getLightPosition() {
    BlockPos pos = new BlockPos(this.position());
    if (!level.getBlockState(pos).canOcclude()) {
      return pos.above();
    }
    return pos;
  }
  */
  @Override
  public void handleEntityEvent(byte b) {
    if (b == 4) {
      this.attackTimer = 10;
      this.playSound(SoundInit.LEMURIAN_ATTACK.get(), 1.0F, 1.0F);
    } else {
      super.handleEntityEvent(b);
    }
  }

  public boolean getSelectedArm() {
    return this.rightHandSelected;
  }

  public void setRightArmSelected(boolean value) {
    this.rightHandSelected = value;
  }

  public int getChoice() {
    this.armSelected = ++this.armSelected % 2;
    return this.armSelected;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getY() != entity.yOld;
  }
}

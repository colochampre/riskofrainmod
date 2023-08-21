package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.entities.goals.LemurianAttackGoal;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Difficulty;
import net.minecraft.world.DifficultyInstance;
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
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.stream.Collectors;

public class LemurianEntity extends Monster {
  private static final EntityDataAccessor<Integer> DATA_TYPE_ID = SynchedEntityData.defineId(LemurianEntity.class, EntityDataSerializers.INT);
  private final float FIREBALL_ATTACK_RANGE = 20;
  private final MeleeAttackGoal meleeAttackGoal = new MeleeAttackGoal(this, 1.0D, true);
  private final LemurianAttackGoal fireballAttackGoal = new LemurianAttackGoal(this, this.FIREBALL_ATTACK_RANGE, 0.8D);
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
  protected void defineSynchedData() {
    super.defineSynchedData();
    this.entityData.define(DATA_TYPE_ID, 0);
  }

  @Override
  protected void registerGoals() {
    this.goalSelector.addGoal(1, new FloatGoal(this));
    this.goalSelector.addGoal(2, new AvoidEntityGoal<>(this, IronGolem.class, 8.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(3, new AvoidEntityGoal<>(this, Creeper.class, 6.0F, 0.8D, 1.0D));
    this.goalSelector.addGoal(7, new WaterAvoidingRandomStrollGoal(this, 0.6D));
    this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 8.0F));
    this.goalSelector.addGoal(8, new RandomLookAroundGoal(this));
    this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
    this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, WanderingTrader.class, true));
    //this.targetSelector.addGoal(4, (new HurtByTargetGoal(this)).setAlertOthers());
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
    if (this.isEvolved() && RoRConfig.SERVER.ENABLE_FIREBALL_ATTACK.get()) {
      LivingEntity livingentity = this.getTarget();
      if (livingentity != null) {
        float attackReach = this.FIREBALL_ATTACK_RANGE * this.FIREBALL_ATTACK_RANGE;
        double d0 = this.distanceToSqr(livingentity);
        if (d0 < (double) attackReach && d0 > (double) attackReach * 0.3) {
          this.goalSelector.addGoal(4, this.fireballAttackGoal);
          this.goalSelector.removeGoal(this.meleeAttackGoal);
        } else {
          this.goalSelector.addGoal(4, this.meleeAttackGoal);
          this.goalSelector.removeGoal(this.fireballAttackGoal);
        }
      }
    } else {
      this.goalSelector.addGoal(4, this.meleeAttackGoal);
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
    //this.attackTimer = 10;
    this.level().broadcastEntityEvent(this, (byte) 4);
    float f = this.getAttackDamage();
    boolean flag = entity.hurt(this.damageSources().mobAttack(this), f);
    this.playSound(SoundInit.LEMURIAN_ATTACK.get(), 1.0F, 1.0F);
    return flag;
  }

  @Override
  public SpawnGroupData finalizeSpawn(ServerLevelAccessor level, @NotNull DifficultyInstance difficulty, @NotNull MobSpawnType type, SpawnGroupData groupData, CompoundTag nbt) {
    Holder<Biome> holder = level.getBiome(this.blockPosition());
    LemurianEntity.Type lemurian$type = LemurianEntity.Type.byBiome(holder);
    this.setLemurianType(lemurian$type);
    return super.finalizeSpawn(level, difficulty, type, groupData, nbt);
  }

  public LemurianEntity.Type getLemurianType() {
    return LemurianEntity.Type.byId(this.entityData.get(DATA_TYPE_ID));
  }

  private void setLemurianType(LemurianEntity.Type type) {
    this.entityData.set(DATA_TYPE_ID, type.getId());
  }

  @Override
  public void addAdditionalSaveData(@NotNull CompoundTag nbt) {
    super.addAdditionalSaveData(nbt);
    nbt.putString("Type", this.getLemurianType().getName());
  }

  @Override
  public void readAdditionalSaveData(@NotNull CompoundTag nbt) {
    super.readAdditionalSaveData(nbt);
    this.setLemurianType(LemurianEntity.Type.byName(nbt.getString("Type")));
  }

  public float getAttackDamage() {
    float f = (float) this.getAttributeValue(Attributes.ATTACK_DAMAGE);
    if (this.level().getDifficulty() == Difficulty.HARD) {
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

  public boolean isEvolved() {
    return this.getEntityData().get(DATA_TYPE_ID) == 1;
  }

  public static boolean isMoving(LivingEntity entity) {
    return entity.getX() != entity.xOld || entity.getZ() != entity.zOld;
  }

  public static enum Type {
    DEFAULT(0, "default"),
    EVOLVED(1, "evolved");

    private static final LemurianEntity.Type[] BY_ID = Arrays.stream(values()).sorted(Comparator.comparingInt(LemurianEntity.Type::getId)).toArray((p_28822_) -> {
      return new LemurianEntity.Type[p_28822_];
    });
    private static final Map<String, Type> BY_NAME = Arrays.stream(values()).collect(Collectors.toMap(LemurianEntity.Type::getName, (p_28815_) -> {
      return p_28815_;
    }));
    private final int id;
    private final String name;

    private Type(int id, String name) {
      this.id = id;
      this.name = name;
    }

    public String getName() {
      return this.name;
    }

    public int getId() {
      return this.id;
    }

    public static LemurianEntity.Type byName(String name) {
      return BY_NAME.getOrDefault(name, DEFAULT);
    }

    public static LemurianEntity.Type byId(int i) {
      if (i < 0 || i > BY_ID.length) {
        i = 0;
      }
      return BY_ID[i];
    }

    public static LemurianEntity.Type byBiome(Holder<Biome> biome) {
      return (biome.value().getBaseTemperature() >= 1.0F && !biome.is(Biomes.WARPED_FOREST)) ? EVOLVED : DEFAULT;
    }
  }
}

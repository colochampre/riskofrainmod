package io.github.colochampre.riskofrain_mobs.entities;

import com.google.common.collect.Sets;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.entities.goals.DroneFollowOwnerGoal;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.Set;

public class AbstractFlyingDroneEntity extends TamableAnimal implements FlyingAnimal {
  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.RAW_GOLD);
  private static final Set<Item> REPAIR_ITEMS = Sets.newHashSet(Items.IRON_INGOT, Items.IRON_NUGGET, Items.RAW_IRON);
  private final FloatGoal floatGoal = new FloatGoal(this);
  private final DroneFollowOwnerGoal followOwnerGoal = new DroneFollowOwnerGoal(this, 1.0D, 8.0F, 4.0F, true);
  private final RandomLookAroundGoal randomLookAroundGoal = new RandomLookAroundGoal(this);
  private final WaterAvoidingRandomFlyingGoal randomFlyingGoal = new WaterAvoidingRandomFlyingGoal(this, 0.5D);
  private float rollAmount;
  private float rollAmountO;
  private int flyingSound;
  private int goldCount = this.setGoldCount();
  private int underWaterTicks;

  public AbstractFlyingDroneEntity(EntityType<? extends AbstractFlyingDroneEntity> type, Level level) {
    super(type, level);
    this.moveControl = new FlyingMoveControl(this, 16, true);
    this.setPathfindingMalus(BlockPathTypes.COCOA, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_CACTUS, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.FENCE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER_BORDER, 16.0F);
  }

  protected PathNavigation createNavigation(Level level) {
    FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level) {
      public boolean isStableDestination(BlockPos pos) {
        return !this.level.getBlockState(pos.below()).isAir();
      }
    };
    flyingpathnavigation.setCanOpenDoors(false);
    flyingpathnavigation.setCanFloat(false);
    flyingpathnavigation.setCanPassDoors(true);
    return flyingpathnavigation;
  }

  public float getWalkTargetValue(BlockPos pos, LevelReader level) {
    return level.getBlockState(pos).isAir() ? 30.0F : 0.0F;
  }

  @Override
  public void aiStep() {
    if (this.isTame()) {
      this.goalSelector.addGoal(3, this.followOwnerGoal);
      this.goalSelector.addGoal(4, this.randomFlyingGoal);
      this.goalSelector.addGoal(5, this.randomLookAroundGoal);
      this.goalSelector.addGoal(6, this.floatGoal);
    }
    this.landIfSitting();
    super.aiStep();
  }

  @Override
  public void tick() {
    this.doFlyingSound();
    this.smokeIfLowHealth();
    this.waterDamage();
    this.updateRollAmount();
    super.tick();
  }

  private void doFlyingSound() {
    if (!this.isTame() || this.onGround || this.isInSittingPose() || this.isOrderedToSit()) {
      flyingSound = 0;
    } else {
      ++flyingSound;
    }
    if (flyingSound == 1 || flyingSound == 31) {
      this.playSound(this.getFlyingSound(), 0.05F, 1.0F);
      flyingSound = 1;
    }
  }

  private void landIfSitting() {
    Vec3 vec3 = this.getDeltaMovement();
    if (this.isTame() && this.isOrderedToSit()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) -0.1F - vec3.y), 0.0D));
      this.hasImpulse = true;
    }
  }

  private void smokeIfLowHealth() {
    if (this.isLowHealth()) {
      for (int i = 0; i < 2; ++i) {
        this.level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
      }
    }
  }

  private void waterDamage() {
    if (this.isInWaterOrBubble()) {
      ++this.underWaterTicks;
    } else {
      this.underWaterTicks = 0;
    }
    if (this.underWaterTicks > 20) {
      this.hurt(DamageSource.DROWN, 1.0F);
    }
  }

  public boolean causeFallDamage(float p_149683_, float p_149684_, DamageSource source) {
    return false;
  }

  protected int decreaseAirSupply(int air) {
    return air;
  }

  @Override
  public InteractionResult mobInteract(Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    if (this.isTame()) {
      // Repair
      if (this.getHealth() < this.getMaxHealth()) {
        if (REPAIR_ITEMS.contains(itemstack.getItem())) {
          if (!player.getAbilities().instabuild) {
            itemstack.shrink(1);
          }
          this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundEvents.IRON_GOLEM_REPAIR, this.getSoundSource(), 0.2F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
          if (itemstack.getItem().equals(Items.IRON_INGOT)) {
            this.heal(18.0F);
          } else if (itemstack.getItem().equals(Items.RAW_IRON)) {
            this.heal(12.0F);
          } else {
            this.heal(2.0F);
          }
          return InteractionResult.SUCCESS;
        }
      }
      // Set sitting
      if (this.isOwnedBy(player)) { // !dyeItem
        this.setOrderedToSit(!this.isOrderedToSit());
        this.navigation.stop();
        this.setTarget((LivingEntity) null);
        this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.DRONE_REPAIR.get(), this.getSoundSource(), 0.2F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        return InteractionResult.SUCCESS;
      }
      // No gold
    } else if (!this.isTame()) {
      if (!TAME_ITEMS.contains(itemstack.getItem())) {
        this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.INSUFFICIENT_FOUNDS_PROC.get(), this.getSoundSource(), 0.5F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        return InteractionResult.SUCCESS;
        // Taming
      } else if (TAME_ITEMS.contains(itemstack.getItem())) {
        if (!player.getAbilities().instabuild) {
          itemstack.shrink(1);
        }
        this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.COIN_PROC.get(), this.getSoundSource(), 1.0F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
        if (itemstack.getItem().equals(Items.GOLD_INGOT)) {
          goldCount -= 9;
        } else if (itemstack.getItem().equals(Items.RAW_GOLD)) {
          goldCount -= 5;
        } else {
          goldCount--;
        }
        RoRmod.LOGGER.info("goldCount = " + goldCount);
        if (!this.level.isClientSide) {
          if (this.goldCount <= 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, player)) {
            this.tame(player);
            this.level.playSound((Player) null, this.getX(), this.getY(), this.getZ(), SoundInit.DRONE_REPAIR.get(), this.getSoundSource(), 0.6F, 1.0F + (this.random.nextFloat() - this.random.nextFloat()) * 0.2F);
            this.level.broadcastEntityEvent(this, (byte) 7);
            RoRmod.LOGGER.info("Drone tamed = " + this.isTame());
          } else {
            this.level.broadcastEntityEvent(this, (byte) 6);
          }
        }
        return InteractionResult.SUCCESS;
      }
    }
    return super.mobInteract(player, hand);
  }

  @Override
  public void die(DamageSource source) {
    this.playSound(this.getShutDownSound(), 0.6F, 1.0F);
    super.die(source);
  }

  @Override
  protected SoundEvent getDeathSound() {
    return SoundInit.DRONE_DEATH1.get();
  }

  protected SoundEvent getShutDownSound() {
    return SoundInit.DRONE_DEATH2.get();
  }

  protected SoundEvent getFlyingSound() {
    return SoundInit.DRONE_FLYING.get();
  }

  protected void playStepSound(BlockPos pos, BlockState blockIn) {
  }

  public int getGoldCount() {
    return goldCount;
  }

  private int setGoldCount() {
    Difficulty difficulty = this.level.getDifficulty();
    if (difficulty == Difficulty.HARD) {
      goldCount = 27;
    } else {
      goldCount = 18;
    }
    return goldCount;
  }

  public float getRollAmount(float pitch) {
    return Mth.lerp(pitch, this.rollAmountO, this.rollAmount);
  }

  private void updateRollAmount() {
    this.rollAmountO = this.rollAmount;
    if (!this.isFlying() || this.isDroneMoving()) {
      this.rollAmount = Math.min(1.0F, this.rollAmount + 0.2F);
    } else {
      this.rollAmount = Math.max(0.0F, this.rollAmount - 0.24F);
    }
  }

  @Override
  public boolean hurt(DamageSource source, float damage) {
    if (this.isInvulnerableTo(source)) {
      return false;
    } else {
      Entity entity = source.getEntity();
      if (!this.level.isClientSide) {
        this.setOrderedToSit(false);
      }

      if (entity != null && !(entity instanceof Player) && !(entity instanceof AbstractArrow)) {
        damage = (damage + 1.0F) / 2.0F;
      }
      return super.hurt(source, damage);
    }
  }

  public boolean isDroneMoving() {
    return this.getX() != this.xOld || this.getY() != this.yOld || this.getZ() != this.zOld;
  }

  @Override
  public boolean isFlying() {
    return !this.onGround;
  }

  public boolean isLowHealth() {
    double health = this.getHealth();
    if (health < 10.0D) {
      return true;
    } else {
      return false;
    }
  }

  @Override
  public AgeableMob getBreedOffspring(ServerLevel level, AgeableMob mob) {
    return null;
  }

  @Override
  public boolean canBreed() {
    return false;
  }
}

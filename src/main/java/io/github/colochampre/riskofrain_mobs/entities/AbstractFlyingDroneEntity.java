package io.github.colochampre.riskofrain_mobs.entities;

import com.google.common.collect.Sets;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentContents;
import net.minecraft.network.chat.Style;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.util.Mth;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.control.FlyingMoveControl;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomFlyingGoal;
import net.minecraft.world.entity.ai.navigation.FlyingPathNavigation;
import net.minecraft.world.entity.ai.navigation.PathNavigation;
import net.minecraft.world.entity.animal.FlyingAnimal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.BlockPathTypes;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Set;

public class AbstractFlyingDroneEntity extends TamableAnimal implements FlyingAnimal {
  private static final Set<Item> TAME_ITEMS = Sets.newHashSet(Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.RAW_GOLD);
  private static final Set<Item> REPAIR_ITEMS = Sets.newHashSet(Items.IRON_INGOT, Items.IRON_NUGGET, Items.RAW_IRON);
  private final FloatGoal floatGoal = new FloatGoal(this);
  private final FollowOwnerGoal followOwnerGoal = new FollowOwnerGoal(this, 1.0D, 6.0F, 4.0F, true);
  private final WaterAvoidingRandomFlyingGoal randomFlyingGoal = new WaterAvoidingRandomFlyingGoal(this, 0.5D);
  private float rollAmount;
  private float rollAmountO;
  private int flyingSound;
  private int goldCount = this.setGoldCount();
  private int underWaterTicks;

  public AbstractFlyingDroneEntity(EntityType<? extends AbstractFlyingDroneEntity> type, Level level) {
    super(type, level);
    this.moveControl = new FlyingMoveControl(this, 16, true);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_CACTUS, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DANGER_FIRE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0F);
    this.setPathfindingMalus(BlockPathTypes.WATER, -1.0F);
  }

  @Override
  public void aiStep() {
    if (this.isTame() && !this.isInSittingPose()) {
      this.goalSelector.addGoal(3, this.followOwnerGoal);
      this.goalSelector.addGoal(4, this.randomFlyingGoal);
      this.goalSelector.addGoal(9, this.floatGoal);
    }
    super.aiStep();
  }

  @Override
  protected void customServerAiStep() {
    if (this.isInWaterOrBubble()) {
      ++this.underWaterTicks;
    } else {
      this.underWaterTicks = 0;
    }
    if (this.underWaterTicks > 20) {
      this.hurt(DamageSource.DROWN, 1.0F);
    }
  }

  @Override
  public void tick() {
    flyingTick();
    if (this.isLowHealth()) {
      for (int i = 0; i < 2; ++i) {
        this.level.addParticle(ParticleTypes.SMOKE, this.getRandomX(0.5D), this.getRandomY(), this.getRandomZ(0.5D), 0.0D, 0.0D, 0.0D);
      }
    }
    Vec3 vec3 = this.getDeltaMovement();
    if (this.isInSittingPose() && !this.isOnGround()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, ((double) -0.1F - vec3.y), 0.0D));
    }
    super.tick();
    this.updateRollAmount();
  }

  private void flyingTick() {
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

  public boolean causeFallDamage(float p_149683_, float p_149684_, DamageSource source) {
    return false;
  }

  protected PathNavigation createNavigation(Level level) {
    FlyingPathNavigation flyingpathnavigation = new FlyingPathNavigation(this, level);
    flyingpathnavigation.setCanOpenDoors(false);
    flyingpathnavigation.setCanFloat(false);
    flyingpathnavigation.setCanPassDoors(true);
    return flyingpathnavigation;
  }

  public float getWalkTargetValue(BlockPos pos, LevelReader level) {
    return level.getBlockState(pos).isAir() ? 10.0F : 0.0F;
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

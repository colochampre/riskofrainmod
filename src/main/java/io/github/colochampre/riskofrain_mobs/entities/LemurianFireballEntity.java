package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;

public class LemurianFireballEntity extends Fireball {
  public LemurianFireballEntity(EntityType<? extends SmallFireball> type, Level level) {
    super(type, level);
  }

  public LemurianFireballEntity(Level level, LivingEntity entity, double x, double y, double z) {
    super(EntityType.SMALL_FIREBALL, entity, x, y, z, level);
  }

  public LemurianFireballEntity(Level level, double p_37368_, double p_37369_, double p_37370_, double p_37371_, double p_37372_, double p_37373_) {
    super(EntityType.SMALL_FIREBALL, p_37368_, p_37369_, p_37370_, p_37371_, p_37372_, p_37373_, level);
  }

  protected void onHitEntity(@NotNull EntityHitResult hitResult) {
    super.onHitEntity(hitResult);
    if (!this.level.isClientSide) {
      Entity entity = hitResult.getEntity();
      Entity entity1 = this.getOwner();
      int i = entity.getRemainingFireTicks();
      entity.setSecondsOnFire(5);
      if (!entity.hurt(DamageSource.fireball(this, entity1), 5.0F)) {
        entity.setRemainingFireTicks(i);
      } else if (entity1 instanceof LivingEntity) {
        this.doEnchantDamageEffects((LivingEntity) entity1, entity);
      }
    }
  }

  protected void onHitBlock(@NotNull BlockHitResult hitResult) {
    super.onHitBlock(hitResult);
    if (!this.level.isClientSide) {
      Entity entity = this.getOwner();
      if (!(entity instanceof Mob) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level, entity)) {
        BlockPos blockpos = hitResult.getBlockPos().relative(hitResult.getDirection());
        if (this.level.isEmptyBlock(blockpos) && RoRConfig.SERVER.ENABLE_FIREBALL_GRIEF.get()) {
          this.level.setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level, blockpos));
        }
      }

    }
  }

  protected void onHit(@NotNull HitResult hitResult) {
    super.onHit(hitResult);
    if (!this.level.isClientSide) {
      this.discard();
    }

  }

  public boolean isPickable() {
    return false;
  }

  public boolean hurt(@NotNull DamageSource source, float p_37382_) {
    return false;
  }
}

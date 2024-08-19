package io.github.colochampre.riskofrain_mobs.entities.projectiles;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.projectile.Fireball;
import net.minecraft.world.entity.projectile.SmallFireball;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseFireBlock;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;

public class LemurianFireballEntity extends Fireball {
  public LemurianFireballEntity(EntityType<? extends SmallFireball> type, Level level) {
    super(type, level);
  }

  public LemurianFireballEntity(Level level, LivingEntity entity, Vec3 vec3) {
    super(EntityType.SMALL_FIREBALL, entity, vec3, level);
  }

  public LemurianFireballEntity(Level level, double x, double y, double z, Vec3 vec3) {
    super(EntityType.SMALL_FIREBALL, x, y, z, vec3, level);
  }

  protected void onHitEntity(@NotNull EntityHitResult hitResult) {
    super.onHitEntity(hitResult);
    Level var3 = this.level();
    if (var3 instanceof ServerLevel serverLevel) {
      Entity entity = hitResult.getEntity();
      Entity owner = this.getOwner();
      int ticks = entity.getRemainingFireTicks();
      entity.igniteForSeconds(5);
      DamageSource source = this.damageSources().fireball(this, owner);
      if (!entity.hurt(this.damageSources().fireball(this, owner), 5.0F)) {
        entity.setRemainingFireTicks(ticks);
      } else if (owner instanceof LivingEntity) {
        EnchantmentHelper.doPostAttackEffects(serverLevel, entity, source);
      }
    }
  }

  protected void onHitBlock(@NotNull BlockHitResult hitResult) {
    super.onHitBlock(hitResult);
    if (!this.level().isClientSide) {
      Entity entity = this.getOwner();
      if (!(entity instanceof Mob) || net.minecraftforge.event.ForgeEventFactory.getMobGriefingEvent(this.level(), entity)) {
        BlockPos blockpos = hitResult.getBlockPos().relative(hitResult.getDirection());
        if (this.level().isEmptyBlock(blockpos) && RoRConfig.SERVER.ENABLE_FIREBALL_GRIEF.get()) {
          this.level().setBlockAndUpdate(blockpos, BaseFireBlock.getState(this.level(), blockpos));
        }
      }
    }
  }

  protected void onHit(@NotNull HitResult hitResult) {
    super.onHit(hitResult);
    if (!this.level().isClientSide) {
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

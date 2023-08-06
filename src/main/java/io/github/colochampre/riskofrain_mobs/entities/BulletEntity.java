package io.github.colochampre.riskofrain_mobs.entities;

import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleOptions;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.PatrollingMonster;
import net.minecraft.world.entity.monster.piglin.AbstractPiglin;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.PlayMessages;

public class BulletEntity extends EntityMobProjectile {

  // code from AlexMod MudBall
  public BulletEntity(EntityType type, Level level) {
    super(type, level);
  }

  public BulletEntity(Level worldIn, GunnerDroneEntity drone) {
    super(EntityInit.DRONE_BULLET_ENTITY.get(), worldIn, drone);
    Vec3 vec3 = drone.position().add(calcOffsetVec(new Vec3(0, 0, 0.2F * drone.getScale()), 0F, drone.getYRot()));
    this.setPos(vec3.x, vec3.y, vec3.z);
  }

  public BulletEntity(PlayMessages.SpawnEntity entity, Level world) {
    this(EntityInit.DRONE_BULLET_ENTITY.get(), world);
  }

  public void doBehavior() {
    this.setDeltaMovement(this.getDeltaMovement().scale((double) 0.9F));
    if (!this.isNoGravity()) {
      this.setDeltaMovement(this.getDeltaMovement().add(0.0D, (double) -0.06F, 0.0D));
    }
  }

  @Override
  protected boolean removeInWater() {
    return false;
  }

  @Override
  protected float getDamage() {
    return 1 + random.nextInt(3);
  }

  @Override
  protected void onEntityHit(EntityHitResult hitResult) {
    Entity entity = hitResult.getEntity();
    if (entity instanceof AbstractVillager || entity instanceof PatrollingMonster || entity instanceof AbstractPiglin) {
      ParticleOptions particle = new BlockParticleOption(ParticleTypes.BLOCK, Blocks.BARRIER.defaultBlockState());
      for (int i = 0; i < 8; ++i) {
        this.level.addParticle(particle, this.getX(), this.getY(), this.getZ(), 0.0D, 0.0D, 0.0D);
      }
    }
    super.onEntityHit(hitResult);
  }

  @Override
  public void handleEntityEvent(byte event) {
    super.handleEntityEvent(event);
  }

  @Override
  protected void onImpact(HitResult result) {
    if (!this.level.isClientSide) {
      this.level.broadcastEntityEvent(this, (byte) 3);
    }
    super.onImpact(result);
  }
}
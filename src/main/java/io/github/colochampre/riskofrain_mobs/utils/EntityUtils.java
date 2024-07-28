package io.github.colochampre.riskofrain_mobs.utils;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

public class EntityUtils {

  public static void doParticlesAtEntity(LivingEntity entity, SimpleParticleType particle, int amount) {
    if (entity.level().isClientSide) {
      for (int i = 0; i < amount; ++i) {
        double randomX = entity.getX((2.0D * ThreadLocalRandom.current().nextDouble() - 1.0D) * 0.5);
        double randomY = entity.getY(ThreadLocalRandom.current().nextDouble());
        double randomZ = entity.getZ((2.0D * ThreadLocalRandom.current().nextDouble() - 1.0D) * 0.5);
        entity.level().addParticle(particle, randomX, randomY, randomZ, 0.0D, 0.0D, 0.0D);
      }
    }
  }

  public static double getHeightAboveSurface(LivingEntity entity) {
    Level level = entity.level();
    Vec3 entityPos = entity.position();
    Vec3 rayStart = new Vec3(entityPos.x, entityPos.y, entityPos.z);
    Vec3 rayEnd = new Vec3(entityPos.x, entityPos.y - level.getMaxBuildHeight(), entityPos.z);
    BlockHitResult blockHitResult = level.clip(new ClipContext(rayStart, rayEnd, ClipContext.Block.COLLIDER, ClipContext.Fluid.ANY, entity));
    if (blockHitResult.getType() == BlockHitResult.Type.MISS) {
      return 64; // If no block is detected, return a high value
    }
    BlockPos pos = blockHitResult.getBlockPos();
    return entityPos.y - pos.getY();
  }

  public static void updateMovementInclinations(LivingEntity entity, float currentBodyXRot, float currentBodyZRot, Consumer<Float> bodyXRotSetter, Consumer<Float> bodyZRotSetter) {
    Vec3 motion = entity.getDeltaMovement(); // Movement vector
    float yaw = entity.getYRot(); // Entity orientation (yaw)
    Vec3 lookVec = Vec3.directionFromRotation(0, yaw);
    Vec3 normalizedMotion = motion.normalize();
    double dotForward = normalizedMotion.dot(lookVec);
    double dotRight = normalizedMotion.dot(new Vec3(-lookVec.z, 0, lookVec.x));
    float targetBodyXRot = (float) -dotForward * (float) Math.PI / 6;
    float targetBodyZRot = (float) dotRight * (float) Math.PI / 4;
    float newBodyXRot = Mth.lerp(0.1f, currentBodyXRot, targetBodyXRot);
    float newBodyZRot = Mth.lerp(0.1f, currentBodyZRot, targetBodyZRot);
    bodyXRotSetter.accept(newBodyXRot);
    bodyZRotSetter.accept(newBodyZRot);
  }

  public static float normalizeAngle(float angle) {
    angle = angle % (2 * (float) Math.PI);
    if (angle > Math.PI) {
      angle -= 2 * (float) Math.PI;
    } else if (angle < -Math.PI) {
      angle += 2 * (float) Math.PI;
    }
    return angle;
  }
}

package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.StoneGolemModel;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.culling.Frustum;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import org.joml.Matrix3f;
import org.joml.Matrix4f;

public class StoneGolemRenderer extends MobRenderer<StoneGolemEntity, StoneGolemModel> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_default.png");
  private static final ResourceLocation BEAM_LOCATION = new ResourceLocation("textures/entity/guardian_beam.png");
  private static final RenderType BEAM_RENDER_TYPE = RenderType.entityCutoutNoCull(BEAM_LOCATION);

  public StoneGolemRenderer(EntityRendererProvider.Context context) {
    this(context, StoneGolemModel.LAYER_LOCATION, 1.0F);
  }

  protected StoneGolemRenderer(EntityRendererProvider.Context context, ModelLayerLocation layerLocation, float shadow) {
    super(context, new StoneGolemModel(context.bakeLayer(layerLocation)), shadow);
  }

  public boolean shouldRender(StoneGolemEntity p_114836_, Frustum frustum, double p_114838_, double p_114839_, double p_114840_) {
    if (super.shouldRender(p_114836_, frustum, p_114838_, p_114839_, p_114840_)) {
      return true;
    } else {
      if (p_114836_.hasActiveAttackTarget()) {
        LivingEntity livingentity = p_114836_.getActiveAttackTarget();
        if (livingentity != null) {
          Vec3 vec3 = this.getPosition(livingentity, (double) livingentity.getBbHeight() * 0.5D, 1.0F);
          Vec3 vec31 = this.getPosition(p_114836_, (double) p_114836_.getEyeHeight(), 1.0F);
          return frustum.isVisible(new AABB(vec31.x, vec31.y, vec31.z, vec3.x, vec3.y, vec3.z));
        }
      }

      return false;
    }
  }

  private Vec3 getPosition(LivingEntity entity, double p_114804_, float p_114805_) {
    double d0 = Mth.lerp((double) p_114805_, entity.xOld, entity.getX());
    double d1 = Mth.lerp((double) p_114805_, entity.yOld, entity.getY()) + p_114804_;
    double d2 = Mth.lerp((double) p_114805_, entity.zOld, entity.getZ());
    return new Vec3(d0, d1, d2);
  }

  public void render(StoneGolemEntity golem, float p_114830_, float p_114831_, PoseStack poseStack, MultiBufferSource bufferSource, int p_114834_) {
    super.render(golem, p_114830_, p_114831_, poseStack, bufferSource, p_114834_);
    LivingEntity livingentity = golem.getActiveAttackTarget();
    if (livingentity != null) {
      float f = golem.getAttackAnimationScale(p_114831_);
      float f1 = (float)golem.level.getGameTime() + p_114831_;
      float f2 = f1 * 0.5F % 1.0F;
      float f3 = golem.getEyeHeight();
      poseStack.pushPose();
      poseStack.translate(0.0F, f3, 0.0F);
      Vec3 vec3 = this.getPosition(livingentity, (double)livingentity.getBbHeight() * 0.5D, p_114831_);
      Vec3 vec31 = this.getPosition(golem, (double)f3, p_114831_);
      Vec3 vec32 = vec3.subtract(vec31);
      float f4 = (float)(vec32.length() + 1.0D);
      vec32 = vec32.normalize();
      float f5 = (float)Math.acos(vec32.y);
      float f6 = (float)Math.atan2(vec32.z, vec32.x);
      poseStack.mulPose(Axis.YP.rotationDegrees((((float)Math.PI / 2F) - f6) * (180F / (float)Math.PI)));
      poseStack.mulPose(Axis.XP.rotationDegrees(f5 * (180F / (float)Math.PI)));
      int i = 1;
      float f7 = f1 * 0.05F * -1.5F;
      float f8 = f * f;
      int j = 64 + (int)(f8 * 191.0F);
      int k = 32 + (int)(f8 * 191.0F);
      int l = 128 - (int)(f8 * 64.0F);
      float f9 = 0.2F;
      float f10 = 0.282F;
      float f11 = Mth.cos(f7 + 2.3561945F) * 0.282F;
      float f12 = Mth.sin(f7 + 2.3561945F) * 0.282F;
      float f13 = Mth.cos(f7 + ((float)Math.PI / 4F)) * 0.282F;
      float f14 = Mth.sin(f7 + ((float)Math.PI / 4F)) * 0.282F;
      float f15 = Mth.cos(f7 + 3.926991F) * 0.282F;
      float f16 = Mth.sin(f7 + 3.926991F) * 0.282F;
      float f17 = Mth.cos(f7 + 5.4977875F) * 0.282F;
      float f18 = Mth.sin(f7 + 5.4977875F) * 0.282F;
      float f19 = Mth.cos(f7 + (float)Math.PI) * 0.2F;
      float f20 = Mth.sin(f7 + (float)Math.PI) * 0.2F;
      float f21 = Mth.cos(f7 + 0.0F) * 0.2F;
      float f22 = Mth.sin(f7 + 0.0F) * 0.2F;
      float f23 = Mth.cos(f7 + ((float)Math.PI / 2F)) * 0.2F;
      float f24 = Mth.sin(f7 + ((float)Math.PI / 2F)) * 0.2F;
      float f25 = Mth.cos(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
      float f26 = Mth.sin(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
      float f27 = 0.0F;
      float f28 = 0.4999F;
      float f29 = -1.0F + f2;
      float f30 = f4 * 2.5F + f29;
      VertexConsumer vertexconsumer = bufferSource.getBuffer(BEAM_RENDER_TYPE);
      PoseStack.Pose posestack$pose = poseStack.last();
      Matrix4f matrix4f = posestack$pose.pose();
      Matrix3f matrix3f = posestack$pose.normal();
      vertex(vertexconsumer, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.2999F, f30); // just testing (original value = 0.4999)
      vertex(vertexconsumer, matrix4f, matrix3f, f19, 0.0F, f20, j, k, l, 0.2999F, f29);
      vertex(vertexconsumer, matrix4f, matrix3f, f21, 0.0F, f22, j, k, l, 0.0F, f29);
      vertex(vertexconsumer, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0F, f30);
      vertex(vertexconsumer, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999F, f30);
      vertex(vertexconsumer, matrix4f, matrix3f, f23, 0.0F, f24, j, k, l, 0.2999F, f29);
      vertex(vertexconsumer, matrix4f, matrix3f, f25, 0.0F, f26, j, k, l, 0.0F, f29);
      vertex(vertexconsumer, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0F, f30);
      float f31 = 0.0F;
      if (golem.tickCount % 2 == 0) {
        f31 = 0.5F;
      }

      vertex(vertexconsumer, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5F, f31 + 0.5F);
      vertex(vertexconsumer, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0F, f31 + 0.5F);
      vertex(vertexconsumer, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0F, f31);
      vertex(vertexconsumer, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5F, f31);
      poseStack.popPose();
    }
  }

  private static void vertex(VertexConsumer vertexConsumer, Matrix4f matrix4f, Matrix3f matrix3f, float p_253994_, float p_254492_, float p_254474_, int p_254080_, int p_253655_, int p_254133_, float p_254233_, float p_253939_) {
    vertexConsumer.vertex(matrix4f, p_253994_, p_254492_, p_254474_).color(255, 0, 0, 255).uv(p_254233_, p_253939_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(15728880).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
  }

  @Override
  public ResourceLocation getTextureLocation(StoneGolemEntity entity) {
    return DEFAULT_TEXTURE;
  }
}

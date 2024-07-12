package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import io.github.colochampre.riskofrain_mobs.entities.BulletEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import org.jetbrains.annotations.NotNull;

public class BulletRenderer extends EntityRenderer<BulletEntity> {
  private static final ResourceLocation TEXTURE = new ResourceLocation("textures/item/iron_nugget.png");

  public BulletRenderer(EntityRendererProvider.Context context) {
    super(context);
  }
  // code from AlexsMod MudBall
  public void render(@NotNull BulletEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight) {
    poseStack.pushPose();
    poseStack.scale(0.7F, 0.7F, 0.7F);
    poseStack.mulPose(this.entityRenderDispatcher.cameraOrientation());
    poseStack.mulPose(Axis.YP.rotationDegrees(180.0F));
    PoseStack.Pose $$6 = poseStack.last();
    Matrix4f $$7 = $$6.pose();
    Matrix3f $$8 = $$6.normal();
    VertexConsumer $$9 = buffer.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
    vertex($$9, $$7, $$8, packedLight, 0.0F, 0, 0, 1);
    vertex($$9, $$7, $$8, packedLight, 1.0F, 0, 1, 1);
    vertex($$9, $$7, $$8, packedLight, 1.0F, 1, 1, 0);
    vertex($$9, $$7, $$8, packedLight, 0.0F, 1, 0, 0);
    poseStack.popPose();
    super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
  }

  private static void vertex(VertexConsumer p_114090_, Matrix4f matrix4f, Matrix3f matrix3f, int i1, float f1, int i2, int i3, int i4) {
    p_114090_.vertex(matrix4f, f1 - 0.5F, (float) i2 - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float) i3, (float) i4).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(i1).normal(matrix3f, 0.0F, 1.0F, 0.0F).endVertex();
  }

  public @NotNull ResourceLocation getTextureLocation(@NotNull BulletEntity renderer) {
    return TEXTURE;
  }
}

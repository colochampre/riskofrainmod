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

  // code from AlexMod MudBall
  public BulletRenderer(EntityRendererProvider.Context context) {
    super(context);
  }

  public void render(@NotNull BulletEntity entity, float f, float f2, PoseStack p_114083_, MultiBufferSource p_114084_, int p_114085_) {
    p_114083_.pushPose();
    p_114083_.scale(0.7F, 0.7F, 0.7F);
    p_114083_.mulPose(this.entityRenderDispatcher.cameraOrientation());
    p_114083_.mulPose(Axis.YP.rotationDegrees(180.0F));
    PoseStack.Pose $$6 = p_114083_.last();
    Matrix4f $$7 = $$6.pose();
    Matrix3f $$8 = $$6.normal();
    VertexConsumer $$9 = p_114084_.getBuffer(RenderType.entityCutoutNoCull(TEXTURE));
    vertex($$9, $$7, $$8, p_114085_, 0.0F, 0, 0, 1);
    vertex($$9, $$7, $$8, p_114085_, 1.0F, 0, 1, 1);
    vertex($$9, $$7, $$8, p_114085_, 1.0F, 1, 1, 0);
    vertex($$9, $$7, $$8, p_114085_, 0.0F, 1, 0, 0);
    p_114083_.popPose();
    super.render(entity, f, f2, p_114083_, p_114084_, p_114085_);
  }

  private static void vertex(VertexConsumer p_114090_, Matrix4f p_114091_, Matrix3f p_114092_, int p_114093_, float p_114094_, int p_114095_, int p_114096_, int p_114097_) {
    p_114090_.vertex(p_114091_, p_114094_ - 0.5F, (float) p_114095_ - 0.25F, 0.0F).color(255, 255, 255, 255).uv((float) p_114096_, (float) p_114097_).overlayCoords(OverlayTexture.NO_OVERLAY).uv2(p_114093_).normal(p_114092_, 0.0F, 1.0F, 0.0F).endVertex();
  }

  public @NotNull ResourceLocation getTextureLocation(BulletEntity renderer) {
    return TEXTURE;
  }
}
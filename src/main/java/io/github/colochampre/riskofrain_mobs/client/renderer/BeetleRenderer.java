package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.BeetleModel;
import io.github.colochampre.riskofrain_mobs.client.renderer.layers.BeetleEyesLayer;
import io.github.colochampre.riskofrain_mobs.entities.enemies.BeetleEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BeetleRenderer extends MobRenderer<BeetleEntity, BeetleModel<BeetleEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "textures/entity/beetle/beetle_default.png");

  public BeetleRenderer(EntityRendererProvider.Context context) {
    super(context, new BeetleModel<>(context.bakeLayer(ModClientEvents.BEETLE_LAYER)), 0.66F);
    this.addLayer(new BeetleEyesLayer(this));
  }

  @Override
  protected float getFlipDegrees(@NotNull BeetleEntity entity) {
    return 90;
  }

  @Override
  public void render(@NotNull BeetleEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
    poseStack.pushPose();
    float scale = 0.90F;
    poseStack.scale(scale, scale, scale);
    super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    poseStack.popPose();
  }

  @NotNull
  @Override
  public ResourceLocation getTextureLocation(@NotNull BeetleEntity entity) {
    return BeetleRenderer.DEFAULT_TEXTURE;
  }
}

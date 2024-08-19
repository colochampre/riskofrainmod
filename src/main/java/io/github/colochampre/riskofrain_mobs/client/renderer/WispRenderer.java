package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.WispModel;
import io.github.colochampre.riskofrain_mobs.entities.enemies.WispEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class WispRenderer extends MobRenderer<WispEntity, WispModel<WispEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "textures/entity/wisp/wisp_default.png");

  public WispRenderer(EntityRendererProvider.Context context) {
    super(context, new WispModel<>(context.bakeLayer(ModClientEvents.WISP_LAYER)), 0.33F);
  }

  @Override
  public void render(@NotNull WispEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
    poseStack.pushPose();
    float scale = 0.75F;
    poseStack.scale(scale, scale, scale);
    super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    poseStack.popPose();
  }

  @NotNull
  @Override
  public ResourceLocation getTextureLocation(@NotNull WispEntity p_114482_) {
    return WispRenderer.DEFAULT_TEXTURE;
  }
}

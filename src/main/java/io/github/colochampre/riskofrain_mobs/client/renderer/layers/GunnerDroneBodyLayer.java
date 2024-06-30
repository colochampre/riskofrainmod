package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerDroneModel;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GunnerDroneBodyLayer extends RenderLayer<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> {
  private static final ResourceLocation GUNNER_DRONE_BODY_COLOR = ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone_color.png");

  public GunnerDroneBodyLayer(RenderLayerParent<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> layer) {
    super(layer);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int p_117722_, GunnerDroneEntity drone, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
    if (drone.isTame() && !drone.isInvisible() && !(drone.getBodyColor().getId() == 3)) {
      int i = drone.getBodyColor().getTextureDiffuseColor();
      VertexConsumer vertex = bufferSource.getBuffer(RenderType.entityCutoutNoCull(GUNNER_DRONE_BODY_COLOR));
      ((GunnerDroneModel) this.getParentModel()).renderToBuffer(poseStack, vertex, p_117722_, OverlayTexture.NO_OVERLAY, i);
    }
  }
}

package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerDroneModel;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerDroneEntity;
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
  public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int packedLight, GunnerDroneEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isTame() && !entity.isInvisible() && !(entity.getBodyColor().getId() == 3)) {
      int i = entity.getBodyColor().getTextureDiffuseColor();
      VertexConsumer vertex = source.getBuffer(RenderType.entityCutoutNoCull(GUNNER_DRONE_BODY_COLOR));
      this.getParentModel().renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, i);
    }
  }
}

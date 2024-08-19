package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerTurretModel;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GunnerTurretBodyLayer extends RenderLayer<GunnerTurretEntity, GunnerTurretModel<GunnerTurretEntity>> {
  private static final ResourceLocation GUNNER_TURRET_BODY_COLOR = ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "textures/entity/gunner_turret/gunner_turret_color.png");

  public GunnerTurretBodyLayer(RenderLayerParent<GunnerTurretEntity, GunnerTurretModel<GunnerTurretEntity>> layer) {
    super(layer);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource source, int packedLight, GunnerTurretEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isTame() && !entity.isInvisible() && !(entity.getBodyColor().getId() == 3)) {
      int i = entity.getBodyColor().getTextureDiffuseColor();
      VertexConsumer vertex = source.getBuffer(RenderType.entityCutoutNoCull(GUNNER_TURRET_BODY_COLOR));
      this.getParentModel().renderToBuffer(poseStack, vertex, packedLight, OverlayTexture.NO_OVERLAY, i);
    }
  }
}

package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerDroneModel;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerDroneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class GunnerDroneBodyLayer extends RenderLayer<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> {
  private static final ResourceLocation GUNNER_DRONE_BODY_COLOR = new ResourceLocation(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone_color.png");

  public GunnerDroneBodyLayer(RenderLayerParent<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> layer) {
    super(layer);
  }

  @Override
  public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource bufferSource, int p_117722_, GunnerDroneEntity entity, float p_117724_, float p_117725_, float p_117726_, float p_117727_, float p_117728_, float p_117729_) {
    if (entity.isTame() && !entity.isInvisible() && !(entity.getBodyColor().getId() == 3)) {
      float[] afloat = entity.getBodyColor().getTextureDiffuseColors();
      renderColoredCutoutModel(this.getParentModel(), GUNNER_DRONE_BODY_COLOR, poseStack, bufferSource, p_117722_, entity, afloat[0], afloat[1], afloat[2]);
    }
  }
}

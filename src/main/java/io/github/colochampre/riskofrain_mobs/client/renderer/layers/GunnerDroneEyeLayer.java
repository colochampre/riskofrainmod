package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerDroneModel;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GunnerDroneEyeLayer extends EyesLayer<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> {
  static final RenderType GUNNER_DRONE_EYE = RenderType.eyes(new ResourceLocation(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone_eye.png"));

  public GunnerDroneEyeLayer(RenderLayerParent<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> layer) {
    super(layer);
  }

  @Override
  public void render(PoseStack poseStack, MultiBufferSource source, int packedLight, GunnerDroneEntity entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    if (entity.isTame() && !entity.isInSittingPose()) {
      super.render(poseStack, source, packedLight, entity, limbSwing, limbSwingAmount, partialTicks, ageInTicks, netHeadYaw, headPitch);
    }
  }

  @Override
  public @NotNull RenderType renderType() {
    return GUNNER_DRONE_EYE;
  }
}

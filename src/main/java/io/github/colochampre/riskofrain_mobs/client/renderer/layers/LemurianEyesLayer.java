/*package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LightLayer;

public class LemurianEyesLayer<T extends LemurianEntity> extends RenderLayer<T, LemurianModel<T>> {
  private static final ResourceLocation LEMURIAN_EYES = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_eyes.png");

  public LemurianEyesLayer(RenderLayerParent parent) {
    super(parent);
  }

  @Override
  public void render(PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn, T entity, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
    long roundedTime = entity.level.getDayTime() % 24000;
    boolean night = roundedTime >= 13000 && roundedTime <= 22000;
    BlockPos ratPos = entity.getLightPosition();
    int i = entity.level.getBrightness(LightLayer.SKY, ratPos);
    int j = entity.level.getBrightness(LightLayer.BLOCK, ratPos);
    int brightness;
    if (night) {
      brightness = j;
    } else {
      brightness = Math.max(i, j);
    }
    if (brightness < 7) {
      VertexConsumer ivertexbuilder = bufferIn.getBuffer(RenderType.eyes(LEMURIAN_EYES));
      this.getParentModel().renderToBuffer(matrixStackIn, ivertexbuilder, packedLightIn, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }
  }
}
*/
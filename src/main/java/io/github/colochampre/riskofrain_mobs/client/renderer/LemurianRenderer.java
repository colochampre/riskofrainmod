package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.client.renderer.layers.LemurianEyesLayer;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.LightLayer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class LemurianRenderer extends MobRenderer<LemurianEntity, LemurianModel<LemurianEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_default.png");
  private static final ResourceLocation LEMURIAN_EYES = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_eyes.png");

  public LemurianRenderer(EntityRendererProvider.Context context) {
    super(context, new LemurianModel<>(context.bakeLayer(ModClientEvents.LEMURIAN_LAYER)), 0.4F);
    this.addLayer(new LemurianEyesLayer(this));
  }

  @Override
  protected float getFlipDegrees(LemurianEntity entity) {
    return 90;
  }

  @Override
  public ResourceLocation getTextureLocation(LemurianEntity entity) {
    return LemurianRenderer.DEFAULT_TEXTURE;
  }
  
  @Override
  public void render(LemurianEntity entity, float p_114209_, float partialTicks, PoseStack poseStack, MultiBufferSource bufferSource, int packedLight) {
    long roundedTime = entity.level.getDayTime() % 24000;
    boolean night = roundedTime >= 13000 && roundedTime <= 22000;
    BlockPos ratPos = entity.blockPosition();
    int i = entity.level.getBrightness(LightLayer.SKY, ratPos);
    int j = entity.level.getBrightness(LightLayer.BLOCK, ratPos);
    int brightness;
    if (night) {
      brightness = j;
    } else {
      brightness = Math.max(i, j);
    }
    if (brightness < 7) {
      VertexConsumer eyesTexture = bufferSource.getBuffer(RenderType.eyes(LEMURIAN_EYES));
      this.model.renderToBuffer(poseStack, eyesTexture, packedLight, LivingEntityRenderer.getOverlayCoords(entity, 0.0F), 1.0F, 1.0F, 1.0F, 1.0F);
    }
    this.model.prepareMobModel(entity, 0.0F, 0.0F, partialTicks);
    super.render(entity, p_114209_, partialTicks, poseStack, bufferSource, packedLight);
  }
}

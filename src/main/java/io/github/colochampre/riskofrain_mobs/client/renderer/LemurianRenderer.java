package io.github.colochampre.riskofrain_mobs.client.renderer;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class LemurianRenderer extends MobRenderer<LemurianEntity, LemurianModel> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_default.png");

  public LemurianRenderer(EntityRendererProvider.Context context) {
    super(context, new LemurianModel(context.bakeLayer(LemurianModel.LAYER_LOCATION)), 0.4F);
  }

  @Override
  public ResourceLocation getTextureLocation(LemurianEntity entity) {
    return DEFAULT_TEXTURE;
  }
}

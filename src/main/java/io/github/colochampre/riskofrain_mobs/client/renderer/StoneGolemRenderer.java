package io.github.colochampre.riskofrain_mobs.client.renderer;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.StoneGolemModel;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class StoneGolemRenderer extends MobRenderer<StoneGolemEntity, StoneGolemModel> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_default.png");

  public StoneGolemRenderer(EntityRendererProvider.Context context) {
    super(context, new StoneGolemModel(context.bakeLayer(StoneGolemModel.LAYER_LOCATION)), 1.0F);
  }

  @Override
  public ResourceLocation getTextureLocation(StoneGolemEntity entity) {
    return DEFAULT_TEXTURE;
  }
}

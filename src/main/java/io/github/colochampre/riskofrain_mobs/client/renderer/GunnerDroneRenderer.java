package io.github.colochampre.riskofrain_mobs.client.renderer;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerDroneModel;
import io.github.colochampre.riskofrain_mobs.client.renderer.layers.GunnerDroneEyeLayer;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GunnerDroneRenderer extends MobRenderer<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone_default.png");

  public GunnerDroneRenderer(EntityRendererProvider.Context context) {
    super(context, new GunnerDroneModel<>(context.bakeLayer(ModClientEvents.GUNNER_DRONE_LAYER)), 0.3F);
    this.addLayer(new GunnerDroneEyeLayer(this));
  }

  @Override
  public ResourceLocation getTextureLocation(GunnerDroneEntity entity) {
    return GunnerDroneRenderer.DEFAULT_TEXTURE;
  }
}

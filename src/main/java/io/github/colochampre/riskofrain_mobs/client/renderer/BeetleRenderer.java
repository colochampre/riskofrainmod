package io.github.colochampre.riskofrain_mobs.client.renderer;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.BeetleModel;
import io.github.colochampre.riskofrain_mobs.entities.BeetleEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BeetleRenderer extends MobRenderer<BeetleEntity, BeetleModel<BeetleEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/beetle/beetle_default.png");

  public BeetleRenderer(EntityRendererProvider.Context context) {
    super(context, new BeetleModel<>(context.bakeLayer(ModClientEvents.BEETLE_LAYER)), 0.9F);
    //this.addLayer(new BeetleEyesLayer(this));
  }

  @Override
  protected float getFlipDegrees(@NotNull BeetleEntity entity) {
    return 90;
  }

  @NotNull
  @Override
  public ResourceLocation getTextureLocation(@NotNull BeetleEntity entity) {
    return BeetleRenderer.DEFAULT_TEXTURE;
  }
}

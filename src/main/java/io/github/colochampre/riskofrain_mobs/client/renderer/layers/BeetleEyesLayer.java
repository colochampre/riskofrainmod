package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.BeetleModel;
import io.github.colochampre.riskofrain_mobs.entities.enemies.BeetleEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BeetleEyesLayer extends EyesLayer<BeetleEntity, BeetleModel<BeetleEntity>> {
  static final RenderType BEETLE_EYES = RenderType.eyes(new ResourceLocation(RoRmod.MODID, "textures/entity/beetle/beetle_eyes.png"));

  public BeetleEyesLayer(RenderLayerParent<BeetleEntity, BeetleModel<BeetleEntity>> layer) {
    super(layer);
  }

  @NotNull
  @Override
  public RenderType renderType() {
    return BEETLE_EYES;
  }
}

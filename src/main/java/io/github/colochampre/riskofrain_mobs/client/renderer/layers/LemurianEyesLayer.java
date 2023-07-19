package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LemurianEyesLayer extends EyesLayer<LemurianEntity, LemurianModel<LemurianEntity>> {
  static final RenderType LEMURIAN_EYES = RenderType.eyes(new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_eyes.png"));

  public LemurianEyesLayer(RenderLayerParent<LemurianEntity, LemurianModel<LemurianEntity>> layer) {
    super(layer);
  }

  @Override
  public @NotNull RenderType renderType() {
    return LEMURIAN_EYES;
  }
}

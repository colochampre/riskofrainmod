package io.github.colochampre.riskofrain_mobs.client.renderer.layers;

import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;

public class LemurianEyesLayer extends EyesLayer<LemurianEntity, LemurianModel<LemurianEntity>> {
    static final RenderType EYES = RenderType.eyes(new ResourceLocation("textures/entity/lemurian/lemurian_eyes.png"));

    public LemurianEyesLayer(RenderLayerParent<LemurianEntity, LemurianModel<LemurianEntity>>layer) {
        super(layer);
    }

    @Override
    public RenderType renderType() {
        return EYES;
    }
}

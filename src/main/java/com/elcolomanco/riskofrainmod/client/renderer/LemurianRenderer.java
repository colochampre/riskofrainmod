package com.elcolomanco.riskofrainmod.client.renderer;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.LemurianModel;
import com.elcolomanco.riskofrainmod.client.renderer.layers.LemurianEyesLayer;
import com.elcolomanco.riskofrainmod.entities.LemurianEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class LemurianRenderer extends MobRenderer<LemurianEntity, LemurianModel<LemurianEntity>> {
	
	private static final ResourceLocation HOT_BIOMES = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_hot.png");
	private static final ResourceLocation NEUTRAL = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_neutral.png");
	
	public LemurianRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new LemurianModel<LemurianEntity>(), 0.4F);
		this.addLayer(new LemurianEyesLayer<>(this));
	}
	
	@Override
	public ResourceLocation getEntityTexture(LemurianEntity entity) {
		if (entity.getVariantType() == LemurianEntity.Type.HOT_BIOMES) {
			return HOT_BIOMES;
		} else {
			return NEUTRAL;
		}
	}
}

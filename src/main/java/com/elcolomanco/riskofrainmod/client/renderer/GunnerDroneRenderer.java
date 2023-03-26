package com.elcolomanco.riskofrainmod.client.renderer;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.GunnerDroneModel;
import com.elcolomanco.riskofrainmod.client.renderer.layers.GunnerDroneEyeLayer;
import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;

import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;

public class GunnerDroneRenderer extends MobRenderer<GunnerDroneEntity, GunnerDroneModel<GunnerDroneEntity>> {
	
	private static final ResourceLocation TEXTURE = new ResourceLocation(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone.png");

	public GunnerDroneRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new GunnerDroneModel<GunnerDroneEntity>(), 0.30F);
			this.addLayer(new GunnerDroneEyeLayer<>(this));
	}

	@Override
	public ResourceLocation getEntityTexture(GunnerDroneEntity entity) {
		return TEXTURE;
	}
}

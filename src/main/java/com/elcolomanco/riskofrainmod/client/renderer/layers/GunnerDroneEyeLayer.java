package com.elcolomanco.riskofrainmod.client.renderer.layers;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.GunnerDroneModel;
import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class GunnerDroneEyeLayer <T extends GunnerDroneEntity, M extends GunnerDroneModel<T>> extends LayerRenderer<T, M> {
	public static final ResourceLocation GUNNER_DRONE_EYE_LAYER = new ResourceLocation(RoRmod.MODID, "textures/entity/gunner_drone/gunner_drone_eye.png");

	public GunnerDroneEyeLayer(IEntityRenderer<T, M> entityRenderer) {
		super(entityRenderer);
	}

	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T adolescent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		Minecraft.getInstance().getTextureManager().bindTexture(GUNNER_DRONE_EYE_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderTypes.getEmissiveEntity(GUNNER_DRONE_EYE_LAYER));
		
		this.getEntityModel().setRotationAngles(adolescent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}

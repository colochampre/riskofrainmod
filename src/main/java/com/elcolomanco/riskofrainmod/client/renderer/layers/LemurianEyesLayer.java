package com.elcolomanco.riskofrainmod.client.renderer.layers;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.LemurianModel;
import com.elcolomanco.riskofrainmod.entities.LemurianEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class LemurianEyesLayer<T extends LemurianEntity, M extends LemurianModel<T>> extends LayerRenderer<T, M> {
	public static final ResourceLocation LEMURIAN_EYE_LAYER = new ResourceLocation(RoRmod.MODID, "textures/entity/lemurian/lemurian_eyes.png");
	
	public LemurianEyesLayer(IEntityRenderer<T, M> entityRenderer) {
		super(entityRenderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T adolescent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		Minecraft.getInstance().getTextureManager().bindTexture(LEMURIAN_EYE_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderTypes.getEmissiveEntity(LEMURIAN_EYE_LAYER));
		
		this.getEntityModel().setRotationAngles(adolescent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}

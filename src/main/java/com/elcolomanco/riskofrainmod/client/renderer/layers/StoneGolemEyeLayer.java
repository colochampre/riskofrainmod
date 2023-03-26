package com.elcolomanco.riskofrainmod.client.renderer.layers;
import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.StoneGolemModel;
import com.elcolomanco.riskofrainmod.entities.StoneGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.util.ResourceLocation;

public class StoneGolemEyeLayer <T extends StoneGolemEntity, M extends StoneGolemModel<T>> extends LayerRenderer<T, M> {
public static final ResourceLocation STONE_GOLEM_EYE_LAYER = new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_eye.png");
	
	public StoneGolemEyeLayer(IEntityRenderer<T, M> entityRenderer) {
		super(entityRenderer);
	}
	
	@Override
	public void render(MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn, T adolescent, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
		Minecraft.getInstance().getTextureManager().bindTexture(STONE_GOLEM_EYE_LAYER);

		IVertexBuilder ivertexbuilder = bufferIn.getBuffer(RenderTypes.getEmissiveEntity(STONE_GOLEM_EYE_LAYER));
		
		this.getEntityModel().setRotationAngles(adolescent, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch);
		this.getEntityModel().render(matrixStackIn, ivertexbuilder, 240, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
	}
}

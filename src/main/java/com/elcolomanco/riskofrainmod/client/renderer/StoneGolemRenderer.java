package com.elcolomanco.riskofrainmod.client.renderer;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.model.StoneGolemModel;
import com.elcolomanco.riskofrainmod.client.renderer.layers.StoneGolemEyeLayer;
import com.elcolomanco.riskofrainmod.entities.StoneGolemEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;

import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.util.math.vector.Matrix3f;
import net.minecraft.util.math.vector.Matrix4f;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.util.math.vector.Vector3f;
import net.minecraft.client.renderer.culling.ClippingHelper;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3d;

public class StoneGolemRenderer extends MobRenderer<StoneGolemEntity, StoneGolemModel<StoneGolemEntity>>{
	private static final ResourceLocation TEXTURE_LASER_BEAM = new ResourceLocation("textures/entity/guardian_beam.png");
	private static final RenderType field_229107_h_ = RenderType.getEntityCutoutNoCull(TEXTURE_LASER_BEAM);
	
	private static final ResourceLocation BADLANDS		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_badlands.png");
	private static final ResourceLocation DARK_FOREST	= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_dark_forest.png");
	private static final ResourceLocation DESERT		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_desert.png");
	private static final ResourceLocation EXTREME_HILLS	= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_extreme_hills.png");
	private static final ResourceLocation FOREST		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_forest.png");
	private static final ResourceLocation JUNGLE		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_jungle.png");
	private static final ResourceLocation NEUTRAL		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_neutral.png");
	private static final ResourceLocation PLAINS		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_plains.png");
	private static final ResourceLocation SAVANNA		= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_savanna.png");
	private static final ResourceLocation SNOW			= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_snow.png");
	private static final ResourceLocation SWAMP			= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_swamp.png");
	private static final ResourceLocation TAIGA			= new ResourceLocation(RoRmod.MODID, "textures/entity/stone_golem/stone_golem_taiga.png");
	
	public StoneGolemRenderer(EntityRendererManager renderManagerIn) {
		super(renderManagerIn, new StoneGolemModel<StoneGolemEntity>(), 1.0F);
		this.addLayer(new StoneGolemEyeLayer<>(this));
	}
	
	protected StoneGolemRenderer(EntityRendererManager p_i50968_1_, float p_i50968_2_) {
		super(p_i50968_1_, new StoneGolemModel<StoneGolemEntity>(), p_i50968_2_);
	}
	
	public boolean shouldRender(StoneGolemEntity livingEntityIn, ClippingHelper camera, double camX, double camY, double camZ) {
		if (super.shouldRender(livingEntityIn, camera, camX, camY, camZ)) {
			return true;
		} else {
			if (livingEntityIn.hasTargetedEntity()) {
				LivingEntity livingentity = livingEntityIn.getTargetedEntity();
				if (livingentity != null) {
					Vector3d vec3d = this.getPosition(livingentity, (double)livingentity.getHeight() * 0.5D, 1.0F);
					Vector3d vec3d1 = this.getPosition(livingEntityIn, (double)livingEntityIn.getEyeHeight(), 1.0F);
					return camera.isBoundingBoxInFrustum(new AxisAlignedBB(vec3d1.x, vec3d1.y, vec3d1.z, vec3d.x, vec3d.y, vec3d.z));
				}
			}

			return false;
		}
	}
	
	private Vector3d getPosition(LivingEntity entityLivingBaseIn, double p_177110_2_, float p_177110_4_) {
		double d0 = MathHelper.lerp((double)p_177110_4_, entityLivingBaseIn.lastTickPosX, entityLivingBaseIn.getPosX());
		double d1 = MathHelper.lerp((double)p_177110_4_, entityLivingBaseIn.lastTickPosY, entityLivingBaseIn.getPosY()) + p_177110_2_;
		double d2 = MathHelper.lerp((double)p_177110_4_, entityLivingBaseIn.lastTickPosZ, entityLivingBaseIn.getPosZ());
		return new Vector3d(d0, d1, d2);
	}
	
	@SuppressWarnings("unused")
	public void render(StoneGolemEntity entityIn, float entityYaw, float partialTicks, MatrixStack matrixStackIn, IRenderTypeBuffer bufferIn, int packedLightIn) {
		super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
		LivingEntity livingentity = entityIn.getTargetedEntity();
		if (livingentity != null) {
			float f = entityIn.getAttackAnimationScale(partialTicks);
			float f1 = (float)entityIn.world.getGameTime() + partialTicks;
			float f2 = f1 * 0.5F % 1.0F;
			float f3 = entityIn.getEyeHeight();
			matrixStackIn.push();
			matrixStackIn.translate(0.0D, (double)f3, 0.0D);
			Vector3d vec3d = this.getPosition(livingentity, (double)livingentity.getHeight() * 0.5D, partialTicks);
			Vector3d vec3d1 = this.getPosition(entityIn, (double)f3, partialTicks);
			Vector3d vec3d2 = vec3d.subtract(vec3d1);
			float f4 = (float)(vec3d2.length() + 1.0D);
			vec3d2 = vec3d2.normalize();
			float f5 = (float)Math.acos(vec3d2.y);
			float f6 = (float)Math.atan2(vec3d2.z, vec3d2.x);
			matrixStackIn.rotate(Vector3f.YP.rotationDegrees((((float)Math.PI / 2F) - f6) * (180F / (float)Math.PI)));
			matrixStackIn.rotate(Vector3f.XP.rotationDegrees(f5 * (180F / (float)Math.PI)));
			int i = 1;
			float f7 = f1 * 0.05F * -1.5F;
			float f8 = f * f;
			int j = 64 + (int)(f8 * 191.0F);
			int k = 32 + (int)(f8 * 191.0F);
			int l = 128 - (int)(f8 * 64.0F);
			float f9 = 0.2F;
			float f10 = 0.282F;
			float f11 = MathHelper.cos(f7 + 2.3561945F) * 0.282F;
			float f12 = MathHelper.sin(f7 + 2.3561945F) * 0.282F;
			float f13 = MathHelper.cos(f7 + ((float)Math.PI / 4F)) * 0.282F;
			float f14 = MathHelper.sin(f7 + ((float)Math.PI / 4F)) * 0.282F;
			float f15 = MathHelper.cos(f7 + 3.926991F) * 0.282F;
			float f16 = MathHelper.sin(f7 + 3.926991F) * 0.282F;
			float f17 = MathHelper.cos(f7 + 5.4977875F) * 0.282F;
			float f18 = MathHelper.sin(f7 + 5.4977875F) * 0.282F;
			float f19 = MathHelper.cos(f7 + (float)Math.PI) * 0.2F;
			float f20 = MathHelper.sin(f7 + (float)Math.PI) * 0.2F;
			float f21 = MathHelper.cos(f7 + 0.0F) * 0.2F;
			float f22 = MathHelper.sin(f7 + 0.0F) * 0.2F;
			float f23 = MathHelper.cos(f7 + ((float)Math.PI / 2F)) * 0.2F;
			float f24 = MathHelper.sin(f7 + ((float)Math.PI / 2F)) * 0.2F;
			float f25 = MathHelper.cos(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
			float f26 = MathHelper.sin(f7 + ((float)Math.PI * 1.5F)) * 0.2F;
			float f27 = 0.0F;
			float f28 = 0.4999F;
			float f29 = -1.0F + f2;
			float f30 = f4 * 2.5F + f29;
			IVertexBuilder ivertexbuilder = bufferIn.getBuffer(field_229107_h_);
			MatrixStack.Entry matrixstack$entry = matrixStackIn.getLast();
			Matrix4f matrix4f = matrixstack$entry.getMatrix();
			Matrix3f matrix3f = matrixstack$entry.getNormal();
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f19, f4, f20, j, k, l, 0.4999F, f30);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f19, 0.0F, f20, j, k, l, 0.4999F, f29);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f21, 0.0F, f22, j, k, l, 0.0F, f29);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f21, f4, f22, j, k, l, 0.0F, f30);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f23, f4, f24, j, k, l, 0.4999F, f30);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f23, 0.0F, f24, j, k, l, 0.4999F, f29);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f25, 0.0F, f26, j, k, l, 0.0F, f29);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f25, f4, f26, j, k, l, 0.0F, f30);
			float f31 = 0.0F;
			if (entityIn.ticksExisted % 2 == 0) {
				f31 = 0.5F;
			}
			
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f11, f4, f12, j, k, l, 0.5F, f31 + 0.5F);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f13, f4, f14, j, k, l, 1.0F, f31 + 0.5F);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f17, f4, f18, j, k, l, 1.0F, f31);
			func_229108_a_(ivertexbuilder, matrix4f, matrix3f, f15, f4, f16, j, k, l, 0.5F, f31);
			matrixStackIn.pop();
		}
	}
	
	private static void func_229108_a_(IVertexBuilder p_229108_0_, Matrix4f p_229108_1_, Matrix3f p_229108_2_, float p_229108_3_, float p_229108_4_, float p_229108_5_, int p_229108_6_, int p_229108_7_, int p_229108_8_, float p_229108_9_, float p_229108_10_) {
		p_229108_0_.pos(p_229108_1_, p_229108_3_, p_229108_4_, p_229108_5_).color(255, 0, 0, 200).tex(p_229108_9_, p_229108_10_).overlay(OverlayTexture.NO_OVERLAY).lightmap(15728880).normal(p_229108_2_, 0.0F, 1.0F, 0.0F).endVertex();
	}
	
	public ResourceLocation getEntityTexture(StoneGolemEntity entity) {
		if (entity.getVariantType() == StoneGolemEntity.Type.BADLANDS) {
			return BADLANDS;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.DARK_FOREST) {
			return DARK_FOREST;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.DESERT) {
			return DESERT;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.EXTREME_HILLS) {
			return EXTREME_HILLS;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.FOREST) {
			return FOREST;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.JUNGLE) {
			return JUNGLE;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.PLAINS) {
			return PLAINS;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.SAVANNA) {
			return SAVANNA;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.SNOW) {
			return SNOW;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.SWAMP) {
			return SWAMP;
		} else if (entity.getVariantType() == StoneGolemEntity.Type.TAIGA) {
			return TAIGA;
		} else {
			return NEUTRAL; 
		}
	}
}

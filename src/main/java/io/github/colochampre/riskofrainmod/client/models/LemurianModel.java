package io.github.colochampre.riskofrainmod.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrainmod.RoRmod;
import io.github.colochampre.riskofrainmod.entities.LemurianEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class LemurianModel extends EntityModel<LemurianEntity> {
	public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RoRmod.MODID, "lemurian_entity"), "main");
	private final ModelPart Core;

	public LemurianModel(ModelPart root) {
		this.Core = root.getChild("Core");
	}

	public static LayerDefinition createBodyLayer() {
		var mesh = new MeshDefinition();
		PartDefinition parts = mesh.getRoot();

		PartDefinition Core = parts.addOrReplaceChild("Core", CubeListBuilder.create().texOffs(0, 0).addBox(-5.0F, -27.0F, -4.0F, 10.0F, 27.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 24.0F, 0.0F));

		return LayerDefinition.create(mesh, 48, 48);
	}

	@Override
	public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		Core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public void setupAnim(@NotNull LemurianEntity entity, float limbSwing, float limbSwingAmmount, float ageInTicks, float headYaw, float headPitch) {
	}
}
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
import net.minecraft.util.Mth;

public class LemurianModel extends EntityModel<LemurianEntity> {
  public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(RoRmod.MODID, "lemurian_entity"), "main");
  private final ModelPart Core;
  private ModelPart Stomach_axis;
  private ModelPart Stomach;
  private ModelPart Rib_cage_axis;
  private ModelPart Rib_cage;
  private ModelPart Neck_axis;
  private ModelPart Neck;
  private ModelPart head_axis;
  private ModelPart Head;
  private ModelPart Top_head;
  private ModelPart Top_mouth;
  private ModelPart Low_mouth_axis;
  private ModelPart Low_mouth;

  public LemurianModel(ModelPart root) {
    this.Core = root.getChild("Core");
  }

  public static LayerDefinition createBodyLayer() {
    var mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition Core = parts.addOrReplaceChild("Core", CubeListBuilder.create().texOffs(1, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, 0.0F));
    PartDefinition Stomach_axis = Core.addOrReplaceChild("Stomach_axis", CubeListBuilder.create().texOffs(10, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.08726646F, 0.0F, 0.0F));
    PartDefinition Stomach = Stomach_axis.addOrReplaceChild("Stomach", CubeListBuilder.create().texOffs(28, 10).addBox(-4.0F, -6.0F, -1.5F, 8.0F, 7.3F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Rib_cage_axis = Stomach.addOrReplaceChild("Rib_cage_axis", CubeListBuilder.create().texOffs(15, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -0.25F, 0.17453292F, 0.0F, 0.0F));
    PartDefinition Rib_cage = Rib_cage_axis.addOrReplaceChild("Rib_cage", CubeListBuilder.create().texOffs(1, 10).addBox(-4.5F, -5.0F, -2.0F, 9.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Neck_axis = Rib_cage.addOrReplaceChild("Neck_axis", CubeListBuilder.create().texOffs(20, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition Neck = Neck_axis.addOrReplaceChild("Neck", CubeListBuilder.create().texOffs(1, 1).addBox(-3.0F, -4.0F, -1.5F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Head_axis = Neck.addOrReplaceChild("Head_axis", CubeListBuilder.create().texOffs(25, 60).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, -0.5235988F, 0.0F, 0.0F));
    PartDefinition Head = Head_axis.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(20, 2).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.25F, 0.25F));
    PartDefinition Top_head = Head.addOrReplaceChild("Top_head", CubeListBuilder.create().texOffs(77, 3).addBox(-2.0F, -0.5F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, -3.5F));
    PartDefinition Top_mouth = Head.addOrReplaceChild("Top_mouth", CubeListBuilder.create().texOffs(60, 4).addBox(-2.5F, -1.0F, -3.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));
    PartDefinition Low_mouth_axis = Head.addOrReplaceChild("Low_mouth_axis", CubeListBuilder.create().texOffs(30, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.08726646F, 0.0F, 0.0F));
    PartDefinition Low_mouth = Low_mouth_axis.addOrReplaceChild("Low_mouth", CubeListBuilder.create().texOffs(39, 3).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
    PartDefinition Left_arm_axis = Rib_cage.addOrReplaceChild("Left_arm_axis", CubeListBuilder.create().texOffs(35, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 0.0F, -0.08726646F, 0.0F, 0.0F));
    PartDefinition Left_arm = Left_arm_axis.addOrReplaceChild("Left_arm", CubeListBuilder.create().texOffs(1, 31).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.0F));
    PartDefinition Left_shoulder_plate = Left_arm.addOrReplaceChild("Left_shoulder_plate", CubeListBuilder.create().texOffs(14, 36).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 0.0F));
    PartDefinition Left_forearm_axis = Left_arm.addOrReplaceChild("Left_forearm_axis", CubeListBuilder.create().texOffs(40, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 9.0F, 0.0F, -0.2617994F, 0.0F, 0.0F));
    PartDefinition Left_forearm = Left_forearm_axis.addOrReplaceChild("Left_forearm", CubeListBuilder.create().texOffs(31, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
    PartDefinition Left_claws = Left_forearm.addOrReplaceChild("Left_claws", CubeListBuilder.create().texOffs(40, 36).addBox(-0.5F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 0.0F));
    PartDefinition Right_arm_axis = Rib_cage.addOrReplaceChild("Right_arm_axis", CubeListBuilder.create().texOffs(45, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, 0.0F, -0.08726646F, 0.0F, 0.0F));
    PartDefinition Right_arm = Right_arm_axis.addOrReplaceChild("Right_arm", CubeListBuilder.create().texOffs(51, 31).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.5F, 0.0F));
    PartDefinition Right_shoulder_plate = Right_arm.addOrReplaceChild("Right_shoulder_plate", CubeListBuilder.create().texOffs(64, 36).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.5F, 0.0F));
    PartDefinition Right_forearm_axis = Right_arm.addOrReplaceChild("Right_forearm_axis", CubeListBuilder.create().texOffs(50, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2617994F, 0.0F, 0.0F));
    PartDefinition Right_forearm = Right_forearm_axis.addOrReplaceChild("Right_forearm", CubeListBuilder.create().texOffs(81, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 0.0F));
    PartDefinition Right_claws = Right_forearm.addOrReplaceChild("Right_claws", CubeListBuilder.create().texOffs(90, 36).addBox(-1.5F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 0.0F));
    PartDefinition Tail_1_axis = Core.addOrReplaceChild("Tail_1_axis", CubeListBuilder.create().texOffs(95, 60).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition Tail_1 = Tail_1_axis.addOrReplaceChild("Tail_1", CubeListBuilder.create().texOffs(1, 21).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Tail_2_axis = Tail_1.addOrReplaceChild("Tail_2_axis", CubeListBuilder.create().texOffs(100, 60).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -0.5F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition Tail_2 = Tail_2_axis.addOrReplaceChild("Tail_2", CubeListBuilder.create().texOffs(16, 23).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Tail_3_axis = Tail_2.addOrReplaceChild("Tail_3_axis", CubeListBuilder.create().texOffs(105, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, -0.75F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition Tail_3 = Tail_3_axis.addOrReplaceChild("Tail_3", CubeListBuilder.create().texOffs(27, 25).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Left_leg_1_axis = Core.addOrReplaceChild("Left_leg_1_axis", CubeListBuilder.create().texOffs(75, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.75F, 0.0F, -0.43633232F, 0.0F, 0.0F));
    PartDefinition Left_leg_1 = Left_leg_1_axis.addOrReplaceChild("Left_leg_1", CubeListBuilder.create().texOffs(49, 46).addBox(-1.0F, -1.0F, -1.75F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Left_leg_2_axis = Left_leg_1.addOrReplaceChild("Left_leg_2_axis", CubeListBuilder.create().texOffs(80, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 6.0F, -0.5F, 0.9599311F, 0.0F, 0.0F));
    PartDefinition Left_leg_2 = Left_leg_2_axis.addOrReplaceChild("Left_leg_2", CubeListBuilder.create().texOffs(64, 50).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Left_leg_3_axis = Left_leg_2.addOrReplaceChild("Left_leg_3_axis", CubeListBuilder.create().texOffs(85, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.87266463F, 0.0F, 0.0F));
    PartDefinition Left_leg_3 = Left_leg_3_axis.addOrReplaceChild("Left_leg_3", CubeListBuilder.create().texOffs(75, 51).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Left_foot_axis = Left_leg_3.addOrReplaceChild("Left_foot_axis", CubeListBuilder.create().texOffs(90, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.25F, 0.0F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition Left_foot = Left_foot_axis.addOrReplaceChild("Left_foot", CubeListBuilder.create().texOffs(84, 52).addBox(-1.5F, 0.0F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Right_leg_1_axis = Core.addOrReplaceChild("Right_leg_1_axis", CubeListBuilder.create().texOffs(55, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.75F, 0.0F, -0.43633232F, 0.0F, 0.0F));
    PartDefinition Right_leg_1 = Right_leg_1_axis.addOrReplaceChild("Right_leg_1", CubeListBuilder.create().texOffs(1, 46).addBox(-3.0F, -1.0F, -1.75F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Right_leg_2_axis = Right_leg_1.addOrReplaceChild("Right_leg_2_axis", CubeListBuilder.create().texOffs(60, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, -0.5F, 0.9599311F, 0.0F, 0.0F));
    PartDefinition Right_leg_2 = Right_leg_2_axis.addOrReplaceChild("Right_leg_2", CubeListBuilder.create().texOffs(16, 50).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Right_leg_3_axis = Right_leg_2.addOrReplaceChild("Right_leg_3_axis", CubeListBuilder.create().texOffs(65, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.87266463F, 0.0F, 0.0F));
    PartDefinition Right_leg_3 = Right_leg_3_axis.addOrReplaceChild("Right_leg_3", CubeListBuilder.create().texOffs(27, 51).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition Right_foot_axis = Right_leg_3.addOrReplaceChild("Right_foot_axis", CubeListBuilder.create().texOffs(70, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.25F, 0.0F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition Right_foot = Right_foot_axis.addOrReplaceChild("Right_foot", CubeListBuilder.create().texOffs(36, 52).addBox(-1.5F, 0.0F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(mesh, 110, 64);
  }

  @Override
  public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    Core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void setupAnim(LemurianEntity entity, float limbSwing, float limbSwingAmmount, float ageInTicks, float headYaw, float headPitch) {
    // Looking anims
    this.Head.xRot = headPitch * ((float)Math.PI / 180F);
    this.Head.yRot = headYaw * ((float)Math.PI / 180F);
    // Idle anims
    this.Low_mouth.xRot = Mth.cos(ageInTicks * 0.06F) * 0.09F;
  }
}

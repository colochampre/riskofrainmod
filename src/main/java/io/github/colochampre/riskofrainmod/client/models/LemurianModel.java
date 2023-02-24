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
  protected final ModelPart Core;
  protected ModelPart Stomach_axis;
  protected ModelPart Stomach;
  protected ModelPart Rib_cage_axis;
  protected ModelPart Rib_cage;
  protected ModelPart Neck_axis;
  protected ModelPart Neck;
  protected ModelPart Head_axis;
  protected ModelPart Head;
  protected ModelPart Top_head;
  protected ModelPart Top_mouth;
  protected ModelPart Low_mouth_axis;
  protected ModelPart Low_mouth;
  protected ModelPart Left_arm_axis;
  protected ModelPart Left_arm;
  protected ModelPart Left_shoulder_plate;
  protected ModelPart Left_forearm_axis;
  protected ModelPart Left_forearm;
  protected ModelPart Left_claws;
  protected ModelPart Right_arm_axis;
  protected ModelPart Right_arm;
  protected ModelPart Right_shoulder_plate;
  protected ModelPart Right_forearm_axis;
  protected ModelPart Right_forearm;
  protected ModelPart Right_claws;
  protected ModelPart Tail_1_axis;
  protected ModelPart Tail_1;
  protected ModelPart Tail_2_axis;
  protected ModelPart Tail_2;
  protected ModelPart Tail_3_axis;
  protected ModelPart Tail_3;
  protected ModelPart Left_leg_1_axis;
  protected ModelPart Left_leg_1;
  protected ModelPart Left_leg_2_axis;
  protected ModelPart Left_leg_2;
  protected ModelPart Left_leg_3_axis;
  protected ModelPart Left_leg_3;
  protected ModelPart Left_foot_axis;
  protected ModelPart Left_foot;
  protected ModelPart Right_leg_1_axis;
  protected ModelPart Right_leg_1;
  protected ModelPart Right_leg_2_axis;
  protected ModelPart Right_leg_2;
  protected ModelPart Right_leg_3_axis;
  protected ModelPart Right_leg_3;
  protected ModelPart Right_foot_axis;
  protected ModelPart Right_foot;

  public LemurianModel(ModelPart root) {
    this.Core = root.getChild("Core");
    this.Stomach_axis = Core.getChild("Stomach_axis");
    this.Stomach = Stomach_axis.getChild("Stomach");
    this.Rib_cage_axis = Stomach.getChild("Rib_cage_axis");
    this.Rib_cage = Rib_cage_axis.getChild("Rib_cage");
    this.Neck_axis = Rib_cage.getChild("Neck_axis");
    this.Neck = Neck_axis.getChild("Neck");
    this.Head_axis = Neck.getChild("Head_axis");
    this.Head = Head_axis.getChild("Head");
    this.Top_head = Head.getChild("Top_head");
    this.Top_mouth = Head.getChild("Top_mouth");
    this.Low_mouth_axis = Head.getChild("Low_mouth_axis");
    this.Low_mouth = Low_mouth_axis.getChild("Low_mouth");
    this.Left_arm_axis = Rib_cage.getChild("Left_arm_axis");
    this.Left_arm = Left_arm_axis.getChild("Left_arm");
    this.Left_shoulder_plate = Left_arm.getChild("Left_shoulder_plate");
    this.Left_forearm_axis = Left_arm.getChild("Left_forearm_axis");
    this.Left_forearm = Left_forearm_axis.getChild("Left_forearm");
    this.Left_claws = Left_forearm.getChild("Left_claws");
    this.Right_arm_axis = Rib_cage.getChild("Right_arm_axis");
    this.Right_arm = Right_arm_axis.getChild("Right_arm");
    this.Right_shoulder_plate = Right_arm.getChild("Right_shoulder_plate");
    this.Right_forearm_axis = Right_arm.getChild("Right_forearm_axis");
    this.Right_forearm = Right_forearm_axis.getChild("Right_forearm");
    this.Right_claws = Right_forearm.getChild("Right_claws");
    this.Tail_1_axis = Core.getChild("Tail_1_axis");
    this.Tail_1 = Tail_1_axis.getChild("Tail_1");
    this.Tail_2_axis = Tail_1.getChild("Tail_2_axis");
    this.Tail_2 = Tail_2_axis.getChild("Tail_2");
    this.Tail_3_axis = Tail_2.getChild("Tail_3_axis");
    this.Tail_3 = Tail_3_axis.getChild("Tail_3");
    this.Left_leg_1_axis = Core.getChild("Left_leg_1_axis");
    this.Left_leg_1 = Left_leg_1_axis.getChild("Left_leg_1");
    this.Left_leg_2_axis = Left_leg_1.getChild("Left_leg_2_axis");
    this.Left_leg_2 = Left_leg_2_axis.getChild("Left_leg_2");
    this.Left_leg_3_axis = Left_leg_2.getChild("Left_leg_3_axis");
    this.Left_leg_3 = Left_leg_3_axis.getChild("Left_leg_3");
    this.Left_foot_axis = Left_leg_3.getChild("Left_foot_axis");
    this.Left_foot = Left_foot_axis.getChild("Left_foot");
    this.Right_leg_1_axis = Core.getChild("Right_leg_1_axis");
    this.Right_leg_1 = Right_leg_1_axis.getChild("Right_leg_1");
    this.Right_leg_2_axis = Right_leg_1.getChild("Right_leg_2_axis");
    this.Right_leg_2 = Right_leg_2_axis.getChild("Right_leg_2");
    this.Right_leg_3_axis = Right_leg_2.getChild("Right_leg_3_axis");
    this.Right_leg_3 = Right_leg_3_axis.getChild("Right_leg_3");
    this.Right_foot_axis = Right_leg_3.getChild("Right_foot_axis");
    this.Right_foot = Right_foot_axis.getChild("Right_foot");
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
    setLookAnim(headYaw, headPitch);
    setIdleAnim(entity, ageInTicks);
  }

  private void setLookAnim(float headYaw, float headPitch) {
    this.Head.xRot = headPitch * 0.023271058F;
    this.Head.yRot = headYaw * 0.017453292F / 2.0F;
    this.Rib_cage_axis.yRot = headYaw * 0.017453292F / 5.0F;
    this.Stomach_axis.yRot = headYaw * 0.017453292F / 5.0F;
  }

  private void setIdleAnim(LemurianEntity entity, float ageInTicks) {
    this.Low_mouth.xRot = Mth.cos(ageInTicks * 0.06F) * 0.09F;
    this.Neck.xRot = Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.Neck.yRot = Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.Neck.zRot = -Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.Rib_cage.xRot = -Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.Tail_1.xRot = Mth.cos(ageInTicks * 0.08F) * 0.09F;
    this.Tail_1.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    this.Tail_2.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    this.Tail_3.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    if (!entity.isMoving(entity)) {
      this.Head_axis.xRot = -0.5235988F + Mth.cos(ageInTicks * 0.06F) * 0.06F;
      this.Head_axis.yRot = Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.Head_axis.zRot = -Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.Left_arm.xRot = -0.17453292F + -Mth.cos(ageInTicks * 0.06F) * 0.03F;
      this.Left_arm.zRot = Mth.cos(ageInTicks * 0.09F) * 0.03F;
      this.Right_arm.xRot = -0.17453292F + Mth.cos(ageInTicks * 0.06F) * 0.03F;
      this.Right_arm.zRot = -Mth.cos(ageInTicks * 0.09F) * 0.03F;
    }
  }
}

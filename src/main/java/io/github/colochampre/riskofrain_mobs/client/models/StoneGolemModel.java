package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class StoneGolemModel<T extends StoneGolemEntity> extends EntityModel<T> {
  protected final ModelPart core;
  protected ModelPart torso_axis;
  protected ModelPart torso;
  protected ModelPart torso_2;
  protected ModelPart torso_3;
  protected ModelPart torso_4;
  protected ModelPart torso_5;
  protected ModelPart torso_6;
  protected ModelPart neck_axis;
  protected ModelPart neck;
  protected ModelPart head_axis;
  protected ModelPart head;
  protected ModelPart head_2;
  protected ModelPart eye;
  protected ModelPart head_3_1;
  protected ModelPart head_3_2;
  protected ModelPart head_4_1;
  protected ModelPart head_5_1;
  protected ModelPart head_4_2;
  protected ModelPart head_5_2;
  protected ModelPart head_6;
  protected ModelPart head_7_1;
  protected ModelPart head_7_2;
  protected ModelPart head_7_3;
  protected ModelPart head_8_1;
  protected ModelPart head_8_2_1;
  protected ModelPart head_8_2_2;
  protected ModelPart left_shoulder_axis;
  protected ModelPart left_shoulder;
  protected ModelPart left_shoulder_2;
  protected ModelPart left_shoulder_3;
  protected ModelPart left_arm_1_axis;
  protected ModelPart left_arm_1;
  protected ModelPart left_arm_1_1;
  protected ModelPart left_arm_2_axis;
  protected ModelPart left_arm_2;
  protected ModelPart left_forearm_axis;
  protected ModelPart left_forearm;
  protected ModelPart left_forearm_2;
  protected ModelPart left_forearm_3;
  protected ModelPart left_thumb;
  protected ModelPart left_hand_1_axis;
  protected ModelPart left_hand_1;
  protected ModelPart left_hand_1_1;
  protected ModelPart left_hand_2_axis;
  protected ModelPart left_hand_2;
  protected ModelPart left_hand_2_1;
  protected ModelPart right_shoulder_axis;
  protected ModelPart right_shoulder;
  protected ModelPart right_arm_axis;
  protected ModelPart right_arm;
  protected ModelPart right_arm_2;
  protected ModelPart right_arm_3;
  protected ModelPart right_elbow_axis;
  protected ModelPart right_elbow;
  protected ModelPart right_forearm_axis;
  protected ModelPart right_forearm;
  protected ModelPart right_forearm_2;
  protected ModelPart right_forearm_3;
  protected ModelPart right_thumb;
  protected ModelPart right_hand_1_axis;
  protected ModelPart right_hand_1;
  protected ModelPart right_hand_1_1;
  protected ModelPart right_hand_2_axis;
  protected ModelPart right_hand_2;
  protected ModelPart right_hand_2_1;
  protected ModelPart hip_axis;
  protected ModelPart hip;
  protected ModelPart hip_2;
  protected ModelPart hip_3_1;
  protected ModelPart hip_3_2;
  protected ModelPart left_leg_axis;
  protected ModelPart left_leg;
  protected ModelPart left_leg_1_1;
  protected ModelPart left_leg_1_2;
  protected ModelPart left_leg_2_axis;
  protected ModelPart left_leg_2;
  protected ModelPart left_leg_2_1;
  protected ModelPart left_leg_2_2;
  protected ModelPart right_leg_axis;
  protected ModelPart right_leg;
  protected ModelPart right_leg_1_1;
  protected ModelPart right_leg_1_2;
  protected ModelPart right_leg_2_axis;
  protected ModelPart right_leg_2;
  protected ModelPart right_leg_2_1;
  protected ModelPart right_leg_2_2;

  public StoneGolemModel(ModelPart root) {
    this.core = root.getChild("core");
    this.torso_axis = core.getChild("torso_axis");
    this.hip_axis = core.getChild("hip_axis");
    this.torso = torso_axis.getChild("torso");
    this.torso_2 = torso.getChild("torso_2");
    this.torso_3 = torso_2.getChild("torso_3");
    this.torso_4 = torso_3.getChild("torso_4");
    this.torso_5 = torso_4.getChild("torso_5");
    this.torso_6 = torso_5.getChild("torso_6");
    this.neck_axis = torso_5.getChild("neck_axis");
    this.left_shoulder_axis = torso_5.getChild("left_shoulder_axis");
    this.right_shoulder_axis = torso_5.getChild("right_shoulder_axis");
    this.neck = neck_axis.getChild("neck");
    this.head_axis = neck.getChild("head_axis");
    this.head = head_axis.getChild("head");
    this.head_2 = head.getChild("head_2");
    this.eye = head_2.getChild("eye");
    this.head_3_1 = head_2.getChild("head_3_1");
    this.head_3_2 = head_2.getChild("head_3_2");
    this.head_4_1 = head_3_1.getChild("head_4_1");
    this.head_4_2 = head_3_2.getChild("head_4_2");
    this.head_5_1 = head_4_1.getChild("head_5_1");
    this.head_5_2 = head_4_2.getChild("head_5_2");
    this.head_6 = head_5_2.getChild("head_6");
    this.head_7_1 = head_6.getChild("head_7_1");
    this.head_7_2 = head_6.getChild("head_7_2");
    this.head_7_3 = head_6.getChild("head_7_3");
    this.head_8_1 = head_7_3.getChild("head_8_1");
    this.head_8_2_1 = head_7_3.getChild("head_8_2_1");
    this.head_8_2_2 = head_8_2_1.getChild("head_8_2_2");
    this.left_shoulder = left_shoulder_axis.getChild("left_shoulder");
    this.left_shoulder_2 = left_shoulder.getChild("left_shoulder_2");
    this.left_shoulder_3 = left_shoulder_2.getChild("left_shoulder_3");
    this.left_arm_1_axis = left_shoulder_3.getChild("left_arm_1_axis");
    this.left_arm_1 = left_arm_1_axis.getChild("left_arm_1");
    this.left_arm_1_1 = left_arm_1.getChild("left_arm_1_1");
    this.left_arm_2_axis = left_arm_1_1.getChild("left_arm_2_axis");
    this.left_arm_2 = left_arm_2_axis.getChild("left_arm_2");
    this.left_forearm_axis = left_arm_2.getChild("left_forearm_axis");
    this.left_forearm = left_forearm_axis.getChild("left_forearm");
    this.left_forearm_2 = left_forearm.getChild("left_forearm_2");
    this.left_forearm_3 = left_forearm_2.getChild("left_forearm_3");
    this.left_thumb = left_forearm_2.getChild("left_thumb");
    this.left_hand_1_axis = left_forearm_3.getChild("left_hand_1_axis");
    this.left_hand_1 = left_hand_1_axis.getChild("left_hand_1");
    this.left_hand_1_1 = left_hand_1.getChild("left_hand_1_1");
    this.left_hand_2_axis = left_hand_1_1.getChild("left_hand_2_axis");
    this.left_hand_2 = left_hand_2_axis.getChild("left_hand_2");
    this.left_hand_2_1 = left_hand_2.getChild("left_hand_2_1");
    this.right_shoulder = right_shoulder_axis.getChild("right_shoulder");
    this.right_arm_axis = right_shoulder.getChild("right_arm_axis");
    this.right_arm = right_arm_axis.getChild("right_arm");
    this.right_arm_2 = right_arm.getChild("right_arm_2");
    this.right_arm_3 = right_arm_2.getChild("right_arm_3");
    this.right_elbow_axis = right_arm_3.getChild("right_elbow_axis");
    this.right_elbow = right_elbow_axis.getChild("right_elbow");
    this.right_forearm_axis = right_elbow.getChild("right_forearm_axis");
    this.right_forearm = right_forearm_axis.getChild("right_forearm");
    this.right_forearm_2 = right_forearm.getChild("right_forearm_2");
    this.right_forearm_3 = right_forearm_2.getChild("right_forearm_3");
    this.right_thumb = right_forearm_2.getChild("right_thumb");
    this.right_hand_1_axis = right_forearm_3.getChild("right_hand_1_axis");
    this.right_hand_1 = right_hand_1_axis.getChild("right_hand_1");
    this.right_hand_1_1 = right_hand_1.getChild("right_hand_1_1");
    this.right_hand_2_axis = right_hand_1_1.getChild("right_hand_2_axis");
    this.right_hand_2 = right_hand_2_axis.getChild("right_hand_2");
    this.right_hand_2_1 = right_hand_2.getChild("right_hand_2_1");
    this.hip = hip_axis.getChild("hip");
    this.left_leg_axis = hip.getChild("left_leg_axis");
    this.right_leg_axis = hip.getChild("right_leg_axis");
    this.hip_2 = hip.getChild("hip_2");
    this.hip_3_1 = hip_2.getChild("hip_3_1");
    this.hip_3_2 = hip_2.getChild("hip_3_2");
    this.left_leg = left_leg_axis.getChild("left_leg");
    this.left_leg_1_1 = left_leg.getChild("left_leg_1_1");
    this.left_leg_1_2 = left_leg_1_1.getChild("left_leg_1_2");
    this.left_leg_2_axis = left_leg_1_2.getChild("left_leg_2_axis");
    this.left_leg_2 = left_leg_2_axis.getChild("left_leg_2");
    this.left_leg_2_1 = left_leg_2.getChild("left_leg_2_1");
    this.left_leg_2_2 = left_leg_2_1.getChild("left_leg_2_2");
    this.right_leg = right_leg_axis.getChild("right_leg");
    this.right_leg_1_1 = right_leg.getChild("right_leg_1_1");
    this.right_leg_1_2 = right_leg_1_1.getChild("right_leg_1_2");
    this.right_leg_2_axis = right_leg_1_2.getChild("right_leg_2_axis");
    this.right_leg_2 = right_leg_2_axis.getChild("right_leg_2");
    this.right_leg_2_1 = right_leg_2.getChild("right_leg_2_1");
    this.right_leg_2_2 = right_leg_2_1.getChild("right_leg_2_2");
  }

  public static LayerDefinition createBodyLayer() {
    var mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition core = parts.addOrReplaceChild("core", CubeListBuilder.create().texOffs(1, 149).addBox(-5.0F, -1.5F, -2.5F, 10.0F, 2.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.5F, 0.0F));
    PartDefinition torso_axis = core.addOrReplaceChild("torso_axis", CubeListBuilder.create().texOffs(32, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.5F, 0.0F));
    PartDefinition torso = torso_axis.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(1, 49).addBox(-12.5F, -0.5F, -3.5F, 25.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition torso_2 = torso.addOrReplaceChild("torso_2", CubeListBuilder.create().texOffs(66, 41).addBox(-13.5F, -8.0F, -4.0F, 27.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));
    PartDefinition torso_3 = torso_2.addOrReplaceChild("torso_3", CubeListBuilder.create().texOffs(137, 47).addBox(-12.5F, -2.0F, -4.0F, 25.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -8.0F, 0.0F));
    PartDefinition torso_4 = torso_3.addOrReplaceChild("torso_4", CubeListBuilder.create().texOffs(204, 47).addBox(-12.5F, -3.0F, -4.0F, 24.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
    PartDefinition torso_5 = torso_4.addOrReplaceChild("torso_5", CubeListBuilder.create().texOffs(269, 49).addBox(-11.5F, -2.0F, -3.5F, 22.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
    PartDefinition torso_6 = torso_5.addOrReplaceChild("torso_6", CubeListBuilder.create().texOffs(328, 54).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-9.5F, -2.0F, -2.5F));
    PartDefinition neck_axis = torso_5.addOrReplaceChild("neck_axis", CubeListBuilder.create().texOffs(37, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.0F, -3.0F, 0.0F));
    PartDefinition neck = neck_axis.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(1, 35).addBox(-3.0F, -0.5F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_axis = neck.addOrReplaceChild("head_axis", CubeListBuilder.create().texOffs(42, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -2.0F, 0.0F));
    PartDefinition head = head_axis.addOrReplaceChild("head", CubeListBuilder.create().texOffs(1, 26).addBox(-9.0F, -0.5F, -3.5F, 18.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_2 = head.addOrReplaceChild("head_2", CubeListBuilder.create().texOffs(52, 25).addBox(-11.0F, -1.5F, -4.0F, 22.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_3_1 = head_2.addOrReplaceChild("head_3_1", CubeListBuilder.create().texOffs(137, 15).addBox(-10.0F, -1.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.5F, 0.0F));
    PartDefinition head_3_2 = head_2.addOrReplaceChild("head_3_2", CubeListBuilder.create().texOffs(1, 15).addBox(0.0F, -1.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -1.5F, 0.0F));
    PartDefinition head_4_1 = head_3_1.addOrReplaceChild("head_4_1", CubeListBuilder.create().texOffs(174, 12).addBox(-9.0F, -4.0F, -4.0F, 9.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -1.0F, 0.0F));
    PartDefinition head_4_2 = head_3_2.addOrReplaceChild("head_4_2", CubeListBuilder.create().texOffs(38, 12).addBox(0.0F, -4.0F, -4.0F, 9.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -1.0F, 0.0F));
    PartDefinition head_5_1 = head_4_1.addOrReplaceChild("head_5_1", CubeListBuilder.create().texOffs(209, 15).addBox(-10.0F, -1.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -4.0F, 0.0F));
    PartDefinition head_5_2 = head_4_2.addOrReplaceChild("head_5_2", CubeListBuilder.create().texOffs(73, 15).addBox(0.0F, -1.0F, -4.0F, 10.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -4.0F, 0.0F));
    PartDefinition head_6 = head_5_2.addOrReplaceChild("head_6", CubeListBuilder.create().texOffs(1, 1).addBox(-11.0F, -1.0F, -4.0F, 23.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -1.0F, 0.0F));
    PartDefinition head_7_1 = head_6.addOrReplaceChild("head_7_1", CubeListBuilder.create().texOffs(130, 4).addBox(-1.5F, -1.0F, -2.5F, 3.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.5F, -1.0F, 1.5F));
    PartDefinition head_7_2 = head_6.addOrReplaceChild("head_7_2", CubeListBuilder.create().texOffs(111, 2).addBox(-2.0F, -1.0F, -3.5F, 2.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.0F, 0.5F));
    PartDefinition head_7_3 = head_6.addOrReplaceChild("head_7_3", CubeListBuilder.create().texOffs(64, 2).addBox(0.0F, -1.0F, -3.5F, 16.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -1.0F, 0.0F));
    PartDefinition head_8_1 = head_7_3.addOrReplaceChild("head_8_1", CubeListBuilder.create().texOffs(173, 8).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(7.0F, -1.0F, -3.0F));
    PartDefinition head_8_2_1 = head_7_3.addOrReplaceChild("head_8_2_1", CubeListBuilder.create().texOffs(147, 5).addBox(-2.0F, -1.0F, -1.5F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(13.0F, -1.0F, -2.0F));
    PartDefinition head_8_2_2 = head_8_2_1.addOrReplaceChild("head_8_2_2", CubeListBuilder.create().texOffs(164, 7).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 0.0F));
    PartDefinition eye = head_2.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(110, 11).addBox(-3.0F, -6.0F, -3.5F, 6.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 0.5F));
    PartDefinition left_shoulder_axis = torso_5.addOrReplaceChild("left_shoulder_axis", CubeListBuilder.create().texOffs(47, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(16.5F, -1.5F, 0.0F));
    PartDefinition left_shoulder = left_shoulder_axis.addOrReplaceChild("left_shoulder", CubeListBuilder.create().texOffs(1, 67).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, -0.5235988F));
    PartDefinition left_shoulder_2 = left_shoulder.addOrReplaceChild("left_shoulder_2", CubeListBuilder.create().texOffs(23, 58).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 1.5F, 0.0F));
    PartDefinition left_shoulder_3 = left_shoulder_2.addOrReplaceChild("left_shoulder_3", CubeListBuilder.create().texOffs(56, 66).addBox(-3.0F, 6.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_arm_1_axis = left_shoulder_3.addOrReplaceChild("left_arm_1_axis", CubeListBuilder.create().texOffs(52, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 9.0F, 0.0F, 0.0F, 0.0F, 0.5235988F));
    PartDefinition left_arm_1 = left_arm_1_axis.addOrReplaceChild("left_arm_1", CubeListBuilder.create().texOffs(81, 68).addBox(-3.0F, -0.5F, -2.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.08726646F, -0.2617994F));
    PartDefinition left_arm_1_1 = left_arm_1.addOrReplaceChild("left_arm_1_1", CubeListBuilder.create().texOffs(102, 67).addBox(-3.5F, 0.0F, -2.5F, 7.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, 0.0F));
    PartDefinition left_arm_2_axis = left_arm_1_1.addOrReplaceChild("left_arm_2_axis", CubeListBuilder.create().texOffs(57, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.5F, 0.0F, 0.0F, 0.0F, 0.2617994F));
    PartDefinition left_arm_2 = left_arm_2_axis.addOrReplaceChild("left_arm_2", CubeListBuilder.create().texOffs(127, 67).addBox(-4.5F, -0.5F, -2.0F, 9.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -0.5F, 0.0F, 0.0F, -0.08726646F));
    PartDefinition left_forearm_axis = left_arm_2.addOrReplaceChild("left_forearm_axis", CubeListBuilder.create().texOffs(62, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.5F, 0.0F, 0.0F, 0.0F, 0.08726646F));
    PartDefinition left_forearm = left_forearm_axis.addOrReplaceChild("left_forearm", CubeListBuilder.create().texOffs(1, 87).addBox(-3.0F, -0.5F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.0F, 0.0F, -0.08726646F, 0.0F));
    PartDefinition left_forearm_2 = left_forearm.addOrReplaceChild("left_forearm_2", CubeListBuilder.create().texOffs(30, 74).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.5F));
    PartDefinition left_forearm_3 = left_forearm_2.addOrReplaceChild("left_forearm_3", CubeListBuilder.create().texOffs(67, 87).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -0.5F));
    PartDefinition left_hand_1_axis = left_forearm_3.addOrReplaceChild("left_hand_1_axis", CubeListBuilder.create().texOffs(67, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, 0.5F));
    PartDefinition left_hand_1 = left_hand_1_axis.addOrReplaceChild("left_hand_1", CubeListBuilder.create().texOffs(96, 88).addBox(-2.5F, -0.5F, -3.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));
    PartDefinition left_hand_1_1 = left_hand_1.addOrReplaceChild("left_hand_1_1", CubeListBuilder.create().texOffs(121, 88).addBox(-2.0F, 0.5F, -3.5F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 0.0F));
    PartDefinition left_hand_2_axis = left_hand_1_1.addOrReplaceChild("left_hand_2_axis", CubeListBuilder.create().texOffs(72, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 2.5F, 0.0F));
    PartDefinition left_hand_2 = left_hand_2_axis.addOrReplaceChild("left_hand_2", CubeListBuilder.create().texOffs(144, 86).addBox(-2.5F, -0.5F, -3.5F, 5.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, 0.0F, 0.0F));
    PartDefinition left_hand_2_1 = left_hand_2.addOrReplaceChild("left_hand_2_1", CubeListBuilder.create().texOffs(169, 88).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.5F, 0.0F));
    PartDefinition left_thumb = left_forearm_2.addOrReplaceChild("left_thumb", CubeListBuilder.create().texOffs(190, 88).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, 6.5F, -6.0F));
    PartDefinition right_shoulder_axis = torso_5.addOrReplaceChild("right_shoulder_axis", CubeListBuilder.create().texOffs(77, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-15.5F, 0.0F, 0.0F));
    PartDefinition right_shoulder = right_shoulder_axis.addOrReplaceChild("right_shoulder", CubeListBuilder.create().texOffs(154, 59).addBox(-3.0F, -0.5F, -6.0F, 6.0F, 2.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.5F, 0.0F, 0.0F, 0.7853982F));
    PartDefinition right_arm_axis = right_shoulder.addOrReplaceChild("right_arm_axis", CubeListBuilder.create().texOffs(82, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 4.0F, 0.0F, 0.0F, 0.0F, -0.7853982F));
    PartDefinition right_arm = right_arm_axis.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(191, 67).addBox(-2.5F, -0.5F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.08726646F, 0.3926991F));
    PartDefinition right_arm_2 = right_arm.addOrReplaceChild("right_arm_2", CubeListBuilder.create().texOffs(212, 58).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 7.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.5F, 0.0F));
    PartDefinition right_arm_3 = right_arm_2.addOrReplaceChild("right_arm_3", CubeListBuilder.create().texOffs(245, 66).addBox(-3.0F, 6.0F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_elbow_axis = right_arm_3.addOrReplaceChild("right_elbow_axis", CubeListBuilder.create().texOffs(87, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 9.0F, 0.0F, 0.0F, 0.0F, -0.3926991F));
    PartDefinition right_elbow = right_elbow_axis.addOrReplaceChild("right_elbow", CubeListBuilder.create().texOffs(203, 91).addBox(-2.0F, -0.5F, -2.0F, 4.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_forearm_axis = right_elbow.addOrReplaceChild("right_forearm_axis", CubeListBuilder.create().texOffs(92, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
    PartDefinition right_forearm = right_forearm_axis.addOrReplaceChild("right_forearm", CubeListBuilder.create().texOffs(220, 87).addBox(-3.0F, -0.5F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 0.0F, -1.0F, 0.0F, 0.08726646F, 0.0F));
    PartDefinition right_forearm_2 = right_forearm.addOrReplaceChild("right_forearm_2", CubeListBuilder.create().texOffs(249, 74).addBox(-4.0F, 0.0F, -5.0F, 8.0F, 12.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.5F));
    PartDefinition right_forearm_3 = right_forearm_2.addOrReplaceChild("right_forearm_3", CubeListBuilder.create().texOffs(286, 87).addBox(-3.0F, 0.0F, -4.0F, 6.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, -0.5F));
    PartDefinition right_hand_1_axis = right_forearm_3.addOrReplaceChild("right_hand_1_axis", CubeListBuilder.create().texOffs(97, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, 0.5F));
    PartDefinition right_hand_1 = right_hand_1_axis.addOrReplaceChild("right_hand_1", CubeListBuilder.create().texOffs(315, 88).addBox(-2.5F, -0.5F, -3.5F, 5.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -0.5F));
    PartDefinition right_hand_1_1 = right_hand_1.addOrReplaceChild("right_hand_1_1", CubeListBuilder.create().texOffs(340, 88).addBox(-2.0F, 0.5F, -3.5F, 4.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));
    PartDefinition right_hand_2_axis = right_hand_1_1.addOrReplaceChild("right_hand_2_axis", CubeListBuilder.create().texOffs(102, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.5F, 0.0F));
    PartDefinition right_hand_2 = right_hand_2_axis.addOrReplaceChild("right_hand_2", CubeListBuilder.create().texOffs(363, 86).addBox(-2.5F, -0.5F, -3.5F, 5.0F, 3.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, 0.0F));
    PartDefinition right_hand_2_1 = right_hand_2.addOrReplaceChild("right_hand_2_1", CubeListBuilder.create().texOffs(388, 88).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.5F, 0.0F));
    PartDefinition right_thumb = right_forearm_2.addOrReplaceChild("right_thumb", CubeListBuilder.create().texOffs(409, 88).addBox(-1.5F, 0.0F, -1.5F, 3.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, 6.5F, -6.0F));
    PartDefinition hip_axis = core.addOrReplaceChild("hip_axis", CubeListBuilder.create().texOffs(107, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));
    PartDefinition hip = hip_axis.addOrReplaceChild("hip", CubeListBuilder.create().texOffs(1, 97).addBox(-11.0F, -2.5F, -3.0F, 22.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));
    PartDefinition hip_2 = hip.addOrReplaceChild("hip_2", CubeListBuilder.create().texOffs(58, 101).addBox(-5.0F, 0.0F, -2.5F, 10.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));
    PartDefinition hip_3_1 = hip_2.addOrReplaceChild("hip_3_1", CubeListBuilder.create().texOffs(110, 101).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-8.0F, -5.0F, 0.0F));
    PartDefinition hip_3_2 = hip_2.addOrReplaceChild("hip_3_2", CubeListBuilder.create().texOffs(89, 101).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(8.0F, -5.0F, 0.0F));
    PartDefinition left_leg_axis = hip.addOrReplaceChild("left_leg_axis", CubeListBuilder.create().texOffs(112, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(9.0F, 3.0F, 0.0F));
    PartDefinition left_leg = left_leg_axis.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(1, 121).addBox(-3.0F, -0.5F, -3.0F, 6.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.17453292F, 0.0F));
    PartDefinition left_leg_1_1 = left_leg.addOrReplaceChild("left_leg_1_1", CubeListBuilder.create().texOffs(26, 109).addBox(-4.5F, 0.0F, -4.5F, 8.0F, 11.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.5F));
    PartDefinition left_leg_1_2 = left_leg_1_1.addOrReplaceChild("left_leg_1_2", CubeListBuilder.create().texOffs(59, 120).addBox(-3.5F, 0.0F, -3.5F, 7.0F, 1.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 11.0F, -0.5F));
    PartDefinition left_leg_2_axis = left_leg_1_2.addOrReplaceChild("left_leg_2_axis", CubeListBuilder.create().texOffs(117, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 2.0F, 0.0F));
    PartDefinition left_leg_2 = left_leg_2_axis.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(1, 141).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, -1.0F, 0.0F, -0.08726646F, 0.0F));
    PartDefinition left_leg_2_1 = left_leg_2.addOrReplaceChild("left_leg_2_1", CubeListBuilder.create().texOffs(24, 129).addBox(-3.5F, 0.0F, -4.5F, 7.0F, 10.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 1.0F));
    PartDefinition left_leg_2_2 = left_leg_2_1.addOrReplaceChild("left_leg_2_2", CubeListBuilder.create().texOffs(57, 139).addBox(-3.5F, 0.0F, -3.5F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 10.0F, 0.0F));
    PartDefinition right_leg_axis = hip.addOrReplaceChild("right_leg_axis", CubeListBuilder.create().texOffs(122, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, 3.0F, 0.0F));
    PartDefinition right_leg = right_leg_axis.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(88, 121).addBox(-3.0F, -0.5F, -3.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.17453292F, 0.0F));
    PartDefinition right_leg_1_1 = right_leg.addOrReplaceChild("right_leg_1_1", CubeListBuilder.create().texOffs(115, 108).addBox(-4.5F, 0.0F, -4.0F, 9.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.0F));
    PartDefinition right_leg_1_2 = right_leg_1_1.addOrReplaceChild("right_leg_1_2", CubeListBuilder.create().texOffs(150, 121).addBox(-3.5F, 0.0F, -3.0F, 7.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
    PartDefinition right_leg_2_axis = right_leg_1_2.addOrReplaceChild("right_leg_2_axis", CubeListBuilder.create().texOffs(127, 154).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 2.0F, 0.0F));
    PartDefinition right_leg_2 = right_leg_2_axis.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(82, 141).addBox(-2.5F, -0.5F, -3.0F, 5.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.0F, 0.0F, 0.08726646F, 0.0F));
    PartDefinition right_leg_2_1 = right_leg_2.addOrReplaceChild("right_leg_2_1", CubeListBuilder.create().texOffs(105, 130).addBox(-3.5F, 0.0F, -4.5F, 7.0F, 9.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.5F, -0.5F));
    PartDefinition right_leg_2_2 = right_leg_2_1.addOrReplaceChild("right_leg_2_2", CubeListBuilder.create().texOffs(138, 139).addBox(-3.5F, 0.0F, -3.5F, 5.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, 9.0F, 0.0F));

    return LayerDefinition.create(mesh, 422, 157);
  }

  @Override
  public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void setupAnim(StoneGolemEntity entity, float limbSwing, float limbSwingAmmount, float ageInTicks, float headYaw, float headPitch) {
    getLookAnim(headYaw, headPitch);
    getIdleAnim(entity, ageInTicks);
    getWalkingAnim(entity, limbSwing, limbSwingAmmount);
  }

  @Override
  public void prepareMobModel(StoneGolemEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    int i = entity.getAttackTimer();
    if (i > 0) {
      getAttackAnim(entity, i, ageInTicks);
    } else {
      this.left_shoulder_axis.xRot = Mth.cos(limbSwing * 0.5F) * 0.8F * limbSwingAmount;
      this.left_arm_1_axis.xRot = Mth.cos(limbSwing * 0.5F) * 0.8F * limbSwingAmount;
      this.left_forearm_axis.xRot = -Mth.cos(limbSwing * 0.75F) * 0.8F * limbSwingAmount;
      this.left_hand_1_axis.xRot = -Mth.cos(limbSwing * 0.75F) * 0.8F * limbSwingAmount;
      this.right_shoulder_axis.xRot = Mth.cos(limbSwing * 0.5F + 3.1415927F) * 0.8F * limbSwingAmount;
      this.right_arm_axis.xRot = Mth.cos(limbSwing * 0.5F + 3.1415927F) * 0.8F * limbSwingAmount;
      this.right_forearm_axis.xRot = -Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.8F * limbSwingAmount;
      this.right_hand_1_axis.xRot = -Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.8F * limbSwingAmount;
      this.left_shoulder_axis.yRot = 0.0F;
      this.left_shoulder_axis.zRot = 0.0F;
      this.left_arm_1_axis.zRot = 0.5235988F;
      this.left_forearm_axis.zRot = 0.08726646F;
      this.left_hand_1_axis.zRot = 0.0F;
      this.right_shoulder_axis.yRot = 0.0F;
      this.right_shoulder_axis.zRot = 0.0F;
      this.right_elbow_axis.zRot = -0.3926991F;
      this.right_forearm_axis.zRot = 0.0F;
      this.right_hand_1_axis.zRot = 0.0F;
    }
  }

  private void getLookAnim(float headYaw, float headPitch) {
    this.head.xRot = headPitch * 0.017453292F;
    this.head.yRot = headYaw * 0.005817764F;
    this.neck.yRot = headYaw * 0.005817764F;
    this.torso.yRot = headYaw * 0.005817764F;
  }

  private void getIdleAnim(StoneGolemEntity entity, float ageInTicks) {
    this.neck.xRot = Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.torso.xRot = -Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.left_shoulder.xRot = -Mth.cos(ageInTicks * 0.06F) * 0.03F;
    this.left_shoulder.zRot = -0.5235988F + Mth.cos(ageInTicks * 0.06F) * 0.03F;
    this.right_shoulder.xRot = Mth.cos(ageInTicks * 0.06F) * 0.03F;
    this.right_shoulder.zRot = 0.7853982F + -Mth.cos(ageInTicks * 0.06F) * 0.03F;
  }

  // field_f = xRot | field_g = yRot | field_h = zRot
  private void getWalkingAnim(StoneGolemEntity entity, float limbSwing, float limbSwingAmount) {
    this.torso_axis.yRot = Mth.cos(limbSwing * 0.5F + 3.1415927F) * 1.6F * limbSwingAmount;
    this.left_shoulder_axis.yRot = -0.08726646F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.4F * limbSwingAmount;
    this.right_shoulder_axis.yRot = -0.08726646F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.4F * limbSwingAmount;
    this.hip_axis.yRot = Mth.cos(limbSwing * 0.5F) * 0.8F * limbSwingAmount;
    this.left_leg_axis.xRot = 0.0F + Mth.cos(limbSwing * 0.5F + 3.1415927F) * 1.2F * limbSwingAmount;
    this.left_leg_2_axis.xRot = 0.0F + -Mth.cos(limbSwing * 1.0F + 3.1415927F) * 0.6F * limbSwingAmount;
    this.right_leg_axis.xRot = 0.0F + Mth.cos(limbSwing * 0.5F) * 1.2F * limbSwingAmount;
    this.right_leg_2_axis.xRot = 0.0F + Mth.cos(limbSwing * 1.0F) * 0.6F * limbSwingAmount;
  }

  private void getAttackAnim(StoneGolemEntity entity, int i, float ageInTicks) {
    if (i > 0) {
      this.left_shoulder_axis.xRot = -(-0.45F + 0.45F * Mth.triangleWave((float) i - ageInTicks, 20.0F));
      this.left_shoulder_axis.yRot = -(-0.75F + 0.45F * Mth.triangleWave((float) i - ageInTicks, 20.0F));
      this.left_shoulder_axis.zRot = -0.45F + 0.45F * Mth.triangleWave((float) i - ageInTicks, 20.0F);
      /*
      this.torso_axis.xRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 15.0F));
      this.left_shoulder_axis.xRot = -0.75F + 0.75F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.left_shoulder_axis.yRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
      this.left_shoulder_axis.zRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
      this.left_arm_1_axis.zRot = -(-0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 15.0F));
      this.left_forearm_axis.xRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.left_forearm_axis.zRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 15.0F));
      this.left_hand_1_axis.zRot = -0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.right_shoulder_axis.xRot = -0.75F + 0.75F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.right_shoulder_axis.yRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
      this.right_shoulder_axis.zRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
      this.right_elbow_axis.zRot = -0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.right_forearm_axis.xRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.right_forearm_axis.zRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
      this.right_hand_1_axis.zRot = -(-0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 15.0F));
      */
    }
    if (i > 0 && i < 14) {
      this.left_forearm_axis.xRot = -0.90F + 0.90F * Mth.triangleWave((float) i - ageInTicks, 15.0F);
    }
  }
}

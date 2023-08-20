package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class LemurianModel<T extends LemurianEntity> extends EntityModel<T> {
  protected final ModelPart core;
  protected ModelPart stomach_axis;
  protected ModelPart stomach;
  protected ModelPart rib_cage_axis;
  protected ModelPart rib_cage;
  protected ModelPart neck_axis;
  protected ModelPart neck;
  protected ModelPart head_axis;
  protected ModelPart head;
  protected ModelPart top_head;
  protected ModelPart top_mouth;
  protected ModelPart low_mouth_axis;
  protected ModelPart low_mouth;
  protected ModelPart left_arm_axis;
  protected ModelPart left_arm;
  protected ModelPart left_shoulder_plate;
  protected ModelPart left_forearm_axis;
  protected ModelPart left_forearm;
  protected ModelPart left_claws;
  protected ModelPart right_arm_axis;
  protected ModelPart right_arm;
  protected ModelPart right_shoulder_plate;
  protected ModelPart right_forearm_axis;
  protected ModelPart right_forearm;
  protected ModelPart right_claws;
  protected ModelPart tail_1_axis;
  protected ModelPart tail_1;
  protected ModelPart tail_2_axis;
  protected ModelPart tail_2;
  protected ModelPart tail_3_axis;
  protected ModelPart tail_3;
  protected ModelPart left_leg_1_axis;
  protected ModelPart left_leg_1;
  protected ModelPart left_leg_2_axis;
  protected ModelPart left_leg_2;
  protected ModelPart left_leg_3_axis;
  protected ModelPart left_leg_3;
  protected ModelPart left_foot_axis;
  protected ModelPart left_foot;
  protected ModelPart right_leg_1_axis;
  protected ModelPart right_leg_1;
  protected ModelPart right_leg_2_axis;
  protected ModelPart right_leg_2;
  protected ModelPart right_leg_3_axis;
  protected ModelPart right_leg_3;
  protected ModelPart right_foot_axis;
  protected ModelPart right_foot;
  private boolean rightHandSelected;

  public LemurianModel(ModelPart root) {
    this.core = root.getChild("core");
    this.stomach_axis = core.getChild("stomach_axis");
    this.stomach = stomach_axis.getChild("stomach");
    this.rib_cage_axis = stomach.getChild("rib_cage_axis");
    this.rib_cage = rib_cage_axis.getChild("rib_cage");
    this.neck_axis = rib_cage.getChild("neck_axis");
    this.neck = neck_axis.getChild("neck");
    this.head_axis = neck.getChild("head_axis");
    this.head = head_axis.getChild("head");
    this.top_head = head.getChild("top_head");
    this.top_mouth = head.getChild("top_mouth");
    this.low_mouth_axis = head.getChild("low_mouth_axis");
    this.low_mouth = low_mouth_axis.getChild("low_mouth");
    this.left_arm_axis = rib_cage.getChild("left_arm_axis");
    this.left_arm = left_arm_axis.getChild("left_arm");
    this.left_shoulder_plate = left_arm.getChild("left_shoulder_plate");
    this.left_forearm_axis = left_arm.getChild("left_forearm_axis");
    this.left_forearm = left_forearm_axis.getChild("left_forearm");
    this.left_claws = left_forearm.getChild("left_claws");
    this.right_arm_axis = rib_cage.getChild("right_arm_axis");
    this.right_arm = right_arm_axis.getChild("right_arm");
    this.right_shoulder_plate = right_arm.getChild("right_shoulder_plate");
    this.right_forearm_axis = right_arm.getChild("right_forearm_axis");
    this.right_forearm = right_forearm_axis.getChild("right_forearm");
    this.right_claws = right_forearm.getChild("right_claws");
    this.tail_1_axis = core.getChild("tail_1_axis");
    this.tail_1 = tail_1_axis.getChild("tail_1");
    this.tail_2_axis = tail_1.getChild("tail_2_axis");
    this.tail_2 = tail_2_axis.getChild("tail_2");
    this.tail_3_axis = tail_2.getChild("tail_3_axis");
    this.tail_3 = tail_3_axis.getChild("tail_3");
    this.left_leg_1_axis = core.getChild("left_leg_1_axis");
    this.left_leg_1 = left_leg_1_axis.getChild("left_leg_1");
    this.left_leg_2_axis = left_leg_1.getChild("left_leg_2_axis");
    this.left_leg_2 = left_leg_2_axis.getChild("left_leg_2");
    this.left_leg_3_axis = left_leg_2.getChild("left_leg_3_axis");
    this.left_leg_3 = left_leg_3_axis.getChild("left_leg_3");
    this.left_foot_axis = left_leg_3.getChild("left_foot_axis");
    this.left_foot = left_foot_axis.getChild("left_foot");
    this.right_leg_1_axis = core.getChild("right_leg_1_axis");
    this.right_leg_1 = right_leg_1_axis.getChild("right_leg_1");
    this.right_leg_2_axis = right_leg_1.getChild("right_leg_2_axis");
    this.right_leg_2 = right_leg_2_axis.getChild("right_leg_2");
    this.right_leg_3_axis = right_leg_2.getChild("right_leg_3_axis");
    this.right_leg_3 = right_leg_3_axis.getChild("right_leg_3");
    this.right_foot_axis = right_leg_3.getChild("right_foot_axis");
    this.right_foot = right_foot_axis.getChild("right_foot");
  }

  public static LayerDefinition createBodyLayer() {
    var mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition core = parts.addOrReplaceChild("core", CubeListBuilder.create().texOffs(1, 58).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 10.5F, 0.0F));
    PartDefinition stomach_axis = core.addOrReplaceChild("stomach_axis", CubeListBuilder.create().texOffs(10, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.08726646F, 0.0F, 0.0F));
    PartDefinition stomach = stomach_axis.addOrReplaceChild("stomach", CubeListBuilder.create().texOffs(28, 10).addBox(-4.0F, -6.0F, -1.5F, 8.0F, 7.3F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition rib_cage_axis = stomach.addOrReplaceChild("rib_cage_axis", CubeListBuilder.create().texOffs(15, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -5.0F, -0.25F, 0.17453292F, 0.0F, 0.0F));
    PartDefinition rib_cage = rib_cage_axis.addOrReplaceChild("rib_cage", CubeListBuilder.create().texOffs(1, 10).addBox(-4.5F, -5.0F, -2.0F, 9.0F, 6.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition neck_axis = rib_cage.addOrReplaceChild("neck_axis", CubeListBuilder.create().texOffs(20, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 0.0F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition neck = neck_axis.addOrReplaceChild("neck", CubeListBuilder.create().texOffs(1, 1).addBox(-3.0F, -4.0F, -1.5F, 6.0F, 5.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_axis = neck.addOrReplaceChild("head_axis", CubeListBuilder.create().texOffs(25, 60).addBox(-0.5F, -0.5F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -3.5F, 0.5F, -0.5235988F, 0.0F, 0.0F));
    PartDefinition head = head_axis.addOrReplaceChild("head", CubeListBuilder.create().texOffs(20, 2).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 3.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.25F, 0.25F));
    PartDefinition top_head = head.addOrReplaceChild("top_head", CubeListBuilder.create().texOffs(77, 3).addBox(-2.0F, -0.5F, -2.5F, 4.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.25F, -3.5F));
    PartDefinition top_mouth = head.addOrReplaceChild("top_mouth", CubeListBuilder.create().texOffs(60, 4).addBox(-2.5F, -1.0F, -3.0F, 5.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, -4.0F));
    PartDefinition low_mouth_axis = head.addOrReplaceChild("low_mouth_axis", CubeListBuilder.create().texOffs(30, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.0F, -2.0F, 0.08726646F, 0.0F, 0.0F));
    PartDefinition low_mouth = low_mouth_axis.addOrReplaceChild("low_mouth", CubeListBuilder.create().texOffs(39, 3).addBox(-2.5F, 0.0F, -4.0F, 5.0F, 1.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
    PartDefinition left_arm_axis = rib_cage.addOrReplaceChild("left_arm_axis", CubeListBuilder.create().texOffs(35, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.0F, -4.0F, 0.0F, -0.08726646F, 0.0F, 0.0F));
    PartDefinition left_arm = left_arm_axis.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(1, 31).addBox(-1.0F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.5F, 0.0F));
    PartDefinition left_shoulder_plate = left_arm.addOrReplaceChild("left_shoulder_plate", CubeListBuilder.create().texOffs(14, 36).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(1.0F, -0.5F, 0.0F));
    PartDefinition left_forearm_axis = left_arm.addOrReplaceChild("left_forearm_axis", CubeListBuilder.create().texOffs(40, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 9.0F, 0.0F, -0.2617994F, 0.0F, 0.0F));
    PartDefinition left_forearm = left_forearm_axis.addOrReplaceChild("left_forearm", CubeListBuilder.create().texOffs(31, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 0.0F));
    PartDefinition left_claws = left_forearm.addOrReplaceChild("left_claws", CubeListBuilder.create().texOffs(40, 36).addBox(-0.5F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 0.0F));
    PartDefinition right_arm_axis = rib_cage.addOrReplaceChild("right_arm_axis", CubeListBuilder.create().texOffs(45, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-5.0F, -4.0F, 0.0F, -0.08726646F, 0.0F, 0.0F));
    PartDefinition right_arm = right_arm_axis.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(51, 31).addBox(-2.0F, -1.0F, -1.5F, 3.0F, 11.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.5F, 0.0F));
    PartDefinition right_shoulder_plate = right_arm.addOrReplaceChild("right_shoulder_plate", CubeListBuilder.create().texOffs(64, 36).addBox(-2.0F, -1.0F, -2.0F, 4.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.0F, -0.5F, 0.0F));
    PartDefinition right_forearm_axis = right_arm.addOrReplaceChild("right_forearm_axis", CubeListBuilder.create().texOffs(50, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 9.0F, 0.0F, -0.2617994F, 0.0F, 0.0F));
    PartDefinition right_forearm = right_forearm_axis.addOrReplaceChild("right_forearm", CubeListBuilder.create().texOffs(81, 36).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 7.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 1.0F, 0.0F));
    PartDefinition right_claws = right_forearm.addOrReplaceChild("right_claws", CubeListBuilder.create().texOffs(90, 36).addBox(-1.5F, 0.0F, -1.5F, 2.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.75F, 0.0F));
    PartDefinition tail_1_axis = core.addOrReplaceChild("tail_1_axis", CubeListBuilder.create().texOffs(95, 60).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 1.5F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition tail_1 = tail_1_axis.addOrReplaceChild("tail_1", CubeListBuilder.create().texOffs(1, 21).addBox(-2.0F, 0.0F, -3.0F, 4.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition tail_2_axis = tail_1.addOrReplaceChild("tail_2_axis", CubeListBuilder.create().texOffs(100, 60).addBox(-0.5F, 0.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 5.0F, -0.5F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition tail_2 = tail_2_axis.addOrReplaceChild("tail_2", CubeListBuilder.create().texOffs(16, 23).addBox(-1.5F, 0.0F, -2.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition tail_3_axis = tail_2.addOrReplaceChild("tail_3_axis", CubeListBuilder.create().texOffs(105, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.5F, -0.75F, 0.2617994F, 0.0F, 0.0F));
    PartDefinition tail_3 = tail_3_axis.addOrReplaceChild("tail_3", CubeListBuilder.create().texOffs(27, 25).addBox(-1.0F, 0.0F, -0.5F, 2.0F, 4.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_leg_1_axis = core.addOrReplaceChild("left_leg_1_axis", CubeListBuilder.create().texOffs(75, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.5F, 0.75F, 0.0F, -0.43633232F, 0.0F, 0.0F));
    PartDefinition left_leg_1 = left_leg_1_axis.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(49, 46).addBox(-1.0F, -1.0F, -1.75F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_leg_2_axis = left_leg_1.addOrReplaceChild("left_leg_2_axis", CubeListBuilder.create().texOffs(80, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 6.0F, -0.5F, 0.9599311F, 0.0F, 0.0F));
    PartDefinition left_leg_2 = left_leg_2_axis.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(64, 50).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_leg_3_axis = left_leg_2.addOrReplaceChild("left_leg_3_axis", CubeListBuilder.create().texOffs(85, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.87266463F, 0.0F, 0.0F));
    PartDefinition left_leg_3 = left_leg_3_axis.addOrReplaceChild("left_leg_3", CubeListBuilder.create().texOffs(75, 51).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_foot_axis = left_leg_3.addOrReplaceChild("left_foot_axis", CubeListBuilder.create().texOffs(90, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.25F, 0.0F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition left_foot = left_foot_axis.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(84, 52).addBox(-1.5F, 0.0F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_leg_1_axis = core.addOrReplaceChild("right_leg_1_axis", CubeListBuilder.create().texOffs(55, 60).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.5F, 0.75F, 0.0F, -0.43633232F, 0.0F, 0.0F));
    PartDefinition right_leg_1 = right_leg_1_axis.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(1, 46).addBox(-3.0F, -1.0F, -1.75F, 4.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_leg_2_axis = right_leg_1.addOrReplaceChild("right_leg_2_axis", CubeListBuilder.create().texOffs(60, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 6.0F, -0.5F, 0.9599311F, 0.0F, 0.0F));
    PartDefinition right_leg_2 = right_leg_2_axis.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(16, 50).addBox(-1.5F, 0.0F, -1.0F, 3.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_leg_3_axis = right_leg_2.addOrReplaceChild("right_leg_3_axis", CubeListBuilder.create().texOffs(65, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 4.0F, 0.0F, -0.87266463F, 0.0F, 0.0F));
    PartDefinition right_leg_3 = right_leg_3_axis.addOrReplaceChild("right_leg_3", CubeListBuilder.create().texOffs(27, 51).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_foot_axis = right_leg_3.addOrReplaceChild("right_foot_axis", CubeListBuilder.create().texOffs(70, 60).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5F, 3.25F, 0.0F, 0.34906584F, 0.0F, 0.0F));
    PartDefinition right_foot = right_foot_axis.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(36, 52).addBox(-1.5F, 0.0F, -3.5F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(mesh, 110, 64);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void setupAnim(@NotNull LemurianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
    getLookAnim(headYaw, headPitch);
    getIdleAnim(entity, ageInTicks);
    getWalkAnim(entity, limbSwing, limbSwingAmount, ageInTicks);
  }

  @Override
  public void prepareMobModel(LemurianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    int i = entity.getAttackTimer();
    if (i > 0) {
      getAttackAnim(entity, i, ageInTicks);
    } else {
      entity.setIsSelectingHand(true);
      this.left_arm_axis.xRot = -0.08726646F + Mth.cos(limbSwing * 0.75F) * 0.75F * limbSwingAmount;
      this.left_forearm_axis.xRot = -0.2617994F + Mth.cos(limbSwing * 0.75F) * 0.75F * limbSwingAmount;
      this.right_arm_axis.xRot = -0.08726646F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.75F * limbSwingAmount;
      this.right_forearm_axis.xRot = -0.2617994F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.75F * limbSwingAmount;
      this.head_axis.xRot = -0.5235988F + -Mth.cos(limbSwing * 1.5F) * 0.3F * limbSwingAmount;
      this.neck_axis.xRot = 0.2617994F + Mth.cos(limbSwing * 1.5F) * 0.3F * limbSwingAmount;
      this.left_arm_axis.zRot = 0.0F;
      this.right_arm_axis.zRot = 0.0F;
      this.rib_cage_axis.xRot = 0.17453292F;
      this.rib_cage_axis.zRot = 0.0F;
      this.stomach_axis.xRot = 0.08726646F;
      this.stomach_axis.zRot = 0.0F;
    }
  }

  // field_f = xRot | field_g = yRot | field_h = zRot
  private void getLookAnim(float headYaw, float headPitch) {
    this.head.xRot = headPitch * 0.023271058F;
    this.head.yRot = headYaw * 0.017453292F / 2.0F;
    this.rib_cage.yRot = headYaw * 0.017453292F / 5.0F;
    this.stomach.yRot = headYaw * 0.017453292F / 5.0F;
  }

  private void getIdleAnim(LemurianEntity entity, float ageInTicks) {
    this.low_mouth.xRot = Mth.cos(ageInTicks * 0.06F) * 0.09F;
    this.neck.xRot = Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.neck.yRot = Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.neck.zRot = -Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.rib_cage.xRot = -Mth.cos(ageInTicks * 0.06F) * 0.06F;
    this.tail_1.xRot = Mth.cos(ageInTicks * 0.08F) * 0.09F;
    this.tail_1.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    this.tail_2.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    this.tail_3.zRot = Mth.cos(ageInTicks * 0.12F) * 0.06F;
    if (!entity.isMoving(entity)) {
      this.head_axis.xRot = -0.5235988F + Mth.cos(ageInTicks * 0.06F) * 0.06F;
      this.head_axis.yRot = Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.head_axis.zRot = -Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.left_arm.xRot = -0.17453292F + -Mth.cos(ageInTicks * 0.06F) * 0.03F;
      this.left_arm.zRot = Mth.cos(ageInTicks * 0.09F) * 0.03F;
      this.right_arm.xRot = -0.17453292F + Mth.cos(ageInTicks * 0.06F) * 0.03F;
      this.right_arm.zRot = -Mth.cos(ageInTicks * 0.09F) * 0.03F;
    }
  }

  private void getWalkAnim(LemurianEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    this.rib_cage_axis.yRot = Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.15F * limbSwingAmount;
    this.stomach_axis.yRot = Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.3F * limbSwingAmount;
    this.left_arm_axis.yRot = -0.08726646F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.4F * limbSwingAmount;
    this.right_arm_axis.yRot = -0.08726646F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 0.4F * limbSwingAmount;
    this.left_leg_1_axis.xRot = -0.34906584F + Mth.cos(limbSwing * 0.75F + 3.1415927F) * 1.2F * limbSwingAmount;
    this.left_leg_2_axis.xRot = 0.9599311F + -Mth.cos(limbSwing * 0.5F + 3.1415927F) * 1.0F * limbSwingAmount;
    this.left_leg_3_axis.xRot = -0.87266463F + Mth.cos(limbSwing * 0.5F + 3.1415927F) * 1.5F * limbSwingAmount;
    this.left_foot_axis.xRot = 0.34906584F + -Mth.cos(limbSwing * 0.5F + 3.1415927F) * 1.4F * limbSwingAmount;
    this.right_leg_1_axis.xRot = -0.34906584F + Mth.cos(limbSwing * 0.75F) * 1.2F * limbSwingAmount;
    this.right_leg_2_axis.xRot = 0.9599311F + -Mth.cos(limbSwing * 0.5F) * 1.0F * limbSwingAmount;
    this.right_leg_3_axis.xRot = -0.87266463F + Mth.cos(limbSwing * 0.5F) * 1.5F * limbSwingAmount;
    this.right_foot_axis.xRot = 0.34906584F + -Mth.cos(limbSwing * 0.5F) * 1.4F * limbSwingAmount;
    this.tail_1_axis.zRot = Mth.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
    this.tail_2_axis.zRot = Mth.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
    this.tail_3_axis.zRot = Mth.cos(limbSwing * 0.5F) * 0.31F * limbSwingAmount;
  }

  private void getAttackAnim(LemurianEntity entity, int i, float ageInTicks) {
    //int random = RandomSource.create().nextIntBetweenInclusive(1, 3);
    if (entity.getIsSelectedHand()) {
      this.rightHandSelected = entity.getIsRightHandSelected();
      entity.setIsSelectingHand(false);
    }
    // right arm anims
    if (this.rightHandSelected) {
      //switch(random) {
      //case 1:
      rightPunch(i, ageInTicks);
      //break;
      //case 2:
      //break;
      //case 3:
      //break;
      //}
      // left arm anims
    } else {
      //switch(random) {
      //case 1:
      leftPunch(i, ageInTicks);
      //break;
      //case 2:
      //break;
      //case 3:
      //break;
      //}
    }
  }

  private void rightPunch(int i, float ageInTicks) {
    this.left_arm_axis.xRot = -0.75F + 0.75F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
    this.left_arm_axis.zRot = -0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
    this.left_forearm_axis.xRot = 0.0F + -1.25F + 1.25F * Mth.triangleWave((float) i - ageInTicks, 5.0F);
    this.right_arm_axis.zRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
    this.rib_cage_axis.xRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 5.0F));
    this.rib_cage_axis.zRot = -0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
    this.stomach_axis.xRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
    this.stomach_axis.zRot = -0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
  }

  private void leftPunch(int i, float ageInTicks) {
    this.right_arm_axis.xRot = -0.75F + 0.75F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
    this.right_arm_axis.zRot = -(-0.5F + 0.5F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
    this.right_forearm_axis.xRot = 0.0F + -1.25F + 1.25F * Mth.triangleWave((float) i - ageInTicks, 5.0F);
    this.left_arm_axis.zRot = -0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F);
    this.rib_cage_axis.xRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 5.0F));
    this.rib_cage_axis.zRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
    this.stomach_axis.xRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
    this.stomach_axis.zRot = -(-0.1F + 0.1F * Mth.triangleWave((float) i - ageInTicks, 10.0F));
  }
}

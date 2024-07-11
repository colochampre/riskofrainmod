package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.BeetleEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class BeetleModel<T extends BeetleEntity> extends EntityModel<T> {

  protected final ModelPart core;
  private final ModelPart chest_axis;
  private final ModelPart chest;
  private final ModelPart chest_shell_axis;
  private final ModelPart chest_shell;
  private final ModelPart head_axis;
  private final ModelPart head;
  private final ModelPart head_shell_axis;
  private final ModelPart head_shell;
  private final ModelPart left_arm_axis;
  private final ModelPart left_arm;
  private final ModelPart left_forearm_axis;
  private final ModelPart left_forearm;
  private final ModelPart right_arm_axis;
  private final ModelPart right_arm;
  private final ModelPart right_forearm_axis;
  private final ModelPart right_forearm;
  private final ModelPart hips_axis;
  private final ModelPart hips;
  private final ModelPart tail_axis;
  private final ModelPart tail;
  private final ModelPart left_leg_1_axis;
  private final ModelPart left_leg_1;
  private final ModelPart left_leg_2_axis;
  private final ModelPart left_leg_2;
  private final ModelPart right_leg_1_axis;
  private final ModelPart right_leg_1;
  private final ModelPart right_leg_2_axis;
  private final ModelPart right_leg_2;

  public BeetleModel(ModelPart root) {
    this.core = root.getChild("core");
    this.chest_axis = core.getChild("chest_axis");
    this.chest = chest_axis.getChild("chest");
    this.chest_shell_axis = chest.getChild("chest_shell_axis");
    this.chest_shell = chest_shell_axis.getChild("chest_shell");
    this.head_axis = chest.getChild("head_axis");
    this.head = head_axis.getChild("head");
    this.head_shell_axis = head.getChild("head_shell_axis");
    this.head_shell = head_shell_axis.getChild("head_shell");
    this.left_arm_axis = chest.getChild("left_arm_axis");
    this.left_arm = left_arm_axis.getChild("left_arm");
    this.left_forearm_axis = left_arm.getChild("left_forearm_axis");
    this.left_forearm = left_forearm_axis.getChild("left_forearm");
    this.right_arm_axis = chest.getChild("right_arm_axis");
    this.right_arm = right_arm_axis.getChild("right_arm");
    this.right_forearm_axis = right_arm.getChild("right_forearm_axis");
    this.right_forearm = right_forearm_axis.getChild("right_forearm");
    this.hips_axis = core.getChild("hips_axis");
    this.hips = hips_axis.getChild("hips");
    this.tail_axis = hips.getChild("tail_axis");
    this.tail = tail_axis.getChild("tail");
    this.left_leg_1_axis = hips.getChild("left_leg_1_axis");
    this.left_leg_1 = left_leg_1_axis.getChild("left_leg_1");
    this.left_leg_2_axis = left_leg_1.getChild("left_leg_2_axis");
    this.left_leg_2 = left_leg_2_axis.getChild("left_leg_2");
    this.right_leg_1_axis = hips.getChild("right_leg_1_axis");
    this.right_leg_1 = right_leg_1_axis.getChild("right_leg_1");
    this.right_leg_2_axis = right_leg_1.getChild("right_leg_2_axis");
    this.right_leg_2 = right_leg_2_axis.getChild("right_leg_2");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition core = parts.addOrReplaceChild("core", CubeListBuilder.create().texOffs(81, 113).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
    PartDefinition chest_axis = core.addOrReplaceChild("chest_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -3.0F, -2.0F, 0.0873F, 0.0F, 0.0F));
    PartDefinition chest = chest_axis.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(53, 0).addBox(-6.75F, -6.5F, -4.0F, 13.5F, 10.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 49).addBox(-3.75F, -8.0F, -3.0F, 7.5F, 13.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-7.75F, -4.0F, -4.5F, 15.5F, 4.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition chest_shell_axis = chest.addOrReplaceChild("chest_shell_axis", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 3.0F));
    PartDefinition chest_shell = chest_shell_axis.addOrReplaceChild("chest_shell", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition chest_shell_5_r1 = chest_shell.addOrReplaceChild("chest_shell_5_r1", CubeListBuilder.create().texOffs(0, 83).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 8.0F, -0.9163F, 0.0F, 0.0F));
    PartDefinition chest_shell_4_r1 = chest_shell.addOrReplaceChild("chest_shell_4_r1", CubeListBuilder.create().texOffs(83, 69).addBox(-8.0F, -0.75F, -1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.7854F, 0.0873F, -0.2618F));
    PartDefinition chest_shell_3_r1 = chest_shell.addOrReplaceChild("chest_shell_3_r1", CubeListBuilder.create().texOffs(112, 69).addBox(0.0F, -0.75F, -1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.7854F, -0.0873F, 0.2618F));
    PartDefinition chest_shell_2_r1 = chest_shell.addOrReplaceChild("chest_shell_2_r1", CubeListBuilder.create().texOffs(27, 49).addBox(-8.5F, -1.5F, -2.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.5672F, -0.0873F, -0.1309F));
    PartDefinition chest_shell_1_r1 = chest_shell.addOrReplaceChild("chest_shell_1_r1", CubeListBuilder.create().texOffs(27, 59).addBox(-0.5F, -1.5F, -2.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.5672F, 0.0873F, 0.1309F));
    PartDefinition head_axis = chest.addOrReplaceChild("head_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -9.0F, -1.5F, -0.0873F, 0.0F, 0.0F));
    PartDefinition head = head_axis.addOrReplaceChild("head", CubeListBuilder.create().texOffs(117, 34).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 6.0F, 7.5F, new CubeDeformation(0.0F))
            .texOffs(63, 83).addBox(-2.25F, -6.0F, -4.5F, 4.5F, 7.5F, 4.5F, new CubeDeformation(0.0F))
            .texOffs(0, 69).addBox(-2.5F, -4.0F, -6.5F, 5.0F, 4.0F, 8.5F, new CubeDeformation(0.0F))
            .texOffs(0, 34).addBox(-5.5F, -4.0F, -6.0F, 11.0F, 4.0F, 7.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_shell_axis = head.addOrReplaceChild("head_shell_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -5.0F, -2.0F, -0.0873F, 0.0F, 0.0F));
    PartDefinition head_shell = head_shell_axis.addOrReplaceChild("head_shell", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_shell_6_r1 = head_shell.addOrReplaceChild("head_shell_6_r1", CubeListBuilder.create().texOffs(43, 19).addBox(-6.5F, -3.5F, -6.5F, 9.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, -0.48F, 0.9163F, -0.4843F));
    PartDefinition head_shell_5_r1 = head_shell.addOrReplaceChild("head_shell_5_r1", CubeListBuilder.create().texOffs(84, 19).addBox(-2.5F, -3.5F, -6.5F, 9.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, -0.48F, -0.9163F, 0.4843F));
    PartDefinition head_shell_4_r1 = head_shell.addOrReplaceChild("head_shell_4_r1", CubeListBuilder.create().texOffs(39, 34).addBox(-9.5F, -1.5F, -4.0F, 11.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.5F, 0.0436F, 0.1745F, -0.1745F));
    PartDefinition head_shell_3_r1 = head_shell.addOrReplaceChild("head_shell_3_r1", CubeListBuilder.create().texOffs(78, 34).addBox(-1.5F, -1.5F, -4.0F, 11.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.5F, 0.0436F, -0.1745F, 0.1745F));
    PartDefinition head_shell_2_r1 = head_shell.addOrReplaceChild("head_shell_2_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-5.0F, -0.75F, -3.0F, 8.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, 0.5236F, -0.8727F, -0.6109F));
    PartDefinition head_shell_1_r1 = head_shell.addOrReplaceChild("head_shell_1_r1", CubeListBuilder.create().texOffs(98, 0).addBox(-3.0F, -0.75F, -3.0F, 8.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, 0.5236F, 0.8727F, 0.6109F));
    PartDefinition left_arm_axis = chest.addOrReplaceChild("left_arm_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(8.0F, -1.0F, 0.0F, 0.0436F, 0.0F, 0.0F));
    PartDefinition left_arm = left_arm_axis.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 113).addBox(-2.25F, -1.5F, -1.0F, 4.0F, 2.5F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(46, 83).addBox(-0.25F, 1.0F, -1.5F, 3.0F, 12.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_forearm_axis = left_arm.addOrReplaceChild("left_forearm_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(1.0F, 12.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
    PartDefinition left_forearm = left_forearm_axis.addOrReplaceChild("left_forearm", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -1.5F, -5.0F, 3.0F, 3.5F, 6.5F, new CubeDeformation(0.0F))
            .texOffs(57, 101).addBox(-1.0F, -1.25F, -10.0F, 3.0F, 2.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(70, 113).addBox(-0.5F, -1.25F, -13.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(89, 101).addBox(0.0F, -1.25F, -15.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_arm_axis = chest.addOrReplaceChild("right_arm_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(-8.0F, -1.0F, 1.0F, 0.0436F, 0.0F, 0.0F));
    PartDefinition right_arm = right_arm_axis.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(104, 101).addBox(-1.75F, -1.5F, -2.0F, 4.0F, 2.5F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(29, 83).addBox(-2.75F, 1.0F, -2.5F, 3.0F, 12.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_forearm_axis = right_arm.addOrReplaceChild("right_forearm_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(-1.0F, 12.0F, -1.0F, 0.1745F, 0.0F, 0.0F));
    PartDefinition right_forearm = right_forearm_axis.addOrReplaceChild("right_forearm", CubeListBuilder.create().texOffs(84, 83).addBox(-2.0F, -1.5F, -5.0F, 3.0F, 3.5F, 6.5F, new CubeDeformation(0.0F))
            .texOffs(40, 101).addBox(-2.0F, -1.25F, -10.0F, 3.0F, 2.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(59, 113).addBox(-1.5F, -1.25F, -13.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(74, 101).addBox(-1.0F, -1.25F, -15.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition hips_axis = core.addOrReplaceChild("hips_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 1.0F));
    PartDefinition hips = hips_axis.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(60, 49).addBox(-2.0F, -1.0F, -0.5F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition tail_axis = hips.addOrReplaceChild("tail_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 7.0F));
    PartDefinition tail = tail_axis.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, 101).addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_leg_1_axis = hips.addOrReplaceChild("left_leg_1_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(3.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
    PartDefinition left_leg_1 = left_leg_1_axis.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(56, 69).addBox(-1.0F, -1.5F, -1.0F, 2.5F, 2.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition left_leg_2_axis = left_leg_1.addOrReplaceChild("left_leg_2_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 8.5F));
    PartDefinition left_leg_2 = left_leg_2_axis.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(28, 113).addBox(-1.0F, -1.5F, -1.0F, 2.5F, 5.5F, 2.5F, new CubeDeformation(0.0F))
            .texOffs(50, 113).addBox(-0.5F, 3.5F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_leg_1_axis = hips.addOrReplaceChild("right_leg_1_axis", CubeListBuilder.create(), PartPose.offsetAndRotation(-3.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
    PartDefinition right_leg_1 = right_leg_1_axis.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(29, 69).addBox(-1.5F, -1.5F, -1.0F, 2.5F, 2.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition right_leg_2_axis = right_leg_1.addOrReplaceChild("right_leg_2_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 1.0F, 8.5F));
    PartDefinition right_leg_2 = right_leg_2_axis.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(15, 113).addBox(-1.5F, -1.5F, -1.0F, 2.5F, 5.5F, 2.5F, new CubeDeformation(0.0F))
            .texOffs(41, 113).addBox(-1.5F, 3.5F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(mesh, 160, 160);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void prepareMobModel(BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    int i = entity.getAttackTick();
    if (i > 0) {
      getAttackAnim(entity, i, ageInTicks);
    } else {
      this.core.z = 0 - Mth.cos(limbSwing) * 5.50F * limbSwingAmount;
      this.head.z = 0 - Mth.cos(limbSwing) * 2.00F * limbSwingAmount;
      this.chest_axis.xRot = 0.0873F + Mth.cos(limbSwing) * 0.40F * limbSwingAmount;
      this.head_axis.xRot = -0.0873F - Mth.cos(limbSwing) * 0.38F * limbSwingAmount;
    }
  }

  @Override
  public void setupAnim(@NotNull BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
    getLookAnim(entity, headYaw, headPitch);
    getIdleAnim(entity, ageInTicks);
    getWalkAnim(entity, limbSwing, limbSwingAmount, ageInTicks);
  }

  private void getLookAnim(BeetleEntity entity, float headYaw, float headPitch) {
    this.head.xRot = headPitch * 0.023271058F / 2;
    this.head.yRot = headYaw * 0.017453292F / 2;
    this.chest.yRot = 0;
    if (!entity.isMoving(entity)) {
      this.chest.yRot = headYaw * 0.017453292F / 3;
    }
  }

  private void getIdleAnim(BeetleEntity entity, float ageInTicks) {
    this.head_axis.yRot = -Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.head_axis.zRot = Mth.cos(ageInTicks * 0.04F) * 0.06F;
    this.tail.xRot = Mth.cos(ageInTicks * 0.08F) * 0.09F;
    this.tail.zRot = -Mth.cos(ageInTicks * 0.12F) * 0.06F;
    if (!entity.isMoving(entity)) {
      this.head_axis.xRot = Mth.cos(ageInTicks * 0.06F) * 0.06F;
      this.chest.xRot = Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.left_arm.xRot = -Mth.cos(ageInTicks * 0.05F) * 0.06F;
      this.right_arm.xRot = -Mth.cos(ageInTicks * 0.05F) * 0.06F;
    }
  }

  private void getWalkAnim(BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    this.core.y = 12 - Mth.cos(limbSwing) * 2.50F * limbSwingAmount;
    this.chest_shell_axis.xRot = 0 - Mth.cos(limbSwing) * 0.20F * limbSwingAmount;
    this.left_arm_axis.xRot = 0.0436F + Mth.cos(limbSwing) * 1.25F * limbSwingAmount;
    this.left_forearm_axis.xRot = 0.1745F - Mth.cos(limbSwing) * 1.25F * limbSwingAmount;
    this.right_arm_axis.xRot = 0.0436F + Mth.cos(limbSwing) * 0.65F * limbSwingAmount;
    this.right_forearm_axis.xRot = 0.1745F - Mth.cos(limbSwing) * 0.65F * limbSwingAmount;
    this.hips_axis.xRot = 0 - Mth.cos(limbSwing) * 0.40F * limbSwingAmount;
    this.left_leg_1_axis.xRot = -0.3491F - Mth.cos(limbSwing) * 0.15F * limbSwingAmount;
    this.left_leg_2_axis.xRot = 0 - Mth.cos(limbSwing) * 0.35F * limbSwingAmount;
    this.right_leg_1_axis.xRot = -0.3491F - Mth.cos(limbSwing) * 0.20F * limbSwingAmount;
    this.right_leg_2_axis.xRot = 0 - Mth.cos(limbSwing) * 0.30F * limbSwingAmount;
  }

  private void getAttackAnim(BeetleEntity entity, int i, float ageInTicks) {
    this.head_axis.xRot = -(-0.50F + 0.50F * Mth.triangleWave((float) i - ageInTicks, 16.0F));
    this.chest_axis.xRot = -(-0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 16.0F));
    this.left_arm.xRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 16.0F);
    this.right_arm.xRot = -0.25F + 0.25F * Mth.triangleWave((float) i - ageInTicks, 16.0F);
  }
}
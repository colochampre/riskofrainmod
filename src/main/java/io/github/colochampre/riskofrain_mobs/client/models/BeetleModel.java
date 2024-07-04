package io.github.colochampre.riskofrain_mobs.client.models;// Made with Blockbench 4.10.3
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.BeetleEntity;
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
public class BeetleModel<T extends BeetleEntity> extends EntityModel<T> {

  protected final ModelPart core;
  private final ModelPart chest;
  private final ModelPart chest_shell;
  private final ModelPart head;
  private final ModelPart head_shell;
  private final ModelPart left_arm;
  private final ModelPart left_forearm;
  private final ModelPart right_arm;
  private final ModelPart right_forearm;
  private final ModelPart hips;
  private final ModelPart tail;
  private final ModelPart left_leg_1;
  private final ModelPart left_leg_2;
  private final ModelPart right_leg_1;
  private final ModelPart right_leg_2;

  public BeetleModel(ModelPart root) {
    this.core = root.getChild("core");
    this.chest = core.getChild("chest");
    this.chest_shell = chest.getChild("chest_shell");
    this.head = chest.getChild("head");
    this.head_shell = head.getChild("head_shell");
    this.left_arm = chest.getChild("left_arm");
    this.left_forearm = left_arm.getChild("left_forearm");
    this.right_arm = chest.getChild("right_arm");
    this.right_forearm = right_arm.getChild("right_forearm");
    this.hips = core.getChild("hips");
    this.tail = hips.getChild("tail");
    this.left_leg_1 = hips.getChild("left_leg_1");
    this.left_leg_2 = left_leg_1.getChild("left_leg_2");
    this.right_leg_1 = hips.getChild("right_leg_1");
    this.right_leg_2 = right_leg_1.getChild("right_leg_2");
  }

  public static LayerDefinition createBodyLayer() {
    var mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition core = parts.addOrReplaceChild("core", CubeListBuilder.create().texOffs(81, 113).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 12.0F, 0.0F));
    PartDefinition chest = core.addOrReplaceChild("chest", CubeListBuilder.create().texOffs(53, 0).addBox(-6.75F, -6.5F, -4.0F, 13.5F, 10.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 49).addBox(-3.75F, -8.0F, -3.0F, 7.5F, 13.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-7.75F, -4.0F, -4.5F, 15.5F, 4.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, -2.0F));
    PartDefinition chest_shell = chest.addOrReplaceChild("chest_shell", CubeListBuilder.create(), PartPose.offset(0.0F, -9.0F, 3.0F));
    PartDefinition chest_shell_5_r1 = chest_shell.addOrReplaceChild("chest_shell_5_r1", CubeListBuilder.create().texOffs(0, 83).addBox(-4.0F, 0.0F, -1.0F, 8.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 7.0F, 8.0F, -0.9163F, 0.0F, 0.0F));
    PartDefinition chest_shell_4_r1 = chest_shell.addOrReplaceChild("chest_shell_4_r1", CubeListBuilder.create().texOffs(83, 69).addBox(-8.0F, -0.75F, -1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.7854F, 0.0873F, -0.2618F));
    PartDefinition chest_shell_3_r1 = chest_shell.addOrReplaceChild("chest_shell_3_r1", CubeListBuilder.create().texOffs(112, 69).addBox(0.0F, -0.75F, -1.0F, 8.0F, 2.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 3.0F, 4.0F, -0.7854F, -0.0873F, 0.2618F));
    PartDefinition chest_shell_2_r1 = chest_shell.addOrReplaceChild("chest_shell_2_r1", CubeListBuilder.create().texOffs(27, 49).addBox(-8.5F, -1.5F, -2.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.5672F, -0.0873F, -0.1309F));
    PartDefinition chest_shell_1_r1 = chest_shell.addOrReplaceChild("chest_shell_1_r1", CubeListBuilder.create().texOffs(27, 59).addBox(-0.5F, -1.5F, -2.0F, 9.0F, 2.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.5672F, 0.0873F, 0.1309F));
    PartDefinition head = chest.addOrReplaceChild("head", CubeListBuilder.create().texOffs(117, 34).addBox(-4.0F, -5.0F, -6.0F, 8.0F, 6.0F, 7.5F, new CubeDeformation(0.0F))
            .texOffs(63, 83).addBox(-2.25F, -6.0F, -4.5F, 4.5F, 7.5F, 4.5F, new CubeDeformation(0.0F))
            .texOffs(0, 69).addBox(-2.5F, -4.0F, -6.5F, 5.0F, 4.0F, 8.5F, new CubeDeformation(0.0F))
            .texOffs(0, 34).addBox(-5.5F, -4.0F, -6.0F, 11.0F, 4.0F, 7.5F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -9.0F, -1.5F));
    PartDefinition head_shell = head.addOrReplaceChild("head_shell", CubeListBuilder.create(), PartPose.offsetAndRotation(0.0F, -5.0F, -2.0F, -0.0873F, 0.0F, 0.0F));
    PartDefinition head_shell_6_r1 = head_shell.addOrReplaceChild("head_shell_6_r1", CubeListBuilder.create().texOffs(43, 19).addBox(-6.5F, -3.5F, -6.5F, 9.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, -0.48F, 0.9163F, -0.4843F));
    PartDefinition head_shell_5_r1 = head_shell.addOrReplaceChild("head_shell_5_r1", CubeListBuilder.create().texOffs(84, 19).addBox(-2.5F, -3.5F, -6.5F, 9.0F, 2.0F, 11.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 4.0F, -0.48F, -0.9163F, 0.4843F));
    PartDefinition head_shell_4_r1 = head_shell.addOrReplaceChild("head_shell_4_r1", CubeListBuilder.create().texOffs(39, 34).addBox(-9.5F, -1.5F, -4.0F, 11.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, -1.0F, 0.5F, 0.0436F, 0.1745F, -0.1745F));
    PartDefinition head_shell_3_r1 = head_shell.addOrReplaceChild("head_shell_3_r1", CubeListBuilder.create().texOffs(78, 34).addBox(-1.5F, -1.5F, -4.0F, 11.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, -1.0F, 0.5F, 0.0436F, -0.1745F, 0.1745F));
    PartDefinition head_shell_2_r1 = head_shell.addOrReplaceChild("head_shell_2_r1", CubeListBuilder.create().texOffs(0, 19).addBox(-5.0F, -0.75F, -3.0F, 8.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, 0.5236F, -0.8727F, -0.6109F));
    PartDefinition head_shell_1_r1 = head_shell.addOrReplaceChild("head_shell_1_r1", CubeListBuilder.create().texOffs(98, 0).addBox(-3.0F, -0.75F, -3.0F, 8.0F, 1.0F, 13.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -1.0F, -4.0F, 0.5236F, 0.8727F, 0.6109F));
    PartDefinition left_arm = chest.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 113).addBox(-2.25F, -1.5F, -1.0F, 4.0F, 2.5F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(46, 83).addBox(-0.25F, 1.0F, -1.5F, 3.0F, 12.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, -1.0F, 0.0F, 0.0873F, 0.0F, 0.0F));
    PartDefinition left_forearm = left_arm.addOrReplaceChild("left_forearm", CubeListBuilder.create().texOffs(0, 101).addBox(-1.0F, -1.5F, -5.0F, 3.0F, 3.5F, 6.5F, new CubeDeformation(0.0F))
            .texOffs(57, 101).addBox(-1.0F, -1.25F, -10.0F, 3.0F, 2.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(70, 113).addBox(-0.5F, -1.25F, -13.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(89, 101).addBox(0.0F, -1.25F, -15.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.0F, 12.0F, 0.0F, 0.1745F, 0.0F, 0.0F));
    PartDefinition right_arm2 = chest.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(104, 101).addBox(-1.75F, -1.5F, -2.0F, 4.0F, 2.5F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(29, 83).addBox(-2.75F, 1.0F, -2.5F, 3.0F, 12.0F, 4.5F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, -1.0F, 1.0F, 0.0873F, 0.0F, 0.0F));
    PartDefinition right_forearm2 = right_arm2.addOrReplaceChild("right_forearm", CubeListBuilder.create().texOffs(84, 83).addBox(-2.0F, -1.5F, -5.0F, 3.0F, 3.5F, 6.5F, new CubeDeformation(0.0F))
            .texOffs(40, 101).addBox(-2.0F, -1.25F, -10.0F, 3.0F, 2.5F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(59, 113).addBox(-1.5F, -1.25F, -13.0F, 2.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(74, 101).addBox(-1.0F, -1.25F, -15.0F, 1.0F, 3.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.0F, 12.0F, -1.0F, 0.1745F, 0.0F, 0.0F));
    PartDefinition hips = core.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(60, 49).addBox(-2.0F, -1.0F, -0.5F, 4.0F, 3.0F, 10.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 1.0F));
    PartDefinition tail = hips.addOrReplaceChild("tail", CubeListBuilder.create().texOffs(21, 101).addBox(-2.5F, -2.5F, -2.0F, 5.0F, 5.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 7.0F));
    PartDefinition left_leg_1 = hips.addOrReplaceChild("left_leg_1", CubeListBuilder.create().texOffs(56, 69).addBox(-1.0F, -1.5F, -1.0F, 2.5F, 2.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(3.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
    PartDefinition left_leg_2 = left_leg_1.addOrReplaceChild("left_leg_2", CubeListBuilder.create().texOffs(28, 113).addBox(-1.0F, -1.5F, -1.0F, 2.5F, 5.5F, 2.5F, new CubeDeformation(0.0F))
            .texOffs(50, 113).addBox(-0.5F, 3.5F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.5F));
    PartDefinition right_leg_1 = hips.addOrReplaceChild("right_leg_1", CubeListBuilder.create().texOffs(29, 69).addBox(-1.5F, -1.5F, -1.0F, 2.5F, 2.5F, 10.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, 0.0F, 3.0F, -0.3491F, 0.0F, 0.0F));
    PartDefinition right_leg_2 = right_leg_1.addOrReplaceChild("right_leg_2", CubeListBuilder.create().texOffs(15, 113).addBox(-1.5F, -1.5F, -1.0F, 2.5F, 5.5F, 2.5F, new CubeDeformation(0.0F))
            .texOffs(41, 113).addBox(-1.5F, 3.5F, -0.5F, 2.0F, 5.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.0F, 8.5F));

    return LayerDefinition.create(mesh, 160, 160);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void setupAnim(@NotNull BeetleEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
    getLookAnim(headYaw, headPitch);
  }

  private void getLookAnim(float headYaw, float headPitch) {
    this.head.xRot = headPitch * 0.023271058F / 2;
    this.head.yRot = headYaw * 0.017453292F / 2;
    this.chest.yRot = headYaw * 0.017453292F / 3.0F;
  }
}
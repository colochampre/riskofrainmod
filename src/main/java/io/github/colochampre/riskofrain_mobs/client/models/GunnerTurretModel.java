package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GunnerTurretModel<T extends GunnerTurretEntity> extends EntityModel<T> {
  private final ModelPart core;
  private final ModelPart hips_axis;
  private final ModelPart hips;
  private final ModelPart spout;
  private final ModelPart head_axis;
  private final ModelPart head;
  private final ModelPart bag;
  private final ModelPart bandolier_left;
  private final ModelPart bandolier_right;
  private final ModelPart gun_1;
  private final ModelPart gun_2_axis;
  private final ModelPart gun_2;
  private final ModelPart leg_front_left_1;
  private final ModelPart leg_front_left_2;
  private final ModelPart leg_front_left_3;
  private final ModelPart leg_front_left_4;
  private final ModelPart leg_front_left_5;
  private final ModelPart leg_front_right_1;
  private final ModelPart leg_front_right_2;
  private final ModelPart leg_front_right_3;
  private final ModelPart leg_front_right_4;
  private final ModelPart leg_front_right_5;
  private final ModelPart leg_back_left_1;
  private final ModelPart leg_back_left_2;
  private final ModelPart leg_back_left_3;
  private final ModelPart leg_back_left_4;
  private final ModelPart leg_back_left_5;
  private final ModelPart leg_back_right_1;
  private final ModelPart leg_back_right_2;
  private final ModelPart leg_back_right_3;
  private final ModelPart leg_back_right_4;
  private final ModelPart leg_back_right_5;

  private float bodyPitch;

  public GunnerTurretModel(ModelPart root) {
    this.core = root.getChild("core");
    this.hips_axis = core.getChild("hips_axis");
    this.hips = hips_axis.getChild("hips");
    this.spout = hips.getChild("spout");
    this.head_axis = spout.getChild("head_axis");
    this.head = head_axis.getChild("head");
    this.bag = head.getChild("bag");
    this.bandolier_left = bag.getChild("bandolier_left");
    this.bandolier_right = bag.getChild("bandolier_right");
    this.gun_1 = head.getChild("gun_1");
    this.gun_2_axis = gun_1.getChild("gun_2_axis");
    this.gun_2 = gun_2_axis.getChild("gun_2");
    this.leg_front_left_1 = hips.getChild("leg_front_left_1");
    this.leg_front_left_2 = leg_front_left_1.getChild("leg_front_left_2");
    this.leg_front_left_3 = leg_front_left_2.getChild("leg_front_left_3");
    this.leg_front_left_4 = leg_front_left_3.getChild("leg_front_left_4");
    this.leg_front_left_5 = leg_front_left_4.getChild("leg_front_left_5");
    this.leg_front_right_1 = hips.getChild("leg_front_right_1");
    this.leg_front_right_2 = leg_front_right_1.getChild("leg_front_right_2");
    this.leg_front_right_3 = leg_front_right_2.getChild("leg_front_right_3");
    this.leg_front_right_4 = leg_front_right_3.getChild("leg_front_right_4");
    this.leg_front_right_5 = leg_front_right_4.getChild("leg_front_right_5");
    this.leg_back_left_1 = hips.getChild("leg_back_left_1");
    this.leg_back_left_2 = leg_back_left_1.getChild("leg_back_left_2");
    this.leg_back_left_3 = leg_back_left_2.getChild("leg_back_left_3");
    this.leg_back_left_4 = leg_back_left_3.getChild("leg_back_left_4");
    this.leg_back_left_5 = leg_back_left_4.getChild("leg_back_left_5");
    this.leg_back_right_1 = hips.getChild("leg_back_right_1");
    this.leg_back_right_2 = leg_back_right_1.getChild("leg_back_right_2");
    this.leg_back_right_3 = leg_back_right_2.getChild("leg_back_right_3");
    this.leg_back_right_4 = leg_back_right_3.getChild("leg_back_right_4");
    this.leg_back_right_5 = leg_back_right_4.getChild("leg_back_right_5");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 70).addBox(-2.5F, 0.0F, -2.5F, 5.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 9.0F, 0.0F));
    PartDefinition hips_axis = core.addOrReplaceChild("hips_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition hips = hips_axis.addOrReplaceChild("hips", CubeListBuilder.create().texOffs(47, 68).addBox(-3.5F, -2.0F, -2.5F, 7.0F, 5.0F, 5.0F, new CubeDeformation(0.0F))
            .texOffs(22, 63).addBox(-2.5F, -2.0F, -3.5F, 5.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(0, 43).addBox(-4.5F, -1.0F, -3.5F, 9.0F, 5.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(36, 11).addBox(-3.5F, -1.0F, -4.5F, 7.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition spout = hips.addOrReplaceChild("spout", CubeListBuilder.create().texOffs(76, 18).addBox(-0.5F, -5.5F, -0.5F, 1.0F, 10.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition head_axis = spout.addOrReplaceChild("head_axis", CubeListBuilder.create(), PartPose.offset(0.0F, -6.0F, 0.0F));
    PartDefinition head = head_axis.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 56).addBox(-4.0F, -3.5F, -3.0F, 8.0F, 7.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(33, 47).addBox(-3.0F, -3.5F, -4.0F, 6.0F, 7.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(36, 0).addBox(-5.0F, 1.0F, -4.0F, 10.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(27, 28).addBox(-4.0F, 1.0F, -5.0F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(27, 35).addBox(-5.0F, -3.0F, -4.0F, 10.0F, 3.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(17, 0).addBox(-4.0F, -3.0F, -5.0F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(43, 29).addBox(-1.5F, -5.5F, -1.5F, 3.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, 0.0F));
    PartDefinition bag = head.addOrReplaceChild("bag", CubeListBuilder.create().texOffs(0, 28).addBox(-4.5F, -3.0F, 1.0F, 9.0F, 6.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition bag_hole_right_r1 = bag.addOrReplaceChild("bag_hole_right_r1", CubeListBuilder.create().texOffs(53, 26).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(23, 56).addBox(8.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.5F, -1.0F, 6.5F, 0.0873F, 0.0F, 0.0F));
    PartDefinition bandolier_left = bag.addOrReplaceChild("bandolier_left", CubeListBuilder.create(), PartPose.offset(5.0F, -1.0F, 6.5F));
    PartDefinition bandolier_left_2_r1 = bandolier_left.addOrReplaceChild("bandolier_left_2_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.25F, -2.0F, -15.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(0.0F, -2.0F, -15.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0873F, -0.5236F));
    PartDefinition bandolier_right = bag.addOrReplaceChild("bandolier_right", CubeListBuilder.create(), PartPose.offset(-5.0F, -1.0F, 6.5F));
    PartDefinition bandolier_right_2_r1 = bandolier_right.addOrReplaceChild("bandolier_right_2_r1", CubeListBuilder.create().texOffs(21, 58).addBox(0.25F, -2.0F, -15.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F))
            .texOffs(21, 58).addBox(0.0F, -2.0F, -15.0F, 0.0F, 9.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.0873F, 0.5236F));
    PartDefinition gun_1 = head.addOrReplaceChild("gun_1", CubeListBuilder.create().texOffs(56, 26).addBox(-3.0F, -2.0F, -11.0F, 6.0F, 4.0F, 7.0F, new CubeDeformation(0.0F))
            .texOffs(66, 4).addBox(-2.0F, -3.0F, -11.0F, 4.0F, 6.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition gun_hole_right_r1 = gun_1.addOrReplaceChild("gun_hole_right_r1", CubeListBuilder.create().texOffs(40, 63).addBox(-0.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(60, 15).addBox(5.5F, -0.5F, -1.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-3.0F, -1.0F, -7.5F, 0.0873F, 0.0F, 0.0F));
    PartDefinition gun_2_axis = gun_1.addOrReplaceChild("gun_2_axis", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, -11.0F));
    PartDefinition gun_2 = gun_2_axis.addOrReplaceChild("gun_2", CubeListBuilder.create().texOffs(55, 38).addBox(-2.0F, -2.5F, 0.0F, 4.0F, 5.0F, 9.0F, new CubeDeformation(0.0F))
            .texOffs(53, 54).addBox(-2.5F, -2.0F, 0.0F, 5.0F, 4.0F, 9.0F, new CubeDeformation(0.0F))
            .texOffs(17, 10).addBox(-0.5F, -2.4F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F))
            .texOffs(17, 10).addBox(-0.5F, 1.4F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -9.0F));
    PartDefinition cannon_6_r1 = gun_2.addOrReplaceChild("cannon_6_r1", CubeListBuilder.create().texOffs(17, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.732F, -1.0F, 0.0F, 0.0F, 0.0F, -1.0472F));
    PartDefinition cannon_5_r1 = gun_2.addOrReplaceChild("cannon_5_r1", CubeListBuilder.create().texOffs(17, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-1.732F, 1.0F, 0.0F, 0.0F, 0.0F, -2.0944F));
    PartDefinition cannon_3_r1 = gun_2.addOrReplaceChild("cannon_3_r1", CubeListBuilder.create().texOffs(17, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.82F, 1.0F, 0.0F, 0.0F, 0.0F, 2.0944F));
    PartDefinition cannon_2_r1 = gun_2.addOrReplaceChild("cannon_2_r1", CubeListBuilder.create().texOffs(17, 10).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 16.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.82F, -1.0F, 0.0F, 0.0F, 0.0F, 1.0472F));
    PartDefinition leg_front_left_1 = hips.addOrReplaceChild("leg_front_left_1", CubeListBuilder.create().texOffs(36, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(33, 2).addBox(-0.5F, 4.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, -2.0F, 0.0F, -0.7854F, 0.0F));
    PartDefinition leg_front_left_2 = leg_front_left_1.addOrReplaceChild("leg_front_left_2", CubeListBuilder.create().texOffs(73, 58).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
    PartDefinition leg_front_left_3 = leg_front_left_2.addOrReplaceChild("leg_front_left_3", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.0F));
    PartDefinition leg_front_left_4 = leg_front_left_3.addOrReplaceChild("leg_front_left_4", CubeListBuilder.create().texOffs(73, 53).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -1.0F));
    PartDefinition leg_front_left_5 = leg_front_left_4.addOrReplaceChild("leg_front_left_5", CubeListBuilder.create().texOffs(72, 68).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(17, 5).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12).addBox(-1.0F, -2.5F, -4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(73, 38).addBox(-1.0F, 4.0F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(9, 0).addBox(-0.5F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
    PartDefinition leg_front_right_1 = hips.addOrReplaceChild("leg_front_right_1", CubeListBuilder.create().texOffs(36, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(33, 2).addBox(-0.5F, 4.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, -2.0F, 0.0F, 0.7854F, 0.0F));
    PartDefinition leg_front_right_2 = leg_front_right_1.addOrReplaceChild("leg_front_right_2", CubeListBuilder.create().texOffs(73, 58).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
    PartDefinition leg_front_right_3 = leg_front_right_2.addOrReplaceChild("leg_front_right_3", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.0F));
    PartDefinition leg_front_right_4 = leg_front_right_3.addOrReplaceChild("leg_front_right_4", CubeListBuilder.create().texOffs(73, 53).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -1.0F));
    PartDefinition leg_front_right_5 = leg_front_right_4.addOrReplaceChild("leg_front_right_5", CubeListBuilder.create().texOffs(72, 68).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(17, 5).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12).addBox(-1.0F, -2.5F, -4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(73, 38).addBox(-1.0F, 4.0F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(9, 0).addBox(-0.5F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
    PartDefinition leg_back_left_1 = hips.addOrReplaceChild("leg_back_left_1", CubeListBuilder.create().texOffs(36, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(33, 2).addBox(-0.5F, 4.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(2.0F, 4.0F, 2.0F, 0.0F, -2.3562F, 0.0F));
    PartDefinition leg_back_left_2 = leg_back_left_1.addOrReplaceChild("leg_back_left_2", CubeListBuilder.create().texOffs(73, 58).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
    PartDefinition leg_back_left_3 = leg_back_left_2.addOrReplaceChild("leg_back_left_3", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.0F));
    PartDefinition leg_back_left_4 = leg_back_left_3.addOrReplaceChild("leg_back_left_4", CubeListBuilder.create().texOffs(73, 53).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -1.0F));
    PartDefinition leg_back_left_5 = leg_back_left_4.addOrReplaceChild("leg_back_left_5", CubeListBuilder.create().texOffs(72, 68).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(17, 5).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12).addBox(-1.0F, -2.5F, -4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(73, 38).addBox(-1.0F, 4.0F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(9, 0).addBox(-0.5F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));
    PartDefinition leg_back_right_1 = hips.addOrReplaceChild("leg_back_right_1", CubeListBuilder.create().texOffs(36, 11).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(33, 2).addBox(-0.5F, 4.5F, -3.0F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-2.0F, 4.0F, 2.0F, 0.0F, 2.3562F, 0.0F));
    PartDefinition leg_back_right_2 = leg_back_right_1.addOrReplaceChild("leg_back_right_2", CubeListBuilder.create().texOffs(73, 58).addBox(-1.0F, -1.0F, -2.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0F, -2.0F));
    PartDefinition leg_back_right_3 = leg_back_right_2.addOrReplaceChild("leg_back_right_3", CubeListBuilder.create().texOffs(0, 26).addBox(-0.5F, -3.0F, -1.5F, 1.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -0.5F, -2.0F));
    PartDefinition leg_back_right_4 = leg_back_right_3.addOrReplaceChild("leg_back_right_4", CubeListBuilder.create().texOffs(73, 53).addBox(-1.0F, -1.0F, -1.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, -1.0F));
    PartDefinition leg_back_right_5 = leg_back_right_4.addOrReplaceChild("leg_back_right_5", CubeListBuilder.create().texOffs(72, 68).addBox(-0.5F, -2.5F, -2.5F, 1.0F, 6.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(17, 5).addBox(-2.0F, -2.0F, -4.5F, 4.0F, 7.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-1.0F, -2.0F, -5.5F, 2.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(0, 12).addBox(-1.0F, -2.5F, -4.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(73, 38).addBox(-1.0F, 4.0F, -4.5F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
            .texOffs(9, 0).addBox(-0.5F, 6.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -1.0F));

    return LayerDefinition.create(meshdefinition, 128, 128);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void prepareMobModel(GunnerTurretEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    this.bodyPitch = entity.getRollAmount(ageInTicks);
  }

  @Override
  public void setupAnim(@NotNull GunnerTurretEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    float partialTicks = ageInTicks - entity.tickCount;
    this.resetBodyParts(entity);

    this.getLookAnim(netHeadYaw, headPitch);
    this.getBuriedPosition(entity);
    this.getWalkAnim(limbSwing, limbSwingAmount);
    this.getGunAnim(entity, partialTicks);
  }

  private void getLookAnim(float headYaw, float headPitch) {
    this.head.xRot = headPitch * 0.0139626348F;
    this.spout.yRot = headYaw * (Mth.PI / 180);
  }

  private void getWalkAnim(float limbSwing, float limbSwingAmount) {
    this.spout.xRot = 0 + Mth.cos(limbSwing * 0.3F) * 1.0F * limbSwingAmount;
    this.bandolier_left.zRot = 0 + Mth.cos(limbSwing * 0.3F) * 1.0F * limbSwingAmount;
    this.bandolier_right.zRot = 0 - Mth.cos(limbSwing * 0.3F) * 1.0F * limbSwingAmount;
    this.leg_front_left_1.yRot = -0.7854F + Mth.cos(limbSwing * 0.25F) * 1.0F * limbSwingAmount;
    this.leg_front_right_1.yRot = 0.7854F + Mth.cos(limbSwing * 0.25F) * 1.0F * limbSwingAmount;
    this.leg_back_left_1.yRot = -2.3562F - Mth.cos(limbSwing * 0.25F) * 1.0F * limbSwingAmount;
    this.leg_back_right_1.yRot = 2.3562F - Mth.cos(limbSwing * 0.25F) * 1.0F * limbSwingAmount;
  }

  private void getBuriedPosition(GunnerTurretEntity entity) {
    if (this.bodyPitch > 0.0F && !entity.isTame()) {
      this.core.y = 13.0F;
      this.core.xRot = ModelUtils.rotlerpRad(this.core.xRot, -0.261799F, this.bodyPitch);
      this.core.zRot = ModelUtils.rotlerpRad(this.core.zRot, 0.261799F, this.bodyPitch);
      this.head_axis.xRot = ModelUtils.rotlerpRad(this.head_axis.xRot, 0.349066F, this.bodyPitch);
      this.head_axis.yRot = ModelUtils.rotlerpRad(this.head_axis.yRot, -0.436332F, this.bodyPitch);
      this.leg_front_left_1.xRot = ModelUtils.rotlerpRad(this.leg_front_left_1.xRot, -0.174533F, this.bodyPitch);
      this.leg_front_left_2.zRot = ModelUtils.rotlerpRad(this.leg_front_left_2.zRot, 0.261799F, this.bodyPitch);
      this.leg_front_right_1.xRot = ModelUtils.rotlerpRad(this.leg_front_right_1.xRot, 0.174533F, this.bodyPitch);
      this.leg_back_left_2.xRot = ModelUtils.rotlerpRad(this.leg_back_left_2.xRot, -0.523599F, this.bodyPitch);
      this.leg_back_left_3.zRot = ModelUtils.rotlerpRad(this.leg_back_left_3.zRot, 1.39626F, this.bodyPitch);
      this.leg_back_right_1.xRot = ModelUtils.rotlerpRad(this.leg_back_right_1.xRot, -0.174533F, this.bodyPitch);
      this.leg_back_right_2.zRot = ModelUtils.rotlerpRad(this.leg_back_right_2.xRot, -0.174533F, this.bodyPitch);
    } else if (entity.isTame()) {
      if (entity.isInSittingPose()) {
        this.core.y = 9F;
        this.head_axis.xRot = 0.523599F;
        this.leg_front_left_2.xRot = 0;
        this.leg_front_right_2.xRot = 0;
        this.leg_back_left_2.xRot = 0;
        this.leg_back_right_2.xRot = 0;
      } else {
        this.core.y = 7.5F;
        this.head_axis.xRot = 0;
        this.leg_front_left_2.xRot = 0.261799F;
        this.leg_front_right_2.xRot = 0.261799F;
        this.leg_back_left_2.xRot = 0.261799F;
        this.leg_back_right_2.xRot = 0.261799F;
      }
      this.head_axis.yRot = 0;
      this.leg_front_left_1.xRot = 0;
      this.leg_front_left_2.zRot = 0;
      this.leg_front_right_1.xRot = 0;
      this.leg_back_left_3.zRot = 0;
      this.leg_back_right_1.xRot = 0;
      this.leg_back_right_2.zRot = 0;
    }
  }

  private void resetBodyParts(@NotNull GunnerTurretEntity entity) {
    this.core.xRot = 0;
    this.core.zRot = 0;
    this.head_axis.xRot = 0;
    this.head_axis.yRot = 0;
    this.leg_front_left_1.xRot = 0;
    this.leg_front_left_2.zRot = 0;
    this.leg_front_right_1.xRot = 0;
    this.leg_back_left_3.zRot = 0;
    this.leg_back_right_1.xRot = 0;
    this.leg_back_right_2.zRot = 0;
  }

  private void getGunAnim(GunnerTurretEntity entity, float partialTicks) {
    float speed = entity.getGunSpeed();
    float angle = entity.getGunAngle();
    float prevAngle = entity.getPrevGunAngle();
    float interpolatedAngle = prevAngle + (angle - prevAngle) * partialTicks;
    if (speed == 0) {
      this.gun_2_axis.zRot = 0.785398F + angle;
    } else {
      this.gun_2_axis.zRot = 0.785398F + interpolatedAngle;
    }
  }
}

package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.ModelUtils;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class GunnerDroneModel<T extends GunnerDroneEntity> extends EntityModel<T> {
  protected final ModelPart core;
  protected ModelPart eye;
  protected ModelPart eye_ring;
  protected ModelPart top_case;
  protected ModelPart lower_case;
  protected ModelPart eye_ring_left;
  protected ModelPart eye_ring_right;
  protected ModelPart eye_ring_front;
  protected ModelPart eye_ring_back;
  protected ModelPart top_case_left;
  protected ModelPart top_case_right;
  protected ModelPart top_case_front_1_left;
  protected ModelPart top_case_front_1_right;
  protected ModelPart top_case_front_2;
  protected ModelPart top_case_back;
  protected ModelPart top_case_top;
  protected ModelPart top_case_propeller;
  protected ModelPart propeller_rod;
  protected ModelPart wing_left;
  protected ModelPart wing_left_2;
  protected ModelPart wing_right;
  protected ModelPart wing_right_2;
  protected ModelPart wing_front;
  protected ModelPart wing_front_2;
  protected ModelPart wing_back_2;
  protected ModelPart wing_back;
  protected ModelPart lower_case_left;
  protected ModelPart lower_case_right;
  protected ModelPart lower_case_front;
  protected ModelPart lower_case_back;
  protected ModelPart gun_yaw;
  protected ModelPart gun_pitch_axis;
  protected ModelPart gun_pitch;
  protected ModelPart gun_pitch_2;
  private float bodyPitch;

  public GunnerDroneModel(ModelPart root) {
    this.core = root.getChild("core");
    this.eye = core.getChild("eye");
    this.eye_ring = eye.getChild("eye_ring");
    this.top_case = eye.getChild("top_case");
    this.lower_case = eye.getChild("lower_case");
    this.eye_ring_right = eye_ring.getChild("eye_ring_right");
    this.eye_ring_front = eye_ring.getChild("eye_ring_front");
    this.eye_ring_left = eye_ring.getChild("eye_ring_left");
    this.eye_ring_back = eye_ring.getChild("eye_ring_back");
    this.top_case_propeller = top_case.getChild("top_case_propeller");
    this.lower_case_right = lower_case.getChild("lower_case_right");
    this.gun_yaw = lower_case.getChild("gun_yaw");
    this.gun_pitch_axis = gun_yaw.getChild("gun_pitch_axis");
    this.lower_case_front = lower_case.getChild("lower_case_front");
    this.lower_case_back = lower_case.getChild("lower_case_back");
    this.lower_case_left = lower_case.getChild("lower_case_left");
    this.gun_pitch = gun_pitch_axis.getChild("gun_pitch");
    this.gun_pitch_2 = gun_pitch.getChild("gun_pitch_2");
    this.propeller_rod = top_case_propeller.getChild("propeller_rod");
    this.wing_left = propeller_rod.getChild("wing_left");
    this.wing_left_2 = wing_left.getChild("wing_left_2");
    this.wing_right = propeller_rod.getChild("wing_right");
    this.wing_right_2 = wing_right.getChild("wing_right_2");
    this.wing_front = propeller_rod.getChild("wing_front");
    this.wing_front_2 = wing_front.getChild("wing_front_2");
    this.wing_back = propeller_rod.getChild("wing_back");
    this.wing_back_2 = wing_back.getChild("wing_front_2_1");
    this.top_case_left = top_case.getChild("top_case_left");
    this.top_case_right = top_case.getChild("top_case_right");
    this.top_case_front_1_left = top_case.getChild("top_case_front_1_left");
    this.top_case_front_1_right = top_case.getChild("top_case_front_1_right");
    this.top_case_front_2 = top_case.getChild("top_case_front_2");
    this.top_case_back = top_case.getChild("top_case_back");
    this.top_case_top = top_case.getChild("top_case_top");
  }

  public static LayerDefinition createBodyLayer() {
    var mesh = new MeshDefinition();
    PartDefinition parts = mesh.getRoot();

    PartDefinition core = parts.addOrReplaceChild("core", CubeListBuilder.create().texOffs(1, 35).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));
    PartDefinition eye = core.addOrReplaceChild("eye", CubeListBuilder.create().texOffs(10, 31).addBox(-2.0F, -1.0F, -1.75F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.0F));
    PartDefinition eye_ring = eye.addOrReplaceChild("eye_ring", CubeListBuilder.create().texOffs(20, 40).addBox(-4.0F, -1.0F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 3.0F));
    PartDefinition top_case = eye.addOrReplaceChild("top_case", CubeListBuilder.create().texOffs(20, 17).addBox(-4.0F, -2.5F, -4.0F, 8.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.5F, 3.0F));
    PartDefinition lower_case = eye.addOrReplaceChild("lower_case", CubeListBuilder.create().texOffs(20, 51).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.5F, 3.0F));
    PartDefinition eye_ring_right = eye_ring.addOrReplaceChild("eye_ring_right", CubeListBuilder.create().texOffs(53, 40).addBox(-1.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.0F, 0.0F));
    PartDefinition eye_ring_left = eye_ring.addOrReplaceChild("eye_ring_left", CubeListBuilder.create().texOffs(1, 40).addBox(0.0F, -1.0F, -4.0F, 1.0F, 2.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 0.0F, 0.0F));
    PartDefinition eye_ring_front = eye_ring.addOrReplaceChild("eye_ring_front", CubeListBuilder.create().texOffs(72, 47).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.0F));
    PartDefinition eye_ring_back = eye_ring.addOrReplaceChild("eye_ring_back", CubeListBuilder.create().texOffs(91, 47).addBox(-4.0F, -1.0F, -0.5F, 8.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.0F));
    PartDefinition top_case_propeller = top_case.addOrReplaceChild("top_case_propeller", CubeListBuilder.create().texOffs(34, 12).addBox(-1.5F, -0.5F, -1.5F, 3.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.0F));
    PartDefinition lower_case_right = lower_case.addOrReplaceChild("lower_case_right", CubeListBuilder.create().texOffs(53, 52).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.0F, 0.0F));
    PartDefinition gun_yaw = lower_case.addOrReplaceChild("gun_yaw", CubeListBuilder.create().texOffs(1, 63).addBox(-1.0F, 0.0F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 1.5F, 0.0F));
    PartDefinition gun_pitch_axis = gun_yaw.addOrReplaceChild("gun_pitch_axis", CubeListBuilder.create().texOffs(10, 67).addBox(-0.5F, -0.5F, -0.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 3.0F, 0.5F));
    PartDefinition lower_case_front = lower_case.addOrReplaceChild("lower_case_front", CubeListBuilder.create().texOffs(72, 59).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -4.5F));
    PartDefinition lower_case_back = lower_case.addOrReplaceChild("lower_case_back", CubeListBuilder.create().texOffs(91, 59).addBox(-4.0F, -0.5F, -0.5F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 4.5F));
    PartDefinition lower_case_left = lower_case.addOrReplaceChild("lower_case_left", CubeListBuilder.create().texOffs(1, 52).addBox(-0.5F, -0.5F, -4.0F, 1.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 0.0F, 0.0F));
    PartDefinition gun_pitch = gun_pitch_axis.addOrReplaceChild("gun_pitch", CubeListBuilder.create().texOffs(15, 62).addBox(-0.5F, -0.5F, -5.0F, 1.0F, 1.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.7853982F));
    PartDefinition gun_pitch_2 = gun_pitch.addOrReplaceChild("gun_pitch_2", CubeListBuilder.create().texOffs(30, 63).addBox(-1.0F, -1.0F, -2.5F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, -3.5F));
    PartDefinition propeller_rod = top_case_propeller.addOrReplaceChild("propeller_rod", CubeListBuilder.create().texOffs(47, 10).addBox(-0.5F, -5.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -0.5F, 0.0F, 0.0F, 0.7853982F, 0.0F));
    PartDefinition wing_left = propeller_rod.addOrReplaceChild("wing_left", CubeListBuilder.create().texOffs(1, 5).addBox(-1.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.25F, -4.0F, 0.0F));
    PartDefinition wing_left_2 = wing_left.addOrReplaceChild("wing_left_2", CubeListBuilder.create().texOffs(3, 4).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.5F, 0.0F, 0.5F));
    PartDefinition wing_right = propeller_rod.addOrReplaceChild("wing_right", CubeListBuilder.create().texOffs(15, 5).addBox(0.0F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.25F, -4.0F, 0.0F));
    PartDefinition wing_right_2 = wing_right.addOrReplaceChild("wing_right_2", CubeListBuilder.create().texOffs(17, 4).addBox(-2.5F, 0.0F, -1.0F, 5.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, 0.0F, -0.5F));
    PartDefinition wing_front = propeller_rod.addOrReplaceChild("wing_front", CubeListBuilder.create().texOffs(29, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, -0.75F));
    PartDefinition wing_front_2 = wing_front.addOrReplaceChild("wing_front_2", CubeListBuilder.create().texOffs(28, 1).addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, 0.0F, -3.0F));
    PartDefinition wing_back = propeller_rod.addOrReplaceChild("wing_back", CubeListBuilder.create().texOffs(37, 5).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 0.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -4.0F, 0.75F));
    PartDefinition wing_back_2 = wing_back.addOrReplaceChild("wing_front_2_1", CubeListBuilder.create().texOffs(36, 1).addBox(-1.0F, 0.0F, -2.5F, 2.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 0.0F, 3.0F));
    PartDefinition top_case_left = top_case.addOrReplaceChild("top_case_left", CubeListBuilder.create().texOffs(1, 17).addBox(-0.5F, -2.5F, -4.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.5F, 0.0F, 0.0F));
    PartDefinition top_case_right = top_case.addOrReplaceChild("top_case_right", CubeListBuilder.create().texOffs(53, 17).addBox(-0.5F, -2.5F, -4.0F, 1.0F, 5.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, 0.0F, 0.0F));
    PartDefinition top_case_front_1_left = top_case.addOrReplaceChild("top_case_front_1_left", CubeListBuilder.create().texOffs(72, 27).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 1.5F, -4.5F));
    PartDefinition top_case_front_1_right = top_case.addOrReplaceChild("top_case_front_1_right", CubeListBuilder.create().texOffs(84, 27).addBox(-1.0F, -1.0F, -0.5F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 1.5F, -4.5F));
    PartDefinition top_case_front_2 = top_case.addOrReplaceChild("top_case_front_2", CubeListBuilder.create().texOffs(72, 22).addBox(-4.0F, -1.5F, -0.5F, 8.0F, 3.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, -4.5F));
    PartDefinition top_case_back = top_case.addOrReplaceChild("top_case_back", CubeListBuilder.create().texOffs(91, 24).addBox(-4.0F, -1.5F, -0.5F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -1.0F, 4.5F));
    PartDefinition top_case_top = top_case.addOrReplaceChild("top_case_top", CubeListBuilder.create().texOffs(1, 7).addBox(-4.0F, -0.5F, -4.0F, 8.0F, 1.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -3.0F, 0.0F));

    return LayerDefinition.create(mesh, 112, 72);
  }

  @Override
  public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void setupAnim(GunnerDroneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
    this.core.xRot = 0.0F;
    this.core.zRot = 0.0F;
    this.eye.xRot = 0.0F;
    this.gun_pitch_axis.xRot = headPitch * ((float) Mth.PI / 180F);
    this.gun_yaw.yRot = netHeadYaw * ((float) Mth.PI / 180F);

    if (entity.isTame() && entity.isFlying() && !entity.isInSittingPose()) {
      float f1 = ageInTicks * (float) Mth.PI * 0.3F;
      this.eye.y = (Mth.cos(ageInTicks * 0.18F) * 0.9F);
      this.propeller_rod.yRot = f1;
    } else if (entity.isOnGround()) {
      this.eye.y = 0;
      this.propeller_rod.yRot = 0.7853981633974483F;
    }
    //Buried position
    if (this.bodyPitch > 0.0F && !entity.isTame()) {
      this.core.zRot = ModelUtils.rotlerpRad(this.core.zRot, 0.2617993877991494F, this.bodyPitch);
      this.core.xRot = ModelUtils.rotlerpRad(this.core.zRot, -0.2617993877991494F, this.bodyPitch);
      this.core.y = (float) 23.5;
    } else if (entity.isOnGround() && entity.isTame()) {
      this.core.y = 18.75F;
    } else {
      this.core.y = 15.0F;
    }
    //Moving inclination
    if (this.bodyPitch > 0.0F && entity.isDroneMoving()) {
      float f1 = Mth.cos(ageInTicks * 0.18F);
      this.eye.xRot = 0.1F + f1 * (float) Math.PI * 0.025F;
    }
  }

  @Override
  public void prepareMobModel(GunnerDroneEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks) {
    //super.prepareMobModel(entity, limbSwing, limbSwingAmount, ageInTicks);
    this.bodyPitch = entity.getRollAmount(ageInTicks);
  }
}

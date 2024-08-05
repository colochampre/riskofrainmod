package io.github.colochampre.riskofrain_mobs.client.models;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import io.github.colochampre.riskofrain_mobs.entities.enemies.WispEntity;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class WispModel<T extends WispEntity> extends EntityModel<T> {
  protected final ModelPart core;
  private final ModelPart mask;

  private float bodyXRot;
  private float bodyZRot;

  public WispModel(ModelPart root) {
    this.core = root.getChild("core");
    this.mask = core.getChild("mask");
  }

  public static LayerDefinition createBodyLayer() {
    MeshDefinition meshdefinition = new MeshDefinition();
    PartDefinition partdefinition = meshdefinition.getRoot();

    PartDefinition core = partdefinition.addOrReplaceChild("core", CubeListBuilder.create().texOffs(0, 54).addBox(-1.0F, -1.0F, -1.0F, 2.0F, 2.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 17.0F, 0.0F));
    PartDefinition mask = core.addOrReplaceChild("mask", CubeListBuilder.create().texOffs(38, 37).addBox(-3.0F, -1.0F, -9.0F, 6.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
            .texOffs(0, 11).addBox(-8.0F, -5.0F, -9.0F, 16.0F, 4.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(0, 37).addBox(6.0F, -5.0F, -6.0F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(66, 20).addBox(-8.0F, -5.0F, -6.0F, 2.0F, 10.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(76, 11).addBox(-8.0F, 3.0F, -9.0F, 16.0F, 2.0F, 3.0F, new CubeDeformation(0.0F))
            .texOffs(45, 0).addBox(-7.0F, -7.0F, -8.0F, 14.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 0).addBox(-7.0F, 5.0F, -8.0F, 14.0F, 2.0F, 8.0F, new CubeDeformation(0.0F))
            .texOffs(0, 20).addBox(-5.0F, -10.0F, -6.0F, 10.0F, 3.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(33, 20).addBox(-5.0F, 7.0F, -6.0F, 10.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(39, 11).addBox(-6.0F, -9.0F, -7.0F, 12.0F, 2.0F, 6.0F, new CubeDeformation(0.0F))
            .texOffs(17, 43).addBox(-3.0F, -11.0F, -4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
            .texOffs(17, 37).addBox(-3.0F, 8.0F, -4.0F, 6.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

    return LayerDefinition.create(meshdefinition, 128, 128);
  }

  @Override
  public void renderToBuffer(@NotNull PoseStack poseStack, @NotNull VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
    core.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
  }

  @Override
  public void prepareMobModel(WispEntity entity, float p_102615_, float p_102616_, float p_102617_) {
    this.bodyXRot = entity.getBodyXRot();
    this.bodyZRot = entity.getBodyZRot();
  }

  @Override
  public void setupAnim(@NotNull WispEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float headYaw, float headPitch) {
    this.mask.xRot = 0.0F;
    this.mask.zRot = 0.0F;
    this.getLookAnim(headYaw, headPitch);
    this.getFlyingAnim(entity, ageInTicks);
  }

  private void getLookAnim(float headYaw, float headPitch) {
    this.mask.xRot = headPitch * 0.023271058F / 2;
    this.mask.yRot = headYaw * 0.017453292F / 2;
  }

  private void getFlyingAnim(WispEntity entity, float ageInTicks) {
    if (entity.isFlying()) {
      // Up and down movement
      this.mask.y = (Mth.cos(ageInTicks * 0.18F) * 0.9F);
      // Forward-backward and sides inclination
      this.mask.xRot = -this.bodyXRot;
      this.mask.zRot = -this.bodyZRot;
    } else if (entity.onGround()) {
      this.mask.y = 0;
    }
  }
}
package io.github.colochampre.riskofrain_mobs.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.GunnerTurretModel;
import io.github.colochampre.riskofrain_mobs.client.renderer.layers.GunnerTurretBodyLayer;
import io.github.colochampre.riskofrain_mobs.client.renderer.layers.GunnerTurretEyeLayer;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import io.github.colochampre.riskofrain_mobs.events.ModClientEvents;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import org.jetbrains.annotations.NotNull;

@OnlyIn(Dist.CLIENT)
public class GunnerTurretRenderer extends MobRenderer<GunnerTurretEntity, GunnerTurretModel<GunnerTurretEntity>> {
  private static final ResourceLocation DEFAULT_TEXTURE = ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "textures/entity/gunner_turret/gunner_turret_default.png");

  public GunnerTurretRenderer(EntityRendererProvider.Context context) {
    super(context, new GunnerTurretModel<>(context.bakeLayer(ModClientEvents.GUNNER_TURRET_LAYER)), 0.40F);
    this.addLayer(new GunnerTurretBodyLayer(this));
    this.addLayer(new GunnerTurretEyeLayer(this));
  }

  @Override
  public void render(@NotNull GunnerTurretEntity entity, float entityYaw, float partialTicks, PoseStack poseStack, @NotNull MultiBufferSource buffer, int packedLight) {
    poseStack.pushPose();
    float scale = 0.80F;
    poseStack.scale(scale, scale, scale);
    super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight);
    poseStack.popPose();
  }

  @Override
  public @NotNull ResourceLocation getTextureLocation(@NotNull GunnerTurretEntity entity) {
    return DEFAULT_TEXTURE;
  }
}

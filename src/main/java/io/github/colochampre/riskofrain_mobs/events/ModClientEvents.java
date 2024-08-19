package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.*;
import io.github.colochampre.riskofrain_mobs.client.renderer.*;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
  public static final ModelLayerLocation BEETLE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "beetle_entity"), "main");
  public static final ModelLayerLocation LEMURIAN_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "lemurian_entity"), "main");
  public static final ModelLayerLocation STONE_GOLEM_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "stone_golem_entity"), "main");
  public static final ModelLayerLocation WISP_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "wisp_entity"), "main");
  public static final ModelLayerLocation GUNNER_DRONE_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "gunner_drone_entity"), "main");
  public static final ModelLayerLocation GUNNER_TURRET_LAYER = new ModelLayerLocation(ResourceLocation.fromNamespaceAndPath(RoRmod.MODID, "gunner_turret_entity"), "main");

  @SubscribeEvent
  public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(EntityInit.BEETLE_ENTITY.get(), BeetleRenderer::new);
    event.registerEntityRenderer(EntityInit.LEMURIAN_ENTITY.get(), LemurianRenderer::new);
    event.registerEntityRenderer(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemRenderer::new);
    event.registerEntityRenderer(EntityInit.WISP_ENTITY.get(), WispRenderer::new);
    event.registerEntityRenderer(EntityInit.GUNNER_DRONE_ENTITY.get(), GunnerDroneRenderer::new);
    event.registerEntityRenderer(EntityInit.GUNNER_TURRET_ENTITY.get(), GunnerTurretRenderer::new);
    event.registerEntityRenderer(EntityInit.DRONE_BULLET_ENTITY.get(), BulletRenderer::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions events) {
    events.registerLayerDefinition(BEETLE_LAYER, BeetleModel::createBodyLayer);
    events.registerLayerDefinition(LEMURIAN_LAYER, LemurianModel::createBodyLayer);
    events.registerLayerDefinition(STONE_GOLEM_LAYER, StoneGolemModel::createBodyLayer);
    events.registerLayerDefinition(WISP_LAYER, WispModel::createBodyLayer);
    events.registerLayerDefinition(GUNNER_DRONE_LAYER, GunnerDroneModel::createBodyLayer);
    events.registerLayerDefinition(GUNNER_TURRET_LAYER, GunnerTurretModel::createBodyLayer);
  }
}

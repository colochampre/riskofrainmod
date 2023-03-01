package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.client.models.LemurianModel;
import io.github.colochampre.riskofrain_mobs.client.models.StoneGolemModel;
import io.github.colochampre.riskofrain_mobs.client.renderer.LemurianRenderer;
import io.github.colochampre.riskofrain_mobs.client.renderer.StoneGolemRenderer;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModClientEvents {
  public static final ModelLayerLocation LEMURIAN_LAYER = new ModelLayerLocation(new ResourceLocation(RoRmod.MODID, "lemurian_entity"), "main");

  @SubscribeEvent
  public static void entityRenderers(EntityRenderersEvent.RegisterRenderers event) {
    event.registerEntityRenderer(EntityInit.LEMURIAN_ENTITY.get(), LemurianRenderer::new);
    event.registerEntityRenderer(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemRenderer::new);
  }

  @SubscribeEvent
  public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions events) {
    events.registerLayerDefinition(LEMURIAN_LAYER, LemurianModel::createBodyLayer);
    events.registerLayerDefinition(StoneGolemModel.LAYER_LOCATION, StoneGolemModel::createBodyLayer);
  }
}

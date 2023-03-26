package com.elcolomanco.riskofrainmod.setup;

import java.util.function.Supplier;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.client.renderer.GunnerDroneRenderer;
import com.elcolomanco.riskofrainmod.client.renderer.LemurianRenderer;
import com.elcolomanco.riskofrainmod.client.renderer.StoneGolemRenderer;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.ItemRenderer;
import net.minecraft.client.renderer.entity.SpriteRenderer;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.client.registry.RenderingRegistry;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {
	
	@SubscribeEvent
	public static void init(FMLClientSetupEvent event) {
		RenderingRegistry.registerEntityRenderingHandler(RegistrySetup.GUNNER_DRONE.get(), GunnerDroneRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(RegistrySetup.LEMURIAN.get(), LemurianRenderer::new);
		RenderingRegistry.registerEntityRenderingHandler(RegistrySetup.STONE_GOLEM.get(), StoneGolemRenderer::new);
		registerEntityModels(event.getMinecraftSupplier());
	}
	
	private static void registerEntityModels(Supplier<Minecraft> minecraft) {
		ItemRenderer renderer = minecraft.get().getItemRenderer();
		RenderingRegistry.registerEntityRenderingHandler(RegistrySetup.DRONE_BULLET_ENTITY.get(), (renderManager) -> new SpriteRenderer<>(renderManager, renderer));
	}
	
	@SubscribeEvent
    public static void onItemColor(ColorHandlerEvent.Item event) {
        event.getItemColors().register((stack, i) -> 0x6c537c, RegistrySetup.LEMURIAN_SPAWN_EGG.get());
        event.getItemColors().register((stack, i) -> 0x545172, RegistrySetup.STONE_GOLEM_SPAWN_EGG.get());
        event.getItemColors().register((stack, i) -> 0x017cdc, RegistrySetup.GUNNER_DRONE_SPAWN_EGG.get());
    }
}

package com.elcolomanco.riskofrainmod.setup;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.config.ConfigHolder;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigSetup {
	
	@SubscribeEvent
	public static void onModConfigEvent(final ModConfig.ModConfigEvent event) {
		 final ModConfig config = event.getConfig();
		 // Rebake the configs when they change
		 if (config.getSpec() == ConfigHolder.SERVER_SPEC) {
			 RoRconfig.bakeServer(config);
		 }
	 }
}

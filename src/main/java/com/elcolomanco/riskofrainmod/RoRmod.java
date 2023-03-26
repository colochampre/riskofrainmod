package com.elcolomanco.riskofrainmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.elcolomanco.riskofrainmod.config.ConfigHolder;
import com.elcolomanco.riskofrainmod.entities.SpawnHandler;
import com.elcolomanco.riskofrainmod.setup.ModSetup;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

@Mod(RoRmod.MODID)
public class RoRmod {
	
	public static final String MODID = "riskofrainmod";
	public static final Logger LOGGER = LogManager.getLogger();
	
	public RoRmod() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(ModSetup::init);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHolder.SERVER_SPEC);
		MinecraftForge.EVENT_BUS.addListener(SpawnHandler::addEntitySpawns);
		RegistrySetup.init();
	}
}

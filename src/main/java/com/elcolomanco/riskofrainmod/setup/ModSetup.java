package com.elcolomanco.riskofrainmod.setup;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.entities.SpawnHandler;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModSetup {
	
	public static final ItemGroup RIKSOFRAIN_GROUP = new ItemGroup("riskofrain_group") {
		@Override
		public ItemStack createIcon() {
			return new ItemStack(RegistrySetup.COMMON_CHEST.get());
		}
	};
	
	@SuppressWarnings("deprecation")
	public static void init(final FMLCommonSetupEvent event) {
		net.minecraftforge.fml.DeferredWorkQueue.runLater(() -> {
			SpawnHandler.setSpawnPlacement();
		});
	}
}

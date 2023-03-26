package com.elcolomanco.riskofrainmod.setup;

import java.util.Set;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.google.common.collect.Sets;

import net.minecraft.util.ResourceLocation;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.TableLootEntry;
import net.minecraftforge.event.LootTableLoadEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRmod.MODID)
public class LootTablesSetup {
	
	private static final Set<ResourceLocation> ABANDONED_MINESHAFT_INJECTIONS	= Sets.newHashSet(LootTables.CHESTS_ABANDONED_MINESHAFT);
	private static final Set<ResourceLocation> BURIED_TREASURE_INJECTIONS		= Sets.newHashSet(LootTables.CHESTS_BURIED_TREASURE);
	private static final Set<ResourceLocation> DESERT_PYRAMID_INJECTIONS		= Sets.newHashSet(LootTables.CHESTS_DESERT_PYRAMID);
	private static final Set<ResourceLocation> JUNGLE_TEMPLE_INJECTIONS			= Sets.newHashSet(LootTables.CHESTS_JUNGLE_TEMPLE);
	private static final Set<ResourceLocation> PILLAGER_OUTPOST_INJECTIONS		= Sets.newHashSet(LootTables.CHESTS_PILLAGER_OUTPOST);
	private static final Set<ResourceLocation> SIMPLE_DUNGEON_INJECTIONS		= Sets.newHashSet(LootTables.CHESTS_SIMPLE_DUNGEON);
	private static final Set<ResourceLocation> UNDERWATER_RUIN_SMALL_INJECTIONS	= Sets.newHashSet(LootTables.CHESTS_UNDERWATER_RUIN_SMALL);
	private static final Set<ResourceLocation> UNDERWATER_RUIN_BIG_INJECTIONS	= Sets.newHashSet(LootTables.CHESTS_UNDERWATER_RUIN_BIG);
	private static final Set<ResourceLocation> WOODLAND_MANSION_INJECTIONS		= Sets.newHashSet(LootTables.CHESTS_WOODLAND_MANSION);
	
	private static final Set<ResourceLocation> BASTION_HOGLIN_STABLE_INJECTIONS	= Sets.newHashSet(LootTables.BASTION_HOGLIN_STABLE);
	private static final Set<ResourceLocation> BASTION_TREASURE_INJECTIONS		= Sets.newHashSet(LootTables.BASTION_TREASURE);
	private static final Set<ResourceLocation> NETHER_BRIDGE_INJECTIONS			= Sets.newHashSet(LootTables.CHESTS_NETHER_BRIDGE);
	private static final Set<ResourceLocation> RUINED_PORTAL_INJECTIONS			= Sets.newHashSet(LootTables.RUINED_PORTAL);
	
	private static final Set<ResourceLocation> END_CITY_TREASURE_INJECTIONS	= Sets.newHashSet(LootTables.CHESTS_END_CITY_TREASURE);

	@SubscribeEvent
	public static void onInjectLoot(LootTableLoadEvent event) {
		
		if(ABANDONED_MINESHAFT_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_abandoned_mineshaft"))).name("items_abandoned_mineshaft").build();
			event.getTable().addPool(pool);
		}
		if(BASTION_HOGLIN_STABLE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_bastion_hoglin_stable"))).name("items_bastion_hoglin_stable").build();
			event.getTable().addPool(pool);
		}
		if(BASTION_TREASURE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_bastion_treasure"))).name("items_bastion_treasure").build();
			event.getTable().addPool(pool);
		}
		if(BURIED_TREASURE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_buried_treasure"))).name("items_buried_treasure").build();
			event.getTable().addPool(pool);
		}
		if(DESERT_PYRAMID_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_desert_pyramid"))).name("items_desert_pyramid").build();
			event.getTable().addPool(pool);
		}
		if(END_CITY_TREASURE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_end_city_treasure"))).name("items_end_city_treasure").build();
			event.getTable().addPool(pool);
		}
		if(JUNGLE_TEMPLE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_jungle_temple"))).name("items_jungle_temple").build();
			event.getTable().addPool(pool);
		}
		if(NETHER_BRIDGE_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_nether_bridge"))).name("items_nether_bridge").build();
			event.getTable().addPool(pool);
		}
		if(PILLAGER_OUTPOST_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_pillager_outpost"))).name("items_pillager_outpost").build();
			event.getTable().addPool(pool);
		}
		if(RUINED_PORTAL_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_ruined_portal"))).name("items_ruined_portal").build();
			event.getTable().addPool(pool);
		}
		if(SIMPLE_DUNGEON_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_simple_dungeon"))).name("items_simple_dungeon").build();
			event.getTable().addPool(pool);
		}
		if(UNDERWATER_RUIN_BIG_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_underwater_ruin_big"))).name("items_underwater_ruin_big").build();
			event.getTable().addPool(pool);
		}
		if(UNDERWATER_RUIN_SMALL_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_underwater_ruin_small"))).name("items_underwater_ruin_small").build();
			event.getTable().addPool(pool);
		}
		if(WOODLAND_MANSION_INJECTIONS.contains(event.getName())) {
			LootPool pool = LootPool.builder().addEntry(TableLootEntry.builder(new ResourceLocation(RoRmod.MODID, "injections/items_woodland_mansion"))).name("items_woodland_mansion").build();
			event.getTable().addPool(pool);
		}
	}
}

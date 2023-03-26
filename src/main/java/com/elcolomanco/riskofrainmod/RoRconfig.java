package com.elcolomanco.riskofrainmod;

import java.util.List;

import com.elcolomanco.riskofrainmod.config.ConfigHolder;
import com.google.common.collect.Lists;

import net.minecraftforge.fml.config.ModConfig;

public class RoRconfig {
	
	public static boolean SPAWNABLE_GUNNER_DRONE = true;
	public static boolean SPAWNABLE_LEMURIAN = true;
	public static boolean SPAWNABLE_STONE_GOLEM = true;
	public static boolean DESPAWN_STONE_GOLEM = false;
	public static boolean LASER_KNOCKBACK = false;
	public static boolean NETHER_SPAWNABLE = true;
	public static int GUNNER_DRONE_SPAWN_RATE = 1;
	public static int LEMURIAN_SPAWN_RATE = 80;
	public static int STONE_GOLEM_SPAWN_RATE = 20;
	public static int INFUSION_CAP = 60;
	public static boolean SOUNDS = true;
	public static List<? extends String> OVERWORLD_SPAWN_BIOMES = Lists.newArrayList(
			"minecraft:badlands",
    		"minecraft:eroded_badlands",
    		"minecraft:badlands_plateau",
    		"minecraft:wooded_badlands_plateau",
    		"minecraft:modified_badlands_plateau",
    		"minecraft:modified_wooded_badlands_plateau",
    		
    		"minecraft:beach",
    		"minecraft:snowy_beach",
    		"minecraft:stone_shore",
    		
    		"minecraft:forest",
    		"minecraft:flower_forest",
    		"minecraft:birch_forest",
            "minecraft:birch_forest_hills",
    		"minecraft:tall_birch_forest",
    		"minecraft:dark_forest",
    		"minecraft:dark_forest_hills",
    		"minecraft:wooded_hills",
    		"minecraft:wooded_mountains",
    		
    		"minecraft:desert",
    		"minecraft:desert_lakes",
    		"minecraft:desert_hills",
    		
    		"minecraft:mountains",
    		"minecraft:mountain_edge",
    		"minecraft:gravelly_mountains",
    		"minecraft:modified_gravelly_mountains",
    		"minecraft:snowy_mountains",
            
            "minecraft:jungle",
            "minecraft:jungle_edge",
            "minecraft:jungle_hills",
            "minecraft:modified_jungle",
            "minecraft:bamboo_jungle",
            "minecraft:bamboo_jungle_hills",
            "minecraft:modified_jungle_edge",
            
            "minecraft:plains",
    		"minecraft:sunflower_plains",
            
            "minecraft:river",
    		"minecraft:frozen_river",
            
    		"minecraft:savanna",
    		"minecraft:savanna_plateau",
    		"minecraft:shattered_savanna",
    		"minecraft:shattered_savanna_plateau",
    		
    		"minecraft:swamp",
    		"minecraft:swamp_hills",
    		
            "minecraft:taiga",
            "minecraft:taiga_hills",
            "minecraft:taiga_mountains",
            "minecraft:giant_tree_taiga",
            "minecraft:giant_tree_taiga_hills",
            "minecraft:giant_spruce_taiga",
    		"minecraft:giant_spruce_taiga_hills",
    		"minecraft:snowy_taiga",
    		"minecraft:snowy_taiga_hills",
            "minecraft:snowy_taiga_mountains",
            "minecraft:snowy_tundra",
    		"minecraft:ice_spikes"
    );
	public static List<? extends String> NETHER_SPAWN_BIOMES = Lists.newArrayList(
			"minecraft:basalt_deltas",
    		"minecraft:crimson_forest",
			"minecraft:nether_wastes",
            "minecraft:warped_forest"
    );
	
	public static void bakeServer(final ModConfig config) {
		try {
			SPAWNABLE_GUNNER_DRONE = ConfigHolder.SERVER.SPAWNABLE_GUNNER_DRONE.get();
			SPAWNABLE_LEMURIAN = ConfigHolder.SERVER.SPAWNABLE_LEMURIAN.get();
			SPAWNABLE_STONE_GOLEM = ConfigHolder.SERVER.SPAWNABLE_STONE_GOLEM.get();
			DESPAWN_STONE_GOLEM = ConfigHolder.SERVER.DESPAWN_STONE_GOLEM.get();
			LASER_KNOCKBACK = ConfigHolder.SERVER.LASER_KNOCKBACK.get();
			NETHER_SPAWNABLE = ConfigHolder.SERVER.NETHER_SPAWNABLE.get();
			GUNNER_DRONE_SPAWN_RATE = ConfigHolder.SERVER.GUNNER_DRONE_SPAWN_RATE.get();
			LEMURIAN_SPAWN_RATE = ConfigHolder.SERVER.LEMURIAN_SPAWN_RATE.get();
			STONE_GOLEM_SPAWN_RATE = ConfigHolder.SERVER.STONE_GOLEM_SPAWN_RATE.get();
			OVERWORLD_SPAWN_BIOMES = ConfigHolder.SERVER.OVERWORLD_SPAWN_BIOMES.get();
			NETHER_SPAWN_BIOMES = ConfigHolder.SERVER.NETHER_SPAWN_BIOMES.get();
			INFUSION_CAP = ConfigHolder.SERVER.INFUSION_CAP.get();
			SOUNDS = ConfigHolder.SERVER.SOUNDS.get();
		} catch (Exception var2) {
			RoRmod.LOGGER.warn("An exception was caused trying to load the common config for Risk of Rain mod.");
			var2.printStackTrace();
		}
	}
}

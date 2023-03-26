package com.elcolomanco.riskofrainmod.config;

import java.util.List;

import com.google.common.collect.Lists;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;
import net.minecraftforge.common.ForgeConfigSpec.Builder;
import net.minecraftforge.common.ForgeConfigSpec.IntValue;

public class ServerConfig {
	
	public static final String CATEGORY_SPAWNABLES = "Allow entity spawns";
    public static final String CATEGORY_SPAWN_RATES = "Entity spawn rates";
    public static final String CATEGORY_WHITE_LISTED_OVERWORLD_SPAWN_BIOMES = "Withelisted overworld spawn biomes";
    public static final String CATEGORY_WHITE_LISTED_NETHER_SPAWN_BIOMES = "Withelisted nether spawn biomes";
    public static final String CATEGORY_ITEMS = "Item settings";
    public static final String CATEGORY_SOUNDS = "Sound settings";
	
	public final ForgeConfigSpec.BooleanValue SPAWNABLE_GUNNER_DRONE;
	public final ForgeConfigSpec.BooleanValue SPAWNABLE_LEMURIAN;
    public final ForgeConfigSpec.BooleanValue SPAWNABLE_STONE_GOLEM;
    public final ForgeConfigSpec.BooleanValue DESPAWN_STONE_GOLEM;
    public final ForgeConfigSpec.BooleanValue LASER_KNOCKBACK;
    public final ForgeConfigSpec.BooleanValue NETHER_SPAWNABLE;
    public final ForgeConfigSpec.IntValue GUNNER_DRONE_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_SPAWN_RATE;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> OVERWORLD_SPAWN_BIOMES;
    public ForgeConfigSpec.ConfigValue<List<? extends String>> NETHER_SPAWN_BIOMES;
	public final ForgeConfigSpec.IntValue INFUSION_CAP;
	public final ForgeConfigSpec.BooleanValue SOUNDS;
	
	public ServerConfig(final ForgeConfigSpec.Builder builder) {
		
		builder.push(CATEGORY_SPAWNABLES);
		this.SPAWNABLE_GUNNER_DRONE = buildBoolean(builder, "Spawn Gunner Drones", "all", true, "Whether to spawn Gunner Drones or not");
		SPAWNABLE_LEMURIAN = buildBoolean(builder, "Spawn Lemurians", "all", true, "Whether to spawn Lemurians or not");
		SPAWNABLE_STONE_GOLEM = buildBoolean(builder, "Spawn Stone Golems", "all", true, "Whether to spawn Stone Golems or not");
		DESPAWN_STONE_GOLEM = buildBoolean(builder, "Stone Golem spawn persistence", "all", false, "Wheter to allow Stone Golems spawn persistence or not");
		LASER_KNOCKBACK = buildBoolean(builder, "Stone Golem laser knockback", "all", false, "Wheter to allow Stone Golems laser to knockback players or not (crash risk)");
		NETHER_SPAWNABLE = buildBoolean(builder, "Nether spawns", "all", true, "Wheter to spawn RoR monsters in the Nether or not");
		builder.pop();
		
		builder.push(CATEGORY_SPAWN_RATES);
		this.GUNNER_DRONE_SPAWN_RATE = buildInt(builder, "Gunner Drone weight", "all", 1, 1, 100, "The weight of Gunner Drones in vanilla spawn rate");
		this.LEMURIAN_SPAWN_RATE = buildInt(builder, "Lemurian weight", "all", 80, 1, 100, "The weight of Lemurians in vanilla spawn rate");
		this.STONE_GOLEM_SPAWN_RATE = buildInt(builder, "Stone Golem weight", "all", 20, 1, 100, "The weight of Stone Golems in vanilla spawn rate");
		builder.pop();
		
		builder.push(CATEGORY_WHITE_LISTED_OVERWORLD_SPAWN_BIOMES);
		this.OVERWORLD_SPAWN_BIOMES = builder .comment("Use the format \"mod_ID:biome\" like \"minecraft:plains\" or \"rats:ratlantis\". You can see a mod's ID by clicking the 'Mods' button on the main screen and clicking on the mod's name on the left ")
                .defineList("Overworld whitelist", Lists.newArrayList(
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
                		"minecraft:ice_spikes"), o -> o instanceof String);
		builder.pop();
		
		builder.push(CATEGORY_WHITE_LISTED_NETHER_SPAWN_BIOMES);
		this.NETHER_SPAWN_BIOMES = builder .comment("Use the format like \"minecraft:nether_wastes\" ")
                .defineList("Nether whitelist ", Lists.newArrayList(
                		"minecraft:basalt_deltas",
                		"minecraft:crimson_forest",
            			"minecraft:nether_wastes",
                        "minecraft:warped_forest"), o -> o instanceof String);
		builder.pop();
		
		builder.push(CATEGORY_ITEMS);
		this.INFUSION_CAP = buildInt(builder, "Infusion cap", "all", 60, 20, 200, "The Infusion max health cap");
		builder.pop();
		
		builder.push(CATEGORY_SOUNDS);
		this.SOUNDS = buildBoolean(builder, "Allow RoR sounds", "all", true, "Wheter to allow RoR sounds or not");
		builder.pop();
	}
	
	private static BooleanValue buildBoolean(Builder builder, String name, String catagory, boolean defaultValue, String comment) {
		return builder.comment(comment).translation(name).define(name, defaultValue);
	}
	
	private static IntValue buildInt(Builder builder, String name, String catagory, int defaultValue, int min, int max, String comment) {
		return builder.comment(comment).translation(name).defineInRange(name, defaultValue, min, max);
	}
}

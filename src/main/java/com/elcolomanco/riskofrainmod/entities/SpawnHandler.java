package com.elcolomanco.riskofrainmod.entities;

import java.util.List;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntitySpawnPlacementRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.world.biome.MobSpawnInfo;
import net.minecraft.world.biome.Biome.Category;
import net.minecraft.world.biome.MobSpawnInfo.Spawners;
import net.minecraft.world.gen.Heightmap;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = RoRmod.MODID)
public class SpawnHandler {
	
	@SubscribeEvent(priority = EventPriority.HIGH)
	public static void addEntitySpawns(BiomeLoadingEvent event) {
		Category category = event.getCategory();
		List<Spawners> monsterSpawners = event.getSpawns().getSpawner(EntityClassification.MONSTER);
		List<Spawners> creatureSpawners = event.getSpawns().getSpawner(EntityClassification.CREATURE);
		if (!monsterSpawners.isEmpty() && category != Category.MUSHROOM && category != Category.THEEND) {
			if (RoRconfig.NETHER_SPAWNABLE) {
				if(RoRconfig.NETHER_SPAWN_BIOMES.contains(event.getName().toString())){
					if (RoRconfig.SPAWNABLE_LEMURIAN) {
						monsterSpawners.add(new MobSpawnInfo.Spawners(RegistrySetup.LEMURIAN.get(), RoRconfig.LEMURIAN_SPAWN_RATE / 2, 2, 3));
					}
					if (RoRconfig.SPAWNABLE_STONE_GOLEM) {
						monsterSpawners.add(new MobSpawnInfo.Spawners(RegistrySetup.STONE_GOLEM.get(), RoRconfig.STONE_GOLEM_SPAWN_RATE / 2, 1, 1));
					}
				}
			}
			if (category != Category.NETHER) {
				if(RoRconfig.OVERWORLD_SPAWN_BIOMES.contains(event.getName().toString())){
					if (RoRconfig.SPAWNABLE_LEMURIAN) {
						monsterSpawners.add(new MobSpawnInfo.Spawners(RegistrySetup.LEMURIAN.get(), RoRconfig.LEMURIAN_SPAWN_RATE, 2, 3));
					}
					if (RoRconfig.SPAWNABLE_STONE_GOLEM) {
						monsterSpawners.add(new MobSpawnInfo.Spawners(RegistrySetup.STONE_GOLEM.get(), RoRconfig.STONE_GOLEM_SPAWN_RATE, 1, 1));
					}
					if (RoRconfig.SPAWNABLE_GUNNER_DRONE) {
						creatureSpawners.add(new MobSpawnInfo.Spawners(RegistrySetup.GUNNER_DRONE.get(), RoRconfig.GUNNER_DRONE_SPAWN_RATE, 1, 1));
					}
				}
			}
		}
	}
	
	public static void setSpawnPlacement() {
	    EntitySpawnPlacementRegistry.register(RegistrySetup.GUNNER_DRONE.get(), EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, GunnerDroneEntity::canAnimalSpawn);
		EntitySpawnPlacementRegistry.register(RegistrySetup.LEMURIAN.get(),     EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, LemurianEntity::canMonsterSpawnInLight);
		EntitySpawnPlacementRegistry.register(RegistrySetup.STONE_GOLEM.get(),  EntitySpawnPlacementRegistry.PlacementType.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, StoneGolemEntity::canMonsterSpawnInLight);
		
		GlobalEntityTypeAttributes.put(RegistrySetup.GUNNER_DRONE.get(), GunnerDroneEntity.setAttributes().create());
		GlobalEntityTypeAttributes.put(RegistrySetup.LEMURIAN.get(),     LemurianEntity.setAttributes().create());
		GlobalEntityTypeAttributes.put(RegistrySetup.STONE_GOLEM.get(),  StoneGolemEntity.setAttributes().create());
	}
}

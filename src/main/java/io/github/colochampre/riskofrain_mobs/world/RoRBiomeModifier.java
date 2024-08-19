package io.github.colochampre.riskofrain_mobs.world;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.init.BiomeModifierInit;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class RoRBiomeModifier implements BiomeModifier {
  public static final RoRBiomeModifier INSTANCE = new RoRBiomeModifier();

  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    // Overworld
    if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {
      int droneSpawnRate = RoRConfig.SERVER.DRONES_SPAWN_RATE.get();
      if (droneSpawnRate > 0) { // Drones
        List<MobSpawnSettings.SpawnerData> droneList = Arrays.asList(
                new MobSpawnSettings.SpawnerData(EntityInit.GUNNER_DRONE_ENTITY.get(), droneSpawnRate, 1, 1),
                new MobSpawnSettings.SpawnerData(EntityInit.GUNNER_TURRET_ENTITY.get(), droneSpawnRate, 1, 1)
        );
        MobSpawnSettings.SpawnerData randomDrone = droneList.get(ThreadLocalRandom.current().nextInt(droneList.size()));
        builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(randomDrone);
      }
      if (!biome.is(Tags.Biomes.IS_MUSHROOM)) {
        if (RoRConfig.SERVER.BEETLE_OVERWORLD_SPAWN_RATE.get() > 0) { // Beetles
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.BEETLE_ENTITY.get(),
                  RoRConfig.SERVER.BEETLE_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.BEETLE_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.BEETLE_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.LEMURIAN_OVERWORLD_SPAWN_RATE.get() > 0) { // Lemurians
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.LEMURIAN_ENTITY.get(),
                  RoRConfig.SERVER.LEMURIAN_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.LEMURIAN_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.LEMURIAN_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.STONE_GOLEM_OVERWORLD_SPAWN_RATE.get() > 0) { // Stone Golems
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.STONE_GOLEM_ENTITY.get(),
                  RoRConfig.SERVER.STONE_GOLEM_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.STONE_GOLEM_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.STONE_GOLEM_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.WISP_OVERWORLD_SPAWN_RATE.get() > 0) { // Wisps
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.WISP_ENTITY.get(),
                  RoRConfig.SERVER.WISP_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.WISP_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.WISP_MAX_GROUP_SIZE.get()));
        }
      }
    }
    // Nether
    if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_NETHER) && !biome.is(Tags.Biomes.IS_VOID)) {
      if (!biome.is(Biomes.WARPED_FOREST)) {
        if (RoRConfig.SERVER.BEETLE_NETHER_SPAWN_RATE.get() > 0) { // Beetles
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.BEETLE_ENTITY.get(),
                  RoRConfig.SERVER.BEETLE_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.BEETLE_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.BEETLE_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.LEMURIAN_NETHER_SPAWN_RATE.get() > 0) { // Lemurians
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.LEMURIAN_ENTITY.get(),
                  RoRConfig.SERVER.LEMURIAN_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.LEMURIAN_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.LEMURIAN_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.STONE_GOLEM_NETHER_SPAWN_RATE.get() > 0) { // Stone Golems
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.STONE_GOLEM_ENTITY.get(),
                  RoRConfig.SERVER.STONE_GOLEM_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.STONE_GOLEM_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.STONE_GOLEM_MAX_GROUP_SIZE.get()));
        }
        if (RoRConfig.SERVER.WISP_NETHER_SPAWN_RATE.get() > 0) { // Wisps
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.WISP_ENTITY.get(),
                  RoRConfig.SERVER.WISP_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.WISP_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.WISP_MAX_GROUP_SIZE.get()));
        }
      }
    }
  }

  @Override
  public MapCodec<? extends BiomeModifier> codec() {
    return BiomeModifierInit.ROR_ENTITY_MODIFIER_TYPE.get();
  }
}
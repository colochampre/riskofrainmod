package io.github.colochampre.riskofrain_mobs.world;

import com.mojang.serialization.Codec;
import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.init.BiomeModifierInit;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.core.Holder;
import net.minecraft.tags.BiomeTags;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ModifiableBiomeInfo;

public class RoRBiomeModifier implements BiomeModifier {
  public static final RoRBiomeModifier INSTANCE = new RoRBiomeModifier();

  @Override
  public void modify(Holder<Biome> biome, Phase phase, ModifiableBiomeInfo.BiomeInfo.Builder builder) {
    /* Overworld */
    if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_OVERWORLD) && !biome.is(Biomes.DEEP_DARK) && !biome.is(Tags.Biomes.IS_VOID)) {
      /* Gunner Drones */
      if (RoRConfig.SERVER.DRONES_SPAWN_RATE.get() > 0) {
        builder.getMobSpawnSettings().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(EntityInit.GUNNER_DRONE_ENTITY.get(),
                RoRConfig.SERVER.DRONES_SPAWN_RATE.get(), 1, 1));
      }
      /* Lemurians */
      if (RoRConfig.SERVER.LEMURIAN_OVERWORLD_SPAWN_RATE.get() > 0) {
        if (!biome.is(Tags.Biomes.IS_MUSHROOM)) {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.LEMURIAN_ENTITY.get(),
                  RoRConfig.SERVER.LEMURIAN_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.LEMURIAN_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.LEMURIAN_MAX_GROUP_SIZE.get()));
        }
      }
      /* Stone Golems */
      if (RoRConfig.SERVER.STONE_GOLEM_OVERWORLD_SPAWN_RATE.get() > 0) {
        if (!biome.is(Tags.Biomes.IS_MUSHROOM)) {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.STONE_GOLEM_ENTITY.get(),
                  RoRConfig.SERVER.STONE_GOLEM_OVERWORLD_SPAWN_RATE.get(), RoRConfig.SERVER.STONE_GOLEM_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.STONE_GOLEM_MAX_GROUP_SIZE.get()));
        }
      }
    }
    /* Nether */
    if (phase == Phase.ADD && biome.containsTag(BiomeTags.IS_NETHER) && !biome.is(Tags.Biomes.IS_VOID)) {
      /* Lemurians */
      if (RoRConfig.SERVER.LEMURIAN_NETHER_SPAWN_RATE.get() > 0) {
        if (biome.is(Biomes.CRIMSON_FOREST) || biome.is(Biomes.WARPED_FOREST)) {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.LEMURIAN_ENTITY.get(),
                  Mth.ceil((double) RoRConfig.SERVER.LEMURIAN_NETHER_SPAWN_RATE.get() / 2), RoRConfig.SERVER.LEMURIAN_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.LEMURIAN_MAX_GROUP_SIZE.get()));
        } else {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.LEMURIAN_ENTITY.get(),
                  RoRConfig.SERVER.LEMURIAN_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.LEMURIAN_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.LEMURIAN_MAX_GROUP_SIZE.get()));
        }
      }
      /* Stone Golems */
      if (RoRConfig.SERVER.STONE_GOLEM_NETHER_SPAWN_RATE.get() > 0) {
        if (biome.is(Biomes.CRIMSON_FOREST) || biome.is(Biomes.WARPED_FOREST)) {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.STONE_GOLEM_ENTITY.get(),
                  Mth.ceil((double) RoRConfig.SERVER.STONE_GOLEM_NETHER_SPAWN_RATE.get() / 2), RoRConfig.SERVER.STONE_GOLEM_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.STONE_GOLEM_MAX_GROUP_SIZE.get()));
        } else {
          builder.getMobSpawnSettings().getSpawner(MobCategory.MONSTER).add(new MobSpawnSettings.SpawnerData(EntityInit.STONE_GOLEM_ENTITY.get(),
                  RoRConfig.SERVER.STONE_GOLEM_NETHER_SPAWN_RATE.get(), RoRConfig.SERVER.STONE_GOLEM_MIN_GROUP_SIZE.get(), RoRConfig.SERVER.STONE_GOLEM_MAX_GROUP_SIZE.get()));
        }
      }
    }
  }

  @Override
  public Codec<? extends BiomeModifier> codec() {
    return BiomeModifierInit.ROR_ENTITY_MODIFIER_TYPE.get();
  }
}

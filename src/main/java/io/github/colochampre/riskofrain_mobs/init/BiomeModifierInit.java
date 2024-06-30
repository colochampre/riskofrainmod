package io.github.colochampre.riskofrain_mobs.init;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.world.RoRBiomeModifier;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BiomeModifierInit {
  public static final DeferredRegister<MapCodec<? extends BiomeModifier>> BIOME_MODIFIER_SERIALIZERS =
          DeferredRegister.create((ResourceKey<? extends Registry<MapCodec<? extends BiomeModifier>>>) ForgeRegistries.Keys.BIOME_MODIFIER_SERIALIZERS, RoRmod.MODID);

  public static final RegistryObject<MapCodec<RoRBiomeModifier>> ROR_ENTITY_MODIFIER_TYPE =
          BIOME_MODIFIER_SERIALIZERS.register("add_mob_spawns", () -> MapCodec.unit(RoRBiomeModifier.INSTANCE));
}
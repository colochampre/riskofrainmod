package io.github.colochampre.riskofrain_items.init;

import com.mojang.serialization.MapCodec;
import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.loot.CommonLootTableModifier;
import io.github.colochampre.riskofrain_items.loot.UncommonLootTableModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootInit {

  public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS =
          DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RoRitems.MODID);

  public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_COMMON_LOOT =
          LOOT_MODIFIERS.register("add_common_loot_table", CommonLootTableModifier.CODEC);

  public static final RegistryObject<MapCodec<? extends IGlobalLootModifier>> ADD_UNCOMMON_LOOT =
          LOOT_MODIFIERS.register("add_uncommon_loot_table", UncommonLootTableModifier.CODEC);
  /*
  public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_LEGENDARY_LOOT =
          LOOT_MODIFIER_SERIALIZERS.register("add_legendary_loot_table", LegendaryLootTableModifier.CODEC);
  */
}

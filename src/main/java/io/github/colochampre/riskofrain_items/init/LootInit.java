package io.github.colochampre.riskofrain_items.init;

import com.mojang.serialization.Codec;
import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.loot.LootTableModifier;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LootInit {
  public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
          DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, RoRitems.MODID);

  public static final RegistryObject<Codec<? extends IGlobalLootModifier>> ADD_ITEM =
          LOOT_MODIFIER_SERIALIZERS.register("add_to_loot_table", LootTableModifier.CODEC);
}

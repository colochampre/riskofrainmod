package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRitems.MODID);

  public static final RegistryObject<Item> COMMON_CHEST = ITEMS.register("common_chest",
          () -> new Item(new Item.Properties()));

  public static final RegistryObject<Item> CROWBAR = ITEMS.register("crowbar",
          () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> INFUSION = ITEMS.register("infusion",
          () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> LENS_MAKERS_GLASSES = ITEMS.register("lens_makers_glasses",
          () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> TOPAZ_BROOCH = ITEMS.register("topaz_brooch",
          () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> TOUGHER_TIMES = ITEMS.register("tougher_times",
          () -> new Item(new Item.Properties()));
}

package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRitems.MODID);

  public static final RegistryObject<Item> COMMON_CHEST = ITEMS.register("common_chest",
          () -> new Item(new Item.Properties()));
  public static final RegistryObject<Item> BUSTLING_FUNGUS = ITEMS.register("bustling_fungus",
          () -> new BustlingFungusItem(new Item.Properties()));
  public static final RegistryObject<Item> CROWBAR = ITEMS.register("crowbar",
          () -> new CrowbarItem(new Item.Properties()));
  public static final RegistryObject<Item> GOLDEN_GUN = ITEMS.register("golden_gun",
          () -> new GoldenGunItem(new Item.Properties()));
  public static final RegistryObject<Item> INFUSION = ITEMS.register("infusion",
          () -> new InfusionItem(new Item.Properties()));
  public static final RegistryObject<Item> LENS_MAKERS_GLASSES = ITEMS.register("lens_makers_glasses",
          () -> new LensMakersGlassesItem(new Item.Properties()));
  public static final RegistryObject<Item> REPULSION_ARMOR_PLATE = ITEMS.register("repulsion_armor_plate",
          () -> new RepulsionArmorPlateItem(new Item.Properties()));
  public static final RegistryObject<Item> SMART_SHOPPER = ITEMS.register("smart_shopper",
          () -> new SmartShopperItem(new Item.Properties()));
  public static final RegistryObject<Item> TOPAZ_BROOCH = ITEMS.register("topaz_brooch",
          () -> new TopazBroochItem(new Item.Properties()));
  public static final RegistryObject<Item> TOUGHER_TIMES = ITEMS.register("tougher_times",
          () -> new TougherTimesItem(new Item.Properties()));
}

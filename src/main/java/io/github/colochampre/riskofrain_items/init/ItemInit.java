package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.items.*;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRitems.MODID);

  public static final RegistryObject<Item> SMALL_CHEST = ITEMS.register("small_chest_item",
          () -> new Item(new Item.Properties()));

  /* Common items */
  public static final RegistryObject<Item> BUSTLING_FUNGUS = ITEMS.register("bustling_fungus",
          () -> new BustlingFungusItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> CROWBAR = ITEMS.register("crowbar",
          () -> new CrowbarItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> GOAT_HOOF = ITEMS.register("goat_hoof",
          () -> new GoatHoofItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> LENS_MAKERS_GLASSES = ITEMS.register("lens_makers_glasses",
          () -> new LensMakersGlassesItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> REPULSION_ARMOR_PLATE = ITEMS.register("repulsion_armor_plate",
          () -> new RepulsionArmorPlateItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> SOLDIERS_SYRINGE = ITEMS.register("soldiers_syringe",
          () -> new SoldiersSyringeItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> TOPAZ_BROOCH = ITEMS.register("topaz_brooch",
          () -> new TopazBroochItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> TOUGHER_TIMES = ITEMS.register("tougher_times",
          () -> new TougherTimesItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));

  /* Rare items */
  public static final RegistryObject<Item> GOLDEN_GUN = ITEMS.register("golden_gun",
          () -> new GoldenGunItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> INFUSION = ITEMS.register("infusion",
          () -> new InfusionItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> ROSE_BUCKLER = ITEMS.register("rose_buckler",
          () -> new RoseBucklerItem (new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
  public static final RegistryObject<Item> SMART_SHOPPER = ITEMS.register("smart_shopper",
          () -> new SmartShopperItem(new Item.Properties().tab(ModCreativeModeTabs.RISKOFRAIN_TAB)));
}

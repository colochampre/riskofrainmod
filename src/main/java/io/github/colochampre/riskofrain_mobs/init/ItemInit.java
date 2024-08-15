package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.items.GunnerTurretItem;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRmod.MODID);

  // Spawn Eggs
  public static final RegistryObject<ForgeSpawnEggItem> BEETLE_SPAWN_EGG = ITEMS.register("beetle_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.BEETLE_ENTITY, 0x47302F, 0xB38B5D, props()));
  public static final RegistryObject<ForgeSpawnEggItem> LEMURIAN_SPAWN_EGG = ITEMS.register("lemurian_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.LEMURIAN_ENTITY, 0x6C537B, 0x2E2242, props()));
  public static final RegistryObject<ForgeSpawnEggItem> STONE_GOLEM_SPAWN_EGG = ITEMS.register("stone_golem_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.STONE_GOLEM_ENTITY, 0x38364E, 0x2B273D, props()));
  public static final RegistryObject<ForgeSpawnEggItem> WISP_SPAWN_EGG = ITEMS.register("wisp_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.WISP_ENTITY, 0x210502, 0xDE6B2C, props()));
  public static final RegistryObject<ForgeSpawnEggItem> GUNNER_DRONE_SPAWN_EGG = ITEMS.register("gunner_drone_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.GUNNER_DRONE_ENTITY, 0x007ada, 0x202f51, props()));
  /*public static final RegistryObject<ForgeSpawnEggItem> GUNNER_TURRET_SPAWN_EGG = ITEMS.register("gunner_turret_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.GUNNER_TURRET_ENTITY, 0x007ada, 0x363636, props()));*/

  // Gunner Turret Colors
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM = ITEMS.register("gunner_turret",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_WHITE = ITEMS.register("gunner_turret_white",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_ORANGE = ITEMS.register("gunner_turret_orange",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_MAGENTA = ITEMS.register("gunner_turret_magenta",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_YELLOW = ITEMS.register("gunner_turret_yellow",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_LIME = ITEMS.register("gunner_turret_lime",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_PINK = ITEMS.register("gunner_turret_pink",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_GRAY = ITEMS.register("gunner_turret_gray",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_LIGHT_GRAY = ITEMS.register("gunner_turret_light_gray",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_CYAN = ITEMS.register("gunner_turret_cyan",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_PURPLE = ITEMS.register("gunner_turret_purple",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_BLUE = ITEMS.register("gunner_turret_blue",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_BROWN = ITEMS.register("gunner_turret_brown",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_GREEN = ITEMS.register("gunner_turret_green",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_RED = ITEMS.register("gunner_turret_red",
          () -> new GunnerTurretItem(props().stacksTo(16)));
  public static final RegistryObject<Item> GUNNER_TURRET_ITEM_BLACK = ITEMS.register("gunner_turret_black",
          () -> new GunnerTurretItem(props().stacksTo(16)));

  public static Item getItemForColor(DyeColor color) {
    return switch (color) {
      case WHITE -> ItemInit.GUNNER_TURRET_ITEM_WHITE.get();
      case ORANGE -> ItemInit.GUNNER_TURRET_ITEM_ORANGE.get();
      case MAGENTA -> ItemInit.GUNNER_TURRET_ITEM_MAGENTA.get();
      case YELLOW -> ItemInit.GUNNER_TURRET_ITEM_YELLOW.get();
      case LIME -> ItemInit.GUNNER_TURRET_ITEM_LIME.get();
      case PINK -> ItemInit.GUNNER_TURRET_ITEM_PINK.get();
      case GRAY -> ItemInit.GUNNER_TURRET_ITEM_GRAY.get();
      case LIGHT_GRAY -> ItemInit.GUNNER_TURRET_ITEM_LIGHT_GRAY.get();
      case CYAN -> ItemInit.GUNNER_TURRET_ITEM_CYAN.get();
      case PURPLE -> ItemInit.GUNNER_TURRET_ITEM_PURPLE.get();
      case BLUE -> ItemInit.GUNNER_TURRET_ITEM_BLUE.get();
      case BROWN -> ItemInit.GUNNER_TURRET_ITEM_BROWN.get();
      case GREEN -> ItemInit.GUNNER_TURRET_ITEM_GREEN.get();
      case RED -> ItemInit.GUNNER_TURRET_ITEM_RED.get();
      case BLACK -> ItemInit.GUNNER_TURRET_ITEM_BLACK.get();
      default -> ItemInit.GUNNER_TURRET_ITEM.get();
    };
  }

  private static Item.Properties props() {
    return new Item.Properties();
  }
}

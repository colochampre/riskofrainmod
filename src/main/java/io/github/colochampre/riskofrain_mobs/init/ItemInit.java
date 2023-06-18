package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRmod.MODID);

  public static final RegistryObject<ForgeSpawnEggItem> LEMURIAN_SPAWN_EGG = ITEMS.register("lemurian_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.LEMURIAN_ENTITY, 0x6C537B, 0x2E2242, props()));

  public static final RegistryObject<ForgeSpawnEggItem> STONE_GOLEM_SPAWN_EGG = ITEMS.register("stone_golem_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.STONE_GOLEM_ENTITY, 0x38364E, 0x2B273D, props()));

  public static final RegistryObject<ForgeSpawnEggItem> GUNNER_DRONE_SPAWN_EGG = ITEMS.register("gunner_drone_spawn_egg",
          () -> new ForgeSpawnEggItem(EntityInit.GUNNER_DRONE_ENTITY, 0x007ada, 0x202f51, props()));

  private static Item.Properties props() {
    return new Item.Properties().tab(CreativeModeTab.TAB_MISC);
  }
}

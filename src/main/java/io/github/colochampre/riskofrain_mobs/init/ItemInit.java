package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
//import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemInit {
  public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, RoRmod.MODID);

  public static final RegistryObject<ForgeSpawnEggItem> LEMURIAN_SPAWN_EGG = ITEMS.register("lemurian_spawn_egg", () -> new ForgeSpawnEggItem(EntityInit.LEMURIAN_ENTITY, 0x6C537B,0x2E2242, props()));

  private static Item.Properties props() {
    return new Item.Properties();
  }
}

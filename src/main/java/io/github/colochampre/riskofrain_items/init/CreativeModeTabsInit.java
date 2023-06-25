package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class CreativeModeTabsInit {
  public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, RoRitems.MODID);

  public static RegistryObject<CreativeModeTab> RISKOFRAIN_TAB = CREATIVE_MODE_TABS.register("riskofrain_tab",
          () -> CreativeModeTab.builder()
                  .icon(() -> new ItemStack(ItemInit.SMALL_CHEST.get()))
                  .title(Component.translatable("creativemodetab.riskofrain_tab")).build());

  public static void addItemsToTabs(BuildCreativeModeTabContentsEvent event) {
    if (event.getTab() == CreativeModeTabsInit.RISKOFRAIN_TAB.get()) {
      event.accept(ItemInit.BUSTLING_FUNGUS);
      event.accept(ItemInit.CROWBAR);
      event.accept(ItemInit.GOAT_HOOF);
      event.accept(ItemInit.LENS_MAKERS_GLASSES);
      event.accept(ItemInit.REPULSION_ARMOR_PLATE);
      event.accept(ItemInit.SOLDIERS_SYRINGE);
      event.accept(ItemInit.TOPAZ_BROOCH);
      event.accept(ItemInit.TOUGHER_TIMES);
      event.accept(ItemInit.GOLDEN_GUN);
      event.accept(ItemInit.INFUSION);
      event.accept(ItemInit.ROSE_BUCKLER);
      event.accept(ItemInit.SMART_SHOPPER);
      // event.accept(BlockInit.SMALL_CHEST);
    }
  }
}

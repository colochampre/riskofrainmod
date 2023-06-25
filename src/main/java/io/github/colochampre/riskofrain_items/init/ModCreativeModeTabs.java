package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.init.ItemInit;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeModeTabs {
  public static final CreativeModeTab RISKOFRAIN_TAB = new CreativeModeTab("riskofrain_tab") {
    @Override
    public ItemStack makeIcon() {
      return new ItemStack(ItemInit.SMALL_CHEST.get());
    }
  };
}

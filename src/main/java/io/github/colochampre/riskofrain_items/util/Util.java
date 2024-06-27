package io.github.colochampre.riskofrain_items.util;

import io.github.colochampre.riskofrain_items.init.ItemInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Util {

  private static final List<Item> commonItems = new ArrayList<>();
  private static final List<Item> uncommonItems = new ArrayList<>();
  //private static final List<Item> legendaryItems = new ArrayList<>();

  static {
    commonItems.add(ItemInit.BUSTLING_FUNGUS.get());
    commonItems.add(ItemInit.CROWBAR.get());
    commonItems.add(ItemInit.GOAT_HOOF.get());
    commonItems.add(ItemInit.LENS_MAKERS_GLASSES.get());
    commonItems.add(ItemInit.REPULSION_ARMOR_PLATE.get());
    commonItems.add(ItemInit.SOLDIERS_SYRINGE.get());
    commonItems.add(ItemInit.TOPAZ_BROOCH.get());
    commonItems.add(ItemInit.TOUGHER_TIMES.get());

    uncommonItems.add(ItemInit.GOLDEN_GUN.get());
    uncommonItems.add(ItemInit.INFUSION.get());
    uncommonItems.add(ItemInit.ROSE_BUCKLER.get());
    uncommonItems.add(ItemInit.SMART_SHOPPER.get());
  }

  public static boolean hasItemStack(Player player, Item item) {
    Inventory inv = player.getInventory();
    for (int i = 0; i <= 35; ++i) {
      ItemStack stack = inv.getItem(i);
      if (ItemStack.isSame(stack, item.getDefaultInstance())) {
        return true;
      }
    }
    return false;
  }

  public static int countItemStack(Player player, Item item) {
    int total = 0;
    Inventory inv = player.getInventory();
    for (int i = 0; i < inv.getContainerSize(); ++i) {
      ItemStack stack = inv.getItem(i);
      if (ItemStack.isSame(stack, item.getDefaultInstance())) {
        total += stack.getCount();
      }
    }
    return total;
  }
}

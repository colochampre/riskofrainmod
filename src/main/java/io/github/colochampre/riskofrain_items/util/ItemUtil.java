package io.github.colochampre.riskofrain_items.util;

import io.github.colochampre.riskofrain_items.init.ItemInit;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class ItemUtil {

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

  public static List<Item> getCommonItems() {
    return commonItems;
  }

  public static List<Item> getUncommonItems() {
    return uncommonItems;
  }

  /*
  public static List<Item> getLegendaryItems() {
      return legendaryItems;
  }
  */
  // Small chest chances: common 79%, uncommon 20%, legendary 1%
  public static Item getSmallChestItem() {
    Random random = new Random();
    int randomInt = random.nextInt(100);
        /*
        if (randomInt < 1) {
            randomInt = random.nextInt(legendaryItems.size());
            return legendaryItems.get(randomInt);
        } else*/
    if (randomInt < 20) {
      randomInt = random.nextInt(uncommonItems.size());
      return uncommonItems.get(randomInt);
    }
    randomInt = random.nextInt(commonItems.size());
    return commonItems.get(randomInt);
  }

  // Large chest chances: common 0%, uncommon 80%, legendary 20%
  public static Item getLargeChestItem() {
    Random random = new Random();
    int randomInt;
        /*
        randomInt = random.nextInt(100);
        if (randomInt < 20) {
            randomInt = random.nextInt(legendaryItems.size());
            return legendaryItems.get(randomInt);
        }
        */
    randomInt = random.nextInt(uncommonItems.size());
    return uncommonItems.get(randomInt);
  }

  public static boolean hasItemStack(Player player, Item item) {
    Inventory inv = player.getInventory();
    for (int i = 0; i <= 35; ++i) {
      ItemStack stack = inv.getItem(i);
      if (ItemStack.isSameItem(stack, item.getDefaultInstance())) {
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
      if (ItemStack.isSameItem(stack, item.getDefaultInstance())) {
        total += stack.getCount();
      }
    }
    return total;
  }
}

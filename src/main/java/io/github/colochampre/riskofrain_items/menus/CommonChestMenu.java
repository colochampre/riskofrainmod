package io.github.colochampre.riskofrain_items.menus;

import io.github.colochampre.riskofrain_items.blocks.entity.CommonChestBlockEntity;
import io.github.colochampre.riskofrain_items.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.*;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.Nullable;

public class CommonChestMenu extends AbstractContainerMenu {

  private final ContainerLevelAccess levelAccess;
  private final ContainerData data;

  protected CommonChestMenu(int id, Inventory playerInv, IItemHandler slots, BlockPos pos, ContainerData data) {
    super(MenuInit.COMMON_CHEST.get(), id);
    this.levelAccess = ContainerLevelAccess.create(playerInv.player.getLevel(), pos);
    this.data = data;

    addSlot(new SlotItemHandler(slots, 0, 56, 35));
    addSlot(new SlotWithRestriction(slots, 1, 116, 35));

    final int slotSizePlus2 = 18;
    final int startX = 8;
    final int startY = 84;
    final int hotbarY = 142;

    for (int row = 0; row < 3; ++row) {
      for (int column = 0; column < 9; ++column) {
        addSlot(new Slot(playerInv, column + row * 9 + 9, startX + column * slotSizePlus2,
                startY + row * slotSizePlus2));
      }
    }

    for (int column = 0; column < 9; ++column) {
      addSlot(new Slot(playerInv, column, startX + column * slotSizePlus2, hotbarY));
    }

    this.addDataSlots(this.data);
  }

  public static CommonChestMenu getClientMenu(int id, Inventory playerInv) {
    return new CommonChestMenu(id, playerInv, new ItemStackHandler(2), BlockPos.ZERO, new SimpleContainerData(2));
  }

  public static MenuConstructor getServerMenu(CommonChestBlockEntity blockEntity, BlockPos pos) {
    return (id, playerInv, player) -> new CommonChestMenu(id, playerInv, blockEntity.getInventory(), pos,
            blockEntity.getContainerData());
  }

  @Override
  public ItemStack quickMoveStack(Player player, int index) {
    ItemStack itemstack = ItemStack.EMPTY;
    Slot slot = this.slots.get(index);
    if (slot.hasItem()) {
      ItemStack current = slot.getItem();
      itemstack = current.copy();
      if (index < 2) {
        if (!this.moveItemStackTo(current, 2, this.slots.size(), true)) {
          return ItemStack.EMPTY;
        }
      } else if (!this.moveItemStackTo(current, 0, 2, false)) {
        return ItemStack.EMPTY;
      }

      if (current.isEmpty()) {
        slot.set(ItemStack.EMPTY);
      } else {
        slot.setChanged();
      }
    }

    return itemstack;
  }

  @Override
  public boolean stillValid(Player player) {
    return stillValid(this.levelAccess, player, BlockInit.COMMON_CHEST.get());
  }

  public ContainerData getData() {
    return data;
  }
}

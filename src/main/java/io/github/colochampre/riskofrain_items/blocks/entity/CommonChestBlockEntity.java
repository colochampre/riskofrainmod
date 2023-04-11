package io.github.colochampre.riskofrain_items.blocks.entity;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.ItemStackHandler;

public class CommonChestBlockEntity extends BlockEntity {

  private final ItemStackHandler inventory = new ItemStackHandler(2) {
    @Override
    protected void onContentsChanged(int slot) {
      CommonChestBlockEntity.this.setChanged();
      super.onContentsChanged(slot);
    }
  };
  private final LazyOptional<IItemHandlerModifiable> optional = LazyOptional.of(() -> this.inventory);

  private final ContainerData data = new ContainerData() {

    @Override
    public int get(int index) {
      return 0;
    }

    @Override
    public void set(int index, int value) {
    }

    @Override
    public int getCount() {
      return 2;
    }
  };

  public static final Component TITLE = Component.translatable("container.riskofrain_items.common_chest");

  public CommonChestBlockEntity(BlockPos pos, BlockState state) {
    super(BlockEntityInit.COMMON_CHEST.get(), pos, state);
  }

  @Override
  public void load(CompoundTag nbt) {
    super.load(nbt);
    this.inventory.deserializeNBT(nbt.getCompound("Inventory"));
    System.out.println(nbt.getCompound("Inventory"));
  }

  @Override
  protected void saveAdditional(CompoundTag nbt) {
    super.saveAdditional(nbt);
    nbt.put("Inventory", this.inventory.serializeNBT());
    System.out.println(this.inventory.serializeNBT());
  }

  @Override
  public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
    return cap == ForgeCapabilities.ITEM_HANDLER ? this.optional.cast() : super.getCapability(cap, side);
  }

  public ItemStackHandler getInventory() {
    return inventory;
  }

  public ContainerData getContainerData() {
    return this.data;
  }
}

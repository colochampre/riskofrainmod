package io.github.colochampre.riskofrain_items.blocks.entity;
/*
import io.github.colochampre.riskofrain_items.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.Containers;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SmallChestBlockEntity extends BlockEntity implements MenuProvider {
  private int price = 36;
  private final ItemStackHandler itemHandler = new ItemStackHandler(27) {
    @Override
    protected void onContentsChanged(int slot) {
      setChanged();
    }
  };

  LazyOptional<IItemHandler> lazyItemHandler = LazyOptional.empty();

  public SmallChestBlockEntity(BlockPos pos, BlockState state) {
    super(BlockEntityInit.SMALL_CHEST.get(), pos, state);
  }

  @Override
  public Component getDisplayName() {
    return Component.translatable("block.riskofrain_items.small_chest.name");
  }

  @Nullable
  @Override
  public AbstractContainerMenu createMenu(int id, Inventory inventory, Player player) {
    return null;
  }

  @Override
  public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
    if (cap == ForgeCapabilities.ITEM_HANDLER) {
      return lazyItemHandler.cast();
    }
    return super.getCapability(cap, side);
  }

  @Override
  public void onLoad() {
    super.onLoad();
    lazyItemHandler = LazyOptional.of(() -> itemHandler);
  }

  @Override
  public void invalidateCaps() {
    super.invalidateCaps();
    lazyItemHandler.invalidate();
  }

  @Override
  protected void saveAdditional(CompoundTag nbt) {
    nbt.put("inventory", itemHandler.serializeNBT());
    nbt.putInt("price", this.price);
    super.saveAdditional(nbt);
  }

  @Override
  public void load(CompoundTag nbt) {
    super.load(nbt);
    itemHandler.deserializeNBT(nbt.getCompound("inventory"));
    price = nbt.getInt("price");
  }

  public void drop() {
    SimpleContainer inventory = new SimpleContainer(itemHandler.getSlots());
    for (int i = 0; i < itemHandler.getSlots(); i++) {
      inventory.setItem(i, itemHandler.getStackInSlot(i));
    }
    Containers.dropContents(this.level, this.worldPosition, inventory);
  }

  public static void tick(Level level, BlockPos blockPos, BlockState blockState, SmallChestBlockEntity entity) {
    if (level.isClientSide()) {
      return;
    }
  }
}
*/
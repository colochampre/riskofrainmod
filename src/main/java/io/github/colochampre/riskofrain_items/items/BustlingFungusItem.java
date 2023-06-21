package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BustlingFungusItem extends Item {
  private int timer;
  private double prevX;
  private double prevY;
  private double prevZ;

  public BustlingFungusItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.bustling_fungus.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.bustling_fungus.tooltip_2"));
    tooltip.add(Component.translatable("item.riskofrain_items.bustling_fungus.tooltip_3"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  @Override
  public void inventoryTick(ItemStack stack, Level level, @NotNull Entity entity, int itemSlot, boolean isSelected) {
    ServerPlayer player = (ServerPlayer) entity;
    int amount = stack.getCount();
    if (!level.isClientSide()) {
      if (this.isMoving(player)) {
        this.timer = -40;
      } else {
        this.timer++;
      }
      if (this.timer == -1) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_1.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_2.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
      }
      if ((this.timer == 0 || this.timer == 20) && player.getHealth() < player.getMaxHealth()) {
        player.heal(amount * (player.getMaxHealth() * 5 / 100));
        this.timer = 0;
      }
    }
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);
  }

  private boolean isMoving(Entity entity) {
    boolean result;
    result = this.prevX != entity.getX() || this.prevY != entity.getY() || this.prevZ != entity.getZ();
    this.prevX = entity.getX();
    this.prevY = entity.getY();
    this.prevZ = entity.getZ();
    return result;
  }
}

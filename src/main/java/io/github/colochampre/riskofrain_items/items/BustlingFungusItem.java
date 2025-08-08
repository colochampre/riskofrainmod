package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class BustlingFungusItem extends Item {

  // 1. Usamos un HashMap moderno y lo nombramos claramente.
  private static final Map<UUID, FungusData> PLAYER_DATA = new HashMap<>();

  public BustlingFungusItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(@NotNull ItemStack itemstack, @Nullable Level level, List<Component> tooltip, @NotNull TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.bustling_fungus.tooltip"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  @Override
  public void inventoryTick(@NotNull ItemStack stack, @NotNull Level level, @NotNull Entity entity, int itemSlot, boolean isSelected) {
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);

    if (level.isClientSide() || !(entity instanceof Player player)) {
      return;
    }
    // Calculate healing factor
    double factor = 0.045 + (stack.getCount() * 0.045); // 4.5% max health per second per stack.
    int healAmount = 1;
    int healInterval = 20; // Every second (20 ticks)
    // Get player data
    FungusData data = PLAYER_DATA.computeIfAbsent(player.getUUID(), k -> new FungusData(player.position()));
    // Check if player moved
    if (!data.lastPosition.equals(player.position())) {
      data.reset(player.position()); // Si se movió, reiniciamos el temporizador.
      return;
    }
    // If player didn't move, increase timer.
    data.ticksStill++;
    // Check if player has been still for long enough (2 seconds).
    int requiredTicks = 40;
    if (data.ticksStill >= requiredTicks && player.getHealth() < player.getMaxHealth()) {
      // Play sound on proc
      if (data.ticksStill == requiredTicks) {
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_1.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_2.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
      }
      // Heal the player at the defined interval.
      if (data.ticksStill % healInterval == 0) {
        player.heal(healAmount + (float)(player.getMaxHealth() * factor));
      }
    }
  }

  // Internal static class to store player data.
  private static class FungusData {
    int ticksStill;
    Vec3 lastPosition;

    FungusData(Vec3 position) {
      this.lastPosition = position;
      this.ticksStill = 0;
    }

    void reset(Vec3 newPosition) {
      this.lastPosition = newPosition;
      this.ticksStill = 0;
    }
  }
}

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
//import net.minecraft.ChatFormatting;
//import org.checkerframework.common.value.qual.StringVal;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

public class BustlingFungusItem extends Item {
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
    //NOTE: Not sure if tick rate is multiplied by the number of players holding the item
    Player player = (Player) entity;
    double factor = Math.min(.05f + stack.getCount() * .01f, .1);  //5% for the first item then 1% for every stack max at 10%
    //player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + String.valueOf(factor))); //debug
    int amount = (int) (player.getMaxHealth() * factor);
    if (!level.isClientSide()) {
      healIfStill(player, level, 80, 40, amount);
    }
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);
  }

  /* Bungus multiplayer fix by Yuese */
  private static Hashtable<UUID, double[]> oldTLocs = new Hashtable<UUID, double[]>();

  private void healIfStill(Player player, Level level, double stillReq, double every, int amount) {
    //String msg = "";
    UUID uuid = player.getUUID();
    double stillFor = 0;
    double[] tLoc = {stillFor, player.getX(), player.getY(), player.getZ(), amount};
    //msg = "Players:" + oldTLocs.size(); //debug
    if (!oldTLocs.containsKey(uuid)) {
      oldTLocs.put(uuid, tLoc);
    } else {
      double[] oldTLoc = oldTLocs.get(uuid);
      if (oldTLoc[1] != tLoc[1] || oldTLoc[2] != tLoc[2] || oldTLoc[3] != tLoc[3]) {
        //msg = msg + " || " + player.getName().getString() + " moved!"; //debug
      } else {
        stillFor = oldTLoc[0];
        stillFor++;
        //msg =msg + " || " + player.getName().getString()+" is Still for: " + stillFor; //debug
      }
      if (stillFor >= stillReq && player.getHealth() < player.getMaxHealth()) {
        if (stillFor == stillReq) {
          level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_1.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
          level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.BUNGUS_PROC_2.get(), SoundSource.PLAYERS, 0.5F, 1.0F);
        }
        if (stillFor % every == 0) {
          player.heal(amount);
        }
      }
      tLoc[0] = stillFor;
      oldTLocs.replace(uuid, tLoc);
    }
    //player.sendSystemMessage(Component.literal(ChatFormatting.GREEN + msg)); //debug
  }
}

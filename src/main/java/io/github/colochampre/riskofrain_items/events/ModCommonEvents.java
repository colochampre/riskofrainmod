package io.github.colochampre.riskofrain_items.events;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import io.github.colochampre.riskofrain_items.items.TougherTimesItem;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModCommonEvents {

  @Mod.EventBusSubscriber(modid = RoRitems.MODID)
  public static class ForgeEvents {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void tougherTimesProc(LivingDamageEvent event) {
      if (event.getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.TOUGHER_TIMES.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        boolean proc = false;
        double chance = (1.0D - 1.0D / (1.0D + 0.15D * (double) total) - 0.01D) * 100.0D;
        int random = RandomSource.create().nextInt(99);
        Level level = player.getLevel();
        if (random <= chance) {
          proc = true;
        }
        if (!proc) {
          return;
        }
        event.setAmount(0);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), TougherTimesItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
      }
    }
  }
  /*
  @Mod.EventBusSubscriber(modid = RoRitems.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
  public static class ModEventBusEvents {
  }
  */
}
package io.github.colochampre.riskofrain_items.events;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import io.github.colochampre.riskofrain_items.items.CrowbarItem;
import io.github.colochampre.riskofrain_items.items.InfusionItem;
import io.github.colochampre.riskofrain_items.items.TougherTimesItem;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ModCommonEvents {

  @Mod.EventBusSubscriber(modid = RoRitems.MODID)
  public static class ForgeEvents {

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void coinPickupSound(EntityItemPickupEvent event) {
      Player player = event.getEntity();
      ItemStack itemStack = event.getItem().getItem();
      Item item = itemStack.getItem();
      if (item == Items.GOLD_NUGGET || item == Items.RAW_GOLD || item == Items.GOLD_INGOT) {
        Level level = player.getLevel();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.COIN_PROC.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void crowBarProc(LivingDamageEvent event) {
      if (event.getSource().getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.CROWBAR.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        boolean canProc = false;
        LivingEntity livingentity = event.getEntity();
        float currentHp = livingentity.getHealth();
        float maxHp = livingentity.getMaxHealth();
        if ((double) currentHp >= (double) maxHp * 0.9D) {
          canProc = true;
        }
        if (!canProc) {
          return;
        }
        Level level = player.getLevel();
        float damage = event.getAmount();
        double damageMultiplier = 1.0D + 0.5D * (double) total;
        float crowbarDamage = damage * (float) damageMultiplier;
        event.setAmount(crowbarDamage);
        level.playSound(null, player.getX(), player.getY(), player.getZ(), CrowbarItem.getProcSound(), SoundSource.PLAYERS, (float) (damageMultiplier / 10), 1.0F);
      }
    }

    /*
    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void infusionProc(PlayerInteractEvent.RightClickItem event) {
      Player player = event.getEntity();
      Level level = event.getLevel();
      if (!player.isCreative()) {
        if (!level.isClientSide()) {
          if (event.getItemStack().getItem().equals(ItemInit.INFUSION.get())) {
            if (!player.getAbilities().instabuild) {
              event.getItemStack().shrink(1);
            }
            player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Infusion hp increase", 2.0, AttributeModifier.Operation.ADDITION));
            level.playSound(null, player.getX(), player.getY(), player.getZ(), InfusionItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
          }
        }
      }
    }
    */

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void infusionProc(LivingDeathEvent event) {
      if (event.getSource().getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.INFUSION.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.getLevel();
        if (!player.isCreative()) {
          if (!level.isClientSide()) {
            if (player.getAttribute(Attributes.MAX_HEALTH).getBaseValue() <= total * 10 * 2) {
              player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Infusion hp increase", 1.0, AttributeModifier.Operation.ADDITION));
              level.playSound(null, player.getX(), player.getY(), player.getZ(), InfusionItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
            }
          }
        }
      }
    }

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
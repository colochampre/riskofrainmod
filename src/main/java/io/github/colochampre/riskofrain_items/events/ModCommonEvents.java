package io.github.colochampre.riskofrain_items.events;

import com.google.common.collect.Sets;
import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.ItemInit;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import io.github.colochampre.riskofrain_items.items.*;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Enemy;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Set;

public class ModCommonEvents {

  @Mod.EventBusSubscriber(modid = RoRitems.MODID)
  public static class ForgeEvents {
    private static final Set<Item> GOLD_TYPE = Sets.newHashSet(Items.GOLD_INGOT, Items.GOLD_NUGGET, Items.RAW_GOLD);

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void coinPickupSound(EntityItemPickupEvent event) {
      Player player = event.getEntity();
      Level level = player.level();
      ItemStack itemStack = event.getItem().getItem();
      Item item = itemStack.getItem();
      if (!level.isClientSide()) {
        if (item == Items.GOLD_NUGGET || item == Items.RAW_GOLD || item == Items.GOLD_INGOT) {
          level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.COIN_PROC.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
        }
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
        Level level = player.level();
        if (!level.isClientSide()) {
          boolean canProc = false;
          LivingEntity livingentity = event.getEntity();
          float currentHp = livingentity.getHealth();
          float maxHp = livingentity.getMaxHealth();
          if ((double) currentHp >= (double) maxHp * 0.8D) {
            canProc = true;
          }
          if (!canProc) {
            return;
          }
          float damage = event.getAmount();
          double damageMultiplier = 1.2D + 0.3D * (double) total;
          double procVolume = damageMultiplier / 10 > 10 ? 10 : damageMultiplier / 10;
          float crowbarDamage = damage * (float) damageMultiplier;
          event.setAmount(crowbarDamage);
          level.playSound(null, player.getX(), player.getY(), player.getZ(), CrowbarItem.getProcSound(), SoundSource.PLAYERS, (float) procVolume, 1.0F);
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void goldenGunProc(LivingDamageEvent event) {
      if (event.getSource().getEntity() instanceof Player player) {
        int total = 0;
        int gold = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.GOLDEN_GUN.get())) {
            total += stack.getCount();
            found = true;
          }
          if (GOLD_TYPE.contains(inv.getItem(i).getItem())) {
            if (stack.getItem().equals(Items.GOLD_INGOT)) {
              gold += stack.getCount() * 9;
            } else if (stack.getItem().equals(Items.RAW_GOLD)) {
              gold += stack.getCount() * 6;
            } else {
              gold += stack.getCount();
            }
          }
          if (gold == 0) {
            found = false;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.level();
        if (!level.isClientSide()) {
          float damage = event.getAmount();
          double neededGoldForMaxAmplifier = total > 9 ? 9 : (2304 / Math.pow(2, total)) * 2;
          double damageAmplifier = gold > neededGoldForMaxAmplifier ? 40.0D : Mth.floor(gold * 40 / neededGoldForMaxAmplifier);
          float goldenGunDamage = damage * ((float) damageAmplifier / 100 + 1);
          event.setAmount(goldenGunDamage);
          level.playSound(null, player.getX(), player.getY(), player.getZ(), GoldenGunItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
        }
      }
    }

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
        Level level = player.level();
        if (!player.isCreative()) {
          if (!level.isClientSide()) {
            if (player.getMaxHealth() < 20 + (total * 10 * 2)) {
              player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Infusion hp increase", 1.0, AttributeModifier.Operation.ADDITION));
              level.playSound(null, player.getX(), player.getY(), player.getZ(), InfusionItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
            }
          }
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void lensMakersGlassesProc(LivingDamageEvent event) {
      if (event.getSource().getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.LENS_MAKERS_GLASSES.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.level();
        if (!level.isClientSide()) {
          boolean proc = false;
          double chance = 1.5625D * (double) total - 1.0D;
          int random = RandomSource.create().nextInt(99);
          if (random <= chance) {
            proc = true;
          }
          if (!proc) {
            return;
          }
          float damage = event.getAmount();
          float critDamage = damage * 2.0F;
          event.setAmount(critDamage);
          level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.LENS_CRIT_PROC.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void repulsionArmorPlateProc(LivingDamageEvent event) {
      if (event.getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.REPULSION_ARMOR_PLATE.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.level();
        if (!level.isClientSide()) {
          float damage = event.getAmount();
          float reducedDamage = damage - total < 1 ? 1 : damage - total;
          event.setAmount(reducedDamage);
          level.playSound(null, player.getX(), player.getY(), player.getZ(), RepulsionArmorPlateItem.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F + (RandomSource.create().nextFloat() - RandomSource.create().nextFloat()) * 0.2F);
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void roseBucklerProc(LivingDamageEvent event) {
      if (event.getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.ROSE_BUCKLER.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.level();
        if (!level.isClientSide()) {
          if (player.isSprinting()) {
            level.playSound(null, player.getX(), player.getY(), player.getZ(), RoseBucklerItem.getProcSound(), SoundSource.PLAYERS, Math.min(0.4F * total, 8.0F), 1.0F + (RandomSource.create().nextFloat() - RandomSource.create().nextFloat()) * 0.2F);
          }
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void smartShopperProc(LivingDeathEvent event) {
      if (event.getSource().getEntity() instanceof Player player && event.getEntity() instanceof Enemy) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.SMART_SHOPPER.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        LivingEntity livingentity = event.getEntity();
        Level level = player.level();
        if (!level.isClientSide()) {
          ItemStack goldItem = new ItemStack(Items.GOLD_NUGGET, livingentity.getExperienceReward());
          ItemEntity goldItemEntity = new ItemEntity(level, livingentity.getX(), livingentity.getY(), livingentity.getZ(), goldItem);
          boolean proc = false;
          double chance = 4 * (double) total;
          int random = RandomSource.create().nextInt(99);
          if (random <= chance) {
            proc = true;
          }
          if (!proc) {
            return;
          }
          level.addFreshEntity(goldItemEntity);
          level.playSound(null, livingentity.getX(), livingentity.getY(), livingentity.getZ(), SoundInit.COIN_PROC.get(), SoundSource.PLAYERS, 0.4F, 1.0F);
        }
      }
    }

    @SubscribeEvent(priority = EventPriority.NORMAL)
    public static void topazBroochProc(LivingDeathEvent event) {
      if (event.getSource().getEntity() instanceof Player player) {
        int total = 0;
        boolean found = false;
        ItemStack stack;
        Inventory inv = player.getInventory();
        for (int i = 0; i <= 35; ++i) {
          stack = inv.getItem(i);
          if (inv.getItem(i).getItem().equals(ItemInit.TOPAZ_BROOCH.get())) {
            total += stack.getCount();
            found = true;
          }
        }
        if (!found) {
          return;
        }
        Level level = player.level();
        if (!level.isClientSide()) {
          if (total * 4 <= player.getMaxHealth()) {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 160, total - 1, true, false));
          } else {
            player.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 160, (int) player.getMaxHealth() / 4 - 1, true, false));
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
        Level level = player.level();
        if (!level.isClientSide()) {
          boolean proc = false;
          double chance = (1.0D - 1.0D / (1.0D + 0.15D * (double) total) - 0.01D) * 100.0D;
          int random = RandomSource.create().nextInt(99);
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
  }
  /*
  @Mod.EventBusSubscriber(modid = RoRitems.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
  public static class ModEventBusEvents {
  }
  */
}
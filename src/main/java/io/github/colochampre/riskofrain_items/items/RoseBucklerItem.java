package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;

import java.util.List;

public class RoseBucklerItem extends Item {
  public RoseBucklerItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.rose_buckler.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.rose_buckler.tooltip_2"));
    tooltip.add(Component.translatable("item.riskofrain_items.rose_buckler.tooltip_3"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  @Override
  public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
    int amount = stack.getCount();
    LivingEntity livingentity = (LivingEntity) entity;
    if (!level.isClientSide()) {
      if (livingentity.isSprinting()) {
        livingentity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 2, Math.min(amount - 1, 3), true, false));
      }
    }
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);
  }

  public static SoundEvent getProcSound() {
    return SoundInit.ROSE_BUCKLER_PROC.get();
  }
}

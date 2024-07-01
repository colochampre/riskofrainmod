package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class LensMakersGlassesItem extends Item {

  public LensMakersGlassesItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, TooltipContext level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.lens_makers_glasses.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.lens_makers_glasses.tooltip_2"));
    tooltip.add(Component.translatable("item.riskofrain_items.lens_makers_glasses.tooltip_3"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  public static SoundEvent getProcSound() {
    return SoundInit.LENS_CRIT_PROC.get();
  }
}

package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class TougherTimesItem extends Item {

  public TougherTimesItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.tougher_times.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.tougher_times.tooltip_2"));
    tooltip.add(Component.translatable("item.riskofrain_items.tougher_times.tooltip_3"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  public static SoundEvent getProcSound() {
    return SoundInit.TOUGHER_TIMES_PROC.get();
  }
}

package io.github.colochampre.riskofrain_items.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SmartShopperItem extends Item {

  public SmartShopperItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.smart_shopper.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.smart_shopper.tooltip_2"));
    tooltip.add(Component.translatable("item.riskofrain_items.smart_shopper.tooltip_3"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }
}

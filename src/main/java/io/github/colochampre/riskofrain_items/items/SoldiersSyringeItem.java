package io.github.colochampre.riskofrain_items.items;

import net.minecraft.network.chat.Component;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoldiersSyringeItem extends Item {

  public SoldiersSyringeItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.soldiers_syringe.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.soldiers_syringe.tooltip_2"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  @Override
  public void inventoryTick(ItemStack stack, Level level, @NotNull Entity entity, int itemSlot, boolean isSelected) {
    int amount = stack.getCount();
    Player player = (Player) entity;
    if (!level.isClientSide()) {
      player.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 2, (int) Math.ceil(amount / 4), true, false));
    }
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);
  }
}

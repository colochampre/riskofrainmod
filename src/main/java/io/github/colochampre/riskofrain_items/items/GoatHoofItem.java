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
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class GoatHoofItem extends Item {

  public GoatHoofItem(Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
    tooltip.add(Component.translatable("item.riskofrain_items.goat_hoof.tooltip_1"));
    tooltip.add(Component.translatable("item.riskofrain_items.goat_hoof.tooltip_2"));
    super.appendHoverText(itemstack, level, tooltip, flag);
  }

  @Override
  public void inventoryTick(ItemStack stack, Level level, Entity entity, int itemSlot, boolean isSelected) {
    int amount = stack.getCount();
    Player player = (Player) entity;
    if (!level.isClientSide()) {
      if (player.isSprinting()) {
        player.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 2, (int) Math.ceil(amount / 4), true, false));
      }
    }
    super.inventoryTick(stack, level, entity, itemSlot, isSelected);
  }
}

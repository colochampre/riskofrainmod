package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class InfusionItem extends Item {

  public static final Component INFUSION_TOOLTIP_1 = Component.translatable("item.riskofrain_items.infusion.tooltip_1")
          .withStyle(ChatFormatting.WHITE).withStyle(Style.EMPTY.withItalic(true));
  public static final Component INFUSION_TOOLTIP_2 = Component.translatable("item.riskofrain_items.infusion.tooltip_2")
          .withStyle(ChatFormatting.GREEN).withStyle(Style.EMPTY.withBold(true));

  public InfusionItem(Item.Properties props) {
    super(props);
  }

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
    super.appendHoverText(itemstack, level, components, flag);
    components.add(Component.translatable("item.riskofrain_items.infusion.tooltip_1").append(INFUSION_TOOLTIP_2));
  }

  public static SoundEvent getProcSound() {
    return SoundInit.INFUSION_PROC.get();
  }
}

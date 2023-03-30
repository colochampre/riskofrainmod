package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.Style;
import net.minecraft.network.chat.TextColor;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.UUID;

public class InfusionItem extends Item {

  //private static final UUID INFUSION_HEALTH_ID = UUID.fromString("E11710C8-4247-4CB6-B3B5-729CB34CFC1A");
  public static final Component INFUSION_TOOLTIP_1 = Component.translatable("item.riskofrain_items.infusion.tooltip_1")
          .withStyle(Style.EMPTY.withItalic(true));
  public static final Component INFUSION_TOOLTIP_2 = Component.translatable("item.riskofrain_items.infusion.tooltip_2")
          .withStyle(ChatFormatting.GREEN).withStyle(Style.EMPTY.withBold(true));

  public InfusionItem(Item.Properties props) {
    super(props);
  }
/*
  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    if (!player.isCreative()) {
      if (!level.isClientSide()) {
        if (!player.getAbilities().instabuild) {
          itemstack.shrink(1);
        }
        //AttributeModifier INFUSION_MODIFIER = new AttributeModifier("Infusion hp increase", 2.0F, AttributeModifier.Operation.ADDITION);
        player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("Infusion hp increase", 2.0, AttributeModifier.Operation.ADDITION));
        //player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(INFUSION_MODIFIER);
        RoRitems.LOGGER.info("item right clicked");
        level.playSound(null, player.getX(), player.getY(), player.getZ(), this.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
        return InteractionResultHolder.consume(itemstack);
      } else {
        return InteractionResultHolder.success(itemstack);
      }
    }
    return InteractionResultHolder.pass(itemstack);
  }*/

  @Override
  public void appendHoverText(ItemStack itemstack, @Nullable Level level, List<Component> components, TooltipFlag flag) {
    super.appendHoverText(itemstack, level, components, flag);
    if (flag.isCreative()) {
      components.add(INFUSION_TOOLTIP_1);
      components.add(INFUSION_TOOLTIP_2);
    }
  }

  public static SoundEvent getProcSound() {
    return SoundInit.INFUSION_PROC.get();
  }
}

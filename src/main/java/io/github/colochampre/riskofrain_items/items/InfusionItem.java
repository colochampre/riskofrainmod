package io.github.colochampre.riskofrain_items.items;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.init.SoundInit;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

import java.util.Objects;

public class InfusionItem extends Item {
  private static final AttributeModifier MAX_HP_MODIFIER = new AttributeModifier("healthBoost", 2.0D, AttributeModifier.Operation.ADDITION);

  public InfusionItem(Properties props) {
    super(props);
  }

  @Override
  public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
    ItemStack itemstack = player.getItemInHand(hand);
    //double maxHp = player.getAttributeValue(Attributes.MAX_HEALTH);
    if (!level.isClientSide) {
      player.getAttribute(Attributes.MAX_HEALTH).addPermanentModifier(new AttributeModifier("healthBoost", 2.0F, AttributeModifier.Operation.ADDITION));
      RoRitems.LOGGER.info("item right clicked");
      if (!player.getAbilities().instabuild) {
        itemstack.shrink(1);
      }
    }
    level.playSound(null, player.getX(), player.getY(), player.getZ(), this.getProcSound(), SoundSource.PLAYERS, 0.4F, 1.0F);
    return InteractionResultHolder.sidedSuccess(itemstack, level.isClientSide());
  }

  public SoundEvent getProcSound() {
    return SoundInit.INFUSION_PROC.get();
  }
}

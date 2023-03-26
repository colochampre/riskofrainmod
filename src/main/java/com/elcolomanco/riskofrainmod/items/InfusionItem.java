package com.elcolomanco.riskofrainmod.items;

import java.util.List;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.setup.ModSetup;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.ai.attributes.AttributeModifier.Operation;
import net.minecraft.entity.ai.attributes.Attributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class InfusionItem extends Item {
	
	public InfusionItem(Properties properties) {
		super(new Item.Properties()
				.maxStackSize(64)
				.group(ModSetup.RIKSOFRAIN_GROUP));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.infusion.tooltip1"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.infusion.tooltip2"));
	}
	
	@Override
	public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
		ItemStack itemStack = playerIn.getHeldItem(handIn);
		double maxHealth = playerIn.getAttribute(Attributes.MAX_HEALTH).getValue();
		if (!worldIn.isRemote) {
			if (maxHealth < RoRconfig.INFUSION_CAP) {
				playerIn.getAttribute(Attributes.MAX_HEALTH).applyPersistentModifier(new AttributeModifier("healthBoost", 2.0D, Operation.ADDITION));
				if (!playerIn.abilities.isCreativeMode) {
					itemStack.shrink(1);
				}
			}
		}
		worldIn.playSound(playerIn, playerIn.getPosition(), this.getInfusionProcSound(), SoundCategory.NEUTRAL, 0.3F, 1.0F);
		return ActionResult.resultSuccess(itemStack);
	}
	
	protected SoundEvent getInfusionProcSound() {
		return RegistrySetup.INFUSION_PROC.get();
	}
}

package com.elcolomanco.riskofrainmod.items;

import java.util.List;

import com.elcolomanco.riskofrainmod.setup.ModSetup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class CrowbarItem extends Item {
	
	private static int ammount;
	private static double crowbarDamage;
	
	public CrowbarItem(Properties properties) {
		super(new Item.Properties()
				.maxStackSize(64)
				.group(ModSetup.RIKSOFRAIN_GROUP));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.crowbar.tooltip1"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.crowbar.tooltip2"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.crowbar.tooltip3"));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		ammount = stack.getCount();
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	public static double getDamageMultiplier() {
		if (ammount > 0) {
			crowbarDamage = 1.0 + (0.5 * ammount);
		}
		return crowbarDamage;
	}
}

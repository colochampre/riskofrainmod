package com.elcolomanco.riskofrainmod.items;

import java.util.List;

import com.elcolomanco.riskofrainmod.setup.ModSetup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class RoseBucklerItem extends Item {
	
	private int amplifier;
	private static int ammount;

	public RoseBucklerItem(Properties properties) {
		super(new Item.Properties()
				.maxStackSize(64)
				.group(ModSetup.RIKSOFRAIN_GROUP));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.rose_buckler.tooltip1"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.rose_buckler.tooltip2"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.rose_buckler.tooltip3"));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		ammount = stack.getCount();
		
		if (!worldIn.isRemote) {
			if (entityIn instanceof LivingEntity) {
				if (this.isActive() && entityIn.isSprinting()) {
					((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.RESISTANCE, 2, this.getAmplifier(), true, false));
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	private boolean isActive() {
		if (ammount < 4) {
			return false;
		} else {
			return true;
		}
	}
	
	public int getAmplifier() {
		if (ammount >= 4 && ammount < 8) { amplifier = 0; }
		else if (ammount >= 8 && ammount < 12) { amplifier = 1; }
		else if (ammount >= 12 && ammount < 16) { amplifier = 2; }
		else if (ammount >= 16) { amplifier = 3; }
		return amplifier;
	}
	
	public static int getAmmount() {
		return ammount;
	}
}

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

public class SoldiersSyringeItem extends Item {
	
	private int ammount;
	private int amplifier;
	
	public SoldiersSyringeItem(Properties properties) {
		super(new Item.Properties()
				.maxStackSize(64)
				.group(ModSetup.RIKSOFRAIN_GROUP));
	}
	
	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.soldiers_syringe.tooltip1"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.soldiers_syringe.tooltip2"));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {
		ammount = stack.getCount();
		
		if (!worldIn.isRemote) {
			if (entityIn instanceof LivingEntity) {
				((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.HASTE, 2, this.getAmplifier(), true, false));
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
	
	private int getAmplifier() {
		if (ammount > 0 && ammount < 4) { amplifier = 0; }
		else if (ammount >= 4 && ammount < 8) { amplifier = 1; }
		else if (ammount >= 8 && ammount < 12) { amplifier = 2; }
		else if (ammount >= 12 && ammount < 16) { amplifier = 3; }
		else if (ammount >= 16 && ammount < 20) { amplifier = 4; }
		else if (ammount >= 20 && ammount < 24) { amplifier = 5; }
		else if (ammount >= 24 && ammount < 28) { amplifier = 6; }
		else if (ammount >= 28 && ammount < 32) { amplifier = 7; }
		else if (ammount >= 32 && ammount < 36) { amplifier = 8; }
		else if (ammount >= 36 && ammount < 40) { amplifier = 9; }
		else if (ammount >= 40 && ammount < 44) { amplifier = 10; }
		else if (ammount >= 44 && ammount < 48) { amplifier = 11; }
		else if (ammount >= 48 && ammount < 52) { amplifier = 12; }
		else if (ammount >= 52 && ammount < 56) { amplifier = 13; }
		else if (ammount >= 56 && ammount < 60) { amplifier = 14; }
		else if (ammount >= 60 && ammount < 64) { amplifier = 15; }
		else if (ammount == 64) { amplifier = 16; }
		return amplifier;
	}
}

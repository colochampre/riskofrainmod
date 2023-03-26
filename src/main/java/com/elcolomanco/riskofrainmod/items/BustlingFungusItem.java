package com.elcolomanco.riskofrainmod.items;

import java.util.List;

import com.elcolomanco.riskofrainmod.setup.ModSetup;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;

public class BustlingFungusItem extends Item {

	private int ammount;
	private int timer = -40;

	public BustlingFungusItem(Properties properties) {
		super(new Item.Properties()
				.maxStackSize(64)
				.group(ModSetup.RIKSOFRAIN_GROUP));
	}

	@Override
	public void addInformation(ItemStack stack, World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.bustling_fungus.tooltip1"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.bustling_fungus.tooltip2"));
		tooltip.add(new TranslationTextComponent("item.riskofrainmod.bustling_fungus.tooltip3"));
	}
	
	@Override
	public void inventoryTick(ItemStack stack, World worldIn, Entity entityIn, int itemSlot, boolean isSelected) {

		ammount = stack.getCount();
		SoundCategory soundcategory = entityIn instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
		
		if (!worldIn.isRemote) {
			if (entityIn instanceof LivingEntity ) {
				if (isEntityMoving(entityIn)) {
					timer = -40;
				} else {
					++timer;
				}
				if (timer == -1) {
					worldIn.playSound((PlayerEntity)null, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), RegistrySetup.MUSHROOM_PROC_1.get(), soundcategory, 0.5F, 1.0F);
					worldIn.playSound((PlayerEntity)null, entityIn.getPosX(), entityIn.getPosY(), entityIn.getPosZ(), RegistrySetup.MUSHROOM_PROC_2.get(), soundcategory, 0.5F, 1.0F);
				}
				if (timer >= 0) {
					if (ammount > 0 && ammount < 4) {
						if (timer == 0 || timer == 50) {
						((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 51, 0, true, true));
						timer = 0;
						}
					} else if (ammount >= 4 && ammount < 8) {
						if (timer == 0 || timer == 25) {
						((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 26, 1, true, true));
						timer = 0;
						}
					} else if (ammount >= 8 && ammount < 12) {
						if (timer == 0 || timer == 12) {
						((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 13, 2, true, true));
						timer = 0;
						}
					} else if (ammount >= 12 && ammount < 16) {
						if (timer == 0 || timer == 6) {
						((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 7, 3, true, true));
						timer = 0;
						}
					} else if (ammount >= 16) {
						if (timer == 0 || timer == 3) {
						((LivingEntity)entityIn).addPotionEffect(new EffectInstance(Effects.REGENERATION, 4, 4, true, true));
						timer = 0;
						}
					}
				}
			}
		}
		super.inventoryTick(stack, worldIn, entityIn, itemSlot, isSelected);
	}
		
	private double prevX = 0.0;
	private double prevY = 0.0;
	private double prevZ = 0.0;
		
	private boolean isEntityMoving(Entity entity) {
		boolean result;
		LivingEntity livingEntity = (LivingEntity)entity;

		if(prevX != livingEntity.getPosX() || prevY != livingEntity.getPosY() || prevZ != livingEntity.getPosZ()) {
			result = true;
		} else {
			result = false;
		}
		prevX = entity.getPosX();
		prevY = entity.getPosY();
		prevZ = entity.getPosZ();
		return result;
	}
}

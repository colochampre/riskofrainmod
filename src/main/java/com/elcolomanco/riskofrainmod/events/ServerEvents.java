package com.elcolomanco.riskofrainmod.events;

import java.util.Random;

import com.elcolomanco.riskofrainmod.RoRconfig;
import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;
import com.elcolomanco.riskofrainmod.items.CrowbarItem;
import com.elcolomanco.riskofrainmod.items.LensMakersGlassesItem;
import com.elcolomanco.riskofrainmod.items.RoseBucklerItem;
import com.elcolomanco.riskofrainmod.items.TopazBroochItem;
import com.elcolomanco.riskofrainmod.items.TougherTimesItem;
import com.elcolomanco.riskofrainmod.setup.RegistrySetup;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent.LevelChange;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ServerEvents {
	private static Random rand = new Random();
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onAdvancement(AdvancementEvent event) {
		if (RoRconfig.SOUNDS) {
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity playerIn = (PlayerEntity)event.getEntityLiving();
				World worldIn = playerIn.getEntityWorld();
				SoundCategory soundcategory = playerIn instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
				
				worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), RegistrySetup.ADVANCEMENT_PROC.get(), soundcategory, 0.15F, 1.0F);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onCoinPickUp(EntityItemPickupEvent event) {
		if (RoRconfig.SOUNDS) {
			if (event.getEntityLiving() instanceof PlayerEntity) {
				ItemStack itemStack = event.getItem().getItem();
				Item item = itemStack.getItem();
				if (item == Items.GOLD_NUGGET) {
					PlayerEntity playerIn = (PlayerEntity)event.getEntityLiving();
					World worldIn = playerIn.getEntityWorld();
					SoundCategory soundcategory = playerIn instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
					
					worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), RegistrySetup.COIN_PROC.get(), soundcategory, 0.4F, 1.0F);
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onLevelUp(LevelChange event) {
		if (RoRconfig.SOUNDS) {
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity playerIn = (PlayerEntity)event.getEntityLiving();
				World worldIn = playerIn.getEntityWorld();
				SoundCategory soundcategory = playerIn instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
				
				worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), RegistrySetup.LEVEL_UP_PROC.get(), soundcategory, 0.6F, 1.0F);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void onPlayerDeath(LivingDeathEvent event) {
		if (RoRconfig.SOUNDS) {
			if (event.getEntityLiving() instanceof PlayerEntity) {
				PlayerEntity playerIn = (PlayerEntity)event.getEntityLiving();
				World worldIn = playerIn.getEntityWorld();
				SoundCategory soundcategory = playerIn instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
				
				worldIn.playSound((PlayerEntity)null, playerIn.getPosX(), playerIn.getPosY(), playerIn.getPosZ(), RegistrySetup.PLAYER_DEATH_PROC.get(), soundcategory, 1.0F, 1.0F);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void inmuneDrones(LivingAttackEvent event) {
		if (event.getEntityLiving() instanceof GunnerDroneEntity) {
			if (event.getSource().getTrueSource() instanceof LivingEntity) {
				boolean tamed = false;
				GunnerDroneEntity drone = (GunnerDroneEntity)event.getEntityLiving();
				if (drone.isTamed()) {
					tamed = true;
				}
				if (tamed) {
					return;
				}
				if (event.isCancelable()) {
					event.setCanceled(true);
				}
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void crowbarEffect(LivingDamageEvent event) {
		if (event.getSource().getTrueSource() instanceof PlayerEntity) {
			boolean found = false;
			PlayerEntity killer = (PlayerEntity)event.getSource().getTrueSource();
			PlayerInventory inv = killer.inventory;
			
			for(int n = 0; n <= 35; ++n) {
				if (inv.getStackInSlot(n).getItem().equals(RegistrySetup.CROWBAR.get())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return;
			}
			boolean proc = false;
			LivingEntity entity = (LivingEntity)event.getEntityLiving();
			float currentHp = entity.getHealth();
			float maxHp = entity.getMaxHealth();
			if (currentHp >= (maxHp * 0.9)) {
				proc = true;
			}
			if (!proc) {
				return;
			}
			float damage = event.getAmount();
			double multiplier = CrowbarItem.getDamageMultiplier();
			float crowbarDamage = (float)(damage * multiplier);
			World worldIn = killer.getEntityWorld();
			SoundCategory soundcategory = killer instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
			
			event.setAmount(crowbarDamage);
			worldIn.playSound((PlayerEntity)null, killer.getPosX(), killer.getPosY(), killer.getPosZ(), RegistrySetup.CROWBAR_PROC.get(), soundcategory, 0.4F, rand.nextFloat() * 0.1F + 0.9F);
		}
	}

	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void lensMakersGlassesEffect(LivingDamageEvent event) {
		
		if (event.getSource().getTrueSource() instanceof PlayerEntity) {
			boolean found = false;
			PlayerEntity killer = (PlayerEntity)event.getSource().getTrueSource();
			PlayerInventory inv = killer.inventory;
			
			for(int n = 0; n <= 35; ++n) {
				if (inv.getStackInSlot(n).getItem().equals(RegistrySetup.LENS_MARKS_GLASSES.get())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return;
			}
			boolean isCrit = false;
			double chance = LensMakersGlassesItem.getChance();
			float damage = event.getAmount();
			float crit = damage * 2;
			int random = LensMakersGlassesItem.getRandomCrit();
			World worldIn = killer.getEntityWorld();
			SoundCategory soundcategory = killer instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
			
			if (random <= chance) {
				isCrit = true;
			}
			if (!isCrit) {
				return;
			}
			event.setAmount(crit);
			worldIn.playSound((PlayerEntity)null, killer.getPosX(), killer.getPosY(), killer.getPosZ(), RegistrySetup.LENS_CRIT_PROC.get(), soundcategory, 0.4F, rand.nextFloat() * 0.1F + 0.9F);
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void roseBucklerEffect(LivingDamageEvent event) {
		
		if (event.getEntityLiving() instanceof PlayerEntity) {
			boolean found = false;
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			PlayerInventory inv = player.inventory;
			
			for(int n = 0; n <= 35; ++n) {
				if (inv.getStackInSlot(n).getItem().equals(RegistrySetup.ROSE_BUCKLER.get())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return;
			}
			int ammount = RoseBucklerItem.getAmmount();
			World worldIn = player.getEntityWorld();
			SoundCategory soundcategory = player instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
			if (player.isSprinting() && ammount >= 4) {
				worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), RegistrySetup.ROSE_BUCKLER_PROC.get(), soundcategory, 0.4F, 1.0F);
			}
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void topazBroochEffect(LivingDeathEvent event) {
		
		if (event.getSource().getTrueSource() instanceof PlayerEntity) {
			boolean found = false;
			PlayerEntity killer = (PlayerEntity)event.getSource().getTrueSource();
			PlayerInventory inv = killer.inventory;
			
			for(int n = 0; n <= 35; ++n) {
				if (inv.getStackInSlot(n).getItem().equals(RegistrySetup.TOPAZ_BROOCH.get())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return;
			}
			(killer).addPotionEffect(new EffectInstance(Effects.ABSORPTION, 160, TopazBroochItem.getAmplifier(), true, false));
		}
	}
	
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void tougherTimesEffect(LivingDamageEvent event) {
		
		if (event.getEntityLiving() instanceof PlayerEntity) {
			boolean found = false;
			PlayerEntity player = (PlayerEntity)event.getEntityLiving();
			PlayerInventory inv = player.inventory;
			
			for(int n = 0; n <= 35; ++n) {
				if (inv.getStackInSlot(n).getItem().equals(RegistrySetup.TOUGHER_TIMES.get())) {
					found = true;
					break;
				}
			}
			if (!found) {
				return;
			}
			boolean isBlocking = false;
			double chance = TougherTimesItem.getChance();
			float damage = event.getAmount();
			float blocked = damage - damage;
			int random = TougherTimesItem.getRandomBlockChance();
			World worldIn = player.getEntityWorld();
			SoundCategory soundcategory = player instanceof PlayerEntity ? SoundCategory.PLAYERS : SoundCategory.NEUTRAL;
			
			if (random <= chance) {
				isBlocking = true;
			}
			if (!isBlocking) {
				return;
			}
			event.setAmount(blocked);
			worldIn.playSound((PlayerEntity)null, player.getPosX(), player.getPosY(), player.getPosZ(), RegistrySetup.TOUGHER_TIMES_PROC.get(), soundcategory, 0.4F, 1.0F);
		}
	}
}

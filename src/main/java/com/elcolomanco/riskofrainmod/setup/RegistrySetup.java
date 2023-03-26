package com.elcolomanco.riskofrainmod.setup;

import java.util.List;

import com.elcolomanco.riskofrainmod.RoRmod;
import com.elcolomanco.riskofrainmod.entities.GunnerDroneEntity;
import com.elcolomanco.riskofrainmod.entities.IronNuggetEntity;
import com.elcolomanco.riskofrainmod.entities.LemurianEntity;
import com.elcolomanco.riskofrainmod.entities.StoneGolemEntity;
import com.elcolomanco.riskofrainmod.items.BustlingFungusItem;
import com.elcolomanco.riskofrainmod.items.CrowbarItem;
import com.elcolomanco.riskofrainmod.items.GoatHoofItem;
import com.elcolomanco.riskofrainmod.items.GunnerDroneSpawnEggItem;
import com.elcolomanco.riskofrainmod.items.InfusionItem;
import com.elcolomanco.riskofrainmod.items.LemurianSpawnEggItem;
import com.elcolomanco.riskofrainmod.items.LensMakersGlassesItem;
import com.elcolomanco.riskofrainmod.items.RoseBucklerItem;
import com.elcolomanco.riskofrainmod.items.SoldiersSyringeItem;
import com.elcolomanco.riskofrainmod.items.StoneGolemSpawnEggItem;
import com.elcolomanco.riskofrainmod.items.TopazBroochItem;
import com.elcolomanco.riskofrainmod.items.TougherTimesItem;
import static com.elcolomanco.riskofrainmod.RoRmod.MODID;

import com.google.common.collect.Lists;

import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class RegistrySetup {
	
	private static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RoRmod.MODID);
	private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
	public  static final List<RegistryObject<Item>> SPAWN_EGGS = Lists.newArrayList();
	private static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITIES, RoRmod.MODID);
	
	public static void init() {
		SOUNDS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
		ENTITY_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
	
	public static final RegistryObject<Item> BUSTLING_FUNGUS	= ITEMS.register("bustling_fungus",		() -> new BustlingFungusItem(new Item.Properties()));
	public static final RegistryObject<Item> COMMON_CHEST		= ITEMS.register("common_chest",		() -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> CROWBAR			= ITEMS.register("crowbar",				() -> new CrowbarItem(new Item.Properties()));
//	public static final RegistryObject<Item> FRESH_MEAT			= ITEMS.register("fresh_meat",			() -> new FreshMeatItem(new Item.Properties()));
	public static final RegistryObject<Item> GOAT_HOOF			= ITEMS.register("goat_hoof",			() -> new GoatHoofItem(new Item.Properties()));
	public static final RegistryObject<Item> LENS_MARKS_GLASSES	= ITEMS.register("lens_makers_glasses",	() -> new LensMakersGlassesItem(new Item.Properties()));
//	public static final RegistryObject<Item> MEDKIT				= ITEMS.register("medkit",				() -> new MedkitItem(new Item.Properties()));
	public static final RegistryObject<Item> SOLDIERS_SYRINGE	= ITEMS.register("soldiers_syringe",	() -> new SoldiersSyringeItem(new Item.Properties()));
	public static final RegistryObject<Item> TOPAZ_BROOCH		= ITEMS.register("topaz_brooch",		() -> new TopazBroochItem(new Item.Properties()));
	public static final RegistryObject<Item> TOUGHER_TIMES		= ITEMS.register("tougher_times",		() -> new TougherTimesItem(new Item.Properties()));
	public static final RegistryObject<Item> INFUSION			= ITEMS.register("infusion",			() -> new InfusionItem(new Item.Properties()));
	public static final RegistryObject<Item> ROSE_BUCKLER		= ITEMS.register("rose_buckler",		() -> new RoseBucklerItem(new Item.Properties()));
//	public static final RegistryObject<Item> UKULELE			= ITEMS.register("ukulele",				() -> new UkuleleItem(new Item.Properties()));
	
	public static final RegistryObject<Item> LEMURIAN_SPAWN_EGG     = ITEMS.register("lemurian_spawn_egg", LemurianSpawnEggItem::new);
	public static final RegistryObject<Item> STONE_GOLEM_SPAWN_EGG  = ITEMS.register("stone_golem_spawn_egg", StoneGolemSpawnEggItem::new);
	public static final RegistryObject<Item> GUNNER_DRONE_SPAWN_EGG = ITEMS.register("gunner_drone_spawn_egg", GunnerDroneSpawnEggItem::new);
	
	public static final RegistryObject<EntityType<GunnerDroneEntity>> GUNNER_DRONE = ENTITY_TYPES.register("gunner_drone", () -> EntityType.Builder.create(GunnerDroneEntity::new, EntityClassification.CREATURE)
			.size(0.75F, 1.15F)
			.build("gunner_drone"));
	public static final RegistryObject<EntityType<IronNuggetEntity>> DRONE_BULLET_ENTITY = ENTITY_TYPES.register("drone_bullet", () -> EntityType.Builder.<IronNuggetEntity>create(IronNuggetEntity::new, EntityClassification.MISC)
			.size(0.25F, 0.25F)
			.build("drone_bullet"));
	public static final RegistryObject<EntityType<LemurianEntity>> LEMURIAN = ENTITY_TYPES.register("lemurian", () -> EntityType.Builder.create(LemurianEntity::new, EntityClassification.MONSTER)
			.size(0.7F, 1.72F)
			.build("lemurian"));
	public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM = ENTITY_TYPES.register("stone_golem", () -> EntityType.Builder.create(StoneGolemEntity::new, EntityClassification.MONSTER)
			.size(1.85F, 3.95F)
			.build("stone_golem"));
	
	public static final RegistryObject<SoundEvent> DRONE_DEATH1		  = SOUNDS.register("entity.drone.death1", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.drone.death1")));
	public static final RegistryObject<SoundEvent> DRONE_DEATH2		  = SOUNDS.register("entity.drone.death2", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.drone.death2")));
	public static final RegistryObject<SoundEvent> DRONE_FLYING		  = SOUNDS.register("entity.drone.flying", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.drone.flying")));
	public static final RegistryObject<SoundEvent> DRONE_REPAIR		  = SOUNDS.register("entity.drone.repair", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.drone.repair")));
	public static final RegistryObject<SoundEvent> GUNNER_DRONE_SHOOT = SOUNDS.register("entity.gunner_drone.shoot", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.gunner_drone.shoot")));
	
	public static final RegistryObject<SoundEvent> LEMURIAN_AMBIENT	= SOUNDS.register("entity.lemurian.ambient", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.lemurian.ambient")));
	public static final RegistryObject<SoundEvent> LEMURIAN_ATTACK	= SOUNDS.register("entity.lemurian.attack", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.lemurian.attack")));
	public static final RegistryObject<SoundEvent> LEMURIAN_DEATH	= SOUNDS.register("entity.lemurian.death", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.lemurian.death")));
	public static final RegistryObject<SoundEvent> LEMURIAN_HURT	= SOUNDS.register("entity.lemurian.hurt", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.lemurian.hurt")));
	public static final RegistryObject<SoundEvent> LEMURIAN_STEP	= SOUNDS.register("entity.lemurian.step", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.lemurian.step")));
	
	public static final RegistryObject<SoundEvent> STONE_GOLEM_CLAP			= SOUNDS.register("entity.stone_golem.clap", ()			-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.clap")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_DEATH		= SOUNDS.register("entity.stone_golem.death", ()		-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.death")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_DEATH_VOICE	= SOUNDS.register("entity.stone_golem.death_voice", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.death_voice")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_HURT			= SOUNDS.register("entity.stone_golem.hurt", ()			-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.hurt")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_LASER_CHARGE = SOUNDS.register("entity.stone_golem.laser_charge", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.laser_charge")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_LASER_FIRE	= SOUNDS.register("entity.stone_golem.laser_fire", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.laser_fire")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_SPAWN		= SOUNDS.register("entity.stone_golem.spawn", ()		-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.spawn")));
	public static final RegistryObject<SoundEvent> STONE_GOLEM_STEP			= SOUNDS.register("entity.stone_golem.step", ()			-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.stone_golem.step")));
	
	public static final RegistryObject<SoundEvent> ADVANCEMENT_PROC	 = SOUNDS.register("entity.advancement.proc", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.advancement.proc")));
	public static final RegistryObject<SoundEvent> LEVEL_UP_PROC	 = SOUNDS.register("entity.level_up.proc", ()		-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.level_up.proc")));
	public static final RegistryObject<SoundEvent> PLAYER_DEATH_PROC = SOUNDS.register("entity.player_death.proc", ()	-> new SoundEvent(new ResourceLocation(RoRmod.MODID, "entity.player_death.proc")));
	
	public static final RegistryObject<SoundEvent> COIN_PROC				= SOUNDS.register("interactive.coin.proc", 			 	  () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "interactive.coin.proc")));
	public static final RegistryObject<SoundEvent> INSUFFICIENT_FOUNDS_PROC = SOUNDS.register("interactive.insufficient_founds.proc", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "interactive.insufficient_founds.proc")));
	
	public static final RegistryObject<SoundEvent> CROWBAR_PROC			= SOUNDS.register("item.crowbar.proc", 		 	 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.crowbar.proc")));
	public static final RegistryObject<SoundEvent> INFUSION_PROC		= SOUNDS.register("item.infusion.proc", 		 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.infusion.proc")));
	public static final RegistryObject<SoundEvent> LENS_CRIT_PROC		= SOUNDS.register("item.lens_crit.proc",		 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.lens_crit.proc")));
	public static final RegistryObject<SoundEvent> MUSHROOM_PROC_1		= SOUNDS.register("item.bustling_fungus.proc_1", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.bustling_fungus.proc_1")));
	public static final RegistryObject<SoundEvent> MUSHROOM_PROC_2		= SOUNDS.register("item.bustling_fungus.proc_2", () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.bustling_fungus.proc_2")));
	public static final RegistryObject<SoundEvent> ROSE_BUCKLER_PROC	= SOUNDS.register("item.rose_buckler.proc", 	 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.rose_buckler.proc")));
	public static final RegistryObject<SoundEvent> TOUGHER_TIMES_PROC	= SOUNDS.register("item.tougher_times.proc",	 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.tougher_times.proc")));
//	public static final RegistryObject<SoundEvent> UKULELE_PROC			= SOUNDS.register("item.ukulele.proc",			 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.ukulele.proc")));
//	public static final RegistryObject<SoundEvent> UKULELE_SHOCK		= SOUNDS.register("item.ukulele.shock",			 () -> new SoundEvent(new ResourceLocation(RoRmod.MODID, "item.ukulele.shock")));
}

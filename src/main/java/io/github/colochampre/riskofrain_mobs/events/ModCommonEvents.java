package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModCommonEvents {

  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      SpawnPlacements.register(EntityInit.GUNNER_DRONE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerDroneEntity::canSpawn);
      SpawnPlacements.register(EntityInit.LEMURIAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LemurianEntity::canSpawn);
      SpawnPlacements.register(EntityInit.STONE_GOLEM_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, StoneGolemEntity::canSpawn);
    });
  }

  @SubscribeEvent
  public static void entityAttributes(EntityAttributeCreationEvent event) {
    event.put(EntityInit.GUNNER_DRONE_ENTITY.get(), GunnerDroneEntity.createAttributes().build());
    event.put(EntityInit.LEMURIAN_ENTITY.get(), LemurianEntity.createAttributes().build());
    event.put(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemEntity.createAttributes().build());
  }
}

package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.AbstractFlyingDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

public class ModCommonEvents {

  @Mod.EventBusSubscriber(modid = RoRmod.MODID)
  public static class ForgeEvents {

    @SubscribeEvent
    public static void playerDeathSound(LivingDeathEvent event) {
      if (event.getEntity() instanceof Player player) {
        Level level = player.level();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.PLAYER_DEATH.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
      }
    }

    @SubscribeEvent
    public static void immuneDrones(LivingAttackEvent event) {
      if (event.getEntity() instanceof AbstractFlyingDroneEntity drone && event.getSource().getDirectEntity() instanceof LivingEntity) {
        boolean isTamed = false;
        if (drone.isTame()) {
          isTamed = true;
        }
        if (isTamed) {
          return;
        }
        if (event.isCancelable()) {
          event.setCanceled(true);
        }
      }
    }
  }

  @Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
  public static class ModEventBusEvents {

    @SubscribeEvent
    public static void commonSetup(FMLCommonSetupEvent event) {
      event.enqueueWork(() -> {
        SpawnPlacements.register(EntityInit.LEMURIAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LemurianEntity::canSpawn);
        SpawnPlacements.register(EntityInit.STONE_GOLEM_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, StoneGolemEntity::canSpawn);
        SpawnPlacements.register(EntityInit.GUNNER_DRONE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerDroneEntity::checkDroneSpawnRules);
      });
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
      event.put(EntityInit.LEMURIAN_ENTITY.get(), LemurianEntity.createAttributes().build());
      event.put(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemEntity.createAttributes().build());
      event.put(EntityInit.GUNNER_DRONE_ENTITY.get(), GunnerDroneEntity.createAttributes().build());
    }
  }
}

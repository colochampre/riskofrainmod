package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.*;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import io.github.colochampre.riskofrain_mobs.network.packets.DifficultyChangeSoundPacket;
import net.minecraft.client.Minecraft;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.DifficultyChangeEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.network.PacketDistributor;

public class ModCommonEvents {

  @Mod.EventBusSubscriber(modid = RoRmod.MODID)
  public static class ForgeEvents {

    @SubscribeEvent
    public static void advancementsSound(AdvancementEvent event) {
      double d0 = RoRConfig.SERVER.ADVANCEMENT.get();
      if (d0 > 0 && Minecraft.getInstance().player != null) {
        Minecraft.getInstance().player.playSound(SoundInit.ADVANCEMENT.get(), (float) (d0 / 100), 1.0F);
      }
    }

    @SubscribeEvent
    public static void chatMessageSound(ClientChatReceivedEvent event) {
      double d0 = RoRConfig.SERVER.CHAT_MESSAGE.get();
      if (d0 > 0 && Minecraft.getInstance().player != null) {
        Minecraft.getInstance().player.playSound(SoundInit.CHAT_MESSAGE.get(), (float) (d0 / 100), 1.0F);
      }
    }

    @SubscribeEvent
    public static void difficultyChangeSound(DifficultyChangeEvent event) {
      if (RoRConfig.SERVER.DIFFICULTY_UPDATE.get() > 0) {
        SoundEvent soundEvent = SoundInit.DIFFICULTY_CHANGE.get();
        RoRmod.CHANNEL.send(PacketDistributor.ALL.noArg(), new DifficultyChangeSoundPacket(soundEvent));
      }
    }

    @SubscribeEvent
    public static void playerDeathSound(LivingDeathEvent event) {
      double d0 = RoRConfig.SERVER.PLAYER_DEATH_SOUND.get();
      if (d0 > 0 && event.getEntity() instanceof Player player) {
        Level level = player.level();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.PLAYER_DEATH.get(), SoundSource.PLAYERS, (float) d0 / 100, 1.0F);
      }
    }

    @SubscribeEvent
    public static void levelUpSound(PlayerXpEvent.LevelChange event) {
      double d0 = RoRConfig.SERVER.LEVEL_UPDATE.get();
      if (d0 > 0) {
        Player player = event.getEntity();
        Level level = player.level();
        level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundInit.LEVEL_UP.get(), SoundSource.PLAYERS, (float) d0 / 100, 1.0F);
      }
    }

    @SubscribeEvent
    public static void immuneDrones(LivingAttackEvent event) {
      if (event.getEntity() instanceof AbstractFlyingDroneEntity drone && event.getSource().getDirectEntity() instanceof LivingEntity) {
        boolean isTamed = drone.isTame();
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
        SpawnPlacements.register(EntityInit.BEETLE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, BeetleEntity::canSpawn);
        SpawnPlacements.register(EntityInit.LEMURIAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LemurianEntity::canSpawn);
        SpawnPlacements.register(EntityInit.STONE_GOLEM_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, StoneGolemEntity::canSpawn);
        SpawnPlacements.register(EntityInit.GUNNER_DRONE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerDroneEntity::checkDroneSpawnRules);
      });
    }

    @SubscribeEvent
    public static void entityAttributes(EntityAttributeCreationEvent event) {
      event.put(EntityInit.BEETLE_ENTITY.get(), BeetleEntity.createAttributes().build());
      event.put(EntityInit.LEMURIAN_ENTITY.get(), LemurianEntity.createAttributes().build());
      event.put(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemEntity.createAttributes().build());
      event.put(EntityInit.GUNNER_DRONE_ENTITY.get(), GunnerDroneEntity.createAttributes().build());
    }
  }
}

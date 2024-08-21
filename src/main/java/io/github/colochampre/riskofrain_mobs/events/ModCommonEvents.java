package io.github.colochampre.riskofrain_mobs.events;

import io.github.colochampre.riskofrain_mobs.RoRConfig;
import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.allies.AbstractDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.enemies.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.init.EntityInit;
import io.github.colochampre.riskofrain_mobs.init.SoundInit;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.player.AdvancementEvent;
import net.minecraftforge.event.entity.player.PlayerXpEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Objects;

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

    /*@SubscribeEvent
    public static void difficultyChangeSound(DifficultyChangeEvent event) {
      if (RoRConfig.SERVER.DIFFICULTY_UPDATE.get() > 0) {
        SoundEvent soundEvent = SoundInit.DIFFICULTY_CHANGE.get();
        RoRmod.CHANNEL.send(PacketDistributor.ALL.noArg(), new DifficultyChangeSoundPacket(soundEvent));
      }
    }*/

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
      if (event.getEntity() instanceof AbstractDroneEntity drone && event.getSource().getDirectEntity() instanceof LivingEntity) {
        boolean isTamed = drone.isTame();
        if (isTamed) {
          return;
        }
        if (event.isCancelable()) {
          event.setCanceled(true);
        }
      }
    }

    @SubscribeEvent
    public static void stoneGolemLoot(LivingDropsEvent event) {
      if (event.getEntity() instanceof StoneGolemEntity) {
        int count = 3 + event.getEntity().level().random.nextInt(3);
        ItemStack blackstone = new ItemStack(Items.BLACKSTONE, count);
        event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), blackstone));
        if (event.getEntity().level().random.nextFloat() < 0.5f) {
          ItemStack redstone = new ItemStack(Items.REDSTONE_BLOCK);
          event.getDrops().add(new ItemEntity(event.getEntity().level(), event.getEntity().getX(), event.getEntity().getY(), event.getEntity().getZ(), redstone));
        }
      }
    }

    @SubscribeEvent
    public static void onBlockPlace(BlockEvent.EntityPlaceEvent event) {
      if (event.getPlacedBlock().getBlock() == Blocks.REDSTONE_BLOCK) {
        Level level = Objects.requireNonNull(event.getEntity()).level();
        BlockPos pos = event.getPos();
        if (isValidBlackstoneStructure(level, pos)) {
          removeStructure(level, pos);
          StoneGolemEntity golem = new StoneGolemEntity(EntityInit.STONE_GOLEM_ENTITY.get(), level);
          golem.setPos(pos.getX() + 0.5, pos.getY() - 2, pos.getZ() + 0.5);
          level.addFreshEntity(golem);
        }
      }
    }

    private static boolean isValidBlackstoneStructure(Level level, BlockPos pos) {
      return level.getBlockState(pos.below()).getBlock() == Blocks.BLACKSTONE &&
              level.getBlockState(pos.below(2)).getBlock() == Blocks.BLACKSTONE &&
              level.getBlockState(pos.below().east()).getBlock() == Blocks.BLACKSTONE &&
              level.getBlockState(pos.below().west()).getBlock() == Blocks.BLACKSTONE;
    }

    private static void removeStructure(Level level, BlockPos pos) {
      level.setBlock(pos, Blocks.AIR.defaultBlockState(), 3);
      level.setBlock(pos.below(), Blocks.AIR.defaultBlockState(), 3);
      level.setBlock(pos.below(2), Blocks.AIR.defaultBlockState(), 3);
      level.setBlock(pos.below().east(), Blocks.AIR.defaultBlockState(), 3);
      level.setBlock(pos.below().west(), Blocks.AIR.defaultBlockState(), 3);
    }
  }
}

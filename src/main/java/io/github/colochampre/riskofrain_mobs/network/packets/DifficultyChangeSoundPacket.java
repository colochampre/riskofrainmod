package io.github.colochampre.riskofrain_mobs.network.packets;

/*import io.github.colochampre.riskofrain_mobs.RoRConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class DifficultyChangeSoundPacket {
  private final ResourceLocation soundLocation;

  public DifficultyChangeSoundPacket(SoundEvent soundEvent) {
    this.soundLocation = soundEvent.getLocation();
  }

  public DifficultyChangeSoundPacket(ResourceLocation soundLocation) {
    this.soundLocation = soundLocation;
  }

  public static void encode(DifficultyChangeSoundPacket packet, FriendlyByteBuf buffer) {
    buffer.writeResourceLocation(packet.soundLocation);
  }

  public static DifficultyChangeSoundPacket decode(FriendlyByteBuf buffer) {
    return new DifficultyChangeSoundPacket(buffer.readResourceLocation());
  }

  public static void handle(DifficultyChangeSoundPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
    NetworkEvent.Context context = contextSupplier.get();
    context.enqueueWork(() -> {
      LocalPlayer player = Minecraft.getInstance().player;
      if (player != null) {
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(packet.soundLocation);
        if (soundEvent != null) {
          player.playSound(soundEvent, RoRConfig.SERVER.DIFFICULTY_UPDATE.get(), 1.0F);
        }
      }
    });
    context.setPacketHandled(true);
  }
}*/

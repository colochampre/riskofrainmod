package io.github.colochampre.riskofrain_mobs.network.packets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class SoundPacket {
  private final ResourceLocation soundLocation;

  public SoundPacket(SoundEvent soundEvent) {
    this.soundLocation = soundEvent.getLocation();
  }

  public SoundPacket(ResourceLocation soundLocation) {
    this.soundLocation = soundLocation;
  }

  public static void encode(SoundPacket packet, FriendlyByteBuf buffer) {
    buffer.writeResourceLocation(packet.soundLocation);
  }

  public static SoundPacket decode(FriendlyByteBuf buffer) {
    return new SoundPacket(buffer.readResourceLocation());
  }

  public static void handle(SoundPacket packet, Supplier<NetworkEvent.Context> contextSupplier) {
    NetworkEvent.Context context = contextSupplier.get();
    context.enqueueWork(() -> {
      LocalPlayer player = Minecraft.getInstance().player;
      if (player != null) {
        SoundEvent soundEvent = ForgeRegistries.SOUND_EVENTS.getValue(packet.soundLocation);
        if (soundEvent != null) {
          player.playSound(soundEvent, 1.0F, 1.0F);
        }
      }
    });
    context.setPacketHandled(true);
  }
}

package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SoundInit {
  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RoRitems.MODID);

  public static final RegistryObject<SoundEvent> COIN_PROC = registerSoundEvent("interactive.coin.proc");
  public static final RegistryObject<SoundEvent> CROWBAR_PROC = registerSoundEvent("item.crowbar.proc");
  public static final RegistryObject<SoundEvent> INFUSION_PROC = registerSoundEvent("item.infusion.proc");
  public static final RegistryObject<SoundEvent> LENS_CRIT_PROC = registerSoundEvent("item.lens_crit.proc");
  public static final RegistryObject<SoundEvent> TOUGHER_TIMES_PROC = registerSoundEvent("item.tougher_times.proc");

  private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
    return SOUNDS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RoRitems.MODID, soundName)));
  }
}

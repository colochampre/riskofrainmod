package io.github.colochampre.riskofrainmod.init;

import io.github.colochampre.riskofrainmod.RoRmod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public final class SoundInit {

  public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, RoRmod.MODID);

  public static final RegistryObject<SoundEvent> LEMURIAN_AMBIENT = registerSoundEvent("entity.lemurian.ambient");
  public static final RegistryObject<SoundEvent> LEMURIAN_ATTACK = registerSoundEvent("entity.lemurian.attack");
  public static final RegistryObject<SoundEvent> LEMURIAN_DEATH = registerSoundEvent("entity.lemurian.death");
  public static final RegistryObject<SoundEvent> LEMURIAN_HURT = registerSoundEvent("entity.lemurian.hurt");
  public static final RegistryObject<SoundEvent> LEMURIAN_STEP = registerSoundEvent("entity.lemurian.step");

  private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
    return SOUNDS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RoRmod.MODID, soundName)));
  }

  private SoundInit() {
  }
}

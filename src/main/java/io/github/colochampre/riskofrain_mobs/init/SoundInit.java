package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
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

  public static final RegistryObject<SoundEvent> STONE_GOLEM_CLAP = registerSoundEvent("entity.stone_golem.clap");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_DEATH = registerSoundEvent("entity.stone_golem.death");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_GROWL = registerSoundEvent("entity.stone_golem.growl");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_HURT = registerSoundEvent("entity.stone_golem.hurt");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_LASER_CHARGE = registerSoundEvent("entity.stone_golem.laser_charge");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_LASER_FIRE = registerSoundEvent("entity.stone_golem.laser_fire");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_SPAWN = registerSoundEvent("entity.stone_golem.spawn");
  public static final RegistryObject<SoundEvent> STONE_GOLEM_STEP = registerSoundEvent("entity.stone_golem.step");

  private static RegistryObject<SoundEvent> registerSoundEvent(final String soundName) {
    return SOUNDS.register(soundName, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(RoRmod.MODID, soundName)));
  }

  private SoundInit() {
  }
}

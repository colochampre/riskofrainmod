package io.github.colochampre.riskofrain_mobs;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class RoRConfig {

  public static final ForgeConfigSpec SERVER_SPEC;
  public static final ServerConfig SERVER;

  static {
    Pair<ServerConfig, ForgeConfigSpec> commonSpecPair = new ForgeConfigSpec.Builder().configure(ServerConfig::new);
    SERVER_SPEC = commonSpecPair.getRight();
    SERVER = commonSpecPair.getLeft();
  }

  public static class ServerConfig {
    public final ForgeConfigSpec.DoubleValue GUNNER_DRONE_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue GUNNER_TURRET_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue BULLETS_DAMAGE;
    public final ForgeConfigSpec.IntValue DRONES_SPAWN_RATE;

    public final ForgeConfigSpec.DoubleValue BEETLE_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue BEETLE_ATTACK_DAMAGE;
    public final ForgeConfigSpec.IntValue BEETLE_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue BEETLE_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue BEETLE_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue BEETLE_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue BEETLES_DESPAWN;

    public final ForgeConfigSpec.DoubleValue LEMURIAN_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue LEMURIAN_ATTACK_DAMAGE;
    public final ForgeConfigSpec.IntValue LEMURIAN_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue LEMURIAN_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue LEMURIANS_DESPAWN;
    public final ForgeConfigSpec.BooleanValue ENABLE_FIREBALL_ATTACK;
    public final ForgeConfigSpec.BooleanValue ENABLE_FIREBALL_GRIEF;

    public final ForgeConfigSpec.DoubleValue STONE_GOLEM_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue STONE_GOLEM_ATTACK_DAMAGE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue STONE_GOLEM_DESPAWN;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_SPAWN_VOLUME;

    public final ForgeConfigSpec.DoubleValue WISP_MAX_HEALTH;
    public final ForgeConfigSpec.DoubleValue WISP_ATTACK_DAMAGE;
    public final ForgeConfigSpec.IntValue WISP_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue WISP_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue WISP_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue WISP_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue WISP_DESPAWN;

    public final ForgeConfigSpec.IntValue ADVANCEMENT;
    public final ForgeConfigSpec.IntValue CHAT_MESSAGE;
    public final ForgeConfigSpec.IntValue DIFFICULTY_UPDATE;
    public final ForgeConfigSpec.IntValue LEVEL_UPDATE;
    public final ForgeConfigSpec.IntValue PLAYER_DEATH_SOUND;


    public ServerConfig(ForgeConfigSpec.Builder builder) {
      builder.comment("Entity spawn configs").push("Drones");
      GUNNER_DRONE_MAX_HEALTH = builder
              .comment("Gunner Drones max health")
              .defineInRange("gunnerDroneMaxHealth", 20.0D, 1.0D, 999.9D);
      GUNNER_TURRET_MAX_HEALTH = builder
              .comment("Gunner Turrets max health")
              .defineInRange("gunnerTurretMaxHealth", 26.0D, 1.0D, 999.9D);
      BULLETS_DAMAGE = builder
              .comment("Bullets damage")
              .defineInRange("bulletsDamage", 2.0D, 1.0D, 999.9D);
      DRONES_SPAWN_RATE = builder
              .comment("Drones spawn rate")
              .defineInRange("droneSpawnWeight", 1, 0, 100);
      builder.pop();

      builder.push("Beetles");
      BEETLE_MAX_HEALTH = builder
              .comment("Beetles max health")
              .defineInRange("beetleMaxHealth", 20.0D, 1.0D, 999.9D);
      BEETLE_ATTACK_DAMAGE = builder
              .comment("Beetles attack damage")
              .defineInRange("beetleAttackDamage", 2.5D, 1.0D, 999.9D);
      BEETLE_OVERWORLD_SPAWN_RATE = builder
              .comment("Beetles overworld spawn rate. Set this to 0 to disable spawns")
              .defineInRange("beetleOverworldSpawnWeight", 66, 0, 100);
      BEETLE_NETHER_SPAWN_RATE = builder
              .comment("Beetles nether spawn rate (Sensitive). Set this to 0 to disable spawns")
              .defineInRange("beetleNetherSpawnWeight", 10, 0, 100);
      BEETLE_MIN_GROUP_SIZE = builder
              .comment("Beetles minimum group size on spawn. Set this equal or lower than max group size")
              .defineInRange("beetleMinGroupSize", 1, 1, 8);
      BEETLE_MAX_GROUP_SIZE = builder
              .comment("Beetles maximum group size on spawn. Set this equal or higher than min group size")
              .defineInRange("beetleMaxGroupSize", 3, 1, 8);
      BEETLES_DESPAWN = builder
              .comment("Despawn beetles when they are far away")
              .define("enableBeetleDespawn", true);
      builder.pop();

      builder.push("Lemurians");
      LEMURIAN_MAX_HEALTH = builder
              .comment("Lemurians max health")
              .defineInRange("lemurianMaxHealth", 20.0D, 1.0D, 999.9D);
      LEMURIAN_ATTACK_DAMAGE = builder
              .comment("Lemurians attack damage")
              .defineInRange("lemurianAttackDamage", 2.5D, 1.0D, 999.9D);
      LEMURIAN_OVERWORLD_SPAWN_RATE = builder
              .comment("Lemurians overworld spawn rate. Set this to 0 to disable spawns")
              .defineInRange("lemurianOverworldSpawnWeight", 66, 0, 100);
      LEMURIAN_NETHER_SPAWN_RATE = builder
              .comment("Lemurians nether spawn rate (Sensitive). Set this to 0 to disable spawns")
              .defineInRange("lemurianNetherSpawnWeight", 10, 0, 100);
      LEMURIAN_MIN_GROUP_SIZE = builder
              .comment("Lemurians minimum group size on spawn. Set this equal or lower than max group size")
              .defineInRange("lemurianMinGroupSize", 1, 1, 8);
      LEMURIAN_MAX_GROUP_SIZE = builder
              .comment("Lemurians maximum group size on spawn. Set this equal or higher than min group size")
              .defineInRange("lemurianMaxGroupSize", 3, 1, 8);
      LEMURIANS_DESPAWN = builder
              .comment("Despawn lemurians when they are far away")
              .define("enableLemurianDespawn", true);
      ENABLE_FIREBALL_ATTACK = builder
              .comment("Allow evolved lemurians to spit fireballs")
              .define("enableFireballAttack", true);
      ENABLE_FIREBALL_GRIEF = builder
              .comment("Allow lemurian fireballs to set blocks on fire")
              .define("enableFireballGrief", true);
      builder.pop();

      builder.push("Stone Golems");
      STONE_GOLEM_MAX_HEALTH = builder
              .comment("Stone Golems max health")
              .defineInRange("stoneGolemMaxHealth", 100.0D, 1.0D, 999.9D);
      STONE_GOLEM_ATTACK_DAMAGE = builder
              .comment("Stone Golems attack damage")
              .defineInRange("stoneGolemAttackDamage", 24.0D, 1.0D, 999.9D);
      STONE_GOLEM_OVERWORLD_SPAWN_RATE = builder
              .comment("Stone Golems overworld spawn rate. Set this to 0 to disable spawns")
              .defineInRange("stoneGolemOverworldSpawnWeight", 10, 0, 100);
      STONE_GOLEM_NETHER_SPAWN_RATE = builder
              .comment("Stone Golems nether spawn rate (Sensitive). Set this to 0 to disable spawns")
              .defineInRange("stoneGolemNetherSpawnWeight", 2, 0, 100);
      STONE_GOLEM_MIN_GROUP_SIZE = builder
              .comment("Stone Golems minimum group size. Set this equal or lower than max group size")
              .defineInRange("stoneGolemMinGroupSize", 1, 1, 8);
      STONE_GOLEM_MAX_GROUP_SIZE = builder
              .comment("Stone Golems maximum group size. Set this equal or higher than min group size")
              .defineInRange("stoneGolemMaxGroupSize", 1, 1, 8);
      STONE_GOLEM_DESPAWN = builder
              .comment("Despawn stone golems when they are far away")
              .define("enableStoneGolemDespawn", false);
      STONE_GOLEM_SPAWN_VOLUME = builder
              .comment("Stone Golems spawn volume. Set this to 0 to disable")
              .defineInRange("stoneGolemSpawnVolume", 100, 0, 100);
      builder.pop();

      builder.push("Wisps");
      WISP_MAX_HEALTH = builder
              .comment("Wisps max health")
              .defineInRange("wispMaxHealth", 12.0D, 1.0D, 999.9D);
      WISP_ATTACK_DAMAGE = builder
              .comment("Wisps attack damage")
              .defineInRange("wispAttackDamage", 2.0D, 1.0D, 999.9D);
      WISP_OVERWORLD_SPAWN_RATE = builder
              .comment("Wisps overworld spawn rate. Set this to 0 to disable spawns")
              .defineInRange("wispOverworldSpawnWeight", 50, 0, 100);
      WISP_NETHER_SPAWN_RATE = builder
              .comment("Wisps nether spawn rate (Sensitive). Set this to 0 to disable spawns")
              .defineInRange("wispNetherSpawnWeight", 10, 0, 100);
      WISP_MIN_GROUP_SIZE = builder
              .comment("Wisps minimum group size on spawn. Set this equal or lower than max group size")
              .defineInRange("wispMinGroupSize", 1, 1, 8);
      WISP_MAX_GROUP_SIZE = builder
              .comment("Wisps maximum group size on spawn. Set this equal or higher than min group size")
              .defineInRange("wispMaxGroupSize", 3, 1, 8);
      WISP_DESPAWN = builder
              .comment("Despawn wisps when they are far away")
              .define("wispBeetleDespawn", true);
      builder.pop();

      builder.push("Sound effects from Risk of Rain");
      ADVANCEMENT = builder
              .comment("Advancement sound volume. Set this to 0 to disable")
              .defineInRange("advancementSoundVolume", 0, 0, 100);
      CHAT_MESSAGE = builder
              .comment("Chat sound volume. Set this to 0 to disable")
              .defineInRange("chatSoundVolume", 0, 0, 100);
      DIFFICULTY_UPDATE = builder
              .comment("Difficulty update sound volume. Set this to 0 to disable")
              .defineInRange("difficultyUpdateSoundVolume", 0, 0, 100);
      LEVEL_UPDATE = builder
              .comment("Level update sound volume. Set this to 0 to disable")
              .defineInRange("levelUpdateSoundVolume", 0, 0, 100);
      PLAYER_DEATH_SOUND = builder
              .comment("Player death sound volume. Set this to 0 to disable")
              .defineInRange("deathSoundVolume", 0, 0, 100);
      builder.pop();
    }
  }
}

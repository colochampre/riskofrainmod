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
    public final ForgeConfigSpec.IntValue DRONES_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue LEMURIAN_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue LEMURIAN_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue LEMURIANS_DESPAWN;
    public final ForgeConfigSpec.BooleanValue PIGLINS_ATTACK_LEMURIANS;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_OVERWORLD_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_NETHER_SPAWN_RATE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_MIN_GROUP_SIZE;
    public final ForgeConfigSpec.IntValue STONE_GOLEM_MAX_GROUP_SIZE;
    public final ForgeConfigSpec.BooleanValue STONE_GOLEMS_DESPAWN;
    public final ForgeConfigSpec.BooleanValue DEATH_SOUND;


    public ServerConfig(ForgeConfigSpec.Builder builder) {
      builder.comment("Entity spawn configs").push("Drones");
      DRONES_SPAWN_RATE = builder
              .comment("Drones spawn rate")
              .defineInRange("droneSpawnWeight", 1, 0, 100);
      builder.pop();

      builder.push("Lemurians");
      LEMURIAN_OVERWORLD_SPAWN_RATE = builder
              .comment("Lemurians overworld spawn rate. Set this to 0 to disable spawns")
              .defineInRange("lemurianOverworldSpawnWeight", 66, 0, 100);
      LEMURIAN_NETHER_SPAWN_RATE = builder
              .comment("Lemurians nether spawn rate (Sensitive). Set this to 0 to disable spawns")
              .defineInRange("lemurianNetherSpawnWeight", 4, 0, 100);
      LEMURIAN_MIN_GROUP_SIZE = builder
              .comment("Lemurians minimum group size on spawn. Set this equal or lower than max group size")
              .defineInRange("lemurianMinGroupSize", 1, 1, 8);
      LEMURIAN_MAX_GROUP_SIZE = builder
              .comment("Lemurians maximum group size on spawn. Set this equal or higher than min group size")
              .defineInRange("lemurianMaxGroupSize", 2, 1, 8);
      LEMURIANS_DESPAWN = builder
              .comment("Despawn lemurians when they are far away")
              .define("enableLemurianDespawn", true);
      PIGLINS_ATTACK_LEMURIANS = builder
              .comment("Piglins attack Lemurians for not wearing gold")
              .define("piglinsAttackLemurians", true);
      builder.pop();

      builder.push("StoneGolems");
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
      STONE_GOLEMS_DESPAWN = builder
              .comment("Despawn stone golems when they are far away")
              .define("enableStoneGolemDespawn", false);
      builder.pop();

      builder.push("Sounds");
      DEATH_SOUND = builder
              .comment("Enable death sound effect from Risk of Rain")
              .define("enableDeathSound", true);
      builder.pop();
    }
  }
}

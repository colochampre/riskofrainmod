package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerDroneEntity;
import io.github.colochampre.riskofrain_mobs.entities.allies.GunnerTurretEntity;
import io.github.colochampre.riskofrain_mobs.entities.enemies.BeetleEntity;
import io.github.colochampre.riskofrain_mobs.entities.enemies.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.enemies.StoneGolemEntity;
import io.github.colochampre.riskofrain_mobs.entities.enemies.WispEntity;
import io.github.colochampre.riskofrain_mobs.entities.projectiles.BulletEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityInit {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RoRmod.MODID);

  public static final RegistryObject<EntityType<BeetleEntity>> BEETLE_ENTITY = ENTITY_TYPES.register("beetle_entity",
          () -> EntityType.Builder.of(BeetleEntity::new, MobCategory.MONSTER).sized(0.98F, 1.70F).build(RoRmod.MODID + ":beetle_entity"));

  public static final RegistryObject<EntityType<LemurianEntity>> LEMURIAN_ENTITY = ENTITY_TYPES.register("lemurian_entity",
          () -> EntityType.Builder.of(LemurianEntity::new, MobCategory.MONSTER).sized(0.7F, 1.72F).build(RoRmod.MODID + ":lemurian_entity"));

  public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM_ENTITY = ENTITY_TYPES.register("stone_golem_entity",
          () -> EntityType.Builder.of(StoneGolemEntity::new, MobCategory.MONSTER).sized(1.66F, 3.95F).build(RoRmod.MODID + ":stone_golem_entity"));

  public static final RegistryObject<EntityType<WispEntity>> WISP_ENTITY = ENTITY_TYPES.register("wisp_entity",
          () -> EntityType.Builder.of(WispEntity::new, MobCategory.MONSTER).sized(0.5625F, 0.75F).build(RoRmod.MODID + ":wisp_entity"));

  public static final RegistryObject<EntityType<GunnerDroneEntity>> GUNNER_DRONE_ENTITY = ENTITY_TYPES.register("gunner_drone_entity",
          () -> EntityType.Builder.of(GunnerDroneEntity::new, MobCategory.CREATURE).sized(0.75F, 1.15F).build(RoRmod.MODID + ":gunner_drone_entity"));

  public static final RegistryObject<EntityType<GunnerTurretEntity>> GUNNER_TURRET_ENTITY = ENTITY_TYPES.register("gunner_turret_entity",
          () -> EntityType.Builder.of(GunnerTurretEntity::new, MobCategory.CREATURE).sized(0.8125F, 1.3125F).build(RoRmod.MODID + ":gunner_turret_entity"));

  public static final RegistryObject<EntityType<BulletEntity>> DRONE_BULLET_ENTITY = ENTITY_TYPES.register("drone_bullet_entity",
          () -> registerEntity(EntityType.Builder.of(BulletEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setCustomClientFactory(BulletEntity::new).fireImmune(), "drone_bullet_entity"));

  private static EntityType registerEntity(EntityType.Builder builder, String entityName) {
    return builder.build(entityName);
  }

  @SubscribeEvent
  public static void commonSetup(FMLCommonSetupEvent event) {
    event.enqueueWork(() -> {
      SpawnPlacements.register(EntityInit.BEETLE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, BeetleEntity::canSpawn);
      SpawnPlacements.register(EntityInit.LEMURIAN_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LemurianEntity::canSpawn);
      SpawnPlacements.register(EntityInit.STONE_GOLEM_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, StoneGolemEntity::canSpawn);
      SpawnPlacements.register(EntityInit.WISP_ENTITY.get(), SpawnPlacements.Type.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WispEntity::canSpawn);
      SpawnPlacements.register(EntityInit.GUNNER_DRONE_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerDroneEntity::checkDroneSpawnRules);
      SpawnPlacements.register(EntityInit.GUNNER_TURRET_ENTITY.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerTurretEntity::checkDroneSpawnRules);
    });
  }

  @SubscribeEvent
  public static void entityAttributes(EntityAttributeCreationEvent event) {
    event.put(EntityInit.BEETLE_ENTITY.get(), BeetleEntity.createAttributes().build());
    event.put(EntityInit.LEMURIAN_ENTITY.get(), LemurianEntity.createAttributes().build());
    event.put(EntityInit.STONE_GOLEM_ENTITY.get(), StoneGolemEntity.createAttributes().build());
    event.put(EntityInit.WISP_ENTITY.get(), WispEntity.createAttributes().build());
    event.put(EntityInit.GUNNER_DRONE_ENTITY.get(), GunnerDroneEntity.createAttributes().build());
    event.put(EntityInit.GUNNER_TURRET_ENTITY.get(), GunnerTurretEntity.createAttributes().build());
  }
}

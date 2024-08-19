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
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = RoRmod.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class EntityInit {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RoRmod.MODID);

  public static final RegistryObject<EntityType<BeetleEntity>> BEETLE_ENTITY = ENTITY_TYPES.register("beetle_entity",
          () -> EntityType.Builder.of(BeetleEntity::new, MobCategory.MONSTER).sized(0.98F, 1.70F).eyeHeight(1.4375F).build(RoRmod.MODID + ":beetle_entity"));

  public static final RegistryObject<EntityType<LemurianEntity>> LEMURIAN_ENTITY = ENTITY_TYPES.register("lemurian_entity",
          () -> EntityType.Builder.of(LemurianEntity::new, MobCategory.MONSTER).sized(0.7F, 1.72F).eyeHeight(1.62F).build(RoRmod.MODID + ":lemurian_entity"));

  public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM_ENTITY = ENTITY_TYPES.register("stone_golem_entity",
          () -> EntityType.Builder.of(StoneGolemEntity::new, MobCategory.MONSTER).sized(1.66F, 3.95F).eyeHeight(3.5F).build(RoRmod.MODID + ":stone_golem_entity"));

  public static final RegistryObject<EntityType<WispEntity>> WISP_ENTITY = ENTITY_TYPES.register("wisp_entity",
          () -> EntityType.Builder.of(WispEntity::new, MobCategory.MONSTER).sized(0.5625F, 0.75F).eyeHeight(0.28125F).build(RoRmod.MODID + ":wisp_entity"));

  public static final RegistryObject<EntityType<GunnerDroneEntity>> GUNNER_DRONE_ENTITY = ENTITY_TYPES.register("gunner_drone_entity",
          () -> EntityType.Builder.of(GunnerDroneEntity::new, MobCategory.CREATURE).sized(0.75F, 1.15F).eyeHeight(0.055F).build(RoRmod.MODID + ":gunner_drone_entity"));

  public static final RegistryObject<EntityType<GunnerTurretEntity>> GUNNER_TURRET_ENTITY = ENTITY_TYPES.register("gunner_turret_entity",
          () -> EntityType.Builder.of(GunnerTurretEntity::new, MobCategory.CREATURE).sized(0.8125F, 1.3125F).eyeHeight(1.15625F).build(RoRmod.MODID + ":gunner_turret_entity"));

  public static final RegistryObject<EntityType<BulletEntity>> DRONE_BULLET_ENTITY = ENTITY_TYPES.register("drone_bullet_entity",
          () -> registerEntity(EntityType.Builder.of(BulletEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setCustomClientFactory(BulletEntity::new).fireImmune(), "drone_bullet_entity"));

  private static EntityType registerEntity(EntityType.Builder builder, String entityName) {
    return builder.build(entityName);
  }

  @SubscribeEvent
  public static void commonSetup(SpawnPlacementRegisterEvent event) {
    event.register(EntityInit.BEETLE_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, BeetleEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    event.register(EntityInit.LEMURIAN_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, LemurianEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    event.register(EntityInit.STONE_GOLEM_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, StoneGolemEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    event.register(EntityInit.WISP_ENTITY.get(), SpawnPlacementTypes.NO_RESTRICTIONS, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, WispEntity::canSpawn, SpawnPlacementRegisterEvent.Operation.AND);
    event.register(EntityInit.GUNNER_DRONE_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerDroneEntity::checkDroneSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
    event.register(EntityInit.GUNNER_TURRET_ENTITY.get(), SpawnPlacementTypes.ON_GROUND, Heightmap.Types.WORLD_SURFACE, GunnerTurretEntity::checkDroneSpawnRules, SpawnPlacementRegisterEvent.Operation.AND);
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

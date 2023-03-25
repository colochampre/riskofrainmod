package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.*;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RoRmod.MODID);

  public static final RegistryObject<EntityType<LemurianEntity>> LEMURIAN_ENTITY = ENTITY_TYPES.register("lemurian_entity",
          () -> EntityType.Builder.of(LemurianEntity::new, MobCategory.MONSTER).sized(0.7F, 1.72F).build(RoRmod.MODID + ":lemurian_entity"));

  public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM_ENTITY = ENTITY_TYPES.register("stone_golem_entity",
          () -> EntityType.Builder.of(StoneGolemEntity::new, MobCategory.MONSTER).sized(1.66F, 3.95F).build(RoRmod.MODID + ":stone_golem_entity"));

  public static final RegistryObject<EntityType<GunnerDroneEntity>> GUNNER_DRONE_ENTITY = ENTITY_TYPES.register("gunner_drone_entity",
          () -> EntityType.Builder.of(GunnerDroneEntity::new, MobCategory.CREATURE).sized(0.75F, 1.15F).build(RoRmod.MODID + ":gunner_drone_entity"));

  public static final RegistryObject<EntityType<BulletEntity>> DRONE_BULLET_ENTITY = ENTITY_TYPES.register("drone_bullet_entity",
          () -> registerEntity(EntityType.Builder.of(BulletEntity::new, MobCategory.MISC).sized(0.25F, 0.25F).setCustomClientFactory(BulletEntity::new).fireImmune(), "drone_bullet_entity"));

  private static final EntityType registerEntity(EntityType.Builder builder, String entityName) {
    return (EntityType) builder.build(entityName);
  }
}

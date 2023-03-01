package io.github.colochampre.riskofrain_mobs.init;

import io.github.colochampre.riskofrain_mobs.RoRmod;
import io.github.colochampre.riskofrain_mobs.entities.LemurianEntity;
import io.github.colochampre.riskofrain_mobs.entities.StoneGolemEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class EntityInit {
  public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, RoRmod.MODID);

  public static final RegistryObject<EntityType<LemurianEntity>> LEMURIAN_ENTITY = ENTITY_TYPES.register("lemurian_entity",
          () -> EntityType.Builder.of(LemurianEntity::new, MobCategory.MONSTER).sized(0.7f, 1.72f).build(RoRmod.MODID + ":lemurian_entity"));

  public static final RegistryObject<EntityType<StoneGolemEntity>> STONE_GOLEM_ENTITY = ENTITY_TYPES.register("stone_golem_entity",
          () -> EntityType.Builder.of(StoneGolemEntity::new, MobCategory.MONSTER).sized(1.66f, 3.95f).build(RoRmod.MODID + ":stone_golem_entity"));
}

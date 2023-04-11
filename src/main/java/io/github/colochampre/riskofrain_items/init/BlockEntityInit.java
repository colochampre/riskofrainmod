package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.blocks.entity.CommonChestBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockEntityInit {
  public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, RoRitems.MODID);

  public static final RegistryObject<BlockEntityType<CommonChestBlockEntity>> COMMON_CHEST = BLOCK_ENTITIES.register("common_chest",
          () -> BlockEntityType.Builder.of(CommonChestBlockEntity::new, BlockInit.COMMON_CHEST.get()).build(null));
}

package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.blocks.CommonChestBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class BlockInit {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RoRitems.MODID);

  public static final RegistryObject<Block> COMMON_CHEST = BLOCKS.register("common_chest",
          () -> new Block(BlockBehaviour.Properties.of(Material.METAL).strength(2.5f, 10.0f).requiresCorrectToolForDrops()));
}

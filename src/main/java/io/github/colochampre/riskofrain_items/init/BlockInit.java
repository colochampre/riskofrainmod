package io.github.colochampre.riskofrain_items.init;

import io.github.colochampre.riskofrain_items.RoRitems;
import io.github.colochampre.riskofrain_items.blocks.SmallChestBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BlockInit {

  public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, RoRitems.MODID);

  public static final RegistryObject<Block> SMALL_CHEST = register("small_chest",
          () -> new SmallChestBlock(BlockBehaviour.Properties.of(Material.METAL)
                  .strength(6.0F)
                  .noOcclusion()),
          new Item.Properties());

  private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> supplier, Item.Properties props) {
    RegistryObject<T> block = BLOCKS.register(name, supplier);
    ItemInit.ITEMS.register(name, () -> new BlockItem(block.get(), props));
    return block;
  }
}

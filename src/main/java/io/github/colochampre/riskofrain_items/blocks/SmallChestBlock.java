package io.github.colochampre.riskofrain_items.blocks;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SmallChestBlock extends Block {

  public SmallChestBlock(Properties props) {
    super(props);
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext collision) {
    return super.getShape(state, getter, pos, collision);
  }
}

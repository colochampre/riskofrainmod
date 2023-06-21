package io.github.colochampre.riskofrain_items.blocks.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class SmallChestBlock extends BaseEntityBlock {
  public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
  private static final VoxelShape SHAPE = Block.box(0, 0, 2, 16, 12, 14);

  public SmallChestBlock(Properties props) {
    super(props);
    this.registerDefaultState(this.stateDefinition.any()
            .setValue(FACING, Direction.NORTH));
  }

  @Override
  public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
    return SHAPE;
  }

  @Nullable
  @Override
  public BlockState getStateForPlacement(BlockPlaceContext context) {
    return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
  }

  @Override
  public BlockState rotate(BlockState state, Rotation rotation) {
    return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
  }

  @Override
  public BlockState mirror(BlockState state, Mirror mirror) {
    return state.rotate(mirror.getRotation(state.getValue(FACING)));
  }

  @Override
  protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
    builder.add(FACING);
  }

  // BlockEntity

  @Override
  public RenderShape getRenderShape(BlockState p_49232_) {
    return RenderShape.MODEL;
  }
  /*
  @Override
  public void onRemove(BlockState state, Level level, BlockPos pos, BlockState state1, boolean isMoving) {
    if (state.getBlock() != state1.getBlock()) {
      BlockEntity blockEntity = level.getBlockEntity(pos);
      if (blockEntity instanceof SmallChestBlockEntity) {
        ((SmallChestBlockEntity) blockEntity).drop();
      }
    }
    super.onRemove(state, level, pos, state1, isMoving);
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
    if (!level.isClientSide) {
      BlockEntity blockentity = level.getBlockEntity(pos);
      if (blockentity instanceof SmallChestBlockEntity) {
        NetworkHooks.openScreen(((ServerPlayer) player), (SmallChestBlockEntity) blockentity, pos);
      } else {
        throw new IllegalStateException("Container provider is missing!");
      }
    }
    return InteractionResult.sidedSuccess(level.isClientSide());
  } */

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    // return new SmallChestBlockEntity(pos, state);
    return null;
  }
  /*
  @Nullable
  @Override
  public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState stat, BlockEntityType<T> type) {
    return createTickerHelper(type, BlockEntityInit.SMALL_CHEST.get(), SmallChestBlockEntity::tick);
  } */
}

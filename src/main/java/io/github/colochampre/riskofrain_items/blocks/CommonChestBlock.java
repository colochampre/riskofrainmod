package io.github.colochampre.riskofrain_items.blocks;

import io.github.colochampre.riskofrain_items.blocks.entity.CommonChestBlockEntity;
import io.github.colochampre.riskofrain_items.init.BlockEntityInit;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleMenuProvider;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.MenuConstructor;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.Nullable;

public class CommonChestBlock extends Block implements EntityBlock {

  public CommonChestBlock(Properties props) {
    super(props);
  }

  @Nullable
  @Override
  public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
    return BlockEntityInit.COMMON_CHEST.get().create(pos, state);
  }

  @Override
  public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult resut) {
    if (!level.isClientSide()) {
      if (level.getBlockEntity(pos) instanceof CommonChestBlockEntity chest) {
        MenuConstructor menuConstructor = CommonChestMenu.getServerMenu(chest, pos);
        SimpleMenuProvider provider = new SimpleMenuProvider(menuConstructor, CommonChestBlockEntity.TITLE);
        NetworkHooks.openScreen((ServerPlayer) player, provider, pos);
      }
    }
    return InteractionResult.sidedSuccess(!level.isClientSide());
  }
}

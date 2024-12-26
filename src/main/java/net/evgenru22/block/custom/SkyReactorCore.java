package net.evgenru22.block.custom;

import com.mojang.serialization.MapCodec;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.block.entity.ModBlockEntities;
import net.evgenru22.block.entity.SkyReactorCoreBlockEntity;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class SkyReactorCore extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<SkyReactorCore> CODEC = SkyReactorCore.createCodec(SkyReactorCore::new);

    public SkyReactorCore(Settings settings) {
        super(settings);
    }

    @Override
    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    @Override
    protected MapCodec<? extends BlockWithEntity> getCodec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new SkyReactorCoreBlockEntity(pos, state);
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof SkyReactorCoreBlockEntity) {
                ItemScatterer.spawn(world, pos, (SkyReactorCoreBlockEntity)blockEntity);
                world.updateComparators(pos, this);
            }
        }
        super.onStateReplaced(state, world, pos, newState, moved);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient()) {
            NamedScreenHandlerFactory screenHandlerFactory = ((SkyReactorCoreBlockEntity) world.getBlockEntity(pos));

            if (screenHandlerFactory != null) {
                if (isStructureValid(world, pos)) {
                    player.openHandledScreen(screenHandlerFactory);
                } else {
                    player.sendMessage(Text.literal("Structure is not built correctly"));
                }
            }
        }

        return ActionResult.SUCCESS;
    }

    private boolean isStructureValid(World world, BlockPos corePos) {
        for (int x = 0; x < 2; x++) {
            for (int z = 0; z < 2; z++) {
                BlockPos checkPos = corePos.add(x, -1, z);
                Block block = world.getBlockState(checkPos).getBlock();
                if (!(block.equals(ModBlocks.SKY_REACTOR_BLOCK))) {
                    return false;
                }
            }
        }

        if (world.getBlockState(corePos).getBlock() != this) {
            return false;
        }
        if (world.getBlockState(corePos.add(1, 0, 0)).getBlock() != Blocks.IRON_BARS) {
            return false;
        }
        if (world.getBlockState(corePos.add(0, 0, 1)).getBlock() != Blocks.IRON_BARS) {
            return false;
        }
        if (world.getBlockState(corePos.add(1, 0, 1)).getBlock() != Blocks.IRON_BARS) {
            return false;
        }

        return true;
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.SKY_REACTOR_CORE_BLOCK_ENTITY_BLOCK_ENTITY,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }
}

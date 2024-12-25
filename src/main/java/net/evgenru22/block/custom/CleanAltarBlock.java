package net.evgenru22.block.custom;

import com.mojang.serialization.MapCodec;
import net.evgenru22.block.entity.CleanAltarBlockEntity;
import net.evgenru22.block.entity.ModBlockEntities;
import net.evgenru22.block.entity.SoulReconstructorBlockEntity;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderType;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityTicker;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CleanAltarBlock extends BlockWithEntity implements BlockEntityProvider {
    public static final MapCodec<CleanAltarBlock> CODEC = CleanAltarBlock.createCodec(CleanAltarBlock::new);

    public CleanAltarBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        ItemStack heldItem = player.getStackInHand(hand);
        BlockEntity blockEntity = world.getBlockEntity(pos);

        if (blockEntity instanceof CleanAltarBlockEntity altar) {
            if (!world.isClient()) {
                if (altar.getItem().isEmpty() && !heldItem.isEmpty()) {
                    if (altar.placeItem(heldItem)) {
                        player.getStackInHand(hand).decrement(1);
                        return ActionResult.SUCCESS;
                    }
                } else if (!altar.getItem().isEmpty()) {
                    ItemStack takenItem = altar.takeItem();
                    if (!player.giveItemStack(takenItem)) {
                        player.dropItem(takenItem, false);
                    }
                    return ActionResult.SUCCESS;
                }
            }
            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
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
        return new CleanAltarBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return validateTicker(type, ModBlockEntities.CLEAN_ALTAR_BLOCK_ENTITY_BLOCK_ENTITY,
                ((world1, pos, state1, blockEntity) -> blockEntity.tick(world1, pos, state1)));
    }
}

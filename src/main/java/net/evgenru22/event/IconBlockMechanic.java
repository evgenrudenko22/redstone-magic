package net.evgenru22.event;

import net.evgenru22.block.ModBlocks;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ItemEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;

import java.util.List;

public class IconBlockMechanic {
    public static void register() {
        ServerTickEvents.END_WORLD_TICK.register(world -> {
            if (world instanceof ServerWorld) {
                ServerWorld serverWorld = (ServerWorld) world;

                List<ItemEntity> items = serverWorld.getEntitiesByClass(
                        ItemEntity.class,
                        new Box(-30000000, 0, -30000000, 30000000, 256, 30000000),
                        itemEntity -> !itemEntity.isRemoved()
                );

                for (ItemEntity itemEntity : items) {
                    if (itemEntity.getStack().getItem() == Items.REDSTONE) {
                        BlockPos pos = itemEntity.getBlockPos();

                        if (serverWorld.getBlockState(pos.down()).getBlock() == Blocks.OBSIDIAN) {
                            if (serverWorld.getBlockState(pos).getBlock() == Blocks.FIRE) {
                                itemEntity.discard();
                                serverWorld.setBlockState(pos, Blocks.AIR.getDefaultState());

                                serverWorld.setBlockState(pos, ModBlocks.PICTOGRAM_BLOCK.getDefaultState());

                                serverWorld.playSound(null, pos, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.BLOCKS, 1.0f, 1.0f);
                            }
                        }
                    }
                }
            }
        });
    }
}

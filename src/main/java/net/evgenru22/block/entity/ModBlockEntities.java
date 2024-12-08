package net.evgenru22.block.entity;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<SoulReconstructorBlockEntity> SOUL_RECONSTRUCTOR_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMagic.MOD_ID, "soul_reconstructor_be"),
                    BlockEntityType.Builder.create(SoulReconstructorBlockEntity::new,
                            ModBlocks.SOUL_RECONSTRUCTOR_BLOCK).build());

    public static void registerBlockEntities() {
        RedstoneMagic.LOGGER.info("Registering Block Entities for" + RedstoneMagic.MOD_ID);

    }
}

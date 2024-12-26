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
    public static final BlockEntityType<SpellEnchanterBlockEntity> SPELL_ENCHANTER_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMagic.MOD_ID, "spell_enchant_be"),
                    BlockEntityType.Builder.create(SpellEnchanterBlockEntity::new,
                            ModBlocks.SPELL_ENCHANTER_BLOCK).build());
    public static final BlockEntityType<CleanAltarBlockEntity> CLEAN_ALTAR_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMagic.MOD_ID, "clean_altar_be"),
                    BlockEntityType.Builder.create(CleanAltarBlockEntity::new,
                            ModBlocks.CLEAN_ALTAR_BLOCK).build());
    public static final BlockEntityType<SkyReactorCoreBlockEntity> SKY_REACTOR_CORE_BLOCK_ENTITY_BLOCK_ENTITY =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(RedstoneMagic.MOD_ID, "sky_reactor_core_be"),
                    BlockEntityType.Builder.create(SkyReactorCoreBlockEntity::new,
                            ModBlocks.SKY_REACTOR_CORE).build());

    public static void registerBlockEntities() {
        RedstoneMagic.LOGGER.info("Registering Block Entities for" + RedstoneMagic.MOD_ID);

    }
}

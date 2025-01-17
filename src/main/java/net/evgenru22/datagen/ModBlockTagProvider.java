package net.evgenru22.datagen;

import net.evgenru22.block.ModBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends FabricTagProvider.BlockTagProvider {
    public ModBlockTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                .add(ModBlocks.TRANSMUTATION_BLOCK)
                .add(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK)
                .add(ModBlocks.SPELL_ENCHANTER_BLOCK)
                .add(ModBlocks.ORE_OF_SINGS)
                .add(ModBlocks.CLEAN_ALTAR_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK)
                .add(ModBlocks.SPELL_ENCHANTER_BLOCK);
        getOrCreateTagBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.TRANSMUTATION_BLOCK)
                .add(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK)
                .add(ModBlocks.ORE_OF_SINGS)
                .add(ModBlocks.CLEAN_ALTAR_BLOCK);

    }
}

package net.evgenru22.datagen;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TRANSMUTATION_BLOCK);

        blockStateModelGenerator.registerSimpleState(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CURSED_REDSTONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUL_CRASHER, Models.GENERATED);
    }
}

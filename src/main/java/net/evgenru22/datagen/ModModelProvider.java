package net.evgenru22.datagen;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.util.Identifier;

import java.util.Optional;

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
        itemModelGenerator.register(ModItems.ENLIGHTENMENT_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CURSED_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GIFT_OF_ANCIENTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_TOKEN, Models.GENERATED);

        itemModelGenerator.register(ModItems.PERENNIAL_TORTOISE_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        // itemModelGenerator.register(ModItems.CURSED_PICKAXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.CURSED_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_HOE, Models.HANDHELD);
    }
}

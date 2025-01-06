package net.evgenru22.datagen;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.block.custom.ReedsCropBlock;
import net.evgenru22.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Model;
import net.minecraft.data.client.Models;
import net.minecraft.item.ArmorItem;
import net.minecraft.util.Identifier;

import java.util.Optional;

public class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.TRANSMUTATION_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKY_REACTOR_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.SKY_REACTOR_CORE);

        blockStateModelGenerator.registerSimpleState(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.SPELL_ENCHANTER_BLOCK);
        blockStateModelGenerator.registerSimpleState(ModBlocks.CLEAN_ALTAR_BLOCK);

        blockStateModelGenerator.registerSimpleCubeAll(ModBlocks.ORE_OF_SINGS);

        blockStateModelGenerator.registerCrop(ModBlocks.REEDS_CROP, ReedsCropBlock.AGE, 0, 1, 2, 3, 4, 5, 6, 7, 8);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.CURSED_REDSTONE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SOUL_CRASHER, Models.GENERATED);
        itemModelGenerator.register(ModItems.ENLIGHTENMENT_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CURSED_STAFF, Models.GENERATED);
        itemModelGenerator.register(ModItems.GIFT_OF_ANCIENTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MAGIC_TOKEN, Models.GENERATED);
        itemModelGenerator.register(ModItems.SINS_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.REEDS_HEADS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SKY_SHARD, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORE_LEVEL_1, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORE_LEVEL_2, Models.GENERATED);
        itemModelGenerator.register(ModItems.CORE_LEVEL_3, Models.GENERATED);
        itemModelGenerator.register(ModItems.SKY_AMULET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SKY_AMULET_TRINKET, Models.GENERATED);
        itemModelGenerator.register(ModItems.QUICK_RING, Models.GENERATED);

        itemModelGenerator.register(ModItems.PERENNIAL_TORTOISE_SPAWN_EGG,
                new Model(Optional.of(new Identifier("item/template_spawn_egg")), Optional.empty()));

        // itemModelGenerator.register(ModItems.CURSED_PICKAXE, Models.HANDHELD);

        itemModelGenerator.register(ModItems.CURSED_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.CURSED_HOE, Models.HANDHELD);

        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SKY_HELMET));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SKY_CHESTPLATE));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SKY_LEGGINGS));
        itemModelGenerator.registerArmor(((ArmorItem) ModItems.SKY_BOOTS));
    }
}

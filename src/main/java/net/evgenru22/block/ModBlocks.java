package net.evgenru22.block;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.custom.*;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static final Block TRANSMUTATION_BLOCK = registerBlock("transmutation_block",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK)));
    public static final Block PICTOGRAM_BLOCK = registerBlock("pictogram_block",
            new PictogramBlock(FabricBlockSettings.copyOf(Blocks.REDSTONE_WIRE).nonOpaque()));

    public static final Block SOUL_RECONSTRUCTOR_BLOCK = registerBlock("soul_reconstructor",
            new SoulReconstructorBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));
    public static final Block SPELL_ENCHANTER_BLOCK = registerBlock("spell_enchanter",
            new SpellEnchanterBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block ORE_OF_SINGS = registerBlock("ore_of_sins",
            new Block(FabricBlockSettings.copyOf(Blocks.IRON_ORE)));

    public static final Block REEDS_CROP = Registry.register(Registries.BLOCK, new Identifier(RedstoneMagic.MOD_ID, "reeds_crop"),
            new ReedsCropBlock(FabricBlockSettings.copyOf(Blocks.WHEAT)));

    public static final Block CLEAN_ALTAR_BLOCK = registerBlock("clean_altar",
            new CleanAltarBlock(FabricBlockSettings.copyOf(Blocks.IRON_BLOCK).nonOpaque()));

    public static final Block SKY_REACTOR_BLOCK = registerBlock("sky_reactor_block",
            new Block(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));
    public static final Block SKY_REACTOR_CORE = registerBlock("sky_reactor_core",
            new SkyReactorCore(FabricBlockSettings.copyOf(Blocks.STONE_BRICKS)));


    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(RedstoneMagic.MOD_ID, name), block);
    }

    private static Item registerBlockItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(RedstoneMagic.MOD_ID, name),
                new BlockItem(block, new FabricItemSettings()));
    }

    public static void registerModBlocks() {
        RedstoneMagic.LOGGER.info("Registering ModBlocks for" + RedstoneMagic.MOD_ID);
    }
}

package net.evgenru22.datagen;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.block.custom.ReedsCropBlock;
import net.evgenru22.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.data.server.loottable.BlockLootTableGenerator;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.AnyOfLootCondition;
import net.minecraft.loot.condition.BlockStatePropertyLootCondition;
import net.minecraft.loot.context.LootContextTypes;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.entry.LootPoolEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.StatePredicate;
import net.minecraft.util.Identifier;

public class ModLootTableProvider extends FabricBlockLootTableProvider {
    public ModLootTableProvider(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(ModBlocks.TRANSMUTATION_BLOCK);
        addDrop(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK);
        addDrop(ModBlocks.SPELL_ENCHANTER_BLOCK);

        addDrop(ModBlocks.ORE_OF_SINGS, copperLikeOreDrops(ModBlocks.ORE_OF_SINGS, ModItems.SINS_SHARD));

        AnyOfLootCondition.Builder builder =
                BlockStatePropertyLootCondition.builder(ModBlocks.REEDS_CROP).properties(StatePredicate.Builder.create()
                        .exactMatch(ReedsCropBlock.AGE, 7))
                .or(BlockStatePropertyLootCondition.builder(ModBlocks.REEDS_CROP).properties(StatePredicate.Builder.create()
                        .exactMatch(ReedsCropBlock.AGE, 8)));
        addDrop(ModBlocks.REEDS_CROP, cropDrops(ModBlocks.REEDS_CROP, ModItems.REEDS_HEADS, ModItems.REEDS_SEEDS, builder));

    }

    public LootTable.Builder copperLikeOreDrops(Block drop, Item item) {
        return BlockLootTableGenerator.dropsWithSilkTouch(drop,
                this.applyExplosionDecay(drop,
                        ((LeafEntry.Builder<?>) ItemEntry.builder(item)
                                    .apply(SetCountLootFunction
                                            .builder(UniformLootNumberProvider
                                                    .create(2.0f, 5.0f))))
                                .apply(ApplyBonusLootFunction.oreDrops(Enchantments.FORTUNE))));
    }
}

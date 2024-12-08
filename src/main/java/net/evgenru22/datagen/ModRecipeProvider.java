package net.evgenru22.datagen;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.item.ModItems;
import net.evgenru22.util.ModTags;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generate(RecipeExporter recipeExporter) {
        offerSmelting(recipeExporter, List.of(ModBlocks.TRANSMUTATION_BLOCK), RecipeCategory.MISC, Items.IRON_BLOCK,
                0.7f, 200, "redstone-magic");
        offerBlasting(recipeExporter, List.of(ModBlocks.TRANSMUTATION_BLOCK), RecipeCategory.MISC, Items.OBSIDIAN,
                0.7f, 200, "redstone-magic");
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.TRANSMUTATION_BLOCK, 1)
                .pattern("###")
                .pattern("SaS")
                .pattern("###")
                .input('#',  ModItems.CURSED_REDSTONE)
                .input('S', Items.GOLD_INGOT)
                .input('a', Items.OBSIDIAN)
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModBlocks.TRANSMUTATION_BLOCK)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModBlocks.SOUL_RECONSTRUCTOR_BLOCK, 1)
                .pattern("AAA")
                .pattern(" # ")
                .pattern("aSa")
                .input('A', Items.STONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .input('a', ModItems.CURSED_REDSTONE)
                .input('S', ModItems.ENLINGHTENMENT_SHARD)
                .criterion(hasItem(ModItems.ENLINGHTENMENT_SHARD), conditionsFromItem(ModItems.ENLINGHTENMENT_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SOUL_CRASHER, 1)
                .input(Items.STONE, 2)
                .input(ModTags.Items.LOGS_TAG)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SOUL_CRASHER)));
    }
}

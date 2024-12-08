package net.evgenru22.datagen;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
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

    }
}

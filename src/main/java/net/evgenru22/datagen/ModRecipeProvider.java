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
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Map;

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
        //offerFoodCookingRecipe(recipeExporter, );
        /* ------------------------------------------------- */
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
                .input('S', ModItems.ENLIGHTENMENT_SHARD)
                .criterion(hasItem(ModItems.ENLIGHTENMENT_SHARD), conditionsFromItem(ModItems.ENLIGHTENMENT_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK)));
        /* ------------------------------------------------- */
        ShapelessRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.SOUL_CRASHER, 1)
                .input(Items.STONE, 2)
                .input(ModTags.Items.LOGS_TAG)
                .criterion(hasItem(Items.STONE), conditionsFromItem(Items.STONE))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SOUL_CRASHER)));
        /* ------------------------------------------------- */
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CURSED_PICKAXE, 1)
                .pattern("AAA")
                .pattern(" # ")
                .pattern(" # ")
                .input('A', ModItems.CURSED_REDSTONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.CURSED_PICKAXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CURSED_AXE, 1)
                .pattern("AA ")
                .pattern("A# ")
                .pattern(" # ")
                .input('A', ModItems.CURSED_REDSTONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.CURSED_AXE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CURSED_SHOVEL, 1)
                .pattern(" A ")
                .pattern(" # ")
                .pattern(" # ")
                .input('A', ModItems.CURSED_REDSTONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.CURSED_SHOVEL)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CURSED_SWORD, 1)
                .pattern(" A ")
                .pattern(" A ")
                .pattern(" # ")
                .input('A', ModItems.CURSED_REDSTONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.CURSED_SWORD)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.TOOLS, ModItems.CURSED_HOE, 1)
                .pattern("AA ")
                .pattern(" # ")
                .pattern(" # ")
                .input('A', ModItems.CURSED_REDSTONE)
                .input('#', ModBlocks.TRANSMUTATION_BLOCK.asItem())
                .criterion(hasItem(ModItems.CURSED_REDSTONE), conditionsFromItem(ModItems.CURSED_REDSTONE))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK.asItem()))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.CURSED_HOE)));
        /* ------------------------------------------------- */
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModBlocks.CLEAN_ALTAR_BLOCK, 1)
                .pattern("###")
                .pattern(" S ")
                .pattern(" a ")
                .input('#', ModItems.ENLIGHTENMENT_SHARD)
                .input('S', ModBlocks.TRANSMUTATION_BLOCK)
                .input('a', Items.OBSIDIAN)
                .criterion(hasItem(ModItems.ENLIGHTENMENT_SHARD), conditionsFromItem(ModItems.ENLIGHTENMENT_SHARD))
                .criterion(hasItem(ModBlocks.TRANSMUTATION_BLOCK), conditionsFromItem(ModBlocks.TRANSMUTATION_BLOCK))
                .criterion(hasItem(Items.OBSIDIAN), conditionsFromItem(Items.OBSIDIAN))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModBlocks.CLEAN_ALTAR_BLOCK)));
        /* ------------------------------------------------- */
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_CHESTPLATE, 1)
                .pattern("a a")
                .pattern("aaa")
                .pattern("aaa")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_CHESTPLATE)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_LEGGINGS, 1)
                .pattern("aaa")
                .pattern("a a")
                .pattern("a a")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_LEGGINGS)));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_HELMET, 1)
                .pattern("aaa")
                .pattern("a a")
                .pattern("   ")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_HELMET)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_HELMET, 1)
                .pattern("   ")
                .pattern("aaa")
                .pattern("a a")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_HELMET) + "_2"));

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_BOOTS, 1)
                .pattern("a a")
                .pattern("a a")
                .pattern("   ")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_BOOTS)));
        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_BOOTS, 1)
                .pattern("   ")
                .pattern("a a")
                .pattern("a a")
                .input('a', ModItems.SKY_SHARD)
                .criterion(hasItem(ModItems.SKY_SHARD), conditionsFromItem(ModItems.SKY_SHARD))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_BOOTS) + "_2"));
        /* ------------------------------------------------- */
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_AMULET_TRINKET)
                .input(ModItems.SKY_AMULET)
                .criterion(hasItem(ModItems.SKY_AMULET), conditionsFromItem(ModItems.SKY_AMULET))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_AMULET_TRINKET)));
        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.SKY_AMULET)
                .input(ModItems.SKY_AMULET_TRINKET)
                .criterion(hasItem(ModItems.SKY_AMULET_TRINKET), conditionsFromItem(ModItems.SKY_AMULET_TRINKET))
                .offerTo(recipeExporter, new Identifier(RedstoneMagic.MOD_ID, getRecipeName(ModItems.SKY_AMULET)));
    }
}

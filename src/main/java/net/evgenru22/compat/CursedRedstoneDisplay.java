package net.evgenru22.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.entry.EntryStack;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;

import java.util.List;

public class CursedRedstoneDisplay implements Display {
    private final Block block;
    private final Item item;
    private final Item result;

    public CursedRedstoneDisplay(Block block, Item item, Item result) {
        this.block = block;
        this.item = item;
        this.result = result;
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(EntryIngredients.of(block), EntryIngredients.of(item));
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(EntryIngredients.of(result));
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return CursedRedstoneCategory.CURSED_REDSTONE;
    }
}

package net.evgenru22.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.display.DisplayRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.recipe.SoulReconstructorRecipe;
import net.evgenru22.recipe.SpellEnchanterRecipe;
import net.evgenru22.screen.SoulReconstructorScreen;
import net.evgenru22.screen.SpellEnchanterScreen;

public class RedstoneMagicREIClientPlugin implements REIClientPlugin {

    @Override
    public void registerDisplays(DisplayRegistry registry) {
        registry.registerRecipeFiller(SoulReconstructorRecipe.class, SoulReconstructorRecipe.Type.INSTANCE,
                SoulReconstructionDisplay::new);
        registry.registerRecipeFiller(SpellEnchanterRecipe.class, SpellEnchanterRecipe.Type.INSTANCE,
                SpellEnchantingDisplay::new);
    }

    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new SoulReconstructionCategory());
        registry.add(new SpellEnchantingCategory());

        registry.addWorkstations(SoulReconstructionCategory.SOUL_RECONSTRUCTION, EntryStacks.of(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK));
        registry.addWorkstations(SpellEnchantingCategory.SPELL_ENCHANTING, EntryStacks.of(ModBlocks.SPELL_ENCHANTER_BLOCK));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(78, 19, 13, 10), SoulReconstructorScreen.class,
                SoulReconstructionCategory.SOUL_RECONSTRUCTION);
        registry.registerClickArea(screen -> new Rectangle(77, 30, 20, 30), SpellEnchanterScreen.class,
                SpellEnchantingCategory.SPELL_ENCHANTING);
    }
}

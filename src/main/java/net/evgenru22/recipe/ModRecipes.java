package net.evgenru22.recipe;

import net.evgenru22.RedstoneMagic;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModRecipes {
    public static void registerRecipes() {
        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(RedstoneMagic.MOD_ID, SoulReconstructorRecipe.Serializer.ID),
                SoulReconstructorRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(RedstoneMagic.MOD_ID, SoulReconstructorRecipe.Type.ID),
                SoulReconstructorRecipe.Type.INSTANCE);

        Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(RedstoneMagic.MOD_ID, SpellEnchanterRecipe.Serializer.ID),
            SpellEnchanterRecipe.Serializer.INSTANCE);
        Registry.register(Registries.RECIPE_TYPE, new Identifier(RedstoneMagic.MOD_ID, SpellEnchanterRecipe.Type.ID),
            SpellEnchanterRecipe.Type.INSTANCE);
    }
}

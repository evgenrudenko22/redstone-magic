package net.evgenru22.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class SpellEnchanterRecipe implements Recipe<SimpleInventory> {
    private final String spell;
    private final List<Ingredient> recipeItem;

    public SpellEnchanterRecipe(List<Ingredient> recipeItem, String spell) {
        this.spell = spell;
        this.recipeItem = recipeItem;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return recipeItem.get(0).test(inventory.getStack(0));
    }

    public String getSpell() {
        return spell;
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return null;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItem.size());
        list.addAll(recipeItem);
        return list;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return Serializer.INSTANCE;
    }

    @Override
    public RecipeType<?> getType() {
        return Type.INSTANCE;
    }

    public static class Type implements RecipeType<SpellEnchanterRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "spell_enchanting";
    }

    public static class Serializer implements RecipeSerializer<SpellEnchanterRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "spell_enchanting";

        public static final Codec<SpellEnchanterRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
                validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredients").forGetter(SpellEnchanterRecipe::getIngredients),
            Codec.STRING.fieldOf("spell").forGetter(SpellEnchanterRecipe::getSpell)
        ).apply(in, SpellEnchanterRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredients!") : DataResult.success(list));
        }

        @Override
        public Codec<SpellEnchanterRecipe> codec() {
            return CODEC;
        }

        @Override
        public SpellEnchanterRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            String spell = buf.readString();
            return new SpellEnchanterRecipe(inputs, spell);
        }

        @Override
        public void write(PacketByteBuf buf, SpellEnchanterRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            buf.writeString(recipe.getSpell());
        }
    }

}

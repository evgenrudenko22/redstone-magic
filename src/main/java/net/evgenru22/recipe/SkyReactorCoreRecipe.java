package net.evgenru22.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.evgenru22.item.ModItems;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.registry.DynamicRegistryManager;
import net.minecraft.registry.Registries;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.dynamic.Codecs;
import net.minecraft.world.World;

import java.util.List;

public class SkyReactorCoreRecipe implements Recipe<SimpleInventory> {
    private final Item coreLevel;
    private final ItemStack output;
    private final List<Ingredient> recipeItems;

    public SkyReactorCoreRecipe(Item coreLevel, List<Ingredient> recipeItems, ItemStack output ) {
        this.coreLevel = coreLevel;
        this.output = output;
        this.recipeItems = recipeItems;
    }

    @Override
    public boolean matches(SimpleInventory inventory, World world) {
        if (world.isClient()) {
            return false;
        }

        return coreLevel == inventory.getStack(0).getItem() && recipeItems.get(0).test(inventory.getStack(1));
    }

    @Override
    public ItemStack craft(SimpleInventory inventory, DynamicRegistryManager registryManager) {
        return output;
    }

    @Override
    public boolean fits(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResult(DynamicRegistryManager registryManager) {
        return output;
    }

    public Item getCoreLevel() {
        return coreLevel;
    }

    public ItemStack getOutput() {
        return output;
    }

    @Override
    public DefaultedList<Ingredient> getIngredients() {
        DefaultedList<Ingredient> list = DefaultedList.ofSize(this.recipeItems.size());
        list.addAll(recipeItems);
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

    public static class Type implements RecipeType<SkyReactorCoreRecipe> {
        public static final Type INSTANCE = new Type();
        public static final String ID = "sky_reactor_core";
    }

    public static class Serializer implements RecipeSerializer<SkyReactorCoreRecipe> {
        public static final Serializer INSTANCE = new Serializer();
        public static final String ID = "sky_reactor_core";

        public static final Codec<SkyReactorCoreRecipe> CODEC = RecordCodecBuilder.create(in -> in.group(
            Registries.ITEM.getCodec().fieldOf("core_level").forGetter(SkyReactorCoreRecipe::getCoreLevel),
            validateAmount(Ingredient.DISALLOW_EMPTY_CODEC, 1).fieldOf("ingredient").forGetter(SkyReactorCoreRecipe::getIngredients),
            ItemStack.RECIPE_RESULT_CODEC.fieldOf("output").forGetter(SkyReactorCoreRecipe::getOutput)
        ).apply(in, SkyReactorCoreRecipe::new));

        private static Codec<List<Ingredient>> validateAmount(Codec<Ingredient> delegate, int max) {
            return Codecs.validate(Codecs.validate(
                    delegate.listOf(), list -> list.size() > max ? DataResult.error(() -> "Recipe has too many ingredients!") : DataResult.success(list)
            ), list -> list.isEmpty() ? DataResult.error(() -> "Recipe has no ingredient!") : DataResult.success(list));
        }

        @Override
        public Codec<SkyReactorCoreRecipe> codec() {
            return CODEC;
        }

        @Override
        public SkyReactorCoreRecipe read(PacketByteBuf buf) {
            DefaultedList<Ingredient> inputs = DefaultedList.ofSize(buf.readInt(), Ingredient.EMPTY);

            for (int i = 0; i < inputs.size(); i++) {
                inputs.set(i, Ingredient.fromPacket(buf));
            }

            String coreLevel = buf.readString();
            Item core;

            if (coreLevel == "3") {
                core = ModItems.CORE_LEVEL_3;
            } else if (coreLevel == "2") {
                core = ModItems.CORE_LEVEL_2;
            } else {
                core = ModItems.CORE_LEVEL_1;
            }

            ItemStack output = buf.readItemStack();
            return new SkyReactorCoreRecipe(core, inputs, output);
        }

        @Override
        public void write(PacketByteBuf buf, SkyReactorCoreRecipe recipe) {
            buf.writeInt(recipe.getIngredients().size());

            for (Ingredient ingredient : recipe.getIngredients()) {
                ingredient.write(buf);
            }

            Item core = recipe.getCoreLevel();
            if (core == ModItems.CORE_LEVEL_1) {
                buf.writeString("1");
            } else if (core == ModItems.CORE_LEVEL_2) {
                buf.writeString("2");
            } else if (core == ModItems.CORE_LEVEL_3) {
                buf.writeString("3");
            }

            buf.writeItemStack(recipe.getResult(null));
        }
    }
}

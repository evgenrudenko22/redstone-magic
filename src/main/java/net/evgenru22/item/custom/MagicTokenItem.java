package net.evgenru22.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Colors;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class MagicTokenItem extends Item {
    public MagicTokenItem(Settings settings) {
        super(settings);
    }

    public static String getSpell(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt().contains("spell")) {
            return stack.getNbt().getString("spell");
        }
        return "";
    }

    public static void setSpell(ItemStack stack, String spell) {
        stack.getOrCreateNbt().putString("spell", spell);
    }

    @Override
    public boolean hasGlint(ItemStack stack) {
        return getSpell(stack) != "";
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (getSpell(stack) == "") {
            tooltip.add(Text.literal("[EMPTY]").withColor(Colors.GRAY));
        } else {
            tooltip.add(Text.of(getSpell(stack)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }
}

package net.evgenru22.item.custom;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class CursedStaffItem extends Item {
    public CursedStaffItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack wandStack = user.getStackInHand(hand);
        ItemStack offHandStack = user.getOffHandStack();

        if (!world.isClient() && offHandStack.getItem() instanceof MagicTokenItem) {
            String spell = MagicTokenItem.getSpell(offHandStack);
            if (!spell.isEmpty()) {
                CursedStaffItem.setCurrentSpell(wandStack, spell);
                user.sendMessage(Text.literal("Set current spell to: " + spell), true);
            }
        }

        return TypedActionResult.success(wandStack);
    }

    @Override
    public boolean onClicked(ItemStack stack, ItemStack otherStack, Slot slot, ClickType clickType, PlayerEntity player, StackReference cursorStackReference) {
        if (clickType == ClickType.LEFT) {
            player.sendMessage(Text.literal(getCurrentSpell(stack)));
        }
        return true;
    }

    public static String getCurrentSpell(ItemStack stack) {
        if (stack.hasNbt() && stack.getNbt().contains("currentSpell")) {
            return stack.getNbt().getString("currentSpell");
        }
        return "";
    }

    public static void setCurrentSpell(ItemStack stack, String spell) {
        stack.getOrCreateNbt().putString("currentSpell", spell);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (getCurrentSpell(stack) == "") {
            tooltip.add(Text.literal("[EMPTY]").withColor(Colors.GRAY));
        } else {
            tooltip.add(Text.of(getCurrentSpell(stack)));
        }
        super.appendTooltip(stack, world, tooltip, context);
    }

}

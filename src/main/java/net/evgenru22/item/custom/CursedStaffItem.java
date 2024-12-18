package net.evgenru22.item.custom;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.util.IEntityDataSaver;
import net.evgenru22.util.IntegrityOfSoulData;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.inventory.StackReference;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;
import net.minecraft.text.Text;
import net.minecraft.util.ClickType;
import net.minecraft.util.Colors;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.Vec3d;
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

        if (!world.isClient()) {
            if (offHandStack.getItem() instanceof MagicTokenItem) {
                String spell = MagicTokenItem.getSpell(offHandStack);
                if (!spell.isEmpty()) {
                    CursedStaffItem.setCurrentSpell(wandStack, spell);
                    user.sendMessage(Text.literal("Set current spell to: " + spell), true);
                }
            } else {
                String spell = CursedStaffItem.getCurrentSpell(wandStack);

                if (((IEntityDataSaver) user).getPersistentData().getInt("integrity_of_soul") >= 6) {
                    IntegrityOfSoulData.removeIntegrityOfSoul((IEntityDataSaver) user, 5);
                    switch (spell) {
                        case "protection" ->
                                user.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 100, 2));
                        case "fire" -> {
                            Vec3d playerPos = user.getEyePos();
                            Vec3d lookDir = user.getRotationVec(1.0f);

                            FireballEntity entity = new FireballEntity(world, user, lookDir.x, lookDir.y, lookDir.z, 2);

                            entity.setPos(playerPos.x + lookDir.x * 1.5,
                                    playerPos.y + lookDir.y * 1.5,
                                    playerPos.z + lookDir.z * 1.5);

                            world.spawnEntity(entity);
                        }
                        case "repulsion" -> {

                        }
                        default -> user.sendMessage(Text.literal("Nothing to do"), true);
                    }
                }
            }
        }

        return TypedActionResult.success(wandStack);
    }

    @Override
    public int getMaxUseTime(ItemStack stack) {
        return 30;
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

package net.evgenru22.item.custom;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class SkyAmulet extends Item {
    public SkyAmulet(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient()) {
            if (entity instanceof PlayerEntity player) {
                if (player.getOffHandStack().getItem() == this) {
                    player.getAbilities().allowFlying = true;
                    player.sendAbilitiesUpdate();
                } else if (!player.getAbilities().creativeMode) {
                    player.getAbilities().allowFlying = false;
                    player.getAbilities().flying = false;
                    player.sendAbilitiesUpdate();
                }
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }
}

package net.evgenru22.item.custom.trinkets;

import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public class GiftOfMoonTrinket extends TrinketItem {
    public GiftOfMoonTrinket(Settings settings) {
        super(settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient() && entity instanceof PlayerEntity) {
            world.getEntitiesByClass(MobEntity.class, entity.getBoundingBox().expand(10), mob -> mob instanceof HostileEntity)
                    .forEach(this::makeMobPassive);
        }
    }

    private void makeMobPassive(MobEntity mob) {
        mob.setTarget(null);
        mob.setAttacking(false);
    }
}

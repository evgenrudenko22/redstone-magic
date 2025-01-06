package net.evgenru22.item.custom.trinkets;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import dev.emi.trinkets.api.TrinketsApi;
import net.evgenru22.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;

import java.util.UUID;

public class QuickRingTrinket extends TrinketItem {
    public QuickRingTrinket(Settings settings) {
        super(settings);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);

        modifiers.put(EntityAttributes.GENERIC_MOVEMENT_SPEED, new EntityAttributeModifier(uuid, "redstone-magic:movement_speed", 0.1, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));

        if (TrinketsApi.getTrinketComponent(entity).get().isEquipped(ModItems.SKY_AMULET_TRINKET)) {
            modifiers.put(EntityAttributes.GENERIC_FLYING_SPEED, new EntityAttributeModifier(uuid, "redstone-magic:flying-speed", 0.25, EntityAttributeModifier.Operation.MULTIPLY_TOTAL));
        }

        return modifiers;
    }
}

package net.evgenru22.effect;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.AttributeContainer;
import net.minecraft.entity.attribute.EntityAttributeInstance;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectCategory;
import net.minecraft.entity.player.PlayerEntity;
import java.util.UUID;

public class IchorEffect extends StatusEffect {
    private static final UUID ARMOR_REDUCTION_UUID = UUID.fromString("1b1b0d00-1b6d-4dd7-8b4f-92f8073b830d");

    protected IchorEffect() {
        super(StatusEffectCategory.HARMFUL, 0xE77623);
    }

    @Override
    public void applyUpdateEffect(LivingEntity entity, int amplifier) {
        if (!entity.getWorld().isClient && entity instanceof PlayerEntity player) {
            EntityAttributeInstance armorAttribute = player.getAttributeInstance(EntityAttributes.GENERIC_ARMOR);

            if (armorAttribute != null && armorAttribute.getModifier(ARMOR_REDUCTION_UUID) == null) {
                double reduction = 3;

                armorAttribute.addPersistentModifier(new EntityAttributeModifier(
                        ARMOR_REDUCTION_UUID,
                        "Ichor armor reduction",
                        -reduction,
                        EntityAttributeModifier.Operation.ADDITION
                ));
            }
        }
    }

    @Override
    public void onRemoved(AttributeContainer attributeContainer) {
        super.onRemoved(attributeContainer);

        EntityAttributeInstance armorAttribute = attributeContainer.getCustomInstance(EntityAttributes.GENERIC_ARMOR);

        if (armorAttribute != null) {
            armorAttribute.removeModifier(ARMOR_REDUCTION_UUID);
        }
    }

    @Override
    public boolean canApplyUpdateEffect(int duration, int amplifier) {
        return false;
    }
}

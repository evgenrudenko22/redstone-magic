package net.evgenru22.effect;

import net.evgenru22.RedstoneMagic;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModStatusEffects {
    public static final StatusEffect ICHOR_EFFECT = register("ichor", new IchorEffect());

    private static StatusEffect register(String name, StatusEffect effect) {
        return Registry.register(Registries.STATUS_EFFECT, new Identifier(RedstoneMagic.MOD_ID, name), effect);
    }

    public static void registerModStatusEffects() {
        RedstoneMagic.LOGGER.info("Registering ModStatusEffects for" + RedstoneMagic.MOD_ID);
    }
}

package net.evgenru22.event;

import net.evgenru22.item.ModItems;
import net.evgenru22.item.custom.CursedStaffItem;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import org.lwjgl.glfw.GLFW;

public class KeyInputHandler {
    public static final String KEY_CATEGORY_REDSTONE_MAGIC = "key.category.redstone_magic";
    public static final String KEY_USE_PROTECTION = "key.use_protection";

    public static KeyBinding usingProtection;

    public static void registerKeyInputs() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (usingProtection.wasPressed()) {
                assert client.player != null;
                if (client.player.getActiveItem().isOf(ModItems.GIFT_OF_ANCIENTS)){
                    client.player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 10, 2));
                }
            }
        });
    }

    public static void register() {
        usingProtection = KeyBindingHelper.registerKeyBinding(new KeyBinding(
                KEY_USE_PROTECTION,
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_O,
                KEY_CATEGORY_REDSTONE_MAGIC
        ));

        registerKeyInputs();
    }
}

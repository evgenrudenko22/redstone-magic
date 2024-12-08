package net.evgenru22.screen;

import net.evgenru22.RedstoneMagic;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;

public class ModScreenHandlers {
    public static final ScreenHandlerType<SoulReconstructorScreenHandler> SOUL_RECONSTRUCTOR_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, new Identifier(RedstoneMagic.MOD_ID, "soul_reconstructor"),
                    new ExtendedScreenHandlerType<>(SoulReconstructorScreenHandler::new));

    public static void registerScreenHandlers() {
        RedstoneMagic.LOGGER.info("Registering Screen Handlers for " + RedstoneMagic.MOD_ID);
    }
}

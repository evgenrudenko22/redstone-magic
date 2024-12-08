package net.evgenru22;

import net.evgenru22.screen.ModScreenHandlers;
import net.evgenru22.screen.SoulReconstructorScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class RedstoneMagicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.SOUL_RECONSTRUCTOR_SCREEN_HANDLER, SoulReconstructorScreen::new);
    }
}

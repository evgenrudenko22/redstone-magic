package net.evgenru22;

import net.evgenru22.block.entity.ModBlockEntities;
import net.evgenru22.block.entity.renderer.SoulReconstructorBlockEntityRenderer;
import net.evgenru22.entity.ModEntities;
import net.evgenru22.entity.client.ModModelLayers;
import net.evgenru22.entity.client.PerennialTortoiseModel;
import net.evgenru22.entity.client.PerennialTortoiseRenderer;
import net.evgenru22.screen.ModScreenHandlers;
import net.evgenru22.screen.SoulReconstructorScreen;
import net.evgenru22.screen.SpellEnchanterScreen;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class RedstoneMagicClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.SOUL_RECONSTRUCTOR_SCREEN_HANDLER, SoulReconstructorScreen::new);
        HandledScreens.register(ModScreenHandlers.SPELL_ENCHANTER_SCREEN_HANDLER_SCREEN_HANDLER, SpellEnchanterScreen::new);

        BlockEntityRendererFactories.register(ModBlockEntities.SOUL_RECONSTRUCTOR_BLOCK_ENTITY_BLOCK_ENTITY, SoulReconstructorBlockEntityRenderer::new);

        EntityRendererRegistry.register(ModEntities.PERENNIAL_TORTOISE, PerennialTortoiseRenderer::new);
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.PERENNIAL_TORTOISE, PerennialTortoiseModel::getTexturedModelData);
    }
}

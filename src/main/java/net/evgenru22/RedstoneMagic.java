package net.evgenru22;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.block.entity.ModBlockEntities;
import net.evgenru22.effect.ModStatusEffects;
import net.evgenru22.entity.ModEntities;
import net.evgenru22.entity.custom.PerennialTortoiseEntity;
import net.evgenru22.event.IconBlockMechanic;
import net.evgenru22.event.PlayerTickHandler;
import net.evgenru22.item.ModItems;
import net.evgenru22.item.ModItemsGroups;
import net.evgenru22.recipe.ModRecipes;
import net.evgenru22.screen.ModScreenHandlers;
import net.evgenru22.world.gen.ModWorldGeneration;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedstoneMagic implements ModInitializer {
	public static final String MOD_ID = "redstone-magic";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		ModItemsGroups.registerItemGroups();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		FuelRegistry.INSTANCE.add(ModItems.REEDS_HEADS, 1200);

		ModStatusEffects.registerModStatusEffects();

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerRecipes();

		IconBlockMechanic.register();
		FabricDefaultAttributeRegistry.register(ModEntities.PERENNIAL_TORTOISE, PerennialTortoiseEntity.createPerennialTortoiseAttributes());

		ModWorldGeneration.generateModWorldGen();

		ServerTickEvents.START_SERVER_TICK.register(new PlayerTickHandler());
	}
}
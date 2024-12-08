package net.evgenru22;

import net.evgenru22.block.ModBlocks;
import net.evgenru22.block.entity.ModBlockEntities;
import net.evgenru22.event.IconBlockMechanic;
import net.evgenru22.item.ModItems;
import net.evgenru22.item.ModItemsGroups;
import net.evgenru22.recipe.ModRecipes;
import net.evgenru22.screen.ModScreenHandlers;
import net.fabricmc.api.ModInitializer;

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

		ModBlockEntities.registerBlockEntities();
		ModScreenHandlers.registerScreenHandlers();

		ModRecipes.registerRecipes();

		IconBlockMechanic.register();
	}
}
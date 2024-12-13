package net.evgenru22.item;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.entity.ModEntities;
import net.evgenru22.item.custom.ActivatedPickaxeItem;
import net.evgenru22.item.custom.SoulCrasher;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CURSED_REDSTONE = registerItem("cursed_redstone", new Item(new FabricItemSettings()));
    public static final Item ENLIGHTENMENT_SHARD = registerItem("enlightenment_shard", new Item(new FabricItemSettings()));

    public static final Item SOUL_CRASHER = registerItem("soul_crasher", new SoulCrasher(new FabricItemSettings().maxCount(1)));

    public static final Item PERENNIAL_TORTOISE_SPAWN_EGG = registerItem("perennial_tortoise_spawn_egg",
            new SpawnEggItem(ModEntities.PERENNIAL_TORTOISE, 0x232708, 0x20aa2f, new FabricItemSettings()));

    public static final Item CURSED_PICKAXE = registerItem("cursed_pickaxe", new ActivatedPickaxeItem(ModToolMaterial.CURSED, 2, 2f, new FabricItemSettings()));
    public static final Item CURSED_AXE = registerItem("cursed_axe", new AxeItem(ModToolMaterial.CURSED, 3, 1f, new FabricItemSettings()));
    public static final Item CURSED_SHOVEL = registerItem("cursed_shovel", new ShovelItem(ModToolMaterial.CURSED, 0, 0f, new FabricItemSettings()));
    public static final Item CURSED_SWORD = registerItem("cursed_sword", new SwordItem(ModToolMaterial.CURSED, 5, 3f, new FabricItemSettings()));
    public static final Item CURSED_HOE = registerItem("cursed_hoe", new HoeItem(ModToolMaterial.CURSED, 0, 0f, new FabricItemSettings()));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(CURSED_REDSTONE);
        entries.add(ENLIGHTENMENT_SHARD);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RedstoneMagic.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}

package net.evgenru22.item;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.item.custom.SoulCrasher;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroupEntries;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static final Item CURSED_REDSTONE = registerItem("cursed_redstone", new Item(new FabricItemSettings()));
    public static final Item ENLINGHTENMENT_SHARD = registerItem("enlightenment_shard", new Item(new FabricItemSettings()));

    public static final Item SOUL_CRASHER = registerItem("soul_crasher", new SoulCrasher(new FabricItemSettings().maxCount(1)));

    private static void addItemsToIngredientItemGroup(FabricItemGroupEntries entries) {
        entries.add(CURSED_REDSTONE);
        entries.add(ENLINGHTENMENT_SHARD);
    }

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, new Identifier(RedstoneMagic.MOD_ID, name), item);
    }

    public static void registerModItems() {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(ModItems::addItemsToIngredientItemGroup);
    }
}

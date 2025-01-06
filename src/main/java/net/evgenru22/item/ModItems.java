package net.evgenru22.item;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.entity.ModEntities;
import net.evgenru22.item.custom.*;
import net.evgenru22.item.custom.trinkets.QuickRingTrinket;
import net.evgenru22.item.custom.trinkets.SkyAmuletTrinket;
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
    public static final Item GIFT_OF_ANCIENTS = registerItem("gift_of_ancients", new Item(new FabricItemSettings()));
    public static final Item SINS_SHARD = registerItem("sins_shard", new Item(new FabricItemSettings()));
    public static final Item SKY_SHARD = registerItem("sky_shard", new Item(new FabricItemSettings()));

    public static final Item SOUL_CRASHER = registerItem("soul_crasher", new SoulCrasher(new FabricItemSettings().maxCount(1)));

    public static final Item PERENNIAL_TORTOISE_SPAWN_EGG = registerItem("perennial_tortoise_spawn_egg",
            new SpawnEggItem(ModEntities.PERENNIAL_TORTOISE, 0x232708, 0x20aa2f, new FabricItemSettings()));

    public static final Item CURSED_PICKAXE = registerItem("cursed_pickaxe", new ActivatedPickaxeItem(ModToolMaterial.CURSED, 2, 1f, new FabricItemSettings()));
    public static final Item CURSED_AXE = registerItem("cursed_axe", new AxeItem(ModToolMaterial.CURSED, 3, 1f, new FabricItemSettings()));
    public static final Item CURSED_SHOVEL = registerItem("cursed_shovel", new ShovelItem(ModToolMaterial.CURSED, 0, 0f, new FabricItemSettings()));
    public static final Item CURSED_SWORD = registerItem("cursed_sword", new SwordItem(ModToolMaterial.CURSED, 4, 1f, new FabricItemSettings()));
    public static final Item CURSED_HOE = registerItem("cursed_hoe", new HoeItem(ModToolMaterial.CURSED, 0, 0f, new FabricItemSettings()));

    public static final Item CURSED_STAFF = registerItem("cursed_staff", new CursedStaffItem(new FabricItemSettings().maxCount(1)));
    public static final Item MAGIC_TOKEN = registerItem("magic_token", new MagicTokenItem(new FabricItemSettings().maxCount(1)));

    public static final Item REEDS_SEEDS = registerItem("reeds_seeds",
            new AliasedBlockItem(ModBlocks.REEDS_CROP, new FabricItemSettings()));
    public static final Item REEDS_HEADS = registerItem("reeds_heads", new Item(new FabricItemSettings()));

    public static final Item SKY_HELMET = registerItem("sky_helmet",
            new ArmorItem(ModArmorMaterials.SKY, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SKY_CHESTPLATE = registerItem("sky_chestplate",
            new ArmorItem(ModArmorMaterials.SKY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SKY_LEGGINGS = registerItem("sky_leggings",
            new ArmorItem(ModArmorMaterials.SKY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SKY_BOOTS = registerItem("sky_boots",
            new ArmorItem(ModArmorMaterials.SKY, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    public static final Item CORE_LEVEL_1 = registerItem("core_level_1",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CORE_LEVEL_2 = registerItem("core_level_2",
            new Item(new FabricItemSettings().maxCount(1)));
    public static final Item CORE_LEVEL_3 = registerItem("core_level_3",
            new Item(new FabricItemSettings().maxCount(1)));

    public static final Item SKY_AMULET = registerItem("sky_amulet",
            new SkyAmulet(new FabricItemSettings().maxCount(1)));
    public static final Item SKY_AMULET_TRINKET = registerItem("sky_amulet_trinket",
            new SkyAmuletTrinket(new FabricItemSettings().maxCount(1)));

    public static final Item QUICK_RING = registerItem("quick_ring",
            new QuickRingTrinket(new FabricItemSettings().maxCount(1)));

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

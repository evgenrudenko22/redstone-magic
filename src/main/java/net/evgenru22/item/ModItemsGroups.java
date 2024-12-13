package net.evgenru22.item;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemsGroups {
    public static final ItemGroup REDSTONE_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(RedstoneMagic.MOD_ID, "redstone_magic"),
            FabricItemGroup.builder().displayName(Text.translatable("itemGroup.redstone_magic"))
                    .icon(() -> new ItemStack(ModItems.CURSED_REDSTONE)).entries((displayContext, entries) -> {
                        entries.add(ModItems.CURSED_REDSTONE);
                        entries.add(ModItems.ENLINGHTENMENT_SHARD);
                        entries.add(ModItems.SOUL_CRASHER);

                        entries.add(ModItems.PERENNIAL_TORTOISE_SPAWN_EGG);

                        entries.add(ModItems.CURSED_PICKAXE);
                        entries.add(ModItems.CURSED_AXE);
                        entries.add(ModItems.CURSED_SHOVEL);
                        entries.add(ModItems.CURSED_SWORD);
                        entries.add(ModItems.CURSED_HOE);

                        entries.add(ModBlocks.TRANSMUTATION_BLOCK);
                        entries.add(ModBlocks.PICTOGRAM_BLOCK);
                        entries.add(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK);
                    }).build());

    public static void registerItemGroups() {
        RedstoneMagic.LOGGER.info("Registering Item Groups for" + RedstoneMagic.MOD_ID);
    }
}

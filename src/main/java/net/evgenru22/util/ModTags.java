package net.evgenru22.util;

import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class ModTags {
    public static class Blocks {

    }

    public static class Items {
        public static final TagKey<Item> LOGS_TAG = TagKey.of(RegistryKeys.ITEM, new Identifier("minecraft", "logs"));
    }
}

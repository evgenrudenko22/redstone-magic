package net.evgenru22.entity;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.entity.custom.PerennialTortoiseEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<PerennialTortoiseEntity> PERENNIAL_TORTOISE = Registry.register(Registries.ENTITY_TYPE,
            new Identifier(RedstoneMagic.MOD_ID, "perennial_tortoise"),
            EntityType.Builder.create(PerennialTortoiseEntity::new, SpawnGroup.CREATURE).setDimensions(1f, 1f).build());
}

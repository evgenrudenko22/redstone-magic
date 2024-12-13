package net.evgenru22.entity.client;

import net.evgenru22.RedstoneMagic;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

public class ModModelLayers {
    public static final EntityModelLayer PERENNIAL_TORTOISE =
        new EntityModelLayer(new Identifier(RedstoneMagic.MOD_ID, "perennial_tortoise"), "main");
}

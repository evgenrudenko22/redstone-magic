package net.evgenru22.entity.client;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.entity.custom.PerennialTortoiseEntity;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

public class PerennialTortoiseRenderer extends MobEntityRenderer<PerennialTortoiseEntity, PerennialTortoiseModel<PerennialTortoiseEntity>> {
    private static final Identifier TEXTURE = new Identifier(RedstoneMagic.MOD_ID, "textures/entity/perennial_tortoise.png");

    public PerennialTortoiseRenderer(EntityRendererFactory.Context context) {
        super(context, new PerennialTortoiseModel<>(context.getPart(ModModelLayers.PERENNIAL_TORTOISE)), 0.6f);
    }

    @Override
    public Identifier getTexture(PerennialTortoiseEntity entity) {
        return TEXTURE;
    }

    @Override
    public void render(PerennialTortoiseEntity mobEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {

        if (mobEntity.isBaby()) {
            matrixStack.scale(0.5f, 0.5f, 0.5f);
        } else {
            matrixStack.scale(1f, 1f, 1f);
        }

        super.render(mobEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }
}

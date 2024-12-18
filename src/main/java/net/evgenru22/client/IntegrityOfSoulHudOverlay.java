package net.evgenru22.client;

import com.mojang.blaze3d.systems.RenderSystem;
import me.shedaniel.rei.api.client.gui.DrawableConsumer;
import net.evgenru22.RedstoneMagic;
import net.evgenru22.util.IEntityDataSaver;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.render.GameRenderer;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class IntegrityOfSoulHudOverlay implements HudRenderCallback {
    private final static Identifier FULL_INTEGRITY_OF_SOUL = new Identifier(RedstoneMagic.MOD_ID,
            "textures/gui/full_integrity_of_soul.png");
    private final static Identifier EMPTY_INTEGRITY_OF_SOUL = new Identifier(RedstoneMagic.MOD_ID,
            "textures/gui/empty_integrity_of_soul.png");

    @Override
    public void onHudRender(DrawContext drawContext, float v) {
        int x = 0;
        int y = 0;
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null) return;

        int width = client.getWindow().getScaledWidth();
        int height = client.getWindow().getScaledHeight();

        x = width;
        y = height;

        RenderSystem.setShader(GameRenderer::getPositionTexProgram);
        RenderSystem.setShaderColor(1, 1, 1, 1);
        RenderSystem.setShaderTexture(0, EMPTY_INTEGRITY_OF_SOUL);

        drawContext.drawTexture(EMPTY_INTEGRITY_OF_SOUL, x - 102 - 12, y - 20 - 8, 0, 0, 110, 20, 110, 20);

        RenderSystem.setShaderTexture(0, FULL_INTEGRITY_OF_SOUL);

        drawContext.drawTexture(FULL_INTEGRITY_OF_SOUL, x - 102 - 12 + 5, y - 20 - 5, 0, 0, getIOS(client.player), 14, 100, 14);
    }

    private int getIOS(PlayerEntity player) {
        IEntityDataSaver dataSaver = ((IEntityDataSaver) player);

        return dataSaver.getPersistentData().getInt("integrity_of_soul");
    }
}

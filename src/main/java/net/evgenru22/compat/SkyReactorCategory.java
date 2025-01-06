package net.evgenru22.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class SkyReactorCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(RedstoneMagic.MOD_ID, "textures/gui/sky_reactor_core_gui.png");
    public static final CategoryIdentifier<SkyReactorDisplay> SKY_REACTOR =
            CategoryIdentifier.of(RedstoneMagic.MOD_ID, "sky_reactor");

    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return SKY_REACTOR;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Sky Reactor");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.SKY_REACTOR_CORE.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 21, startPoint.y + 11))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 75, startPoint.y + 34))
                .entries(display.getInputEntries().get(1)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 123, startPoint.y + 34))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}

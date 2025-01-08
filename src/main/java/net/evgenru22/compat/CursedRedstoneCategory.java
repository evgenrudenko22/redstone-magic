package net.evgenru22.compat;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.evgenru22.item.ModItems;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CursedRedstoneCategory implements DisplayCategory<CursedRedstoneDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(RedstoneMagic.MOD_ID, "textures/gui/cursed_redstone_gui.png");
    public static final CategoryIdentifier<CursedRedstoneDisplay> CURSED_REDSTONE =
            CategoryIdentifier.of(RedstoneMagic.MOD_ID, "cursed_redstone");

    @Override
    public CategoryIdentifier<? extends CursedRedstoneDisplay> getCategoryIdentifier() {
        return CURSED_REDSTONE;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Redstone Magic");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModItems.CURSED_REDSTONE.getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(CursedRedstoneDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 37);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 84)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 42, startPoint.y + 11))
                .entries(display.getInputEntries().get(0)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 118, startPoint.y + 11))
                .entries(display.getInputEntries().get(1)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 80, startPoint.y + 59))
                .markOutput().entries(display.getOutputEntries().get(0)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}

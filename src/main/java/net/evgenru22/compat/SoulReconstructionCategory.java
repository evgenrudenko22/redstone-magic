package net.evgenru22.compat;

import me.shedaniel.math.Point;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.basic.BasicDisplay;
import me.shedaniel.rei.api.common.util.EntryStacks;
import me.shedaniel.math.Rectangle;
import net.evgenru22.RedstoneMagic;
import net.evgenru22.block.ModBlocks;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.LinkedList;
import java.util.List;

public class SoulReconstructionCategory implements DisplayCategory<BasicDisplay> {
    public static final Identifier TEXTURE =
            new Identifier(RedstoneMagic.MOD_ID, "textures/gui/soul_reconstructor_gui.png");
    public static final CategoryIdentifier<SoulReconstructionDisplay> SOUL_RECONSTRUCTION =
            CategoryIdentifier.of(RedstoneMagic.MOD_ID, "soul_reconstruction");


    @Override
    public CategoryIdentifier<? extends BasicDisplay> getCategoryIdentifier() {
        return SOUL_RECONSTRUCTION;
    }

    @Override
    public Text getTitle() {
        return Text.literal("Soul Reconstructor");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.SOUL_RECONSTRUCTOR_BLOCK.asItem().getDefaultStack());
    }

    @Override
    public List<Widget> setupDisplay(BasicDisplay display, Rectangle bounds) {
        final Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);
        List<Widget> widgets = new LinkedList<>();
        widgets.add(Widgets.createTexturedWidget(TEXTURE, new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        /*
        this.addSlot(new Slot(inventory, 0, 77, 32));
        this.addSlot(new Slot(inventory, 1, 38, 32));
        this.addSlot(new Slot(inventory, 2, 116, 32));
        this.addSlot(new Slot(inventory, 3, 77, 58));
         */
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 77, startPoint.y + 32))
                .markOutput().entries(display.getOutputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 38, startPoint.y + 32))
                .entries(display.getInputEntries().get(0)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 116, startPoint.y + 32))
                .entries(display.getInputEntries().get(1)));
        widgets.add(Widgets.createSlot(new Point(startPoint.x + 77, startPoint.y + 58))
                .entries(display.getInputEntries().get(2)));

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}

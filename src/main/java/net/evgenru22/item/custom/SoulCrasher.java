package net.evgenru22.item.custom;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.item.ModItems;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoulCrasher extends Item {
    public SoulCrasher(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if (!context.getWorld().isClient()) {
            BlockPos pos = context.getBlockPos();
            PlayerEntity player = context.getPlayer();
            Block clickBlock = context.getWorld().getBlockState(pos).getBlock();
            RedstoneMagic.LOGGER.info("Soul Crasher used");
            RedstoneMagic.LOGGER.info(String.valueOf(clickBlock.getName()));

            if(clickBlock == Blocks.REDSTONE_BLOCK) {
                RedstoneMagic.LOGGER.info("On redstone");
                if (player != null) {
                    if (player.getHealth() > 2) {
                        player.setHealth(Math.max(player.getHealth() - 2, 0));
                        player.giveItemStack(new ItemStack(ModItems.CURSED_REDSTONE, 1));
                    }
                }
            } else {
                RedstoneMagic.LOGGER.info("Is not redstone");
                return ActionResult.PASS;
            }
        }

        return ActionResult.SUCCESS;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.translatable("tooltip.redstone-magic.soul_crasher"));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

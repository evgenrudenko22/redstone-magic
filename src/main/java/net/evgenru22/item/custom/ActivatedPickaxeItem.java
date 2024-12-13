package net.evgenru22.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class ActivatedPickaxeItem extends PickaxeItem {
    private static final String MODE_KEY = "ActiveMode";

    public ActivatedPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        if (!world.isClient) {
            boolean isActive = stack.getOrCreateNbt().getBoolean(MODE_KEY);
            stack.getOrCreateNbt().putBoolean(MODE_KEY, !isActive);

            stack.getOrCreateNbt().putInt("CustomModelData", !isActive ? 1 : 0);

            user.sendMessage(Text.of("Mode: " + (!isActive ? "3x3" : "Normal")));
        }
        return new TypedActionResult<>(ActionResult.SUCCESS, stack);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient && stack.getOrCreateNbt().getBoolean(MODE_KEY)) {
            break3x3(world, pos, miner);
        }
        return super.postMine(stack, world, state, pos, miner);
    }

    private void break3x3(World world, BlockPos pos, LivingEntity miner) {
        Direction.Axis axis;
        float pitch = miner.getPitch(1.0f);

        if (pitch > 45) {
            axis = Direction.Axis.Y;
        } else if (pitch < -45) {
            axis = Direction.Axis.Y;
        } else {
            axis = miner.getHorizontalFacing().getAxis();
        }

        List<BlockPos> blocksToBreak = get3x3Blocks(pos, axis);

        for (BlockPos targetPos : blocksToBreak) {
            if (!targetPos.equals(pos)) {
                BlockState targetState = world.getBlockState(targetPos);
                Block targetBlock = targetState.getBlock();
                if (!targetState.isAir() && targetBlock.getHardness() >= 0) {
                    world.breakBlock(targetPos, true, miner);
                }
            }
        }
    }

    private List<BlockPos> get3x3Blocks(BlockPos pos, Direction.Axis axis) {
        List<BlockPos> positions = new ArrayList<>();

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                for (int dz = -1; dz <= 1; dz++) {
                    if ((axis == Direction.Axis.X && dy == 0) ||
                        (axis == Direction.Axis.Y && dz == 0) ||
                        (axis == Direction.Axis.Z && dy == 0)) {
                        positions.add(pos.add(dx, dy, dz));
                    }
                }
            }
        }

        return positions;
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.of("Mode: " + (!stack.getOrCreateNbt().getBoolean(MODE_KEY) ? "3x3" : "Normal")));
        super.appendTooltip(stack, world, tooltip, context);
    }
}

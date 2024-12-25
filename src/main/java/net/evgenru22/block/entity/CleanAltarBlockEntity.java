package net.evgenru22.block.entity;

import net.evgenru22.item.ModItems;
import net.evgenru22.recipe.CleanAltarRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class CleanAltarBlockEntity extends BlockEntity {
    private ItemStack item = ItemStack.EMPTY;

    private int progress = 0;
    private int maxProgress = 72;

    public CleanAltarBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CLEAN_ALTAR_BLOCK_ENTITY_BLOCK_ENTITY, pos, state);
    }

    public boolean placeItem(ItemStack stack) {
        if (item.isEmpty() && !stack.isEmpty()) {
            item = stack.copy();
            item.setCount(1);
            resetProgress();
            markDirty();
            return true;
        }
        return false;
    }

    public ItemStack takeItem() {
        if (!item.isEmpty()) {
            ItemStack taken = item.copy();
            item = ItemStack.EMPTY;
            resetProgress();
            markDirty();
            return taken;
        }
        return ItemStack.EMPTY;
    }

    public ItemStack getItem() {
        return item;
    }

    @Override
    public void markDirty() {
        world.updateListeners(pos, getCachedState(), getCachedState(), 3);
        super.markDirty();
    }

    @Override
    protected void writeNbt(NbtCompound nbt) {
        super.writeNbt(nbt);
        if (!item.isEmpty()) {
            NbtCompound itemNbt = new NbtCompound();
            item.writeNbt(itemNbt);
            nbt.put("clean_altar.item", itemNbt);
        }
        nbt.putInt("clean_altar.progress", progress);
    }

    @Override
    public void readNbt(NbtCompound nbt) {
        super.readNbt(nbt);
        if (nbt.contains("clean_altar.item")) {
            item = ItemStack.fromNbt(nbt.getCompound("clean_altar.item"));
        } else {
            item = ItemStack.EMPTY;
        }
        progress = nbt.getInt("clean_altar.progress");
    }

    public void tick(World world, BlockPos pos, BlockState state) {
        if (world.isClient()) {
            return;
        }

        if (this.hasRecipe()) {
            this.increaseCraftProgress();
            this.spawnParticles();
            markDirty(world, pos, state);

            if (hasCraftingFinished()) {
                this.craftItem();
                this.resetProgress();
                markDirty();
            }
        } else {
            this.resetProgress();
            markDirty(world, pos, state);
        }
    }

    private void resetProgress() {
        this.progress = 0;
    }

    private void craftItem() {
        Optional<RecipeEntry<CleanAltarRecipe>> recipe = getCurrentRecipe();

        item = recipe.get().value().getResult(null);
    }

    private boolean hasCraftingFinished() {
        return progress >= maxProgress;
    }

    private void increaseCraftProgress() {
        progress++;
    }

    private boolean hasRecipe() {
        Optional<RecipeEntry<CleanAltarRecipe>> recipe = getCurrentRecipe();

        return recipe.isPresent() && item.getCount() == 1;
    }

    private Optional<RecipeEntry<CleanAltarRecipe>> getCurrentRecipe() {
        SimpleInventory inv = new SimpleInventory(1);

        inv.setStack(0, item);

        return getWorld().getRecipeManager().getFirstMatch(CleanAltarRecipe.Type.INSTANCE, inv, getWorld());
    }

    private void spawnParticles() {
        if (world != null && world instanceof ServerWorld serverWorld) {
            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.2;
            double z = pos.getZ() + 0.5;

            serverWorld.spawnParticles(ParticleTypes.ENCHANT, x, y, z, 1, 0, 0.05, 0, 0.25);
            //world.addParticle(ParticleTypes.ENCHANT, x, y, z, 0, 0.05, 0);
        }
    }

    @Override
    public @Nullable Packet<ClientPlayPacketListener> toUpdatePacket() {
        return BlockEntityUpdateS2CPacket.create(this);
    }

    @Override
    public NbtCompound toInitialChunkDataNbt() {
        return createNbt();
    }
}

package net.evgenru22.util;

import net.evgenru22.networking.ModMessages;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;

public class IntegrityOfSoulData {
    public static int addIntegrityOfSoul(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int integrityOfSoul = nbt.getInt("integrity_of_soul");
        if (integrityOfSoul + amount >= 100) {
            integrityOfSoul = 100;
        } else {
            integrityOfSoul += amount;
        }

        nbt.putInt("integrity_of_soul", integrityOfSoul);
        syncIntegrityOfSoul(integrityOfSoul, ((ServerPlayerEntity) player));
        return integrityOfSoul;
    }

    public static int removeIntegrityOfSoul(IEntityDataSaver player, int amount) {
        NbtCompound nbt = player.getPersistentData();
        int integrityOfSoul = nbt.getInt("integrity_of_soul");
        if (integrityOfSoul - amount <= 0) {
            integrityOfSoul = 0;
        } else {
            integrityOfSoul -= amount;
        }

        nbt.putInt("integrity_of_soul", integrityOfSoul);
        syncIntegrityOfSoul(integrityOfSoul, ((ServerPlayerEntity) player));
        return integrityOfSoul;
    }

    public static void syncIntegrityOfSoul(int integrityOfSoul, ServerPlayerEntity player) {
        PacketByteBuf buffer = PacketByteBufs.create();
        buffer.writeInt(integrityOfSoul);
        ServerPlayNetworking.send(player, ModMessages.INTEGRITY_OF_SOUL_SYNC_ID, buffer);
    }
}

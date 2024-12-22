package net.evgenru22.networking.packet;

import net.evgenru22.util.IEntityDataSaver;
import net.fabricmc.fabric.api.networking.v1.PacketSender;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.PacketByteBuf;

public class IntegrityOfSoulSyncDataS2CPacket {
    public static void receive(MinecraftClient client, ClientPlayNetworkHandler handler,
                               PacketByteBuf buf, PacketSender responseSender) {
        if (client.player == null) return;
        ((IEntityDataSaver) client.player).getPersistentData().putInt("integrity_of_soul", buf.readInt());
    }
}

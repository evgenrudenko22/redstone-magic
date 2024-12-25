package net.evgenru22.networking;

import net.evgenru22.RedstoneMagic;
import net.evgenru22.networking.packet.IntegrityOfSoulSyncDataS2CPacket;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;

public class ModMessages {
    public static final Identifier INTEGRITY_OF_SOUL_SYNC_ID = new Identifier(RedstoneMagic.MOD_ID, "integrity_of_soul_sync");

    public static void registerC2SPackets() {

    }

    public static void registerS2CPackets() {
        ClientPlayNetworking.registerGlobalReceiver(INTEGRITY_OF_SOUL_SYNC_ID, IntegrityOfSoulSyncDataS2CPacket::receive);
    }
}

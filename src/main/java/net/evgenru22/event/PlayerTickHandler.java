package net.evgenru22.event;

import net.evgenru22.util.IEntityDataSaver;
import net.evgenru22.util.IntegrityOfSoulData;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

import java.util.Random;

public class PlayerTickHandler implements ServerTickEvents.StartTick{
    @Override
    public void onStartTick(MinecraftServer minecraftServer) {
        for (ServerPlayerEntity player : minecraftServer.getPlayerManager().getPlayerList()) {
            if (new Random().nextFloat() <= 0.5f) {
                IEntityDataSaver dataPlayer = ((IEntityDataSaver) player);
                IntegrityOfSoulData.addIntegrityOfSoul(dataPlayer, 1);
            }
        }
    }
}

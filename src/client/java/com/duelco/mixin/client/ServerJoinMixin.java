package com.duelco.mixin.client;

import com.duelco.managers.StartupCmdManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.ClientConnection;
import net.minecraft.network.packet.s2c.play.GameJoinS2CPacket;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayNetworkHandler.class)
public class ServerJoinMixin {
    @Unique
    private final StartupCmdManager startupCmdManager = new StartupCmdManager();

    @Inject(method = "onGameJoin", at = @At("HEAD"))
    private void onServerJoin(GameJoinS2CPacket packet, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();

        // Retrieve the network connection
        ClientConnection connection = ((ClientPlayNetworkHandler) (Object) this).getConnection();

        if (connection != null && connection.getAddress() != null && connection.getAddress().toString().startsWith("lords.rawb.tv")) {
            client.execute(startupCmdManager::executeNamesCmd);
        }
    }
}
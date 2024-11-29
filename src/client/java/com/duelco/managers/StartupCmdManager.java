package com.duelco.managers;

import com.duelco._enum.NamesCmdOptions;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.ClientPlayerEntity;

public class StartupCmdManager {
    private final NamesCmdOptions namesCmdOption = NamesCmdOptions.NAMES_OFF;

    public void executeNamesCmd() {
        ClientPlayerEntity client = MinecraftClient.getInstance().player;

        if (client != null) {
            switch (this.namesCmdOption) {
                case NAMES_ON -> client.networkHandler.sendChatCommand("names on");
                case NAMES_OFF -> client.networkHandler.sendChatCommand("names off");
                case NAMES_CHAR -> client.networkHandler.sendChatCommand("names character");
            }
        }
    }
}

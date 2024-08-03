package com.duelco.handlers;

import com.duelco.managers.SkinFlipperManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinFlipperHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("handlers.SkinFlipperHandler");
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static SkinFlipperManager skinFlipperDataManager = new SkinFlipperManager();

    public static void execute() {
        if (client.player != null) {
            LOGGER.info("Retrieving skin url.");
            String skinUrl = getPlayerSkin();
            LOGGER.info("The retrieved player skin is " + skinUrl);
            LOGGER.info("Executing skin flip.");
            String newSkin = skinFlipperDataManager.flipSkin(getPlayerSkin());
            LOGGER.info("The new player skin is " + newSkin);
            client.player.networkHandler.sendChatCommand("skin " + newSkin);
            LOGGER.info("The command is is " + "/skin " + newSkin);
        } else {
            LOGGER.debug("Player is null, skipping command execution.");
        }
    }

    private static String getPlayerSkin() {
        if (client.player != null) {
            PlayerListEntry playerEntry = SkinFlipperHandler.client.player.networkHandler.getPlayerListEntry(SkinFlipperHandler.client.player.getGameProfile().getId());
            if (playerEntry != null) {
                return playerEntry.getSkinTextures().textureUrl();
            }
        }
        return null;
    }
}

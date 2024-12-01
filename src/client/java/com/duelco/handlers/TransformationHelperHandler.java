package com.duelco.handlers;

import com.duelco.config.ModConfig;
import com.duelco.config.NewModConfig;
import com.duelco.managers.TransformationHelperManager;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformationHelperHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger("handlers.SkinFlipperHandler");
    private static final MinecraftClient client = MinecraftClient.getInstance();
    private static final TransformationHelperManager transformationHelperManager = new TransformationHelperManager();

    public static void execute() {
        if (client.player != null) {
            LOGGER.info("Retrieving skin url.");
            String skinUrl = getPlayerSkin();
            LOGGER.info("The retrieved player skin is {}", skinUrl);
            LOGGER.info("Executing skin flip.");
            String newSkin = transformationHelperManager.handleTransform(getPlayerSkin());
            LOGGER.info("The new player skin is {}", newSkin);
            client.player.networkHandler.sendChatCommand("skin " + newSkin);
            LOGGER.info("The command is /skin {}", newSkin);
            ModConfig.HANDLER.save();
        } else {
            LOGGER.debug("Player is null, skipping command execution.");
        }
    }

    private static String getPlayerSkin() {
        if (client.player != null) {
            PlayerListEntry playerEntry = client.player.networkHandler.getPlayerListEntry(client.player.getGameProfile().getId());
            if (playerEntry != null) {
                return playerEntry.getSkinTextures().textureUrl();
            }
        }
        return null;
    }
}

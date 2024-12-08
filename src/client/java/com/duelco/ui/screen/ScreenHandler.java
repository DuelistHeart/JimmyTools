package com.duelco.ui.screen;

import com.duelco._enum.Screen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;

public class ScreenHandler {
    public static void displayScreen(Screen screen, MinecraftClient client) {
        switch (screen) {
            case BINGO_CARDS_SCREEN -> client.setScreen(new BingoScreen());
            case BINGO_ITEMS_SCREEN -> client.setScreen(new BingoItemsScreen());
            case INVENTORY_SCREEN -> client.setScreen(new InventoryScreen(client.player));
        }
    }
}

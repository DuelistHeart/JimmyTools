package com.duelco.handlers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.screen.slot.SlotActionType;

public class BagHandler {
    public static void clickCraftingSlot(MinecraftClient client, int craftingGridSlotIndex) {
        // Ensure the client has the player's inventory open
        if (client.currentScreen instanceof InventoryScreen inventoryScreen) {
            // Get the slot ID for the crafting grid
            int slotId = craftingGridSlotIndex + 1; // Adjust for slot index offset

            // Simulate the click
            client.interactionManager.clickSlot(
                    inventoryScreen.getScreenHandler().syncId, // Inventory sync ID
                    slotId,                                   // Slot ID to click
                    0,                                        // Mouse button (0 = left, 1 = right)
                    SlotActionType.PICKUP,                   // Action type (PICKUP simulates a normal click)
                    client.player                            // Player entity
            );

            System.out.println("Clicked crafting grid slot: " + craftingGridSlotIndex);
        }
    }
}

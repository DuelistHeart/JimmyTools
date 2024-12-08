package com.duelco;

import com.duelco.config.ModConfig;
import com.duelco.handlers.BagHandler;
import com.duelco.handlers.TransformationHelperHandler;
import com.duelco.ui.screen.BingoItemsScreen;
import com.duelco.ui.screen.BingoScreen;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ingame.InventoryScreen;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.entity.Entity;
import net.minecraft.entity.decoration.DisplayEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;

public class DuelUtilsClient implements ClientModInitializer {
	private static KeyBinding skinFlipperToggleKeybind;
	private static KeyBinding bingoScreenKeybind;
	private static KeyBinding modMenuKeybind;
	private static KeyBinding bagOneKeybind;
	private static KeyBinding bagTwoKeybind;
	private static KeyBinding bagThreeKeybind;
	private static KeyBinding bagFourKeybind;

	public static final Logger LOGGER = LoggerFactory.getLogger("duelutils-client");

	@Override
	public void onInitializeClient() {
		ModConfig.HANDLER.load();
		registerKeybinds();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (skinFlipperToggleKeybind.wasPressed()) {
				TransformationHelperHandler.execute();
			}
			while (bingoScreenKeybind.wasPressed()) {
				client.setScreen(new BingoScreen());
			}
			while (bagOneKeybind.wasPressed()) {
				client.setScreen(new InventoryScreen(client.player));
				BagHandler.clickCraftingSlot(client, 0);
			}
			while (bagTwoKeybind.wasPressed()) {
				client.setScreen(new InventoryScreen(client.player));
				BagHandler.clickCraftingSlot(client, 1);
			}
			while (bagThreeKeybind.wasPressed()) {
				client.setScreen(new InventoryScreen(client.player));
				BagHandler.clickCraftingSlot(client, 2);
			}
			while (bagFourKeybind.wasPressed()) {
				client.setScreen(new InventoryScreen(client.player));
				BagHandler.clickCraftingSlot(client, 3);
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (modMenuKeybind.wasPressed()) {
				client.setScreen(ModConfig.build().generateScreen(client.currentScreen));
			}
		});


		ClientTickEvents.END_CLIENT_TICK.register(this::onTick);
	}

	private void onTick(MinecraftClient client) {
		if (client.world == null || client.player == null) return;

		// Iterate through all entities in the world
		for (Entity entity : client.world.getEntities()) {
			// Skip the player entity
			if (!(entity instanceof DisplayEntity.TextDisplayEntity)) continue;

			// Check if the entity is within the detection radius
			if (entity.distanceTo(client.player) <= 5) {
				if (((DisplayEntity.TextDisplayEntity) entity).getData().backgroundColor().lerp(0) == 1073741824) {
					System.out.println("HIIII!!!");
				}
				// Notify the player about the entity
				notifyPlayer(entity);
			}
		}
	}

	// Add logic to decide whether to block the message
	private boolean shouldBlockMessage(String message) {
		// Example: Block messages containing the word "block"
		return message.contains("block");
	}

	private void notifyPlayer(Entity entity) {
		String entityName = entity.getName().getString(); // Get the entity's name
		Text message = Text.literal("Entity spawned near you: " + entityName);
		MinecraftClient.getInstance().player.sendMessage(message, false); // Send the message to the player's chat
	}

	private void registerKeybinds() {
		skinFlipperToggleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.flipskin", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_K, // The keycode of the key
				"category.duelutils" // The translation key of the keybinding's category.
		));

		bingoScreenKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.bingo_screen",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_B,
				"category.duelutils"
		));

		modMenuKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.modmenu",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"category.duelutils"
		));

		bagOneKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.openbagone",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_UP,
				"category.duelutils"
		));

		bagTwoKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.openbagtwo",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_RIGHT,
				"category.duelutils"
		));

		bagThreeKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.openbagthree",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_DOWN,
				"category.duelutils"
		));

		bagFourKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelutils.openbagfour",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_LEFT,
				"category.duelutils"
		));
	}


}
package com.duelco;

import com.duelco._enum.Screen;
import com.duelco.config.ModConfig;
import com.duelco.handlers.BagHandler;
import com.duelco.handlers.TransformationHelperHandler;
import com.duelco.ui.screen.ScreenHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
				if (ModConfig.areTransformationsEnabled) {
					TransformationHelperHandler.execute();
				}
			}
			while (bingoScreenKeybind.wasPressed()) {
				ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
			}
			while (bagOneKeybind.wasPressed()) {
				ScreenHandler.displayScreen(Screen.INVENTORY_SCREEN, client);
				BagHandler.clickCraftingSlot(client, 0);
			}
			while (bagTwoKeybind.wasPressed()) {
				ScreenHandler.displayScreen(Screen.INVENTORY_SCREEN, client);
				BagHandler.clickCraftingSlot(client, 1);
			}
			while (bagThreeKeybind.wasPressed()) {
				ScreenHandler.displayScreen(Screen.INVENTORY_SCREEN, client);
				BagHandler.clickCraftingSlot(client, 2);
			}
			while (bagFourKeybind.wasPressed()) {
				ScreenHandler.displayScreen(Screen.INVENTORY_SCREEN, client);
				BagHandler.clickCraftingSlot(client, 3);
			}
		});

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (modMenuKeybind.wasPressed()) {
				client.setScreen(ModConfig.build().generateScreen(client.currentScreen));
			}
		});
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
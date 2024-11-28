package com.duelco;

import com.duelco.config.ModConfig;
import com.duelco.handlers.SkinFlipperHandler;
import com.duelco.handlers.SlashMeContinuesHandler;
import com.duelco.ui.screen.BingoScreen;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.JanksonConfigSerializer;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DuelUtilsClient implements ClientModInitializer {
	private static final Identifier SCREEN_ID = Identifier.of("fabricmod", "apple_grid_screen");

	private static KeyBinding skinFlipperToggleKeybind;
	private static KeyBinding bingoScreenKeybind;
	public static final Logger LOGGER = LoggerFactory.getLogger("duelutils-client");
	public static ModConfig config;

	@Override
	public void onInitializeClient() {
		AutoConfig.register(
				ModConfig.class,
				PartitioningSerializer.wrap(JanksonConfigSerializer::new)
		);

		config = AutoConfig.getConfigHolder(ModConfig.class).getConfig();

		registerKeybinds();
		SlashMeContinuesHandler.register();

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (skinFlipperToggleKeybind.wasPressed()) {
				SkinFlipperHandler.execute();
			}
			while (bingoScreenKeybind.wasPressed()) {
				client.setScreen(new BingoScreen());
			}
		});
	}

	private void registerKeybinds() {
		skinFlipperToggleKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelco.flipskin", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_K, // The keycode of the key
				"category.duelco.skinflipper" // The translation key of the keybinding's category.
		));

		bingoScreenKeybind = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelco.bingo_screen",
				InputUtil.Type.KEYSYM,
				GLFW.GLFW_KEY_K,
				"category.duelco.bingo_options"
		));
	}


}
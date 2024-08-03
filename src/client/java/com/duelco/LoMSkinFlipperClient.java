package com.duelco;

import com.duelco.managers.SkinFlipperManager;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.PlayerListEntry;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoMSkinFlipperClient implements ClientModInitializer {
	private static KeyBinding keyBinding;
	private SkinFlipperManager skinFlipperDataManager;
	public static final Logger LOGGER = LoggerFactory.getLogger("lom-skin-flipper-client");

	@Override
	public void onInitializeClient() {
		AutoConfig.register(SkinFlipperConfig.class, GsonConfigSerializer::new);
		this.skinFlipperDataManager = new SkinFlipperManager();

		keyBinding = KeyBindingHelper.registerKeyBinding(new KeyBinding(
				"key.duelco.flipskin", // The translation key of the keybinding's name
				InputUtil.Type.KEYSYM, // The type of the keybinding, KEYSYM for keyboard, MOUSE for mouse.
				GLFW.GLFW_KEY_K, // The keycode of the key
				"category.duelco.skinflipper" // The translation key of the keybinding's category.
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (keyBinding.wasPressed()) {
				LOGGER.info("Keybind pressed.");

				if (client.player != null) {
					LOGGER.info("Retrieving skin url.");
					String skinUrl = getPlayerSkin(client);
					LOGGER.info("The retrieved player skin is " + skinUrl);
					LOGGER.info("Executing skin flip.");
					String newSkin = skinFlipperDataManager.flipSkin(getPlayerSkin(client));
					LOGGER.info("The new player skin is " + newSkin);
					client.player.networkHandler.sendChatCommand("skin " + newSkin);
//					client.player.networkHandler.sendChatCommand("skin " + newSkin);
					LOGGER.info("The command is is " + "/skin " + newSkin);
				} else {
					LOGGER.debug("Player is null, skipping command execution.");
				}
			}
		});
	}

	private String getPlayerSkin(MinecraftClient client) {
		if (client.player != null) {
			PlayerListEntry playerEntry = client.player.networkHandler.getPlayerListEntry(client.player.getGameProfile().getId());
			if (playerEntry != null) {
				return playerEntry.getSkinTextures().textureUrl();
			}
		}
		return null;
	}
}
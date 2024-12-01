package com.duelco.managers;

import com.duelco.config.ModConfig;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformationHelperManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("TransformationHelperManager");
    public String handleTransform(String currentSkin) {
        if (!ModConfig.transformationSkin.isBlank()) {
            if (ModConfig.isTransformed) {
                ModConfig.isTransformed = false;
                return ModConfig.regularSkin;
            } else {
                ModConfig.regularSkin = currentSkin;
                ModConfig.isTransformed = true;
                return ModConfig.transformationSkin;
            }
        } else {
            MinecraftClient.getInstance().getToastManager().add(new SystemToast(
                    SystemToast.Type.CHUNK_SAVE_FAILURE,
                    Text.of("No Transformation Skin"),
                    Text.of("Set a transformation skin in the config.")
            ));
            return null;
        }
    }
}

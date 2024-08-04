package com.duelco.managers;

import com.duelco.LomEnhancedClient;
import com.duelco.config.SkinFlipperConfig;
import me.shedaniel.autoconfig.AutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinFlipperManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-data-manager");
    public SkinFlipperConfig config;
    public String flipSkin(String currentSkin) {
        if (LomEnhancedClient.config.skinFlipperConfig.isSkinFlipped()) {
            LomEnhancedClient.config.skinFlipperConfig.setSkinFlipped(false);
            return LomEnhancedClient.config.skinFlipperConfig.getChangingSkin();
        } else {
            LomEnhancedClient.config.skinFlipperConfig.setChangingSkin(currentSkin);
            LomEnhancedClient.config.skinFlipperConfig.setSkinFlipped(true);
            return LomEnhancedClient.config.skinFlipperConfig.getConstantSkin();
        }
    }
}

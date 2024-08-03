package com.duelco.managers;

import com.duelco.SkinFlipperConfig;
import me.shedaniel.autoconfig.AutoConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinFlipperManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-data-manager");
    public SkinFlipperConfig config;
    public SkinFlipperManager() {
        LOGGER.info("Initializing Skin Flipper Data Manager...");
        this.config = AutoConfig.getConfigHolder(SkinFlipperConfig.class).getConfig();
    }
    public String flipSkin(String currentSkin) {
        if (this.config.isSkinFlipped()) {
            this.config.setSkinFlipped(false);
            return this.config.getChangingSkin();
        } else {
            this.config.setChangingSkin(currentSkin);
            this.config.setSkinFlipped(true);
            return this.config.getConstantSkin();
        }
    }
}

package com.duelco.managers;

import com.duelco.DuelUtilsClient;
import com.duelco.config.SkinFlipperConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SkinFlipperManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-data-manager");
    public String flipSkin(String currentSkin) {
        if (DuelUtilsClient.config.skinFlipperConfig.isSkinFlipped()) {
            DuelUtilsClient.config.skinFlipperConfig.setSkinFlipped(false);
            return DuelUtilsClient.config.skinFlipperConfig.getChangingSkin();
        } else {
            DuelUtilsClient.config.skinFlipperConfig.setChangingSkin(currentSkin);
            DuelUtilsClient.config.skinFlipperConfig.setSkinFlipped(true);
            return DuelUtilsClient.config.skinFlipperConfig.getConstantSkin();
        }
    }
}

package com.duelco.managers;

import com.duelco.DuelUtilsClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TransformationHelperManager {
    private static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-data-manager");
    public String handleTransform(String currentSkin) {
        if (DuelUtilsClient.config.transformationHelperConfig.isTransformed()) {
            DuelUtilsClient.config.transformationHelperConfig.setIsTransformed(false);
            return DuelUtilsClient.config.transformationHelperConfig.getTransformationSkin();
        } else {
            DuelUtilsClient.config.transformationHelperConfig.setRegularSkin(currentSkin);
            DuelUtilsClient.config.transformationHelperConfig.setIsTransformed(true);
            return DuelUtilsClient.config.transformationHelperConfig.getRegularSkin();
        }
    }
}

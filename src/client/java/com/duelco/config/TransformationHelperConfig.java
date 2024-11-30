package com.duelco.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "transformationHelperConfig")
public class TransformationHelperConfig implements ConfigData {
    public static final Logger LOGGER = LoggerFactory.getLogger("config.transformationHelperConfig");

    @ConfigEntry.Gui.Tooltip()
    public boolean isTransformed;
    @ConfigEntry.Gui.Tooltip()
    public String regularSkin;
    @ConfigEntry.Gui.Tooltip()
    public String transformationSkin;

    public boolean isTransformed() {
        return isTransformed;
    }

    public void setIsTransformed(boolean isTransformed) {
        this.isTransformed = isTransformed;
    }

    public String getRegularSkin() {
        return regularSkin;
    }

    public void setRegularSkin(String changingSkin) {
        this.regularSkin = regularSkin;
    }

    public String getTransformationSkin() {
        return transformationSkin;
    }

    public void setTransformationSkin(String transformationSkin) {
        this.transformationSkin = transformationSkin;
    }
}

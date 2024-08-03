package com.duelco.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "lom-skin-flipper")
public class SkinFlipperConfig implements ConfigData {
    public static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-config");

    @ConfigEntry.Gui.Tooltip()
    public boolean isSkinFlipped;
    @ConfigEntry.Gui.Tooltip()
    public String changingSkin;
    @ConfigEntry.Gui.Tooltip()
    public String constantSkin;

    public boolean isSkinFlipped() {
        return isSkinFlipped;
    }

    public void setSkinFlipped(boolean skinFlipped) {
        isSkinFlipped = skinFlipped;
    }

    public String getChangingSkin() {
        return changingSkin;
    }

    public void setChangingSkin(String changingSkin) {
        this.changingSkin = changingSkin;
    }

    public String getConstantSkin() {
        return constantSkin;
    }

    public void setConstantSkin(String constantSkin) {
        this.constantSkin = constantSkin;
    }
}

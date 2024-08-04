package com.duelco.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "levelUpMessageConfig")
public class LevelUpMessageConfig implements ConfigData {
    public static final Logger LOGGER = LoggerFactory.getLogger("config.LevelUpMessageConfig");

    @ConfigEntry.Gui.Tooltip()
    public boolean areMessagesEnabled;

    public boolean areMessagesEnabled() {
        return areMessagesEnabled;
    }

    public void setAreMessagesEnabled(boolean areMessagesEnabled) {
        this.areMessagesEnabled = areMessagesEnabled;
    }
}

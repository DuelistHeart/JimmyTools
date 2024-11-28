package com.duelco.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "BingoConfig")
public class BingoConfig implements ConfigData {
    public static final Logger LOGGER = LoggerFactory.getLogger("config.BingoConfig");

    @ConfigEntry.Gui.Tooltip()
    public int maxCards = 3;

    public int getMaxCards() {
        return maxCards;
    }
}

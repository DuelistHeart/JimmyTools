package com.duelco.config;

import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Config(name = "chatUtilsConfig")
public class ChatUtilsConfig implements ConfigData {
    public static final Logger LOGGER = LoggerFactory.getLogger("config.ChatUtilsConfig");

    @ConfigEntry.Gui.Tooltip()
    public boolean slashMeContinuesEnabled;

    @ConfigEntry.Gui.Tooltip()
    public boolean startupCommandsNamesEnabled;

//    @ConfigEntry.Gui.Tooltip()
//    public NamesCmdOptions startupCommandsNamesOption;

    public boolean isSlashMeContinuesEnabled() {
        return slashMeContinuesEnabled;
    }

    public boolean isStartupCommandsNamesEnabled() {
        return startupCommandsNamesEnabled;
    }

//    public NamesCmdOptions getStartupCommandsNamesOption() {
//        return startupCommandsNamesOption;
//    }
}

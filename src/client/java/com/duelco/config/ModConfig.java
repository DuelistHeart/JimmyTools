package com.duelco.config;

import me.shedaniel.autoconfig.annotation.Config;
import me.shedaniel.autoconfig.annotation.ConfigEntry;
import me.shedaniel.autoconfig.serializer.PartitioningSerializer;

@Config(name = "duelutils")
public class ModConfig extends PartitioningSerializer.GlobalData {
    @ConfigEntry.Category("skinFlipperConfig")
    @ConfigEntry.Gui.TransitiveObject
    public SkinFlipperConfig skinFlipperConfig = new SkinFlipperConfig();

    @ConfigEntry.Category("levelUpMessageConfig")
    @ConfigEntry.Gui.TransitiveObject
    public LevelUpMessageConfig levelUpMessageConfig = new LevelUpMessageConfig();

    @ConfigEntry.Category("bingoConfig")
    @ConfigEntry.Gui.TransitiveObject
    public BingoConfig bingoConfig = new BingoConfig();

    @ConfigEntry.Category("chatUtilsConfig")
    @ConfigEntry.Gui.TransitiveObject
    public ChatUtilsConfig chatUtilsConfig = new ChatUtilsConfig();
}
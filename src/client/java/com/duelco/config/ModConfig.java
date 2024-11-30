package com.duelco.config;

import com.duelco._enum.NamesCmdOptions;
import eu.midnightdust.lib.config.MidnightConfig;

public class ModConfig extends MidnightConfig {
    @Entry(category = "Bingo", name = "Max Bingo Card Count")
    public static int bingoMaxCards = 12; // The isColor property adds a color chooser for a hexadecimal color
    @Entry(category = "Bingo", width = 7, min = 7, isColor = true, name = "Bingo Card Background Color")
    public static String bingoBackgroundColor = "#ffffff"; // The isColor property adds a color chooser for a hexadecimal color
    @Entry(category = "Bingo", width = 7, min = 7, isColor = true, name = "Bingo Card Grid Color")
    public static String bingoGridColor = "#ffffff"; // The isColor property adds a color chooser for a hexadecimal color

    @Entry(category = "TransformationHelper", name = "Currently Transformed")
    public static boolean isTransformed = false;
    @Entry(category = "TransformationHelper", name = "Regular Form")
    public static String regularSkin = "";
    @Entry(category = "TransformationHelper", name = "Transformation Form")
    public static String transformationSkin = "";

    @Entry(category = "ChatUtils", name = "/me Continues Enabled")
    public static boolean slashMeContinuesEnabled = true;
    @Entry(category = "ChatUtils", name = "LevelUp Messages Enabled")
    public static boolean areLevelUpMessagesEnabled = true;
    @Entry(category = "ChatUtils", name = "StartupCommands Enabled for /names")
    public static boolean startupCommandsNamesEnabled = true;
    @Entry(category = "ChatUtils", name = "Option for /names")
    public static NamesCmdOptions startupCommandsNamesOption = NamesCmdOptions.NAMES_OFF;

    public static int getBingoBackgroundColorPackedInt() {
        return hexToPackedInt(bingoBackgroundColor);
    }

    public static int getBingoGridColorPackedInt() {
        return hexToPackedInt(bingoGridColor);
    }

    private static int hexToPackedInt(String hexColor) {
        // Remove the '#' if it exists
        hexColor = hexColor.startsWith("#") ? hexColor.substring(1) : hexColor;

        // Parse the color values
        int red = Integer.parseInt(hexColor.substring(0, 2), 16);
        int green = Integer.parseInt(hexColor.substring(2, 4), 16);
        int blue = Integer.parseInt(hexColor.substring(4, 6), 16);

        // Pack RGB into a single integer
        return (red << 16) | (green << 8) | blue;
    }
}
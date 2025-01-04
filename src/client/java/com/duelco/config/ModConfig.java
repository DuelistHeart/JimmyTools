package com.duelco.config;

import com.duelco._enum.NamesCmdOptions;
import dev.isxander.yacl3.api.*;
import dev.isxander.yacl3.api.controller.*;
import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;

public class ModConfig {
    public static ConfigClassHandler<ModConfig> HANDLER = ConfigClassHandler.createBuilder(ModConfig.class)
            .id(Identifier.of("jimmytools", "config"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance().getConfigDir().resolve("jimmytools.json5"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    public static int bingoMaxCards = 3; // The isColor property adds a color chooser for a hexadecimal color
    @SerialEntry()
    public static Color bingoBackgroundColor = Color.decode("#e2d5c4"); // The isColor property adds a color chooser for a hexadecimal color
    @SerialEntry
    public static Color bingoGridColor = Color.decode("#d59989"); // The isColor property adds a color chooser for a hexadecimal color
    @SerialEntry
    public static boolean isBingoMarkerPlaceSoundEnabled = true;
    @SerialEntry
    public static boolean isBingoMarkerRemoveSoundEnabled = true;
    @SerialEntry
    public static boolean isBingoCardClearSoundEnabled = true;
    @SerialEntry
    public static boolean isBingoCardGenerateSoundEnabled = true;


    @SerialEntry
    public static boolean areTransformationsEnabled = false;
    @SerialEntry
    public static boolean isTransformed = false;
    @SerialEntry
    public static String regularSkin = "";
    @SerialEntry
    public static String transformationSkin = "";

    @SerialEntry
    public static boolean areLevelUpMessagesEnabled = false;
    @SerialEntry
    public static boolean startupCommandsNamesEnabled = false;
    @SerialEntry
    public static NamesCmdOptions startupCommandsNamesOption = NamesCmdOptions.NAMES_CHAR;

    public static YetAnotherConfigLib build() {
        return YetAnotherConfigLib.createBuilder()
                .title(Text.literal("JimmyTools Config"))
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Bingo"))
                        .tooltip(Text.literal("Bingo config"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Color Options"))
                                .description(OptionDescription.of(Text.literal("Color options for Bingo cards")))
                                .option(Option.<Color>createBuilder()
                                        .name(Text.literal("Background Color"))
                                        .description(OptionDescription.of(Text.literal("Background color of the bingo card.")))
                                        .binding(Color.BLACK, () -> bingoBackgroundColor, newVal -> bingoBackgroundColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .option(Option.<Color>createBuilder()
                                        .name(Text.literal("Grid Color"))
                                        .description(OptionDescription.of(Text.literal("Grid color of the bingo card.")))
                                        .binding(Color.BLACK, () -> bingoGridColor, newVal -> bingoGridColor = newVal)
                                        .controller(ColorControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Generation Options"))
                                .description(OptionDescription.of(Text.literal("Generation options for Bingo cards")))
                                .option(Option.<Integer>createBuilder()
                                        .name(Text.literal("Max Cards"))
                                        .description(OptionDescription.of(Text.literal("The max amount of cards that can be generated.")))
                                        .binding(3, () -> bingoMaxCards, newVal -> bingoMaxCards = newVal)
                                        .controller(integerOption -> IntegerSliderControllerBuilder.create(integerOption)
                                                .range(1, 50)
                                                .step(1)
                                                .formatValue(val -> Text.of(val + " Card(s)")))
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Sound Options"))
                                .description(OptionDescription.of(Text.literal("Sound options for Bingo cards")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Marker Place Sound"))
                                        .description(OptionDescription.of(Text.literal("Determines if a sound plays when placing Bingo markers.")))
                                        .binding(true, () -> isBingoMarkerPlaceSoundEnabled, newVal -> isBingoMarkerPlaceSoundEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Marker Remove Sound"))
                                        .description(OptionDescription.of(Text.literal("Determines if a sound plays when removing Bingo markers.")))
                                        .binding(true, () -> isBingoMarkerRemoveSoundEnabled, newVal -> isBingoMarkerRemoveSoundEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Card Clear Sound"))
                                        .description(OptionDescription.of(Text.literal("Determines if a sound plays when clearing Bingo cards.")))
                                        .binding(true, () -> isBingoCardClearSoundEnabled, newVal -> isBingoCardClearSoundEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Card Generate Sound"))
                                        .description(OptionDescription.of(Text.literal("Determines if a sound plays when generating Bingo cards.")))
                                        .binding(true, () -> isBingoCardGenerateSoundEnabled, newVal -> isBingoCardGenerateSoundEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("ChatUtils"))
                        .tooltip(Text.literal("ChatUtils config"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("LevelUp Options"))
                                .description(OptionDescription.of(Text.literal("Options for the LevelUp Messages")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable LevelUp Messages"))
                                        .description(OptionDescription.of(Text.literal("Enables/Disables level up messages from appearing.")))
                                        .binding(true, () -> areLevelUpMessagesEnabled, newVal -> areLevelUpMessagesEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("StartUp Command Options"))
                                .description(OptionDescription.of(Text.literal("Options for startup commands")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Enable Names command"))
                                        .description(OptionDescription.of(Text.literal("Enables/Disables running /names on server join.")))
                                        .binding(true, () -> startupCommandsNamesEnabled, newVal -> startupCommandsNamesEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<NamesCmdOptions>createBuilder()
                                        .name(Text.literal("Names command option"))
                                        .description(OptionDescription.of(Text.literal("The option that is ran for /names on startup.")))
                                        .binding(NamesCmdOptions.NAMES_OFF, () -> startupCommandsNamesOption, newVal -> startupCommandsNamesOption = newVal)
                                        .controller(opt -> EnumControllerBuilder.create(opt)
                                                .enumClass(NamesCmdOptions.class)
                                                .formatValue(v -> Text.translatable("jimmytools.config.startupcommands.namesoptions." + v.name().toLowerCase())))
                                        .build())
                                .build())
                        .build())
                .category(ConfigCategory.createBuilder()
                        .name(Text.literal("Transformation"))
                        .tooltip(Text.literal("Transformation config"))
                        .group(OptionGroup.createBuilder()
                                .name(Text.literal("Skin Settings"))
                                .description(OptionDescription.of(Text.literal("Skin settings for transformations")))
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Transformation Enabled"))
                                        .description(OptionDescription.of(Text.literal("Determines if transformations are enabled or not.")))
                                        .binding(false, () -> areTransformationsEnabled, newVal -> areTransformationsEnabled = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Text.literal("Regular Skin"))
                                        .description(OptionDescription.of(Text.literal("The URL of the pre-transformation skin. (Auto-Updates)")))
                                        .binding("", () -> regularSkin, newVal -> regularSkin = newVal)
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<String>createBuilder()
                                        .name(Text.literal("Transformation Skin"))
                                        .description(OptionDescription.of(Text.literal("The URL of the transformation skin")))
                                        .binding("", () -> transformationSkin, newVal -> transformationSkin = newVal)
                                        .controller(StringControllerBuilder::create)
                                        .build())
                                .option(Option.<Boolean>createBuilder()
                                        .name(Text.literal("Skin Transformed"))
                                        .description(OptionDescription.of(Text.literal("Denotes if a transformation is currently active")))
                                        .binding(true, () -> isTransformed, newVal -> isTransformed = newVal)
                                        .controller(BooleanControllerBuilder::create)
                                        .build())
                                .build())
                        .build())
                .build();
    }
}

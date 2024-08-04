package com.duelco.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.network.listener.ClientPlayPacketListener;
import net.minecraft.network.packet.s2c.play.SubtitleS2CPacket;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Mixin(SubtitleS2CPacket.class)
public class TitleMixin {
    private static final Logger LOGGER = LoggerFactory.getLogger("title-mixin");
    private static final List<Text> pastLvlUpMsgs = new ArrayList<>();

    @Final
    @Shadow private Text text;
    @Inject(at = @At("HEAD"), method = "apply(Lnet/minecraft/network/listener/ClientPlayPacketListener;)V")
    private void onTitle(ClientPlayPacketListener clientPlayPacketListener, CallbackInfo ci) {
        LOGGER.info("receiving title: {}", text);
        MinecraftClient client = MinecraftClient.getInstance();

        Text lvlUpMsg = this.getLevelUpMessage(text.getString());

        if (client.player != null) {
            if (lvlUpMsg != null && !Objects.equals(lvlUpMsg.getString(), "[]") && !pastLvlUpMsgs.contains(lvlUpMsg)) {
                client.player.sendMessage(Text.of("Subtitle: " + lvlUpMsg.getString()), false);
                client.player.sendMessage(lvlUpMsg, false);
                pastLvlUpMsgs.add(lvlUpMsg);
            }
        }
    }

    @Unique
    private Text getLevelUpMessage(String lvlUpMsg) {
        // Define a regex pattern to match the input string and capture the desired parts
        String regex = "Your (\\w+) Level has increased to (\\d+)!!!";

        // Compile the regex pattern
        Pattern pattern = Pattern.compile(regex);

        // Create a matcher for the input string
        Matcher matcher = pattern.matcher(lvlUpMsg);

        // Check if the pattern matches
        if (matcher.find()) {
            // Extract the captured groups
            String activity = matcher.group(1);
            String level = matcher.group(2);

            // Print the extracted parts
            Text activityText = Text.literal(activity).formatted(Formatting.AQUA);
            LOGGER.info("Activity: " + activity);
            Text levelText = Text.literal(level).formatted(Formatting.AQUA);
            LOGGER.info("Level: " + level);

            return Text.literal("[LevelUp] Your ").formatted(Formatting.GOLD)
                    .append(activityText)
                    .append(" level has increased to ").formatted(Formatting.GOLD)
                    .append(levelText)
                    .append("!!!").formatted(Formatting.GOLD);
        } else {
            LOGGER.info("Subtitle was not a level up notification.");
            return null;
        }
    }
}

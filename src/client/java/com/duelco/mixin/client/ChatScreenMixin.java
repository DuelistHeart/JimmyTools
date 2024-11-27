package com.duelco.mixin.client;

import com.duelco.LomEnhancedClient;
import com.duelco.handlers.SlashMeContinuesHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.ChatScreen;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(ChatScreen.class)
public class ChatScreenMixin {
    MinecraftClient client = MinecraftClient.getInstance();

    @Inject(at = @At("HEAD"), method = "sendMessage", cancellable = true)
    protected void checkSlashMeMessage(String chatText, boolean addToHistory, CallbackInfo info) {
//        SlashMeContinuesHandler.handleChatMessage(chatText, MinecraftClient.getInstance().player);
        if (chatText.startsWith("/me")) {
            List<String> slashMeSplit = SlashMeContinuesHandler.handleChatMessage(chatText);

            if (slashMeSplit.size() > 2) {
                client.player.sendMessage(Text.literal("That message is a bit too long, it is recommended to shorten it.").formatted(Formatting.RED));
                info.cancel();
            } else if (slashMeSplit.size() > 1) {
                for (String message : slashMeSplit) {
                    client.player.networkHandler.sendChatCommand("me " + message);
                }
                info.cancel();
            }
        }
    }

    @Inject(at = @At("HEAD"), method = "sendMessage", cancellable = true)
    protected void checkForCharacterChange(String chatText, boolean addToHistory, CallbackInfo info) {

        System.out.println("Chat text: " + chatText);
    }
}
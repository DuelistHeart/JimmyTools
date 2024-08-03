package com.duelco.handlers;

import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.StringJoiner;

public class SlashMeContinuesHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger("handlers.SlashMeContinuesHandler");
    private static final MinecraftClient client = MinecraftClient.getInstance();
    public static void register() {
    }

    public static List<String> handleChatMessage(String chatText) {
        String parsedChatText = chatText.substring(4);
        List<String> newChatMessages = new ArrayList<>();

        if (parsedChatText.length() > 50) {
            StringJoiner sentence = new StringJoiner(" ");

            for (String word: parsedChatText.split(" ")) {
                if ((sentence + word).length() <= 50) {
                    sentence.add(word);
                    LOGGER.info("Chat Message in loop: {}", sentence);
                    LOGGER.info("Chat Message size in loop: {}", sentence.length());
                } else {
                    newChatMessages.add(sentence.toString());
                    sentence = new StringJoiner(" ");
                    sentence.add(word);
                }
            }

            newChatMessages.add(sentence.toString());
//            newChatMessages.add(parsedChatText.substring(0,49));
//            newChatMessages.add(parsedChatText.substring(50));
        } else {
            newChatMessages.add(parsedChatText);
        }

        LOGGER.info("A chat message was sent!!!");

        LOGGER.info("Chat message: {}", chatText);
        LOGGER.info("Length of chat message: {}", chatText.length());

        return newChatMessages;
    }
}

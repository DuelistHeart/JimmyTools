package com.duelco.managers;

import com.duelco._enum.Screen;
import com.duelco.config.ModConfig;
import com.duelco.obj.bingo.BingoCard;
import com.duelco.ui.screen.ScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BingoManager {
    private static List<BingoCard> bingoCards = new ArrayList<>();

    public static void generateCard() {
        if (bingoCards.size() < ModConfig.bingoMaxCards) {
            BingoCard generatedCard = new BingoCard();

            generatedCard.initItems();
            bingoCards.add(generatedCard);
            DataManager.saveData();
            ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, MinecraftClient.getInstance());
        } else {
            ToastManager.displayToast(Text.of("Max Cards Reached"), Text.of("Max cards currently set to " + ModConfig.bingoMaxCards));
        }
    }

    public static void loadCards(List<BingoCard> pregeneratedCards) {
        bingoCards = pregeneratedCards;
    }

    public static void resetCards() {
        bingoCards.clear();
        DataManager.getDataStore().getBingoCards().clear();
        DataManager.saveData();
    }

    public static List<BingoCard> getCards() {
        return bingoCards;
    }
}

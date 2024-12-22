package com.duelco.managers;

import com.duelco._enum.Screen;
import com.duelco.config.ModConfig;
import com.duelco.obj.BingoCard;
import com.duelco.ui.screen.ScreenHandler;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BingoManager {
    private final List<BingoCard> bingoCards = new ArrayList<>();

    public void generateCard() {
        if (this.bingoCards.size() < ModConfig.bingoMaxCards) {
            this.bingoCards.add(new BingoCard());
            ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, MinecraftClient.getInstance());
        } else {
            ToastManager.displayToast(Text.of("Max Cards Reached"), Text.of("Max cards currently set to " + ModConfig.bingoMaxCards));
        }
    }

    public void resetCards() {
        this.bingoCards.clear();
    }

    public List<BingoCard> getCards() {
        return bingoCards;
    }
}

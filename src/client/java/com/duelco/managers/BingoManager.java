package com.duelco.managers;

import com.duelco.obj.BingoCard;
import com.duelco.ui.screen.BingoScreen;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class BingoManager {
    private final List<BingoCard> bingoCards = new ArrayList<>();

    public void clearMarksFromCards() {
        for (BingoCard bingoCard : this.bingoCards) {
            bingoCard.clear();
            MinecraftClient.getInstance().setScreen(new BingoScreen());
        }
    }

    public void generateCard() {
        if (this.bingoCards.size() < 3) {
            this.bingoCards.add(new BingoCard());
            MinecraftClient.getInstance().setScreen(new BingoScreen());
        } else {
            MinecraftClient.getInstance().getToastManager().add(new SystemToast(
                    SystemToast.Type.CHUNK_SAVE_FAILURE,
                    Text.of("Max Cards Reached"),
                    Text.of("Max cards currently set to 3")
            ));
        }
    }

    public void resetCards() {
        this.bingoCards.clear();
    }

    public List<BingoCard> getCards() {
        return bingoCards;
    }
}

package com.duelco.obj.data;

import com.duelco.managers.BingoManager;
import com.duelco.obj.general.UiPosition;
import com.duelco.obj.bingo.BingoCard;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public List<BingoCard> bingoCards;

    public DataStore() {
        this.bingoCards = new ArrayList<>();
    }

    public void setBingoCards(List<BingoCard> bingoCards) {
        this.bingoCards = bingoCards;
    }

    public List<BingoCard> getBingoCards() {
        return bingoCards;
    }

    public void unloadData() {
        BingoManager.loadCards(bingoCards);
    }
}

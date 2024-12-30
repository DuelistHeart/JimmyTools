package com.duelco.obj;

import com.duelco.managers.BingoManager;

import java.util.ArrayList;
import java.util.List;

public class DataStore {
    public List<BingoCard> bingoCards;
    private List<UiPosition> bingoMarkerPositions;

    public DataStore() {
        this.bingoCards = new ArrayList<>();
        this.bingoMarkerPositions = new ArrayList<>();
    }

    public void setBingoCards(List<BingoCard> bingoCards) {
        this.bingoCards = bingoCards;
    }

    public List<BingoCard> getBingoCards() {
        return bingoCards;
    }

    public void addBingoMarkerPosition(UiPosition markerPos) {
        this.bingoMarkerPositions.add(markerPos);
    }

    public void removeBingoMarkerPosition(UiPosition markerPos) {
        this.bingoMarkerPositions.remove(markerPos);
    }

    public void unloadData() {
        BingoManager.loadCards(bingoCards);
    }

    public List<UiPosition> getBingoMarkerPositions() {
        return bingoMarkerPositions;
    }
}

package com.duelco.obj.bingo;

import com.duelco.obj.general.UiPosition;

import java.util.List;

public class BingoCard {
    private List<BingoItem> items;
    private List<UiPosition> bingoMarkerPositions;

    public BingoCard(BingoCard bingoCard) {
        this.items = bingoCard.getItems();
        this.bingoMarkerPositions = bingoCard.getBingoMarkerPositions();
    }

    public BingoCard() {
    }

    public void initItems() {
        this.items = BingoPossibleItemsList.generateBingoItemsList();
    }

    public List<BingoItem> getItems() {
        return this.items;
    }

    public void setItems(List<BingoItem> items) {
        this.items = items;
    }

    public List<UiPosition> getBingoMarkerPositions() {
        return this.bingoMarkerPositions;
    }

    public void setBingoMarkerPositions(List<UiPosition> bingoMarkerPositions) {
        this.bingoMarkerPositions = bingoMarkerPositions;
    }

    public void addMarker(UiPosition markerPos) {
        this.bingoMarkerPositions.add(markerPos);
    }
}

package com.duelco.obj.bingo;

import java.util.List;

public class BingoCard {
    private List<BingoItem> items;

    public BingoCard(BingoCard bingoCard) {
        this.items = bingoCard.getItems();
    }

    public BingoCard() {
    }

    public void initItems() {
        this.items = BingoPossibleItemsList.generateBingoItemsList();
    }

    public List<BingoItem> getItems() {
        return this.items;
    }
}

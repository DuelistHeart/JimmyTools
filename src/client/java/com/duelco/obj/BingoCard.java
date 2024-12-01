package com.duelco.obj;

import java.util.List;

public class BingoCard {
    private final List<BingoItem> items;

    public BingoCard() {
        this.items = BingoPossibleItemsList.generateBingoItemsList();
    }

    public void clear() {
        for (BingoItem item: this.items) {
            item.setMarked(false);
        }
    }

    public List<BingoItem> getItems() {
        return this.items;
    }
}

package com.duelco.obj;

import java.util.List;

public class BingoCard {
    private final List<BingoItem> items;

    public BingoCard() {
        this.items = BingoPossibleItemsList.generateBingoItemsList();
    }

    public List<BingoItem> getItems() {
        return this.items;
    }
}

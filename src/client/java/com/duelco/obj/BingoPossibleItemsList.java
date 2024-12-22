package com.duelco.obj;

import java.util.*;

public class BingoPossibleItemsList {
    private static final ArrayList<BingoItem> items = new ArrayList<>();
    private static final int MAX_CARD_SIZE = 25;

    public static void init(List<BingoItem> bingoItems) {
        items.clear();
        for (BingoItem item : bingoItems) {
            items.add(new BingoItem(item));
        }
    }

    public static List<BingoItem> generateBingoItemsList() {
        List<BingoItem> itemsForBingoCard = new ArrayList<>();
        List<BingoItem> claimedItems = new ArrayList<>();
        Random random = new Random();

        while (itemsForBingoCard.size() < MAX_CARD_SIZE) {
            int randIndex = random.nextInt(items.size());
            BingoItem randItem = items.get(randIndex);

            if (!claimedItems.contains(randItem)) {
                itemsForBingoCard.add(new BingoItem(randItem));
                claimedItems.add(randItem);
            }
        }

        return itemsForBingoCard;
    }

    public static List<BingoItem> getListOfPossibleItems() {
        items.sort(Comparator.comparing(BingoItem::getName));

        return items;
    }
}

package com.duelco.obj;

import com.google.gson.Gson;
import net.minecraft.item.Items;

import java.util.*;

public class BingoPossibleItemsList {
    private static final ArrayList<BingoItem> items = new ArrayList<>();
    private static final int MAX_CARD_SIZE = 25;

    private static void init() {
        items.clear();
        items.add(new BingoItem("Glass Bottle", 0, "Empty Bottle")); //Empty Bottle
        items.add(new BingoItem("Apple", 75, "Dwarven Ale")); // Dwarven Ale
        items.add(new BingoItem("Rabbit Foot", 45, "Playing Card")); // Playing Card
        items.add(new BingoItem("Rabbit Foot", 16, "Sea Shell")); // Sea Shell
        items.add(new BingoItem("Rabbit Foot", 9, "Raw Fish")); // Raw Fish
        items.add(new BingoItem("Bone", 1, "Dwarven Bones")); // Dwarven Bones
        items.add(new BingoItem("Rabbit Foot", 15, "Sand")); // Sand
        items.add(new BingoItem("Rabbit Foot", 18, "Raw Stone")); // Raw Stone
        items.add(new BingoItem("Rabbit Foot", 14, "Raw Ore")); // Raw Ore
        items.add(new BingoItem("Potion", 1, "Bottle of Water")); // Bottle of Water
        items.add(new BingoItem("Rabbit Foot", 39, "Christmas Envelope")); // Christmas Envelope
        items.add(new BingoItem("Apple", 15, "Goo Glue")); // Goo Glue
        items.add(new BingoItem("Rotten Flesh", 1, "Muk Pie")); // Muk Pie
        items.add(new BingoItem("Apple", 3, "Devilish Apples")); // Devilish Apples
        items.add(new BingoItem("Rabbit Foot", 38, "Crafting Scrape")); // Crafting Scrape
        items.add(new BingoItem("Filled Map", 0, "Map")); // Map
        items.add(new BingoItem("Wooden Shovel", 0, "Sewage Shovel")); // Sewage Shovel
        items.add(new BingoItem("Rabbit Foot", 10, "Gold Coin")); // Gold Coin
        items.add(new BingoItem("Rabbit Foot", 17, "Silver Coin")); // Silver Coin
        items.add(new BingoItem("Rabbit Foot", 8, "Penny")); // Penny
        items.add(new BingoItem("Cherry Sapling", 0, "Cherry Sapling")); // Cherry Sapling
        items.add(new BingoItem("Music Disc Cat", 22, "Holy Cleanser")); // Holy Cleanser
        items.add(new BingoItem("Paper", 0, "Holy Writs")); // Holy Writs
        items.add(new BingoItem("Music Disc Cat", 52, "Deck of Cards")); // Deck of Cards
        items.add(new BingoItem("Rabbit Foot", 13, "Muk")); // Muk
        items.add(new BingoItem("Music Disc Cat", 4, "Elven Dagger")); // Elven Dagger
        items.add(new BingoItem("Rabbit Foot", 12, "Goo")); // Goo
        items.add(new BingoItem("Rabbit Foot", 35, "Skill Book")); // Skill Book
        items.add(new BingoItem("Fishing Rod", 0, "Fishing Rod")); // Fishing Rod
        items.add(new BingoItem("Warped Fungus on a Stick", 1, "Wrench")); // Wrench
        items.add(new BingoItem("Iron Pickaxe", 1, "Dwarven Pickaxe")); // Dwarven Pickaxe
    }

    public static void init(List<BingoItem> bingoItems) {
        items.clear();
        for (BingoItem item : bingoItems) {
            items.add(new BingoItem(item));
        }
    }

    public static List<BingoItem> generateBingoItemsList() {
        if (items.isEmpty()) {
            init();
        }

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
        if (items.isEmpty()) {
            init();
        }

        items.sort(Comparator.comparing(BingoItem::getName));

        return items;
    }
}

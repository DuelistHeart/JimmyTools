package com.duelco.obj;

import net.minecraft.item.Items;

import java.util.*;

public class BingoPossibleItemsList {
    private static final ArrayList<BingoItem> items = new ArrayList<>();
    private static final int MAX_CARD_SIZE = 25;

    private static void init() {
        items.add(new BingoItem(Items.GLASS_BOTTLE.getDefaultStack(), 0, false, "Empty Bottle")); //Empty Bottle
        items.add(new BingoItem(Items.APPLE.getDefaultStack(), 75, false, "Dwarven Ale")); // Dwarven Ale
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 45, false, "Playing Card")); // Playing Card
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 16, false, "Sea Shell")); // Sea Shell
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 9, false, "Raw Fish")); // Raw Fish
        items.add(new BingoItem(Items.BONE.getDefaultStack(), 1, false, "Dwarven Bones")); // Dwarven Bones
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 15, false, "Sand")); // Sand
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 18, false, "Raw Stone")); // Raw Stone
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 14, false, "Raw Ore")); // Raw Ore
        items.add(new BingoItem(Items.POTION.getDefaultStack(), 1, false, "Bottle of Water")); // Bottle of Water
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 39, false, "Christmas Envelope")); // Christmas Envelope
        items.add(new BingoItem(Items.APPLE.getDefaultStack(), 15, false, "Goo Glue")); // Goo Glue
        items.add(new BingoItem(Items.ROTTEN_FLESH.getDefaultStack(), 1, false, "Muk Pie")); // Muk Pie
        items.add(new BingoItem(Items.APPLE.getDefaultStack(), 3, false, "Devilish Apples")); // Devilish Apples
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 38, false, "Crafting Scrape")); // Crafting Scrape
        items.add(new BingoItem(Items.FILLED_MAP.getDefaultStack(), 0, false, "Map")); // Map
        items.add(new BingoItem(Items.WOODEN_SHOVEL.getDefaultStack(), 0, false, "Sewage Shovel")); // Sewage Shovel
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 10, false, "Gold Coin")); // Gold Coin
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 17, false, "Silver Coin")); // Silver Coin
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 8, false, "Penny")); // Penny
        items.add(new BingoItem(Items.CHERRY_SAPLING.getDefaultStack(), 0, false, "Cherry Sapling")); // Cherry Sapling
        items.add(new BingoItem(Items.MUSIC_DISC_CAT.getDefaultStack(), 22, false, "Holy Cleanser")); // Holy Cleanser
        items.add(new BingoItem(Items.PAPER.getDefaultStack(), 0, false, "Holy Writs")); // Holy Writs
        items.add(new BingoItem(Items.MUSIC_DISC_CAT.getDefaultStack(), 52, false, "Deck of Cards")); // Deck of Cards
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 13, false, "Muk")); // Muk
        items.add(new BingoItem(Items.MUSIC_DISC_CAT.getDefaultStack(), 4, false, "Elven Dagger")); // Elven Dagger
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 12, false, "Goo")); // Goo
        items.add(new BingoItem(Items.RABBIT_FOOT.getDefaultStack(), 35, false, "Skill Book")); // Skill Book
        items.add(new BingoItem(Items.FISHING_ROD.getDefaultStack(), 0, false, "Fishing Rod")); // Fishing Rod
        items.add(new BingoItem(Items.WARPED_FUNGUS_ON_A_STICK.getDefaultStack(), 1, false, "Wrench")); // Wrench
        items.add(new BingoItem(Items.IRON_PICKAXE.getDefaultStack(), 1, false, "Dwarven Pickaxe")); // Dwarven Pickaxe
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

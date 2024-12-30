package com.duelco.handlers;

import com.duelco.obj.BingoItem;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class ItemHandler {
    public static ItemStack getItemFromString(String itemName) {
        // Using the itemName string, retrieve an itemstack.
        // Please support the defaultstacks listed in the init() method in BingoPossibleItemsList.java
        switch (itemName) {
            case "Apple":
                return Items.APPLE.getDefaultStack();
            case "Rabbit Foot":
                return Items.RABBIT_FOOT.getDefaultStack();
            case "Bone":
                return Items.BONE.getDefaultStack();
            case "Potion":
                return Items.POTION.getDefaultStack();
            case "Rotten Flesh":
                return Items.ROTTEN_FLESH.getDefaultStack();
            case "Filled Map":
                return Items.FILLED_MAP.getDefaultStack();
            case "Wooden Shovel":
                return Items.WOODEN_SHOVEL.getDefaultStack();
            case "Cherry Sapling":
                return Items.CHERRY_SAPLING.getDefaultStack();
            case "Music Disc Cat":
                return Items.MUSIC_DISC_CAT.getDefaultStack();
            case "Paper":
                return Items.PAPER.getDefaultStack();
            case "Fishing Rod":
                return Items.FISHING_ROD.getDefaultStack();
            case "Warped Fungus on a Stick":
                return Items.WARPED_FUNGUS_ON_A_STICK.getDefaultStack();
            case "Iron Pickaxe":
                return Items.IRON_PICKAXE.getDefaultStack();
            case "Glass Bottle":
                return Items.GLASS_BOTTLE.getDefaultStack();
            case "Wheat":
                return Items.WHEAT.getDefaultStack();
            default:
                return Items.AIR.getDefaultStack();
        }

    }
}

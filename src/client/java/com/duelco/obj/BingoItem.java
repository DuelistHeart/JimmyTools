package com.duelco.obj;

import com.duelco.handlers.ItemHandler;
import net.minecraft.component.*;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemStack;

public class BingoItem {
    // Mark item as ignorable for Gson
    private String baseItem;
    private boolean isFreeSpace;
    private int customModelDataNum;
    private String name;

    public BingoItem(String baseItem, int customModelDataNum, String name) {

        this.baseItem = baseItem;
        this.customModelDataNum = customModelDataNum;
        this.isFreeSpace = false;
        this.name = name;
    }

    public BingoItem(BingoItem bingoItem) {
        this.baseItem = bingoItem.getBaseItem();
        this.isFreeSpace = bingoItem.isFreeSpace();
        this.customModelDataNum = bingoItem.getCustomModelDataNum();
        this.name = bingoItem.getName();
    }

    public ItemStack getItem() {
        CustomModelDataComponent ItemComponents= new CustomModelDataComponent(customModelDataNum);
        ItemStack item = ItemHandler.getItemFromString(baseItem);
        item.set(DataComponentTypes.CUSTOM_MODEL_DATA, ItemComponents);
        return item;
    }

    public void setBaseItem(String baseItem) {
        this.baseItem = baseItem;
    }
    public String getBaseItem() {
        return baseItem;
    }

    public boolean isFreeSpace() {
        return isFreeSpace;
    }

    public int getCustomModelDataNum() {
        return customModelDataNum;
    }

    public void setCustomModelDataNum(int customModelDataNum) {
        this.customModelDataNum = customModelDataNum;
    }

    public void setFreeSpace(boolean isFreeSpace) {
        this.isFreeSpace = isFreeSpace;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package com.duelco.obj;

import com.duelco.screen.BingoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.StackLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.component.*;
import net.minecraft.component.type.CustomModelDataComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;

public class BingoItem {
    private ItemStack item;
    private boolean isMarked;
    private boolean isFreeSpace;
    private int customModelDataNum;

    public BingoItem(ItemStack item, int customModelDataNum, boolean isMarked, boolean isFreeSpace) {
        CustomModelDataComponent ItemComponents= new CustomModelDataComponent(customModelDataNum);
        item.set(DataComponentTypes.CUSTOM_MODEL_DATA, ItemComponents);

        this.item = item;
        this.customModelDataNum = customModelDataNum;
        this.isMarked = isMarked;
        this.isFreeSpace = isFreeSpace;
    }

    public BingoItem(ItemStack item, int customModelDataNum, boolean isMarked) {
        CustomModelDataComponent ItemComponents= new CustomModelDataComponent(customModelDataNum);
        item.set(DataComponentTypes.CUSTOM_MODEL_DATA, ItemComponents);

        this.item = item;
        this.isMarked = isMarked;
        this.isFreeSpace = isFreeSpace;
    }

    public BingoItem(BingoItem bingoItem) {
        this.item = bingoItem.getItem();
        this.isMarked = bingoItem.isMarked();
        this.isFreeSpace = bingoItem.isFreeSpace();
    }

    public StackLayout buildItemSlot() {
        StackLayout sampleSlot = (StackLayout) Containers.stack(Sizing.fixed(24), Sizing.fixed(24))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
                .padding(Insets.of(2));

        sampleSlot.child(
                Components.box(Sizing.fill(), Sizing.fill())
                        .fill(true)
                        .color(Color.ofRgb(15457478))
        );

        if (this.isFreeSpace) {
            sampleSlot.child(
                    Components.texture(Identifier.of("mymod", "owo_ui/free_space.png"), 1, 1, 256, 256)
                            .sizing(Sizing.fixed(20))
                            .zIndex(20)
            );
        } else {
            sampleSlot.child(
                    Components.item(item)
            );
        }

        sampleSlot.mouseDown().subscribe((a, b, c) -> {
            this.isMarked = !this.isMarked;
            MinecraftClient.getInstance().setScreen(new BingoScreen());
            return true;
        });

        if (isMarked) {
            sampleSlot.child(
                    Components.texture(Identifier.of("mymod", "owo_ui/marker.png"), 1, 1, 256, 256)
                        .sizing(Sizing.fixed(20))
            );
        }

        return sampleSlot;
    }

    public ItemStack getItem() {
        return item;
    }

    public void setItem(ItemStack item) {
        this.item = item;
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

    public boolean isMarked() {
        return isMarked;
    }

    public void setMarked(boolean marked) {
        this.isMarked = marked;
    }

    public void setFreeSpace(boolean isFreeSpace) {
        this.isFreeSpace = isFreeSpace;
    }
}

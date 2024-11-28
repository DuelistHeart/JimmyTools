package com.duelco.obj;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.GridLayout;
import io.wispforest.owo.ui.container.StackLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.util.Identifier;

import java.util.List;

public class BingoCard {
    private final List<BingoItem> items;

    public BingoCard() {
        this.items = BingoPossibleItemsList.generateBingoItemsList();
    }

    public StackLayout buildBingoCardComponent() {
        StackLayout bingoCard = (StackLayout) Containers.stack(Sizing.fixed(132), Sizing.fixed(165))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.BOTTOM);
        StackLayout bingoGridContainer = (StackLayout) Containers.stack(Sizing.fixed(130), Sizing.fixed(130))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER)
                .padding(Insets.of(4));
        GridLayout bingoGrid = (GridLayout) Containers.grid(Sizing.fixed(120), Sizing.fixed(120), 5, 5)
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        int fullIndex = 0;

        this.items.get(12).setFreeSpace(true);

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                bingoGrid.child(
                        this.items.get(fullIndex).buildItemSlot(), i, j
                );
                fullIndex++;
            }
        }

        bingoGridContainer.child(
                Components.box(Sizing.fill(), Sizing.fill())
                        .color(Color.ofRgb(13998473))
                        .fill(true)
        ).child(
                bingoGrid
        );

        bingoCard.child(
                Components.box(Sizing.fixed(130), Sizing.fixed(160))
                        .color(Color.ofRgb(15457478))
                        .fill(true)
        ).child(
                Components.texture(Identifier.of("mymod","owo_ui/bingo_header.png"), 1, 1, 255, 256)
                        .sizing(Sizing.fixed(100), Sizing.fixed(20))
                        .positioning(Positioning.relative(50, 10))
                        .zIndex(200)
        ).child(
                bingoGridContainer
        );

        return bingoCard;
    }

    public void clear() {
        for (BingoItem item: this.items) {
            item.setMarked(false);
        }
    }
}

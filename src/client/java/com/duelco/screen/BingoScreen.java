package com.duelco.screen;

import com.duelco.obj.BingoCard;
import com.duelco.obj.BingoItem;
import com.duelco.obj.BingoPossibleItemsList;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.GridLayout;
import io.wispforest.owo.ui.container.StackLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Colors;
import net.minecraft.util.Identifier;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class BingoScreen extends BaseOwoScreen<FlowLayout> {
    private static final List<BingoCard> bingoCards = new ArrayList<>();

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::horizontalFlow);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        if (bingoCards.isEmpty()) {
            bingoCards.add(new BingoCard());
            bingoCards.add(new BingoCard());
            bingoCards.add(new BingoCard());
        }

        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        FlowLayout bingoCardsLayout = (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fill())
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        for (BingoCard bingoCard : bingoCards) {
            bingoCardsLayout.child(bingoCard.buildBingoCardComponent());
        }

        rootComponent.child(
                bingoCardsLayout
        );
    }
}
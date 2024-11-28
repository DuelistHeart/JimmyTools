package com.duelco.screen;

import com.duelco.obj.BingoCard;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
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
        ).child(
                Components.button(Text.of("Clear cards"), buttonComponent -> {
                    for (BingoCard bingoCard : bingoCards) {
                        bingoCard.clear();
                        MinecraftClient.getInstance().setScreen(new BingoScreen());
                    }
                })
        );
    }
}
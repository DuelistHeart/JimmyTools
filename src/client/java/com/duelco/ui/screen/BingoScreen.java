package com.duelco.ui.screen;

import com.duelco.managers.BingoManager;
import com.duelco.obj.BingoCard;
import com.duelco.ui.managers.BingoCardUIManager;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class BingoScreen extends BaseOwoScreen<FlowLayout> {
    private static final BingoManager bingoManager = new BingoManager();

    @Override
    protected @NotNull OwoUIAdapter<FlowLayout> createAdapter() {
        return OwoUIAdapter.create(this, Containers::verticalFlow);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    protected void build(FlowLayout rootComponent) {
        rootComponent
                .surface(Surface.VANILLA_TRANSLUCENT)
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        FlowLayout bingoCardsLayout = (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        for (BingoCard bingoCard : bingoManager.getCards()) {
            bingoCardsLayout.child(BingoCardUIManager.buildBingoCardComponent(bingoCard));
        }

        FlowLayout buttonGroup = (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(22))
                .horizontalAlignment(HorizontalAlignment.CENTER);

        buttonGroup.child(
                Components.button(Text.of("Clear Cards"), buttonComponent -> {
                    bingoManager.clearMarksFromCards();
                    MinecraftClient.getInstance().setScreen(new BingoScreen());
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.of("Generate Card"), buttonComponent -> {
                    bingoManager.generateCard();
                    MinecraftClient.getInstance().setScreen(new BingoScreen());
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.of("Reset"), buttonComponent -> {
                    bingoManager.resetCards();
                    MinecraftClient.getInstance().setScreen(new BingoScreen());
                }).margins(Insets.of(2))
        );

        rootComponent.child(
                Components.label(Text.of("Bingo Screen"))
        ).child(
                bingoCardsLayout
        ).child(
                buttonGroup
        );
    }
}
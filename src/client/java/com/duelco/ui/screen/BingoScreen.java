package com.duelco.ui.screen;

import com.duelco._enum.Screen;
import com.duelco.handlers.ImageSelection;
import com.duelco.handlers.ScreenCaptureHandler;
import com.duelco.managers.BingoManager;
import com.duelco.managers.ToastManager;
import com.duelco.obj.bingo.BingoCard;
import com.duelco.ui.managers.BingoCardUIManager;
import com.duelco.ui.managers.BingoMarkerUIManager;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.StackLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class BingoScreen extends BaseOwoScreen<FlowLayout> {
    private static final BingoMarkerUIManager bingoMarkerManager = new BingoMarkerUIManager();

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

        StackLayout bingoCardsAndMarkerLayout = (StackLayout) Containers.stack(Sizing.fixed(500), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        FlowLayout bingoCardsLayout = (FlowLayout) Containers.horizontalFlow(Sizing.fixed(500), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        bingoCardsAndMarkerLayout.child(bingoCardsLayout)
                .child(bingoMarkerManager.bingoMarkersLayout);

        bingoCardsLayout.mouseDown().subscribe((x, y, btn) -> {
                    bingoMarkerManager.addMarker((int) x, (int) y);

                    return true;
                });

        for (BingoCard bingoCard : BingoManager.getCards()) {
            bingoCardsLayout.child(BingoCardUIManager.buildBingoCardComponent(bingoCard));
        }

        FlowLayout buttonGroup = (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(22))
                .horizontalAlignment(HorizontalAlignment.CENTER);

        buttonGroup.child(
                Components.button(Text.of("\uD83D\uDCF7"), buttonComponent -> {
                    ImageSelection.copyImageToClipboard(ScreenCaptureHandler.captureFramebuffer(MinecraftClient.getInstance().getFramebuffer()));
                    ToastManager.displayToast(Text.of("Copied Bingo Cards"), Text.of("Your bingo cards were copied to clipboard"));
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.clear_marks"),buttonComponent -> {
                    bingoMarkerManager.clearMarkers();
                    ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.generate_cards"), buttonComponent -> {
                    BingoManager.generateCard();
                    ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.reset"), buttonComponent -> {
                    ScreenHandler.displayConfirmationScreen(MinecraftClient.getInstance(),"Are you sure you want to reset (delete) your bingo cards?",
                    () -> {
                        bingoMarkerManager.clearMarkers();
                        BingoManager.resetCards();
                        ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
                    }, () -> {
                        ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
                    });
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.item_list"), buttonComponent -> {
                    ScreenHandler.displayScreen(Screen.BINGO_ITEMS_SCREEN, client);
                }).margins(Insets.of(2))
        );

        rootComponent.child(
                Components.label(Text.translatable("screen.jimmytools.bingo.title"))
        ).child(
                bingoCardsAndMarkerLayout
        ).child(
                buttonGroup
        );
    }
}
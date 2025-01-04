package com.duelco.ui.screen;

import com.duelco._enum.Screen;
import com.duelco.handlers.ImageSelection;
import com.duelco.handlers.ScreenCaptureHandler;
import com.duelco.managers.BingoManager;
import com.duelco.managers.ToastManager;
import com.duelco.obj.bingo.BingoCard;
import com.duelco.ui.managers.BingoCardUIManager;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.container.ScrollContainer;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class BingoScreen extends BaseOwoScreen<FlowLayout> {
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

        FlowLayout bingoCardsLayout = (FlowLayout) Containers.horizontalFlow(Sizing.content(), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

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
                    BingoManager.clearMarkers();
                    ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, client);
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.generate_cards"), buttonComponent -> {
                    BingoCard newBingoCard = BingoManager.generateCard();

                    if (newBingoCard != null) {
                        bingoCardsLayout.child(BingoCardUIManager.buildBingoCardComponent(newBingoCard));
                    }
                }).margins(Insets.of(2))
        ).child(
                Components.button(Text.translatable("buttons.jimmytools.bingo.reset"), buttonComponent -> {
                    ScreenHandler.displayConfirmationScreen(MinecraftClient.getInstance(),"Are you sure you want to reset (delete) your bingo cards?",
                    () -> {
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
            Containers.horizontalScroll(Sizing.fixed(340), Sizing.fixed(200), bingoCardsLayout)
                .scrollbar(ScrollContainer.Scrollbar.vanillaFlat())
                .scrollbarThiccness(5)
                .scrollStep(114)
        ).child(
                buttonGroup
        );
    }
}
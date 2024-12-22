package com.duelco.ui.screen;

import com.duelco._enum.Screen;
import io.wispforest.owo.ui.base.BaseOwoScreen;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import org.jetbrains.annotations.NotNull;

public class ConfirmationScreen extends BaseOwoScreen<FlowLayout> {

    private final Runnable onConfirm;

    ConfirmationScreen(String title, Runnable onConfirm) {
        super(Text.of(title));
        this.onConfirm = onConfirm;
    }

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

        rootComponent.child(
                Components.label(Text.translatable("screen.jimmytools.bingo.title"))
        ).child(
                Components.button(Text.of("Confirm"), buttonComponent -> onConfirm.run())
        ).child(
                Components.button(Text.of("Return"), buttonComponent -> ScreenHandler.displayScreen(Screen.BINGO_CARDS_SCREEN, MinecraftClient.getInstance()))
        );
    }
}

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
    private final Runnable onReturn;

    ConfirmationScreen(String title, Runnable onConfirm, Runnable onReturn) {
        super(Text.of(title));
        this.onConfirm = onConfirm;
        this.onReturn = onReturn;
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

        FlowLayout buttonGroup = (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(22))
                .horizontalAlignment(HorizontalAlignment.CENTER)
                .margins(Insets.of(5));

        buttonGroup.child(
                Components.button(Text.of("Confirm"), buttonComponent -> onConfirm.run())
                        .margins(Insets.of(2))
        ).child(
                Components.button(Text.of("Return"), buttonComponent -> onReturn.run())
                        .margins(Insets.of(2))
        );

        rootComponent.child(
                Components.label(Text.of(this.title))
        ).child(
                buttonGroup
        );
    }
}

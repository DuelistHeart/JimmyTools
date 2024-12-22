package com.duelco.ui.managers;

import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.util.Identifier;

public class BingoMarkerUIManager {
    private static final int MARKER_SIZE = 20;
    private static final int MARKER_INDEX = 30;

    public final FlowLayout bingoMarkersLayout;

    public BingoMarkerUIManager() {
        this.bingoMarkersLayout =  (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);
    }

    public void addMarker(int x, int y) {
        Component markerComponent = getMarkerComponent().positioning(Positioning.absolute(x-(MARKER_SIZE/2), y-(MARKER_SIZE/2)));
        markerComponent.mouseDown().subscribe((mx, my, btn) -> {
            markerComponent.remove();
            return true;
        });

        bingoMarkersLayout.child(
                markerComponent
        );
    }

    public void clearMarkers() {
        bingoMarkersLayout.clearChildren();
    }

    private Component getMarkerComponent() {
        return Components.texture(Identifier.of("jimmytools", "ui/marker.png"), 1, 1, 256, 256)
                .sizing(Sizing.fixed(MARKER_SIZE))
                .zIndex(MARKER_INDEX);
    }
}

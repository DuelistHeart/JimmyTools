package com.duelco.ui.managers;

import com.duelco.managers.DataManager;
import com.duelco.obj.DataStore;
import com.duelco.obj.UiPosition;
import io.wispforest.owo.ui.component.Components;
import io.wispforest.owo.ui.container.Containers;
import io.wispforest.owo.ui.container.FlowLayout;
import io.wispforest.owo.ui.core.*;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public class BingoMarkerUIManager {
    private static final int MARKER_SIZE = 20;
    private static final int MARKER_INDEX = 30;

    private static final List<UiPosition> bingoMarkerPositions = new ArrayList<>();
    public final FlowLayout bingoMarkersLayout;

    public BingoMarkerUIManager() {
        this.bingoMarkersLayout =  (FlowLayout) Containers.horizontalFlow(Sizing.fill(), Sizing.fixed(220))
                .alignment(HorizontalAlignment.CENTER, VerticalAlignment.CENTER);

        if (DataManager.getDataStore().getBingoMarkerPositions() != null && !DataManager.getDataStore().getBingoMarkerPositions().isEmpty()) {
            for (UiPosition markerPos : DataManager.getDataStore().getBingoMarkerPositions()) {
                bingoMarkerPositions.add(markerPos);
                addMarkerToComponent(markerPos);
            }
        }
    }

    public void addMarker(int x, int y) {
        UiPosition markerPos = new UiPosition(x-(MARKER_SIZE/2), y-(MARKER_SIZE/2));
        addMarkerToComponent(markerPos);
        bingoMarkerPositions.add(markerPos);
        DataManager.getDataStore().addBingoMarkerPosition(markerPos);
        DataManager.saveData();
    }

    public void clearMarkers() {
        bingoMarkersLayout.clearChildren();
        bingoMarkerPositions.clear();
        DataManager.getDataStore().getBingoMarkerPositions().clear();
        DataManager.saveData();
    }

    private void addMarkerToComponent(UiPosition markerPos) {
        Component markerComponent = getMarkerComponent().positioning(Positioning.absolute(markerPos.getX(), markerPos.getY()));

        markerComponent.mouseDown().subscribe((mx, my, btn) -> {
            bingoMarkerPositions.remove(markerPos);
            DataManager.getDataStore().removeBingoMarkerPosition(markerPos);
            markerComponent.remove();
            DataManager.saveData();
            return true;
        });

        bingoMarkersLayout.child(
                markerComponent
        );
    }

    private Component getMarkerComponent() {
        return Components.texture(Identifier.of("jimmytools", "ui/marker.png"), 1, 1, 256, 256)
                .sizing(Sizing.fixed(MARKER_SIZE))
                .zIndex(MARKER_INDEX);
    }
}

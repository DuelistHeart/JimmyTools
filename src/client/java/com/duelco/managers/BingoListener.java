package com.duelco.managers;
import com.duelco.obj.BingoItem;
import com.duelco.obj.BingoPossibleItemsList;
import com.google.gson.*;
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener;
import net.minecraft.resource.Resource;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BingoListener implements SimpleSynchronousResourceReloadListener {
    private static final Identifier ID = Identifier.of("jimmytools", "bingo_listener");
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Identifier getFabricId() {
        return ID;
    }

    @Override
    public void reload(ResourceManager manager) {
        // Load your JSON here
        Identifier jsonId = Identifier.of("jimmytools", "data/bingo_card.json");

        try {
            Optional<Resource> resource = manager.getResource(jsonId);
            if (resource.isPresent()) {
                List<BingoItem> bingoItems = new ArrayList<>();

                try (var stream = resource.get().getInputStream()) {
                    // Parse the JSON list, then copy it into a new ArrayList.
                    JsonArray jsonElement = JsonParser.parseReader(new InputStreamReader(stream)).getAsJsonArray();
                    for (JsonElement element : jsonElement) {
                        bingoItems.add(gson.fromJson(element, BingoItem.class));
                    }

                    BingoPossibleItemsList.init(bingoItems);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
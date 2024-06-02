package com.duelco.managers;

import com.duelco.obj.SkinFlipperData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import net.fabricmc.loader.api.FabricLoader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class SkinFlipperDataManager {
    public static final Logger LOGGER = LoggerFactory.getLogger("skin-flipper-data-manager");
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(FabricLoader.getInstance().getConfigDir().toFile(), "skin_flipper_data.json");
    private SkinFlipperData skinFlipperData;

    public SkinFlipperDataManager() {
        LOGGER.info("Initializing Skin Flipper Data Manager...");
        this.skinFlipperData = new SkinFlipperData();

        if (CONFIG_FILE.exists()) {
            LOGGER.info("Loading file...");
            this.loadSkinFlipperData();
        } else {
            LOGGER.info("Creating file...");
            this.saveSkinFlipperData();
        }
    }

    public SkinFlipperData getSkinFlipperData() {
        return skinFlipperData;
    }

    public String flipSkin(String currentSkin) {
        if (!skinFlipperData.isSkinFlipped()) { // Confirmed false
            skinFlipperData.setChangingSkin(currentSkin); // changes skin
        }

        String postFlipSkin = skinFlipperData.flipSkin();
        this.saveSkinFlipperData();
        return postFlipSkin;
    }

    private void loadSkinFlipperData() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                this.skinFlipperData = GSON.fromJson(reader, SkinFlipperData.class);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void saveSkinFlipperData() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            writer.write(GSON.toJson(this.skinFlipperData));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

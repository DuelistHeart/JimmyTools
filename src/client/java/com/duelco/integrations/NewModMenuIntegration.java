package com.duelco.integrations;

import com.duelco.config.NewModConfig;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;

public class NewModMenuIntegration implements ModMenuApi {


    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> NewModConfig.build().generateScreen(parentScreen);
    }
}
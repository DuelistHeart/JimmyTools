package com.duelco.managers;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.toast.SystemToast;
import net.minecraft.text.Text;

public class ToastManager {
    public static void displayToast(Text title, Text description) {
        MinecraftClient.getInstance().getToastManager().add(new SystemToast(
                SystemToast.Type.CHUNK_SAVE_FAILURE,
                Text.of(title),
                Text.of(description)
        ));
    }
}

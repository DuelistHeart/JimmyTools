package com.duelco.mixin.client;

import com.duelco.managers.CharacterMapperManager;
import net.minecraft.client.network.ClientPlayNetworkHandler;
import net.minecraft.network.packet.s2c.play.GameMessageS2CPacket;
import net.minecraft.text.HoverEvent;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableTextContent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

@Mixin(ClientPlayNetworkHandler.class)
public abstract class ChatMixin {
//    @Inject(method = "onGameMessage", at = @At("HEAD"), cancellable = true)
//    private void interceptGameMessage(GameMessageS2CPacket packet, CallbackInfo ci) {
//
//        try {
//            String playerName = ((MutableText) (Arrays.stream(((TranslatableTextContent) packet.content().getContent()).getArgs()).findFirst().get())).getSiblings().getFirst().getStyle().getHoverEvent().getValue(HoverEvent.Action.SHOW_TEXT).getSiblings().get(1).getString();
//            String characterName = ((MutableText) (Arrays.stream(((TranslatableTextContent) packet.content().getContent()).getArgs()).findFirst().get())).getSiblings().getFirst().getString();
//            CharacterMapperManager.addMapping(playerName, characterName);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
}
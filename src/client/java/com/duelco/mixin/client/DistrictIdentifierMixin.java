package com.duelco.mixin.client;

import com.duelco.handlers.TabListHandler;
import net.minecraft.network.packet.s2c.play.PlayerListHeaderS2CPacket;
import net.minecraft.text.Text;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerListHeaderS2CPacket.class)
public class DistrictIdentifierMixin {
    @Inject(method = "apply", at = @At("HEAD"))
    private void onApply(CallbackInfo info) {
//        PlayerListHeaderS2CPacket packet = (PlayerListHeaderS2CPacket) (Object) this;
//        Text footer = packet.footer();
//
//        // Here you can handle the header and footer values
//        if (footer.getString().contains("(")) {
//            if (footer.getString().indexOf("(") + 1 > 0 && footer.getString().indexOf(")") > 0) {
//                String district = footer.getString().substring(footer.getString().indexOf("(") + 1, footer.getString().indexOf(")"));
////                System.out.println("District: " + district);
//
//                // Store or manipulate the footer value as needed
//                // For example, store it in a static variable for later use
//                TabListHandler.serverFooter = footer.getString();
//            }
//        } else {
//            if (footer.getString().indexOf("(") + 1 > 0 && footer.getString().indexOf(")") > 0) {
//                String district = footer.getString().split("\n")[0];
//
//                // Store or manipulate the footer value as needed
//                // For example, store it in a static variable for later use
//                TabListHandler.serverFooter = footer.getString();
//            }
//        }
    }
}
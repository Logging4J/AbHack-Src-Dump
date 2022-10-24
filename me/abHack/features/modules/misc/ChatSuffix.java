//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ChatSuffix extends Module
{
    public ChatSuffix() {
        super("ChatSuffix", "suffix", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketChatMessage) {
            final CPacketChatMessage cPacketChatMessage = (CPacketChatMessage)send.getPacket();
            final String getMessage = cPacketChatMessage.getMessage();
            if (!getMessage.startsWith("/")) {
                String message = String.valueOf(new StringBuilder().append(getMessage).append(" | \u1d00\u0299\u029c\u1d00\u1d04\u1d0b"));
                if (message.length() >= 256) {
                    message = message.substring(0, 256);
                }
                cPacketChatMessage.message = message;
            }
        }
    }
}

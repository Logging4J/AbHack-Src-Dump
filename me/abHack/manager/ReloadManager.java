//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import net.minecraftforge.common.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import me.abHack.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ReloadManager extends Feature
{
    public /* synthetic */ String prefix;
    
    public void unload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    public void init(final String s) {
        this.prefix = s;
        MinecraftForge.EVENT_BUS.register((Object)this);
        if (!fullNullCheck()) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("OyVey has been unloaded. Type ").append(s).append("reload to reload.")));
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        final CPacketChatMessage cPacketChatMessage;
        if (send.getPacket() instanceof CPacketChatMessage && (cPacketChatMessage = (CPacketChatMessage)send.getPacket()).getMessage().startsWith(this.prefix) && cPacketChatMessage.getMessage().contains("reload")) {
            OyVey.load();
            send.setCanceled(true);
        }
    }
}

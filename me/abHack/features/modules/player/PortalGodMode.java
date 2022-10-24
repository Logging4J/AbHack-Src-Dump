//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PortalGodMode extends Module
{
    public PortalGodMode() {
        super("PortalGodMode", "PortalGodMode.", Module.Category.PLAYER, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && send.getPacket() instanceof CPacketConfirmTeleport) {
            send.setCanceled(true);
        }
    }
}

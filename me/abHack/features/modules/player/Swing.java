//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;

public class Swing extends Module
{
    private final /* synthetic */ Setting<Hand> hand;
    
    @SubscribeEvent
    public void onPacket(final Packet packet) {
        if (nullCheck() || packet.getType() == Packet.Type.INCOMING) {
            return;
        }
        if (packet.getPacket() instanceof CPacketAnimation) {
            packet.setCanceled(true);
        }
    }
    
    public void onUpdate() {
        if (Swing.mc.world == null) {
            return;
        }
        if (this.hand.getValue() == Hand.OFFHAND) {
            Swing.mc.player.swingingHand = EnumHand.OFF_HAND;
        }
        if (this.hand.getValue() == Hand.MAINHAND) {
            Swing.mc.player.swingingHand = EnumHand.MAIN_HAND;
        }
    }
    
    public Swing() {
        super("Swing", "Changes the hand you swing with", Module.Category.PLAYER, false, false, false);
        this.hand = (Setting<Hand>)this.register(new Setting("Hand", (T)Hand.OFFHAND));
    }
    
    public enum Hand
    {
        OFFHAND, 
        MAINHAND;
    }
}

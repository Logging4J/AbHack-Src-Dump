//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.util.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class NoRotate extends Module
{
    private /* synthetic */ boolean cancelPackets;
    private /* synthetic */ boolean timerReset;
    private final /* synthetic */ Timer timer;
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && this.cancelPackets && receive.getPacket() instanceof SPacketPlayerPosLook) {
            final SPacketPlayerPosLook sPacketPlayerPosLook = (SPacketPlayerPosLook)receive.getPacket();
            sPacketPlayerPosLook.yaw = NoRotate.mc.player.rotationYaw;
            sPacketPlayerPosLook.pitch = NoRotate.mc.player.rotationPitch;
        }
    }
    
    @Override
    public void onLogout() {
        this.cancelPackets = false;
    }
    
    @Override
    public void onUpdate() {
        if (this.timerReset && !this.cancelPackets) {
            this.cancelPackets = true;
            this.timerReset = false;
        }
    }
    
    @Override
    public void onLogin() {
        this.timer.reset();
        this.timerReset = true;
    }
    
    public NoRotate() {
        super("NoRotate", "Dangerous to use might desync you.", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.cancelPackets = true;
        this.timerReset = false;
    }
}

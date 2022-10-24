//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class PacketEvent extends EventStage
{
    public final /* synthetic */ Packet<?> packet;
    
    public PacketEvent(final int n, final Packet<?> packet) {
        super(n);
        this.packet = packet;
    }
    
    public <T extends Packet<?>> T getPacket() {
        return (T)this.packet;
    }
    
    @Cancelable
    public static class Receive extends PacketEvent
    {
        public Receive(final int n, final Packet<?> packet) {
            super(n, packet);
        }
    }
    
    @Cancelable
    public static class Send extends PacketEvent
    {
        public Send(final int n, final Packet<?> packet) {
            super(n, packet);
        }
    }
}

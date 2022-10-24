//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import net.minecraftforge.fml.common.eventhandler.*;

@Cancelable
public class Packet extends Event
{
    private /* synthetic */ Type type;
    private /* synthetic */ Object packet;
    
    public void setType(final Type type) {
        this.type = type;
    }
    
    public void setPacket(final Object packet) {
        this.packet = packet;
    }
    
    public Type getType() {
        return this.type;
    }
    
    public Packet(final Object packet, final Type type) {
        this.packet = packet;
        this.type = type;
    }
    
    public Object getPacket() {
        return this.packet;
    }
    
    public enum Type
    {
        INCOMING, 
        OUTGOING;
    }
}

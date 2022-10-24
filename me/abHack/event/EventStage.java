//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event;

import net.minecraftforge.fml.common.eventhandler.*;

public class EventStage extends Event
{
    private /* synthetic */ int stage;
    
    public void setStage(final int stage) {
        this.stage = stage;
    }
    
    public int getStage() {
        return this.stage;
    }
    
    public EventStage(final int stage) {
        this.stage = stage;
    }
    
    public EventStage() {
    }
}

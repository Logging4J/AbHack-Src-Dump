//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;

public class Render3DEvent extends EventStage
{
    private final /* synthetic */ float partialTicks;
    
    public float getPartialTicks() {
        return this.partialTicks;
    }
    
    public Render3DEvent(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraft.client.gui.*;

public class Render2DEvent extends EventStage
{
    public /* synthetic */ float partialTicks;
    public /* synthetic */ ScaledResolution scaledResolution;
    
    public void setScaledResolution(final ScaledResolution scaledResolution) {
        this.scaledResolution = scaledResolution;
    }
    
    public double getScreenHeight() {
        return this.scaledResolution.getScaledHeight_double();
    }
    
    public double getScreenWidth() {
        return this.scaledResolution.getScaledWidth_double();
    }
    
    public Render2DEvent(final float partialTicks, final ScaledResolution scaledResolution) {
        this.partialTicks = partialTicks;
        this.scaledResolution = scaledResolution;
    }
    
    public void setPartialTicks(final float partialTicks) {
        this.partialTicks = partialTicks;
    }
}

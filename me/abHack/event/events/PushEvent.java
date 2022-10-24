//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;

@Cancelable
public class PushEvent extends EventStage
{
    public /* synthetic */ double y;
    public /* synthetic */ boolean airbone;
    public /* synthetic */ double z;
    public /* synthetic */ Entity entity;
    public /* synthetic */ double x;
    
    public PushEvent(final int n) {
        super(n);
    }
    
    public PushEvent(final int n, final Entity entity) {
        super(n);
        this.entity = entity;
    }
    
    public PushEvent(final Entity entity, final double x, final double y, final double z, final boolean airbone) {
        super(0);
        this.entity = entity;
        this.x = x;
        this.y = y;
        this.z = z;
        this.airbone = airbone;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraft.entity.player.*;

public class TotemPopEvent extends EventStage
{
    private final /* synthetic */ EntityPlayer entity;
    
    public EntityPlayer getEntity() {
        return this.entity;
    }
    
    public TotemPopEvent(final EntityPlayer entity) {
        this.entity = entity;
    }
}

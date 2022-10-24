//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class ConnectionEvent extends EventStage
{
    private final /* synthetic */ EntityPlayer entity;
    private final /* synthetic */ UUID uuid;
    private final /* synthetic */ String name;
    
    public ConnectionEvent(final int n, final UUID uuid, final String name) {
        super(n);
        this.uuid = uuid;
        this.name = name;
        this.entity = null;
    }
    
    public EntityPlayer getEntity() {
        return this.entity;
    }
    
    public ConnectionEvent(final int n, final EntityPlayer entity, final UUID uuid, final String name) {
        super(n);
        this.entity = entity;
        this.uuid = uuid;
        this.name = name;
    }
    
    public UUID getUuid() {
        return this.uuid;
    }
    
    public String getName() {
        return this.name;
    }
}

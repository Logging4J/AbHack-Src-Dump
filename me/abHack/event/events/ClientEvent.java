//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.event.events;

import me.abHack.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.features.*;
import me.abHack.features.setting.*;

@Cancelable
public class ClientEvent extends EventStage
{
    private /* synthetic */ Feature feature;
    private /* synthetic */ Setting setting;
    
    public ClientEvent(final int n, final Feature feature) {
        super(n);
        this.feature = feature;
    }
    
    public Setting getSetting() {
        return this.setting;
    }
    
    public Feature getFeature() {
        return this.feature;
    }
    
    public ClientEvent(final Setting setting) {
        super(2);
        this.setting = setting;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.bus.type;

import me.zero.alpine.fork.bus.*;

public interface AttachableEventBus extends EventBus
{
    void detach(final EventBus p0);
    
    void attach(final EventBus p0);
}

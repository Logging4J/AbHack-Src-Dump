//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.service.mojang;

import org.spongepowered.asm.service.*;
import net.minecraft.launchwrapper.*;

public class Blackboard implements IGlobalPropertyService
{
    public final <T> T getProperty(final String s) {
        return Launch.blackboard.get(s);
    }
    
    public final void setProperty(final String s, final Object o) {
        Launch.blackboard.put(s, o);
    }
    
    public final <T> T getProperty(final String s, final T t) {
        final T value = Launch.blackboard.get(s);
        return (value != null) ? value : t;
    }
    
    public final String getPropertyString(final String s, final String s2) {
        final Object value = Launch.blackboard.get(s);
        return (value != null) ? value.toString() : s2;
    }
}

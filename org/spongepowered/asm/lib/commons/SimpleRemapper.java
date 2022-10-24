//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.commons;

import java.util.*;

public class SimpleRemapper extends Remapper
{
    private final Map<String, String> mapping;
    
    public SimpleRemapper(final Map<String, String> mapping) {
        this.mapping = mapping;
    }
    
    public SimpleRemapper(final String key, final String value) {
        this.mapping = Collections.singletonMap(key, value);
    }
    
    public String mapMethodName(final String str, final String str2, final String str3) {
        final String map = this.map(str + '.' + str2 + str3);
        return (map == null) ? str2 : map;
    }
    
    public String mapInvokeDynamicMethodName(final String str, final String str2) {
        final String map = this.map('.' + str + str2);
        return (map == null) ? str : map;
    }
    
    public String mapFieldName(final String str, final String str2, final String s) {
        final String map = this.map(str + '.' + str2);
        return (map == null) ? str2 : map;
    }
    
    public String map(final String s) {
        return this.mapping.get(s);
    }
}

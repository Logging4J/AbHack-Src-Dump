//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools;

import java.io.*;

public class Pair<V1, V2> implements Serializable
{
    private final /* synthetic */ V2 obj2;
    private final /* synthetic */ V1 obj1;
    
    public V1 getValue1() {
        return this.obj1;
    }
    
    @Override
    public String toString() {
        return String.valueOf(new StringBuilder().append(Pair.class.getName()).append("@").append(Integer.toHexString(this.hashCode())).append(" [").append(this.obj1.toString()).append(", ").append(this.obj2.toString()).append("]"));
    }
    
    public Pair(final V1 obj1, final V2 obj2) {
        this.obj1 = obj1;
        this.obj2 = obj2;
    }
    
    public V2 getValue2() {
        return this.obj2;
    }
}

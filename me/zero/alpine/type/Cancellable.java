//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.type;

public class Cancellable
{
    private /* synthetic */ boolean cancelled;
    
    public final boolean isCancelled() {
        return this.cancelled;
    }
    
    public final void cancel() {
        this.cancelled = true;
    }
}

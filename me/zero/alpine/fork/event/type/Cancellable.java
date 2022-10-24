//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.event.type;

public class Cancellable implements ICancellable
{
    private /* synthetic */ boolean cancelled;
    
    @Override
    public void cancel() {
        this.cancelled = true;
    }
    
    @Override
    public boolean isCancelled() {
        return this.cancelled;
    }
}

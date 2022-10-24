//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.listener;

import java.util.function.*;

public class MethodRefListener<T> extends Listener<T>
{
    private /* synthetic */ Class<T> target;
    
    @SafeVarargs
    public MethodRefListener(final Class<T> target, final EventHook<T> eventHook, final Predicate<T>... array) {
        super((EventHook)eventHook, (Predicate[])array);
        this.target = target;
    }
    
    public Class<T> getTarget() {
        return this.target;
    }
    
    @SafeVarargs
    public MethodRefListener(final Class<T> target, final EventHook<T> eventHook, final int n, final Predicate<T>... array) {
        super((EventHook)eventHook, n, (Predicate[])array);
        this.target = target;
    }
}

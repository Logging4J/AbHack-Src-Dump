//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.listener;

import java.util.function.*;
import net.jodah.typetools.*;

public class Listener<T> implements EventHook<T>
{
    private final /* synthetic */ Predicate<T>[] filters;
    private final /* synthetic */ EventHook<T> hook;
    private final /* synthetic */ int priority;
    private final /* synthetic */ Class<T> target;
    
    public void invoke(final T t) {
        if (this.filters.length > 0) {
            final Predicate<T>[] filters = this.filters;
            for (int length = filters.length, i = 0; i < length; ++i) {
                if (!filters[i].test(t)) {
                    return;
                }
            }
        }
        this.hook.invoke((Object)t);
    }
    
    public int getPriority() {
        return this.priority;
    }
    
    @SafeVarargs
    public Listener(final EventHook<T> eventHook, final Predicate<T>... array) {
        this(eventHook, 0, (Predicate[])array);
    }
    
    @SafeVarargs
    public Listener(final EventHook<T> hook, final int priority, final Predicate<T>... filters) {
        this.hook = hook;
        this.priority = priority;
        this.target = (Class<T>)TypeResolver.resolveRawArgument((Class)EventHook.class, (Class)hook.getClass());
        this.filters = filters;
    }
    
    public Class<T> getTarget() {
        return this.target;
    }
}

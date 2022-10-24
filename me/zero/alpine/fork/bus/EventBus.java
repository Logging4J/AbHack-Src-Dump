//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.bus;

import java.util.*;
import java.util.function.*;
import me.zero.alpine.fork.listener.*;

public interface EventBus
{
    default void subscribeAll(final Listenable... array) {
        Arrays.stream(array).forEach(this::subscribe);
    }
    
    void subscribe(final Listener p0);
    
    default void unsubscribeAll(final Listenable... array) {
        Arrays.stream(array).forEach(this::unsubscribe);
    }
    
    void unsubscribe(final Listener p0);
    
    default void subscribeAll(final Listener... array) {
        Arrays.stream(array).forEach(this::subscribe);
    }
    
    void subscribe(final Listenable p0);
    
    void unsubscribe(final Listenable p0);
    
    default void subscribeAll(final Iterable<Listenable> iterable) {
        iterable.forEach(this::subscribe);
    }
    
    default void unsubscribeAll(final Listener... array) {
        Arrays.stream(array).forEach(this::unsubscribe);
    }
    
    default void unsubscribeAll(final Iterable<Listenable> iterable) {
        iterable.forEach(this::unsubscribe);
    }
    
    void post(final Object p0);
}

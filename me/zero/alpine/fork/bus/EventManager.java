//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.bus;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.*;
import java.util.concurrent.*;
import me.zero.alpine.fork.listener.*;
import java.lang.annotation.*;
import java.util.function.*;

public class EventManager implements EventBus
{
    private final /* synthetic */ Map<Class<?>, List<Listener>> SUBSCRIPTION_MAP;
    private final /* synthetic */ Map<Listenable, List<Listener>> SUBSCRIPTION_CACHE;
    
    public void post(final Object o) {
        final List<Listener> list = this.SUBSCRIPTION_MAP.get(o.getClass());
        if (list != null) {
            list.forEach(listener -> listener.invoke(o));
        }
    }
    
    public void subscribe(final Listener listener) {
        List<Listener> list;
        int n;
        for (list = this.SUBSCRIPTION_MAP.computeIfAbsent(listener.getTarget(), p0 -> new CopyOnWriteArrayList()), n = 0; n < list.size() && listener.getPriority() <= list.get(n).getPriority(); ++n) {}
        list.add(n, listener);
    }
    
    private static Listener asListener(final Listenable obj, final Field field) {
        try {
            final boolean accessible = field.isAccessible();
            field.setAccessible(true);
            final Listener listener = (Listener)field.get(obj);
            field.setAccessible(accessible);
            if (listener == null) {
                return null;
            }
            return listener;
        }
        catch (IllegalAccessException ex) {
            return null;
        }
    }
    
    public EventManager() {
        this.SUBSCRIPTION_CACHE = new ConcurrentHashMap<Listenable, List<Listener>>();
        this.SUBSCRIPTION_MAP = new ConcurrentHashMap<Class<?>, List<Listener>>();
    }
    
    private static boolean isValidField(final Field field) {
        return field.isAnnotationPresent(EventHandler.class) && Listener.class.isAssignableFrom(field.getType());
    }
    
    public void unsubscribe(final Listenable listenable) {
        if (this.SUBSCRIPTION_CACHE.get(listenable) == null) {
            return;
        }
        final List list2;
        this.SUBSCRIPTION_MAP.values().forEach(list -> list.removeIf(list2::contains));
    }
    
    public void subscribe(final Listenable key) {
        this.SUBSCRIPTION_CACHE.computeIfAbsent(key, listenable -> Arrays.stream(listenable.getClass().getDeclaredFields()).filter(EventManager::isValidField).map(field -> asListener(listenable, field)).filter(Objects::nonNull).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList())).forEach(this::subscribe);
    }
    
    public void unsubscribe(final Listener obj) {
        this.SUBSCRIPTION_MAP.get(obj.getTarget()).removeIf(listener -> listener.equals(obj));
    }
}

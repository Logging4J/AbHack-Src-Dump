//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.zero.alpine.fork.bus.type;

import me.zero.alpine.fork.bus.*;
import me.zero.alpine.fork.listener.*;
import java.util.*;

public class AttachableEventManager extends EventManager implements AttachableEventBus
{
    private final /* synthetic */ List<EventBus> attached;
    
    public void unsubscribe(final Listenable listenable) {
        super.unsubscribe(listenable);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.unsubscribe(listenable));
        }
    }
    
    public void subscribe(final Listenable listenable) {
        super.subscribe(listenable);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.subscribe(listenable));
        }
    }
    
    public void subscribe(final Listener listener) {
        super.subscribe(listener);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.subscribe(listener));
        }
    }
    
    public void detach(final EventBus eventBus) {
        this.attached.remove(eventBus);
    }
    
    public void attach(final EventBus eventBus) {
        if (!this.attached.contains(eventBus)) {
            this.attached.add(eventBus);
        }
    }
    
    public AttachableEventManager() {
        this.attached = new ArrayList<EventBus>();
    }
    
    public void post(final Object o) {
        super.post(o);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.post(o));
        }
    }
    
    public void unsubscribe(final Listener listener) {
        super.unsubscribe(listener);
        if (!this.attached.isEmpty()) {
            this.attached.forEach(eventBus -> eventBus.unsubscribe(listener));
        }
    }
}

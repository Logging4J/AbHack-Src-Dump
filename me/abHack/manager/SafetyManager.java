//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import java.util.concurrent.atomic.*;
import java.util.concurrent.*;
import me.abHack.features.modules.combat.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import me.abHack.util.*;
import java.util.*;

public class SafetyManager extends Feature implements Runnable
{
    private final /* synthetic */ AtomicBoolean SAFE;
    private final /* synthetic */ Timer syncTimer;
    
    public ScheduledExecutorService getService() {
        return Executors.newSingleThreadScheduledExecutor();
    }
    
    public void run() {
        if (AutoCrystal.getInstance().isOff() || AutoCrystal.getInstance().threadMode.getValue() == AutoCrystal.ThreadMode.NONE) {
            this.doSafetyCheck();
        }
    }
    
    public void doSafetyCheck() {
        if (!fullNullCheck()) {
            boolean newValue = true;
            for (final Entity entity : new ArrayList<Entity>(SafetyManager.mc.world.loadedEntityList)) {
                if (entity instanceof EntityEnderCrystal) {
                    if (DamageUtil.calculateDamage(entity, (Entity)SafetyManager.mc.player) <= 4.0) {
                        continue;
                    }
                    newValue = false;
                    break;
                }
            }
            this.SAFE.set(newValue);
        }
    }
    
    public boolean isSafe() {
        return this.SAFE.get();
    }
    
    public void onUpdate() {
        this.run();
    }
    
    public String getSafetyString() {
        if (this.SAFE.get()) {
            return "§aSecure";
        }
        return "§cUnsafe";
    }
    
    public SafetyManager() {
        this.syncTimer = new Timer();
        this.SAFE = new AtomicBoolean(false);
    }
}

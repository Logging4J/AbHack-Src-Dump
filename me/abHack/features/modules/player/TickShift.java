//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class TickShift extends Module
{
    public /* synthetic */ boolean pause;
    public /* synthetic */ int ticksPassed;
    public final /* synthetic */ Setting<Mode> disableMode;
    public final /* synthetic */ Setting<Float> multiplier;
    public /* synthetic */ int unPauseTicks;
    public final /* synthetic */ Setting<Integer> disableTicks;
    public final /* synthetic */ Setting<Integer> noPauseTicks;
    
    public void onUpdate() {
        ++this.ticksPassed;
        if (!this.pause) {
            TickShift.mc.timer.tickLength = 50.0f / this.getMultiplier();
        }
        if (this.pause) {
            ++this.unPauseTicks;
            TickShift.mc.timer.tickLength = 50.0f;
        }
        if (this.disableMode.getValue() != Mode.Pause) {
            this.pause = false;
        }
        if (this.ticksPassed >= this.disableTicks.getValue()) {
            this.ticksPassed = 0;
            if (this.disableMode.getValue() == Mode.Disable) {
                this.disable();
            }
            if (this.disableMode.getValue() == Mode.Pause) {
                if (this.unPauseTicks <= this.noPauseTicks.getValue()) {
                    this.pause = true;
                }
                else if (this.unPauseTicks >= this.noPauseTicks.getValue()) {
                    this.pause = false;
                    this.unPauseTicks = 0;
                }
            }
        }
    }
    
    public float getMultiplier() {
        if (this.isEnabled()) {
            return this.multiplier.getValue();
        }
        return 1.0f;
    }
    
    public TickShift() {
        super("TickShift", "Changes tick speed at certain intervals to bypass anticheat and go faster", Module.Category.PLAYER, true, false, false);
        this.multiplier = (Setting<Float>)this.register(new Setting("Multiplier", (T)1.8f, (T)0.1f, (T)20.0f));
        this.disableTicks = (Setting<Integer>)this.register(new Setting("Disable Ticks", (T)30, (T)1, (T)100));
        this.noPauseTicks = (Setting<Integer>)this.register(new Setting("UnPause Ticks", (T)30, (T)1, (T)100));
        this.disableMode = (Setting<Mode>)this.register(new Setting("Disable Mode", (T)Mode.Pause));
        this.ticksPassed = 0;
        this.unPauseTicks = 0;
        this.pause = false;
    }
    
    public void onDisable() {
        TickShift.mc.timer.tickLength = 50.0f;
    }
    
    public enum Mode
    {
        Pause, 
        Disable;
    }
}

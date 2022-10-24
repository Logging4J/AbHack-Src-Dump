//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.util.*;

public class Timer extends Module
{
    public /* synthetic */ Setting<Float> timer;
    
    @Override
    public void onDisable() {
        EntityUtil.resetTimer();
    }
    
    @Override
    public String getDisplayInfo() {
        return String.valueOf(new StringBuilder().append(this.timer.getValue()).append(""));
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        EntityUtil.setTimer(this.timer.getValue());
    }
    
    public Timer() {
        super("Timer", "Timer", Category.MISC, true, false, false);
        this.timer = (Setting<Float>)this.register(new Setting("Timer", (T)1.1f, (T)1.0f, (T)2.0f));
    }
}

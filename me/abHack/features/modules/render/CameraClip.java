//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class CameraClip extends Module
{
    public /* synthetic */ Setting<Boolean> extend;
    private static /* synthetic */ CameraClip INSTANCE;
    public /* synthetic */ Setting<Double> distance;
    
    static {
        CameraClip.INSTANCE = new CameraClip();
    }
    
    public CameraClip() {
        super("CameraClip", "Change F5 Perspective.", Module.Category.RENDER, false, false, false);
        this.extend = (Setting<Boolean>)this.register(new Setting("Extend", (T)true));
        this.distance = (Setting<Double>)this.register(new Setting("Distance", (T)10.0, (T)0.0, (T)50.0, p0 -> this.extend.getValue(), "By how much you want to extend the distance."));
        this.setInstance();
    }
    
    public static CameraClip getInstance() {
        if (CameraClip.INSTANCE == null) {
            CameraClip.INSTANCE = new CameraClip();
        }
        return CameraClip.INSTANCE;
    }
    
    private void setInstance() {
        CameraClip.INSTANCE = this;
    }
}

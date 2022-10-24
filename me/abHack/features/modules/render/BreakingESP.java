//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import java.awt.*;
import me.abHack.util.*;

public class BreakingESP extends Module
{
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> box;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> green;
    public final /* synthetic */ Setting<Float> lineWidth;
    public final /* synthetic */ Setting<Integer> boxAlpha;
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (BreakingESP.mc.playerController.currentBlock != null) {
            RenderUtil.boxESP(BreakingESP.mc.playerController.currentBlock, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.boxAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }
    
    public BreakingESP() {
        super("BreakingESP", "Rendering broken blocks", Module.Category.RENDER, true, false, false);
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)125, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)0, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.outline.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)85, (T)0, (T)255, p0 -> this.box.getValue()));
    }
}

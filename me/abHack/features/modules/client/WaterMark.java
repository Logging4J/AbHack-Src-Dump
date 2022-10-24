//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.client;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import me.abHack.util.*;

public class WaterMark extends Module
{
    public /* synthetic */ Setting<Integer> compactY;
    public /* synthetic */ Setting<Integer> blue;
    private /* synthetic */ int color1;
    private final /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> green;
    private /* synthetic */ int color2;
    public /* synthetic */ Setting<Integer> compactX;
    public /* synthetic */ Setting<Integer> red;
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (this.rainbow.getValue()) {
            this.color1 = ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB();
            this.color2 = ColorUtil.rainbow(100).getRGB();
            RenderUtil.drawRectangleCorrectly(this.compactX.getValue(), this.compactY.getValue(), 200, 15, ColorUtil.toRGBA(20, 20, 20, 200));
            RenderUtil.drawGradientSideways(this.compactX.getValue(), 0.0 + this.compactY.getValue(), 200 + this.compactX.getValue(), 1.5 + this.compactY.getValue(), this.color1, this.color2);
            this.renderer.drawString(String.valueOf(new StringBuilder().append("abHack v0.0.1 | ").append(HudUtil.getPingSatus()).append("ms | ").append(HudUtil.getFpsStatus()).append("fps | ").append(HudUtil.getTpsStatus()).append("tps")), this.compactX.getValue(), (float)(this.compactY.getValue() + 3), this.color1, true);
        }
        else {
            this.color1 = ColorUtil.toRGBA(this.red.getValue(), this.green.getValue(), this.blue.getValue(), 255);
            RenderUtil.drawRectangleCorrectly(this.compactX.getValue(), this.compactY.getValue(), 200, 15, ColorUtil.toRGBA(20, 20, 20, 200));
            RenderUtil.drawGradientSideways(this.compactX.getValue(), 0.0 + this.compactY.getValue(), 200 + this.compactX.getValue(), 1.5 + this.compactY.getValue(), this.color1, this.color1);
            this.renderer.drawString(String.valueOf(new StringBuilder().append("abHack v0.0.1 | ").append(HudUtil.getPingSatus()).append("ms | ").append(HudUtil.getFpsStatus()).append("fps | ").append(HudUtil.getTpsStatus()).append("tps")), this.compactX.getValue(), (float)(this.compactY.getValue() + 3), this.color1, true);
        }
    }
    
    public WaterMark() {
        super("WaterMark", "watermark", Category.CLIENT, true, false, false);
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
        this.compactX = (Setting<Integer>)this.register(new Setting("WaterMarkX", (T)0, (T)0, (T)1080));
        this.compactY = (Setting<Integer>)this.register(new Setting("WaterMarkY", (T)0, (T)0, (T)530));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)20, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)20, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)20, (T)0, (T)255));
    }
}

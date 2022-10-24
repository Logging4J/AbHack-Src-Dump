//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.common.*;
import java.awt.*;

public class SkyColor extends Module
{
    private static /* synthetic */ SkyColor INSTANCE;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Boolean> rainbow;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> red;
    
    public void onUpdate() {
        if (this.rainbow.getValue()) {
            this.doRainbow();
        }
    }
    
    @SubscribeEvent
    public void fog_density(final EntityViewRenderEvent.FogDensity fogDensity) {
        fogDensity.setDensity(0.0f);
        fogDensity.setCanceled(true);
    }
    
    @SubscribeEvent
    public void fogColors(final EntityViewRenderEvent.FogColors fogColors) {
        fogColors.setRed(this.red.getValue() / 255.0f);
        fogColors.setGreen(this.green.getValue() / 255.0f);
        fogColors.setBlue(this.blue.getValue() / 255.0f);
    }
    
    public void onEnable() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    public SkyColor() {
        super("SkyColor", "Sky Render.", Module.Category.RENDER, false, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
    }
    
    private void setInstance() {
        SkyColor.INSTANCE = this;
    }
    
    public static SkyColor getInstance() {
        if (SkyColor.INSTANCE == null) {
            SkyColor.INSTANCE = new SkyColor();
        }
        return SkyColor.INSTANCE;
    }
    
    public void onDisable() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    static {
        SkyColor.INSTANCE = new SkyColor();
    }
    
    public void doRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.red.setValue(hsBtoRGB >> 16 & 0xFF);
        this.green.setValue(hsBtoRGB >> 8 & 0xFF);
        this.blue.setValue(hsBtoRGB & 0xFF);
    }
}

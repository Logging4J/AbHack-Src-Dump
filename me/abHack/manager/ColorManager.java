//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import java.awt.*;
import me.abHack.util.*;
import me.abHack.features.modules.client.*;
import me.abHack.features.gui.components.*;

public class ColorManager
{
    private /* synthetic */ float green;
    private /* synthetic */ float red;
    private /* synthetic */ float blue;
    private /* synthetic */ float alpha;
    private /* synthetic */ Color color;
    
    public void setColor(final Color color) {
        this.color = color;
    }
    
    public void setColor(final float red, final float green, final float blue, final float alpha) {
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.alpha = alpha;
        this.updateColor();
    }
    
    public ColorManager() {
        this.red = 1.0f;
        this.green = 1.0f;
        this.blue = 1.0f;
        this.alpha = 1.0f;
        this.color = new Color(this.red, this.green, this.blue, this.alpha);
    }
    
    public Color getColor() {
        return this.color;
    }
    
    public void setColor(final int n, final int n2, final int n3, final int n4) {
        this.red = n / 255.0f;
        this.green = n2 / 255.0f;
        this.blue = n3 / 255.0f;
        this.alpha = n4 / 255.0f;
        this.updateColor();
    }
    
    public int getColorAsIntFullAlpha() {
        return ColorUtil.toRGBA(new Color(this.color.getRed(), this.color.getGreen(), this.color.getBlue(), 255));
    }
    
    public int getColorWithAlpha(final int n) {
        if (ClickGui.getInstance().rainbow.getValue()) {
            return ColorUtil.rainbow(Component.counter1[0] * (int)ClickGui.getInstance().rainbowHue.getValue()).getRGB();
        }
        return ColorUtil.toRGBA(new Color(this.red, this.green, this.blue, n / 255.0f));
    }
    
    public void setBlue(final float blue) {
        this.blue = blue;
        this.updateColor();
    }
    
    public void setRed(final float red) {
        this.red = red;
        this.updateColor();
    }
    
    public void setAlpha(final float alpha) {
        this.alpha = alpha;
        this.updateColor();
    }
    
    public int getColorAsInt() {
        return ColorUtil.toRGBA(this.color);
    }
    
    public void setGreen(final float green) {
        this.green = green;
        this.updateColor();
    }
    
    public void updateColor() {
        this.setColor(new Color(this.red, this.green, this.blue, this.alpha));
    }
}

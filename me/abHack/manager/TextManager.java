//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.util.*;
import me.abHack.features.gui.font.*;
import java.awt.*;
import me.abHack.*;
import me.abHack.features.modules.client.*;
import net.minecraft.util.math.*;

public class TextManager extends Feature
{
    private final /* synthetic */ Timer idleTimer;
    public /* synthetic */ int scaledWidth;
    private /* synthetic */ boolean idling;
    private /* synthetic */ CustomFont customFont;
    public /* synthetic */ int scaledHeight;
    public /* synthetic */ int scaleFactor;
    
    public TextManager() {
        this.idleTimer = new Timer();
        this.customFont = new CustomFont(new Font("Verdana", 0, 17), true, false);
        this.updateResolution();
    }
    
    public void setFontRenderer(final Font font, final boolean b, final boolean b2) {
        this.customFont = new CustomFont(font, b, b2);
    }
    
    public String getIdleSign() {
        if (this.idleTimer.passedMs(500L)) {
            this.idling = !this.idling;
            this.idleTimer.reset();
        }
        if (this.idling) {
            return "_";
        }
        return "";
    }
    
    public int getStringWidth(final String s) {
        if (OyVey.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            return this.customFont.getStringWidth(s);
        }
        return TextManager.mc.fontRenderer.getStringWidth(s);
    }
    
    public Font getCurrentFont() {
        return this.customFont.getFont();
    }
    
    public int getFontHeight() {
        if (OyVey.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            return this.customFont.getStringHeight("A");
        }
        return TextManager.mc.fontRenderer.FONT_HEIGHT;
    }
    
    public void drawString(final String s, final float n, final float n2, final int n3, final boolean b) {
        if (OyVey.moduleManager.isModuleEnabled(FontMod.getInstance().getName())) {
            if (b) {
                this.customFont.drawStringWithShadow(s, (double)n, (double)n2, n3);
            }
            else {
                this.customFont.drawString(s, n, n2, n3);
            }
            return;
        }
        TextManager.mc.fontRenderer.drawString(s, n, n2, n3, b);
    }
    
    public void init(final boolean b) {
        final FontMod fontMod = (FontMod)OyVey.moduleManager.getModuleByClass((Class)FontMod.class);
        try {
            this.setFontRenderer(new Font((String)fontMod.fontName.getValue(), (int)fontMod.fontStyle.getValue(), (int)fontMod.fontSize.getValue()), (boolean)fontMod.antiAlias.getValue(), (boolean)fontMod.fractionalMetrics.getValue());
        }
        catch (Exception ex) {}
    }
    
    public void drawStringWithShadow(final String s, final float n, final float n2, final int n3) {
        this.drawString(s, n, n2, n3, true);
    }
    
    public void updateResolution() {
        this.scaledWidth = TextManager.mc.displayWidth;
        this.scaledHeight = TextManager.mc.displayHeight;
        this.scaleFactor = 1;
        final boolean isUnicode = TextManager.mc.isUnicode();
        int guiScale = TextManager.mc.gameSettings.guiScale;
        if (guiScale == 0) {
            guiScale = 1000;
        }
        while (this.scaleFactor < guiScale && this.scaledWidth / (this.scaleFactor + 1) >= 320 && this.scaledHeight / (this.scaleFactor + 1) >= 240) {
            ++this.scaleFactor;
        }
        if (isUnicode && this.scaleFactor % 2 != 0 && this.scaleFactor != 1) {
            --this.scaleFactor;
        }
        final double n = this.scaledWidth / this.scaleFactor;
        final double n2 = this.scaledHeight / this.scaleFactor;
        this.scaledWidth = MathHelper.ceil(n);
        this.scaledHeight = MathHelper.ceil(n2);
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items;

import me.abHack.*;
import me.abHack.util.*;

public class DescriptionDisplay extends Item
{
    private /* synthetic */ boolean draw;
    private /* synthetic */ String description;
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        this.width = OyVey.textManager.getStringWidth(this.description) + 4;
        this.height = OyVey.textManager.getFontHeight() + 4;
        RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height, -704643072);
        OyVey.textManager.drawString(this.description, this.x + 2.0f, this.y + 2.0f, 16777215, true);
    }
    
    public DescriptionDisplay(final String description, final float n, final float n2) {
        super("DescriptionDisplay");
        this.description = description;
        this.setLocation(n, n2);
        this.width = OyVey.textManager.getStringWidth(this.description) + 4;
        this.height = OyVey.textManager.getFontHeight() + 4;
        this.draw = false;
    }
    
    public void setDescription(final String description) {
        this.description = description;
    }
    
    public boolean shouldDraw() {
        return this.draw;
    }
    
    public void setDraw(final boolean draw) {
        this.draw = draw;
    }
    
    public String getDescription() {
        return this.description;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items;

import me.abHack.features.*;

public class Item extends Feature
{
    protected /* synthetic */ int width;
    private /* synthetic */ boolean hidden;
    protected /* synthetic */ int height;
    protected /* synthetic */ float y;
    protected /* synthetic */ float x;
    
    public void setHeight(final int height) {
        this.height = height;
    }
    
    public void mouseReleased(final int n, final int n2, final int n3) {
    }
    
    public int getHeight() {
        return this.height;
    }
    
    public void onKeyTyped(final char c, final int n) {
    }
    
    public void setLocation(final float x, final float y) {
        this.x = x;
        this.y = y;
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
    }
    
    public int getWidth() {
        return this.width;
    }
    
    public boolean isHidden() {
        return this.hidden;
    }
    
    public void update() {
    }
    
    public Item(final String s) {
        super(s);
    }
    
    public boolean setHidden(final boolean hidden) {
        this.hidden = hidden;
        return this.hidden;
    }
    
    public void setWidth(final int width) {
        this.width = width;
    }
    
    public float getY() {
        return this.y;
    }
    
    public float getX() {
        return this.x;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class ItemPhysics extends Module
{
    public final /* synthetic */ Setting<Float> Scaling;
    public static /* synthetic */ ItemPhysics INSTANCE;
    
    public static ItemPhysics getInstance() {
        if (ItemPhysics.INSTANCE == null) {
            ItemPhysics.INSTANCE = new ItemPhysics();
        }
        return ItemPhysics.INSTANCE;
    }
    
    public ItemPhysics() {
        super("ItemPhysics", "Apply physics to items.", Module.Category.RENDER, false, false, false);
        this.Scaling = (Setting<Float>)this.register(new Setting("Scaling", (T)0.5f, (T)0.0f, (T)10.0f));
        this.setInstance();
    }
    
    static {
        ItemPhysics.INSTANCE = new ItemPhysics();
    }
    
    private void setInstance() {
        ItemPhysics.INSTANCE = this;
    }
}

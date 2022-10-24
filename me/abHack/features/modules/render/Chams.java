//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;

public class Chams extends Module
{
    private static /* synthetic */ Chams INSTANCE;
    
    static {
        Chams.INSTANCE = new Chams();
    }
    
    private void setInstance() {
        Chams.INSTANCE = this;
    }
    
    public static Chams getInstance() {
        if (Chams.INSTANCE == null) {
            Chams.INSTANCE = new Chams();
        }
        return Chams.INSTANCE;
    }
    
    public Chams() {
        super("Chams", "Player behind rendered wall.", Module.Category.RENDER, false, false, false);
        this.setInstance();
    }
}

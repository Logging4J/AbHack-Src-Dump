//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;

public class MultiTask extends Module
{
    private static /* synthetic */ MultiTask INSTANCE;
    
    private void setInstance() {
        MultiTask.INSTANCE = this;
    }
    
    public MultiTask() {
        super("MultiTask", "Allows you to eat while mining.", Module.Category.PLAYER, false, false, false);
        this.setInstance();
    }
    
    public static MultiTask getInstance() {
        if (MultiTask.INSTANCE == null) {
            MultiTask.INSTANCE = new MultiTask();
        }
        return MultiTask.INSTANCE;
    }
    
    static {
        MultiTask.INSTANCE = new MultiTask();
    }
}

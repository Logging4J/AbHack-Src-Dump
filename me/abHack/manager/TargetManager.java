//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import net.minecraft.entity.*;

public class TargetManager
{
    private /* synthetic */ EntityLivingBase currentTarget;
    
    public void updateTarget(final EntityLivingBase currentTarget) {
        this.currentTarget = currentTarget;
    }
    
    public EntityLivingBase getTarget() {
        return this.currentTarget;
    }
    
    public TargetManager() {
        this.currentTarget = null;
    }
}

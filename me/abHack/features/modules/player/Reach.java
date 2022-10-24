//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;

public class Reach extends Module
{
    private final /* synthetic */ Setting<Integer> Reach;
    
    public void onUpdate() {
        me.abHack.features.modules.player.Reach.mc.player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).setBaseValue((double)this.Reach.getValue());
    }
    
    public Reach() {
        super("Reach", "reach", Module.Category.PLAYER, true, false, false);
        this.Reach = (Setting<Integer>)this.register(new Setting("Reach", (T)6, (T)5, (T)10));
    }
    
    public void onDisable() {
        me.abHack.features.modules.player.Reach.mc.player.getEntityAttribute(EntityPlayer.REACH_DISTANCE).setBaseValue(5.0);
    }
}

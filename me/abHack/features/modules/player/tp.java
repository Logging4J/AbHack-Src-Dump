//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class tp extends Module
{
    private final /* synthetic */ Setting<String> xcoord;
    private final /* synthetic */ Setting<Integer> tickblock;
    private final /* synthetic */ Setting<String> zcoord;
    
    public tp() {
        super("tp", "tp coord", Module.Category.PLAYER, true, false, false);
        this.xcoord = (Setting<String>)this.register(new Setting("X", (T)"100"));
        this.zcoord = (Setting<String>)this.register(new Setting("Z", (T)"100"));
        this.tickblock = (Setting<Integer>)this.register(new Setting("Tick", (T)10, (T)1, (T)10));
    }
    
    public void onTick() {
        if (tp.mc.player.posY < 180.0) {
            tp.mc.player.setPosition(tp.mc.player.posX, tp.mc.player.posY + 5.0, tp.mc.player.posZ);
        }
        if (tp.mc.player.posX <= Integer.parseInt(this.xcoord.getValue()) && Integer.parseInt(this.xcoord.getValue()) >= 0) {
            tp.mc.player.setPosition(tp.mc.player.posX + this.tickblock.getValue(), tp.mc.player.posY, tp.mc.player.posZ);
        }
        else if (tp.mc.player.posX >= Integer.parseInt(this.xcoord.getValue()) && (Integer.parseInt(this.xcoord.getValue()) <= 0 || Integer.parseInt(this.xcoord.getValue()) >= 0)) {
            tp.mc.player.setPosition(tp.mc.player.posX - this.tickblock.getValue(), tp.mc.player.posY, tp.mc.player.posZ);
        }
        else if (tp.mc.player.posX <= Integer.parseInt(this.xcoord.getValue()) && Integer.parseInt(this.xcoord.getValue()) <= 0) {
            tp.mc.player.setPosition(tp.mc.player.posX + this.tickblock.getValue(), tp.mc.player.posY, tp.mc.player.posZ);
        }
        if (tp.mc.player.posX + 10.0 >= Integer.parseInt(this.xcoord.getValue()) && tp.mc.player.posX - 10.0 <= Integer.parseInt(this.xcoord.getValue())) {
            if (tp.mc.player.posZ <= Integer.parseInt(this.zcoord.getValue()) && Integer.parseInt(this.zcoord.getValue()) >= 0) {
                tp.mc.player.setPosition(tp.mc.player.posX, tp.mc.player.posY, tp.mc.player.posZ + this.tickblock.getValue());
            }
            else if (tp.mc.player.posZ >= Integer.parseInt(this.zcoord.getValue()) && (Integer.parseInt(this.zcoord.getValue()) <= 0 || Integer.parseInt(this.zcoord.getValue()) >= 0)) {
                tp.mc.player.setPosition(tp.mc.player.posX, tp.mc.player.posY, tp.mc.player.posZ - this.tickblock.getValue());
            }
            else if (tp.mc.player.posZ <= Integer.parseInt(this.zcoord.getValue()) && Integer.parseInt(this.zcoord.getValue()) <= 0) {
                tp.mc.player.setPosition(tp.mc.player.posX, tp.mc.player.posY, tp.mc.player.posZ + this.tickblock.getValue());
            }
        }
        tp.mc.player.setPosition(tp.mc.player.posX, tp.mc.player.posY - 1.0, tp.mc.player.posZ);
    }
}

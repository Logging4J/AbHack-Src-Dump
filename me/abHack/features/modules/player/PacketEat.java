//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;

public class PacketEat extends Module
{
    private static /* synthetic */ PacketEat INSTANCE;
    
    public PacketEat() {
        super("PacketEat", "PacketEat", Module.Category.PLAYER, true, false, false);
        this.setInstance();
    }
    
    private void setInstance() {
        PacketEat.INSTANCE = this;
    }
    
    static {
        PacketEat.INSTANCE = new PacketEat();
    }
    
    public static PacketEat getInstance() {
        if (PacketEat.INSTANCE == null) {
            PacketEat.INSTANCE = new PacketEat();
        }
        return PacketEat.INSTANCE;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import net.minecraft.world.*;

public class Gamemode extends Module
{
    @Override
    public void onTick() {
        if (Gamemode.mc.player == null) {
            return;
        }
        Gamemode.mc.playerController.setGameType(GameType.CREATIVE);
    }
    
    @Override
    public void onDisable() {
        if (Gamemode.mc.player == null) {
            return;
        }
        Gamemode.mc.playerController.setGameType(GameType.SURVIVAL);
    }
    
    public Gamemode() {
        super("Gamemode", "fake gamemode", Category.MISC, true, false, false);
    }
}

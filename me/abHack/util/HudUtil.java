//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import me.abHack.*;
import net.minecraft.client.*;

public class HudUtil implements Util
{
    public static String getTpsStatus() {
        return String.valueOf(new StringBuilder().append("").append(" ").append(Math.ceil(OyVey.serverManager.getTPS())));
    }
    
    public static String getFpsStatus() {
        return String.valueOf(new StringBuilder().append("").append(" ").append(Minecraft.getDebugFPS()));
    }
    
    public static String getPingSatus() {
        return String.valueOf(new StringBuilder().append("").append(" ").append(OyVey.serverManager.getPing()));
    }
}

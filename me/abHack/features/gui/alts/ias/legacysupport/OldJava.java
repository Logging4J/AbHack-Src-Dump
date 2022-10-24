//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.legacysupport;

import net.minecraft.client.resources.*;

public class OldJava implements ILegacyCompat
{
    public String getFormattedDate() {
        return I18n.format("ias.updatejava", (Object[])new Object[0]);
    }
    
    public int[] getDate() {
        return new int[3];
    }
}

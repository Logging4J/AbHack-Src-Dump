//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.tools;

import me.abHack.features.gui.alts.ias.legacysupport.*;

public class JavaTools
{
    private static double getJavaVersion() {
        final String property = System.getProperty("java.version");
        return Double.parseDouble(property.substring(0, property.indexOf(46, property.indexOf(46) + 1)));
    }
    
    public static ILegacyCompat getJavaCompat() {
        if (getJavaVersion() >= 1.8) {
            return (ILegacyCompat)new NewJava();
        }
        return (ILegacyCompat)new OldJava();
    }
}

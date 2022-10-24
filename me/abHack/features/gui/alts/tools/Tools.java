//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools;

import net.minecraft.client.gui.*;

public class Tools
{
    public static void drawBorderedRect(final int n, final int n2, final int n3, final int n4, final int n5, final int n6, final int n7) {
        Gui.drawRect(n + n5, n2 + n5, n3 - n5, n4 - n5, n7);
        Gui.drawRect(n + n5, n2 + n5, n3, n2, n6);
        Gui.drawRect(n, n2, n + n5, n4, n6);
        Gui.drawRect(n3, n4, n3 - n5, n2 + n5, n6);
        Gui.drawRect(n, n4 - n5, n3, n4, n6);
    }
}

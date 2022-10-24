//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.tools;

import java.net.*;
import java.io.*;

public class HttpTools
{
    public static boolean ping(final String spec) {
        try {
            new URL(spec).openConnection().connect();
            return true;
        }
        catch (IOException ex) {
            return false;
        }
    }
}

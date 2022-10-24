//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.util.*;
import java.net.*;
import java.io.*;

public class VerificationUtil
{
    public static List<String> readURL() {
        final ArrayList<String> list = new ArrayList<String>();
        try {
            String line;
            while ((line = new BufferedReader(new InputStreamReader(new URL("https://pastebin.com/raw/2Mk5bQdd").openStream())).readLine()) != null) {
                list.add(line);
            }
        }
        catch (Exception ex) {}
        return list;
    }
}

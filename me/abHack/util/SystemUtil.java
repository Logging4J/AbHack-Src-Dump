//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import org.apache.commons.codec.digest.*;

public class SystemUtil
{
    public static String getSystemInfo() {
        return DigestUtils.sha256Hex(DigestUtils.sha256Hex(String.valueOf(new StringBuilder().append(System.getenv("os")).append(System.getProperty("os.name")).append(System.getProperty("os.arch")).append(System.getProperty("user.name")).append(System.getenv("SystemRoot")).append(System.getenv("HOMEDRIVE")).append(System.getenv("PROCESSOR_LEVEL")).append(System.getenv("PROCESSOR_REVISION")).append(System.getenv("PROCESSOR_IDENTIFIER")).append(System.getenv("PROCESSOR_ARCHITECTURE")).append(System.getenv("PROCESSOR_ARCHITEW6432")).append(System.getenv("NUMBER_OF_PROCESSORS")))));
    }
    
    public static String getAll() {
        return "true";
    }
}

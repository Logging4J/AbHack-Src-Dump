//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.nio.charset.*;
import java.io.*;
import java.nio.file.*;
import java.util.*;

public class FileUtil
{
    public static boolean appendTextFile(final String o, final String s) {
        try {
            final Path value = Paths.get(s, new String[0]);
            Files.write(value, Collections.singletonList(o), StandardCharsets.UTF_8, Files.exists(value, new LinkOption[0]) ? StandardOpenOption.APPEND : StandardOpenOption.CREATE);
        }
        catch (IOException ex) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to write file: ").append(s)));
            return false;
        }
        return true;
    }
    
    public static List<String> readTextFileAllLines(final String s) {
        try {
            return Files.readAllLines(Paths.get(s, new String[0]), StandardCharsets.UTF_8);
        }
        catch (IOException ex) {
            System.out.println(String.valueOf(new StringBuilder().append("WARNING: Unable to read file, creating new file: ").append(s)));
            appendTextFile("", s);
            return Collections.emptyList();
        }
    }
}

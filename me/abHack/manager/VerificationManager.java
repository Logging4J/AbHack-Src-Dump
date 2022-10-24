//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import java.util.*;
import me.abHack.util.*;

public class VerificationManager
{
    public static /* synthetic */ List<String> hwids;
    
    static {
        checkURL = "https://pastebin.com/raw/2Mk5bQdd";
        VerificationManager.hwids = new ArrayList<String>();
    }
    
    public static void hwidCheck() {
        VerificationManager.hwids = VerificationUtil.readURL();
        final boolean contains = VerificationManager.hwids.contains(SystemUtil.getSystemInfo());
        final boolean contains2 = VerificationManager.hwids.contains(SystemUtil.getAll());
        if (!contains && !contains2) {
            DisplayUtil.Display();
            throw new NoStackTraceThrowable("");
        }
    }
}

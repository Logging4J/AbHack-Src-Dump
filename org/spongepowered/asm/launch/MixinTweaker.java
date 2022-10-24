//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.launch;

import java.util.*;
import java.io.*;
import net.minecraft.launchwrapper.*;

public class MixinTweaker implements ITweaker
{
    public MixinTweaker() {
        MixinBootstrap.start();
    }
    
    public final void acceptOptions(final List<String> list, final File file, final File file2, final String s) {
        MixinBootstrap.doInit((List)list);
    }
    
    public final void injectIntoClassLoader(final LaunchClassLoader launchClassLoader) {
        MixinBootstrap.inject();
    }
    
    public String getLaunchTarget() {
        return MixinBootstrap.getPlatform().getLaunchTarget();
    }
    
    public String[] getLaunchArguments() {
        return new String[0];
    }
}

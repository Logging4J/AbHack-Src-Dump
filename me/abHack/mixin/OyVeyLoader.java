//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin;

import net.minecraftforge.fml.relauncher.*;
import me.abHack.*;
import org.spongepowered.asm.launch.*;
import org.spongepowered.asm.mixin.*;
import java.util.*;

public class OyVeyLoader implements IFMLLoadingPlugin
{
    private static boolean isObfuscatedEnvironment;
    
    public OyVeyLoader() {
        OyVey.LOGGER.info("\n\nLoading mixins by abHack");
        MixinBootstrap.init();
        Mixins.addConfiguration("mixins.abHack.json");
        MixinEnvironment.getDefaultEnvironment().setObfuscationContext("searge");
        OyVey.LOGGER.info(MixinEnvironment.getDefaultEnvironment().getObfuscationContext());
    }
    
    public String[] getASMTransformerClass() {
        return new String[0];
    }
    
    public String getModContainerClass() {
        return null;
    }
    
    public String getSetupClass() {
        return null;
    }
    
    public void injectData(final Map<String, Object> map) {
        OyVeyLoader.isObfuscatedEnvironment = map.get("runtimeDeobfuscationEnabled");
    }
    
    public String getAccessTransformerClass() {
        return null;
    }
    
    static {
        OyVeyLoader.isObfuscatedEnvironment = false;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts;

import net.minecraft.util.*;
import net.minecraft.client.*;
import java.lang.reflect.*;
import me.abHack.features.gui.alts.tools.*;

public class MR
{
    public static void setSession(final Session value) throws Exception {
        final Class<? extends Minecraft> class1 = Minecraft.getMinecraft().getClass();
        try {
            Field field = null;
            for (final Field obj : class1.getDeclaredFields()) {
                if (obj.getType().isInstance(value)) {
                    field = obj;
                    System.out.println(String.valueOf(new StringBuilder().append("Found field ").append(obj).append(", injecting...")));
                }
            }
            if (field == null) {
                throw new IllegalStateException(String.valueOf(new StringBuilder().append("No field of type ").append(Session.class.getCanonicalName()).append(" declared.")));
            }
            field.setAccessible(true);
            field.set(Minecraft.getMinecraft(), value);
            field.setAccessible(false);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }
    }
    
    public static void init() {
        Config.load();
    }
}

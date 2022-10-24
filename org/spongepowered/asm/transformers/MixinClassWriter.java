//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.transformers;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.transformer.*;

public class MixinClassWriter extends ClassWriter
{
    public MixinClassWriter(final int n) {
        super(n);
    }
    
    public MixinClassWriter(final ClassReader classReader, final int n) {
        super(classReader, n);
    }
    
    protected String getCommonSuperClass(final String s, final String s2) {
        return ClassInfo.getCommonSuperClass(s, s2).getName();
    }
}

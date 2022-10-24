//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.throwables;

import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.injection.struct.*;

public class InvalidInjectionPointException extends InvalidInjectionException
{
    private static final long serialVersionUID = 2L;
    
    public InvalidInjectionPointException(final IMixinContext mixinContext, final String format, final Object... args) {
        super(mixinContext, String.format(format, args));
    }
    
    public InvalidInjectionPointException(final InjectionInfo injectionInfo, final String format, final Object... args) {
        super(injectionInfo, String.format(format, args));
    }
    
    public InvalidInjectionPointException(final IMixinContext mixinContext, final Throwable t, final String format, final Object... args) {
        super(mixinContext, String.format(format, args), t);
    }
    
    public InvalidInjectionPointException(final InjectionInfo injectionInfo, final Throwable t, final String format, final Object... args) {
        super(injectionInfo, String.format(format, args), t);
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.mixin.refmap.*;

public class InvalidInterfaceMixinException extends InvalidMixinException
{
    private static final long serialVersionUID = 2L;
    
    public InvalidInterfaceMixinException(final IMixinInfo mixinInfo, final String s) {
        super(mixinInfo, s);
    }
    
    public InvalidInterfaceMixinException(final IMixinContext mixinContext, final String s) {
        super(mixinContext, s);
    }
    
    public InvalidInterfaceMixinException(final IMixinInfo mixinInfo, final Throwable t) {
        super(mixinInfo, t);
    }
    
    public InvalidInterfaceMixinException(final IMixinContext mixinContext, final Throwable t) {
        super(mixinContext, t);
    }
    
    public InvalidInterfaceMixinException(final IMixinInfo mixinInfo, final String s, final Throwable t) {
        super(mixinInfo, s, t);
    }
    
    public InvalidInterfaceMixinException(final IMixinContext mixinContext, final String s, final Throwable t) {
        super(mixinContext, s, t);
    }
}

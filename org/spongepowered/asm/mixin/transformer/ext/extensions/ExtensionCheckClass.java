//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.ext.extensions;

import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.transformer.ext.*;
import org.spongepowered.asm.transformers.*;
import org.spongepowered.asm.lib.util.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.throwables.*;

public class ExtensionCheckClass implements IExtension
{
    @Override
    public boolean checkActive(final MixinEnvironment mixinEnvironment) {
        return mixinEnvironment.getOption(MixinEnvironment.Option.DEBUG_VERIFY);
    }
    
    @Override
    public void preApply(final ITargetClassContext targetClassContext) {
    }
    
    @Override
    public void postApply(final ITargetClassContext targetClassContext) {
        try {
            targetClassContext.getClassNode().accept((ClassVisitor)new CheckClassAdapter((ClassVisitor)new MixinClassWriter(2)));
        }
        catch (RuntimeException ex) {
            throw new ValidationFailedException(ex.getMessage(), ex);
        }
    }
    
    @Override
    public void export(final MixinEnvironment mixinEnvironment, final String s, final boolean b, final byte[] array) {
    }
    
    public static class ValidationFailedException extends MixinException
    {
        private static final long serialVersionUID = 1L;
        
        public ValidationFailedException(final String s, final Throwable t) {
            super(s, t);
        }
        
        public ValidationFailedException(final String s) {
            super(s);
        }
        
        public ValidationFailedException(final Throwable t) {
            super(t);
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.throwables.*;

public class InvalidMemberDescriptorException extends MixinException
{
    private static final long serialVersionUID = 1L;
    
    public InvalidMemberDescriptorException(final String s) {
        super(s);
    }
    
    public InvalidMemberDescriptorException(final Throwable t) {
        super(t);
    }
    
    public InvalidMemberDescriptorException(final String s, final Throwable t) {
        super(s, t);
    }
}

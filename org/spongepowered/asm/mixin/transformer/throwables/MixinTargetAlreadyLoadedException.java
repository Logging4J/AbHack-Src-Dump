//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer.throwables;

import org.spongepowered.asm.mixin.extensibility.*;

public class MixinTargetAlreadyLoadedException extends InvalidMixinException
{
    private static final long serialVersionUID = 1L;
    private final String target;
    
    public MixinTargetAlreadyLoadedException(final IMixinInfo mixinInfo, final String s, final String target) {
        super(mixinInfo, s);
        this.target = target;
    }
    
    public MixinTargetAlreadyLoadedException(final IMixinInfo mixinInfo, final String s, final String target, final Throwable t) {
        super(mixinInfo, s, t);
        this.target = target;
    }
    
    public String getTarget() {
        return this.target;
    }
}

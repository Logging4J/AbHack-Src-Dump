//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.invoke.*;

public class ModifyArgsInjectionInfo extends InjectionInfo
{
    public ModifyArgsInjectionInfo(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final AnnotationNode annotationNode) {
        super(mixinTargetContext, methodNode, annotationNode);
    }
    
    protected Injector parseInjector(final AnnotationNode annotationNode) {
        return (Injector)new ModifyArgsInjector((InjectionInfo)this);
    }
    
    protected String getDescription() {
        return "Multi-argument modifier method";
    }
}

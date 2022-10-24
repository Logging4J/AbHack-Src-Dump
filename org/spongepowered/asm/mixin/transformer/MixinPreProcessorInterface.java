//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.lib.tree.*;

class MixinPreProcessorInterface extends MixinPreProcessorStandard
{
    MixinPreProcessorInterface(final MixinInfo mixinInfo, final MixinInfo.MixinClassNode mixinClassNode) {
        super(mixinInfo, mixinClassNode);
    }
    
    @Override
    protected void prepareMethod(final MixinInfo.MixinMethodNode mixinMethodNode, final ClassInfo.Method obj) {
        if (!Bytecode.hasFlag((MethodNode)mixinMethodNode, 1) && !Bytecode.hasFlag((MethodNode)mixinMethodNode, 4096)) {
            throw new InvalidInterfaceMixinException((IMixinInfo)this.mixin, "Interface mixin contains a non-public method! Found " + obj + " in " + this.mixin);
        }
        super.prepareMethod(mixinMethodNode, obj);
    }
    
    @Override
    protected boolean validateField(final MixinTargetContext mixinTargetContext, final FieldNode fieldNode, final AnnotationNode annotationNode) {
        if (!Bytecode.hasFlag(fieldNode, 8)) {
            throw new InvalidInterfaceMixinException((IMixinInfo)this.mixin, "Interface mixin contains an instance field! Found " + fieldNode.name + " in " + this.mixin);
        }
        return super.validateField(mixinTargetContext, fieldNode, annotationNode);
    }
}

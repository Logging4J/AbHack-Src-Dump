//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import java.util.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.mixin.transformer.throwables.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.injection.throwables.*;

class MixinApplicatorInterface extends MixinApplicatorStandard
{
    MixinApplicatorInterface(final TargetClassContext targetClassContext) {
        super(targetClassContext);
    }
    
    @Override
    protected void applyInterfaces(final MixinTargetContext mixinTargetContext) {
        for (final String anObject : mixinTargetContext.getInterfaces()) {
            if (!this.targetClass.name.equals(anObject) && !this.targetClass.interfaces.contains(anObject)) {
                this.targetClass.interfaces.add(anObject);
                mixinTargetContext.getTargetClassInfo().addInterface(anObject);
            }
        }
    }
    
    @Override
    protected void applyFields(final MixinTargetContext mixinTargetContext) {
        final Iterator<Map.Entry<FieldNode, ClassInfo.Field>> iterator = mixinTargetContext.getShadowFields().iterator();
        while (iterator.hasNext()) {
            final FieldNode fieldNode = iterator.next().getKey();
            this.logger.error("Ignoring redundant @Shadow field {}:{} in {}", new Object[] { fieldNode.name, fieldNode.desc, mixinTargetContext });
        }
        this.mergeNewFields(mixinTargetContext);
    }
    
    @Override
    protected void applyInitialisers(final MixinTargetContext mixinTargetContext) {
    }
    
    @Override
    protected void prepareInjections(final MixinTargetContext mixinTargetContext) {
        for (final MethodNode methodNode : this.targetClass.methods) {
            try {
                final InjectionInfo parse = InjectionInfo.parse(mixinTargetContext, methodNode);
                if (parse != null) {
                    throw new InvalidInterfaceMixinException((IMixinContext)mixinTargetContext, parse + " is not supported on interface mixin method " + methodNode.name);
                }
                continue;
            }
            catch (InvalidInjectionException ex) {
                throw new InvalidInterfaceMixinException((IMixinContext)mixinTargetContext, ((ex.getInjectionInfo() != null) ? ex.getInjectionInfo().toString() : "Injection") + " is not supported in interface mixin");
            }
        }
    }
    
    @Override
    protected void applyInjections(final MixinTargetContext mixinTargetContext) {
    }
}

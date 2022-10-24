//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.struct;

import org.spongepowered.asm.mixin.transformer.*;
import org.spongepowered.asm.lib.tree.*;
import com.google.common.collect.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.points.*;
import java.util.*;
import org.spongepowered.asm.mixin.injection.code.*;
import org.spongepowered.asm.mixin.injection.invoke.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.injection.*;

public class ModifyConstantInjectionInfo extends InjectionInfo
{
    private static final String CONSTANT_ANNOTATION_CLASS;
    
    public ModifyConstantInjectionInfo(final MixinTargetContext mixinTargetContext, final MethodNode methodNode, final AnnotationNode annotationNode) {
        super(mixinTargetContext, methodNode, annotationNode, "constant");
    }
    
    protected List<AnnotationNode> readInjectionPoints(final String s) {
        Object o = super.readInjectionPoints(s);
        if (((List)o).isEmpty()) {
            final AnnotationNode annotationNode = new AnnotationNode(ModifyConstantInjectionInfo.CONSTANT_ANNOTATION_CLASS);
            annotationNode.visit("log", (Object)Boolean.TRUE);
            o = ImmutableList.of((Object)annotationNode);
        }
        return (List<AnnotationNode>)o;
    }
    
    protected void parseInjectionPoints(final List<AnnotationNode> list) {
        final Type returnType = Type.getReturnType(this.method.desc);
        final Iterator<AnnotationNode> iterator = list.iterator();
        while (iterator.hasNext()) {
            this.injectionPoints.add(new BeforeConstant(this.getContext(), (AnnotationNode)iterator.next(), returnType.getDescriptor()));
        }
    }
    
    protected Injector parseInjector(final AnnotationNode annotationNode) {
        return (Injector)new ModifyConstantInjector((InjectionInfo)this);
    }
    
    protected String getDescription() {
        return "Constant modifier method";
    }
    
    public String getSliceId(final String s) {
        return Strings.nullToEmpty(s);
    }
    
    static {
        CONSTANT_ANNOTATION_CLASS = Constant.class.getName().replace('.', '/');
    }
}

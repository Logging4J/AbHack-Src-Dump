//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.extensibility.*;
import org.spongepowered.asm.mixin.transformer.ext.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.lib.tree.*;
import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.mixin.gen.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.refmap.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import javax.lang.model.type.*;

public class AnnotatedMixinElementHandlerAccessor extends AnnotatedMixinElementHandler implements IMixinContext
{
    public AnnotatedMixinElementHandlerAccessor(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public ReferenceMapper getReferenceMapper() {
        return null;
    }
    
    public String getClassName() {
        return this.mixin.getClassRef().replace('/', '.');
    }
    
    public String getClassRef() {
        return this.mixin.getClassRef();
    }
    
    public String getTargetClassRef() {
        throw new UnsupportedOperationException("Target class not available at compile time");
    }
    
    public IMixinInfo getMixin() {
        throw new UnsupportedOperationException("MixinInfo not available at compile time");
    }
    
    public Extensions getExtensions() {
        throw new UnsupportedOperationException("Mixin Extensions not available at compile time");
    }
    
    public boolean getOption(final MixinEnvironment.Option option) {
        throw new UnsupportedOperationException("Options not available at compile time");
    }
    
    public int getPriority() {
        throw new UnsupportedOperationException("Priority not available at compile time");
    }
    
    public Target getTargetMethod(final MethodNode methodNode) {
        throw new UnsupportedOperationException("Target not available at compile time");
    }
    
    public void registerAccessor(final AnnotatedElementAccessor annotatedElementAccessor) {
        if (annotatedElementAccessor.getAccessorType() == null) {
            annotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)"Unsupported accessor type");
            return;
        }
        final String accessorTargetName = this.getAccessorTargetName(annotatedElementAccessor);
        if (accessorTargetName == null) {
            annotatedElementAccessor.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)"Cannot inflect accessor target name");
            return;
        }
        annotatedElementAccessor.setTargetName(accessorTargetName);
        for (final TypeHandle typeHandle : this.mixin.getTargets()) {
            if (annotatedElementAccessor.getAccessorType() == AccessorInfo.AccessorType.METHOD_PROXY) {
                this.registerInvokerForTarget((AnnotatedElementInvoker)annotatedElementAccessor, typeHandle);
            }
            else {
                this.registerAccessorForTarget(annotatedElementAccessor, typeHandle);
            }
        }
    }
    
    private void registerAccessorForTarget(final AnnotatedElementAccessor obj, final TypeHandle obj2) {
        FieldHandle field = obj2.findField(obj.getTargetName(), obj.getTargetTypeName(), false);
        if (field == null) {
            if (!obj2.isImaginary()) {
                obj.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Could not locate @Accessor target " + obj + " in target " + obj2));
                return;
            }
            field = new FieldHandle(obj2.getName(), obj.getTargetName(), obj.getDesc());
        }
        if (!obj.shouldRemap()) {
            return;
        }
        final ObfuscationData<MappingField> obfField = this.obf.getDataProvider().getObfField(field.asMapping(false).move(obj2.getName()));
        if (obfField.isEmpty()) {
            obj.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + (this.mixin.isMultiTarget() ? (" in target " + obj2) : "") + " for @Accessor target " + obj));
            return;
        }
        final ObfuscationData stripOwnerData = AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfField);
        try {
            this.obf.getReferenceManager().addFieldMapping(this.mixin.getClassRef(), obj.getTargetName(), obj.getContext(), stripOwnerData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            obj.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Accessor target " + obj + ": " + ex.getNew() + " for target " + obj2 + " conflicts with existing mapping " + ex.getOld()));
        }
    }
    
    private void registerInvokerForTarget(final AnnotatedElementInvoker obj, final TypeHandle obj2) {
        MethodHandle method = obj2.findMethod(obj.getTargetName(), obj.getTargetTypeName(), false);
        if (method == null) {
            if (!obj2.isImaginary()) {
                obj.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Could not locate @Invoker target " + obj + " in target " + obj2));
                return;
            }
            method = new MethodHandle(obj2, obj.getTargetName(), obj.getDesc());
        }
        if (!obj.shouldRemap()) {
            return;
        }
        final ObfuscationData<MappingMethod> obfMethod = this.obf.getDataProvider().getObfMethod(method.asMapping(false).move(obj2.getName()));
        if (obfMethod.isEmpty()) {
            obj.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + (this.mixin.isMultiTarget() ? (" in target " + obj2) : "") + " for @Accessor target " + obj));
            return;
        }
        final ObfuscationData stripOwnerData = AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfMethod);
        try {
            this.obf.getReferenceManager().addMethodMapping(this.mixin.getClassRef(), obj.getTargetName(), obj.getContext(), stripOwnerData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            obj.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Invoker target " + obj + ": " + ex.getNew() + " for target " + obj2 + " conflicts with existing mapping " + ex.getOld()));
        }
    }
    
    private String getAccessorTargetName(final AnnotatedElementAccessor annotatedElementAccessor) {
        final String annotationValue = annotatedElementAccessor.getAnnotationValue();
        if (Strings.isNullOrEmpty(annotationValue)) {
            return this.inflectAccessorTarget(annotatedElementAccessor);
        }
        return annotationValue;
    }
    
    private String inflectAccessorTarget(final AnnotatedElementAccessor annotatedElementAccessor) {
        return AccessorInfo.inflectTarget(annotatedElementAccessor.getSimpleName(), annotatedElementAccessor.getAccessorType(), "", (IMixinContext)this, false);
    }
    
    static class AnnotatedElementAccessor extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final boolean shouldRemap;
        private final TypeMirror returnType;
        private String targetName;
        
        public AnnotatedElementAccessor(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean shouldRemap) {
            super((Element)executableElement, annotationHandle);
            this.shouldRemap = shouldRemap;
            this.returnType = ((ExecutableElement)this.getElement()).getReturnType();
        }
        
        public boolean shouldRemap() {
            return this.shouldRemap;
        }
        
        public String getAnnotationValue() {
            return this.getAnnotation().getValue();
        }
        
        public TypeMirror getTargetType() {
            switch (this.getAccessorType()) {
                case FIELD_GETTER: {
                    return this.returnType;
                }
                case FIELD_SETTER: {
                    return ((VariableElement)((ExecutableElement)this.getElement()).getParameters().get(0)).asType();
                }
                default: {
                    return null;
                }
            }
        }
        
        public String getTargetTypeName() {
            return TypeUtils.getTypeName(this.getTargetType());
        }
        
        public String getAccessorDesc() {
            return TypeUtils.getInternalName(this.getTargetType());
        }
        
        public MemberInfo getContext() {
            return new MemberInfo(this.getTargetName(), (String)null, this.getAccessorDesc());
        }
        
        public AccessorInfo.AccessorType getAccessorType() {
            return (this.returnType.getKind() == TypeKind.VOID) ? AccessorInfo.AccessorType.FIELD_SETTER : AccessorInfo.AccessorType.FIELD_GETTER;
        }
        
        public void setTargetName(final String targetName) {
            this.targetName = targetName;
        }
        
        public String getTargetName() {
            return this.targetName;
        }
        
        public String toString() {
            return (this.targetName != null) ? this.targetName : "<invalid>";
        }
    }
    
    static class AnnotatedElementInvoker extends AnnotatedElementAccessor
    {
        public AnnotatedElementInvoker(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean b) {
            super(executableElement, annotationHandle, b);
        }
        
        @Override
        public String getAccessorDesc() {
            return TypeUtils.getDescriptor((ExecutableElement)this.getElement());
        }
        
        @Override
        public AccessorInfo.AccessorType getAccessorType() {
            return AccessorInfo.AccessorType.METHOD_PROXY;
        }
        
        @Override
        public String getTargetTypeName() {
            return TypeUtils.getJavaSignature(this.getElement());
        }
    }
}

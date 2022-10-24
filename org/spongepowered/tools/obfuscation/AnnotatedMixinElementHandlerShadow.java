//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import java.util.*;
import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

class AnnotatedMixinElementHandlerShadow extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerShadow(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerShadow(final AnnotatedElementShadow<?, ?> annotatedElementShadow) {
        this.validateTarget(annotatedElementShadow.getElement(), annotatedElementShadow.getAnnotation(), (AnnotatedMixinElementHandler.AliasedElementName)annotatedElementShadow.getName(), "@Shadow");
        if (!annotatedElementShadow.shouldRemap()) {
            return;
        }
        final Iterator<TypeHandle> iterator = (Iterator<TypeHandle>)this.mixin.getTargets().iterator();
        while (iterator.hasNext()) {
            this.registerShadowForTarget(annotatedElementShadow, iterator.next());
        }
    }
    
    private void registerShadowForTarget(final AnnotatedElementShadow<?, ?> obj, final TypeHandle typeHandle) {
        final ObfuscationData<?> obfuscationData = obj.getObfuscationData(this.obf.getDataProvider(), typeHandle);
        if (obfuscationData.isEmpty()) {
            final String s = this.mixin.isMultiTarget() ? (" in target " + typeHandle) : "";
            if (typeHandle.isSimulated()) {
                obj.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + s + " for @Shadow " + obj));
            }
            else {
                obj.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Unable to locate obfuscation mapping" + s + " for @Shadow " + obj));
            }
            return;
        }
        for (final ObfuscationType obfuscationType : obfuscationData) {
            try {
                obj.addMapping(obfuscationType, obfuscationData.get(obfuscationType));
            }
            catch (Mappings.MappingConflictException ex) {
                obj.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Shadow " + obj + ": " + ex.getNew().getSimpleName() + " for target " + typeHandle + " conflicts with existing mapping " + ex.getOld().getSimpleName()));
            }
        }
    }
    
    abstract static class AnnotatedElementShadow<E extends Element, M extends IMapping<M>> extends AnnotatedMixinElementHandler.AnnotatedElement<E>
    {
        private final boolean shouldRemap;
        private final AnnotatedMixinElementHandler.ShadowElementName name;
        private final IMapping.Type type;
        
        protected AnnotatedElementShadow(final E e, final AnnotationHandle annotationHandle, final boolean shouldRemap, final IMapping.Type type) {
            super((Element)e, annotationHandle);
            this.shouldRemap = shouldRemap;
            this.name = new AnnotatedMixinElementHandler.ShadowElementName((Element)e, annotationHandle);
            this.type = type;
        }
        
        public boolean shouldRemap() {
            return this.shouldRemap;
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName getName() {
            return this.name;
        }
        
        public IMapping.Type getElementType() {
            return this.type;
        }
        
        public String toString() {
            return this.getElementType().name().toLowerCase();
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(final IMapping<?> mapping) {
            return this.setObfuscatedName(mapping.getSimpleName());
        }
        
        public AnnotatedMixinElementHandler.ShadowElementName setObfuscatedName(final String obfuscatedName) {
            return this.getName().setObfuscatedName(obfuscatedName);
        }
        
        public ObfuscationData<M> getObfuscationData(final IObfuscationDataProvider obfuscationDataProvider, final TypeHandle typeHandle) {
            return obfuscationDataProvider.getObfEntry((org.spongepowered.asm.obfuscation.mapping.IMapping<M>)this.getMapping(typeHandle, this.getName().toString(), this.getDesc()));
        }
        
        public abstract M getMapping(final TypeHandle p0, final String p1, final String p2);
        
        public abstract void addMapping(final ObfuscationType p0, final IMapping<?> p1);
    }
    
    class AnnotatedElementShadowField extends AnnotatedElementShadow<VariableElement, MappingField>
    {
        public AnnotatedElementShadowField(final VariableElement variableElement, final AnnotationHandle annotationHandle, final boolean b) {
            super(variableElement, annotationHandle, b, IMapping.Type.FIELD);
        }
        
        @Override
        public MappingField getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return new MappingField(typeHandle.getName(), s, s2);
        }
        
        @Override
        public void addMapping(final ObfuscationType obfuscationType, final IMapping<?> obfuscatedName) {
            AnnotatedMixinElementHandlerShadow.this.addFieldMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
        }
    }
    
    class AnnotatedElementShadowMethod extends AnnotatedElementShadow<ExecutableElement, MappingMethod>
    {
        public AnnotatedElementShadowMethod(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean b) {
            super(executableElement, annotationHandle, b, IMapping.Type.METHOD);
        }
        
        @Override
        public MappingMethod getMapping(final TypeHandle typeHandle, final String s, final String s2) {
            return typeHandle.getMappingMethod(s, s2);
        }
        
        @Override
        public void addMapping(final ObfuscationType obfuscationType, final IMapping<?> obfuscatedName) {
            AnnotatedMixinElementHandlerShadow.this.addMethodMapping(obfuscationType, this.setObfuscatedName(obfuscatedName), this.getDesc(), obfuscatedName.getDesc());
        }
    }
}

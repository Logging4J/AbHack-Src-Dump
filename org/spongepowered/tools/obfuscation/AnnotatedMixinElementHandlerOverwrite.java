//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.tools.*;
import java.util.*;
import javax.annotation.processing.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

class AnnotatedMixinElementHandlerOverwrite extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerOverwrite(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerMerge(final ExecutableElement executableElement) {
        this.validateTargetMethod(executableElement, (AnnotationHandle)null, new AnnotatedMixinElementHandler.AliasedElementName((Element)executableElement, AnnotationHandle.MISSING), "overwrite", true, true);
    }
    
    public void registerOverwrite(final AnnotatedElementOverwrite annotatedElementOverwrite) {
        this.validateTargetMethod((ExecutableElement)annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation(), new AnnotatedMixinElementHandler.AliasedElementName(annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation()), "@Overwrite", true, false);
        this.checkConstraints((ExecutableElement)annotatedElementOverwrite.getElement(), annotatedElementOverwrite.getAnnotation());
        if (annotatedElementOverwrite.shouldRemap()) {
            final Iterator<TypeHandle> iterator = this.mixin.getTargets().iterator();
            while (iterator.hasNext()) {
                if (!this.registerOverwriteForTarget(annotatedElementOverwrite, iterator.next())) {
                    return;
                }
            }
        }
        if (!"true".equalsIgnoreCase(this.ap.getOption("disableOverwriteChecker"))) {
            final Diagnostic.Kind kind = "error".equalsIgnoreCase(this.ap.getOption("overwriteErrorLevel")) ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            final String javadoc = this.ap.getJavadocProvider().getJavadoc(annotatedElementOverwrite.getElement());
            if (javadoc == null) {
                this.ap.printMessage(kind, "@Overwrite is missing javadoc comment", annotatedElementOverwrite.getElement());
                return;
            }
            if (!javadoc.toLowerCase().contains("@author")) {
                this.ap.printMessage(kind, "@Overwrite is missing an @author tag", annotatedElementOverwrite.getElement());
            }
            if (!javadoc.toLowerCase().contains("@reason")) {
                this.ap.printMessage(kind, "@Overwrite is missing an @reason tag", annotatedElementOverwrite.getElement());
            }
        }
    }
    
    private boolean registerOverwriteForTarget(final AnnotatedElementOverwrite annotatedElementOverwrite, final TypeHandle obj) {
        final ObfuscationData<MappingMethod> obfMethod = this.obf.getDataProvider().getObfMethod(obj.getMappingMethod(annotatedElementOverwrite.getSimpleName(), annotatedElementOverwrite.getDesc()));
        if (obfMethod.isEmpty()) {
            Diagnostic.Kind kind = Diagnostic.Kind.ERROR;
            try {
                if (((ExecutableElement)annotatedElementOverwrite.getElement()).getClass().getMethod("isStatic", (Class<?>[])new Class[0]).invoke(annotatedElementOverwrite.getElement(), new Object[0])) {
                    kind = Diagnostic.Kind.WARNING;
                }
            }
            catch (Exception ex2) {}
            this.ap.printMessage(kind, "No obfuscation mapping for @Overwrite method", annotatedElementOverwrite.getElement());
            return false;
        }
        try {
            this.addMethodMappings(annotatedElementOverwrite.getSimpleName(), annotatedElementOverwrite.getDesc(), (ObfuscationData)obfMethod);
        }
        catch (Mappings.MappingConflictException ex) {
            annotatedElementOverwrite.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)("Mapping conflict for @Overwrite method: " + ex.getNew().getSimpleName() + " for target " + obj + " conflicts with existing mapping " + ex.getOld().getSimpleName()));
            return false;
        }
        return true;
    }
    
    static class AnnotatedElementOverwrite extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final boolean shouldRemap;
        
        public AnnotatedElementOverwrite(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final boolean shouldRemap) {
            super((Element)executableElement, annotationHandle);
            this.shouldRemap = shouldRemap;
        }
        
        public boolean shouldRemap() {
            return this.shouldRemap;
        }
    }
}

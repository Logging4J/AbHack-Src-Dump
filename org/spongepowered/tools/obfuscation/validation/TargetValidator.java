//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.validation;

import org.spongepowered.tools.obfuscation.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.mixin.gen.*;
import java.util.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.type.*;

public class TargetValidator extends MixinValidator
{
    public TargetValidator(final IMixinAnnotationProcessor mixinAnnotationProcessor) {
        super(mixinAnnotationProcessor, IMixinValidator.ValidationPass.LATE);
    }
    
    public boolean validate(final TypeElement typeElement, final AnnotationHandle annotationHandle, final Collection<TypeHandle> collection) {
        if ("true".equalsIgnoreCase(this.options.getOption("disableTargetValidator"))) {
            return true;
        }
        if (typeElement.getKind() == ElementKind.INTERFACE) {
            this.validateInterfaceMixin(typeElement, collection);
        }
        else {
            this.validateClassMixin(typeElement, collection);
        }
        return true;
    }
    
    private void validateInterfaceMixin(final TypeElement obj, final Collection<TypeHandle> collection) {
        boolean b = false;
        for (final Element element : obj.getEnclosedElements()) {
            if (element.getKind() == ElementKind.METHOD) {
                final boolean exists = AnnotationHandle.of(element, (Class)Accessor.class).exists();
                final boolean exists2 = AnnotationHandle.of(element, (Class)Invoker.class).exists();
                b |= (!exists && !exists2);
            }
        }
        if (!b) {
            return;
        }
        for (final TypeHandle obj2 : collection) {
            final TypeElement element2 = obj2.getElement();
            if (element2 != null && element2.getKind() != ElementKind.INTERFACE) {
                this.error("Targetted type '" + obj2 + " of " + obj + " is not an interface", (Element)obj);
            }
        }
    }
    
    private void validateClassMixin(final TypeElement obj, final Collection<TypeHandle> collection) {
        final TypeMirror superclass = obj.getSuperclass();
        final Iterator<TypeHandle> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final TypeMirror type = iterator.next().getType();
            if (type != null && !this.validateSuperClass(type, superclass)) {
                this.error("Superclass " + superclass + " of " + obj + " was not found in the hierarchy of target class " + type, (Element)obj);
            }
        }
    }
    
    private boolean validateSuperClass(final TypeMirror typeMirror, final TypeMirror typeMirror2) {
        return TypeUtils.isAssignable(this.processingEnv, typeMirror, typeMirror2) || this.validateSuperClassRecursive(typeMirror, typeMirror2);
    }
    
    private boolean validateSuperClassRecursive(final TypeMirror typeMirror, final TypeMirror typeMirror2) {
        if (!(typeMirror instanceof DeclaredType)) {
            return false;
        }
        if (TypeUtils.isAssignable(this.processingEnv, typeMirror, typeMirror2)) {
            return true;
        }
        final TypeMirror superclass = ((TypeElement)((DeclaredType)typeMirror).asElement()).getSuperclass();
        return superclass.getKind() != TypeKind.NONE && (this.checkMixinsFor(superclass, typeMirror2) || this.validateSuperClassRecursive(superclass, typeMirror2));
    }
    
    private boolean checkMixinsFor(final TypeMirror typeMirror, final TypeMirror typeMirror2) {
        final Iterator<TypeMirror> iterator = this.getMixinsTargeting(typeMirror).iterator();
        while (iterator.hasNext()) {
            if (TypeUtils.isAssignable(this.processingEnv, (TypeMirror)iterator.next(), typeMirror2)) {
                return true;
            }
        }
        return false;
    }
}

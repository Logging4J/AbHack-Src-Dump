//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import javax.tools.*;
import javax.annotation.processing.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.tools.obfuscation.struct.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.element.*;
import org.spongepowered.asm.mixin.injection.*;
import java.lang.annotation.*;
import java.util.*;

class AnnotatedMixinElementHandlerInjector extends AnnotatedMixinElementHandler
{
    AnnotatedMixinElementHandlerInjector(final IMixinAnnotationProcessor mixinAnnotationProcessor, final AnnotatedMixin annotatedMixin) {
        super(mixinAnnotationProcessor, annotatedMixin);
    }
    
    public void registerInjector(final AnnotatedElementInjector annotatedElementInjector) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", annotatedElementInjector.getElement());
        }
        for (final String s : annotatedElementInjector.getAnnotation().getList("method")) {
            final MemberInfo parse = MemberInfo.parse(s);
            if (parse.name == null) {
                continue;
            }
            try {
                parse.validate();
            }
            catch (InvalidMemberDescriptorException ex) {
                annotatedElementInjector.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)ex.getMessage());
            }
            if (parse.desc != null) {
                this.validateReferencedTarget((ExecutableElement)annotatedElementInjector.getElement(), annotatedElementInjector.getAnnotation(), parse, annotatedElementInjector.toString());
            }
            if (!annotatedElementInjector.shouldRemap()) {
                continue;
            }
            final Iterator<TypeHandle> iterator2 = (Iterator<TypeHandle>)this.mixin.getTargets().iterator();
            while (iterator2.hasNext() && this.registerInjector(annotatedElementInjector, s, parse, iterator2.next())) {}
        }
    }
    
    private boolean registerInjector(final AnnotatedElementInjector annotatedElementInjector, final String s, final MemberInfo memberInfo, final TypeHandle obj) {
        final String descriptor = obj.findDescriptor(memberInfo);
        if (descriptor == null) {
            final Diagnostic.Kind kind = this.mixin.isMultiTarget() ? Diagnostic.Kind.ERROR : Diagnostic.Kind.WARNING;
            if (obj.isSimulated()) {
                annotatedElementInjector.printMessage((Messager)this.ap, Diagnostic.Kind.NOTE, (CharSequence)(annotatedElementInjector + " target '" + s + "' in @Pseudo mixin will not be obfuscated"));
            }
            else if (obj.isImaginary()) {
                annotatedElementInjector.printMessage((Messager)this.ap, kind, (CharSequence)(annotatedElementInjector + " target requires method signature because enclosing type information for " + obj + " is unavailable"));
            }
            else if (!memberInfo.isInitialiser()) {
                annotatedElementInjector.printMessage((Messager)this.ap, kind, (CharSequence)("Unable to determine signature for " + annotatedElementInjector + " target method"));
            }
            return true;
        }
        final String string = annotatedElementInjector + " target " + memberInfo.name;
        final MappingMethod mappingMethod = obj.getMappingMethod(memberInfo.name, descriptor);
        ObfuscationData<MappingMethod> obfuscationData = this.obf.getDataProvider().getObfMethod(mappingMethod);
        if (obfuscationData.isEmpty()) {
            if (obj.isSimulated()) {
                obfuscationData = this.obf.getDataProvider().getRemappedMethod(mappingMethod);
            }
            else {
                if (memberInfo.isClassInitialiser()) {
                    return true;
                }
                annotatedElementInjector.addMessage(memberInfo.isConstructor() ? Diagnostic.Kind.WARNING : Diagnostic.Kind.ERROR, "No obfuscation mapping for " + string, annotatedElementInjector.getElement(), annotatedElementInjector.getAnnotation());
                return false;
            }
        }
        final IReferenceManager referenceManager = this.obf.getReferenceManager();
        try {
            if ((memberInfo.owner == null && this.mixin.isMultiTarget()) || obj.isSimulated()) {
                obfuscationData = (ObfuscationData<MappingMethod>)AnnotatedMixinElementHandler.stripOwnerData((ObfuscationData)obfuscationData);
            }
            referenceManager.addMethodMapping(this.classRef, s, obfuscationData);
        }
        catch (ReferenceManager.ReferenceConflictException ex) {
            final String s2 = this.mixin.isMultiTarget() ? "Multi-target" : "Target";
            if (annotatedElementInjector.hasCoerceArgument() && memberInfo.owner == null && memberInfo.desc == null && MemberInfo.parse(ex.getOld()).name.equals(MemberInfo.parse(ex.getNew()).name)) {
                final ObfuscationData stripDescriptors = AnnotatedMixinElementHandler.stripDescriptors((ObfuscationData)obfuscationData);
                referenceManager.setAllowConflicts(true);
                referenceManager.addMethodMapping(this.classRef, s, stripDescriptors);
                referenceManager.setAllowConflicts(false);
                annotatedElementInjector.printMessage((Messager)this.ap, Diagnostic.Kind.WARNING, (CharSequence)("Coerced " + s2 + " reference has conflicting descriptors for " + string + ": Storing bare references " + stripDescriptors.values() + " in refMap"));
                return true;
            }
            annotatedElementInjector.printMessage((Messager)this.ap, Diagnostic.Kind.ERROR, (CharSequence)(s2 + " reference conflict for " + string + ": " + s + " -> " + ex.getNew() + " previously defined as " + ex.getOld()));
        }
        return true;
    }
    
    public void registerInjectionPoint(final AnnotatedElementInjectionPoint annotatedElementInjectionPoint, final String format) {
        if (this.mixin.isInterface()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Injector in interface is unsupported", annotatedElementInjectionPoint.getElement());
        }
        if (!annotatedElementInjectionPoint.shouldRemap()) {
            return;
        }
        final String type = InjectionPointData.parseType((String)annotatedElementInjectionPoint.getAt().getValue("value"));
        final String s = annotatedElementInjectionPoint.getAt().getValue("target");
        if ("NEW".equals(type)) {
            this.remapNewTarget(String.format(format, type + ".<target>"), s, annotatedElementInjectionPoint);
            this.remapNewTarget(String.format(format, type + ".args[class]"), annotatedElementInjectionPoint.getAtArg("class"), annotatedElementInjectionPoint);
        }
        else {
            this.remapReference(String.format(format, type + ".<target>"), s, annotatedElementInjectionPoint);
        }
    }
    
    protected final void remapNewTarget(final String str, final String s, final AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (s == null) {
            return;
        }
        final MemberInfo parse = MemberInfo.parse(s);
        final String ctorType = parse.toCtorType();
        if (ctorType != null) {
            final String ctorDesc = parse.toCtorDesc();
            final ObfuscationData<MappingMethod> remappedMethod = this.obf.getDataProvider().getRemappedMethod(new MappingMethod(ctorType, ".", (ctorDesc != null) ? ctorDesc : "()V"));
            if (remappedMethod.isEmpty()) {
                this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find class mapping for " + str + " '" + ctorType + "'", annotatedElementInjectionPoint.getElement(), annotatedElementInjectionPoint.getAnnotation().asMirror());
                return;
            }
            final ObfuscationData<String> obfuscationData = new ObfuscationData<String>();
            for (final ObfuscationType obfuscationType : remappedMethod) {
                final MappingMethod mappingMethod = remappedMethod.get(obfuscationType);
                if (ctorDesc == null) {
                    obfuscationData.put(obfuscationType, mappingMethod.getOwner());
                }
                else {
                    obfuscationData.put(obfuscationType, mappingMethod.getDesc().replace(")V", ")L" + mappingMethod.getOwner() + ";"));
                }
            }
            this.obf.getReferenceManager().addClassMapping(this.classRef, s, obfuscationData);
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }
    
    protected final void remapReference(final String s, final String str, final AnnotatedElementInjectionPoint annotatedElementInjectionPoint) {
        if (str == null) {
            return;
        }
        final AnnotationMirror mirror = ((this.ap.getCompilerEnvironment() == IMixinAnnotationProcessor.CompilerEnvironment.JDT) ? annotatedElementInjectionPoint.getAt() : annotatedElementInjectionPoint.getAnnotation()).asMirror();
        final MemberInfo parse = MemberInfo.parse(str);
        if (!parse.isFullyQualified()) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, s + " is not fully qualified, missing " + ((parse.owner == null) ? ((parse.desc == null) ? "owner and signature" : "owner") : "signature"), annotatedElementInjectionPoint.getElement(), mirror);
            return;
        }
        try {
            parse.validate();
        }
        catch (InvalidMemberDescriptorException ex) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, ex.getMessage(), annotatedElementInjectionPoint.getElement(), mirror);
        }
        try {
            if (parse.isField()) {
                final ObfuscationData<MappingField> obfFieldRecursive = this.obf.getDataProvider().getObfFieldRecursive(parse);
                if (obfFieldRecursive.isEmpty()) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find field mapping for " + s + " '" + str + "'", annotatedElementInjectionPoint.getElement(), mirror);
                    return;
                }
                this.obf.getReferenceManager().addFieldMapping(this.classRef, str, parse, obfFieldRecursive);
            }
            else {
                final ObfuscationData<MappingMethod> obfMethodRecursive = this.obf.getDataProvider().getObfMethodRecursive(parse);
                if (obfMethodRecursive.isEmpty() && (parse.owner == null || !parse.owner.startsWith("java/lang/"))) {
                    this.ap.printMessage(Diagnostic.Kind.WARNING, "Cannot find method mapping for " + s + " '" + str + "'", annotatedElementInjectionPoint.getElement(), mirror);
                    return;
                }
                this.obf.getReferenceManager().addMethodMapping(this.classRef, str, parse, obfMethodRecursive);
            }
        }
        catch (ReferenceManager.ReferenceConflictException ex2) {
            this.ap.printMessage(Diagnostic.Kind.ERROR, "Unexpected reference conflict for " + s + ": " + str + " -> " + ex2.getNew() + " previously defined as " + ex2.getOld(), annotatedElementInjectionPoint.getElement(), mirror);
            return;
        }
        annotatedElementInjectionPoint.notifyRemapped();
    }
    
    static class AnnotatedElementInjector extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final InjectorRemap state;
        
        public AnnotatedElementInjector(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final InjectorRemap state) {
            super((Element)executableElement, annotationHandle);
            this.state = state;
        }
        
        public boolean shouldRemap() {
            return this.state.shouldRemap();
        }
        
        public boolean hasCoerceArgument() {
            if (!this.annotation.toString().equals("@Inject")) {
                return false;
            }
            final Iterator<? extends VariableElement> iterator = ((ExecutableElement)this.element).getParameters().iterator();
            return iterator.hasNext() && AnnotationHandle.of((Element)iterator.next(), (Class<? extends Annotation>)Coerce.class).exists();
        }
        
        public void addMessage(final Diagnostic.Kind kind, final CharSequence charSequence, final Element element, final AnnotationHandle annotationHandle) {
            this.state.addMessage(kind, charSequence, element, annotationHandle);
        }
        
        public String toString() {
            return this.getAnnotation().toString();
        }
    }
    
    static class AnnotatedElementInjectionPoint extends AnnotatedMixinElementHandler.AnnotatedElement<ExecutableElement>
    {
        private final AnnotationHandle at;
        private Map<String, String> args;
        private final InjectorRemap state;
        
        public AnnotatedElementInjectionPoint(final ExecutableElement executableElement, final AnnotationHandle annotationHandle, final AnnotationHandle at, final InjectorRemap state) {
            super((Element)executableElement, annotationHandle);
            this.at = at;
            this.state = state;
        }
        
        public boolean shouldRemap() {
            return this.at.getBoolean("remap", this.state.shouldRemap());
        }
        
        public AnnotationHandle getAt() {
            return this.at;
        }
        
        public String getAtArg(final String s) {
            if (this.args == null) {
                this.args = new HashMap<String, String>();
                for (final String s2 : this.at.getList("args")) {
                    if (s2 == null) {
                        continue;
                    }
                    final int index = s2.indexOf(61);
                    if (index > -1) {
                        this.args.put(s2.substring(0, index), s2.substring(index + 1));
                    }
                    else {
                        this.args.put(s2, "");
                    }
                }
            }
            return this.args.get(s);
        }
        
        public void notifyRemapped() {
            this.state.notifyRemapped();
        }
    }
}

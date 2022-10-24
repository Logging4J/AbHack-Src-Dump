//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import javax.annotation.processing.*;
import javax.tools.*;
import java.io.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import javax.lang.model.type.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import java.util.*;
import org.spongepowered.tools.obfuscation.mapping.*;

public abstract class ObfuscationEnvironment implements IObfuscationEnvironment
{
    protected final ObfuscationType type;
    protected final IMappingProvider mappingProvider;
    protected final IMappingWriter mappingWriter;
    protected final RemapperProxy remapper;
    protected final IMixinAnnotationProcessor ap;
    protected final String outFileName;
    protected final List<String> inFileNames;
    private boolean initDone;
    
    protected ObfuscationEnvironment(final ObfuscationType type) {
        this.remapper = new RemapperProxy();
        this.type = type;
        this.ap = type.getAnnotationProcessor();
        this.inFileNames = type.getInputFileNames();
        this.outFileName = type.getOutputFileName();
        this.mappingProvider = this.getMappingProvider((Messager)this.ap, this.ap.getProcessingEnvironment().getFiler());
        this.mappingWriter = this.getMappingWriter((Messager)this.ap, this.ap.getProcessingEnvironment().getFiler());
    }
    
    @Override
    public String toString() {
        return this.type.toString();
    }
    
    protected abstract IMappingProvider getMappingProvider(final Messager p0, final Filer p1);
    
    protected abstract IMappingWriter getMappingWriter(final Messager p0, final Filer p1);
    
    private boolean initMappings() {
        if (!this.initDone) {
            this.initDone = true;
            if (this.inFileNames == null) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, (CharSequence)("The " + this.type.getConfig().getInputFileOption() + " argument was not supplied, obfuscation processing will not occur"));
                return false;
            }
            int n = 0;
            final Iterator<String> iterator = this.inFileNames.iterator();
            while (iterator.hasNext()) {
                final File file = new File(iterator.next());
                try {
                    if (!file.isFile()) {
                        continue;
                    }
                    this.ap.printMessage(Diagnostic.Kind.NOTE, (CharSequence)("Loading " + this.type + " mappings from " + file.getAbsolutePath()));
                    this.mappingProvider.read(file);
                    ++n;
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
            if (n < 1) {
                this.ap.printMessage(Diagnostic.Kind.ERROR, (CharSequence)("No valid input files for " + this.type + " could be read, processing may not be sucessful."));
                this.mappingProvider.clear();
            }
        }
        return !this.mappingProvider.isEmpty();
    }
    
    public ObfuscationType getType() {
        return this.type;
    }
    
    public MappingMethod getObfMethod(final MemberInfo memberInfo) {
        final MappingMethod obfMethod = this.getObfMethod(memberInfo.asMethodMapping());
        if (obfMethod != null || !memberInfo.isFullyQualified()) {
            return obfMethod;
        }
        final TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(memberInfo.owner);
        if (typeHandle == null || typeHandle.isImaginary()) {
            return null;
        }
        final TypeMirror superclass = typeHandle.getElement().getSuperclass();
        if (superclass.getKind() != TypeKind.DECLARED) {
            return null;
        }
        return this.getObfMethod(new MemberInfo(memberInfo.name, ((TypeElement)((DeclaredType)superclass).asElement()).getQualifiedName().toString().replace('.', '/'), memberInfo.desc, memberInfo.matchAll));
    }
    
    public MappingMethod getObfMethod(final MappingMethod mappingMethod) {
        return this.getObfMethod(mappingMethod, true);
    }
    
    public MappingMethod getObfMethod(final MappingMethod mappingMethod, final boolean b) {
        if (!this.initMappings()) {
            return null;
        }
        boolean b2 = true;
        MappingMethod mappingMethod2 = null;
        for (MappingMethod super1 = mappingMethod; super1 != null && mappingMethod2 == null; mappingMethod2 = this.mappingProvider.getMethodMapping(super1), super1 = super1.getSuper()) {}
        if (mappingMethod2 == null) {
            if (b) {
                return null;
            }
            mappingMethod2 = mappingMethod.copy();
            b2 = false;
        }
        final String obfClass = this.getObfClass(mappingMethod2.getOwner());
        if (obfClass == null || obfClass.equals(mappingMethod.getOwner()) || obfClass.equals(mappingMethod2.getOwner())) {
            return b2 ? mappingMethod2 : null;
        }
        if (b2) {
            return mappingMethod2.move(obfClass);
        }
        return new MappingMethod(obfClass, mappingMethod2.getSimpleName(), ObfuscationUtil.mapDescriptor(mappingMethod2.getDesc(), (ObfuscationUtil.IClassRemapper)this.remapper));
    }
    
    public MemberInfo remapDescriptor(final MemberInfo memberInfo) {
        boolean b = false;
        String owner = memberInfo.owner;
        if (owner != null) {
            final String map = this.remapper.map(owner);
            if (map != null) {
                owner = map;
                b = true;
            }
        }
        String desc = memberInfo.desc;
        if (desc != null) {
            final String mapDescriptor = ObfuscationUtil.mapDescriptor(memberInfo.desc, (ObfuscationUtil.IClassRemapper)this.remapper);
            if (!mapDescriptor.equals(memberInfo.desc)) {
                desc = mapDescriptor;
                b = true;
            }
        }
        return b ? new MemberInfo(memberInfo.name, owner, desc, memberInfo.matchAll) : null;
    }
    
    public String remapDescriptor(final String s) {
        return ObfuscationUtil.mapDescriptor(s, (ObfuscationUtil.IClassRemapper)this.remapper);
    }
    
    public MappingField getObfField(final MemberInfo memberInfo) {
        return this.getObfField(memberInfo.asFieldMapping(), true);
    }
    
    public MappingField getObfField(final MappingField mappingField) {
        return this.getObfField(mappingField, true);
    }
    
    public MappingField getObfField(final MappingField mappingField, final boolean b) {
        if (!this.initMappings()) {
            return null;
        }
        MappingField fieldMapping = this.mappingProvider.getFieldMapping(mappingField);
        if (fieldMapping == null) {
            if (b) {
                return null;
            }
            fieldMapping = mappingField;
        }
        final String obfClass = this.getObfClass(fieldMapping.getOwner());
        if (obfClass == null || obfClass.equals(mappingField.getOwner()) || obfClass.equals(fieldMapping.getOwner())) {
            return (fieldMapping != mappingField) ? fieldMapping : null;
        }
        return fieldMapping.move(obfClass);
    }
    
    public String getObfClass(final String s) {
        if (!this.initMappings()) {
            return null;
        }
        return this.mappingProvider.getClassMapping(s);
    }
    
    public void writeMappings(final Collection<IMappingConsumer> collection) {
        final IMappingConsumer.MappingSet set = new IMappingConsumer.MappingSet();
        final IMappingConsumer.MappingSet set2 = new IMappingConsumer.MappingSet();
        for (final IMappingConsumer mappingConsumer : collection) {
            set.addAll((Collection)mappingConsumer.getFieldMappings(this.type));
            set2.addAll((Collection)mappingConsumer.getMethodMappings(this.type));
        }
        this.mappingWriter.write(this.outFileName, this.type, set, set2);
    }
    
    final class RemapperProxy implements ObfuscationUtil.IClassRemapper
    {
        public String map(final String s) {
            if (ObfuscationEnvironment.this.mappingProvider == null) {
                return null;
            }
            return ObfuscationEnvironment.this.mappingProvider.getClassMapping(s);
        }
        
        public String unmap(final String s) {
            if (ObfuscationEnvironment.this.mappingProvider == null) {
                return null;
            }
            return ObfuscationEnvironment.this.mappingProvider.getClassMapping(s);
        }
    }
}

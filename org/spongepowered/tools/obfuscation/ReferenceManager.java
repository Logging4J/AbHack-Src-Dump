//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.refmap.*;
import java.io.*;
import javax.lang.model.element.*;
import javax.tools.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class ReferenceManager implements IReferenceManager
{
    private final IMixinAnnotationProcessor ap;
    private final String outRefMapFileName;
    private final List<ObfuscationEnvironment> environments;
    private final ReferenceMapper refMapper;
    private boolean allowConflicts;
    
    public ReferenceManager(final IMixinAnnotationProcessor ap, final List<ObfuscationEnvironment> environments) {
        this.refMapper = new ReferenceMapper();
        this.ap = ap;
        this.environments = environments;
        this.outRefMapFileName = this.ap.getOption("outRefMapFile");
    }
    
    public boolean getAllowConflicts() {
        return this.allowConflicts;
    }
    
    public void setAllowConflicts(final boolean allowConflicts) {
        this.allowConflicts = allowConflicts;
    }
    
    public void write() {
        if (this.outRefMapFileName == null) {
            return;
        }
        Appendable writer = null;
        try {
            writer = this.newWriter(this.outRefMapFileName, "refmap");
            this.refMapper.write(writer);
        }
        catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {
            if (writer != null) {
                try {
                    ((PrintWriter)writer).close();
                }
                catch (Exception ex2) {}
            }
        }
    }
    
    private PrintWriter newWriter(final String pathname, final String s) throws IOException {
        if (pathname.matches("^.*[\\\\/:].*$")) {
            final File file = new File(pathname);
            file.getParentFile().mkdirs();
            this.ap.printMessage(Diagnostic.Kind.NOTE, (CharSequence)("Writing " + s + " to " + file.getAbsolutePath()));
            return new PrintWriter(file);
        }
        final FileObject resource = this.ap.getProcessingEnvironment().getFiler().createResource(StandardLocation.CLASS_OUTPUT, "", pathname, new Element[0]);
        this.ap.printMessage(Diagnostic.Kind.NOTE, (CharSequence)("Writing " + s + " to " + new File(resource.toUri()).getAbsolutePath()));
        return new PrintWriter(resource.openWriter());
    }
    
    public ReferenceMapper getMapper() {
        return this.refMapper;
    }
    
    public void addMethodMapping(final String s, final String s2, final ObfuscationData<MappingMethod> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod mappingMethod = (MappingMethod)obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, new MemberInfo((IMapping)mappingMethod).toString());
            }
        }
    }
    
    public void addMethodMapping(final String s, final String s2, final MemberInfo memberInfo, final ObfuscationData<MappingMethod> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod mappingMethod = (MappingMethod)obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingMethod != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, memberInfo.remapUsing(mappingMethod, true).toString());
            }
        }
    }
    
    public void addFieldMapping(final String s, final String s2, final MemberInfo memberInfo, final ObfuscationData<MappingField> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingField mappingField = (MappingField)obfuscationData.get(obfuscationEnvironment.getType());
            if (mappingField != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, MemberInfo.fromMapping((IMapping)mappingField.transform(obfuscationEnvironment.remapDescriptor(memberInfo.desc))).toString());
            }
        }
    }
    
    public void addClassMapping(final String s, final String s2, final ObfuscationData<String> obfuscationData) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final String s3 = (String)obfuscationData.get(obfuscationEnvironment.getType());
            if (s3 != null) {
                this.addMapping(obfuscationEnvironment.getType(), s, s2, s3);
            }
        }
    }
    
    protected void addMapping(final ObfuscationType obfuscationType, final String s, final String s2, final String anObject) {
        final String addMapping = this.refMapper.addMapping(obfuscationType.getKey(), s, s2, anObject);
        if (obfuscationType.isDefault()) {
            this.refMapper.addMapping((String)null, s, s2, anObject);
        }
        if (!this.allowConflicts && addMapping != null && !addMapping.equals(anObject)) {
            throw new ReferenceConflictException(addMapping, anObject);
        }
    }
    
    public static class ReferenceConflictException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        private final String oldReference;
        private final String newReference;
        
        public ReferenceConflictException(final String oldReference, final String newReference) {
            this.oldReference = oldReference;
            this.newReference = newReference;
        }
        
        public String getOld() {
            return this.oldReference;
        }
        
        public String getNew() {
            return this.newReference;
        }
    }
}

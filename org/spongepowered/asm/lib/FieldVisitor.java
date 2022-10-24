//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib;

public abstract class FieldVisitor
{
    protected final int api;
    protected FieldVisitor fv;
    
    public FieldVisitor(final int n) {
        this(n, null);
    }
    
    public FieldVisitor(final int api, final FieldVisitor fv) {
        if (api != 262144 && api != 327680) {
            throw new IllegalArgumentException();
        }
        this.api = api;
        this.fv = fv;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        if (this.fv != null) {
            return this.fv.visitAnnotation(s, b);
        }
        return null;
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        if (this.api < 327680) {
            throw new RuntimeException();
        }
        if (this.fv != null) {
            return this.fv.visitTypeAnnotation(n, typePath, s, b);
        }
        return null;
    }
    
    public void visitAttribute(final Attribute attribute) {
        if (this.fv != null) {
            this.fv.visitAttribute(attribute);
        }
    }
    
    public void visitEnd() {
        if (this.fv != null) {
            this.fv.visitEnd();
        }
    }
}

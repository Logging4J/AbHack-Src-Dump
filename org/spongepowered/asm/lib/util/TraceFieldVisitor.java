//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.util;

import org.spongepowered.asm.lib.*;

public final class TraceFieldVisitor extends FieldVisitor
{
    public final Printer p;
    
    public TraceFieldVisitor(final Printer printer) {
        this(null, printer);
    }
    
    public TraceFieldVisitor(final FieldVisitor fieldVisitor, final Printer p2) {
        super(327680, fieldVisitor);
        this.p = p2;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        return (AnnotationVisitor)new TraceAnnotationVisitor((this.fv == null) ? null : this.fv.visitAnnotation(s, b), this.p.visitFieldAnnotation(s, b));
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        return (AnnotationVisitor)new TraceAnnotationVisitor((this.fv == null) ? null : this.fv.visitTypeAnnotation(n, typePath, s, b), this.p.visitFieldTypeAnnotation(n, typePath, s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.p.visitFieldAttribute(attribute);
        super.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.p.visitFieldEnd();
        super.visitEnd();
    }
}

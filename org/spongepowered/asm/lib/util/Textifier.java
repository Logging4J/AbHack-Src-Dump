//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.util;

import java.io.*;
import org.spongepowered.asm.lib.signature.*;
import java.util.*;
import org.spongepowered.asm.lib.*;

public class Textifier extends Printer
{
    public static final int INTERNAL_NAME = 0;
    public static final int FIELD_DESCRIPTOR = 1;
    public static final int FIELD_SIGNATURE = 2;
    public static final int METHOD_DESCRIPTOR = 3;
    public static final int METHOD_SIGNATURE = 4;
    public static final int CLASS_SIGNATURE = 5;
    public static final int TYPE_DECLARATION = 6;
    public static final int CLASS_DECLARATION = 7;
    public static final int PARAMETERS_DECLARATION = 8;
    public static final int HANDLE_DESCRIPTOR = 9;
    protected String tab;
    protected String tab2;
    protected String tab3;
    protected String ltab;
    protected Map<Label, String> labelNames;
    private int access;
    private int valueNumber;
    
    public Textifier() {
        this(327680);
        if (this.getClass() != Textifier.class) {
            throw new IllegalStateException();
        }
    }
    
    protected Textifier(final int n) {
        super(n);
        this.tab = "  ";
        this.tab2 = "    ";
        this.tab3 = "      ";
        this.ltab = "   ";
        this.valueNumber = 0;
    }
    
    public static void main(final String[] array) throws Exception {
        int n = 0;
        int n2 = 2;
        int n3 = 1;
        if (array.length < 1 || array.length > 2) {
            n3 = 0;
        }
        if (n3 != 0 && "-debug".equals(array[0])) {
            n = 1;
            n2 = 0;
            if (array.length != 2) {
                n3 = 0;
            }
        }
        if (n3 == 0) {
            System.err.println("Prints a disassembled view of the given class.");
            System.err.println("Usage: Textifier [-debug] <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader;
        if (array[n].endsWith(".class") || array[n].indexOf(92) > -1 || array[n].indexOf(47) > -1) {
            classReader = new ClassReader((InputStream)new FileInputStream(array[n]));
        }
        else {
            classReader = new ClassReader(array[n]);
        }
        classReader.accept((ClassVisitor)new TraceClassVisitor(new PrintWriter(System.out)), n2);
    }
    
    public void visit(final int i, final int n, final String str, final String s, final String anObject, final String[] array) {
        this.access = n;
        final int j = i & 0xFFFF;
        final int k = i >>> 16;
        this.buf.setLength(0);
        this.buf.append("// class version ").append(j).append('.').append(k).append(" (").append(i).append(")\n");
        if ((n & 0x20000) != 0x0) {
            this.buf.append("// DEPRECATED\n");
        }
        this.buf.append("// access flags 0x").append(Integer.toHexString(n).toUpperCase()).append('\n');
        this.appendDescriptor(5, s);
        if (s != null) {
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(n);
            new SignatureReader(s).accept((SignatureVisitor)traceSignatureVisitor);
            this.buf.append("// declaration: ").append(str).append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.appendAccess(n & 0xFFFFFFDF);
        if ((n & 0x2000) != 0x0) {
            this.buf.append("@interface ");
        }
        else if ((n & 0x200) != 0x0) {
            this.buf.append("interface ");
        }
        else if ((n & 0x4000) == 0x0) {
            this.buf.append("class ");
        }
        this.appendDescriptor(0, str);
        if (anObject != null && !"java/lang/Object".equals(anObject)) {
            this.buf.append(" extends ");
            this.appendDescriptor(0, anObject);
            this.buf.append(' ');
        }
        if (array != null && array.length > 0) {
            this.buf.append(" implements ");
            for (int l = 0; l < array.length; ++l) {
                this.appendDescriptor(0, array[l]);
                this.buf.append(' ');
            }
        }
        this.buf.append(" {\n\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitSource(final String str, final String str2) {
        this.buf.setLength(0);
        if (str != null) {
            this.buf.append(this.tab).append("// compiled from: ").append(str).append('\n');
        }
        if (str2 != null) {
            this.buf.append(this.tab).append("// debug info: ").append(str2).append('\n');
        }
        if (this.buf.length() > 0) {
            this.text.add(this.buf.toString());
        }
    }
    
    public void visitOuterClass(final String s, final String str, final String s2) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("OUTERCLASS ");
        this.appendDescriptor(0, s);
        this.buf.append(' ');
        if (str != null) {
            this.buf.append(str).append(' ');
        }
        this.appendDescriptor(3, s2);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public Textifier visitClassAnnotation(final String s, final boolean b) {
        this.text.add("\n");
        return this.visitAnnotation(s, b);
    }
    
    public Printer visitClassTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        this.text.add("\n");
        return this.visitTypeAnnotation(n, typePath, s, b);
    }
    
    public void visitClassAttribute(final Attribute attribute) {
        this.text.add("\n");
        this.visitAttribute(attribute);
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("// access flags 0x");
        this.buf.append(Integer.toHexString(n & 0xFFFFFFDF).toUpperCase()).append('\n');
        this.buf.append(this.tab);
        this.appendAccess(n);
        this.buf.append("INNERCLASS ");
        this.appendDescriptor(0, s);
        this.buf.append(' ');
        this.appendDescriptor(0, s2);
        this.buf.append(' ');
        this.appendDescriptor(0, s3);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public Textifier visitField(final int i, final String str, final String s, final String s2, final Object o) {
        this.buf.setLength(0);
        this.buf.append('\n');
        if ((i & 0x20000) != 0x0) {
            this.buf.append(this.tab).append("// DEPRECATED\n");
        }
        this.buf.append(this.tab).append("// access flags 0x").append(Integer.toHexString(i).toUpperCase()).append('\n');
        if (s2 != null) {
            this.buf.append(this.tab);
            this.appendDescriptor(2, s2);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s2).acceptType((SignatureVisitor)traceSignatureVisitor);
            this.buf.append(this.tab).append("// declaration: ").append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.buf.append(this.tab);
        this.appendAccess(i);
        this.appendDescriptor(1, s);
        this.buf.append(' ').append(str);
        if (o != null) {
            this.buf.append(" = ");
            if (o instanceof String) {
                this.buf.append('\"').append(o).append('\"');
            }
            else {
                this.buf.append(o);
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        return textifier;
    }
    
    public Textifier visitMethod(final int i, final String s, final String s2, final String s3, final String[] array) {
        this.buf.setLength(0);
        this.buf.append('\n');
        if ((i & 0x20000) != 0x0) {
            this.buf.append(this.tab).append("// DEPRECATED\n");
        }
        this.buf.append(this.tab).append("// access flags 0x").append(Integer.toHexString(i).toUpperCase()).append('\n');
        if (s3 != null) {
            this.buf.append(this.tab);
            this.appendDescriptor(4, s3);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s3).accept((SignatureVisitor)traceSignatureVisitor);
            final String declaration = traceSignatureVisitor.getDeclaration();
            final String returnType = traceSignatureVisitor.getReturnType();
            final String exceptions = traceSignatureVisitor.getExceptions();
            this.buf.append(this.tab).append("// declaration: ").append(returnType).append(' ').append(s).append(declaration);
            if (exceptions != null) {
                this.buf.append(" throws ").append(exceptions);
            }
            this.buf.append('\n');
        }
        this.buf.append(this.tab);
        this.appendAccess(i & 0xFFFFFFBF);
        if ((i & 0x100) != 0x0) {
            this.buf.append("native ");
        }
        if ((i & 0x80) != 0x0) {
            this.buf.append("varargs ");
        }
        if ((i & 0x40) != 0x0) {
            this.buf.append("bridge ");
        }
        if ((this.access & 0x200) != 0x0 && (i & 0x400) == 0x0 && (i & 0x8) == 0x0) {
            this.buf.append("default ");
        }
        this.buf.append(s);
        this.appendDescriptor(3, s2);
        if (array != null && array.length > 0) {
            this.buf.append(" throws ");
            for (int j = 0; j < array.length; ++j) {
                this.appendDescriptor(0, array[j]);
                this.buf.append(' ');
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        return textifier;
    }
    
    public void visitClassEnd() {
        this.text.add("}\n");
    }
    
    public void visit(final String str, final Object o) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (str != null) {
            this.buf.append(str).append('=');
        }
        if (o instanceof String) {
            this.visitString((String)o);
        }
        else if (o instanceof Type) {
            this.visitType((Type)o);
        }
        else if (o instanceof Byte) {
            this.visitByte((byte)o);
        }
        else if (o instanceof Boolean) {
            this.visitBoolean((boolean)o);
        }
        else if (o instanceof Short) {
            this.visitShort((short)o);
        }
        else if (o instanceof Character) {
            this.visitChar((char)o);
        }
        else if (o instanceof Integer) {
            this.visitInt((int)o);
        }
        else if (o instanceof Float) {
            this.visitFloat((float)o);
        }
        else if (o instanceof Long) {
            this.visitLong((long)o);
        }
        else if (o instanceof Double) {
            this.visitDouble((double)o);
        }
        else if (o.getClass().isArray()) {
            this.buf.append('{');
            if (o instanceof byte[]) {
                final byte[] array = (byte[])o;
                for (int i = 0; i < array.length; ++i) {
                    this.appendComa(i);
                    this.visitByte(array[i]);
                }
            }
            else if (o instanceof boolean[]) {
                final boolean[] array2 = (boolean[])o;
                for (int j = 0; j < array2.length; ++j) {
                    this.appendComa(j);
                    this.visitBoolean(array2[j]);
                }
            }
            else if (o instanceof short[]) {
                final short[] array3 = (short[])o;
                for (int k = 0; k < array3.length; ++k) {
                    this.appendComa(k);
                    this.visitShort(array3[k]);
                }
            }
            else if (o instanceof char[]) {
                final char[] array4 = (char[])o;
                for (int l = 0; l < array4.length; ++l) {
                    this.appendComa(l);
                    this.visitChar(array4[l]);
                }
            }
            else if (o instanceof int[]) {
                final int[] array5 = (int[])o;
                for (int n = 0; n < array5.length; ++n) {
                    this.appendComa(n);
                    this.visitInt(array5[n]);
                }
            }
            else if (o instanceof long[]) {
                final long[] array6 = (long[])o;
                for (int n2 = 0; n2 < array6.length; ++n2) {
                    this.appendComa(n2);
                    this.visitLong(array6[n2]);
                }
            }
            else if (o instanceof float[]) {
                final float[] array7 = (float[])o;
                for (int n3 = 0; n3 < array7.length; ++n3) {
                    this.appendComa(n3);
                    this.visitFloat(array7[n3]);
                }
            }
            else if (o instanceof double[]) {
                final double[] array8 = (double[])o;
                for (int n4 = 0; n4 < array8.length; ++n4) {
                    this.appendComa(n4);
                    this.visitDouble(array8[n4]);
                }
            }
            this.buf.append('}');
        }
        this.text.add(this.buf.toString());
    }
    
    private void visitInt(final int i) {
        this.buf.append(i);
    }
    
    private void visitLong(final long lng) {
        this.buf.append(lng).append('L');
    }
    
    private void visitFloat(final float f) {
        this.buf.append(f).append('F');
    }
    
    private void visitDouble(final double d) {
        this.buf.append(d).append('D');
    }
    
    private void visitChar(final char i) {
        this.buf.append("(char)").append((int)i);
    }
    
    private void visitShort(final short i) {
        this.buf.append("(short)").append(i);
    }
    
    private void visitByte(final byte i) {
        this.buf.append("(byte)").append(i);
    }
    
    private void visitBoolean(final boolean b) {
        this.buf.append(b);
    }
    
    private void visitString(final String s) {
        appendString(this.buf, s);
    }
    
    private void visitType(final Type type) {
        this.buf.append(type.getClassName()).append(".class");
    }
    
    public void visitEnum(final String str, final String s, final String str2) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (str != null) {
            this.buf.append(str).append('=');
        }
        this.appendDescriptor(1, s);
        this.buf.append('.').append(str2);
        this.text.add(this.buf.toString());
    }
    
    public Textifier visitAnnotation(final String str, final String s) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (str != null) {
            this.buf.append(str).append('=');
        }
        this.buf.append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.text.add(")");
        return textifier;
    }
    
    public Textifier visitArray(final String str) {
        this.buf.setLength(0);
        this.appendComa(this.valueNumber++);
        if (str != null) {
            this.buf.append(str).append('=');
        }
        this.buf.append('{');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.text.add("}");
        return textifier;
    }
    
    public void visitAnnotationEnd() {
    }
    
    public Textifier visitFieldAnnotation(final String s, final boolean b) {
        return this.visitAnnotation(s, b);
    }
    
    public Printer visitFieldTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        return this.visitTypeAnnotation(n, typePath, s, b);
    }
    
    public void visitFieldAttribute(final Attribute attribute) {
        this.visitAttribute(attribute);
    }
    
    public void visitFieldEnd() {
    }
    
    public void visitParameter(final String s, final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("// parameter ");
        this.appendAccess(n);
        this.buf.append(' ').append((s == null) ? "<no name>" : s).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public Textifier visitAnnotationDefault() {
        this.text.add(this.tab2 + "default=");
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.text.add("\n");
        return textifier;
    }
    
    public Textifier visitMethodAnnotation(final String s, final boolean b) {
        return this.visitAnnotation(s, b);
    }
    
    public Printer visitMethodTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        return this.visitTypeAnnotation(n, typePath, s, b);
    }
    
    public Textifier visitParameterAnnotation(final int i, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.text.add(b ? ") // parameter " : ") // invisible, parameter ");
        this.text.add(i);
        this.text.add("\n");
        return textifier;
    }
    
    public void visitMethodAttribute(final Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("ATTRIBUTE ");
        this.appendDescriptor(-1, attribute.type);
        if (attribute instanceof Textifiable) {
            ((Textifiable)attribute).textify(this.buf, (Map)this.labelNames);
        }
        else {
            this.buf.append(" : unknown\n");
        }
        this.text.add(this.buf.toString());
    }
    
    public void visitCode() {
    }
    
    public void visitFrame(final int n, final int i, final Object[] array, final int n2, final Object[] array2) {
        this.buf.setLength(0);
        this.buf.append(this.ltab);
        this.buf.append("FRAME ");
        switch (n) {
            case -1:
            case 0: {
                this.buf.append("FULL [");
                this.appendFrameTypes(i, array);
                this.buf.append("] [");
                this.appendFrameTypes(n2, array2);
                this.buf.append(']');
                break;
            }
            case 1: {
                this.buf.append("APPEND [");
                this.appendFrameTypes(i, array);
                this.buf.append(']');
                break;
            }
            case 2: {
                this.buf.append("CHOP ").append(i);
                break;
            }
            case 3: {
                this.buf.append("SAME");
                break;
            }
            case 4: {
                this.buf.append("SAME1 ");
                this.appendFrameTypes(1, array2);
                break;
            }
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitInsn(final int n) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitIntInsn(final int n, final int i) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ').append((n == 188) ? Textifier.TYPES[i] : Integer.toString(i)).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitVarInsn(final int n, final int i) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ').append(i).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitTypeInsn(final int n, final String s) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitFieldInsn(final int n, final String s, final String str, final String s2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('.').append(str).append(" : ");
        this.appendDescriptor(1, s2);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    @Deprecated
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3) {
        if (this.api >= 327680) {
            super.visitMethodInsn(n, s, s2, s3);
            return;
        }
        this.doVisitMethodInsn(n, s, s2, s3, n == 185);
    }
    
    public void visitMethodInsn(final int n, final String s, final String s2, final String s3, final boolean b) {
        if (this.api < 327680) {
            super.visitMethodInsn(n, s, s2, s3, b);
            return;
        }
        this.doVisitMethodInsn(n, s, s2, s3, b);
    }
    
    private void doVisitMethodInsn(final int n, final String s, final String str, final String s2, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('.').append(str).append(' ');
        this.appendDescriptor(3, s2);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitInvokeDynamicInsn(final String str, final String s, final Handle handle, final Object... array) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("INVOKEDYNAMIC").append(' ');
        this.buf.append(str);
        this.appendDescriptor(3, s);
        this.buf.append(" [");
        this.buf.append('\n');
        this.buf.append(this.tab3);
        this.appendHandle(handle);
        this.buf.append('\n');
        this.buf.append(this.tab3).append("// arguments:");
        if (array.length == 0) {
            this.buf.append(" none");
        }
        else {
            this.buf.append('\n');
            for (int i = 0; i < array.length; ++i) {
                this.buf.append(this.tab3);
                final Object obj = array[i];
                if (obj instanceof String) {
                    Printer.appendString(this.buf, (String)obj);
                }
                else if (obj instanceof Type) {
                    final Type type = (Type)obj;
                    if (type.getSort() == 11) {
                        this.appendDescriptor(3, type.getDescriptor());
                    }
                    else {
                        this.buf.append(type.getDescriptor()).append(".class");
                    }
                }
                else if (obj instanceof Handle) {
                    this.appendHandle((Handle)obj);
                }
                else {
                    this.buf.append(obj);
                }
                this.buf.append(", \n");
            }
            this.buf.setLength(this.buf.length() - 3);
        }
        this.buf.append('\n');
        this.buf.append(this.tab2).append("]\n");
        this.text.add(this.buf.toString());
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append(Textifier.OPCODES[n]).append(' ');
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitLabel(final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.ltab);
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitLdcInsn(final Object obj) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LDC ");
        if (obj instanceof String) {
            Printer.appendString(this.buf, (String)obj);
        }
        else if (obj instanceof Type) {
            this.buf.append(((Type)obj).getDescriptor()).append(".class");
        }
        else {
            this.buf.append(obj);
        }
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitIincInsn(final int i, final int j) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("IINC ").append(i).append(' ').append(j).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitTableSwitchInsn(final int n, final int n2, final Label label, final Label... array) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("TABLESWITCH\n");
        for (int i = 0; i < array.length; ++i) {
            this.buf.append(this.tab3).append(n + i).append(": ");
            this.appendLabel(array[i]);
            this.buf.append('\n');
        }
        this.buf.append(this.tab3).append("default: ");
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LOOKUPSWITCH\n");
        for (int i = 0; i < array2.length; ++i) {
            this.buf.append(this.tab3).append(array[i]).append(": ");
            this.appendLabel(array2[i]);
            this.buf.append('\n');
        }
        this.buf.append(this.tab3).append("default: ");
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitMultiANewArrayInsn(final String s, final int i) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MULTIANEWARRAY ");
        this.appendDescriptor(1, s);
        this.buf.append(' ').append(i).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public Printer visitInsnAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        return this.visitTypeAnnotation(n, typePath, s, b);
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("TRYCATCHBLOCK ");
        this.appendLabel(label);
        this.buf.append(' ');
        this.appendLabel(label2);
        this.buf.append(' ');
        this.appendLabel(label3);
        this.buf.append(' ');
        this.appendDescriptor(0, s);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public Printer visitTryCatchAnnotation(final int n, final TypePath obj, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("TRYCATCHBLOCK @");
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.buf.setLength(0);
        this.buf.append(") : ");
        this.appendTypeReference(n);
        this.buf.append(", ").append(obj);
        this.buf.append(b ? "\n" : " // invisible\n");
        this.text.add(this.buf.toString());
        return textifier;
    }
    
    public void visitLocalVariable(final String str, final String s, final String s2, final Label label, final Label label2, final int i) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LOCALVARIABLE ").append(str).append(' ');
        this.appendDescriptor(1, s);
        this.buf.append(' ');
        this.appendLabel(label);
        this.buf.append(' ');
        this.appendLabel(label2);
        this.buf.append(' ').append(i).append('\n');
        if (s2 != null) {
            this.buf.append(this.tab2);
            this.appendDescriptor(2, s2);
            final TraceSignatureVisitor traceSignatureVisitor = new TraceSignatureVisitor(0);
            new SignatureReader(s2).acceptType((SignatureVisitor)traceSignatureVisitor);
            this.buf.append(this.tab2).append("// declaration: ").append(traceSignatureVisitor.getDeclaration()).append('\n');
        }
        this.text.add(this.buf.toString());
    }
    
    public Printer visitLocalVariableAnnotation(final int n, final TypePath obj, final Label[] array, final Label[] array2, final int[] array3, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LOCALVARIABLE @");
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.buf.setLength(0);
        this.buf.append(") : ");
        this.appendTypeReference(n);
        this.buf.append(", ").append(obj);
        for (int i = 0; i < array.length; ++i) {
            this.buf.append(" [ ");
            this.appendLabel(array[i]);
            this.buf.append(" - ");
            this.appendLabel(array2[i]);
            this.buf.append(" - ").append(array3[i]).append(" ]");
        }
        this.buf.append(b ? "\n" : " // invisible\n");
        this.text.add(this.buf.toString());
        return textifier;
    }
    
    public void visitLineNumber(final int i, final Label label) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("LINENUMBER ").append(i).append(' ');
        this.appendLabel(label);
        this.buf.append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitMaxs(final int i, final int j) {
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MAXSTACK = ").append(i).append('\n');
        this.text.add(this.buf.toString());
        this.buf.setLength(0);
        this.buf.append(this.tab2).append("MAXLOCALS = ").append(j).append('\n');
        this.text.add(this.buf.toString());
    }
    
    public void visitMethodEnd() {
    }
    
    public Textifier visitAnnotation(final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.text.add(b ? ")\n" : ") // invisible\n");
        return textifier;
    }
    
    public Textifier visitTypeAnnotation(final int n, final TypePath obj, final String s, final boolean b) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append('@');
        this.appendDescriptor(1, s);
        this.buf.append('(');
        this.text.add(this.buf.toString());
        final Textifier textifier = this.createTextifier();
        this.text.add(textifier.getText());
        this.buf.setLength(0);
        this.buf.append(") : ");
        this.appendTypeReference(n);
        this.buf.append(", ").append(obj);
        this.buf.append(b ? "\n" : " // invisible\n");
        this.text.add(this.buf.toString());
        return textifier;
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.buf.setLength(0);
        this.buf.append(this.tab).append("ATTRIBUTE ");
        this.appendDescriptor(-1, attribute.type);
        if (attribute instanceof Textifiable) {
            ((Textifiable)attribute).textify(this.buf, (Map)null);
        }
        else {
            this.buf.append(" : unknown\n");
        }
        this.text.add(this.buf.toString());
    }
    
    protected Textifier createTextifier() {
        return new Textifier();
    }
    
    protected void appendDescriptor(final int n, final String s) {
        if (n == 5 || n == 2 || n == 4) {
            if (s != null) {
                this.buf.append("// signature ").append(s).append('\n');
            }
        }
        else {
            this.buf.append(s);
        }
    }
    
    protected void appendLabel(final Label label) {
        if (this.labelNames == null) {
            this.labelNames = new HashMap<Label, String>();
        }
        String string = this.labelNames.get(label);
        if (string == null) {
            string = "L" + this.labelNames.size();
            this.labelNames.put(label, string);
        }
        this.buf.append(string);
    }
    
    protected void appendHandle(final Handle handle) {
        final int tag = handle.getTag();
        this.buf.append("// handle kind 0x").append(Integer.toHexString(tag)).append(" : ");
        boolean b = false;
        switch (tag) {
            case 1: {
                this.buf.append("GETFIELD");
                break;
            }
            case 2: {
                this.buf.append("GETSTATIC");
                break;
            }
            case 3: {
                this.buf.append("PUTFIELD");
                break;
            }
            case 4: {
                this.buf.append("PUTSTATIC");
                break;
            }
            case 9: {
                this.buf.append("INVOKEINTERFACE");
                b = true;
                break;
            }
            case 7: {
                this.buf.append("INVOKESPECIAL");
                b = true;
                break;
            }
            case 6: {
                this.buf.append("INVOKESTATIC");
                b = true;
                break;
            }
            case 5: {
                this.buf.append("INVOKEVIRTUAL");
                b = true;
                break;
            }
            case 8: {
                this.buf.append("NEWINVOKESPECIAL");
                b = true;
                break;
            }
        }
        this.buf.append('\n');
        this.buf.append(this.tab3);
        this.appendDescriptor(0, handle.getOwner());
        this.buf.append('.');
        this.buf.append(handle.getName());
        if (!b) {
            this.buf.append('(');
        }
        this.appendDescriptor(9, handle.getDesc());
        if (!b) {
            this.buf.append(')');
        }
    }
    
    private void appendAccess(final int n) {
        if ((n & 0x1) != 0x0) {
            this.buf.append("public ");
        }
        if ((n & 0x2) != 0x0) {
            this.buf.append("private ");
        }
        if ((n & 0x4) != 0x0) {
            this.buf.append("protected ");
        }
        if ((n & 0x10) != 0x0) {
            this.buf.append("final ");
        }
        if ((n & 0x8) != 0x0) {
            this.buf.append("static ");
        }
        if ((n & 0x20) != 0x0) {
            this.buf.append("synchronized ");
        }
        if ((n & 0x40) != 0x0) {
            this.buf.append("volatile ");
        }
        if ((n & 0x80) != 0x0) {
            this.buf.append("transient ");
        }
        if ((n & 0x400) != 0x0) {
            this.buf.append("abstract ");
        }
        if ((n & 0x800) != 0x0) {
            this.buf.append("strictfp ");
        }
        if ((n & 0x1000) != 0x0) {
            this.buf.append("synthetic ");
        }
        if ((n & 0x8000) != 0x0) {
            this.buf.append("mandated ");
        }
        if ((n & 0x4000) != 0x0) {
            this.buf.append("enum ");
        }
    }
    
    private void appendComa(final int n) {
        if (n != 0) {
            this.buf.append(", ");
        }
    }
    
    private void appendTypeReference(final int n) {
        final TypeReference typeReference = new TypeReference(n);
        switch (typeReference.getSort()) {
            case 0: {
                this.buf.append("CLASS_TYPE_PARAMETER ").append(typeReference.getTypeParameterIndex());
                break;
            }
            case 1: {
                this.buf.append("METHOD_TYPE_PARAMETER ").append(typeReference.getTypeParameterIndex());
                break;
            }
            case 16: {
                this.buf.append("CLASS_EXTENDS ").append(typeReference.getSuperTypeIndex());
                break;
            }
            case 17: {
                this.buf.append("CLASS_TYPE_PARAMETER_BOUND ").append(typeReference.getTypeParameterIndex()).append(", ").append(typeReference.getTypeParameterBoundIndex());
                break;
            }
            case 18: {
                this.buf.append("METHOD_TYPE_PARAMETER_BOUND ").append(typeReference.getTypeParameterIndex()).append(", ").append(typeReference.getTypeParameterBoundIndex());
                break;
            }
            case 19: {
                this.buf.append("FIELD");
                break;
            }
            case 20: {
                this.buf.append("METHOD_RETURN");
                break;
            }
            case 21: {
                this.buf.append("METHOD_RECEIVER");
                break;
            }
            case 22: {
                this.buf.append("METHOD_FORMAL_PARAMETER ").append(typeReference.getFormalParameterIndex());
                break;
            }
            case 23: {
                this.buf.append("THROWS ").append(typeReference.getExceptionIndex());
                break;
            }
            case 64: {
                this.buf.append("LOCAL_VARIABLE");
                break;
            }
            case 65: {
                this.buf.append("RESOURCE_VARIABLE");
                break;
            }
            case 66: {
                this.buf.append("EXCEPTION_PARAMETER ").append(typeReference.getTryCatchBlockIndex());
                break;
            }
            case 67: {
                this.buf.append("INSTANCEOF");
                break;
            }
            case 68: {
                this.buf.append("NEW");
                break;
            }
            case 69: {
                this.buf.append("CONSTRUCTOR_REFERENCE");
                break;
            }
            case 70: {
                this.buf.append("METHOD_REFERENCE");
                break;
            }
            case 71: {
                this.buf.append("CAST ").append(typeReference.getTypeArgumentIndex());
                break;
            }
            case 72: {
                this.buf.append("CONSTRUCTOR_INVOCATION_TYPE_ARGUMENT ").append(typeReference.getTypeArgumentIndex());
                break;
            }
            case 73: {
                this.buf.append("METHOD_INVOCATION_TYPE_ARGUMENT ").append(typeReference.getTypeArgumentIndex());
                break;
            }
            case 74: {
                this.buf.append("CONSTRUCTOR_REFERENCE_TYPE_ARGUMENT ").append(typeReference.getTypeArgumentIndex());
                break;
            }
            case 75: {
                this.buf.append("METHOD_REFERENCE_TYPE_ARGUMENT ").append(typeReference.getTypeArgumentIndex());
                break;
            }
        }
    }
    
    private void appendFrameTypes(final int n, final Object[] array) {
        for (int i = 0; i < n; ++i) {
            if (i > 0) {
                this.buf.append(' ');
            }
            if (array[i] instanceof String) {
                final String s = (String)array[i];
                if (s.startsWith("[")) {
                    this.appendDescriptor(1, s);
                }
                else {
                    this.appendDescriptor(0, s);
                }
            }
            else if (array[i] instanceof Integer) {
                switch ((int)array[i]) {
                    case 0: {
                        this.appendDescriptor(1, "T");
                        break;
                    }
                    case 1: {
                        this.appendDescriptor(1, "I");
                        break;
                    }
                    case 2: {
                        this.appendDescriptor(1, "F");
                        break;
                    }
                    case 3: {
                        this.appendDescriptor(1, "D");
                        break;
                    }
                    case 4: {
                        this.appendDescriptor(1, "J");
                        break;
                    }
                    case 5: {
                        this.appendDescriptor(1, "N");
                        break;
                    }
                    case 6: {
                        this.appendDescriptor(1, "U");
                        break;
                    }
                }
            }
            else {
                this.appendLabel((Label)array[i]);
            }
        }
    }
}

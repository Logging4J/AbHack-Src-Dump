//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.util;

import java.lang.reflect.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.tree.analysis.*;
import java.io.*;
import java.util.*;
import org.spongepowered.asm.lib.*;

public class CheckMethodAdapter extends MethodVisitor
{
    public int version;
    private int access;
    private boolean startCode;
    private boolean endCode;
    private boolean endMethod;
    private int insnCount;
    private final Map<Label, Integer> labels;
    private Set<Label> usedLabels;
    private int expandedFrames;
    private int compressedFrames;
    private int lastFrame;
    private List<Label> handlers;
    private static final int[] TYPE;
    private static Field labelStatusField;
    
    public CheckMethodAdapter(final MethodVisitor methodVisitor) {
        this(methodVisitor, new HashMap<Label, Integer>());
    }
    
    public CheckMethodAdapter(final MethodVisitor methodVisitor, final Map<Label, Integer> map) {
        this(327680, methodVisitor, map);
        if (this.getClass() != CheckMethodAdapter.class) {
            throw new IllegalStateException();
        }
    }
    
    protected CheckMethodAdapter(final int n, final MethodVisitor methodVisitor, final Map<Label, Integer> labels) {
        super(n, methodVisitor);
        this.lastFrame = -1;
        this.labels = labels;
        this.usedLabels = new HashSet<Label>();
        this.handlers = new ArrayList<Label>();
    }
    
    public CheckMethodAdapter(final int access, final String s, final String s2, final MethodVisitor methodVisitor, final Map<Label, Integer> map) {
        this((MethodVisitor)new MethodNode(327680, access, s, s2, null, null) {
            public void visitEnd() {
                final Analyzer analyzer = new Analyzer((Interpreter)new BasicVerifier());
                try {
                    analyzer.analyze("dummy", (MethodNode)this);
                }
                catch (Exception ex) {
                    if (ex instanceof IndexOutOfBoundsException && this.maxLocals == 0 && this.maxStack == 0) {
                        throw new RuntimeException("Data flow checking option requires valid, non zero maxLocals and maxStack values.");
                    }
                    ex.printStackTrace();
                    final StringWriter out = new StringWriter();
                    final PrintWriter printWriter = new PrintWriter(out, true);
                    CheckClassAdapter.printAnalyzerResult((MethodNode)this, analyzer, printWriter);
                    printWriter.close();
                    throw new RuntimeException(ex.getMessage() + ' ' + out.toString());
                }
                this.accept(methodVisitor);
            }
        }, map);
        this.access = access;
    }
    
    public void visitParameter(final String s, final int n) {
        if (s != null) {
            checkUnqualifiedName(this.version, s, "name");
        }
        CheckClassAdapter.checkAccess(n, 36880);
        super.visitParameter(s, n);
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.checkEndMethod();
        checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitAnnotation(s, b));
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        this.checkEndMethod();
        final int i = n >>> 24;
        if (i != 1 && i != 18 && i != 20 && i != 21 && i != 22 && i != 23) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i));
        }
        CheckClassAdapter.checkTypeRefAndPath(n, typePath);
        checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitTypeAnnotation(n, typePath, s, b));
    }
    
    public AnnotationVisitor visitAnnotationDefault() {
        this.checkEndMethod();
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitAnnotationDefault(), false);
    }
    
    public AnnotationVisitor visitParameterAnnotation(final int n, final String s, final boolean b) {
        this.checkEndMethod();
        checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitParameterAnnotation(n, s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.checkEndMethod();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        super.visitAttribute(attribute);
    }
    
    public void visitCode() {
        if ((this.access & 0x400) != 0x0) {
            throw new RuntimeException("Abstract methods cannot have code");
        }
        this.startCode = true;
        super.visitCode();
    }
    
    public void visitFrame(final int i, final int j, final Object[] array, final int k, final Object[] array2) {
        if (this.insnCount == this.lastFrame) {
            throw new IllegalStateException("At most one frame can be visited at a given code location.");
        }
        this.lastFrame = this.insnCount;
        int n = 0;
        int n2 = 0;
        switch (i) {
            case -1:
            case 0: {
                n = Integer.MAX_VALUE;
                n2 = Integer.MAX_VALUE;
                break;
            }
            case 3: {
                n = 0;
                n2 = 0;
                break;
            }
            case 4: {
                n = 0;
                n2 = 1;
                break;
            }
            case 1:
            case 2: {
                n = 3;
                n2 = 0;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid frame type " + i);
            }
        }
        if (j > n) {
            throw new IllegalArgumentException("Invalid nLocal=" + j + " for frame type " + i);
        }
        if (k > n2) {
            throw new IllegalArgumentException("Invalid nStack=" + k + " for frame type " + i);
        }
        if (i != 2) {
            if (j > 0 && (array == null || array.length < j)) {
                throw new IllegalArgumentException("Array local[] is shorter than nLocal");
            }
            for (int l = 0; l < j; ++l) {
                this.checkFrameValue(array[l]);
            }
        }
        if (k > 0 && (array2 == null || array2.length < k)) {
            throw new IllegalArgumentException("Array stack[] is shorter than nStack");
        }
        for (int n3 = 0; n3 < k; ++n3) {
            this.checkFrameValue(array2[n3]);
        }
        if (i == -1) {
            ++this.expandedFrames;
        }
        else {
            ++this.compressedFrames;
        }
        if (this.expandedFrames > 0 && this.compressedFrames > 0) {
            throw new RuntimeException("Expanded and compressed frames must not be mixed.");
        }
        super.visitFrame(i, j, array, k, array2);
    }
    
    public void visitInsn(final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 0);
        super.visitInsn(n);
        ++this.insnCount;
    }
    
    public void visitIntInsn(final int n, final int i) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 1);
        switch (n) {
            case 16: {
                checkSignedByte(i, "Invalid operand");
                break;
            }
            case 17: {
                checkSignedShort(i, "Invalid operand");
                break;
            }
            default: {
                if (i < 4 || i > 11) {
                    throw new IllegalArgumentException("Invalid operand (must be an array type code T_...): " + i);
                }
                break;
            }
        }
        super.visitIntInsn(n, i);
        ++this.insnCount;
    }
    
    public void visitVarInsn(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 2);
        checkUnsignedShort(n2, "Invalid variable index");
        super.visitVarInsn(n, n2);
        ++this.insnCount;
    }
    
    public void visitTypeInsn(final int n, final String str) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 3);
        checkInternalName(str, "type");
        if (n == 187 && str.charAt(0) == '[') {
            throw new IllegalArgumentException("NEW cannot be used to create arrays: " + str);
        }
        super.visitTypeInsn(n, str);
        ++this.insnCount;
    }
    
    public void visitFieldInsn(final int n, final String s, final String s2, final String s3) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 4);
        checkInternalName(s, "owner");
        checkUnqualifiedName(this.version, s2, "name");
        checkDesc(s3, false);
        super.visitFieldInsn(n, s, s2, s3);
        ++this.insnCount;
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
    
    private void doVisitMethodInsn(final int n, final String s, final String anObject, final String s2, final boolean b) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 5);
        if (n != 183 || !"<init>".equals(anObject)) {
            checkMethodIdentifier(this.version, anObject, "name");
        }
        checkInternalName(s, "owner");
        checkMethodDesc(s2);
        if (n == 182 && b) {
            throw new IllegalArgumentException("INVOKEVIRTUAL can't be used with interfaces");
        }
        if (n == 185 && !b) {
            throw new IllegalArgumentException("INVOKEINTERFACE can't be used with classes");
        }
        if (n == 183 && b && (this.version & 0xFFFF) < 52) {
            throw new IllegalArgumentException("INVOKESPECIAL can't be used with interfaces prior to Java 8");
        }
        if (this.mv != null) {
            this.mv.visitMethodInsn(n, s, anObject, s2, b);
        }
        ++this.insnCount;
    }
    
    public void visitInvokeDynamicInsn(final String s, final String s2, final Handle handle, final Object... array) {
        this.checkStartCode();
        this.checkEndCode();
        checkMethodIdentifier(this.version, s, "name");
        checkMethodDesc(s2);
        if (handle.getTag() != 6 && handle.getTag() != 8) {
            throw new IllegalArgumentException("invalid handle tag " + handle.getTag());
        }
        for (int i = 0; i < array.length; ++i) {
            this.checkLDCConstant(array[i]);
        }
        super.visitInvokeDynamicInsn(s, s2, handle, array);
        ++this.insnCount;
    }
    
    public void visitJumpInsn(final int n, final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        checkOpcode(n, 6);
        this.checkLabel(label, false, "label");
        checkNonDebugLabel(label);
        super.visitJumpInsn(n, label);
        this.usedLabels.add(label);
        ++this.insnCount;
    }
    
    public void visitLabel(final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        this.checkLabel(label, false, "label");
        if (this.labels.get(label) != null) {
            throw new IllegalArgumentException("Already visited label");
        }
        this.labels.put(label, this.insnCount);
        super.visitLabel(label);
    }
    
    public void visitLdcInsn(final Object o) {
        this.checkStartCode();
        this.checkEndCode();
        this.checkLDCConstant(o);
        super.visitLdcInsn(o);
        ++this.insnCount;
    }
    
    public void visitIincInsn(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnsignedShort(n, "Invalid variable index");
        checkSignedShort(n2, "Invalid increment");
        super.visitIincInsn(n, n2);
        ++this.insnCount;
    }
    
    public void visitTableSwitchInsn(final int i, final int j, final Label label, final Label... array) {
        this.checkStartCode();
        this.checkEndCode();
        if (j < i) {
            throw new IllegalArgumentException("Max = " + j + " must be greater than or equal to min = " + i);
        }
        this.checkLabel(label, false, "default label");
        checkNonDebugLabel(label);
        if (array == null || array.length != j - i + 1) {
            throw new IllegalArgumentException("There must be max - min + 1 labels");
        }
        for (int k = 0; k < array.length; ++k) {
            this.checkLabel(array[k], false, "label at index " + k);
            checkNonDebugLabel(array[k]);
        }
        super.visitTableSwitchInsn(i, j, label, array);
        for (int l = 0; l < array.length; ++l) {
            this.usedLabels.add(array[l]);
        }
        ++this.insnCount;
    }
    
    public void visitLookupSwitchInsn(final Label label, final int[] array, final Label[] array2) {
        this.checkEndCode();
        this.checkStartCode();
        this.checkLabel(label, false, "default label");
        checkNonDebugLabel(label);
        if (array == null || array2 == null || array.length != array2.length) {
            throw new IllegalArgumentException("There must be the same number of keys and labels");
        }
        for (int i = 0; i < array2.length; ++i) {
            this.checkLabel(array2[i], false, "label at index " + i);
            checkNonDebugLabel(array2[i]);
        }
        super.visitLookupSwitchInsn(label, array, array2);
        this.usedLabels.add(label);
        for (int j = 0; j < array2.length; ++j) {
            this.usedLabels.add(array2[j]);
        }
        ++this.insnCount;
    }
    
    public void visitMultiANewArrayInsn(final String str, final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkDesc(str, false);
        if (str.charAt(0) != '[') {
            throw new IllegalArgumentException("Invalid descriptor (must be an array type descriptor): " + str);
        }
        if (n < 1) {
            throw new IllegalArgumentException("Invalid dimensions (must be greater than 0): " + n);
        }
        if (n > str.lastIndexOf(91) + 1) {
            throw new IllegalArgumentException("Invalid dimensions (must not be greater than dims(desc)): " + n);
        }
        super.visitMultiANewArrayInsn(str, n);
        ++this.insnCount;
    }
    
    public AnnotationVisitor visitInsnAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        this.checkStartCode();
        this.checkEndCode();
        final int i = n >>> 24;
        if (i != 67 && i != 68 && i != 69 && i != 70 && i != 71 && i != 72 && i != 73 && i != 74 && i != 75) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i));
        }
        CheckClassAdapter.checkTypeRefAndPath(n, typePath);
        checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitInsnAnnotation(n, typePath, s, b));
    }
    
    public void visitTryCatchBlock(final Label label, final Label label2, final Label label3, final String s) {
        this.checkStartCode();
        this.checkEndCode();
        this.checkLabel(label, false, "start label");
        this.checkLabel(label2, false, "end label");
        this.checkLabel(label3, false, "handler label");
        checkNonDebugLabel(label);
        checkNonDebugLabel(label2);
        checkNonDebugLabel(label3);
        if (this.labels.get(label) != null || this.labels.get(label2) != null || this.labels.get(label3) != null) {
            throw new IllegalStateException("Try catch blocks must be visited before their labels");
        }
        if (s != null) {
            checkInternalName(s, "type");
        }
        super.visitTryCatchBlock(label, label2, label3, s);
        this.handlers.add(label);
        this.handlers.add(label2);
    }
    
    public AnnotationVisitor visitTryCatchAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        this.checkStartCode();
        this.checkEndCode();
        final int i = n >>> 24;
        if (i != 66) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i));
        }
        CheckClassAdapter.checkTypeRefAndPath(n, typePath);
        checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitTryCatchAnnotation(n, typePath, s, b));
    }
    
    public void visitLocalVariable(final String s, final String s2, final String s3, final Label label, final Label label2, final int n) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnqualifiedName(this.version, s, "name");
        checkDesc(s2, false);
        this.checkLabel(label, true, "start label");
        this.checkLabel(label2, true, "end label");
        checkUnsignedShort(n, "Invalid variable index");
        if (this.labels.get(label2) < this.labels.get(label)) {
            throw new IllegalArgumentException("Invalid start and end labels (end must be greater than start)");
        }
        super.visitLocalVariable(s, s2, s3, label, label2, n);
    }
    
    public AnnotationVisitor visitLocalVariableAnnotation(final int n, final TypePath typePath, final Label[] array, final Label[] array2, final int[] array3, final String s, final boolean b) {
        this.checkStartCode();
        this.checkEndCode();
        final int i = n >>> 24;
        if (i != 64 && i != 65) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i));
        }
        CheckClassAdapter.checkTypeRefAndPath(n, typePath);
        checkDesc(s, false);
        if (array == null || array2 == null || array3 == null || array2.length != array.length || array3.length != array.length) {
            throw new IllegalArgumentException("Invalid start, end and index arrays (must be non null and of identical length");
        }
        for (int j = 0; j < array.length; ++j) {
            this.checkLabel(array[j], true, "start label");
            this.checkLabel(array2[j], true, "end label");
            checkUnsignedShort(array3[j], "Invalid variable index");
            if (this.labels.get(array2[j]) < this.labels.get(array[j])) {
                throw new IllegalArgumentException("Invalid start and end labels (end must be greater than start)");
            }
        }
        return super.visitLocalVariableAnnotation(n, typePath, array, array2, array3, s, b);
    }
    
    public void visitLineNumber(final int n, final Label label) {
        this.checkStartCode();
        this.checkEndCode();
        checkUnsignedShort(n, "Invalid line number");
        this.checkLabel(label, true, "start label");
        super.visitLineNumber(n, label);
    }
    
    public void visitMaxs(final int n, final int n2) {
        this.checkStartCode();
        this.checkEndCode();
        this.endCode = true;
        final Iterator<Label> iterator = this.usedLabels.iterator();
        while (iterator.hasNext()) {
            if (this.labels.get(iterator.next()) == null) {
                throw new IllegalStateException("Undefined label used");
            }
        }
        int i = 0;
        while (i < this.handlers.size()) {
            final Integer n3 = this.labels.get(this.handlers.get(i++));
            final Integer n4 = this.labels.get(this.handlers.get(i++));
            if (n3 == null || n4 == null) {
                throw new IllegalStateException("Undefined try catch block labels");
            }
            if (n4 <= n3) {
                throw new IllegalStateException("Emty try catch block handler range");
            }
        }
        checkUnsignedShort(n, "Invalid max stack");
        checkUnsignedShort(n2, "Invalid max locals");
        super.visitMaxs(n, n2);
    }
    
    public void visitEnd() {
        this.checkEndMethod();
        this.endMethod = true;
        super.visitEnd();
    }
    
    void checkStartCode() {
        if (!this.startCode) {
            throw new IllegalStateException("Cannot visit instructions before visitCode has been called.");
        }
    }
    
    void checkEndCode() {
        if (this.endCode) {
            throw new IllegalStateException("Cannot visit instructions after visitMaxs has been called.");
        }
    }
    
    void checkEndMethod() {
        if (this.endMethod) {
            throw new IllegalStateException("Cannot visit elements after visitEnd has been called.");
        }
    }
    
    void checkFrameValue(final Object obj) {
        if (obj == Opcodes.TOP || obj == Opcodes.INTEGER || obj == Opcodes.FLOAT || obj == Opcodes.LONG || obj == Opcodes.DOUBLE || obj == Opcodes.NULL || obj == Opcodes.UNINITIALIZED_THIS) {
            return;
        }
        if (obj instanceof String) {
            checkInternalName((String)obj, "Invalid stack frame value");
            return;
        }
        if (!(obj instanceof Label)) {
            throw new IllegalArgumentException("Invalid stack frame value: " + obj);
        }
        this.usedLabels.add((Label)obj);
    }
    
    static void checkOpcode(final int i, final int n) {
        if (i < 0 || i > 199 || CheckMethodAdapter.TYPE[i] != n) {
            throw new IllegalArgumentException("Invalid opcode: " + i);
        }
    }
    
    static void checkSignedByte(final int i, final String str) {
        if (i < -128 || i > 127) {
            throw new IllegalArgumentException(str + " (must be a signed byte): " + i);
        }
    }
    
    static void checkSignedShort(final int i, final String str) {
        if (i < -32768 || i > 32767) {
            throw new IllegalArgumentException(str + " (must be a signed short): " + i);
        }
    }
    
    static void checkUnsignedShort(final int i, final String str) {
        if (i < 0 || i > 65535) {
            throw new IllegalArgumentException(str + " (must be an unsigned short): " + i);
        }
    }
    
    static void checkConstant(final Object obj) {
        if (!(obj instanceof Integer) && !(obj instanceof Float) && !(obj instanceof Long) && !(obj instanceof Double) && !(obj instanceof String)) {
            throw new IllegalArgumentException("Invalid constant: " + obj);
        }
    }
    
    void checkLDCConstant(final Object o) {
        if (o instanceof Type) {
            final int sort = ((Type)o).getSort();
            if (sort != 10 && sort != 9 && sort != 11) {
                throw new IllegalArgumentException("Illegal LDC constant value");
            }
            if (sort != 11 && (this.version & 0xFFFF) < 49) {
                throw new IllegalArgumentException("ldc of a constant class requires at least version 1.5");
            }
            if (sort == 11 && (this.version & 0xFFFF) < 51) {
                throw new IllegalArgumentException("ldc of a method type requires at least version 1.7");
            }
        }
        else if (o instanceof Handle) {
            if ((this.version & 0xFFFF) < 51) {
                throw new IllegalArgumentException("ldc of a handle requires at least version 1.7");
            }
            final int tag = ((Handle)o).getTag();
            if (tag < 1 || tag > 9) {
                throw new IllegalArgumentException("invalid handle tag " + tag);
            }
        }
        else {
            checkConstant(o);
        }
    }
    
    static void checkUnqualifiedName(final int n, final String str, final String str2) {
        if ((n & 0xFFFF) < 49) {
            checkIdentifier(str, str2);
        }
        else {
            for (int i = 0; i < str.length(); ++i) {
                if (".;[/".indexOf(str.charAt(i)) != -1) {
                    throw new IllegalArgumentException("Invalid " + str2 + " (must be a valid unqualified name): " + str);
                }
            }
        }
    }
    
    static void checkIdentifier(final String s, final String s2) {
        checkIdentifier(s, 0, -1, s2);
    }
    
    static void checkIdentifier(final String s, final int index, final int n, final String str) {
        if (s != null) {
            if (n == -1) {
                if (s.length() <= index) {
                    throw new IllegalArgumentException("Invalid " + str + " (must not be null or empty)");
                }
            }
            else if (n <= index) {
                throw new IllegalArgumentException("Invalid " + str + " (must not be null or empty)");
            }
            if (!Character.isJavaIdentifierStart(s.charAt(index))) {
                throw new IllegalArgumentException("Invalid " + str + " (must be a valid Java identifier): " + s);
            }
            for (int n2 = (n == -1) ? s.length() : n, i = index + 1; i < n2; ++i) {
                if (!Character.isJavaIdentifierPart(s.charAt(i))) {
                    throw new IllegalArgumentException("Invalid " + str + " (must be a valid Java identifier): " + s);
                }
            }
            return;
        }
        throw new IllegalArgumentException("Invalid " + str + " (must not be null or empty)");
    }
    
    static void checkMethodIdentifier(final int n, final String str, final String s) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid " + s + " (must not be null or empty)");
        }
        if ((n & 0xFFFF) >= 49) {
            for (int i = 0; i < str.length(); ++i) {
                if (".;[/<>".indexOf(str.charAt(i)) != -1) {
                    throw new IllegalArgumentException("Invalid " + s + " (must be a valid unqualified name): " + str);
                }
            }
            return;
        }
        if (!Character.isJavaIdentifierStart(str.charAt(0))) {
            throw new IllegalArgumentException("Invalid " + s + " (must be a '<init>', '<clinit>' or a valid Java identifier): " + str);
        }
        for (int j = 1; j < str.length(); ++j) {
            if (!Character.isJavaIdentifierPart(str.charAt(j))) {
                throw new IllegalArgumentException("Invalid " + s + " (must be '<init>' or '<clinit>' or a valid Java identifier): " + str);
            }
        }
    }
    
    static void checkInternalName(final String s, final String str) {
        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Invalid " + str + " (must not be null or empty)");
        }
        if (s.charAt(0) == '[') {
            checkDesc(s, false);
        }
        else {
            checkInternalName(s, 0, -1, str);
        }
    }
    
    static void checkInternalName(final String str, final int n, final int n2, final String str2) {
        final int n3 = (n2 == -1) ? str.length() : n2;
        try {
            int n4 = n;
            int i;
            do {
                i = str.indexOf(47, n4 + 1);
                if (i == -1 || i > n3) {
                    i = n3;
                }
                checkIdentifier(str, n4, i, null);
                n4 = i + 1;
            } while (i != n3);
        }
        catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException("Invalid " + str2 + " (must be a fully qualified class name in internal form): " + str);
        }
    }
    
    static void checkDesc(final String str, final boolean b) {
        if (checkDesc(str, 0, b) != str.length()) {
            throw new IllegalArgumentException("Invalid descriptor: " + str);
        }
    }
    
    static int checkDesc(final String str, final int n, final boolean b) {
        if (str == null || n >= str.length()) {
            throw new IllegalArgumentException("Invalid type descriptor (must not be null or empty)");
        }
        switch (str.charAt(n)) {
            case 'V': {
                if (b) {
                    return n + 1;
                }
                throw new IllegalArgumentException("Invalid descriptor: " + str);
            }
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'Z': {
                return n + 1;
            }
            case '[': {
                int index;
                for (index = n + 1; index < str.length() && str.charAt(index) == '['; ++index) {}
                if (index < str.length()) {
                    return checkDesc(str, index, false);
                }
                throw new IllegalArgumentException("Invalid descriptor: " + str);
            }
            case 'L': {
                final int index2 = str.indexOf(59, n);
                if (index2 == -1 || index2 - n < 2) {
                    throw new IllegalArgumentException("Invalid descriptor: " + str);
                }
                try {
                    checkInternalName(str, n + 1, index2, null);
                }
                catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Invalid descriptor: " + str);
                }
                return index2 + 1;
            }
            default: {
                throw new IllegalArgumentException("Invalid descriptor: " + str);
            }
        }
    }
    
    static void checkMethodDesc(final String str) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Invalid method descriptor (must not be null or empty)");
        }
        if (str.charAt(0) != '(' || str.length() < 3) {
            throw new IllegalArgumentException("Invalid descriptor: " + str);
        }
        int checkDesc = 1;
        Label_0143: {
            if (str.charAt(checkDesc) != ')') {
                while (str.charAt(checkDesc) != 'V') {
                    checkDesc = checkDesc(str, checkDesc, false);
                    if (checkDesc >= str.length() || str.charAt(checkDesc) == ')') {
                        break Label_0143;
                    }
                }
                throw new IllegalArgumentException("Invalid descriptor: " + str);
            }
        }
        if (checkDesc(str, checkDesc + 1, true) != str.length()) {
            throw new IllegalArgumentException("Invalid descriptor: " + str);
        }
    }
    
    void checkLabel(final Label label, final boolean b, final String s) {
        if (label == null) {
            throw new IllegalArgumentException("Invalid " + s + " (must not be null)");
        }
        if (b && this.labels.get(label) == null) {
            throw new IllegalArgumentException("Invalid " + s + " (must be visited first)");
        }
    }
    
    private static void checkNonDebugLabel(final Label obj) {
        final Field labelStatusField = getLabelStatusField();
        int n;
        try {
            n = (int)((labelStatusField == null) ? 0 : labelStatusField.get(obj));
        }
        catch (IllegalAccessException ex) {
            throw new Error("Internal error");
        }
        if ((n & 0x1) != 0x0) {
            throw new IllegalArgumentException("Labels used for debug info cannot be reused for control flow");
        }
    }
    
    private static Field getLabelStatusField() {
        if (CheckMethodAdapter.labelStatusField == null) {
            CheckMethodAdapter.labelStatusField = getLabelField("a");
            if (CheckMethodAdapter.labelStatusField == null) {
                CheckMethodAdapter.labelStatusField = getLabelField("status");
            }
        }
        return CheckMethodAdapter.labelStatusField;
    }
    
    private static Field getLabelField(final String name) {
        try {
            final Field declaredField = Label.class.getDeclaredField(name);
            declaredField.setAccessible(true);
            return declaredField;
        }
        catch (NoSuchFieldException ex) {
            return null;
        }
    }
    
    static {
        final String s = "BBBBBBBBBBBBBBBBCCIAADDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBDDDDDAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBJBBBBBBBBBBBBBBBBBBBBHHHHHHHHHHHHHHHHDKLBBBBBBFFFFGGGGAECEBBEEBBAMHHAA";
        TYPE = new int[s.length()];
        for (int i = 0; i < CheckMethodAdapter.TYPE.length; ++i) {
            CheckMethodAdapter.TYPE[i] = s.charAt(i) - 'A' - 1;
        }
    }
}

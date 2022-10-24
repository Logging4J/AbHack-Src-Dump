//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.util;

import java.io.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.tree.analysis.*;
import java.util.*;
import org.spongepowered.asm.lib.*;

public class CheckClassAdapter extends ClassVisitor
{
    private int version;
    private boolean start;
    private boolean source;
    private boolean outer;
    private boolean end;
    private Map<Label, Integer> labels;
    private boolean checkDataFlow;
    
    public static void main(final String[] array) throws Exception {
        if (array.length != 1) {
            System.err.println("Verifies the given class.");
            System.err.println("Usage: CheckClassAdapter <fully qualified class name or class file name>");
            return;
        }
        ClassReader classReader;
        if (array[0].endsWith(".class")) {
            classReader = new ClassReader((InputStream)new FileInputStream(array[0]));
        }
        else {
            classReader = new ClassReader(array[0]);
        }
        verify(classReader, false, new PrintWriter(System.err));
    }
    
    public static void verify(final ClassReader classReader, final ClassLoader classLoader, final boolean b, final PrintWriter s) {
        final ClassNode classNode = new ClassNode();
        classReader.accept((ClassVisitor)new CheckClassAdapter((ClassVisitor)classNode, false), 2);
        final Type type = (classNode.superName == null) ? null : Type.getObjectType(classNode.superName);
        final List methods = classNode.methods;
        final ArrayList<Type> list = new ArrayList<Type>();
        final Iterator iterator = classNode.interfaces.iterator();
        while (iterator.hasNext()) {
            list.add(Type.getObjectType((String)iterator.next()));
        }
        for (int i = 0; i < methods.size(); ++i) {
            final MethodNode methodNode = methods.get(i);
            final SimpleVerifier simpleVerifier = new SimpleVerifier(Type.getObjectType(classNode.name), type, (List)list, (classNode.access & 0x200) != 0x0);
            final Analyzer analyzer = new Analyzer((Interpreter)simpleVerifier);
            if (classLoader != null) {
                simpleVerifier.setClassLoader(classLoader);
            }
            try {
                analyzer.analyze(classNode.name, methodNode);
                if (!b) {
                    continue;
                }
            }
            catch (Exception ex) {
                ex.printStackTrace(s);
            }
            printAnalyzerResult(methodNode, (Analyzer<BasicValue>)analyzer, s);
        }
        s.flush();
    }
    
    public static void verify(final ClassReader classReader, final boolean b, final PrintWriter printWriter) {
        verify(classReader, null, b, printWriter);
    }
    
    static void printAnalyzerResult(final MethodNode methodNode, final Analyzer<BasicValue> analyzer, final PrintWriter printWriter) {
        final Frame[] frames = analyzer.getFrames();
        final Textifier textifier = new Textifier();
        final TraceMethodVisitor traceMethodVisitor = new TraceMethodVisitor(textifier);
        printWriter.println(methodNode.name + methodNode.desc);
        for (int i = 0; i < methodNode.instructions.size(); ++i) {
            methodNode.instructions.get(i).accept((MethodVisitor)traceMethodVisitor);
            final StringBuilder obj = new StringBuilder();
            final Frame frame = frames[i];
            if (frame == null) {
                obj.append('?');
            }
            else {
                for (int j = 0; j < frame.getLocals(); ++j) {
                    obj.append(getShortName(((BasicValue)frame.getLocal(j)).toString())).append(' ');
                }
                obj.append(" : ");
                for (int k = 0; k < frame.getStackSize(); ++k) {
                    obj.append(getShortName(((BasicValue)frame.getStack(k)).toString())).append(' ');
                }
            }
            while (obj.length() < methodNode.maxStack + methodNode.maxLocals + 1) {
                obj.append(' ');
            }
            printWriter.print(Integer.toString(i + 100000).substring(1));
            printWriter.print(" " + (Object)obj + " : " + textifier.text.get(textifier.text.size() - 1));
        }
        for (int l = 0; l < methodNode.tryCatchBlocks.size(); ++l) {
            ((TryCatchBlockNode)methodNode.tryCatchBlocks.get(l)).accept((MethodVisitor)traceMethodVisitor);
            printWriter.print(" " + textifier.text.get(textifier.text.size() - 1));
        }
        printWriter.println();
    }
    
    private static String getShortName(final String s) {
        final int lastIndex = s.lastIndexOf(47);
        int length = s.length();
        if (s.charAt(length - 1) == ';') {
            --length;
        }
        return (lastIndex == -1) ? s : s.substring(lastIndex + 1, length);
    }
    
    public CheckClassAdapter(final ClassVisitor classVisitor) {
        this(classVisitor, true);
    }
    
    public CheckClassAdapter(final ClassVisitor classVisitor, final boolean b) {
        this(327680, classVisitor, b);
        if (this.getClass() != CheckClassAdapter.class) {
            throw new IllegalStateException();
        }
    }
    
    protected CheckClassAdapter(final int n, final ClassVisitor classVisitor, final boolean checkDataFlow) {
        super(n, classVisitor);
        this.labels = new HashMap<Label, Integer>();
        this.checkDataFlow = checkDataFlow;
    }
    
    public void visit(final int version, final int n, final String anObject, final String s, final String anObject2, final String[] array) {
        if (this.start) {
            throw new IllegalStateException("visit must be called only once");
        }
        this.start = true;
        this.checkState();
        checkAccess(n, 423473);
        if (anObject == null || !anObject.endsWith("package-info")) {
            CheckMethodAdapter.checkInternalName(anObject, "class name");
        }
        if ("java/lang/Object".equals(anObject)) {
            if (anObject2 != null) {
                throw new IllegalArgumentException("The super class name of the Object class must be 'null'");
            }
        }
        else {
            CheckMethodAdapter.checkInternalName(anObject2, "super class name");
        }
        if (s != null) {
            checkClassSignature(s);
        }
        if ((n & 0x200) != 0x0 && !"java/lang/Object".equals(anObject2)) {
            throw new IllegalArgumentException("The super class name of interfaces must be 'java/lang/Object'");
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                CheckMethodAdapter.checkInternalName(array[i], "interface name at index " + i);
            }
        }
        super.visit(this.version = version, n, anObject, s, anObject2, array);
    }
    
    public void visitSource(final String s, final String s2) {
        this.checkState();
        if (this.source) {
            throw new IllegalStateException("visitSource can be called only once.");
        }
        this.source = true;
        super.visitSource(s, s2);
    }
    
    public void visitOuterClass(final String s, final String s2, final String s3) {
        this.checkState();
        if (this.outer) {
            throw new IllegalStateException("visitOuterClass can be called only once.");
        }
        this.outer = true;
        if (s == null) {
            throw new IllegalArgumentException("Illegal outer class owner");
        }
        if (s3 != null) {
            CheckMethodAdapter.checkMethodDesc(s3);
        }
        super.visitOuterClass(s, s2, s3);
    }
    
    public void visitInnerClass(final String s, final String s2, final String s3, final int n) {
        this.checkState();
        CheckMethodAdapter.checkInternalName(s, "class name");
        if (s2 != null) {
            CheckMethodAdapter.checkInternalName(s2, "outer class name");
        }
        if (s3 != null) {
            int index;
            for (index = 0; index < s3.length() && Character.isDigit(s3.charAt(index)); ++index) {}
            if (index == 0 || index < s3.length()) {
                CheckMethodAdapter.checkIdentifier(s3, index, -1, "inner class name");
            }
        }
        checkAccess(n, 30239);
        super.visitInnerClass(s, s2, s3, n);
    }
    
    public FieldVisitor visitField(final int n, final String s, final String s2, final String s3, final Object o) {
        this.checkState();
        checkAccess(n, 413919);
        CheckMethodAdapter.checkUnqualifiedName(this.version, s, "field name");
        CheckMethodAdapter.checkDesc(s2, false);
        if (s3 != null) {
            checkFieldSignature(s3);
        }
        if (o != null) {
            CheckMethodAdapter.checkConstant(o);
        }
        return new CheckFieldAdapter(super.visitField(n, s, s2, s3, o));
    }
    
    public MethodVisitor visitMethod(final int n, final String s, final String s2, final String s3, final String[] array) {
        this.checkState();
        checkAccess(n, 400895);
        if (!"<init>".equals(s) && !"<clinit>".equals(s)) {
            CheckMethodAdapter.checkMethodIdentifier(this.version, s, "method name");
        }
        CheckMethodAdapter.checkMethodDesc(s2);
        if (s3 != null) {
            checkMethodSignature(s3);
        }
        if (array != null) {
            for (int i = 0; i < array.length; ++i) {
                CheckMethodAdapter.checkInternalName(array[i], "exception name at index " + i);
            }
        }
        CheckMethodAdapter checkMethodAdapter;
        if (this.checkDataFlow) {
            checkMethodAdapter = new CheckMethodAdapter(n, s, s2, super.visitMethod(n, s, s2, s3, array), this.labels);
        }
        else {
            checkMethodAdapter = new CheckMethodAdapter(super.visitMethod(n, s, s2, s3, array), this.labels);
        }
        checkMethodAdapter.version = this.version;
        return checkMethodAdapter;
    }
    
    public AnnotationVisitor visitAnnotation(final String s, final boolean b) {
        this.checkState();
        CheckMethodAdapter.checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitAnnotation(s, b));
    }
    
    public AnnotationVisitor visitTypeAnnotation(final int n, final TypePath typePath, final String s, final boolean b) {
        this.checkState();
        final int i = n >>> 24;
        if (i != 0 && i != 17 && i != 16) {
            throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i));
        }
        checkTypeRefAndPath(n, typePath);
        CheckMethodAdapter.checkDesc(s, false);
        return (AnnotationVisitor)new CheckAnnotationAdapter(super.visitTypeAnnotation(n, typePath, s, b));
    }
    
    public void visitAttribute(final Attribute attribute) {
        this.checkState();
        if (attribute == null) {
            throw new IllegalArgumentException("Invalid attribute (must not be null)");
        }
        super.visitAttribute(attribute);
    }
    
    public void visitEnd() {
        this.checkState();
        this.end = true;
        super.visitEnd();
    }
    
    private void checkState() {
        if (!this.start) {
            throw new IllegalStateException("Cannot visit member before visit has been called.");
        }
        if (this.end) {
            throw new IllegalStateException("Cannot visit member after visitEnd has been called.");
        }
    }
    
    static void checkAccess(final int i, final int n) {
        if ((i & ~n) != 0x0) {
            throw new IllegalArgumentException("Invalid access flags: " + i);
        }
        if (((((i & 0x1) != 0x0) + ((i & 0x2) != 0x0) + ((i & 0x4) != 0x0)) ? 1 : 0) > 1) {
            throw new IllegalArgumentException("public private and protected are mutually exclusive: " + i);
        }
        if (((((i & 0x10) != 0x0) + ((i & 0x400) != 0x0)) ? 1 : 0) > 1) {
            throw new IllegalArgumentException("final and abstract are mutually exclusive: " + i);
        }
    }
    
    public static void checkClassSignature(final String str) {
        int checkFormalTypeParameters = 0;
        if (getChar(str, 0) == '<') {
            checkFormalTypeParameters = checkFormalTypeParameters(str, checkFormalTypeParameters);
        }
        int i;
        for (i = checkClassTypeSignature(str, checkFormalTypeParameters); getChar(str, i) == 'L'; i = checkClassTypeSignature(str, i)) {}
        if (i != str.length()) {
            throw new IllegalArgumentException(str + ": error at index " + i);
        }
    }
    
    public static void checkMethodSignature(final String str) {
        int checkFormalTypeParameters = 0;
        if (getChar(str, 0) == '<') {
            checkFormalTypeParameters = checkFormalTypeParameters(str, checkFormalTypeParameters);
        }
        int n;
        for (n = checkChar('(', str, checkFormalTypeParameters); "ZCBSIFJDL[T".indexOf(getChar(str, n)) != -1; n = checkTypeSignature(str, n)) {}
        int i = checkChar(')', str, n);
        if (getChar(str, i) == 'V') {
            ++i;
        }
        else {
            i = checkTypeSignature(str, i);
        }
        while (getChar(str, i) == '^') {
            ++i;
            if (getChar(str, i) == 'L') {
                i = checkClassTypeSignature(str, i);
            }
            else {
                i = checkTypeVariableSignature(str, i);
            }
        }
        if (i != str.length()) {
            throw new IllegalArgumentException(str + ": error at index " + i);
        }
    }
    
    public static void checkFieldSignature(final String str) {
        final int checkFieldTypeSignature = checkFieldTypeSignature(str, 0);
        if (checkFieldTypeSignature != str.length()) {
            throw new IllegalArgumentException(str + ": error at index " + checkFieldTypeSignature);
        }
    }
    
    static void checkTypeRefAndPath(final int i, final TypePath typePath) {
        int n = 0;
        switch (i >>> 24) {
            case 0:
            case 1:
            case 22: {
                n = -65536;
                break;
            }
            case 19:
            case 20:
            case 21:
            case 64:
            case 65:
            case 67:
            case 68:
            case 69:
            case 70: {
                n = -16777216;
                break;
            }
            case 16:
            case 17:
            case 18:
            case 23:
            case 66: {
                n = -256;
                break;
            }
            case 71:
            case 72:
            case 73:
            case 74:
            case 75: {
                n = -16776961;
                break;
            }
            default: {
                throw new IllegalArgumentException("Invalid type reference sort 0x" + Integer.toHexString(i >>> 24));
            }
        }
        if ((i & ~n) != 0x0) {
            throw new IllegalArgumentException("Invalid type reference 0x" + Integer.toHexString(i));
        }
        if (typePath != null) {
            for (int j = 0; j < typePath.getLength(); ++j) {
                final int step = typePath.getStep(j);
                if (step != 0 && step != 1 && step != 3 && step != 2) {
                    throw new IllegalArgumentException("Invalid type path step " + j + " in " + typePath);
                }
                if (step != 3 && typePath.getStepArgument(j) != 0) {
                    throw new IllegalArgumentException("Invalid type path step argument for step " + j + " in " + typePath);
                }
            }
        }
    }
    
    private static int checkFormalTypeParameters(final String s, int n) {
        for (n = checkChar('<', s, n), n = checkFormalTypeParameter(s, n); getChar(s, n) != '>'; n = checkFormalTypeParameter(s, n)) {}
        return n + 1;
    }
    
    private static int checkFormalTypeParameter(final String s, int n) {
        n = checkIdentifier(s, n);
        n = checkChar(':', s, n);
        if ("L[T".indexOf(getChar(s, n)) != -1) {
            n = checkFieldTypeSignature(s, n);
        }
        while (getChar(s, n) == ':') {
            n = checkFieldTypeSignature(s, n + 1);
        }
        return n;
    }
    
    private static int checkFieldTypeSignature(final String s, final int n) {
        switch (getChar(s, n)) {
            case 'L': {
                return checkClassTypeSignature(s, n);
            }
            case '[': {
                return checkTypeSignature(s, n + 1);
            }
            default: {
                return checkTypeVariableSignature(s, n);
            }
        }
    }
    
    private static int checkClassTypeSignature(final String s, int n) {
        for (n = checkChar('L', s, n), n = checkIdentifier(s, n); getChar(s, n) == '/'; n = checkIdentifier(s, n + 1)) {}
        if (getChar(s, n) == '<') {
            n = checkTypeArguments(s, n);
        }
        while (getChar(s, n) == '.') {
            n = checkIdentifier(s, n + 1);
            if (getChar(s, n) == '<') {
                n = checkTypeArguments(s, n);
            }
        }
        return checkChar(';', s, n);
    }
    
    private static int checkTypeArguments(final String s, int n) {
        for (n = checkChar('<', s, n), n = checkTypeArgument(s, n); getChar(s, n) != '>'; n = checkTypeArgument(s, n)) {}
        return n + 1;
    }
    
    private static int checkTypeArgument(final String s, int n) {
        final char char1 = getChar(s, n);
        if (char1 == '*') {
            return n + 1;
        }
        if (char1 == '+' || char1 == '-') {
            ++n;
        }
        return checkFieldTypeSignature(s, n);
    }
    
    private static int checkTypeVariableSignature(final String s, int n) {
        n = checkChar('T', s, n);
        n = checkIdentifier(s, n);
        return checkChar(';', s, n);
    }
    
    private static int checkTypeSignature(final String s, final int n) {
        switch (getChar(s, n)) {
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
            default: {
                return checkFieldTypeSignature(s, n);
            }
        }
    }
    
    private static int checkIdentifier(final String str, int i) {
        if (!Character.isJavaIdentifierStart(getChar(str, i))) {
            throw new IllegalArgumentException(str + ": identifier expected at index " + i);
        }
        ++i;
        while (Character.isJavaIdentifierPart(getChar(str, i))) {
            ++i;
        }
        return i;
    }
    
    private static int checkChar(final char c, final String str, final int i) {
        if (getChar(str, i) == c) {
            return i + 1;
        }
        throw new IllegalArgumentException(str + ": '" + c + "' expected at index " + i);
    }
    
    private static char getChar(final String s, final int index) {
        return (index < s.length()) ? s.charAt(index) : '\0';
    }
}

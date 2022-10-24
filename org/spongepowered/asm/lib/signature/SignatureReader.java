//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.signature;

public class SignatureReader
{
    private final String signature;
    
    public SignatureReader(final String signature) {
        this.signature = signature;
    }
    
    public void accept(final SignatureVisitor signatureVisitor) {
        final String signature = this.signature;
        final int length = signature.length();
        int n;
        if (signature.charAt(0) == '<') {
            n = 2;
            char char1;
            do {
                final int index = signature.indexOf(58, n);
                signatureVisitor.visitFormalTypeParameter(signature.substring(n - 1, index));
                n = index + 1;
                final char char2 = signature.charAt(n);
                if (char2 == 'L' || char2 == '[' || char2 == 'T') {
                    n = parseType(signature, n, signatureVisitor.visitClassBound());
                }
                while ((char1 = signature.charAt(n++)) == ':') {
                    n = parseType(signature, n, signatureVisitor.visitInterfaceBound());
                }
            } while (char1 != '>');
        }
        else {
            n = 0;
        }
        if (signature.charAt(n) == '(') {
            ++n;
            while (signature.charAt(n) != ')') {
                n = parseType(signature, n, signatureVisitor.visitParameterType());
            }
            for (int i = parseType(signature, n + 1, signatureVisitor.visitReturnType()); i < length; i = parseType(signature, i + 1, signatureVisitor.visitExceptionType())) {}
        }
        else {
            for (int j = parseType(signature, n, signatureVisitor.visitSuperclass()); j < length; j = parseType(signature, j, signatureVisitor.visitInterface())) {}
        }
    }
    
    public void acceptType(final SignatureVisitor signatureVisitor) {
        parseType(this.signature, 0, signatureVisitor);
    }
    
    private static int parseType(final String s, int index, final SignatureVisitor signatureVisitor) {
        final char char1;
        switch (char1 = s.charAt(index++)) {
            case 'B':
            case 'C':
            case 'D':
            case 'F':
            case 'I':
            case 'J':
            case 'S':
            case 'V':
            case 'Z': {
                signatureVisitor.visitBaseType(char1);
                return index;
            }
            case '[': {
                return parseType(s, index, signatureVisitor.visitArrayType());
            }
            case 'T': {
                final int index2 = s.indexOf(59, index);
                signatureVisitor.visitTypeVariable(s.substring(index, index2));
                return index2 + 1;
            }
            default: {
                int n = index;
                int n2 = 0;
                int n3 = 0;
            Block_3:
                while (true) {
                    final char char2;
                    switch (char2 = s.charAt(index++)) {
                        case '.':
                        case ';': {
                            if (n2 == 0) {
                                final String substring = s.substring(n, index - 1);
                                if (n3 != 0) {
                                    signatureVisitor.visitInnerClassType(substring);
                                }
                                else {
                                    signatureVisitor.visitClassType(substring);
                                }
                            }
                            if (char2 == ';') {
                                break Block_3;
                            }
                            n = index;
                            n2 = 0;
                            n3 = 1;
                            continue;
                        }
                        case '<': {
                            final String substring2 = s.substring(n, index - 1);
                            if (n3 != 0) {
                                signatureVisitor.visitInnerClassType(substring2);
                            }
                            else {
                                signatureVisitor.visitClassType(substring2);
                            }
                            n2 = 1;
                        Label_0368:
                            while (true) {
                                final char char3;
                                switch (char3 = s.charAt(index)) {
                                    case '>': {
                                        break Label_0368;
                                    }
                                    case '*': {
                                        ++index;
                                        signatureVisitor.visitTypeArgument();
                                        continue;
                                    }
                                    case '+':
                                    case '-': {
                                        index = parseType(s, index + 1, signatureVisitor.visitTypeArgument(char3));
                                        continue;
                                    }
                                    default: {
                                        index = parseType(s, index, signatureVisitor.visitTypeArgument('='));
                                        continue;
                                    }
                                }
                            }
                            continue;
                        }
                    }
                }
                signatureVisitor.visitEnd();
                return index;
            }
        }
    }
}

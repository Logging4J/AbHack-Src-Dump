//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.commons;

import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.signature.*;

public abstract class Remapper
{
    public String mapDesc(final String s) {
        final Type type = Type.getType(s);
        switch (type.getSort()) {
            case 9: {
                String str = this.mapDesc(type.getElementType().getDescriptor());
                for (int i = 0; i < type.getDimensions(); ++i) {
                    str = '[' + str;
                }
                return str;
            }
            case 10: {
                final String map = this.map(type.getInternalName());
                if (map != null) {
                    return 'L' + map + ';';
                }
                break;
            }
        }
        return s;
    }
    
    private Type mapType(final Type type) {
        switch (type.getSort()) {
            case 9: {
                String str = this.mapDesc(type.getElementType().getDescriptor());
                for (int i = 0; i < type.getDimensions(); ++i) {
                    str = '[' + str;
                }
                return Type.getType(str);
            }
            case 10: {
                final String map = this.map(type.getInternalName());
                return (map != null) ? Type.getObjectType(map) : type;
            }
            case 11: {
                return Type.getMethodType(this.mapMethodDesc(type.getDescriptor()));
            }
            default: {
                return type;
            }
        }
    }
    
    public String mapType(final String s) {
        if (s == null) {
            return null;
        }
        return this.mapType(Type.getObjectType(s)).getInternalName();
    }
    
    public String[] mapTypes(final String[] array) {
        String[] array2 = null;
        boolean b = false;
        for (int i = 0; i < array.length; ++i) {
            final String s = array[i];
            final String map = this.map(s);
            if (map != null && array2 == null) {
                array2 = new String[array.length];
                if (i > 0) {
                    System.arraycopy(array, 0, array2, 0, i);
                }
                b = true;
            }
            if (b) {
                array2[i] = ((map == null) ? s : map);
            }
        }
        return b ? array2 : array;
    }
    
    public String mapMethodDesc(final String anObject) {
        if ("()V".equals(anObject)) {
            return anObject;
        }
        final Type[] argumentTypes = Type.getArgumentTypes(anObject);
        final StringBuilder sb = new StringBuilder("(");
        for (int i = 0; i < argumentTypes.length; ++i) {
            sb.append(this.mapDesc(argumentTypes[i].getDescriptor()));
        }
        final Type returnType = Type.getReturnType(anObject);
        if (returnType == Type.VOID_TYPE) {
            sb.append(")V");
            return sb.toString();
        }
        sb.append(')').append(this.mapDesc(returnType.getDescriptor()));
        return sb.toString();
    }
    
    public Object mapValue(final Object o) {
        if (o instanceof Type) {
            return this.mapType((Type)o);
        }
        if (o instanceof Handle) {
            final Handle handle = (Handle)o;
            return new Handle(handle.getTag(), this.mapType(handle.getOwner()), this.mapMethodName(handle.getOwner(), handle.getName(), handle.getDesc()), this.mapMethodDesc(handle.getDesc()), handle.isInterface());
        }
        return o;
    }
    
    public String mapSignature(final String s, final boolean b) {
        if (s == null) {
            return null;
        }
        final SignatureReader signatureReader = new SignatureReader(s);
        final SignatureWriter signatureWriter = new SignatureWriter();
        final SignatureVisitor signatureRemapper = this.createSignatureRemapper(signatureWriter);
        if (b) {
            signatureReader.acceptType(signatureRemapper);
        }
        else {
            signatureReader.accept(signatureRemapper);
        }
        return signatureWriter.toString();
    }
    
    @Deprecated
    protected SignatureVisitor createRemappingSignatureAdapter(final SignatureVisitor signatureVisitor) {
        return new SignatureRemapper(signatureVisitor, this);
    }
    
    protected SignatureVisitor createSignatureRemapper(final SignatureVisitor signatureVisitor) {
        return this.createRemappingSignatureAdapter(signatureVisitor);
    }
    
    public String mapMethodName(final String s, final String s2, final String s3) {
        return s2;
    }
    
    public String mapInvokeDynamicMethodName(final String s, final String s2) {
        return s;
    }
    
    public String mapFieldName(final String s, final String s2, final String s3) {
        return s2;
    }
    
    public String map(final String s) {
        return s;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror.mapping;

import org.spongepowered.asm.obfuscation.mapping.common.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;

public final class ResolvableMappingMethod extends MappingMethod
{
    private final TypeHandle ownerHandle;
    
    public ResolvableMappingMethod(final TypeHandle ownerHandle, final String s, final String s2) {
        super(ownerHandle.getName(), s, s2);
        this.ownerHandle = ownerHandle;
    }
    
    public MappingMethod getSuper() {
        if (this.ownerHandle == null) {
            return super.getSuper();
        }
        final String simpleName = this.getSimpleName();
        final String desc = this.getDesc();
        final String javaSignature = TypeUtils.getJavaSignature(desc);
        final TypeHandle superclass = this.ownerHandle.getSuperclass();
        if (superclass != null && superclass.findMethod(simpleName, javaSignature) != null) {
            return superclass.getMappingMethod(simpleName, desc);
        }
        for (final TypeHandle typeHandle : this.ownerHandle.getInterfaces()) {
            if (typeHandle.findMethod(simpleName, javaSignature) != null) {
                return typeHandle.getMappingMethod(simpleName, desc);
            }
        }
        if (superclass != null) {
            return superclass.getMappingMethod(simpleName, desc).getSuper();
        }
        return super.getSuper();
    }
    
    public MappingMethod move(final TypeHandle typeHandle) {
        return new ResolvableMappingMethod(typeHandle, this.getSimpleName(), this.getDesc());
    }
    
    public MappingMethod remap(final String s) {
        return new ResolvableMappingMethod(this.ownerHandle, s, this.getDesc());
    }
    
    public MappingMethod transform(final String s) {
        return new ResolvableMappingMethod(this.ownerHandle, this.getSimpleName(), s);
    }
    
    public MappingMethod copy() {
        return new ResolvableMappingMethod(this.ownerHandle, this.getSimpleName(), this.getDesc());
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mirror;

import org.spongepowered.asm.obfuscation.mapping.common.*;
import javax.lang.model.element.*;
import org.spongepowered.tools.obfuscation.mirror.mapping.*;
import com.google.common.base.*;
import org.spongepowered.asm.obfuscation.mapping.*;

public class MethodHandle extends MemberHandle<MappingMethod>
{
    private final ExecutableElement element;
    private final TypeHandle ownerHandle;
    
    public MethodHandle(final TypeHandle typeHandle, final ExecutableElement executableElement) {
        this(typeHandle, executableElement, TypeUtils.getName(executableElement), TypeUtils.getDescriptor(executableElement));
    }
    
    public MethodHandle(final TypeHandle typeHandle, final String s, final String s2) {
        this(typeHandle, null, s, s2);
    }
    
    private MethodHandle(final TypeHandle ownerHandle, final ExecutableElement element, final String s, final String s2) {
        super((ownerHandle != null) ? ownerHandle.getName() : null, s, s2);
        this.element = element;
        this.ownerHandle = ownerHandle;
    }
    
    public boolean isImaginary() {
        return this.element == null;
    }
    
    public ExecutableElement getElement() {
        return this.element;
    }
    
    public Visibility getVisibility() {
        return TypeUtils.getVisibility(this.element);
    }
    
    public MappingMethod asMapping(final boolean b) {
        if (!b) {
            return new MappingMethod((String)null, this.getName(), this.getDesc());
        }
        if (this.ownerHandle != null) {
            return (MappingMethod)new ResolvableMappingMethod(this.ownerHandle, this.getName(), this.getDesc());
        }
        return new MappingMethod(this.getOwner(), this.getName(), this.getDesc());
    }
    
    public String toString() {
        return String.format("%s%s%s", (this.getOwner() != null) ? ("L" + this.getOwner() + ";") : "", Strings.nullToEmpty(this.getName()), Strings.nullToEmpty(this.getDesc()));
    }
}

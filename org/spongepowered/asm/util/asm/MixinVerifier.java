//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util.asm;

import org.spongepowered.asm.lib.tree.analysis.*;
import org.spongepowered.asm.lib.*;
import java.util.*;
import org.spongepowered.asm.mixin.transformer.*;

public class MixinVerifier extends SimpleVerifier
{
    private Type currentClass;
    private Type currentSuperClass;
    private List<Type> currentClassInterfaces;
    private boolean isInterface;
    
    public MixinVerifier(final Type currentClass, final Type currentSuperClass, final List<Type> currentClassInterfaces, final boolean isInterface) {
        super(currentClass, currentSuperClass, (List)currentClassInterfaces, isInterface);
        this.currentClass = currentClass;
        this.currentSuperClass = currentSuperClass;
        this.currentClassInterfaces = currentClassInterfaces;
        this.isInterface = isInterface;
    }
    
    protected boolean isInterface(final Type type) {
        if (this.currentClass != null && type.equals((Object)this.currentClass)) {
            return this.isInterface;
        }
        return ClassInfo.forType(type).isInterface();
    }
    
    protected Type getSuperClass(final Type type) {
        if (this.currentClass != null && type.equals((Object)this.currentClass)) {
            return this.currentSuperClass;
        }
        final ClassInfo superClass = ClassInfo.forType(type).getSuperClass();
        return (superClass == null) ? null : Type.getType("L" + superClass.getName() + ";");
    }
    
    protected boolean isAssignableFrom(final Type type, final Type type2) {
        if (type.equals((Object)type2)) {
            return true;
        }
        if (this.currentClass != null && type.equals((Object)this.currentClass)) {
            if (this.getSuperClass(type2) == null) {
                return false;
            }
            if (this.isInterface) {
                return type2.getSort() == 10 || type2.getSort() == 9;
            }
            return this.isAssignableFrom(type, this.getSuperClass(type2));
        }
        else if (this.currentClass != null && type2.equals((Object)this.currentClass)) {
            if (this.isAssignableFrom(type, this.currentSuperClass)) {
                return true;
            }
            if (this.currentClassInterfaces != null) {
                for (int i = 0; i < this.currentClassInterfaces.size(); ++i) {
                    if (this.isAssignableFrom(type, this.currentClassInterfaces.get(i))) {
                        return true;
                    }
                }
            }
            return false;
        }
        else {
            ClassInfo classInfo = ClassInfo.forType(type);
            if (classInfo == null) {
                return false;
            }
            if (classInfo.isInterface()) {
                classInfo = ClassInfo.forName("java/lang/Object");
            }
            return ClassInfo.forType(type2).hasSuperClass(classInfo);
        }
    }
}

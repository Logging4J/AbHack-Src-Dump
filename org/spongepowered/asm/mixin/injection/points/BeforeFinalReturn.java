//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.refmap.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import java.util.*;

@InjectionPoint.AtCode("TAIL")
public class BeforeFinalReturn extends InjectionPoint
{
    private final IMixinContext context;
    
    public BeforeFinalReturn(final InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.context = injectionPointData.getContext();
    }
    
    public boolean checkPriority(final int n, final int n2) {
        return true;
    }
    
    public boolean find(final String s, final InsnList list, final Collection<AbstractInsnNode> collection) {
        AbstractInsnNode abstractInsnNode = null;
        final int opcode = Type.getReturnType(s).getOpcode(172);
        for (final AbstractInsnNode abstractInsnNode2 : list) {
            if (abstractInsnNode2 instanceof InsnNode && abstractInsnNode2.getOpcode() == opcode) {
                abstractInsnNode = abstractInsnNode2;
            }
        }
        if (abstractInsnNode == null) {
            throw new InvalidInjectionException(this.context, "TAIL could not locate a valid RETURN in the target method!");
        }
        collection.add(abstractInsnNode);
        return true;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.lib.tree.*;

@InjectionPoint.AtCode("HEAD")
public class MethodHead extends InjectionPoint
{
    public MethodHead(final InjectionPointData injectionPointData) {
        super(injectionPointData);
    }
    
    public boolean checkPriority(final int n, final int n2) {
        return true;
    }
    
    public boolean find(final String s, final InsnList list, final Collection<AbstractInsnNode> collection) {
        collection.add(list.getFirst());
        return true;
    }
}

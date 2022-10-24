//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.*;
import java.util.*;

public abstract class AccessorGenerator
{
    protected final AccessorInfo info;
    
    public AccessorGenerator(final AccessorInfo info) {
        this.info = info;
    }
    
    protected final MethodNode createMethod(final int maxLocals, final int maxStack) {
        final MethodNode method = this.info.getMethod();
        final MethodNode methodNode = new MethodNode(327680, (method.access & 0xFFFFFBFF) | 0x1000, method.name, method.desc, (String)null, (String[])null);
        (methodNode.visibleAnnotations = new ArrayList()).add(this.info.getAnnotation());
        methodNode.maxLocals = maxLocals;
        methodNode.maxStack = maxStack;
        return methodNode;
    }
    
    public abstract MethodNode generate();
}

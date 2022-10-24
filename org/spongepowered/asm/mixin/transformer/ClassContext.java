//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.transformer;

import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.mixin.struct.*;
import java.util.*;

abstract class ClassContext
{
    private final Set<ClassInfo.Method> upgradedMethods;
    
    ClassContext() {
        this.upgradedMethods = new HashSet<ClassInfo.Method>();
    }
    
    abstract String getClassRef();
    
    abstract ClassNode getClassNode();
    
    abstract ClassInfo getClassInfo();
    
    void addUpgradedMethod(final MethodNode methodNode) {
        final ClassInfo.Method method = this.getClassInfo().findMethod(methodNode);
        if (method == null) {
            throw new IllegalStateException("Meta method for " + methodNode.name + " not located in " + this);
        }
        this.upgradedMethods.add(method);
    }
    
    protected void upgradeMethods() {
        final Iterator<MethodNode> iterator = this.getClassNode().methods.iterator();
        while (iterator.hasNext()) {
            this.upgradeMethod(iterator.next());
        }
    }
    
    private void upgradeMethod(final MethodNode methodNode) {
        for (final AbstractInsnNode abstractInsnNode : methodNode.instructions) {
            if (!(abstractInsnNode instanceof MethodInsnNode)) {
                continue;
            }
            final MemberRef.Method method = new MemberRef.Method((MethodInsnNode)abstractInsnNode);
            if (!((MemberRef)method).getOwner().equals(this.getClassRef())) {
                continue;
            }
            this.upgradeMethodRef(methodNode, (MemberRef)method, this.getClassInfo().findMethod(((MemberRef)method).getName(), ((MemberRef)method).getDesc(), 10));
        }
    }
    
    protected void upgradeMethodRef(final MethodNode methodNode, final MemberRef memberRef, final ClassInfo.Method method) {
        if (memberRef.getOpcode() != 183) {
            return;
        }
        if (this.upgradedMethods.contains(method)) {
            memberRef.setOpcode(182);
        }
    }
}

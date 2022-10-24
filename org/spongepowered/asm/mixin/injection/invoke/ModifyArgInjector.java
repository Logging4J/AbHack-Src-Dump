//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.lib.tree.*;
import java.util.*;
import org.spongepowered.asm.util.*;

public class ModifyArgInjector extends InvokeInjector
{
    private final int index;
    private final boolean singleArgMode;
    
    public ModifyArgInjector(final InjectionInfo injectionInfo, final int index) {
        super(injectionInfo, "@ModifyArg");
        this.index = index;
        this.singleArgMode = (this.methodArgs.length == 1);
    }
    
    protected void sanityCheck(final Target target, final List<InjectionPoint> list) {
        super.sanityCheck(target, (List)list);
        if (this.singleArgMode && !this.methodArgs[0].equals((Object)this.returnType)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg return type on " + this + " must match the parameter type. ARG=" + this.methodArgs[0] + " RETURN=" + this.returnType);
        }
    }
    
    protected void checkTarget(final Target target) {
        if (!this.isStatic && target.isStatic) {
            throw new InvalidInjectionException(this.info, "non-static callback method " + this + " targets a static method which is not supported");
        }
    }
    
    protected void inject(final Target target, final InjectionNodes.InjectionNode injectionNode) {
        this.checkTargetForNode(target, injectionNode);
        super.inject(target, injectionNode);
    }
    
    protected void injectAtInvoke(final Target target, final InjectionNodes.InjectionNode injectionNode) {
        final MethodInsnNode methodInsnNode = (MethodInsnNode)injectionNode.getCurrentTarget();
        final Type[] argumentTypes = Type.getArgumentTypes(methodInsnNode.desc);
        final int argIndex = this.findArgIndex(target, argumentTypes);
        final InsnList list = new InsnList();
        int n;
        if (this.singleArgMode) {
            n = this.injectSingleArgHandler(target, argumentTypes, argIndex, list);
        }
        else {
            n = this.injectMultiArgHandler(target, argumentTypes, argIndex, list);
        }
        target.insns.insertBefore((AbstractInsnNode)methodInsnNode, list);
        target.addToLocals(n);
        target.addToStack(2 - (n - 1));
    }
    
    private int injectSingleArgHandler(final Target target, final Type[] array, final int n, final InsnList list) {
        final int[] storeArgs = this.storeArgs(target, array, list, n);
        this.invokeHandlerWithArgs(array, list, storeArgs, n, n + 1);
        this.pushArgs(array, list, storeArgs, n + 1, array.length);
        return storeArgs[storeArgs.length - 1] - target.getMaxLocals() + array[array.length - 1].getSize();
    }
    
    private int injectMultiArgHandler(final Target target, final Type[] a, final int n, final InsnList list) {
        if (!Arrays.equals(a, this.methodArgs)) {
            throw new InvalidInjectionException(this.info, "@ModifyArg method " + this + " targets a method with an invalid signature " + Bytecode.getDescriptor(a) + ", expected " + Bytecode.getDescriptor(this.methodArgs));
        }
        final int[] storeArgs = this.storeArgs(target, a, list, 0);
        this.pushArgs(a, list, storeArgs, 0, n);
        this.invokeHandlerWithArgs(a, list, storeArgs, 0, a.length);
        this.pushArgs(a, list, storeArgs, n + 1, a.length);
        return storeArgs[storeArgs.length - 1] - target.getMaxLocals() + a[a.length - 1].getSize();
    }
    
    protected int findArgIndex(final Target target, final Type[] array) {
        if (this.index > -1) {
            if (this.index >= array.length || !array[this.index].equals((Object)this.returnType)) {
                throw new InvalidInjectionException(this.info, "Specified index " + this.index + " for @ModifyArg is invalid for args " + Bytecode.getDescriptor(array) + ", expected " + this.returnType + " on " + this);
            }
            return this.index;
        }
        else {
            int i = -1;
            for (int j = 0; j < array.length; ++j) {
                if (array[j].equals((Object)this.returnType)) {
                    if (i != -1) {
                        throw new InvalidInjectionException(this.info, "Found duplicate args with index [" + i + ", " + j + "] matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this + ". Please specify index of desired arg.");
                    }
                    i = j;
                }
            }
            if (i == -1) {
                throw new InvalidInjectionException(this.info, "Could not find arg matching type " + this.returnType + " for @ModifyArg target " + target + " in " + this);
            }
            return i;
        }
    }
}

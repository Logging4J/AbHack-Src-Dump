//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke;

import org.spongepowered.asm.mixin.injection.invoke.arg.*;
import org.spongepowered.asm.mixin.transformer.ext.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.asm.lib.*;
import org.spongepowered.asm.mixin.injection.throwables.*;
import org.spongepowered.asm.util.*;
import org.spongepowered.asm.lib.tree.*;

public class ModifyArgsInjector extends InvokeInjector
{
    private final ArgsClassGenerator argsClassGenerator;
    
    public ModifyArgsInjector(final InjectionInfo injectionInfo) {
        super(injectionInfo, "@ModifyArgs");
        this.argsClassGenerator = injectionInfo.getContext().getExtensions().getGenerator((Class<? extends IClassGenerator>)ArgsClassGenerator.class);
    }
    
    protected void checkTarget(final Target target) {
        this.checkTargetModifiers(target, false);
    }
    
    protected void inject(final Target target, final InjectionNodes.InjectionNode injectionNode) {
        this.checkTargetForNode(target, injectionNode);
        super.inject(target, injectionNode);
    }
    
    protected void injectAtInvoke(final Target target, final InjectionNodes.InjectionNode injectionNode) {
        final MethodInsnNode methodInsnNode = (MethodInsnNode)injectionNode.getCurrentTarget();
        final Type[] argumentTypes = Type.getArgumentTypes(methodInsnNode.desc);
        if (argumentTypes.length == 0) {
            throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " targets a method invocation " + methodInsnNode.name + methodInsnNode.desc + " with no arguments!");
        }
        final String classRef = this.argsClassGenerator.getClassRef(methodInsnNode.desc);
        final boolean verifyTarget = this.verifyTarget(target);
        final InsnList list = new InsnList();
        target.addToStack(1);
        this.packArgs(list, classRef, methodInsnNode);
        if (verifyTarget) {
            target.addToStack(Bytecode.getArgsSize(target.arguments));
            Bytecode.loadArgs(target.arguments, list, target.isStatic ? 0 : 1);
        }
        this.invokeHandler(list);
        this.unpackArgs(list, classRef, argumentTypes);
        target.insns.insertBefore((AbstractInsnNode)methodInsnNode, list);
    }
    
    private boolean verifyTarget(final Target target) {
        final String format = String.format("(L%s;)V", ArgsClassGenerator.ARGS_REF);
        if (this.methodNode.desc.equals(format)) {
            return false;
        }
        final String format2 = String.format("(L%s;%s", ArgsClassGenerator.ARGS_REF, Bytecode.changeDescriptorReturnType(target.method.desc, "V").substring(1));
        if (this.methodNode.desc.equals(format2)) {
            return true;
        }
        throw new InvalidInjectionException(this.info, "@ModifyArgs injector " + this + " has an invalid signature " + this.methodNode.desc + ", expected " + format + " or " + format2);
    }
    
    private void packArgs(final InsnList list, final String str, final MethodInsnNode methodInsnNode) {
        list.add((AbstractInsnNode)new MethodInsnNode(184, str, "of", Bytecode.changeDescriptorReturnType(methodInsnNode.desc, "L" + str + ";"), false));
        list.add((AbstractInsnNode)new InsnNode(89));
        if (!this.isStatic) {
            list.add((AbstractInsnNode)new VarInsnNode(25, 0));
            list.add((AbstractInsnNode)new InsnNode(95));
        }
    }
    
    private void unpackArgs(final InsnList list, final String s, final Type[] array) {
        for (int i = 0; i < array.length; ++i) {
            if (i < array.length - 1) {
                list.add((AbstractInsnNode)new InsnNode(89));
            }
            list.add((AbstractInsnNode)new MethodInsnNode(182, s, "$" + i, "()" + array[i].getDescriptor(), false));
            if (i < array.length - 1) {
                if (array[i].getSize() == 1) {
                    list.add((AbstractInsnNode)new InsnNode(95));
                }
                else {
                    list.add((AbstractInsnNode)new InsnNode(93));
                    list.add((AbstractInsnNode)new InsnNode(88));
                }
            }
        }
    }
}

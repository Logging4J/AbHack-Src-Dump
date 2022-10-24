//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.gen;

import org.spongepowered.asm.lib.tree.*;

public class AccessorGeneratorFieldSetter extends AccessorGeneratorField
{
    public AccessorGeneratorFieldSetter(final AccessorInfo accessorInfo) {
        super(accessorInfo);
    }
    
    public MethodNode generate() {
        final int isInstanceField = this.isInstanceField ? 1 : 0;
        final MethodNode method = this.createMethod(isInstanceField + this.targetType.getSize(), isInstanceField + this.targetType.getSize());
        if (this.isInstanceField) {
            method.instructions.add((AbstractInsnNode)new VarInsnNode(25, 0));
        }
        method.instructions.add((AbstractInsnNode)new VarInsnNode(this.targetType.getOpcode(21), isInstanceField));
        method.instructions.add((AbstractInsnNode)new FieldInsnNode(this.isInstanceField ? 181 : 179, this.info.getClassNode().name, this.targetField.name, this.targetField.desc));
        method.instructions.add((AbstractInsnNode)new InsnNode(177));
        return method;
    }
}

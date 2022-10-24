//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class TypeInsnNode extends AbstractInsnNode
{
    public String desc;
    
    public TypeInsnNode(final int n, final String desc) {
        super(n);
        this.desc = desc;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    public int getType() {
        return 3;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitTypeInsn(this.opcode, this.desc);
        this.acceptAnnotations(methodVisitor);
    }
    
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> map) {
        return new TypeInsnNode(this.opcode, this.desc).cloneAnnotations((AbstractInsnNode)this);
    }
}

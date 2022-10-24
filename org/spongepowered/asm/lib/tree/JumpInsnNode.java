//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class JumpInsnNode extends AbstractInsnNode
{
    public LabelNode label;
    
    public JumpInsnNode(final int n, final LabelNode label) {
        super(n);
        this.label = label;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    public int getType() {
        return 7;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitJumpInsn(this.opcode, this.label.getLabel());
        this.acceptAnnotations(methodVisitor);
    }
    
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> map) {
        return new JumpInsnNode(this.opcode, clone(this.label, (Map)map)).cloneAnnotations((AbstractInsnNode)this);
    }
}

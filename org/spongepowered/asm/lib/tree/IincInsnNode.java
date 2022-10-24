//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class IincInsnNode extends AbstractInsnNode
{
    public int var;
    public int incr;
    
    public IincInsnNode(final int var, final int incr) {
        super(132);
        this.var = var;
        this.incr = incr;
    }
    
    public int getType() {
        return 10;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitIincInsn(this.var, this.incr);
        this.acceptAnnotations(methodVisitor);
    }
    
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> map) {
        return new IincInsnNode(this.var, this.incr).cloneAnnotations((AbstractInsnNode)this);
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree;

import org.spongepowered.asm.lib.*;
import java.util.*;

public class MethodInsnNode extends AbstractInsnNode
{
    public String owner;
    public String name;
    public String desc;
    public boolean itf;
    
    @Deprecated
    public MethodInsnNode(final int n, final String s, final String s2, final String s3) {
        this(n, s, s2, s3, n == 185);
    }
    
    public MethodInsnNode(final int n, final String owner, final String name, final String desc, final boolean itf) {
        super(n);
        this.owner = owner;
        this.name = name;
        this.desc = desc;
        this.itf = itf;
    }
    
    public void setOpcode(final int opcode) {
        this.opcode = opcode;
    }
    
    public int getType() {
        return 5;
    }
    
    public void accept(final MethodVisitor methodVisitor) {
        methodVisitor.visitMethodInsn(this.opcode, this.owner, this.name, this.desc, this.itf);
        this.acceptAnnotations(methodVisitor);
    }
    
    public AbstractInsnNode clone(final Map<LabelNode, LabelNode> map) {
        return new MethodInsnNode(this.opcode, this.owner, this.name, this.desc, this.itf);
    }
}

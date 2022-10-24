//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree.analysis;

import java.util.*;
import org.spongepowered.asm.lib.tree.*;

public class SourceValue implements Value
{
    public final int size;
    public final Set<AbstractInsnNode> insns;
    
    public SourceValue(final int n) {
        this(n, SmallSet.emptySet());
    }
    
    public SourceValue(final int size, final AbstractInsnNode abstractInsnNode) {
        this.size = size;
        this.insns = (Set<AbstractInsnNode>)new SmallSet((Object)abstractInsnNode, (Object)null);
    }
    
    public SourceValue(final int size, final Set<AbstractInsnNode> insns) {
        this.size = size;
        this.insns = insns;
    }
    
    public int getSize() {
        return this.size;
    }
    
    @Override
    public boolean equals(final Object o) {
        if (!(o instanceof SourceValue)) {
            return false;
        }
        final SourceValue sourceValue = (SourceValue)o;
        return this.size == sourceValue.size && this.insns.equals(sourceValue.insns);
    }
    
    @Override
    public int hashCode() {
        return this.insns.hashCode();
    }
}

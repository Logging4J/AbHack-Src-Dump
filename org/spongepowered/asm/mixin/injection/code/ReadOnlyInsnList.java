//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.code;

import org.spongepowered.asm.lib.tree.*;
import java.util.*;

class ReadOnlyInsnList extends InsnList
{
    private InsnList insnList;
    
    public ReadOnlyInsnList(final InsnList insnList) {
        this.insnList = insnList;
    }
    
    void dispose() {
        this.insnList = null;
    }
    
    public final void set(final AbstractInsnNode abstractInsnNode, final AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }
    
    public final void add(final AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }
    
    public final void add(final InsnList list) {
        throw new UnsupportedOperationException();
    }
    
    public final void insert(final AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }
    
    public final void insert(final InsnList list) {
        throw new UnsupportedOperationException();
    }
    
    public final void insert(final AbstractInsnNode abstractInsnNode, final AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }
    
    public final void insert(final AbstractInsnNode abstractInsnNode, final InsnList list) {
        throw new UnsupportedOperationException();
    }
    
    public final void insertBefore(final AbstractInsnNode abstractInsnNode, final AbstractInsnNode abstractInsnNode2) {
        throw new UnsupportedOperationException();
    }
    
    public final void insertBefore(final AbstractInsnNode abstractInsnNode, final InsnList list) {
        throw new UnsupportedOperationException();
    }
    
    public final void remove(final AbstractInsnNode abstractInsnNode) {
        throw new UnsupportedOperationException();
    }
    
    public AbstractInsnNode[] toArray() {
        return this.insnList.toArray();
    }
    
    public int size() {
        return this.insnList.size();
    }
    
    public AbstractInsnNode getFirst() {
        return this.insnList.getFirst();
    }
    
    public AbstractInsnNode getLast() {
        return this.insnList.getLast();
    }
    
    public AbstractInsnNode get(final int n) {
        return this.insnList.get(n);
    }
    
    public boolean contains(final AbstractInsnNode abstractInsnNode) {
        return this.insnList.contains(abstractInsnNode);
    }
    
    public int indexOf(final AbstractInsnNode abstractInsnNode) {
        return this.insnList.indexOf(abstractInsnNode);
    }
    
    public ListIterator<AbstractInsnNode> iterator() {
        return (ListIterator<AbstractInsnNode>)this.insnList.iterator();
    }
    
    public ListIterator<AbstractInsnNode> iterator(final int n) {
        return (ListIterator<AbstractInsnNode>)this.insnList.iterator(n);
    }
    
    public final void resetLabels() {
        this.insnList.resetLabels();
    }
}

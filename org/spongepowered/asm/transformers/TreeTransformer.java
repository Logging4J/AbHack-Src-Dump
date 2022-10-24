//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.transformers;

import org.spongepowered.asm.service.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;

public abstract class TreeTransformer implements ILegacyClassTransformer
{
    private ClassReader classReader;
    private ClassNode classNode;
    
    protected final ClassNode readClass(final byte[] array) {
        return this.readClass(array, true);
    }
    
    protected final ClassNode readClass(final byte[] array, final boolean b) {
        final ClassReader classReader = new ClassReader(array);
        if (b) {
            this.classReader = classReader;
        }
        final ClassNode classNode = new ClassNode();
        classReader.accept((ClassVisitor)classNode, 8);
        return classNode;
    }
    
    protected final byte[] writeClass(final ClassNode classNode) {
        if (this.classReader != null && this.classNode == classNode) {
            this.classNode = null;
            final MixinClassWriter mixinClassWriter = new MixinClassWriter(this.classReader, 3);
            this.classReader = null;
            classNode.accept((ClassVisitor)mixinClassWriter);
            return ((ClassWriter)mixinClassWriter).toByteArray();
        }
        this.classNode = null;
        final MixinClassWriter mixinClassWriter2 = new MixinClassWriter(3);
        classNode.accept((ClassVisitor)mixinClassWriter2);
        return ((ClassWriter)mixinClassWriter2).toByteArray();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.lib.tree.analysis;

import org.spongepowered.asm.lib.tree.*;

public class AnalyzerException extends Exception
{
    public final AbstractInsnNode node;
    
    public AnalyzerException(final AbstractInsnNode node, final String message) {
        super(message);
        this.node = node;
    }
    
    public AnalyzerException(final AbstractInsnNode node, final String message, final Throwable cause) {
        super(message, cause);
        this.node = node;
    }
    
    public AnalyzerException(final AbstractInsnNode node, final String str, final Object obj, final Value obj2) {
        super(((str == null) ? "Expected " : (str + ": expected ")) + obj + ", but found " + obj2);
        this.node = node;
    }
}

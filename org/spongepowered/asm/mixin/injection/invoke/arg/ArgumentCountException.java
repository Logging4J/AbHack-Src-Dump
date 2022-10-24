//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.invoke.arg;

public class ArgumentCountException extends IllegalArgumentException
{
    private static final long serialVersionUID = 1L;
    
    public ArgumentCountException(final int i, final int j, final String str) {
        super("Invalid number of arguments for setAll, received " + i + " but expected " + j + ": " + str);
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.util;

public final class Counter
{
    public int value;
    
    @Override
    public boolean equals(final Object o) {
        return o != null && o.getClass() == Counter.class && ((Counter)o).value == this.value;
    }
    
    @Override
    public int hashCode() {
        return this.value;
    }
}

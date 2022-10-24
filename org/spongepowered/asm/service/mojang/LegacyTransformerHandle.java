//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.service.mojang;

import org.spongepowered.asm.service.*;
import net.minecraft.launchwrapper.*;
import javax.annotation.*;

class LegacyTransformerHandle implements ILegacyClassTransformer
{
    private final IClassTransformer transformer;
    
    LegacyTransformerHandle(final IClassTransformer transformer) {
        this.transformer = transformer;
    }
    
    public String getName() {
        return this.transformer.getClass().getName();
    }
    
    public boolean isDelegationExcluded() {
        return this.transformer.getClass().getAnnotation(Resource.class) != null;
    }
    
    public byte[] transformClassBytes(final String s, final String s2, final byte[] array) {
        return this.transformer.transform(s, s2, array);
    }
}

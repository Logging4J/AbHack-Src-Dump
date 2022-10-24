//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.obfuscation;

import org.spongepowered.asm.mixin.extensibility.*;
import java.util.*;

public class RemapperChain implements IRemapper
{
    private final List<IRemapper> remappers;
    
    public RemapperChain() {
        this.remappers = new ArrayList<IRemapper>();
    }
    
    @Override
    public String toString() {
        return String.format("RemapperChain[%d]", this.remappers.size());
    }
    
    public RemapperChain add(final IRemapper remapper) {
        this.remappers.add(remapper);
        return this;
    }
    
    public String mapMethodName(final String s, String anObject, final String s2) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String mapMethodName = iterator.next().mapMethodName(s, anObject, s2);
            if (mapMethodName != null && !mapMethodName.equals(anObject)) {
                anObject = mapMethodName;
            }
        }
        return anObject;
    }
    
    public String mapFieldName(final String s, String anObject, final String s2) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String mapFieldName = iterator.next().mapFieldName(s, anObject, s2);
            if (mapFieldName != null && !mapFieldName.equals(anObject)) {
                anObject = mapFieldName;
            }
        }
        return anObject;
    }
    
    public String map(String anObject) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String map = iterator.next().map(anObject);
            if (map != null && !map.equals(anObject)) {
                anObject = map;
            }
        }
        return anObject;
    }
    
    public String unmap(String anObject) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String unmap = iterator.next().unmap(anObject);
            if (unmap != null && !unmap.equals(anObject)) {
                anObject = unmap;
            }
        }
        return anObject;
    }
    
    public String mapDesc(String anObject) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String mapDesc = iterator.next().mapDesc(anObject);
            if (mapDesc != null && !mapDesc.equals(anObject)) {
                anObject = mapDesc;
            }
        }
        return anObject;
    }
    
    public String unmapDesc(String anObject) {
        final Iterator<IRemapper> iterator = this.remappers.iterator();
        while (iterator.hasNext()) {
            final String unmapDesc = iterator.next().unmapDesc(anObject);
            if (unmapDesc != null && !unmapDesc.equals(anObject)) {
                anObject = unmapDesc;
            }
        }
        return anObject;
    }
}

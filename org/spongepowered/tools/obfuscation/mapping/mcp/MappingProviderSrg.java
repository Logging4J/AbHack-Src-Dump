//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation.mapping.mcp;

import org.spongepowered.tools.obfuscation.mapping.common.*;
import javax.annotation.processing.*;
import java.nio.charset.*;
import com.google.common.collect.*;
import com.google.common.base.*;
import org.spongepowered.asm.obfuscation.mapping.mcp.*;
import org.spongepowered.asm.mixin.throwables.*;
import java.io.*;
import com.google.common.io.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class MappingProviderSrg extends MappingProvider
{
    public MappingProviderSrg(final Messager messager, final Filer filer) {
        super(messager, filer);
    }
    
    public void read(final File file) throws IOException {
        Files.readLines(file, Charset.defaultCharset(), (LineProcessor)new LineProcessor<String>() {
            final /* synthetic */ BiMap val$packageMap = MappingProviderSrg.this.packageMap;
            final /* synthetic */ BiMap val$classMap = MappingProviderSrg.this.classMap;
            final /* synthetic */ BiMap val$fieldMap = MappingProviderSrg.this.fieldMap;
            final /* synthetic */ BiMap val$methodMap = MappingProviderSrg.this.methodMap;
            
            public String getResult() {
                return null;
            }
            
            public boolean processLine(final String s) throws IOException {
                if (Strings.isNullOrEmpty(s) || s.startsWith("#")) {
                    return true;
                }
                final String substring = s.substring(0, 2);
                final String[] split = s.substring(4).split(" ");
                if (substring.equals("PK")) {
                    this.val$packageMap.forcePut((Object)split[0], (Object)split[1]);
                }
                else if (substring.equals("CL")) {
                    this.val$classMap.forcePut((Object)split[0], (Object)split[1]);
                }
                else if (substring.equals("FD")) {
                    this.val$fieldMap.forcePut((Object)new MappingFieldSrg(split[0]).copy(), (Object)new MappingFieldSrg(split[1]).copy());
                }
                else {
                    if (!substring.equals("MD")) {
                        throw new MixinException("Invalid SRG file: " + file);
                    }
                    this.val$methodMap.forcePut((Object)new MappingMethod(split[0], split[1]), (Object)new MappingMethod(split[2], split[3]));
                }
                return true;
            }
        });
    }
    
    public MappingField getFieldMapping(MappingField mappingField) {
        if (mappingField.getDesc() != null) {
            mappingField = (MappingField)new MappingFieldSrg(mappingField);
        }
        return (MappingField)this.fieldMap.get((Object)mappingField);
    }
}

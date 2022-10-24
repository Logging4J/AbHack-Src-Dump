//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.service.*;
import org.spongepowered.tools.obfuscation.interfaces.*;
import java.lang.reflect.*;
import com.google.common.collect.*;
import java.util.*;

public final class ObfuscationType
{
    private static final Map<String, ObfuscationType> types;
    private final String key;
    private final ObfuscationTypeDescriptor descriptor;
    private final IMixinAnnotationProcessor ap;
    private final IOptionProvider options;
    
    private ObfuscationType(final ObfuscationTypeDescriptor descriptor, final IMixinAnnotationProcessor mixinAnnotationProcessor) {
        this.key = descriptor.getKey();
        this.descriptor = descriptor;
        this.ap = mixinAnnotationProcessor;
        this.options = (IOptionProvider)mixinAnnotationProcessor;
    }
    
    public final ObfuscationEnvironment createEnvironment() {
        try {
            final Constructor<? extends ObfuscationEnvironment> declaredConstructor = this.descriptor.getEnvironmentType().getDeclaredConstructor(ObfuscationType.class);
            declaredConstructor.setAccessible(true);
            return (ObfuscationEnvironment)declaredConstructor.newInstance(this);
        }
        catch (Exception cause) {
            throw new RuntimeException(cause);
        }
    }
    
    @Override
    public String toString() {
        return this.key;
    }
    
    public String getKey() {
        return this.key;
    }
    
    public ObfuscationTypeDescriptor getConfig() {
        return this.descriptor;
    }
    
    public IMixinAnnotationProcessor getAnnotationProcessor() {
        return this.ap;
    }
    
    public boolean isDefault() {
        final String option = this.options.getOption("defaultObfuscationEnv");
        return (option == null && this.key.equals("searge")) || (option != null && this.key.equals(option.toLowerCase()));
    }
    
    public boolean isSupported() {
        return this.getInputFileNames().size() > 0;
    }
    
    public List<String> getInputFileNames() {
        final ImmutableList.Builder builder = ImmutableList.builder();
        final String option = this.options.getOption(this.descriptor.getInputFileOption());
        if (option != null) {
            builder.add((Object)option);
        }
        final String option2 = this.options.getOption(this.descriptor.getExtraInputFilesOption());
        if (option2 != null) {
            final String[] split = option2.split(";");
            for (int length = split.length, i = 0; i < length; ++i) {
                builder.add((Object)split[i].trim());
            }
        }
        return (List<String>)builder.build();
    }
    
    public String getOutputFileName() {
        return this.options.getOption(this.descriptor.getOutputFileOption());
    }
    
    public static Iterable<ObfuscationType> types() {
        return ObfuscationType.types.values();
    }
    
    public static ObfuscationType create(final ObfuscationTypeDescriptor obfuscationTypeDescriptor, final IMixinAnnotationProcessor mixinAnnotationProcessor) {
        final String key = obfuscationTypeDescriptor.getKey();
        if (ObfuscationType.types.containsKey(key)) {
            throw new IllegalArgumentException("Obfuscation type with key " + key + " was already registered");
        }
        final ObfuscationType obfuscationType = new ObfuscationType(obfuscationTypeDescriptor, mixinAnnotationProcessor);
        ObfuscationType.types.put(key, obfuscationType);
        return obfuscationType;
    }
    
    public static ObfuscationType get(final String str) {
        final ObfuscationType obfuscationType = ObfuscationType.types.get(str);
        if (obfuscationType == null) {
            throw new IllegalArgumentException("Obfuscation type with key " + str + " was not registered");
        }
        return obfuscationType;
    }
    
    static {
        types = new LinkedHashMap<String, ObfuscationType>();
    }
}

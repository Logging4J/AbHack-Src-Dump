//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.mapping.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.*;

class Mappings implements IMappingConsumer
{
    private final Map<ObfuscationType, IMappingConsumer.MappingSet<MappingField>> fieldMappings;
    private final Map<ObfuscationType, IMappingConsumer.MappingSet<MappingMethod>> methodMappings;
    private UniqueMappings unique;
    
    public Mappings() {
        this.fieldMappings = new HashMap<ObfuscationType, IMappingConsumer.MappingSet<MappingField>>();
        this.methodMappings = new HashMap<ObfuscationType, IMappingConsumer.MappingSet<MappingMethod>>();
        this.init();
    }
    
    private void init() {
        for (final ObfuscationType obfuscationType : ObfuscationType.types()) {
            this.fieldMappings.put(obfuscationType, (IMappingConsumer.MappingSet<MappingField>)new IMappingConsumer.MappingSet());
            this.methodMappings.put(obfuscationType, (IMappingConsumer.MappingSet<MappingMethod>)new IMappingConsumer.MappingSet());
        }
    }
    
    public IMappingConsumer asUnique() {
        if (this.unique == null) {
            this.unique = new UniqueMappings((IMappingConsumer)this);
        }
        return (IMappingConsumer)this.unique;
    }
    
    public IMappingConsumer.MappingSet<MappingField> getFieldMappings(final ObfuscationType obfuscationType) {
        final IMappingConsumer.MappingSet<MappingField> set = this.fieldMappings.get(obfuscationType);
        return (IMappingConsumer.MappingSet<MappingField>)((set != null) ? set : new IMappingConsumer.MappingSet());
    }
    
    public IMappingConsumer.MappingSet<MappingMethod> getMethodMappings(final ObfuscationType obfuscationType) {
        final IMappingConsumer.MappingSet<MappingMethod> set = this.methodMappings.get(obfuscationType);
        return (IMappingConsumer.MappingSet<MappingMethod>)((set != null) ? set : new IMappingConsumer.MappingSet());
    }
    
    public void clear() {
        this.fieldMappings.clear();
        this.methodMappings.clear();
        if (this.unique != null) {
            this.unique.clearMaps();
        }
        this.init();
    }
    
    public void addFieldMapping(final ObfuscationType obfuscationType, final MappingField mappingField, final MappingField mappingField2) {
        IMappingConsumer.MappingSet set = this.fieldMappings.get(obfuscationType);
        if (set == null) {
            set = new IMappingConsumer.MappingSet();
            this.fieldMappings.put(obfuscationType, (IMappingConsumer.MappingSet<MappingField>)set);
        }
        set.add((Object)new IMappingConsumer.MappingSet.Pair((IMapping)mappingField, (IMapping)mappingField2));
    }
    
    public void addMethodMapping(final ObfuscationType obfuscationType, final MappingMethod mappingMethod, final MappingMethod mappingMethod2) {
        IMappingConsumer.MappingSet set = this.methodMappings.get(obfuscationType);
        if (set == null) {
            set = new IMappingConsumer.MappingSet();
            this.methodMappings.put(obfuscationType, (IMappingConsumer.MappingSet<MappingMethod>)set);
        }
        set.add((Object)new IMappingConsumer.MappingSet.Pair((IMapping)mappingMethod, (IMapping)mappingMethod2));
    }
    
    public static class MappingConflictException extends RuntimeException
    {
        private static final long serialVersionUID = 1L;
        private final IMapping<?> oldMapping;
        private final IMapping<?> newMapping;
        
        public MappingConflictException(final IMapping<?> oldMapping, final IMapping<?> newMapping) {
            this.oldMapping = oldMapping;
            this.newMapping = newMapping;
        }
        
        public IMapping<?> getOld() {
            return this.oldMapping;
        }
        
        public IMapping<?> getNew() {
            return this.newMapping;
        }
    }
    
    static class UniqueMappings implements IMappingConsumer
    {
        private final IMappingConsumer mappings;
        private final Map<ObfuscationType, Map<MappingField, MappingField>> fields;
        private final Map<ObfuscationType, Map<MappingMethod, MappingMethod>> methods;
        
        public UniqueMappings(final IMappingConsumer mappings) {
            this.fields = new HashMap<ObfuscationType, Map<MappingField, MappingField>>();
            this.methods = new HashMap<ObfuscationType, Map<MappingMethod, MappingMethod>>();
            this.mappings = mappings;
        }
        
        public void clear() {
            this.clearMaps();
            this.mappings.clear();
        }
        
        protected void clearMaps() {
            this.fields.clear();
            this.methods.clear();
        }
        
        public void addFieldMapping(final ObfuscationType obfuscationType, final MappingField mappingField, final MappingField mappingField2) {
            if (!this.checkForExistingMapping(obfuscationType, mappingField, mappingField2, this.fields)) {
                this.mappings.addFieldMapping(obfuscationType, mappingField, mappingField2);
            }
        }
        
        public void addMethodMapping(final ObfuscationType obfuscationType, final MappingMethod mappingMethod, final MappingMethod mappingMethod2) {
            if (!this.checkForExistingMapping(obfuscationType, mappingMethod, mappingMethod2, this.methods)) {
                this.mappings.addMethodMapping(obfuscationType, mappingMethod, mappingMethod2);
            }
        }
        
        private <TMapping extends IMapping<TMapping>> boolean checkForExistingMapping(final ObfuscationType obfuscationType, final TMapping tMapping, final TMapping obj, final Map<ObfuscationType, Map<TMapping, TMapping>> map) throws MappingConflictException {
            Map<TMapping, TMapping> map2 = map.get(obfuscationType);
            if (map2 == null) {
                map2 = new HashMap<TMapping, TMapping>();
                map.put(obfuscationType, map2);
            }
            final IMapping<TMapping> mapping = map2.get(tMapping);
            if (mapping == null) {
                map2.put(tMapping, obj);
                return false;
            }
            if (mapping.equals(obj)) {
                return true;
            }
            throw new MappingConflictException(mapping, obj);
        }
        
        public IMappingConsumer.MappingSet<MappingField> getFieldMappings(final ObfuscationType obfuscationType) {
            return (IMappingConsumer.MappingSet<MappingField>)this.mappings.getFieldMappings(obfuscationType);
        }
        
        public IMappingConsumer.MappingSet<MappingMethod> getMethodMappings(final ObfuscationType obfuscationType) {
            return (IMappingConsumer.MappingSet<MappingMethod>)this.mappings.getMethodMappings(obfuscationType);
        }
    }
}

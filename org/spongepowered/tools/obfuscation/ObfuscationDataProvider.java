//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.obfuscation;

import org.spongepowered.tools.obfuscation.interfaces.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import org.spongepowered.tools.obfuscation.mirror.*;
import java.util.*;
import org.spongepowered.asm.obfuscation.mapping.*;
import org.spongepowered.asm.obfuscation.mapping.common.*;

public class ObfuscationDataProvider implements IObfuscationDataProvider
{
    private final IMixinAnnotationProcessor ap;
    private final List<ObfuscationEnvironment> environments;
    
    public ObfuscationDataProvider(final IMixinAnnotationProcessor ap, final List<ObfuscationEnvironment> environments) {
        this.ap = ap;
        this.environments = environments;
    }
    
    public <T> ObfuscationData<T> getObfEntryRecursive(final MemberInfo memberInfo) {
        MemberInfo move = memberInfo;
        final ObfuscationData<String> obfClass = this.getObfClass(move.owner);
        org.spongepowered.tools.obfuscation.ObfuscationData<Object> obfuscationData = this.getObfEntry(move);
        try {
            while (obfuscationData.isEmpty()) {
                final TypeHandle typeHandle = this.ap.getTypeProvider().getTypeHandle(move.owner);
                if (typeHandle == null) {
                    return (ObfuscationData<T>)obfuscationData;
                }
                final TypeHandle superclass = typeHandle.getSuperclass();
                obfuscationData = this.getObfEntryUsing(move, superclass);
                if (!obfuscationData.isEmpty()) {
                    return applyParents(obfClass, (org.spongepowered.tools.obfuscation.ObfuscationData<T>)obfuscationData);
                }
                final Iterator iterator = typeHandle.getInterfaces().iterator();
                while (iterator.hasNext()) {
                    obfuscationData = this.getObfEntryUsing(move, iterator.next());
                    if (!obfuscationData.isEmpty()) {
                        return applyParents(obfClass, (org.spongepowered.tools.obfuscation.ObfuscationData<T>)obfuscationData);
                    }
                }
                if (superclass == null) {
                    break;
                }
                move = move.move(superclass.getName());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return (ObfuscationData<T>)this.getObfEntry(memberInfo);
        }
        return (ObfuscationData<T>)obfuscationData;
    }
    
    private <T> ObfuscationData<T> getObfEntryUsing(final MemberInfo memberInfo, final TypeHandle typeHandle) {
        return (ObfuscationData<T>)((typeHandle == null) ? new ObfuscationData() : this.getObfEntry(memberInfo.move(typeHandle.getName())));
    }
    
    public <T> ObfuscationData<T> getObfEntry(final MemberInfo memberInfo) {
        if (memberInfo.isField()) {
            return (ObfuscationData<T>)this.getObfField(memberInfo);
        }
        return (ObfuscationData<T>)this.getObfMethod(memberInfo.asMethodMapping());
    }
    
    public <T> ObfuscationData<T> getObfEntry(final IMapping<T> mapping) {
        if (mapping != null) {
            if (mapping.getType() == IMapping.Type.FIELD) {
                return (ObfuscationData<T>)this.getObfField((MappingField)mapping);
            }
            if (mapping.getType() == IMapping.Type.METHOD) {
                return (ObfuscationData<T>)this.getObfMethod((MappingMethod)mapping);
            }
        }
        return (ObfuscationData<T>)new ObfuscationData();
    }
    
    public ObfuscationData<MappingMethod> getObfMethodRecursive(final MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }
    
    public ObfuscationData<MappingMethod> getObfMethod(final MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, memberInfo.isConstructor());
    }
    
    public ObfuscationData<MappingMethod> getRemappedMethod(final MemberInfo memberInfo) {
        return this.getRemappedMethod(memberInfo, true);
    }
    
    private ObfuscationData<MappingMethod> getRemappedMethod(final MemberInfo memberInfo, final boolean b) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod obfMethod = obfuscationEnvironment.getObfMethod(memberInfo);
            if (obfMethod != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfMethod);
            }
        }
        if (!obfuscationData.isEmpty() || !b) {
            return (ObfuscationData<MappingMethod>)obfuscationData;
        }
        return this.remapDescriptor((ObfuscationData<MappingMethod>)obfuscationData, memberInfo);
    }
    
    public ObfuscationData<MappingMethod> getObfMethod(final MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, mappingMethod.isConstructor());
    }
    
    public ObfuscationData<MappingMethod> getRemappedMethod(final MappingMethod mappingMethod) {
        return this.getRemappedMethod(mappingMethod, true);
    }
    
    private ObfuscationData<MappingMethod> getRemappedMethod(final MappingMethod mappingMethod, final boolean b) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MappingMethod obfMethod = obfuscationEnvironment.getObfMethod(mappingMethod);
            if (obfMethod != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfMethod);
            }
        }
        if (!obfuscationData.isEmpty() || !b) {
            return (ObfuscationData<MappingMethod>)obfuscationData;
        }
        return this.remapDescriptor((ObfuscationData<MappingMethod>)obfuscationData, new MemberInfo((IMapping)mappingMethod));
    }
    
    public ObfuscationData<MappingMethod> remapDescriptor(final ObfuscationData<MappingMethod> obfuscationData, final MemberInfo memberInfo) {
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final MemberInfo remapDescriptor = obfuscationEnvironment.remapDescriptor(memberInfo);
            if (remapDescriptor != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)remapDescriptor.asMethodMapping());
            }
        }
        return obfuscationData;
    }
    
    public ObfuscationData<MappingField> getObfFieldRecursive(final MemberInfo memberInfo) {
        return this.getObfEntryRecursive(memberInfo);
    }
    
    public ObfuscationData<MappingField> getObfField(final MemberInfo memberInfo) {
        return this.getObfField(memberInfo.asFieldMapping());
    }
    
    public ObfuscationData<MappingField> getObfField(final MappingField mappingField) {
        final ObfuscationData obfuscationData = new ObfuscationData();
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            MappingField mappingField2 = obfuscationEnvironment.getObfField(mappingField);
            if (mappingField2 != null) {
                if (mappingField2.getDesc() == null && mappingField.getDesc() != null) {
                    mappingField2 = mappingField2.transform(obfuscationEnvironment.remapDescriptor(mappingField.getDesc()));
                }
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)mappingField2);
            }
        }
        return (ObfuscationData<MappingField>)obfuscationData;
    }
    
    public ObfuscationData<String> getObfClass(final TypeHandle typeHandle) {
        return this.getObfClass(typeHandle.getName());
    }
    
    public ObfuscationData<String> getObfClass(final String s) {
        final ObfuscationData obfuscationData = new ObfuscationData((Object)s);
        for (final ObfuscationEnvironment obfuscationEnvironment : this.environments) {
            final String obfClass = obfuscationEnvironment.getObfClass(s);
            if (obfClass != null) {
                obfuscationData.put(obfuscationEnvironment.getType(), (Object)obfClass);
            }
        }
        return (ObfuscationData<String>)obfuscationData;
    }
    
    private static <T> ObfuscationData<T> applyParents(final ObfuscationData<String> obfuscationData, final ObfuscationData<T> obfuscationData2) {
        for (final ObfuscationType obfuscationType : obfuscationData2) {
            obfuscationData2.put(obfuscationType, (Object)MemberInfo.fromMapping((IMapping)obfuscationData2.get(obfuscationType)).move((String)obfuscationData.get(obfuscationType)).asMapping());
        }
        return obfuscationData2;
    }
}

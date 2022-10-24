//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.mixin.injection.points;

import org.spongepowered.asm.mixin.injection.*;
import com.google.common.base.*;
import org.spongepowered.asm.mixin.injection.struct.*;
import java.util.*;
import org.spongepowered.asm.lib.tree.*;

@InjectionPoint.AtCode("NEW")
public class BeforeNew extends InjectionPoint
{
    private final String target;
    private final String desc;
    private final int ordinal;
    
    public BeforeNew(final InjectionPointData injectionPointData) {
        super(injectionPointData);
        this.ordinal = injectionPointData.getOrdinal();
        final MemberInfo andValidate = MemberInfo.parseAndValidate(Strings.emptyToNull(injectionPointData.get("class", injectionPointData.get("target", "")).replace('.', '/')), injectionPointData.getContext());
        this.target = andValidate.toCtorType();
        this.desc = andValidate.toCtorDesc();
    }
    
    public boolean hasDescriptor() {
        return this.desc != null;
    }
    
    public boolean find(final String s, final InsnList list, final Collection<AbstractInsnNode> collection) {
        boolean b = false;
        int n = 0;
        final ArrayList<TypeInsnNode> list2 = new ArrayList<TypeInsnNode>();
        final ArrayList<AbstractInsnNode> list3 = (ArrayList<AbstractInsnNode>)((this.desc != null) ? list2 : collection);
        for (final AbstractInsnNode abstractInsnNode : list) {
            if (abstractInsnNode instanceof TypeInsnNode && abstractInsnNode.getOpcode() == 187 && this.matchesOwner((TypeInsnNode)abstractInsnNode)) {
                if (this.ordinal == -1 || this.ordinal == n) {
                    list3.add(abstractInsnNode);
                    b = (this.desc == null);
                }
                ++n;
            }
        }
        if (this.desc != null) {
            for (final TypeInsnNode typeInsnNode : list2) {
                if (this.findCtor(list, typeInsnNode)) {
                    collection.add((AbstractInsnNode)typeInsnNode);
                    b = true;
                }
            }
        }
        return b;
    }
    
    protected boolean findCtor(final InsnList list, final TypeInsnNode typeInsnNode) {
        final ListIterator iterator = list.iterator(list.indexOf((AbstractInsnNode)typeInsnNode));
        while (iterator.hasNext()) {
            final AbstractInsnNode abstractInsnNode = iterator.next();
            if (abstractInsnNode instanceof MethodInsnNode && abstractInsnNode.getOpcode() == 183) {
                final MethodInsnNode methodInsnNode = (MethodInsnNode)abstractInsnNode;
                if ("<init>".equals(methodInsnNode.name) && methodInsnNode.owner.equals(typeInsnNode.desc) && methodInsnNode.desc.equals(this.desc)) {
                    return true;
                }
                continue;
            }
        }
        return false;
    }
    
    private boolean matchesOwner(final TypeInsnNode typeInsnNode) {
        return this.target == null || this.target.equals(typeInsnNode.desc);
    }
}

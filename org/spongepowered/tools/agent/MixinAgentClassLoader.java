//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.tools.agent;

import java.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.lib.*;
import org.apache.logging.log4j.*;

class MixinAgentClassLoader extends ClassLoader
{
    private static final Logger logger;
    private Map<Class<?>, byte[]> mixins;
    private Map<String, byte[]> targets;
    
    MixinAgentClassLoader() {
        this.mixins = new HashMap<Class<?>, byte[]>();
        this.targets = new HashMap<String, byte[]>();
    }
    
    void addMixinClass(final String name) {
        MixinAgentClassLoader.logger.debug("Mixin class {} added to class loader", new Object[] { name });
        try {
            final byte[] materialise = this.materialise(name);
            final Class<?> defineClass = this.defineClass(name, materialise, 0, materialise.length);
            defineClass.newInstance();
            this.mixins.put(defineClass, materialise);
        }
        catch (Throwable t) {
            MixinAgentClassLoader.logger.catching(t);
        }
    }
    
    void addTargetClass(final String s, final byte[] array) {
        this.targets.put(s, array);
    }
    
    byte[] getFakeMixinBytecode(final Class<?> clazz) {
        return this.mixins.get(clazz);
    }
    
    byte[] getOriginalTargetBytecode(final String s) {
        return this.targets.get(s);
    }
    
    private byte[] materialise(final String s) {
        final ClassWriter classWriter = new ClassWriter(3);
        classWriter.visit(MixinEnvironment.getCompatibilityLevel().classVersion(), 1, s.replace('.', '/'), (String)null, Type.getInternalName((Class)Object.class), (String[])null);
        final MethodVisitor visitMethod = classWriter.visitMethod(1, "<init>", "()V", (String)null, (String[])null);
        visitMethod.visitCode();
        visitMethod.visitVarInsn(25, 0);
        visitMethod.visitMethodInsn(183, Type.getInternalName((Class)Object.class), "<init>", "()V", false);
        visitMethod.visitInsn(177);
        visitMethod.visitMaxs(1, 1);
        visitMethod.visitEnd();
        classWriter.visitEnd();
        return classWriter.toByteArray();
    }
    
    static {
        logger = LogManager.getLogger("mixin.agent");
    }
}

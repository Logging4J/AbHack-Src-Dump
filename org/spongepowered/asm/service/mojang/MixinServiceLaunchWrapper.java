//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package org.spongepowered.asm.service.mojang;

import org.spongepowered.asm.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.launch.*;
import com.google.common.collect.*;
import org.spongepowered.asm.mixin.throwables.*;
import java.util.*;
import java.net.*;
import org.apache.commons.io.*;
import java.io.*;
import org.spongepowered.asm.util.perf.*;
import org.spongepowered.asm.service.*;
import org.spongepowered.asm.lib.tree.*;
import org.spongepowered.asm.lib.*;
import net.minecraft.launchwrapper.*;
import org.apache.logging.log4j.*;

public class MixinServiceLaunchWrapper implements IMixinService, IClassProvider, IClassBytecodeProvider
{
    public static final String BLACKBOARD_KEY_TWEAKCLASSES = "TweakClasses";
    public static final String BLACKBOARD_KEY_TWEAKS = "Tweaks";
    private static final String LAUNCH_PACKAGE = "org.spongepowered.asm.launch.";
    private static final String MIXIN_PACKAGE = "org.spongepowered.asm.mixin.";
    private static final String STATE_TWEAKER = "org.spongepowered.asm.mixin.EnvironmentStateTweaker";
    private static final String TRANSFORMER_PROXY_CLASS = "org.spongepowered.asm.mixin.transformer.Proxy";
    private static final Logger logger;
    private final LaunchClassLoaderUtil classLoaderUtil;
    private final ReEntranceLock lock;
    private IClassNameTransformer nameTransformer;
    
    public MixinServiceLaunchWrapper() {
        this.classLoaderUtil = new LaunchClassLoaderUtil(Launch.classLoader);
        this.lock = new ReEntranceLock(1);
    }
    
    public String getName() {
        return "LaunchWrapper";
    }
    
    public boolean isValid() {
        try {
            Launch.classLoader.hashCode();
        }
        catch (Throwable t) {
            return false;
        }
        return true;
    }
    
    public void prepare() {
        Launch.classLoader.addClassLoaderExclusion("org.spongepowered.asm.launch.");
    }
    
    public MixinEnvironment.Phase getInitialPhase() {
        if (findInStackTrace("net.minecraft.launchwrapper.Launch", "launch") > 132) {
            return MixinEnvironment.Phase.DEFAULT;
        }
        return MixinEnvironment.Phase.PREINIT;
    }
    
    public void init() {
        if (findInStackTrace("net.minecraft.launchwrapper.Launch", "launch") < 4) {
            MixinServiceLaunchWrapper.logger.error("MixinBootstrap.doInit() called during a tweak constructor!");
        }
        final List list = (List)GlobalProperties.get("TweakClasses");
        if (list != null) {
            list.add("org.spongepowered.asm.mixin.EnvironmentStateTweaker");
        }
    }
    
    public ReEntranceLock getReEntranceLock() {
        return this.lock;
    }
    
    public Collection<String> getPlatformAgents() {
        return (Collection<String>)ImmutableList.of((Object)"org.spongepowered.asm.launch.platform.MixinPlatformAgentFML");
    }
    
    public IClassProvider getClassProvider() {
        return (IClassProvider)this;
    }
    
    public IClassBytecodeProvider getBytecodeProvider() {
        return (IClassBytecodeProvider)this;
    }
    
    public Class<?> findClass(final String s) throws ClassNotFoundException {
        return (Class<?>)Launch.classLoader.findClass(s);
    }
    
    public Class<?> findClass(final String name, final boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, (ClassLoader)Launch.classLoader);
    }
    
    public Class<?> findAgentClass(final String name, final boolean initialize) throws ClassNotFoundException {
        return Class.forName(name, initialize, Launch.class.getClassLoader());
    }
    
    public void beginPhase() {
        Launch.classLoader.registerTransformer("org.spongepowered.asm.mixin.transformer.Proxy");
    }
    
    public void checkEnv(final Object o) {
        if (o.getClass().getClassLoader() != Launch.class.getClassLoader()) {
            throw new MixinException("Attempted to init the mixin environment in the wrong classloader");
        }
    }
    
    public InputStream getResourceAsStream(final String s) {
        return Launch.classLoader.getResourceAsStream(s);
    }
    
    public void registerInvalidClass(final String s) {
        this.classLoaderUtil.registerInvalidClass(s);
    }
    
    public boolean isClassLoaded(final String s) {
        return this.classLoaderUtil.isClassLoaded(s);
    }
    
    public String getClassRestrictions(final String s) {
        String string = "";
        if (this.classLoaderUtil.isClassClassLoaderExcluded(s, (String)null)) {
            string = "PACKAGE_CLASSLOADER_EXCLUSION";
        }
        if (this.classLoaderUtil.isClassTransformerExcluded(s, (String)null)) {
            string = ((string.length() > 0) ? (string + ",") : "") + "PACKAGE_TRANSFORMER_EXCLUSION";
        }
        return string;
    }
    
    public URL[] getClassPath() {
        return Launch.classLoader.getSources().toArray(new URL[0]);
    }
    
    public Collection<ITransformer> getTransformers() {
        final List transformers = Launch.classLoader.getTransformers();
        final ArrayList list = new ArrayList<ITransformer>(transformers.size());
        for (final IClassTransformer classTransformer : transformers) {
            if (classTransformer instanceof ITransformer) {
                list.add((ITransformer)classTransformer);
            }
            else {
                list.add((ITransformer)new LegacyTransformerHandle(classTransformer));
            }
            if (classTransformer instanceof IClassNameTransformer) {
                MixinServiceLaunchWrapper.logger.debug("Found name transformer: {}", new Object[] { classTransformer.getClass().getName() });
                this.nameTransformer = (IClassNameTransformer)classTransformer;
            }
        }
        return (Collection<ITransformer>)list;
    }
    
    public byte[] getClassBytes(final String s, final String s2) throws IOException {
        final byte[] classBytes = Launch.classLoader.getClassBytes(s);
        if (classBytes != null) {
            return classBytes;
        }
        final URLClassLoader urlClassLoader = (URLClassLoader)Launch.class.getClassLoader();
        InputStream resourceAsStream = null;
        try {
            resourceAsStream = urlClassLoader.getResourceAsStream(s2.replace('.', '/').concat(".class"));
            return IOUtils.toByteArray(resourceAsStream);
        }
        catch (Exception ex) {
            return null;
        }
        finally {
            IOUtils.closeQuietly(resourceAsStream);
        }
    }
    
    public byte[] getClassBytes(final String s, final boolean b) throws ClassNotFoundException, IOException {
        final String replace = s.replace('/', '.');
        final String unmapClassName = this.unmapClassName(replace);
        final Profiler profiler = MixinEnvironment.getProfiler();
        final Profiler.Section begin = profiler.begin(1, "class.load");
        byte[] array = this.getClassBytes(unmapClassName, replace);
        begin.end();
        if (b) {
            final Profiler.Section begin2 = profiler.begin(1, "class.transform");
            array = this.applyTransformers(unmapClassName, replace, array, profiler);
            begin2.end();
        }
        if (array == null) {
            throw new ClassNotFoundException(String.format("The specified class '%s' was not found", replace));
        }
        return array;
    }
    
    private byte[] applyTransformers(final String s, final String s2, byte[] transformClassBytes, final Profiler profiler) {
        if (this.classLoaderUtil.isClassExcluded(s, s2)) {
            return transformClassBytes;
        }
        final MixinEnvironment currentEnvironment = MixinEnvironment.getCurrentEnvironment();
        for (final ILegacyClassTransformer legacyClassTransformer : currentEnvironment.getTransformers()) {
            this.lock.clear();
            final Profiler.Section begin = profiler.begin(2, legacyClassTransformer.getName().substring(legacyClassTransformer.getName().lastIndexOf(46) + 1).toLowerCase());
            begin.setInfo(legacyClassTransformer.getName());
            transformClassBytes = legacyClassTransformer.transformClassBytes(s, s2, transformClassBytes);
            begin.end();
            if (this.lock.isSet()) {
                currentEnvironment.addTransformerExclusion(legacyClassTransformer.getName());
                this.lock.clear();
                MixinServiceLaunchWrapper.logger.info("A re-entrant transformer '{}' was detected and will no longer process meta class data", new Object[] { legacyClassTransformer.getName() });
            }
        }
        return transformClassBytes;
    }
    
    private String unmapClassName(final String s) {
        if (this.nameTransformer == null) {
            this.findNameTransformer();
        }
        if (this.nameTransformer != null) {
            return this.nameTransformer.unmapClassName(s);
        }
        return s;
    }
    
    private void findNameTransformer() {
        for (final IClassTransformer classTransformer : Launch.classLoader.getTransformers()) {
            if (classTransformer instanceof IClassNameTransformer) {
                MixinServiceLaunchWrapper.logger.debug("Found name transformer: {}", new Object[] { classTransformer.getClass().getName() });
                this.nameTransformer = (IClassNameTransformer)classTransformer;
            }
        }
    }
    
    public ClassNode getClassNode(final String s) throws ClassNotFoundException, IOException {
        return this.getClassNode(this.getClassBytes(s, true), 0);
    }
    
    private ClassNode getClassNode(final byte[] array, final int n) {
        final ClassNode classNode = new ClassNode();
        new ClassReader(array).accept((ClassVisitor)classNode, n);
        return classNode;
    }
    
    public final String getSideName() {
        for (final ITweaker tweaker : (List)GlobalProperties.get("Tweaks")) {
            if (tweaker.getClass().getName().endsWith(".common.launcher.FMLServerTweaker")) {
                return "SERVER";
            }
            if (tweaker.getClass().getName().endsWith(".common.launcher.FMLTweaker")) {
                return "CLIENT";
            }
        }
        final String sideName = this.getSideName("net.minecraftforge.fml.relauncher.FMLLaunchHandler", "side");
        if (sideName != null) {
            return sideName;
        }
        final String sideName2 = this.getSideName("cpw.mods.fml.relauncher.FMLLaunchHandler", "side");
        if (sideName2 != null) {
            return sideName2;
        }
        final String sideName3 = this.getSideName("com.mumfrey.liteloader.launch.LiteLoaderTweaker", "getEnvironmentType");
        if (sideName3 != null) {
            return sideName3;
        }
        return "UNKNOWN";
    }
    
    private String getSideName(final String name, final String name2) {
        try {
            return ((Enum)Class.forName(name, false, (ClassLoader)Launch.classLoader).getDeclaredMethod(name2, (Class<?>[])new Class[0]).invoke(null, new Object[0])).name();
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static int findInStackTrace(final String s, final String s2) {
        final Thread currentThread = Thread.currentThread();
        if (!"main".equals(currentThread.getName())) {
            return 0;
        }
        for (final StackTraceElement stackTraceElement : currentThread.getStackTrace()) {
            if (s.equals(stackTraceElement.getClassName()) && s2.equals(stackTraceElement.getMethodName())) {
                return stackTraceElement.getLineNumber();
            }
        }
        return 0;
    }
    
    static {
        logger = LogManager.getLogger("mixin");
    }
}

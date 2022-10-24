//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package net.jodah.typetools;

import java.nio.charset.*;
import javax.crypto.spec.*;
import javax.crypto.*;
import java.lang.ref.*;
import java.util.*;
import sun.misc.*;
import java.security.*;
import java.lang.reflect.*;

public final class TypeResolver
{
    private static /* synthetic */ Method GET_CONSTANT_POOL_METHOD_AT;
    private static /* synthetic */ Method GET_CONSTANT_POOL_SIZE;
    private static volatile /* synthetic */ boolean CACHE_ENABLED;
    private static final /* synthetic */ Map<Class<?>, Class<?>> PRIMITIVE_WRAPPERS;
    private static final /* synthetic */ Map<String, Method> OBJECT_METHODS;
    private static final /* synthetic */ int[] llIIIlIlIl;
    private static final /* synthetic */ Map<Class<?>, Reference<Map<TypeVariable<?>, Type>>> TYPE_VARIABLE_CACHE;
    private static /* synthetic */ boolean RESOLVES_LAMBDAS;
    private static final /* synthetic */ String[] llIIIlIIIl;
    private static /* synthetic */ Method GET_CONSTANT_POOL;
    private static final /* synthetic */ Double JAVA_VERSION;
    
    public static void enableCache() {
        TypeResolver.CACHE_ENABLED = (TypeResolver.llIIIlIlIl[0] != 0);
    }
    
    private static String lIIlIIllIIIl(final String s, final String s2) {
        try {
            final SecretKeySpec key = new SecretKeySpec(MessageDigest.getInstance("MD5").digest(s2.getBytes(StandardCharsets.UTF_8)), "Blowfish");
            final Cipher instance = Cipher.getInstance("Blowfish");
            instance.init(TypeResolver.llIIIlIlIl[2], key);
            return new String(instance.doFinal(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> resolveRawClass(final Type type, final Class<?> clazz) {
        return resolveRawClass(type, clazz, null);
    }
    
    private static boolean lIIlIIlllllI(final Object o) {
        return o != null;
    }
    
    public static <T, S extends T> Class<?> resolveRawArgument(final Class<T> clazz, final Class<S> clazz2) {
        return resolveRawArgument(resolveGenericType(clazz, clazz2), clazz2);
    }
    
    private static String lIIlIIlIlllI(final String s, final String s2) {
        try {
            final SecretKeySpec key = new SecretKeySpec(Arrays.copyOf(MessageDigest.getInstance("MD5").digest(s2.getBytes(StandardCharsets.UTF_8)), TypeResolver.llIIIlIlIl[8]), "DES");
            final Cipher instance = Cipher.getInstance("DES");
            instance.init(TypeResolver.llIIIlIlIl[2], key);
            return new String(instance.doFinal(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8))), StandardCharsets.UTF_8);
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public static Class<?> resolveRawArgument(final Type obj, final Class<?> clazz) {
        final Class<?>[] resolveRawArguments = resolveRawArguments(obj, clazz);
        if (lIIlIIlllIll(resolveRawArguments)) {
            return Unknown.class;
        }
        if (lIIlIIllllII(resolveRawArguments.length, TypeResolver.llIIIlIlIl[0])) {
            throw new IllegalArgumentException(String.valueOf(new StringBuilder().append(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[1]]).append(obj).append(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[0]]).append(resolveRawArguments.length)));
        }
        return resolveRawArguments[TypeResolver.llIIIlIlIl[1]];
    }
    
    private static void populateTypeArgs(final ParameterizedType parameterizedType, final Map<TypeVariable<?>, Type> map, final boolean b) {
        if (lIIlIIllllIl((parameterizedType.getRawType() instanceof Class) ? 1 : 0)) {
            final TypeVariable[] typeParameters = ((Class)parameterizedType.getRawType()).getTypeParameters();
            final Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            if (lIIlIIlllllI(parameterizedType.getOwnerType())) {
                final Type ownerType = parameterizedType.getOwnerType();
                if (lIIlIIllllIl((ownerType instanceof ParameterizedType) ? 1 : 0)) {
                    populateTypeArgs((ParameterizedType)ownerType, map, b);
                }
            }
            int n = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n, actualTypeArguments.length)) {
                final TypeVariable typeVariable = typeParameters[n];
                final Type type = actualTypeArguments[n];
                Label_0410: {
                    if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
                        map.put(typeVariable, type);
                        "".length();
                        "".length();
                        if ("  ".length() == ((0x3 ^ 0x33 ^ (0x4E ^ 0x69)) & (0x2F ^ 0x28 ^ (0x2F ^ 0x3F) ^ -" ".length()))) {
                            return;
                        }
                    }
                    else if (lIIlIIllllIl((type instanceof GenericArrayType) ? 1 : 0)) {
                        map.put(typeVariable, type);
                        "".length();
                        "".length();
                        if (((0xDA ^ 0xC4) & ~(0x50 ^ 0x4E)) != 0x0) {
                            return;
                        }
                    }
                    else if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
                        map.put(typeVariable, type);
                        "".length();
                        "".length();
                        if (" ".length() > (0xDB ^ 0xAD ^ (0xE2 ^ 0x90))) {
                            return;
                        }
                    }
                    else if (lIIlIIllllIl((type instanceof TypeVariable) ? 1 : 0)) {
                        final TypeVariable<Class<T>> typeVariable2 = (TypeVariable<Class<T>>)type;
                        if (lIIlIIllllIl(b ? 1 : 0)) {
                            final TypeVariable<Class<T>> typeVariable3 = map.get(typeVariable);
                            if (lIIlIIlllllI(typeVariable3)) {
                                map.put(typeVariable2, typeVariable3);
                                "".length();
                                "".length();
                                if (-(0x70 ^ 0x74) > 0) {
                                    return;
                                }
                                break Label_0410;
                            }
                        }
                        Type resolveBound = map.get(typeVariable2);
                        if (lIIlIIlllIll(resolveBound)) {
                            resolveBound = resolveBound(typeVariable2);
                        }
                        map.put(typeVariable, resolveBound);
                        "".length();
                    }
                }
                ++n;
                "".length();
                if ("   ".length() != "   ".length()) {
                    return;
                }
            }
        }
    }
    
    private static boolean lIIlIlIIIIII(final int n) {
        return n == 0;
    }
    
    private static void populateSuperTypeArgs(final Type[] array, final Map<TypeVariable<?>, Type> map, final boolean b) {
        final int length = array.length;
        int n = TypeResolver.llIIIlIlIl[1];
        while (lIIlIIllllll(n, length)) {
            final Type type = array[n];
            if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
                final ParameterizedType parameterizedType = (ParameterizedType)type;
                if (lIIlIlIIIIII(b ? 1 : 0)) {
                    populateTypeArgs(parameterizedType, map, b);
                }
                final Type rawType = parameterizedType.getRawType();
                if (lIIlIIllllIl((rawType instanceof Class) ? 1 : 0)) {
                    populateSuperTypeArgs(((Class)rawType).getGenericInterfaces(), map, b);
                }
                if (lIIlIIllllIl(b ? 1 : 0)) {
                    populateTypeArgs(parameterizedType, map, b);
                }
                "".length();
                if ((0xC3 ^ 0xAC ^ (0x23 ^ 0x49)) <= 0) {
                    return;
                }
            }
            else if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
                populateSuperTypeArgs(((Class)type).getGenericInterfaces(), map, b);
            }
            ++n;
            "".length();
            if (null != null) {
                return;
            }
        }
    }
    
    private TypeResolver() {
    }
    
    private static boolean lIIlIlIlIIlI(final int n) {
        return n < 0;
    }
    
    private static boolean lIIlIIllllll(final int n, final int n2) {
        return n < n2;
    }
    
    private static boolean isAutoBoxingMethod(final Method method) {
        final Class<?>[] parameterTypes = method.getParameterTypes();
        int n;
        if (lIIlIIllllIl(method.getName().equals(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[3]]) ? 1 : 0) && lIIlIlIIlIlI(parameterTypes.length, TypeResolver.llIIIlIlIl[0]) && lIIlIIllllIl(parameterTypes[TypeResolver.llIIIlIlIl[1]].isPrimitive() ? 1 : 0) && lIIlIIllllIl(wrapPrimitives(parameterTypes[TypeResolver.llIIIlIlIl[1]]).equals(method.getDeclaringClass()) ? 1 : 0)) {
            n = TypeResolver.llIIIlIlIl[0];
            "".length();
            if (-" ".length() > "   ".length()) {
                return ((0x1C ^ 0x40 ^ (0xE2 ^ 0x86)) & (0x5B ^ 0x78 ^ (0x14 ^ 0xF) ^ -" ".length())) != 0x0;
            }
        }
        else {
            n = TypeResolver.llIIIlIlIl[1];
        }
        return n != 0;
    }
    
    private static Member getConstantPoolMethodAt(final Object obj, final int i) {
        try {
            final Method get_CONSTANT_POOL_METHOD_AT = TypeResolver.GET_CONSTANT_POOL_METHOD_AT;
            final Object[] args = new Object[TypeResolver.llIIIlIlIl[0]];
            args[TypeResolver.llIIIlIlIl[1]] = i;
            return (Member)get_CONSTANT_POOL_METHOD_AT.invoke(obj, args);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    private static String lIIlIIllIIII(String s, final String s2) {
        s = new String(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
        final StringBuilder obj = new StringBuilder();
        final char[] charArray = s2.toCharArray();
        int n = TypeResolver.llIIIlIlIl[1];
        final char[] charArray2 = s.toCharArray();
        final int length = charArray2.length;
        int n2 = TypeResolver.llIIIlIlIl[1];
        while (lIIlIIllllll(n2, length)) {
            obj.append((char)(charArray2[n2] ^ charArray[n % charArray.length]));
            "".length();
            ++n;
            ++n2;
            "".length();
            if (-" ".length() > 0) {
                return null;
            }
        }
        return String.valueOf(obj);
    }
    
    private static boolean lIIlIIllllII(final int n, final int n2) {
        return n != n2;
    }
    
    private static boolean lIIlIlIIllIl(final int n) {
        return n >= 0;
    }
    
    private static void lIIlIIllIIlI() {
        (llIIIlIIIl = new String[TypeResolver.llIIIlIlIl[12]])[TypeResolver.llIIIlIlIl[1]] = lIIlIIlIlllI("iy/RZYtzW6TFvY5Pzh++YeT1aY89B+l2w3CQWFeeerq731ooL4fONw==", "oBXaf");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[0]] = lIIlIIllIIII("ajAgLWUsPSA3IWo=", "JRUYE");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[2]] = lIIlIIllIIII("LjISC0woMgoNTC09EgUJIX03DxAtMggDGCE3KAsPJjcF", "DSdjb");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[3]] = lIIlIIllIIII("Mwc0LBQKAA==", "EfXYq");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[4]] = lIIlIIllIIII("Jg8FN1s/HhY1HCoHEDcBJQEdeAMpHAA/GiI=", "LnsVu");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[5]] = lIIlIIllIIIl("aaeD8cvva6U=", "hPLLa");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[6]] = lIIlIIlIlllI("X8r4/xYndox25cGkI31S5A==", "GqRkO");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[7]] = lIIlIIlIlllI("AtCJKG/3CRyO0A2yfIa3Tw/7dvXwdtUyVbKYJwf1b48=", "mkRwA");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[8]] = lIIlIIllIIII("AwEkdzEHESorNggJYSs9DwkqOixHJiA3Kx0EIS0IBgoj", "ieOYX");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[9]] = lIIlIIllIIII("PyQgOC0iJA==", "XATkD");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[10]] = lIIlIIlIlllI("jkd80l3kx1qaGx7qAgm/jQ==", "kWmhn");
        TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[11]] = lIIlIIllIIII("CgQdOTQMFh0=", "erxKF");
    }
    
    private static Member getMemberRef(final Class<?> obj) {
        Object invoke;
        try {
            invoke = TypeResolver.GET_CONSTANT_POOL.invoke(obj, new Object[TypeResolver.llIIIlIlIl[1]]);
            "".length();
            if ("  ".length() < 0) {
                return null;
            }
        }
        catch (Exception ex) {
            return null;
        }
        Member member = null;
        int n = getConstantPoolSize(invoke) - TypeResolver.llIIIlIlIl[0];
        while (lIIlIlIIllIl(n)) {
            final Member constantPoolMethod = getConstantPoolMethodAt(invoke, n);
            if (lIIlIIlllllI(constantPoolMethod) && (!lIIlIIllllIl((constantPoolMethod instanceof Constructor) ? 1 : 0) || lIIlIlIIIIII(constantPoolMethod.getDeclaringClass().getName().equals(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[2]]) ? 1 : 0))) {
                if (lIIlIIllllIl(constantPoolMethod.getDeclaringClass().isAssignableFrom(obj) ? 1 : 0)) {
                    "".length();
                    if (null != null) {
                        return null;
                    }
                }
                else {
                    member = constantPoolMethod;
                    if (!lIIlIIllllIl((constantPoolMethod instanceof Method) ? 1 : 0)) {
                        break;
                    }
                    if (lIIlIlIIIIII(isAutoBoxingMethod((Method)constantPoolMethod) ? 1 : 0)) {
                        "".length();
                        if ("   ".length() == 0) {
                            return null;
                        }
                        break;
                    }
                }
            }
            --n;
            "".length();
            if (null != null) {
                return null;
            }
        }
        return member;
    }
    
    private static boolean lIIlIlIIlIII(final int n) {
        return n > 0;
    }
    
    private static void populateLambdaArgs(final Class<?> clazz, final Class<?> clazz2, final Map<TypeVariable<?>, Type> map) {
        if (lIIlIIllllIl(TypeResolver.RESOLVES_LAMBDAS ? 1 : 0)) {
            final Method[] methods = clazz.getMethods();
            final int length = methods.length;
            int n = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n, length)) {
                final Method method = methods[n];
                if (lIIlIlIIIIII(isDefaultMethod(method) ? 1 : 0) && lIIlIlIIIIII(Modifier.isStatic(method.getModifiers()) ? 1 : 0) && lIIlIlIIIIII(method.isBridge() ? 1 : 0)) {
                    final Method method2 = TypeResolver.OBJECT_METHODS.get(method.getName());
                    if (lIIlIIlllllI(method2) && lIIlIIllllIl(Arrays.equals(method.getTypeParameters(), method2.getTypeParameters()) ? 1 : 0)) {
                        "".length();
                        if (((0x7D ^ 0x13 ^ (0x6E ^ 0x17)) & (129 + 71 - 52 + 6 ^ 115 + 22 - 134 + 138 ^ -" ".length())) >= " ".length()) {
                            return;
                        }
                    }
                    else {
                        final Type genericReturnType = method.getGenericReturnType();
                        final Type[] genericParameterTypes = method.getGenericParameterTypes();
                        final Member memberRef = getMemberRef(clazz2);
                        if (lIIlIIlllIll(memberRef)) {
                            return;
                        }
                        if (lIIlIIllllIl((genericReturnType instanceof TypeVariable) ? 1 : 0)) {
                            Class<?> clazz3;
                            if (lIIlIIllllIl((memberRef instanceof Method) ? 1 : 0)) {
                                clazz3 = ((Method)memberRef).getReturnType();
                                "".length();
                                if ("  ".length() > "   ".length()) {
                                    return;
                                }
                            }
                            else {
                                clazz3 = ((Constructor<?>)memberRef).getDeclaringClass();
                            }
                            final Class<?> wrapPrimitives = wrapPrimitives(clazz3);
                            if (lIIlIlIIIIII(wrapPrimitives.equals(Void.class) ? 1 : 0)) {
                                map.put((TypeVariable<?>)genericReturnType, wrapPrimitives);
                                "".length();
                            }
                        }
                        Class<?>[] array;
                        if (lIIlIIllllIl((memberRef instanceof Method) ? 1 : 0)) {
                            array = ((Method)memberRef).getParameterTypes();
                            "".length();
                            if ((0x1 ^ 0x7F ^ (0xC9 ^ 0xB3)) == 0x0) {
                                return;
                            }
                        }
                        else {
                            array = ((Constructor<?>)memberRef).getParameterTypes();
                        }
                        final Class<?>[] array2 = array;
                        int n2 = TypeResolver.llIIIlIlIl[1];
                        if (lIIlIlIIlIII(genericParameterTypes.length) && lIIlIIllllIl((genericParameterTypes[TypeResolver.llIIIlIlIl[1]] instanceof TypeVariable) ? 1 : 0) && lIIlIlIIlIlI(genericParameterTypes.length, array2.length + TypeResolver.llIIIlIlIl[0])) {
                            map.put((TypeVariable<?>)genericParameterTypes[TypeResolver.llIIIlIlIl[1]], memberRef.getDeclaringClass());
                            "".length();
                            n2 = TypeResolver.llIIIlIlIl[0];
                        }
                        int n3 = TypeResolver.llIIIlIlIl[1];
                        if (lIIlIIllllll(genericParameterTypes.length, array2.length)) {
                            n3 = array2.length - genericParameterTypes.length;
                        }
                        int n4 = TypeResolver.llIIIlIlIl[1];
                        while (lIIlIIllllll(n4 + n3, array2.length)) {
                            if (lIIlIIllllIl((genericParameterTypes[n4] instanceof TypeVariable) ? 1 : 0)) {
                                map.put((TypeVariable<?>)genericParameterTypes[n4 + n2], wrapPrimitives(array2[n4 + n3]));
                                "".length();
                            }
                            ++n4;
                            "".length();
                            if (" ".length() >= "  ".length()) {
                                return;
                            }
                        }
                        return;
                    }
                }
                ++n;
                "".length();
                if (-" ".length() >= 0) {
                    return;
                }
            }
        }
    }
    
    private static void lIIlIIlllIlI() {
        (llIIIlIlIl = new int[13])[0] = " ".length();
        TypeResolver.llIIIlIlIl[1] = ((0x11 ^ 0x1A ^ (0x3E ^ 0x4)) & (0x2A ^ 0x31 ^ (0x2B ^ 0x1) ^ -" ".length()));
        TypeResolver.llIIIlIlIl[2] = "  ".length();
        TypeResolver.llIIIlIlIl[3] = "   ".length();
        TypeResolver.llIIIlIlIl[4] = (39 + 107 - 82 + 119 ^ 90 + 24 + 35 + 30);
        TypeResolver.llIIIlIlIl[5] = (0x9E ^ 0xC6 ^ (0x1B ^ 0x46));
        TypeResolver.llIIIlIlIl[6] = (0x7C ^ 0x7A);
        TypeResolver.llIIIlIlIl[7] = (0x91 ^ 0x96);
        TypeResolver.llIIIlIlIl[8] = (0x26 ^ 0x2E);
        TypeResolver.llIIIlIlIl[9] = (0xB5 ^ 0xBC);
        TypeResolver.llIIIlIlIl[10] = (0x93 ^ 0x99);
        TypeResolver.llIIIlIlIl[11] = (0x8C ^ 0x87);
        TypeResolver.llIIIlIlIl[12] = (0x44 ^ 0x48);
    }
    
    private static Class<?> wrapPrimitives(final Class<?> clazz) {
        Class<?> clazz2;
        if (lIIlIIllllIl(clazz.isPrimitive() ? 1 : 0)) {
            clazz2 = TypeResolver.PRIMITIVE_WRAPPERS.get(clazz);
            "".length();
            if (null != null) {
                return null;
            }
        }
        else {
            clazz2 = clazz;
        }
        return clazz2;
    }
    
    private static int getConstantPoolSize(final Object obj) {
        try {
            return (int)TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(obj, new Object[TypeResolver.llIIIlIlIl[1]]);
        }
        catch (Exception ex) {
            return TypeResolver.llIIIlIlIl[1];
        }
    }
    
    public static Class<?>[] resolveRawArguments(final Type type, final Class<?> clazz) {
        Class<?>[] array = null;
        Class<?> clazz2 = null;
        if (lIIlIIllllIl(TypeResolver.RESOLVES_LAMBDAS ? 1 : 0) && lIIlIIllllIl(clazz.isSynthetic() ? 1 : 0)) {
            Class<?> clazz3;
            if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0) && lIIlIIllllIl((((ParameterizedType)type).getRawType() instanceof Class) ? 1 : 0)) {
                clazz3 = (Class<?>)((ParameterizedType)type).getRawType();
                "".length();
                if (-(0x74 ^ 0x70) > 0) {
                    return null;
                }
            }
            else if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
                clazz3 = (Class<?>)type;
                "".length();
                if (((0x38 ^ 0x5D ^ (0xEF ^ 0x8C)) & (163 + 152 - 260 + 134 ^ 162 + 124 - 218 + 119 ^ -" ".length())) < 0) {
                    return null;
                }
            }
            else {
                clazz3 = null;
            }
            final Class<?> clazz4 = clazz3;
            if (lIIlIIlllllI(clazz4) && lIIlIIllllIl(clazz4.isInterface() ? 1 : 0)) {
                clazz2 = clazz4;
            }
        }
        if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
            final Type[] actualTypeArguments = ((ParameterizedType)type).getActualTypeArguments();
            array = (Class<?>[])new Class[actualTypeArguments.length];
            int n = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n, actualTypeArguments.length)) {
                array[n] = resolveRawClass(actualTypeArguments[n], clazz, clazz2);
                ++n;
                "".length();
                if (null != null) {
                    return null;
                }
            }
            "".length();
            if ("   ".length() < "   ".length()) {
                return null;
            }
        }
        else if (lIIlIIllllIl((type instanceof TypeVariable) ? 1 : 0)) {
            array = (Class<?>[])new Class[TypeResolver.llIIIlIlIl[0]];
            array[TypeResolver.llIIIlIlIl[1]] = resolveRawClass(type, clazz, clazz2);
            "".length();
            if (-" ".length() > 0) {
                return null;
            }
        }
        else if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
            final TypeVariable[] typeParameters = ((Class)type).getTypeParameters();
            array = (Class<?>[])new Class[typeParameters.length];
            int n2 = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n2, typeParameters.length)) {
                array[n2] = resolveRawClass(typeParameters[n2], clazz, clazz2);
                ++n2;
                "".length();
                if (-" ".length() > 0) {
                    return null;
                }
            }
        }
        return array;
    }
    
    public static Type resolveBound(final TypeVariable<?> typeVariable) {
        final Type[] bounds = typeVariable.getBounds();
        if (lIIlIlIIIIII(bounds.length)) {
            return Unknown.class;
        }
        Type resolveBound = bounds[TypeResolver.llIIIlIlIl[1]];
        if (lIIlIIllllIl((resolveBound instanceof TypeVariable) ? 1 : 0)) {
            resolveBound = resolveBound((TypeVariable<?>)resolveBound);
        }
        Object o;
        if (lIIlIlIIIlII(resolveBound, Object.class)) {
            o = Unknown.class;
            "".length();
            if (-" ".length() < -" ".length()) {
                return null;
            }
        }
        else {
            o = resolveBound;
        }
        return (Type)o;
    }
    
    public static void disableCache() {
        TypeResolver.TYPE_VARIABLE_CACHE.clear();
        TypeResolver.CACHE_ENABLED = (TypeResolver.llIIIlIlIl[1] != 0);
    }
    
    private static boolean lIIlIIllllIl(final int n) {
        return n != 0;
    }
    
    private static boolean lIIlIlIIIlII(final Object o, final Object o2) {
        return o == o2;
    }
    
    public static Type resolveGenericType(final Class<?> clazz, final Type type) {
        Class obj;
        if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
            obj = (Class)((ParameterizedType)type).getRawType();
            "".length();
            if (" ".length() >= "  ".length()) {
                return null;
            }
        }
        else {
            obj = (Class)type;
        }
        if (lIIlIIllllIl(clazz.equals(obj) ? 1 : 0)) {
            return type;
        }
        if (lIIlIIllllIl(clazz.isInterface() ? 1 : 0)) {
            final Type[] genericInterfaces = obj.getGenericInterfaces();
            final int length = genericInterfaces.length;
            int n = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n, length)) {
                final Type type2 = genericInterfaces[n];
                final Type resolveGenericType;
                if (lIIlIIlllllI(type2) && lIIlIlIIIIII(type2.equals(Object.class) ? 1 : 0) && lIIlIIlllllI(resolveGenericType = resolveGenericType(clazz, type2))) {
                    return resolveGenericType;
                }
                ++n;
                "".length();
                if (null != null) {
                    return null;
                }
            }
        }
        final Type genericSuperclass = obj.getGenericSuperclass();
        final Type resolveGenericType2;
        if (lIIlIIlllllI(genericSuperclass) && lIIlIlIIIIII(genericSuperclass.equals(Object.class) ? 1 : 0) && lIIlIIlllllI(resolveGenericType2 = resolveGenericType(clazz, genericSuperclass))) {
            return resolveGenericType2;
        }
        return null;
    }
    
    public static <T, S extends T> Class<?>[] resolveRawArguments(final Class<T> clazz, final Class<S> clazz2) {
        return resolveRawArguments(resolveGenericType(clazz, clazz2), clazz2);
    }
    
    private static boolean lIIlIlIIlIlI(final int n, final int n2) {
        return n == n2;
    }
    
    private static Class<?> resolveRawClass(Type type, final Class<?> clazz, final Class<?> clazz2) {
        if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
            return (Class<?>)type;
        }
        if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
            return resolveRawClass(((ParameterizedType)type).getRawType(), clazz, clazz2);
        }
        if (lIIlIIllllIl((type instanceof GenericArrayType) ? 1 : 0)) {
            return Array.newInstance(resolveRawClass(((GenericArrayType)type).getGenericComponentType(), clazz, clazz2), TypeResolver.llIIIlIlIl[1]).getClass();
        }
        if (lIIlIIllllIl((type instanceof TypeVariable) ? 1 : 0)) {
            final TypeVariable typeVariable = (TypeVariable)type;
            type = getTypeVariableMap(clazz, clazz2).get(typeVariable);
            Type type2;
            if (lIIlIIlllIll(type)) {
                type2 = resolveBound(typeVariable);
                "".length();
                if (((2 + 40 + 100 + 10 ^ 18 + 109 - 45 + 69) & (38 + 44 + 53 + 27 ^ 31 + 58 + 39 + 45 ^ -" ".length())) > (0x7B ^ 0x30 ^ (0xDD ^ 0x92))) {
                    return null;
                }
            }
            else {
                type2 = resolveRawClass(type, clazz, clazz2);
            }
            type = type2;
        }
        Class<Unknown> clazz3;
        if (lIIlIIllllIl((type instanceof Class) ? 1 : 0)) {
            clazz3 = (Class<Unknown>)type;
            "".length();
            if (((0xD1 ^ 0xAD ^ (0x3F ^ 0x5B)) & (0x72 ^ 0x11 ^ (0x55 ^ 0x2E) ^ -" ".length())) > 0) {
                return null;
            }
        }
        else {
            clazz3 = Unknown.class;
        }
        return clazz3;
    }
    
    private static boolean isDefaultMethod(final Method method) {
        int n;
        if (lIIlIlIIllIl(dcmpl((double)TypeResolver.JAVA_VERSION, 1.8)) && lIIlIIllllIl(method.isDefault() ? 1 : 0)) {
            n = TypeResolver.llIIIlIlIl[0];
            "".length();
            if (null != null) {
                return ((163 + 123 - 182 + 71 ^ 115 + 87 - 69 + 48) & (0x45 ^ 0x2D ^ (0x4E ^ 0x3C) ^ -" ".length())) != 0x0;
            }
        }
        else {
            n = TypeResolver.llIIIlIlIl[1];
        }
        return n != 0;
    }
    
    private static boolean lIIlIIlllIll(final Object o) {
        return o == null;
    }
    
    private static Map<TypeVariable<?>, Type> getTypeVariableMap(final Class<?> clazz, final Class<?> clazz2) {
        final Reference<Map<TypeVariable<?>, Type>> reference = TypeResolver.TYPE_VARIABLE_CACHE.get(clazz);
        Map<TypeVariable<?>, Type> map;
        if (lIIlIIlllllI(reference)) {
            map = reference.get();
            "".length();
            if ("  ".length() <= " ".length()) {
                return null;
            }
        }
        else {
            map = null;
        }
        Map<TypeVariable<?>, Type> referent = map;
        if (lIIlIIlllIll(referent)) {
            referent = new HashMap<TypeVariable<?>, Type>();
            if (lIIlIIlllllI(clazz2)) {
                populateLambdaArgs(clazz2, clazz, referent);
            }
            final Type[] genericInterfaces = clazz.getGenericInterfaces();
            final Map<TypeVariable<?>, Type> map2 = referent;
            int n;
            if (lIIlIIlllllI(clazz2)) {
                n = TypeResolver.llIIIlIlIl[0];
                "".length();
                if (null != null) {
                    return null;
                }
            }
            else {
                n = TypeResolver.llIIIlIlIl[1];
            }
            populateSuperTypeArgs(genericInterfaces, map2, (boolean)(n != 0));
            Type type = clazz.getGenericSuperclass();
            Class<?> obj = clazz.getSuperclass();
            while (lIIlIIlllllI(obj) && lIIlIlIIIIII(Object.class.equals(obj) ? 1 : 0)) {
                if (lIIlIIllllIl((type instanceof ParameterizedType) ? 1 : 0)) {
                    populateTypeArgs((ParameterizedType)type, referent, (boolean)(TypeResolver.llIIIlIlIl[1] != 0));
                }
                populateSuperTypeArgs(obj.getGenericInterfaces(), referent, (boolean)(TypeResolver.llIIIlIlIl[1] != 0));
                type = obj.getGenericSuperclass();
                obj = obj.getSuperclass();
                "".length();
                if (" ".length() <= ((0x39 ^ 0x6B) & ~(0x17 ^ 0x45))) {
                    return null;
                }
            }
            Class<?> enclosingClass = clazz;
            while (lIIlIIllllIl(enclosingClass.isMemberClass() ? 1 : 0)) {
                final Type genericSuperclass = enclosingClass.getGenericSuperclass();
                if (lIIlIIllllIl((genericSuperclass instanceof ParameterizedType) ? 1 : 0)) {
                    final ParameterizedType parameterizedType = (ParameterizedType)genericSuperclass;
                    final Map<TypeVariable<?>, Type> map3 = referent;
                    int n2;
                    if (lIIlIIlllllI(clazz2)) {
                        n2 = TypeResolver.llIIIlIlIl[0];
                        "".length();
                        if (-" ".length() != -" ".length()) {
                            return null;
                        }
                    }
                    else {
                        n2 = TypeResolver.llIIIlIlIl[1];
                    }
                    populateTypeArgs(parameterizedType, map3, (boolean)(n2 != 0));
                }
                enclosingClass = enclosingClass.getEnclosingClass();
                "".length();
                if ((80 + 77 - 55 + 86 ^ 183 + 42 - 114 + 73) > (0x94 ^ 0x8C ^ (0x8C ^ 0x90))) {
                    return null;
                }
            }
            if (lIIlIIllllIl(TypeResolver.CACHE_ENABLED ? 1 : 0)) {
                TypeResolver.TYPE_VARIABLE_CACHE.put(clazz, new WeakReference<Map<TypeVariable<?>, Type>>(referent));
                "".length();
            }
        }
        return referent;
    }
    
    static {
        lIIlIIlllIlI();
        lIIlIIllIIlI();
        TYPE_VARIABLE_CACHE = Collections.synchronizedMap(new WeakHashMap<Class<?>, Reference<Map<TypeVariable<?>, Type>>>());
        TypeResolver.CACHE_ENABLED = (TypeResolver.llIIIlIlIl[0] != 0);
        OBJECT_METHODS = new HashMap<String, Method>();
        JAVA_VERSION = Double.parseDouble(System.getProperty(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[4]], TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[5]]));
        try {
            final Unsafe unsafe = AccessController.doPrivileged((PrivilegedExceptionAction<Unsafe>)new PrivilegedExceptionAction<Unsafe>() {
                private static final /* synthetic */ String[] lIIllIIIIII;
                private static final /* synthetic */ int[] lIIllIIIIIl;
                
                private static boolean llIllllIllll(final int n, final int n2) {
                    return n < n2;
                }
                
                private static String llIllllIllII(String s, final String s2) {
                    s = new String(Base64.getDecoder().decode(s.getBytes(StandardCharsets.UTF_8)), StandardCharsets.UTF_8);
                    final StringBuilder obj = new StringBuilder();
                    final char[] charArray = s2.toCharArray();
                    int n = TypeResolver$1.lIIllIIIIIl[0];
                    final char[] charArray2 = s.toCharArray();
                    final int length = charArray2.length;
                    int n2 = TypeResolver$1.lIIllIIIIIl[0];
                    while (llIllllIllll(n2, length)) {
                        obj.append((char)(charArray2[n2] ^ charArray[n % charArray.length]));
                        "".length();
                        ++n;
                        ++n2;
                        "".length();
                        if ((182 + 28 - 201 + 187 ^ 15 + 21 + 151 + 6) == 0x0) {
                            return null;
                        }
                    }
                    return String.valueOf(obj);
                }
                
                private static void llIllllIlllI() {
                    (lIIllIIIIIl = new int[2])[0] = ("  ".length() & ~"  ".length());
                    TypeResolver$1.lIIllIIIIIl[1] = " ".length();
                }
                
                @Override
                public Unsafe run() throws Exception {
                    final Field declaredField = Unsafe.class.getDeclaredField(TypeResolver$1.lIIllIIIIII[TypeResolver$1.lIIllIIIIIl[0]]);
                    declaredField.setAccessible((boolean)(TypeResolver$1.lIIllIIIIIl[1] != 0));
                    return (Unsafe)declaredField.get(null);
                }
                
                static {
                    llIllllIlllI();
                    llIllllIllIl();
                }
                
                private static void llIllllIllIl() {
                    (lIIllIIIIII = new String[TypeResolver$1.lIIllIIIIIl[1]])[TypeResolver$1.lIIllIIIIIl[0]] = llIllllIllII("GSMLEyMeKggj", "mKnFM");
                }
            });
            TypeResolver.GET_CONSTANT_POOL = Class.class.getDeclaredMethod(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[6]], (Class<?>[])new Class[TypeResolver.llIIIlIlIl[1]]);
            String className;
            if (lIIlIlIlIIlI(dcmpg((double)TypeResolver.JAVA_VERSION, 9.0))) {
                className = TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[7]];
                "".length();
                if ((0xD0 ^ 0xBD ^ (0x12 ^ 0x7B)) < 0) {
                    return;
                }
            }
            else {
                className = TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[8]];
            }
            final Class<?> forName = Class.forName(className);
            TypeResolver.GET_CONSTANT_POOL_SIZE = forName.getDeclaredMethod(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[9]], (Class<?>[])new Class[TypeResolver.llIIIlIlIl[1]]);
            final Class<?> clazz = forName;
            final String name = TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[10]];
            final Class[] parameterTypes = new Class[TypeResolver.llIIIlIlIl[0]];
            parameterTypes[TypeResolver.llIIIlIlIl[1]] = Integer.TYPE;
            TypeResolver.GET_CONSTANT_POOL_METHOD_AT = clazz.getDeclaredMethod(name, (Class<?>[])parameterTypes);
            final long objectFieldOffset = unsafe.objectFieldOffset(AccessibleObject.class.getDeclaredField(TypeResolver.llIIIlIIIl[TypeResolver.llIIIlIlIl[11]]));
            unsafe.putBoolean(TypeResolver.GET_CONSTANT_POOL, objectFieldOffset, (boolean)(TypeResolver.llIIIlIlIl[0] != 0));
            unsafe.putBoolean(TypeResolver.GET_CONSTANT_POOL_SIZE, objectFieldOffset, (boolean)(TypeResolver.llIIIlIlIl[0] != 0));
            unsafe.putBoolean(TypeResolver.GET_CONSTANT_POOL_METHOD_AT, objectFieldOffset, (boolean)(TypeResolver.llIIIlIlIl[0] != 0));
            TypeResolver.GET_CONSTANT_POOL_SIZE.invoke(TypeResolver.GET_CONSTANT_POOL.invoke(Object.class, new Object[TypeResolver.llIIIlIlIl[1]]), new Object[TypeResolver.llIIIlIlIl[1]]);
            "".length();
            final Method[] declaredMethods = Object.class.getDeclaredMethods();
            final int length = declaredMethods.length;
            int n = TypeResolver.llIIIlIlIl[1];
            while (lIIlIIllllll(n, length)) {
                final Method method = declaredMethods[n];
                TypeResolver.OBJECT_METHODS.put(method.getName(), method);
                "".length();
                ++n;
                "".length();
                if (((52 + 11 - 47 + 128 ^ 19 + 86 - 74 + 99) & (0xBB ^ 0xAB ^ "  ".length() ^ -" ".length())) != 0x0) {
                    return;
                }
            }
            TypeResolver.RESOLVES_LAMBDAS = (TypeResolver.llIIIlIlIl[0] != 0);
            "".length();
            if ("   ".length() != "   ".length()) {
                return;
            }
        }
        catch (Exception ex) {}
        final HashMap<Class<?>, Class<?>> m = new HashMap<Class<?>, Class<?>>();
        m.put(Boolean.TYPE, Boolean.class);
        "".length();
        m.put(Byte.TYPE, Byte.class);
        "".length();
        m.put(Character.TYPE, Character.class);
        "".length();
        m.put(Double.TYPE, Double.class);
        "".length();
        m.put(Float.TYPE, Float.class);
        "".length();
        m.put(Integer.TYPE, Integer.class);
        "".length();
        m.put(Long.TYPE, Long.class);
        "".length();
        m.put(Short.TYPE, Short.class);
        "".length();
        m.put(Void.TYPE, Void.class);
        "".length();
        PRIMITIVE_WRAPPERS = Collections.unmodifiableMap((Map<?, ?>)m);
    }
    
    public static final class Unknown
    {
        private Unknown() {
        }
    }
}

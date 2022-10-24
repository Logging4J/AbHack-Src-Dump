//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import java.util.*;
import java.lang.reflect.*;

public class ReflectionUtil
{
    public static void makeMutable(final Member member) throws IllegalAccessException, NoSuchFieldException {
        Objects.requireNonNull(member);
        final Field declaredField = Field.class.getDeclaredField("modifiers");
        makePublic(declaredField);
        declaredField.setInt(member, member.getModifiers() & 0xFFFFFFEF);
    }
    
    public static <F, T extends F> void copyOf(final F n, final T t) throws IllegalAccessException, NoSuchFieldException {
        copyOf(n, t, false);
    }
    
    public static boolean isFinal(final Member member) {
        return (member.getModifiers() & 0x10) != 0x0;
    }
    
    public static void makeImmutable(final Member member) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(member);
        final Field declaredField = Field.class.getDeclaredField("modifiers");
        makePublic(declaredField);
        declaredField.setInt(member, member.getModifiers() & 0x10);
    }
    
    public static boolean isStatic(final Member member) {
        return (member.getModifiers() & 0x8) != 0x0;
    }
    
    public static <F, T extends F> void copyOf(final F n, final T t, final boolean b) throws NoSuchFieldException, IllegalAccessException {
        Objects.requireNonNull(n);
        Objects.requireNonNull(t);
        for (final Field field : n.getClass().getDeclaredFields()) {
            makePublic(field);
            if (!isStatic(field)) {
                if (!b || !isFinal(field)) {
                    makeMutable(field);
                    field.set(t, field.get(n));
                }
            }
        }
    }
    
    public static void makePublic(final AccessibleObject accessibleObject) {
        makeAccessible(accessibleObject, true);
    }
    
    public static void makeAccessible(final AccessibleObject obj, final boolean accessible) {
        Objects.requireNonNull(obj);
        obj.setAccessible(accessible);
    }
    
    public static void makePrivate(final AccessibleObject accessibleObject) {
        makeAccessible(accessibleObject, false);
    }
}

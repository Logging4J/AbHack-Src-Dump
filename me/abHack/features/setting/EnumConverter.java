//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.setting;

import com.google.common.base.*;
import com.google.gson.*;

public class EnumConverter extends Converter<Enum, JsonElement>
{
    private final /* synthetic */ Class<? extends Enum> clazz;
    
    public static Enum increaseEnum(final Enum enum1) {
        final int currentEnum = currentEnum(enum1);
        for (int i = 0; i < ((Enum[])enum1.getClass().getEnumConstants()).length; ++i) {
            final Enum enum2 = ((Enum[])enum1.getClass().getEnumConstants())[i];
            if (i == currentEnum + 1) {
                return enum2;
            }
        }
        return ((Enum[])enum1.getClass().getEnumConstants())[0];
    }
    
    public Enum doBackward(final JsonElement jsonElement) {
        try {
            return (Enum)Enum.valueOf(this.clazz, jsonElement.getAsString());
        }
        catch (IllegalArgumentException ex) {
            return null;
        }
    }
    
    public static int currentEnum(final Enum enum1) {
        for (int i = 0; i < ((Enum[])enum1.getClass().getEnumConstants()).length; ++i) {
            if (((Enum[])enum1.getClass().getEnumConstants())[i].name().equalsIgnoreCase(enum1.name())) {
                return i;
            }
        }
        return -1;
    }
    
    public EnumConverter(final Class<? extends Enum> clazz) {
        this.clazz = clazz;
    }
    
    public JsonElement doForward(final Enum enum1) {
        return (JsonElement)new JsonPrimitive(enum1.toString());
    }
    
    public static String getProperName(final Enum enum1) {
        return String.valueOf(new StringBuilder().append(Character.toUpperCase(enum1.name().charAt(0))).append(enum1.name().toLowerCase().substring(1)));
    }
}

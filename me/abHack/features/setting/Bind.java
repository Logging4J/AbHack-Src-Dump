//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.setting;

import org.lwjgl.input.*;
import com.google.common.base.*;
import com.google.gson.*;

public class Bind
{
    private /* synthetic */ int key;
    
    public int getKey() {
        return this.key;
    }
    
    public void setKey(final int key) {
        this.key = key;
    }
    
    private String capitalise(final String s) {
        if (s.isEmpty()) {
            return "";
        }
        return String.valueOf(new StringBuilder().append(Character.toUpperCase(s.charAt(0))).append((s.length() != 1) ? s.substring(1).toLowerCase() : ""));
    }
    
    public boolean isEmpty() {
        return this.key < 0;
    }
    
    public Bind(final int key) {
        this.key = key;
    }
    
    public boolean isDown() {
        return !this.isEmpty() && Keyboard.isKeyDown(this.getKey());
    }
    
    @Override
    public String toString() {
        return this.isEmpty() ? "None" : ((this.key < 0) ? "None" : this.capitalise(Keyboard.getKeyName(this.key)));
    }
    
    public static Bind none() {
        return new Bind(-1);
    }
    
    public static class BindConverter extends Converter<Bind, JsonElement>
    {
        public JsonElement doForward(final Bind bind) {
            return (JsonElement)new JsonPrimitive(bind.toString());
        }
        
        public Bind doBackward(final JsonElement jsonElement) {
            final String asString = jsonElement.getAsString();
            if (asString.equalsIgnoreCase("None")) {
                return Bind.none();
            }
            int keyIndex = -1;
            try {
                keyIndex = Keyboard.getKeyIndex(asString.toUpperCase());
            }
            catch (Exception ex) {}
            if (keyIndex == 0) {
                return Bind.none();
            }
            return new Bind(keyIndex);
        }
    }
}

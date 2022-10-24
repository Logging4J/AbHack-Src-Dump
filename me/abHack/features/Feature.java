//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features;

import me.abHack.util.*;
import me.abHack.features.setting.*;
import me.abHack.manager.*;
import me.abHack.features.modules.*;
import me.abHack.features.gui.*;
import me.abHack.*;
import java.util.*;

public class Feature implements Util
{
    public /* synthetic */ List<Setting> settings;
    private /* synthetic */ String name;
    public /* synthetic */ TextManager renderer;
    
    public Setting register(final Setting setting) {
        setting.setFeature(this);
        this.settings.add(setting);
        if (this instanceof Module && Feature.mc.currentScreen instanceof OyVeyGui) {
            OyVeyGui.getInstance().updateModule((Module)this);
        }
        return setting;
    }
    
    public List<Setting> getSettings() {
        return this.settings;
    }
    
    public Feature(final String name) {
        this.settings = new ArrayList<Setting>();
        this.renderer = OyVey.textManager;
        this.name = name;
    }
    
    public Setting getSettingByName(final String anotherString) {
        for (final Setting setting : this.settings) {
            if (!setting.getName().equalsIgnoreCase(anotherString)) {
                continue;
            }
            return setting;
        }
        return null;
    }
    
    public void clearSettings() {
        this.settings = new ArrayList<Setting>();
    }
    
    public void reset() {
        for (final Setting<Object> setting : this.settings) {
            setting.setValue(setting.getDefaultValue());
        }
    }
    
    public static boolean nullCheck() {
        return Feature.mc.player == null;
    }
    
    public void unregister(final Setting obj) {
        final ArrayList<Setting> list = new ArrayList<Setting>();
        for (final Setting e : this.settings) {
            if (!e.equals(obj)) {
                continue;
            }
            list.add(e);
        }
        if (!list.isEmpty()) {
            this.settings.removeAll(list);
        }
        if (this instanceof Module && Feature.mc.currentScreen instanceof OyVeyGui) {
            OyVeyGui.getInstance().updateModule((Module)this);
        }
    }
    
    public boolean hasSettings() {
        return !this.settings.isEmpty();
    }
    
    public Feature() {
        this.settings = new ArrayList<Setting>();
        this.renderer = OyVey.textManager;
    }
    
    public boolean isEnabled() {
        return this instanceof Module && ((Module)this).isOn();
    }
    
    public static boolean fullNullCheck() {
        return Feature.mc.player == null || Feature.mc.world == null;
    }
    
    public boolean isDisabled() {
        return !this.isEnabled();
    }
    
    public String getName() {
        return this.name;
    }
}

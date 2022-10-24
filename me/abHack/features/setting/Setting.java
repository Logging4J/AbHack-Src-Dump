//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.setting;

import java.util.function.*;
import me.abHack.features.*;
import me.abHack.event.events.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Setting<T>
{
    private /* synthetic */ T min;
    private /* synthetic */ Predicate<T> visibility;
    private /* synthetic */ Feature feature;
    private /* synthetic */ T plannedValue;
    private /* synthetic */ String description;
    private final /* synthetic */ String name;
    private /* synthetic */ T value;
    private /* synthetic */ T max;
    private /* synthetic */ boolean hasRestriction;
    private final /* synthetic */ T defaultValue;
    
    public Feature getFeature() {
        return this.feature;
    }
    
    public String getDescription() {
        if (this.description == null) {
            return "";
        }
        return this.description;
    }
    
    public void setMin(final T min) {
        this.min = min;
    }
    
    public void setPlannedValue(final T plannedValue) {
        this.plannedValue = plannedValue;
    }
    
    public boolean isNumberSetting() {
        return this.value instanceof Double || this.value instanceof Integer || this.value instanceof Short || this.value instanceof Long || this.value instanceof Float;
    }
    
    public void setVisibility(final Predicate<T> visibility) {
        this.visibility = visibility;
    }
    
    public boolean isVisible() {
        return this.visibility == null || this.visibility.test(this.getValue());
    }
    
    public void setEnumValue(final String anotherString) {
        for (final Enum value : (Enum[])((Enum)this.value).getClass().getEnumConstants()) {
            if (value.name().equalsIgnoreCase(anotherString)) {
                this.value = (T)value;
            }
        }
    }
    
    public Setting(final String name, final T plannedValue, final T min, final T max, final Predicate<T> visibility, final String description) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.min = min;
        this.max = max;
        this.plannedValue = plannedValue;
        this.visibility = visibility;
        this.description = description;
        this.hasRestriction = true;
    }
    
    public void increaseEnum() {
        this.plannedValue = (T)EnumConverter.increaseEnum((Enum)this.value);
        final ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public void increaseEnumNoEvent() {
        this.value = (T)EnumConverter.increaseEnum((Enum)this.value);
    }
    
    public void setValue(final T plannedValue) {
        this.setPlannedValue(plannedValue);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        final ClientEvent clientEvent = new ClientEvent(this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.value = this.plannedValue;
        }
        else {
            this.plannedValue = this.value;
        }
    }
    
    public Setting(final String name, final T plannedValue, final T min, final T max) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.min = min;
        this.max = max;
        this.plannedValue = plannedValue;
        this.description = "";
        this.hasRestriction = true;
    }
    
    public Setting(final String name, final T plannedValue, final String description) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.plannedValue = plannedValue;
        this.description = description;
    }
    
    public Setting(final String name, final T plannedValue, final Predicate<T> visibility) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.visibility = visibility;
        this.plannedValue = plannedValue;
    }
    
    public void setFeature(final Feature feature) {
        this.feature = feature;
    }
    
    public int currentEnum() {
        return EnumConverter.currentEnum((Enum)this.value);
    }
    
    public String currentEnumName() {
        return EnumConverter.getProperName((Enum)this.value);
    }
    
    public T getValue() {
        return this.value;
    }
    
    public String getName() {
        return this.name;
    }
    
    public Setting(final String name, final T plannedValue, final T min, final T max, final String description) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.min = min;
        this.max = max;
        this.plannedValue = plannedValue;
        this.description = description;
        this.hasRestriction = true;
    }
    
    public T getPlannedValue() {
        return this.plannedValue;
    }
    
    public String getType() {
        if (this.isEnumSetting()) {
            return "Enum";
        }
        return this.getClassName(this.defaultValue);
    }
    
    public int getEnum(final String anotherString) {
        for (int i = 0; i < this.value.getClass().getEnumConstants().length; ++i) {
            if (((Enum)this.value.getClass().getEnumConstants()[i]).name().equalsIgnoreCase(anotherString)) {
                return i;
            }
        }
        return -1;
    }
    
    public String getValueAsString() {
        return this.value.toString();
    }
    
    public T getMin() {
        return this.min;
    }
    
    public boolean hasRestriction() {
        return this.hasRestriction;
    }
    
    public void setMax(final T max) {
        this.max = max;
    }
    
    public Setting(final String name, final T plannedValue, final T min, final T max, final Predicate<T> visibility) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.min = min;
        this.max = max;
        this.plannedValue = plannedValue;
        this.visibility = visibility;
        this.description = "";
        this.hasRestriction = true;
    }
    
    public void setValueNoEvent(final T plannedValue) {
        this.setPlannedValue(plannedValue);
        if (this.hasRestriction) {
            if (((Number)this.min).floatValue() > ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.min);
            }
            if (((Number)this.max).floatValue() < ((Number)plannedValue).floatValue()) {
                this.setPlannedValue(this.max);
            }
        }
        this.value = this.plannedValue;
    }
    
    public T getMax() {
        return this.max;
    }
    
    public Setting(final String name, final T plannedValue) {
        this.name = name;
        this.defaultValue = plannedValue;
        this.value = plannedValue;
        this.plannedValue = plannedValue;
        this.description = "";
    }
    
    public boolean isStringSetting() {
        return this.value instanceof String;
    }
    
    public boolean isEnumSetting() {
        return !this.isNumberSetting() && !(this.value instanceof String) && !(this.value instanceof Bind) && !(this.value instanceof Character) && !(this.value instanceof Boolean);
    }
    
    public <T> String getClassName(final T t) {
        return t.getClass().getSimpleName();
    }
    
    public T getDefaultValue() {
        return this.defaultValue;
    }
}

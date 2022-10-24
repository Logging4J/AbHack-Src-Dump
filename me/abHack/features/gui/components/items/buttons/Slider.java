//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import me.abHack.features.setting.*;
import me.abHack.features.gui.*;
import me.abHack.features.gui.components.*;
import java.util.*;
import org.lwjgl.input.*;
import me.abHack.util.*;
import me.abHack.*;
import me.abHack.features.modules.client.*;
import com.mojang.realmsclient.gui.*;

public class Slider extends Button
{
    private final /* synthetic */ int difference;
    private final /* synthetic */ Number min;
    private final /* synthetic */ Number max;
    public /* synthetic */ Setting setting;
    
    private float middle() {
        return this.max.floatValue() - this.min.floatValue();
    }
    
    public boolean isHovering(final int n, final int n2) {
        final Iterator<Component> iterator = OyVeyGui.getClickGui().getComponents().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() + 8.0f && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    private void setSettingFromX(final int n) {
        final float n2 = (n - this.x) / (this.width + 7.4f);
        if (this.setting.getValue() instanceof Double) {
            this.setting.setValue(Math.round(10.0 * (this.setting.getMin() + this.difference * n2)) / 10.0);
        }
        else if (this.setting.getValue() instanceof Float) {
            this.setting.setValue(Math.round(10.0f * (this.setting.getMin() + this.difference * n2)) / 10.0f);
        }
        else if (this.setting.getValue() instanceof Integer) {
            this.setting.setValue(this.setting.getMin() + (int)(this.difference * n2));
        }
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    private void dragSetting(final int settingFromX, final int n) {
        if (this.isHovering(settingFromX, n) && Mouse.isButtonDown(0)) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    public int getHeight() {
        return 14;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.dragSetting(n, n2);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.isHovering(n, n2) ? -2007673515 : 290805077);
        RenderUtil.drawRect(this.x, this.y, (this.setting.getValue().floatValue() <= this.min.floatValue()) ? this.x : (this.x + (this.width + 7.4f) * this.partialMultiplier()), this.y + this.height - 0.5f, this.isHovering(n, n2) ? OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue()));
        OyVey.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.getName()).append(" ").append(ChatFormatting.GRAY).append((this.setting.getValue() instanceof Float) ? this.setting.getValue() : this.setting.getValue().doubleValue())), this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), -1);
    }
    
    private float part() {
        return this.setting.getValue().floatValue() - this.min.floatValue();
    }
    
    public void mouseClicked(final int settingFromX, final int n, final int n2) {
        super.mouseClicked(settingFromX, n, n2);
        if (this.isHovering(settingFromX, n)) {
            this.setSettingFromX(settingFromX);
        }
    }
    
    public Slider(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.min = setting.getMin();
        this.max = setting.getMax();
        this.difference = this.max.intValue() - this.min.intValue();
        this.width = 15;
    }
    
    private float partialMultiplier() {
        return this.part() / this.middle();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.abHack.features.setting.*;
import me.abHack.features.modules.client.*;
import me.abHack.*;
import me.abHack.util.*;
import me.abHack.features.gui.*;
import com.mojang.realmsclient.gui.*;

public class BindButton extends Button
{
    public /* synthetic */ boolean isListening;
    private final /* synthetic */ Setting setting;
    
    @Override
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            BindButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    @Override
    public boolean getState() {
        return !this.isListening;
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
    
    public BindButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    @Override
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    @Override
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            Bind value = new Bind(n);
            if (value.toString().equalsIgnoreCase("Escape")) {
                return;
            }
            if (value.toString().equalsIgnoreCase("Delete")) {
                value = new Bind(-1);
            }
            this.setting.setValue(value);
            this.onMouseClick();
        }
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        ColorUtil.toARGB(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 255);
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? -2007673515 : 290805077) : (this.isHovering(n, n2) ? OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())));
        if (this.isListening) {
            OyVey.textManager.drawStringWithShadow("Press a Key...", this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), -1);
        }
        else {
            OyVey.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName()).append(" ").append(ChatFormatting.GRAY).append(this.setting.getValue().toString().toUpperCase())), this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
}

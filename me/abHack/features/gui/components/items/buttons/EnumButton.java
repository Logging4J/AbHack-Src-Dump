//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.abHack.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.gui.*;

public class EnumButton extends Button
{
    public /* synthetic */ Setting setting;
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            EnumButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        OyVey.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName()).append(" ").append(ChatFormatting.GRAY).append(this.setting.currentEnumName().equalsIgnoreCase("ABC") ? "ABC" : this.setting.currentEnumName())), this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    public boolean getState() {
        return true;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public int getHeight() {
        return 14;
    }
    
    public EnumButton(final Setting setting) {
        super(setting.getName());
        this.setting = setting;
        this.width = 15;
    }
    
    public void toggle() {
        this.setting.increaseEnum();
    }
}

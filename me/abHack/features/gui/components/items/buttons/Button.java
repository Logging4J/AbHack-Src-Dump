//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import me.abHack.features.gui.components.items.*;
import me.abHack.features.gui.*;
import me.abHack.features.gui.components.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import me.abHack.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;

public class Button extends Item
{
    private /* synthetic */ boolean state;
    
    public boolean isHovering(final int n, final int n2) {
        final Iterator<Component> iterator = OyVeyGui.getClickGui().getComponents().iterator();
        while (iterator.hasNext()) {
            if (!iterator.next().drag) {
                continue;
            }
            return false;
        }
        return n >= this.getX() && n <= this.getX() + this.getWidth() && n2 >= this.getY() && n2 <= this.getY() + this.height;
    }
    
    @Override
    public void mouseClicked(final int n, final int n2, final int n3) {
        if (n3 == 0 && this.isHovering(n, n2)) {
            this.onMouseClick();
        }
    }
    
    public Button(final String s) {
        super(s);
        this.height = 15;
    }
    
    public boolean getState() {
        return this.state;
    }
    
    public void onMouseClick() {
        this.state = !this.state;
        this.toggle();
        Button.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
    }
    
    public void toggle() {
    }
    
    @Override
    public void drawScreen(final int n, final int n2, final float n3) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        OyVey.textManager.drawStringWithShadow(this.getName(), this.x + 2.3f, this.y - 2.0f - OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
    }
    
    @Override
    public int getHeight() {
        return 14;
    }
}

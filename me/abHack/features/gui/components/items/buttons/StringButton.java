//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.components.items.buttons;

import me.abHack.features.setting.*;
import me.abHack.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import me.abHack.features.gui.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.init.*;
import net.minecraft.client.audio.*;
import org.lwjgl.input.*;
import java.awt.*;
import java.awt.datatransfer.*;
import net.minecraft.util.*;

public class StringButton extends Button
{
    private final /* synthetic */ Setting setting;
    private /* synthetic */ CurrentString currentString;
    public /* synthetic */ boolean isListening;
    
    public int getHeight() {
        return 14;
    }
    
    public void update() {
        this.setHidden(!this.setting.isVisible());
    }
    
    public StringButton(final Setting setting) {
        super(setting.getName());
        this.currentString = new CurrentString("");
        this.setting = setting;
        this.width = 15;
    }
    
    private void enterString() {
        if (this.currentString.getString().isEmpty()) {
            this.setting.setValue(this.setting.getDefaultValue());
        }
        else {
            this.setting.setValue(this.currentString.getString());
        }
        this.setString("");
        this.onMouseClick();
    }
    
    public static String removeLastChar(final String s) {
        String substring = "";
        if (s != null && s.length() > 0) {
            substring = s.substring(0, s.length() - 1);
        }
        return substring;
    }
    
    public boolean getState() {
        return !this.isListening;
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        RenderUtil.drawRect(this.x, this.y, this.x + this.width + 7.4f, this.y + this.height - 0.5f, this.getState() ? (this.isHovering(n, n2) ? OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).alpha.getValue()) : OyVey.colorManager.getColorWithAlpha(OyVey.moduleManager.getModuleByClass(ClickGui.class).hoverAlpha.getValue())) : (this.isHovering(n, n2) ? -2007673515 : 290805077));
        if (this.isListening) {
            OyVey.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(OyVey.textManager.getIdleSign())), this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
        else {
            OyVey.textManager.drawStringWithShadow(String.valueOf(new StringBuilder().append(this.setting.getName().equals("Buttons") ? "Buttons " : (this.setting.getName().equals("Prefix") ? String.valueOf(new StringBuilder().append("Prefix  ").append(ChatFormatting.GRAY)) : "")).append(this.setting.getValue())), this.x + 2.3f, this.y - 1.7f - OyVeyGui.getClickGui().getTextOffset(), this.getState() ? -1 : -5592406);
        }
    }
    
    public void setString(final String s) {
        this.currentString = new CurrentString(s);
    }
    
    public void mouseClicked(final int n, final int n2, final int n3) {
        super.mouseClicked(n, n2, n3);
        if (this.isHovering(n, n2)) {
            StringButton.mc.getSoundHandler().playSound((ISound)PositionedSoundRecord.getMasterRecord(SoundEvents.UI_BUTTON_CLICK, 1.0f));
        }
    }
    
    public void toggle() {
        this.isListening = !this.isListening;
    }
    
    public void onKeyTyped(final char c, final int n) {
        if (this.isListening) {
            if (n == 1) {
                return;
            }
            if (n == 28) {
                this.enterString();
            }
            else if (n == 14) {
                this.setString(removeLastChar(this.currentString.getString()));
            }
            else {
                Label_0122: {
                    if (n == 47) {
                        if (!Keyboard.isKeyDown(157)) {
                            if (!Keyboard.isKeyDown(29)) {
                                break Label_0122;
                            }
                        }
                        try {
                            this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(Toolkit.getDefaultToolkit().getSystemClipboard().getData(DataFlavor.stringFlavor))));
                        }
                        catch (Exception ex) {
                            ex.printStackTrace();
                        }
                        return;
                    }
                }
                if (ChatAllowedCharacters.isAllowedCharacter(c)) {
                    this.setString(String.valueOf(new StringBuilder().append(this.currentString.getString()).append(c)));
                }
            }
        }
    }
    
    public static class CurrentString
    {
        private final /* synthetic */ String string;
        
        public CurrentString(final String string) {
            this.string = string;
        }
        
        public String getString() {
            return this.string;
        }
    }
}

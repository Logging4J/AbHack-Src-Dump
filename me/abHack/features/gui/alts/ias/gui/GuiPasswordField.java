//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import joptsimple.internal.*;
import net.minecraft.client.gui.*;

public class GuiPasswordField extends GuiTextField
{
    public boolean mouseClicked(final int n, final int n2, final int n3) {
        final String getText = this.getText();
        this.replaceText(Strings.repeat('*', this.getText().length()));
        super.mouseClicked(n, n2, n3);
        this.replaceText(getText);
        return true;
    }
    
    private void replaceText(final String s) {
        final int getCursorPosition = this.getCursorPosition();
        final int getSelectionEnd = this.getSelectionEnd();
        this.setText(s);
        this.setCursorPosition(getCursorPosition);
        this.setSelectionPos(getSelectionEnd);
    }
    
    public void drawTextBox() {
        final String getText = this.getText();
        this.replaceText(Strings.repeat('*', this.getText().length()));
        super.drawTextBox();
        this.replaceText(getText);
    }
    
    public GuiPasswordField(final int n, final FontRenderer fontRenderer, final int n2, final int n3, final int n4, final int n5) {
        super(n, fontRenderer, n2, n3, n4, n5);
    }
    
    public boolean textboxKeyTyped(final char c, final int n) {
        return !GuiScreen.isKeyComboCtrlC(n) && !GuiScreen.isKeyComboCtrlX(n) && super.textboxKeyTyped(c, n);
    }
}

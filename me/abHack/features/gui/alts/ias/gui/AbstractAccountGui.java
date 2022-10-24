//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import net.minecraft.client.gui.*;
import net.minecraft.client.resources.*;
import java.io.*;
import org.lwjgl.input.*;
import me.abHack.features.gui.alts.tools.alt.*;
import me.abHack.features.gui.alts.iasencrypt.*;
import java.util.*;

public abstract class AbstractAccountGui extends GuiScreen
{
    private final /* synthetic */ String actionString;
    private /* synthetic */ GuiButton complete;
    protected /* synthetic */ boolean hasUserChanged;
    private /* synthetic */ GuiTextField username;
    private /* synthetic */ GuiTextField password;
    
    public AbstractAccountGui(final String actionString) {
        this.actionString = actionString;
    }
    
    protected void keyTyped(final char c, final int n) {
        if (n == 1) {
            this.escape();
        }
        else if (n == 28) {
            if (this.username.isFocused()) {
                this.username.setFocused(false);
                this.password.setFocused(true);
            }
            else if (this.password.isFocused() && this.complete.enabled) {
                this.complete();
                this.escape();
            }
        }
        else if (n == 15) {
            this.username.setFocused(!this.username.isFocused());
            this.password.setFocused(!this.password.isFocused());
        }
        else {
            this.username.textboxKeyTyped(c, n);
            this.password.textboxKeyTyped(c, n);
            if (this.username.isFocused()) {
                this.hasUserChanged = true;
            }
        }
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.drawDefaultBackground();
        this.drawCenteredString(this.fontRenderer, I18n.format(this.actionString, (Object[])new Object[0]), this.width / 2, 7, -1);
        this.drawCenteredString(this.fontRenderer, I18n.format("ias.username", (Object[])new Object[0]), this.width / 2 - 130, 66, -1);
        this.drawCenteredString(this.fontRenderer, I18n.format("ias.password", (Object[])new Object[0]), this.width / 2 - 130, 96, -1);
        this.username.drawTextBox();
        this.password.drawTextBox();
        super.drawScreen(n, n2, n3);
    }
    
    public String getUsername() {
        return this.username.getText();
    }
    
    public void setUsername(final String s) {
        this.username.setText(s);
    }
    
    public void updateScreen() {
        this.username.updateCursorCounter();
        this.password.updateCursorCounter();
        this.complete.enabled = this.canComplete();
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        this.username.mouseClicked(n, n2, n3);
        this.password.mouseClicked(n, n2, n3);
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
    }
    
    private void escape() {
        this.mc.displayGuiScreen((GuiScreen)new GuiAccountSelector());
    }
    
    public String getPassword() {
        return this.password.getText();
    }
    
    public boolean canComplete() {
        return this.getUsername().length() > 0 && this.accountNotInList();
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.buttonList.clear();
        this.complete = new GuiButton(2, this.width / 2 - 152, this.height - 28, 150, 20, I18n.format(this.actionString, (Object[])new Object[0]));
        this.buttonList.add(this.complete);
        this.buttonList.add(new GuiButton(3, this.width / 2 + 2, this.height - 28, 150, 20, I18n.format("gui.cancel", (Object[])new Object[0])));
        this.username = new GuiTextField(0, this.fontRenderer, this.width / 2 - 100, 60, 200, 20);
        this.username.setFocused(true);
        this.username.setMaxStringLength(64);
        this.password = new GuiPasswordField(1, this.fontRenderer, this.width / 2 - 100, 90, 200, 20);
        this.password.setMaxStringLength(64);
        this.complete.enabled = false;
    }
    
    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 2) {
                this.complete();
                this.escape();
            }
            else if (guiButton.id == 3) {
                this.escape();
            }
        }
    }
    
    public void setPassword(final String s) {
        this.password.setText(s);
    }
    
    public abstract void complete();
    
    protected boolean accountNotInList() {
        final Iterator<AccountData> iterator = AltDatabase.getInstance().getAlts().iterator();
        while (iterator.hasNext()) {
            if (!EncryptionTools.decode(iterator.next().user).equals(this.getUsername())) {
                continue;
            }
            return false;
        }
        return true;
    }
}

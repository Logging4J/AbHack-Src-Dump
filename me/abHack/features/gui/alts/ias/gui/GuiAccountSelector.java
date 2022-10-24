//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import me.abHack.features.gui.alts.iasencrypt.*;
import me.abHack.features.gui.alts.tools.alt.*;
import net.minecraft.client.*;
import java.util.*;
import me.abHack.features.gui.alts.ias.enums.*;
import me.abHack.features.gui.alts.ias.account.*;
import me.abHack.features.gui.alts.ias.tools.*;
import java.io.*;
import net.minecraft.client.resources.*;
import me.abHack.features.gui.alts.ias.config.*;
import org.lwjgl.input.*;
import me.abHack.features.gui.alts.tools.*;
import net.minecraft.client.gui.*;
import org.apache.commons.lang3.*;

public class GuiAccountSelector extends GuiScreen
{
    private /* synthetic */ int prevIndex;
    private /* synthetic */ List accountsgui;
    private /* synthetic */ String query;
    private /* synthetic */ GuiButton login;
    private /* synthetic */ Throwable loginfailed;
    private /* synthetic */ int selectedAccountIndex;
    private /* synthetic */ GuiButton loginoffline;
    private /* synthetic */ ArrayList<ExtendedAccountData> queriedaccounts;
    private /* synthetic */ GuiButton delete;
    private /* synthetic */ GuiTextField search;
    private /* synthetic */ GuiButton reloadskins;
    private /* synthetic */ GuiButton edit;
    
    private void updateShownSkin() {
        if (!this.queriedaccounts.isEmpty()) {
            SkinTools.buildSkin(this.queriedaccounts.get(this.selectedAccountIndex).alias);
        }
    }
    
    private ArrayList<ExtendedAccountData> convertData() {
        final ArrayList list = (ArrayList)AltDatabase.getInstance().getAlts().clone();
        final ArrayList<ExtendedAccountData> list2 = new ArrayList<ExtendedAccountData>();
        int index = 0;
        for (final AccountData accountData : list) {
            if (accountData instanceof ExtendedAccountData) {
                list2.add((ExtendedAccountData)accountData);
            }
            else {
                list2.add(new ExtendedAccountData(EncryptionTools.decode(accountData.user), EncryptionTools.decode(accountData.pass), accountData.alias));
                AltDatabase.getInstance().getAlts().set(index, (AccountData)new ExtendedAccountData(EncryptionTools.decode(accountData.user), EncryptionTools.decode(accountData.pass), accountData.alias));
            }
            ++index;
        }
        return list2;
    }
    
    private void login(final int index) {
        final ExtendedAccountData extendedAccountData = this.queriedaccounts.get(index);
        this.loginfailed = AltManager.getInstance().setUser(extendedAccountData.user, extendedAccountData.pass);
        if (this.loginfailed == null) {
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
            final ExtendedAccountData currentAsEditable = this.getCurrentAsEditable();
            Objects.requireNonNull(currentAsEditable).premium = EnumBool.TRUE;
            final ExtendedAccountData extendedAccountData2 = currentAsEditable;
            ++extendedAccountData2.useCount;
            currentAsEditable.lastused = JavaTools.getJavaCompat().getDate();
        }
        else if (this.loginfailed instanceof AlreadyLoggedInException) {
            Objects.requireNonNull(this.getCurrentAsEditable()).lastused = JavaTools.getJavaCompat().getDate();
        }
        else if (HttpTools.ping("https://minecraft.net")) {
            Objects.requireNonNull(this.getCurrentAsEditable()).premium = EnumBool.FALSE;
        }
    }
    
    protected void mouseClicked(final int n, final int n2, final int n3) throws IOException {
        super.mouseClicked(n, n2, n3);
        final boolean isFocused = this.search.isFocused();
        this.search.mouseClicked(n, n2, n3);
        if (!isFocused && this.search.isFocused()) {
            this.query = "";
            this.updateText();
            this.updateQueried();
        }
    }
    
    private void updateButtons() {
        this.login.enabled = (!this.queriedaccounts.isEmpty() && !EncryptionTools.decode(this.queriedaccounts.get(this.selectedAccountIndex).pass).equals(""));
        this.loginoffline.enabled = !this.queriedaccounts.isEmpty();
        this.delete.enabled = !this.queriedaccounts.isEmpty();
        this.edit.enabled = !this.queriedaccounts.isEmpty();
        this.reloadskins.enabled = !AltDatabase.getInstance().getAlts().isEmpty();
    }
    
    public void drawScreen(final int n, final int n2, final float n3) {
        this.accountsgui.drawScreen(n, n2, n3);
        this.drawCenteredString(this.fontRenderer, I18n.format("ias.selectaccount", (Object[])new Object[0]), this.width / 2, 4, -1);
        if (this.loginfailed != null) {
            this.drawCenteredString(this.fontRenderer, this.loginfailed.getLocalizedMessage(), this.width / 2, this.height - 62, 16737380);
        }
        this.search.drawTextBox();
        super.drawScreen(n, n2, n3);
        if (!this.queriedaccounts.isEmpty()) {
            SkinTools.javDrawSkin(8, this.height / 2 - 64 - 16, 64, 128);
            Tools.drawBorderedRect(this.width - 8 - 64, this.height / 2 - 64 - 16, this.width - 8, this.height / 2 + 64 - 16, 2, -5855578, -13421773);
            if (this.queriedaccounts.get(this.selectedAccountIndex).premium == EnumBool.TRUE) {
                this.drawString(this.fontRenderer, I18n.format("ias.premium", (Object[])new Object[0]), this.width - 8 - 61, this.height / 2 - 64 - 13, 6618980);
            }
            else if (this.queriedaccounts.get(this.selectedAccountIndex).premium == EnumBool.FALSE) {
                this.drawString(this.fontRenderer, I18n.format("ias.notpremium", (Object[])new Object[0]), this.width - 8 - 61, this.height / 2 - 64 - 13, 16737380);
            }
            this.drawString(this.fontRenderer, I18n.format("ias.timesused", (Object[])new Object[0]), this.width - 8 - 61, this.height / 2 - 64 - 15 + 12, -1);
            this.drawString(this.fontRenderer, String.valueOf(this.queriedaccounts.get(this.selectedAccountIndex).useCount), this.width - 8 - 61, this.height / 2 - 64 - 15 + 21, -1);
            if (this.queriedaccounts.get(this.selectedAccountIndex).useCount > 0) {
                this.drawString(this.fontRenderer, I18n.format("ias.lastused", (Object[])new Object[0]), this.width - 8 - 61, this.height / 2 - 64 - 15 + 30, -1);
                this.drawString(this.fontRenderer, JavaTools.getJavaCompat().getFormattedDate(), this.width - 8 - 61, this.height / 2 - 64 - 15 + 39, -1);
            }
        }
    }
    
    private void updateQueried() {
        this.queriedaccounts = this.convertData();
        if (!this.query.equals(I18n.format("ias.search", (Object[])new Object[0])) && !this.query.equals("")) {
            for (int i = 0; i < this.queriedaccounts.size(); ++i) {
                if (!this.queriedaccounts.get(i).alias.contains(this.query) && ConfigValues.CASESENSITIVE) {
                    this.queriedaccounts.remove(i);
                    --i;
                }
                else if (!this.queriedaccounts.get(i).alias.toLowerCase().contains(this.query.toLowerCase())) {
                    if (!ConfigValues.CASESENSITIVE) {
                        this.queriedaccounts.remove(i);
                        --i;
                    }
                }
            }
        }
        if (!this.queriedaccounts.isEmpty()) {
            while (this.selectedAccountIndex >= this.queriedaccounts.size()) {
                --this.selectedAccountIndex;
            }
        }
    }
    
    private void updateText() {
        this.search.setText(this.query);
    }
    
    protected void keyTyped(final char c, final int n) {
        if (n == 200 && !this.queriedaccounts.isEmpty()) {
            if (this.selectedAccountIndex > 0) {
                --this.selectedAccountIndex;
            }
        }
        else if (n == 208 && !this.queriedaccounts.isEmpty()) {
            if (this.selectedAccountIndex < this.queriedaccounts.size() - 1) {
                ++this.selectedAccountIndex;
            }
        }
        else if (n == 1) {
            this.escape();
        }
        else if (n == 211 && this.delete.enabled) {
            this.delete();
        }
        else if (c == '+') {
            this.add();
        }
        else if (c == '/' && this.edit.enabled) {
            this.edit();
        }
        else if (!this.search.isFocused() && n == 19) {
            this.reloadSkins();
        }
        else if (n == 28 && !this.search.isFocused() && (this.login.enabled || this.loginoffline.enabled)) {
            if ((Keyboard.isKeyDown(54) || Keyboard.isKeyDown(42)) && this.loginoffline.enabled) {
                this.logino(this.selectedAccountIndex);
            }
            else if (this.login.enabled) {
                this.login(this.selectedAccountIndex);
            }
        }
        else if (n == 14) {
            if (this.search.isFocused() && this.query.length() > 0) {
                this.query = this.query.substring(0, this.query.length() - 1);
                this.updateText();
                this.updateQueried();
            }
        }
        else if (n == 63) {
            this.reloadSkins();
        }
        else if (c != '\0' && this.search.isFocused()) {
            if (n == 28) {
                this.search.setFocused(false);
                this.updateText();
                this.updateQueried();
                return;
            }
            this.query = String.valueOf(new StringBuilder().append(this.query).append(c));
            this.updateText();
            this.updateQueried();
        }
    }
    
    public void initGui() {
        Keyboard.enableRepeatEvents(true);
        this.accountsgui = new List(this.mc);
        this.accountsgui.registerScrollButtons(5, 6);
        this.query = I18n.format("ias.search", (Object[])new Object[0]);
        this.buttonList.clear();
        this.reloadskins = new GuiButton(8, this.width / 2 - 154 - 10, this.height - 76 - 8, 120, 20, I18n.format("ias.reloadskins", (Object[])new Object[0]));
        this.buttonList.add(this.reloadskins);
        this.buttonList.add(new GuiButton(0, this.width / 2 + 4 + 40, this.height - 52, 120, 20, I18n.format("ias.addaccount", (Object[])new Object[0])));
        this.login = new GuiButton(1, this.width / 2 - 154 - 10, this.height - 52, 120, 20, I18n.format("ias.login", (Object[])new Object[0]));
        this.buttonList.add(this.login);
        this.edit = new GuiButton(7, this.width / 2 - 40, this.height - 52, 80, 20, I18n.format("ias.edit", (Object[])new Object[0]));
        this.buttonList.add(this.edit);
        this.loginoffline = new GuiButton(2, this.width / 2 - 154 - 10, this.height - 28, 110, 20, String.valueOf(new StringBuilder().append(I18n.format("ias.login", (Object[])new Object[0])).append(" ").append(I18n.format("ias.offline", (Object[])new Object[0]))));
        this.buttonList.add(this.loginoffline);
        this.buttonList.add(new GuiButton(3, this.width / 2 + 4 + 50, this.height - 28, 110, 20, I18n.format("gui.cancel", (Object[])new Object[0])));
        this.delete = new GuiButton(4, this.width / 2 - 50, this.height - 28, 100, 20, I18n.format("ias.delete", (Object[])new Object[0]));
        this.buttonList.add(this.delete);
        this.search = new GuiTextField(8, this.fontRenderer, this.width / 2 - 80, 14, 160, 16);
        this.search.setText(this.query);
        this.updateButtons();
        if (!this.queriedaccounts.isEmpty()) {
            SkinTools.buildSkin(this.queriedaccounts.get(this.selectedAccountIndex).alias);
        }
    }
    
    private void escape() {
        this.mc.displayGuiScreen((GuiScreen)null);
    }
    
    private void delete() {
        AltDatabase.getInstance().getAlts().remove(this.getCurrentAsEditable());
        if (this.selectedAccountIndex > 0) {
            --this.selectedAccountIndex;
        }
        this.updateQueried();
        this.updateButtons();
    }
    
    private ExtendedAccountData getCurrentAsEditable() {
        for (final AccountData accountData : this.getAccountList()) {
            if (accountData instanceof ExtendedAccountData) {
                if (!accountData.equals(this.queriedaccounts.get(this.selectedAccountIndex))) {
                    continue;
                }
                return (ExtendedAccountData)accountData;
            }
        }
        return null;
    }
    
    private void reloadSkins() {
        Config.save();
        SkinTools.cacheSkins();
        this.updateShownSkin();
    }
    
    public GuiAccountSelector() {
        this.queriedaccounts = this.convertData();
    }
    
    private ArrayList<AccountData> getAccountList() {
        return AltDatabase.getInstance().getAlts();
    }
    
    protected void actionPerformed(final GuiButton guiButton) {
        if (guiButton.enabled) {
            if (guiButton.id == 3) {
                this.escape();
            }
            else if (guiButton.id == 0) {
                this.add();
            }
            else if (guiButton.id == 4) {
                this.delete();
            }
            else if (guiButton.id == 1) {
                this.login(this.selectedAccountIndex);
            }
            else if (guiButton.id == 2) {
                this.logino(this.selectedAccountIndex);
            }
            else if (guiButton.id == 7) {
                this.edit();
            }
            else if (guiButton.id == 8) {
                this.reloadSkins();
            }
            else {
                this.accountsgui.actionPerformed(guiButton);
            }
        }
    }
    
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        this.accountsgui.handleMouseInput();
    }
    
    public void onGuiClosed() {
        Keyboard.enableRepeatEvents(false);
        Config.save();
    }
    
    public void updateScreen() {
        this.search.updateCursorCounter();
        this.updateText();
        this.updateButtons();
        if (this.prevIndex != this.selectedAccountIndex) {
            this.updateShownSkin();
            this.prevIndex = this.selectedAccountIndex;
        }
    }
    
    private void add() {
        this.mc.displayGuiScreen((GuiScreen)new GuiAddAccount());
    }
    
    private void logino(final int index) {
        AltManager.getInstance().setUserOffline(this.queriedaccounts.get(index).alias);
        this.loginfailed = null;
        Minecraft.getMinecraft().displayGuiScreen((GuiScreen)null);
        final ExtendedAccountData currentAsEditable = this.getCurrentAsEditable();
        final ExtendedAccountData extendedAccountData = Objects.requireNonNull(currentAsEditable);
        ++extendedAccountData.useCount;
        currentAsEditable.lastused = JavaTools.getJavaCompat().getDate();
    }
    
    private void edit() {
        this.mc.displayGuiScreen((GuiScreen)new GuiEditAccount(this.selectedAccountIndex));
    }
    
    class List extends GuiSlot
    {
        protected int getSize() {
            return GuiAccountSelector.this.queriedaccounts.size();
        }
        
        protected boolean isSelected(final int n) {
            return n == GuiAccountSelector.this.selectedAccountIndex;
        }
        
        protected void drawSlot(final int index, final int n, final int n2, final int n3, final int n4, final int n5, final float n6) {
            final ExtendedAccountData extendedAccountData = GuiAccountSelector.this.queriedaccounts.get(index);
            String s = extendedAccountData.alias;
            if (StringUtils.isEmpty((CharSequence)s)) {
                s = String.valueOf(new StringBuilder().append(I18n.format("ias.alt", (Object[])new Object[0])).append(" ").append(index + 1));
            }
            int n7 = 16777215;
            if (Minecraft.getMinecraft().getSession().getUsername().equals(extendedAccountData.alias)) {
                n7 = 65280;
            }
            GuiAccountSelector.this.drawString(GuiAccountSelector.this.fontRenderer, s, n + 2, n2 + 1, n7);
        }
        
        protected int getContentHeight() {
            return GuiAccountSelector.this.queriedaccounts.size() * 14;
        }
        
        public List(final Minecraft minecraft) {
            super(minecraft, GuiAccountSelector.this.width, GuiAccountSelector.this.height, 32, GuiAccountSelector.this.height - 64, 14);
        }
        
        protected void elementClicked(final int n, final boolean b, final int n2, final int n3) {
            GuiAccountSelector.this.selectedAccountIndex = n;
            GuiAccountSelector.this.updateButtons();
            if (b && GuiAccountSelector.this.login.enabled) {
                GuiAccountSelector.this.login(n);
            }
        }
        
        protected void drawBackground() {
            GuiAccountSelector.this.drawDefaultBackground();
        }
    }
}

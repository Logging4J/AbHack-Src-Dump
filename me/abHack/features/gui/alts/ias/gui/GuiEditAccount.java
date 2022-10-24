//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import me.abHack.features.gui.alts.ias.account.*;
import me.abHack.features.gui.alts.tools.alt.*;
import me.abHack.features.gui.alts.ias.tools.*;
import me.abHack.features.gui.alts.ias.enums.*;
import me.abHack.features.gui.alts.iasencrypt.*;

class GuiEditAccount extends AbstractAccountGui
{
    private final /* synthetic */ ExtendedAccountData data;
    private final /* synthetic */ int selectedIndex;
    
    public void complete() {
        AltDatabase.getInstance().getAlts().set(this.selectedIndex, (AccountData)new ExtendedAccountData(this.getUsername(), this.getPassword(), this.hasUserChanged ? this.getUsername() : this.data.alias, this.data.useCount, this.data.lastused, this.data.premium));
    }
    
    public GuiEditAccount(final int n) {
        super("ias.editaccount");
        this.selectedIndex = n;
        final AccountData accountData = AltDatabase.getInstance().getAlts().get(n);
        this.data = ((accountData instanceof ExtendedAccountData) ? accountData : new ExtendedAccountData(accountData.user, accountData.pass, accountData.alias, 0, JavaTools.getJavaCompat().getDate(), EnumBool.UNKNOWN));
    }
    
    public void initGui() {
        super.initGui();
        this.setUsername(EncryptionTools.decode(this.data.user));
        this.setPassword(EncryptionTools.decode(this.data.pass));
    }
}

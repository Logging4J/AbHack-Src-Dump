//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools.alt;

import java.io.*;
import me.abHack.features.gui.alts.iasencrypt.*;

public class AccountData implements Serializable
{
    public final /* synthetic */ String pass;
    public final /* synthetic */ String user;
    public /* synthetic */ String alias;
    
    public boolean equalsBasic(final AccountData accountData) {
        return this == accountData || (accountData != null && this.getClass() == accountData.getClass() && this.user.equals(accountData.user));
    }
    
    protected AccountData(final String s, final String s2, final String alias) {
        this.user = EncryptionTools.encode(s);
        this.pass = EncryptionTools.encode(s2);
        this.alias = alias;
    }
}

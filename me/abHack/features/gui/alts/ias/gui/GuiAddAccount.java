//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.gui;

import me.abHack.features.gui.alts.ias.account.*;
import me.abHack.features.gui.alts.tools.alt.*;

public class GuiAddAccount extends AbstractAccountGui
{
    public void complete() {
        AltDatabase.getInstance().getAlts().add((AccountData)new ExtendedAccountData(this.getUsername(), this.getPassword(), this.getUsername()));
    }
    
    public GuiAddAccount() {
        super("ias.addaccount");
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.account;

import net.minecraft.client.resources.*;

public class AlreadyLoggedInException extends Exception
{
    @Override
    public String getLocalizedMessage() {
        return I18n.format("ias.alreadyloggedin", (Object[])new Object[0]);
    }
}

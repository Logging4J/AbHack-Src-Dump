//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import net.minecraft.client.*;
import java.awt.*;
import java.awt.datatransfer.*;

public final class IpCommand extends Command
{
    public void execute(final String[] array) {
        final Minecraft getMinecraft = Minecraft.getMinecraft();
        if (getMinecraft.getCurrentServerData() != null) {
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(getMinecraft.getCurrentServerData().serverIP), null);
            Command.sendMessage("Copied IP to clipboard");
        }
        else {
            Command.sendMessage("Error, Join a server");
        }
    }
    
    public IpCommand() {
        super("IP", new String[0]);
    }
}

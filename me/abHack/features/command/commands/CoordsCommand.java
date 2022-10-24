//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import java.text.*;
import java.awt.*;
import java.awt.datatransfer.*;

public class CoordsCommand extends Command
{
    public void execute(final String[] array) {
        final DecimalFormat decimalFormat = new DecimalFormat("#");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.valueOf(new StringBuilder().append(decimalFormat.format(CoordsCommand.mc.player.posX)).append(", ").append(decimalFormat.format(CoordsCommand.mc.player.posY)).append(", ").append(decimalFormat.format(CoordsCommand.mc.player.posZ)))), null);
        Command.sendMessage("Saved Coordinates To Clipboard.");
    }
    
    public CoordsCommand() {
        super("coords", new String[0]);
    }
}

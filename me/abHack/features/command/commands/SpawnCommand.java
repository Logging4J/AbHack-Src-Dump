//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import java.text.*;
import java.awt.*;
import net.minecraft.client.*;
import java.awt.datatransfer.*;

public class SpawnCommand extends Command
{
    public SpawnCommand() {
        super("spawnCoord", new String[0]);
    }
    
    public void execute(final String[] array) {
        final DecimalFormat decimalFormat = new DecimalFormat("#");
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(new StringSelection(String.valueOf(new StringBuilder().append(Minecraft.getMinecraft().player.getBedLocation()).append(" !"))), null);
        Command.sendMessage("Saved Spawn Coords to Clipboard, use CTRL + V to paste.");
    }
}

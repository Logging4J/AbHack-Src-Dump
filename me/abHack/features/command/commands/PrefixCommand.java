//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.*;

public class PrefixCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Current prefix is ").append(OyVey.commandManager.getPrefix())));
            return;
        }
        OyVey.commandManager.setPrefix(array[0]);
        Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix changed to ").append(ChatFormatting.GRAY).append(array[0])));
    }
    
    public PrefixCommand() {
        super("prefix", new String[] { "<char>" });
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class HelpCommand extends Command
{
    public HelpCommand() {
        super("help");
    }
    
    public void execute(final String[] array) {
        sendMessage("Commands: ");
        final Iterator<Command> iterator = OyVey.commandManager.getCommands().iterator();
        while (iterator.hasNext()) {
            sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append(OyVey.commandManager.getPrefix()).append(iterator.next().getName())));
        }
    }
}

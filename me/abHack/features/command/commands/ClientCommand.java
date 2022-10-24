//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import org.lwjgl.opengl.*;
import com.mojang.realmsclient.gui.*;

public class ClientCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1 || array[0] == null) {
            Display.setTitle("ab-Hack 0.0.1");
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(".Client <Client Name>")));
            return;
        }
        String value = "ab-Hack 0.0.1";
        if (array[0] != null) {
            value = array[0];
        }
        if (array[0] != null && array[1] != null) {
            value = String.valueOf(new StringBuilder().append(array[0]).append(" ").append(array[1]));
        }
        Display.setTitle(value);
        Command.sendMessage("new Client Name.");
    }
    
    public ClientCommand() {
        super("Client", new String[] { "<name>" });
    }
}

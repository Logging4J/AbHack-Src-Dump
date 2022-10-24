//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import org.lwjgl.input.*;
import me.abHack.features.setting.*;
import me.abHack.features.modules.*;

public class BindCommand extends Command
{
    public BindCommand() {
        super("bind", new String[] { "<module>", "<bind>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage("Please specify a module.");
            return;
        }
        final String str = array[1];
        final Module moduleByName = OyVey.moduleManager.getModuleByName(array[0]);
        if (moduleByName == null) {
            sendMessage(String.valueOf(new StringBuilder().append("Unknown module '").append(moduleByName).append("'!")));
            return;
        }
        if (str == null) {
            sendMessage(String.valueOf(new StringBuilder().append(moduleByName.getName()).append(" is bound to ").append(ChatFormatting.GRAY).append(moduleByName.getBind().toString())));
            return;
        }
        int keyIndex = Keyboard.getKeyIndex(str.toUpperCase());
        if (str.equalsIgnoreCase("none")) {
            keyIndex = -1;
        }
        if (keyIndex == 0) {
            sendMessage(String.valueOf(new StringBuilder().append("Unknown key '").append(str).append("'!")));
            return;
        }
        moduleByName.bind.setValue(new Bind(keyIndex));
        sendMessage(String.valueOf(new StringBuilder().append("Bind for ").append(ChatFormatting.GREEN).append(moduleByName.getName()).append(ChatFormatting.WHITE).append(" set to ").append(ChatFormatting.GRAY).append(str.toUpperCase())));
    }
}

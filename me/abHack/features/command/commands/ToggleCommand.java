//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;
import me.abHack.features.modules.*;

public class ToggleCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 2) {
            final Module moduleByName = OyVey.moduleManager.getModuleByName(array[0].replaceAll("_", " "));
            if (moduleByName != null) {
                moduleByName.toggle();
            }
            else {
                Command.sendMessage("Unable to find a module with that name!");
            }
        }
        else {
            Command.sendMessage("Please provide a valid module name!");
        }
    }
    
    public ToggleCommand() {
        super("toggle", new String[] { "<toggle>", "<module>" });
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;

public class ClearRamCommand extends Command
{
    public ClearRamCommand() {
        super("clearram");
    }
    
    public void execute(final String[] array) {
        System.gc();
        Command.sendMessage("Finished clearing the ram.");
    }
}

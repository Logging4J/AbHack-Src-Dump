//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;

public class ReloadCommand extends Command
{
    public void execute(final String[] array) {
        OyVey.reload();
    }
    
    public ReloadCommand() {
        super("reload", new String[0]);
    }
}

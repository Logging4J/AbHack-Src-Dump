//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;

public class VclipCommand extends Command
{
    public VclipCommand() {
        super("vclip", new String[] { "<y>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(".Vclip <Highly>")));
            return;
        }
        final int int1 = Integer.parseInt(array[0]);
        Command.sendMessage(String.valueOf(new StringBuilder().append("Vclip to ").append(ChatFormatting.GRAY).append(int1)));
        VclipCommand.mc.player.setPosition(VclipCommand.mc.player.posX, VclipCommand.mc.player.posY + int1, VclipCommand.mc.player.posZ);
    }
}

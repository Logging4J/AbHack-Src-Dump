//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class HatCommand extends Command
{
    public HatCommand() {
        super("Hat", new String[0]);
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(".Hat <item> <count> <data> <nbt>")));
            return;
        }
        if (array.length == 2) {
            HatCommand.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append("/replaceitem entity @p slot.armor.head ").append(array[0]))));
            Command.sendMessage("New Hat.");
        }
        if (array.length == 3) {
            HatCommand.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append("/replaceitem entity @p slot.armor.head ").append(array[0]).append(" ").append(array[1]))));
            Command.sendMessage("New Hat.");
        }
        if (array.length == 4) {
            HatCommand.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append("/replaceitem entity @p slot.armor.head ").append(array[0]).append(" ").append(array[1]).append(" ").append(array[2]))));
            Command.sendMessage("New Hat.");
        }
        if (array.length == 5) {
            HatCommand.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append("/replaceitem entity @p slot.armor.head ").append(array[0]).append(" ").append(array[1]).append(" ").append(array[2]).append(" ").append(array[3]))));
            Command.sendMessage("New Hat.");
        }
    }
}

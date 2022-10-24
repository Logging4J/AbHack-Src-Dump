//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class TpCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1 || array.length == 2 || array.length > 4) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(".tp <x> <y> <z>")));
            return;
        }
        if (array.length == 4) {
            final int int1 = Integer.parseInt(array[0]);
            final int int2 = Integer.parseInt(array[1]);
            final int int3 = Integer.parseInt(array[2]);
            final int n = (Math.abs(int1) > Math.abs(int2)) ? Math.abs(int1) : Math.abs(int2);
            for (int n2 = (Math.abs(int3) > n) ? Math.abs(int3) : n, i = 0; i < n2; i += 10) {
                TpCommand.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(TpCommand.mc.player.posX, TpCommand.mc.player.posY + 0.001, TpCommand.mc.player.posZ, true));
            }
            TpCommand.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(TpCommand.mc.player.posX + int1, TpCommand.mc.player.posY + int2, TpCommand.mc.player.posZ + int3, false));
        }
    }
    
    public TpCommand() {
        super("tp", new String[] { "<x> <y> <z>" });
    }
}

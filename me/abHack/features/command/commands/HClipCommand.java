//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;

public class HClipCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.DARK_RED).append(".Hclip <distance>")));
            return;
        }
        final double double1 = Double.parseDouble(array[0]);
        final Vec3d vec3d = new Vec3d(Math.cos((HClipCommand.mc.player.rotationYaw + 90.0f) * 0.017453292f), 0.0, Math.sin((HClipCommand.mc.player.rotationYaw + 90.0f) * 0.017453292f));
        final Object o = HClipCommand.mc.player.isRiding() ? HClipCommand.mc.player.getRidingEntity() : HClipCommand.mc.player;
        ((Entity)o).setPosition(((Entity)o).posX + vec3d.x * double1, ((Entity)o).posY, ((Entity)o).posZ + vec3d.z * double1);
        Command.sendMessage(String.valueOf(new StringBuilder().append("Hclip to ").append(double1)));
    }
    
    public HClipCommand() {
        super("hclip", new String[] { "<distance>" });
    }
}

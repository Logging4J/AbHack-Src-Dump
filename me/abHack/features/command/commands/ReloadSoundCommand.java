//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import net.minecraft.client.audio.*;
import net.minecraftforge.fml.common.*;
import com.mojang.realmsclient.gui.*;

public class ReloadSoundCommand extends Command
{
    public void execute(final String[] array) {
        try {
            ((SoundManager)ObfuscationReflectionHelper.getPrivateValue((Class)SoundHandler.class, (Object)ReloadSoundCommand.mc.getSoundHandler(), new String[] { "sndManager", "sndManager" })).reloadSoundSystem();
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Reloaded Sound System.")));
        }
        catch (Exception obj) {
            System.out.println(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Could not restart sound manager: ").append(obj)));
            obj.printStackTrace();
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Couldnt Reload Sound System!")));
        }
    }
    
    public ReloadSoundCommand() {
        super("sound", new String[0]);
    }
}

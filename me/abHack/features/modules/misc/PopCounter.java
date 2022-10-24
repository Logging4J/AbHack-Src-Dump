//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import java.util.*;
import net.minecraft.entity.player.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;

public class PopCounter extends Module
{
    private static /* synthetic */ PopCounter INSTANCE;
    public static /* synthetic */ HashMap<String, Integer> TotemPopContainer;
    
    public void onDeath(final EntityPlayer entityPlayer) {
        if (PopCounter.TotemPopContainer.containsKey(entityPlayer.getName())) {
            final int intValue = PopCounter.TotemPopContainer.get(entityPlayer.getName());
            PopCounter.TotemPopContainer.remove(entityPlayer.getName());
            if (intValue == 1) {
                if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                    Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("You died after popping ").append(ChatFormatting.RED).append(intValue).append(ChatFormatting.RED).append(" Totem!")));
                }
                else {
                    Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(intValue).append(ChatFormatting.RED).append(" Totem!")));
                }
            }
            else if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("You died after popping ").append(ChatFormatting.RED).append(intValue).append(ChatFormatting.RED).append(" Totems!")));
            }
            else {
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityPlayer.getName()).append(" died after popping ").append(ChatFormatting.GREEN).append(intValue).append(ChatFormatting.RED).append(" Totems!")));
            }
        }
    }
    
    @Override
    public void onEnable() {
        PopCounter.TotemPopContainer.clear();
    }
    
    private void setInstance() {
        PopCounter.INSTANCE = this;
    }
    
    static {
        PopCounter.TotemPopContainer = new HashMap<String, Integer>();
        PopCounter.INSTANCE = new PopCounter();
    }
    
    public static PopCounter getInstance() {
        if (PopCounter.INSTANCE == null) {
            PopCounter.INSTANCE = new PopCounter();
        }
        return PopCounter.INSTANCE;
    }
    
    public PopCounter() {
        super("PopCounter", "Counts other players totem pops.", Category.MISC, true, false, false);
        this.setInstance();
    }
    
    public void onTotemPop(final EntityPlayer entityPlayer) {
        if (fullNullCheck()) {
            return;
        }
        int intValue = 1;
        if (PopCounter.TotemPopContainer.containsKey(entityPlayer.getName())) {
            intValue = PopCounter.TotemPopContainer.get(entityPlayer.getName());
            PopCounter.TotemPopContainer.put(entityPlayer.getName(), ++intValue);
        }
        else {
            PopCounter.TotemPopContainer.put(entityPlayer.getName(), intValue);
        }
        if (intValue == 1) {
            if (PopCounter.mc.player.equals((Object)entityPlayer)) {
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("You popped ").append(ChatFormatting.RED).append(intValue).append(ChatFormatting.RED).append(" Totem.")));
            }
            else {
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(intValue).append(ChatFormatting.RED).append(" Totem.")));
            }
        }
        else if (PopCounter.mc.player.equals((Object)entityPlayer)) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.BLUE).append("You popped ").append(ChatFormatting.RED).append(intValue).append(ChatFormatting.RED).append(" Totems.")));
        }
        else {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(entityPlayer.getName()).append(" popped ").append(ChatFormatting.GREEN).append(intValue).append(ChatFormatting.RED).append(" Totems.")));
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.features.modules.misc.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import java.util.*;

public class PeekCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            final ItemStack getHeldItemMainhand = PeekCommand.mc.player.getHeldItemMainhand();
            if (getHeldItemMainhand == null || !(getHeldItemMainhand.getItem() instanceof ItemShulkerBox)) {
                Command.sendMessage("§cYou need to hold a Shulker in your mainhand.");
                return;
            }
            ShulkerViewer.displayInv(getHeldItemMainhand, null);
        }
        if (array.length > 1) {
            if (ShulkerViewer.getInstance().isOn()) {
                for (final Map.Entry<EntityPlayer, ItemStack> entry : ShulkerViewer.getInstance().spiedPlayers.entrySet()) {
                    if (!entry.getKey().getName().equalsIgnoreCase(array[0])) {
                        continue;
                    }
                    ShulkerViewer.displayInv(entry.getValue(), entry.getKey().getName());
                    break;
                }
            }
            else {
                Command.sendMessage("§cYou need to turn on ShulkerViewer Module");
            }
        }
    }
    
    public PeekCommand() {
        super("peek", new String[] { "<player>" });
    }
}

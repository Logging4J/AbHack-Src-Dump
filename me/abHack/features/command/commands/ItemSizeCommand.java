//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import net.minecraft.client.*;
import net.minecraft.item.*;
import io.netty.buffer.*;
import net.minecraft.network.*;

public class ItemSizeCommand extends Command
{
    public void execute(final String[] array) {
        final ItemStack getHeldItemMainhand = Minecraft.getMinecraft().player.getHeldItemMainhand();
        if (getHeldItemMainhand.isEmpty()) {
            Command.sendMessage("You are not holding any item");
        }
        Command.sendMessage(String.valueOf(new StringBuilder().append("Item weights ").append(getItemSize(getHeldItemMainhand)).append(" bytes")));
    }
    
    public static int getItemSize(final ItemStack itemStack) {
        final PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
        packetBuffer.writeItemStack(itemStack);
        final int writerIndex = packetBuffer.writerIndex();
        packetBuffer.release();
        return writerIndex;
    }
    
    public ItemSizeCommand() {
        super("size", new String[0]);
    }
}

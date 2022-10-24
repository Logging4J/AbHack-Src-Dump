//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command;

import me.abHack.features.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;
import java.util.regex.*;

public abstract class Command extends Feature
{
    protected /* synthetic */ String name;
    protected /* synthetic */ String[] commands;
    
    public static void sendMessage(final String str) {
        sendSilentMessage(String.valueOf(new StringBuilder().append(OyVey.commandManager.getClientMessage()).append(" ").append(ChatFormatting.GRAY).append(str)));
    }
    
    public static void sendMessagePh(final String str, final boolean b) {
        sendSilentMessage(String.valueOf(new StringBuilder().append(OyVey.commandManager.getClientMessage()).append(" §r").append(str)));
    }
    
    public abstract void execute(final String[] p0);
    
    public static String getCommandPrefix() {
        return OyVey.commandManager.getPrefix();
    }
    
    public static void sendSilentMessage(final String s) {
        if (nullCheck()) {
            return;
        }
        Command.mc.player.sendMessage((ITextComponent)new ChatMessage(s));
    }
    
    public Command(final String name) {
        super(name);
        this.name = name;
        this.commands = new String[] { "" };
    }
    
    @Override
    public String getName() {
        return this.name;
    }
    
    public String[] getCommands() {
        return this.commands;
    }
    
    public Command(final String name, final String[] commands) {
        super(name);
        this.name = name;
        this.commands = commands;
    }
    
    public static class ChatMessage extends TextComponentBase
    {
        private final /* synthetic */ String text;
        
        public ITextComponent createCopy() {
            return null;
        }
        
        public String getUnformattedComponentText() {
            return this.text;
        }
        
        public ChatMessage(final String input) {
            final Matcher matcher = Pattern.compile("&[0123456789abcdefrlosmk]").matcher(input);
            final StringBuffer sb = new StringBuffer();
            while (matcher.find()) {
                matcher.appendReplacement(sb, matcher.group().substring(1));
            }
            matcher.appendTail(sb);
            this.text = sb.toString();
        }
        
        public ITextComponent shallowCopy() {
            return (ITextComponent)new ChatMessage(this.text);
        }
    }
}

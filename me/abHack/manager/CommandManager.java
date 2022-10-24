//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.commands.*;
import java.util.*;

public class CommandManager extends Feature
{
    private final /* synthetic */ ArrayList<Command> commands;
    private /* synthetic */ String prefix;
    private /* synthetic */ String clientMessage;
    
    private static String strip(final String s, final String s2) {
        if (s.startsWith(s2) && s.endsWith(s2)) {
            return s.substring(s2.length(), s.length() - s2.length());
        }
        return s;
    }
    
    public void executeCommand(final String s) {
        final String[] split = s.split(" (?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
        final String substring = split[0].substring(1);
        final String[] removeElement = removeElement(split, 0);
        for (int i = 0; i < removeElement.length; ++i) {
            if (removeElement[i] != null) {
                removeElement[i] = strip(removeElement[i], "\"");
            }
        }
        for (final Command command : this.commands) {
            if (!command.getName().equalsIgnoreCase(substring)) {
                continue;
            }
            command.execute(split);
            return;
        }
        Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GRAY).append("Command not found, type 'help' for the commands list.")));
    }
    
    public ArrayList<Command> getCommands() {
        return this.commands;
    }
    
    public Command getCommandByName(final String anObject) {
        for (final Command command : this.commands) {
            if (!command.getName().equals(anObject)) {
                continue;
            }
            return command;
        }
        return null;
    }
    
    public void setClientMessage(final String clientMessage) {
        this.clientMessage = clientMessage;
    }
    
    public CommandManager() {
        super("Command");
        this.commands = new ArrayList<Command>();
        this.clientMessage = "<ab-Hack>";
        this.prefix = ".";
        this.commands.add((Command)new BindCommand());
        this.commands.add((Command)new ModuleCommand());
        this.commands.add((Command)new PrefixCommand());
        this.commands.add((Command)new ConfigCommand());
        this.commands.add((Command)new FriendCommand());
        this.commands.add((Command)new HelpCommand());
        this.commands.add((Command)new ReloadCommand());
        this.commands.add((Command)new VclipCommand());
        this.commands.add((Command)new BookCommand());
        this.commands.add((Command)new ClearRamCommand());
        this.commands.add((Command)new IpCommand());
        this.commands.add((Command)new CoordsCommand());
        this.commands.add((Command)new SpawnCommand());
        this.commands.add((Command)new ItemSizeCommand());
        this.commands.add((Command)new PeekCommand());
        this.commands.add((Command)new UnloadCommand());
        this.commands.add((Command)new ToggleCommand());
        this.commands.add((Command)new ReloadSoundCommand());
        this.commands.add((Command)new ClientCommand());
        this.commands.add((Command)new HatCommand());
        this.commands.add((Command)new TpCommand());
        this.commands.add((Command)new HClipCommand());
    }
    
    public void setPrefix(final String prefix) {
        this.prefix = prefix;
    }
    
    public String getPrefix() {
        return this.prefix;
    }
    
    public String getClientMessage() {
        return this.clientMessage;
    }
    
    public static String[] removeElement(final String[] a, final int n) {
        final LinkedList<String> list = new LinkedList<String>();
        for (int i = 0; i < a.length; ++i) {
            if (i != n) {
                list.add(a[i]);
            }
        }
        return list.toArray(a);
    }
}

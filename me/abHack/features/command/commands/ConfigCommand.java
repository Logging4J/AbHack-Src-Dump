//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import java.io.*;
import java.util.stream.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class ConfigCommand extends Command
{
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage(".config <save/load/list>");
            return;
        }
        if (array.length == 2) {
            if ("list".equals(array[0])) {
                final String str = "Configs: ";
                final List<? super File> list = Arrays.stream(new File("ab-Hack/").listFiles()).filter(File::isDirectory).filter(file -> !file.getName().equals("util")).collect((Collector<? super File, ?, List<? super File>>)Collectors.toList());
                final StringBuilder obj = new StringBuilder(str);
                final Iterator<File> iterator = list.iterator();
                while (iterator.hasNext()) {
                    obj.append(String.valueOf(new StringBuilder().append(iterator.next().getName()).append(", ")));
                }
                sendMessage(String.valueOf(obj));
            }
            else {
                sendMessage("Not a valid command... Possible usage: <list>");
            }
        }
        if (array.length >= 3) {
            final String s = array[0];
            switch (s) {
                case "save": {
                    OyVey.configManager.saveConfig(array[1]);
                    sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Config '").append(array[1]).append("' has been saved.")));
                }
                case "load": {
                    if (OyVey.configManager.configExists(array[1])) {
                        OyVey.configManager.loadConfig(array[1]);
                        sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Config '").append(array[1]).append("' has been loaded.")));
                    }
                    else {
                        sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Config '").append(array[1]).append("' does not exist.")));
                    }
                }
            }
        }
    }
    
    public ConfigCommand() {
        super("config", new String[] { "<save/load/list>" });
    }
}

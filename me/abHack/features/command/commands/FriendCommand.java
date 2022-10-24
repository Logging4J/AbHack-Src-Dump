//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.command.commands;

import me.abHack.features.command.*;
import me.abHack.*;
import me.abHack.manager.*;
import com.mojang.realmsclient.gui.*;
import java.util.*;

public class FriendCommand extends Command
{
    public FriendCommand() {
        super("friend", new String[] { "<add/del/reset/list>", "<name>" });
    }
    
    public void execute(final String[] array) {
        if (array.length == 1) {
            sendMessage(".friend <add/del/reset/list>");
            return;
        }
        if (array.length == 2) {
            final String s = array[0];
            switch (s) {
                case "reset": {
                    OyVey.friendManager.onLoad();
                    sendMessage("Friends got reset.");
                }
                case "add": {
                    sendMessage(".friend add <player>");
                }
                case "del": {
                    sendMessage(".friend del <player>");
                }
                case "list": {
                    if (OyVey.friendManager.getFriends().isEmpty()) {
                        sendMessage("No Friends.");
                        break;
                    }
                    String value = "Friends: ";
                    for (final FriendManager.Friend friend : OyVey.friendManager.getFriends()) {
                        try {
                            value = String.valueOf(new StringBuilder().append(value).append(friend.getUsername()).append(", "));
                        }
                        catch (Exception ex) {}
                    }
                    sendMessage(value);
                    break;
                }
            }
            return;
        }
        if (array.length >= 3) {
            final String s2 = array[0];
            switch (s2) {
                case "add": {
                    OyVey.friendManager.addFriend(array[1]);
                    sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append(array[1]).append(" has been friended")));
                }
                case "del": {
                    OyVey.friendManager.removeFriend(array[1]);
                    sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append(array[1]).append(" has been unfriended")));
                }
            }
        }
    }
}

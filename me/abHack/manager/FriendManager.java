//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.features.setting.*;
import java.util.function.*;
import me.abHack.util.*;
import net.minecraft.entity.player.*;
import java.util.*;

public class FriendManager extends Feature
{
    private /* synthetic */ List<Friend> friends;
    
    public void removeFriend(final String anotherString) {
        this.cleanFriends();
        for (final Friend friend : this.friends) {
            if (!friend.getUsername().equalsIgnoreCase(anotherString)) {
                continue;
            }
            this.friends.remove(friend);
            break;
        }
    }
    
    public void saveFriends() {
        this.clearSettings();
        this.cleanFriends();
        for (final Friend friend : this.friends) {
            this.register(new Setting(friend.getUuid().toString(), (Object)friend.getUsername()));
        }
    }
    
    public void addFriend(final Friend friend) {
        this.friends.add(friend);
    }
    
    public void cleanFriends() {
        this.friends.stream().filter(Objects::nonNull).filter(friend -> friend.getUsername() != null);
    }
    
    public Friend getFriendByName(final String s) {
        final UUID uuidFromName = PlayerUtil.getUUIDFromName(s);
        if (uuidFromName != null) {
            return new Friend(s, uuidFromName);
        }
        return null;
    }
    
    public boolean isFriend(final String anotherString) {
        this.cleanFriends();
        return this.friends.stream().anyMatch(friend -> friend.username.equalsIgnoreCase(anotherString));
    }
    
    public List<Friend> getFriends() {
        this.cleanFriends();
        return this.friends;
    }
    
    public void addFriend(final String s) {
        final Friend friendByName = this.getFriendByName(s);
        if (friendByName != null) {
            this.friends.add(friendByName);
        }
        this.cleanFriends();
    }
    
    public boolean isFriend(final EntityPlayer entityPlayer) {
        return this.isFriend(entityPlayer.getName());
    }
    
    public void onLoad() {
        this.friends = new ArrayList<Friend>();
        this.clearSettings();
    }
    
    public FriendManager() {
        super("Friends");
        this.friends = new ArrayList<Friend>();
    }
    
    public static class Friend
    {
        private final /* synthetic */ UUID uuid;
        private final /* synthetic */ String username;
        
        public UUID getUuid() {
            return this.uuid;
        }
        
        public Friend(final String username, final UUID uuid) {
            this.username = username;
            this.uuid = uuid;
        }
        
        public String getUsername() {
            return this.username;
        }
    }
}

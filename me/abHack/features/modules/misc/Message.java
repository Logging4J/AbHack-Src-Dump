//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.util.*;
import me.abHack.features.setting.*;
import me.abHack.*;
import org.apache.commons.lang3.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Message extends Module
{
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> toggle;
    private final /* synthetic */ Setting<String> custom;
    private final /* synthetic */ Setting<Integer> random;
    private final /* synthetic */ Setting<Boolean> escoff;
    
    @Override
    public void onLogin() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("Message")) {
            this.disable();
        }
    }
    
    @Override
    public void onLogout() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("Message")) {
            this.disable();
        }
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (this.timer.passedS(this.delay.getValue())) {
            Message.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(String.valueOf(new StringBuilder().append(this.custom.getValue()).append(RandomStringUtils.randomAlphanumeric((int)this.random.getValue())))));
            this.timer.reset();
        }
        if (this.toggle.getValue()) {
            this.toggle();
        }
    }
    
    public Message() {
        super("Message", "Message", Category.MISC, true, false, false);
        this.timer = new Timer();
        this.custom = (Setting<String>)this.register(new Setting("Custom", (T)"/kit ab "));
        this.random = (Setting<Integer>)this.register(new Setting("Random", (T)0, (T)0, (T)20));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)5, (T)0, (T)20));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)true));
        this.escoff = (Setting<Boolean>)this.register(new Setting("EscOff", (T)true));
    }
}

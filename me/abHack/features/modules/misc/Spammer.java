//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import me.abHack.*;
import me.abHack.util.*;
import java.util.*;

public class Spammer extends Module
{
    private final /* synthetic */ Setting<Boolean> escoff;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Integer> delay;
    public /* synthetic */ Setting<Boolean> greentext;
    private static final /* synthetic */ List<String> spamMessages;
    public /* synthetic */ Setting<Boolean> random;
    public /* synthetic */ Setting<Boolean> loadFile;
    private static final /* synthetic */ Random rnd;
    private final /* synthetic */ List<String> sendPlayers;
    public /* synthetic */ Setting<String> msgTarget;
    public /* synthetic */ Setting<String> custom;
    public /* synthetic */ Setting<Mode> mode;
    
    @Override
    public void onDisable() {
        Spammer.spamMessages.clear();
        this.timer.reset();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            this.disable();
            return;
        }
        if (this.loadFile.getValue()) {
            this.readSpamFile();
            this.loadFile.setValue(false);
        }
        if (this.timer.passedS(this.delay.getValue())) {
            if (this.mode.getValue() == Mode.MSG) {
                Spammer.mc.player.sendChatMessage(String.valueOf(new StringBuilder().append("/msg ").append(this.msgTarget.getValue()).append(" ").append(this.custom.getValue())));
            }
            else if (Spammer.spamMessages.size() > 0) {
                String value;
                if (this.random.getValue()) {
                    final int nextInt = Spammer.rnd.nextInt(Spammer.spamMessages.size());
                    value = Spammer.spamMessages.get(nextInt);
                    Spammer.spamMessages.remove(nextInt);
                }
                else {
                    value = Spammer.spamMessages.get(0);
                    Spammer.spamMessages.remove(0);
                }
                Spammer.spamMessages.add(value);
                if (this.greentext.getValue()) {
                    value = String.valueOf(new StringBuilder().append("> ").append(value));
                }
                Spammer.mc.player.connection.sendPacket((Packet)new CPacketChatMessage(value.replaceAll("§", "")));
            }
            this.timer.reset();
        }
    }
    
    @Override
    public void onLogin() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("Spammer")) {
            this.disable();
        }
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
            return;
        }
        this.readSpamFile();
    }
    
    @Override
    public void onLogout() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("Spammer")) {
            this.disable();
        }
    }
    
    public Spammer() {
        super("Spammer", "Spams stuff.", Category.MISC, true, false, false);
        this.escoff = (Setting<Boolean>)this.register(new Setting("EscOff", (T)true));
        this.timer = new Timer();
        this.sendPlayers = new ArrayList<String>();
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.FILE));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)5, (T)0, (T)20));
        this.custom = (Setting<String>)this.register(new Setting("Custom", (T)"String", p0 -> this.mode.getValue() == Mode.MSG));
        this.msgTarget = (Setting<String>)this.register(new Setting("MsgTarget", (T)"Target...", p0 -> this.mode.getValue() == Mode.MSG));
        this.greentext = (Setting<Boolean>)this.register(new Setting("Greentext", (T)false, p0 -> this.mode.getValue() == Mode.FILE));
        this.random = (Setting<Boolean>)this.register(new Setting("Random", (T)false, p0 -> this.mode.getValue() == Mode.FILE));
        this.loadFile = (Setting<Boolean>)this.register(new Setting("LoadFile", (T)false, p0 -> this.mode.getValue() == Mode.FILE));
    }
    
    private void readSpamFile() {
        final Iterator<String> iterator = FileUtil.readTextFileAllLines("ab-Hack/Spammer.txt").iterator();
        Spammer.spamMessages.clear();
        while (iterator.hasNext()) {
            final String s = iterator.next();
            if (s.replaceAll("\\s", "").isEmpty()) {
                continue;
            }
            Spammer.spamMessages.add(s);
        }
        if (Spammer.spamMessages.size() == 0) {
            Spammer.spamMessages.add("Welcome to use abHack");
        }
    }
    
    static {
        defaultMessage = "Welcome to use abHack";
        fileName = "ab-Hack/Spammer.txt";
        spamMessages = new ArrayList<String>();
        rnd = new Random();
    }
    
    public enum Mode
    {
        FILE, 
        MSG;
    }
}

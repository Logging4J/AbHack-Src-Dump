//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class TpsSync extends Module
{
    public /* synthetic */ Setting<Boolean> mining;
    private static /* synthetic */ TpsSync INSTANCE;
    public /* synthetic */ Setting<Boolean> attack;
    
    private void setInstance() {
        TpsSync.INSTANCE = this;
    }
    
    public static TpsSync getInstance() {
        if (TpsSync.INSTANCE == null) {
            TpsSync.INSTANCE = new TpsSync();
        }
        return TpsSync.INSTANCE;
    }
    
    public TpsSync() {
        super("TpsSync", "Syncs your client with the TPS.", Module.Category.PLAYER, true, false, false);
        this.attack = (Setting<Boolean>)this.register(new Setting("Attack", (T)Boolean.FALSE));
        this.mining = (Setting<Boolean>)this.register(new Setting("Mine", (T)Boolean.TRUE));
        this.setInstance();
    }
    
    static {
        TpsSync.INSTANCE = new TpsSync();
    }
}

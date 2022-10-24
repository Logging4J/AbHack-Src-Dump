//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.item.*;

public class NoEntityTrace extends Module
{
    public /* synthetic */ Setting<Boolean> pick;
    public /* synthetic */ Setting<Boolean> gap;
    public /* synthetic */ Setting<Boolean> obby;
    public /* synthetic */ boolean noTrace;
    private static /* synthetic */ NoEntityTrace INSTANCE;
    
    static {
        NoEntityTrace.INSTANCE = new NoEntityTrace();
    }
    
    @Override
    public void onUpdate() {
        final Item getItem = NoEntityTrace.mc.player.getHeldItemMainhand().getItem();
        if (getItem instanceof ItemPickaxe && this.pick.getValue()) {
            this.noTrace = true;
            return;
        }
        if (getItem == Items.GOLDEN_APPLE && this.gap.getValue()) {
            this.noTrace = true;
            return;
        }
        if (getItem instanceof ItemBlock) {
            this.noTrace = (((ItemBlock)getItem).getBlock() == Blocks.OBSIDIAN && this.obby.getValue());
            return;
        }
        this.noTrace = false;
    }
    
    private void setInstance() {
        NoEntityTrace.INSTANCE = this;
    }
    
    public static NoEntityTrace getINSTANCE() {
        if (NoEntityTrace.INSTANCE == null) {
            NoEntityTrace.INSTANCE = new NoEntityTrace();
        }
        return NoEntityTrace.INSTANCE;
    }
    
    public NoEntityTrace() {
        super("NoEntityTrace", "Mine through entities", Category.MISC, false, false, false);
        this.pick = (Setting<Boolean>)this.register(new Setting("Pick", (T)true));
        this.gap = (Setting<Boolean>)this.register(new Setting("Gap", (T)true));
        this.obby = (Setting<Boolean>)this.register(new Setting("Obby", (T)false));
        this.setInstance();
    }
}

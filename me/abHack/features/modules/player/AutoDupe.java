//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import me.abHack.util.*;
import net.minecraft.block.state.*;

public class AutoDupe extends Module
{
    /* synthetic */ int box;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ BlockPos pos;
    
    public AutoDupe() {
        super("AutoDupe", "Automatically places Shulker", Module.Category.PLAYER, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)0, (T)2000));
        this.timer = new Timer();
    }
    
    public int getItemShulkerBox() {
        int n = -1;
        for (int i = 0; i <= 8; ++i) {
            if (AutoDupe.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemShulkerBox) {
                n = i;
            }
        }
        return n;
    }
    
    public void onUpdate() {
        if (InstantMine.breakPos == null) {
            return;
        }
        this.pos = InstantMine.breakPos;
        final IBlockState getBlockState = AutoDupe.mc.world.getBlockState(this.pos);
        this.box = this.getItemShulkerBox();
        if (getBlockState.getBlock() == Blocks.AIR && this.box != -1) {
            AutoDupe.mc.player.inventory.currentItem = this.box;
            BlockUtil.placeBlock(this.pos, EnumHand.MAIN_HAND, false, false, false);
            this.timer.passedDms(this.delay.getValue());
        }
    }
}

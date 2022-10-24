//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.abHack.util.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import java.util.*;

public class AntiShulkerBox extends Module
{
    private final /* synthetic */ Setting<Integer> saferange;
    private static /* synthetic */ AntiShulkerBox INSTANCE;
    private final /* synthetic */ Setting<Integer> range;
    
    public static AntiShulkerBox getInstance() {
        if (AntiShulkerBox.INSTANCE == null) {
            AntiShulkerBox.INSTANCE = new AntiShulkerBox();
        }
        return AntiShulkerBox.INSTANCE;
    }
    
    private NonNullList<BlockPos> breakPos(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)BlockUtil.getSphere(new BlockPos(Math.floor(AntiShulkerBox.mc.player.posX), Math.floor(AntiShulkerBox.mc.player.posY), Math.floor(AntiShulkerBox.mc.player.posZ)), n, 0, false, true, 0));
        return (NonNullList<BlockPos>)create;
    }
    
    private void setInstance() {
        AntiShulkerBox.INSTANCE = this;
    }
    
    public AntiShulkerBox() {
        super("AntiShulkerBox", "Automatically dig someone else's box", Module.Category.PLAYER, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)5, (T)1, (T)6));
        this.saferange = (Setting<Integer>)this.register(new Setting("SafeRange", (T)2, (T)0, (T)6));
        this.setInstance();
    }
    
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        final int currentItem = AntiShulkerBox.mc.player.inventory.currentItem;
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
        }
        for (final BlockPos blockPos : this.breakPos(this.range.getValue())) {
            final int itemHotbar = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
            if (itemHotbar == -1) {
                return;
            }
            if (AntiShulkerBox.mc.player.getDistanceSq(blockPos) < MathUtil.square(this.saferange.getValue())) {
                continue;
            }
            if (blockPos == null) {
                continue;
            }
            if (AntiShulkerBox.mc.world.getBlockState(blockPos).getBlock() instanceof BlockShulkerBox) {
                AntiShulkerBox.mc.player.inventory.currentItem = itemHotbar;
                AntiShulkerBox.mc.player.swingArm(EnumHand.MAIN_HAND);
                AntiShulkerBox.mc.playerController.onPlayerDamageBlock(blockPos, BlockUtil.getRayTraceFacing(blockPos));
            }
            else {
                AntiShulkerBox.mc.player.inventory.currentItem = currentItem;
            }
        }
    }
    
    static {
        AntiShulkerBox.INSTANCE = new AntiShulkerBox();
    }
}

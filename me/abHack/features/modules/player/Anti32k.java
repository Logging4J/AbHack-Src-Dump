//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.block.*;
import me.abHack.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import java.util.*;

public class Anti32k extends Module
{
    private static /* synthetic */ Anti32k INSTANCE;
    private final /* synthetic */ Setting<Integer> range;
    
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        final int currentItem = Anti32k.mc.player.inventory.currentItem;
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
        }
        for (final BlockPos blockPos : this.breakPos(this.range.getValue())) {
            final int itemHotbar = InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE);
            if (itemHotbar == -1) {
                return;
            }
            if (blockPos == null) {
                continue;
            }
            if (Anti32k.mc.world.getBlockState(blockPos).getBlock() instanceof BlockHopper && Anti32k.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock() instanceof BlockShulkerBox) {
                Anti32k.mc.player.inventory.currentItem = itemHotbar;
                Anti32k.mc.player.swingArm(EnumHand.MAIN_HAND);
                Anti32k.mc.playerController.processRightClickBlock(Anti32k.mc.player, Anti32k.mc.world, blockPos.add(0, 1, 0), BlockUtil.getRayTraceFacing(blockPos.add(0, 1, 0)), new Vec3d((Vec3i)blockPos.add(0, 1, 0)), EnumHand.MAIN_HAND);
                Anti32k.mc.playerController.onPlayerDamageBlock(blockPos, BlockUtil.getRayTraceFacing(blockPos));
            }
            else {
                Anti32k.mc.player.inventory.currentItem = currentItem;
            }
        }
    }
    
    public static Anti32k getInstance() {
        if (Anti32k.INSTANCE == null) {
            Anti32k.INSTANCE = new Anti32k();
        }
        return Anti32k.INSTANCE;
    }
    
    public Anti32k() {
        super("Anti32k", "Anti32k", Module.Category.PLAYER, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)5, (T)3, (T)5));
        this.setInstance();
    }
    
    private NonNullList<BlockPos> breakPos(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)BlockUtil.getSphere(new BlockPos(Math.floor(Anti32k.mc.player.posX), Math.floor(Anti32k.mc.player.posY), Math.floor(Anti32k.mc.player.posZ)), n, 0, false, true, 0));
        return (NonNullList<BlockPos>)create;
    }
    
    private void setInstance() {
        Anti32k.INSTANCE = this;
    }
    
    static {
        Anti32k.INSTANCE = new Anti32k();
    }
}

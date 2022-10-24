//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import net.minecraft.entity.player.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.block.state.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import me.abHack.*;
import java.util.*;

public class TrapHead extends Module
{
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Float> range;
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        this.target = this.getTarget(this.range.getValue());
        if (this.target == null) {
            return;
        }
        final BlockPos blockPos = new BlockPos(this.target.posX, this.target.posY, this.target.posZ);
        final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        if (hotbarBlock == -1) {
            return;
        }
        final int currentItem = TrapHead.mc.player.inventory.currentItem;
        if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() != Blocks.AIR || this.getBlock(blockPos.add(0, 2, 1)).getBlock() != Blocks.AIR || this.getBlock(blockPos.add(-1, 2, 0)).getBlock() != Blocks.AIR || this.getBlock(blockPos.add(0, 2, -1)).getBlock() != Blocks.AIR) {
                this.switchToSlot(hotbarBlock);
                BlockUtil.placeBlock(blockPos.add(0, 2, 0), EnumHand.MAIN_HAND, false, true, false);
                this.switchToSlot(currentItem);
            }
            else if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR) {
                this.switchToSlot(hotbarBlock);
                BlockUtil.placeBlock(blockPos.add(1, 2, 0), EnumHand.MAIN_HAND, false, true, false);
                this.switchToSlot(currentItem);
            }
            else if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR) {
                this.switchToSlot(hotbarBlock);
                BlockUtil.placeBlock(blockPos.add(-1, 2, 0), EnumHand.MAIN_HAND, false, true, false);
                this.switchToSlot(currentItem);
            }
            else if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR) {
                this.switchToSlot(hotbarBlock);
                BlockUtil.placeBlock(blockPos.add(0, 2, 1), EnumHand.MAIN_HAND, false, true, false);
                this.switchToSlot(currentItem);
            }
            else if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR) {
                this.switchToSlot(hotbarBlock);
                BlockUtil.placeBlock(blockPos.add(0, 2, -1), EnumHand.MAIN_HAND, false, true, false);
                this.switchToSlot(currentItem);
            }
        }
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return TrapHead.mc.world.getBlockState(blockPos);
    }
    
    private void switchToSlot(final int currentItem) {
        TrapHead.mc.player.inventory.currentItem = currentItem;
        TrapHead.mc.playerController.updateController();
    }
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : TrapHead.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                continue;
            }
            if (OyVey.friendManager.isFriend(entityPlayer2.getName())) {
                continue;
            }
            if (TrapHead.mc.player.posY - entityPlayer2.posY >= 5.0) {
                continue;
            }
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                n2 = EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
            }
            else {
                if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2) >= n2) {
                    continue;
                }
                entityPlayer = entityPlayer2;
                n2 = EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
            }
        }
        return entityPlayer;
    }
    
    public TrapHead() {
        super("TrapHead", "Trap Head", Category.COMBAT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)1.0f, (T)6.0f));
    }
    
    @Override
    public void onEnable() {
        Surround.breakcrystal();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import net.minecraft.entity.*;
import me.abHack.*;
import net.minecraft.util.*;
import me.abHack.util.*;

public class TrapSelf extends Module
{
    private final /* synthetic */ Setting<Boolean> toggle;
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int obsidian;
    private final /* synthetic */ Setting<Boolean> hole;
    private final /* synthetic */ Setting<Boolean> center;
    
    private void switchToSlot(final int currentItem) {
        TrapSelf.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        TrapSelf.mc.player.inventory.currentItem = currentItem;
        TrapSelf.mc.playerController.updateController();
    }
    
    public TrapSelf() {
        super("TrapSelf", "One Self Trap", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.hole = (Setting<Boolean>)this.register(new Setting("Hole or Burrrow", (T)true));
        this.center = (Setting<Boolean>)this.register(new Setting("TPCenter", (T)true));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
        this.obsidian = -1;
    }
    
    private boolean isBurrowed(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return TrapSelf.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || TrapSelf.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || TrapSelf.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST;
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return TrapSelf.mc.world.getBlockState(blockPos);
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        this.obsidian = InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN);
        if (this.obsidian == -1) {
            return;
        }
        final BlockPos blockPos = new BlockPos(TrapSelf.mc.player.posX, TrapSelf.mc.player.posY, TrapSelf.mc.player.posZ);
        if ((!EntityUtil.isInHole((Entity)TrapSelf.mc.player) || !this.isBurrowed((EntityPlayer)TrapSelf.mc.player)) && this.hole.getValue()) {
            return;
        }
        if (this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.AIR) {
            this.place(blockPos.add(1, 0, 0));
        }
        if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(1, 1, 0));
        }
        if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 1, 0)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(1, 2, 0));
        }
        if (this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.AIR) {
            this.place(blockPos.add(-1, 0, 0));
        }
        if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(-1, 1, 0));
        }
        if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 1, 0)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(-1, 2, 0));
        }
        if (this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.AIR) {
            this.place(blockPos.add(0, 0, 1));
        }
        if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(0, 1, 1));
        }
        if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, 1)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(0, 2, 1));
        }
        if (this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.AIR) {
            this.place(blockPos.add(0, 0, -1));
        }
        if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(0, 1, -1));
        }
        if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 1, -1)).getBlock() != Blocks.AIR) {
            this.place(blockPos.add(0, 2, -1));
        }
        if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR) {
            this.place(blockPos.add(0, 2, 0));
        }
        if (this.toggle.getValue() || OyVey.speedManager.getPlayerSpeed((EntityPlayer)TrapSelf.mc.player) > 10.0) {
            this.toggle();
        }
    }
    
    @Override
    public void onEnable() {
        Surround.breakcrystal();
        final BlockPos roundedBlockPos = EntityUtil.getRoundedBlockPos((Entity)Surround.mc.player);
        if (this.center.getValue()) {
            OyVey.positionManager.setPositionPacket(roundedBlockPos.getX() + 0.5, roundedBlockPos.getY(), roundedBlockPos.getZ() + 0.5, true, true, true);
        }
    }
    
    private void place(final BlockPos blockPos) {
        final int currentItem = TrapSelf.mc.player.inventory.currentItem;
        this.switchToSlot(this.obsidian);
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        this.switchToSlot(currentItem);
    }
}

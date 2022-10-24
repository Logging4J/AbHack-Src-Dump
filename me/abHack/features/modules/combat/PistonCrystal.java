//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import me.abHack.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import me.abHack.util.*;
import net.minecraft.util.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.block.state.*;

public class PistonCrystal extends Module
{
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ List<Block> godBlocks;
    private final /* synthetic */ Setting<Float> range;
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : PistonCrystal.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                continue;
            }
            if (OyVey.friendManager.isFriend(entityPlayer2.getName())) {
                continue;
            }
            if (PistonCrystal.mc.player.posY - entityPlayer2.posY >= 5.0) {
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
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        this.target = this.getTarget(this.range.getValue());
        if (this.target == null) {
            return;
        }
        final int materials = this.findMaterials(Blocks.REDSTONE_BLOCK);
        final int materials2 = this.findMaterials((Block)Blocks.PISTON);
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (materials == -1 || materials2 == -1 || itemHotbar == -1) {
            return;
        }
        final BlockPos blockPos = new BlockPos(this.target.posX, this.target.posY, this.target.posZ);
        final int currentItem = PistonCrystal.mc.player.inventory.currentItem;
        if (!this.godBlocks.contains(this.getBlock(blockPos.add(2, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(2, 0, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(1, 1, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(1, 0, 0)).getBlock())) {
            if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() != Blocks.AIR) {
                this.switchToSlot(materials);
                BlockUtil.placeBlock2(blockPos.add(2, 0, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.AIR && this.godBlocks.contains(this.getBlock(blockPos.add(1, 0, 0)).getBlock()) && this.getBlock(blockPos.add(2, 0, 0)).getBlock() == Blocks.REDSTONE_BLOCK) {
                this.switchToSlot(materials2);
                BlockUtil.placeBlock2(blockPos.add(2, 1, 0), EnumHand.MAIN_HAND, true, true, false);
                this.switchToSlot(itemHotbar);
                PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(1, 0, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(1, 1, 0), BlockUtil.getRayTraceFacing(blockPos.add(1, 1, 0)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(-2, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-2, 0, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-1, 1, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-1, 0, 0)).getBlock())) {
            if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() != Blocks.AIR) {
                this.switchToSlot(materials);
                BlockUtil.placeBlock2(blockPos.add(-2, 0, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.AIR && this.godBlocks.contains(this.getBlock(blockPos.add(-1, 0, 0)).getBlock()) && this.getBlock(blockPos.add(-2, 0, 0)).getBlock() == Blocks.REDSTONE_BLOCK) {
                this.switchToSlot(materials2);
                BlockUtil.placeBlock2(blockPos.add(-2, 1, 0), EnumHand.MAIN_HAND, true, true, false);
                this.switchToSlot(itemHotbar);
                PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(-1, 0, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(-1, 1, 0), BlockUtil.getRayTraceFacing(blockPos.add(-1, 1, 0)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 1)).getBlock())) {
            if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() != Blocks.AIR) {
                this.switchToSlot(materials);
                BlockUtil.placeBlock2(blockPos.add(0, 0, 2), EnumHand.MAIN_HAND, true, true, false);
            }
            if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.AIR && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 1)).getBlock()) && this.getBlock(blockPos.add(0, 0, 2)).getBlock() == Blocks.REDSTONE_BLOCK) {
                this.switchToSlot(materials2);
                BlockUtil.placeBlock2(blockPos.add(0, 1, 2), EnumHand.MAIN_HAND, true, true, false);
                this.switchToSlot(itemHotbar);
                PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 0, 1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 1, 1), BlockUtil.getRayTraceFacing(blockPos.add(0, 1, 1)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -1)).getBlock())) {
            if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() != Blocks.AIR) {
                this.switchToSlot(materials);
                BlockUtil.placeBlock2(blockPos.add(0, 0, -2), EnumHand.MAIN_HAND, true, true, false);
            }
            if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.AIR && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -1)).getBlock()) && this.getBlock(blockPos.add(0, 0, -2)).getBlock() == Blocks.REDSTONE_BLOCK) {
                this.switchToSlot(materials2);
                BlockUtil.placeBlock2(blockPos.add(0, 1, -2), EnumHand.MAIN_HAND, true, true, false);
                this.switchToSlot(itemHotbar);
                PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 0, -1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            }
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 1, -1), BlockUtil.getRayTraceFacing(blockPos.add(0, 1, -1)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(1, 2, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(3, 2, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(2, 2, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(1, 1, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(2, 1, 0)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(3, 1, 0)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(3, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(2, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(1, 1, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(3, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(1, 2, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(1, 2, 0), BlockUtil.getRayTraceFacing(blockPos.add(1, 2, 0)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(-1, 2, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-3, 2, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-2, 2, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-1, 1, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-2, 1, 0)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(-3, 1, 0)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(-3, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(-2, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(-1, 1, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(-3, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(-1, 2, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(-1, 2, 0), BlockUtil.getRayTraceFacing(blockPos.add(-1, 2, 0)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, 1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, 3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, 2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 2)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(0, 1, 3)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(0, 1, 3), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(0, 2, 2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 1, 1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 2, 3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 2, 1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 2, 1), BlockUtil.getRayTraceFacing(blockPos.add(0, 2, 1)));
            }
        }
        else if (!this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, -1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, -3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, -2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -2)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(0, 1, -3)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(0, 1, -3), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(0, 2, -2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 1, -1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 2, -3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 2, -1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 2, -1), BlockUtil.getRayTraceFacing(blockPos.add(0, 2, -1)));
            }
        }
        else if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR && !this.godBlocks.contains(this.getBlock(blockPos.add(1, 3, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(3, 3, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(2, 3, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(1, 2, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(2, 2, 0)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(3, 2, 0)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(3, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(2, 3, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(1, 2, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(3, 3, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(1, 3, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(1, 3, 0), BlockUtil.getRayTraceFacing(blockPos.add(1, 3, 0)));
            }
        }
        else if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR && !this.godBlocks.contains(this.getBlock(blockPos.add(-1, 3, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-3, 3, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-2, 3, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-1, 2, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-2, 2, 0)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(-3, 2, 0)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(-3, 2, 0), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(-2, 3, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(-1, 2, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(-3, 3, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(-1, 3, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(-1, 3, 0), BlockUtil.getRayTraceFacing(blockPos.add(-1, 3, 0)));
            }
        }
        else if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, 1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, 3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, 2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, 1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, 2)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(0, 2, 3)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(0, 2, 3), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(0, 3, 2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 2, 1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 3, 3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 3, 1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 3, 1), BlockUtil.getRayTraceFacing(blockPos.add(0, 3, 1)));
            }
        }
        else if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.AIR && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, -1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, -3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 3, -2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, -1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 2, -2)).getBlock())) {
            this.switchToSlot(materials2);
            if (this.getBlock(blockPos.add(0, 2, -3)).getBlock() == Blocks.AIR) {
                BlockUtil.placeBlock2(blockPos.add(0, 2, -3), EnumHand.MAIN_HAND, true, true, false);
            }
            BlockUtil.placeBlock2(blockPos.add(0, 3, -2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 2, -1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 3, -3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 3, -1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 3, -1), BlockUtil.getRayTraceFacing(blockPos.add(0, 3, -1)));
            }
        }
        else if (this.godBlocks.contains(this.getBlock(blockPos.add(1, 0, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(2, 0, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(3, 0, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(1, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(2, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(3, 1, 0)).getBlock())) {
            this.switchToSlot(materials2);
            BlockUtil.placeBlock2(blockPos.add(2, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(1, 0, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(3, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(1, 1, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(1, 1, 0), BlockUtil.getRayTraceFacing(blockPos.add(1, 1, 0)));
            }
        }
        else if (this.godBlocks.contains(this.getBlock(blockPos.add(-1, 0, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-2, 0, 0)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(-3, 0, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-1, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-2, 1, 0)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(-3, 1, 0)).getBlock())) {
            this.switchToSlot(materials2);
            BlockUtil.placeBlock2(blockPos.add(-2, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(-1, 0, 0), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(-3, 1, 0), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(-1, 1, 0)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(-1, 1, 0), BlockUtil.getRayTraceFacing(blockPos.add(-1, 1, 0)));
            }
        }
        else if (this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, 3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, 3)).getBlock())) {
            this.switchToSlot(materials2);
            BlockUtil.placeBlock2(blockPos.add(0, 1, 2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 0, 1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 1, 3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 1, 1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 1, 1), BlockUtil.getRayTraceFacing(blockPos.add(0, 1, 1)));
            }
        }
        else if (this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -1)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -2)).getBlock()) && this.godBlocks.contains(this.getBlock(blockPos.add(0, 0, -3)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -1)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -2)).getBlock()) && !this.godBlocks.contains(this.getBlock(blockPos.add(0, 1, -3)).getBlock())) {
            this.switchToSlot(materials2);
            BlockUtil.placeBlock2(blockPos.add(0, 1, -2), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(itemHotbar);
            PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos.add(0, 0, -1), EnumFacing.UP, (PistonCrystal.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, 0.0f, 0.0f, 0.0f));
            this.switchToSlot(materials);
            BlockUtil.placeBlock2(blockPos.add(0, 1, -3), EnumHand.MAIN_HAND, true, true, false);
            this.switchToSlot(currentItem);
            if (this.getBlock(blockPos.add(0, 1, -1)).getBlock() == Blocks.PISTON_HEAD) {
                PistonCrystal.mc.playerController.onPlayerDamageBlock(blockPos.add(0, 1, -1), BlockUtil.getRayTraceFacing(blockPos.add(0, 1, -1)));
            }
        }
        final Entity entity3 = (Entity)PistonCrystal.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal).min(Comparator.comparing(entity2 -> this.target.getDistance(entity2))).orElse(null);
        if (entity3 == null) {
            return;
        }
        PistonCrystal.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity3));
    }
    
    private int findMaterials(final Block block) {
        for (int i = 0; i < 9; ++i) {
            if (PistonCrystal.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)PistonCrystal.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    public PistonCrystal() {
        super("PistonCrystal", "Trap Head", Category.COMBAT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)1.0f, (T)6.0f));
        this.godBlocks = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK);
    }
    
    private void switchToSlot(final int currentItem) {
        PistonCrystal.mc.player.inventory.currentItem = currentItem;
        PistonCrystal.mc.playerController.updateController();
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return PistonCrystal.mc.world.getBlockState(blockPos);
    }
}

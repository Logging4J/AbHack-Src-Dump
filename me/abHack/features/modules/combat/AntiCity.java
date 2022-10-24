//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.block.state.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class AntiCity extends Module
{
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> toggle;
    private /* synthetic */ int obsidian;
    private final /* synthetic */ Setting<Double> range;
    
    @Override
    public void onUpdate() {
        if (AntiCity.mc.player == null || AntiCity.mc.world == null) {
            return;
        }
        if (!OyVey.moduleManager.isModuleEnabled("Surround")) {
            return;
        }
        this.obsidian = InventoryUtil.findHotbarBlock(Blocks.OBSIDIAN);
        if (this.obsidian == -1) {
            return;
        }
        final BlockPos blockPos = new BlockPos(AntiCity.mc.player.posX, AntiCity.mc.player.posY, AntiCity.mc.player.posZ);
        if (blockPos == null) {
            return;
        }
        if (this.getTarget(this.range.getValue()) == null) {
            return;
        }
        if (!isHard(this.getBlock(blockPos.add(1, 0, 0)).getBlock())) {
            if (this.getBlock(blockPos.add(2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(2, 0, 0));
            }
            if (this.getBlock(blockPos.add(1, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(1, 0, 1));
            }
            if (this.getBlock(blockPos.add(1, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(1, 0, -1));
            }
            if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.OBSIDIAN && this.getBlock(blockPos.add(2, 1, 0)).getBlock() == Blocks.AIR) {
                this.perform(blockPos.add(2, 1, 0));
            }
        }
        if (!isHard(this.getBlock(blockPos.add(-1, 0, 0)).getBlock())) {
            if (this.getBlock(blockPos.add(-2, 0, 0)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(-2, 0, 0));
            }
            if (this.getBlock(blockPos.add(-1, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(-1, 0, 1));
            }
            if (this.getBlock(blockPos.add(-1, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(-1, 0, 0)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(-1, 0, -1));
            }
            if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.OBSIDIAN && this.getBlock(blockPos.add(-2, 1, 0)).getBlock() == Blocks.AIR) {
                this.perform(blockPos.add(-2, 1, 0));
            }
        }
        if (!isHard(this.getBlock(blockPos.add(0, 0, 1)).getBlock())) {
            if (this.getBlock(blockPos.add(0, 0, 2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(0, 0, 2));
            }
            if (this.getBlock(blockPos.add(1, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(1, 0, 1));
            }
            if (this.getBlock(blockPos.add(-1, 0, 1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, 1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(-1, 0, 1));
            }
            if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.OBSIDIAN && this.getBlock(blockPos.add(0, 1, 2)).getBlock() == Blocks.AIR) {
                this.perform(blockPos.add(0, 1, 2));
            }
        }
        if (!isHard(this.getBlock(blockPos.add(0, 0, -1)).getBlock())) {
            if (this.getBlock(blockPos.add(0, 0, -2)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(0, 0, -2));
            }
            if (this.getBlock(blockPos.add(1, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(1, 0, -1));
            }
            if (this.getBlock(blockPos.add(-1, 0, -1)).getBlock() == Blocks.AIR && this.getBlock(blockPos.add(0, 0, -1)).getBlock() == Blocks.OBSIDIAN) {
                this.perform(blockPos.add(-1, 0, -1));
            }
            if (this.getBlock(blockPos.add(0, 2, 0)).getBlock() == Blocks.OBSIDIAN && this.getBlock(blockPos.add(0, 1, -2)).getBlock() == Blocks.AIR) {
                this.perform(blockPos.add(0, 1, -2));
            }
        }
        if (this.toggle.getValue()) {
            this.toggle();
        }
    }
    
    public static boolean isHard(final Block block) {
        return block == Blocks.BEDROCK;
    }
    
    private void perform(final BlockPos blockPos) {
        final int currentItem = AntiCity.mc.player.inventory.currentItem;
        this.switchToSlot(this.obsidian);
        BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, false);
        this.switchToSlot(currentItem);
    }
    
    public AntiCity() {
        super("AntiCity", "AntiCity", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
        this.range = (Setting<Double>)this.register(new Setting("Range", (T)5.0, (T)1.0, (T)10.0));
        this.obsidian = -1;
    }
    
    private EntityPlayer getTarget(final double a) {
        EntityPlayer entityPlayer = null;
        double n = Math.pow(a, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AutoTrap.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, a)) {
                if (OyVey.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private IBlockState getBlock(final BlockPos blockPos) {
        return AntiCity.mc.world.getBlockState(blockPos);
    }
    
    private void switchToSlot(final int currentItem) {
        AntiCity.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        AntiCity.mc.player.inventory.currentItem = currentItem;
        AntiCity.mc.playerController.updateController();
    }
    
    @Override
    public void onEnable() {
        Surround.breakcrystal();
    }
}

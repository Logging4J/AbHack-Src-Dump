//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.init.*;
import net.minecraft.block.state.*;
import me.abHack.*;
import me.abHack.util.*;
import java.util.*;

public class HoleTP extends Module
{
    private final /* synthetic */ Setting<Boolean> setY;
    private final /* synthetic */ Setting<Float> zOffset;
    private final /* synthetic */ double[] oneblockPositions;
    private final /* synthetic */ Setting<Float> xOffset;
    private static /* synthetic */ HoleTP INSTANCE;
    private final /* synthetic */ Setting<Float> yOffset;
    private final /* synthetic */ Setting<Float> range;
    
    private double getNearestBlockBelow() {
        for (double posY = HoleTP.mc.player.posY; posY > 0.0; posY -= 0.001) {
            if (!(HoleTP.mc.world.getBlockState(new BlockPos(HoleTP.mc.player.posX, posY, HoleTP.mc.player.posZ)).getBlock() instanceof BlockSlab) && HoleTP.mc.world.getBlockState(new BlockPos(HoleTP.mc.player.posX, posY, HoleTP.mc.player.posZ)).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)HoleTP.mc.world, new BlockPos(0, 0, 0)) != null) {
                return posY;
            }
        }
        return -1.0;
    }
    
    public HoleTP() {
        super("HoleTP", "Teleports you in a hole.", Module.Category.MOVEMENT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)0.0f, (T)5.0f));
        this.setY = (Setting<Boolean>)this.register(new Setting("SetY", (T)false));
        this.xOffset = (Setting<Float>)this.register(new Setting("XOffset", (T)0.5f, (T)0.0f, (T)1.0f));
        this.yOffset = (Setting<Float>)this.register(new Setting("YOffset", (T)0.0f, (T)0.0f, (T)1.0f));
        this.zOffset = (Setting<Float>)this.register(new Setting("ZOffset", (T)0.5f, (T)0.0f, (T)1.0f));
        this.oneblockPositions = new double[] { 0.42, 0.75 };
        this.setInstance();
    }
    
    private boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = HoleTP.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static HoleTP getInstance() {
        if (HoleTP.INSTANCE == null) {
            HoleTP.INSTANCE = new HoleTP();
        }
        return HoleTP.INSTANCE;
    }
    
    private boolean isBlockValid(final IBlockState blockState, final BlockPos blockPos) {
        return blockState.getBlock() == Blocks.AIR && HoleTP.mc.player.getDistanceSq(blockPos) >= 1.0 && HoleTP.mc.world.getBlockState(blockPos.up()).getBlock() == Blocks.AIR && HoleTP.mc.world.getBlockState(blockPos.up(2)).getBlock() == Blocks.AIR && (this.isBedrockHole(blockPos) || this.isObbyHole(blockPos) || this.isBothHole(blockPos) || this.isElseHole(blockPos));
    }
    
    private boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = HoleTP.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isElseHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = HoleTP.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || !getBlockState.isFullBlock()) {
                return false;
            }
        }
        return true;
    }
    
    public void onEnable() {
        OyVey.holeManager.update();
        final List<BlockPos> sortedHoles = OyVey.holeManager.getSortedHoles();
        if (!sortedHoles.isEmpty()) {
            final BlockPos blockPos = sortedHoles.get(0);
            if (HoleTP.mc.player.getDistanceSq(blockPos) <= MathUtil.square(this.range.getValue())) {
                OyVey.positionManager.setPositionPacket(blockPos.getX() + this.xOffset.getValue(), (this.setY.getValue() ? blockPos.getY() : HoleTP.mc.player.posY) + this.yOffset.getValue(), blockPos.getZ() + this.zOffset.getValue(), this.setY.getValue() && this.yOffset.getValue() == 0.0f, true, true);
            }
        }
        this.disable();
    }
    
    private boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = HoleTP.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    private boolean isInHole() {
        final BlockPos blockPos = new BlockPos(HoleTP.mc.player.posX, HoleTP.mc.player.posY, HoleTP.mc.player.posZ);
        return this.isBlockValid(HoleTP.mc.world.getBlockState(blockPos), blockPos);
    }
    
    private void setInstance() {
        HoleTP.INSTANCE = this;
    }
    
    static {
        HoleTP.INSTANCE = new HoleTP();
    }
}

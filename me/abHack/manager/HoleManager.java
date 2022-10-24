//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.abHack.util.*;
import net.minecraft.entity.player.*;
import java.util.*;
import net.minecraft.block.*;

public class HoleManager extends Feature
{
    private /* synthetic */ List<BlockPos> holes;
    private static final /* synthetic */ BlockPos[] surroundOffset;
    private final /* synthetic */ List<BlockPos> midSafety;
    
    public HoleManager() {
        this.midSafety = new ArrayList<BlockPos>();
        this.holes = new ArrayList<BlockPos>();
    }
    
    public List<BlockPos> getSortedHoles() {
        this.holes.sort(Comparator.comparingDouble(blockPos -> HoleManager.mc.player.getDistanceSq(blockPos)));
        return this.getHoles();
    }
    
    public void update() {
        if (!fullNullCheck()) {
            this.holes = this.calcHoles();
        }
    }
    
    public List<BlockPos> getMidSafety() {
        return this.midSafety;
    }
    
    public boolean isSafe(final BlockPos blockPos) {
        boolean b = true;
        final BlockPos[] surroundOffset = HoleManager.surroundOffset;
        for (int length = surroundOffset.length, i = 0; i < length; ++i) {
            if (HoleManager.mc.world.getBlockState(blockPos.add((Vec3i)surroundOffset[i])).getBlock() != Blocks.BEDROCK) {
                b = false;
                break;
            }
        }
        return b;
    }
    
    public List<BlockPos> getHoles() {
        return this.holes;
    }
    
    static {
        surroundOffset = BlockUtil.toBlockPos(EntityUtil.getOffsets(0, true));
    }
    
    public List<BlockPos> calcHoles() {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        this.midSafety.clear();
        for (final BlockPos e : BlockUtil.getSphere(EntityUtil.getPlayerPos((EntityPlayer)HoleManager.mc.player), 6.0f, 6, false, true, 0)) {
            if (HoleManager.mc.world.getBlockState(e).getBlock().equals(Blocks.AIR) && HoleManager.mc.world.getBlockState(e.add(0, 1, 0)).getBlock().equals(Blocks.AIR)) {
                if (!HoleManager.mc.world.getBlockState(e.add(0, 2, 0)).getBlock().equals(Blocks.AIR)) {
                    continue;
                }
                boolean b = true;
                boolean b2 = true;
                final BlockPos[] surroundOffset = HoleManager.surroundOffset;
                for (int length = surroundOffset.length, i = 0; i < length; ++i) {
                    final Block getBlock = HoleManager.mc.world.getBlockState(e.add((Vec3i)surroundOffset[i])).getBlock();
                    if (BlockUtil.isBlockUnSolid(getBlock)) {
                        b2 = false;
                    }
                    if (getBlock != Blocks.BEDROCK && getBlock != Blocks.OBSIDIAN && getBlock != Blocks.ENDER_CHEST) {
                        if (getBlock != Blocks.ANVIL) {
                            b = false;
                        }
                    }
                }
                if (b) {
                    list.add(e);
                }
                if (!b2) {
                    continue;
                }
                this.midSafety.add(e);
            }
        }
        return list;
    }
}

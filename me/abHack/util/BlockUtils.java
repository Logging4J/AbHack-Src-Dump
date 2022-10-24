//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.client.*;
import net.minecraft.item.*;
import net.minecraft.world.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;

public class BlockUtils implements Util
{
    public /* synthetic */ double roty;
    public /* synthetic */ BlockPos pos;
    public /* synthetic */ double dist;
    protected static /* synthetic */ Minecraft mc;
    public /* synthetic */ EnumFacing f;
    public /* synthetic */ double rotx;
    public /* synthetic */ int a;
    
    public BlockUtils(final BlockPos pos, final int a, final EnumFacing f, final double dist) {
        this.pos = pos;
        this.a = a;
        this.f = f;
        this.dist = dist;
    }
    
    public static boolean isAir(final BlockPos blockPos) {
        return BlockUtils.mc.world.getBlockState(blockPos).getBlock() instanceof BlockAir;
    }
    
    protected final Vec3d getVectorForRotation(final float n, final float n2) {
        final float cos = MathHelper.cos(-n2 * 0.017453292f - 3.1415927f);
        final float sin = MathHelper.sin(-n2 * 0.017453292f - 3.1415927f);
        final float n3 = -MathHelper.cos(-n * 0.017453292f);
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin(-n * 0.017453292f), (double)(cos * n3));
    }
    
    public static BlockUtils isPlaceable(final BlockPos blockPos, final double n, final boolean b) {
        final BlockUtils blockUtils = new BlockUtils(blockPos, 0, null, n);
        if (!isAir(blockPos)) {
            return null;
        }
        if (!(BlockUtils.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBlock)) {
            return null;
        }
        final AxisAlignedBB getCollisionBoundingBox = ((ItemBlock)BlockUtils.mc.player.inventory.getCurrentItem().getItem()).getBlock().getDefaultState().getCollisionBoundingBox((IBlockAccess)BlockUtils.mc.world, blockPos);
        if (!isAir(blockPos) && BlockUtils.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid) {
            if (BlockUtils.mc.world.getBlockState(blockPos.offset(EnumFacing.UP)).getBlock() instanceof BlockLiquid) {
                blockUtils.f = EnumFacing.DOWN;
                blockUtils.pos.offset(EnumFacing.UP);
                return blockUtils;
            }
            blockUtils.f = EnumFacing.UP;
            blockUtils.pos.offset(EnumFacing.DOWN);
            return blockUtils;
        }
        else {
            final EnumFacing[] values = EnumFacing.values();
            final int length = values.length;
            int i = 0;
            while (i < length) {
                final EnumFacing f = values[i];
                if (!isAir(new BlockPos(blockPos.getX() - f.getDirectionVec().getX(), blockPos.getY() - f.getDirectionVec().getY(), blockPos.getZ() - f.getDirectionVec().getZ()))) {
                    blockUtils.f = f;
                    if (b && getCollisionBoundingBox != Block.NULL_AABB && !BlockUtils.mc.world.checkNoEntityCollision(getCollisionBoundingBox.offset(blockPos), (Entity)null)) {
                        return null;
                    }
                    return blockUtils;
                }
                else {
                    ++i;
                }
            }
            if (!isRePlaceable(blockPos)) {
                return null;
            }
            blockUtils.f = EnumFacing.UP;
            blockUtils.pos.offset(EnumFacing.UP);
            blockPos.offset(EnumFacing.DOWN);
            if (b && getCollisionBoundingBox != Block.NULL_AABB && !BlockUtils.mc.world.checkNoEntityCollision(getCollisionBoundingBox.offset(blockPos), (Entity)null)) {
                return null;
            }
            return blockUtils;
        }
    }
    
    public boolean doPlace(final boolean b) {
        final double n = this.pos.getX() + 0.5 - BlockUtils.mc.player.posX - this.f.getDirectionVec().getX() / 2.0;
        final double n2 = this.pos.getY() + 0.5 - BlockUtils.mc.player.posY - this.f.getDirectionVec().getY() / 2.0 - BlockUtils.mc.player.getEyeHeight();
        final double n3 = this.pos.getZ() + 0.5 - BlockUtils.mc.player.posZ - this.f.getDirectionVec().getZ() / 2.0;
        final double direction2D = getDirection2D(n3, n);
        final double direction2D2 = getDirection2D(n2, Math.sqrt(n * n + n3 * n3));
        final Vec3d vectorForRotation = this.getVectorForRotation(-direction2D2, direction2D - 90.0);
        this.roty = -direction2D2;
        this.rotx = direction2D - 90.0;
        if (BlockUtils.mc.playerController.processRightClickBlock(BlockUtils.mc.player, BlockUtils.mc.world, new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f, vectorForRotation, EnumHand.MAIN_HAND) == EnumActionResult.SUCCESS) {
            if (b) {
                BlockUtils.mc.player.swingArm(EnumHand.MAIN_HAND);
            }
            return true;
        }
        return false;
    }
    
    public static double getDirection2D(final double n, final double n2) {
        double n3;
        if (n2 == 0.0) {
            n3 = ((n > 0.0) ? 90.0 : -90.0);
        }
        else {
            n3 = Math.atan(n / n2) * 57.2957796;
            if (n2 < 0.0) {
                double n4 = 0.0;
                if (n > 0.0) {
                    n4 = n3 + 180.0;
                }
                else if (n < 0.0) {}
                n3 = n4;
            }
        }
        return n3;
    }
    
    public static boolean doBreak(final BlockPos blockPos, final EnumFacing enumFacing) {
        return BlockUtils.mc.playerController.clickBlock(blockPos, enumFacing);
    }
    
    static {
        BlockUtils.mc = Minecraft.getMinecraft();
    }
    
    public static boolean isRePlaceable(final BlockPos blockPos) {
        final Block getBlock = BlockUtils.mc.world.getBlockState(blockPos).getBlock();
        return getBlock.isReplaceable((IBlockAccess)BlockUtils.mc.world, blockPos) && !(getBlock instanceof BlockAir);
    }
    
    public static boolean doPlace(final BlockUtils blockUtils, final boolean b) {
        return blockUtils != null && blockUtils.doPlace(b);
    }
    
    public void doBreak() {
        BlockUtils.mc.playerController.onPlayerDamageBlock(new BlockPos(this.pos.getX() - this.f.getDirectionVec().getX(), this.pos.getY() - this.f.getDirectionVec().getY(), this.pos.getZ() - this.f.getDirectionVec().getZ()), this.f);
    }
    
    public static Vec3d floorVec3(final Vec3d vec3d) {
        return new Vec3d(Math.floor(vec3d.x), Math.floor(vec3d.y), Math.floor(vec3d.z));
    }
    
    protected final Vec3d getVectorForRotation(final double n, final double n2) {
        final float cos = MathHelper.cos((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float sin = MathHelper.sin((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float n3 = -MathHelper.cos((float)(-n * 0.01745329238474369));
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin((float)(-n * 0.01745329238474369)), (double)(cos * n3));
    }
}

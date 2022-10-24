//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.block.*;
import net.minecraft.client.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.network.play.client.*;

public class TestUtil
{
    public static /* synthetic */ List<Block> emptyBlocks;
    public static /* synthetic */ List<Block> rightclickableBlocks;
    private static final /* synthetic */ Minecraft mc;
    
    public static boolean canSeeBlock(final BlockPos blockPos) {
        return TestUtil.mc.player != null && TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static boolean canPlaceBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                if (!TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
                    if (TestUtil.mc.player.getPositionEyes(TestUtil.mc.getRenderPartialTicks()).distanceTo(new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5)) <= 4.25) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b) {
        return rayTracePlaceCheck(blockPos, b, 1.0f);
    }
    
    public static void openBlock(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            if (TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock())) {
                TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos, enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                return;
            }
        }
    }
    
    static {
        mc = Minecraft.getMinecraft();
        TestUtil.emptyBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.VINE, Blocks.SNOW_LAYER, (Block)Blocks.TALLGRASS, (Block)Blocks.FIRE);
        TestUtil.rightclickableBlocks = Arrays.asList((Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.ENDER_CHEST, Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.ANVIL, Blocks.WOODEN_BUTTON, Blocks.STONE_BUTTON, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, (Block)Blocks.POWERED_COMPARATOR, Blocks.OAK_FENCE_GATE, Blocks.SPRUCE_FENCE_GATE, Blocks.BIRCH_FENCE_GATE, Blocks.JUNGLE_FENCE_GATE, Blocks.DARK_OAK_FENCE_GATE, Blocks.ACACIA_FENCE_GATE, Blocks.BREWING_STAND, Blocks.DISPENSER, Blocks.DROPPER, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.JUKEBOX, (Block)Blocks.BEACON, Blocks.BED, Blocks.FURNACE, (Block)Blocks.OAK_DOOR, (Block)Blocks.SPRUCE_DOOR, (Block)Blocks.BIRCH_DOOR, (Block)Blocks.JUNGLE_DOOR, (Block)Blocks.ACACIA_DOOR, (Block)Blocks.DARK_OAK_DOOR, Blocks.CAKE, Blocks.ENCHANTING_TABLE, Blocks.DRAGON_EGG, (Block)Blocks.HOPPER, Blocks.REPEATING_COMMAND_BLOCK, Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.CRAFTING_TABLE);
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand) {
        final RayTraceResult rayTraceBlocks = TestUtil.mc.world.rayTraceBlocks(new Vec3d(TestUtil.mc.player.posX, TestUtil.mc.player.posY + TestUtil.mc.player.getEyeHeight(), TestUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, enumHand, 0.0f, 0.0f, 0.0f));
    }
    
    public static boolean isBlockEmpty(final BlockPos blockPos) {
        try {
            if (TestUtil.emptyBlocks.contains(TestUtil.mc.world.getBlockState(blockPos).getBlock())) {
                final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(blockPos);
                final Iterator<Entity> iterator = TestUtil.mc.world.loadedEntityList.iterator();
                while (iterator.hasNext()) {
                    final Entity entity;
                    if ((entity = iterator.next()) instanceof EntityLivingBase && axisAlignedBB.intersects(entity.getEntityBoundingBox())) {
                        return false;
                    }
                }
                return true;
            }
        }
        catch (Exception ex) {}
        return false;
    }
    
    public static boolean placeBlock(final BlockPos blockPos) {
        if (isBlockEmpty(blockPos)) {
            for (final EnumFacing enumFacing : EnumFacing.values()) {
                final Block getBlock = TestUtil.mc.world.getBlockState(blockPos.offset(enumFacing)).getBlock();
                final Vec3d vec3d = new Vec3d(blockPos.getX() + 0.5 + enumFacing.getXOffset() * 0.5, blockPos.getY() + 0.5 + enumFacing.getYOffset() * 0.5, blockPos.getZ() + 0.5 + enumFacing.getZOffset() * 0.5);
                if (!TestUtil.emptyBlocks.contains(getBlock) && TestUtil.mc.player.getPositionEyes(TestUtil.mc.getRenderPartialTicks()).distanceTo(vec3d) <= 4.25) {
                    final float[] array = { TestUtil.mc.player.rotationYaw, TestUtil.mc.player.rotationPitch };
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
                    }
                    TestUtil.mc.playerController.processRightClickBlock(TestUtil.mc.player, TestUtil.mc.world, blockPos.offset(enumFacing), enumFacing.getOpposite(), new Vec3d((Vec3i)blockPos), EnumHand.MAIN_HAND);
                    if (TestUtil.rightclickableBlocks.contains(getBlock)) {
                        TestUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)TestUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    }
                    TestUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                    return true;
                }
            }
        }
        return false;
    }
}

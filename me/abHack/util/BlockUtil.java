//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.entity.*;
import net.minecraft.block.state.*;
import net.minecraft.block.material.*;
import net.minecraft.init.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.entity.item.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import me.abHack.features.command.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import java.util.stream.*;
import java.util.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.client.*;
import com.google.common.util.concurrent.*;
import java.util.concurrent.atomic.*;
import net.minecraft.network.play.client.*;
import me.abHack.*;

public class BlockUtil implements Util
{
    public static final /* synthetic */ List<Block> shulkerList;
    public static /* synthetic */ List<Block> unSolidBlocks;
    public static final /* synthetic */ List<Block> blackList;
    public static final /* synthetic */ List<Block> unSafeBlocks;
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos) {
        return rayTracePlaceCheck(blockPos, true);
    }
    
    public static BlockPos vec3dToPos(final Vec3d vec3d) {
        return new BlockPos(vec3d);
    }
    
    public static List<BlockPos> getSphere(final BlockPos blockPos, final float n, final int n2, final boolean b, final boolean b2, final int n3) {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final int getX = blockPos.getX();
        final int getY = blockPos.getY();
        final int getZ = blockPos.getZ();
        for (int n4 = getX - (int)n; n4 <= getX + n; ++n4) {
            for (int n5 = getZ - (int)n; n5 <= getZ + n; ++n5) {
                for (int n6 = b2 ? (getY - (int)n) : getY; n6 < (b2 ? (getY + n) : ((float)(getY + n2))); ++n6) {
                    final double n7 = (getX - n4) * (getX - n4) + (getZ - n5) * (getZ - n5) + (b2 ? ((getY - n6) * (getY - n6)) : 0);
                    if (n7 < n * n && (!b || n7 >= (n - 1.0f) * (n - 1.0f))) {
                        list.add(new BlockPos(n4, n6 + n3, n5));
                    }
                }
            }
        }
        return list;
    }
    
    public static Boolean isPosInFov(final BlockPos blockPos) {
        final int direction4D = RotationUtil.getDirection4D();
        if (direction4D == 0 && blockPos.getZ() - BlockUtil.mc.player.getPositionVector().z < 0.0) {
            return false;
        }
        if (direction4D == 1 && blockPos.getX() - BlockUtil.mc.player.getPositionVector().x > 0.0) {
            return false;
        }
        if (direction4D == 2 && blockPos.getZ() - BlockUtil.mc.player.getPositionVector().z > 0.0) {
            return false;
        }
        return direction4D != 3 || blockPos.getX() - BlockUtil.mc.player.getPositionVector().x >= 0.0;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos, final boolean b, final boolean b2) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if ((!b2 && BlockUtil.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) || BlockUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR) {
                return false;
            }
            for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add))) {
                if (!entity.isDead) {
                    if (b && entity instanceof EntityEnderCrystal) {
                        continue;
                    }
                    return false;
                }
            }
            if (!b2) {
                for (final Entity entity2 : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2))) {
                    if (!entity2.isDead) {
                        if (b && entity2 instanceof EntityEnderCrystal) {
                            continue;
                        }
                        return false;
                    }
                }
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidBlock(final BlockPos blockPos) {
        final Block getBlock = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        return !(getBlock instanceof BlockLiquid) && getBlock.getMaterial((IBlockState)null) != Material.AIR;
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getY() - 0.5, blockPos.getZ() + 0.5));
        final EnumFacing enumFacing = (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit;
        final int currentItem = BlockUtil.mc.player.inventory.currentItem;
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(itemHotbar));
        }
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, 0.0f, 0.0f, 0.0f));
        if (enumHand == EnumHand.MAIN_HAND && b3 && itemHotbar != -1 && itemHotbar != BlockUtil.mc.player.inventory.currentItem) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        }
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketAnimation(b2 ? enumHand : EnumHand.MAIN_HAND));
        }
    }
    
    public static EnumFacing getRayTraceFacing(final BlockPos blockPos) {
        final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5, blockPos.getX() - 0.5, blockPos.getX() + 0.5));
        if (rayTraceBlocks == null || rayTraceBlocks.sideHit == null) {
            return EnumFacing.UP;
        }
        return rayTraceBlocks.sideHit;
    }
    
    public static EnumFacing getFacing(final BlockPos blockPos) {
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final RayTraceResult rayTraceBlocks = BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(blockPos.getX() + 0.5 + enumFacing.getDirectionVec().getX() * 1.0 / 2.0, blockPos.getY() + 0.5 + enumFacing.getDirectionVec().getY() * 1.0 / 2.0, blockPos.getZ() + 0.5 + enumFacing.getDirectionVec().getZ() * 1.0 / 2.0), false, true, false);
            if (rayTraceBlocks == null || (rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK && rayTraceBlocks.getBlockPos().equals((Object)blockPos))) {
                return enumFacing;
            }
        }
        if (blockPos.getY() > BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight()) {
            return EnumFacing.DOWN;
        }
        return EnumFacing.UP;
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b, final boolean b2) {
        final Block getBlock = BlockUtil.mc.world.getBlockState(blockPos).getBlock();
        if (!(getBlock instanceof BlockAir) && !(getBlock instanceof BlockLiquid) && !(getBlock instanceof BlockTallGrass) && !(getBlock instanceof BlockFire) && !(getBlock instanceof BlockDeadBush) && !(getBlock instanceof BlockSnow)) {
            return 0;
        }
        if (!rayTracePlaceCheck(blockPos, b, 0.0f)) {
            return -1;
        }
        if (b2) {
            for (final Entity entity : BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(blockPos))) {
                if (!(entity instanceof EntityItem)) {
                    if (entity instanceof EntityXPOrb) {
                        continue;
                    }
                    return 1;
                }
            }
        }
        final Iterator<EnumFacing> iterator2 = getPossibleSides(blockPos).iterator();
        while (iterator2.hasNext()) {
            if (!canBeClicked(blockPos.offset((EnumFacing)iterator2.next()))) {
                continue;
            }
            return 3;
        }
        return 2;
    }
    
    public static boolean isBlockBelowEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY - 1.0, entity.posZ));
    }
    
    public static boolean canBreak(final BlockPos blockPos) {
        final IBlockState getBlockState = BlockUtil.mc.world.getBlockState(blockPos);
        return getBlockState.getBlock().getBlockHardness(getBlockState, (World)BlockUtil.mc.world, blockPos) != -1.0f;
    }
    
    static {
        blackList = Arrays.asList(Blocks.ENDER_CHEST, (Block)Blocks.CHEST, Blocks.TRAPPED_CHEST, Blocks.CRAFTING_TABLE, Blocks.ANVIL, Blocks.BREWING_STAND, (Block)Blocks.HOPPER, Blocks.DROPPER, Blocks.DISPENSER, Blocks.TRAPDOOR, Blocks.ENCHANTING_TABLE);
        shulkerList = Arrays.asList(Blocks.WHITE_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.SILVER_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.BLACK_SHULKER_BOX);
        unSafeBlocks = Arrays.asList(Blocks.OBSIDIAN, Blocks.BEDROCK, Blocks.ENDER_CHEST, Blocks.ANVIL);
        BlockUtil.unSolidBlocks = Arrays.asList((Block)Blocks.FLOWING_LAVA, Blocks.FLOWER_POT, Blocks.SNOW, Blocks.CARPET, Blocks.END_ROD, (Block)Blocks.SKULL, Blocks.FLOWER_POT, Blocks.TRIPWIRE, (Block)Blocks.TRIPWIRE_HOOK, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.STONE_BUTTON, Blocks.LADDER, (Block)Blocks.UNPOWERED_COMPARATOR, (Block)Blocks.POWERED_COMPARATOR, (Block)Blocks.UNPOWERED_REPEATER, (Block)Blocks.POWERED_REPEATER, Blocks.UNLIT_REDSTONE_TORCH, Blocks.REDSTONE_TORCH, (Block)Blocks.REDSTONE_WIRE, Blocks.AIR, (Block)Blocks.PORTAL, Blocks.END_PORTAL, (Block)Blocks.WATER, (Block)Blocks.FLOWING_WATER, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_LAVA, Blocks.SAPLING, (Block)Blocks.RED_FLOWER, (Block)Blocks.YELLOW_FLOWER, (Block)Blocks.BROWN_MUSHROOM, (Block)Blocks.RED_MUSHROOM, Blocks.WHEAT, Blocks.CARROTS, Blocks.POTATOES, Blocks.BEETROOTS, (Block)Blocks.REEDS, Blocks.PUMPKIN_STEM, Blocks.MELON_STEM, Blocks.WATERLILY, Blocks.NETHER_WART, Blocks.COCOA, Blocks.CHORUS_FLOWER, Blocks.CHORUS_PLANT, (Block)Blocks.TALLGRASS, (Block)Blocks.DEADBUSH, Blocks.VINE, (Block)Blocks.FIRE, Blocks.RAIL, Blocks.ACTIVATOR_RAIL, Blocks.DETECTOR_RAIL, Blocks.GOLDEN_RAIL, Blocks.TORCH);
    }
    
    public static EnumFacing getFirstFacing(final BlockPos blockPos) {
        final Iterator<EnumFacing> iterator = getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            return iterator.next();
        }
        return null;
    }
    
    public static boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
            b4 = true;
        }
        if (b) {
            RotationUtil.faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static int isPositionPlaceable(final BlockPos blockPos, final boolean b) {
        return isPositionPlaceable(blockPos, b, true);
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b, final float n) {
        return !b || BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)(blockPos.getY() + n), (double)blockPos.getZ()), false, true, false) == null;
    }
    
    public static void debugPos(final String str, final BlockPos blockPos) {
        Command.sendMessage(String.valueOf(new StringBuilder().append(str).append(blockPos.getX()).append("x, ").append(blockPos.getY()).append("y, ").append(blockPos.getZ()).append("z")));
    }
    
    public static void placeBlockStopSneaking(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final boolean placeBlockSmartRotate = placeBlockSmartRotate(blockPos, enumHand, b, b2, b3);
        if (!b3 && placeBlockSmartRotate) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    private static IBlockState getState(final BlockPos blockPos) {
        return BlockUtil.mc.world.getBlockState(blockPos);
    }
    
    public static boolean isBlockAboveEntitySolid(final Entity entity) {
        return entity != null && isBlockSolid(new BlockPos(entity.posX, entity.posY + 2.0, entity.posZ));
    }
    
    public static boolean canBlockBeSeen(final double n, final double n2, final double n3) {
        return BlockUtil.mc.world.rayTraceBlocks(new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ), new Vec3d(n, n2 + 1.7, n3), false, true, false) == null;
    }
    
    public static Vec3d[] convertVec3ds(final EntityPlayer entityPlayer, final Vec3d[] array) {
        return convertVec3ds(entityPlayer.getPositionVector(), array);
    }
    
    public static Vec3d[] getHelpingBlocks(final Vec3d vec3d) {
        return new Vec3d[] { new Vec3d(vec3d.x, vec3d.y - 1.0, vec3d.z), new Vec3d((vec3d.x != 0.0) ? (vec3d.x * 2.0) : vec3d.x, vec3d.y, (vec3d.x != 0.0) ? vec3d.z : (vec3d.z * 2.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x + 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z + 1.0)), new Vec3d((vec3d.x == 0.0) ? (vec3d.x - 1.0) : vec3d.x, vec3d.y, (vec3d.x == 0.0) ? vec3d.z : (vec3d.z - 1.0)), new Vec3d(vec3d.x, vec3d.y + 1.0, vec3d.z) };
    }
    
    public static List<EnumFacing> getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        for (final EnumFacing e : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(e);
            if (BlockUtil.mc.world.getBlockState(offset).getBlock().canCollideCheck(BlockUtil.mc.world.getBlockState(offset), false)) {
                if (!BlockUtil.mc.world.getBlockState(offset).getMaterial().isReplaceable()) {
                    list.add(e);
                }
            }
        }
        return list;
    }
    
    public static List<BlockPos> possiblePlacePositions(final float n, final boolean b, final boolean b2) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> canPlaceCrystal(blockPos, b, b2)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static Vec3d posToVec3d(final BlockPos blockPos) {
        return new Vec3d((Vec3i)blockPos);
    }
    
    private static Block getBlock(final BlockPos blockPos) {
        return getState(blockPos).getBlock();
    }
    
    public static boolean canBeClicked(final BlockPos blockPos) {
        return getBlock(blockPos).canCollideCheck(getState(blockPos), false);
    }
    
    public static boolean rayTracePlaceCheck(final BlockPos blockPos, final boolean b) {
        return rayTracePlaceCheck(blockPos, b, 1.0f);
    }
    
    public static void placeBlockScaffold(final BlockPos blockPos) {
        final Vec3d vec3d = new Vec3d(BlockUtil.mc.player.posX, BlockUtil.mc.player.posY + BlockUtil.mc.player.getEyeHeight(), BlockUtil.mc.player.posZ);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset = blockPos.offset(enumFacing);
            final EnumFacing getOpposite = enumFacing.getOpposite();
            final Vec3d add;
            if (canBeClicked(offset) && vec3d.squareDistanceTo(add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5))) <= 18.0625) {
                faceVectorPacketInstant(add);
                processRightClickBlock(offset, getOpposite, add);
                BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
                BlockUtil.mc.rightClickDelayTimer = 4;
                return;
            }
        }
    }
    
    public static void placeCrystalOnBlock(final BlockPos blockPos, final EnumHand enumHand) {
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, EnumFacing.UP, enumHand, (float)blockPos.getX(), (float)blockPos.getY(), (float)blockPos.getZ()));
    }
    
    private static PlayerControllerMP getPlayerController() {
        return Minecraft.getMinecraft().playerController;
    }
    
    public static void rightClickBlockLegit(final BlockPos blockPos, final float n, final boolean b, final EnumHand enumHand, final AtomicDouble atomicDouble, final AtomicDouble atomicDouble2, final AtomicBoolean atomicBoolean) {
        final Vec3d eyesPos = RotationUtil.getEyesPos();
        final Vec3d add = new Vec3d((Vec3i)blockPos).add(0.5, 0.5, 0.5);
        final double squareDistanceTo = eyesPos.squareDistanceTo(add);
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final Vec3d add = add.add(new Vec3d(enumFacing.getDirectionVec()).scale(0.5));
            final double squareDistanceTo2 = eyesPos.squareDistanceTo(add);
            if (squareDistanceTo2 <= MathUtil.square(n) && squareDistanceTo2 < squareDistanceTo && BlockUtil.mc.world.rayTraceBlocks(eyesPos, add, false, true, false) == null) {
                if (b) {
                    final float[] legitRotations = RotationUtil.getLegitRotations(add);
                    atomicDouble.set((double)legitRotations[0]);
                    atomicDouble2.set((double)legitRotations[1]);
                    atomicBoolean.set(true);
                }
                BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, add, enumHand);
                BlockUtil.mc.player.swingArm(enumHand);
                BlockUtil.mc.rightClickDelayTimer = 4;
                break;
            }
        }
    }
    
    public static Vec3d[] convertVec3ds(final Vec3d vec3d, final Vec3d[] array) {
        final Vec3d[] array2 = new Vec3d[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = vec3d.add(array[i]);
        }
        return array2;
    }
    
    public static void faceVectorPacketInstant(final Vec3d vec3d) {
        final float[] legitRotations = RotationUtil.getLegitRotations(vec3d);
        BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], legitRotations[1], BlockUtil.mc.player.onGround));
    }
    
    public static boolean isBlockUnSafe(final Block block) {
        return BlockUtil.unSafeBlocks.contains(block);
    }
    
    public static boolean placeBlock2(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
            BlockUtil.mc.player.setSneaking(false);
            b4 = true;
        }
        if (b) {
            RotationUtil.faceVector(add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static boolean placeBlockSmartRotate(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        boolean b4 = false;
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        Command.sendMessage(firstFacing.toString());
        if (firstFacing == null) {
            return b3;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b4 = true;
        }
        if (b) {
            OyVey.rotationManager.lookAtVec3d(add);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
        return b4 || b3;
    }
    
    public static boolean isBlockUnSolid(final Block block) {
        return BlockUtil.unSolidBlocks.contains(block);
    }
    
    public static List<BlockPos> getBlockSphere(final float n, final Class clazz) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)getSphere(EntityUtil.getPlayerPos((EntityPlayer)BlockUtil.mc.player), n, (int)n, false, true, 0).stream().filter(blockPos -> clazz.isInstance(BlockUtil.mc.world.getBlockState(blockPos).getBlock())).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        return (List<BlockPos>)create;
    }
    
    public static boolean isBlockSolid(final BlockPos blockPos) {
        return !isBlockUnSolid(blockPos);
    }
    
    public static boolean isScaffoldPos(final BlockPos blockPos) {
        return BlockUtil.mc.world.isAirBlock(blockPos) || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.SNOW_LAYER || BlockUtil.mc.world.getBlockState(blockPos).getBlock() == Blocks.TALLGRASS || BlockUtil.mc.world.getBlockState(blockPos).getBlock() instanceof BlockLiquid;
    }
    
    public static void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            BlockUtil.mc.playerController.processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        BlockUtil.mc.rightClickDelayTimer = 4;
    }
    
    public static boolean canPlaceCrystal(final BlockPos blockPos) {
        final BlockPos add = blockPos.add(0, 1, 0);
        final BlockPos add2 = blockPos.add(0, 2, 0);
        try {
            if (BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.BEDROCK && BlockUtil.mc.world.getBlockState(blockPos).getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
            if (BlockUtil.mc.world.getBlockState(add).getBlock() != Blocks.AIR || BlockUtil.mc.world.getBlockState(add2).getBlock() != Blocks.AIR) {
                return false;
            }
            final Iterator iterator = BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add)).iterator();
            while (iterator.hasNext()) {
                if (iterator.next() instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
            final Iterator iterator2 = BlockUtil.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(add2)).iterator();
            while (iterator2.hasNext()) {
                if (iterator2.next() instanceof EntityEnderCrystal) {
                    continue;
                }
                return false;
            }
        }
        catch (Exception ex) {
            return false;
        }
        return true;
    }
    
    public static boolean placeBlockNotRetarded(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2, final boolean b3) {
        final EnumFacing firstFacing = getFirstFacing(blockPos);
        if (firstFacing == null) {
            return false;
        }
        final BlockPos offset = blockPos.offset(firstFacing);
        final EnumFacing getOpposite = firstFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = BlockUtil.mc.world.getBlockState(offset).getBlock();
        if (!BlockUtil.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            BlockUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BlockUtil.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            BlockUtil.mc.player.setSneaking(true);
        }
        if (b) {
            RotationUtil.faceVector(b3 ? new Vec3d((Vec3i)blockPos) : add, true);
        }
        rightClickBlock(offset, add, enumHand, getOpposite, b2);
        BlockUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        return true;
    }
    
    public static BlockPos[] toBlockPos(final Vec3d[] array) {
        final BlockPos[] array2 = new BlockPos[array.length];
        for (int i = 0; i < array.length; ++i) {
            array2[i] = new BlockPos(array[i]);
        }
        return array2;
    }
    
    public static boolean isBlockUnSolid(final BlockPos blockPos) {
        return isBlockUnSolid(BlockUtil.mc.world.getBlockState(blockPos).getBlock());
    }
    
    static void processRightClickBlock(final BlockPos blockPos, final EnumFacing enumFacing, final Vec3d vec3d) {
        getPlayerController().processRightClickBlock(BlockUtil.mc.player, BlockUtil.mc.world, blockPos, enumFacing, vec3d, EnumHand.MAIN_HAND);
    }
}

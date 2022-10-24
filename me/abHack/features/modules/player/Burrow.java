//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.block.*;
import net.minecraft.item.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import me.abHack.util.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.block.state.*;

public class Burrow extends Module
{
    private /* synthetic */ int oldSlot;
    private final /* synthetic */ Setting<Double> offset;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ BlockPos originalPos;
    
    public static int getBlockSlot(final Block block) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = Burrow.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot == ItemStack.EMPTY) {
                return -1;
            }
            if (getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    public void switchToSlot(final int currentItem, final boolean b) {
        if (b) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            Burrow.mc.playerController.updateController();
        }
        else {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            Burrow.mc.player.inventory.currentItem = currentItem;
            Burrow.mc.playerController.updateController();
        }
    }
    
    private boolean intersectsWithEntity(final BlockPos blockPos) {
        for (final Entity entity : Burrow.mc.world.loadedEntityList) {
            if (!entity.equals((Object)Burrow.mc.player) && !(entity instanceof EntityItem) && new AxisAlignedBB(blockPos).intersects(entity.getEntityBoundingBox())) {
                return true;
            }
        }
        return false;
    }
    
    public void rightClickBlock(final BlockPos blockPos, final Vec3d vec3d, final EnumHand enumHand, final EnumFacing enumFacing, final boolean b) {
        if (b) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItemOnBlock(blockPos, enumFacing, enumHand, (float)(vec3d.x - blockPos.getX()), (float)(vec3d.y - blockPos.getY()), (float)(vec3d.z - blockPos.getZ())));
        }
        else {
            Burrow.mc.playerController.processRightClickBlock(Burrow.mc.player, Burrow.mc.world, blockPos, enumFacing, vec3d, enumHand);
        }
        Burrow.mc.player.swingArm(EnumHand.MAIN_HAND);
        Burrow.mc.rightClickDelayTimer = 4;
    }
    
    public void onUpdate() {
        if (getBlockSlot(Blocks.OBSIDIAN) == -1 && getBlockSlot(Blocks.ENDER_CHEST) == -1) {
            this.toggle();
            return;
        }
        if (getBlockSlot(Blocks.OBSIDIAN) != -1) {
            this.switchToSlot(getBlockSlot(Blocks.OBSIDIAN), false);
        }
        else {
            this.switchToSlot(getBlockSlot(Blocks.ENDER_CHEST), false);
        }
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.41999998688698, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 0.7531999805211997, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.00133597911214, Burrow.mc.player.posZ, true));
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + 1.06610926093821, Burrow.mc.player.posZ, true));
        this.placeBlock(this.originalPos, EnumHand.MAIN_HAND, this.rotate.getValue(), false);
        Burrow.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Burrow.mc.player.posX, Burrow.mc.player.posY + this.offset.getValue(), Burrow.mc.player.posZ, false));
        this.switchToSlot(this.oldSlot, false);
        this.toggle();
    }
    
    public boolean placeBlock(final BlockPos blockPos, final EnumHand enumHand, final boolean b, final boolean b2) {
        boolean b3 = false;
        EnumFacing enumFacing = null;
        final Iterator<EnumFacing> iterator = this.getPossibleSides(blockPos).iterator();
        if (iterator.hasNext()) {
            enumFacing = iterator.next();
        }
        if (enumFacing == null) {
            return b2;
        }
        final BlockPos offset = blockPos.offset(enumFacing);
        final EnumFacing getOpposite = enumFacing.getOpposite();
        final Vec3d add = new Vec3d((Vec3i)offset).add(0.5, 0.5, 0.5).add(new Vec3d(getOpposite.getDirectionVec()).scale(0.5));
        final Block getBlock = Burrow.mc.world.getBlockState(offset).getBlock();
        if (!Burrow.mc.player.isSneaking() && (BlockUtil.blackList.contains(getBlock) || BlockUtil.shulkerList.contains(getBlock))) {
            Burrow.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Burrow.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            Burrow.mc.player.setSneaking(true);
            b3 = true;
        }
        this.rightClickBlock(offset, add, enumHand, getOpposite, true);
        Burrow.mc.player.swingArm(EnumHand.MAIN_HAND);
        Burrow.mc.rightClickDelayTimer = 4;
        return b3 || b2;
    }
    
    public void onEnable() {
        this.originalPos = new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ);
        if (Burrow.mc.world.getBlockState(new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ)).getBlock().equals(Blocks.OBSIDIAN) || Burrow.mc.world.getBlockState(new BlockPos(Burrow.mc.player.posX, Burrow.mc.player.posY, Burrow.mc.player.posZ)).getBlock().equals(Blocks.ENDER_CHEST) || this.intersectsWithEntity(this.originalPos)) {
            this.toggle();
            return;
        }
        this.oldSlot = Burrow.mc.player.inventory.currentItem;
    }
    
    public List<EnumFacing> getPossibleSides(final BlockPos blockPos) {
        final ArrayList<EnumFacing> list = new ArrayList<EnumFacing>();
        if (Burrow.mc.world == null || blockPos == null) {
            return list;
        }
        for (final EnumFacing e : EnumFacing.values()) {
            final IBlockState getBlockState = Burrow.mc.world.getBlockState(blockPos.offset(e));
            if (getBlockState != null && getBlockState.getBlock().canCollideCheck(getBlockState, false) && !getBlockState.getMaterial().isReplaceable()) {
                list.add(e);
            }
        }
        return list;
    }
    
    public Burrow() {
        super("Burrow", "Rubberbands you into a block", Module.Category.PLAYER, true, false, false);
        this.oldSlot = -1;
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.offset = (Setting<Double>)this.register(new Setting("Offset", (T)1.2, (T)(-5.0), (T)10.0));
    }
}

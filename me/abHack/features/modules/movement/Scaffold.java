//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.client.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import me.abHack.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import me.abHack.event.events.*;
import me.abHack.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Scaffold extends Module
{
    private /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Float> expand;
    private final /* synthetic */ Setting<Mode> mode;
    private /* synthetic */ int lastY;
    private final /* synthetic */ Setting<Boolean> down;
    private final /* synthetic */ Setting<Boolean> sprint;
    private final /* synthetic */ Setting<Boolean> center;
    private final /* synthetic */ Setting<Boolean> swing;
    private final /* synthetic */ Setting<Boolean> bSwitch;
    private /* synthetic */ boolean teleported;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Timer itemTimer;
    private final /* synthetic */ Setting<Boolean> keepY;
    public /* synthetic */ Setting<Boolean> rotation;
    private final /* synthetic */ Timer timerMotion;
    private final /* synthetic */ Setting<Boolean> replenishBlocks;
    private final /* synthetic */ List<Block> invalid;
    
    public static boolean isMoving(final EntityLivingBase entityLivingBase) {
        return entityLivingBase.moveForward != 0.0f || entityLivingBase.moveStrafing != 0.0f;
    }
    
    public static void swap(final int n, final int n2) {
        Scaffold.mc.playerController.windowClick(Scaffold.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Scaffold.mc.player);
        Scaffold.mc.playerController.windowClick(Scaffold.mc.player.inventoryContainer.windowId, n2, 0, ClickType.PICKUP, (EntityPlayer)Scaffold.mc.player);
        Scaffold.mc.playerController.windowClick(Scaffold.mc.player.inventoryContainer.windowId, n, 0, ClickType.PICKUP, (EntityPlayer)Scaffold.mc.player);
        Scaffold.mc.playerController.updateController();
    }
    
    public void place(final BlockPos blockPos, final EnumFacing enumFacing) {
        BlockPos blockPos2 = blockPos;
        if (enumFacing == EnumFacing.UP) {
            blockPos2 = blockPos2.add(0, -1, 0);
        }
        else if (enumFacing == EnumFacing.NORTH) {
            blockPos2 = blockPos2.add(0, 0, 1);
        }
        else if (enumFacing == EnumFacing.SOUTH) {
            blockPos2 = blockPos2.add(0, 0, -1);
        }
        else if (enumFacing == EnumFacing.EAST) {
            blockPos2 = blockPos2.add(-1, 0, 0);
        }
        else if (enumFacing == EnumFacing.WEST) {
            blockPos2 = blockPos2.add(1, 0, 0);
        }
        final int currentItem = Scaffold.mc.player.inventory.currentItem;
        int currentItem2 = -1;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = Scaffold.mc.player.inventory.getStackInSlot(i);
            if (!InventoryUtil.isNull(getStackInSlot) && getStackInSlot.getItem() instanceof ItemBlock && Block.getBlockFromItem(getStackInSlot.getItem()).getDefaultState().isFullBlock()) {
                currentItem2 = i;
                break;
            }
        }
        if (currentItem2 == -1) {
            return;
        }
        boolean b = false;
        if (!Scaffold.mc.player.isSneaking() && BlockUtil.blackList.contains(Scaffold.mc.world.getBlockState(blockPos2).getBlock())) {
            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Scaffold.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            b = true;
        }
        if (!(Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem2));
            Scaffold.mc.player.inventory.currentItem = currentItem2;
            Scaffold.mc.playerController.updateController();
        }
        if (Scaffold.mc.gameSettings.keyBindJump.isKeyDown()) {
            final EntityPlayerSP player = Scaffold.mc.player;
            player.motionX *= 0.3;
            final EntityPlayerSP player2 = Scaffold.mc.player;
            player2.motionZ *= 0.3;
            Scaffold.mc.player.jump();
            if (this.timer.passedMs(1500L)) {
                Scaffold.mc.player.motionY = -0.28;
                this.timer.reset();
            }
        }
        if (this.rotation.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(Scaffold.mc.player.getPositionEyes(Scaffold.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos2.getX() + 0.5f), (double)(blockPos2.getY() - 0.5f), (double)(blockPos2.getZ() + 0.5f)));
            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(calcAngle[0], (float)MathHelper.normalizeAngle((int)calcAngle[1], 360), Scaffold.mc.player.onGround));
        }
        Scaffold.mc.playerController.processRightClickBlock(Scaffold.mc.player, Scaffold.mc.world, blockPos2, enumFacing, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
        Scaffold.mc.player.swingArm(EnumHand.MAIN_HAND);
        Scaffold.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        Scaffold.mc.player.inventory.currentItem = currentItem;
        Scaffold.mc.playerController.updateController();
        if (b) {
            Scaffold.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Scaffold.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
    }
    
    public static int getItemSlot(final Container container, final Item item) {
        int n = 0;
        for (int i = 9; i < 45; ++i) {
            if (container.getSlot(i).getHasStack()) {
                if (container.getSlot(i).getStack().getItem() == item) {
                    n = i;
                }
            }
        }
        return n;
    }
    
    public void onEnable() {
        this.timer.reset();
    }
    
    public Scaffold() {
        super("Scaffold", "Places Blocks underneath you.", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.Legit));
        this.swing = (Setting<Boolean>)this.register(new Setting("Swing Arm", (T)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.Legit));
        this.bSwitch = (Setting<Boolean>)this.register(new Setting("Switch", (T)Boolean.TRUE, p0 -> this.mode.getValue() == Mode.Legit));
        this.center = (Setting<Boolean>)this.register(new Setting("Center", (T)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.Legit));
        this.keepY = (Setting<Boolean>)this.register(new Setting("KeepYLevel", (T)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.Legit));
        this.sprint = (Setting<Boolean>)this.register(new Setting("UseSprint", (T)Boolean.TRUE, p0 -> this.mode.getValue() == Mode.Legit));
        this.replenishBlocks = (Setting<Boolean>)this.register(new Setting("ReplenishBlocks", (T)Boolean.TRUE, p0 -> this.mode.getValue() == Mode.Legit));
        this.down = (Setting<Boolean>)this.register(new Setting("Down", (T)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.Legit));
        this.expand = (Setting<Float>)this.register(new Setting("Expand", (T)2.0f, (T)1.0f, (T)6.0f, p0 -> this.mode.getValue() == Mode.Legit));
        this.invalid = Arrays.asList(Blocks.ENCHANTING_TABLE, Blocks.FURNACE, Blocks.CARPET, Blocks.CRAFTING_TABLE, Blocks.TRAPPED_CHEST, (Block)Blocks.CHEST, Blocks.DISPENSER, Blocks.AIR, (Block)Blocks.WATER, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.FLOWING_LAVA, Blocks.SNOW_LAYER, Blocks.TORCH, Blocks.ANVIL, Blocks.JUKEBOX, Blocks.STONE_BUTTON, Blocks.WOODEN_BUTTON, Blocks.LEVER, Blocks.NOTEBLOCK, Blocks.STONE_PRESSURE_PLATE, Blocks.LIGHT_WEIGHTED_PRESSURE_PLATE, Blocks.WOODEN_PRESSURE_PLATE, Blocks.HEAVY_WEIGHTED_PRESSURE_PLATE, (Block)Blocks.RED_MUSHROOM, (Block)Blocks.BROWN_MUSHROOM, (Block)Blocks.YELLOW_FLOWER, (Block)Blocks.RED_FLOWER, Blocks.ANVIL, (Block)Blocks.CACTUS, Blocks.LADDER, Blocks.ENDER_CHEST);
        this.timerMotion = new Timer();
        this.itemTimer = new Timer();
        this.timer = new Timer();
        this.rotation = (Setting<Boolean>)this.register(new Setting("Rotate", (T)Boolean.FALSE, p0 -> this.mode.getValue() == Mode.Fast));
    }
    
    public boolean canPlace(final Block block) {
        return (block instanceof BlockAir || block instanceof BlockLiquid) && Scaffold.mc.world != null && Scaffold.mc.player != null && this.pos != null && Scaffold.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(this.pos)).isEmpty();
    }
    
    public void onUpdate() {
        if (this.mode.getValue() == Mode.Legit) {
            if (OyVey.moduleManager.isModuleEnabled("Sprint") && ((this.down.getValue() && Scaffold.mc.gameSettings.keyBindSneak.isKeyDown()) || !this.sprint.getValue())) {
                Scaffold.mc.player.setSprinting(false);
                Sprint.getInstance().disable();
            }
            if (this.replenishBlocks.getValue() && !(Scaffold.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBlock) && this.getBlockCountHotbar() <= 0 && this.itemTimer.passedMs(100L)) {
                for (int i = 9; i < 45; ++i) {
                    final ItemStack getStack;
                    if (Scaffold.mc.player.inventoryContainer.getSlot(i).getHasStack() && (getStack = Scaffold.mc.player.inventoryContainer.getSlot(i).getStack()).getItem() instanceof ItemBlock && !this.invalid.contains(Block.getBlockFromItem(getStack.getItem()))) {
                        if (i < 36) {
                            swap(getItemSlot(Scaffold.mc.player.inventoryContainer, getStack.getItem()), 44);
                        }
                    }
                }
            }
            if (this.keepY.getValue()) {
                if ((!isMoving((EntityLivingBase)Scaffold.mc.player) && Scaffold.mc.gameSettings.keyBindJump.isKeyDown()) || Scaffold.mc.player.collidedVertically || Scaffold.mc.player.onGround) {
                    this.lastY = MathHelper.floor(Scaffold.mc.player.posY);
                }
            }
            else {
                this.lastY = MathHelper.floor(Scaffold.mc.player.posY);
            }
            BlockData blockData2 = null;
            double n = Scaffold.mc.player.posX;
            double n2 = Scaffold.mc.player.posZ;
            final double n3 = this.keepY.getValue() ? this.lastY : Scaffold.mc.player.posY;
            final double n4 = Scaffold.mc.player.movementInput.moveForward;
            final double n5 = Scaffold.mc.player.movementInput.moveStrafe;
            final float rotationYaw = Scaffold.mc.player.rotationYaw;
            if (!Scaffold.mc.player.collidedHorizontally) {
                final double[] expandCoords = this.getExpandCoords(n, n2, n4, n5, rotationYaw);
                n = expandCoords[0];
                n2 = expandCoords[1];
            }
            if (this.canPlace(Scaffold.mc.world.getBlockState(new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY - ((Scaffold.mc.gameSettings.keyBindSneak.isKeyDown() && this.down.getValue()) ? 2 : 1), Scaffold.mc.player.posZ)).getBlock())) {
                n = Scaffold.mc.player.posX;
                n2 = Scaffold.mc.player.posZ;
            }
            BlockPos pos = new BlockPos(n, n3 - 1.0, n2);
            if (Scaffold.mc.gameSettings.keyBindSneak.isKeyDown() && this.down.getValue()) {
                pos = new BlockPos(n, n3 - 2.0, n2);
            }
            this.pos = pos;
            if (Scaffold.mc.world.getBlockState(pos).getBlock() == Blocks.AIR) {
                blockData2 = this.getBlockData2(pos);
            }
            if (blockData2 != null) {
                if (this.getBlockCountHotbar() <= 0 || (!this.bSwitch.getValue() && !(Scaffold.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock))) {
                    return;
                }
                final int currentItem = Scaffold.mc.player.inventory.currentItem;
                if (this.bSwitch.getValue()) {
                    for (int j = 0; j < 9; ++j) {
                        Scaffold.mc.player.inventory.getStackInSlot(j);
                        if (Scaffold.mc.player.inventory.getStackInSlot(j).getCount() != 0 && Scaffold.mc.player.inventory.getStackInSlot(j).getItem() instanceof ItemBlock && !this.invalid.contains(((ItemBlock)Scaffold.mc.player.inventory.getStackInSlot(j).getItem()).getBlock())) {
                            Scaffold.mc.player.inventory.currentItem = j;
                            break;
                        }
                    }
                }
                if (this.mode.getValue() == Mode.Legit) {
                    if (Scaffold.mc.gameSettings.keyBindJump.isKeyDown() && Scaffold.mc.player.moveForward == 0.0f && Scaffold.mc.player.moveStrafing == 0.0f && !Scaffold.mc.player.isPotionActive(MobEffects.JUMP_BOOST)) {
                        if (!this.teleported && this.center.getValue()) {
                            this.teleported = true;
                            final BlockPos blockPos = new BlockPos(Scaffold.mc.player.posX, Scaffold.mc.player.posY, Scaffold.mc.player.posZ);
                            Scaffold.mc.player.setPosition(blockPos.getX() + 0.5, (double)blockPos.getY(), blockPos.getZ() + 0.5);
                        }
                        if (this.center.getValue() && !this.teleported) {
                            return;
                        }
                        Scaffold.mc.player.motionY = 0.41999998688697815;
                        Scaffold.mc.player.motionZ = 0.0;
                        Scaffold.mc.player.motionX = 0.0;
                        if (this.timerMotion.sleep(1500L)) {
                            Scaffold.mc.player.motionY = -0.28;
                        }
                    }
                    else {
                        this.timerMotion.reset();
                        if (this.teleported && this.center.getValue()) {
                            this.teleported = false;
                        }
                    }
                }
                if (Scaffold.mc.playerController.processRightClickBlock(Scaffold.mc.player, Scaffold.mc.world, blockData2.position, blockData2.face, new Vec3d(blockData2.position.getX() + Math.random(), blockData2.position.getY() + Math.random(), blockData2.position.getZ() + Math.random()), EnumHand.MAIN_HAND) != EnumActionResult.FAIL) {
                    if (this.swing.getValue()) {
                        Scaffold.mc.player.swingArm(EnumHand.MAIN_HAND);
                    }
                    else {
                        Scaffold.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.MAIN_HAND));
                    }
                }
                Scaffold.mc.player.inventory.currentItem = currentItem;
            }
        }
    }
    
    public double[] getExpandCoords(final double n, final double n2, final double n3, final double n4, final float n5) {
        Block block = Scaffold.mc.world.getBlockState(new BlockPos(n, Scaffold.mc.player.posY - ((Scaffold.mc.gameSettings.keyBindSneak.isKeyDown() && this.down.getValue()) ? 2 : 1), n2)).getBlock();
        double n6 = -999.0;
        double n7 = -999.0;
        double n8 = 0.0;
        final double n9 = this.expand.getValue() * 2.0f;
        while (!this.canPlace(block)) {
            if (++n8 > n9) {
                n8 = n9;
            }
            n6 = n + (n3 * 0.45 * Math.cos(Math.toRadians(n5 + 90.0f)) + n4 * 0.45 * Math.sin(Math.toRadians(n5 + 90.0f))) * n8;
            n7 = n2 + (n3 * 0.45 * Math.sin(Math.toRadians(n5 + 90.0f)) - n4 * 0.45 * Math.cos(Math.toRadians(n5 + 90.0f))) * n8;
            if (n8 == n9) {
                break;
            }
            block = Scaffold.mc.world.getBlockState(new BlockPos(n6, Scaffold.mc.player.posY - ((Scaffold.mc.gameSettings.keyBindSneak.isKeyDown() && this.down.getValue()) ? 2 : 1), n7)).getBlock();
        }
        return new double[] { n6, n7 };
    }
    
    private int getBlockCountHotbar() {
        int n = 0;
        for (int i = 36; i < 45; ++i) {
            if (Scaffold.mc.player.inventoryContainer.getSlot(i).getHasStack()) {
                final ItemStack getStack = Scaffold.mc.player.inventoryContainer.getSlot(i).getStack();
                final Item getItem = getStack.getItem();
                if (getStack.getItem() instanceof ItemBlock) {
                    if (!this.invalid.contains(((ItemBlock)getItem).getBlock())) {
                        n += getStack.getCount();
                    }
                }
            }
        }
        return n;
    }
    
    private BlockData getBlockData2(final BlockPos blockPos) {
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(0, -1, 0)).getBlock())) {
            return new BlockData(blockPos.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(-1, 0, 0)).getBlock())) {
            return new BlockData(blockPos.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(1, 0, 0)).getBlock())) {
            return new BlockData(blockPos.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(0, 0, 1)).getBlock())) {
            return new BlockData(blockPos.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(0, 0, -1)).getBlock())) {
            return new BlockData(blockPos.add(0, 0, -1), EnumFacing.SOUTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock())) {
            return new BlockData(blockPos.add(0, 1, 0), EnumFacing.DOWN);
        }
        final BlockPos add = blockPos.add(-1, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, -1, 0)).getBlock())) {
            return new BlockData(add.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 1, 0)).getBlock())) {
            return new BlockData(add.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(1, 0, 0)).getBlock())) {
            return new BlockData(add.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 0, 1)).getBlock())) {
            return new BlockData(add.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 0, -1)).getBlock())) {
            return new BlockData(add.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add2 = blockPos.add(1, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, -1, 0)).getBlock())) {
            return new BlockData(add2.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 1, 0)).getBlock())) {
            return new BlockData(add2.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add2.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(1, 0, 0)).getBlock())) {
            return new BlockData(add2.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 0, 1)).getBlock())) {
            return new BlockData(add2.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 0, -1)).getBlock())) {
            return new BlockData(add2.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add3 = blockPos.add(0, 0, 1);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, -1, 0)).getBlock())) {
            return new BlockData(add3.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 1, 0)).getBlock())) {
            return new BlockData(add3.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add3.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(1, 0, 0)).getBlock())) {
            return new BlockData(add3.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 0, 1)).getBlock())) {
            return new BlockData(add3.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 0, -1)).getBlock())) {
            return new BlockData(add3.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add4 = blockPos.add(0, 0, -1);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, -1, 0)).getBlock())) {
            return new BlockData(add4.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 1, 0)).getBlock())) {
            return new BlockData(add4.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add4.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(1, 0, 0)).getBlock())) {
            return new BlockData(add4.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 0, 1)).getBlock())) {
            return new BlockData(add4.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 0, -1)).getBlock())) {
            return new BlockData(add4.add(0, 0, -1), EnumFacing.SOUTH);
        }
        blockPos.add(-2, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, -1, 0)).getBlock())) {
            return new BlockData(add.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 1, 0)).getBlock())) {
            return new BlockData(add.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(1, 0, 0)).getBlock())) {
            return new BlockData(add.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 0, 1)).getBlock())) {
            return new BlockData(add.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add.add(0, 0, -1)).getBlock())) {
            return new BlockData(add.add(0, 0, -1), EnumFacing.SOUTH);
        }
        blockPos.add(2, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, -1, 0)).getBlock())) {
            return new BlockData(add2.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 1, 0)).getBlock())) {
            return new BlockData(add2.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add2.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(1, 0, 0)).getBlock())) {
            return new BlockData(add2.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 0, 1)).getBlock())) {
            return new BlockData(add2.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add2.add(0, 0, -1)).getBlock())) {
            return new BlockData(add2.add(0, 0, -1), EnumFacing.SOUTH);
        }
        blockPos.add(0, 0, 2);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, -1, 0)).getBlock())) {
            return new BlockData(add3.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 1, 0)).getBlock())) {
            return new BlockData(add3.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add3.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(1, 0, 0)).getBlock())) {
            return new BlockData(add3.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 0, 1)).getBlock())) {
            return new BlockData(add3.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add3.add(0, 0, -1)).getBlock())) {
            return new BlockData(add3.add(0, 0, -1), EnumFacing.SOUTH);
        }
        blockPos.add(0, 0, -2);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, -1, 0)).getBlock())) {
            return new BlockData(add4.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 1, 0)).getBlock())) {
            return new BlockData(add4.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add4.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(1, 0, 0)).getBlock())) {
            return new BlockData(add4.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 0, 1)).getBlock())) {
            return new BlockData(add4.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add4.add(0, 0, -1)).getBlock())) {
            return new BlockData(add4.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add5 = blockPos.add(0, -1, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(0, -1, 0)).getBlock())) {
            return new BlockData(add5.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(0, 1, 0)).getBlock())) {
            return new BlockData(add5.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add5.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(1, 0, 0)).getBlock())) {
            return new BlockData(add5.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(0, 0, 1)).getBlock())) {
            return new BlockData(add5.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add5.add(0, 0, -1)).getBlock())) {
            return new BlockData(add5.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add6 = add5.add(1, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(0, -1, 0)).getBlock())) {
            return new BlockData(add6.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(0, 1, 0)).getBlock())) {
            return new BlockData(add6.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add6.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(1, 0, 0)).getBlock())) {
            return new BlockData(add6.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(0, 0, 1)).getBlock())) {
            return new BlockData(add6.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add6.add(0, 0, -1)).getBlock())) {
            return new BlockData(add6.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add7 = add5.add(-1, 0, 0);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(0, -1, 0)).getBlock())) {
            return new BlockData(add7.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(0, 1, 0)).getBlock())) {
            return new BlockData(add7.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add7.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(1, 0, 0)).getBlock())) {
            return new BlockData(add7.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(0, 0, 1)).getBlock())) {
            return new BlockData(add7.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add7.add(0, 0, -1)).getBlock())) {
            return new BlockData(add7.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add8 = add5.add(0, 0, 1);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(0, -1, 0)).getBlock())) {
            return new BlockData(add8.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add8.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(0, 1, 0)).getBlock())) {
            return new BlockData(add8.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(1, 0, 0)).getBlock())) {
            return new BlockData(add8.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(0, 0, 1)).getBlock())) {
            return new BlockData(add8.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add8.add(0, 0, -1)).getBlock())) {
            return new BlockData(add8.add(0, 0, -1), EnumFacing.SOUTH);
        }
        final BlockPos add9 = add5.add(0, 0, -1);
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(0, -1, 0)).getBlock())) {
            return new BlockData(add9.add(0, -1, 0), EnumFacing.UP);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(0, 1, 0)).getBlock())) {
            return new BlockData(add9.add(0, 1, 0), EnumFacing.DOWN);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(-1, 0, 0)).getBlock())) {
            return new BlockData(add9.add(-1, 0, 0), EnumFacing.EAST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(1, 0, 0)).getBlock())) {
            return new BlockData(add9.add(1, 0, 0), EnumFacing.WEST);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(0, 0, 1)).getBlock())) {
            return new BlockData(add9.add(0, 0, 1), EnumFacing.NORTH);
        }
        if (!this.invalid.contains(Scaffold.mc.world.getBlockState(add9.add(0, 0, -1)).getBlock())) {
            return new BlockData(add9.add(0, 0, -1), EnumFacing.SOUTH);
        }
        return null;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerPost(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.mode.getValue() == Mode.Fast) {
            if (this.isOff() || fullNullCheck() || updateWalkingPlayerEvent.getStage() == 0) {
                return;
            }
            if (!Scaffold.mc.gameSettings.keyBindJump.isKeyDown()) {
                this.timer.reset();
            }
            final BlockPos playerPosWithEntity;
            if (BlockUtil.isScaffoldPos((playerPosWithEntity = EntityUtil.getPlayerPosWithEntity()).add(0, -1, 0))) {
                if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -2, 0))) {
                    this.place(playerPosWithEntity.add(0, -1, 0), EnumFacing.UP);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(-1, -1, 0))) {
                    this.place(playerPosWithEntity.add(0, -1, 0), EnumFacing.EAST);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(1, -1, 0))) {
                    this.place(playerPosWithEntity.add(0, -1, 0), EnumFacing.WEST);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -1, -1))) {
                    this.place(playerPosWithEntity.add(0, -1, 0), EnumFacing.SOUTH);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -1, 1))) {
                    this.place(playerPosWithEntity.add(0, -1, 0), EnumFacing.NORTH);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(1, -1, 1))) {
                    if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -1, 1))) {
                        this.place(playerPosWithEntity.add(0, -1, 1), EnumFacing.NORTH);
                    }
                    this.place(playerPosWithEntity.add(1, -1, 1), EnumFacing.EAST);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(-1, -1, 1))) {
                    if (BlockUtil.isValidBlock(playerPosWithEntity.add(-1, -1, 0))) {
                        this.place(playerPosWithEntity.add(0, -1, 1), EnumFacing.WEST);
                    }
                    this.place(playerPosWithEntity.add(-1, -1, 1), EnumFacing.SOUTH);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(1, -1, 1))) {
                    if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -1, 1))) {
                        this.place(playerPosWithEntity.add(0, -1, 1), EnumFacing.SOUTH);
                    }
                    this.place(playerPosWithEntity.add(1, -1, 1), EnumFacing.WEST);
                }
                else if (BlockUtil.isValidBlock(playerPosWithEntity.add(1, -1, 1))) {
                    if (BlockUtil.isValidBlock(playerPosWithEntity.add(0, -1, 1))) {
                        this.place(playerPosWithEntity.add(0, -1, 1), EnumFacing.EAST);
                    }
                    this.place(playerPosWithEntity.add(1, -1, 1), EnumFacing.NORTH);
                }
            }
        }
    }
    
    public enum Mode
    {
        Fast, 
        Legit;
    }
    
    private static class BlockData
    {
        public /* synthetic */ BlockPos position;
        public /* synthetic */ EnumFacing face;
        
        public BlockData(final BlockPos position, final EnumFacing face) {
            this.position = position;
            this.face = face;
        }
    }
}

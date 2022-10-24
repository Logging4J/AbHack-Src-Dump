//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import com.google.common.util.concurrent.*;
import net.minecraft.entity.player.*;
import java.util.concurrent.atomic.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import me.abHack.util.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import net.minecraft.tileentity.*;
import java.util.*;
import net.minecraft.util.math.*;
import net.minecraft.block.state.*;

public class BedAura extends Module
{
    private final /* synthetic */ Setting<Boolean> explode;
    private /* synthetic */ BlockPos finalPos;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Boolean> oneDot15;
    private final /* synthetic */ Setting<Float> minDamage;
    private final /* synthetic */ Setting<BreakLogic> breakMode;
    private final /* synthetic */ Setting<Float> breakRange;
    private /* synthetic */ boolean sendRotationPacket;
    private final /* synthetic */ Timer breakTimer;
    private final /* synthetic */ Setting<Float> placeRange;
    private final /* synthetic */ Setting<Logic> logic;
    private /* synthetic */ int bedSlot;
    private final /* synthetic */ Setting<Boolean> place;
    private final /* synthetic */ AtomicDouble yaw;
    private final /* synthetic */ Setting<Boolean> suicide;
    private /* synthetic */ EntityPlayer target;
    private final /* synthetic */ AtomicDouble pitch;
    private /* synthetic */ BlockPos maxPos;
    private final /* synthetic */ Timer placeTimer;
    private final /* synthetic */ Setting<Integer> breakDelay;
    private final /* synthetic */ Setting<Boolean> extraPacket;
    private final /* synthetic */ Setting<SwitchModes> switchMode;
    private final /* synthetic */ Setting<Boolean> dimensionCheck;
    private final /* synthetic */ Setting<Float> range;
    private /* synthetic */ int lastHotbarSlot;
    private final /* synthetic */ Setting<Boolean> removeTiles;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ AtomicBoolean shouldRotate;
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ EnumFacing finalFacing;
    
    @Override
    public void onToggle() {
        this.lastHotbarSlot = -1;
        this.bedSlot = -1;
        this.sendRotationPacket = false;
        this.target = null;
        this.yaw.set(-1.0);
        this.pitch.set(-1.0);
        this.shouldRotate.set(false);
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    public void recheckBedSlots(final int n, final int n2) {
        for (int i = 1; i <= 3; ++i) {
            if (BedAura.mc.player.openContainer.getInventory().get(i) == ItemStack.EMPTY) {
                BedAura.mc.playerController.windowClick(1, n, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                BedAura.mc.playerController.windowClick(1, i, 1, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                BedAura.mc.playerController.windowClick(1, n, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
            }
        }
        for (int j = 4; j <= 6; ++j) {
            if (BedAura.mc.player.openContainer.getInventory().get(j) == ItemStack.EMPTY) {
                BedAura.mc.playerController.windowClick(1, n2, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                BedAura.mc.playerController.windowClick(1, j, 1, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
                BedAura.mc.playerController.windowClick(1, n2, 0, ClickType.PICKUP, (EntityPlayer)BedAura.mc.player);
            }
        }
    }
    
    @Override
    public void onEnable() {
    }
    
    private int safety(final BlockPos blockPos) {
        int n = 0;
        final EnumFacing[] values = EnumFacing.values();
        for (int length = values.length, i = 0; i < length; ++i) {
            if (!BedAura.mc.world.getBlockState(blockPos.offset(values[i])).getMaterial().isReplaceable()) {
                ++n;
            }
        }
        return n;
    }
    
    @SubscribeEvent
    public void onPacket(final PacketEvent.Send send) {
        if (this.shouldRotate.get() && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = (float)this.yaw.get();
            cPacketPlayer.pitch = (float)this.pitch.get();
            this.shouldRotate.set(false);
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0) {
            this.doBedAura();
        }
        else if (updateWalkingPlayerEvent.getStage() == 1 && this.finalPos != null) {
            final Vec3d add = new Vec3d((Vec3i)this.finalPos.down()).add(0.5, 0.5, 0.5).add(new Vec3d(this.finalFacing.getOpposite().getDirectionVec()).scale(0.5));
            BedAura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BedAura.mc.player, CPacketEntityAction.Action.START_SNEAKING));
            switch (this.switchMode.getValue()) {
                case NORMAL: {
                    InventoryUtil.switchToHotbarSlot(this.bedSlot, false);
                    break;
                }
            }
            if (InventoryUtil.getItemHotbar(Items.BED) != -1) {
                if (this.switchMode.getValue() == SwitchModes.SILENT) {
                    final int currentItem = BedAura.mc.player.inventory.currentItem;
                    this.switchToSlot(this.bedSlot);
                    BlockUtil.rightClickBlock(this.finalPos.down(), add, (this.bedSlot == -2) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, EnumFacing.UP, this.packet.getValue());
                    BedAura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BedAura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                    this.switchToSlot(currentItem);
                }
                else {
                    BlockUtil.rightClickBlock(this.finalPos.down(), add, (this.bedSlot == -2) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, EnumFacing.UP, this.packet.getValue());
                    BedAura.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BedAura.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
                }
            }
            this.placeTimer.reset();
            this.finalPos = null;
        }
    }
    
    public BedAura() {
        super("BedAura", "AutoPlace and Break for beds", Category.COMBAT, true, false, false);
        this.place = (Setting<Boolean>)this.register(new Setting("Place", (T)true));
        this.placeDelay = (Setting<Integer>)this.register(new Setting("Placedelay", (T)50, (T)0, (T)500, p0 -> this.place.getValue()));
        this.placeRange = (Setting<Float>)this.register(new Setting("PlaceRange", (T)6.0f, (T)1.0f, (T)10.0f, p0 -> this.place.getValue()));
        this.extraPacket = (Setting<Boolean>)this.register(new Setting("InsanePacket", (T)false, p0 -> this.place.getValue()));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)false, p0 -> this.place.getValue()));
        this.explode = (Setting<Boolean>)this.register(new Setting("Break", (T)true));
        this.breakMode = (Setting<BreakLogic>)this.register(new Setting("BreakMode", (T)BreakLogic.ALL, p0 -> this.explode.getValue()));
        this.breakDelay = (Setting<Integer>)this.register(new Setting("Breakdelay", (T)50, (T)0, (T)500, p0 -> this.explode.getValue()));
        this.breakRange = (Setting<Float>)this.register(new Setting("BreakRange", (T)6.0f, (T)1.0f, (T)10.0f, p0 -> this.explode.getValue()));
        this.minDamage = (Setting<Float>)this.register(new Setting("MinDamage", (T)5.0f, (T)1.0f, (T)36.0f, p0 -> this.explode.getValue()));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)10.0f, (T)1.0f, (T)12.0f, p0 -> this.explode.getValue()));
        this.suicide = (Setting<Boolean>)this.register(new Setting("Suicide", (T)false, p0 -> this.explode.getValue()));
        this.removeTiles = (Setting<Boolean>)this.register(new Setting("RemoveTiles", (T)false));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.oneDot15 = (Setting<Boolean>)this.register(new Setting("1.15", (T)false));
        this.dimensionCheck = (Setting<Boolean>)this.register(new Setting("Dimension Check", (T)true));
        this.switchMode = (Setting<SwitchModes>)this.register(new Setting("Switch Mode", (T)SwitchModes.SILENT));
        this.logic = (Setting<Logic>)this.register(new Setting("Logic", (T)Logic.BREAKPLACE, p0 -> this.place.getValue() && this.explode.getValue()));
        this.breakTimer = new Timer();
        this.placeTimer = new Timer();
        this.yaw = new AtomicDouble(-1.0);
        this.pitch = new AtomicDouble(-1.0);
        this.shouldRotate = new AtomicBoolean(false);
        this.target = null;
        this.sendRotationPacket = false;
        this.maxPos = null;
        this.lastHotbarSlot = -1;
        this.bedSlot = -1;
    }
    
    private void placeBed(final BlockPos blockPos2, final boolean b) {
        if (BedAura.mc.world.getBlockState(blockPos2).getBlock() == Blocks.BED) {
            return;
        }
        if (DamageUtil.calculateDamage(blockPos2, (Entity)BedAura.mc.player) > EntityUtil.getHealth((Entity)BedAura.mc.player) + 0.5) {
            if (b && this.oneDot15.getValue()) {
                this.placeBed(blockPos2.up(), false);
            }
            return;
        }
        if (!BedAura.mc.world.getBlockState(blockPos2).getMaterial().isReplaceable()) {
            if (b && this.oneDot15.getValue()) {
                this.placeBed(blockPos2.up(), false);
            }
            return;
        }
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        final HashMap<BlockPos, EnumFacing> hashMap = new HashMap<BlockPos, EnumFacing>();
        for (final EnumFacing enumFacing : EnumFacing.values()) {
            final BlockPos offset;
            if (enumFacing != EnumFacing.DOWN && enumFacing != EnumFacing.UP && BedAura.mc.player.getDistanceSq(offset = blockPos2.offset(enumFacing)) <= MathUtil.square(this.placeRange.getValue()) && BedAura.mc.world.getBlockState(offset).getMaterial().isReplaceable() && !BedAura.mc.world.getBlockState(offset.down()).getMaterial().isReplaceable()) {
                list.add(offset);
                hashMap.put(offset, enumFacing.getOpposite());
            }
        }
        if (list.isEmpty()) {
            if (b && this.oneDot15.getValue()) {
                this.placeBed(blockPos2.up(), false);
            }
            return;
        }
        list.sort(Comparator.comparingDouble(blockPos -> BedAura.mc.player.getDistanceSq(blockPos)));
        this.finalPos = list.get(0);
        this.finalFacing = hashMap.get(this.finalPos);
        final float[] simpleFacing = RotationUtil.simpleFacing(this.finalFacing);
        if (!this.sendRotationPacket && this.extraPacket.getValue()) {
            RotationUtil.faceYawAndPitch(simpleFacing[0], simpleFacing[1]);
            this.sendRotationPacket = true;
        }
        this.yaw.set((double)simpleFacing[0]);
        this.pitch.set((double)simpleFacing[1]);
        this.shouldRotate.set(true);
        OyVey.rotationManager.setPlayerRotations(simpleFacing[0], simpleFacing[1]);
    }
    
    @Override
    public void onUpdate() {
        if (this.dimensionCheck.getValue() && BedAura.mc.player.dimension == 0) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("<WorldCheck> You are in the Overworld! Toggling Off!")));
            this.disable();
        }
    }
    
    private void mapBeds() {
        this.maxPos = null;
        float n = 0.5f;
        if (this.removeTiles.getValue()) {
            final ArrayList<BedData> list = new ArrayList<BedData>();
            for (final TileEntity tileEntity : BedAura.mc.world.loadedTileEntityList) {
                if (!(tileEntity instanceof TileEntityBed)) {
                    continue;
                }
                final TileEntityBed tileEntityBed = (TileEntityBed)tileEntity;
                list.add(new BedData(tileEntity.getPos(), BedAura.mc.world.getBlockState(tileEntity.getPos()), tileEntityBed, tileEntityBed.isHeadPiece()));
            }
            final Iterator<BedData> iterator2 = list.iterator();
            while (iterator2.hasNext()) {
                BedAura.mc.world.setBlockToAir(iterator2.next().getPos());
            }
            for (final BedData bedData : list) {
                final BlockPos pos;
                if (bedData.isHeadPiece() && BedAura.mc.player.getDistanceSq(pos = bedData.getPos()) <= MathUtil.square(this.breakRange.getValue())) {
                    final float calculateDamage;
                    if ((calculateDamage = DamageUtil.calculateDamage(pos, (Entity)BedAura.mc.player)) + 1.0 >= EntityUtil.getHealth((Entity)BedAura.mc.player) && DamageUtil.canTakeDamage(this.suicide.getValue())) {
                        continue;
                    }
                    for (final EntityPlayer entityPlayer : BedAura.mc.world.playerEntities) {
                        final float calculateDamage2;
                        if (entityPlayer.getDistanceSq(pos) < MathUtil.square(this.range.getValue()) && EntityUtil.isValid((Entity)entityPlayer, this.range.getValue() + this.breakRange.getValue()) && ((calculateDamage2 = DamageUtil.calculateDamage(pos, (Entity)entityPlayer)) > calculateDamage || (calculateDamage2 > this.minDamage.getValue() && !DamageUtil.canTakeDamage(this.suicide.getValue())) || calculateDamage2 > EntityUtil.getHealth((Entity)entityPlayer))) {
                            if (calculateDamage2 <= n) {
                                continue;
                            }
                            n = calculateDamage2;
                            this.maxPos = pos;
                        }
                    }
                }
            }
            for (final BedData bedData2 : list) {
                BedAura.mc.world.setBlockState(bedData2.getPos(), bedData2.getState());
            }
        }
        else {
            for (final TileEntity tileEntity2 : BedAura.mc.world.loadedTileEntityList) {
                final TileEntityBed tileEntityBed2;
                final BlockPos getPos;
                if (tileEntity2 instanceof TileEntityBed && (tileEntityBed2 = (TileEntityBed)tileEntity2).isHeadPiece() && BedAura.mc.player.getDistanceSq(getPos = tileEntityBed2.getPos()) <= MathUtil.square(this.breakRange.getValue())) {
                    final float calculateDamage3;
                    if ((calculateDamage3 = DamageUtil.calculateDamage(getPos, (Entity)BedAura.mc.player)) + 1.0 >= EntityUtil.getHealth((Entity)BedAura.mc.player) && DamageUtil.canTakeDamage(this.suicide.getValue())) {
                        continue;
                    }
                    for (final EntityPlayer entityPlayer2 : BedAura.mc.world.playerEntities) {
                        final float calculateDamage4;
                        if (entityPlayer2.getDistanceSq(getPos) < MathUtil.square(this.range.getValue()) && EntityUtil.isValid((Entity)entityPlayer2, this.range.getValue() + this.breakRange.getValue()) && ((calculateDamage4 = DamageUtil.calculateDamage(getPos, (Entity)entityPlayer2)) > calculateDamage3 || (calculateDamage4 > this.minDamage.getValue() && !DamageUtil.canTakeDamage(this.suicide.getValue())) || calculateDamage4 > EntityUtil.getHealth((Entity)entityPlayer2))) {
                            if (calculateDamage4 <= n) {
                                continue;
                            }
                            n = calculateDamage4;
                            this.maxPos = getPos;
                        }
                    }
                }
            }
        }
    }
    
    private void switchToSlot(final int currentItem) {
        BedAura.mc.player.inventory.currentItem = currentItem;
        BedAura.mc.playerController.updateController();
    }
    
    @Override
    public void onDisable() {
    }
    
    private int findBedSlot() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BedAura.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() == Items.BED) {
                return i;
            }
        }
        return -1;
    }
    
    private void doBedAura() {
        switch (this.logic.getValue()) {
            case BREAKPLACE: {
                this.mapBeds();
                this.breakBeds();
                this.placeBeds();
                break;
            }
            case PLACEBREAK: {
                this.mapBeds();
                this.placeBeds();
                this.breakBeds();
                break;
            }
        }
    }
    
    private void breakBeds() {
        if (this.explode.getValue() && this.breakTimer.passedMs(this.breakDelay.getValue())) {
            if (this.breakMode.getValue() == BreakLogic.CALC) {
                if (this.maxPos != null) {
                    final Vec3d add = new Vec3d((Vec3i)this.maxPos).add(0.5, 0.5, 0.5);
                    final float[] legitRotations = RotationUtil.getLegitRotations(add);
                    this.yaw.set((double)legitRotations[0]);
                    if (this.rotate.getValue()) {
                        this.shouldRotate.set(true);
                        this.pitch.set((double)legitRotations[1]);
                    }
                    final RayTraceResult rayTraceBlocks;
                    BlockUtil.rightClickBlock(this.maxPos, add, EnumHand.MAIN_HAND, ((rayTraceBlocks = BedAura.mc.world.rayTraceBlocks(new Vec3d(BedAura.mc.player.posX, BedAura.mc.player.posY + BedAura.mc.player.getEyeHeight(), BedAura.mc.player.posZ), new Vec3d(this.maxPos.getX() + 0.5, this.maxPos.getY() - 0.5, this.maxPos.getZ() + 0.5))) == null || rayTraceBlocks.sideHit == null) ? EnumFacing.UP : rayTraceBlocks.sideHit, true);
                    this.breakTimer.reset();
                }
            }
            else {
                for (final TileEntity tileEntity : BedAura.mc.world.loadedTileEntityList) {
                    if (tileEntity instanceof TileEntityBed) {
                        if (BedAura.mc.player.getDistanceSq(tileEntity.getPos()) > MathUtil.square(this.breakRange.getValue())) {
                            continue;
                        }
                        final Vec3d add2 = new Vec3d((Vec3i)tileEntity.getPos()).add(0.5, 0.5, 0.5);
                        final float[] legitRotations2 = RotationUtil.getLegitRotations(add2);
                        this.yaw.set((double)legitRotations2[0]);
                        if (this.rotate.getValue()) {
                            this.shouldRotate.set(true);
                            this.pitch.set((double)legitRotations2[1]);
                        }
                        final RayTraceResult rayTraceBlocks2;
                        BlockUtil.rightClickBlock(tileEntity.getPos(), add2, EnumHand.MAIN_HAND, ((rayTraceBlocks2 = BedAura.mc.world.rayTraceBlocks(new Vec3d(BedAura.mc.player.posX, BedAura.mc.player.posY + BedAura.mc.player.getEyeHeight(), BedAura.mc.player.posZ), new Vec3d(tileEntity.getPos().getX() + 0.5, tileEntity.getPos().getY() - 0.5, tileEntity.getPos().getZ() + 0.5))) == null || rayTraceBlocks2.sideHit == null) ? EnumFacing.UP : rayTraceBlocks2.sideHit, true);
                        this.breakTimer.reset();
                    }
                }
            }
        }
    }
    
    private void placeBeds() {
        if (this.place.getValue() && this.placeTimer.passedMs(this.placeDelay.getValue()) && this.maxPos == null) {
            this.bedSlot = this.findBedSlot();
            if (this.bedSlot == -1 && BedAura.mc.player.getHeldItemOffhand().getItem() == Items.BED) {
                this.bedSlot = -2;
            }
            this.lastHotbarSlot = BedAura.mc.player.inventory.currentItem;
            this.target = EntityUtil.getClosestEnemy(this.placeRange.getValue());
            if (this.target != null) {
                this.placeBed(new BlockPos(this.target.getPositionVector()), true);
            }
        }
    }
    
    public enum SwitchModes
    {
        NORMAL, 
        SILENT;
    }
    
    public static class BedData
    {
        private final /* synthetic */ boolean isHeadPiece;
        private final /* synthetic */ BlockPos pos;
        private final /* synthetic */ IBlockState state;
        private final /* synthetic */ TileEntityBed entity;
        
        public BlockPos getPos() {
            return this.pos;
        }
        
        public TileEntityBed getEntity() {
            return this.entity;
        }
        
        public BedData(final BlockPos pos, final IBlockState state, final TileEntityBed entity, final boolean isHeadPiece) {
            this.pos = pos;
            this.state = state;
            this.entity = entity;
            this.isHeadPiece = isHeadPiece;
        }
        
        public IBlockState getState() {
            return this.state;
        }
        
        public boolean isHeadPiece() {
            return this.isHeadPiece;
        }
    }
    
    public enum Logic
    {
        PLACEBREAK, 
        BREAKPLACE;
    }
    
    public enum BreakLogic
    {
        CALC, 
        ALL;
    }
}

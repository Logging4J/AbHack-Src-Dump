//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.*;
import java.util.stream.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import me.abHack.util.*;
import net.minecraft.block.*;
import me.abHack.features.command.*;

public class Surround extends Module
{
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> breakcrystal;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> noGhost;
    private /* synthetic */ int extenders;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private /* synthetic */ BlockPos startPos;
    private /* synthetic */ int lastHotbarSlot;
    private /* synthetic */ int obbySlot;
    public static /* synthetic */ boolean isPlacing;
    private /* synthetic */ boolean offHand;
    private final /* synthetic */ Setting<Boolean> center;
    private final /* synthetic */ Setting<Double> safehealth;
    private /* synthetic */ int placements;
    private final /* synthetic */ Set<Vec3d> extendingBlocks;
    private final /* synthetic */ Timer retryTimer;
    private /* synthetic */ int isSafe;
    private /* synthetic */ boolean isSneaking;
    private /* synthetic */ boolean didPlace;
    private final /* synthetic */ Map<BlockPos, Integer> retries;
    
    @Override
    public String getDisplayInfo() {
        switch (this.isSafe) {
            case 0: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Unsafe"));
            }
            case 1: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.YELLOW).append("Safe"));
            }
            default: {
                return String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("Safe"));
            }
        }
    }
    
    private boolean placeBlocks(final Vec3d vec3d, final Vec3d[] array, final boolean b, final boolean b2, final boolean b3) {
        for (final Vec3d vec3d2 : array) {
            boolean placeBlocks = true;
            final BlockPos add = new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z);
            switch (BlockUtil.isPositionPlaceable(add, false)) {
                case 1: {
                    if (this.retries.get(add) == null || this.retries.get(add) < 4) {
                        this.placeBlock(add);
                        this.retries.put(add, (this.retries.get(add) == null) ? 1 : (this.retries.get(add) + 1));
                        this.retryTimer.reset();
                        break;
                    }
                    if (OyVey.speedManager.getSpeedKpH() != 0.0 || b3) {
                        break;
                    }
                    if (this.extenders >= 1) {
                        break;
                    }
                    this.placeBlocks(Surround.mc.player.getPositionVector().add(vec3d2), EntityUtil.getUnsafeBlockArrayFromVec3d(Surround.mc.player.getPositionVector().add(vec3d2), 0, true), b, false, true);
                    this.extendingBlocks.add(vec3d2);
                    ++this.extenders;
                    break;
                }
                case 2: {
                    if (!b) {
                        break;
                    }
                    placeBlocks = this.placeBlocks(vec3d, BlockUtil.getHelpingBlocks(vec3d2), false, true, true);
                }
                case 3: {
                    if (placeBlocks) {
                        this.placeBlock(add);
                    }
                    if (!b2) {
                        break;
                    }
                    return true;
                }
            }
        }
        return false;
    }
    
    @Override
    public void onDisable() {
        if (nullCheck()) {
            return;
        }
        Surround.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    public static void breakcrystal() {
        for (final Entity entity3 : (List)Surround.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal && !entity.isDead).sorted(Comparator.comparing(entity2 -> Surround.mc.player.getDistance(entity2))).collect(Collectors.toList())) {
            if (entity3 instanceof EntityEnderCrystal && Surround.mc.player.getDistance(entity3) <= 4.0f) {
                Surround.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity3));
                Surround.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
            }
        }
    }
    
    private Vec3d areClose(final Vec3d[] array) {
        int n = 0;
        for (final Vec3d vec3d : array) {
            final Vec3d[] unsafeBlockArray = EntityUtil.getUnsafeBlockArray((Entity)Surround.mc.player, 0, true);
            for (int length2 = unsafeBlockArray.length, j = 0; j < length2; ++j) {
                if (vec3d.equals((Object)unsafeBlockArray[j])) {
                    ++n;
                }
            }
        }
        if (n == 2) {
            return Surround.mc.player.getPositionVector().add(array[0].add(array[1]));
        }
        return null;
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
        }
        this.lastHotbarSlot = Surround.mc.player.inventory.currentItem;
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)Surround.mc.player);
        if (this.center.getValue()) {
            OyVey.positionManager.setPositionPacket(this.startPos.getX() + 0.5, this.startPos.getY(), this.startPos.getZ() + 0.5, true, true, true);
        }
        this.retries.clear();
        this.retryTimer.reset();
    }
    
    @Override
    public void onTick() {
        if (this.breakcrystal.getValue() && Surround.mc.player.getHealth() >= this.safehealth.getValue() && this.isSafe == 0) {
            breakcrystal();
        }
        this.doFeetPlace();
    }
    
    static {
        Surround.isPlacing = false;
    }
    
    public Surround() {
        super("Surround", "Surrounds you with Obsidian", Category.COMBAT, true, false, false);
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (T)12, (T)1, (T)20));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)0, (T)250));
        this.noGhost = (Setting<Boolean>)this.register(new Setting("PacketPlace", (T)true));
        this.breakcrystal = (Setting<Boolean>)this.register(new Setting("BreakCrystal", (T)true));
        this.safehealth = (Setting<Double>)this.register(new Setting("Safe Health", (T)12.5, (T)1.0, (T)36.0, p0 -> this.breakcrystal.getValue()));
        this.center = (Setting<Boolean>)this.register(new Setting("TPCenter", (T)true));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.timer = new Timer();
        this.retryTimer = new Timer();
        this.extendingBlocks = new HashSet<Vec3d>();
        this.retries = new HashMap<BlockPos, Integer>();
        this.didPlace = false;
        this.placements = 0;
        this.extenders = 1;
        this.obbySlot = -1;
        this.offHand = false;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < this.blocksPerTick.getValue()) {
            final int currentItem = Surround.mc.player.inventory.currentItem;
            final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int hotbarBlock2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (hotbarBlock == -1 && hotbarBlock2 == -1) {
                this.toggle();
            }
            Surround.isPlacing = true;
            Surround.mc.player.inventory.currentItem = ((hotbarBlock == -1) ? hotbarBlock2 : hotbarBlock);
            Surround.mc.playerController.updateController();
            this.isSneaking = BlockUtil.placeBlock(blockPos, this.offHand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.rotate.getValue(), this.noGhost.getValue(), this.isSneaking);
            Surround.mc.player.inventory.currentItem = currentItem;
            Surround.mc.playerController.updateController();
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    private boolean check() {
        if (nullCheck()) {
            return true;
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int hotbarBlock2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (hotbarBlock == -1 && hotbarBlock2 == -1) {
            this.toggle();
        }
        this.offHand = InventoryUtil.isBlock(Surround.mc.player.getHeldItemOffhand().getItem(), BlockObsidian.class);
        Surround.isPlacing = false;
        this.didPlace = false;
        this.extenders = 1;
        this.placements = 0;
        this.obbySlot = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int hotbarBlock3 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (this.isOff()) {
            return true;
        }
        if (this.retryTimer.passedMs(2500L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (this.obbySlot == -1 && !this.offHand && hotbarBlock3 == -1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> ").append(ChatFormatting.RED).append("No Obsidian in hotbar disabling...")));
            this.disable();
            return true;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        if (Surround.mc.player.inventory.currentItem != this.lastHotbarSlot && Surround.mc.player.inventory.currentItem != this.obbySlot && Surround.mc.player.inventory.currentItem != hotbarBlock3) {
            this.lastHotbarSlot = Surround.mc.player.inventory.currentItem;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)Surround.mc.player))) {
            this.disable();
            return true;
        }
        return !this.timer.passedMs(this.delay.getValue());
    }
    
    private void processExtendingBlocks() {
        if (this.extendingBlocks.size() == 2 && this.extenders < 1) {
            final Vec3d[] array = new Vec3d[2];
            int n = 0;
            final Iterator<Vec3d> iterator = this.extendingBlocks.iterator();
            while (iterator.hasNext()) {
                array[n] = iterator.next();
                ++n;
            }
            final int placements = this.placements;
            if (this.areClose(array) != null) {
                this.placeBlocks(this.areClose(array), EntityUtil.getUnsafeBlockArrayFromVec3d(this.areClose(array), 0, true), true, false, true);
            }
            if (placements < this.placements) {
                this.extendingBlocks.clear();
            }
        }
        else if (this.extendingBlocks.size() > 2 || this.extenders >= 1) {
            this.extendingBlocks.clear();
        }
    }
    
    private void doFeetPlace() {
        if (this.check()) {
            return;
        }
        if (!EntityUtil.isSafe((Entity)Surround.mc.player, 0, true)) {
            this.isSafe = 0;
            this.placeBlocks(Surround.mc.player.getPositionVector(), EntityUtil.getUnsafeBlockArray((Entity)Surround.mc.player, 0, true), true, false, false);
        }
        else if (!EntityUtil.isSafe((Entity)Surround.mc.player, -1, false)) {
            this.isSafe = 1;
            this.placeBlocks(Surround.mc.player.getPositionVector(), EntityUtil.getUnsafeBlockArray((Entity)Surround.mc.player, -1, false), false, false, true);
        }
        else {
            this.isSafe = 2;
        }
        this.processExtendingBlocks();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
}

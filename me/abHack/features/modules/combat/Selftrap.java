//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.block.*;
import net.minecraft.entity.*;
import me.abHack.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.*;
import me.abHack.util.*;
import net.minecraft.util.math.*;
import java.util.*;

public class Selftrap extends Module
{
    private /* synthetic */ int blocksThisTick;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Integer> disableTime;
    private /* synthetic */ boolean hasOffhand;
    private final /* synthetic */ Setting<Boolean> disable;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Timer retryTimer;
    private final /* synthetic */ Map<BlockPos, Integer> retries;
    private final /* synthetic */ Timer offTimer;
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            this.disable();
        }
        this.offTimer.reset();
    }
    
    @Override
    public void onTick() {
        if (this.isOn() && (this.blocksPerTick.getValue() != 1 || !this.rotate.getValue())) {
            this.doHoleFill();
        }
    }
    
    private boolean check() {
        if (fullNullCheck()) {
            this.disable();
            return true;
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        final int hotbarBlock2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
        if (hotbarBlock == -1 && hotbarBlock2 == -1) {
            this.toggle();
        }
        this.blocksThisTick = 0;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        if (this.retryTimer.passedMs(2000L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (!EntityUtil.isSafe((Entity)Selftrap.mc.player)) {
            this.offTimer.reset();
            return true;
        }
        if (this.disable.getValue() && this.offTimer.passedMs(this.disableTime.getValue())) {
            this.disable();
            return true;
        }
        return !this.timer.passedMs(this.delay.getValue());
    }
    
    @Override
    public void onDisable() {
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.retries.clear();
        this.hasOffhand = false;
    }
    
    public Selftrap() {
        super("Selftrap", "Lure your enemies in!", Category.COMBAT, true, false, true);
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (T)8, (T)1, (T)20));
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)0, (T)250));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.disableTime = (Setting<Integer>)this.register(new Setting("DisableTime", (T)300, (T)50, (T)300));
        this.disable = (Setting<Boolean>)this.register(new Setting("AutoDisable", (T)true));
        this.packet = (Setting<Boolean>)this.register(new Setting("PacketPlace", (T)true));
        this.offTimer = new Timer();
        this.timer = new Timer();
        this.retries = new HashMap<BlockPos, Integer>();
        this.retryTimer = new Timer();
        this.blocksThisTick = 0;
        this.hasOffhand = false;
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.isOn() && updateWalkingPlayerEvent.getStage() == 0 && this.blocksPerTick.getValue() == 1 && this.rotate.getValue()) {
            this.doHoleFill();
        }
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.blocksThisTick < this.blocksPerTick.getValue()) {
            final boolean b = this.blocksPerTick.getValue() == 1 && this.rotate.getValue();
            final int currentItem = Selftrap.mc.player.inventory.currentItem;
            final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int hotbarBlock2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (hotbarBlock == -1 && hotbarBlock2 == -1) {
                this.toggle();
            }
            Selftrap.mc.player.inventory.currentItem = ((hotbarBlock == -1) ? hotbarBlock2 : hotbarBlock);
            Selftrap.mc.playerController.updateController();
            this.isSneaking = (b ? BlockUtil.placeBlockSmartRotate(blockPos, this.hasOffhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, true, this.packet.getValue(), this.isSneaking) : BlockUtil.placeBlock(blockPos, this.hasOffhand ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.isSneaking));
            Selftrap.mc.player.inventory.currentItem = currentItem;
            Selftrap.mc.playerController.updateController();
            this.timer.reset();
            ++this.blocksThisTick;
        }
    }
    
    private List<BlockPos> getPositions() {
        final ArrayList<BlockPos> list = new ArrayList<BlockPos>();
        list.add(new BlockPos(Selftrap.mc.player.posX, Selftrap.mc.player.posY + 2.0, Selftrap.mc.player.posZ));
        switch (BlockUtil.isPositionPlaceable(list.get(0), false)) {
            case 0: {
                return new ArrayList<BlockPos>();
            }
            case 3: {
                return list;
            }
            case 1: {
                if (BlockUtil.isPositionPlaceable(list.get(0), false, false) == 3) {
                    return list;
                }
            }
            case 2: {
                list.add(new BlockPos(Selftrap.mc.player.posX + 1.0, Selftrap.mc.player.posY + 1.0, Selftrap.mc.player.posZ));
                list.add(new BlockPos(Selftrap.mc.player.posX + 1.0, Selftrap.mc.player.posY + 2.0, Selftrap.mc.player.posZ));
                break;
            }
        }
        list.sort(Comparator.comparingDouble(Vec3i::getY));
        return list;
    }
    
    private void doHoleFill() {
        if (this.check()) {
            return;
        }
        for (final BlockPos blockPos : this.getPositions()) {
            final int positionPlaceable = BlockUtil.isPositionPlaceable(blockPos, false);
            if (positionPlaceable == 1 && (this.retries.get(blockPos) == null || this.retries.get(blockPos) < 4)) {
                this.placeBlock(blockPos);
                this.retries.put(blockPos, (this.retries.get(blockPos) == null) ? 1 : (this.retries.get(blockPos) + 1));
            }
            if (positionPlaceable != 3) {
                continue;
            }
            this.placeBlock(blockPos);
        }
    }
}

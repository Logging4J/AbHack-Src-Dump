//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraft.util.math.*;
import java.awt.*;
import me.abHack.util.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.item.*;
import java.util.*;

public class AutoBuilder extends Module
{
    private final /* synthetic */ Setting<Switch> switchMode;
    private final /* synthetic */ Setting<Direction> stairDirection;
    private /* synthetic */ int blocksThisTick;
    private final /* synthetic */ Setting<Boolean> ground;
    private final /* synthetic */ Setting<Boolean> keepPos;
    private final /* synthetic */ Setting<Integer> oGreen;
    private final /* synthetic */ Setting<Updates> updates;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Boolean> allBlocks;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> bRed;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ List<BlockPos> placepositions;
    private final /* synthetic */ Setting<Integer> oRed;
    private final /* synthetic */ Setting<Integer> bBlue;
    private final /* synthetic */ Setting<Boolean> altRotate;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Settings> settings;
    private final /* synthetic */ Setting<Boolean> noMove;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> placeDelay;
    private final /* synthetic */ Setting<Integer> blocksPerTick;
    private final /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Setting<Integer> oBlue;
    private final /* synthetic */ Setting<Boolean> dynamic;
    private final /* synthetic */ Setting<Boolean> setPos;
    private final /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ int blockSlot;
    private final /* synthetic */ Setting<Integer> bGreen;
    private final /* synthetic */ Setting<Integer> bAlpha;
    private final /* synthetic */ Setting<Integer> oAlpha;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Setting<Integer> width;
    private /* synthetic */ int lastSlot;
    
    public void onTick() {
        if (this.updates.getValue() == Updates.TICK) {
            this.doAutoBuilder();
        }
    }
    
    public void onEnable() {
        this.placepositions.clear();
        if (!this.keepPos.getValue() || this.startPos == null) {
            this.startPos = new BlockPos(AutoBuilder.mc.player.posX, Math.ceil(AutoBuilder.mc.player.posY), AutoBuilder.mc.player.posZ).down();
        }
        this.blocksThisTick = 0;
        this.lastSlot = AutoBuilder.mc.player.inventory.currentItem;
        this.timer.reset();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (this.updates.getValue() == Updates.WALKING && updateWalkingPlayerEvent.getStage() != 1) {
            this.doAutoBuilder();
        }
    }
    
    private void getPositions() {
        if (this.startPos == null) {
            return;
        }
        this.placepositions.clear();
        for (final BlockPos blockPos : BlockUtil.getSphere(new BlockPos(AutoBuilder.mc.player.posX, Math.ceil(AutoBuilder.mc.player.posY), AutoBuilder.mc.player.posZ).up(), this.range.getValue(), this.range.getValue().intValue(), false, true, 0)) {
            if (this.placepositions.contains(blockPos)) {
                continue;
            }
            if (!AutoBuilder.mc.world.isAirBlock(blockPos)) {
                continue;
            }
            if (this.mode.getValue() == Mode.STAIRS) {
                switch (this.stairDirection.getValue()) {
                    case NORTH: {
                        if (this.startPos.getZ() - blockPos.getZ() == blockPos.getY() - this.startPos.getY() && Math.abs(blockPos.getX() - this.startPos.getX()) < this.width.getValue() / 2) {
                            this.placepositions.add(blockPos);
                            continue;
                        }
                        continue;
                    }
                    case EAST: {
                        if (blockPos.getX() - this.startPos.getX() == blockPos.getY() - this.startPos.getY() && Math.abs(blockPos.getZ() - this.startPos.getZ()) < this.width.getValue() / 2) {
                            this.placepositions.add(blockPos);
                            continue;
                        }
                        continue;
                    }
                    case SOUTH: {
                        if (blockPos.getZ() - this.startPos.getZ() == blockPos.getY() - this.startPos.getY() && Math.abs(this.startPos.getX() - blockPos.getX()) < this.width.getValue() / 2) {
                            this.placepositions.add(blockPos);
                            continue;
                        }
                        continue;
                    }
                    case WEST: {
                        if (this.startPos.getX() - blockPos.getX() == blockPos.getY() - this.startPos.getY() && Math.abs(this.startPos.getZ() - blockPos.getZ()) < this.width.getValue() / 2) {
                            this.placepositions.add(blockPos);
                            continue;
                        }
                        continue;
                    }
                }
            }
            else {
                if (this.mode.getValue() != Mode.FLAT) {
                    continue;
                }
                if (blockPos.getY() != (this.dynamic.getValue() ? (Math.ceil(AutoBuilder.mc.player.posY) - 1.0) : this.startPos.getY())) {
                    continue;
                }
                this.placepositions.add(blockPos);
            }
        }
    }
    
    public void onUpdate() {
        if (this.updates.getValue() == Updates.UPDATE) {
            this.doAutoBuilder();
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.placepositions == null || !this.render.getValue()) {
            return;
        }
        this.placepositions.forEach(blockPos -> RenderUtil.drawSexyBoxOyVeyIsRetardedFuckYouESP(new AxisAlignedBB(blockPos), new Color(this.bRed.getValue(), this.bGreen.getValue(), this.bBlue.getValue(), this.bAlpha.getValue()), new Color(this.oRed.getValue(), this.oGreen.getValue(), this.oBlue.getValue(), this.oAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), 1.0f, 1.0f, 1.0f));
    }
    
    private boolean check() {
        if (this.setPos.getValue()) {
            this.startPos = new BlockPos(AutoBuilder.mc.player.posX, Math.ceil(AutoBuilder.mc.player.posY), AutoBuilder.mc.player.posZ).down();
            this.setPos.setValue(false);
        }
        this.getPositions();
        if (this.placepositions.isEmpty()) {
            return false;
        }
        if (!this.timer.passedMs(this.placeDelay.getValue())) {
            return false;
        }
        this.timer.reset();
        this.blocksThisTick = 0;
        this.lastSlot = AutoBuilder.mc.player.inventory.currentItem;
        this.blockSlot = (this.allBlocks.getValue() ? InventoryUtil.findAnyBlock() : InventoryUtil.findHotbarBlock(BlockObsidian.class));
        return (!this.ground.getValue() || AutoBuilder.mc.player.onGround) && this.blockSlot != -1 && (!this.noMove.getValue() || (AutoBuilder.mc.player.moveForward == 0.0f && AutoBuilder.mc.player.moveStrafing == 0.0f)) && this.doSwitch(false);
    }
    
    private void doAutoBuilder() {
        if (!this.check()) {
            return;
        }
        for (final BlockPos blockPos : this.placepositions) {
            if (this.blocksThisTick >= this.blocksPerTick.getValue()) {
                this.doSwitch(true);
                return;
            }
            final int positionPlaceable = BlockUtil.isPositionPlaceable(blockPos, false, true);
            if (positionPlaceable == 3) {
                BlockUtil.placeBlockNotRetarded(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                ++this.blocksThisTick;
            }
            else {
                if (positionPlaceable != 2 || this.mode.getValue() != Mode.STAIRS) {
                    continue;
                }
                if (BlockUtil.isPositionPlaceable(blockPos.down(), false, true) == 3) {
                    BlockUtil.placeBlockNotRetarded(blockPos.down(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                    ++this.blocksThisTick;
                }
                else {
                    switch (this.stairDirection.getValue()) {
                        case SOUTH: {
                            if (BlockUtil.isPositionPlaceable(blockPos.south(), false, true) == 3) {
                                BlockUtil.placeBlockNotRetarded(blockPos.south(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                                ++this.blocksThisTick;
                                continue;
                            }
                            continue;
                        }
                        case WEST: {
                            if (BlockUtil.isPositionPlaceable(blockPos.west(), false, true) == 3) {
                                BlockUtil.placeBlockNotRetarded(blockPos.west(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                                ++this.blocksThisTick;
                                continue;
                            }
                            continue;
                        }
                        case NORTH: {
                            if (BlockUtil.isPositionPlaceable(blockPos.north(), false, true) == 3) {
                                BlockUtil.placeBlockNotRetarded(blockPos.north(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                                ++this.blocksThisTick;
                                continue;
                            }
                            continue;
                        }
                        case EAST: {
                            if (BlockUtil.isPositionPlaceable(blockPos.east(), false, true) == 3) {
                                BlockUtil.placeBlockNotRetarded(blockPos.east(), EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.altRotate.getValue());
                                ++this.blocksThisTick;
                                continue;
                            }
                            continue;
                        }
                    }
                }
            }
        }
        this.doSwitch(true);
    }
    
    private boolean doSwitch(final boolean b) {
        final Item getItem = AutoBuilder.mc.player.getHeldItemMainhand().getItem();
        switch (this.switchMode.getValue()) {
            case NONE: {
                return getItem instanceof ItemBlock && (this.allBlocks.getValue() || ((ItemBlock)getItem).getBlock() instanceof BlockObsidian);
            }
            case NORMAL: {
                if (!b) {
                    InventoryUtil.switchToHotbarSlot(this.blockSlot, false);
                    break;
                }
                break;
            }
            case SILENT: {
                if (getItem instanceof ItemBlock) {
                    if (this.allBlocks.getValue()) {
                        break;
                    }
                    if (((ItemBlock)getItem).getBlock() instanceof BlockObsidian) {
                        break;
                    }
                }
                if (this.lastSlot == -1) {
                    break;
                }
                if (b) {
                    AutoBuilder.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.lastSlot));
                    break;
                }
                AutoBuilder.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.blockSlot));
                break;
            }
        }
        return true;
    }
    
    public AutoBuilder() {
        super("AutoBuilder", "Auto Builds", Module.Category.PLAYER, true, false, false);
        this.settings = (Setting<Settings>)this.register(new Setting("Settings", (T)Settings.PATTERN));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.FLAT, p0 -> this.settings.getValue() == Settings.PATTERN));
        this.stairDirection = (Setting<Direction>)this.register(new Setting("Direction", (T)Direction.NORTH, p0 -> this.mode.getValue() == Mode.STAIRS && this.settings.getValue() == Settings.PATTERN));
        this.width = (Setting<Integer>)this.register(new Setting("StairWidth", (T)40, (T)1, (T)100, p0 -> this.mode.getValue() == Mode.STAIRS && this.settings.getValue() == Settings.PATTERN));
        this.dynamic = (Setting<Boolean>)this.register(new Setting("Dynamic", (T)true, p0 -> this.mode.getValue() == Mode.FLAT && this.settings.getValue() == Settings.PATTERN));
        this.setPos = (Setting<Boolean>)this.register(new Setting("ResetPos", (T)false, p0 -> this.settings.getValue() == Settings.PATTERN && (this.mode.getValue() == Mode.STAIRS || (this.mode.getValue() == Mode.FLAT && !this.dynamic.getValue()))));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)1.0f, (T)6.0f, p0 -> this.settings.getValue() == Settings.PLACE));
        this.blocksPerTick = (Setting<Integer>)this.register(new Setting("Blocks/Tick", (T)8, (T)1, (T)8, p0 -> this.settings.getValue() == Settings.PLACE));
        this.placeDelay = (Setting<Integer>)this.register(new Setting("PlaceDelay", (T)0, (T)0, (T)500, p0 -> this.settings.getValue() == Settings.PLACE));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false, p0 -> this.settings.getValue() == Settings.PLACE));
        this.altRotate = (Setting<Boolean>)this.register(new Setting("AltRotate", (T)false, p0 -> this.rotate.getValue() && this.settings.getValue() == Settings.PLACE));
        this.ground = (Setting<Boolean>)this.register(new Setting("NoJump", (T)true, p0 -> this.settings.getValue() == Settings.PLACE));
        this.noMove = (Setting<Boolean>)this.register(new Setting("NoMove", (T)true, p0 -> this.settings.getValue() == Settings.PLACE));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)false, p0 -> this.settings.getValue() == Settings.PLACE));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true, p0 -> this.settings.getValue() == Settings.RENDER));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue()));
        this.bRed = (Setting<Integer>)this.register(new Setting("BoxRed", (T)150, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (T)0, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (T)150, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.bAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)40, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue()));
        this.oRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (T)255, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (T)50, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (T)255, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.oAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (T)255, (T)0, (T)255, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.5f, (T)0.1f, (T)5.0f, p0 -> this.settings.getValue() == Settings.RENDER && this.render.getValue() && this.outline.getValue()));
        this.keepPos = (Setting<Boolean>)this.register(new Setting("KeepOldPos", (T)false, p0 -> this.settings.getValue() == Settings.MISC));
        this.updates = (Setting<Updates>)this.register(new Setting("Update", (T)Updates.TICK, p0 -> this.settings.getValue() == Settings.MISC));
        this.switchMode = (Setting<Switch>)this.register(new Setting("Switch", (T)Switch.SILENT, p0 -> this.settings.getValue() == Settings.MISC));
        this.allBlocks = (Setting<Boolean>)this.register(new Setting("AllBlocks", (T)true, p0 -> this.settings.getValue() == Settings.MISC));
        this.timer = new Timer();
        this.placepositions = new ArrayList<BlockPos>();
    }
    
    public enum Direction
    {
        WEST, 
        SOUTH, 
        EAST, 
        NORTH;
    }
    
    public enum Switch
    {
        NORMAL, 
        NONE, 
        SILENT;
    }
    
    public enum Updates
    {
        WALKING, 
        TICK, 
        UPDATE;
    }
    
    public enum Mode
    {
        FLAT, 
        STAIRS;
    }
    
    public enum Settings
    {
        PLACE, 
        MISC, 
        RENDER, 
        PATTERN;
    }
}

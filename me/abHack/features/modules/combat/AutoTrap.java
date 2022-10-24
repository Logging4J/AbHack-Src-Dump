//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import me.abHack.*;
import me.abHack.util.*;
import net.minecraft.block.*;
import net.minecraft.util.*;

public class AutoTrap extends Module
{
    private final /* synthetic */ Setting<Boolean> antiScaffold;
    private final /* synthetic */ Setting<Integer> blocksPerPlace;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Map<BlockPos, Integer> retries;
    private /* synthetic */ boolean smartRotate;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Setting<Boolean> raytrace;
    private final /* synthetic */ Setting<Boolean> antiStep;
    private /* synthetic */ int placements;
    private /* synthetic */ boolean didPlace;
    private final /* synthetic */ Timer retryTimer;
    public static /* synthetic */ boolean isPlacing;
    private final /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ int lastHotbarSlot;
    
    @Override
    public void onDisable() {
        AutoTrap.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    public AutoTrap() {
        super("AutoTrap", "Traps other players", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)250));
        this.blocksPerPlace = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (T)8, (T)1, (T)30));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.raytrace = (Setting<Boolean>)this.register(new Setting("Raytrace", (T)false));
        this.antiScaffold = (Setting<Boolean>)this.register(new Setting("AntiScaffold", (T)false));
        this.antiStep = (Setting<Boolean>)this.register(new Setting("AntiStep", (T)false));
        this.timer = new Timer();
        this.retries = new HashMap<BlockPos, Integer>();
        this.retryTimer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
    }
    
    private void placeList(final List<Vec3d> list) {
        list.sort((vec3d, vec3d3) -> Double.compare(AutoTrap.mc.player.getDistanceSq(vec3d3.x, vec3d3.y, vec3d3.z), AutoTrap.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z)));
        list.sort(Comparator.comparingDouble(vec3d2 -> vec3d2.y));
        final Iterator<Vec3d> iterator = list.iterator();
        while (iterator.hasNext()) {
            final BlockPos blockPos = new BlockPos((Vec3d)iterator.next());
            final int positionPlaceable = BlockUtil.isPositionPlaceable(blockPos, this.raytrace.getValue());
            if (positionPlaceable == 1 && (this.retries.get(blockPos) == null || this.retries.get(blockPos) < 4)) {
                this.placeBlock(blockPos);
                this.retries.put(blockPos, (this.retries.get(blockPos) == null) ? 1 : (this.retries.get(blockPos) + 1));
                this.retryTimer.reset();
            }
            else {
                if (positionPlaceable != 3) {
                    continue;
                }
                this.placeBlock(blockPos);
            }
        }
    }
    
    private boolean check() {
        AutoTrap.isPlacing = false;
        this.didPlace = false;
        this.placements = 0;
        if (InventoryUtil.findHotbarBlock(BlockObsidian.class) == -1) {
            this.toggle();
        }
        final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
        if (this.isOff()) {
            return true;
        }
        if (!this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)AutoTrap.mc.player))) {
            this.disable();
            return true;
        }
        if (this.retryTimer.passedMs(2000L)) {
            this.retries.clear();
            this.retryTimer.reset();
        }
        if (hotbarBlock == -1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> ").append(ChatFormatting.RED).append("No Obsidian in hotbar disabling...")));
            this.disable();
            return true;
        }
        if (AutoTrap.mc.player.inventory.currentItem != this.lastHotbarSlot && AutoTrap.mc.player.inventory.currentItem != hotbarBlock) {
            this.lastHotbarSlot = AutoTrap.mc.player.inventory.currentItem;
        }
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = this.getTarget(10.0, true);
        return this.target == null || !this.timer.passedMs(this.delay.getValue());
    }
    
    private void doStaticTrap() {
        this.placeList(EntityUtil.targets(this.target.getPositionVector(), this.antiScaffold.getValue(), this.antiStep.getValue(), false, false, false, this.raytrace.getValue()));
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        Surround.breakcrystal();
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)AutoTrap.mc.player);
        this.lastHotbarSlot = AutoTrap.mc.player.inventory.currentItem;
        this.retries.clear();
    }
    
    static {
        AutoTrap.isPlacing = false;
    }
    
    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doStaticTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        this.smartRotate = false;
        this.doTrap();
    }
    
    private EntityPlayer getTarget(final double a, final boolean b) {
        EntityPlayer entityPlayer = null;
        double n = Math.pow(a, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AutoTrap.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, a) && (!b || !EntityUtil.isTrapped(entityPlayer2, this.antiScaffold.getValue(), this.antiStep.getValue(), false, false, false))) {
                if (OyVey.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < this.blocksPerPlace.getValue() && AutoTrap.mc.player.getDistanceSq(blockPos) <= MathUtil.square(5.0)) {
            AutoTrap.isPlacing = true;
            final int currentItem = AutoTrap.mc.player.inventory.currentItem;
            final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int hotbarBlock2 = InventoryUtil.findHotbarBlock(BlockEnderChest.class);
            if (hotbarBlock == -1 && hotbarBlock2 == -1) {
                this.toggle();
            }
            if (this.smartRotate) {
                AutoTrap.mc.player.inventory.currentItem = ((hotbarBlock == -1) ? hotbarBlock2 : hotbarBlock);
                AutoTrap.mc.playerController.updateController();
                this.isSneaking = BlockUtil.placeBlockSmartRotate(blockPos, EnumHand.MAIN_HAND, true, true, this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = currentItem;
                AutoTrap.mc.playerController.updateController();
            }
            else {
                AutoTrap.mc.player.inventory.currentItem = ((hotbarBlock == -1) ? hotbarBlock2 : hotbarBlock);
                AutoTrap.mc.playerController.updateController();
                this.isSneaking = BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), true, this.isSneaking);
                AutoTrap.mc.player.inventory.currentItem = currentItem;
                AutoTrap.mc.playerController.updateController();
            }
            this.didPlace = true;
            ++this.placements;
        }
    }
}

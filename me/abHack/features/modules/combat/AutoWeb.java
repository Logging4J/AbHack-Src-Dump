//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import net.minecraft.entity.player.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import me.abHack.*;
import net.minecraft.block.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import me.abHack.util.*;
import java.util.*;

public class AutoWeb extends Module
{
    private final /* synthetic */ Timer timer;
    private /* synthetic */ boolean smartRotate;
    private /* synthetic */ int lastHotbarSlot;
    public /* synthetic */ EntityPlayer target;
    private /* synthetic */ int placements;
    public static /* synthetic */ boolean isPlacing;
    private final /* synthetic */ Setting<Integer> delay;
    private /* synthetic */ boolean switchedItem;
    private /* synthetic */ boolean didPlace;
    private final /* synthetic */ Setting<Boolean> raytrace;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Setting<Boolean> packet;
    private final /* synthetic */ Setting<Boolean> lowerbody;
    private final /* synthetic */ Setting<Integer> blocksPerPlace;
    private final /* synthetic */ Setting<Boolean> upperBody;
    private /* synthetic */ boolean isSneaking;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Boolean> disable;
    
    private void doTrap() {
        if (this.check()) {
            return;
        }
        this.doWebTrap();
        if (this.didPlace) {
            this.timer.reset();
        }
    }
    
    public AutoWeb() {
        super("AutoWeb", "Traps other players in webs", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)250));
        this.blocksPerPlace = (Setting<Integer>)this.register(new Setting("BlocksPerTick", (T)8, (T)1, (T)30));
        this.packet = (Setting<Boolean>)this.register(new Setting("PacketPlace", (T)false));
        this.disable = (Setting<Boolean>)this.register(new Setting("AutoDisable", (T)false));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false));
        this.raytrace = (Setting<Boolean>)this.register(new Setting("Raytrace", (T)false));
        this.lowerbody = (Setting<Boolean>)this.register(new Setting("Feet", (T)true));
        this.upperBody = (Setting<Boolean>)this.register(new Setting("Face", (T)false));
        this.timer = new Timer();
        this.didPlace = false;
        this.placements = 0;
        this.smartRotate = false;
        this.startPos = null;
    }
    
    private EntityPlayer getTarget(final double a) {
        EntityPlayer entityPlayer = null;
        double n = Math.pow(a, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AutoWeb.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, a) && !entityPlayer2.isInWeb) {
                if (OyVey.speedManager.getPlayerSpeed(entityPlayer2) > 30.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n = AutoWeb.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AutoWeb.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n = AutoWeb.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private boolean check() {
        AutoWeb.isPlacing = false;
        this.didPlace = false;
        this.placements = 0;
        final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockWeb.class);
        if (this.isOff()) {
            return true;
        }
        if (this.disable.getValue() && !this.startPos.equals((Object)EntityUtil.getRoundedBlockPos((Entity)AutoWeb.mc.player))) {
            this.disable();
            return true;
        }
        if (hotbarBlock == -1) {
            Command.sendMessage(String.valueOf(new StringBuilder().append("<").append(this.getDisplayName()).append("> ").append(ChatFormatting.RED).append("No Webs in hotbar disabling...")));
            this.toggle();
            return true;
        }
        if (AutoWeb.mc.player.inventory.currentItem != this.lastHotbarSlot && AutoWeb.mc.player.inventory.currentItem != hotbarBlock) {
            this.lastHotbarSlot = AutoWeb.mc.player.inventory.currentItem;
        }
        this.switchItem(true);
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.target = this.getTarget(10.0);
        return this.target == null || !this.timer.passedMs(this.delay.getValue());
    }
    
    private void doWebTrap() {
        this.placeList(this.getPlacements());
    }
    
    @Override
    public void onDisable() {
        AutoWeb.isPlacing = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
        this.switchItem(true);
    }
    
    private List<Vec3d> getPlacements() {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        final Vec3d getPositionVector = this.target.getPositionVector();
        if (this.lowerbody.getValue()) {
            list.add(getPositionVector);
        }
        if (this.upperBody.getValue()) {
            list.add(getPositionVector.add(0.0, 1.0, 0.0));
        }
        return list;
    }
    
    @Override
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        this.startPos = EntityUtil.getRoundedBlockPos((Entity)AutoWeb.mc.player);
        this.lastHotbarSlot = AutoWeb.mc.player.inventory.currentItem;
    }
    
    private void placeBlock(final BlockPos blockPos) {
        if (this.placements < this.blocksPerPlace.getValue() && AutoWeb.mc.player.getDistanceSq(blockPos) <= MathUtil.square(6.0) && this.switchItem(false)) {
            AutoWeb.isPlacing = true;
            this.isSneaking = (this.smartRotate ? BlockUtil.placeBlockSmartRotate(blockPos, EnumHand.MAIN_HAND, true, this.packet.getValue(), this.isSneaking) : BlockUtil.placeBlock(blockPos, EnumHand.MAIN_HAND, this.rotate.getValue(), this.packet.getValue(), this.isSneaking));
            this.didPlace = true;
            ++this.placements;
        }
    }
    
    @Override
    public void onTick() {
        this.smartRotate = false;
        this.doTrap();
    }
    
    private boolean switchItem(final boolean b) {
        final boolean[] switchItem = InventoryUtil.switchItem(b, this.lastHotbarSlot, this.switchedItem, InventoryUtil.Switch.NORMAL, BlockWeb.class);
        this.switchedItem = switchItem[0];
        return switchItem[1];
    }
    
    private void placeList(final List<Vec3d> list) {
        list.sort((vec3d, vec3d3) -> Double.compare(AutoWeb.mc.player.getDistanceSq(vec3d3.x, vec3d3.y, vec3d3.z), AutoWeb.mc.player.getDistanceSq(vec3d.x, vec3d.y, vec3d.z)));
        list.sort(Comparator.comparingDouble(vec3d2 -> vec3d2.y));
        final Iterator<Vec3d> iterator = list.iterator();
        while (iterator.hasNext()) {
            final BlockPos blockPos = new BlockPos((Vec3d)iterator.next());
            final int positionPlaceable = BlockUtil.isPositionPlaceable(blockPos, this.raytrace.getValue());
            if (positionPlaceable != 3 && positionPlaceable != 1) {
                continue;
            }
            this.placeBlock(blockPos);
        }
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            return this.target.getName();
        }
        return null;
    }
    
    static {
        AutoWeb.isPlacing = false;
    }
}

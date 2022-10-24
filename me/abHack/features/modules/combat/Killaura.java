//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import me.abHack.*;
import net.minecraft.init.*;
import me.abHack.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import me.abHack.util.*;
import java.util.*;

public class Killaura extends Module
{
    public /* synthetic */ Setting<Boolean> tps;
    public /* synthetic */ Setting<Float> raytrace;
    public /* synthetic */ Setting<Boolean> projectiles;
    public /* synthetic */ Setting<Boolean> mobs;
    public /* synthetic */ Setting<Boolean> animals;
    public /* synthetic */ Setting<Boolean> delay;
    public static /* synthetic */ Entity target;
    public /* synthetic */ Setting<Float> range;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> silent;
    public /* synthetic */ Setting<Boolean> onlySharp;
    public /* synthetic */ Setting<Boolean> rotate;
    public /* synthetic */ Setting<Boolean> players;
    public /* synthetic */ Setting<Boolean> packet;
    public /* synthetic */ Setting<Boolean> vehicles;
    
    private void switchToSlot(final int currentItem) {
        Killaura.mc.player.inventory.currentItem = currentItem;
        Killaura.mc.playerController.updateController();
    }
    
    @Override
    public String getDisplayInfo() {
        if (Killaura.target instanceof EntityPlayer) {
            return Killaura.target.getName();
        }
        return null;
    }
    
    private void doKillaura() {
        if (this.onlySharp.getValue() && !EntityUtil.holdingWeapon((EntityPlayer)Killaura.mc.player) && !this.silent.getValue()) {
            Killaura.target = null;
            return;
        }
        int n = this.delay.getValue() ? ((int)(DamageUtil.getCooldownByWeapon((EntityPlayer)Killaura.mc.player) * (this.tps.getValue() ? OyVey.serverManager.getTpsFactor() : 1.0f))) : 0;
        if (this.silent.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD) != -1) {
            n = 600;
        }
        if (!this.timer.passedMs(n)) {
            return;
        }
        Killaura.target = this.getTarget();
        if (Killaura.target == null) {
            return;
        }
        if (this.rotate.getValue()) {
            OyVey.rotationManager.lookAtEntity(Killaura.target);
        }
        if (this.silent.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD) != -1) {
            final int currentItem = Killaura.mc.player.inventory.currentItem;
            this.switchToSlot(InventoryUtil.getItemHotbar(Items.DIAMOND_SWORD));
            EntityUtil.attackEntity(Killaura.target, this.packet.getValue(), true);
            this.switchToSlot(currentItem);
        }
        else {
            EntityUtil.attackEntity(Killaura.target, this.packet.getValue(), true);
        }
        this.timer.reset();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayerEvent(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 0 && this.rotate.getValue()) {
            this.doKillaura();
        }
    }
    
    public Killaura() {
        super("KillAura", "Attacks entities automatically.", Category.COMBAT, true, false, false);
        this.timer = new Timer();
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)6.0f, (T)0.1f, (T)7.0f));
        this.delay = (Setting<Boolean>)this.register(new Setting("HitDelay", (T)true));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.onlySharp = (Setting<Boolean>)this.register(new Setting("SwordOnly", (T)true));
        this.raytrace = (Setting<Float>)this.register(new Setting("Raytrace", (T)3.0f, (T)0.1f, (T)3.0f, "Wall Range."));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (T)true));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Mobs", (T)false));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (T)false));
        this.vehicles = (Setting<Boolean>)this.register(new Setting("Entities", (T)false));
        this.projectiles = (Setting<Boolean>)this.register(new Setting("Projectiles", (T)false));
        this.tps = (Setting<Boolean>)this.register(new Setting("TpsSync", (T)true));
        this.silent = (Setting<Boolean>)this.register(new Setting("Silent", (T)false));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)false));
    }
    
    private Entity getTarget() {
        Entity entity = null;
        for (final Entity entity2 : Killaura.mc.world.loadedEntityList) {
            if ((this.players.getValue() && entity2 instanceof EntityPlayer) || (this.mobs.getValue() && EntityUtil.isMobAggressive(entity2)) || (this.animals.getValue() && EntityUtil.isPassive(entity2)) || (this.vehicles.getValue() && EntityUtil.isVehicle(entity2)) || (this.projectiles.getValue() && EntityUtil.isProjectile(entity2))) {
                if (entity2 instanceof EntityLivingBase && EntityUtil.isntValid(entity2, this.range.getValue())) {
                    continue;
                }
                if (!Killaura.mc.player.canEntityBeSeen(entity2) && Killaura.mc.player.getDistanceSq(entity2) > MathUtil.square(this.raytrace.getValue())) {
                    continue;
                }
                if (entity == null) {
                    entity = entity2;
                }
                else {
                    if (entity.getDistance((Entity)Killaura.mc.player) <= entity2.getDistance((Entity)Killaura.mc.player)) {
                        continue;
                    }
                    entity = entity2;
                }
            }
        }
        return entity;
    }
    
    @Override
    public void onTick() {
        if (!this.rotate.getValue()) {
            this.doKillaura();
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import me.abHack.features.command.*;
import me.abHack.event.events.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class BowGod extends Module
{
    public /* synthetic */ Setting<Integer> Timeout;
    public /* synthetic */ Setting<Boolean> bypass;
    public /* synthetic */ Setting<Boolean> snowballs;
    public /* synthetic */ Setting<Boolean> eggs;
    public /* synthetic */ Setting<Boolean> pearls;
    private final /* synthetic */ Setting<String> spoofs;
    private /* synthetic */ long lastShootTime;
    public /* synthetic */ Setting<Boolean> debug;
    public /* synthetic */ Setting<Boolean> Bows;
    private /* synthetic */ boolean shooting;
    
    public BowGod() {
        super("BowGod", "super bow", Category.MISC, true, false, false);
        this.spoofs = (Setting<String>)this.register(new Setting("Spoofs", (T)"10"));
        this.Bows = (Setting<Boolean>)this.register(new Setting("Bows", (T)true));
        this.pearls = (Setting<Boolean>)this.register(new Setting("Pearls", (T)true));
        this.eggs = (Setting<Boolean>)this.register(new Setting("Eggs", (T)true));
        this.snowballs = (Setting<Boolean>)this.register(new Setting("SnowBallz", (T)true));
        this.Timeout = (Setting<Integer>)this.register(new Setting("Timeout", (T)500, (T)0, (T)2000));
        this.bypass = (Setting<Boolean>)this.register(new Setting("Bypass", (T)false));
        this.debug = (Setting<Boolean>)this.register(new Setting("Debug", (T)false));
    }
    
    @Override
    public void onEnable() {
        if (this.isEnabled()) {
            this.shooting = false;
            this.lastShootTime = System.currentTimeMillis();
        }
    }
    
    private void doSpoofs() {
        if (System.currentTimeMillis() - this.lastShootTime >= this.Timeout.getValue()) {
            this.shooting = true;
            this.lastShootTime = System.currentTimeMillis();
            BowGod.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)BowGod.mc.player, CPacketEntityAction.Action.START_SPRINTING));
            for (int i = 0; i < Integer.valueOf(this.spoofs.getValue()); ++i) {
                if (this.bypass.getValue()) {
                    BowGod.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowGod.mc.player.posX, BowGod.mc.player.posY + 1.0E-10, BowGod.mc.player.posZ, false));
                    BowGod.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowGod.mc.player.posX, BowGod.mc.player.posY - 1.0E-10, BowGod.mc.player.posZ, true));
                }
                else {
                    BowGod.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowGod.mc.player.posX, BowGod.mc.player.posY - 1.0E-10, BowGod.mc.player.posZ, true));
                    BowGod.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(BowGod.mc.player.posX, BowGod.mc.player.posY + 1.0E-10, BowGod.mc.player.posZ, false));
                }
            }
            if (this.debug.getValue()) {
                Command.sendMessage("Spoofed");
            }
            this.shooting = false;
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() != 0) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging) {
            if (((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.RELEASE_USE_ITEM) {
                final ItemStack getHeldItem = BowGod.mc.player.getHeldItem(EnumHand.MAIN_HAND);
                if (!getHeldItem.isEmpty() && getHeldItem.getItem() != null && getHeldItem.getItem() instanceof ItemBow && this.Bows.getValue()) {
                    this.doSpoofs();
                    if (this.debug.getValue()) {
                        Command.sendMessage("trying to spoof");
                    }
                }
            }
        }
        else if (send.getPacket() instanceof CPacketPlayerTryUseItem && ((CPacketPlayerTryUseItem)send.getPacket()).getHand() == EnumHand.MAIN_HAND) {
            final ItemStack getHeldItem2 = BowGod.mc.player.getHeldItem(EnumHand.MAIN_HAND);
            if (!getHeldItem2.isEmpty() && getHeldItem2.getItem() != null) {
                if (getHeldItem2.getItem() instanceof ItemEgg && this.eggs.getValue()) {
                    this.doSpoofs();
                }
                else if (getHeldItem2.getItem() instanceof ItemEnderPearl && this.pearls.getValue()) {
                    this.doSpoofs();
                }
                else if (getHeldItem2.getItem() instanceof ItemSnowball && this.snowballs.getValue()) {
                    this.doSpoofs();
                }
            }
        }
    }
}

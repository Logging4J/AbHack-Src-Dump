//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.*;

public class PlayerTweaks extends Module
{
    public /* synthetic */ Setting<Boolean> noBlockPush;
    public static /* synthetic */ PlayerTweaks INSTANCE;
    public /* synthetic */ Setting<Boolean> noWaterPush;
    public /* synthetic */ Setting<Boolean> guiMove;
    public /* synthetic */ Setting<Boolean> noEntityPush;
    public /* synthetic */ Setting<Boolean> noSlow;
    public /* synthetic */ Setting<Boolean> antiKnockBack;
    
    @SubscribeEvent
    public void onPacketReceived(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if (this.antiKnockBack.getValue()) {
            if (receive.getPacket() instanceof SPacketEntityVelocity && ((SPacketEntityVelocity)receive.getPacket()).getEntityID() == PlayerTweaks.mc.player.getEntityId()) {
                receive.setCanceled(true);
            }
            if (receive.getPacket() instanceof SPacketExplosion) {
                receive.setCanceled(true);
            }
        }
    }
    
    static {
        PlayerTweaks.INSTANCE = new PlayerTweaks();
    }
    
    private void setInstance() {
        PlayerTweaks.INSTANCE = this;
    }
    
    public PlayerTweaks() {
        super("PlayerTweaks", "tweaks", Module.Category.MOVEMENT, true, false, false);
        this.noSlow = (Setting<Boolean>)this.register(new Setting("No Slow", (T)true));
        this.antiKnockBack = (Setting<Boolean>)this.register(new Setting("Velocity", (T)true));
        this.noEntityPush = (Setting<Boolean>)this.register(new Setting("No PlayerPush", (T)true));
        this.noBlockPush = (Setting<Boolean>)this.register(new Setting("No BlockPush", (T)true));
        this.noWaterPush = (Setting<Boolean>)this.register(new Setting("No LiquidPush", (T)true));
        this.guiMove = (Setting<Boolean>)this.register(new Setting("Gui Move", (T)true));
        this.setInstance();
    }
    
    public static PlayerTweaks getInstance() {
        if (PlayerTweaks.INSTANCE == null) {
            PlayerTweaks.INSTANCE = new PlayerTweaks();
        }
        return PlayerTweaks.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPush(final PushEvent pushEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (pushEvent.getStage() == 0 && this.noEntityPush.getValue() && pushEvent.entity.equals((Object)PlayerTweaks.mc.player)) {
            pushEvent.x = -pushEvent.x * 0.0;
            pushEvent.y = -pushEvent.y * 0.0;
            pushEvent.z = -pushEvent.z * 0.0;
        }
        else if (pushEvent.getStage() == 1 && this.noBlockPush.getValue()) {
            pushEvent.setCanceled(true);
        }
        else if (pushEvent.getStage() == 2 && this.noWaterPush.getValue() && PlayerTweaks.mc.player != null && PlayerTweaks.mc.player.equals((Object)pushEvent.entity)) {
            pushEvent.setCanceled(true);
        }
    }
    
    @SubscribeEvent
    public void Slow(final InputUpdateEvent inputUpdateEvent) {
        if (this.noSlow.getValue() && PlayerTweaks.mc.player.isHandActive() && !PlayerTweaks.mc.player.isRiding()) {
            final MovementInput movementInput = inputUpdateEvent.getMovementInput();
            movementInput.moveStrafe *= 5.0f;
            final MovementInput movementInput2 = inputUpdateEvent.getMovementInput();
            movementInput2.moveForward *= 5.0f;
        }
    }
}

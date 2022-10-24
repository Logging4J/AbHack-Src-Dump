//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.client.entity.*;
import me.abHack.*;

public class Phase extends Module
{
    private final /* synthetic */ Setting<Double> speed;
    
    public Phase() {
        super("Phase", "Phase.", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (T)5.0, (T)0.0, (T)50.0));
    }
    
    public void onDisable() {
        Phase.mc.player.capabilities.isFlying = false;
        Phase.mc.player.noClip = false;
    }
    
    public void onUpdate() {
        if (this.shouldReturn()) {
            Phase.mc.player.capabilities.isFlying = false;
            Phase.mc.player.noClip = false;
            return;
        }
        Phase.mc.player.motionY = 0.0;
        Phase.mc.player.noClip = true;
        Phase.mc.player.capabilities.isFlying = true;
        Phase.mc.player.onGround = false;
        Phase.mc.player.fallDistance = 0.0f;
        Phase.mc.player.capabilities.setFlySpeed((float)(this.speed.getValue() / 100.0));
        if (Phase.mc.gameSettings.keyBindJump.isPressed()) {
            final EntityPlayerSP player = Phase.mc.player;
            player.motionY += 0.010000000149011612;
        }
        if (Phase.mc.gameSettings.keyBindSneak.isPressed()) {
            final EntityPlayerSP player2 = Phase.mc.player;
            player2.motionY -= 0.010000000149011612;
        }
    }
    
    private boolean shouldReturn() {
        return OyVey.moduleManager.isModuleEnabled("Freecam") || OyVey.moduleManager.isModuleEnabled("Step") || OyVey.moduleManager.isModuleEnabled("ElytraFlight") || OyVey.moduleManager.isModuleEnabled("Flight") || OyVey.moduleManager.isModuleEnabled("Strafe");
    }
    
    public void onEnable() {
        Phase.mc.player.capabilities.isFlying = true;
    }
}

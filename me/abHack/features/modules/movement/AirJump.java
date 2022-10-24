//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.*;

public class AirJump extends Module
{
    private final /* synthetic */ Setting<Float> movementspeed;
    private /* synthetic */ boolean owo;
    private final /* synthetic */ Setting<Float> speed;
    
    private boolean shouldReturn() {
        return OyVey.moduleManager.isModuleEnabled("Freecam") || OyVey.moduleManager.isModuleEnabled("ElytraFlight") || OyVey.moduleManager.isModuleEnabled("Phase") || OyVey.moduleManager.isModuleEnabled("Flight") || OyVey.moduleManager.isModuleEnabled("Strafe");
    }
    
    public void onUpdate() {
        if (AirJump.mc.player == null) {
            return;
        }
        if (this.shouldReturn()) {
            return;
        }
        AirJump.mc.player.capabilities.isFlying = false;
        AirJump.mc.player.jumpMovementFactor = this.movementspeed.getValue() / 100.0f;
        if (AirJump.mc.gameSettings.keyBindJump.isKeyDown()) {
            if (!this.owo) {
                AirJump.mc.player.motionY = this.speed.getValue() / 10.0f;
                this.owo = true;
            }
        }
        else if (!AirJump.mc.gameSettings.keyBindJump.isKeyDown()) {
            this.owo = false;
        }
    }
    
    public AirJump() {
        super("AirJump", "AirJump.", Module.Category.MOVEMENT, true, false, false);
        this.owo = false;
        this.speed = (Setting<Float>)this.register(new Setting("Speed", (T)5.0f, (T)1.0f, (T)10.0f));
        this.movementspeed = (Setting<Float>)this.register(new Setting("MoveSpeed", (T)10.0f, (T)1.0f, (T)10.0f));
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.*;
import net.minecraft.entity.*;

public class ReverseStep extends Module
{
    private final /* synthetic */ Setting<Boolean> inliquid;
    private final /* synthetic */ Setting<Integer> speed;
    private static /* synthetic */ ReverseStep INSTANCE;
    private final /* synthetic */ Setting<Cancel> canceller;
    
    public void onUpdate() {
        if (nullCheck()) {
            return;
        }
        if (ReverseStep.mc.player.isSneaking() || ReverseStep.mc.player.isDead || ReverseStep.mc.player.collidedHorizontally || !ReverseStep.mc.player.onGround || (ReverseStep.mc.player.isInWater() && !this.inliquid.getValue()) || (ReverseStep.mc.player.isInLava() && !this.inliquid.getValue()) || ReverseStep.mc.player.isOnLadder() || ReverseStep.mc.gameSettings.keyBindJump.isKeyDown() || OyVey.moduleManager.isModuleEnabled("Burrow") || ReverseStep.mc.player.noClip || OyVey.moduleManager.isModuleEnabled("Packetfly") || OyVey.moduleManager.isModuleEnabled("Phase") || (ReverseStep.mc.gameSettings.keyBindSneak.isKeyDown() && this.canceller.getValue() == Cancel.Shift) || (ReverseStep.mc.gameSettings.keyBindSneak.isKeyDown() && this.canceller.getValue() == Cancel.Both) || (ReverseStep.mc.gameSettings.keyBindJump.isKeyDown() && this.canceller.getValue() == Cancel.Space) || (ReverseStep.mc.gameSettings.keyBindJump.isKeyDown() && this.canceller.getValue() == Cancel.Both) || OyVey.moduleManager.isModuleEnabled("Strafe")) {
            return;
        }
        for (double n = 0.0; n < 90.5; n += 0.01) {
            if (!ReverseStep.mc.world.getCollisionBoxes((Entity)ReverseStep.mc.player, ReverseStep.mc.player.getEntityBoundingBox().offset(0.0, -n, 0.0)).isEmpty()) {
                ReverseStep.mc.player.motionY = -this.speed.getValue() / 10.0f;
                break;
            }
        }
    }
    
    public static ReverseStep getInstance() {
        if (ReverseStep.INSTANCE == null) {
            ReverseStep.INSTANCE = new ReverseStep();
        }
        return ReverseStep.INSTANCE;
    }
    
    static {
        ReverseStep.INSTANCE = new ReverseStep();
    }
    
    private void setInstance() {
        ReverseStep.INSTANCE = this;
    }
    
    public ReverseStep() {
        super("ReverseStep", "Rapid decline", Module.Category.MOVEMENT, true, false, false);
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", (T)8, (T)1, (T)20));
        this.inliquid = (Setting<Boolean>)this.register(new Setting("Liquid", (T)false));
        this.canceller = (Setting<Cancel>)this.register(new Setting("CancelType", (T)Cancel.None));
        this.setInstance();
    }
    
    public enum Cancel
    {
        None, 
        Shift, 
        Space, 
        Both;
    }
}

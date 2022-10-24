//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;

public class BoatFly extends Module
{
    private final /* synthetic */ Setting<Double> speed;
    private final /* synthetic */ Setting<Boolean> glide;
    private final /* synthetic */ Setting<Double> yspeed;
    
    private double[] directionSpeed(final double n) {
        float moveForward = BoatFly.mc.player.movementInput.moveForward;
        float moveStrafe = BoatFly.mc.player.movementInput.moveStrafe;
        float n2 = BoatFly.mc.player.prevRotationYaw + (BoatFly.mc.player.rotationYaw - BoatFly.mc.player.prevRotationYaw) * BoatFly.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public void onUpdate() {
        if (BoatFly.mc.player == null || BoatFly.mc.player.getRidingEntity() == null || BoatFly.mc.world == null) {
            return;
        }
        if (BoatFly.mc.player.getRidingEntity() != null) {
            BoatFly.mc.player.getRidingEntity().setNoGravity(true);
            BoatFly.mc.player.getRidingEntity().motionY = 0.0;
            if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                BoatFly.mc.player.getRidingEntity().onGround = false;
                BoatFly.mc.player.getRidingEntity().motionY = this.yspeed.getValue();
            }
            if (BoatFly.mc.gameSettings.keyBindSprint.isKeyDown()) {
                BoatFly.mc.player.getRidingEntity().onGround = false;
                BoatFly.mc.player.getRidingEntity().motionY = -(this.speed.getValue() / 10.0);
            }
            final double[] directionSpeed = this.directionSpeed(this.speed.getValue() / 2.0);
            if (BoatFly.mc.player.movementInput.moveStrafe != 0.0f || BoatFly.mc.player.movementInput.moveForward != 0.0f) {
                BoatFly.mc.player.getRidingEntity().motionX = directionSpeed[0];
                BoatFly.mc.player.getRidingEntity().motionZ = directionSpeed[1];
            }
            else {
                BoatFly.mc.player.getRidingEntity().motionX = 0.0;
                BoatFly.mc.player.getRidingEntity().motionZ = 0.0;
            }
            if (this.glide.getValue()) {
                if (BoatFly.mc.gameSettings.keyBindJump.isKeyDown()) {
                    if (BoatFly.mc.player.ticksExisted % 8 < 2) {
                        BoatFly.mc.player.getRidingEntity().motionY = -0.03999999910593033;
                    }
                }
                else if (BoatFly.mc.player.ticksExisted % 8 < 4) {
                    BoatFly.mc.player.getRidingEntity().motionY = -0.03999999910593033;
                }
            }
        }
    }
    
    public BoatFly() {
        super("BoatFly", "BoatFly.", Module.Category.MOVEMENT, false, false, false);
        this.speed = (Setting<Double>)this.register(new Setting("Speed", (T)5.0, (T)0.1, (T)20.0));
        this.yspeed = (Setting<Double>)this.register(new Setting("YSpeed", (T)3.0, (T)0.1, (T)20.0));
        this.glide = (Setting<Boolean>)this.register(new Setting("Glide", (T)true));
    }
    
    public void onDisable() {
        if (BoatFly.mc.player.getRidingEntity() != null) {
            BoatFly.mc.player.getRidingEntity().setNoGravity(false);
        }
    }
}

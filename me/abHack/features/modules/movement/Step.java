//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.text.*;

public class Step extends Module
{
    /* synthetic */ Setting<Double> height;
    private static /* synthetic */ Step instance;
    /* synthetic */ Setting<Mode> mode;
    
    public void onUpdate() {
        if (Step.mc.world == null || Step.mc.player == null) {
            return;
        }
        if (Step.mc.player.isInWater() || Step.mc.player.isInLava() || Step.mc.player.isOnLadder() || Step.mc.gameSettings.keyBindJump.isKeyDown()) {
            return;
        }
        if (this.mode.getValue() == Mode.Normal) {
            final double[] forward = forward(0.1);
            boolean b = false;
            boolean b2 = false;
            boolean b3 = false;
            boolean b4 = false;
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.4, forward[1])).isEmpty()) {
                b = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 2.1, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.9, forward[1])).isEmpty()) {
                b2 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.6, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.4, forward[1])).isEmpty()) {
                b3 = true;
            }
            if (Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 1.0, forward[1])).isEmpty() && !Step.mc.world.getCollisionBoxes((Entity)Step.mc.player, Step.mc.player.getEntityBoundingBox().offset(forward[0], 0.6, forward[1])).isEmpty()) {
                b4 = true;
            }
            if (Step.mc.player.collidedHorizontally && (Step.mc.player.moveForward != 0.0f || Step.mc.player.moveStrafing != 0.0f) && Step.mc.player.onGround) {
                if (b4 && this.height.getValue() >= 1.0) {
                    final double[] array = { 0.42, 0.753 };
                    for (int i = 0; i < array.length; ++i) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array[i], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.0, Step.mc.player.posZ);
                }
                if (b3 && this.height.getValue() >= 1.5) {
                    final double[] array2 = { 0.42, 0.75, 1.0, 1.16, 1.23, 1.2 };
                    for (int j = 0; j < array2.length; ++j) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array2[j], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 1.5, Step.mc.player.posZ);
                }
                if (b2 && this.height.getValue() >= 2.0) {
                    final double[] array3 = { 0.42, 0.78, 0.63, 0.51, 0.9, 1.21, 1.45, 1.43 };
                    for (int k = 0; k < array3.length; ++k) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array3[k], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.0, Step.mc.player.posZ);
                }
                if (b && this.height.getValue() >= 2.5) {
                    final double[] array4 = { 0.425, 0.821, 0.699, 0.599, 1.022, 1.372, 1.652, 1.869, 2.019, 1.907 };
                    for (int l = 0; l < array4.length; ++l) {
                        Step.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(Step.mc.player.posX, Step.mc.player.posY + array4[l], Step.mc.player.posZ, Step.mc.player.onGround));
                    }
                    Step.mc.player.setPosition(Step.mc.player.posX, Step.mc.player.posY + 2.5, Step.mc.player.posZ);
                }
            }
        }
        if (this.mode.getValue() == Mode.Vanilla) {
            Step.mc.player.stepHeight = Float.parseFloat(new DecimalFormat("#").format(this.height.getValue()));
        }
    }
    
    public static double[] forward(final double n) {
        float moveForward = Step.mc.player.movementInput.moveForward;
        float moveStrafe = Step.mc.player.movementInput.moveStrafe;
        float n2 = Step.mc.player.prevRotationYaw + (Step.mc.player.rotationYaw - Step.mc.player.prevRotationYaw) * Step.mc.getRenderPartialTicks();
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
    
    public void onDisable() {
        Step.mc.player.stepHeight = 0.5f;
    }
    
    public Step() {
        super("Step", "step", Module.Category.MOVEMENT, true, false, false);
        this.height = (Setting<Double>)this.register(new Setting("Height", (T)2.5, (T)0.5, (T)2.5));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.Vanilla));
        Step.instance = this;
    }
    
    public static Step getInstance() {
        if (Step.instance == null) {
            Step.instance = new Step();
        }
        return Step.instance;
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public void onToggle() {
        Step.mc.player.stepHeight = 0.6f;
    }
    
    public enum Mode
    {
        Vanilla, 
        Normal;
    }
}

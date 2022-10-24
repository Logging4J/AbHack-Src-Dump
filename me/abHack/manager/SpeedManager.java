//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import java.util.*;
import net.minecraft.client.*;
import net.minecraft.util.math.*;

public class SpeedManager extends Feature
{
    public /* synthetic */ double percentJumpSpeedChanged;
    public /* synthetic */ double speedometerCurrentSpeed;
    public /* synthetic */ boolean didJumpLastTick;
    public /* synthetic */ double lastJumpSpeed;
    public /* synthetic */ double jumpSpeedChanged;
    public /* synthetic */ HashMap<EntityPlayer, Double> playerSpeeds;
    public /* synthetic */ long jumpInfoStartTime;
    public static /* synthetic */ boolean didJumpThisTick;
    public /* synthetic */ double firstJumpSpeed;
    public static /* synthetic */ boolean isJumping;
    public /* synthetic */ boolean wasFirstJump;
    
    public double getSpeedKpH() {
        return Math.round(10.0 * this.turnIntoKpH(this.speedometerCurrentSpeed)) / 10.0;
    }
    
    public void updatePlayers() {
        for (final EntityPlayer key : SpeedManager.mc.world.playerEntities) {
            final double getDistanceSq = SpeedManager.mc.player.getDistanceSq((Entity)key);
            this.getClass();
            final int n = 20;
            this.getClass();
            if (getDistanceSq >= n * 20) {
                continue;
            }
            final double n2 = key.posX - key.prevPosX;
            final double n3 = key.posZ - key.prevPosZ;
            this.playerSpeeds.put(key, n2 * n2 + n3 * n3);
        }
    }
    
    public float lastJumpInfoTimeRemaining() {
        return (Minecraft.getSystemTime() - this.jumpInfoStartTime) / 1000.0f;
    }
    
    public SpeedManager() {
        this.firstJumpSpeed = 0.0;
        this.lastJumpSpeed = 0.0;
        this.percentJumpSpeedChanged = 0.0;
        this.jumpSpeedChanged = 0.0;
        this.didJumpLastTick = false;
        this.jumpInfoStartTime = 0L;
        this.wasFirstJump = true;
        this.speedometerCurrentSpeed = 0.0;
        this.playerSpeeds = new HashMap<EntityPlayer, Double>();
    }
    
    public static void setDidJumpThisTick(final boolean didJumpThisTick) {
        SpeedManager.didJumpThisTick = didJumpThisTick;
    }
    
    public double getPlayerSpeed(final EntityPlayer entityPlayer) {
        if (this.playerSpeeds.get(entityPlayer) == null) {
            return 0.0;
        }
        return this.turnIntoKpH(this.playerSpeeds.get(entityPlayer));
    }
    
    public void updateValues() {
        final double n = SpeedManager.mc.player.posX - SpeedManager.mc.player.prevPosX;
        final double n2 = SpeedManager.mc.player.posZ - SpeedManager.mc.player.prevPosZ;
        this.speedometerCurrentSpeed = n * n + n2 * n2;
        if (SpeedManager.didJumpThisTick && (!SpeedManager.mc.player.onGround || SpeedManager.isJumping)) {
            if (SpeedManager.didJumpThisTick && !this.didJumpLastTick) {
                this.wasFirstJump = (this.lastJumpSpeed == 0.0);
                this.percentJumpSpeedChanged = ((this.speedometerCurrentSpeed != 0.0) ? (this.speedometerCurrentSpeed / this.lastJumpSpeed - 1.0) : -1.0);
                this.jumpSpeedChanged = this.speedometerCurrentSpeed - this.lastJumpSpeed;
                this.jumpInfoStartTime = Minecraft.getSystemTime();
                this.lastJumpSpeed = this.speedometerCurrentSpeed;
                this.firstJumpSpeed = (this.wasFirstJump ? this.lastJumpSpeed : 0.0);
            }
            this.didJumpLastTick = SpeedManager.didJumpThisTick;
        }
        else {
            this.didJumpLastTick = false;
            this.lastJumpSpeed = 0.0;
        }
        this.updatePlayers();
    }
    
    static {
        SpeedManager.didJumpThisTick = false;
        SpeedManager.isJumping = false;
    }
    
    public static void setIsJumping(final boolean isJumping) {
        SpeedManager.isJumping = isJumping;
    }
    
    public double getSpeedMpS() {
        return Math.round(10.0 * (this.turnIntoKpH(this.speedometerCurrentSpeed) / 3.6)) / 10.0;
    }
    
    public double turnIntoKpH(final double n) {
        return MathHelper.sqrt(n) * 71.2729367892;
    }
}

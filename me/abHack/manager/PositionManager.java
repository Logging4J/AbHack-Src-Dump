//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class PositionManager extends Feature
{
    private /* synthetic */ double y;
    private /* synthetic */ double x;
    private /* synthetic */ boolean onground;
    private /* synthetic */ double z;
    
    public double getX() {
        return this.x;
    }
    
    public void setX(final double x) {
        this.x = x;
    }
    
    public void updatePosition() {
        this.x = PositionManager.mc.player.posX;
        this.y = PositionManager.mc.player.posY;
        this.z = PositionManager.mc.player.posZ;
        this.onground = PositionManager.mc.player.onGround;
    }
    
    public void setY(final double y) {
        this.y = y;
    }
    
    public void setZ(final double z) {
        this.z = z;
    }
    
    public void setPlayerPosition(final double posX, final double posY, final double posZ) {
        PositionManager.mc.player.posX = posX;
        PositionManager.mc.player.posY = posY;
        PositionManager.mc.player.posZ = posZ;
    }
    
    public double getY() {
        return this.y;
    }
    
    public double getZ() {
        return this.z;
    }
    
    public void setPositionPacket(final double n, final double n2, final double n3, final boolean b, final boolean b2, final boolean b3) {
        PositionManager.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Position(n, n2, n3, b));
        if (b2) {
            PositionManager.mc.player.setPosition(n, n2, n3);
            if (b3) {
                this.updatePosition();
            }
        }
    }
    
    public void restorePosition() {
        PositionManager.mc.player.posX = this.x;
        PositionManager.mc.player.posY = this.y;
        PositionManager.mc.player.posZ = this.z;
        PositionManager.mc.player.onGround = this.onground;
    }
    
    public void setPlayerPosition(final double posX, final double posY, final double posZ, final boolean onGround) {
        PositionManager.mc.player.posX = posX;
        PositionManager.mc.player.posY = posY;
        PositionManager.mc.player.posZ = posZ;
        PositionManager.mc.player.onGround = onGround;
    }
}

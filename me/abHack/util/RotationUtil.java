//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;

public class RotationUtil implements Util
{
    public static Vec3d getEyesPos() {
        return new Vec3d(RotationUtil.mc.player.posX, RotationUtil.mc.player.posY + RotationUtil.mc.player.getEyeHeight(), RotationUtil.mc.player.posZ);
    }
    
    public static void faceVector(final Vec3d vec3d, final boolean b) {
        final float[] legitRotations = getLegitRotations(vec3d);
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(legitRotations[0], b ? ((float)MathHelper.normalizeAngle((int)legitRotations[1], 360)) : legitRotations[1], RotationUtil.mc.player.onGround));
    }
    
    public static float[] getLegitRotations(final Vec3d vec3d) {
        final Vec3d eyesPos = getEyesPos();
        final double x = vec3d.x - eyesPos.x;
        final double y = vec3d.y - eyesPos.y;
        final double y2 = vec3d.z - eyesPos.z;
        return new float[] { RotationUtil.mc.player.rotationYaw + MathHelper.wrapDegrees((float)Math.toDegrees(Math.atan2(y2, x)) - 90.0f - RotationUtil.mc.player.rotationYaw), RotationUtil.mc.player.rotationPitch + MathHelper.wrapDegrees((float)(-Math.toDegrees(Math.atan2(y, Math.sqrt(x * x + y2 * y2)))) - RotationUtil.mc.player.rotationPitch) };
    }
    
    public static int getDirection4D() {
        return MathHelper.floor(RotationUtil.mc.player.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3;
    }
    
    public static void faceYawAndPitch(final float n, final float n2) {
        RotationUtil.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(n, n2, RotationUtil.mc.player.onGround));
    }
    
    public static void faceEntity(final Entity entity) {
        final float[] calcAngle = MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
        faceYawAndPitch(calcAngle[0], calcAngle[1]);
    }
    
    public static float[] getAngle(final Entity entity) {
        return MathUtil.calcAngle(RotationUtil.mc.player.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()), entity.getPositionEyes(RotationUtil.mc.getRenderPartialTicks()));
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static String getDirection4D(final boolean b) {
        final int direction4D = getDirection4D();
        if (direction4D == 0) {
            return "South (+Z)";
        }
        if (direction4D == 1) {
            return "West (-X)";
        }
        if (direction4D == 2) {
            return String.valueOf(new StringBuilder().append(b ? "\u00c2§c" : "").append("North (-Z)"));
        }
        if (direction4D == 3) {
            return "East (+X)";
        }
        return "Loading...";
    }
    
    public static float[] simpleFacing(final EnumFacing enumFacing) {
        switch (enumFacing) {
            case DOWN: {
                return new float[] { RotationUtil.mc.player.rotationYaw, 90.0f };
            }
            case UP: {
                return new float[] { RotationUtil.mc.player.rotationYaw, -90.0f };
            }
            case NORTH: {
                return new float[] { 180.0f, 0.0f };
            }
            case SOUTH: {
                return new float[] { 0.0f, 0.0f };
            }
            case WEST: {
                return new float[] { 90.0f, 0.0f };
            }
            default: {
                return new float[] { 270.0f, 0.0f };
            }
        }
    }
}

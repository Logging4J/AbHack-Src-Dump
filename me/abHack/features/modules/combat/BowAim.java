//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.item.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.entity.*;
import me.abHack.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import java.util.*;
import net.minecraft.util.math.*;

public class BowAim extends Module
{
    public final /* synthetic */ Setting<Boolean> fastbow;
    public final /* synthetic */ Setting<Boolean> packet;
    
    @Override
    public void onUpdate() {
        if (BowAim.mc.player.getHeldItemMainhand().getItem() instanceof ItemBow && BowAim.mc.player.isHandActive() && BowAim.mc.player.getItemInUseMaxCount() >= 3) {
            Object o = null;
            float n = 100.0f;
            for (final EntityPlayer entityPlayer : BowAim.mc.world.playerEntities) {
                if (!(entityPlayer instanceof EntityPlayerSP) && !OyVey.friendManager.isFriend(entityPlayer.getName())) {
                    final float getDistance;
                    if ((getDistance = entityPlayer.getDistance((Entity)BowAim.mc.player)) >= n) {
                        continue;
                    }
                    n = getDistance;
                    o = entityPlayer;
                }
            }
            if (o != null) {
                final float[] calcAngle = calcAngle(interpolateEntity((Entity)BowAim.mc.player, BowAim.mc.getRenderPartialTicks()), interpolateEntity((Entity)o, BowAim.mc.getRenderPartialTicks()));
                if (this.packet.getValue()) {
                    BowAim.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(calcAngle[0], calcAngle[1], BowAim.mc.player.onGround));
                }
                else {
                    BowAim.mc.player.rotationYaw = calcAngle[0];
                    BowAim.mc.player.rotationPitch = calcAngle[1];
                }
            }
        }
        if (BowAim.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && BowAim.mc.player.isHandActive() && BowAim.mc.player.getItemInUseMaxCount() >= 3 && this.fastbow.getValue()) {
            BowAim.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, BowAim.mc.player.getHorizontalFacing()));
            BowAim.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(BowAim.mc.player.getActiveHand()));
            BowAim.mc.player.stopActiveHand();
        }
    }
    
    public BowAim() {
        super("BowAim", "Automatically aims your bow at your opponent", Category.COMBAT, true, false, false);
        this.packet = (Setting<Boolean>)this.register(new Setting("PacketRotate", (T)true));
        this.fastbow = (Setting<Boolean>)this.register(new Setting("FastBow", (T)true));
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static float[] calcAngle(final Vec3d vec3d, final Vec3d vec3d2) {
        final double x = vec3d2.x - vec3d.x;
        final double y = (vec3d2.y - vec3d.y) * -1.0;
        final double y2 = vec3d2.z - vec3d.z;
        return new float[] { (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y2, x)) - 90.0), (float)MathHelper.wrapDegrees(Math.toDegrees(Math.atan2(y, MathHelper.sqrt(x * x + y2 * y2)))) };
    }
}

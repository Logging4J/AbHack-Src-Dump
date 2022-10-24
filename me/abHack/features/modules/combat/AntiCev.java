//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.client.entity.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import me.abHack.util.*;
import net.minecraft.init.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class AntiCev extends Module
{
    public /* synthetic */ Setting<Boolean> packet;
    public /* synthetic */ Setting<Boolean> rotate;
    private /* synthetic */ boolean isSneaking;
    private /* synthetic */ boolean rotating;
    private /* synthetic */ float yaw;
    private /* synthetic */ float pitch;
    
    private void rotateTo(final Entity entity) {
        if (this.rotate.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(AntiCev.mc.player.getPositionEyes(AntiCev.mc.getRenderPartialTicks()), entity.getPositionVector());
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.rotating = true;
        }
    }
    
    private void main() {
        final Vec3d getPositionVector = AntiCev.mc.player.getPositionVector();
        if (this.checkTrap(getPositionVector, EntityUtil.getOffsets(2, false)) && this.checkTrap(getPositionVector, EntityUtil.getVarOffsets(0, 2, 0)) && this.checkCrystal(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0)) != null) {
            final EntityPlayerSP player = AntiCev.mc.player;
            ++player.motionY;
            if (this.packet.getValue()) {
                this.rotateTo(this.checkCrystal(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0)));
                EntityUtil.attackEntity(this.checkCrystal(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0)), true);
            }
            else {
                this.rotateTo(this.checkCrystal(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0)));
                EntityUtil.attackEntity(this.checkCrystal(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0)), false);
            }
            this.rotateToPos(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0));
            this.place(getPositionVector, EntityUtil.getVarOffsets(0, 3, 0));
        }
    }
    
    private void rotateToPos(final Vec3d vec3d, final Vec3d[] array) {
        if (this.rotate.getValue()) {
            for (final Vec3d vec3d2 : array) {
                final BlockPos add = new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z);
                final float[] calcAngle = MathUtil.calcAngle(AntiCev.mc.player.getPositionEyes(AntiCev.mc.getRenderPartialTicks()), new Vec3d((double)(add.getX() + 0.5f), (double)(add.getY() - 0.5f), (double)(add.getZ() + 0.5f)));
                this.yaw = calcAngle[0];
                this.pitch = calcAngle[1];
                this.rotating = true;
            }
        }
    }
    
    @Override
    public void onDisable() {
        this.rotating = false;
        this.isSneaking = EntityUtil.stopSneaking(this.isSneaking);
    }
    
    private void place(final Vec3d vec3d, final Vec3d[] array) {
        for (final Vec3d vec3d2 : array) {
            final BlockPos add = new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z);
            final int currentItem = AntiCev.mc.player.inventory.currentItem;
            AntiCev.mc.player.inventory.currentItem = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            AntiCev.mc.playerController.updateController();
            this.isSneaking = BlockUtil.placeBlock(add, EnumHand.MAIN_HAND, false, this.packet.getValue(), true);
            AntiCev.mc.player.inventory.currentItem = currentItem;
            AntiCev.mc.playerController.updateController();
        }
    }
    
    private boolean checkTrap(final Vec3d vec3d, final Vec3d[] array) {
        for (final Vec3d vec3d2 : array) {
            if (EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z)).getBlock() == Blocks.OBSIDIAN) {
                return true;
            }
        }
        return false;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (send.getStage() == 0 && this.rotate.getValue() && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.rotating = false;
        }
    }
    
    Entity checkCrystal(final Vec3d vec3d, final Vec3d[] array) {
        Entity entity = null;
        for (final Vec3d vec3d2 : array) {
            for (final Entity entity2 : AntiCev.mc.world.getEntitiesWithinAABB((Class)Entity.class, new AxisAlignedBB(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z)))) {
                if (entity2 instanceof EntityEnderCrystal && entity == null) {
                    entity = entity2;
                }
            }
        }
        return entity;
    }
    
    public AntiCev() {
        super("AntiCev", "AntiCev", Category.COMBAT, true, false, false);
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)true));
        this.packet = (Setting<Boolean>)this.register(new Setting("Packet", (T)true));
        this.yaw = 0.0f;
        this.pitch = 0.0f;
        this.rotating = false;
    }
    
    @Override
    public void onTick() {
        if (!fullNullCheck() && InventoryUtil.findHotbarBlock(BlockObsidian.class) != -1) {
            this.main();
        }
    }
}

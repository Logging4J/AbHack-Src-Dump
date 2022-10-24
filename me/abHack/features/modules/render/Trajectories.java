//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.event.events.*;
import net.minecraft.entity.player.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.*;
import net.minecraft.init.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import org.lwjgl.util.glu.*;
import net.minecraft.item.*;
import java.util.*;
import com.google.common.base.*;

public class Trajectories extends Module
{
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (Trajectories.mc.world == null || Trajectories.mc.player == null) {
            return;
        }
        this.drawTrajectories((EntityPlayer)Trajectories.mc.player, render3DEvent.getPartialTicks());
    }
    
    public void enableGL3D(final float n) {
        GL11.glDisable(3008);
        GL11.glEnable(3042);
        GL11.glBlendFunc(770, 771);
        GL11.glDisable(3553);
        GL11.glDisable(2929);
        GL11.glDepthMask(false);
        GL11.glEnable(2884);
        Trajectories.mc.entityRenderer.disableLightmap();
        GL11.glEnable(2848);
        GL11.glHint(3154, 4354);
        GL11.glHint(3155, 4354);
        GL11.glLineWidth(n);
    }
    
    public Trajectories() {
        super("Trajectories", "Shows the way of projectiles.", Module.Category.RENDER, false, false, false);
    }
    
    private void drawTrajectories(final EntityPlayer entityPlayer, final float n) {
        final double n2 = entityPlayer.lastTickPosX + (entityPlayer.posX - entityPlayer.lastTickPosX) * n;
        final double n3 = entityPlayer.lastTickPosY + (entityPlayer.posY - entityPlayer.lastTickPosY) * n;
        final double n4 = entityPlayer.lastTickPosZ + (entityPlayer.posZ - entityPlayer.lastTickPosZ) * n;
        entityPlayer.getHeldItem(EnumHand.MAIN_HAND);
        if (Trajectories.mc.gameSettings.thirdPersonView != 0 || (!(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemBow) && !(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFishingRod) && !(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEnderPearl) && !(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemEgg) && !(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemSnowball) && !(entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemExpBottle))) {
            return;
        }
        GL11.glPushMatrix();
        final Item getItem = entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem();
        double n5 = n2 - MathHelper.cos(entityPlayer.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        double n6 = n3 + entityPlayer.getEyeHeight() - 0.1000000014901161;
        double n7 = n4 - MathHelper.sin(entityPlayer.rotationYaw / 180.0f * 3.1415927f) * 0.16f;
        final double n8 = -MathHelper.sin(entityPlayer.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(entityPlayer.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
        final double n9 = -MathHelper.sin(entityPlayer.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
        final double n10 = MathHelper.cos(entityPlayer.rotationYaw / 180.0f * 3.1415927f) * MathHelper.cos(entityPlayer.rotationPitch / 180.0f * 3.1415927f) * ((getItem instanceof ItemBow) ? 1.0 : 0.4);
        final float n11 = (72000 - entityPlayer.getItemInUseCount()) / 20.0f;
        float n12 = (n11 * n11 + n11 * 2.0f) / 3.0f;
        if (n12 > 1.0f) {
            n12 = 1.0f;
        }
        final float sqrt = MathHelper.sqrt(n8 * n8 + n9 * n9 + n10 * n10);
        final double n13 = n8 / sqrt;
        final double n14 = n9 / sqrt;
        final double n15 = n10 / sqrt;
        final float n16 = (getItem instanceof ItemBow) ? (n12 * 2.0f) : ((getItem instanceof ItemFishingRod) ? 1.25f : ((entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.9f : 1.0f));
        double n17 = n13 * (n16 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
        double n18 = n14 * (n16 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
        double n19 = n15 * (n16 * ((getItem instanceof ItemFishingRod) ? 0.75f : ((entityPlayer.getHeldItem(EnumHand.MAIN_HAND).getItem() == Items.EXPERIENCE_BOTTLE) ? 0.75f : 1.5f)));
        this.enableGL3D(2.0f);
        if (n12 > 0.6f) {
            GlStateManager.color(0.0f, 1.0f, 0.0f, 1.0f);
        }
        else {
            GlStateManager.color(0.8f, 0.5f, 0.0f, 1.0f);
        }
        GL11.glEnable(2848);
        final float n20 = (float)((getItem instanceof ItemBow) ? 0.3 : 0.25);
        int n21 = 0;
        EntityPlayer entityPlayer2 = null;
        RayTraceResult rayTraceResult = null;
        while (n21 == 0 && n6 > 0.0) {
            final Vec3d vec3d = new Vec3d(n5, n6, n7);
            final Vec3d vec3d2 = new Vec3d(n5 + n17, n6 + n18, n7 + n19);
            final RayTraceResult rayTraceBlocks = Trajectories.mc.world.rayTraceBlocks(vec3d, vec3d2, false, true, false);
            if (rayTraceBlocks != null && rayTraceBlocks.typeOfHit != RayTraceResult.Type.MISS) {
                rayTraceResult = rayTraceBlocks;
                n21 = 1;
            }
            for (final Entity entity : this.getEntitiesWithinAABB(new AxisAlignedBB(n5 - n20, n6 - n20, n7 - n20, n5 + n20, n6 + n20, n7 + n20).offset(n17, n18, n19).expand(1.0, 1.0, 1.0))) {
                if (entity.canBeCollidedWith()) {
                    if (entity == entityPlayer) {
                        continue;
                    }
                    final float n22 = 0.3f;
                    final RayTraceResult calculateIntercept = entity.getEntityBoundingBox().expand((double)n22, (double)n22, (double)n22).calculateIntercept(vec3d, vec3d2);
                    if (calculateIntercept == null) {
                        continue;
                    }
                    n21 = 1;
                    entityPlayer2 = (EntityPlayer)entity;
                    rayTraceResult = calculateIntercept;
                }
            }
            if (entityPlayer2 != null) {
                GlStateManager.color(1.0f, 0.0f, 0.0f, 1.0f);
            }
            n5 += n17;
            n6 += n18;
            n7 += n19;
            final float n23 = 0.99f;
            n17 *= n23;
            final double n24 = n18 * n23;
            n19 *= n23;
            n18 = n24 - ((getItem instanceof ItemBow) ? 0.05 : 0.03);
        }
        if (rayTraceResult != null && rayTraceResult.typeOfHit == RayTraceResult.Type.BLOCK) {
            GlStateManager.translate(n5 - n2, n6 - n3, n7 - n4);
            final int getIndex = rayTraceResult.sideHit.getIndex();
            if (getIndex == 2) {
                GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
            }
            else if (getIndex == 3) {
                GlStateManager.rotate(90.0f, 1.0f, 0.0f, 0.0f);
            }
            else if (getIndex == 4) {
                GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
            }
            else if (getIndex == 5) {
                GlStateManager.rotate(90.0f, 0.0f, 0.0f, 1.0f);
            }
            final Cylinder cylinder = new Cylinder();
            GlStateManager.rotate(-90.0f, 1.0f, 0.0f, 0.0f);
            cylinder.setDrawStyle(100011);
            if (entityPlayer2 != null) {
                GlStateManager.color(0.0f, 0.0f, 0.0f, 1.0f);
                GL11.glLineWidth(2.5f);
                cylinder.draw(0.6f, 0.3f, 0.0f, 4, 1);
                GL11.glLineWidth(0.1f);
                GlStateManager.color(1.0f, 0.0f, 0.0f, 1.0f);
            }
            cylinder.draw(0.6f, 0.3f, 0.0f, 4, 1);
        }
        this.disableGL3D();
        GL11.glPopMatrix();
    }
    
    private List getEntitiesWithinAABB(final AxisAlignedBB axisAlignedBB) {
        final ArrayList list = new ArrayList();
        final int floor = MathHelper.floor((axisAlignedBB.minX - 2.0) / 16.0);
        final int floor2 = MathHelper.floor((axisAlignedBB.maxX + 2.0) / 16.0);
        final int floor3 = MathHelper.floor((axisAlignedBB.minZ - 2.0) / 16.0);
        final int floor4 = MathHelper.floor((axisAlignedBB.maxZ + 2.0) / 16.0);
        for (int i = floor; i <= floor2; ++i) {
            for (int j = floor3; j <= floor4; ++j) {
                if (Trajectories.mc.world.getChunkProvider().getLoadedChunk(i, j) != null) {
                    Trajectories.mc.world.getChunk(i, j).getEntitiesWithinAABBForEntity((Entity)Trajectories.mc.player, axisAlignedBB, (List)list, (Predicate)null);
                }
            }
        }
        return list;
    }
    
    public void disableGL3D() {
        GL11.glEnable(3553);
        GL11.glEnable(2929);
        GL11.glDisable(3042);
        GL11.glEnable(3008);
        GL11.glDepthMask(true);
        GL11.glCullFace(1029);
        GL11.glDisable(2848);
        GL11.glHint(3154, 4352);
        GL11.glHint(3155, 4352);
    }
}

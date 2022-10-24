//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.client.entity.*;
import net.minecraft.client.*;
import net.minecraftforge.common.*;
import java.awt.*;
import net.minecraftforge.client.event.*;
import org.lwjgl.opengl.*;
import me.abHack.features.modules.render.*;
import net.minecraft.util.math.*;
import net.minecraft.client.model.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;

public class TotemPopChams
{
    /* synthetic */ double alphaLine;
    /* synthetic */ EntityOtherPlayerMP player;
    /* synthetic */ Long startTime;
    /* synthetic */ ModelPlayer playerModel;
    /* synthetic */ double alphaFill;
    private static final /* synthetic */ Minecraft mc;
    
    public TotemPopChams(final EntityOtherPlayerMP player, final ModelPlayer playerModel, final Long startTime, final double n, final double n2) {
        MinecraftForge.EVENT_BUS.register((Object)this);
        this.player = player;
        this.playerModel = playerModel;
        this.startTime = startTime;
        this.alphaFill = n;
        this.alphaLine = n;
    }
    
    public static Color newAlpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        if (this.player == null || TotemPopChams.mc.world == null || TotemPopChams.mc.player == null) {
            return;
        }
        GL11.glLineWidth(1.0f);
        final Color color = new Color((int)PopChams.rL.getValue(), (int)PopChams.gL.getValue(), (int)PopChams.bL.getValue(), (int)PopChams.aL.getValue());
        final Color color2 = new Color((int)PopChams.rF.getValue(), (int)PopChams.gF.getValue(), (int)PopChams.bF.getValue(), (int)PopChams.aF.getValue());
        int alpha = color.getAlpha();
        int alpha2 = color2.getAlpha();
        final long n = System.currentTimeMillis() - this.startTime - ((Number)PopChams.fadestart.getValue()).longValue();
        if (System.currentTimeMillis() - this.startTime > ((Number)PopChams.fadestart.getValue()).longValue()) {
            final double n2 = -MathHelper.clamp(this.normalize((double)n, 0.0, ((Number)PopChams.fadetime.getValue()).doubleValue()), 0.0, 1.0) + 1.0;
            alpha *= (int)n2;
            alpha2 *= (int)n2;
        }
        final Color alpha3 = newAlpha(color, alpha);
        final Color alpha4 = newAlpha(color2, alpha2);
        if (this.player != null && this.playerModel != null) {
            PopChams.prepareGL();
            GL11.glPushAttrib(1048575);
            GL11.glEnable(2881);
            GL11.glEnable(2848);
            if (this.alphaFill > 1.0) {
                this.alphaFill -= (float)PopChams.fadetime.getValue();
            }
            final Color color3 = new Color(alpha4.getRed(), alpha4.getGreen(), alpha4.getBlue(), (int)this.alphaFill);
            if (this.alphaLine > 1.0) {
                this.alphaLine -= (float)PopChams.fadetime.getValue();
            }
            final Color color4 = new Color(alpha3.getRed(), alpha3.getGreen(), alpha3.getBlue(), (int)this.alphaLine);
            glColor(color3);
            GL11.glPolygonMode(1032, 6914);
            renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            glColor(color4);
            GL11.glPolygonMode(1032, 6913);
            renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1.0f);
            GL11.glPolygonMode(1032, 6914);
            GL11.glPopAttrib();
            PopChams.releaseGL();
        }
    }
    
    public static void prepareTranslate(final EntityLivingBase entityLivingBase, final double n, final double n2, final double n3) {
        renderLivingAt(n - TotemPopChams.mc.getRenderManager().viewerPosX, n2 - TotemPopChams.mc.getRenderManager().viewerPosY, n3 - TotemPopChams.mc.getRenderManager().viewerPosZ);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static float interpolateRotation(final float n, final float n2, final float n3) {
        float n4;
        for (n4 = n2 - n; n4 < -180.0f; n4 += 360.0f) {}
        while (n4 >= 180.0f) {
            n4 -= 360.0f;
        }
        return n + n3 * n4;
    }
    
    public static void renderLivingAt(final double n, final double n2, final double n3) {
        GlStateManager.translate((float)n, (float)n2, (float)n3);
    }
    
    static {
        mc = Minecraft.getMinecraft();
    }
    
    public static void renderEntity(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (TotemPopChams.mc.getRenderManager() == null) {
            return;
        }
        final float getRenderPartialTicks = TotemPopChams.mc.getRenderPartialTicks();
        final double n7 = entityLivingBase.posX - TotemPopChams.mc.getRenderManager().viewerPosX;
        double n8 = entityLivingBase.posY - TotemPopChams.mc.getRenderManager().viewerPosY;
        final double n9 = entityLivingBase.posZ - TotemPopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            n8 -= 0.125;
        }
        final float n10 = interpolateRotation(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, getRenderPartialTicks) - interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, getRenderPartialTicks);
        final float n11 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * getRenderPartialTicks;
        renderLivingAt(n7, n8, n9);
        final float handleRotationFloat = handleRotationFloat(entityLivingBase, getRenderPartialTicks);
        prepareRotations(entityLivingBase);
        final float prepareScale = prepareScale(entityLivingBase, n6);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, n, n2, getRenderPartialTicks);
        modelBase.setRotationAngles(n, n2, handleRotationFloat, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, prepareScale, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, n, n2, handleRotationFloat, entityLivingBase.rotationYawHead, entityLivingBase.rotationPitch, prepareScale);
        GlStateManager.popMatrix();
    }
    
    public static float handleRotationFloat(final EntityLivingBase entityLivingBase, final float n) {
        return 0.0f;
    }
    
    public static void prepareRotations(final EntityLivingBase entityLivingBase) {
        GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
    }
    
    public static float prepareScale(final EntityLivingBase entityLivingBase, final float n) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        GlStateManager.scale(n + (entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX), (double)(n * entityLivingBase.height), n + (entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ));
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        return 0.0625f;
    }
    
    double normalize(final double n, final double n2, final double n3) {
        return (n - n2) / (n3 - n2);
    }
}

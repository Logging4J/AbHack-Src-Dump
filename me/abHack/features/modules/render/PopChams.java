//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.*;
import net.minecraftforge.client.event.*;
import me.abHack.features.modules.client.*;
import net.minecraft.util.math.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.world.*;
import com.mojang.authlib.*;
import me.abHack.util.*;

public class PopChams extends Module
{
    public static /* synthetic */ Setting<Integer> rF;
    public static /* synthetic */ Setting<Float> fadetime;
    public static /* synthetic */ Setting<Integer> fadestart;
    /* synthetic */ double alphaLine;
    public static /* synthetic */ Setting<Integer> aL;
    /* synthetic */ Long startTime;
    public static /* synthetic */ Setting<Boolean> self;
    /* synthetic */ EntityOtherPlayerMP player;
    public static /* synthetic */ Setting<Integer> bF;
    public static /* synthetic */ Setting<Boolean> outlinerainbow;
    public static /* synthetic */ Setting<Boolean> fillrainbow;
    public static /* synthetic */ Setting<Integer> gL;
    public static /* synthetic */ Setting<ElevatorMode> elevatorMode;
    public static /* synthetic */ Setting<Boolean> onlyOneEsp;
    public static /* synthetic */ Setting<Integer> rL;
    public static /* synthetic */ Setting<Integer> aF;
    public static /* synthetic */ Setting<Integer> gF;
    /* synthetic */ ModelPlayer playerModel;
    public static /* synthetic */ Setting<Boolean> elevator;
    public static /* synthetic */ Setting<Integer> bL;
    /* synthetic */ double alphaFill;
    
    public static float handleRotationFloat(final EntityLivingBase entityLivingBase, final float n) {
        return 0.0f;
    }
    
    public static float prepareScale(final EntityLivingBase entityLivingBase, final float n) {
        GlStateManager.enableRescaleNormal();
        GlStateManager.scale(-1.0f, -1.0f, 1.0f);
        GlStateManager.scale(n + (entityLivingBase.getRenderBoundingBox().maxX - entityLivingBase.getRenderBoundingBox().minX), (double)(n * entityLivingBase.height), n + (entityLivingBase.getRenderBoundingBox().maxZ - entityLivingBase.getRenderBoundingBox().minZ));
        GlStateManager.translate(0.0f, -1.501f, 0.0f);
        return 0.0625f;
    }
    
    public static Color newAlpha(final Color color, final int a) {
        return new Color(color.getRed(), color.getGreen(), color.getBlue(), a);
    }
    
    public static void renderLivingAt(final double n, final double n2, final double n3) {
        GlStateManager.translate((float)n, (float)n2, (float)n3);
    }
    
    public static void glColor(final Color color) {
        GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
    }
    
    public static void prepareTranslate(final EntityLivingBase entityLivingBase, final double n, final double n2, final double n3) {
        renderLivingAt(n - PopChams.mc.getRenderManager().viewerPosX, n2 - PopChams.mc.getRenderManager().viewerPosY, n3 - PopChams.mc.getRenderManager().viewerPosZ);
    }
    
    public PopChams() {
        super("PopChams", "Pop rendering", Module.Category.RENDER, true, false, false);
        PopChams.self = (Setting<Boolean>)this.register(new Setting("Render Own Pops", (T)true));
        PopChams.elevator = (Setting<Boolean>)this.register(new Setting("Travel", (T)true));
        PopChams.elevatorMode = (Setting<ElevatorMode>)this.register(new Setting("Elevator", (T)ElevatorMode.UP, p0 -> PopChams.elevator.getValue()));
        PopChams.rL = (Setting<Integer>)this.register(new Setting("Outline Red", (T)30, (T)0, (T)255));
        PopChams.bL = (Setting<Integer>)this.register(new Setting("Outline Green", (T)167, (T)0, (T)255));
        PopChams.gL = (Setting<Integer>)this.register(new Setting("Outline Blue", (T)255, (T)0, (T)255));
        PopChams.aL = (Setting<Integer>)this.register(new Setting("Outline Alpha", (T)255, (T)0, (T)255));
        PopChams.outlinerainbow = (Setting<Boolean>)this.register(new Setting("Outline Rainbow", (T)true));
        PopChams.rF = (Setting<Integer>)this.register(new Setting("Fill Red", (T)30, (T)0, (T)255));
        PopChams.bF = (Setting<Integer>)this.register(new Setting("Fill Green", (T)167, (T)0, (T)255));
        PopChams.gF = (Setting<Integer>)this.register(new Setting("Fill Blue", (T)255, (T)0, (T)255));
        PopChams.aF = (Setting<Integer>)this.register(new Setting("Fill Alpha", (T)140, (T)0, (T)255));
        PopChams.fillrainbow = (Setting<Boolean>)this.register(new Setting("Fill Rainbow", (T)true));
        PopChams.fadestart = (Setting<Integer>)this.register(new Setting("Fade Start", (T)0, (T)0, (T)255));
        PopChams.fadetime = (Setting<Float>)this.register(new Setting("Fade Time", (T)0.5f, (T)0.0f, (T)2.0f));
        PopChams.onlyOneEsp = (Setting<Boolean>)this.register(new Setting("Only Render One", (T)true));
    }
    
    public static float interpolateRotation(final float n, final float n2, final float n3) {
        float n4;
        for (n4 = n2 - n; n4 < -180.0f; n4 += 360.0f) {}
        while (n4 >= 180.0f) {
            n4 -= 360.0f;
        }
        return n + n3 * n4;
    }
    
    public static void renderEntity(final EntityLivingBase entityLivingBase, final ModelBase modelBase, final float n, final float n2, final float n3, final float n4, final float n5, final int n6) {
        if (PopChams.mc.getRenderManager() == null) {
            return;
        }
        final float getRenderPartialTicks = PopChams.mc.getRenderPartialTicks();
        final double n7 = entityLivingBase.posX - PopChams.mc.getRenderManager().viewerPosX;
        double n8 = entityLivingBase.posY - PopChams.mc.getRenderManager().viewerPosY;
        final double n9 = entityLivingBase.posZ - PopChams.mc.getRenderManager().viewerPosZ;
        GlStateManager.pushMatrix();
        if (entityLivingBase.isSneaking()) {
            n8 -= 0.125;
        }
        final float n10 = interpolateRotation(entityLivingBase.prevRotationYawHead, entityLivingBase.rotationYawHead, getRenderPartialTicks) - interpolateRotation(entityLivingBase.prevRenderYawOffset, entityLivingBase.renderYawOffset, getRenderPartialTicks);
        final float n11 = entityLivingBase.prevRotationPitch + (entityLivingBase.rotationPitch - entityLivingBase.prevRotationPitch) * getRenderPartialTicks;
        renderLivingAt(n7, n8, n9);
        final float handleRotationFloat = handleRotationFloat(entityLivingBase, getRenderPartialTicks);
        prepareRotations(entityLivingBase);
        final float prepareScale = prepareScale(entityLivingBase, (float)n6);
        GlStateManager.enableAlpha();
        modelBase.setLivingAnimations(entityLivingBase, n, n2, getRenderPartialTicks);
        modelBase.setRotationAngles(n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale, (Entity)entityLivingBase);
        modelBase.render((Entity)entityLivingBase, n, n2, handleRotationFloat, entityLivingBase.rotationYaw, entityLivingBase.rotationPitch, prepareScale);
        GlStateManager.popMatrix();
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
    }
    
    @SubscribeEvent
    public void onRenderWorld(final RenderWorldLastEvent renderWorldLastEvent) {
        if (PopChams.onlyOneEsp.getValue()) {
            if (this.player == null || PopChams.mc.world == null || PopChams.mc.player == null) {
                return;
            }
            if (PopChams.elevator.getValue()) {
                if (PopChams.elevatorMode.getValue() == ElevatorMode.UP) {
                    final EntityOtherPlayerMP player = this.player;
                    player.posY += 0.05f * renderWorldLastEvent.getPartialTicks();
                }
                else if (PopChams.elevatorMode.getValue() == ElevatorMode.DOWN) {
                    final EntityOtherPlayerMP player2 = this.player;
                    player2.posY -= 0.05f * renderWorldLastEvent.getPartialTicks();
                }
            }
            GL11.glLineWidth(1.0f);
            final Color color = PopChams.outlinerainbow.getValue() ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(PopChams.rL.getValue(), PopChams.bL.getValue(), PopChams.gL.getValue(), PopChams.aL.getValue());
            final Color color2 = PopChams.fillrainbow.getValue() ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(PopChams.rF.getValue(), PopChams.bF.getValue(), PopChams.gF.getValue(), PopChams.aF.getValue());
            int alpha = color.getAlpha();
            int alpha2 = color2.getAlpha();
            final long n = System.currentTimeMillis() - this.startTime - PopChams.fadestart.getValue().longValue();
            if (System.currentTimeMillis() - this.startTime > PopChams.fadestart.getValue().longValue()) {
                final double n2 = -MathHelper.clamp(this.normalize((double)n, 0.0, PopChams.fadetime.getValue().doubleValue()), 0.0, 1.0) + 1.0;
                alpha *= (int)n2;
                alpha2 *= (int)n2;
            }
            final Color alpha3 = newAlpha(color, alpha);
            final Color alpha4 = newAlpha(color2, alpha2);
            if (this.player != null && this.playerModel != null) {
                prepareGL();
                GL11.glPushAttrib(1048575);
                GL11.glEnable(2881);
                GL11.glEnable(2848);
                if (this.alphaFill > 1.0) {
                    this.alphaFill -= PopChams.fadetime.getValue();
                }
                final Color color3 = new Color(alpha4.getRed(), alpha4.getGreen(), alpha4.getBlue(), (int)this.alphaFill);
                if (this.alphaLine > 1.0) {
                    this.alphaLine -= PopChams.fadetime.getValue();
                }
                final Color color4 = new Color(alpha3.getRed(), alpha3.getGreen(), alpha3.getBlue(), (int)this.alphaLine);
                glColor(color3);
                GL11.glPolygonMode(1032, 6914);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1);
                glColor(color4);
                GL11.glPolygonMode(1032, 6913);
                renderEntity((EntityLivingBase)this.player, (ModelBase)this.playerModel, this.player.limbSwing, this.player.limbSwingAmount, (float)this.player.ticksExisted, this.player.rotationYawHead, this.player.rotationPitch, 1);
                GL11.glPolygonMode(1032, 6914);
                GL11.glPopAttrib();
                releaseGL();
            }
        }
    }
    
    public static void prepareRotations(final EntityLivingBase entityLivingBase) {
        GlStateManager.rotate(180.0f - entityLivingBase.rotationYaw, 0.0f, 1.0f, 0.0f);
    }
    
    double normalize(final double n, final double n2, final double n3) {
        return (n - n2) / (n3 - n2);
    }
    
    @SubscribeEvent
    public void onUpdate(final PacketEvent.Receive receive) {
        final SPacketEntityStatus sPacketEntityStatus;
        if (receive.getPacket() instanceof SPacketEntityStatus && (sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket()).getOpCode() == 35 && sPacketEntityStatus.getEntity((World)PopChams.mc.world) != null && (PopChams.self.getValue() || sPacketEntityStatus.getEntity((World)PopChams.mc.world).getEntityId() != PopChams.mc.player.getEntityId())) {
            this.player = new EntityOtherPlayerMP((World)PopChams.mc.world, new GameProfile(PopChams.mc.player.getUniqueID(), ""));
            this.player.copyLocationAndAnglesFrom(sPacketEntityStatus.getEntity((World)PopChams.mc.world));
            this.playerModel = new ModelPlayer(0.0f, false);
            this.startTime = System.currentTimeMillis();
            this.playerModel.bipedHead.showModel = false;
            this.playerModel.bipedBody.showModel = false;
            this.playerModel.bipedLeftArmwear.showModel = false;
            this.playerModel.bipedLeftLegwear.showModel = false;
            this.playerModel.bipedRightArmwear.showModel = false;
            this.playerModel.bipedRightLegwear.showModel = false;
            this.alphaFill = PopChams.aF.getValue();
            this.alphaLine = PopChams.aL.getValue();
            if (!PopChams.onlyOneEsp.getValue()) {
                final TotemPopChams totemPopChams = new TotemPopChams(this.player, this.playerModel, this.startTime, this.alphaFill, this.alphaLine);
            }
        }
    }
    
    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(1.5f);
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
    }
    
    public enum ElevatorMode
    {
        DOWN, 
        UP;
    }
}

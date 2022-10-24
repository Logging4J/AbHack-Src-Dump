//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import org.lwjgl.opengl.*;
import me.abHack.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.abHack.util.*;
import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.math.*;
import java.util.*;

public class Ranges extends Module
{
    private final /* synthetic */ Setting<Boolean> circle;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> ownSphere;
    private final /* synthetic */ Setting<Boolean> hitSpheres;
    private final /* synthetic */ Setting<Double> radius;
    private final /* synthetic */ Setting<Boolean> raytrace;
    
    public Ranges() {
        super("Ranges", "Draws a circle around the player.", Module.Category.RENDER, false, false, false);
        this.hitSpheres = (Setting<Boolean>)this.register(new Setting("HitSpheres", (T)false));
        this.circle = (Setting<Boolean>)this.register(new Setting("Circle", (T)true));
        this.ownSphere = (Setting<Boolean>)this.register(new Setting("OwnSphere", (T)Boolean.FALSE, p0 -> this.hitSpheres.getValue()));
        this.raytrace = (Setting<Boolean>)this.register(new Setting("RayTrace", (T)Boolean.FALSE, p0 -> this.circle.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.5f, (T)0.1f, (T)5.0f));
        this.radius = (Setting<Double>)this.register(new Setting("Radius", (T)4.5, (T)0.1, (T)8.0));
    }
    
    public void onUpdate() {
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.circle.getValue()) {
            GlStateManager.pushMatrix();
            GlStateManager.enableBlend();
            GlStateManager.disableTexture2D();
            GlStateManager.enableDepth();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            final RenderManager getRenderManager = Ranges.mc.getRenderManager();
            float hue = System.currentTimeMillis() % 7200L / 7200.0f;
            Color color = new Color(Color.HSBtoRGB(hue, 1.0f, 1.0f));
            final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
            final double n = Ranges.mc.player.lastTickPosX + (Ranges.mc.player.posX - Ranges.mc.player.lastTickPosX) * render3DEvent.getPartialTicks() - getRenderManager.renderPosX;
            final double n2 = Ranges.mc.player.lastTickPosY + (Ranges.mc.player.posY - Ranges.mc.player.lastTickPosY) * render3DEvent.getPartialTicks() - getRenderManager.renderPosY;
            final double n3 = Ranges.mc.player.lastTickPosZ + (Ranges.mc.player.posZ - Ranges.mc.player.lastTickPosZ) * render3DEvent.getPartialTicks() - getRenderManager.renderPosZ;
            GL11.glLineWidth((float)this.lineWidth.getValue());
            GL11.glBegin(1);
            for (int i = 0; i <= 360; ++i) {
                final Vec3d e = new Vec3d(n + Math.sin(i * 3.141592653589793 / 180.0) * this.radius.getValue(), n2 + 0.1, n3 + Math.cos(i * 3.141592653589793 / 180.0) * this.radius.getValue());
                final RayTraceResult rayTraceBlocks = Ranges.mc.world.rayTraceBlocks(new Vec3d(Ranges.mc.player.posX, Ranges.mc.player.posY + Ranges.mc.player.getEyeHeight(), Ranges.mc.player.posZ), e, false, false, true);
                if (rayTraceBlocks != null && this.raytrace.getValue()) {
                    OyVey.LOGGER.info("raytrace was not null");
                    list.add(rayTraceBlocks.hitVec);
                }
                else {
                    list.add(e);
                }
            }
            for (int j = 0; j < list.size() - 1; ++j) {
                GL11.glColor4f(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
                GL11.glVertex3d(list.get(j).x, list.get(j).y, list.get(j).z);
                GL11.glVertex3d(list.get(j + 1).x, list.get(j + 1).y, list.get(j + 1).z);
                color = new Color(Color.HSBtoRGB(hue += 0.0027777778f, 1.0f, 1.0f));
            }
            GL11.glEnd();
            GlStateManager.resetColor();
            GlStateManager.disableDepth();
            GlStateManager.enableTexture2D();
            GlStateManager.disableBlend();
            GlStateManager.popMatrix();
        }
        if (this.hitSpheres.getValue()) {
            for (final EntityPlayer entityPlayer : Ranges.mc.world.playerEntities) {
                if (entityPlayer != null) {
                    if (entityPlayer.equals((Object)Ranges.mc.player) && !this.ownSphere.getValue()) {
                        continue;
                    }
                    final Vec3d interpolateEntity = EntityUtil.interpolateEntity((Entity)entityPlayer, render3DEvent.getPartialTicks());
                    if (OyVey.friendManager.isFriend(entityPlayer.getName())) {
                        GL11.glColor4f(0.15f, 0.15f, 1.0f, 1.0f);
                    }
                    else if (Ranges.mc.player.getDistance((Entity)entityPlayer) >= 64.0f) {
                        GL11.glColor4f(0.0f, 1.0f, 0.0f, 1.0f);
                    }
                    else {
                        GL11.glColor4f(1.0f, Ranges.mc.player.getDistance((Entity)entityPlayer) / 150.0f, 0.0f, 1.0f);
                    }
                    RenderUtil.drawSphere(interpolateEntity.x, interpolateEntity.y, interpolateEntity.z, this.radius.getValue().floatValue(), 20, 15);
                }
            }
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.*;
import org.lwjgl.opengl.*;
import net.minecraft.util.math.*;
import net.minecraft.client.model.*;
import me.abHack.event.events.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import java.util.*;

public class Skeleton extends Module
{
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Boolean> colorFriends;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Boolean> rainbow;
    private static /* synthetic */ Skeleton INSTANCE;
    private final /* synthetic */ Map<EntityPlayer, float[][]> rotationList;
    
    private void renderSkeleton(final EntityPlayer entityPlayer, final float[][] array, final Color color) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos((Entity)entityPlayer, Skeleton.mc.getRenderPartialTicks());
        GlStateManager.translate(interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z);
        GlStateManager.rotate(-entityPlayer.renderYawOffset, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? -0.235 : 0.0);
        final float n = entityPlayer.isSneaking() ? 0.6f : 0.75f;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.125, (double)n, 0.0);
        if (array[3][0] != 0.0f) {
            GlStateManager.rotate(array[3][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[3][1] != 0.0f) {
            GlStateManager.rotate(array[3][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[3][2] != 0.0f) {
            GlStateManager.rotate(array[3][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.125, (double)n, 0.0);
        if (array[4][0] != 0.0f) {
            GlStateManager.rotate(array[4][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[4][1] != 0.0f) {
            GlStateManager.rotate(array[4][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[4][2] != 0.0f) {
            GlStateManager.rotate(array[4][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? 0.25 : 0.0);
        GlStateManager.pushMatrix();
        double n2 = 0.0;
        if (entityPlayer.isSneaking()) {
            n2 = -0.05;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.01725 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.375, n + 0.55, 0.0);
        if (array[1][0] != 0.0f) {
            GlStateManager.rotate(array[1][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[1][1] != 0.0f) {
            GlStateManager.rotate(array[1][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[1][2] != 0.0f) {
            GlStateManager.rotate(-array[1][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.375, n + 0.55, 0.0);
        if (array[2][0] != 0.0f) {
            GlStateManager.rotate(array[2][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[2][1] != 0.0f) {
            GlStateManager.rotate(array[2][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[2][2] != 0.0f) {
            GlStateManager.rotate(-array[2][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        if (array[0][0] != 0.0f) {
            GlStateManager.rotate(array[0][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.3, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.rotate(entityPlayer.isSneaking() ? 25.0f : 0.0f, 1.0f, 0.0f, 0.0f);
        if (entityPlayer.isSneaking()) {
            n2 = -0.16175;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.48025 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(-0.125, 0.0, 0.0);
        GL11.glVertex3d(0.125, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GL11.glVertex3d(0.0, 0.55, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        GlStateManager.glBegin(3);
        GL11.glVertex3d(-0.375, 0.0, 0.0);
        GL11.glVertex3d(0.375, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
    
    private void setInstance() {
        Skeleton.INSTANCE = this;
    }
    
    private void renderSkeletonTest(final EntityPlayer entityPlayer, final float[][] array, final Color color, final Color color2) {
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.pushMatrix();
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos((Entity)entityPlayer, Skeleton.mc.getRenderPartialTicks());
        GlStateManager.translate(interpolatedRenderPos.x, interpolatedRenderPos.y, interpolatedRenderPos.z);
        GlStateManager.rotate(-entityPlayer.renderYawOffset, 0.0f, 1.0f, 0.0f);
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? -0.235 : 0.0);
        final float n = entityPlayer.isSneaking() ? 0.6f : 0.75f;
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.125, (double)n, 0.0);
        if (array[3][0] != 0.0f) {
            GlStateManager.rotate(array[3][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[3][1] != 0.0f) {
            GlStateManager.rotate(array[3][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[3][2] != 0.0f) {
            GlStateManager.rotate(array[3][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.125, (double)n, 0.0);
        if (array[4][0] != 0.0f) {
            GlStateManager.rotate(array[4][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[4][1] != 0.0f) {
            GlStateManager.rotate(array[4][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[4][2] != 0.0f) {
            GlStateManager.rotate(array[4][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, (double)(-n), 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.translate(0.0, 0.0, entityPlayer.isSneaking() ? 0.25 : 0.0);
        GlStateManager.pushMatrix();
        double n2 = 0.0;
        if (entityPlayer.isSneaking()) {
            n2 = -0.05;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.01725 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(-0.375, n + 0.55, 0.0);
        if (array[1][0] != 0.0f) {
            GlStateManager.rotate(array[1][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[1][1] != 0.0f) {
            GlStateManager.rotate(array[1][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[1][2] != 0.0f) {
            GlStateManager.rotate(-array[1][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.375, n + 0.55, 0.0);
        if (array[2][0] != 0.0f) {
            GlStateManager.rotate(array[2][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if (array[2][1] != 0.0f) {
            GlStateManager.rotate(array[2][1] * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (array[2][2] != 0.0f) {
            GlStateManager.rotate(-array[2][2] * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, -0.5, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        if (array[0][0] != 0.0f) {
            GlStateManager.rotate(array[0][0] * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.3, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
        GlStateManager.rotate(entityPlayer.isSneaking() ? 25.0f : 0.0f, 1.0f, 0.0f, 0.0f);
        if (entityPlayer.isSneaking()) {
            n2 = -0.16175;
        }
        GlStateManager.translate(0.0, n2, entityPlayer.isSneaking() ? -0.48025 : 0.0);
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(-0.125, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.125, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, (double)n, 0.0);
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.0, 0.55, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.pushMatrix();
        GlStateManager.translate(0.0, n + 0.55, 0.0);
        GlStateManager.glBegin(3);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GL11.glVertex3d(-0.375, 0.0, 0.0);
        GlStateManager.color(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, color2.getAlpha() / 255.0f);
        GL11.glVertex3d(0.375, 0.0, 0.0);
        GlStateManager.glEnd();
        GlStateManager.popMatrix();
        GlStateManager.popMatrix();
    }
    
    public static Skeleton getInstance() {
        if (Skeleton.INSTANCE == null) {
            Skeleton.INSTANCE = new Skeleton();
        }
        return Skeleton.INSTANCE;
    }
    
    public void onRenderModel(final RenderEntityModelEvent renderEntityModelEvent) {
        if (renderEntityModelEvent.getStage() == 0 && renderEntityModelEvent.entity instanceof EntityPlayer && renderEntityModelEvent.modelBase instanceof ModelBiped) {
            this.rotationList.put((EntityPlayer)renderEntityModelEvent.entity, RenderUtil.getBipedRotations((ModelBiped)renderEntityModelEvent.modelBase));
        }
    }
    
    public Skeleton() {
        super("Skeleton", "Draws a nice Skeleton.", Module.Category.RENDER, false, false, false);
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.5f, (T)0.1f, (T)5.0f));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        this.colorFriends = (Setting<Boolean>)this.register(new Setting("Friends", (T)true));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("Invisibles", (T)false));
        this.rotationList = new HashMap<EntityPlayer, float[][]>();
        this.setInstance();
    }
    
    static {
        Skeleton.INSTANCE = new Skeleton();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        RenderUtil.GLPre(this.lineWidth.getValue());
        for (final EntityPlayer entityPlayer : Skeleton.mc.world.playerEntities) {
            if (entityPlayer != null && entityPlayer != Skeleton.mc.getRenderViewEntity() && entityPlayer.isEntityAlive() && !entityPlayer.isPlayerSleeping() && (!entityPlayer.isInvisible() || this.invisibles.getValue()) && this.rotationList.get(entityPlayer) != null) {
                if (Skeleton.mc.player.getDistanceSq((Entity)entityPlayer) >= 2500.0) {
                    continue;
                }
                this.renderSkeleton(entityPlayer, this.rotationList.get(entityPlayer), ((boolean)this.rainbow.getValue()) ? EntityUtil.getColor((Entity)entityPlayer, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue(), this.colorFriends.getValue()) : EntityUtil.getColor((Entity)entityPlayer, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue(), this.colorFriends.getValue()));
            }
        }
        RenderUtil.GlPost();
    }
}

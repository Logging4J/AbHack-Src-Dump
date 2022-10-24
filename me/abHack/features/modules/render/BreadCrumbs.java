//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import java.awt.*;
import net.minecraft.client.renderer.*;
import org.lwjgl.opengl.*;
import me.abHack.event.events.*;
import java.util.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import net.minecraft.entity.player.*;

public class BreadCrumbs extends Module
{
    public static /* synthetic */ Setting<Boolean> rainbow;
    public static /* synthetic */ ArrayList<double[]> vecs;
    public static /* synthetic */ Setting<Integer> red;
    public static /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Color color;
    public static /* synthetic */ Setting<Integer> alpha;
    public static /* synthetic */ Setting<Float> width;
    public static /* synthetic */ Setting<Integer> length;
    public static /* synthetic */ Setting<Integer> blue;
    
    public void onDisable() {
        BreadCrumbs.vecs.removeAll(BreadCrumbs.vecs);
    }
    
    public Color getCurrentColor() {
        return new Color(BreadCrumbs.red.getValue(), BreadCrumbs.green.getValue(), BreadCrumbs.blue.getValue(), BreadCrumbs.alpha.getValue());
    }
    
    public static void releaseGL() {
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(12.552789f) ^ 0x7EC8D839), Float.intBitsToFloat(Float.floatToIntBits(7.122752f) ^ 0x7F63ED96), Float.intBitsToFloat(Float.floatToIntBits(5.4278784f) ^ 0x7F2DB12E));
        GL11.glColor4f(Float.intBitsToFloat(Float.floatToIntBits(10.5715685f) ^ 0x7EA92525), Float.intBitsToFloat(Float.floatToIntBits(4.9474883f) ^ 0x7F1E51D3), Float.intBitsToFloat(Float.floatToIntBits(4.9044757f) ^ 0x7F1CF177), Float.intBitsToFloat(Float.floatToIntBits(9.482457f) ^ 0x7E97B825));
    }
    
    public static void prepareGL() {
        GL11.glBlendFunc(770, 771);
        GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
        GlStateManager.glLineWidth(Float.intBitsToFloat(Float.floatToIntBits(5.0675106f) ^ 0x7F22290C));
        GlStateManager.disableTexture2D();
        GlStateManager.depthMask(false);
        GlStateManager.enableBlend();
        GlStateManager.disableDepth();
        GlStateManager.disableLighting();
        GlStateManager.disableCull();
        GlStateManager.enableAlpha();
        GlStateManager.color(Float.intBitsToFloat(Float.floatToIntBits(11.925059f) ^ 0x7EBECD0B), Float.intBitsToFloat(Float.floatToIntBits(18.2283f) ^ 0x7E11D38F), Float.intBitsToFloat(Float.floatToIntBits(9.73656f) ^ 0x7E9BC8F3));
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        try {
            final double renderPosX = BreadCrumbs.mc.getRenderManager().renderPosX;
            final double renderPosY = BreadCrumbs.mc.getRenderManager().renderPosY;
            final double renderPosZ = BreadCrumbs.mc.getRenderManager().renderPosZ;
            final float n = this.color.getRed() / Float.intBitsToFloat(Float.floatToIntBits(0.49987957f) ^ 0x7D80F037);
            final float n2 = this.color.getGreen() / Float.intBitsToFloat(Float.floatToIntBits(0.4340212f) ^ 0x7DA13807);
            final float n3 = this.color.getBlue() / Float.intBitsToFloat(Float.floatToIntBits(0.0131841665f) ^ 0x7F270267);
            if (this.isEnabled()) {
                prepareGL();
                GL11.glPushMatrix();
                GL11.glEnable(2848);
                GL11.glLineWidth((float)BreadCrumbs.width.getValue());
                GL11.glBlendFunc(770, 771);
                GL11.glLineWidth((float)BreadCrumbs.width.getValue());
                GL11.glDepthMask(false);
                GL11.glBegin(3);
                final Iterator<double[]> iterator2;
                Iterator<double[]> iterator = iterator2 = BreadCrumbs.vecs.iterator();
                while (iterator.hasNext()) {
                    final double n4 = 0.0;
                    final double[] array = iterator2.next();
                    final double m = M(Math.hypot(array[0] - BreadCrumbs.mc.player.posX, array[1] - BreadCrumbs.mc.player.posY));
                    if (n4 > BreadCrumbs.length.getValue()) {
                        iterator = iterator2;
                    }
                    else {
                        GL11.glColor4f(n, n2, n3, Float.intBitsToFloat(Float.floatToIntBits(14.099797f) ^ 0x7EE198C5) - (float)(m / BreadCrumbs.length.getValue()));
                        iterator = iterator2;
                        GL11.glVertex3d(array[0] - renderPosX, array[1] - renderPosY, array[2] - renderPosZ);
                    }
                }
                GL11.glEnd();
                GL11.glDepthMask(true);
                GL11.glPopMatrix();
                releaseGL();
            }
        }
        catch (Exception ex) {}
    }
    
    public void onUpdate() {
        this.color = (BreadCrumbs.rainbow.getValue() ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : this.getCurrentColor());
        try {
            final double renderPosX = BreadCrumbs.mc.getRenderManager().renderPosX;
            final double renderPosY = BreadCrumbs.mc.getRenderManager().renderPosY;
            final double renderPosZ = BreadCrumbs.mc.getRenderManager().renderPosZ;
            if (this.isEnabled()) {
                for (final EntityPlayer entityPlayer : BreadCrumbs.mc.world.playerEntities) {
                    if (!(entityPlayer instanceof EntityPlayer)) {
                        continue;
                    }
                    final EntityPlayer entityPlayer2 = entityPlayer;
                    final boolean b = entityPlayer2 == BreadCrumbs.mc.player;
                    double n = renderPosY + Double.longBitsToDouble(Double.doubleToLongBits(0.48965838138858014) ^ 0x7FDF56901B91AE07L);
                    if (BreadCrumbs.mc.player.isElytraFlying()) {
                        n -= Double.longBitsToDouble(Double.doubleToLongBits(29.56900080933637) ^ 0x7FC591AA097B7F4BL);
                    }
                    if (!b) {
                        continue;
                    }
                    BreadCrumbs.vecs.add(new double[] { renderPosX, n - entityPlayer2.height, renderPosZ });
                }
            }
        }
        catch (Exception ex) {}
        if (BreadCrumbs.vecs.size() > BreadCrumbs.length.getValue()) {
            BreadCrumbs.vecs.remove(0);
        }
    }
    
    public static double M(final double n) {
        if (n == Double.longBitsToDouble(Double.doubleToLongBits(1.7931000183463725E308) ^ 0x7FEFEB11C3AAD037L)) {
            return n;
        }
        if (n < Double.longBitsToDouble(Double.doubleToLongBits(1.1859585260803721E308) ^ 0x7FE51C5AEE8AD07FL)) {
            return n * Double.longBitsToDouble(Double.doubleToLongBits(-12.527781766526259) ^ 0x7FD90E3969654F8FL);
        }
        return n;
    }
    
    public BreadCrumbs() {
        super("BreadCrumbs", "Draws a small line behind you", Module.Category.RENDER, true, false, false);
        BreadCrumbs.length = (Setting<Integer>)this.register(new Setting("Length", (T)15, (T)5, (T)40));
        BreadCrumbs.width = (Setting<Float>)this.register(new Setting("Width", (T)1.5f, (T)0.5f, (T)5.0f));
        BreadCrumbs.red = (Setting<Integer>)this.register(new Setting("Red", (T)30, (T)0, (T)255));
        BreadCrumbs.green = (Setting<Integer>)this.register(new Setting("Green", (T)167, (T)0, (T)255));
        BreadCrumbs.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        BreadCrumbs.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        BreadCrumbs.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        BreadCrumbs.vecs = new ArrayList<double[]>();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.mixin.mixins;

import net.minecraft.client.renderer.entity.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import net.minecraft.client.model.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import me.abHack.event.events.*;
import org.lwjgl.opengl.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;
import org.spongepowered.asm.mixin.injection.callback.*;
import net.minecraft.client.*;
import me.abHack.*;
import me.abHack.features.modules.render.*;
import net.minecraft.util.math.*;
import org.spongepowered.asm.mixin.injection.*;

@Mixin({ RenderEnderCrystal.class })
public abstract class MixinRenderEnderCrystal
{
    @Final
    @Shadow
    private static ResourceLocation ENDER_CRYSTAL_TEXTURES;
    @Shadow
    public ModelBase modelEnderCrystal;
    @Shadow
    public ModelBase modelEnderCrystalNoBase;
    
    @Shadow
    public abstract void doRender(final EntityEnderCrystal p0, final double p1, final double p2, final double p3, final float p4, final float p5);
    
    @Redirect(method = { "doRender" }, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/ModelBase;render(Lnet/minecraft/entity/Entity;FFFFFF)V"))
    public void renderModelBaseHook(final ModelBase modelBase, final Entity entity, final float n, final float n2, final float n3, final float n4, final float n5, final float n6) {
        if (CrystalScale.INSTANCE.isEnabled()) {
            if ((boolean)CrystalScale.INSTANCE.animateScale.getValue() && CrystalScale.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.scale((float)CrystalScale.INSTANCE.scaleMap.get(entity), (float)CrystalScale.INSTANCE.scaleMap.get(entity), (float)CrystalScale.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.scale((float)CrystalScale.INSTANCE.scale.getValue(), (float)CrystalScale.INSTANCE.scale.getValue(), (float)CrystalScale.INSTANCE.scale.getValue());
            }
        }
        if (CrystalScale.INSTANCE.isEnabled() && (boolean)CrystalScale.INSTANCE.wireframe.getValue()) {
            CrystalScale.INSTANCE.onRenderModel(new RenderEntityModelEvent(0, modelBase, entity, n, n2, n3, n4, n5, n6));
        }
        if (CrystalScale.INSTANCE.isEnabled() && (boolean)CrystalScale.INSTANCE.chams.getValue()) {
            GL11.glPushAttrib(1048575);
            GL11.glDisable(3008);
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glEnable(3042);
            GL11.glBlendFunc(770, 771);
            GL11.glLineWidth(1.5f);
            GL11.glEnable(2960);
            if (CrystalScale.INSTANCE.rainbow.getValue()) {
                final Color color = CrystalScale.INSTANCE.rainbow.getValue() ? ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue()) : new Color(RenderUtil.getRainbow((int)CrystalScale.INSTANCE.speed.getValue() * 100, 0, (int)CrystalScale.INSTANCE.saturation.getValue() / 100.0f, (int)CrystalScale.INSTANCE.brightness.getValue() / 100.0f));
                final Color color2 = EntityUtil.getColor(entity, color.getRed(), color.getGreen(), color.getBlue(), (int)CrystalScale.INSTANCE.alpha.getValue(), true);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color2.getRed() / 255.0f, color2.getGreen() / 255.0f, color2.getBlue() / 255.0f, (int)CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            else if ((boolean)CrystalScale.INSTANCE.xqz.getValue() && (boolean)CrystalScale.INSTANCE.throughWalls.getValue()) {
                final Color color3 = EntityUtil.getColor(entity, (int)CrystalScale.INSTANCE.hiddenRed.getValue(), (int)CrystalScale.INSTANCE.hiddenGreen.getValue(), (int)CrystalScale.INSTANCE.hiddenBlue.getValue(), (int)CrystalScale.INSTANCE.hiddenAlpha.getValue(), true);
                final Color color4 = EntityUtil.getColor(entity, (int)CrystalScale.INSTANCE.red.getValue(), (int)CrystalScale.INSTANCE.green.getValue(), (int)CrystalScale.INSTANCE.blue.getValue(), (int)CrystalScale.INSTANCE.alpha.getValue(), true);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color3.getRed() / 255.0f, color3.getGreen() / 255.0f, color3.getBlue() / 255.0f, (int)CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
                GL11.glColor4f(color4.getRed() / 255.0f, color4.getGreen() / 255.0f, color4.getBlue() / 255.0f, (int)CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
            }
            else {
                final Color color5 = CrystalScale.INSTANCE.rainbow.getValue() ? ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue()) : EntityUtil.getColor(entity, (int)CrystalScale.INSTANCE.red.getValue(), (int)CrystalScale.INSTANCE.green.getValue(), (int)CrystalScale.INSTANCE.blue.getValue(), (int)CrystalScale.INSTANCE.alpha.getValue(), true);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glDisable(2929);
                    GL11.glDepthMask(false);
                }
                GL11.glEnable(10754);
                GL11.glColor4f(color5.getRed() / 255.0f, color5.getGreen() / 255.0f, color5.getBlue() / 255.0f, (int)CrystalScale.INSTANCE.alpha.getValue() / 255.0f);
                modelBase.render(entity, n, n2, n3, n4, n5, n6);
                if (CrystalScale.INSTANCE.throughWalls.getValue()) {
                    GL11.glEnable(2929);
                    GL11.glDepthMask(true);
                }
            }
            GL11.glEnable(3042);
            GL11.glEnable(2896);
            GL11.glEnable(3553);
            GL11.glEnable(3008);
            GL11.glPopAttrib();
        }
        else {
            modelBase.render(entity, n, n2, n3, n4, n5, n6);
        }
        if (CrystalScale.INSTANCE.isEnabled()) {
            if ((boolean)CrystalScale.INSTANCE.animateScale.getValue() && CrystalScale.INSTANCE.scaleMap.containsKey(entity)) {
                GlStateManager.scale(1.0f / CrystalScale.INSTANCE.scaleMap.get(entity), 1.0f / CrystalScale.INSTANCE.scaleMap.get(entity), 1.0f / CrystalScale.INSTANCE.scaleMap.get(entity));
            }
            else {
                GlStateManager.scale(1.0f / (float)CrystalScale.INSTANCE.scale.getValue(), 1.0f / (float)CrystalScale.INSTANCE.scale.getValue(), 1.0f / (float)CrystalScale.INSTANCE.scale.getValue());
            }
        }
    }
    
    @Inject(method = { "doRender(Lnet/minecraft/entity/item/EntityEnderCrystal;DDDFF)V" }, at = { @At("RETURN") }, cancellable = true)
    public void IdoRender(final EntityEnderCrystal entityEnderCrystal, final double n, final double n2, final double n3, final float n4, final float n5, final CallbackInfo callbackInfo) {
        Minecraft.getMinecraft().gameSettings.fancyGraphics = false;
        if (OyVey.moduleManager.isModuleEnabled("ESP") && (boolean)ESP.getInstance().others.getValue()) {
            final float n6 = entityEnderCrystal.innerRotation + n5;
            GlStateManager.pushMatrix();
            GlStateManager.translate(n, n2, n3);
            Minecraft.getMinecraft().getRenderManager().renderEngine.bindTexture(MixinRenderEnderCrystal.ENDER_CRYSTAL_TEXTURES);
            final float n7 = MathHelper.sin(n6 * 0.2f) / 2.0f + 0.5f;
            final float n8 = n7 + n7 * n7;
            GL11.glLineWidth(5.0f);
            if (entityEnderCrystal.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderOne((float)(double)ESP.getInstance().lineWidth.getValue());
            if (entityEnderCrystal.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderTwo();
            if (entityEnderCrystal.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            final Color rainbow = ColorUtil.rainbow((int)ClickGui.getInstance().rainbowHue.getValue());
            final Color color = new Color(rainbow.getRed(), rainbow.getGreen(), rainbow.getBlue());
            final Color color2 = new Color(color.getRed(), color.getGreen(), color.getBlue());
            RenderUtil.renderThree();
            RenderUtil.renderFour(rainbow);
            RenderUtil.setColor(color2);
            if (entityEnderCrystal.shouldShowBottom()) {
                this.modelEnderCrystal.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            else {
                this.modelEnderCrystalNoBase.render((Entity)entityEnderCrystal, 0.0f, n6 * 3.0f, n8 * 0.2f, 0.0f, 0.0f, 0.0625f);
            }
            RenderUtil.renderFive();
            GlStateManager.popMatrix();
        }
    }
}

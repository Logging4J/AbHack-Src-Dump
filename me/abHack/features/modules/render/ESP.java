//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import me.abHack.features.modules.client.*;
import me.abHack.util.*;
import org.lwjgl.opengl.*;
import java.awt.*;
import me.abHack.event.events.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.item.*;
import java.util.*;
import net.minecraft.util.math.*;

public class ESP extends Module
{
    public final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> players;
    public final /* synthetic */ Setting<Boolean> others;
    private final /* synthetic */ Setting<Boolean> xpbottles;
    private final /* synthetic */ Setting<Boolean> colorSync;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> self;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Boolean> pearl;
    private final /* synthetic */ Setting<Integer> red;
    private final /* synthetic */ Setting<Boolean> onTop;
    private final /* synthetic */ Setting<Integer> green;
    private static /* synthetic */ ESP INSTANCE;
    private final /* synthetic */ Setting<Boolean> animals;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Boolean> colorFriends;
    private final /* synthetic */ Setting<Boolean> mobs;
    private final /* synthetic */ Setting<Boolean> xporbs;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Boolean> items;
    
    static {
        ESP.INSTANCE = new ESP();
    }
    
    public void onRenderModel(final RenderEntityModelEvent renderEntityModelEvent) {
        if (renderEntityModelEvent.getStage() != 0 || renderEntityModelEvent.entity == null || (renderEntityModelEvent.entity.isInvisible() && !this.invisibles.getValue()) || (!this.self.getValue() && renderEntityModelEvent.entity.equals((Object)ESP.mc.player)) || (!this.players.getValue() && renderEntityModelEvent.entity instanceof EntityPlayer) || (!this.animals.getValue() && EntityUtil.isPassive(renderEntityModelEvent.entity)) || (!this.mobs.getValue() && !EntityUtil.isPassive(renderEntityModelEvent.entity) && !(renderEntityModelEvent.entity instanceof EntityPlayer))) {
            return;
        }
        final Color color = this.colorSync.getValue() ? EntityUtil.getColor(renderEntityModelEvent.entity, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue(), this.colorFriends.getValue()) : EntityUtil.getColor(renderEntityModelEvent.entity, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue(), this.colorFriends.getValue());
        final boolean fancyGraphics = ESP.mc.gameSettings.fancyGraphics;
        ESP.mc.gameSettings.fancyGraphics = false;
        final float gammaSetting = ESP.mc.gameSettings.gammaSetting;
        ESP.mc.gameSettings.gammaSetting = 10000.0f;
        if (this.onTop.getValue()) {
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
        }
        if (this.mode.getValue() == Mode.OUTLINE) {
            RenderUtil.renderOne(this.lineWidth.getValue());
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
            GlStateManager.glLineWidth((float)this.lineWidth.getValue());
            RenderUtil.renderTwo();
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
            GlStateManager.glLineWidth((float)this.lineWidth.getValue());
            RenderUtil.renderThree();
            RenderUtil.renderFour(color);
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
            GlStateManager.glLineWidth((float)this.lineWidth.getValue());
            RenderUtil.renderFive();
        }
        else {
            GL11.glPushMatrix();
            GL11.glPushAttrib(1048575);
            if (this.mode.getValue() == Mode.WIREFRAME) {
                GL11.glPolygonMode(1032, 6913);
            }
            else {
                GL11.glPolygonMode(1028, 6913);
            }
            GL11.glDisable(3553);
            GL11.glDisable(2896);
            GL11.glDisable(2929);
            GL11.glEnable(2848);
            GL11.glEnable(3042);
            GlStateManager.blendFunc(770, 771);
            GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
            GlStateManager.glLineWidth((float)this.lineWidth.getValue());
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
            GL11.glPopAttrib();
            GL11.glPopMatrix();
        }
        if (!this.onTop.getValue()) {
            renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
        }
        try {
            ESP.mc.gameSettings.fancyGraphics = fancyGraphics;
            ESP.mc.gameSettings.gammaSetting = gammaSetting;
        }
        catch (Exception ex) {}
        renderEntityModelEvent.setCanceled(true);
    }
    
    public ESP() {
        super("ESP", "Renders a nice ESP.", Module.Category.RENDER, false, false, false);
        this.others = (Setting<Boolean>)this.register(new Setting("Crystals", (T)false));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)2.0f, (T)0.1f, (T)5.0f));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.OUTLINE));
        this.players = (Setting<Boolean>)this.register(new Setting("Players", (T)true));
        this.animals = (Setting<Boolean>)this.register(new Setting("Animals", (T)false));
        this.mobs = (Setting<Boolean>)this.register(new Setting("Mobs", (T)false));
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (T)false));
        this.xporbs = (Setting<Boolean>)this.register(new Setting("XpOrbs", (T)false));
        this.xpbottles = (Setting<Boolean>)this.register(new Setting("XpBottles", (T)false));
        this.pearl = (Setting<Boolean>)this.register(new Setting("Pearls", (T)false));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)120, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        this.colorSync = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
        this.colorFriends = (Setting<Boolean>)this.register(new Setting("Friends", (T)true));
        this.self = (Setting<Boolean>)this.register(new Setting("Self", (T)true));
        this.onTop = (Setting<Boolean>)this.register(new Setting("onTop", (T)true));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("Invisibles", (T)false));
        this.setInstance();
    }
    
    public static ESP getInstance() {
        if (ESP.INSTANCE == null) {
            ESP.INSTANCE = new ESP();
        }
        return ESP.INSTANCE;
    }
    
    private void setInstance() {
        ESP.INSTANCE = this;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.items.getValue()) {
            int n = 0;
            for (final Entity entity : ESP.mc.world.loadedEntityList) {
                if (entity instanceof EntityItem) {
                    if (ESP.mc.player.getDistanceSq(entity) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos = EntityUtil.getInterpolatedRenderPos(entity, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(entity.getEntityBoundingBox().minX - 0.05 - entity.posX + interpolatedRenderPos.x, entity.getEntityBoundingBox().minY - 0.0 - entity.posY + interpolatedRenderPos.y, entity.getEntityBoundingBox().minZ - 0.05 - entity.posZ + interpolatedRenderPos.z, entity.getEntityBoundingBox().maxX + 0.05 - entity.posX + interpolatedRenderPos.x, entity.getEntityBoundingBox().maxY + 0.1 - entity.posY + interpolatedRenderPos.y, entity.getEntityBoundingBox().maxZ + 0.05 - entity.posZ + interpolatedRenderPos.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB, ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed()) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen()) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue()) : (this.blue.getValue() / 255.0f), this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB, ((boolean)this.colorSync.getValue()) ? new Color(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++n < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xporbs.getValue()) {
            int n2 = 0;
            for (final Entity entity2 : ESP.mc.world.loadedEntityList) {
                if (entity2 instanceof EntityXPOrb) {
                    if (ESP.mc.player.getDistanceSq(entity2) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos2 = EntityUtil.getInterpolatedRenderPos(entity2, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB2 = new AxisAlignedBB(entity2.getEntityBoundingBox().minX - 0.05 - entity2.posX + interpolatedRenderPos2.x, entity2.getEntityBoundingBox().minY - 0.0 - entity2.posY + interpolatedRenderPos2.y, entity2.getEntityBoundingBox().minZ - 0.05 - entity2.posZ + interpolatedRenderPos2.z, entity2.getEntityBoundingBox().maxX + 0.05 - entity2.posX + interpolatedRenderPos2.x, entity2.getEntityBoundingBox().maxY + 0.1 - entity2.posY + interpolatedRenderPos2.y, entity2.getEntityBoundingBox().maxZ + 0.05 - entity2.posZ + interpolatedRenderPos2.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB2, ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed()) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen()) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue()) : (this.blue.getValue() / 255.0f), this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB2, ((boolean)this.colorSync.getValue()) ? new Color(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++n2 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.pearl.getValue()) {
            int n3 = 0;
            for (final Entity entity3 : ESP.mc.world.loadedEntityList) {
                if (entity3 instanceof EntityEnderPearl) {
                    if (ESP.mc.player.getDistanceSq(entity3) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos3 = EntityUtil.getInterpolatedRenderPos(entity3, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB3 = new AxisAlignedBB(entity3.getEntityBoundingBox().minX - 0.05 - entity3.posX + interpolatedRenderPos3.x, entity3.getEntityBoundingBox().minY - 0.0 - entity3.posY + interpolatedRenderPos3.y, entity3.getEntityBoundingBox().minZ - 0.05 - entity3.posZ + interpolatedRenderPos3.z, entity3.getEntityBoundingBox().maxX + 0.05 - entity3.posX + interpolatedRenderPos3.x, entity3.getEntityBoundingBox().maxY + 0.1 - entity3.posY + interpolatedRenderPos3.y, entity3.getEntityBoundingBox().maxZ + 0.05 - entity3.posZ + interpolatedRenderPos3.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB3, ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed()) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen()) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue()) : (this.blue.getValue() / 255.0f), this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB3, ((boolean)this.colorSync.getValue()) ? new Color(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++n3 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
        if (this.xpbottles.getValue()) {
            int n4 = 0;
            for (final Entity entity4 : ESP.mc.world.loadedEntityList) {
                if (entity4 instanceof EntityExpBottle) {
                    if (ESP.mc.player.getDistanceSq(entity4) >= 2500.0) {
                        continue;
                    }
                    final Vec3d interpolatedRenderPos4 = EntityUtil.getInterpolatedRenderPos(entity4, ESP.mc.getRenderPartialTicks());
                    final AxisAlignedBB axisAlignedBB4 = new AxisAlignedBB(entity4.getEntityBoundingBox().minX - 0.05 - entity4.posX + interpolatedRenderPos4.x, entity4.getEntityBoundingBox().minY - 0.0 - entity4.posY + interpolatedRenderPos4.y, entity4.getEntityBoundingBox().minZ - 0.05 - entity4.posZ + interpolatedRenderPos4.z, entity4.getEntityBoundingBox().maxX + 0.05 - entity4.posX + interpolatedRenderPos4.x, entity4.getEntityBoundingBox().maxY + 0.1 - entity4.posY + interpolatedRenderPos4.y, entity4.getEntityBoundingBox().maxZ + 0.05 - entity4.posZ + interpolatedRenderPos4.z);
                    GlStateManager.pushMatrix();
                    GlStateManager.enableBlend();
                    GlStateManager.disableDepth();
                    GlStateManager.tryBlendFuncSeparate(770, 771, 0, 1);
                    GlStateManager.disableTexture2D();
                    GlStateManager.depthMask(false);
                    GL11.glEnable(2848);
                    GL11.glHint(3154, 4354);
                    GL11.glLineWidth(1.0f);
                    RenderGlobal.renderFilledBox(axisAlignedBB4, ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed()) : (this.red.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen()) : (this.green.getValue() / 255.0f), ((boolean)this.colorSync.getValue()) ? ((float)ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue()) : (this.blue.getValue() / 255.0f), this.boxAlpha.getValue() / 255.0f);
                    GL11.glDisable(2848);
                    GlStateManager.depthMask(true);
                    GlStateManager.enableDepth();
                    GlStateManager.enableTexture2D();
                    GlStateManager.disableBlend();
                    GlStateManager.popMatrix();
                    RenderUtil.drawBlockOutline(axisAlignedBB4, ((boolean)this.colorSync.getValue()) ? new Color(ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRed(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getGreen(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getBlue(), this.alpha.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), 1.0f);
                    if (++n4 < 50) {
                        continue;
                    }
                    break;
                }
            }
        }
    }
    
    public enum Mode
    {
        OUTLINE, 
        WIREFRAME;
    }
}

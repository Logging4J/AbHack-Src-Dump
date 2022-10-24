//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.item.*;
import me.abHack.util.*;
import org.lwjgl.opengl.*;
import net.minecraft.client.renderer.*;
import java.awt.*;
import net.minecraft.entity.*;
import java.util.*;
import java.util.concurrent.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class CrystalScale extends Module
{
    public static /* synthetic */ CrystalScale INSTANCE;
    public /* synthetic */ Setting<Boolean> throughWalls;
    public /* synthetic */ Setting<Float> scale;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> animateScale;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> speed;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> chams;
    public /* synthetic */ Setting<Boolean> wireframeThroughWalls;
    public /* synthetic */ Setting<Boolean> wireframe;
    public /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ Setting<Integer> hiddenBlue;
    public /* synthetic */ Setting<Integer> brightness;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> hiddenGreen;
    public /* synthetic */ Map<EntityEnderCrystal, Float> scaleMap;
    public /* synthetic */ Setting<Integer> saturation;
    public /* synthetic */ Setting<Integer> hiddenAlpha;
    public /* synthetic */ Setting<Integer> hiddenRed;
    public /* synthetic */ Setting<Boolean> xqz;
    
    public void onRenderModel(final RenderEntityModelEvent renderEntityModelEvent) {
        if (renderEntityModelEvent.getStage() != 0 || !(renderEntityModelEvent.entity instanceof EntityEnderCrystal) || !this.wireframe.getValue()) {
            return;
        }
        final Color color = this.rainbow.getValue() ? ColorUtil.rainbow(this.saturation.getValue()) : EntityUtil.getColor(renderEntityModelEvent.entity, this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue(), false);
        final boolean fancyGraphics = CrystalScale.mc.gameSettings.fancyGraphics;
        CrystalScale.mc.gameSettings.fancyGraphics = false;
        final float gammaSetting = CrystalScale.mc.gameSettings.gammaSetting;
        CrystalScale.mc.gameSettings.gammaSetting = 10000.0f;
        GL11.glPushMatrix();
        GL11.glPushAttrib(1048575);
        GL11.glPolygonMode(1032, 6913);
        GL11.glDisable(3553);
        GL11.glDisable(2896);
        if (this.wireframeThroughWalls.getValue()) {
            GL11.glDisable(2929);
        }
        GL11.glEnable(2848);
        GL11.glEnable(3042);
        GlStateManager.blendFunc(770, 771);
        GlStateManager.color(color.getRed() / 255.0f, color.getGreen() / 255.0f, color.getBlue() / 255.0f, color.getAlpha() / 255.0f);
        GlStateManager.glLineWidth((float)this.lineWidth.getValue());
        renderEntityModelEvent.modelBase.render(renderEntityModelEvent.entity, renderEntityModelEvent.limbSwing, renderEntityModelEvent.limbSwingAmount, renderEntityModelEvent.age, renderEntityModelEvent.headYaw, renderEntityModelEvent.headPitch, renderEntityModelEvent.scale);
        GL11.glPopAttrib();
        GL11.glPopMatrix();
    }
    
    public void onUpdate() {
        for (final Entity entity : CrystalScale.mc.world.loadedEntityList) {
            if (!(entity instanceof EntityEnderCrystal)) {
                continue;
            }
            if (!this.scaleMap.containsKey(entity)) {
                this.scaleMap.put((EntityEnderCrystal)entity, 3.125E-4f);
            }
            else {
                this.scaleMap.put((EntityEnderCrystal)entity, this.scaleMap.get(entity) + 3.125E-4f);
            }
            if (this.scaleMap.get(entity) < 0.0625f * this.scale.getValue()) {
                continue;
            }
            this.scaleMap.remove(entity);
        }
    }
    
    public CrystalScale() {
        super("CrystalMod", "Modifies crystal rendering in different ways", Module.Category.RENDER, true, false, false);
        this.animateScale = (Setting<Boolean>)this.register(new Setting("AnimateScale", (T)false));
        this.chams = (Setting<Boolean>)this.register(new Setting("Chams", (T)true));
        this.throughWalls = (Setting<Boolean>)this.register(new Setting("ThroughWalls", (T)true));
        this.wireframeThroughWalls = (Setting<Boolean>)this.register(new Setting("WireThroughWalls", (T)true));
        this.wireframe = (Setting<Boolean>)this.register(new Setting("Wireframe", (T)false));
        this.scale = (Setting<Float>)this.register(new Setting("Scale", (T)1.0f, (T)0.1f, (T)10.0f));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)3.0f));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
        this.saturation = (Setting<Integer>)this.register(new Setting("Saturation", (T)50, (T)0, (T)100, p0 -> this.rainbow.getValue()));
        this.brightness = (Setting<Integer>)this.register(new Setting("Brightness", (T)100, (T)0, (T)100, p0 -> this.rainbow.getValue()));
        this.speed = (Setting<Integer>)this.register(new Setting("Speed", (T)40, (T)1, (T)100, p0 -> this.rainbow.getValue()));
        this.xqz = (Setting<Boolean>)this.register(new Setting("XQZ", (T)Boolean.FALSE, p0 -> !this.rainbow.getValue() && this.throughWalls.getValue()));
        this.hiddenRed = (Setting<Integer>)this.register(new Setting("Hidden Red", (T)255, (T)0, (T)255, p0 -> this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenGreen = (Setting<Integer>)this.register(new Setting("Hidden Green", (T)0, (T)0, (T)255, p0 -> this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenBlue = (Setting<Integer>)this.register(new Setting("Hidden Blue", (T)255, (T)0, (T)255, p0 -> this.xqz.getValue() && !this.rainbow.getValue()));
        this.hiddenAlpha = (Setting<Integer>)this.register(new Setting("Hidden Alpha", (T)255, (T)0, (T)255, p0 -> this.xqz.getValue() && !this.rainbow.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)130, (T)0, (T)255, p0 -> !this.rainbow.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)55, (T)0, (T)255, p0 -> !this.rainbow.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)210, (T)0, (T)255, p0 -> !this.rainbow.getValue()));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)105, (T)0, (T)255));
        this.scaleMap = new ConcurrentHashMap<EntityEnderCrystal, Float>();
        CrystalScale.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketDestroyEntities) {
            final int[] getEntityIDs = ((SPacketDestroyEntities)receive.getPacket()).getEntityIDs();
            for (int length = getEntityIDs.length, i = 0; i < length; ++i) {
                final Entity getEntityByID = CrystalScale.mc.world.getEntityByID(getEntityIDs[i]);
                if (getEntityByID instanceof EntityEnderCrystal) {
                    this.scaleMap.remove(getEntityByID);
                }
            }
        }
    }
}

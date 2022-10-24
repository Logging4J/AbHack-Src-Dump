//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import java.util.function.*;
import net.minecraft.init.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.client.event.*;

public class NoRender extends Module
{
    public /* synthetic */ Setting<Boolean> explosions;
    public /* synthetic */ Setting<Boolean> items;
    public /* synthetic */ Setting<Boolean> fire;
    public /* synthetic */ Setting<Boolean> nausea;
    public /* synthetic */ Setting<Boolean> noWeather;
    public /* synthetic */ Setting<Boolean> blind;
    public /* synthetic */ Setting<Boolean> skyLightUpdate;
    private static /* synthetic */ NoRender INSTANCE;
    public /* synthetic */ Setting<Boolean> hurtCam;
    public /* synthetic */ Setting<Boolean> armor;
    
    @SubscribeEvent
    public void NoRenderEventListener(final NoRenderEvent noRenderEvent) {
        if (noRenderEvent.getStage() == 0 && this.armor.getValue()) {
            noRenderEvent.setCanceled(true);
        }
        else if (noRenderEvent.getStage() == 1 && this.hurtCam.getValue()) {
            noRenderEvent.setCanceled(true);
        }
    }
    
    public void onUpdate() {
        if (this.items.getValue()) {
            NoRender.mc.world.loadedEntityList.stream().filter(EntityItem.class::isInstance).map(EntityItem.class::cast).forEach(Entity::setDead);
        }
        if (this.blind.getValue() && NoRender.mc.player.isPotionActive(MobEffects.BLINDNESS)) {
            NoRender.mc.player.removePotionEffect(MobEffects.BLINDNESS);
        }
        if (this.nausea.getValue() && NoRender.mc.player.isPotionActive(MobEffects.NAUSEA)) {
            NoRender.mc.player.removePotionEffect(MobEffects.NAUSEA);
        }
        if (this.noWeather.getValue() && NoRender.mc.world.isRaining()) {
            NoRender.mc.world.setRainStrength(0.0f);
        }
    }
    
    public static NoRender getInstance() {
        if (NoRender.INSTANCE == null) {
            NoRender.INSTANCE = new NoRender();
        }
        return NoRender.INSTANCE;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (this.explosions.getValue() && receive.getPacket() instanceof SPacketExplosion) {
            receive.setCanceled(true);
        }
    }
    
    static {
        NoRender.INSTANCE = new NoRender();
    }
    
    public NoRender() {
        super("NoRender", "Shield some particle effects", Module.Category.RENDER, true, false, false);
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (T)true));
        this.fire = (Setting<Boolean>)this.register(new Setting("Frie", (T)true));
        this.blind = (Setting<Boolean>)this.register(new Setting("Blind", (T)true));
        this.nausea = (Setting<Boolean>)this.register(new Setting("Nausea", (T)true));
        this.hurtCam = (Setting<Boolean>)this.register(new Setting("HurtCam", (T)true));
        this.explosions = (Setting<Boolean>)this.register(new Setting("Explosions", (T)false));
        this.items = (Setting<Boolean>)this.register(new Setting("Items", (T)false, "Removes items on the ground."));
        this.noWeather = (Setting<Boolean>)this.register(new Setting("Weather", (T)false, "AntiWeather"));
        this.skyLightUpdate = (Setting<Boolean>)this.register(new Setting("SkyLightUpdate", (T)true));
        this.setInstance();
    }
    
    private void setInstance() {
        NoRender.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void blockOverlayEventListener(final RenderBlockOverlayEvent renderBlockOverlayEvent) {
        if (this.fire.getValue() && renderBlockOverlayEvent.getOverlayType() == RenderBlockOverlayEvent.OverlayType.FIRE) {
            renderBlockOverlayEvent.setCanceled(true);
        }
    }
}

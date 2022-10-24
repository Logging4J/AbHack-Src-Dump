//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.potion.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Fullbright extends Module
{
    public /* synthetic */ Setting<Boolean> effects;
    private /* synthetic */ float previousSetting;
    public /* synthetic */ Setting<Mode> mode;
    
    public void onDisable() {
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.removePotionEffect(MobEffects.NIGHT_VISION);
        }
        Fullbright.mc.gameSettings.gammaSetting = this.previousSetting;
    }
    
    public void onUpdate() {
        if (this.mode.getValue() == Mode.GAMMA) {
            Fullbright.mc.gameSettings.gammaSetting = 1000.0f;
        }
        if (this.mode.getValue() == Mode.POTION) {
            Fullbright.mc.player.addPotionEffect(new PotionEffect(MobEffects.NIGHT_VISION, 5210));
        }
    }
    
    public void onEnable() {
        this.previousSetting = Fullbright.mc.gameSettings.gammaSetting;
    }
    
    public Fullbright() {
        super("Fullbright", "Makes your game brighter.", Module.Category.RENDER, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.GAMMA));
        this.effects = (Setting<Boolean>)this.register(new Setting("Effects", (T)false));
        this.previousSetting = 1.0f;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() == 0 && receive.getPacket() instanceof SPacketEntityEffect && this.effects.getValue()) {
            final SPacketEntityEffect sPacketEntityEffect = (SPacketEntityEffect)receive.getPacket();
            if (Fullbright.mc.player != null && sPacketEntityEffect.getEntityId() == Fullbright.mc.player.getEntityId() && (sPacketEntityEffect.getEffectId() == 9 || sPacketEntityEffect.getEffectId() == 15)) {
                receive.setCanceled(true);
            }
        }
    }
    
    public enum Mode
    {
        GAMMA, 
        POTION;
    }
}

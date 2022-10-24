//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import me.abHack.util.*;
import net.minecraft.client.entity.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiWeb extends Module
{
    private final /* synthetic */ Setting<Float> speed;
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (updateWalkingPlayerEvent.getStage() == 1) {
            return;
        }
        if (AntiWeb.mc.player.isInWeb) {
            final double[] directionSpeed = MathUtil.directionSpeed(this.speed.getValue() / 10.0);
            AntiWeb.mc.player.motionX = directionSpeed[0];
            AntiWeb.mc.player.motionZ = directionSpeed[1];
            if (AntiWeb.mc.gameSettings.keyBindSneak.isKeyDown()) {
                final EntityPlayerSP player = AntiWeb.mc.player;
                player.motionY -= this.speed.getValue() / 10.0f;
            }
        }
    }
    
    public AntiWeb() {
        super("AntiWeb", "Stops you being slowed down by webs", Category.MISC, true, false, false);
        this.speed = (Setting<Float>)this.register(new Setting("Factor", (T)10.0f, (T)1.0f, (T)10.0f));
    }
}

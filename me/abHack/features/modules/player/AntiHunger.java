//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiHunger extends Module
{
    public /* synthetic */ Setting<Boolean> ground;
    public /* synthetic */ Setting<Boolean> cancelSprint;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (this.ground.getValue() && send.getPacket() instanceof CPacketPlayer) {
            ((CPacketPlayer)send.getPacket()).onGround = (AntiHunger.mc.player.fallDistance >= 0.0f || AntiHunger.mc.playerController.isHittingBlock);
        }
        if (this.cancelSprint.getValue() && send.getPacket() instanceof CPacketEntityAction) {
            final CPacketEntityAction cPacketEntityAction = (CPacketEntityAction)send.getPacket();
            if (cPacketEntityAction.getAction() == CPacketEntityAction.Action.START_SPRINTING || cPacketEntityAction.getAction() == CPacketEntityAction.Action.STOP_SPRINTING) {
                send.setCanceled(true);
            }
        }
    }
    
    public AntiHunger() {
        super("AntiHunger", "Prevents you from getting Hungry.", Module.Category.PLAYER, true, false, false);
        this.cancelSprint = (Setting<Boolean>)this.register(new Setting("CancelSprint", (T)true));
        this.ground = (Setting<Boolean>)this.register(new Setting("Ground", (T)true));
    }
}

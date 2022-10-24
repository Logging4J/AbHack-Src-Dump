//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraft.init.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AntiCrash extends Module
{
    public AntiCrash() {
        super("AntiCrash", "anti offhand crash", Category.MISC, true, false, false);
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Receive receive) {
        if (receive.getPacket() instanceof SPacketSoundEffect && ((SPacketSoundEffect)receive.getPacket()).getSound() == SoundEvents.ITEM_ARMOR_EQUIP_GENERIC) {
            receive.setCanceled(true);
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.client.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Interact extends Module
{
    public /* synthetic */ Setting<Boolean> liquid;
    public /* synthetic */ Setting<Boolean> buildHeight;
    public /* synthetic */ Setting<Boolean> portalgui;
    private static /* synthetic */ Interact INSTANCE;
    
    @Override
    public void onUpdate() {
        if (Minecraft.getMinecraft().player.inPortal && this.portalgui.getValue()) {
            Minecraft.getMinecraft().player.inPortal = false;
        }
    }
    
    public static Interact getInstance() {
        if (Interact.INSTANCE == null) {
            Interact.INSTANCE = new Interact();
        }
        return Interact.INSTANCE;
    }
    
    private void setInstance() {
        Interact.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (this.buildHeight.getValue() && send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
            final CPacketPlayerTryUseItemOnBlock cPacketPlayerTryUseItemOnBlock = (CPacketPlayerTryUseItemOnBlock)send.getPacket();
            if (cPacketPlayerTryUseItemOnBlock.getPos().getY() >= 255 && cPacketPlayerTryUseItemOnBlock.getDirection() == EnumFacing.UP) {
                cPacketPlayerTryUseItemOnBlock.placedBlockDirection = EnumFacing.DOWN;
            }
        }
    }
    
    static {
        Interact.INSTANCE = new Interact();
    }
    
    public Interact() {
        super("Interact", "ForceInteract", Category.MISC, true, false, false);
        this.buildHeight = (Setting<Boolean>)this.register(new Setting("BuildHeight", (T)true));
        this.liquid = (Setting<Boolean>)this.register(new Setting("Liquid", (T)true));
        this.portalgui = (Setting<Boolean>)this.register(new Setting("PortalGui", (T)true));
        this.setInstance();
    }
}

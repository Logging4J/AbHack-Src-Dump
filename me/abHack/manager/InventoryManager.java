//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class InventoryManager implements Util
{
    public /* synthetic */ int currentPlayerItem;
    private /* synthetic */ int recoverySlot;
    
    public void update() {
        if (this.recoverySlot != -1) {
            InventoryManager.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange((this.recoverySlot == 8) ? 7 : (this.recoverySlot + 1)));
            InventoryManager.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.recoverySlot));
            InventoryManager.mc.player.inventory.currentItem = this.recoverySlot;
            final int currentItem = InventoryManager.mc.player.inventory.currentItem;
            if (currentItem != this.currentPlayerItem) {
                this.currentPlayerItem = currentItem;
                InventoryManager.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.currentPlayerItem));
            }
            this.recoverySlot = -1;
        }
    }
    
    public void recoverSilent(final int recoverySlot) {
        this.recoverySlot = recoverySlot;
    }
    
    public InventoryManager() {
        this.recoverySlot = -1;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import net.minecraft.item.*;
import net.minecraft.util.math.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;

public class FastBow extends Module
{
    @Override
    public void onUpdate() {
        if (FastBow.mc.player.inventory.getCurrentItem().getItem() instanceof ItemBow && FastBow.mc.player.isHandActive() && FastBow.mc.player.getItemInUseMaxCount() >= 3) {
            FastBow.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.RELEASE_USE_ITEM, BlockPos.ORIGIN, FastBow.mc.player.getHorizontalFacing()));
            FastBow.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(FastBow.mc.player.getActiveHand()));
            FastBow.mc.player.stopActiveHand();
        }
    }
    
    public FastBow() {
        super("FastBow", "Accelerates bow shots.", Category.COMBAT, true, false, false);
    }
}

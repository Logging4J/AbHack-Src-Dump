//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.util.math.*;
import net.minecraft.client.*;
import net.minecraft.init.*;
import net.minecraft.block.*;

public class AntiContainer extends Module
{
    public /* synthetic */ Setting<Boolean> ShulkerBox;
    public /* synthetic */ Setting<Boolean> Crafting_Table;
    public /* synthetic */ Setting<Boolean> Hopper;
    public /* synthetic */ Setting<Boolean> Enchanting_table;
    public /* synthetic */ Setting<Boolean> Anvil;
    public /* synthetic */ Setting<Boolean> Chest;
    public /* synthetic */ Setting<Boolean> Furnace;
    public /* synthetic */ Setting<Boolean> EnderChest;
    public /* synthetic */ Setting<Boolean> Dispenser;
    public /* synthetic */ Setting<Boolean> Brewing_Stand;
    public /* synthetic */ Setting<Boolean> Trapped_Chest;
    public /* synthetic */ Setting<Boolean> Beacon;
    
    public AntiContainer() {
        super("AntiContainer", "Do not display containers", Module.Category.PLAYER, true, false, false);
        this.Chest = (Setting<Boolean>)this.register(new Setting("Chest", (T)true));
        this.EnderChest = (Setting<Boolean>)this.register(new Setting("EnderChest", (T)true));
        this.Trapped_Chest = (Setting<Boolean>)this.register(new Setting("Trapped_Chest", (T)true));
        this.Hopper = (Setting<Boolean>)this.register(new Setting("Hopper", (T)true));
        this.Dispenser = (Setting<Boolean>)this.register(new Setting("Dispenser", (T)true));
        this.Furnace = (Setting<Boolean>)this.register(new Setting("Furnace", (T)true));
        this.Beacon = (Setting<Boolean>)this.register(new Setting("Beacon", (T)true));
        this.Crafting_Table = (Setting<Boolean>)this.register(new Setting("Crafting_Table", (T)true));
        this.Anvil = (Setting<Boolean>)this.register(new Setting("Anvil", (T)true));
        this.Enchanting_table = (Setting<Boolean>)this.register(new Setting("Enchanting_table", (T)true));
        this.Brewing_Stand = (Setting<Boolean>)this.register(new Setting("Brewing_Stand", (T)true));
        this.ShulkerBox = (Setting<Boolean>)this.register(new Setting("ShulkerBox", (T)true));
    }
    
    @SubscribeEvent
    public void onCheck(final PacketEvent.Send send) {
        if (send.packet instanceof CPacketPlayerTryUseItemOnBlock && this.check(((CPacketPlayerTryUseItemOnBlock)send.packet).getPos())) {
            send.setCanceled(true);
        }
    }
    
    public boolean check(final BlockPos blockPos) {
        return (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.CHEST && this.Chest.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST && this.EnderChest.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.TRAPPED_CHEST && this.Trapped_Chest.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.HOPPER && this.Hopper.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.DISPENSER && this.Dispenser.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.FURNACE && this.Furnace.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.BEACON && this.Beacon.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.CRAFTING_TABLE && this.Crafting_Table.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.ANVIL && this.Anvil.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.ENCHANTING_TABLE && this.Enchanting_table.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() == Blocks.BREWING_STAND && this.Brewing_Stand.getValue()) || (Minecraft.getMinecraft().world.getBlockState(blockPos).getBlock() instanceof BlockShulkerBox && this.ShulkerBox.getValue());
    }
}

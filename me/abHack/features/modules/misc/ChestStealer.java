//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class ChestStealer extends Module
{
    @Override
    public void onUpdate() {
        if (ChestStealer.mc.player.openContainer instanceof ContainerChest) {
            final ContainerChest containerChest = (ContainerChest)ChestStealer.mc.player.openContainer;
            for (int i = 0; i < containerChest.getLowerChestInventory().getSizeInventory(); ++i) {
                containerChest.getLowerChestInventory().getStackInSlot(i);
                ChestStealer.mc.playerController.windowClick(containerChest.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)ChestStealer.mc.player);
                if (this.isChestEmpty(containerChest)) {
                    ChestStealer.mc.player.closeScreen();
                }
            }
        }
    }
    
    public ChestStealer() {
        super("ChestStealer", "steal monkes in chests", Category.MISC, true, false, false);
    }
    
    private boolean isChestEmpty(final ContainerChest containerChest) {
        final int n = 0;
        if (n < containerChest.getLowerChestInventory().getSizeInventory()) {
            containerChest.getLowerChestInventory().getStackInSlot(n);
            return false;
        }
        return true;
    }
}

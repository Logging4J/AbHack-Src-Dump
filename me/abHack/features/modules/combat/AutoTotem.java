//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.item.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class AutoTotem extends Module
{
    private final /* synthetic */ Setting<Boolean> strict;
    private final /* synthetic */ Setting<Integer> health;
    
    public static int getItemSlot(final Item obj) {
        int n = -1;
        for (int i = 45; i > 0; --i) {
            if (AutoTotem.mc.player.inventory.getStackInSlot(i).getItem().equals(obj)) {
                n = i;
                break;
            }
        }
        return n;
    }
    
    @Override
    public void onUpdate() {
        if (AutoTotem.mc.currentScreen instanceof GuiContainer && !(Offhand.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (AutoTotem.mc.currentScreen instanceof GuiContainer && !(Offhand.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        if (AutoTotem.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            return;
        }
        if (AutoTotem.mc.player.getHeldItemMainhand().getItem() == Items.TOTEM_OF_UNDYING) {
            return;
        }
        int currentItem = -1;
        final int itemSlot = getItemSlot(Items.TOTEM_OF_UNDYING);
        for (int i = 0; i < 9; ++i) {
            if (AutoTotem.mc.player.inventory.getStackInSlot(i).getItem() == Items.TOTEM_OF_UNDYING) {
                currentItem = i;
                break;
            }
        }
        final int n = (itemSlot < 9) ? (itemSlot + 36) : itemSlot;
        if (itemSlot != -1) {
            AutoTotem.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, 36, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            AutoTotem.mc.playerController.windowClick(0, n, 0, ClickType.PICKUP, (EntityPlayer)AutoTotem.mc.player);
            if ((currentItem != -1 && AutoTotem.mc.player.getHealth() <= this.health.getValue() && this.strict.getValue()) || (currentItem != -1 && !this.strict.getValue())) {
                AutoTotem.mc.player.inventory.currentItem = currentItem;
            }
        }
    }
    
    public AutoTotem() {
        super("AutoTotem", "Main Hand AutoTotem", Category.COMBAT, true, false, false);
        this.strict = (Setting<Boolean>)this.register(new Setting("Strict", (T)true));
        this.health = (Setting<Integer>)this.register(new Setting("Health", (T)13, (T)0, (T)36, p0 -> this.strict.getValue()));
    }
}

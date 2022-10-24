//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.util.*;
import java.util.*;
import net.minecraft.init.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;

public class Replenish extends Module
{
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> webStack;
    private final /* synthetic */ Setting<Integer> gapStack;
    private final /* synthetic */ Setting<Integer> xpStackAt;
    private final /* synthetic */ ArrayList<Item> Hotbar;
    
    private boolean RefillSlotIfNeed(final int n) {
        final ItemStack getStackInSlot = Replenish.mc.player.inventory.getStackInSlot(n);
        if (getStackInSlot.isEmpty() || getStackInSlot.getItem() == Items.AIR) {
            return false;
        }
        if (!getStackInSlot.isStackable()) {
            return false;
        }
        if (getStackInSlot.getCount() >= getStackInSlot.getMaxStackSize()) {
            return false;
        }
        if (getStackInSlot.getItem().equals(Items.GOLDEN_APPLE) && getStackInSlot.getCount() >= this.gapStack.getValue()) {
            return false;
        }
        if (getStackInSlot.getItem().equals(Items.EXPERIENCE_BOTTLE) && getStackInSlot.getCount() > this.xpStackAt.getValue()) {
            return false;
        }
        if (getStackInSlot.getItem().equals(Blocks.WEB) && getStackInSlot.getCount() > this.webStack.getValue()) {
            return false;
        }
        for (int i = 9; i < 36; ++i) {
            final ItemStack getStackInSlot2 = Replenish.mc.player.inventory.getStackInSlot(i);
            if (!getStackInSlot2.isEmpty() && this.CanItemBeMergedWith(getStackInSlot, getStackInSlot2)) {
                Replenish.mc.playerController.windowClick(Replenish.mc.player.inventoryContainer.windowId, i, 0, ClickType.QUICK_MOVE, (EntityPlayer)Replenish.mc.player);
                Replenish.mc.playerController.updateController();
                return true;
            }
        }
        return false;
    }
    
    private boolean CanItemBeMergedWith(final ItemStack itemStack, final ItemStack itemStack2) {
        return itemStack.getItem() == itemStack2.getItem() && itemStack.getDisplayName().equals(itemStack2.getDisplayName());
    }
    
    public void onUpdate() {
        if (Replenish.mc.currentScreen != null) {
            return;
        }
        if (!this.timer.passedMs(this.delay.getValue() * 1000)) {
            return;
        }
        for (int i = 0; i < 9; ++i) {
            if (this.RefillSlotIfNeed(i)) {
                this.timer.reset();
                return;
            }
        }
    }
    
    public Replenish() {
        super("Replenish", "Automatic replenishment.", Module.Category.PLAYER, false, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)0, (T)0, (T)10));
        this.gapStack = (Setting<Integer>)this.register(new Setting("GapStack", (T)20, (T)1, (T)64));
        this.xpStackAt = (Setting<Integer>)this.register(new Setting("XPStack", (T)20, (T)1, (T)64));
        this.webStack = (Setting<Integer>)this.register(new Setting("WebStack", (T)20, (T)1, (T)64));
        this.timer = new Timer();
        this.Hotbar = new ArrayList<Item>();
    }
    
    public void onEnable() {
        if (fullNullCheck()) {
            return;
        }
        this.Hotbar.clear();
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = Replenish.mc.player.inventory.getStackInSlot(i);
            if (!getStackInSlot.isEmpty() && !this.Hotbar.contains(getStackInSlot.getItem())) {
                this.Hotbar.add(getStackInSlot.getItem());
            }
            else {
                this.Hotbar.add(Items.AIR);
            }
        }
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.client.settings.*;

public class AutoEat extends Module
{
    public final /* synthetic */ Setting<Float> health;
    public final /* synthetic */ Setting<Boolean> preferGaps;
    /* synthetic */ boolean resetKeyBind;
    /* synthetic */ int originalSlot;
    /* synthetic */ boolean firstSwap;
    public final /* synthetic */ Setting<Float> hunger;
    public final /* synthetic */ Setting<Boolean> autoSwitch;
    
    private int findFoodSlot() {
        int n = -1;
        float n2 = 0.0f;
        if (n == -1) {
            for (int i = 0; i < 9; ++i) {
                final ItemStack getStackInSlot = AutoEat.mc.player.inventory.getStackInSlot(i);
                if (getStackInSlot.getItem() instanceof ItemFood) {
                    if (this.preferGaps.getValue() && getStackInSlot.getItem() == Items.GOLDEN_APPLE) {
                        n = i;
                        break;
                    }
                    final float n3 = (float)((ItemFood)getStackInSlot.getItem()).getHealAmount(getStackInSlot);
                    if (n3 > n2) {
                        n2 = n3;
                        n = i;
                    }
                }
            }
        }
        return n;
    }
    
    public void onUpdate() {
        if (AutoEat.mc.player == null || AutoEat.mc.world == null) {
            return;
        }
        if (AutoEat.mc.player.isCreative()) {
            return;
        }
        if (AutoEat.mc.player.getHealth() + AutoEat.mc.player.getAbsorptionAmount() <= this.health.getValue() || AutoEat.mc.player.getFoodStats().getFoodLevel() <= this.hunger.getValue()) {
            if (this.autoSwitch.getValue()) {
                final int foodSlot = this.findFoodSlot();
                if (this.firstSwap) {
                    this.originalSlot = AutoEat.mc.player.inventory.currentItem;
                    this.firstSwap = false;
                }
                if (foodSlot != -1) {
                    AutoEat.mc.player.inventory.currentItem = foodSlot;
                    AutoEat.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(foodSlot));
                }
            }
            if (AutoEat.mc.player.getHeldItem(EnumHand.MAIN_HAND).getItem() instanceof ItemFood) {
                if (AutoEat.mc.currentScreen == null || AutoEat.mc.currentScreen instanceof GuiInventory) {
                    KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), true);
                    this.resetKeyBind = true;
                }
                else {
                    AutoEat.mc.playerController.processRightClick((EntityPlayer)AutoEat.mc.player, (World)AutoEat.mc.world, EnumHand.MAIN_HAND);
                }
            }
            else if (AutoEat.mc.currentScreen == null || AutoEat.mc.currentScreen instanceof GuiInventory) {
                KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), GameSettings.isKeyDown(AutoEat.mc.gameSettings.keyBindUseItem));
            }
        }
        else {
            if (this.resetKeyBind) {
                KeyBinding.setKeyBindState(AutoEat.mc.gameSettings.keyBindUseItem.getKeyCode(), GameSettings.isKeyDown(AutoEat.mc.gameSettings.keyBindUseItem));
                this.resetKeyBind = false;
            }
            if (this.originalSlot != -1) {
                AutoEat.mc.player.inventory.currentItem = this.originalSlot;
                AutoEat.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(this.originalSlot));
                this.originalSlot = -1;
                this.firstSwap = true;
            }
        }
    }
    
    public AutoEat() {
        super("AutoEat", "Auto eat", Module.Category.PLAYER, true, false, false);
        this.health = (Setting<Float>)this.register(new Setting("Health", (T)10.0f, (T)0.0f, (T)36.0f));
        this.hunger = (Setting<Float>)this.register(new Setting("Hunger", (T)15.0f, (T)0.0f, (T)20.0f));
        this.autoSwitch = (Setting<Boolean>)this.register(new Setting("AutoSwitch", (T)true));
        this.preferGaps = (Setting<Boolean>)this.register(new Setting("PreferGaps", (T)false));
        this.originalSlot = -1;
        this.firstSwap = true;
        this.resetKeyBind = false;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraft.init.*;
import me.abHack.util.*;
import net.minecraft.network.*;
import net.minecraft.network.play.client.*;
import net.minecraft.item.*;
import net.minecraft.util.*;

public class AutoXP extends Module
{
    private final /* synthetic */ Setting<Boolean> sneakOnly;
    private final /* synthetic */ Setting<Integer> maxHeal;
    public static /* synthetic */ AutoXP INSTANCE;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> predict;
    /* synthetic */ char toMend;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> minDamage;
    public final /* synthetic */ Setting<Bind> bind;
    
    public AutoXP() {
        super("AutoXP", "AutoXP", Module.Category.PLAYER, true, false, false);
        this.toMend = '\0';
        this.timer = new Timer();
        this.delay = (Setting<Integer>)this.register(new Setting("XP Delay", (T)4, (T)0, (T)4));
        this.minDamage = (Setting<Integer>)this.register(new Setting("Min Damage", (T)50, (T)0, (T)100));
        this.maxHeal = (Setting<Integer>)this.register(new Setting("Repair To", (T)90, (T)0, (T)100));
        this.sneakOnly = (Setting<Boolean>)this.register(new Setting("Sneak Only", (T)false));
        this.predict = (Setting<Boolean>)this.register(new Setting("Predict", (T)false));
        this.bind = (Setting<Bind>)this.register(new Setting("PacketBind", (T)new Bind(-1)));
        AutoXP.INSTANCE = this;
    }
    
    private void mendArmor() {
        final int itemHotbar = InventoryUtil.getItemHotbar(Items.EXPERIENCE_BOTTLE);
        final int currentItem = AutoXP.mc.player.inventory.currentItem;
        if (itemHotbar == -1) {
            return;
        }
        AutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayer.Rotation(AutoXP.mc.player.rotationYaw, 90.0f, true));
        AutoXP.mc.player.inventory.currentItem = itemHotbar;
        AutoXP.mc.playerController.updateController();
        AutoXP.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.MAIN_HAND));
        AutoXP.mc.player.inventory.currentItem = currentItem;
        AutoXP.mc.playerController.updateController();
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (this.bind.getValue().isDown()) {
            this.mendArmor();
        }
        int n = 0;
        final NonNullList armorInventory = AutoXP.mc.player.inventory.armorInventory;
        for (int i = 0; i < armorInventory.size(); ++i) {
            final ItemStack itemStack = (ItemStack)armorInventory.get(i);
            if (!itemStack.isEmpty) {
                final float n2 = 100.0f - 100.0f * (1.0f - (itemStack.getMaxDamage() - itemStack.getItemDamage()) / (float)itemStack.getMaxDamage());
                if (n2 <= this.maxHeal.getValue()) {
                    if (n2 <= this.minDamage.getValue()) {
                        this.toMend |= (char)(1 << i);
                    }
                    if (this.predict.getValue()) {
                        n = (int)(n + itemStack.getMaxDamage() * this.maxHeal.getValue() / 100.0f - (itemStack.getMaxDamage() - itemStack.getItemDamage()));
                    }
                }
                else {
                    this.toMend &= (char)~(1 << i);
                }
            }
        }
        if (this.toMend > '\0' && this.timer.passedMs(this.delay.getValue() * 45)) {
            this.timer.reset();
            if (this.predict.getValue()) {
                if (AutoXP.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityXPOrb).filter(entity2 -> entity2.getDistanceSq((Entity)AutoXP.mc.player) <= 1.0).mapToInt(entityXPOrb -> entityXPOrb.xpValue).sum() * 2 < n) {
                    this.mendArmor();
                }
            }
            else if (this.sneakOnly.getValue() && AutoXP.mc.player.isSneaking()) {
                this.mendArmor();
            }
        }
    }
}

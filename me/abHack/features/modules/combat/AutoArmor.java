//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.util.*;
import net.minecraft.entity.player.*;
import me.abHack.*;
import net.minecraft.entity.*;
import java.util.concurrent.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.init.*;
import me.abHack.features.modules.player.*;
import me.abHack.features.setting.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import java.util.*;

public class AutoArmor extends Module
{
    private final /* synthetic */ Setting<Integer> closestEnemy;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    private final /* synthetic */ Setting<Boolean> mendingTakeOff;
    private final /* synthetic */ List<Integer> doneSlots;
    /* synthetic */ boolean flag;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Integer> delay;
    private final /* synthetic */ Setting<Boolean> curse;
    private final /* synthetic */ Setting<Integer> repair;
    private final /* synthetic */ Setting<Integer> actions;
    
    @Override
    public void onLogout() {
        this.taskList.clear();
        this.doneSlots.clear();
    }
    
    @Override
    public void onDisable() {
        this.taskList.clear();
        this.doneSlots.clear();
        this.flag = false;
    }
    
    @Override
    public void onLogin() {
        this.timer.reset();
    }
    
    public AutoArmor() {
        super("AutoArmor", "Puts Armor on for you.", Category.COMBAT, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)50, (T)0, (T)500));
        this.curse = (Setting<Boolean>)this.register(new Setting("Vanishing", (T)false));
        this.mendingTakeOff = (Setting<Boolean>)this.register(new Setting("AutoMend", (T)true));
        this.closestEnemy = (Setting<Integer>)this.register(new Setting("Enemy", (T)8, (T)1, (T)20, p0 -> this.mendingTakeOff.getValue()));
        this.repair = (Setting<Integer>)this.register(new Setting("Repair%", (T)90, (T)1, (T)100, p0 -> this.mendingTakeOff.getValue()));
        this.actions = (Setting<Integer>)this.register(new Setting("Packets", (T)3, (T)1, (T)12));
        this.timer = new Timer();
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.doneSlots = new ArrayList<Integer>();
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck() || (AutoArmor.mc.currentScreen instanceof GuiContainer && !(AutoArmor.mc.currentScreen instanceof GuiInventory))) {
            return;
        }
        if (this.taskList.isEmpty()) {
            if (this.mendingTakeOff.getValue() && InventoryUtil.getItemHotbar(Items.EXPERIENCE_BOTTLE) != -1 && AutoXP.INSTANCE.isEnabled() && AutoXP.INSTANCE.bind.getValue().isDown() && AutoArmor.mc.world.playerEntities.stream().noneMatch(entity -> entity != AutoArmor.mc.player && !OyVey.friendManager.isFriend(((EntityPlayer)entity).getName()) && AutoArmor.mc.player.getDistance(entity) <= this.closestEnemy.getValue()) && !this.flag) {
                int n = 0;
                final Iterator<Map.Entry<Integer, ItemStack>> iterator = this.getArmor().entrySet().iterator();
                while (iterator.hasNext()) {
                    final ItemStack itemStack = iterator.next().getValue();
                    if (Math.round(itemStack.getMaxDamage() * (this.repair.getValue() / 100.0f)) >= itemStack.getMaxDamage() - itemStack.getItemDamage()) {
                        continue;
                    }
                    ++n;
                }
                if (n == 4) {
                    this.flag = true;
                }
                if (!this.flag) {
                    final ItemStack getStack = AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack();
                    final int n2;
                    if (!getStack.isEmpty && Math.round(getStack.getMaxDamage() * (this.repair.getValue() / 100.0f)) < (n2 = getStack.getMaxDamage() - getStack.getItemDamage())) {
                        this.takeOffSlot(5);
                    }
                    final ItemStack getStack2 = AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack();
                    final int n3;
                    if (!getStack2.isEmpty && Math.round(getStack2.getMaxDamage() * (this.repair.getValue() / 100.0f)) < (n3 = getStack2.getMaxDamage() - getStack2.getItemDamage())) {
                        this.takeOffSlot(6);
                    }
                    final ItemStack getStack3 = AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack();
                    final int n4;
                    if (!getStack3.isEmpty && Math.round(getStack3.getMaxDamage() * (this.repair.getValue() / 100.0f)) < (n4 = getStack3.getMaxDamage() - getStack3.getItemDamage())) {
                        this.takeOffSlot(7);
                    }
                    final ItemStack getStack4 = AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack();
                    final int n5;
                    if (!getStack4.isEmpty && Math.round(getStack4.getMaxDamage() * (this.repair.getValue() / 100.0f)) < (n5 = getStack4.getMaxDamage() - getStack4.getItemDamage())) {
                        this.takeOffSlot(8);
                    }
                }
                return;
            }
            this.flag = false;
            final int armorSlot;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(5).getStack().getItem() == Items.AIR && (armorSlot = InventoryUtil.findArmorSlot(EntityEquipmentSlot.HEAD, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(5, armorSlot);
            }
            final int armorSlot2;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(6).getStack().getItem() == Items.AIR && (armorSlot2 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.CHEST, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(6, armorSlot2);
            }
            final int armorSlot3;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(7).getStack().getItem() == Items.AIR && (armorSlot3 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.LEGS, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(7, armorSlot3);
            }
            final int armorSlot4;
            if (AutoArmor.mc.player.inventoryContainer.getSlot(8).getStack().getItem() == Items.AIR && (armorSlot4 = InventoryUtil.findArmorSlot(EntityEquipmentSlot.FEET, this.curse.getValue(), true)) != -1) {
                this.getSlotOn(8, armorSlot4);
            }
        }
        if (this.timer.passedMs((int)(this.delay.getValue() * OyVey.serverManager.getTpsFactor()))) {
            if (!this.taskList.isEmpty()) {
                for (int i = 0; i < this.actions.getValue(); ++i) {
                    final InventoryUtil.Task task = this.taskList.poll();
                    if (task != null) {
                        task.run();
                    }
                }
            }
            this.timer.reset();
        }
    }
    
    private void getSlotOn(final int n, final int i) {
        if (this.taskList.isEmpty()) {
            this.doneSlots.remove((Object)i);
            this.taskList.add(new InventoryUtil.Task(i));
            this.taskList.add(new InventoryUtil.Task(n));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    private void takeOffSlot(final int n) {
        if (this.taskList.isEmpty()) {
            int i = -1;
            for (final int intValue : InventoryUtil.findEmptySlots(true)) {
                if (this.doneSlots.contains(i)) {
                    continue;
                }
                i = intValue;
                this.doneSlots.add(intValue);
            }
            if (i != -1) {
                this.taskList.add(new InventoryUtil.Task(n));
                this.taskList.add(new InventoryUtil.Task(i));
                this.taskList.add(new InventoryUtil.Task());
            }
        }
    }
    
    private Map<Integer, ItemStack> getArmor() {
        return this.getInventorySlots(5, 8);
    }
    
    private Map<Integer, ItemStack> getInventorySlots(int i, final int n) {
        final HashMap<Integer, Object> hashMap = (HashMap<Integer, Object>)new HashMap<Integer, ItemStack>();
        while (i <= n) {
            hashMap.put(i, AutoArmor.mc.player.inventoryContainer.getInventory().get(i));
            ++i;
        }
        return (Map<Integer, ItemStack>)hashMap;
    }
}

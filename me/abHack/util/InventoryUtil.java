//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import java.util.concurrent.atomic.*;
import net.minecraft.item.*;
import me.abHack.*;
import net.minecraft.client.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;

public class InventoryUtil implements Util
{
    public static int findItemInventorySlot(final Item item, final boolean b, final boolean b2) {
        int itemInventorySlot = findItemInventorySlot(item, b);
        if (itemInventorySlot == -1 && b2) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR) {
                    if (getStack.getItem() == item) {
                        itemInventorySlot = i;
                    }
                }
            }
        }
        return itemInventorySlot;
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b, final boolean b2) {
        int armorSlot = findArmorSlot(entityEquipmentSlot, b);
        if (armorSlot == -1 && b2) {
            float n = 0.0f;
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                    final ItemArmor itemArmor;
                    if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                        final float n2 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                        int n3 = 0;
                        if (!b || !EnchantmentHelper.hasBindingCurse(getStack)) {
                            n3 = 0;
                        }
                        final int n4 = n3;
                        if (n2 > n) {
                            if (n4 == 0) {
                                n = n2;
                                armorSlot = i;
                            }
                        }
                    }
                }
            }
        }
        return armorSlot;
    }
    
    public static int findHotbarBlock(final Class clazz) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY) {
                if (clazz.isInstance(getStackInSlot.getItem())) {
                    return i;
                }
                if (getStackInSlot.getItem() instanceof ItemBlock) {
                    if (clazz.isInstance(((ItemBlock)getStackInSlot.getItem()).getBlock())) {
                        return i;
                    }
                }
            }
        }
        return -1;
    }
    
    public static int findBlockSlotInventory(final Class clazz, final boolean b, final boolean b2) {
        int inventoryBlock = findInventoryBlock(clazz, b);
        if (inventoryBlock == -1 && b2) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.getItem() != Items.AIR) {
                    final Item getItem = getStack.getItem();
                    if (clazz.isInstance(getItem)) {
                        inventoryBlock = i;
                    }
                    else if (getItem instanceof ItemBlock) {
                        if (clazz.isInstance(((ItemBlock)getItem).getBlock())) {
                            inventoryBlock = i;
                        }
                    }
                }
            }
        }
        return inventoryBlock;
    }
    
    public static int findStackInventory(final Item item, final boolean b) {
        int n = 0;
        if (!b) {
            n = 9;
        }
        for (int i = n; i < 36; ++i) {
            if (Item.getIdFromItem(item) == Item.getIdFromItem(InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem())) {
                return i + ((i < 9) ? 36 : 0);
            }
        }
        return -1;
    }
    
    public static int findStackInventory(final Item item) {
        return findStackInventory(item, false);
    }
    
    public static int findHotbarBlock(final Block block) {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock && ((ItemBlock)getStackInSlot.getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    private static Map<Integer, ItemStack> getInventorySlots(final int n, final int n2) {
        final HashMap<Integer, Object> hashMap = (HashMap<Integer, Object>)new HashMap<Integer, ItemStack>();
        for (int i = n; i <= n2; ++i) {
            hashMap.put(i, InventoryUtil.mc.player.inventoryContainer.getInventory().get(i));
        }
        return (Map<Integer, ItemStack>)hashMap;
    }
    
    public static List<Integer> findEmptySlots(final boolean b) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (!entry.getValue().isEmpty && entry.getValue().getItem() != Items.AIR) {
                continue;
            }
            list.add(entry.getKey());
        }
        if (b) {
            for (int i = 1; i < 5; ++i) {
                final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
                if (getStack.isEmpty() || getStack.getItem() == Items.AIR) {
                    list.add(i);
                }
            }
        }
        return (List<Integer>)list;
    }
    
    public static boolean isBlock(final Item item, final Class clazz) {
        return item instanceof ItemBlock && clazz.isInstance(((ItemBlock)item).getBlock());
    }
    
    public static void confirmSlot(final int currentItem) {
        InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
        InventoryUtil.mc.player.inventory.currentItem = currentItem;
        InventoryUtil.mc.playerController.updateController();
    }
    
    public static boolean isSlotEmpty(final int n) {
        return InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(n).getStack().isEmpty();
    }
    
    public static void switchToHotbarSlot(final Class clazz, final boolean b) {
        final int hotbarBlock = findHotbarBlock(clazz);
        if (hotbarBlock > -1) {
            switchToHotbarSlot(hotbarBlock, b);
        }
    }
    
    public static int findInventoryBlock(final Class clazz, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (isBlock(entry.getValue().getItem(), clazz)) {
                if (entry.getKey() == 45 && !b) {
                    continue;
                }
                atomicInteger.set(entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static int findItemInventorySlot(final Item item, final boolean b) {
        final AtomicInteger atomicInteger = new AtomicInteger();
        atomicInteger.set(-1);
        for (final Map.Entry<Integer, ItemStack> entry : getInventoryAndHotbarSlots().entrySet()) {
            if (entry.getValue().getItem() == item) {
                if (entry.getKey() == 45 && !b) {
                    continue;
                }
                atomicInteger.set(entry.getKey());
                return atomicInteger.get();
            }
        }
        return atomicInteger.get();
    }
    
    public static Map<Integer, ItemStack> getInventoryAndHotbarSlots() {
        return getInventorySlots(9, 44);
    }
    
    public static EntityEquipmentSlot getEquipmentFromSlot(final int n) {
        if (n == 5) {
            return EntityEquipmentSlot.HEAD;
        }
        if (n == 6) {
            return EntityEquipmentSlot.CHEST;
        }
        if (n == 7) {
            return EntityEquipmentSlot.LEGS;
        }
        return EntityEquipmentSlot.FEET;
    }
    
    public static boolean isNull(final ItemStack itemStack) {
        return itemStack == null || itemStack.getItem() instanceof ItemAir;
    }
    
    public static void switchToHotbarSlot(final int currentItem, final boolean b) {
        if (InventoryUtil.mc.player.inventory.currentItem == currentItem || currentItem < 0) {
            return;
        }
        if (b) {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil.mc.playerController.updateController();
        }
        else {
            InventoryUtil.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            InventoryUtil.mc.player.inventory.currentItem = currentItem;
            InventoryUtil.mc.playerController.updateController();
        }
    }
    
    public static boolean[] switchItemToItem(final boolean b, final int n, final boolean b2, final Switch switch1, final Item item) {
        final boolean[] array = { b2, false };
        switch (switch1) {
            case NORMAL: {
                if (!b && !b2) {
                    switchToHotbarSlot(getItemHotbar(item), false);
                    array[0] = true;
                }
                else if (b && b2) {
                    switchToHotbarSlot(n, false);
                    array[0] = false;
                }
                array[1] = true;
                break;
            }
            case SILENT: {
                if (!b && !b2) {
                    switchToHotbarSlot(getItemHotbar(item), true);
                    array[0] = true;
                }
                else if (b && b2) {
                    array[0] = false;
                    OyVey.inventoryManager.recoverSilent(n);
                }
                array[1] = true;
                break;
            }
            case NONE: {
                array[1] = (b || InventoryUtil.mc.player.inventory.currentItem == getItemHotbar(item));
                break;
            }
        }
        return array;
    }
    
    public static int getItemHotbars(final Item item) {
        for (int i = 0; i < 36; ++i) {
            if (Item.getIdFromItem(InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem()) == Item.getIdFromItem(item)) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean areStacksCompatible(final ItemStack itemStack, final ItemStack itemStack2) {
        return itemStack.getItem().equals(itemStack2.getItem()) && (!(itemStack.getItem() instanceof ItemBlock) || !(itemStack2.getItem() instanceof ItemBlock) || ((ItemBlock)itemStack.getItem()).getBlock().material.equals(((ItemBlock)itemStack2.getItem()).getBlock().material)) && itemStack.getDisplayName().equals(itemStack2.getDisplayName()) && itemStack.getItemDamage() == itemStack2.getItemDamage();
    }
    
    public static int convertHotbarToInv(final int n) {
        return 36 + n;
    }
    
    public static int getItemHotbar(final Item item) {
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(InventoryUtil.mc.player.inventory.getStackInSlot(i).getItem()) == Item.getIdFromItem(item)) {
                return i;
            }
        }
        return -1;
    }
    
    public static void setSlot(final int currentItem) {
        if (currentItem <= 8 && currentItem >= 0) {
            InventoryUtil.mc.player.inventory.currentItem = currentItem;
        }
    }
    
    public static boolean[] switchItem(final boolean b, final int n, final boolean b2, final Switch switch1, final Class clazz) {
        final boolean[] array = { b2, false };
        switch (switch1) {
            case NORMAL: {
                if (!b && !b2) {
                    switchToHotbarSlot(findHotbarBlock(clazz), false);
                    array[0] = true;
                }
                else if (b && b2) {
                    switchToHotbarSlot(n, false);
                    array[0] = false;
                }
                array[1] = true;
                break;
            }
            case SILENT: {
                if (!b && !b2) {
                    switchToHotbarSlot(findHotbarBlock(clazz), true);
                    array[0] = true;
                }
                else if (b && b2) {
                    array[0] = false;
                    OyVey.inventoryManager.recoverSilent(n);
                }
                array[1] = true;
                break;
            }
            case NONE: {
                array[1] = (b || InventoryUtil.mc.player.inventory.currentItem == findHotbarBlock(clazz));
                break;
            }
        }
        return array;
    }
    
    public static int getSlot() {
        return InventoryUtil.mc.player.inventory.currentItem;
    }
    
    public static boolean isInstanceOf(final ItemStack itemStack, final Class clazz) {
        if (itemStack == null) {
            return false;
        }
        final Item getItem = itemStack.getItem();
        return clazz.isInstance(getItem) || (getItem instanceof ItemBlock && clazz.isInstance(Block.getBlockFromItem(getItem)));
    }
    
    public static int findAnyBlock() {
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InventoryUtil.mc.player.inventory.getStackInSlot(i);
            if (getStackInSlot != ItemStack.EMPTY && getStackInSlot.getItem() instanceof ItemBlock) {
                return i;
            }
        }
        return -1;
    }
    
    public static int getEmptyXCarry() {
        for (int i = 1; i < 5; ++i) {
            final ItemStack getStack = InventoryUtil.mc.player.inventoryContainer.inventorySlots.get(i).getStack();
            if (getStack.isEmpty() || getStack.getItem() == Items.AIR) {
                return i;
            }
        }
        return -1;
    }
    
    public static boolean holdingItem(final Class clazz) {
        final ItemStack getHeldItemMainhand = InventoryUtil.mc.player.getHeldItemMainhand();
        boolean b = isInstanceOf(getHeldItemMainhand, clazz);
        if (!b) {
            InventoryUtil.mc.player.getHeldItemOffhand();
            b = isInstanceOf(getHeldItemMainhand, clazz);
        }
        return b;
    }
    
    public static int pickItem(final int n) {
        final ArrayList<Object> list = new ArrayList<Object>();
        for (int i = 0; i < 9; ++i) {
            if (Item.getIdFromItem(((ItemStack)InventoryUtil.mc.player.inventory.mainInventory.get(i)).getItem()) == n) {
                list.add(InventoryUtil.mc.player.inventory.mainInventory.get(i));
            }
        }
        if (list.size() >= 1) {
            return InventoryUtil.mc.player.inventory.mainInventory.indexOf(list.get(0));
        }
        return -1;
    }
    
    public static int findArmorSlot(final EntityEquipmentSlot entityEquipmentSlot, final boolean b) {
        int n = -1;
        float n2 = 0.0f;
        for (int i = 9; i < 45; ++i) {
            final ItemStack getStack = Minecraft.getMinecraft().player.inventoryContainer.getSlot(i).getStack();
            if (getStack.getItem() != Items.AIR && getStack.getItem() instanceof ItemArmor) {
                final ItemArmor itemArmor;
                if ((itemArmor = (ItemArmor)getStack.getItem()).getEquipmentSlot() == entityEquipmentSlot) {
                    final float n3 = (float)(itemArmor.damageReduceAmount + EnchantmentHelper.getEnchantmentLevel(Enchantments.PROTECTION, getStack));
                    int n4 = 0;
                    if (!b || !EnchantmentHelper.hasBindingCurse(getStack)) {
                        n4 = 0;
                    }
                    final int n5 = n4;
                    if (n3 > n2) {
                        if (n5 == 0) {
                            n2 = n3;
                            n = i;
                        }
                    }
                }
            }
        }
        return n;
    }
    
    public static class Task
    {
        private final /* synthetic */ int slot;
        private final /* synthetic */ boolean quickClick;
        private final /* synthetic */ boolean update;
        
        public Task(final int slot, final boolean quickClick) {
            this.slot = slot;
            this.quickClick = quickClick;
            this.update = false;
        }
        
        public void run() {
            if (this.update) {
                Util.mc.playerController.updateController();
            }
            if (this.slot != -1) {
                Util.mc.playerController.windowClick(0, this.slot, 0, this.quickClick ? ClickType.QUICK_MOVE : ClickType.PICKUP, (EntityPlayer)Util.mc.player);
            }
        }
        
        public boolean isSwitching() {
            return !this.update;
        }
        
        public Task() {
            this.update = true;
            this.slot = -1;
            this.quickClick = false;
        }
        
        public Task(final int slot) {
            this.slot = slot;
            this.quickClick = false;
            this.update = false;
        }
    }
    
    public enum Switch
    {
        NORMAL, 
        NONE, 
        SILENT;
    }
}

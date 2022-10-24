//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import java.util.*;
import net.minecraft.init.*;
import org.lwjgl.input.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;
import java.util.concurrent.*;
import java.util.function.*;
import net.minecraft.item.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import net.minecraft.inventory.*;
import net.minecraft.client.gui.inventory.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;

public class Offhand extends Module
{
    public /* synthetic */ Setting<Boolean> gapple;
    private /* synthetic */ boolean switchedForHealthReason;
    public /* synthetic */ int beds;
    private final /* synthetic */ Timer secondTimer;
    public /* synthetic */ Setting<Float> bedHealth;
    public /* synthetic */ Setting<Float> bedHoleHealth;
    public /* synthetic */ boolean holdingGapple;
    public /* synthetic */ int lastCrystalSlot;
    private /* synthetic */ boolean second;
    public /* synthetic */ int crystals;
    public /* synthetic */ Setting<Float> crystalHealth;
    public /* synthetic */ Setting<Integer> actions;
    public /* synthetic */ boolean holdingCrystal;
    public /* synthetic */ boolean holdingBed;
    public /* synthetic */ int lastGappleSlot;
    public /* synthetic */ int gapples;
    public /* synthetic */ int lastTotemSlot;
    public /* synthetic */ boolean holdingTotem;
    public /* synthetic */ Setting<Boolean> armorCheck;
    public /* synthetic */ int totems;
    public /* synthetic */ int lastBedSlot;
    public /* synthetic */ Mode2 currentMode;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    public /* synthetic */ int lastObbySlot;
    public /* synthetic */ boolean didSwitchThisTick;
    private static /* synthetic */ Offhand instance;
    public /* synthetic */ int lastWebSlot;
    public /* synthetic */ Setting<Float> crystalHoleHealth;
    public /* synthetic */ Setting<Boolean> crystal;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> bed;
    
    @Override
    public void onUpdate() {
        if (this.timer.passedMs(50L)) {
            if (Offhand.mc.player != null && Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && Offhand.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL && Mouse.isButtonDown(1)) {
                Offhand.mc.player.setActiveHand(EnumHand.OFF_HAND);
                Offhand.mc.gameSettings.keyBindUseItem.pressed = Mouse.isButtonDown(1);
            }
        }
        else if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && Offhand.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL) {
            Offhand.mc.gameSettings.keyBindUseItem.pressed = false;
        }
        if (nullCheck()) {
            return;
        }
        this.doOffhand();
        if (this.secondTimer.passedMs(50L) && this.second) {
            this.second = false;
            this.timer.reset();
        }
    }
    
    private void putItemInOffhand(final int n, final int n2) {
        if (n != -1 && this.taskList.isEmpty()) {
            this.taskList.add(new InventoryUtil.Task(n));
            this.taskList.add(new InventoryUtil.Task(45));
            this.taskList.add(new InventoryUtil.Task(n2));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final ProcessRightClickBlockEvent processRightClickBlockEvent) {
        if (processRightClickBlockEvent.hand == EnumHand.MAIN_HAND && processRightClickBlockEvent.stack.getItem() == Items.END_CRYSTAL && Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && Offhand.mc.objectMouseOver != null && processRightClickBlockEvent.pos == Offhand.mc.objectMouseOver.getBlockPos()) {
            processRightClickBlockEvent.setCanceled(true);
            Offhand.mc.player.setActiveHand(EnumHand.OFF_HAND);
            Offhand.mc.playerController.processRightClick((EntityPlayer)Offhand.mc.player, (World)Offhand.mc.world, EnumHand.OFF_HAND);
        }
    }
    
    public void setMode(final Mode2 mode2) {
        this.currentMode = ((this.currentMode == mode2) ? Mode2.TOTEMS : mode2);
    }
    
    private int getLastSlot(final Item item, final int n) {
        if (item == Items.END_CRYSTAL) {
            return this.lastCrystalSlot;
        }
        if (item == Items.GOLDEN_APPLE) {
            return this.lastGappleSlot;
        }
        if (item == Items.TOTEM_OF_UNDYING) {
            return this.lastTotemSlot;
        }
        if (InventoryUtil.isBlock(item, BlockObsidian.class)) {
            return this.lastObbySlot;
        }
        if (InventoryUtil.isBlock(item, BlockWeb.class)) {
            return this.lastWebSlot;
        }
        if (item == Items.AIR) {
            return -1;
        }
        return n;
    }
    
    public Offhand() {
        super("Offhand", "Allows you to switch up your Offhand.", Category.COMBAT, true, false, false);
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.timer = new Timer();
        this.secondTimer = new Timer();
        this.crystal = (Setting<Boolean>)this.register(new Setting("Crystal", (T)true));
        this.crystalHealth = (Setting<Float>)this.register(new Setting("CrystalHP", (T)13.0f, (T)0.1f, (T)36.0f, p0 -> this.crystal.getValue()));
        this.crystalHoleHealth = (Setting<Float>)this.register(new Setting("CrystalHoleHP", (T)3.5f, (T)0.1f, (T)36.0f, p0 -> this.crystal.getValue()));
        this.bed = (Setting<Boolean>)this.register(new Setting("Bed", (T)true, p0 -> !this.crystal.getValue()));
        this.bedHealth = (Setting<Float>)this.register(new Setting("BedHP", (T)13.0f, (T)0.1f, (T)36.0f, p0 -> this.bed.getValue()));
        this.bedHoleHealth = (Setting<Float>)this.register(new Setting("BedHoleHP", (T)3.5f, (T)0.1f, (T)36.0f, p0 -> this.bed.getValue()));
        this.gapple = (Setting<Boolean>)this.register(new Setting("Gapple", (T)true));
        this.armorCheck = (Setting<Boolean>)this.register(new Setting("ArmorCheck", (T)true));
        this.actions = (Setting<Integer>)this.register(new Setting("Packets", (T)4, (T)1, (T)4));
        this.currentMode = Mode2.TOTEMS;
        this.totems = 0;
        this.crystals = 0;
        this.gapples = 0;
        this.beds = 0;
        this.lastTotemSlot = -1;
        this.lastGappleSlot = -1;
        this.lastCrystalSlot = -1;
        this.lastBedSlot = -1;
        this.lastObbySlot = -1;
        this.lastWebSlot = -1;
        this.holdingCrystal = false;
        this.holdingBed = false;
        this.holdingTotem = false;
        this.holdingGapple = false;
        this.didSwitchThisTick = false;
        this.second = false;
        this.switchedForHealthReason = false;
        Offhand.instance = this;
    }
    
    @Override
    public String getDisplayInfo() {
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return "Crystals";
        }
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            return "Totems";
        }
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE) {
            return "Gapples";
        }
        if (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.BED) {
            return "Beds";
        }
        return null;
    }
    
    public void doOffhand() {
        this.didSwitchThisTick = false;
        this.holdingCrystal = (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL);
        this.holdingTotem = (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING);
        this.holdingGapple = (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE);
        this.holdingBed = (Offhand.mc.player.getHeldItemOffhand().getItem() == Items.BED);
        this.totems = Offhand.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (this.holdingTotem) {
            this.totems += Offhand.mc.player.inventory.offHandInventory.stream().filter(itemStack2 -> itemStack2.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        }
        this.crystals = Offhand.mc.player.inventory.mainInventory.stream().filter(itemStack3 -> itemStack3.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();
        if (this.holdingCrystal) {
            this.crystals += Offhand.mc.player.inventory.offHandInventory.stream().filter(itemStack4 -> itemStack4.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum();
        }
        this.beds = Offhand.mc.player.inventory.mainInventory.stream().filter(itemStack5 -> itemStack5.getItem() == Items.BED).mapToInt(ItemStack::getCount).sum();
        if (this.holdingBed) {
            this.beds += Offhand.mc.player.inventory.offHandInventory.stream().filter(itemStack6 -> itemStack6.getItem() == Items.BED).mapToInt(ItemStack::getCount).sum();
        }
        this.gapples = Offhand.mc.player.inventory.mainInventory.stream().filter(itemStack7 -> itemStack7.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
        if (this.holdingGapple) {
            this.gapples += Offhand.mc.player.inventory.offHandInventory.stream().filter(itemStack8 -> itemStack8.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum();
        }
        this.doSwitch();
    }
    
    public void doSwitch() {
        this.currentMode = Mode2.TOTEMS;
        if (this.gapple.getValue() && Offhand.mc.player.getHeldItemMainhand().getItem() instanceof ItemSword && Offhand.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            this.currentMode = Mode2.GAPPLES;
        }
        else if (this.currentMode != Mode2.CRYSTALS && this.crystal.getValue() && ((EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.crystalHoleHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.crystalHealth.getValue())) {
            this.currentMode = Mode2.CRYSTALS;
        }
        else if (this.currentMode != Mode2.BEDS && this.bed.getValue() && ((EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.bedHoleHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.bedHealth.getValue())) {
            this.currentMode = Mode2.BEDS;
        }
        if (this.currentMode == Mode2.CRYSTALS && this.crystals == 0) {
            this.setMode(Mode2.TOTEMS);
        }
        if (this.currentMode == Mode2.BEDS && this.beds == 0) {
            this.setMode(Mode2.TOTEMS);
        }
        if (this.currentMode == Mode2.CRYSTALS && ((!EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) <= this.crystalHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) <= this.crystalHoleHealth.getValue())) {
            if (this.currentMode == Mode2.CRYSTALS) {
                this.switchedForHealthReason = true;
            }
            this.setMode(Mode2.TOTEMS);
        }
        if (this.currentMode == Mode2.BEDS && ((!EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) <= this.bedHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) <= this.bedHoleHealth.getValue())) {
            if (this.currentMode == Mode2.BEDS) {
                this.switchedForHealthReason = true;
            }
            this.setMode(Mode2.TOTEMS);
        }
        if (this.switchedForHealthReason && ((EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.crystalHoleHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.crystalHealth.getValue())) {
            this.setMode(Mode2.CRYSTALS);
            this.switchedForHealthReason = false;
        }
        if (this.switchedForHealthReason && ((EntityUtil.isSafe((Entity)Offhand.mc.player) && EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.bedHoleHealth.getValue()) || EntityUtil.getHealth((Entity)Offhand.mc.player, true) > this.bedHealth.getValue())) {
            this.setMode(Mode2.BEDS);
            this.switchedForHealthReason = false;
        }
        if (this.currentMode == Mode2.CRYSTALS && this.armorCheck.getValue() && (Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items.AIR)) {
            this.setMode(Mode2.TOTEMS);
        }
        if (this.currentMode == Mode2.BEDS && this.armorCheck.getValue() && (Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.HEAD).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.LEGS).getItem() == Items.AIR || Offhand.mc.player.getItemStackFromSlot(EntityEquipmentSlot.FEET).getItem() == Items.AIR)) {
            this.setMode(Mode2.TOTEMS);
        }
        if (Offhand.mc.currentScreen instanceof GuiContainer && !(Offhand.mc.currentScreen instanceof GuiInventory)) {
            return;
        }
        final Item getItem = Offhand.mc.player.getHeldItemOffhand().getItem();
        Label_1186: {
            switch (this.currentMode) {
                case TOTEMS: {
                    if (this.totems <= 0) {
                        break Label_1186;
                    }
                    if (this.holdingTotem) {
                        break Label_1186;
                    }
                    this.lastTotemSlot = InventoryUtil.findItemInventorySlot(Items.TOTEM_OF_UNDYING, false);
                    this.putItemInOffhand(this.lastTotemSlot, this.getLastSlot(getItem, this.lastTotemSlot));
                    break Label_1186;
                }
                case GAPPLES: {
                    if (this.gapples <= 0) {
                        break Label_1186;
                    }
                    if (this.holdingGapple) {
                        break Label_1186;
                    }
                    this.lastGappleSlot = InventoryUtil.findItemInventorySlot(Items.GOLDEN_APPLE, false);
                    this.putItemInOffhand(this.lastGappleSlot, this.getLastSlot(getItem, this.lastGappleSlot));
                    break Label_1186;
                }
                case BEDS: {
                    if (this.beds <= 0) {
                        break Label_1186;
                    }
                    if (this.holdingBed) {
                        break Label_1186;
                    }
                    this.lastBedSlot = InventoryUtil.findItemInventorySlot(Items.BED, false);
                    this.putItemInOffhand(this.lastBedSlot, this.getLastSlot(getItem, this.lastBedSlot));
                    break;
                }
            }
            if (this.crystals > 0) {
                if (!this.holdingCrystal) {
                    this.lastCrystalSlot = InventoryUtil.findItemInventorySlot(Items.END_CRYSTAL, false);
                    this.putItemInOffhand(this.lastCrystalSlot, this.getLastSlot(getItem, this.lastCrystalSlot));
                }
            }
        }
        for (int i = 0; i < this.actions.getValue(); ++i) {
            final InventoryUtil.Task task = this.taskList.poll();
            if (task != null) {
                task.run();
                if (task.isSwitching()) {
                    this.didSwitchThisTick = true;
                }
            }
        }
    }
    
    public static Offhand getInstance() {
        if (Offhand.instance == null) {
            Offhand.instance = new Offhand();
        }
        return Offhand.instance;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (!fullNullCheck() && Offhand.mc.player.getHeldItemOffhand().getItem() == Items.GOLDEN_APPLE && Offhand.mc.player.getHeldItemMainhand().getItem() == Items.END_CRYSTAL && Offhand.mc.gameSettings.keyBindUseItem.isKeyDown()) {
            if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock) {
                if (((CPacketPlayerTryUseItemOnBlock)send.getPacket()).getHand() == EnumHand.MAIN_HAND) {
                    if (this.timer.passedMs(50L)) {
                        Offhand.mc.player.setActiveHand(EnumHand.OFF_HAND);
                        Offhand.mc.player.connection.sendPacket((Packet)new CPacketPlayerTryUseItem(EnumHand.OFF_HAND));
                    }
                    send.setCanceled(true);
                }
            }
            else if (send.getPacket() instanceof CPacketPlayerTryUseItem && ((CPacketPlayerTryUseItem)send.getPacket()).getHand() == EnumHand.OFF_HAND && !this.timer.passedMs(50L)) {
                send.setCanceled(true);
            }
        }
    }
    
    public enum Mode2
    {
        CRYSTALS, 
        BEDS, 
        GAPPLES, 
        TOTEMS;
    }
}

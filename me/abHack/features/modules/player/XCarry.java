//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import java.util.concurrent.atomic.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraftforge.fml.common.gameevent.*;
import me.abHack.features.gui.*;
import me.abHack.features.command.*;
import java.util.concurrent.*;
import org.lwjgl.input.*;
import net.minecraft.inventory.*;
import java.util.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import me.abHack.util.*;
import net.minecraft.entity.player.*;
import java.io.*;

public class XCarry extends Module
{
    private final /* synthetic */ Setting<Bind> keyBind;
    private final /* synthetic */ Setting<Boolean> shiftClicker;
    private final /* synthetic */ Queue<InventoryUtil.Task> taskList;
    private final /* synthetic */ Setting<Integer> slot2;
    private final /* synthetic */ Setting<Bind> autoStore;
    private final /* synthetic */ Setting<Integer> slot1;
    private final /* synthetic */ Setting<Boolean> simpleMode;
    private /* synthetic */ List<Integer> doneSlots;
    private /* synthetic */ boolean autoDuelOn;
    private static /* synthetic */ XCarry INSTANCE;
    private final /* synthetic */ Setting<Boolean> withShift;
    private final /* synthetic */ AtomicBoolean guiNeedsClose;
    private /* synthetic */ boolean slot1done;
    private /* synthetic */ boolean slot3done;
    private /* synthetic */ boolean slot2done;
    private /* synthetic */ boolean guiCloseGuard;
    private /* synthetic */ GuiInventory openedGui;
    private /* synthetic */ boolean obbySlotDone;
    private final /* synthetic */ Setting<Integer> tasks;
    private final /* synthetic */ Setting<Integer> obbySlot;
    private final /* synthetic */ Setting<Integer> slot3;
    
    public static XCarry getInstance() {
        if (XCarry.INSTANCE == null) {
            XCarry.INSTANCE = new XCarry();
        }
        return XCarry.INSTANCE;
    }
    
    public void onDisable() {
        if (!fullNullCheck()) {
            if (!this.simpleMode.getValue()) {
                this.closeGui();
                this.close();
            }
            else {
                XCarry.mc.player.connection.sendPacket((Packet)new CPacketCloseWindow(XCarry.mc.player.inventoryContainer.windowId));
            }
        }
    }
    
    @SubscribeEvent
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState() && !(XCarry.mc.currentScreen instanceof OyVeyGui) && this.autoStore.getValue().getKey() == Keyboard.getEventKey()) {
            this.autoDuelOn = !this.autoDuelOn;
            Command.sendMessage("<XCarry> §aAutostoring...");
        }
    }
    
    public void onLogout() {
        this.onDisable();
    }
    
    static {
        XCarry.INSTANCE = new XCarry();
    }
    
    public XCarry() {
        super("XCarry", "Uses the crafting inventory for storage", Module.Category.PLAYER, true, false, false);
        this.simpleMode = (Setting<Boolean>)this.register(new Setting("Simple", (T)false));
        this.autoStore = (Setting<Bind>)this.register(new Setting("AutoDuel", (T)new Bind(-1)));
        this.obbySlot = (Setting<Integer>)this.register(new Setting("ObbySlot", (T)2, (T)1, (T)9, p0 -> this.autoStore.getValue().getKey() != -1));
        this.slot1 = (Setting<Integer>)this.register(new Setting("Slot1", (T)22, (T)9, (T)44, p0 -> this.autoStore.getValue().getKey() != -1));
        this.slot2 = (Setting<Integer>)this.register(new Setting("Slot2", (T)23, (T)9, (T)44, p0 -> this.autoStore.getValue().getKey() != -1));
        this.slot3 = (Setting<Integer>)this.register(new Setting("Slot3", (T)24, (T)9, (T)44, p0 -> this.autoStore.getValue().getKey() != -1));
        this.tasks = (Setting<Integer>)this.register(new Setting("Actions", (T)3, (T)1, (T)12, p0 -> this.autoStore.getValue().getKey() != -1));
        this.shiftClicker = (Setting<Boolean>)this.register(new Setting("ShiftClick", (T)false));
        this.withShift = (Setting<Boolean>)this.register(new Setting("WithShift", (T)Boolean.TRUE, p0 -> this.shiftClicker.getValue()));
        this.keyBind = (Setting<Bind>)this.register(new Setting("KeyBind", (T)new Bind(-1), p0 -> this.shiftClicker.getValue()));
        this.guiNeedsClose = new AtomicBoolean(false);
        this.taskList = new ConcurrentLinkedQueue<InventoryUtil.Task>();
        this.openedGui = null;
        this.guiCloseGuard = false;
        this.autoDuelOn = false;
        this.obbySlotDone = false;
        this.slot1done = false;
        this.slot2done = false;
        this.slot3done = false;
        this.doneSlots = new ArrayList<Integer>();
        this.setInstance();
    }
    
    public void onUpdate() {
        if (this.shiftClicker.getValue() && XCarry.mc.currentScreen instanceof GuiInventory) {
            final boolean b = this.keyBind.getValue().getKey() != -1 && Keyboard.isKeyDown(this.keyBind.getValue().getKey()) && !Keyboard.isKeyDown(42);
            final Slot slotUnderMouse;
            if (((Keyboard.isKeyDown(42) && this.withShift.getValue()) || b) && Mouse.isButtonDown(0) && (slotUnderMouse = ((GuiInventory)XCarry.mc.currentScreen).getSlotUnderMouse()) != null && InventoryUtil.getEmptyXCarry() != -1) {
                final int slotNumber = slotUnderMouse.slotNumber;
                if (slotNumber > 4 && b) {
                    this.taskList.add(new InventoryUtil.Task(slotNumber));
                    this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                }
                else if (slotNumber > 4 && this.withShift.getValue()) {
                    boolean b2 = true;
                    boolean b3 = true;
                    for (final int intValue : InventoryUtil.findEmptySlots(false)) {
                        if (intValue > 4 && intValue < 36) {
                            b3 = false;
                        }
                        else {
                            if (intValue <= 35) {
                                continue;
                            }
                            if (intValue >= 45) {
                                continue;
                            }
                            b2 = false;
                        }
                    }
                    if (slotNumber > 35 && slotNumber < 45) {
                        if (b3) {
                            this.taskList.add(new InventoryUtil.Task(slotNumber));
                            this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                        }
                    }
                    else if (b2) {
                        this.taskList.add(new InventoryUtil.Task(slotNumber));
                        this.taskList.add(new InventoryUtil.Task(InventoryUtil.getEmptyXCarry()));
                    }
                }
            }
        }
        if (this.autoDuelOn) {
            this.doneSlots = new ArrayList<Integer>();
            if (InventoryUtil.getEmptyXCarry() == -1 || (this.obbySlotDone && this.slot1done && this.slot2done && this.slot3done)) {
                this.autoDuelOn = false;
            }
            if (this.autoDuelOn) {
                if (!this.obbySlotDone && !XCarry.mc.player.inventory.getStackInSlot(this.obbySlot.getValue() - 1).isEmpty) {
                    this.addTasks(36 + this.obbySlot.getValue() - 1);
                }
                this.obbySlotDone = true;
                if (!this.slot1done && !XCarry.mc.player.inventoryContainer.inventorySlots.get(this.slot1.getValue()).getStack().isEmpty) {
                    this.addTasks(this.slot1.getValue());
                }
                this.slot1done = true;
                if (!this.slot2done && !XCarry.mc.player.inventoryContainer.inventorySlots.get(this.slot2.getValue()).getStack().isEmpty) {
                    this.addTasks(this.slot2.getValue());
                }
                this.slot2done = true;
                if (!this.slot3done && !XCarry.mc.player.inventoryContainer.inventorySlots.get(this.slot3.getValue()).getStack().isEmpty) {
                    this.addTasks(this.slot3.getValue());
                }
                this.slot3done = true;
            }
        }
        else {
            this.obbySlotDone = false;
            this.slot1done = false;
            this.slot2done = false;
            this.slot3done = false;
        }
        if (!this.taskList.isEmpty()) {
            for (int i = 0; i < this.tasks.getValue(); ++i) {
                final InventoryUtil.Task task = this.taskList.poll();
                if (task != null) {
                    task.run();
                }
            }
        }
    }
    
    private void close() {
        this.openedGui = null;
        this.guiNeedsClose.set(false);
        this.guiCloseGuard = false;
    }
    
    @SubscribeEvent
    public void onCloseGuiScreen(final PacketEvent.Send send) {
        if (this.simpleMode.getValue() && send.getPacket() instanceof CPacketCloseWindow && ((CPacketCloseWindow)send.getPacket()).windowId == XCarry.mc.player.inventoryContainer.windowId) {
            send.setCanceled(true);
        }
    }
    
    private GuiInventory createGuiWrapper(final GuiInventory guiInventory) {
        try {
            final GuiInventoryWrapper guiInventoryWrapper = new GuiInventoryWrapper();
            ReflectionUtil.copyOf(guiInventory, guiInventoryWrapper);
            return guiInventoryWrapper;
        }
        catch (IllegalAccessException | NoSuchFieldException ex) {
            final Throwable t;
            t.printStackTrace();
            return null;
        }
    }
    
    @SubscribeEvent(priority = EventPriority.LOWEST)
    public void onGuiOpen(final GuiOpenEvent guiOpenEvent) {
        if (!this.simpleMode.getValue()) {
            if (this.guiCloseGuard) {
                guiOpenEvent.setCanceled(true);
            }
            else if (guiOpenEvent.getGui() instanceof GuiInventory) {
                this.openedGui = this.createGuiWrapper((GuiInventory)guiOpenEvent.getGui());
                guiOpenEvent.setGui((GuiScreen)this.openedGui);
                this.guiNeedsClose.set(false);
            }
        }
    }
    
    private void setInstance() {
        XCarry.INSTANCE = this;
    }
    
    private void addTasks(final int n) {
        if (InventoryUtil.getEmptyXCarry() != -1) {
            int emptyXCarry = InventoryUtil.getEmptyXCarry();
            if ((this.doneSlots.contains(emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry)) && (this.doneSlots.contains(++emptyXCarry) || !InventoryUtil.isSlotEmpty(emptyXCarry))) {
                return;
            }
            if (emptyXCarry > 4) {
                return;
            }
            this.doneSlots.add(emptyXCarry);
            this.taskList.add(new InventoryUtil.Task(n));
            this.taskList.add(new InventoryUtil.Task(emptyXCarry));
            this.taskList.add(new InventoryUtil.Task());
        }
    }
    
    private void closeGui() {
        if (this.guiNeedsClose.compareAndSet(true, false) && !fullNullCheck()) {
            this.guiCloseGuard = true;
            XCarry.mc.player.closeScreen();
            if (this.openedGui != null) {
                this.openedGui.onGuiClosed();
                this.openedGui = null;
            }
            this.guiCloseGuard = false;
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting() != null && clientEvent.getSetting().getFeature() != null && clientEvent.getSetting().getFeature().equals(this)) {
            final Setting setting = clientEvent.getSetting();
            final String name = clientEvent.getSetting().getName();
            if (setting.equals(this.simpleMode) && setting.getPlannedValue() != setting.getValue()) {
                this.disable();
            }
            else if (name.equalsIgnoreCase("Store")) {
                clientEvent.setCanceled(true);
                this.autoDuelOn = !this.autoDuelOn;
                Command.sendMessage("<XCarry> §aAutostoring...");
            }
        }
    }
    
    private class GuiInventoryWrapper extends GuiInventory
    {
        GuiInventoryWrapper() {
            super((EntityPlayer)Util.mc.player);
        }
        
        protected void keyTyped(final char c, final int n) throws IOException {
            if (XCarry.this.isEnabled() && (n == 1 || this.mc.gameSettings.keyBindInventory.isActiveAndMatches(n))) {
                XCarry.this.guiNeedsClose.set(true);
                this.mc.displayGuiScreen((GuiScreen)null);
            }
            else {
                super.keyTyped(c, n);
            }
        }
        
        public void onGuiClosed() {
            if (XCarry.this.guiCloseGuard || !XCarry.this.isEnabled()) {
                super.onGuiClosed();
            }
        }
    }
}

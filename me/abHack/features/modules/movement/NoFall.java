//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.movement;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraft.client.multiplayer.*;
import net.minecraft.util.math.*;
import net.minecraft.inventory.*;
import net.minecraft.network.*;
import me.abHack.util.*;
import me.abHack.features.command.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import net.minecraft.network.play.client.*;

public class NoFall extends Module
{
    private final /* synthetic */ Setting<Integer> distance;
    private /* synthetic */ boolean equipped;
    private static /* synthetic */ int ogslot;
    private /* synthetic */ State currentState;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Boolean> glide;
    private /* synthetic */ boolean gotElytra;
    private static final /* synthetic */ Timer bypassTimer;
    private final /* synthetic */ Setting<Boolean> bypass;
    private static /* synthetic */ NoFall INSTANCE;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> silent;
    
    public NoFall() {
        super("NoFall", "Prevents fall damage.", Module.Category.MOVEMENT, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.PACKET));
        this.distance = (Setting<Integer>)this.register(new Setting("Distance", (T)3, (T)0, (T)50, p0 -> this.mode.getValue() == Mode.BUCKET || this.mode.getValue() == Mode.PACKET));
        this.glide = (Setting<Boolean>)this.register(new Setting("Glide", (T)false, p0 -> this.mode.getValue() == Mode.ELYTRA));
        this.silent = (Setting<Boolean>)this.register(new Setting("Silent", (T)true, p0 -> this.mode.getValue() == Mode.ELYTRA));
        this.bypass = (Setting<Boolean>)this.register(new Setting("Bypass", (T)false, p0 -> this.mode.getValue() == Mode.ELYTRA));
        this.timer = new Timer();
        this.equipped = false;
        this.gotElytra = false;
        this.currentState = State.FALL_CHECK;
        this.setInstance();
    }
    
    public void onEnable() {
        NoFall.ogslot = -1;
        this.currentState = State.FALL_CHECK;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (fullNullCheck()) {
            return;
        }
        if ((this.equipped || this.bypass.getValue()) && this.mode.getValue() == Mode.ELYTRA && (receive.getPacket() instanceof SPacketWindowItems || receive.getPacket() instanceof SPacketSetSlot)) {
            if (this.bypass.getValue()) {
                this.currentState = this.currentState.onReceive(receive);
            }
            else {
                this.gotElytra = true;
            }
        }
    }
    
    public static NoFall getInstance() {
        if (NoFall.INSTANCE == null) {
            NoFall.INSTANCE = new NoFall();
        }
        return NoFall.INSTANCE;
    }
    
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.BUCKET && NoFall.mc.player.fallDistance >= this.distance.getValue() && !EntityUtil.isAboveWater((Entity)NoFall.mc.player) && this.timer.passedMs(100L)) {
            final WorldClient world = NoFall.mc.world;
            final Vec3d getPositionVector = NoFall.mc.player.getPositionVector();
            final RayTraceResult rayTraceBlocks;
            if ((rayTraceBlocks = world.rayTraceBlocks(getPositionVector, getPositionVector.add(0.0, -5.329999923706055, 0.0), true, true, false)) != null && rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK) {
                EnumHand enumHand = EnumHand.MAIN_HAND;
                if (NoFall.mc.player.getHeldItemOffhand().getItem() == Items.WATER_BUCKET) {
                    enumHand = EnumHand.OFF_HAND;
                }
                else if (NoFall.mc.player.getHeldItemMainhand().getItem() != Items.WATER_BUCKET) {
                    for (int i = 0; i < 9; ++i) {
                        if (NoFall.mc.player.inventory.getStackInSlot(i).getItem() == Items.WATER_BUCKET) {
                            NoFall.mc.player.inventory.currentItem = i;
                            NoFall.mc.player.rotationPitch = 90.0f;
                            this.timer.reset();
                            return;
                        }
                    }
                    return;
                }
                NoFall.mc.player.rotationPitch = 90.0f;
                NoFall.mc.playerController.processRightClick((EntityPlayer)NoFall.mc.player, (World)NoFall.mc.world, enumHand);
                this.timer.reset();
            }
        }
    }
    
    static {
        bypassTimer = new Timer();
        NoFall.INSTANCE = new NoFall();
        NoFall.ogslot = -1;
    }
    
    private void setInstance() {
        NoFall.INSTANCE = this;
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.ELYTRA) {
            if (this.bypass.getValue()) {
                this.currentState = this.currentState.onUpdate();
            }
            else if (this.silent.getValue() && this.equipped && this.gotElytra) {
                NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, 6, NoFall.ogslot, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                NoFall.mc.playerController.updateController();
                this.equipped = false;
                this.gotElytra = false;
            }
            else {
                final int stackInventory;
                if (this.silent.getValue() && InventoryUtil.getItemHotbar(Items.ELYTRA) == -1 && (stackInventory = InventoryUtil.findStackInventory(Items.ELYTRA)) != -1 && NoFall.ogslot != -1) {
                    System.out.println(String.format("Moving %d to hotbar %d", stackInventory, NoFall.ogslot));
                    NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, stackInventory, NoFall.ogslot, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                    NoFall.mc.playerController.updateController();
                }
            }
        }
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (this.mode.getValue() == Mode.ELYTRA) {
            if (this.bypass.getValue()) {
                this.currentState = this.currentState.onSend(send);
            }
            else if (!this.equipped && send.getPacket() instanceof CPacketPlayer && NoFall.mc.player.fallDistance >= 3.0f) {
                RayTraceResult rayTraceBlocks = null;
                if (!this.glide.getValue()) {
                    rayTraceBlocks = NoFall.mc.world.rayTraceBlocks(NoFall.mc.player.getPositionVector(), NoFall.mc.player.getPositionVector().add(0.0, -3.0, 0.0), true, true, false);
                }
                if (this.glide.getValue() || (rayTraceBlocks != null && rayTraceBlocks.typeOfHit == RayTraceResult.Type.BLOCK)) {
                    if (NoFall.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(Items.ELYTRA)) {
                        NoFall.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoFall.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    }
                    else if (this.silent.getValue()) {
                        final int itemHotbar = InventoryUtil.getItemHotbar(Items.ELYTRA);
                        if (itemHotbar != -1) {
                            NoFall.mc.playerController.windowClick(NoFall.mc.player.inventoryContainer.windowId, 6, itemHotbar, ClickType.SWAP, (EntityPlayer)NoFall.mc.player);
                            NoFall.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)NoFall.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                        }
                        NoFall.ogslot = itemHotbar;
                        this.equipped = true;
                    }
                }
            }
        }
        if (this.mode.getValue() == Mode.PACKET && send.getPacket() instanceof CPacketPlayer) {
            if (NoFall.mc.player.getItemStackFromSlot(EntityEquipmentSlot.CHEST).getItem().equals(Items.ELYTRA)) {
                return;
            }
            if (NoFall.mc.player.fallDistance >= this.distance.getValue()) {
                ((CPacketPlayer)send.getPacket()).onGround = true;
            }
        }
    }
    
    public enum State
    {
        WAIT_FOR_NEXT_REQUIP {
            @Override
            public State onUpdate() {
                if (NoFall.bypassTimer.passedMs(250L)) {
                    return NoFall$State$4.REEQUIP_ELYTRA;
                }
                return this;
            }
        }, 
        FALL_CHECK {
            @Override
            public State onSend(final PacketEvent.Send send) {
                final RayTraceResult rayTraceBlocks = Util.mc.world.rayTraceBlocks(Util.mc.player.getPositionVector(), Util.mc.player.getPositionVector().add(0.0, -3.0, 0.0), true, true, false);
                if (!(send.getPacket() instanceof CPacketPlayer) || Util.mc.player.fallDistance < 3.0f || rayTraceBlocks == null || rayTraceBlocks.typeOfHit != RayTraceResult.Type.BLOCK) {
                    return this;
                }
                final int itemHotbar = InventoryUtil.getItemHotbar(Items.ELYTRA);
                if (itemHotbar != -1) {
                    Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, 6, itemHotbar, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                    NoFall.ogslot = itemHotbar;
                    Util.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)Util.mc.player, CPacketEntityAction.Action.START_FALL_FLYING));
                    return NoFall$State$1.WAIT_FOR_ELYTRA_DEQUIP;
                }
                return this;
            }
        }, 
        RESET_TIME {
            @Override
            public State onUpdate() {
                if (Util.mc.player.onGround || NoFall.bypassTimer.passedMs(250L)) {
                    Util.mc.player.connection.sendPacket((Packet)new CPacketClickWindow(0, 0, 0, ClickType.PICKUP, new ItemStack(Blocks.BEDROCK), (short)1337));
                    return NoFall$State$5.FALL_CHECK;
                }
                return this;
            }
        }, 
        REEQUIP_ELYTRA {
            @Override
            public State onUpdate() {
                Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, 6, NoFall.ogslot, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                Util.mc.playerController.updateController();
                final int stackInventory = InventoryUtil.findStackInventory(Items.ELYTRA, true);
                if (stackInventory == -1) {
                    Command.sendMessage("§cElytra not found after regain?");
                    return NoFall$State$3.WAIT_FOR_NEXT_REQUIP;
                }
                Util.mc.playerController.windowClick(Util.mc.player.inventoryContainer.windowId, stackInventory, NoFall.ogslot, ClickType.SWAP, (EntityPlayer)Util.mc.player);
                Util.mc.playerController.updateController();
                NoFall.bypassTimer.reset();
                return NoFall$State$3.RESET_TIME;
            }
        }, 
        WAIT_FOR_ELYTRA_DEQUIP {
            @Override
            public State onReceive(final PacketEvent.Receive receive) {
                if (receive.getPacket() instanceof SPacketWindowItems || receive.getPacket() instanceof SPacketSetSlot) {
                    return NoFall$State$2.REEQUIP_ELYTRA;
                }
                return this;
            }
        };
        
        public State onReceive(final PacketEvent.Receive receive) {
            return this;
        }
        
        public State onUpdate() {
            return this;
        }
        
        public State onSend(final PacketEvent.Send send) {
            return this;
        }
    }
    
    public enum Mode
    {
        BUCKET, 
        ELYTRA, 
        PACKET;
    }
}

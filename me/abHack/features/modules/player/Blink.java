//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.client.entity.*;
import net.minecraft.util.math.*;
import java.util.*;
import net.minecraft.network.*;
import net.minecraft.world.*;
import net.minecraft.entity.*;
import java.util.concurrent.*;
import me.abHack.util.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class Blink extends Module
{
    private final /* synthetic */ Setting<Float> distance;
    private static /* synthetic */ Blink INSTANCE;
    private final /* synthetic */ Timer timer;
    private final /* synthetic */ Setting<Mode> autoOff;
    private /* synthetic */ EntityOtherPlayerMP entity;
    private final /* synthetic */ Setting<Integer> packetLimit;
    private /* synthetic */ int packetsCanceled;
    private final /* synthetic */ Setting<Integer> timeLimit;
    private /* synthetic */ BlockPos startPos;
    private final /* synthetic */ Queue<Packet<?>> packets;
    private final /* synthetic */ Setting<Boolean> cPacketPlayer;
    
    private void setInstance() {
        Blink.INSTANCE = this;
    }
    
    public void onEnable() {
        if (!fullNullCheck()) {
            this.entity = new EntityOtherPlayerMP((World)Blink.mc.world, Blink.mc.session.getProfile());
            this.entity.copyLocationAndAnglesFrom((Entity)Blink.mc.player);
            this.entity.rotationYaw = Blink.mc.player.rotationYaw;
            this.entity.rotationYawHead = Blink.mc.player.rotationYawHead;
            this.entity.inventory.copyInventory(Blink.mc.player.inventory);
            Blink.mc.world.addEntityToWorld(6942069, (Entity)this.entity);
            this.startPos = Blink.mc.player.getPosition();
        }
        else {
            this.disable();
        }
        this.packetsCanceled = 0;
        this.timer.reset();
    }
    
    public void onLogout() {
        if (this.isOn()) {
            this.disable();
        }
    }
    
    public Blink() {
        super("Blink", "Fake lag.", Module.Category.PLAYER, true, false, false);
        this.cPacketPlayer = (Setting<Boolean>)this.register(new Setting("CPacketPlayer", (T)true));
        this.autoOff = (Setting<Mode>)this.register(new Setting("AutoOff", (T)Mode.MANUAL));
        this.timeLimit = (Setting<Integer>)this.register(new Setting("Time", (T)20, (T)1, (T)500, p0 -> this.autoOff.getValue() == Mode.TIME));
        this.packetLimit = (Setting<Integer>)this.register(new Setting("Packets", (T)20, (T)1, (T)500, p0 -> this.autoOff.getValue() == Mode.PACKETS));
        this.distance = (Setting<Float>)this.register(new Setting("Distance", (T)10.0f, (T)1.0f, (T)100.0f, p0 -> this.autoOff.getValue() == Mode.DISTANCE));
        this.timer = new Timer();
        this.packets = new ConcurrentLinkedQueue<Packet<?>>();
        this.packetsCanceled = 0;
        this.startPos = null;
        this.setInstance();
    }
    
    public String getDisplayInfo() {
        if (this.packets != null) {
            return String.valueOf(new StringBuilder().append(this.packets.size()).append(""));
        }
        return null;
    }
    
    public void onUpdate() {
        if (nullCheck() || (this.autoOff.getValue() == Mode.TIME && this.timer.passedS(this.timeLimit.getValue())) || (this.autoOff.getValue() == Mode.DISTANCE && this.startPos != null && Blink.mc.player.getDistanceSq(this.startPos) >= MathUtil.square(this.distance.getValue())) || (this.autoOff.getValue() == Mode.PACKETS && this.packetsCanceled >= this.packetLimit.getValue())) {
            this.disable();
        }
    }
    
    public void onDisable() {
        if (!fullNullCheck()) {
            Blink.mc.world.removeEntity((Entity)this.entity);
            while (!this.packets.isEmpty()) {
                Blink.mc.player.connection.sendPacket((Packet)this.packets.poll());
            }
        }
        this.startPos = null;
    }
    
    static {
        Blink.INSTANCE = new Blink();
    }
    
    public static Blink getInstance() {
        if (Blink.INSTANCE == null) {
            Blink.INSTANCE = new Blink();
        }
        return Blink.INSTANCE;
    }
    
    @SubscribeEvent
    public void onSendPacket(final PacketEvent.Send send) {
        if (Blink.mc.world != null && !Blink.mc.isSingleplayer()) {
            final Packet packet = send.getPacket();
            if (this.cPacketPlayer.getValue() && packet instanceof CPacketPlayer) {
                send.setCanceled(true);
                this.packets.add((Packet<?>)packet);
                ++this.packetsCanceled;
            }
            if (!this.cPacketPlayer.getValue()) {
                if (packet instanceof CPacketChatMessage || packet instanceof CPacketConfirmTeleport || packet instanceof CPacketKeepAlive || packet instanceof CPacketTabComplete || packet instanceof CPacketClientStatus) {
                    return;
                }
                this.packets.add((Packet<?>)packet);
                send.setCanceled(true);
                ++this.packetsCanceled;
            }
        }
    }
    
    public enum Mode
    {
        TIME, 
        MANUAL, 
        PACKETS, 
        DISTANCE;
    }
}

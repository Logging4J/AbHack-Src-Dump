//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.manager;

import me.abHack.features.*;
import me.abHack.util.*;
import java.util.concurrent.atomic.*;
import net.minecraftforge.fml.common.network.*;
import me.abHack.*;
import com.google.common.base.*;
import me.abHack.features.command.*;
import com.mojang.realmsclient.gui.*;
import net.minecraftforge.common.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.renderer.*;
import net.minecraft.entity.player.*;
import net.minecraft.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.features.modules.misc.*;
import java.util.function.*;
import net.minecraft.network.play.server.*;
import java.util.*;
import net.minecraftforge.event.entity.living.*;
import me.abHack.features.modules.client.*;
import net.minecraftforge.fml.common.gameevent.*;
import org.lwjgl.input.*;
import net.minecraftforge.client.event.*;
import me.abHack.event.events.*;

public class EventManager extends Feature
{
    private final /* synthetic */ Timer logoutTimer;
    private final /* synthetic */ AtomicBoolean tickOngoing;
    
    @SubscribeEvent
    public void onClientConnect(final FMLNetworkEvent.ClientConnectedToServerEvent clientConnectedToServerEvent) {
        this.logoutTimer.reset();
        OyVey.moduleManager.onLogin();
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void onChatSent(final ClientChatEvent clientChatEvent) {
        if (clientChatEvent.getMessage().startsWith(Command.getCommandPrefix())) {
            clientChatEvent.setCanceled(true);
            try {
                EventManager.mc.ingameGUI.getChatGUI().addToSentMessages(clientChatEvent.getMessage());
                if (clientChatEvent.getMessage().length() > 1) {
                    OyVey.commandManager.executeCommand(clientChatEvent.getMessage().substring(Command.getCommandPrefix().length() - 1));
                }
                else {
                    Command.sendMessage("Please enter a command.");
                }
            }
            catch (Exception ex) {
                ex.printStackTrace();
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("An error occurred while running this command. Check the log!")));
            }
        }
    }
    
    public boolean ticksOngoing() {
        return this.tickOngoing.get();
    }
    
    public void init() {
        MinecraftForge.EVENT_BUS.register((Object)this);
    }
    
    @SubscribeEvent(priority = EventPriority.LOW)
    public void onRenderGameOverlayEvent(final RenderGameOverlayEvent.Text text) {
        if (text.getType().equals((Object)RenderGameOverlayEvent.ElementType.TEXT)) {
            OyVey.moduleManager.onRender2D(new Render2DEvent(text.getPartialTicks(), new ScaledResolution(EventManager.mc)));
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public void onUnload() {
        MinecraftForge.EVENT_BUS.unregister((Object)this);
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (receive.getStage() != 0) {
            return;
        }
        OyVey.serverManager.onPacketReceived();
        if (receive.getPacket() instanceof SPacketEntityStatus) {
            final SPacketEntityStatus sPacketEntityStatus = (SPacketEntityStatus)receive.getPacket();
            if (sPacketEntityStatus.getOpCode() == 35 && sPacketEntityStatus.getEntity((World)EventManager.mc.world) instanceof EntityPlayer && OyVey.moduleManager.isModuleEnabled("PopCounter")) {
                final EntityPlayer entityPlayer = (EntityPlayer)sPacketEntityStatus.getEntity((World)EventManager.mc.world);
                MinecraftForge.EVENT_BUS.post((Event)new TotemPopEvent(entityPlayer));
                PopCounter.getInstance().onTotemPop(entityPlayer);
            }
        }
        if (receive.getPacket() instanceof SPacketPlayerListItem && !fullNullCheck() && this.logoutTimer.passedS(1.0)) {
            final SPacketPlayerListItem sPacketPlayerListItem = (SPacketPlayerListItem)receive.getPacket();
            if (!SPacketPlayerListItem.Action.ADD_PLAYER.equals((Object)sPacketPlayerListItem.getAction()) && !SPacketPlayerListItem.Action.REMOVE_PLAYER.equals((Object)sPacketPlayerListItem.getAction())) {
                return;
            }
            final SPacketPlayerListItem sPacketPlayerListItem2;
            final UUID uuid;
            final EntityPlayer entityPlayer2;
            sPacketPlayerListItem.getEntries().stream().filter(Objects::nonNull).filter(addPlayerData -> !Strings.isNullOrEmpty(addPlayerData.getProfile().getName()) || addPlayerData.getProfile().getId() != null).forEach(addPlayerData2 -> {
                addPlayerData2.getProfile().getId();
                switch (sPacketPlayerListItem2.getAction()) {
                    case ADD_PLAYER: {
                        MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(0, uuid, addPlayerData2.getProfile().getName()));
                        break;
                    }
                    case REMOVE_PLAYER: {
                        EventManager.mc.world.getPlayerEntityByUUID(uuid);
                        if (entityPlayer2 != null) {
                            MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(1, entityPlayer2, uuid, entityPlayer2.getName()));
                            break;
                        }
                        else {
                            MinecraftForge.EVENT_BUS.post((Event)new ConnectionEvent(2, uuid, (String)null));
                            break;
                        }
                        break;
                    }
                }
                return;
            });
        }
        if (receive.getPacket() instanceof SPacketTimeUpdate) {
            OyVey.serverManager.update();
        }
    }
    
    @SubscribeEvent
    public void onTick(final TickEvent.ClientTickEvent clientTickEvent) {
        if (fullNullCheck()) {
            return;
        }
        OyVey.moduleManager.onTick();
        for (final EntityPlayer entityPlayer : EventManager.mc.world.playerEntities) {
            if (OyVey.moduleManager.isModuleEnabled("PopCounter")) {
                if (entityPlayer == null) {
                    continue;
                }
                if (entityPlayer.getHealth() > 0.0f) {
                    continue;
                }
                MinecraftForge.EVENT_BUS.post((Event)new DeathEvent(entityPlayer));
                PopCounter.getInstance().onDeath(entityPlayer);
            }
        }
    }
    
    @SubscribeEvent
    public void onUpdate(final LivingEvent.LivingUpdateEvent livingUpdateEvent) {
        if (!fullNullCheck() && livingUpdateEvent.getEntity().getEntityWorld().isRemote && livingUpdateEvent.getEntityLiving().equals((Object)EventManager.mc.player)) {
            OyVey.inventoryManager.update();
            OyVey.moduleManager.onUpdate();
            if (HUD.getInstance().renderingMode.getValue() == HUD.RenderingMode.Length) {
                OyVey.moduleManager.sortModules(true);
            }
            else {
                OyVey.moduleManager.sortModulesABC();
            }
        }
    }
    
    @SubscribeEvent(priority = EventPriority.NORMAL, receiveCanceled = true)
    public void onKeyInput(final InputEvent.KeyInputEvent keyInputEvent) {
        if (Keyboard.getEventKeyState()) {
            OyVey.moduleManager.onKeyPressed(Keyboard.getEventKey());
        }
    }
    
    @SubscribeEvent
    public void renderHUD(final RenderGameOverlayEvent.Post post) {
        if (post.getType() == RenderGameOverlayEvent.ElementType.HOTBAR) {
            OyVey.textManager.updateResolution();
        }
    }
    
    @SubscribeEvent
    public void onWorldRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (renderWorldLastEvent.isCanceled()) {
            return;
        }
        EventManager.mc.profiler.startSection("oyvey");
        GlStateManager.disableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.disableAlpha();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.shadeModel(7425);
        GlStateManager.disableDepth();
        GlStateManager.glLineWidth(1.0f);
        OyVey.moduleManager.onRender3D(new Render3DEvent(renderWorldLastEvent.getPartialTicks()));
        GlStateManager.glLineWidth(1.0f);
        GlStateManager.shadeModel(7424);
        GlStateManager.disableBlend();
        GlStateManager.enableAlpha();
        GlStateManager.enableTexture2D();
        GlStateManager.enableDepth();
        GlStateManager.enableCull();
        GlStateManager.enableCull();
        GlStateManager.depthMask(true);
        GlStateManager.enableTexture2D();
        GlStateManager.enableBlend();
        GlStateManager.enableDepth();
        EventManager.mc.profiler.endSection();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final UpdateWalkingPlayerEvent updateWalkingPlayerEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (updateWalkingPlayerEvent.getStage() == 0) {
            OyVey.speedManager.updateValues();
            OyVey.rotationManager.updateRotations();
            OyVey.positionManager.updatePosition();
        }
        if (updateWalkingPlayerEvent.getStage() == 1) {
            OyVey.rotationManager.restoreRotations();
            OyVey.positionManager.restorePosition();
        }
    }
    
    public EventManager() {
        this.logoutTimer = new Timer();
        this.tickOngoing = new AtomicBoolean(false);
    }
    
    @SubscribeEvent
    public void onClientDisconnect(final FMLNetworkEvent.ClientDisconnectionFromServerEvent clientDisconnectionFromServerEvent) {
        OyVey.moduleManager.onLogout();
    }
}

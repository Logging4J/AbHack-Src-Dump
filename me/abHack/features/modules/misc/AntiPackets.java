//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.features.command.*;
import java.util.*;
import net.minecraft.network.play.server.*;

public class AntiPackets extends Module
{
    private final /* synthetic */ Setting<Boolean> RecipeInfo;
    private final /* synthetic */ Setting<Boolean> EnchantItem;
    private final /* synthetic */ Setting<Integer> page;
    private final /* synthetic */ Setting<Boolean> PlayerTryUseItem;
    private final /* synthetic */ Setting<Boolean> EntityEffect;
    private final /* synthetic */ Setting<Boolean> EntityStatus;
    private final /* synthetic */ Setting<Boolean> PlayerTryUseItemOnBlock;
    private final /* synthetic */ Setting<Boolean> VehicleMove;
    private final /* synthetic */ Setting<Boolean> SpawnExperienceOrb;
    private final /* synthetic */ Setting<Boolean> SetPassengers;
    private final /* synthetic */ Setting<Boolean> AdvancementInfo;
    private final /* synthetic */ Setting<Boolean> MultiBlockChange;
    private final /* synthetic */ Setting<Boolean> Animations;
    private final /* synthetic */ Setting<Boolean> HeldItemChanges;
    private final /* synthetic */ Setting<Boolean> HeldItemChange;
    private final /* synthetic */ Setting<Boolean> Effect;
    private final /* synthetic */ Setting<Boolean> Chat;
    private final /* synthetic */ Setting<Boolean> Entity;
    private final /* synthetic */ Setting<Boolean> SteerBoat;
    private final /* synthetic */ Setting<Boolean> PlayerListItem;
    private final /* synthetic */ Setting<Boolean> Explosion;
    private final /* synthetic */ Setting<Boolean> PlayerAbility;
    private final /* synthetic */ Setting<Boolean> BlockChange;
    private final /* synthetic */ Setting<Boolean> PlaceGhostRecipe;
    private final /* synthetic */ Setting<Boolean> Input;
    private final /* synthetic */ Setting<Boolean> TabComplete;
    private final /* synthetic */ Setting<Boolean> Respawn;
    private final /* synthetic */ Setting<Boolean> UpdateScore;
    private final /* synthetic */ Setting<Boolean> BlockBreakAnim;
    private final /* synthetic */ Setting<Boolean> ScoreboardObjective;
    private final /* synthetic */ Setting<Boolean> ResourcePackSend;
    private final /* synthetic */ Setting<Boolean> ConfirmTeleport;
    private final /* synthetic */ Setting<Boolean> EntityAction;
    private final /* synthetic */ Setting<Boolean> ClientStatus;
    private final /* synthetic */ Setting<Boolean> EntityEquipment;
    private final /* synthetic */ Setting<Boolean> ChatMessage;
    private final /* synthetic */ Setting<Boolean> EntityAttach;
    private final /* synthetic */ Setting<Boolean> Maps;
    private final /* synthetic */ Setting<Boolean> Teams;
    private final /* synthetic */ Setting<Boolean> SignEditorOpen;
    private final /* synthetic */ Setting<Boolean> SoundEffect;
    private final /* synthetic */ Setting<Boolean> PlaceRecipe;
    private final /* synthetic */ Setting<Boolean> SetExperience;
    private final /* synthetic */ Setting<Boolean> UpdateTileEntity;
    private final /* synthetic */ Setting<Boolean> Statistics;
    private final /* synthetic */ Setting<Boolean> ServerDifficulty;
    private final /* synthetic */ Setting<Boolean> EntityTeleport;
    private final /* synthetic */ Setting<Boolean> PlayerPosLook;
    private final /* synthetic */ Setting<Boolean> ChangeGameState;
    private final /* synthetic */ Setting<Boolean> UpdateSign;
    private final /* synthetic */ Setting<Boolean> Cooldown;
    private final /* synthetic */ Setting<Boolean> DisplayObjective;
    private final /* synthetic */ Setting<Boolean> Disconnect;
    private final /* synthetic */ Setting<Boolean> CloseWindows;
    private final /* synthetic */ Setting<Boolean> SpawnPlayer;
    private final /* synthetic */ Setting<Boolean> ClientSettings;
    private final /* synthetic */ Setting<Boolean> ClickWindow;
    private final /* synthetic */ Setting<Boolean> Animation;
    private final /* synthetic */ Setting<Boolean> Player;
    private final /* synthetic */ Setting<Boolean> ResourcePackStatus;
    private final /* synthetic */ Setting<Boolean> SpawnObject;
    private final /* synthetic */ Setting<Boolean> SpawnPosition;
    private final /* synthetic */ Setting<Boolean> EntityProperties;
    private final /* synthetic */ Setting<Boolean> EntityVelocity;
    private final /* synthetic */ Setting<Boolean> TimeUpdate;
    private final /* synthetic */ Setting<Boolean> TabCompletion;
    private final /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Boolean> Camera;
    private final /* synthetic */ Setting<Boolean> CollectItem;
    private final /* synthetic */ Setting<Boolean> UseEntity;
    private final /* synthetic */ Setting<Boolean> Particles;
    private final /* synthetic */ Setting<Boolean> UpdateHealth;
    private /* synthetic */ int hudAmount;
    private final /* synthetic */ Setting<Boolean> ConfirmTransactions;
    private final /* synthetic */ Setting<Boolean> CreativeInventoryAction;
    private final /* synthetic */ Setting<Boolean> CustomSound;
    private final /* synthetic */ Setting<Boolean> CloseWindow;
    private final /* synthetic */ Setting<Boolean> EntityHeadLook;
    private final /* synthetic */ Setting<Boolean> RecipeBook;
    private final /* synthetic */ Setting<Boolean> CombatEvent;
    private final /* synthetic */ Setting<Boolean> PlayerAbilities;
    private final /* synthetic */ Setting<Boolean> Title;
    private final /* synthetic */ Setting<Boolean> EntityMetadata;
    private final /* synthetic */ Setting<Boolean> WorldBorder;
    private final /* synthetic */ Setting<Boolean> SeenAdvancements;
    private final /* synthetic */ Setting<Boolean> KeepAlives;
    private final /* synthetic */ Setting<Boolean> ChunkData;
    private final /* synthetic */ Setting<Boolean> CustomPayloads;
    private final /* synthetic */ Setting<Boolean> ConfirmTransaction;
    private final /* synthetic */ Setting<Boolean> WindowProperty;
    private final /* synthetic */ Setting<Boolean> RemoveEntityEffect;
    private final /* synthetic */ Setting<Boolean> JoinGame;
    private final /* synthetic */ Setting<Boolean> KeepAlive;
    private final /* synthetic */ Setting<Boolean> SetSlot;
    private final /* synthetic */ Setting<Boolean> SpawnPainting;
    private final /* synthetic */ Setting<Boolean> WindowItems;
    private final /* synthetic */ Setting<Boolean> PlayerDigging;
    private final /* synthetic */ Setting<Boolean> Spectate;
    private final /* synthetic */ Setting<Boolean> OpenWindow;
    private final /* synthetic */ Setting<Integer> pages;
    private final /* synthetic */ Setting<Boolean> SelectAdvancementsTab;
    private final /* synthetic */ Setting<Boolean> CustomPayload;
    private final /* synthetic */ Setting<Boolean> UpdateBossInfo;
    private final /* synthetic */ Setting<Boolean> PlayerPackets;
    private final /* synthetic */ Setting<Boolean> BlockAction;
    private final /* synthetic */ Setting<Boolean> UnloadChunk;
    private final /* synthetic */ Setting<Boolean> DestroyEntities;
    private final /* synthetic */ Setting<Boolean> SpawnGlobalEntity;
    private final /* synthetic */ Setting<Boolean> UseBed;
    private final /* synthetic */ Setting<Boolean> SpawnMob;
    private final /* synthetic */ Setting<Boolean> PlayerListHeaderFooter;
    private final /* synthetic */ Setting<Boolean> MoveVehicle;
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (!this.isEnabled()) {
            return;
        }
        if (send.getPacket() instanceof CPacketAnimation && this.Animations.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketChatMessage && this.ChatMessage.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketClickWindow && this.ClickWindow.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketClientSettings && this.ClientSettings.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketClientStatus && this.ClientStatus.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketCloseWindow && this.CloseWindows.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketConfirmTeleport && this.ConfirmTeleport.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketConfirmTransaction && this.ConfirmTransactions.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketCreativeInventoryAction && this.CreativeInventoryAction.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketCustomPayload && this.CustomPayloads.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketEnchantItem && this.EnchantItem.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketEntityAction && this.EntityAction.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketHeldItemChange && this.HeldItemChanges.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketInput && this.Input.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketKeepAlive && this.KeepAlives.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlaceRecipe && this.PlaceRecipe.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayer && this.Player.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayerAbilities && this.PlayerAbility.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayerDigging && this.PlayerDigging.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayerTryUseItem && this.PlayerTryUseItem.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketPlayerTryUseItemOnBlock && this.PlayerTryUseItemOnBlock.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketRecipeInfo && this.RecipeInfo.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketResourcePackStatus && this.ResourcePackStatus.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketSeenAdvancements && this.SeenAdvancements.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketSpectate && this.Spectate.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketSteerBoat && this.SteerBoat.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketTabComplete && this.TabCompletion.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketUpdateSign && this.UpdateSign.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketUseEntity && this.UseEntity.getValue()) {
            send.setCanceled(true);
        }
        if (send.getPacket() instanceof CPacketVehicleMove && this.VehicleMove.getValue()) {
            send.setCanceled(true);
        }
    }
    
    public AntiPackets() {
        super("AntiPackets", "anti packet", Category.MISC, true, false, false);
        this.mode = (Setting<Mode>)this.register(new Setting("Packets", (T)Mode.CLIENT));
        this.page = (Setting<Integer>)this.register(new Setting("SPackets", (T)1, (T)1, (T)10, p0 -> this.mode.getValue() == Mode.SERVER));
        this.AdvancementInfo = (Setting<Boolean>)this.register(new Setting("AdvancementInfo", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.Animation = (Setting<Boolean>)this.register(new Setting("Animation", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.BlockAction = (Setting<Boolean>)this.register(new Setting("BlockAction", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.BlockBreakAnim = (Setting<Boolean>)this.register(new Setting("BlockBreakAnim", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.BlockChange = (Setting<Boolean>)this.register(new Setting("BlockChange", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.Camera = (Setting<Boolean>)this.register(new Setting("Camera", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.ChangeGameState = (Setting<Boolean>)this.register(new Setting("ChangeGameState", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.Chat = (Setting<Boolean>)this.register(new Setting("Chat", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 1));
        this.ChunkData = (Setting<Boolean>)this.register(new Setting("ChunkData", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.CloseWindow = (Setting<Boolean>)this.register(new Setting("CloseWindow", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.CollectItem = (Setting<Boolean>)this.register(new Setting("CollectItem", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.CombatEvent = (Setting<Boolean>)this.register(new Setting("Combatevent", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.ConfirmTransaction = (Setting<Boolean>)this.register(new Setting("ConfirmTransaction", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.Cooldown = (Setting<Boolean>)this.register(new Setting("Cooldown", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.CustomPayload = (Setting<Boolean>)this.register(new Setting("CustomPayload", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.CustomSound = (Setting<Boolean>)this.register(new Setting("CustomSound", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 2));
        this.DestroyEntities = (Setting<Boolean>)this.register(new Setting("DestroyEntities", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.Disconnect = (Setting<Boolean>)this.register(new Setting("Disconnect", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.DisplayObjective = (Setting<Boolean>)this.register(new Setting("DisplayObjective", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.Effect = (Setting<Boolean>)this.register(new Setting("Effect", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.Entity = (Setting<Boolean>)this.register(new Setting("Entity", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.EntityAttach = (Setting<Boolean>)this.register(new Setting("EntityAttach", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.EntityEffect = (Setting<Boolean>)this.register(new Setting("EntityEffect", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.EntityEquipment = (Setting<Boolean>)this.register(new Setting("EntityEquipment", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 3));
        this.EntityHeadLook = (Setting<Boolean>)this.register(new Setting("EntityHeadLook", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.EntityMetadata = (Setting<Boolean>)this.register(new Setting("EntityMetadata", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.EntityProperties = (Setting<Boolean>)this.register(new Setting("EntityProperties", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.EntityStatus = (Setting<Boolean>)this.register(new Setting("EntityStatus", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.EntityTeleport = (Setting<Boolean>)this.register(new Setting("EntityTeleport", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.EntityVelocity = (Setting<Boolean>)this.register(new Setting("EntityVelocity", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.Explosion = (Setting<Boolean>)this.register(new Setting("Explosion", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.HeldItemChange = (Setting<Boolean>)this.register(new Setting("HeldItemChange", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 4));
        this.JoinGame = (Setting<Boolean>)this.register(new Setting("JoinGame", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.KeepAlive = (Setting<Boolean>)this.register(new Setting("KeepAlive", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.Maps = (Setting<Boolean>)this.register(new Setting("Maps", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.MoveVehicle = (Setting<Boolean>)this.register(new Setting("MoveVehicle", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.MultiBlockChange = (Setting<Boolean>)this.register(new Setting("MultiBlockChange", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.OpenWindow = (Setting<Boolean>)this.register(new Setting("OpenWindow", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.Particles = (Setting<Boolean>)this.register(new Setting("Particles", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.PlaceGhostRecipe = (Setting<Boolean>)this.register(new Setting("PlaceGhostRecipe", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 5));
        this.PlayerAbilities = (Setting<Boolean>)this.register(new Setting("PlayerAbilities", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.PlayerListHeaderFooter = (Setting<Boolean>)this.register(new Setting("PlayerListHeaderFooter", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.PlayerListItem = (Setting<Boolean>)this.register(new Setting("PlayerListItem", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.PlayerPosLook = (Setting<Boolean>)this.register(new Setting("PlayerPosLook", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.RecipeBook = (Setting<Boolean>)this.register(new Setting("RecipeBook", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.RemoveEntityEffect = (Setting<Boolean>)this.register(new Setting("RemoveEntityEffect", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.ResourcePackSend = (Setting<Boolean>)this.register(new Setting("ResourcePackSend", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.Respawn = (Setting<Boolean>)this.register(new Setting("Respawn", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 6));
        this.ScoreboardObjective = (Setting<Boolean>)this.register(new Setting("ScoreboardObjective", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SelectAdvancementsTab = (Setting<Boolean>)this.register(new Setting("SelectAdvancementsTab", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.ServerDifficulty = (Setting<Boolean>)this.register(new Setting("ServerDifficulty", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SetExperience = (Setting<Boolean>)this.register(new Setting("SetExperience", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SetPassengers = (Setting<Boolean>)this.register(new Setting("SetPassengers", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SetSlot = (Setting<Boolean>)this.register(new Setting("SetSlot", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SignEditorOpen = (Setting<Boolean>)this.register(new Setting("SignEditorOpen", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SoundEffect = (Setting<Boolean>)this.register(new Setting("SoundEffect", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 7));
        this.SpawnExperienceOrb = (Setting<Boolean>)this.register(new Setting("SpawnExperienceOrb", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnGlobalEntity = (Setting<Boolean>)this.register(new Setting("SpawnGlobalEntity", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnMob = (Setting<Boolean>)this.register(new Setting("SpawnMob", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnObject = (Setting<Boolean>)this.register(new Setting("SpawnObject", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnPainting = (Setting<Boolean>)this.register(new Setting("SpawnPainting", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnPlayer = (Setting<Boolean>)this.register(new Setting("SpawnPlayer", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.SpawnPosition = (Setting<Boolean>)this.register(new Setting("SpawnPosition", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.Statistics = (Setting<Boolean>)this.register(new Setting("Statistics", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 8));
        this.TabComplete = (Setting<Boolean>)this.register(new Setting("TabComplete", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.Teams = (Setting<Boolean>)this.register(new Setting("Teams", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.TimeUpdate = (Setting<Boolean>)this.register(new Setting("TimeUpdate", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.Title = (Setting<Boolean>)this.register(new Setting("Title", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.UnloadChunk = (Setting<Boolean>)this.register(new Setting("UnloadChunk", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.UpdateBossInfo = (Setting<Boolean>)this.register(new Setting("UpdateBossInfo", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.UpdateHealth = (Setting<Boolean>)this.register(new Setting("UpdateHealth", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.UpdateScore = (Setting<Boolean>)this.register(new Setting("UpdateScore", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 9));
        this.UpdateTileEntity = (Setting<Boolean>)this.register(new Setting("UpdateTileEntity", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 10));
        this.UseBed = (Setting<Boolean>)this.register(new Setting("UseBed", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 10));
        this.WindowItems = (Setting<Boolean>)this.register(new Setting("WindowItems", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 10));
        this.WindowProperty = (Setting<Boolean>)this.register(new Setting("WindowProperty", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 10));
        this.WorldBorder = (Setting<Boolean>)this.register(new Setting("WorldBorder", (T)false, p0 -> this.mode.getValue() == Mode.SERVER && this.page.getValue() == 10));
        this.PlayerDigging = (Setting<Boolean>)this.register(new Setting("PlayerDigging", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.page.getValue() == 3));
        this.pages = (Setting<Integer>)this.register(new Setting("CPackets", (T)1, (T)1, (T)4, p0 -> this.mode.getValue() == Mode.CLIENT));
        this.Animations = (Setting<Boolean>)this.register(new Setting("Animations", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ChatMessage = (Setting<Boolean>)this.register(new Setting("ChatMessage", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ClickWindow = (Setting<Boolean>)this.register(new Setting("ClickWindow", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ClientSettings = (Setting<Boolean>)this.register(new Setting("ClientSettings", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ClientStatus = (Setting<Boolean>)this.register(new Setting("ClientStatus", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.CloseWindows = (Setting<Boolean>)this.register(new Setting("CloseWindows", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ConfirmTeleport = (Setting<Boolean>)this.register(new Setting("ConfirmTeleport", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.ConfirmTransactions = (Setting<Boolean>)this.register(new Setting("ConfirmTransactions", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 1));
        this.CreativeInventoryAction = (Setting<Boolean>)this.register(new Setting("CreativeInventoryAction", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.CustomPayloads = (Setting<Boolean>)this.register(new Setting("CustomPayloads", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.EnchantItem = (Setting<Boolean>)this.register(new Setting("EnchantItem", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.EntityAction = (Setting<Boolean>)this.register(new Setting("EntityAction", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.HeldItemChanges = (Setting<Boolean>)this.register(new Setting("HeldItemChanges", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.Input = (Setting<Boolean>)this.register(new Setting("Input", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.KeepAlives = (Setting<Boolean>)this.register(new Setting("KeepAlives", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.PlaceRecipe = (Setting<Boolean>)this.register(new Setting("PlaceRecipe", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 2));
        this.Player = (Setting<Boolean>)this.register(new Setting("Player", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.PlayerAbility = (Setting<Boolean>)this.register(new Setting("PlayerAbility", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.PlayerTryUseItem = (Setting<Boolean>)this.register(new Setting("PlayerTryUseItem", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.PlayerTryUseItemOnBlock = (Setting<Boolean>)this.register(new Setting("TryUseItemOnBlock", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.RecipeInfo = (Setting<Boolean>)this.register(new Setting("RecipeInfo", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.ResourcePackStatus = (Setting<Boolean>)this.register(new Setting("ResourcePackStatus", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.SeenAdvancements = (Setting<Boolean>)this.register(new Setting("SeenAdvancements", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 3));
        this.PlayerPackets = (Setting<Boolean>)this.register(new Setting("PlayerPackets", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.Spectate = (Setting<Boolean>)this.register(new Setting("Spectate", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.SteerBoat = (Setting<Boolean>)this.register(new Setting("SteerBoat", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.TabCompletion = (Setting<Boolean>)this.register(new Setting("TabCompletion", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.UpdateSign = (Setting<Boolean>)this.register(new Setting("UpdateSign", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.UseEntity = (Setting<Boolean>)this.register(new Setting("UseEntity", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.VehicleMove = (Setting<Boolean>)this.register(new Setting("VehicleMove", (T)false, p0 -> this.mode.getValue() == Mode.CLIENT && this.pages.getValue() == 4));
        this.hudAmount = 0;
    }
    
    @Override
    public void onEnable() {
        final String s = "§aAntiPackets On!§f Cancelled Packets: ";
        final StringBuilder sb = new StringBuilder(s);
        if (!this.settings.isEmpty()) {
            for (final Setting<Boolean> setting : this.settings) {
                if (setting.getValue() instanceof Boolean && setting.getValue() && !setting.getName().equalsIgnoreCase("Enabled")) {
                    if (setting.getName().equalsIgnoreCase("drawn")) {
                        continue;
                    }
                    sb.append(setting.getName()).append(", ");
                }
            }
        }
        if (String.valueOf(sb).equals(s)) {
            Command.sendMessage("§aAntiPackets On!§f Currently not cancelling any Packets.");
        }
        else {
            Command.sendMessage(this.removeLastChar(this.removeLastChar(String.valueOf(sb))));
        }
    }
    
    @Override
    public void onUpdate() {
        int hudAmount = 0;
        if (!this.settings.isEmpty()) {
            for (final Setting<Boolean> setting : this.settings) {
                if (setting.getValue() instanceof Boolean && setting.getValue() && !setting.getName().equalsIgnoreCase("Enabled")) {
                    if (setting.getName().equalsIgnoreCase("drawn")) {
                        continue;
                    }
                    ++hudAmount;
                }
            }
        }
        this.hudAmount = hudAmount;
    }
    
    @SubscribeEvent
    public void onPacketReceive(final PacketEvent.Receive receive) {
        if (!this.isEnabled()) {
            return;
        }
        if (receive.getPacket() instanceof SPacketAdvancementInfo && this.AdvancementInfo.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketAnimation && this.Animation.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketBlockAction && this.BlockAction.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketBlockBreakAnim && this.BlockBreakAnim.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketBlockChange && this.BlockChange.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCamera && this.Camera.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketChangeGameState && this.ChangeGameState.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketChat && this.Chat.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketChunkData && this.ChunkData.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCloseWindow && this.CloseWindow.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCollectItem && this.CollectItem.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCombatEvent && this.CombatEvent.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketConfirmTransaction && this.ConfirmTransaction.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCooldown && this.Cooldown.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCustomPayload && this.CustomPayload.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCustomSound && this.CustomSound.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketDestroyEntities && this.DestroyEntities.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketDisconnect && this.Disconnect.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketChunkData && this.ChunkData.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCloseWindow && this.CloseWindow.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketCollectItem && this.CollectItem.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketDisplayObjective && this.DisplayObjective.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEffect && this.Effect.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntity && this.Entity.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityAttach && this.EntityAttach.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityEffect && this.EntityEffect.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityEquipment && this.EntityEquipment.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityHeadLook && this.EntityHeadLook.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityMetadata && this.EntityMetadata.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityProperties && this.EntityProperties.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityStatus && this.EntityStatus.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityTeleport && this.EntityTeleport.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketEntityVelocity && this.EntityVelocity.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketExplosion && this.Explosion.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketHeldItemChange && this.HeldItemChange.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketJoinGame && this.JoinGame.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketKeepAlive && this.KeepAlive.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketMaps && this.Maps.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketMoveVehicle && this.MoveVehicle.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketMultiBlockChange && this.MultiBlockChange.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketOpenWindow && this.OpenWindow.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketParticles && this.Particles.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlaceGhostRecipe && this.PlaceGhostRecipe.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlayerAbilities && this.PlayerAbilities.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlayerListHeaderFooter && this.PlayerListHeaderFooter.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlayerListItem && this.PlayerListItem.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketPlayerPosLook && this.PlayerPosLook.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketRecipeBook && this.RecipeBook.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketRemoveEntityEffect && this.RemoveEntityEffect.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketResourcePackSend && this.ResourcePackSend.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketRespawn && this.Respawn.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketScoreboardObjective && this.ScoreboardObjective.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSelectAdvancementsTab && this.SelectAdvancementsTab.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketServerDifficulty && this.ServerDifficulty.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSetExperience && this.SetExperience.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSetPassengers && this.SetPassengers.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSetSlot && this.SetSlot.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSignEditorOpen && this.SignEditorOpen.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSoundEffect && this.SoundEffect.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnExperienceOrb && this.SpawnExperienceOrb.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnGlobalEntity && this.SpawnGlobalEntity.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnMob && this.SpawnMob.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnObject && this.SpawnObject.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnPainting && this.SpawnPainting.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnPlayer && this.SpawnPlayer.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketSpawnPosition && this.SpawnPosition.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketStatistics && this.Statistics.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketTabComplete && this.TabComplete.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketTeams && this.Teams.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketTimeUpdate && this.TimeUpdate.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketTitle && this.Title.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUnloadChunk && this.UnloadChunk.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUpdateBossInfo && this.UpdateBossInfo.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUpdateHealth && this.UpdateHealth.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUpdateScore && this.UpdateScore.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUpdateTileEntity && this.UpdateTileEntity.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketUseBed && this.UseBed.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketWindowItems && this.WindowItems.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketWindowProperty && this.WindowProperty.getValue()) {
            receive.setCanceled(true);
        }
        if (receive.getPacket() instanceof SPacketWorldBorder && this.WorldBorder.getValue()) {
            receive.setCanceled(true);
        }
    }
    
    public String removeLastChar(String substring) {
        if (substring != null && substring.length() > 0) {
            substring = substring.substring(0, substring.length() - 1);
        }
        return substring;
    }
    
    @Override
    public String getDisplayInfo() {
        if (this.hudAmount == 0) {
            return "";
        }
        return String.valueOf(new StringBuilder().append(this.hudAmount).append(""));
    }
    
    public enum Mode
    {
        SERVER, 
        CLIENT;
    }
}

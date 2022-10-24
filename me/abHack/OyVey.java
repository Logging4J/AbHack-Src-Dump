//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack;

import net.minecraftforge.fml.common.*;
import me.abHack.manager.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.event.*;
import org.lwjgl.opengl.*;
import easiervillagertrading.*;
import net.minecraft.util.*;
import net.minecraft.client.*;
import java.nio.*;
import me.abHack.util.*;
import java.io.*;
import org.apache.logging.log4j.*;

@Mod(modid = "ab", name = "ab-Hack", version = "0.0.1")
public class OyVey
{
    public static /* synthetic */ ModuleManager moduleManager;
    public static /* synthetic */ SpeedManager speedManager;
    private static /* synthetic */ boolean unloaded;
    public static /* synthetic */ ConfigManager configManager;
    public static /* synthetic */ TargetManager targetManager;
    public static /* synthetic */ SafetyManager safetyManager;
    public static /* synthetic */ FileManager fileManager;
    public static /* synthetic */ RotationManager rotationManager;
    public static /* synthetic */ PacketManager packetManager;
    public static /* synthetic */ InventoryManager inventoryManager;
    public static /* synthetic */ PotionManager potionManager;
    public static /* synthetic */ PositionManager positionManager;
    public static /* synthetic */ FriendManager friendManager;
    public static /* synthetic */ ColorManager colorManager;
    public static /* synthetic */ CommandManager commandManager;
    public static /* synthetic */ ReloadManager reloadManager;
    public static /* synthetic */ ServerManager serverManager;
    public static final /* synthetic */ Logger LOGGER;
    public static /* synthetic */ HoleManager holeManager;
    public static /* synthetic */ EventManager eventManager;
    public static /* synthetic */ TextManager textManager;
    
    public static void onUnload() {
        if (!OyVey.unloaded) {
            OyVey.eventManager.onUnload();
            OyVey.moduleManager.onUnload();
            OyVey.configManager.saveConfig(OyVey.configManager.config.replaceFirst("ab-Hack/", ""));
            OyVey.moduleManager.onUnloadPost();
            OyVey.unloaded = true;
        }
    }
    
    public static void reload() {
        unload(false);
        load();
    }
    
    public static void unload(final boolean b) {
        OyVey.LOGGER.info("\n\nUnloading ab-Hack by Steve_ab");
        if (b) {
            (OyVey.reloadManager = new ReloadManager()).init((OyVey.commandManager != null) ? OyVey.commandManager.getPrefix() : ".");
        }
        onUnload();
        OyVey.eventManager = null;
        OyVey.friendManager = null;
        OyVey.speedManager = null;
        OyVey.holeManager = null;
        OyVey.positionManager = null;
        OyVey.rotationManager = null;
        OyVey.configManager = null;
        OyVey.commandManager = null;
        OyVey.colorManager = null;
        OyVey.serverManager = null;
        OyVey.fileManager = null;
        OyVey.potionManager = null;
        OyVey.inventoryManager = null;
        OyVey.moduleManager = null;
        OyVey.textManager = null;
        OyVey.safetyManager = null;
        OyVey.targetManager = null;
        OyVey.LOGGER.info("ab-Hack unloaded!\n");
    }
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        final ConfigurationHandler instance = ConfigurationHandler.getInstance();
        instance.load(fmlPreInitializationEvent.getSuggestedConfigurationFile());
        MinecraftForge.EVENT_BUS.register((Object)instance);
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        OyVey.LOGGER.info(String.valueOf(new StringBuilder().append("HWID: ").append(SystemUtil.getSystemInfo())));
        Display.setTitle("ab-Hack 0.0.1");
        load();
        this.setWindowsIcon();
        MinecraftForge.EVENT_BUS.register((Object)OpenTradeEventHandler.getInstance());
    }
    
    public static void setWindowIcon() {
        if (Util.getOSType() != Util.EnumOS.OSX) {
            try (final InputStream resourceAsStream = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/steve16x.png");
                 final InputStream resourceAsStream2 = Minecraft.class.getResourceAsStream("/assets/minecraft/textures/steve32x.png")) {
                Display.setIcon(new ByteBuffer[] { IconUtil.INSTANCE.readImageToBuffer(resourceAsStream), IconUtil.INSTANCE.readImageToBuffer(resourceAsStream2) });
            }
            catch (Exception ex) {
                OyVey.LOGGER.error("Couldn't set Windows Icon", (Throwable)ex);
            }
        }
    }
    
    static {
        MODVER = "0.0.1";
        MODNAME = "ab-Hack";
        MODID = "ab";
        LOGGER = LogManager.getLogger("ab-Hack");
        OyVey.unloaded = false;
    }
    
    public static void load() {
        OyVey.LOGGER.info("\n\nLoading ab-Hack by Steve_ab");
        OyVey.unloaded = false;
        if (OyVey.reloadManager != null) {
            OyVey.reloadManager.unload();
            OyVey.reloadManager = null;
        }
        OyVey.textManager = new TextManager();
        OyVey.commandManager = new CommandManager();
        OyVey.friendManager = new FriendManager();
        OyVey.moduleManager = new ModuleManager();
        OyVey.rotationManager = new RotationManager();
        OyVey.packetManager = new PacketManager();
        OyVey.eventManager = new EventManager();
        OyVey.speedManager = new SpeedManager();
        OyVey.potionManager = new PotionManager();
        OyVey.inventoryManager = new InventoryManager();
        OyVey.serverManager = new ServerManager();
        OyVey.fileManager = new FileManager();
        OyVey.colorManager = new ColorManager();
        OyVey.positionManager = new PositionManager();
        OyVey.configManager = new ConfigManager();
        OyVey.holeManager = new HoleManager();
        OyVey.safetyManager = new SafetyManager();
        OyVey.targetManager = new TargetManager();
        OyVey.LOGGER.info("Managers loaded.");
        OyVey.moduleManager.init();
        OyVey.LOGGER.info("Modules loaded.");
        OyVey.configManager.init();
        OyVey.eventManager.init();
        OyVey.LOGGER.info("EventManager loaded.");
        OyVey.textManager.init(true);
        OyVey.moduleManager.onLoad();
        OyVey.LOGGER.info("abHack successfully loaded!\n");
    }
    
    private void setWindowsIcon() {
        setWindowIcon();
    }
}

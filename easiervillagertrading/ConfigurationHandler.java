//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package easiervillagertrading;

import net.minecraftforge.common.config.*;
import net.minecraftforge.fml.client.event.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.io.*;
import net.minecraftforge.fml.common.*;

public class ConfigurationHandler
{
    private /* synthetic */ int leftPixelOffset;
    private /* synthetic */ boolean autoFocusSearch;
    private /* synthetic */ String configFileName;
    private /* synthetic */ Configuration config;
    private static /* synthetic */ ConfigurationHandler instance;
    private /* synthetic */ boolean showLeft;
    
    public static ConfigurationHandler getInstance() {
        if (ConfigurationHandler.instance == null) {
            ConfigurationHandler.instance = new ConfigurationHandler();
        }
        return ConfigurationHandler.instance;
    }
    
    public static boolean autoFocusSearch() {
        return getInstance().autoFocusSearch;
    }
    
    @SubscribeEvent
    public void onConfigChanged(final ConfigChangedEvent.OnConfigChangedEvent onConfigChangedEvent) {
        if (onConfigChangedEvent.getModID().equalsIgnoreCase("easiervillagertrading")) {
            this.loadConfig();
        }
    }
    
    public static Configuration getConfig() {
        return getInstance().config;
    }
    
    public void load(final File file) {
        if (this.config == null) {
            this.config = new Configuration(file);
            this.configFileName = file.getPath();
            this.loadConfig();
        }
    }
    
    public static boolean showLeft() {
        return getInstance().showLeft;
    }
    
    public static int leftPixelOffset() {
        return getInstance().leftPixelOffset;
    }
    
    public static String getConfigFileName() {
        return getInstance().configFileName;
    }
    
    private void loadConfig() {
        this.showLeft = this.config.getBoolean("Trades list left", "client", Loader.isModLoaded("jei"), "Show trades list to the left, for Just Enough Items compatibility");
        this.leftPixelOffset = this.config.getInt("Trades left pixel offset", "client", 0, 0, Integer.MAX_VALUE, "How many pixels left of the GUI the trades list will be shown. Use 0 for auto detect. Only used if Trades list left is true.");
        if (this.config.hasChanged()) {
            this.config.save();
        }
    }
}

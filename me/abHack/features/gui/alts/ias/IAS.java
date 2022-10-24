//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias;

import net.minecraftforge.fml.common.*;
import net.minecraftforge.common.config.*;
import net.minecraft.client.resources.*;
import me.abHack.features.gui.alts.iasencrypt.*;
import me.abHack.features.gui.alts.*;
import net.minecraftforge.common.*;
import me.abHack.features.gui.alts.ias.events.*;
import me.abHack.features.gui.alts.ias.config.*;
import net.minecraftforge.fml.common.event.*;
import me.abHack.features.gui.alts.ias.tools.*;

@Mod(modid = "ias", name = "In-Game Account Switcher", clientSideOnly = true, guiFactory = "me.abHack.features.gui.alts.ias.config.IASGuiFactory", updateJSON = "https://thefireplace.bitnamiapp.com/jsons/ias.json", acceptedMinecraftVersions = "[1.11,)")
public class IAS
{
    private static /* synthetic */ Property ENABLERELOG_PROPERTY;
    public static /* synthetic */ Configuration config;
    private static /* synthetic */ Property CASESENSITIVE_PROPERTY;
    
    @Mod.EventHandler
    public void preInit(final FMLPreInitializationEvent fmlPreInitializationEvent) {
        (IAS.config = new Configuration(fmlPreInitializationEvent.getSuggestedConfigurationFile())).load();
        IAS.CASESENSITIVE_PROPERTY = IAS.config.get("general", "ias.cfg.casesensitive", false, I18n.format("ias.cfg.casesensitive.tooltip", (Object[])new Object[0]));
        IAS.ENABLERELOG_PROPERTY = IAS.config.get("general", "ias.cfg.enablerelog", false, I18n.format("ias.cfg.enablerelog.tooltip", (Object[])new Object[0]));
        syncConfig();
        if (!fmlPreInitializationEvent.getModMetadata().version.equals("${version}")) {
            Standards.updateFolder();
        }
        else {
            System.out.println("Dev environment detected!");
        }
    }
    
    @Mod.EventHandler
    public void init(final FMLInitializationEvent fmlInitializationEvent) {
        MR.init();
        MinecraftForge.EVENT_BUS.register((Object)new ClientEvents());
        Standards.importAccounts();
    }
    
    public static void syncConfig() {
        ConfigValues.CASESENSITIVE = IAS.CASESENSITIVE_PROPERTY.getBoolean();
        ConfigValues.ENABLERELOG = IAS.ENABLERELOG_PROPERTY.getBoolean();
        if (IAS.config.hasChanged()) {
            IAS.config.save();
        }
    }
    
    @Mod.EventHandler
    public void postInit(final FMLPostInitializationEvent fmlPostInitializationEvent) {
        SkinTools.cacheSkins();
    }
}

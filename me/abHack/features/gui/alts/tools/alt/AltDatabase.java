//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.tools.alt;

import java.io.*;
import java.util.*;
import me.abHack.features.gui.alts.tools.*;

public class AltDatabase implements Serializable
{
    private static /* synthetic */ AltDatabase instance;
    private final /* synthetic */ ArrayList<AccountData> altList;
    
    private static void saveToConfig() {
        Config.getInstance().setKey("altaccounts", AltDatabase.instance);
    }
    
    private AltDatabase() {
        this.altList = new ArrayList<AccountData>();
    }
    
    public ArrayList<AccountData> getAlts() {
        return this.altList;
    }
    
    public static AltDatabase getInstance() {
        loadFromConfig();
        if (AltDatabase.instance == null) {
            AltDatabase.instance = new AltDatabase();
            saveToConfig();
        }
        return AltDatabase.instance;
    }
    
    private static void loadFromConfig() {
        if (AltDatabase.instance == null) {
            AltDatabase.instance = (AltDatabase)Config.getInstance().getKey("altaccounts");
        }
    }
}

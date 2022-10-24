//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.config;

import net.minecraftforge.fml.client.*;
import net.minecraft.client.*;
import java.util.*;
import net.minecraft.client.gui.*;

public class IASGuiFactory implements IModGuiFactory
{
    public void initialize(final Minecraft minecraft) {
    }
    
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
    
    public boolean hasConfigGui() {
        return true;
    }
    
    public GuiScreen createConfigGui(final GuiScreen guiScreen) {
        return (GuiScreen)new IASConfigGui(guiScreen);
    }
}

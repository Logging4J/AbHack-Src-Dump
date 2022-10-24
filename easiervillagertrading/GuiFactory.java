//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package easiervillagertrading;

import net.minecraftforge.fml.client.*;
import net.minecraft.client.gui.*;
import net.minecraft.client.*;
import java.util.*;

public class GuiFactory implements IModGuiFactory
{
    public GuiScreen createConfigGui(final GuiScreen guiScreen) {
        return (GuiScreen)new GuiConfig(guiScreen);
    }
    
    public void initialize(final Minecraft minecraft) {
    }
    
    public Set<IModGuiFactory.RuntimeOptionCategoryElement> runtimeGuiCategories() {
        return null;
    }
    
    public boolean hasConfigGui() {
        return true;
    }
}

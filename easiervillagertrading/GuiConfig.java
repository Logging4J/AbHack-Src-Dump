//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package easiervillagertrading;

import net.minecraft.client.gui.*;
import net.minecraftforge.common.config.*;

public class GuiConfig extends net.minecraftforge.fml.client.config.GuiConfig
{
    public GuiConfig(final GuiScreen guiScreen) {
        super(guiScreen, new ConfigElement(ConfigurationHandler.getConfig().getCategory("client")).getChildElements(), "easiervillagertrading", false, false, "Easier Villager Trading configuration");
    }
}

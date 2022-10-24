//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.gui.alts.ias.events;

import net.minecraftforge.fml.common.gameevent.*;
import net.minecraft.client.*;
import net.minecraft.client.resources.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import me.abHack.features.gui.alts.tools.*;
import me.abHack.features.gui.alts.ias.gui.*;
import net.minecraftforge.fml.client.event.*;
import me.abHack.features.gui.alts.ias.*;

public class ClientEvents
{
    @SubscribeEvent
    public void onTick(final TickEvent.RenderTickEvent renderTickEvent) {
        final GuiScreen currentScreen = Minecraft.getMinecraft().currentScreen;
        if (currentScreen instanceof GuiMainMenu) {
            currentScreen.drawCenteredString(Minecraft.getMinecraft().fontRenderer, String.valueOf(new StringBuilder().append(I18n.format("ias.loggedinas", (Object[])new Object[0])).append(Minecraft.getMinecraft().getSession().getUsername()).append(".")), currentScreen.width / 2, currentScreen.height / 4 + 48 + 72 + 12 + 22, -3372920);
        }
        else if (currentScreen instanceof GuiMultiplayer && Minecraft.getMinecraft().getSession().getToken().equals("0")) {
            currentScreen.drawCenteredString(Minecraft.getMinecraft().fontRenderer, I18n.format("ias.offlinemode", (Object[])new Object[0]), currentScreen.width / 2, 10, 16737380);
        }
    }
    
    @SubscribeEvent
    public void guiEvent(final GuiScreenEvent.InitGuiEvent.Post post) {
        final GuiScreen gui = post.getGui();
        if (gui instanceof GuiMainMenu) {
            post.getButtonList().add(new GuiButtonWithImage(20, gui.width / 2 + 104, gui.height / 4 + 48 + 72 + 12, 20, 20, ""));
        }
    }
    
    @SubscribeEvent
    public void onClick(final GuiScreenEvent.ActionPerformedEvent actionPerformedEvent) {
        if (actionPerformedEvent.getGui() instanceof GuiMainMenu && actionPerformedEvent.getButton().id == 20) {
            if (Config.getInstance() == null) {
                Config.load();
            }
            Minecraft.getMinecraft().displayGuiScreen((GuiScreen)new GuiAccountSelector());
        }
    }
    
    @SubscribeEvent
    public void configChanged(final ConfigChangedEvent configChangedEvent) {
        if (configChangedEvent.getModID().equals("ias")) {
            IAS.syncConfig();
        }
    }
}

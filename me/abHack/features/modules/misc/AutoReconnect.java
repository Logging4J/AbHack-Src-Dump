//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.event.world.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import me.abHack.util.*;
import net.minecraft.client.multiplayer.*;

public class AutoReconnect extends Module
{
    private final /* synthetic */ Setting<Integer> delay;
    private static /* synthetic */ AutoReconnect INSTANCE;
    private static /* synthetic */ ServerData serverData;
    
    public AutoReconnect() {
        super("AutoReconnect", "Reconnects you if you disconnect.", Category.MISC, true, false, false);
        this.delay = (Setting<Integer>)this.register(new Setting("Delay", (T)5, (T)0, (T)20));
        this.setInstance();
    }
    
    private void setInstance() {
        AutoReconnect.INSTANCE = this;
    }
    
    @SubscribeEvent
    public void onWorldUnload(final WorldEvent.Unload unload) {
        this.updateLastConnectedServer();
    }
    
    static {
        AutoReconnect.INSTANCE = new AutoReconnect();
    }
    
    @SubscribeEvent
    public void sendPacket(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiDisconnected) {
            this.updateLastConnectedServer();
            guiOpenEvent.setGui((GuiScreen)new GuiDisconnectedHook((GuiDisconnected)guiOpenEvent.getGui()));
        }
    }
    
    public static AutoReconnect getInstance() {
        if (AutoReconnect.INSTANCE == null) {
            AutoReconnect.INSTANCE = new AutoReconnect();
        }
        return AutoReconnect.INSTANCE;
    }
    
    public void updateLastConnectedServer() {
        final ServerData getCurrentServerData = Util.mc.getCurrentServerData();
        if (getCurrentServerData != null) {
            AutoReconnect.serverData = getCurrentServerData;
        }
    }
    
    private class GuiDisconnectedHook extends GuiDisconnected
    {
        private final /* synthetic */ Timer timer;
        
        public void drawScreen(final int n, final int n2, final float n3) {
            super.drawScreen(n, n2, n3);
            final String value = String.valueOf(new StringBuilder().append("Reconnecting in ").append(MathUtil.round((AutoReconnect.this.delay.getValue() * 1000 - this.timer.getPassedTimeMs()) / 1000.0, 1)));
            AutoReconnect.this.renderer.drawString(value, (float)(this.width / 2 - AutoReconnect.this.renderer.getStringWidth(value) / 2), (float)(this.height - 16), 16777215, true);
        }
        
        public GuiDisconnectedHook(final GuiDisconnected guiDisconnected) {
            super(guiDisconnected.parentScreen, guiDisconnected.reason, guiDisconnected.message);
            this.timer = new Timer();
            this.timer.reset();
        }
        
        public void updateScreen() {
            if (this.timer.passedS(AutoReconnect.this.delay.getValue())) {
                this.mc.displayGuiScreen((GuiScreen)new GuiConnecting(this.parentScreen, this.mc, (AutoReconnect.serverData == null) ? this.mc.currentServerData : AutoReconnect.serverData));
            }
        }
    }
}

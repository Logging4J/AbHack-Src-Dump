//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package easiervillagertrading;

import net.minecraft.client.*;
import net.minecraftforge.client.event.*;
import net.minecraft.world.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class OpenTradeEventHandler
{
    private /* synthetic */ Minecraft mc;
    private static /* synthetic */ OpenTradeEventHandler instance;
    
    public static OpenTradeEventHandler getInstance() {
        if (OpenTradeEventHandler.instance == null) {
            OpenTradeEventHandler.instance = new OpenTradeEventHandler();
            OpenTradeEventHandler.instance.mc = Minecraft.getMinecraft();
        }
        return OpenTradeEventHandler.instance;
    }
    
    @SubscribeEvent
    public void guiOpenEvent(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiMerchant) {
            guiOpenEvent.setGui((GuiScreen)new BetterGuiMerchant(this.mc.player.inventory, (GuiMerchant)guiOpenEvent.getGui(), (World)this.mc.world));
        }
    }
}

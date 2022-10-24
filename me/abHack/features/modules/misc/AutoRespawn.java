//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.client.event.*;
import net.minecraft.client.gui.*;
import me.abHack.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class AutoRespawn extends Module
{
    public /* synthetic */ Setting<Boolean> antiDeathScreen;
    public /* synthetic */ Setting<Boolean> respawn;
    public /* synthetic */ Setting<Boolean> deathCoords;
    
    public AutoRespawn() {
        super("AutoRespawn", "Respawns you when you die.", Category.MISC, true, false, false);
        this.antiDeathScreen = (Setting<Boolean>)this.register(new Setting("AntiDeathScreen", (T)true));
        this.deathCoords = (Setting<Boolean>)this.register(new Setting("DeathCoords", (T)false));
        this.respawn = (Setting<Boolean>)this.register(new Setting("Respawn", (T)true));
    }
    
    @SubscribeEvent
    public void onDisplayDeathScreen(final GuiOpenEvent guiOpenEvent) {
        if (guiOpenEvent.getGui() instanceof GuiGameOver) {
            if (this.deathCoords.getValue() && guiOpenEvent.getGui() instanceof GuiGameOver) {
                Command.sendMessage(String.format("You died at X: %d Y: %d Z: %d", (int)AutoRespawn.mc.player.posX, (int)AutoRespawn.mc.player.posY, (int)AutoRespawn.mc.player.posZ));
            }
            if ((this.respawn.getValue() && AutoRespawn.mc.player.getHealth() <= 0.0f) || (this.antiDeathScreen.getValue() && AutoRespawn.mc.player.getHealth() > 0.0f)) {
                guiOpenEvent.setCanceled(true);
                AutoRespawn.mc.player.respawnPlayer();
            }
        }
    }
}

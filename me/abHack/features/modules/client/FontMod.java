//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.client;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.*;
import me.abHack.event.events.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;

public class FontMod extends Module
{
    private /* synthetic */ boolean reloadFont;
    public /* synthetic */ Setting<String> fontName;
    public /* synthetic */ Setting<Integer> fontStyle;
    public /* synthetic */ Setting<Boolean> fractionalMetrics;
    private static /* synthetic */ FontMod INSTANCE;
    public /* synthetic */ Setting<Integer> fontSize;
    public /* synthetic */ Setting<Boolean> antiAlias;
    
    static {
        FontMod.INSTANCE = new FontMod();
    }
    
    public static FontMod getInstance() {
        if (FontMod.INSTANCE == null) {
            FontMod.INSTANCE = new FontMod();
        }
        return FontMod.INSTANCE;
    }
    
    @Override
    public void onTick() {
        if (this.reloadFont) {
            OyVey.textManager.init(false);
            this.reloadFont = false;
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        final Setting setting;
        if (clientEvent.getStage() == 2 && (setting = clientEvent.getSetting()) != null && setting.getFeature().equals(this)) {
            if (setting.getName().equals("FontName") && !checkFont(setting.getPlannedValue().toString(), false)) {
                Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("That font doesnt exist.")));
                clientEvent.setCanceled(true);
                return;
            }
            this.reloadFont = true;
        }
    }
    
    public FontMod() {
        super("CustomFont", "CustomFont for all of the clients text. Use the font command.", Category.CLIENT, true, false, false);
        this.fontName = (Setting<String>)this.register(new Setting("FontName", (T)"\u5b8b\u4f53", "Name of the font."));
        this.antiAlias = (Setting<Boolean>)this.register(new Setting("AntiAlias", (T)false, "Smoother font."));
        this.fractionalMetrics = (Setting<Boolean>)this.register(new Setting("Metrics", (T)false, "Thinner font."));
        this.fontSize = (Setting<Integer>)this.register(new Setting("Size", (T)18, (T)12, (T)30, "Size of the font."));
        this.fontStyle = (Setting<Integer>)this.register(new Setting("Style", (T)0, (T)0, (T)3, "Style of the font."));
        this.reloadFont = false;
        this.setInstance();
    }
    
    private void setInstance() {
        FontMod.INSTANCE = this;
    }
    
    public static boolean checkFont(final String anObject, final boolean b) {
        for (final String s : GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames()) {
            if (!b && s.equals(anObject)) {
                return true;
            }
            if (b) {
                Command.sendMessage(s);
            }
        }
        return false;
    }
}

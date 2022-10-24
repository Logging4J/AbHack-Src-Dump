//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules;

import me.abHack.features.*;
import me.abHack.features.setting.*;
import me.abHack.features.modules.client.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.util.text.*;
import net.minecraftforge.common.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.event.events.*;
import me.abHack.features.command.*;

public class Module extends Feature
{
    public /* synthetic */ float arrayListVOffset;
    public /* synthetic */ boolean hasListener;
    public /* synthetic */ Setting<String> displayName;
    public /* synthetic */ Setting<Bind> bind;
    public /* synthetic */ boolean sliding;
    private final /* synthetic */ Category category;
    public /* synthetic */ Setting<Boolean> drawn;
    private final /* synthetic */ String description;
    public /* synthetic */ float arrayListOffset;
    public /* synthetic */ boolean alwaysListening;
    public /* synthetic */ boolean hidden;
    public /* synthetic */ Setting<Boolean> enabled;
    
    public void onDisable() {
    }
    
    public boolean isOn() {
        return this.enabled.getValue();
    }
    
    public void enable() {
        this.enabled.setValue(Boolean.TRUE);
        this.onToggle();
        this.onEnable();
        if (HUD.getInstance().notifyToggles.getValue()) {
            Module.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(String.valueOf(new StringBuilder().append(OyVey.commandManager.getClientMessage()).append(" ").append(ChatFormatting.GREEN).append(this.getDisplayName()).append(" toggled on."))), 1);
        }
        if (this.isOn() && this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.register((Object)this);
        }
    }
    
    public void onEnable() {
    }
    
    public String getDisplayInfo() {
        return null;
    }
    
    public boolean isOff() {
        return !this.enabled.getValue();
    }
    
    public void onUpdate() {
    }
    
    public boolean listening() {
        return (this.hasListener && this.isOn()) || this.alwaysListening;
    }
    
    public void toggle() {
        final ClientEvent clientEvent = new ClientEvent((int)(this.isEnabled() ? 0 : 1), (Feature)this);
        MinecraftForge.EVENT_BUS.post((Event)clientEvent);
        if (!clientEvent.isCanceled()) {
            this.setEnabled(!this.isEnabled());
        }
    }
    
    public Bind getBind() {
        return this.bind.getValue();
    }
    
    public boolean isSliding() {
        return this.sliding;
    }
    
    public void onLogin() {
    }
    
    public String getDisplayName() {
        return this.displayName.getValue();
    }
    
    public void setBind(final int n) {
        this.bind.setValue(new Bind(n));
    }
    
    public void onLoad() {
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
    }
    
    public void onUnload() {
    }
    
    public void setDrawn(final boolean b) {
        this.drawn.setValue(b);
    }
    
    public Category getCategory() {
        return this.category;
    }
    
    public Module(final String s, final String description, final Category category, final boolean hasListener, final boolean hidden, final boolean alwaysListening) {
        super(s);
        this.enabled = (Setting<Boolean>)this.register(new Setting("Enabled", (T)false));
        this.drawn = (Setting<Boolean>)this.register(new Setting("Drawn", (T)true));
        this.bind = (Setting<Bind>)this.register(new Setting("Keybind", (T)new Bind(-1)));
        this.arrayListOffset = 0.0f;
        this.arrayListVOffset = 0.0f;
        this.displayName = (Setting<String>)this.register(new Setting("DisplayName", (T)s));
        this.description = description;
        this.category = category;
        this.hasListener = hasListener;
        this.hidden = hidden;
        this.alwaysListening = alwaysListening;
    }
    
    public void onLogout() {
    }
    
    public boolean isDrawn() {
        return this.drawn.getValue();
    }
    
    public void disable() {
        if (this.hasListener && !this.alwaysListening) {
            MinecraftForge.EVENT_BUS.unregister((Object)this);
        }
        this.enabled.setValue(false);
        if (HUD.getInstance().notifyToggles.getValue()) {
            Module.mc.ingameGUI.getChatGUI().printChatMessageWithOptionalDeletion((ITextComponent)new TextComponentString(String.valueOf(new StringBuilder().append(OyVey.commandManager.getClientMessage()).append(" ").append(ChatFormatting.RED).append(this.getDisplayName()).append(" toggled off."))), 1);
        }
        this.onToggle();
        this.onDisable();
    }
    
    public void setDisplayName(final String s) {
        final Module moduleByDisplayName = OyVey.moduleManager.getModuleByDisplayName(s);
        final Module moduleByName = OyVey.moduleManager.getModuleByName(s);
        if (moduleByDisplayName == null && moduleByName == null) {
            Command.sendMessage(String.valueOf(new StringBuilder().append(this.getDisplayName()).append(", name: ").append(this.getName()).append(", has been renamed to: ").append(s)));
            this.displayName.setValue(s);
            return;
        }
        Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("A module of this name already exists.")));
    }
    
    public String getInfo() {
        return null;
    }
    
    public String getFullArrayString() {
        return String.valueOf(new StringBuilder().append(this.getDisplayName()).append(ChatFormatting.GRAY).append((this.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [").append(ChatFormatting.WHITE).append(this.getDisplayInfo()).append(ChatFormatting.GRAY).append("]")) : ""));
    }
    
    public void onTick() {
    }
    
    public String getDescription() {
        return this.description;
    }
    
    public void setEnabled(final boolean b) {
        if (b) {
            this.enable();
        }
        else {
            this.disable();
        }
    }
    
    public void onToggle() {
    }
    
    public enum Category
    {
        CLIENT("Client"), 
        COMBAT("Combat"), 
        MOVEMENT("Movement"), 
        PLAYER("Player");
        
        private final /* synthetic */ String name;
        
        RENDER("Render"), 
        MISC("Misc");
        
        public String getName() {
            return this.name;
        }
        
        private Category(final String name2) {
            this.name = name2;
        }
    }
}

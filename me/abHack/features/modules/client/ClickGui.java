//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.client;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.features.gui.*;
import net.minecraft.client.gui.*;
import me.abHack.util.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.client.settings.*;
import me.abHack.event.events.*;

public class ClickGui extends Module
{
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Integer> g_alpha1;
    public /* synthetic */ Setting<Integer> particleLength;
    public /* synthetic */ Setting<Integer> alphaBox;
    public /* synthetic */ Setting<Integer> particleblue;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Integer> particlegreen;
    public /* synthetic */ Setting<Integer> g_blue;
    public /* synthetic */ Setting<Integer> backgroundAlpha;
    public /* synthetic */ Setting<Integer> g_green;
    public /* synthetic */ Setting<rainbowMode> rainbowModeHud;
    public /* synthetic */ Setting<Integer> rainbowHue;
    public /* synthetic */ Setting<Boolean> rainbow;
    public /* synthetic */ Setting<Integer> gb_green;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> alpha;
    public /* synthetic */ Setting<Integer> gb_red;
    public /* synthetic */ Setting<rainbowModeArray> rainbowModeA;
    public /* synthetic */ Setting<Boolean> rainbowg;
    public /* synthetic */ Setting<Integer> particlered;
    public /* synthetic */ Setting<Integer> gb_blue;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> g_red1;
    public /* synthetic */ Setting<Boolean> customFov;
    public /* synthetic */ Setting<Integer> g_red;
    public /* synthetic */ Setting<Float> rainbowSaturation;
    private /* synthetic */ int color;
    public /* synthetic */ Setting<Boolean> particles;
    public /* synthetic */ Setting<Integer> g_alpha;
    public /* synthetic */ Setting<Boolean> snowing;
    public /* synthetic */ Setting<Integer> g_green1;
    public /* synthetic */ Setting<String> prefix;
    public /* synthetic */ Setting<Boolean> guiComponent;
    public /* synthetic */ Setting<Mode> mode;
    public /* synthetic */ Setting<Boolean> rbg;
    public /* synthetic */ Setting<Integer> g_blue1;
    private final /* synthetic */ Setting<Settings> setting;
    public /* synthetic */ Setting<Integer> hoverAlpha;
    public /* synthetic */ Setting<Float> fov;
    public /* synthetic */ Setting<Boolean> moduleDescription;
    public /* synthetic */ Setting<Float> rainbowBrightness;
    private static /* synthetic */ ClickGui INSTANCE;
    
    public final int getColor() {
        return this.color;
    }
    
    @Override
    public void onEnable() {
        ClickGui.mc.displayGuiScreen((GuiScreen)OyVeyGui.getClickGui());
    }
    
    public static ClickGui getInstance() {
        if (ClickGui.INSTANCE == null) {
            ClickGui.INSTANCE = new ClickGui();
        }
        return ClickGui.INSTANCE;
    }
    
    @Override
    public void onDisable() {
        if (ClickGui.mc.currentScreen instanceof OyVeyGui) {
            Util.mc.displayGuiScreen((GuiScreen)null);
        }
    }
    
    public void drawBackground() {
        if (this.mode.getValue() == Mode.COLOR) {
            if (getInstance().isEnabled()) {
                RenderUtil.drawRectangleCorrectly(0, 0, 1920, 1080, ColorUtil.toRGBA(this.gb_red.getValue(), this.gb_green.getValue(), this.gb_blue.getValue(), this.backgroundAlpha.getValue()));
            }
            else {
                RenderUtil.drawRectangleCorrectly(0, 0, 1920, 1080, ColorUtil.toRGBA(0, 0, 0, 0));
            }
        }
        if (this.mode.getValue() == Mode.NONE) {
            if (getInstance().isEnabled()) {
                RenderUtil.drawRectangleCorrectly(0, 0, 1920, 1080, ColorUtil.toRGBA(this.gb_red.getValue(), this.gb_green.getValue(), this.gb_blue.getValue(), this.backgroundAlpha.getValue()));
            }
            else {
                RenderUtil.drawRectangleCorrectly(0, 0, 1920, 1080, ColorUtil.toRGBA(0, 0, 0, 0));
            }
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && clientEvent.getSetting().getFeature().equals(this)) {
            if (clientEvent.getSetting().equals(this.prefix)) {
                OyVey.commandManager.setPrefix(this.prefix.getPlannedValue());
                Command.sendMessage(String.valueOf(new StringBuilder().append("Prefix set to ").append(ChatFormatting.DARK_GRAY).append(OyVey.commandManager.getPrefix())));
            }
            OyVey.colorManager.setColor(this.red.getPlannedValue(), this.green.getPlannedValue(), this.blue.getPlannedValue(), this.hoverAlpha.getPlannedValue());
        }
    }
    
    @Override
    public void onUpdate() {
        if (this.customFov.getValue()) {
            ClickGui.mc.gameSettings.setOptionFloatValue(GameSettings.Options.FOV, (float)this.fov.getValue());
        }
    }
    
    static {
        ClickGui.INSTANCE = new ClickGui();
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        this.drawBackground();
    }
    
    private void setInstance() {
        ClickGui.INSTANCE = this;
    }
    
    @Override
    public void onTick() {
        if (!(ClickGui.mc.currentScreen instanceof OyVeyGui)) {
            this.disable();
        }
    }
    
    public ClickGui() {
        super("ClickGui", "Opens the ClickGui", Category.CLIENT, true, false, false);
        this.setting = (Setting<Settings>)this.register(new Setting("Settings", (T)Settings.Gui));
        this.prefix = (Setting<String>)this.register(new Setting("Prefix", (T)".", p0 -> this.setting.getValue() == Settings.Gui));
        this.customFov = (Setting<Boolean>)this.register(new Setting("CustomFov", (T)Boolean.FALSE, p0 -> this.setting.getValue() == Settings.Gui));
        this.fov = (Setting<Float>)this.register(new Setting("Fov", (T)90.0f, (T)(-180.0f), (T)180.0f, p0 -> this.setting.getValue() == Settings.Gui && this.customFov.getValue()));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)100, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)24, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)250, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.hoverAlpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)225, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.alphaBox = (Setting<Integer>)this.register(new Setting("AlphaBox", (T)0, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.alpha = (Setting<Integer>)this.register(new Setting("HoverAlpha", (T)240, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)Boolean.TRUE, p0 -> this.setting.getValue() == Settings.Gui));
        this.rainbowModeHud = (Setting<rainbowMode>)this.register(new Setting("HUD", (T)rainbowMode.Static, p0 -> this.rainbow.getValue() && this.setting.getValue() == Settings.Gui));
        this.rainbowModeA = (Setting<rainbowModeArray>)this.register(new Setting("ArrayList", (T)rainbowModeArray.Up, p0 -> this.rainbow.getValue() && this.setting.getValue() == Settings.Gui));
        this.rainbowHue = (Setting<Integer>)this.register(new Setting("Delay", (T)600, (T)0, (T)600, p0 -> this.rainbow.getValue() && this.setting.getValue() == Settings.Gui));
        this.rainbowBrightness = (Setting<Float>)this.register(new Setting("Brightness ", (T)255.0f, (T)1.0f, (T)255.0f, p0 -> this.rainbow.getValue() && this.setting.getValue() == Settings.Gui));
        this.rainbowSaturation = (Setting<Float>)this.register(new Setting("Saturation", (T)255.0f, (T)1.0f, (T)255.0f, p0 -> this.rainbow.getValue() && this.setting.getValue() == Settings.Gui));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true, p0 -> this.setting.getValue() == Settings.Gui));
        this.moduleDescription = (Setting<Boolean>)this.register(new Setting("Description", (T)true, p0 -> this.setting.getValue() == Settings.Gui));
        this.snowing = (Setting<Boolean>)this.register(new Setting("Snowing", (T)true, p0 -> this.setting.getValue() == Settings.Gui));
        this.particles = (Setting<Boolean>)this.register(new Setting("Particles", (T)true, p0 -> this.setting.getValue() == Settings.Gui));
        this.particleLength = (Setting<Integer>)this.register(new Setting("ParticleLength", (T)80, (T)0, (T)300, p0 -> this.setting.getValue() == Settings.Gui && this.particles.getValue()));
        this.particlered = (Setting<Integer>)this.register(new Setting("ParticleRed", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui && this.particles.getValue()));
        this.particlegreen = (Setting<Integer>)this.register(new Setting("ParticleGreen", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui && this.particles.getValue()));
        this.particleblue = (Setting<Integer>)this.register(new Setting("ParticleBlue", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gui && this.particles.getValue()));
        this.rbg = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true, p0 -> this.setting.getValue() == Settings.Gui && this.particles.getValue()));
        this.rainbowg = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)Boolean.FALSE, p0 -> this.setting.getValue() == Settings.Gradient));
        this.guiComponent = (Setting<Boolean>)this.register(new Setting("Gui Component", (T)Boolean.TRUE, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_red = (Setting<Integer>)this.register(new Setting("RedL", (T)105, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_green = (Setting<Integer>)this.register(new Setting("GreenL", (T)162, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_blue = (Setting<Integer>)this.register(new Setting("BlueL", (T)255, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_red1 = (Setting<Integer>)this.register(new Setting("RedR", (T)143, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_green1 = (Setting<Integer>)this.register(new Setting("GreenR", (T)140, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_blue1 = (Setting<Integer>)this.register(new Setting("BlueR", (T)213, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_alpha = (Setting<Integer>)this.register(new Setting("AlphaL", (T)0, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.g_alpha1 = (Setting<Integer>)this.register(new Setting("AlphaR", (T)0, (T)0, (T)255, p0 -> this.setting.getValue() == Settings.Gradient));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.BLUR, p0 -> this.setting.getValue() == Settings.Background));
        this.backgroundAlpha = (Setting<Integer>)this.register(new Setting("Background Alpha", (T)160, (T)0, (T)255, p0 -> this.mode.getValue() == Mode.COLOR && this.setting.getValue() == Settings.Background));
        this.gb_red = (Setting<Integer>)this.register(new Setting("RedBG", (T)20, (T)0, (T)255, p0 -> this.mode.getValue() == Mode.COLOR && this.setting.getValue() == Settings.Background));
        this.gb_green = (Setting<Integer>)this.register(new Setting("GreenBG", (T)20, (T)0, (T)255, p0 -> this.mode.getValue() == Mode.COLOR && this.setting.getValue() == Settings.Background));
        this.gb_blue = (Setting<Integer>)this.register(new Setting("BlueBG", (T)20, (T)0, (T)255, p0 -> this.mode.getValue() == Mode.COLOR && this.setting.getValue() == Settings.Background));
        this.setInstance();
    }
    
    @Override
    public void onLoad() {
        OyVey.colorManager.setColor(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.hoverAlpha.getValue());
        OyVey.commandManager.setPrefix(this.prefix.getValue());
    }
    
    public enum rainbowMode
    {
        Sideway, 
        Static;
    }
    
    public enum Settings
    {
        Gradient, 
        Background, 
        Gui;
    }
    
    public enum rainbowModeArray
    {
        Static, 
        Up;
    }
    
    public enum Mode
    {
        BLUR, 
        COLOR, 
        NONE;
    }
}

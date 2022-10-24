//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraftforge.fml.common.eventhandler.*;

public class ViewModel extends Module
{
    public /* synthetic */ Setting<Double> offScaleX;
    public /* synthetic */ Setting<Boolean> noEatAnimation;
    public /* synthetic */ Setting<Double> mainZ;
    public /* synthetic */ Setting<Integer> mainRotX;
    public /* synthetic */ Setting<Integer> offRotZ;
    public /* synthetic */ Setting<Integer> mainRotZ;
    public /* synthetic */ Setting<Integer> offRotX;
    public /* synthetic */ Setting<Integer> mainRotY;
    public /* synthetic */ Setting<Double> offY;
    public /* synthetic */ Setting<Integer> offRotY;
    public /* synthetic */ Setting<Double> offScaleY;
    public /* synthetic */ Setting<Double> mainScaleY;
    public /* synthetic */ Setting<Double> eatX;
    public /* synthetic */ Setting<Double> offZ;
    private static /* synthetic */ ViewModel INSTANCE;
    public /* synthetic */ Setting<Double> mainX;
    public /* synthetic */ Setting<Double> mainScaleZ;
    public /* synthetic */ Setting<Settings> settings;
    public /* synthetic */ Setting<Double> offScaleZ;
    public /* synthetic */ Setting<Double> offX;
    public /* synthetic */ Setting<Double> mainY;
    public /* synthetic */ Setting<Boolean> doBob;
    public /* synthetic */ Setting<Double> mainScaleX;
    public /* synthetic */ Setting<Double> eatY;
    
    @SubscribeEvent
    public void onItemRender(final RenderItemEvent renderItemEvent) {
        renderItemEvent.setMainX((double)this.mainX.getValue());
        renderItemEvent.setMainY((double)this.mainY.getValue());
        renderItemEvent.setMainZ((double)this.mainZ.getValue());
        renderItemEvent.setOffX(-this.offX.getValue());
        renderItemEvent.setOffY((double)this.offY.getValue());
        renderItemEvent.setOffZ((double)this.offZ.getValue());
        renderItemEvent.setMainRotX((double)(this.mainRotX.getValue() * 5));
        renderItemEvent.setMainRotY((double)(this.mainRotY.getValue() * 5));
        renderItemEvent.setMainRotZ((double)(this.mainRotZ.getValue() * 5));
        renderItemEvent.setOffRotX((double)(this.offRotX.getValue() * 5));
        renderItemEvent.setOffRotY((double)(this.offRotY.getValue() * 5));
        renderItemEvent.setOffRotZ((double)(this.offRotZ.getValue() * 5));
        renderItemEvent.setOffHandScaleX((double)this.offScaleX.getValue());
        renderItemEvent.setOffHandScaleY((double)this.offScaleY.getValue());
        renderItemEvent.setOffHandScaleZ((double)this.offScaleZ.getValue());
        renderItemEvent.setMainHandScaleX((double)this.mainScaleX.getValue());
        renderItemEvent.setMainHandScaleY((double)this.mainScaleY.getValue());
        renderItemEvent.setMainHandScaleZ((double)this.mainScaleZ.getValue());
    }
    
    static {
        ViewModel.INSTANCE = new ViewModel();
    }
    
    public static ViewModel getInstance() {
        if (ViewModel.INSTANCE == null) {
            ViewModel.INSTANCE = new ViewModel();
        }
        return ViewModel.INSTANCE;
    }
    
    public ViewModel() {
        super("ViewModel", "Change the position of the arm", Module.Category.RENDER, true, false, false);
        this.settings = (Setting<Settings>)this.register(new Setting("Settings", (T)Settings.TRANSLATE));
        this.noEatAnimation = (Setting<Boolean>)this.register(new Setting("NoEatAnimation", (T)false, p0 -> this.settings.getValue() == Settings.TWEAKS));
        this.eatX = (Setting<Double>)this.register(new Setting("EatX", (T)1.0, (T)(-2.0), (T)5.0, p0 -> this.settings.getValue() == Settings.TWEAKS && !this.noEatAnimation.getValue()));
        this.eatY = (Setting<Double>)this.register(new Setting("EatY", (T)1.0, (T)(-2.0), (T)5.0, p0 -> this.settings.getValue() == Settings.TWEAKS && !this.noEatAnimation.getValue()));
        this.doBob = (Setting<Boolean>)this.register(new Setting("ItemBob", (T)true, p0 -> this.settings.getValue() == Settings.TWEAKS));
        this.mainX = (Setting<Double>)this.register(new Setting("MainX", (T)1.2, (T)(-2.0), (T)4.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.mainY = (Setting<Double>)this.register(new Setting("MainY", (T)(-0.95), (T)(-3.0), (T)3.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.mainZ = (Setting<Double>)this.register(new Setting("MainZ", (T)(-1.45), (T)(-5.0), (T)5.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.offX = (Setting<Double>)this.register(new Setting("OffX", (T)1.2, (T)(-2.0), (T)4.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.offY = (Setting<Double>)this.register(new Setting("OffY", (T)(-0.95), (T)(-3.0), (T)3.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.offZ = (Setting<Double>)this.register(new Setting("OffZ", (T)(-1.45), (T)(-5.0), (T)5.0, p0 -> this.settings.getValue() == Settings.TRANSLATE));
        this.mainRotX = (Setting<Integer>)this.register(new Setting("MainRotationX", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.mainRotY = (Setting<Integer>)this.register(new Setting("MainRotationY", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.mainRotZ = (Setting<Integer>)this.register(new Setting("MainRotationZ", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.offRotX = (Setting<Integer>)this.register(new Setting("OffRotationX", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.offRotY = (Setting<Integer>)this.register(new Setting("OffRotationY", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.offRotZ = (Setting<Integer>)this.register(new Setting("OffRotationZ", (T)0, (T)(-36), (T)36, p0 -> this.settings.getValue() == Settings.ROTATE));
        this.mainScaleX = (Setting<Double>)this.register(new Setting("MainScaleX", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.mainScaleY = (Setting<Double>)this.register(new Setting("MainScaleY", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.mainScaleZ = (Setting<Double>)this.register(new Setting("MainScaleZ", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.offScaleX = (Setting<Double>)this.register(new Setting("OffScaleX", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.offScaleY = (Setting<Double>)this.register(new Setting("OffScaleY", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.offScaleZ = (Setting<Double>)this.register(new Setting("OffScaleZ", (T)1.0, (T)0.1, (T)5.0, p0 -> this.settings.getValue() == Settings.SCALE));
        this.setInstance();
    }
    
    private void setInstance() {
        ViewModel.INSTANCE = this;
    }
    
    private enum Settings
    {
        TWEAKS, 
        ROTATE, 
        TRANSLATE, 
        SCALE;
    }
}

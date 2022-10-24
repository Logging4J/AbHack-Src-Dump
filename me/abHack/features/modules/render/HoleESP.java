//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.util.math.*;
import net.minecraft.init.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;

public class HoleESP extends Module
{
    private final /* synthetic */ Setting<Integer> boxAlpha;
    public /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> rangeY;
    private final /* synthetic */ Setting<Integer> safeAlpha;
    private static /* synthetic */ HoleESP INSTANCE;
    private final /* synthetic */ Setting<Integer> safeRed;
    public /* synthetic */ Setting<Boolean> outline;
    public /* synthetic */ Setting<Boolean> fov;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Integer> safeGreen;
    public /* synthetic */ Setting<Boolean> renderOwn;
    private final /* synthetic */ Setting<Integer> safeBlue;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> range;
    public /* synthetic */ Setting<Boolean> safeColor;
    public /* synthetic */ Setting<Double> height;
    private final /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Boolean> rainbow;
    
    static {
        HoleESP.INSTANCE = new HoleESP();
    }
    
    public HoleESP() {
        super("HoleESP", "hole esp", Module.Category.RENDER, false, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("RangeX", (T)8, (T)0, (T)20));
        this.rangeY = (Setting<Integer>)this.register(new Setting("RangeY", (T)8, (T)0, (T)20));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)0, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)0, (T)0, (T)255));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
        this.renderOwn = (Setting<Boolean>)this.register(new Setting("RenderOwn", (T)true));
        this.fov = (Setting<Boolean>)this.register(new Setting("InFov", (T)true));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)40, (T)0, (T)255, p0 -> this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.outline.getValue()));
        this.height = (Setting<Double>)this.register(new Setting("Height", (T)0.3, (T)(-2.0), (T)2.0));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)false));
        this.safeColor = (Setting<Boolean>)this.register(new Setting("BedrockColor", (T)false));
        this.safeRed = (Setting<Integer>)this.register(new Setting("BedrockRed", (T)0, (T)0, (T)255, p0 -> this.safeColor.getValue()));
        this.safeGreen = (Setting<Integer>)this.register(new Setting("BedrockGreen", (T)255, (T)0, (T)255, p0 -> this.safeColor.getValue()));
        this.safeBlue = (Setting<Integer>)this.register(new Setting("BedrockBlue", (T)0, (T)0, (T)255, p0 -> this.safeColor.getValue()));
        this.safeAlpha = (Setting<Integer>)this.register(new Setting("BedrockAlpha", (T)255, (T)0, (T)255, p0 -> this.safeColor.getValue()));
        this.setInstance();
    }
    
    public static HoleESP getInstance() {
        if (HoleESP.INSTANCE == null) {
            HoleESP.INSTANCE = new HoleESP();
        }
        return HoleESP.INSTANCE;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        assert HoleESP.mc.renderViewEntity != null;
        final Vec3i vec3i = new Vec3i(HoleESP.mc.renderViewEntity.posX, HoleESP.mc.renderViewEntity.posY, HoleESP.mc.renderViewEntity.posZ);
        for (int i = vec3i.getX() - this.range.getValue(); i < vec3i.getX() + this.range.getValue(); ++i) {
            for (int j = vec3i.getZ() - this.range.getValue(); j < vec3i.getZ() + this.range.getValue(); ++j) {
                for (int k = vec3i.getY() + this.rangeY.getValue(); k > vec3i.getY() - this.rangeY.getValue(); --k) {
                    final BlockPos blockPos = new BlockPos(i, k, j);
                    if (HoleESP.mc.world.getBlockState(blockPos).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 1, 0)).getBlock().equals(Blocks.AIR) && HoleESP.mc.world.getBlockState(blockPos.add(0, 2, 0)).getBlock().equals(Blocks.AIR) && (!blockPos.equals((Object)new BlockPos(HoleESP.mc.player.posX, HoleESP.mc.player.posY, HoleESP.mc.player.posZ)) || this.renderOwn.getValue())) {
                        if (BlockUtil.isPosInFov(blockPos) || !this.fov.getValue()) {
                            final int n = 0;
                            if (HoleESP.mc.world.getBlockState(blockPos.north()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.east()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.west()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.south()).getBlock() == Blocks.BEDROCK && HoleESP.mc.world.getBlockState(blockPos.down()).getBlock() == Blocks.BEDROCK) {
                                RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.safeRed.getValue(), this.safeGreen.getValue(), this.safeBlue.getValue(), this.safeAlpha.getValue()), false, new Color(1, 1, 1, 1), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true, this.height.getValue(), true, true, false, true, n);
                            }
                            else if (BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.down()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.east()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.west()).getBlock()) && BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.south()).getBlock())) {
                                if (BlockUtil.isBlockUnSafe(HoleESP.mc.world.getBlockState(blockPos.north()).getBlock())) {
                                    RenderUtil.drawBoxESP(blockPos, ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), false, new Color(1, 1, 1, 1), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true, this.height.getValue(), true, true, false, true, n);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    
    private void setInstance() {
        HoleESP.INSTANCE = this;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import me.abHack.event.events.*;
import net.minecraft.util.math.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;

public class BlockHighlight extends Module
{
    public final /* synthetic */ Setting<Boolean> rainbow;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> cAlpha;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> red;
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        final RayTraceResult objectMouseOver = BlockHighlight.mc.objectMouseOver;
        if (objectMouseOver != null && objectMouseOver.typeOfHit == RayTraceResult.Type.BLOCK) {
            RenderUtil.drawBlockOutline(objectMouseOver.getBlockPos(), ((boolean)this.rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), false);
        }
    }
    
    public BlockHighlight() {
        super("BlockHighlight", "Render the block you are looking at.", Module.Category.RENDER, false, false, false);
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)255, (T)0, (T)255));
    }
}

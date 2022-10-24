//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.util.math.*;
import me.abHack.event.events.*;
import java.awt.*;
import me.abHack.util.*;
import java.util.*;
import net.minecraftforge.fml.common.gameevent.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.block.*;

public class PortalESP extends Module
{
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ ArrayList<BlockPos> blockPosArrayList;
    private /* synthetic */ int cooldownTicks;
    private final /* synthetic */ Setting<Integer> distance;
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (PortalESP.mc.world == null) {
            return;
        }
        final Iterator<BlockPos> iterator = this.blockPosArrayList.iterator();
        while (iterator.hasNext()) {
            RenderUtil.drawBoxESP(iterator.next(), new Color(204, 0, 153, 255), false, new Color(204, 0, 153, 255), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), false);
        }
    }
    
    @SubscribeEvent
    public void onTickEvent(final TickEvent.ClientTickEvent clientTickEvent) {
        if (PortalESP.mc.world == null) {
            return;
        }
        if (this.cooldownTicks < 1) {
            this.blockPosArrayList.clear();
            this.compileDL();
            this.cooldownTicks = 80;
        }
        --this.cooldownTicks;
    }
    
    private void compileDL() {
        if (PortalESP.mc.world == null || PortalESP.mc.player == null) {
            return;
        }
        for (int i = (int)PortalESP.mc.player.posX - this.distance.getValue(); i <= (int)PortalESP.mc.player.posX + this.distance.getValue(); ++i) {
            for (int j = (int)PortalESP.mc.player.posY - this.distance.getValue(); j <= (int)PortalESP.mc.player.posY + this.distance.getValue(); ++j) {
                for (int n = (int)Math.max(PortalESP.mc.player.posZ - this.distance.getValue(), 1.0); n <= Math.min(PortalESP.mc.player.posZ + this.distance.getValue(), 256.0); ++n) {
                    final BlockPos e = new BlockPos(i, j, n);
                    if (PortalESP.mc.world.getBlockState(e).getBlock() instanceof BlockPortal) {
                        this.blockPosArrayList.add(e);
                    }
                }
            }
        }
    }
    
    public PortalESP() {
        super("PortalESP", "Draws esp over portals.", Module.Category.RENDER, true, false, false);
        this.blockPosArrayList = new ArrayList<BlockPos>();
        this.distance = (Setting<Integer>)this.register(new Setting("Distance", (T)60, (T)1, (T)256));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)false));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)125, (T)0, (T)255, p0 -> this.box.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.outline.getValue()));
    }
}

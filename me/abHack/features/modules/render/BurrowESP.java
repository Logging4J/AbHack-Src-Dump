//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import me.abHack.event.events.*;
import java.util.function.*;
import net.minecraft.init.*;
import java.awt.*;
import me.abHack.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import java.util.*;

public class BurrowESP extends Module
{
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> name;
    private final /* synthetic */ Setting<Integer> boxGreen;
    private final /* synthetic */ Setting<Integer> outlineRed;
    private final /* synthetic */ Setting<Integer> boxRed;
    private final /* synthetic */ Setting<Integer> outlineAlpha;
    private final /* synthetic */ Map<EntityPlayer, BlockPos> burrowedPlayers;
    private final /* synthetic */ Setting<Boolean> cOutline;
    private final /* synthetic */ Setting<Integer> boxBlue;
    private final /* synthetic */ Setting<Float> outlineWidth;
    private final /* synthetic */ Setting<Boolean> box;
    private final /* synthetic */ Setting<Integer> outlineGreen;
    private final /* synthetic */ Setting<Integer> outlineBlue;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    
    private void lambda$onRender3D$8(final Map.Entry entry) {
        this.renderBurrowedBlock(entry.getValue());
        if (this.name.getValue()) {
            RenderUtil.drawText(entry.getValue(), ((EntityPlayer)entry.getKey()).getGameProfile().getName());
        }
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        this.burrowedPlayers.clear();
        this.getPlayers();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (!this.burrowedPlayers.isEmpty()) {
            this.burrowedPlayers.entrySet().forEach(this::lambda$onRender3D$8);
        }
    }
    
    public BurrowESP() {
        super("BurrowESP", "Show burrow players .", Module.Category.RENDER, true, false, false);
        this.name = (Setting<Boolean>)this.register(new Setting("Name", (T)false));
        this.box = new Setting<Boolean>("Box", true);
        this.boxRed = (Setting<Integer>)this.register(new Setting("BoxRed", (T)255, (T)0, (T)255));
        this.boxGreen = (Setting<Integer>)this.register(new Setting("BoxGreen", (T)255, (T)0, (T)255));
        this.boxBlue = (Setting<Integer>)this.register(new Setting("BoxBlue", (T)255, (T)0, (T)255));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)125, (T)0, (T)255));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.outlineWidth = (Setting<Float>)this.register(new Setting("OutlineWidth", (T)1.0f, (T)0.0f, (T)5.0f, p0 -> this.outline.getValue()));
        this.cOutline = (Setting<Boolean>)this.register(new Setting("CustomOutline", (T)false, p0 -> this.outline.getValue()));
        this.outlineRed = (Setting<Integer>)this.register(new Setting("OutlineRed", (T)255, (T)0, (T)255, p0 -> this.cOutline.getValue()));
        this.outlineGreen = (Setting<Integer>)this.register(new Setting("OutlineGreen", (T)255, (T)0, (T)255, p0 -> this.cOutline.getValue()));
        this.outlineBlue = (Setting<Integer>)this.register(new Setting("OutlineBlue", (T)255, (T)0, (T)255, p0 -> this.cOutline.getValue()));
        this.outlineAlpha = (Setting<Integer>)this.register(new Setting("OutlineAlpha", (T)255, (T)0, (T)255, p0 -> this.cOutline.getValue()));
        this.burrowedPlayers = new HashMap<EntityPlayer, BlockPos>();
    }
    
    public void onEnable() {
        this.burrowedPlayers.clear();
    }
    
    private boolean isBurrowed(final EntityPlayer entityPlayer) {
        final BlockPos blockPos = new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY + 0.2), Math.floor(entityPlayer.posZ));
        return BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.ENDER_CHEST || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.OBSIDIAN || BurrowESP.mc.world.getBlockState(blockPos).getBlock() == Blocks.CHEST;
    }
    
    private void renderBurrowedBlock(final BlockPos blockPos) {
        RenderUtil.drawBoxESP(blockPos, new Color(this.boxRed.getValue(), this.boxGreen.getValue(), this.boxBlue.getValue(), this.boxAlpha.getValue()), true, new Color(this.outlineRed.getValue(), this.outlineGreen.getValue(), this.outlineBlue.getValue(), this.outlineAlpha.getValue()), this.outlineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
    }
    
    private void getPlayers() {
        for (final EntityPlayer entityPlayer : BurrowESP.mc.world.playerEntities) {
            if (entityPlayer != BurrowESP.mc.player && !OyVey.friendManager.isFriend(entityPlayer.getName()) && EntityUtil.isLiving((Entity)entityPlayer)) {
                if (!this.isBurrowed(entityPlayer)) {
                    continue;
                }
                this.burrowedPlayers.put(entityPlayer, new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ));
            }
        }
    }
}

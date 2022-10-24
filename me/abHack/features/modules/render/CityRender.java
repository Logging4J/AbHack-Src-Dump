//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import net.minecraft.entity.player.*;
import me.abHack.features.setting.*;
import net.minecraft.init.*;
import java.awt.*;
import me.abHack.util.*;
import net.minecraft.util.math.*;
import me.abHack.event.events.*;
import net.minecraft.entity.*;
import java.util.*;

public class CityRender extends Module
{
    public /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Integer> range;
    
    private void surroundRender() {
        if (this.target != null) {
            final Vec3d getPositionVector = this.target.getPositionVector();
            if (CityRender.mc.world.getBlockState(new BlockPos(getPositionVector)).getBlock() == Blocks.OBSIDIAN || CityRender.mc.world.getBlockState(new BlockPos(getPositionVector)).getBlock() == Blocks.ENDER_CHEST) {
                RenderUtil.drawBoxESP(new BlockPos(getPositionVector), new Color(255, 255, 0), false, new Color(255, 255, 0), 1.0f, false, true, 42, true);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 1)) {
                this.surroundRender(getPositionVector, -1.0, 0.0, 0.0, true);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 2)) {
                this.surroundRender(getPositionVector, 1.0, 0.0, 0.0, true);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 3)) {
                this.surroundRender(getPositionVector, 0.0, 0.0, -1.0, true);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 4)) {
                this.surroundRender(getPositionVector, 0.0, 0.0, 1.0, true);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 5)) {
                this.surroundRender(getPositionVector, -1.0, 0.0, 0.0, false);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 6)) {
                this.surroundRender(getPositionVector, 1.0, 0.0, 0.0, false);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 7)) {
                this.surroundRender(getPositionVector, 0.0, 0.0, -1.0, false);
            }
            if (EntityUtil.getSurroundWeakness(getPositionVector, -1, 8)) {
                this.surroundRender(getPositionVector, 0.0, 0.0, 1.0, false);
            }
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.target = this.getTarget(this.range.getValue());
        this.surroundRender();
    }
    
    public CityRender() {
        super("CityRender", "CityRender", Module.Category.RENDER, true, false, false);
        this.range = (Setting<Integer>)this.register(new Setting("Range", (T)5, (T)1, (T)10));
    }
    
    private void surroundRender(final Vec3d vec3d, final double n, final double n2, final double n3, final boolean b) {
        final BlockPos add = new BlockPos(vec3d).add(n, n2, n3);
        if (CityRender.mc.world.getBlockState(add).getBlock() == Blocks.AIR || CityRender.mc.world.getBlockState(add).getBlock() == Blocks.FIRE) {
            return;
        }
        if (b) {
            RenderUtil.drawBoxESP(add, new Color(255, 0, 0), false, new Color(255, 0, 0), 1.0f, false, true, 42, true);
        }
        else {
            RenderUtil.drawBoxESP(add, new Color(0, 0, 255), false, new Color(0, 0, 255), 1.0f, false, true, 42, true);
        }
    }
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : CityRender.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                continue;
            }
            if (!EntityUtil.isInHole((Entity)entityPlayer2)) {
                continue;
            }
            if (entityPlayer == null) {
                entityPlayer = entityPlayer2;
                n2 = EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
            }
            else {
                if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2) >= n2) {
                    continue;
                }
                entityPlayer = entityPlayer2;
                n2 = EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer2);
            }
        }
        return entityPlayer;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import me.abHack.*;
import java.util.*;
import net.minecraft.util.math.*;
import me.abHack.features.modules.player.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import me.abHack.util.*;

public class AutoCity extends Module
{
    private final /* synthetic */ Setting<Float> range;
    public static /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Boolean> toggle;
    public final /* synthetic */ Setting<Boolean> priority;
    private static /* synthetic */ AutoCity INSTANCE;
    
    public AutoCity() {
        super("AutoCity", "AutoCity", Category.COMBAT, true, false, false);
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)5.0f, (T)1.0f, (T)8.0f));
        this.toggle = (Setting<Boolean>)this.register(new Setting("AutoToggle", (T)false));
        this.priority = (Setting<Boolean>)this.register(new Setting("City Priority ", (T)true));
        this.setInstance();
    }
    
    public static AutoCity getInstance() {
        if (AutoCity.INSTANCE == null) {
            AutoCity.INSTANCE = new AutoCity();
        }
        return AutoCity.INSTANCE;
    }
    
    @Override
    public String getDisplayInfo() {
        if (AutoCity.target != null) {
            return AutoCity.target.getName();
        }
        return null;
    }
    
    private EntityPlayer getTarget(final double n) {
        EntityPlayer entityPlayer = null;
        double n2 = n;
        for (final EntityPlayer entityPlayer2 : AutoCity.mc.world.playerEntities) {
            if (EntityUtil.isntValid((Entity)entityPlayer2, n)) {
                continue;
            }
            if (this.check(entityPlayer2) || OyVey.friendManager.isFriend(entityPlayer2.getName())) {
                continue;
            }
            if (AutoCity.mc.player.posY - entityPlayer2.posY >= 5.0) {
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
    
    private void setInstance() {
        AutoCity.INSTANCE = this;
    }
    
    private void surroundMine(final Vec3d vec3d, final double n, final double n2, final double n3) {
        final BlockPos add = new BlockPos(vec3d).add(n, n2, n3);
        if (InstantMine.getInstance().isOff()) {
            InstantMine.getInstance().enable();
            return;
        }
        if (!InstantMine.getInstance().isOn()) {
            return;
        }
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)add)) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoCity.target.posX, AutoCity.target.posY, AutoCity.target.posZ)) && AutoCity.mc.world.getBlockState(new BlockPos(AutoCity.target.posX, AutoCity.target.posY, AutoCity.target.posZ)).getBlock() != Blocks.AIR && !this.priority.getValue()) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoCity.mc.player.posX, AutoCity.mc.player.posY + 2.0, AutoCity.mc.player.posZ))) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AutoCity.mc.player.posX, AutoCity.mc.player.posY - 1.0, AutoCity.mc.player.posZ))) {
                return;
            }
            if (AutoCity.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.WEB) {
                return;
            }
            if (OyVey.moduleManager.isModuleEnabled("Anti32k") && AutoCity.mc.world.getBlockState(InstantMine.breakPos).getBlock() instanceof BlockHopper) {
                return;
            }
            if (OyVey.moduleManager.isModuleEnabled("AntiShulkerBox") && AutoCity.mc.world.getBlockState(InstantMine.breakPos).getBlock() instanceof BlockShulkerBox) {
                return;
            }
        }
        AutoCity.mc.playerController.onPlayerDamageBlock(add, BlockUtil.getRayTraceFacing(add));
    }
    
    private void surroundMine() {
        if (AutoCity.target != null) {
            final Vec3d getPositionVector = AutoCity.target.getPositionVector();
            if (EntityUtil.getSurroundWeakness(getPositionVector, 1, -1)) {
                this.surroundMine(getPositionVector, -1.0, 0.0, 0.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 2, -1)) {
                this.surroundMine(getPositionVector, 1.0, 0.0, 0.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 3, -1)) {
                this.surroundMine(getPositionVector, 0.0, 0.0, -1.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 4, -1)) {
                this.surroundMine(getPositionVector, 0.0, 0.0, 1.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 5, -1)) {
                this.surroundMine(getPositionVector, -1.0, 0.0, 0.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 6, -1)) {
                this.surroundMine(getPositionVector, 1.0, 0.0, 0.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 7, -1)) {
                this.surroundMine(getPositionVector, 0.0, 0.0, -1.0);
            }
            else if (EntityUtil.getSurroundWeakness(getPositionVector, 8, -1)) {
                this.surroundMine(getPositionVector, 0.0, 0.0, 1.0);
            }
            else {
                AutoCity.target = null;
            }
        }
        if (this.toggle.getValue()) {
            this.toggle();
        }
    }
    
    public boolean check(final EntityPlayer entityPlayer) {
        return AutoCity.mc.world.getBlockState(new BlockPos(entityPlayer.posX + 1.0, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR || AutoCity.mc.world.getBlockState(new BlockPos(entityPlayer.posX - 1.0, entityPlayer.posY, entityPlayer.posZ)).getBlock() == Blocks.AIR || AutoCity.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ + 1.0)).getBlock() == Blocks.AIR || AutoCity.mc.world.getBlockState(new BlockPos(entityPlayer.posX, entityPlayer.posY, entityPlayer.posZ - 1.0)).getBlock() == Blocks.AIR;
    }
    
    static {
        AutoCity.INSTANCE = new AutoCity();
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (OyVey.moduleManager.isModuleEnabled("AutoCev")) {
            return;
        }
        if (InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) == -1) {
            return;
        }
        AutoCity.target = this.getTarget(this.range.getValue());
        this.surroundMine();
    }
}

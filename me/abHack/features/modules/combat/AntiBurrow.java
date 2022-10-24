//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.client.gui.*;
import me.abHack.features.modules.player.*;
import me.abHack.*;
import net.minecraft.init.*;
import net.minecraft.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.math.*;
import net.minecraft.block.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import java.util.*;

public class AntiBurrow extends Module
{
    public static /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Boolean> toggle;
    private final /* synthetic */ Setting<Double> range;
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (AntiBurrow.mc.currentScreen instanceof GuiHopper) {
            return;
        }
        final EntityPlayer target = this.getTarget(this.range.getValue());
        if (this.toggle.getValue()) {
            this.toggle();
        }
        if (target == null) {
            return;
        }
        AntiBurrow.pos = new BlockPos(target.posX, target.posY + 0.5, target.posZ);
        if (AntiBurrow.pos == null) {
            return;
        }
        if (InstantMine.breakPos != null) {
            if (InstantMine.breakPos.equals((Object)AntiBurrow.pos)) {
                return;
            }
            if (OyVey.moduleManager.isModuleEnabled("AutoCity") && AutoCity.target != null && AutoCity.getInstance().priority.getValue()) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AntiBurrow.mc.player.posX, AntiBurrow.mc.player.posY + 2.0, AntiBurrow.mc.player.posZ))) {
                return;
            }
            if (InstantMine.breakPos.equals((Object)new BlockPos(AntiBurrow.mc.player.posX, AntiBurrow.mc.player.posY - 1.0, AntiBurrow.mc.player.posZ))) {
                return;
            }
            if (AntiBurrow.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.WEB) {
                return;
            }
            if (OyVey.moduleManager.isModuleEnabled("Anti32k") && AntiBurrow.mc.world.getBlockState(InstantMine.breakPos).getBlock() instanceof BlockHopper) {
                return;
            }
            if (OyVey.moduleManager.isModuleEnabled("AntiShulkerBox") && AntiBurrow.mc.world.getBlockState(InstantMine.breakPos).getBlock() instanceof BlockShulkerBox) {
                return;
            }
        }
        if (AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.AIR && !this.isOnLiquid() && !this.isInLiquid() && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.WATER && AntiBurrow.mc.world.getBlockState(AntiBurrow.pos).getBlock() != Blocks.LAVA) {
            AntiBurrow.mc.player.swingArm(EnumHand.MAIN_HAND);
            AntiBurrow.mc.playerController.onPlayerDamageBlock(AntiBurrow.pos, BlockUtil.getRayTraceFacing(AntiBurrow.pos));
        }
    }
    
    public AntiBurrow() {
        super("AntiBurrow", "AntiBurrow", Category.COMBAT, true, false, false);
        this.range = (Setting<Double>)this.register(new Setting("Range", (T)5.0, (T)1.0, (T)8.0));
        this.toggle = (Setting<Boolean>)this.register(new Setting("Toggle", (T)false));
    }
    
    private boolean isInLiquid() {
        final double n = AntiBurrow.mc.player.posY + 0.01;
        for (int i = MathHelper.floor(AntiBurrow.mc.player.posX); i < MathHelper.ceil(AntiBurrow.mc.player.posX); ++i) {
            for (int j = MathHelper.floor(AntiBurrow.mc.player.posZ); j < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++j) {
                if (AntiBurrow.mc.world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private EntityPlayer getTarget(final double a) {
        EntityPlayer entityPlayer = null;
        double n = Math.pow(a, 2.0) + 1.0;
        for (final EntityPlayer entityPlayer2 : AutoTrap.mc.world.playerEntities) {
            if (!EntityUtil.isntValid((Entity)entityPlayer2, a)) {
                if (OyVey.speedManager.getPlayerSpeed(entityPlayer2) > 10.0) {
                    continue;
                }
                if (entityPlayer == null) {
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
                else {
                    if (AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2) >= n) {
                        continue;
                    }
                    entityPlayer = entityPlayer2;
                    n = AutoTrap.mc.player.getDistanceSq((Entity)entityPlayer2);
                }
            }
        }
        return entityPlayer;
    }
    
    private boolean isOnLiquid() {
        final double n = AntiBurrow.mc.player.posY - 0.03;
        for (int i = MathHelper.floor(AntiBurrow.mc.player.posX); i < MathHelper.ceil(AntiBurrow.mc.player.posX); ++i) {
            for (int j = MathHelper.floor(AntiBurrow.mc.player.posZ); j < MathHelper.ceil(AntiBurrow.mc.player.posZ); ++j) {
                if (AntiBurrow.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
}

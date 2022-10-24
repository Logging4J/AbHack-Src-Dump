//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.util.*;
import net.minecraft.entity.item.*;
import net.minecraft.block.*;
import com.mojang.realmsclient.gui.*;
import me.abHack.features.command.*;
import java.util.*;

public class AntiPiston extends Module
{
    private void breakCrystalPiston(final Entity entity) {
        breakCrystal(entity);
    }
    
    public static Block getBlock(final double n, final double n2, final double n3) {
        return AntiPiston.mc.world.getBlockState(new BlockPos(n, n2, n3)).getBlock();
    }
    
    @Override
    public void onUpdate() {
        this.blockPiston();
    }
    
    public static void breakCrystal(final Entity entity) {
        AntiPiston.mc.playerController.attackEntity((EntityPlayer)AntiPiston.mc.player, entity);
        AntiPiston.mc.player.swingArm(EnumHand.MAIN_HAND);
    }
    
    private void blockPiston() {
        for (final Entity entity : AntiPiston.mc.world.loadedEntityList) {
            if (entity instanceof EntityEnderCrystal && entity.posX >= AntiPiston.mc.player.posX - 1.5 && entity.posX <= AntiPiston.mc.player.posX + 1.5 && entity.posZ >= AntiPiston.mc.player.posZ - 1.5 && entity.posZ <= AntiPiston.mc.player.posZ + 1.5) {
                for (int i = -2; i < 3; ++i) {
                    for (int j = -2; j < 3; ++j) {
                        if (getBlock(entity.posX + i, entity.posY, entity.posZ + j) instanceof BlockPistonBase) {
                            this.breakCrystalPiston(entity);
                            Command.sendMessage(String.valueOf(new StringBuilder().append(ChatFormatting.GREEN).append("PistonCrystal detected... Destroyed crystal!")));
                        }
                    }
                }
            }
        }
    }
    
    public AntiPiston() {
        super("AntiPiston", "Anti PistonCrystal", Category.COMBAT, true, false, false);
    }
}

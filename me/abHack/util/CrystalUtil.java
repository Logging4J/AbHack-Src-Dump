//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import net.minecraft.client.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.block.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.*;
import net.minecraft.enchantment.*;
import net.minecraft.init.*;

public class CrystalUtil
{
    public static /* synthetic */ Minecraft mc;
    
    public static double getVecDistance(final BlockPos blockPos, final double n, final double n2, final double n3) {
        final double n4 = blockPos.getX() - n;
        final double n5 = blockPos.getY() - n2;
        final double n6 = blockPos.getZ() - n3;
        return Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
    }
    
    public static EnumActionResult placeCrystal(final BlockPos blockPos) {
        blockPos.offset(EnumFacing.DOWN);
        final double n = blockPos.getX() + 0.5 - CrystalUtil.mc.player.posX;
        final double n2 = blockPos.getY() + 0.5 - CrystalUtil.mc.player.posY - 0.5 - CrystalUtil.mc.player.getEyeHeight();
        final double n3 = blockPos.getZ() + 0.5 - CrystalUtil.mc.player.posZ;
        final Vec3d vectorForRotation = getVectorForRotation(-getDirection2D(n2, Math.sqrt(n * n + n3 * n3)), getDirection2D(n3, n) - 90.0);
        if (((ItemStack)CrystalUtil.mc.player.inventory.offHandInventory.get(0)).getItem().getClass().equals(Item.getItemById(426).getClass())) {
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vectorForRotation, EnumHand.OFF_HAND);
        }
        if (InventoryUtil.pickItem(426) != -1) {
            InventoryUtil.setSlot(InventoryUtil.pickItem(426));
            return CrystalUtil.mc.playerController.processRightClickBlock(CrystalUtil.mc.player, CrystalUtil.mc.world, blockPos.offset(EnumFacing.DOWN), EnumFacing.UP, vectorForRotation, EnumHand.MAIN_HAND);
        }
        return EnumActionResult.FAIL;
    }
    
    public static double getRange(final Vec3d vec3d, final double n, final double n2, final double n3) {
        final double n4 = vec3d.x - n;
        final double n5 = vec3d.y - n2;
        final double n6 = vec3d.z - n3;
        return Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
    }
    
    protected static final Vec3d getVectorForRotation(final double n, final double n2) {
        final float cos = MathHelper.cos((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float sin = MathHelper.sin((float)(-n2 * 0.01745329238474369 - 3.1415927410125732));
        final float n3 = -MathHelper.cos((float)(-n * 0.01745329238474369));
        return new Vec3d((double)(sin * n3), (double)MathHelper.sin((float)(-n * 0.01745329238474369)), (double)(cos * n3));
    }
    
    private static float getDamageMultiplied(final float n) {
        final int getId = CrystalUtil.mc.world.getDifficulty().getId();
        return n * ((getId == 0) ? 0.0f : ((getId == 2) ? 1.0f : ((getId == 1) ? 0.5f : 1.5f)));
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity) {
        return calculateDamage(n, n2, n3, entity, new Vec3d(entity.posX, entity.posY, entity.posZ));
    }
    
    public static float calculateDamage(final double n, final double n2, final double n3, final Entity entity, final Vec3d vec3d) {
        final float n4 = 12.0f;
        final double n5 = (1.0 - getRange(vec3d, n, n2, n3) / n4) * entity.world.getBlockDensity(new Vec3d(n, n2, n3), entity.getEntityBoundingBox());
        final float n6 = (float)(int)((n5 * n5 + n5) / 2.0 * 7.0 * n4 + 1.0);
        double n7 = 1.0;
        if (entity instanceof EntityLivingBase) {
            n7 = getBlastReduction((EntityLivingBase)entity, getDamageMultiplied(n6), new Explosion((World)CrystalUtil.mc.world, (Entity)null, n, n2, n3, 6.0f, false, true));
        }
        return (float)n7;
    }
    
    protected static final double getDirection2D(final double n, final double n2) {
        double n3;
        if (n2 == 0.0) {
            n3 = ((n > 0.0) ? 90.0 : -90.0);
        }
        else {
            n3 = Math.atan(n / n2) * 57.2957796;
            if (n2 < 0.0) {
                double n4 = 0.0;
                if (n > 0.0) {
                    n4 = n3 + 180.0;
                }
                else if (n < 0.0) {}
                n3 = n4;
            }
        }
        return n3;
    }
    
    public static boolean isEating() {
        return CrystalUtil.mc.player != null && (CrystalUtil.mc.player.getHeldItemMainhand().getItem() instanceof ItemFood || CrystalUtil.mc.player.getHeldItemOffhand().getItem() instanceof ItemFood) && CrystalUtil.mc.player.isHandActive();
    }
    
    static {
        CrystalUtil.mc = Minecraft.getMinecraft();
    }
    
    public static boolean isReplaceable(final Block block) {
        return block == Blocks.FIRE || block == Blocks.DOUBLE_PLANT || block == Blocks.VINE;
    }
    
    public static BlockPos getPlayerPos() {
        return new BlockPos(Math.floor(CrystalUtil.mc.player.posX), Math.floor(CrystalUtil.mc.player.posY), Math.floor(CrystalUtil.mc.player.posZ));
    }
    
    public static boolean canSeeBlock(final BlockPos blockPos) {
        return CrystalUtil.mc.player == null || CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ()), false, true, false) != null;
    }
    
    public static EnumFacing enumFacing(final BlockPos blockPos) {
        EnumFacing[] values;
        for (int length = (values = EnumFacing.values()).length, i = 0; i < length; ++i) {
            final EnumFacing enumFacing = values[i];
            final RayTraceResult rayTraceBlocks;
            if ((rayTraceBlocks = CrystalUtil.mc.world.rayTraceBlocks(new Vec3d(CrystalUtil.mc.player.posX, CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight(), CrystalUtil.mc.player.posZ), new Vec3d((double)(blockPos.getX() + enumFacing.getDirectionVec().getX()), (double)(blockPos.getY() + enumFacing.getDirectionVec().getY()), (double)(blockPos.getZ() + enumFacing.getDirectionVec().getZ())), false, true, false)) != null && rayTraceBlocks.typeOfHit.equals((Object)RayTraceResult.Type.BLOCK) && rayTraceBlocks.getBlockPos().equals((Object)blockPos)) {
                return enumFacing;
            }
        }
        if (blockPos.getY() > CrystalUtil.mc.player.posY + CrystalUtil.mc.player.getEyeHeight()) {
            return EnumFacing.DOWN;
        }
        return EnumFacing.UP;
    }
    
    private static float getBlastReduction(final EntityLivingBase entityLivingBase, float a, final Explosion explosion) {
        try {
            if (entityLivingBase instanceof EntityPlayer) {
                final EntityPlayer entityPlayer = (EntityPlayer)entityLivingBase;
                final DamageSource causeExplosionDamage = DamageSource.causeExplosionDamage(explosion);
                a = CombatRules.getDamageAfterAbsorb(a, (float)entityPlayer.getTotalArmorValue(), (float)entityPlayer.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
                a *= 1.0f - MathHelper.clamp((float)EnchantmentHelper.getEnchantmentModifierDamage(entityPlayer.getArmorInventoryList(), causeExplosionDamage), 0.0f, 20.0f) / 25.0f;
                if (entityLivingBase.isPotionActive(MobEffects.RESISTANCE)) {
                    a -= a / 5.0f;
                }
                a = Math.max(a, 0.0f);
                return a;
            }
            a = CombatRules.getDamageAfterAbsorb(a, (float)entityLivingBase.getTotalArmorValue(), (float)entityLivingBase.getEntityAttribute(SharedMonsterAttributes.ARMOR_TOUGHNESS).getAttributeValue());
            return a;
        }
        catch (Exception ex) {
            return getBlastReduction(entityLivingBase, a, explosion);
        }
    }
}

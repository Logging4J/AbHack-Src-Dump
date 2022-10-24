//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.util;

import me.abHack.mixin.mixins.accessors.*;
import net.minecraft.entity.player.*;
import me.abHack.*;
import net.minecraft.potion.*;
import net.minecraft.block.*;
import net.minecraft.entity.monster.*;
import net.minecraft.block.state.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import java.util.*;
import java.text.*;
import java.math.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.network.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.network.play.client.*;
import net.minecraft.client.*;
import net.minecraft.entity.passive.*;
import net.minecraft.util.*;
import java.awt.*;
import net.minecraft.entity.item.*;
import me.abHack.features.modules.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.item.*;

public class EntityUtil implements Util
{
    public static final /* synthetic */ Vec3d[] legOffsetList;
    public static final /* synthetic */ Vec3d[] antiDropOffsetList;
    public static final /* synthetic */ Vec3d[] platformOffsetList;
    public static final /* synthetic */ Vec3d[] antiScaffoldOffsetList;
    public static final /* synthetic */ Vec3d[] doubleLegOffsetList;
    public static final /* synthetic */ Vec3d[] antiStepOffsetList;
    public static final /* synthetic */ Vec3d[] OffsetList;
    
    public static boolean isMobAggressive(final Entity entity) {
        if (entity instanceof EntityPigZombie) {
            if (((EntityPigZombie)entity).isArmsRaised() || ((EntityPigZombie)entity).isAngry()) {
                return true;
            }
        }
        else {
            if (entity instanceof EntityWolf) {
                return ((EntityWolf)entity).isAngry() && !EntityUtil.mc.player.equals((Object)((EntityWolf)entity).getOwner());
            }
            if (entity instanceof EntityEnderman) {
                return ((EntityEnderman)entity).isScreaming();
            }
        }
        return isHostileMob(entity);
    }
    
    public static boolean isAboveBlock(final Entity entity, final BlockPos blockPos) {
        return entity.posY >= blockPos.getY();
    }
    
    public static Vec3d interpolateEntity(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX + (entity.posX - entity.lastTickPosX) * n, entity.lastTickPosY + (entity.posY - entity.lastTickPosY) * n, entity.lastTickPosZ + (entity.posZ - entity.lastTickPosZ) * n);
    }
    
    public static void swingArmNoPacket(final EnumHand swingingHand, final EntityLivingBase entityLivingBase) {
        final ItemStack getHeldItem = entityLivingBase.getHeldItem(swingingHand);
        if (!getHeldItem.isEmpty() && getHeldItem.getItem().onEntitySwing(entityLivingBase, getHeldItem)) {
            return;
        }
        if (!entityLivingBase.isSwingInProgress || entityLivingBase.swingProgressInt >= ((IEntityLivingBase)entityLivingBase).getArmSwingAnimationEnd() / 2 || entityLivingBase.swingProgressInt < 0) {
            entityLivingBase.swingProgressInt = -1;
            entityLivingBase.isSwingInProgress = true;
            entityLivingBase.swingingHand = swingingHand;
        }
    }
    
    public static boolean isCrystalAtFeet(final EntityEnderCrystal entityEnderCrystal, final double n) {
        for (final EntityPlayer entityPlayer : EntityUtil.mc.world.playerEntities) {
            if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer) <= n * n) {
                if (OyVey.friendManager.isFriend(entityPlayer)) {
                    continue;
                }
                for (final Vec3d vec3d : EntityUtil.doubleLegOffsetList) {
                    if (new BlockPos(entityPlayer.getPositionVector()).add(vec3d.x, vec3d.y, vec3d.z) == entityEnderCrystal.getPosition()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    
    public static double getMaxSpeed() {
        double n = 0.2873;
        if (EntityUtil.mc.player.isPotionActive((Potion)Objects.requireNonNull(Potion.getPotionById(1)))) {
            n *= 1.0 + 0.2 * (Objects.requireNonNull(EntityUtil.mc.player.getActivePotionEffect((Potion)Objects.requireNonNull(Potion.getPotionById(1)))).getAmplifier() + 1);
        }
        return n;
    }
    
    public static boolean getSurroundWeakness(final Vec3d vec3d, final int n, final int n2) {
        switch (n) {
            case 1: {
                final BlockPos blockPos = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)(blockPos.getX() - 2), (double)blockPos.getY(), (double)blockPos.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos.getX() - 2), (double)blockPos.getY(), (double)blockPos.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (getBlock == Blocks.AIR || getBlock == Blocks.FIRE) {
                    final Block getBlock2 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock();
                    if (getBlock2 == Blocks.AIR || getBlock2 == Blocks.FIRE) {
                        final Block getBlock3 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock();
                        if ((getBlock3 == Blocks.OBSIDIAN || getBlock3 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 2: {
                final BlockPos blockPos2 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)(blockPos2.getX() + 2), (double)blockPos2.getY(), (double)blockPos2.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos2.getX() + 2), (double)blockPos2.getY(), (double)blockPos2.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock4 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (getBlock4 == Blocks.AIR || getBlock4 == Blocks.FIRE) {
                    final Block getBlock5 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock();
                    if (getBlock5 == Blocks.AIR || getBlock5 == Blocks.FIRE) {
                        final Block getBlock6 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock();
                        if ((getBlock6 == Blocks.OBSIDIAN || getBlock6 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                final BlockPos blockPos3 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)blockPos3.getX(), (double)blockPos3.getY(), (double)(blockPos3.getZ() - 2)) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos3.getX(), (double)blockPos3.getY(), (double)(blockPos3.getZ() - 2))) > 3.0) {
                    return false;
                }
                final Block getBlock7 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (getBlock7 == Blocks.AIR || getBlock7 == Blocks.FIRE) {
                    final Block getBlock8 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock();
                    if (getBlock8 == Blocks.AIR || getBlock8 == Blocks.FIRE) {
                        final Block getBlock9 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock();
                        if ((getBlock9 == Blocks.OBSIDIAN || getBlock9 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 4: {
                final BlockPos blockPos4 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)blockPos4.getX(), (double)blockPos4.getY(), (double)(blockPos4.getZ() + 2)) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos4.getX(), (double)blockPos4.getY(), (double)(blockPos4.getZ() + 2))) > 3.0) {
                    return false;
                }
                final Block getBlock10 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (getBlock10 == Blocks.AIR || getBlock10 == Blocks.FIRE) {
                    final Block getBlock11 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock();
                    if (getBlock11 == Blocks.AIR || getBlock11 == Blocks.FIRE) {
                        final Block getBlock12 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock();
                        if ((getBlock12 == Blocks.OBSIDIAN || getBlock12 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 5: {
                final BlockPos blockPos5 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)(blockPos5.getX() - 1), (double)blockPos5.getY(), (double)blockPos5.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos5.getX() - 1), (double)blockPos5.getY(), (double)blockPos5.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock13 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if ((getBlock13 == Blocks.AIR || getBlock13 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 6: {
                final BlockPos blockPos6 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)(blockPos6.getX() + 1), (double)blockPos6.getY(), (double)blockPos6.getZ()) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)(blockPos6.getX() + 1), (double)blockPos6.getY(), (double)blockPos6.getZ())) > 3.0) {
                    return false;
                }
                final Block getBlock14 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if ((getBlock14 == Blocks.AIR || getBlock14 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 7: {
                final BlockPos blockPos7 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)blockPos7.getX(), (double)blockPos7.getY(), (double)(blockPos7.getZ() - 1)) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos7.getX(), (double)blockPos7.getY(), (double)(blockPos7.getZ() - 1))) > 3.0) {
                    return false;
                }
                final Block getBlock15 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if ((getBlock15 == Blocks.AIR || getBlock15 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 8: {
                final BlockPos blockPos8 = new BlockPos(vec3d);
                if (!BlockUtil.canBlockBeSeen((double)blockPos8.getX(), (double)blockPos8.getY(), (double)(blockPos8.getZ() + 1)) && Math.sqrt(EntityUtil.mc.player.getDistanceSq((double)blockPos8.getX(), (double)blockPos8.getY(), (double)(blockPos8.getZ() + 1))) > 3.0) {
                    return false;
                }
                final Block getBlock16 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if ((getBlock16 == Blocks.AIR || getBlock16 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
        }
        switch (n2) {
            case 1: {
                final Block getBlock17 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 1, 0)).getBlock();
                if (getBlock17 == Blocks.AIR || getBlock17 == Blocks.FIRE) {
                    final Block getBlock18 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, 0, 0)).getBlock();
                    if (getBlock18 == Blocks.AIR || getBlock18 == Blocks.FIRE) {
                        final Block getBlock19 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-2, -1, 0)).getBlock();
                        if ((getBlock19 == Blocks.OBSIDIAN || getBlock19 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 2: {
                final Block getBlock20 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 1, 0)).getBlock();
                if (getBlock20 == Blocks.AIR || getBlock20 == Blocks.FIRE) {
                    final Block getBlock21 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, 0, 0)).getBlock();
                    if (getBlock21 == Blocks.AIR || getBlock21 == Blocks.FIRE) {
                        final Block getBlock22 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(2, -1, 0)).getBlock();
                        if ((getBlock22 == Blocks.OBSIDIAN || getBlock22 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 3: {
                final Block getBlock23 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -2)).getBlock();
                if (getBlock23 == Blocks.AIR || getBlock23 == Blocks.FIRE) {
                    final Block getBlock24 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -2)).getBlock();
                    if (getBlock24 == Blocks.AIR || getBlock24 == Blocks.FIRE) {
                        final Block getBlock25 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, -2)).getBlock();
                        if ((getBlock25 == Blocks.OBSIDIAN || getBlock25 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 4: {
                final Block getBlock26 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 2)).getBlock();
                if (getBlock26 == Blocks.AIR || getBlock26 == Blocks.FIRE) {
                    final Block getBlock27 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 2)).getBlock();
                    if (getBlock27 == Blocks.AIR || getBlock27 == Blocks.FIRE) {
                        final Block getBlock28 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, -1, 2)).getBlock();
                        if ((getBlock28 == Blocks.OBSIDIAN || getBlock28 == Blocks.BEDROCK) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                            return true;
                        }
                    }
                    break;
                }
                break;
            }
            case 5: {
                final Block getBlock29 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 1, 0)).getBlock();
                if ((getBlock29 == Blocks.AIR || getBlock29 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(-1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 6: {
                final Block getBlock30 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 1, 0)).getBlock();
                if ((getBlock30 == Blocks.AIR || getBlock30 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(1, 0, 0)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 7: {
                final Block getBlock31 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, -1)).getBlock();
                if ((getBlock31 == Blocks.AIR || getBlock31 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, -1)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
            case 8: {
                final Block getBlock32 = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 1, 1)).getBlock();
                if ((getBlock32 == Blocks.AIR || getBlock32 == Blocks.FIRE) && EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(0, 0, 1)).getBlock() != Blocks.BEDROCK) {
                    return true;
                }
                break;
            }
        }
        return false;
    }
    
    public static List<Vec3d> targets(final Vec3d vec3d, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        final ArrayList<Vec3d> c = new ArrayList<Vec3d>();
        if (b5) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiDropOffsetList));
        }
        if (b4) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.platformOffsetList));
        }
        if (b3) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.legOffsetList));
        }
        Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.OffsetList));
        if (b2) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiStepOffsetList));
        }
        else {
            final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, 2, false);
            if (unsafeBlocksFromVec3d.size() == 4) {
                for (final Vec3d vec3d2 : unsafeBlocksFromVec3d) {
                    switch (BlockUtil.isPositionPlaceable(new BlockPos(vec3d).add(vec3d2.x, vec3d2.y, vec3d2.z), b6)) {
                        case -1:
                        case 1:
                        case 2: {
                            continue;
                        }
                        case 3: {
                            c.add(vec3d.add(vec3d2));
                            break;
                        }
                    }
                    if (b) {
                        Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiScaffoldOffsetList));
                    }
                    return c;
                }
            }
        }
        if (b) {
            Collections.addAll(c, BlockUtil.convertVec3ds(vec3d, EntityUtil.antiScaffoldOffsetList));
        }
        return c;
    }
    
    public static BlockPos getPlayerPos(final EntityPlayer entityPlayer) {
        return new BlockPos(Math.floor(entityPlayer.posX), Math.floor(entityPlayer.posY), Math.floor(entityPlayer.posZ));
    }
    
    public static List<Vec3d> getUnsafeBlocksFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (final Vec3d e : getOffsets(n, b)) {
            final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(vec3d).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static boolean isPassive(final Entity entity) {
        return (!(entity instanceof EntityWolf) || !((EntityWolf)entity).isAngry()) && (entity instanceof EntityAgeable || entity instanceof EntityAmbientCreature || entity instanceof EntitySquid || (entity instanceof EntityIronGolem && ((EntityIronGolem)entity).getRevengeTarget() == null));
    }
    
    public static boolean isBedrockHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.BEDROCK) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean isInHole(final Entity entity) {
        return isBlockValid(new BlockPos(entity.posX, entity.posY, entity.posZ));
    }
    
    public static boolean basicChecksEntity(final Entity entity) {
        return entity.getName().equals(EntityUtil.mc.player.getName()) || entity.isDead;
    }
    
    public static boolean isSafe(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocks(entity, n, b).size() == 0;
    }
    
    public static boolean canEntityFeetBeSeen(final Entity entity) {
        return EntityUtil.mc.world.rayTraceBlocks(new Vec3d(EntityUtil.mc.player.posX, EntityUtil.mc.player.posX + EntityUtil.mc.player.getEyeHeight(), EntityUtil.mc.player.posZ), new Vec3d(entity.posX, entity.posY, entity.posZ), false, true, false) == null;
    }
    
    public static boolean isTrappedExtended(final int n, final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        return getUntrappedBlocksExtended(n, entityPlayer, b, b2, b3, b4, b5, b6).size() == 0;
    }
    
    public static boolean isAboveWater(final Entity entity, final boolean b) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY - (b ? 0.03 : (isPlayer(entity) ? 0.2 : 0.5));
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil.mc.world.getBlockState(new BlockPos(i, MathHelper.floor(n), j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static boolean isHostileMob(final Entity entity) {
        return entity.isCreatureType(EnumCreatureType.MONSTER, false) && !isNeutralMob(entity);
    }
    
    public static boolean isLiving(final Entity entity) {
        return entity instanceof EntityLivingBase;
    }
    
    public static boolean isDead(final Entity entity) {
        return !isAlive(entity);
    }
    
    public static boolean isAboveWater(final Entity entity) {
        return isAboveWater(entity, false);
    }
    
    public static boolean checkCollide() {
        return !EntityUtil.mc.player.isSneaking() && (EntityUtil.mc.player.getRidingEntity() == null || EntityUtil.mc.player.getRidingEntity().fallDistance < 3.0f) && EntityUtil.mc.player.fallDistance < 3.0f;
    }
    
    public static boolean isValid(final Entity entity, final double n) {
        return !isntValid(entity, n);
    }
    
    public static BlockPos getPlayerPosWithEntity() {
        return new BlockPos((EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posX : EntityUtil.mc.player.posX, (EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posY : EntityUtil.mc.player.posY, (EntityUtil.mc.player.getRidingEntity() != null) ? EntityUtil.mc.player.getRidingEntity().posZ : EntityUtil.mc.player.posZ);
    }
    
    public static BlockPos getRoundedBlockPos(final Entity entity) {
        return new BlockPos(MathUtil.roundVec(entity.getPositionVector(), 0));
    }
    
    public static List<Vec3d> getVarOffsetList(final int n, final int n2, final int n3) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d((double)n, (double)n2, (double)n3));
        return list;
    }
    
    public static Map<String, Integer> getTextRadarPlayers() {
        Map<String, Integer> sortByValue = new HashMap<String, Integer>();
        final DecimalFormat decimalFormat = new DecimalFormat("#.#");
        decimalFormat.setRoundingMode(RoundingMode.CEILING);
        final DecimalFormat decimalFormat2 = new DecimalFormat("#.#");
        decimalFormat2.setRoundingMode(RoundingMode.CEILING);
        final StringBuilder obj = new StringBuilder();
        final StringBuilder obj2 = new StringBuilder();
        for (final EntityPlayer entityPlayer : EntityUtil.mc.world.playerEntities) {
            if (!entityPlayer.isInvisible()) {
                if (entityPlayer.getName().equals(EntityUtil.mc.player.getName())) {
                    continue;
                }
                final int n = (int)getHealth((Entity)entityPlayer);
                final String format = decimalFormat.format(n);
                obj.append("\u00c2§");
                if (n >= 20) {
                    obj.append("a");
                }
                else if (n >= 10) {
                    obj.append("e");
                }
                else if (n >= 5) {
                    obj.append("6");
                }
                else {
                    obj.append("c");
                }
                obj.append(format);
                final int n2 = (int)EntityUtil.mc.player.getDistance((Entity)entityPlayer);
                final String format2 = decimalFormat2.format(n2);
                obj2.append("\u00c2§");
                if (n2 >= 25) {
                    obj2.append("a");
                }
                else if (n2 > 10) {
                    obj2.append("6");
                }
                else {
                    obj2.append("c");
                }
                obj2.append(format2);
                sortByValue.put(String.valueOf(new StringBuilder().append((Object)obj).append(" ").append(OyVey.friendManager.isFriend(entityPlayer) ? ChatFormatting.AQUA : ChatFormatting.RED).append(entityPlayer.getName()).append(" ").append((Object)obj2).append(" \u00c2§f0")), (int)EntityUtil.mc.player.getDistance((Entity)entityPlayer));
                obj.setLength(0);
                obj2.setLength(0);
            }
        }
        if (!sortByValue.isEmpty()) {
            sortByValue = MathUtil.sortByValue(sortByValue, false);
        }
        return sortByValue;
    }
    
    public static boolean stopSneaking(final boolean b) {
        if (b && EntityUtil.mc.player != null) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketEntityAction((Entity)EntityUtil.mc.player, CPacketEntityAction.Action.STOP_SNEAKING));
        }
        return false;
    }
    
    public static boolean isEntityMoving(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (entity instanceof EntityPlayer) {
            return EntityUtil.mc.gameSettings.keyBindForward.isKeyDown() || EntityUtil.mc.gameSettings.keyBindBack.isKeyDown() || EntityUtil.mc.gameSettings.keyBindLeft.isKeyDown() || EntityUtil.mc.gameSettings.keyBindRight.isKeyDown();
        }
        return entity.motionX != 0.0 || entity.motionY != 0.0 || entity.motionZ != 0.0;
    }
    
    public static boolean isSafe(final Entity entity) {
        return isSafe(entity, 0, false);
    }
    
    public static boolean isPlayer(final Entity entity) {
        return entity instanceof EntityPlayer;
    }
    
    public static Vec3d[] getUnsafeBlockArray(final Entity entity, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocks = getUnsafeBlocks(entity, n, b);
        return unsafeBlocks.toArray(new Vec3d[unsafeBlocks.size()]);
    }
    
    public static Vec3d getInterpolatedPos(final Entity entity, final float n) {
        return new Vec3d(entity.lastTickPosX, entity.lastTickPosY, entity.lastTickPosZ).add(getInterpolatedAmount(entity, n));
    }
    
    public static List<Vec3d> getOffsetList(final int n, final boolean b) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        list.add(new Vec3d(-1.0, (double)n, 0.0));
        list.add(new Vec3d(1.0, (double)n, 0.0));
        list.add(new Vec3d(0.0, (double)n, -1.0));
        list.add(new Vec3d(0.0, (double)n, 1.0));
        if (b) {
            list.add(new Vec3d(0.0, (double)(n - 1), 0.0));
        }
        return list;
    }
    
    public static boolean isBothHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || (getBlockState.getBlock() != Blocks.BEDROCK && getBlockState.getBlock() != Blocks.OBSIDIAN)) {
                return false;
            }
        }
        return true;
    }
    
    public static boolean is32k(final ItemStack itemStack) {
        return EnchantmentHelper.getEnchantmentLevel(Enchantments.SHARPNESS, itemStack) >= 1000;
    }
    
    public static void attackEntity(final Entity entity, final boolean b, final EnumHand enumHand) {
        if (b) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, entity);
        }
        EntityUtil.mc.player.swingArm(enumHand);
    }
    
    public static void mutliplyEntitySpeed(final Entity entity, final double n) {
        if (entity != null) {
            entity.motionX *= n;
            entity.motionZ *= n;
        }
    }
    
    public static void setTimer(final float n) {
        Minecraft.getMinecraft().timer.tickLength = 50.0f / n;
    }
    
    public static boolean isObbyHole(final BlockPos blockPos) {
        final BlockPos[] array = { blockPos.north(), blockPos.south(), blockPos.east(), blockPos.west(), blockPos.down() };
        for (int length = array.length, i = 0; i < length; ++i) {
            final IBlockState getBlockState = EntityUtil.mc.world.getBlockState(array[i]);
            if (getBlockState.getBlock() == Blocks.AIR || getBlockState.getBlock() != Blocks.OBSIDIAN) {
                return false;
            }
        }
        return true;
    }
    
    public static void attackEntity(final Entity entity, final boolean b, final boolean b2) {
        if (b) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, entity);
        }
        if (b2) {
            EntityUtil.mc.player.swingArm(EnumHand.MAIN_HAND);
        }
    }
    
    public static boolean isNeutralMob(final Entity entity) {
        return entity instanceof EntityPigZombie || entity instanceof EntityWolf || entity instanceof EntityEnderman;
    }
    
    public static Vec3d[] getVarOffsets(final int n, final int n2, final int n3) {
        final List<Vec3d> varOffsetList = getVarOffsetList(n, n2, n3);
        return varOffsetList.toArray(new Vec3d[varOffsetList.size()]);
    }
    
    public static boolean isFriendlyMob(final Entity entity) {
        return (entity.isCreatureType(EnumCreatureType.CREATURE, false) && !isNeutralMob(entity)) || entity.isCreatureType(EnumCreatureType.AMBIENT, false) || entity instanceof EntityVillager || entity instanceof EntityIronGolem || (isNeutralMob(entity) && !isMobAggressive(entity));
    }
    
    public static boolean isMoving() {
        return EntityUtil.mc.player.moveForward != 0.0 || EntityUtil.mc.player.moveStrafing != 0.0;
    }
    
    public static double[] calculateLookAt(final double n, final double n2, final double n3, final EntityPlayer entityPlayer) {
        final double n4 = entityPlayer.posX - n;
        final double n5 = entityPlayer.posY - n2;
        final double n6 = entityPlayer.posZ - n3;
        final double sqrt = Math.sqrt(n4 * n4 + n5 * n5 + n6 * n6);
        return new double[] { Math.atan2(n6 / sqrt, n4 / sqrt) * 180.0 / 3.141592653589793 + 90.0, Math.asin(n5 / sqrt) * 180.0 / 3.141592653589793 };
    }
    
    public static List<Vec3d> getTrapOffsetsList(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>(getOffsetList(1, false));
        list.add(new Vec3d(0.0, 2.0, 0.0));
        if (b) {
            list.add(new Vec3d(0.0, 3.0, 0.0));
        }
        if (b2) {
            list.addAll(getOffsetList(2, false));
        }
        if (b3) {
            list.addAll(getOffsetList(0, false));
        }
        if (b4) {
            list.addAll(getOffsetList(-1, false));
            list.add(new Vec3d(0.0, -1.0, 0.0));
        }
        if (b5) {
            list.add(new Vec3d(0.0, -2.0, 0.0));
        }
        return list;
    }
    
    public static List<Vec3d> getUnsafeBlocks(final Entity entity, final int n, final boolean b) {
        return getUnsafeBlocksFromVec3d(entity.getPositionVector(), n, b);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final float n) {
        return getInterpolatedAmount(entity, n, n, n);
    }
    
    public static boolean isAlive(final Entity entity) {
        return isLiving(entity) && !entity.isDead && ((EntityLivingBase)entity).getHealth() > 0.0f;
    }
    
    public static Vec3d[] getOffsets(final int n, final boolean b) {
        final List<Vec3d> offsetList = getOffsetList(n, b);
        return offsetList.toArray(new Vec3d[offsetList.size()]);
    }
    
    public static float getHealth(final Entity entity) {
        if (isLiving(entity)) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + entityLivingBase.getAbsorptionAmount();
        }
        return 0.0f;
    }
    
    public static void moveEntityStrafe(final double n, final Entity entity) {
        if (entity != null) {
            final MovementInput movementInput = EntityUtil.mc.player.movementInput;
            double n2 = movementInput.moveForward;
            double n3 = movementInput.moveStrafe;
            float rotationYaw = EntityUtil.mc.player.rotationYaw;
            if (n2 == 0.0 && n3 == 0.0) {
                entity.motionX = 0.0;
                entity.motionZ = 0.0;
            }
            else {
                if (n2 != 0.0) {
                    if (n3 > 0.0) {
                        rotationYaw += ((n2 > 0.0) ? -45 : 45);
                    }
                    else if (n3 < 0.0) {
                        rotationYaw += ((n2 > 0.0) ? 45 : -45);
                    }
                    n3 = 0.0;
                    if (n2 > 0.0) {
                        n2 = 1.0;
                    }
                    else if (n2 < 0.0) {
                        n2 = -1.0;
                    }
                }
                entity.motionX = n2 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f)) + n3 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f));
                entity.motionZ = n2 * n * Math.sin(Math.toRadians(rotationYaw + 90.0f)) - n3 * n * Math.cos(Math.toRadians(rotationYaw + 90.0f));
            }
        }
    }
    
    public static boolean isntValid(final Entity entity, final double n) {
        return entity == null || isDead(entity) || entity.equals((Object)EntityUtil.mc.player) || (entity instanceof EntityPlayer && OyVey.friendManager.isFriend(entity.getName())) || EntityUtil.mc.player.getDistanceSq(entity) > MathUtil.square(n);
    }
    
    public static Vec3d[] getTrapOffsets(final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final List<Vec3d> trapOffsetsList = getTrapOffsetsList(b, b2, b3, b4, b5);
        return trapOffsetsList.toArray(new Vec3d[trapOffsetsList.size()]);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Entity entity, final float n) {
        return getInterpolatedPos(entity, n).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
    
    static {
        antiDropOffsetList = new Vec3d[] { new Vec3d(0.0, -2.0, 0.0) };
        platformOffsetList = new Vec3d[] { new Vec3d(0.0, -1.0, 0.0), new Vec3d(0.0, -1.0, -1.0), new Vec3d(0.0, -1.0, 1.0), new Vec3d(-1.0, -1.0, 0.0), new Vec3d(1.0, -1.0, 0.0) };
        legOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0) };
        OffsetList = new Vec3d[] { new Vec3d(1.0, 1.0, 0.0), new Vec3d(-1.0, 1.0, 0.0), new Vec3d(0.0, 1.0, 1.0), new Vec3d(0.0, 1.0, -1.0), new Vec3d(0.0, 2.0, 0.0) };
        antiStepOffsetList = new Vec3d[] { new Vec3d(-1.0, 2.0, 0.0), new Vec3d(1.0, 2.0, 0.0), new Vec3d(0.0, 2.0, 1.0), new Vec3d(0.0, 2.0, -1.0) };
        doubleLegOffsetList = new Vec3d[] { new Vec3d(-1.0, 0.0, 0.0), new Vec3d(1.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -1.0), new Vec3d(0.0, 0.0, 1.0), new Vec3d(-2.0, 0.0, 0.0), new Vec3d(2.0, 0.0, 0.0), new Vec3d(0.0, 0.0, -2.0), new Vec3d(0.0, 0.0, 2.0) };
        antiScaffoldOffsetList = new Vec3d[] { new Vec3d(0.0, 3.0, 0.0) };
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final Vec3d vec3d) {
        return getInterpolatedAmount(entity, vec3d.x, vec3d.y, vec3d.z);
    }
    
    public static Vec3d getInterpolatedAmount(final Entity entity, final double n, final double n2, final double n3) {
        return new Vec3d((entity.posX - entity.lastTickPosX) * n, (entity.posY - entity.lastTickPosY) * n2, (entity.posZ - entity.lastTickPosZ) * n3);
    }
    
    public static double getDst(final Vec3d vec3d) {
        return EntityUtil.mc.player.getPositionVector().distanceTo(vec3d);
    }
    
    public static List<Vec3d> getUntrappedBlocksExtended(final int n, final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5, final boolean b6) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (n == 1) {
            list.addAll(targets(entityPlayer.getPositionVector(), b, b2, b3, b4, b5, b6));
        }
        else {
            int n2 = 1;
            for (final Vec3d vec3d : MathUtil.getBlockBlocks((Entity)entityPlayer)) {
                if (n2 > n) {
                    break;
                }
                list.addAll(targets(vec3d, b, b2, b3, b4, b5, b6));
                ++n2;
            }
        }
        final ArrayList<Vec3d> list2 = new ArrayList<Vec3d>();
        for (final Vec3d e : list) {
            if (BlockUtil.isPositionPlaceable(new BlockPos(e), b6) != -1) {
                continue;
            }
            list2.add(e);
        }
        final Iterator<Vec3d> iterator3 = list2.iterator();
        while (iterator3.hasNext()) {
            list.remove(iterator3.next());
        }
        return list;
    }
    
    public static boolean isTrapped(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        return getUntrappedBlocks(entityPlayer, b, b2, b3, b4, b5).size() == 0;
    }
    
    public static double[] forward(final double n) {
        float moveForward = EntityUtil.mc.player.movementInput.moveForward;
        float moveStrafe = EntityUtil.mc.player.movementInput.moveStrafe;
        float n2 = EntityUtil.mc.player.prevRotationYaw + (EntityUtil.mc.player.rotationYaw - EntityUtil.mc.player.prevRotationYaw) * EntityUtil.mc.getRenderPartialTicks();
        if (moveForward != 0.0f) {
            if (moveStrafe > 0.0f) {
                n2 += ((moveForward > 0.0f) ? -45 : 45);
            }
            else if (moveStrafe < 0.0f) {
                n2 += ((moveForward > 0.0f) ? 45 : -45);
            }
            moveStrafe = 0.0f;
            if (moveForward > 0.0f) {
                moveForward = 1.0f;
            }
            else if (moveForward < 0.0f) {
                moveForward = -1.0f;
            }
        }
        final double sin = Math.sin(Math.toRadians(n2 + 90.0f));
        final double cos = Math.cos(Math.toRadians(n2 + 90.0f));
        return new double[] { moveForward * n * cos + moveStrafe * n * sin, moveForward * n * sin - moveStrafe * n * cos };
    }
    
    public static EntityPlayer getClosestEnemy(final double n) {
        Entity entity = null;
        for (final EntityPlayer entityPlayer : EntityUtil.mc.world.playerEntities) {
            if (isntValid((Entity)entityPlayer, n)) {
                continue;
            }
            if (entity == null) {
                entity = (Entity)entityPlayer;
            }
            else {
                if (EntityUtil.mc.player.getDistanceSq((Entity)entityPlayer) >= EntityUtil.mc.player.getDistanceSq(entity)) {
                    continue;
                }
                entity = (Entity)entityPlayer;
            }
        }
        return (EntityPlayer)entity;
    }
    
    public static Color getColor(final Entity entity, final int n, final int n2, final int n3, final int n4, final boolean b) {
        Color color = new Color(n / 255.0f, n2 / 255.0f, n3 / 255.0f, n4 / 255.0f);
        if (entity instanceof EntityPlayer && b && OyVey.friendManager.isFriend((EntityPlayer)entity)) {
            color = new Color(0.33333334f, 1.0f, 1.0f, n4 / 255.0f);
        }
        return color;
    }
    
    public static boolean isVehicle(final Entity entity) {
        return entity instanceof EntityBoat || entity instanceof EntityMinecart;
    }
    
    public static boolean isInWater(final Entity entity) {
        if (entity == null) {
            return false;
        }
        final double n = entity.posY + 0.01;
        for (int i = MathHelper.floor(entity.posX); i < MathHelper.ceil(entity.posX); ++i) {
            for (int j = MathHelper.floor(entity.posZ); j < MathHelper.ceil(entity.posZ); ++j) {
                if (EntityUtil.mc.world.getBlockState(new BlockPos(i, (int)n, j)).getBlock() instanceof BlockLiquid) {
                    return true;
                }
            }
        }
        return false;
    }
    
    public static void resetTimer() {
        Minecraft.getMinecraft().timer.tickLength = 50.0f;
    }
    
    public static boolean isDrivenByPlayer(final Entity entity) {
        return EntityUtil.mc.player != null && entity != null && entity.equals((Object)EntityUtil.mc.player.getRidingEntity());
    }
    
    public static double getEntitySpeed(final Entity entity) {
        if (entity != null) {
            final double n = entity.posX - entity.prevPosX;
            final double n2 = entity.posZ - entity.prevPosZ;
            return MathHelper.sqrt(n * n + n2 * n2) * 20.0;
        }
        return 0.0;
    }
    
    public static Vec3d[] getUnsafeBlockArrayFromVec3d(final Vec3d vec3d, final int n, final boolean b) {
        final List<Vec3d> unsafeBlocksFromVec3d = getUnsafeBlocksFromVec3d(vec3d, n, b);
        return unsafeBlocksFromVec3d.toArray(new Vec3d[unsafeBlocksFromVec3d.size()]);
    }
    
    public static Vec3d[] getHeightOffsets(final int n, final int n2) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        for (int i = n; i <= n2; ++i) {
            list.add(new Vec3d(0.0, (double)i, 0.0));
        }
        return list.toArray(new Vec3d[list.size()]);
    }
    
    public static boolean isFakePlayer(final EntityPlayer entityPlayer) {
        FakePlayer.getInstance();
        return true;
    }
    
    public static boolean isProjectile(final Entity entity) {
        return entity instanceof EntityShulkerBullet || entity instanceof EntityFireball;
    }
    
    public static void attackEntity(final Entity entity, final boolean b) {
        if (b) {
            EntityUtil.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity));
        }
        else {
            EntityUtil.mc.playerController.attackEntity((EntityPlayer)EntityUtil.mc.player, entity);
        }
    }
    
    public static boolean rayTraceHitCheck(final Entity entity, final boolean b) {
        return !b || EntityUtil.mc.player.canEntityBeSeen(entity);
    }
    
    public static Vec3d getInterpolatedRenderPos(final Vec3d vec3d) {
        return new Vec3d(vec3d.x, vec3d.y, vec3d.z).subtract(EntityUtil.mc.getRenderManager().renderPosX, EntityUtil.mc.getRenderManager().renderPosY, EntityUtil.mc.getRenderManager().renderPosZ);
    }
    
    public static float getHealth(final Entity entity, final boolean b) {
        if (isLiving(entity)) {
            final EntityLivingBase entityLivingBase = (EntityLivingBase)entity;
            return entityLivingBase.getHealth() + (b ? entityLivingBase.getAbsorptionAmount() : 0.0f);
        }
        return 0.0f;
    }
    
    public static List<Vec3d> getUntrappedBlocks(final EntityPlayer entityPlayer, final boolean b, final boolean b2, final boolean b3, final boolean b4, final boolean b5) {
        final ArrayList<Vec3d> list = new ArrayList<Vec3d>();
        if (!b2 && getUnsafeBlocks((Entity)entityPlayer, 2, false).size() == 4) {
            list.addAll(getUnsafeBlocks((Entity)entityPlayer, 2, false));
        }
        for (int i = 0; i < getTrapOffsets(b, b2, b3, b4, b5).length; ++i) {
            final Vec3d e = getTrapOffsets(b, b2, b3, b4, b5)[i];
            final Block getBlock = EntityUtil.mc.world.getBlockState(new BlockPos(entityPlayer.getPositionVector()).add(e.x, e.y, e.z)).getBlock();
            if (getBlock instanceof BlockAir || getBlock instanceof BlockLiquid || getBlock instanceof BlockTallGrass || getBlock instanceof BlockFire || getBlock instanceof BlockDeadBush || getBlock instanceof BlockSnow) {
                list.add(e);
            }
        }
        return list;
    }
    
    public static boolean isBlockValid(final BlockPos blockPos) {
        return isBedrockHole(blockPos) || isObbyHole(blockPos) || isBothHole(blockPos);
    }
    
    public static boolean holdingWeapon(final EntityPlayer entityPlayer) {
        return entityPlayer.getHeldItemMainhand().getItem() instanceof ItemSword || entityPlayer.getHeldItemMainhand().getItem() instanceof ItemAxe;
    }
}

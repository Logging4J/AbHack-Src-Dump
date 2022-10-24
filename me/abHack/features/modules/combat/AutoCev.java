//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.block.*;
import me.abHack.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.*;
import net.minecraft.util.math.*;
import me.abHack.util.*;
import net.minecraft.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.network.*;
import net.minecraft.entity.item.*;
import me.abHack.features.command.*;
import java.util.*;

public class AutoCev extends Module
{
    /* synthetic */ int progress;
    /* synthetic */ int civCounter;
    /* synthetic */ int sleep;
    /* synthetic */ boolean breakFlag;
    private final /* synthetic */ Setting<Double> range;
    /* synthetic */ boolean flag;
    /* synthetic */ Entity currentEntity;
    
    @Override
    public void onEnable() {
        this.findTarget();
        this.progress = 0;
        this.breakFlag = false;
        this.flag = false;
        this.civCounter = 0;
        this.sleep = 0;
        super.onEnable();
    }
    
    private int findMaterials(final Block block) {
        for (int i = 0; i < 9; ++i) {
            if (AutoCev.mc.player.inventory.getStackInSlot(i).getItem() instanceof ItemBlock && ((ItemBlock)AutoCev.mc.player.inventory.getStackInSlot(i).getItem()).getBlock() == block) {
                return i;
            }
        }
        return -1;
    }
    
    private int findItem(final Item item) {
        if (item == Items.END_CRYSTAL && AutoCev.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) {
            return 999;
        }
        for (int i = 0; i < 9; ++i) {
            if (AutoCev.mc.player.inventory.getStackInSlot(i).getItem() == item) {
                return i;
            }
        }
        return -1;
    }
    
    public void findTarget() {
        this.currentEntity = (Entity)AutoCev.mc.world.loadedEntityList.stream().filter(entity -> entity != AutoCev.mc.player && entity instanceof EntityLivingBase && entity.getDistance((Entity)AutoCev.mc.player) < this.range.getValue() && !OyVey.friendManager.isFriend(entity.getName())).findFirst().orElse(null);
    }
    
    public AutoCev() {
        super("AutoCev", "AutoCev", Category.COMBAT, true, false, false);
        this.range = (Setting<Double>)this.register(new Setting("Range", (T)4.9, (T)0.0, (T)6.0));
        this.progress = 0;
    }
    
    @Override
    public void onTick() {
        final int item = this.findItem(Items.DIAMOND_PICKAXE);
        final int item2 = this.findItem(Items.END_CRYSTAL);
        final int materials = this.findMaterials(Blocks.OBSIDIAN);
        final BlockPos[] a = { new BlockPos(0, 0, 1), new BlockPos(0, 1, 1), new BlockPos(0, 2, 1), new BlockPos(0, 2, 0) };
        final int slot = InventoryUtil.getSlot();
        if (item != -1 && item2 != -1 && materials != -1) {
            if (this.currentEntity == null || this.currentEntity.getDistance((Entity)AutoCev.mc.player) > this.range.getValue()) {
                this.findTarget();
            }
            if (this.currentEntity != null) {
                if (this.currentEntity.isDead) {
                    this.disable();
                    return;
                }
                final Entity currentEntity = this.currentEntity;
                OyVey.targetManager.updateTarget((EntityLivingBase)currentEntity);
                if (currentEntity instanceof EntityPlayer && !OyVey.friendManager.isFriend(currentEntity.getName())) {
                    if (this.sleep > 0) {
                        --this.sleep;
                    }
                    else {
                        currentEntity.move(MoverType.SELF, 0.0, -2.0, 0.0);
                        switch (this.progress) {
                            case 0: {
                                final BlockPos blockPos = new BlockPos(currentEntity);
                                final BlockPos[] array = a;
                                for (int length = a.length, i = 0; i < length; ++i) {
                                    final BlockPos blockPos2 = array[i];
                                    if (Arrays.asList(a).indexOf(blockPos2) != -1 && this.civCounter < 1) {
                                        this.flag = true;
                                        InventoryUtil.setSlot(materials);
                                    }
                                    else {
                                        InventoryUtil.setSlot(materials);
                                    }
                                    final BlockUtils placeable = BlockUtils.isPlaceable(blockPos.add((Vec3i)blockPos2), 0.0, true);
                                    if (placeable != null) {
                                        placeable.doPlace(true);
                                    }
                                }
                                InventoryUtil.setSlot(item2);
                                CrystalUtil.placeCrystal(new BlockPos(currentEntity.posX, currentEntity.posY + 3.0, currentEntity.posZ));
                                ++this.progress;
                                break;
                            }
                            case 1: {
                                InventoryUtil.setSlot(item);
                                AutoCev.mc.playerController.onPlayerDamageBlock(new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP);
                                AutoCev.mc.getConnection().sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP));
                                if (AutoCev.mc.world.isAirBlock(new BlockPos(currentEntity).add(0, 2, 0))) {
                                    for (final Entity entity : AutoCev.mc.world.loadedEntityList) {
                                        if (currentEntity.getDistance(entity) <= this.range.getValue() && entity instanceof EntityEnderCrystal) {
                                            AutoCev.mc.playerController.attackEntity((EntityPlayer)AutoCev.mc.player, entity);
                                        }
                                    }
                                    this.breakFlag = true;
                                }
                                if (this.civCounter < 1) {
                                    AutoCev.mc.playerController.onPlayerDamageBlock(new BlockPos(currentEntity).add(0, 2, 0), EnumFacing.UP);
                                    this.sleep += 30;
                                }
                                ++this.progress;
                                break;
                            }
                            case 2: {
                                int n = 0;
                                for (final Entity entity2 : AutoCev.mc.world.loadedEntityList) {
                                    if (currentEntity.getDistance(entity2) <= this.range.getValue() && entity2 instanceof EntityEnderCrystal) {
                                        AutoCev.mc.playerController.attackEntity((EntityPlayer)AutoCev.mc.player, entity2);
                                        ++n;
                                    }
                                }
                                if (n == 0 || this.flag) {
                                    ++this.progress;
                                    break;
                                }
                                break;
                            }
                            case 3: {
                                BlockUtils.doPlace(BlockUtils.isPlaceable(new BlockPos(currentEntity.posX, currentEntity.posY + 2.0, currentEntity.posZ), 0.0, true), true);
                                InventoryUtil.setSlot(materials);
                                this.progress = 0;
                                ++this.civCounter;
                                break;
                            }
                        }
                    }
                    InventoryUtil.setSlot(slot);
                    return;
                }
                InventoryUtil.setSlot(slot);
            }
        }
        else {
            Command.sendMessage("Pix or Crystal or Obsidian No Material");
            this.disable();
        }
    }
    
    @Override
    public void onDisable() {
        super.onDisable();
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.block.state.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.world.*;
import me.abHack.*;
import net.minecraft.init.*;
import net.minecraft.util.math.*;
import net.minecraft.util.*;
import net.minecraftforge.event.world.*;
import net.minecraft.item.*;

public class BlockTweaks extends Module
{
    public /* synthetic */ Setting<Boolean> noFriendAttack;
    private static /* synthetic */ BlockTweaks INSTANCE;
    public /* synthetic */ Setting<Boolean> autoTool;
    private /* synthetic */ int lastHotbarSlot;
    public /* synthetic */ Setting<Boolean> noGhost;
    private /* synthetic */ boolean switched;
    private /* synthetic */ int currentTargetSlot;
    
    public void onDisable() {
        if (this.switched) {
            this.equip(this.lastHotbarSlot, false);
        }
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
    }
    
    @SubscribeEvent
    public void onBlockInteract(final PlayerInteractEvent.LeftClickBlock leftClickBlock) {
        if (this.autoTool.getValue() && (Speedmine.getInstance().mode.getValue() != Speedmine.Mode.PACKET || Speedmine.getInstance().isOff() || !Speedmine.getInstance().tweaks.getValue()) && !fullNullCheck() && leftClickBlock.getPos() != null) {
            this.equipBestTool(BlockTweaks.mc.world.getBlockState(leftClickBlock.getPos()));
        }
    }
    
    public BlockTweaks() {
        super("BlockTweaks", "Some tweaks for blocks.", Module.Category.PLAYER, true, false, false);
        this.autoTool = (Setting<Boolean>)this.register(new Setting("AutoTool", (T)false));
        this.noFriendAttack = (Setting<Boolean>)this.register(new Setting("NoFriendAttack", (T)false));
        this.noGhost = (Setting<Boolean>)this.register(new Setting("NoGlitchBlocks", (T)false));
        this.lastHotbarSlot = -1;
        this.currentTargetSlot = -1;
        this.switched = false;
        this.setInstance();
    }
    
    static {
        BlockTweaks.INSTANCE = new BlockTweaks();
    }
    
    public void equipBestWeapon(final Entity entity) {
        int n = -1;
        double n2 = 0.0;
        EnumCreatureAttribute enumCreatureAttribute = EnumCreatureAttribute.UNDEFINED;
        if (EntityUtil.isLiving(entity)) {
            enumCreatureAttribute = ((EntityLivingBase)entity).getCreatureAttribute();
        }
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            if (!getStackInSlot.isEmpty) {
                if (getStackInSlot.getItem() instanceof ItemTool) {
                    final double n3 = ((ItemTool)getStackInSlot.getItem()).attackDamage + EnchantmentHelper.getModifierForCreature(getStackInSlot, enumCreatureAttribute);
                    if (n3 > n2) {
                        n2 = n3;
                        n = i;
                    }
                }
                else {
                    final double n4;
                    if (getStackInSlot.getItem() instanceof ItemSword && (n4 = ((ItemSword)getStackInSlot.getItem()).getAttackDamage() + EnchantmentHelper.getModifierForCreature(getStackInSlot, enumCreatureAttribute)) > n2) {
                        n2 = n4;
                        n = i;
                    }
                }
            }
        }
        this.equip(n, true);
    }
    
    private void equipBestTool(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = BlockTweaks.mc.player.inventory.getStackInSlot(i);
            final float getDestroySpeed;
            final int getEnchantmentLevel;
            final float n3;
            if (!getStackInSlot.isEmpty && (getDestroySpeed = getStackInSlot.getDestroySpeed(blockState)) > 1.0f && (n3 = (float)(getDestroySpeed + (((getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot)) > 0) ? (Math.pow(getEnchantmentLevel, 2.0) + 1.0) : 0.0))) > n2) {
                n2 = n3;
                n = i;
            }
        }
        this.equip(n, true);
    }
    
    public static BlockTweaks getINSTANCE() {
        if (BlockTweaks.INSTANCE == null) {
            BlockTweaks.INSTANCE = new BlockTweaks();
        }
        return BlockTweaks.INSTANCE;
    }
    
    private void setInstance() {
        BlockTweaks.INSTANCE = this;
    }
    
    private void equip(final int n, final boolean switched) {
        if (n != -1) {
            if (n != BlockTweaks.mc.player.inventory.currentItem) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            this.currentTargetSlot = n;
            BlockTweaks.mc.player.inventory.currentItem = n;
            this.switched = switched;
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        final Entity getEntityFromWorld;
        if (this.noFriendAttack.getValue() && send.getPacket() instanceof CPacketUseEntity && (getEntityFromWorld = ((CPacketUseEntity)send.getPacket()).getEntityFromWorld((World)BlockTweaks.mc.world)) != null && OyVey.friendManager.isFriend(getEntityFromWorld.getName())) {
            send.setCanceled(true);
        }
    }
    
    private void removeGlitchBlocks(final BlockPos blockPos) {
        for (int i = -4; i <= 4; ++i) {
            for (int j = -4; j <= 4; ++j) {
                for (int k = -4; k <= 4; ++k) {
                    final BlockPos blockPos2 = new BlockPos(blockPos.getX() + i, blockPos.getY() + j, blockPos.getZ() + k);
                    if (BlockTweaks.mc.world.getBlockState(blockPos2).getBlock().equals(Blocks.AIR)) {
                        BlockTweaks.mc.playerController.processRightClickBlock(BlockTweaks.mc.player, BlockTweaks.mc.world, blockPos2, EnumFacing.DOWN, new Vec3d(0.5, 0.5, 0.5), EnumHand.MAIN_HAND);
                    }
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onBreak(final BlockEvent.BreakEvent breakEvent) {
        if (fullNullCheck() || !this.noGhost.getValue()) {
            return;
        }
        if (!(BlockTweaks.mc.player.getHeldItemMainhand().getItem() instanceof ItemBlock)) {
            this.removeGlitchBlocks(BlockTweaks.mc.player.getPosition());
        }
    }
    
    public void onUpdate() {
        if (!fullNullCheck()) {
            if (BlockTweaks.mc.player.inventory.currentItem != this.lastHotbarSlot && BlockTweaks.mc.player.inventory.currentItem != this.currentTargetSlot) {
                this.lastHotbarSlot = BlockTweaks.mc.player.inventory.currentItem;
            }
            if (!BlockTweaks.mc.gameSettings.keyBindAttack.isKeyDown() && this.switched) {
                this.equip(this.lastHotbarSlot, false);
            }
        }
    }
}

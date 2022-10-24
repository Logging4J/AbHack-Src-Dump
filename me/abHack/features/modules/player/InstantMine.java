//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import net.minecraftforge.client.event.*;
import net.minecraft.world.*;
import net.minecraft.util.math.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.stream.*;
import net.minecraft.network.*;
import net.minecraft.util.*;
import java.util.*;
import net.minecraft.network.play.client.*;
import net.minecraft.block.*;
import me.abHack.util.*;
import me.abHack.features.modules.combat.*;
import net.minecraft.inventory.*;
import net.minecraft.entity.player.*;
import net.minecraft.block.state.*;
import net.minecraft.init.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import me.abHack.event.events.*;

public class InstantMine extends Module
{
    private final /* synthetic */ Setting<Boolean> rainbow;
    private final /* synthetic */ Setting<Boolean> instant;
    private final /* synthetic */ Setting<Boolean> creativeMode;
    private final /* synthetic */ Setting<Boolean> crystal;
    private final /* synthetic */ Setting<Boolean> ghostHand;
    public final /* synthetic */ Setting<Bind> bind;
    public static /* synthetic */ BlockPos breakPos2;
    private final /* synthetic */ Setting<Integer> fillAlpha;
    private final /* synthetic */ Setting<Boolean> render;
    private static /* synthetic */ InstantMine INSTANCE;
    /* synthetic */ double manxi;
    public /* synthetic */ Block block;
    private final /* synthetic */ Timer breakSuccess;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private /* synthetic */ boolean empty;
    public /* synthetic */ Setting<Mode> rendermode;
    public final /* synthetic */ Timer Rendertimer;
    private /* synthetic */ EnumFacing facing;
    private final /* synthetic */ List<Block> godBlocks;
    public static /* synthetic */ BlockPos breakPos;
    private /* synthetic */ boolean cancelStart;
    private final /* synthetic */ Setting<Boolean> doubleBreak;
    private final /* synthetic */ Setting<Boolean> superghost;
    private final /* synthetic */ Setting<Float> range;
    public final /* synthetic */ Setting<Boolean> attackcrystal;
    
    private void setInstance() {
        InstantMine.INSTANCE = this;
    }
    
    public boolean check() {
        return InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY + 2.0, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY + 3.0, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY - 1.0, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX + 1.0, InstantMine.mc.player.posY, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX - 1.0, InstantMine.mc.player.posY, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY, InstantMine.mc.player.posZ + 1.0)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY, InstantMine.mc.player.posZ - 1.0)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX + 1.0, InstantMine.mc.player.posY + 1.0, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX - 1.0, InstantMine.mc.player.posY + 1.0, InstantMine.mc.player.posZ)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY + 1.0, InstantMine.mc.player.posZ + 1.0)) || InstantMine.breakPos.equals((Object)new BlockPos(InstantMine.mc.player.posX, InstantMine.mc.player.posY + 1.0, InstantMine.mc.player.posZ - 1.0));
    }
    
    private void switchToSlot(final int currentItem) {
        InstantMine.mc.player.inventory.currentItem = currentItem;
        InstantMine.mc.playerController.updateController();
    }
    
    static {
        InstantMine.INSTANCE = new InstantMine();
    }
    
    @SubscribeEvent
    public void onRender(final RenderWorldLastEvent renderWorldLastEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (this.render.getValue() && this.creativeMode.getValue() && this.cancelStart) {
            if (this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
                this.empty = true;
            }
            float n = InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlockHardness((World)InstantMine.mc.world, InstantMine.breakPos) / 5.0f;
            if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.OBSIDIAN) {
                n = 11.0f;
            }
            if (this.Rendertimer.passedMs((int)n)) {
                if (this.manxi <= 10.0) {
                    this.manxi += 0.11;
                }
                this.Rendertimer.reset();
            }
            final AxisAlignedBB getSelectedBoundingBox = InstantMine.mc.world.getBlockState(InstantMine.breakPos).getSelectedBoundingBox((World)InstantMine.mc.world, InstantMine.breakPos);
            final double n2 = getSelectedBoundingBox.minX + (getSelectedBoundingBox.maxX - getSelectedBoundingBox.minX) / 2.0;
            final double n3 = getSelectedBoundingBox.minY + (getSelectedBoundingBox.maxY - getSelectedBoundingBox.minY) / 2.0;
            final double n4 = getSelectedBoundingBox.minZ + (getSelectedBoundingBox.maxZ - getSelectedBoundingBox.minZ) / 2.0;
            final double n5 = this.manxi * (getSelectedBoundingBox.maxX - n2) / 10.0;
            final double n6 = this.manxi * (getSelectedBoundingBox.maxY - n3) / 10.0;
            final double n7 = this.manxi * (getSelectedBoundingBox.maxZ - n4) / 10.0;
            final AxisAlignedBB axisAlignedBB = new AxisAlignedBB(n2 - n5, n3 - n6, n4 - n7, n2 + n5, n3 + n6, n4 + n7);
            final Color color = this.rainbow.getValue() ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.empty ? 0 : 255, this.empty ? 255 : 0, 0, 255);
            if (this.rendermode.getValue() == Mode.Fill) {
                RenderUtil.drawBBFill(axisAlignedBB, color, this.fillAlpha.getValue());
            }
            else {
                RenderUtil.drawBBBox(axisAlignedBB, color, this.boxAlpha.getValue());
            }
        }
    }
    
    public InstantMine() {
        super("InstantMine", "Instant Mine", Module.Category.PLAYER, true, false, false);
        this.breakSuccess = new Timer();
        this.Rendertimer = new Timer();
        this.creativeMode = (Setting<Boolean>)this.register(new Setting("CreativeMode", (T)true));
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)256.0f, (T)1.0f, (T)256.0f));
        this.ghostHand = (Setting<Boolean>)this.register(new Setting("GhostHand", (T)true, p0 -> this.creativeMode.getValue()));
        this.superghost = (Setting<Boolean>)this.register(new Setting("Super GhostHand", (T)Boolean.FALSE, p0 -> this.ghostHand.getValue()));
        this.doubleBreak = (Setting<Boolean>)this.register(new Setting("Double Break", (T)Boolean.FALSE, p0 -> this.ghostHand.getValue()));
        this.crystal = (Setting<Boolean>)this.register(new Setting("Crystal", (T)Boolean.FALSE));
        this.attackcrystal = (Setting<Boolean>)this.register(new Setting("Attack Crystal", (T)Boolean.FALSE, p0 -> this.crystal.getValue()));
        this.bind = (Setting<Bind>)this.register(new Setting("ObsidianBind", (T)new Bind(-1), p0 -> this.crystal.getValue()));
        this.instant = (Setting<Boolean>)this.register(new Setting("Instant", (T)true));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true));
        this.rendermode = (Setting<Mode>)this.register(new Setting("Render Mode", (T)Mode.Fill, p0 -> this.render.getValue()));
        this.fillAlpha = (Setting<Integer>)this.register(new Setting("Fill Alpha", (T)80, (T)0, (T)255, p0 -> this.render.getValue() && this.rendermode.getValue() == Mode.Fill));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("Box Alpha", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.rendermode.getValue() == Mode.Box));
        this.rainbow = (Setting<Boolean>)this.register(new Setting("Rainbow", (T)true, p0 -> this.render.getValue()));
        this.godBlocks = Arrays.asList(Blocks.AIR, (Block)Blocks.FLOWING_LAVA, (Block)Blocks.LAVA, (Block)Blocks.FLOWING_WATER, (Block)Blocks.WATER, Blocks.BEDROCK);
        this.cancelStart = false;
        this.empty = false;
        this.setInstance();
    }
    
    public static void attackcrystal() {
        for (final Entity entity3 : (List)InstantMine.mc.world.loadedEntityList.stream().filter(entity -> entity instanceof EntityEnderCrystal && !entity.isDead).sorted(Comparator.comparing(entity2 -> InstantMine.mc.player.getDistance(entity2))).collect(Collectors.toList())) {
            if (entity3 instanceof EntityEnderCrystal && entity3.getDistanceSq(InstantMine.breakPos) <= 2.0) {
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketUseEntity(entity3));
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketAnimation(EnumHand.OFF_HAND));
            }
        }
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (!this.cancelStart) {
            return;
        }
        if ((InstantMine.breakPos != null || (this.instant.getValue() && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR)) && InstantMine.mc.player != null && InstantMine.mc.player.getDistanceSq(InstantMine.breakPos) > MathUtil.square(this.range.getValue())) {
            InstantMine.breakPos = null;
            InstantMine.breakPos2 = null;
            this.cancelStart = false;
            return;
        }
        if (this.doubleBreak.getValue() && this.ghostHand.getValue() && InstantMine.breakPos2 != null) {
            final int currentItem = InstantMine.mc.player.inventory.currentItem;
            if (InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() != Blocks.AIR) {
                if (InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() == Blocks.OBSIDIAN && !this.breakSuccess.passedMs(1234L)) {
                    return;
                }
                InstantMine.breakPos2 = InstantMine.breakPos;
                try {
                    this.block = InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock();
                }
                catch (Exception ex) {}
                final int bestAvailableToolSlot = this.getBestAvailableToolSlot(this.block.getBlockState().getBaseState());
                if (InstantMine.mc.player.inventory.currentItem != bestAvailableToolSlot && bestAvailableToolSlot != -1) {
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(bestAvailableToolSlot));
                    InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos2, this.facing));
                }
            }
            if (InstantMine.mc.world.getBlockState(InstantMine.breakPos2).getBlock() == Blocks.AIR) {
                InstantMine.breakPos2 = null;
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketHeldItemChange(currentItem));
            }
        }
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (InstantMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (!this.cancelStart) {
            return;
        }
        if (this.crystal.getValue() && this.attackcrystal.getValue() && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR) {
            attackcrystal();
        }
        if (this.bind.getValue().isDown() && this.crystal.getValue() && InventoryUtil.findHotbarBlock(BlockObsidian.class) != -1 && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR) {
            final int hotbarBlock = InventoryUtil.findHotbarBlock(BlockObsidian.class);
            final int currentItem = InstantMine.mc.player.inventory.currentItem;
            this.switchToSlot(hotbarBlock);
            BlockUtil.placeBlock(InstantMine.breakPos, EnumHand.MAIN_HAND, false, true, false);
            this.switchToSlot(currentItem);
        }
        if (InstantMine.breakPos != null && InstantMine.mc.player != null && InstantMine.mc.player.getDistanceSq(InstantMine.breakPos) > MathUtil.square(this.range.getValue())) {
            InstantMine.breakPos = null;
            InstantMine.breakPos2 = null;
            this.cancelStart = false;
            return;
        }
        if (InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.AIR && !this.instant.getValue()) {
            InstantMine.breakPos = null;
            InstantMine.breakPos2 = null;
            this.cancelStart = false;
            return;
        }
        if (this.godBlocks.contains(InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock())) {
            return;
        }
        if (InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1 && this.crystal.getValue() && InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock() == Blocks.OBSIDIAN && !this.check() && !InstantMine.breakPos.equals((Object)AntiBurrow.pos)) {
            BlockUtil.placeCrystalOnBlock(InstantMine.breakPos, EnumHand.MAIN_HAND, true, false, true);
        }
        if (this.ghostHand.getValue() || (this.ghostHand.getValue() && this.superghost.getValue())) {
            final float getBlockHardness = InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlockHardness((World)InstantMine.mc.world, InstantMine.breakPos);
            final int currentItem2 = InstantMine.mc.player.inventory.currentItem;
            if (!this.breakSuccess.passedMs((int)getBlockHardness)) {
                return;
            }
            if (this.superghost.getValue() && InventoryUtil.getItemHotbar(Items.DIAMOND_PICKAXE) == -1) {
                for (int i = 9; i < 36; ++i) {
                    if (InstantMine.mc.player.inventory.getStackInSlot(i).getItem() == Items.DIAMOND_PICKAXE) {
                        InstantMine.mc.playerController.windowClick(InstantMine.mc.player.inventoryContainer.windowId, i, InstantMine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)InstantMine.mc.player);
                        InstantMine.mc.playerController.updateController();
                        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                        InstantMine.mc.playerController.windowClick(InstantMine.mc.player.inventoryContainer.windowId, i, InstantMine.mc.player.inventory.currentItem, ClickType.SWAP, (EntityPlayer)InstantMine.mc.player);
                        InstantMine.mc.playerController.updateController();
                        return;
                    }
                }
            }
            try {
                this.block = InstantMine.mc.world.getBlockState(InstantMine.breakPos).getBlock();
            }
            catch (Exception ex) {}
            final int bestAvailableToolSlot = this.getBestAvailableToolSlot(this.block.getBlockState().getBaseState());
            if (InstantMine.mc.player.inventory.currentItem != bestAvailableToolSlot && bestAvailableToolSlot != -1) {
                InstantMine.mc.player.inventory.currentItem = bestAvailableToolSlot;
                InstantMine.mc.playerController.updateController();
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                InstantMine.mc.player.inventory.currentItem = currentItem2;
                InstantMine.mc.playerController.updateController();
                return;
            }
        }
        InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
    }
    
    public String getDisplayInfo() {
        return this.ghostHand.getValue() ? "Ghost" : "Normal";
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (InstantMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (send.getPacket() instanceof CPacketPlayerDigging && ((CPacketPlayerDigging)send.getPacket()).getAction() == CPacketPlayerDigging.Action.START_DESTROY_BLOCK) {
            send.setCanceled(this.cancelStart);
        }
    }
    
    public int getBestAvailableToolSlot(final IBlockState blockState) {
        int n = -1;
        double n2 = 0.0;
        for (int i = 0; i < 9; ++i) {
            final ItemStack getStackInSlot = InstantMine.mc.player.inventory.getStackInSlot(i);
            final float getDestroySpeed;
            final int getEnchantmentLevel;
            final float n3;
            if (!getStackInSlot.isEmpty && (getDestroySpeed = getStackInSlot.getDestroySpeed(blockState)) > 1.0f && (n3 = (float)(getDestroySpeed + (((getEnchantmentLevel = EnchantmentHelper.getEnchantmentLevel(Enchantments.EFFICIENCY, getStackInSlot)) > 0) ? (Math.pow(getEnchantmentLevel, 2.0) + 1.0) : 0.0))) > n2) {
                n2 = n3;
                n = i;
            }
        }
        return n;
    }
    
    @SubscribeEvent
    public void onBlockEvent(final PlayerDamageBlockEvent playerDamageBlockEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (InstantMine.breakPos != null && InstantMine.breakPos.getX() == playerDamageBlockEvent.getPos().getX() && InstantMine.breakPos.getY() == playerDamageBlockEvent.getPos().getY() && InstantMine.breakPos.getZ() == playerDamageBlockEvent.getPos().getZ()) {
            return;
        }
        if (InstantMine.breakPos2 != null && InstantMine.breakPos2.getX() == playerDamageBlockEvent.getPos().getX() && InstantMine.breakPos2.getY() == playerDamageBlockEvent.getPos().getY() && InstantMine.breakPos2.getZ() == playerDamageBlockEvent.getPos().getZ() && this.doubleBreak.getValue()) {
            return;
        }
        if (InstantMine.mc.player.capabilities.isCreativeMode) {
            return;
        }
        if (BlockUtil.canBreak(playerDamageBlockEvent.pos)) {
            this.manxi = 0.0;
            InstantMine.breakPos2 = InstantMine.breakPos;
            this.empty = false;
            this.cancelStart = false;
            InstantMine.breakPos = playerDamageBlockEvent.pos;
            this.breakSuccess.reset();
            this.facing = playerDamageBlockEvent.facing;
            if (InstantMine.breakPos != null) {
                InstantMine.mc.player.swingArm(EnumHand.MAIN_HAND);
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                this.cancelStart = true;
                InstantMine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, InstantMine.breakPos, this.facing));
                playerDamageBlockEvent.setCanceled(true);
            }
        }
    }
    
    public static InstantMine getInstance() {
        if (InstantMine.INSTANCE == null) {
            InstantMine.INSTANCE = new InstantMine();
        }
        return InstantMine.INSTANCE;
    }
    
    public enum Mode
    {
        Fill, 
        Box;
    }
}

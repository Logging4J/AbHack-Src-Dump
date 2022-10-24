//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.player;

import me.abHack.features.modules.*;
import net.minecraft.block.state.*;
import me.abHack.features.setting.*;
import net.minecraft.network.*;
import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.awt.*;
import net.minecraft.init.*;
import net.minecraft.item.*;
import me.abHack.util.*;
import me.abHack.event.events.*;
import net.minecraft.network.play.client.*;
import net.minecraft.util.math.*;
import net.minecraft.entity.*;
import net.minecraft.entity.item.*;
import java.util.*;

public class Speedmine extends Module
{
    public /* synthetic */ IBlockState currentBlockState;
    public /* synthetic */ Setting<Boolean> noSwing;
    public /* synthetic */ Setting<Boolean> allow;
    public /* synthetic */ Setting<Boolean> tweaks;
    private /* synthetic */ EnumFacing lastFacing;
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ float breakTime;
    public /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ BlockPos lastPos;
    private static /* synthetic */ Speedmine INSTANCE;
    public final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Boolean> noBreakAnim;
    public /* synthetic */ Setting<Boolean> reset;
    public /* synthetic */ Setting<Float> damage;
    public /* synthetic */ Setting<Boolean> webSwitch;
    public /* synthetic */ Setting<Boolean> render;
    public /* synthetic */ Setting<Boolean> noDelay;
    public /* synthetic */ Setting<Integer> red;
    public /* synthetic */ Setting<Mode> mode;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Float> range;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Boolean> outline;
    private /* synthetic */ boolean isMining;
    public final /* synthetic */ Setting<Float> lineWidth;
    public /* synthetic */ BlockPos currentPos;
    
    private int getPickSlot() {
        for (int i = 0; i < 9; ++i) {
            if (Speedmine.mc.player.inventory.getStackInSlot(i).getItem() == Items.DIAMOND_PICKAXE) {
                return i;
            }
        }
        return -1;
    }
    
    private void setInstance() {
        Speedmine.INSTANCE = this;
    }
    
    public static Speedmine getInstance() {
        if (Speedmine.INSTANCE == null) {
            Speedmine.INSTANCE = new Speedmine();
        }
        return Speedmine.INSTANCE;
    }
    
    public String getDisplayInfo() {
        return this.mode.currentEnumName();
    }
    
    public Speedmine() {
        super("Speedmine", "packet mine.", Module.Category.PLAYER, true, false, false);
        this.timer = new Timer();
        this.range = (Setting<Float>)this.register(new Setting("Range", (T)10.0f, (T)0.0f, (T)50.0f));
        this.tweaks = (Setting<Boolean>)this.register(new Setting("Tweaks", (T)true));
        this.mode = (Setting<Mode>)this.register(new Setting("Mode", (T)Mode.PACKET, p0 -> this.tweaks.getValue()));
        this.damage = (Setting<Float>)this.register(new Setting("Damage", (T)0.7f, (T)0.0f, (T)1.0f, p0 -> this.mode.getValue() == Mode.DAMAGE && this.tweaks.getValue()));
        this.reset = (Setting<Boolean>)this.register(new Setting("Reset", (T)true));
        this.noBreakAnim = (Setting<Boolean>)this.register(new Setting("NoBreakAnim", (T)false));
        this.noDelay = (Setting<Boolean>)this.register(new Setting("NoDelay", (T)false));
        this.noSwing = (Setting<Boolean>)this.register(new Setting("NoSwing", (T)false));
        this.allow = (Setting<Boolean>)this.register(new Setting("AllowMultiTask", (T)false));
        this.webSwitch = (Setting<Boolean>)this.register(new Setting("WebSwitch", (T)false));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)125, (T)0, (T)255, p0 -> this.render.getValue()));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)0, (T)0, (T)255, p0 -> this.render.getValue()));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255, p0 -> this.render.getValue()));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)Boolean.TRUE, p0 -> this.render.getValue()));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)85, (T)0, (T)255, p0 -> this.box.getValue() && this.render.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)Boolean.TRUE, p0 -> this.render.getValue()));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.outline.getValue() && this.render.getValue()));
        this.breakTime = -1.0f;
        this.setInstance();
    }
    
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        if (this.noDelay.getValue()) {
            Speedmine.mc.playerController.blockHitDelay = 0;
        }
        if (this.isMining && this.lastPos != null && this.lastFacing != null && this.noBreakAnim.getValue()) {
            Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.ABORT_DESTROY_BLOCK, this.lastPos, this.lastFacing));
        }
        if (this.reset.getValue() && Speedmine.mc.gameSettings.keyBindUseItem.isKeyDown() && !this.allow.getValue()) {
            Speedmine.mc.playerController.isHittingBlock = false;
        }
    }
    
    @SubscribeEvent
    public void onBlockEvent(final BlockEvent blockEvent) {
        if (fullNullCheck()) {
            return;
        }
        if (blockEvent.getStage() == 3 && Speedmine.mc.world.getBlockState(blockEvent.pos).getBlock() instanceof BlockEndPortalFrame) {
            Speedmine.mc.world.getBlockState(blockEvent.pos).getBlock().setHardness(50.0f);
        }
        if (blockEvent.getStage() == 3 && this.reset.getValue() && Speedmine.mc.playerController.curBlockDamageMP > 0.1f) {
            Speedmine.mc.playerController.isHittingBlock = true;
        }
        if (blockEvent.getStage() == 4 && this.tweaks.getValue() && BlockUtil.canBreak(blockEvent.pos)) {
            if (this.reset.getValue()) {
                Speedmine.mc.playerController.isHittingBlock = false;
            }
            switch (this.mode.getValue()) {
                case PACKET: {
                    if (this.currentPos == null) {
                        this.currentPos = blockEvent.pos;
                        this.currentBlockState = Speedmine.mc.world.getBlockState(this.currentPos);
                        this.breakTime = new ItemStack(Items.DIAMOND_PICKAXE).getDestroySpeed(this.currentBlockState) / 3.71f;
                        this.timer.reset();
                    }
                    Speedmine.mc.player.swingArm(EnumHand.MAIN_HAND);
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                    blockEvent.setCanceled(true);
                    break;
                }
                case DAMAGE: {
                    if (Speedmine.mc.playerController.curBlockDamageMP < this.damage.getValue()) {
                        break;
                    }
                    Speedmine.mc.playerController.curBlockDamageMP = 1.0f;
                    break;
                }
                case INSTANT: {
                    Speedmine.mc.player.swingArm(EnumHand.MAIN_HAND);
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.START_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                    Speedmine.mc.player.connection.sendPacket((Packet)new CPacketPlayerDigging(CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK, blockEvent.pos, blockEvent.facing));
                    Speedmine.mc.playerController.onPlayerDestroyBlock(blockEvent.pos);
                    Speedmine.mc.world.setBlockToAir(blockEvent.pos);
                    break;
                }
            }
        }
    }
    
    static {
        Speedmine.INSTANCE = new Speedmine();
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.render.getValue() && this.currentPos != null) {
            RenderUtil.boxESP(this.currentPos, new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.boxAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
        }
    }
    
    public void onTick() {
        if (this.currentPos != null) {
            if (Speedmine.mc.player != null && Speedmine.mc.player.getDistanceSq(this.currentPos) > MathUtil.square(this.range.getValue())) {
                this.currentPos = null;
                this.currentBlockState = null;
                return;
            }
            if (fullNullCheck()) {
                return;
            }
            if (!Speedmine.mc.world.getBlockState(this.currentPos).equals(this.currentBlockState) || Speedmine.mc.world.getBlockState(this.currentPos).getBlock() == Blocks.AIR) {
                this.currentPos = null;
                this.currentBlockState = null;
            }
            else if (this.webSwitch.getValue() && this.currentBlockState.getBlock() == Blocks.WEB && Speedmine.mc.player.getHeldItemMainhand().getItem() instanceof ItemPickaxe) {
                InventoryUtil.switchToHotbarSlot(ItemSword.class, false);
            }
        }
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (fullNullCheck()) {
            return;
        }
        if (send.getStage() == 0) {
            if (this.noSwing.getValue() && send.getPacket() instanceof CPacketAnimation) {
                send.setCanceled(true);
            }
            final CPacketPlayerDigging cPacketPlayerDigging;
            if (this.noBreakAnim.getValue() && send.getPacket() instanceof CPacketPlayerDigging && (cPacketPlayerDigging = (CPacketPlayerDigging)send.getPacket()) != null) {
                cPacketPlayerDigging.getPosition();
                try {
                    final Iterator iterator = Speedmine.mc.world.getEntitiesWithinAABBExcludingEntity((Entity)null, new AxisAlignedBB(cPacketPlayerDigging.getPosition())).iterator();
                    while (iterator.hasNext()) {
                        if (!(iterator.next() instanceof EntityEnderCrystal)) {
                            continue;
                        }
                        this.showAnimation();
                        return;
                    }
                }
                catch (Exception ex) {}
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.START_DESTROY_BLOCK)) {
                    this.showAnimation(true, cPacketPlayerDigging.getPosition(), cPacketPlayerDigging.getFacing());
                }
                if (cPacketPlayerDigging.getAction().equals((Object)CPacketPlayerDigging.Action.STOP_DESTROY_BLOCK)) {
                    this.showAnimation();
                }
            }
        }
    }
    
    public void showAnimation() {
        this.showAnimation(false, null, null);
    }
    
    private void showAnimation(final boolean isMining, final BlockPos lastPos, final EnumFacing lastFacing) {
        this.isMining = isMining;
        this.lastPos = lastPos;
        this.lastFacing = lastFacing;
    }
    
    public enum Mode
    {
        INSTANT, 
        DAMAGE, 
        PACKET;
    }
}

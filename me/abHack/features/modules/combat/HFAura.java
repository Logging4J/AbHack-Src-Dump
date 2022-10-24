//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.combat;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.item.*;
import net.minecraft.entity.*;
import net.minecraft.network.*;
import net.minecraft.network.play.server.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.stream.*;
import net.minecraft.util.math.*;
import net.minecraft.network.play.client.*;
import me.abHack.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.client.gui.*;
import net.minecraft.init.*;
import java.util.function.*;
import java.util.*;
import net.minecraft.util.*;
import me.abHack.event.events.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;

public class HFAura extends Module
{
    private final /* synthetic */ Setting<Boolean> place;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> box;
    private /* synthetic */ float pitch;
    private final /* synthetic */ Setting<Double> breakDelay;
    private /* synthetic */ EntityPlayer target;
    private final /* synthetic */ Setting<Double> maxSelfDmg;
    private final /* synthetic */ Setting<Float> lineWidth;
    private final /* synthetic */ Setting<Integer> cBlue;
    private final /* synthetic */ Timer placeTimer;
    private final /* synthetic */ Setting<Integer> green;
    private final /* synthetic */ Setting<Integer> boxAlpha;
    private final /* synthetic */ Setting<Double> minPlaceDmg;
    private final /* synthetic */ Setting<Double> placeDelay;
    private final /* synthetic */ Timer breakTimer;
    private /* synthetic */ boolean rotating;
    private final /* synthetic */ Setting<Integer> alpha;
    private final /* synthetic */ Setting<SwingMode> breakSwing;
    private final /* synthetic */ Setting<Double> targetRange;
    private final /* synthetic */ Setting<Integer> red;
    private /* synthetic */ EntityEnderCrystal crystal;
    private final /* synthetic */ Setting<Settings> setting;
    private final /* synthetic */ Setting<Boolean> explode;
    private final /* synthetic */ Setting<Boolean> render;
    private /* synthetic */ String displaytarget;
    private final /* synthetic */ Setting<Boolean> packetBreak;
    private final /* synthetic */ Setting<Double> minBreakDmg;
    private final /* synthetic */ Setting<Boolean> rotate;
    private final /* synthetic */ Setting<Double> raytrace;
    private final /* synthetic */ Setting<Double> placeRange;
    private /* synthetic */ BlockPos pos;
    private final /* synthetic */ Setting<Integer> blue;
    private final /* synthetic */ Setting<Boolean> predictsBreak;
    private final /* synthetic */ Setting<Boolean> antiSuicide;
    private final /* synthetic */ Setting<Integer> cGreen;
    private final /* synthetic */ Setting<Boolean> fastUpdate;
    private /* synthetic */ float yaw;
    private final /* synthetic */ Setting<Double> breakRange;
    private final /* synthetic */ Setting<Integer> cAlpha;
    private final /* synthetic */ Setting<Boolean> slient;
    private final /* synthetic */ Setting<Boolean> renderDmg;
    private final /* synthetic */ Setting<Integer> cRed;
    
    @Override
    public String getDisplayInfo() {
        if (this.target != null) {
            this.displaytarget = this.target.getName();
        }
        if (this.displaytarget != null) {
            return this.displaytarget;
        }
        return null;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST, receiveCanceled = true)
    public void onPacketReceive(final PacketEvent.Receive receive) {
        try {
            if (this.predictsBreak.getValue() && receive.getPacket() instanceof SPacketSpawnObject) {
                final SPacketSpawnObject sPacketSpawnObject = (SPacketSpawnObject)receive.getPacket();
                if (sPacketSpawnObject.getType() == 51 && this.Hit2(new BlockPos(sPacketSpawnObject.getX(), sPacketSpawnObject.getY(), sPacketSpawnObject.getZ()))) {
                    final CPacketUseEntity cPacketUseEntity = new CPacketUseEntity();
                    cPacketUseEntity.entityId = sPacketSpawnObject.getEntityID();
                    cPacketUseEntity.action = CPacketUseEntity.Action.ATTACK;
                    HFAura.mc.player.connection.sendPacket((Packet)cPacketUseEntity);
                }
            }
            if (receive.getPacket() instanceof SPacketSoundEffect) {
                final SPacketSoundEffect sPacketSoundEffect = (SPacketSoundEffect)receive.getPacket();
                if (this.fastUpdate.getValue() && this.crystal != null && sPacketSoundEffect.getCategory() == SoundCategory.BLOCKS && sPacketSoundEffect.getSound() == SoundEvents.ENTITY_GENERIC_EXPLODE && this.Hit2(new BlockPos(sPacketSoundEffect.getX(), sPacketSoundEffect.getY(), sPacketSoundEffect.getZ()))) {
                    this.crystal.setDead();
                }
            }
        }
        catch (NullPointerException ex) {}
    }
    
    private List<BlockPos> possiblePlacePositions(final float n) {
        final NonNullList create = NonNullList.create();
        create.addAll((Collection)BlockUtil.getSphere(new BlockPos(Math.floor(HFAura.mc.player.posX), Math.floor(HFAura.mc.player.posY), Math.floor(HFAura.mc.player.posZ)), n, (int)n, false, true, 0).stream().filter(blockPos -> BlockUtil.canPlaceCrystal(blockPos)).collect((Collector<? super Object, ?, List<? super Object>>)Collectors.toList()));
        double n2;
        create.removeIf(blockPos2 -> {
            if (!BlockUtil.canBlockBeSeen(blockPos2.getX() + 0.5, blockPos2.getY() + 1, blockPos2.getZ() + 0.5) && Math.sqrt(HFAura.mc.player.getDistanceSq(blockPos2.getX() + 0.5, (double)(blockPos2.getY() + 1), blockPos2.getZ() + 0.5)) > this.raytrace.getValue()) {
                return true;
            }
            else {
                n2 = DamageUtil.calculateDamage(blockPos2.getX() + 0.5, blockPos2.getY() + 1.0, blockPos2.getZ() + 0.5, (Entity)HFAura.mc.player);
                return n2 + (this.antiSuicide.getValue() ? 2.0 : 0.5) >= HFAura.mc.player.getHealth() + HFAura.mc.player.getAbsorptionAmount() || n2 - 0.5 > this.maxSelfDmg.getValue();
            }
        });
        return (List<BlockPos>)create;
    }
    
    @Override
    public void onDisable() {
        this.rotating = false;
    }
    
    private void rotateToPos(final BlockPos blockPos) {
        if (this.rotate.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(HFAura.mc.player.getPositionEyes(HFAura.mc.getRenderPartialTicks()), new Vec3d((double)(blockPos.getX() + 0.5f), (double)(blockPos.getY() - 0.5f), (double)(blockPos.getZ() + 0.5f)));
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.rotating = true;
        }
    }
    
    public HFAura() {
        super("HFAura", "HF Ez xxs", Category.COMBAT, true, false, false);
        this.placeTimer = new Timer();
        this.breakTimer = new Timer();
        this.setting = (Setting<Settings>)this.register(new Setting("Settings", (T)Settings.PLACE));
        this.place = (Setting<Boolean>)this.register(new Setting("Place", (T)true, p0 -> this.setting.getValue() == Settings.PLACE));
        this.placeDelay = (Setting<Double>)this.register(new Setting("PlaceDelay", (T)0.0, (T)0.0, (T)500.0, p0 -> this.place.getValue() && this.setting.getValue() == Settings.PLACE));
        this.placeRange = (Setting<Double>)this.register(new Setting("PlaceRange", (T)6.0, (T)0.0, (T)6.0, p0 -> this.place.getValue() && this.setting.getValue() == Settings.PLACE));
        this.slient = (Setting<Boolean>)this.register(new Setting("Slient", (T)true, p0 -> this.place.getValue() && this.setting.getValue() == Settings.PLACE));
        this.explode = (Setting<Boolean>)this.register(new Setting("Break", (T)true, p0 -> this.setting.getValue() == Settings.BREAK));
        this.packetBreak = (Setting<Boolean>)this.register(new Setting("PacketBreak", (T)true, p0 -> this.explode.getValue() && this.setting.getValue() == Settings.BREAK));
        this.breakDelay = (Setting<Double>)this.register(new Setting("BreakDelay", (T)0.0, (T)0.0, (T)500.0, p0 -> this.explode.getValue() && this.setting.getValue() == Settings.BREAK));
        this.breakRange = (Setting<Double>)this.register(new Setting("BreakRange", (T)6.0, (T)0.0, (T)6.0, p0 -> this.explode.getValue() && this.setting.getValue() == Settings.BREAK));
        this.predictsBreak = (Setting<Boolean>)this.register(new Setting("PredictsBreak", (T)true, p0 -> this.setting.getValue() == Settings.BREAK));
        this.fastUpdate = (Setting<Boolean>)this.register(new Setting("FastUpDate", (T)true, p0 -> this.setting.getValue() == Settings.BREAK));
        this.rotate = (Setting<Boolean>)this.register(new Setting("Rotate", (T)false, p0 -> this.setting.getValue() == Settings.MISC));
        this.antiSuicide = (Setting<Boolean>)this.register(new Setting("AntiSuicide", (T)true, p0 -> this.setting.getValue() == Settings.MISC));
        this.raytrace = (Setting<Double>)this.register(new Setting("Raytrace", (T)3.0, (T)0.0, (T)3.0, p0 -> this.setting.getValue() == Settings.MISC));
        this.targetRange = (Setting<Double>)this.register(new Setting("TargetRange", (T)12.0, (T)0.0, (T)12.0, p0 -> this.setting.getValue() == Settings.MISC));
        this.minPlaceDmg = (Setting<Double>)this.register(new Setting("MinPlaceDmg", (T)5.0, (T)0.0, (T)24.0, p0 -> this.setting.getValue() == Settings.MISC));
        this.minBreakDmg = (Setting<Double>)this.register(new Setting("MinBreakDmg", (T)5.0, (T)0.0, (T)24.0, p0 -> this.setting.getValue() == Settings.MISC));
        this.maxSelfDmg = (Setting<Double>)this.register(new Setting("MaxSelfDmg", (T)6.0, (T)0.0, (T)12.0, p0 -> this.setting.getValue() == Settings.MISC));
        this.breakSwing = (Setting<SwingMode>)this.register(new Setting("BreakSwing", (T)SwingMode.MainHand, p0 -> this.setting.getValue() == Settings.MISC));
        this.render = (Setting<Boolean>)this.register(new Setting("Render", (T)true, p0 -> this.setting.getValue() == Settings.RENDER));
        this.renderDmg = (Setting<Boolean>)this.register(new Setting("RenderDmg", (T)true, p0 -> this.render.getValue() && this.setting.getValue() == Settings.RENDER));
        this.box = (Setting<Boolean>)this.register(new Setting("Box", (T)true, p0 -> this.render.getValue() && this.setting.getValue() == Settings.RENDER));
        this.red = (Setting<Integer>)this.register(new Setting("Red", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.green = (Setting<Integer>)this.register(new Setting("Green", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.blue = (Setting<Integer>)this.register(new Setting("Blue", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.alpha = (Setting<Integer>)this.register(new Setting("Alpha", (T)200, (T)0, (T)255, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.boxAlpha = (Setting<Integer>)this.register(new Setting("BoxAlpha", (T)100, (T)0, (T)255, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.lineWidth = (Setting<Float>)this.register(new Setting("LineWidth", (T)1.0f, (T)0.1f, (T)5.0f, p0 -> this.render.getValue() && this.box.getValue() && this.setting.getValue() == Settings.RENDER));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true, p0 -> this.render.getValue() && this.setting.getValue() == Settings.RENDER));
        this.cRed = (Setting<Integer>)this.register(new Setting("OL-Red", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.outline.getValue() && this.setting.getValue() == Settings.RENDER));
        this.cGreen = (Setting<Integer>)this.register(new Setting("OL-Green", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.outline.getValue() && this.setting.getValue() == Settings.RENDER));
        this.cBlue = (Setting<Integer>)this.register(new Setting("OL-Blue", (T)255, (T)0, (T)255, p0 -> this.render.getValue() && this.outline.getValue() && this.setting.getValue() == Settings.RENDER));
        this.cAlpha = (Setting<Integer>)this.register(new Setting("OL-Alpha", (T)200, (T)0, (T)255, p0 -> this.render.getValue() && this.outline.getValue() && this.setting.getValue() == Settings.RENDER));
        this.rotating = false;
        this.pitch = 0.0f;
        this.yaw = 0.0f;
    }
    
    @SubscribeEvent
    public void onPacketSend(final PacketEvent.Send send) {
        if (this.rotate.getValue() && this.rotating && send.getPacket() instanceof CPacketPlayer) {
            final CPacketPlayer cPacketPlayer = (CPacketPlayer)send.getPacket();
            cPacketPlayer.yaw = this.yaw;
            cPacketPlayer.pitch = this.pitch;
            this.rotating = false;
        }
    }
    
    private EntityPlayer getTarget() {
        Object o = null;
        for (final EntityPlayer entityPlayer : HFAura.mc.world.playerEntities) {
            if (HFAura.mc.player != null && !HFAura.mc.player.isDead && !entityPlayer.isDead && entityPlayer.getHealth() + entityPlayer.getAbsorptionAmount() > 0.0f && !entityPlayer.getName().equals(HFAura.mc.player.getName()) && !OyVey.friendManager.isFriend(entityPlayer.getName()) && !entityPlayer.isCreative() && !EntityUtil.isInHole((Entity)entityPlayer)) {
                if (entityPlayer.getDistance((Entity)HFAura.mc.player) > this.targetRange.getValue()) {
                    continue;
                }
                if (o == null) {
                    o = entityPlayer;
                }
                else {
                    if (((EntityPlayer)o).getDistance((Entity)HFAura.mc.player) <= entityPlayer.getDistance((Entity)HFAura.mc.player)) {
                        continue;
                    }
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    private void rotateTo(final Entity entity) {
        if (this.rotate.getValue()) {
            final float[] calcAngle = MathUtil.calcAngle(HFAura.mc.player.getPositionEyes(HFAura.mc.getRenderPartialTicks()), entity.getPositionVector());
            this.yaw = calcAngle[0];
            this.pitch = calcAngle[1];
            this.rotating = true;
        }
    }
    
    @Override
    public void onTick() {
        if (fullNullCheck()) {
            return;
        }
        if (HFAura.mc.currentScreen instanceof GuiChest && HFAura.mc.world.getBlockState(HFAura.mc.player.rayTrace(4.5, HFAura.mc.getRenderPartialTicks()).getBlockPos()).getBlock() == Blocks.ENDER_CHEST) {
            HFAura.mc.displayGuiScreen((GuiScreen)null);
        }
        if (HFAura.mc.player.getHeldItemOffhand().getItem() != Items.END_CRYSTAL && HFAura.mc.player.getHeldItemMainhand().getItem() != Items.END_CRYSTAL && !this.slient.getValue()) {
            this.target = null;
            this.pos = null;
            return;
        }
        if (this.target == null) {
            this.target = this.getTarget();
        }
        if (this.target == null) {
            this.crystal = null;
            return;
        }
        this.crystal = (EntityEnderCrystal)HFAura.mc.world.loadedEntityList.stream().filter(this::Hit1).map(entityEnderCrystal -> entityEnderCrystal).min(Comparator.comparing(entity -> this.target.getDistance(entity))).orElse(null);
        if (this.crystal != null && this.explode.getValue() && this.breakTimer.passedMs(this.breakDelay.getValue().longValue())) {
            this.breakTimer.reset();
            if (this.packetBreak.getValue()) {
                this.rotateTo((Entity)this.crystal);
                EntityUtil.attackEntity((Entity)this.crystal, true, (this.breakSwing.getValue() == SwingMode.MainHand) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
            }
            else {
                this.rotateTo((Entity)this.crystal);
                EntityUtil.attackEntity((Entity)this.crystal, false, (this.breakSwing.getValue() == SwingMode.MainHand) ? EnumHand.MAIN_HAND : EnumHand.OFF_HAND);
                HFAura.mc.player.resetCooldown();
            }
        }
        if (this.place.getValue() && this.placeTimer.passedMs(this.placeDelay.getValue().longValue())) {
            this.placeTimer.reset();
            double n = 1.5;
            for (final BlockPos pos : this.possiblePlacePositions(this.placeRange.getValue().floatValue())) {
                if (this.target != null && pos != null && !this.target.isDead) {
                    if (this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
                        continue;
                    }
                    final double n2 = DamageUtil.calculateDamage(pos.getX() + 0.5, pos.getY() + 1.0, pos.getZ() + 0.5, (Entity)this.target);
                    if (n2 < this.minPlaceDmg.getValue()) {
                        continue;
                    }
                    if (n >= n2) {
                        continue;
                    }
                    this.pos = pos;
                    n = n2;
                }
            }
            if (n == 1.5) {
                this.target = null;
                this.pos = null;
                return;
            }
            if (this.pos != null) {
                this.rotateToPos(this.pos);
                if (this.slient.getValue() && InventoryUtil.getItemHotbar(Items.END_CRYSTAL) != -1) {
                    final int itemHotbar = InventoryUtil.getItemHotbar(Items.END_CRYSTAL);
                    final int currentItem = HFAura.mc.player.inventory.currentItem;
                    this.switchToSlot(itemHotbar);
                    BlockUtil.placeCrystalOnBlock(this.pos, (HFAura.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                    this.switchToSlot(currentItem);
                }
                else {
                    BlockUtil.placeCrystalOnBlock(this.pos, (HFAura.mc.player.getHeldItemOffhand().getItem() == Items.END_CRYSTAL) ? EnumHand.OFF_HAND : EnumHand.MAIN_HAND);
                }
            }
        }
    }
    
    @Override
    public void onEnable() {
        this.placeTimer.reset();
        this.breakTimer.reset();
        this.target = null;
        this.displaytarget = null;
        this.crystal = null;
        this.pos = null;
    }
    
    private boolean Hit1(final Entity entity) {
        if (entity == null) {
            return false;
        }
        if (!(entity instanceof EntityEnderCrystal)) {
            return false;
        }
        if (entity.isDead) {
            return false;
        }
        if (this.target == null) {
            return false;
        }
        if (Math.sqrt(HFAura.mc.player.getDistanceSq(entity.posX, entity.posY, entity.posZ)) > this.breakRange.getValue()) {
            return false;
        }
        if (!HFAura.mc.player.canEntityBeSeen(entity) && Math.sqrt(HFAura.mc.player.getDistanceSq(entity.posX, entity.posY, entity.posZ)) > this.raytrace.getValue()) {
            return false;
        }
        if (this.target.isDead || this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
            return false;
        }
        final double n = DamageUtil.calculateDamage(entity.posX, entity.posY, entity.posZ, (Entity)HFAura.mc.player);
        final double n2 = DamageUtil.calculateDamage(entity.posX, entity.posY, entity.posZ, (Entity)this.target);
        return n + (this.antiSuicide.getValue() ? 2.0 : 0.5) < HFAura.mc.player.getHealth() + HFAura.mc.player.getAbsorptionAmount() && n - 0.5 <= this.maxSelfDmg.getValue() && (n2 >= this.target.getHealth() + this.target.getAbsorptionAmount() || n2 >= this.minBreakDmg.getValue());
    }
    
    @Override
    public void onRender3D(final Render3DEvent render3DEvent) {
        if (this.pos != null && this.target != null) {
            RenderUtil.drawBoxESP(this.pos, ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.alpha.getValue()), this.outline.getValue(), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()) : new Color(this.cRed.getValue(), this.cGreen.getValue(), this.cBlue.getValue(), this.cAlpha.getValue()), this.lineWidth.getValue(), this.outline.getValue(), this.box.getValue(), this.boxAlpha.getValue(), true);
            final double n = DamageUtil.calculateDamage(this.pos.getX() + 0.5, this.pos.getY() + 1.0, this.pos.getZ() + 0.5, (Entity)this.target);
            final int n2 = (n > 18.0) ? 16711680 : ((n > 16.0) ? 16720896 : ((n > 12.0) ? 16744192 : ((n > 8.0) ? 16776960 : ((n > 5.0) ? 65535 : 65280))));
            if (this.renderDmg.getValue()) {
                RenderUtil.drawText(this.pos, String.valueOf(new StringBuilder().append((Math.floor(n) == n) ? Integer.valueOf((int)n) : String.format("%.1f", n)).append("")), n2);
            }
        }
    }
    
    private boolean Hit2(final BlockPos blockPos) {
        if (blockPos == null) {
            return false;
        }
        if (this.target == null) {
            return false;
        }
        if (Math.sqrt(HFAura.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())) > this.breakRange.getValue()) {
            return false;
        }
        if (!BlockUtil.canBlockBeSeen(blockPos.getX(), blockPos.getY(), blockPos.getZ()) && Math.sqrt(HFAura.mc.player.getDistanceSq((double)blockPos.getX(), (double)blockPos.getY(), (double)blockPos.getZ())) > this.raytrace.getValue()) {
            return false;
        }
        if (this.target.isDead || this.target.getHealth() + this.target.getAbsorptionAmount() <= 0.0f) {
            return false;
        }
        final double n = DamageUtil.calculateDamage(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (Entity)HFAura.mc.player);
        final double n2 = DamageUtil.calculateDamage(blockPos.getX(), blockPos.getY(), blockPos.getZ(), (Entity)this.target);
        return n + (this.antiSuicide.getValue() ? 2.0 : 0.5) < HFAura.mc.player.getHealth() + HFAura.mc.player.getAbsorptionAmount() && n - 0.5 <= this.maxSelfDmg.getValue() && (n2 >= this.target.getHealth() + this.target.getAbsorptionAmount() || n2 >= this.minBreakDmg.getValue());
    }
    
    private void switchToSlot(final int currentItem) {
        HFAura.mc.player.inventory.currentItem = currentItem;
        HFAura.mc.playerController.updateController();
    }
    
    public enum SwingMode
    {
        OffHand, 
        MainHand;
    }
    
    public enum Settings
    {
        RENDER, 
        PLACE, 
        MISC, 
        BREAK;
    }
}

//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.block.*;
import net.minecraft.init.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.network.*;
import me.abHack.features.modules.combat.*;
import net.minecraft.client.gui.inventory.*;
import net.minecraft.entity.*;
import com.mojang.realmsclient.gui.*;
import java.text.*;
import me.abHack.features.modules.client.*;
import net.minecraft.item.*;
import java.util.*;
import net.minecraftforge.fml.common.eventhandler.*;
import me.abHack.*;
import me.abHack.event.events.*;
import me.abHack.util.*;
import net.minecraft.util.math.*;
import net.minecraft.client.renderer.*;

public class Target extends Module
{
    public /* synthetic */ Setting<Integer> blue;
    public /* synthetic */ Setting<Integer> red;
    private static /* synthetic */ Target INSTANCE;
    private /* synthetic */ int startcolor1;
    public /* synthetic */ Setting<Integer> green;
    public /* synthetic */ Setting<Integer> targetHudY;
    public /* synthetic */ Setting<Integer> targetHudX;
    public /* synthetic */ Setting<Integer> backgroundAlpha;
    public /* synthetic */ Setting<Boolean> targetHudBackground;
    private /* synthetic */ int endcolor1;
    
    private Block getBlock(final BlockPos blockPos) {
        final Block getBlock = Target.mc.world.getBlockState(blockPos).getBlock();
        if (getBlock == Blocks.BEDROCK || getBlock == Blocks.OBSIDIAN) {
            return getBlock;
        }
        return Blocks.AIR;
    }
    
    static {
        Target.INSTANCE = new Target();
    }
    
    public void drawTargetHud(final float n, final EntityPlayer entityPlayer) {
        String value = "";
        try {
            value = String.valueOf(new StringBuilder().append(value).append(Objects.requireNonNull(Target.mc.getConnection()).getPlayerInfo(Target.mc.player.getUniqueID()).getResponseTime()).append(""));
        }
        catch (Exception ex2) {}
        Object o = (AutoCrystal.target != null) ? AutoCrystal.target : ((Killaura.target instanceof EntityPlayer) ? Killaura.target : getClosestEnemy());
        if (o == null) {
            return;
        }
        if (((EntityPlayer)o).isDead) {
            o = null;
        }
        if (o != null) {
            if (this.targetHudBackground.getValue()) {
                final float n2 = ((EntityPlayer)o).getHealth() + ((EntityPlayer)o).getAbsorptionAmount();
                final int n3 = (n2 >= 33.0f) ? ColorUtil.toRGBA(0, 255, 0, 255) : ((n2 >= 30.0f) ? ColorUtil.toRGBA(150, 255, 0, 255) : ((n2 > 25.0f) ? ColorUtil.toRGBA(75, 255, 0, 255) : ((n2 > 20.0f) ? ColorUtil.toRGBA(255, 255, 0, 255) : ((n2 > 15.0f) ? ColorUtil.toRGBA(255, 200, 0, 255) : ((n2 > 10.0f) ? ColorUtil.toRGBA(255, 150, 0, 255) : ((n2 > 5.0f) ? ColorUtil.toRGBA(255, 50, 0, 255) : ColorUtil.toRGBA(255, 0, 0, 255)))))));
                RenderUtil.drawRectangleCorrectly(this.targetHudX.getValue(), this.targetHudY.getValue(), 170, 90, ColorUtil.toRGBA(this.red.getValue(), this.green.getValue(), this.blue.getValue(), this.backgroundAlpha.getValue()));
                this.startcolor1 = n3;
                this.endcolor1 = n3;
                RenderUtil.drawGradientSideways(this.targetHudX.getValue(), this.targetHudY.getValue() + 84.0, this.targetHudX.getValue() + n2 * 4.722222, this.targetHudY.getValue() + 86.8, this.startcolor1, this.endcolor1);
            }
            GlStateManager.disableRescaleNormal();
            GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
            GlStateManager.disableTexture2D();
            GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            try {
                GuiInventory.drawEntityOnScreen(this.targetHudX.getValue() + 30, this.targetHudY.getValue() + 60, 30, 0.0f, 0.0f, (EntityLivingBase)o);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
            final int i = (int)((EntityPlayer)o).getDistance((Entity)Target.mc.player);
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableTexture2D();
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
            this.renderer.drawStringWithShadow(((EntityPlayer)o).getName(), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + 10), ColorUtil.toRGBA(255, 255, 255, 255));
            if (!EntityUtil.isInHole((Entity)o)) {
                this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("").append(ChatFormatting.BOLD).append("Vunerable")), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 40), ColorUtil.toRGBA(255, 0, 0, 255));
            }
            else {
                this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("").append(ChatFormatting.BOLD).append("Safe")), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 40), ColorUtil.toRGBA(0, 255, 0, 255));
            }
            final float n4 = ((EntityPlayer)o).getHealth() + ((EntityPlayer)o).getAbsorptionAmount();
            final int n5 = (n4 >= 33.0f) ? ColorUtil.toRGBA(0, 255, 0, 255) : ((n4 >= 30.0f) ? ColorUtil.toRGBA(150, 255, 0, 255) : ((n4 > 25.0f) ? ColorUtil.toRGBA(75, 255, 0, 255) : ((n4 > 20.0f) ? ColorUtil.toRGBA(255, 255, 0, 255) : ((n4 > 15.0f) ? ColorUtil.toRGBA(255, 200, 0, 255) : ((n4 > 10.0f) ? ColorUtil.toRGBA(255, 150, 0, 255) : ((n4 > 5.0f) ? ColorUtil.toRGBA(255, 50, 0, 255) : ColorUtil.toRGBA(255, 0, 0, 255)))))));
            final DecimalFormat decimalFormat = new DecimalFormat("##.#");
            this.renderer.drawStringWithShadow("", (float)(this.targetHudX.getValue() + 60 + this.renderer.getStringWidth("")), (float)(this.targetHudY.getValue() + 10), n5);
            final Integer value2 = EntityUtil.isFakePlayer((EntityPlayer)o) ? 0 : ((Target.mc.getConnection().getPlayerInfo(((EntityPlayer)o).getUniqueID()) == null) ? 0 : Target.mc.getConnection().getPlayerInfo(((EntityPlayer)o).getUniqueID()).getResponseTime());
            final int n6 = (value2 >= 100) ? ColorUtil.toRGBA(0, 255, 0, 255) : ((value2 > 50) ? ColorUtil.toRGBA(255, 255, 0, 255) : ColorUtil.toRGBA(255, 255, 255, 255));
            final int n7 = (i <= 5) ? ColorUtil.toRGBA(255, 0, 0, 255) : ((i < 10) ? ColorUtil.toRGBA(255, 100, 0, 255) : ((i < 20) ? ColorUtil.toRGBA(255, 150, 0, 255) : ((i < 30) ? ColorUtil.toRGBA(255, 200, 0, 255) : ((i < 50) ? ColorUtil.toRGBA(255, 255, 0, 255) : ((i < 100) ? ColorUtil.toRGBA(150, 255, 0, 255) : ColorUtil.toRGBA(255, 255, 255, 255))))));
            if (FontMod.getInstance().isOn()) {
                this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Health: ").append(n4)), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 12), n5);
            }
            else {
                this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Health: ").append(n4)), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 10), n5);
            }
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Ping: ").append(value)), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 20), n6);
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append("Distance: ").append(i)), (float)(this.targetHudX.getValue() + 60), (float)(this.targetHudY.getValue() + this.renderer.getFontHeight() + 30), n7);
            this.drawOverlay(n, (Entity)o, this.targetHudX.getValue() + 120, this.targetHudY.getValue() + 35);
            GlStateManager.enableTexture2D();
            int n8 = 0;
            final int n9 = this.targetHudX.getValue() + 50;
            final int n10 = this.targetHudY.getValue() + this.renderer.getFontHeight() * 3 + 44;
            for (final ItemStack itemStack : ((EntityPlayer)o).inventory.armorInventory) {
                ++n8;
                if (itemStack.isEmpty()) {
                    continue;
                }
                final int n11 = n9 - 90 + (9 - n8) * 20 + 2;
                GlStateManager.enableDepth();
                RenderUtil.itemRender.zLevel = 200.0f;
                if (FontMod.getInstance().isOn()) {
                    RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n9 - 150 + (9 - n8) * 20 + 2, n10 + 4);
                    RenderUtil.itemRender.renderItemOverlayIntoGUI(Target.mc.fontRenderer, itemStack, n9 - 150 + (9 - n8) * 20 + 2, n10 + 4, "");
                }
                else {
                    RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n9 - 150 + (9 - n8) * 20 + 2, n10 - 2);
                    RenderUtil.itemRender.renderItemOverlayIntoGUI(Target.mc.fontRenderer, itemStack, n9 - 150 + (9 - n8) * 20 + 2, n10 - 2, "");
                }
                RenderUtil.itemRender.zLevel = 0.0f;
                GlStateManager.enableTexture2D();
                GlStateManager.disableLighting();
                GlStateManager.disableDepth();
                final String s = (itemStack.getCount() > 1) ? String.valueOf(new StringBuilder().append(itemStack.getCount()).append("")) : "";
                this.renderer.drawStringWithShadow(s, (float)(n11 - 50 - this.renderer.getStringWidth(s)), (float)(n10 + 9), 16777215);
                final int n12 = itemStack.getMaxDamage() - itemStack.getItemDamage();
                final float n13 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
                final float n14 = 1.0f - n13;
                final int n15 = 100 - (int)(n14 * 100.0f);
                if (FontMod.getInstance().isOn()) {
                    this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n15).append("")), (float)(n11 - 47 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n15).append("")))), (float)(n10 - 2), ColorUtil.toRGBA((int)(n14 * 255.0f), (int)(n13 * 255.0f), 0));
                }
                else {
                    this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n15).append("")), (float)(n11 - 47 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n15).append("")))), (float)(n10 - 8), ColorUtil.toRGBA((int)(n14 * 255.0f), (int)(n13 * 255.0f), 0));
                }
            }
        }
    }
    
    @SubscribeEvent
    public void onReceivePacket(final PacketEvent.Receive receive) {
    }
    
    public Target() {
        super("Target", "Displays Target", Module.Category.RENDER, false, false, true);
        this.targetHudBackground = (Setting<Boolean>)this.register(new Setting("TargetHudBackground", (T)true));
        this.backgroundAlpha = (Setting<Integer>)this.register(new Setting("Background Alpha", (T)160, (T)0, (T)255, p0 -> this.targetHudBackground.getValue()));
        this.targetHudX = (Setting<Integer>)this.register(new Setting("TargetHudX", (T)0, (T)0, (T)1000));
        this.targetHudY = (Setting<Integer>)this.register(new Setting("TargetHudY", (T)0, (T)0, (T)1000));
        this.red = (Setting<Integer>)this.register(new Setting("Background-Red", (T)20, (T)0, (T)255));
        this.green = (Setting<Integer>)this.register(new Setting("Background-Green", (T)20, (T)0, (T)255));
        this.blue = (Setting<Integer>)this.register(new Setting("Background-Blue", (T)20, (T)0, (T)255));
        this.setInstance();
    }
    
    private void setInstance() {
        Target.INSTANCE = this;
    }
    
    public static EntityPlayer getClosestEnemy() {
        Object o = null;
        for (final EntityPlayer entityPlayer : Target.mc.world.playerEntities) {
            if (entityPlayer != Target.mc.player) {
                if (OyVey.friendManager.isFriend(entityPlayer)) {
                    continue;
                }
                if (o == null) {
                    o = entityPlayer;
                }
                else {
                    if (Target.mc.player.getDistanceSq((Entity)entityPlayer) >= Target.mc.player.getDistanceSq((Entity)o)) {
                        continue;
                    }
                    o = entityPlayer;
                }
            }
        }
        return (EntityPlayer)o;
    }
    
    public static Target getInstance() {
        if (Target.INSTANCE == null) {
            Target.INSTANCE = new Target();
        }
        return Target.INSTANCE;
    }
    
    private int getBlockDamage(final BlockPos blockPos) {
        for (final DestroyBlockProgress destroyBlockProgress : Target.mc.renderGlobal.damagedBlocks.values()) {
            if (destroyBlockProgress.getPosition().getX() == blockPos.getX() && destroyBlockProgress.getPosition().getY() == blockPos.getY()) {
                if (destroyBlockProgress.getPosition().getZ() != blockPos.getZ()) {
                    continue;
                }
                return destroyBlockProgress.getPartialBlockDamage();
            }
        }
        return 0;
    }
    
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        this.drawTargetHud(render2DEvent.partialTicks, (EntityPlayer)Target.mc.player);
    }
    
    private BlockPos traceToBlock(final float n, final float n2, final Entity entity) {
        final Vec3d interpolateEntity = EntityUtil.interpolateEntity(entity, n);
        final Vec3d direction = MathUtil.direction(n2);
        return new BlockPos(interpolateEntity.x + direction.x, interpolateEntity.y, interpolateEntity.z + direction.z);
    }
    
    public void drawOverlay(final float n, final Entity entity, final int n2, final int n3) {
        float n4 = 0.0f;
        switch (MathHelper.floor(entity.rotationYaw * 4.0f / 360.0f + 0.5) & 0x3) {
            case 1: {
                n4 = 90.0f;
                break;
            }
            case 2: {
                n4 = -180.0f;
                break;
            }
            case 3: {
                n4 = -90.0f;
                break;
            }
        }
        final BlockPos traceToBlock = this.traceToBlock(n, n4, entity);
        final Block block = this.getBlock(traceToBlock);
        if (block != null && block != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock) != 0) {
                RenderUtil.drawRect((float)(n2 + 16), (float)n3, (float)(n2 + 32), (float)(n3 + 16), 1627324416);
            }
            this.drawBlock(block, (float)(n2 + 16), (float)n3);
        }
        final BlockPos traceToBlock2;
        final Block block2;
        if ((block2 = this.getBlock(traceToBlock2 = this.traceToBlock(n, n4 - 180.0f, entity))) != null && block2 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock2) != 0) {
                RenderUtil.drawRect((float)(n2 + 16), (float)(n3 + 32), (float)(n2 + 32), (float)(n3 + 48), 1627324416);
            }
            this.drawBlock(block2, (float)(n2 + 16), (float)(n3 + 32));
        }
        final BlockPos traceToBlock3;
        final Block block3;
        if ((block3 = this.getBlock(traceToBlock3 = this.traceToBlock(n, n4 + 90.0f, entity))) != null && block3 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock3) != 0) {
                RenderUtil.drawRect((float)(n2 + 32), (float)(n3 + 16), (float)(n2 + 48), (float)(n3 + 32), 1627324416);
            }
            this.drawBlock(block3, (float)(n2 + 32), (float)(n3 + 16));
        }
        final BlockPos traceToBlock4;
        final Block block4;
        if ((block4 = this.getBlock(traceToBlock4 = this.traceToBlock(n, n4 - 90.0f, entity))) != null && block4 != Blocks.AIR) {
            if (this.getBlockDamage(traceToBlock4) != 0) {
                RenderUtil.drawRect((float)n2, (float)(n3 + 16), (float)(n2 + 16), (float)(n3 + 32), 1627324416);
            }
            this.drawBlock(block4, (float)n2, (float)(n3 + 16));
        }
    }
    
    private void drawBlock(final Block block, final float n, final float n2) {
        final ItemStack itemStack = new ItemStack(block);
        GlStateManager.pushMatrix();
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        RenderHelper.enableGUIStandardItemLighting();
        GlStateManager.translate(n, n2, 0.0f);
        Target.mc.getRenderItem().zLevel = 501.0f;
        Target.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, 0, 0);
        Target.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableBlend();
        GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        GlStateManager.popMatrix();
    }
}

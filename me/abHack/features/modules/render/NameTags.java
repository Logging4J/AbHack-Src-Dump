//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.render;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.entity.player.*;
import net.minecraft.enchantment.*;
import net.minecraft.nbt.*;
import me.abHack.event.events.*;
import java.awt.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import org.lwjgl.opengl.*;
import net.minecraft.entity.*;
import net.minecraft.util.text.*;
import net.minecraft.client.renderer.vertex.*;
import net.minecraft.client.renderer.*;
import me.abHack.*;
import me.abHack.util.*;
import net.minecraft.client.network.*;
import java.util.*;

public class NameTags extends Module
{
    private final /* synthetic */ Setting<Integer> NCblue;
    private final /* synthetic */ Setting<Integer> FCgreen;
    private final /* synthetic */ Setting<Integer> FOred;
    private final /* synthetic */ Setting<Boolean> entityID;
    private final /* synthetic */ Setting<Integer> NCgreen;
    private final /* synthetic */ Setting<Integer> Mgreen;
    private final /* synthetic */ Setting<Boolean> rect;
    private final /* synthetic */ Setting<Boolean> IORainbow;
    private final /* synthetic */ Setting<Boolean> invisibles;
    private final /* synthetic */ Setting<Integer> SCblue;
    private final /* synthetic */ Setting<Boolean> friendcolor;
    private final /* synthetic */ Setting<Integer> Oblue;
    private final /* synthetic */ Setting<Integer> SOblue;
    private final /* synthetic */ Setting<Boolean> reversed;
    private final /* synthetic */ Setting<Boolean> gamemode;
    private final /* synthetic */ Setting<Boolean> scaleing;
    private final /* synthetic */ Setting<Boolean> SCRainbow;
    private final /* synthetic */ Setting<Boolean> health;
    private final /* synthetic */ Setting<Boolean> NCRainbow;
    private final /* synthetic */ Setting<Float> Owidth;
    private final /* synthetic */ Setting<Integer> IOblue;
    private final /* synthetic */ Setting<Integer> ICgreen;
    private final /* synthetic */ Setting<Boolean> armor;
    private final /* synthetic */ Setting<Boolean> sneakcolor;
    private final /* synthetic */ Setting<Integer> FCblue;
    private final /* synthetic */ Setting<Boolean> FORainbow;
    private final /* synthetic */ Setting<Boolean> heldStackName;
    private final /* synthetic */ Setting<Boolean> smartScale;
    private final /* synthetic */ Setting<Integer> ICred;
    private final /* synthetic */ Setting<Boolean> maxText;
    private final /* synthetic */ Setting<Integer> NCred;
    private final /* synthetic */ Setting<Integer> Mred;
    private final /* synthetic */ Setting<Float> factor;
    private final /* synthetic */ Setting<Boolean> SORainbow;
    private final /* synthetic */ Setting<Boolean> ORainbow;
    private static /* synthetic */ NameTags INSTANCE;
    private final /* synthetic */ Setting<Float> size;
    private final /* synthetic */ Setting<Integer> SOred;
    private final /* synthetic */ Setting<Integer> SOgreen;
    private final /* synthetic */ Setting<Boolean> ping;
    private final /* synthetic */ Setting<Integer> Ored;
    private final /* synthetic */ Setting<Integer> IOgreen;
    private final /* synthetic */ Setting<Integer> IOred;
    private final /* synthetic */ Setting<Integer> Ogreen;
    private final /* synthetic */ Setting<Integer> ICblue;
    private final /* synthetic */ Setting<Boolean> max;
    private final /* synthetic */ Setting<Integer> Mblue;
    private final /* synthetic */ Setting<Integer> FOblue;
    private final /* synthetic */ Setting<Integer> SCred;
    private final /* synthetic */ Setting<Boolean> textcolor;
    private final /* synthetic */ Setting<Boolean> ICRainbow;
    private final /* synthetic */ Setting<Boolean> invisiblescolor;
    private final /* synthetic */ Setting<Boolean> outline;
    private final /* synthetic */ Setting<Boolean> sneak;
    private final /* synthetic */ Setting<Integer> FCred;
    private final /* synthetic */ Setting<Integer> FOgreen;
    private final /* synthetic */ Setting<Integer> SCgreen;
    private final /* synthetic */ Setting<Boolean> FCRainbow;
    
    private float getBiggestArmorTag(final EntityPlayer entityPlayer) {
        float n = 0.0f;
        boolean b = false;
        for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
            float n2 = 0.0f;
            if (itemStack != null) {
                final NBTTagList getEnchantmentTagList = itemStack.getEnchantmentTagList();
                for (int i = 0; i < getEnchantmentTagList.tagCount(); ++i) {
                    if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList.getCompoundTagAt(i).getShort("id")) != null) {
                        n2 += 8.0f;
                        b = true;
                    }
                }
            }
            if (n2 > n) {
                n = n2;
            }
        }
        final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
        if (copy.hasEffect()) {
            float n3 = 0.0f;
            final NBTTagList getEnchantmentTagList2 = copy.getEnchantmentTagList();
            for (int j = 0; j < getEnchantmentTagList2.tagCount(); ++j) {
                if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList2.getCompoundTagAt(j).getShort("id")) != null) {
                    n3 += 8.0f;
                    b = true;
                }
            }
            if (n3 > n) {
                n = n3;
            }
        }
        final ItemStack copy2 = entityPlayer.getHeldItemOffhand().copy();
        if (copy2.hasEffect()) {
            float n4 = 0.0f;
            final NBTTagList getEnchantmentTagList3 = copy2.getEnchantmentTagList();
            for (int k = 0; k < getEnchantmentTagList3.tagCount(); ++k) {
                if (Enchantment.getEnchantmentByID((int)getEnchantmentTagList3.getCompoundTagAt(k).getShort("id")) != null) {
                    n4 += 8.0f;
                    b = true;
                }
            }
            if (n4 > n) {
                n = n4;
            }
        }
        return (b ? 0 : 20) + n;
    }
    
    public void onRender3D(final Render3DEvent render3DEvent) {
        for (final EntityPlayer entityPlayer : NameTags.mc.world.playerEntities) {
            if (entityPlayer != null && !entityPlayer.equals((Object)NameTags.mc.player) && entityPlayer.isEntityAlive() && (!entityPlayer.isInvisible() || this.invisibles.getValue())) {
                this.renderNameTag(entityPlayer, this.interpolate(entityPlayer.lastTickPosX, entityPlayer.posX, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosX, this.interpolate(entityPlayer.lastTickPosY, entityPlayer.posY, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosY, this.interpolate(entityPlayer.lastTickPosZ, entityPlayer.posZ, render3DEvent.getPartialTicks()) - NameTags.mc.getRenderManager().renderPosZ, render3DEvent.getPartialTicks());
            }
        }
    }
    
    public void onUpdate() {
        if (this.outline.getValue().equals(false)) {
            this.rect.setValue(true);
        }
        else if (this.rect.getValue().equals(false)) {
            this.outline.setValue(true);
        }
        if (this.ORainbow.getValue()) {
            this.OutlineRainbow();
        }
        if (this.NCRainbow.getValue()) {
            this.TextRainbow();
        }
        if (this.FCRainbow.getValue()) {
            this.FriendRainbow();
        }
        if (this.SCRainbow.getValue()) {
            this.SneakColorRainbow();
        }
        if (this.ICRainbow.getValue()) {
            this.InvisibleRainbow();
        }
        if (this.FORainbow.getValue()) {
            this.FriendOutlineRainbow();
        }
        if (this.IORainbow.getValue()) {
            this.InvisibleOutlineRainbow();
        }
        if (this.SORainbow.getValue()) {
            this.SneakOutlineRainbow();
        }
    }
    
    public void InvisibleRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.ICred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.ICgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.ICblue.setValue(hsBtoRGB & 0xFF);
    }
    
    public void InvisibleOutlineRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.IOred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.IOgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.IOblue.setValue(hsBtoRGB & 0xFF);
    }
    
    public NameTags() {
        super("NameTags", "Renders info about the player on a NameTag", Module.Category.RENDER, false, false, false);
        this.rect = (Setting<Boolean>)this.register(new Setting("Rectangle", (T)true));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (T)true));
        this.reversed = (Setting<Boolean>)this.register(new Setting("ArmorReversed", (T)false, p0 -> this.armor.getValue()));
        this.health = (Setting<Boolean>)this.register(new Setting("Health", (T)true));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (T)true));
        this.gamemode = (Setting<Boolean>)this.register(new Setting("Gamemode", (T)true));
        this.entityID = (Setting<Boolean>)this.register(new Setting("EntityID", (T)false));
        this.heldStackName = (Setting<Boolean>)this.register(new Setting("StackName", (T)true));
        this.max = (Setting<Boolean>)this.register(new Setting("Max", (T)false));
        this.maxText = (Setting<Boolean>)this.register(new Setting("NoMaxText", (T)false, p0 -> this.max.getValue()));
        this.Mred = (Setting<Integer>)this.register(new Setting("Max-Red", (T)178, (T)0, (T)255, p0 -> this.max.getValue()));
        this.Mgreen = (Setting<Integer>)this.register(new Setting("Max-Green", (T)52, (T)0, (T)255, p0 -> this.max.getValue()));
        this.Mblue = (Setting<Integer>)this.register(new Setting("Max-Blue", (T)57, (T)0, (T)255, p0 -> this.max.getValue()));
        this.size = (Setting<Float>)this.register(new Setting("Size", (T)2.5f, (T)0.1f, (T)15.0f));
        this.scaleing = (Setting<Boolean>)this.register(new Setting("Scale", (T)true));
        this.smartScale = (Setting<Boolean>)this.register(new Setting("SmartScale", (T)true, p0 -> this.scaleing.getValue()));
        this.factor = (Setting<Float>)this.register(new Setting("Factor", (T)0.3f, (T)0.1f, (T)1.0f, p0 -> this.scaleing.getValue()));
        this.textcolor = (Setting<Boolean>)this.register(new Setting("TextColor", (T)true));
        this.NCRainbow = (Setting<Boolean>)this.register(new Setting("Text-Rainbow", (T)true, p0 -> this.textcolor.getValue()));
        this.NCred = (Setting<Integer>)this.register(new Setting("Text-Red", (T)255, (T)0, (T)255, p0 -> this.textcolor.getValue()));
        this.NCgreen = (Setting<Integer>)this.register(new Setting("Text-Green", (T)255, (T)0, (T)255, p0 -> this.textcolor.getValue()));
        this.NCblue = (Setting<Integer>)this.register(new Setting("Text-Blue", (T)255, (T)0, (T)255, p0 -> this.textcolor.getValue()));
        this.outline = (Setting<Boolean>)this.register(new Setting("Outline", (T)true));
        this.ORainbow = (Setting<Boolean>)this.register(new Setting("Outline-Rainbow", (T)false, p0 -> this.outline.getValue()));
        this.Owidth = (Setting<Float>)this.register(new Setting("Outline-Width", (T)1.3f, (T)0.0f, (T)5.0f, p0 -> this.outline.getValue()));
        this.Ored = (Setting<Integer>)this.register(new Setting("Outline-Red", (T)255, (T)0, (T)255, p0 -> this.outline.getValue()));
        this.Ogreen = (Setting<Integer>)this.register(new Setting("Outline-Green", (T)255, (T)0, (T)255, p0 -> this.outline.getValue()));
        this.Oblue = (Setting<Integer>)this.register(new Setting("Outline-Blue", (T)255, (T)0, (T)255, p0 -> this.outline.getValue()));
        this.friendcolor = (Setting<Boolean>)this.register(new Setting("FriendColor", (T)true));
        this.FCRainbow = (Setting<Boolean>)this.register(new Setting("Friend-Rainbow", (T)false, p0 -> this.friendcolor.getValue()));
        this.FCred = (Setting<Integer>)this.register(new Setting("Friend-Red", (T)0, (T)0, (T)255, p0 -> this.friendcolor.getValue()));
        this.FCgreen = (Setting<Integer>)this.register(new Setting("Friend-Green", (T)213, (T)0, (T)255, p0 -> this.friendcolor.getValue()));
        this.FCblue = (Setting<Integer>)this.register(new Setting("Friend-Blue", (T)255, (T)0, (T)255, p0 -> this.friendcolor.getValue()));
        this.FORainbow = (Setting<Boolean>)this.register(new Setting("FriendOutline-Rainbow", (T)false, p0 -> this.outline.getValue() && this.friendcolor.getValue()));
        this.FOred = (Setting<Integer>)this.register(new Setting("FriendOutline-Red", (T)0, (T)0, (T)255, p0 -> this.outline.getValue() && this.friendcolor.getValue()));
        this.FOgreen = (Setting<Integer>)this.register(new Setting("FriendOutline-Green", (T)213, (T)0, (T)255, p0 -> this.outline.getValue() && this.friendcolor.getValue()));
        this.FOblue = (Setting<Integer>)this.register(new Setting("FriendOutline-Blue", (T)255, (T)0, (T)255, p0 -> this.outline.getValue() && this.friendcolor.getValue()));
        this.sneakcolor = (Setting<Boolean>)this.register(new Setting("Sneak", (T)false));
        this.sneak = (Setting<Boolean>)this.register(new Setting("EnableSneak", (T)true, p0 -> this.sneakcolor.getValue()));
        this.SCRainbow = (Setting<Boolean>)this.register(new Setting("Sneak-Rainbow", (T)false, p0 -> this.sneakcolor.getValue()));
        this.SCred = (Setting<Integer>)this.register(new Setting("Sneak-Red", (T)245, (T)0, (T)255, p0 -> this.sneakcolor.getValue()));
        this.SCgreen = (Setting<Integer>)this.register(new Setting("Sneak-Green", (T)0, (T)0, (T)255, p0 -> this.sneakcolor.getValue()));
        this.SCblue = (Setting<Integer>)this.register(new Setting("Sneak-Blue", (T)122, (T)0, (T)255, p0 -> this.sneakcolor.getValue()));
        this.SORainbow = (Setting<Boolean>)this.register(new Setting("SneakOutline-Rainbow", (T)false, p0 -> this.outline.getValue() && this.sneakcolor.getValue()));
        this.SOred = (Setting<Integer>)this.register(new Setting("SneakOutline-Red", (T)245, (T)0, (T)255, p0 -> this.outline.getValue() && this.sneakcolor.getValue()));
        this.SOgreen = (Setting<Integer>)this.register(new Setting("SneakOutline-Green", (T)0, (T)0, (T)255, p0 -> this.outline.getValue() && this.sneakcolor.getValue()));
        this.SOblue = (Setting<Integer>)this.register(new Setting("SneakOutline-Blue", (T)122, (T)0, (T)255, p0 -> this.outline.getValue() && this.sneakcolor.getValue()));
        this.invisiblescolor = (Setting<Boolean>)this.register(new Setting("InvisiblesColor", (T)false));
        this.invisibles = (Setting<Boolean>)this.register(new Setting("EnableInvisibles", (T)true, p0 -> this.invisiblescolor.getValue()));
        this.ICRainbow = (Setting<Boolean>)this.register(new Setting("Invisible-Rainbow", (T)false, p0 -> this.invisiblescolor.getValue()));
        this.ICred = (Setting<Integer>)this.register(new Setting("Invisible-Red", (T)148, (T)0, (T)255, p0 -> this.invisiblescolor.getValue()));
        this.ICgreen = (Setting<Integer>)this.register(new Setting("Invisible-Green", (T)148, (T)0, (T)255, p0 -> this.invisiblescolor.getValue()));
        this.ICblue = (Setting<Integer>)this.register(new Setting("Invisible-Blue", (T)148, (T)0, (T)255, p0 -> this.invisiblescolor.getValue()));
        this.IORainbow = (Setting<Boolean>)this.register(new Setting("InvisibleOutline-Rainbow", (T)false, p0 -> this.outline.getValue() && this.invisiblescolor.getValue()));
        this.IOred = (Setting<Integer>)this.register(new Setting("InvisibleOutline-Red", (T)148, (T)0, (T)255, p0 -> this.outline.getValue() && this.invisiblescolor.getValue()));
        this.IOgreen = (Setting<Integer>)this.register(new Setting("InvisibleOutline-Green", (T)148, (T)0, (T)255, p0 -> this.outline.getValue() && this.invisiblescolor.getValue()));
        this.IOblue = (Setting<Integer>)this.register(new Setting("InvisibleOutline-Blue", (T)148, (T)0, (T)255, p0 -> this.outline.getValue() && this.invisiblescolor.getValue()));
    }
    
    public void FriendRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.FCred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.FCgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.FCblue.setValue(hsBtoRGB & 0xFF);
    }
    
    public void SneakOutlineRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.SOred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.SOgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.SOblue.setValue(hsBtoRGB & 0xFF);
    }
    
    private void renderNameTag(final EntityPlayer entityPlayer, final double n, final double n2, final double n3, final float n4) {
        final double n5 = n2 + (entityPlayer.isSneaking() ? 0.5 : 0.7);
        final Entity getRenderViewEntity = NameTags.mc.getRenderViewEntity();
        assert getRenderViewEntity != null;
        final double posX = getRenderViewEntity.posX;
        final double posY = getRenderViewEntity.posY;
        final double posZ = getRenderViewEntity.posZ;
        getRenderViewEntity.posX = this.interpolate(getRenderViewEntity.prevPosX, getRenderViewEntity.posX, n4);
        getRenderViewEntity.posY = this.interpolate(getRenderViewEntity.prevPosY, getRenderViewEntity.posY, n4);
        getRenderViewEntity.posZ = this.interpolate(getRenderViewEntity.prevPosZ, getRenderViewEntity.posZ, n4);
        final String displayTag = this.getDisplayTag(entityPlayer);
        final double getDistance = getRenderViewEntity.getDistance(n + NameTags.mc.getRenderManager().viewerPosX, n2 + NameTags.mc.getRenderManager().viewerPosY, n3 + NameTags.mc.getRenderManager().viewerPosZ);
        final int n6 = this.renderer.getStringWidth(displayTag) / 2;
        double n7 = (0.0018 + this.size.getValue() * (getDistance * this.factor.getValue())) / 1000.0;
        if (getDistance <= 8.0 && this.smartScale.getValue()) {
            n7 = 0.0245;
        }
        if (!this.scaleing.getValue()) {
            n7 = this.size.getValue() / 100.0;
        }
        GlStateManager.pushMatrix();
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, -1500000.0f);
        GlStateManager.disableLighting();
        GlStateManager.translate((float)n, (float)n5 + 1.4f, (float)n3);
        GlStateManager.rotate(-NameTags.mc.getRenderManager().playerViewY, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(NameTags.mc.getRenderManager().playerViewX, (NameTags.mc.gameSettings.thirdPersonView == 2) ? -1.0f : 1.0f, 0.0f, 0.0f);
        GlStateManager.scale(-n7, -n7, n7);
        GlStateManager.disableDepth();
        GlStateManager.enableBlend();
        GlStateManager.enableBlend();
        if (this.rect.getValue()) {
            this.drawRect((float)(-n6 - 2), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT + 1)), n6 + 2.0f, 1.5f, 1426063360);
        }
        if (this.outline.getValue()) {
            this.drawOutlineRect((float)(-n6 - 2), (float)(-(NameTags.mc.fontRenderer.FONT_HEIGHT + 1)), n6 + 2.0f, 1.5f, this.getOutlineColor(entityPlayer));
        }
        GlStateManager.disableBlend();
        final ItemStack copy = entityPlayer.getHeldItemMainhand().copy();
        if (copy.hasEffect() && (copy.getItem() instanceof ItemTool || copy.getItem() instanceof ItemArmor)) {
            copy.stackSize = 1;
        }
        if (this.heldStackName.getValue() && !copy.isEmpty && copy.getItem() != Items.AIR) {
            final String getDisplayName = copy.getDisplayName();
            final int n8 = this.renderer.getStringWidth(getDisplayName) / 2;
            GL11.glPushMatrix();
            GL11.glScalef(0.75f, 0.75f, 0.0f);
            this.renderer.drawStringWithShadow(getDisplayName, (float)(-n8), -(this.getBiggestArmorTag(entityPlayer) + 20.0f), -1);
            GL11.glScalef(1.5f, 1.5f, 1.0f);
            GL11.glPopMatrix();
        }
        if (this.armor.getValue()) {
            GlStateManager.pushMatrix();
            int n9 = -6;
            int n10 = 0;
            for (final ItemStack itemStack : entityPlayer.inventory.armorInventory) {
                if (itemStack != null) {
                    n9 -= 8;
                    if (itemStack.getItem() == Items.AIR) {
                        continue;
                    }
                    ++n10;
                }
            }
            n9 -= 8;
            final ItemStack copy2 = entityPlayer.getHeldItemOffhand().copy();
            if (copy2.hasEffect() && (copy2.getItem() instanceof ItemTool || copy2.getItem() instanceof ItemArmor)) {
                copy2.stackSize = 1;
            }
            this.renderItemStack(copy2, n9, -26);
            n9 += 16;
            if (this.reversed.getValue()) {
                for (int i = 0; i <= 3; ++i) {
                    final ItemStack itemStack2 = (ItemStack)entityPlayer.inventory.armorInventory.get(i);
                    if (itemStack2 != null && itemStack2.getItem() != Items.AIR) {
                        itemStack2.copy();
                        this.renderItemStack(itemStack2, n9, -26);
                        n9 += 16;
                    }
                }
            }
            else {
                for (int j = 3; j >= 0; --j) {
                    final ItemStack itemStack3 = (ItemStack)entityPlayer.inventory.armorInventory.get(j);
                    if (itemStack3 != null && itemStack3.getItem() != Items.AIR) {
                        itemStack3.copy();
                        this.renderItemStack(itemStack3, n9, -26);
                        n9 += 16;
                    }
                }
            }
            this.renderItemStack(copy, n9, -26);
            GlStateManager.popMatrix();
        }
        this.renderer.drawStringWithShadow(displayTag, (float)(-n6), (float)(-(this.renderer.getFontHeight() - 1)), this.getDisplayColor(entityPlayer));
        getRenderViewEntity.posX = posX;
        getRenderViewEntity.posY = posY;
        getRenderViewEntity.posZ = posZ;
        GlStateManager.enableDepth();
        GlStateManager.disableBlend();
        GlStateManager.disablePolygonOffset();
        GlStateManager.doPolygonOffset(1.0f, 1500000.0f);
        GlStateManager.popMatrix();
    }
    
    public void SneakColorRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.SCred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.SCgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.SCblue.setValue(hsBtoRGB & 0xFF);
    }
    
    private void renderEnchantmentText(final ItemStack itemStack, final int n, final int n2) {
        int n3 = n2 - 8;
        if (itemStack.getItem() == Items.GOLDEN_APPLE && itemStack.hasEffect()) {
            this.renderer.drawStringWithShadow("god", (float)(n * 2), (float)n3, -3977919);
            n3 -= 8;
        }
        final NBTTagList getEnchantmentTagList = itemStack.getEnchantmentTagList();
        if (getEnchantmentTagList.tagCount() > 2 && this.max.getValue()) {
            if (this.maxText.getValue()) {
                this.renderer.drawStringWithShadow("", (float)(n * 2), (float)n3, ColorHolder.toHex(this.Mred.getValue(), this.Mgreen.getValue(), this.Mblue.getValue()));
                n3 -= 8;
            }
            else {
                this.renderer.drawStringWithShadow("max", (float)(n * 2), (float)n3, ColorHolder.toHex(this.Mred.getValue(), this.Mgreen.getValue(), this.Mblue.getValue()));
                n3 -= 8;
            }
        }
        else {
            for (int i = 0; i < getEnchantmentTagList.tagCount(); ++i) {
                final short getShort = getEnchantmentTagList.getCompoundTagAt(i).getShort("id");
                final short getShort2 = getEnchantmentTagList.getCompoundTagAt(i).getShort("lvl");
                final Enchantment getEnchantmentByID = Enchantment.getEnchantmentByID((int)getShort);
                if (getEnchantmentByID != null) {
                    this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(getEnchantmentByID.isCurse() ? String.valueOf(new StringBuilder().append(TextFormatting.RED).append(getEnchantmentByID.getTranslatedName((int)getShort2).substring(0, 4).toLowerCase())) : getEnchantmentByID.getTranslatedName((int)getShort2).substring(0, 2).toLowerCase()).append(getShort2)), (float)(n * 2), (float)n3, -1);
                    n3 -= 8;
                }
            }
        }
        if (DamageUtil.hasDurability(itemStack)) {
            final int n4 = itemStack.getMaxDamage() - itemStack.getItemDamage();
            final int j = 100 - (int)((1.0f - (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage()) * 100.0f);
            String str;
            if (j >= 60) {
                str = TextUtil.GREEN;
            }
            else if (j >= 25) {
                str = TextUtil.YELLOW;
            }
            else {
                str = TextUtil.RED;
            }
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(str).append(j).append("%")), (float)(n * 2), (float)n3, -1);
        }
    }
    
    public void OutlineRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.Ored.setValue(hsBtoRGB >> 16 & 0xFF);
        this.Ogreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.Oblue.setValue(hsBtoRGB & 0xFF);
    }
    
    public void drawRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.Owidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(7, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    static {
        NameTags.INSTANCE = new NameTags();
    }
    
    private int getDisplayColor(final EntityPlayer entityPlayer) {
        int n = ColorHolder.toHex(this.NCred.getValue(), this.NCgreen.getValue(), this.NCblue.getValue());
        if (OyVey.friendManager.isFriend(entityPlayer)) {
            return ColorHolder.toHex(this.FCred.getValue(), this.FCgreen.getValue(), this.FCblue.getValue());
        }
        if (entityPlayer.isInvisible() && this.invisibles.getValue()) {
            n = ColorHolder.toHex(this.ICred.getValue(), this.ICgreen.getValue(), this.ICblue.getValue());
        }
        else if (entityPlayer.isSneaking() && this.sneak.getValue()) {
            n = ColorHolder.toHex(this.SCred.getValue(), this.SCgreen.getValue(), this.SCblue.getValue());
        }
        return n;
    }
    
    private double interpolate(final double n, final double n2, final float n3) {
        return n + (n2 - n) * n3;
    }
    
    public void TextRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.NCred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.NCgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.NCblue.setValue(hsBtoRGB & 0xFF);
    }
    
    public void drawOutlineRect(final float n, final float n2, final float n3, final float n4, final int n5) {
        final float n6 = (n5 >> 24 & 0xFF) / 255.0f;
        final float n7 = (n5 >> 16 & 0xFF) / 255.0f;
        final float n8 = (n5 >> 8 & 0xFF) / 255.0f;
        final float n9 = (n5 & 0xFF) / 255.0f;
        final Tessellator getInstance = Tessellator.getInstance();
        final BufferBuilder getBuffer = getInstance.getBuffer();
        GlStateManager.enableBlend();
        GlStateManager.disableTexture2D();
        GlStateManager.glLineWidth((float)this.Owidth.getValue());
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        getBuffer.begin(2, DefaultVertexFormats.POSITION_COLOR);
        getBuffer.pos((double)n, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n4, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n3, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getBuffer.pos((double)n, (double)n2, 0.0).color(n7, n8, n9, n6).endVertex();
        getInstance.draw();
        GlStateManager.enableTexture2D();
        GlStateManager.disableBlend();
    }
    
    public static NameTags getInstance() {
        if (NameTags.INSTANCE == null) {
            NameTags.INSTANCE = new NameTags();
        }
        return NameTags.INSTANCE;
    }
    
    private int getOutlineColor(final EntityPlayer entityPlayer) {
        int n = ColorHolder.toHex(this.Ored.getValue(), this.Ogreen.getValue(), this.Oblue.getValue());
        if (OyVey.friendManager.isFriend(entityPlayer)) {
            n = ColorHolder.toHex(this.FOred.getValue(), this.FOgreen.getValue(), this.FOblue.getValue());
        }
        else if (entityPlayer.isInvisible() && this.invisibles.getValue()) {
            n = ColorHolder.toHex(this.IOred.getValue(), this.IOgreen.getValue(), this.IOblue.getValue());
        }
        else if (entityPlayer.isSneaking() && this.sneak.getValue()) {
            n = ColorHolder.toHex(this.SOred.getValue(), this.SOgreen.getValue(), this.SOblue.getValue());
        }
        return n;
    }
    
    public void FriendOutlineRainbow() {
        final int hsBtoRGB = Color.HSBtoRGB((new float[] { System.currentTimeMillis() % 11520L / 11520.0f })[0], 0.8f, 0.8f);
        this.FOred.setValue(hsBtoRGB >> 16 & 0xFF);
        this.FOgreen.setValue(hsBtoRGB >> 8 & 0xFF);
        this.FOblue.setValue(hsBtoRGB & 0xFF);
    }
    
    private void renderItemStack(final ItemStack itemStack, final int n, final int n2) {
        GlStateManager.pushMatrix();
        GlStateManager.depthMask(true);
        GlStateManager.clear(256);
        RenderHelper.enableStandardItemLighting();
        NameTags.mc.getRenderItem().zLevel = -150.0f;
        GlStateManager.disableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.disableCull();
        NameTags.mc.getRenderItem().renderItemAndEffectIntoGUI(itemStack, n, n2);
        NameTags.mc.getRenderItem().renderItemOverlays(NameTags.mc.fontRenderer, itemStack, n, n2);
        NameTags.mc.getRenderItem().zLevel = 0.0f;
        RenderHelper.disableStandardItemLighting();
        GlStateManager.enableCull();
        GlStateManager.enableAlpha();
        GlStateManager.scale(0.5f, 0.5f, 0.5f);
        GlStateManager.disableDepth();
        this.renderEnchantmentText(itemStack, n, n2);
        GlStateManager.enableDepth();
        GlStateManager.scale(2.0f, 2.0f, 2.0f);
        GlStateManager.popMatrix();
    }
    
    private String getDisplayTag(final EntityPlayer entityPlayer) {
        String getFormattedText = entityPlayer.getDisplayName().getFormattedText();
        if (getFormattedText.contains(NameTags.mc.getSession().getUsername())) {
            getFormattedText = "You";
        }
        if (!this.health.getValue()) {
            return getFormattedText;
        }
        final float health = EntityUtil.getHealth((Entity)entityPlayer);
        String s;
        if (health > 18.0f) {
            s = TextUtil.GREEN;
        }
        else if (health > 16.0f) {
            s = TextUtil.DARK_GREEN;
        }
        else if (health > 12.0f) {
            s = TextUtil.YELLOW;
        }
        else if (health > 8.0f) {
            s = TextUtil.RED;
        }
        else if (health > 5.0f) {
            s = TextUtil.DARK_RED;
        }
        else {
            s = TextUtil.DARK_RED;
        }
        String value = "";
        if (this.ping.getValue()) {
            try {
                value = String.valueOf(new StringBuilder().append(value).append(Objects.requireNonNull(NameTags.mc.getConnection()).getPlayerInfo(entityPlayer.getUniqueID()).getResponseTime()).append("ms "));
            }
            catch (Exception ex) {}
        }
        String value2 = "";
        if (this.entityID.getValue()) {
            value2 = String.valueOf(new StringBuilder().append(value2).append("ID: ").append(entityPlayer.getEntityId()).append(" "));
        }
        String s2 = "";
        if (this.gamemode.getValue()) {
            if (entityPlayer.isCreative()) {
                s2 = String.valueOf(new StringBuilder().append(s2).append("[C] "));
            }
            else if (entityPlayer.isSpectator() || entityPlayer.isInvisible()) {
                s2 = String.valueOf(new StringBuilder().append(s2).append("[I] "));
            }
            else {
                s2 = String.valueOf(new StringBuilder().append(s2).append("[S] "));
            }
        }
        String str;
        if (Math.floor(health) == health) {
            str = String.valueOf(new StringBuilder().append(getFormattedText).append(s).append(" ").append((health > 0.0f) ? Integer.valueOf((int)Math.floor(health)) : "dead"));
        }
        else {
            str = String.valueOf(new StringBuilder().append(getFormattedText).append(s).append(" ").append((health > 0.0f) ? Integer.valueOf((int)health) : "dead"));
        }
        return String.valueOf(new StringBuilder().append(" ").append(value).append(value2).append(s2).append(str).append(" "));
    }
}

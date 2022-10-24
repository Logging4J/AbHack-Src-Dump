//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.client;

import me.abHack.features.modules.*;
import me.abHack.features.setting.*;
import net.minecraft.item.*;
import net.minecraft.init.*;
import java.util.function.*;
import me.abHack.*;
import com.mojang.realmsclient.gui.*;
import net.minecraft.client.*;
import net.minecraft.potion.*;
import net.minecraft.client.multiplayer.*;
import java.text.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import java.util.*;
import net.minecraft.entity.player.*;
import net.minecraft.client.gui.*;
import net.minecraftforge.client.event.*;
import net.minecraft.util.text.*;
import me.abHack.event.events.*;
import me.abHack.util.*;
import net.minecraft.entity.*;
import net.minecraft.client.renderer.*;
import net.minecraft.client.entity.*;
import net.minecraft.client.renderer.entity.*;

public class HUD extends Module
{
    private final /* synthetic */ Setting<Boolean> speed;
    public /* synthetic */ Setting<Integer> GapplesY;
    public /* synthetic */ Setting<Integer> playerViewerY;
    private final /* synthetic */ Setting<Boolean> exp;
    private final /* synthetic */ Setting<Boolean> lag;
    private static /* synthetic */ HUD INSTANCE;
    public /* synthetic */ Setting<Boolean> time;
    public /* synthetic */ Setting<RenderingMode> renderingMode;
    public /* synthetic */ Setting<Integer> FriendListY;
    private final /* synthetic */ Setting<Boolean> grayNess;
    private final /* synthetic */ Setting<Boolean> tps;
    private final /* synthetic */ Setting<Boolean> server;
    private final /* synthetic */ Setting<Boolean> potions;
    public /* synthetic */ Setting<TextUtil.Color> commandColor;
    private /* synthetic */ int color;
    public /* synthetic */ Setting<Boolean> magenDavid;
    public /* synthetic */ Setting<Integer> lagTime;
    private final /* synthetic */ Setting<Boolean> crystals;
    private /* synthetic */ boolean shouldIncrement;
    public /* synthetic */ Setting<Integer> waterMarkY;
    public /* synthetic */ Setting<Boolean> FriendList;
    private /* synthetic */ int hitMarkerTimer;
    private final /* synthetic */ Timer timer;
    public /* synthetic */ Setting<Integer> crystalY;
    public /* synthetic */ Setting<Integer> crystalX;
    private final /* synthetic */ Setting<Boolean> arrayList;
    private final /* synthetic */ Setting<Boolean> greeter;
    public /* synthetic */ Setting<TextUtil.Color> bracketColor;
    public /* synthetic */ Setting<Boolean> playerViewer;
    private final /* synthetic */ Setting<Boolean> gapples;
    private final /* synthetic */ Setting<Boolean> renderingUp;
    private final /* synthetic */ Setting<Boolean> armor;
    private final /* synthetic */ Setting<Boolean> direction;
    private final /* synthetic */ Setting<Boolean> waterMark;
    private final /* synthetic */ Setting<Boolean> coords;
    private final /* synthetic */ Map<String, Integer> players;
    public /* synthetic */ Setting<Boolean> Info;
    public /* synthetic */ Setting<Integer> expX;
    public /* synthetic */ Setting<Integer> GapplesX;
    public /* synthetic */ Setting<Boolean> notifyToggles;
    public /* synthetic */ Setting<Integer> playerViewerX;
    private final /* synthetic */ Setting<Boolean> ping;
    public /* synthetic */ Setting<Integer> expY;
    private final /* synthetic */ Setting<Boolean> fps;
    private final /* synthetic */ Setting<Boolean> totems;
    public /* synthetic */ Setting<Float> playerScale;
    private static final /* synthetic */ ItemStack totem;
    public /* synthetic */ Setting<Boolean> chattime;
    private final /* synthetic */ Setting<Boolean> hitMarkers;
    
    public static HUD getInstance() {
        if (HUD.INSTANCE == null) {
            HUD.INSTANCE = new HUD();
        }
        return HUD.INSTANCE;
    }
    
    public void renderTotemHUD() {
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        int sum = HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.TOTEM_OF_UNDYING).mapToInt(ItemStack::getCount).sum();
        if (HUD.mc.player.getHeldItemOffhand().getItem() == Items.TOTEM_OF_UNDYING) {
            sum += HUD.mc.player.getHeldItemOffhand().getCount();
        }
        if (sum > 0) {
            GlStateManager.enableTexture2D();
            final int n = scaledWidth / 2;
            final int n2 = scaledHeight - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
            final int n3 = n - 189 + 180 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(HUD.totem, n3, n2);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, HUD.totem, n3, n2, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(sum).append("")), (float)(n3 + 19 - 2 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(sum).append("")))), (float)(n2 + 9), 16777215);
            GlStateManager.enableDepth();
            GlStateManager.disableLighting();
        }
    }
    
    public void renderArmorHUD(final boolean b) {
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        GlStateManager.enableTexture2D();
        final int n = scaledWidth / 2;
        int n2 = 0;
        final int n3 = scaledHeight - 55 - ((HUD.mc.player.isInWater() && HUD.mc.playerController.gameIsSurvivalOrAdventure()) ? 10 : 0);
        for (final ItemStack itemStack : HUD.mc.player.inventory.armorInventory) {
            ++n2;
            if (itemStack.isEmpty()) {
                continue;
            }
            final int n4 = n - 90 + (9 - n2) * 20 + 2;
            GlStateManager.enableDepth();
            RenderUtil.itemRender.zLevel = 200.0f;
            RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack, n4, n3);
            RenderUtil.itemRender.renderItemOverlayIntoGUI(HUD.mc.fontRenderer, itemStack, n4, n3, "");
            RenderUtil.itemRender.zLevel = 0.0f;
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.disableDepth();
            final String s = (itemStack.getCount() > 1) ? String.valueOf(new StringBuilder().append(itemStack.getCount()).append("")) : "";
            this.renderer.drawStringWithShadow(s, (float)(n4 + 19 - 2 - this.renderer.getStringWidth(s)), (float)(n3 + 9), 16777215);
            if (!b) {
                continue;
            }
            final int n5 = itemStack.getMaxDamage() - itemStack.getItemDamage();
            final float n6 = (itemStack.getMaxDamage() - (float)itemStack.getItemDamage()) / itemStack.getMaxDamage();
            final float n7 = 1.0f - n6;
            int n8 = 100 - (int)(n7 * 100.0f);
            if (n8 == -2147483547) {
                n8 = 100;
            }
            this.renderer.drawStringWithShadow(String.valueOf(new StringBuilder().append(n8).append("")), (float)(n4 + 8 - this.renderer.getStringWidth(String.valueOf(new StringBuilder().append(n8).append(""))) / 2), (float)(n3 - 11), ColorUtil.toRGBA((int)(n7 * 255.0f), (int)(n6 * 255.0f), 0));
        }
        GlStateManager.enableDepth();
        GlStateManager.disableLighting();
    }
    
    @Override
    public void onLoad() {
        OyVey.commandManager.setClientMessage(this.getCommandMessage());
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final int scaledWidth = this.renderer.scaledWidth;
        final int scaledHeight = this.renderer.scaledHeight;
        this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
        if (this.waterMark.getValue()) {
            final String s = "ab-Hack v0.0.1";
            if (ClickGui.getInstance().rainbow.getValue()) {
                if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                    this.renderer.drawString(s, 2.0f, this.waterMarkY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                }
                else {
                    final int[] array = { 1 };
                    final char[] charArray = s.toCharArray();
                    float n = 0.0f;
                    for (final char c : charArray) {
                        this.renderer.drawString(String.valueOf(c), 2.0f + n, this.waterMarkY.getValue(), ColorUtil.rainbow(array[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                        n += this.renderer.getStringWidth(String.valueOf(c));
                        ++array[0];
                    }
                }
            }
            else {
                this.renderer.drawString(s, 2.0f, this.waterMarkY.getValue(), this.color, true);
            }
        }
        final String value = String.valueOf(new StringBuilder().append("Crystals: ").append(HUD.mc.player.inventory.mainInventory.stream().filter(itemStack -> itemStack.getItem() == Items.END_CRYSTAL).mapToInt(ItemStack::getCount).sum()));
        final String value2 = String.valueOf(new StringBuilder().append("Exp: ").append(HUD.mc.player.inventory.mainInventory.stream().filter(itemStack2 -> itemStack2.getItem() == Items.EXPERIENCE_BOTTLE).mapToInt(ItemStack::getCount).sum()));
        final String value3 = String.valueOf(new StringBuilder().append("Gapples: ").append(HUD.mc.player.inventory.mainInventory.stream().filter(itemStack3 -> itemStack3.getItem() == Items.GOLDEN_APPLE).mapToInt(ItemStack::getCount).sum()));
        this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
        int n2 = 10;
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                if (this.Info.getValue() && this.crystals.getValue()) {
                    this.renderer.drawString(value, this.crystalX.getValue(), this.crystalY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n2 += 12;
                }
                if (this.Info.getValue() && this.exp.getValue()) {
                    this.renderer.drawString(value2, this.expX.getValue(), this.expY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n2 += 12;
                }
                if (this.Info.getValue() && this.gapples.getValue()) {
                    this.renderer.drawString(value3, this.GapplesX.getValue(), this.GapplesY.getValue(), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n2 += 12;
                }
            }
        }
        else {
            if (this.Info.getValue() && this.crystals.getValue()) {
                this.renderer.drawString(value, this.crystalX.getValue(), this.crystalY.getValue(), this.color, true);
                n2 += 12;
            }
            if (this.Info.getValue() && this.exp.getValue()) {
                this.renderer.drawString(value2, this.expX.getValue(), this.expY.getValue(), this.color, true);
                n2 += 12;
            }
            if (this.Info.getValue() && this.gapples.getValue()) {
                this.renderer.drawString(value3, this.GapplesX.getValue(), this.GapplesY.getValue(), this.color, true);
                n2 += 12;
            }
        }
        this.color = ColorUtil.toRGBA(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue());
        if (this.FriendList.getValue()) {
            this.renderFriends();
        }
        final int[] array3 = { 1 };
        int n3 = (HUD.mc.currentScreen instanceof GuiChat && !this.renderingUp.getValue()) ? 14 : 0;
        if (this.arrayList.getValue()) {
            if (this.renderingUp.getValue()) {
                if (this.renderingMode.getValue() == RenderingMode.ABC) {
                    for (int j = 0; j < OyVey.moduleManager.sortedModulesABC.size(); ++j) {
                        final String s2 = OyVey.moduleManager.sortedModulesABC.get(j);
                        this.renderer.drawString(s2, (float)(scaledWidth - 2 - this.renderer.getStringWidth(s2)), (float)(2 + n3 * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        ++n3;
                        ++array3[0];
                    }
                }
                else {
                    for (int k = 0; k < OyVey.moduleManager.sortedModules.size(); ++k) {
                        final Module module = OyVey.moduleManager.sortedModules.get(k);
                        final String value4 = String.valueOf(new StringBuilder().append(module.getDisplayName()).append(ChatFormatting.GRAY).append((module.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [").append(ChatFormatting.WHITE).append(module.getDisplayInfo()).append(ChatFormatting.GRAY).append("]")) : ""));
                        this.renderer.drawString(value4, (float)(scaledWidth - 2 - this.renderer.getStringWidth(value4)), (float)(2 + n3 * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                        ++n3;
                        ++array3[0];
                    }
                }
            }
            else if (this.renderingMode.getValue() == RenderingMode.ABC) {
                for (int l = 0; l < OyVey.moduleManager.sortedModulesABC.size(); ++l) {
                    final String s3 = OyVey.moduleManager.sortedModulesABC.get(l);
                    n3 += 10;
                    this.renderer.drawString(s3, (float)(scaledWidth - 2 - this.renderer.getStringWidth(s3)), (float)(scaledHeight - n3), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
            else {
                for (int n4 = 0; n4 < OyVey.moduleManager.sortedModules.size(); ++n4) {
                    final Module module2 = OyVey.moduleManager.sortedModules.get(n4);
                    final String value5 = String.valueOf(new StringBuilder().append(module2.getDisplayName()).append(ChatFormatting.GRAY).append((module2.getDisplayInfo() != null) ? String.valueOf(new StringBuilder().append(" [").append(ChatFormatting.WHITE).append(module2.getDisplayInfo()).append(ChatFormatting.GRAY).append("]")) : ""));
                    n3 += 10;
                    this.renderer.drawString(value5, (float)(scaledWidth - 2 - this.renderer.getStringWidth(value5)), (float)(scaledHeight - n3), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
        }
        final String str = this.grayNess.getValue() ? String.valueOf(ChatFormatting.GRAY) : "";
        int n5 = (HUD.mc.currentScreen instanceof GuiChat && this.renderingUp.getValue()) ? 13 : (this.renderingUp.getValue() ? -2 : 0);
        if (this.renderingUp.getValue()) {
            if (this.potions.getValue()) {
                for (final PotionEffect potionEffect : new ArrayList<Object>(Minecraft.getMinecraft().player.getActivePotionEffects())) {
                    final String coloredPotionString = OyVey.potionManager.getColoredPotionString(potionEffect);
                    n5 += 10;
                    this.renderer.drawString(coloredPotionString, (float)(scaledWidth - this.renderer.getStringWidth(coloredPotionString) - 2), (float)(scaledHeight - 2 - n5), potionEffect.getPotion().getLiquidColor(), true);
                }
            }
            if (this.server.getValue()) {
                final String value6 = String.valueOf(new StringBuilder().append(str).append("IP ").append(ChatFormatting.WHITE).append(HUD.mc.isSingleplayer() ? "SinglePlayer" : Objects.requireNonNull(HUD.mc.getCurrentServerData()).serverIP));
                n5 += 10;
                this.renderer.drawString(value6, (float)(scaledWidth - this.renderer.getStringWidth(value6) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.speed.getValue()) {
                final String value7 = String.valueOf(new StringBuilder().append(str).append("Speed ").append(ChatFormatting.WHITE).append(OyVey.speedManager.getSpeedKpH()).append(" km/h"));
                n5 += 10;
                this.renderer.drawString(value7, (float)(scaledWidth - this.renderer.getStringWidth(value7) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.time.getValue()) {
                final String value8 = String.valueOf(new StringBuilder().append(str).append("Time ").append(ChatFormatting.WHITE).append(new SimpleDateFormat("h:mm a").format(new Date())));
                n5 += 10;
                this.renderer.drawString(value8, (float)(scaledWidth - this.renderer.getStringWidth(value8) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.tps.getValue()) {
                final String value9 = String.valueOf(new StringBuilder().append(str).append("TPS ").append(ChatFormatting.WHITE).append(OyVey.serverManager.getTPS()));
                n5 += 10;
                this.renderer.drawString(value9, (float)(scaledWidth - this.renderer.getStringWidth(value9) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            final String value10 = String.valueOf(new StringBuilder().append(str).append("FPS ").append(ChatFormatting.WHITE).append(Minecraft.debugFPS));
            String.valueOf(new StringBuilder().append(str).append("Server ").append(ChatFormatting.WHITE).append(HUD.mc.isSingleplayer() ? "SinglePlayer" : Objects.requireNonNull(HUD.mc.getCurrentServerData()).serverIP));
            final String value11 = String.valueOf(new StringBuilder().append(str).append("Ping ").append(ChatFormatting.WHITE).append(OyVey.serverManager.getPing()));
            if (this.renderer.getStringWidth(value11) > this.renderer.getStringWidth(value10)) {
                if (this.ping.getValue()) {
                    n5 += 10;
                    this.renderer.drawString(value11, (float)(scaledWidth - this.renderer.getStringWidth(value11) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
                if (this.fps.getValue()) {
                    n5 += 10;
                    this.renderer.drawString(value10, (float)(scaledWidth - this.renderer.getStringWidth(value10) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
            else {
                if (this.fps.getValue()) {
                    n5 += 10;
                    this.renderer.drawString(value10, (float)(scaledWidth - this.renderer.getStringWidth(value10) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
                if (this.ping.getValue()) {
                    n5 += 10;
                    this.renderer.drawString(value11, (float)(scaledWidth - this.renderer.getStringWidth(value11) - 2), (float)(scaledHeight - 2 - n5), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
        }
        else {
            if (this.potions.getValue()) {
                for (final PotionEffect potionEffect2 : new ArrayList<Object>(Minecraft.getMinecraft().player.getActivePotionEffects())) {
                    final String coloredPotionString2 = OyVey.potionManager.getColoredPotionString(potionEffect2);
                    this.renderer.drawString(coloredPotionString2, (float)(scaledWidth - this.renderer.getStringWidth(coloredPotionString2) - 2), (float)(2 + n5++ * 10), potionEffect2.getPotion().getLiquidColor(), true);
                }
            }
            if (this.server.getValue()) {
                final String value12 = String.valueOf(new StringBuilder().append(str).append("IP ").append(ChatFormatting.WHITE).append(HUD.mc.isSingleplayer() ? "SinglePlayer" : Objects.requireNonNull(HUD.mc.getCurrentServerData()).serverIP));
                this.renderer.drawString(value12, (float)(scaledWidth - this.renderer.getStringWidth(value12) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.speed.getValue()) {
                final String value13 = String.valueOf(new StringBuilder().append(str).append("Speed ").append(ChatFormatting.WHITE).append(OyVey.speedManager.getSpeedKpH()).append(" km/h"));
                this.renderer.drawString(value13, (float)(scaledWidth - this.renderer.getStringWidth(value13) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.time.getValue()) {
                final String value14 = String.valueOf(new StringBuilder().append(str).append("Time ").append(ChatFormatting.WHITE).append(new SimpleDateFormat("h:mm a").format(new Date())));
                this.renderer.drawString(value14, (float)(scaledWidth - this.renderer.getStringWidth(value14) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            if (this.tps.getValue()) {
                final String value15 = String.valueOf(new StringBuilder().append(str).append("TPS ").append(ChatFormatting.WHITE).append(OyVey.serverManager.getTPS()));
                this.renderer.drawString(value15, (float)(scaledWidth - this.renderer.getStringWidth(value15) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                ++array3[0];
            }
            final String value16 = String.valueOf(new StringBuilder().append(str).append("FPS ").append(ChatFormatting.WHITE).append(Minecraft.debugFPS));
            final String value17 = String.valueOf(new StringBuilder().append(str).append("Ping ").append(ChatFormatting.WHITE).append(OyVey.serverManager.getPing()));
            if (this.renderer.getStringWidth(value17) > this.renderer.getStringWidth(value16)) {
                if (this.ping.getValue()) {
                    this.renderer.drawString(value17, (float)(scaledWidth - this.renderer.getStringWidth(value17) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
                if (this.fps.getValue()) {
                    this.renderer.drawString(value16, (float)(scaledWidth - this.renderer.getStringWidth(value16) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
            else {
                if (this.fps.getValue()) {
                    this.renderer.drawString(value16, (float)(scaledWidth - this.renderer.getStringWidth(value16) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
                if (this.ping.getValue()) {
                    this.renderer.drawString(value17, (float)(scaledWidth - this.renderer.getStringWidth(value17) - 2), (float)(2 + n5++ * 10), ((boolean)ClickGui.getInstance().rainbow.getValue()) ? ((ClickGui.getInstance().rainbowModeA.getValue() == ClickGui.rainbowModeArray.Up) ? ColorUtil.rainbow(array3[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB() : ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB()) : this.color, true);
                    ++array3[0];
                }
            }
        }
        final boolean equals = HUD.mc.world.getBiome(HUD.mc.player.getPosition()).getBiomeName().equals("Hell");
        final int m = (int)HUD.mc.player.posX;
        final int i2 = (int)HUD.mc.player.posY;
        final int i3 = (int)HUD.mc.player.posZ;
        final float n6 = equals ? 8.0f : 0.125f;
        final int i4 = (int)(HUD.mc.player.posX * n6);
        final int i5 = (int)(HUD.mc.player.posZ * n6);
        int n7 = (HUD.mc.currentScreen instanceof GuiChat) ? 14 : 0;
        final String value18 = String.valueOf(new StringBuilder().append(ChatFormatting.WHITE).append("XYZ ").append(ChatFormatting.RESET).append(equals ? String.valueOf(new StringBuilder().append(m).append(", ").append(i2).append(", ").append(i3).append(ChatFormatting.WHITE).append(" [").append(ChatFormatting.RESET).append(i4).append(", ").append(i5).append(ChatFormatting.WHITE).append("]").append(ChatFormatting.RESET)) : String.valueOf(new StringBuilder().append(m).append(", ").append(i2).append(", ").append(i3).append(ChatFormatting.WHITE).append(" [").append(ChatFormatting.RESET).append(i4).append(", ").append(i5).append(ChatFormatting.WHITE).append("]"))));
        final String s4 = this.direction.getValue() ? OyVey.rotationManager.getDirection4D(false) : "";
        final String s5 = this.coords.getValue() ? value18 : "";
        n7 += 10;
        if (ClickGui.getInstance().rainbow.getValue()) {
            final String s6 = this.coords.getValue() ? String.valueOf(new StringBuilder().append("XYZ ").append(m).append(", ").append(i2).append(", ").append(i3).append(" [").append(i4).append(", ").append(i5).append("]")) : "";
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(s4, 2.0f, (float)(scaledHeight - n7 - 11), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                this.renderer.drawString(s6, 2.0f, (float)(scaledHeight - n7), ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] array4 = { 1 };
                final char[] charArray2 = s4.toCharArray();
                float n8 = 0.0f;
                for (final char c2 : charArray2) {
                    this.renderer.drawString(String.valueOf(c2), 2.0f + n8, (float)(scaledHeight - n7 - 11), ColorUtil.rainbow(array4[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n8 += this.renderer.getStringWidth(String.valueOf(c2));
                    ++array4[0];
                }
                final int[] array6 = { 1 };
                final char[] charArray3 = s6.toCharArray();
                float n10 = 0.0f;
                for (final char c3 : charArray3) {
                    this.renderer.drawString(String.valueOf(c3), 2.0f + n10, (float)(scaledHeight - n7), ColorUtil.rainbow(array6[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n10 += this.renderer.getStringWidth(String.valueOf(c3));
                    ++array6[0];
                }
            }
        }
        else {
            this.renderer.drawString(s4, 2.0f, (float)(scaledHeight - n7 - 11), this.color, true);
            this.renderer.drawString(s5, 2.0f, (float)(scaledHeight - n7), this.color, true);
        }
        if (this.armor.getValue()) {
            this.renderArmorHUD(true);
        }
        if (this.totems.getValue()) {
            this.renderTotemHUD();
        }
        if (this.greeter.getValue()) {
            this.renderGreeter();
        }
        if (this.lag.getValue()) {
            this.renderLag();
        }
        if (this.playerViewer.getValue()) {
            this.drawPlayer();
        }
        if (!this.hitMarkers.getValue()) {
            return;
        }
        if (this.hitMarkerTimer <= 0) {
            return;
        }
        this.drawHitMarkers();
    }
    
    @SubscribeEvent
    public void onUpdateWalkingPlayer(final AttackEntityEvent attackEntityEvent) {
        this.shouldIncrement = true;
    }
    
    private void setInstance() {
        HUD.INSTANCE = this;
    }
    
    public void renderLag() {
        final int scaledWidth = this.renderer.scaledWidth;
        if (OyVey.serverManager.isServerNotResponding()) {
            final String value = String.valueOf(new StringBuilder().append(ChatFormatting.RED).append("Server not responding ").append(MathUtil.round(OyVey.serverManager.serverRespondingTime() / 1000.0f, 1)).append("s."));
            this.renderer.drawString(value, scaledWidth / 2.0f - this.renderer.getStringWidth(value) / 2.0f + 2.0f, 20.0f, this.color, true);
        }
    }
    
    public void drawTextRadar(final int n) {
        if (!this.players.isEmpty()) {
            int n2 = this.renderer.getFontHeight() + 7 + n;
            final Iterator<Map.Entry<String, Integer>> iterator = this.players.entrySet().iterator();
            while (iterator.hasNext()) {
                final String value = String.valueOf(new StringBuilder().append(iterator.next().getKey()).append(" "));
                final int n3 = this.renderer.getFontHeight() + 1;
                this.renderer.drawString(value, 2.0f, (float)n2, this.color, true);
                n2 += n3;
            }
        }
    }
    
    public HUD() {
        super("HUD", "HUD Elements rendered on your screen", Category.CLIENT, true, false, false);
        this.grayNess = (Setting<Boolean>)this.register(new Setting("Gray", (T)true));
        this.renderingUp = (Setting<Boolean>)this.register(new Setting("RenderingUp", (T)false, "Orientation of the HUD-Elements."));
        this.waterMark = (Setting<Boolean>)this.register(new Setting("Watermark", (T)false, "displays watermark"));
        this.arrayList = (Setting<Boolean>)this.register(new Setting("ActiveModules", (T)true, "Lists the active modules."));
        this.coords = (Setting<Boolean>)this.register(new Setting("Coords", (T)false, "Your current coordinates"));
        this.direction = (Setting<Boolean>)this.register(new Setting("Direction", (T)false, "The Direction you are facing."));
        this.armor = (Setting<Boolean>)this.register(new Setting("Armor", (T)true, "ArmorHUD"));
        this.totems = (Setting<Boolean>)this.register(new Setting("Totems", (T)true, "TotemHUD"));
        this.greeter = (Setting<Boolean>)this.register(new Setting("Welcomer", (T)false, "The time"));
        this.speed = (Setting<Boolean>)this.register(new Setting("Speed", (T)true, "Your Speed"));
        this.potions = (Setting<Boolean>)this.register(new Setting("Potions", (T)true, "Your Speed"));
        this.server = (Setting<Boolean>)this.register(new Setting("IP", (T)true, "Shows the server"));
        this.ping = (Setting<Boolean>)this.register(new Setting("Ping", (T)true, "Your response time to the server."));
        this.tps = (Setting<Boolean>)this.register(new Setting("TPS", (T)true, "Ticks per second of the server."));
        this.fps = (Setting<Boolean>)this.register(new Setting("FPS", (T)true, "Your frames per second."));
        this.players = new HashMap<String, Integer>();
        this.lag = (Setting<Boolean>)this.register(new Setting("LagNotifier", (T)true, "The time"));
        this.timer = new Timer();
        this.time = (Setting<Boolean>)this.register(new Setting("Time", (T)true, "The time"));
        this.chattime = (Setting<Boolean>)this.register(new Setting("ChatTime", (T)true, "Prefixes chat messages with the time"));
        this.hitMarkers = (Setting<Boolean>)this.register(new Setting("HitMarkers", (T)false));
        this.Info = (Setting<Boolean>)this.register(new Setting("Info", (T)false));
        this.crystals = (Setting<Boolean>)this.register(new Setting("Crystals", (T)true, p0 -> this.Info.getValue()));
        this.crystalX = (Setting<Integer>)this.register(new Setting("CrystalX", (T)0, (T)0, (T)900, p0 -> this.Info.getValue() && this.crystals.getValue()));
        this.crystalY = (Setting<Integer>)this.register(new Setting("CrystalY", (T)122, (T)0, (T)530, p0 -> this.Info.getValue() && this.crystals.getValue()));
        this.expX = (Setting<Integer>)this.register(new Setting("ExpX", (T)0, (T)0, (T)900, p0 -> this.Info.getValue() && this.crystals.getValue()));
        this.expY = (Setting<Integer>)this.register(new Setting("ExpY", (T)128, (T)0, (T)530, p0 -> this.Info.getValue() && this.crystals.getValue()));
        this.exp = (Setting<Boolean>)this.register(new Setting("Exp", (T)true, p0 -> this.Info.getValue()));
        this.gapples = (Setting<Boolean>)this.register(new Setting("Gapples", (T)true, p0 -> this.Info.getValue()));
        this.GapplesX = (Setting<Integer>)this.register(new Setting("GapplesX", (T)0, (T)0, (T)900, p0 -> this.Info.getValue() && this.gapples.getValue()));
        this.GapplesY = (Setting<Integer>)this.register(new Setting("GapplesY", (T)135, (T)0, (T)530, p0 -> this.Info.getValue() && this.gapples.getValue()));
        this.FriendList = (Setting<Boolean>)this.register(new Setting("FriendList", (T)Boolean.FALSE));
        this.FriendListY = (Setting<Integer>)this.register(new Setting("FriendListY", (T)173, (T)0, (T)530, p0 -> this.FriendList.getValue()));
        this.playerViewer = (Setting<Boolean>)this.register(new Setting("PlayerViewer", (T)false));
        this.playerViewerX = (Setting<Integer>)this.register(new Setting("PlayerX", (T)150, (T)0, (T)700, p0 -> this.playerViewer.getValue()));
        this.playerViewerY = (Setting<Integer>)this.register(new Setting("PlayerY", (T)60, (T)0, (T)500, p0 -> this.playerViewer.getValue()));
        this.playerScale = (Setting<Float>)this.register(new Setting("PlayerScale", (T)0.7f, (T)0.1f, (T)2.0f, p0 -> this.playerViewer.getValue()));
        this.bracketColor = (Setting<TextUtil.Color>)this.register(new Setting("BracketColor", (T)TextUtil.Color.BLUE));
        this.commandColor = (Setting<TextUtil.Color>)this.register(new Setting("NameColor", (T)TextUtil.Color.BLUE));
        this.notifyToggles = (Setting<Boolean>)this.register(new Setting("ChatNotify", (T)true, "notifys in chat"));
        this.magenDavid = (Setting<Boolean>)this.register(new Setting("FutureGui", (T)true, "draws magen david"));
        this.renderingMode = (Setting<RenderingMode>)this.register(new Setting("Ordering", (T)RenderingMode.Length));
        this.waterMarkY = (Setting<Integer>)this.register(new Setting("WatermarkPosY", (T)2, (T)0, (T)20, p0 -> this.waterMark.getValue()));
        this.lagTime = (Setting<Integer>)this.register(new Setting("LagTime", (T)1000, (T)0, (T)2000));
        this.setInstance();
    }
    
    static {
        totem = new ItemStack(Items.TOTEM_OF_UNDYING);
        HUD.INSTANCE = new HUD();
    }
    
    public String getCommandMessage() {
        return String.valueOf(new StringBuilder().append(TextUtil.coloredString("<", this.bracketColor.getPlannedValue())).append(TextUtil.coloredString("ab-Hack", this.commandColor.getPlannedValue())).append(TextUtil.coloredString(">", this.bracketColor.getPlannedValue())));
    }
    
    @Override
    public void onUpdate() {
        if (this.shouldIncrement) {
            ++this.hitMarkerTimer;
        }
        if (this.hitMarkerTimer == 10) {
            this.hitMarkerTimer = 0;
            this.shouldIncrement = false;
        }
    }
    
    private void renderFriends() {
        final ArrayList<String> list = new ArrayList<String>();
        for (final EntityPlayer entityPlayer : HUD.mc.world.playerEntities) {
            if (OyVey.friendManager.isFriend(entityPlayer.getName())) {
                list.add(entityPlayer.getName());
            }
        }
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                int intValue = this.FriendListY.getValue();
                if (list.isEmpty()) {
                    this.renderer.drawString("No friends", 0.0f, (float)intValue, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                }
                else {
                    this.renderer.drawString("Friends:", 0.0f, (float)intValue, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    intValue += 12;
                    final Iterator<Object> iterator2 = list.iterator();
                    while (iterator2.hasNext()) {
                        this.renderer.drawString(iterator2.next(), 0.0f, (float)intValue, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                        intValue += 12;
                    }
                }
            }
        }
        else {
            int intValue2 = this.FriendListY.getValue();
            if (list.isEmpty()) {
                this.renderer.drawString("No Webstas online", 0.0f, (float)intValue2, this.color, true);
            }
            else {
                this.renderer.drawString("Webstas near you:", 0.0f, (float)intValue2, this.color, true);
                intValue2 += 12;
                final Iterator<Object> iterator3 = list.iterator();
                while (iterator3.hasNext()) {
                    this.renderer.drawString(iterator3.next(), 0.0f, (float)intValue2, this.color, true);
                    intValue2 += 12;
                }
            }
        }
    }
    
    public void drawHitMarkers() {
        final ScaledResolution scaledResolution = new ScaledResolution(HUD.mc);
        RenderUtil.drawLine(scaledResolution.getScaledWidth() / 2.0f - 4.0f, scaledResolution.getScaledHeight() / 2.0f - 4.0f, scaledResolution.getScaledWidth() / 2.0f - 8.0f, scaledResolution.getScaledHeight() / 2.0f - 8.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        RenderUtil.drawLine(scaledResolution.getScaledWidth() / 2.0f + 4.0f, scaledResolution.getScaledHeight() / 2.0f - 4.0f, scaledResolution.getScaledWidth() / 2.0f + 8.0f, scaledResolution.getScaledHeight() / 2.0f - 8.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        RenderUtil.drawLine(scaledResolution.getScaledWidth() / 2.0f - 4.0f, scaledResolution.getScaledHeight() / 2.0f + 4.0f, scaledResolution.getScaledWidth() / 2.0f - 8.0f, scaledResolution.getScaledHeight() / 2.0f + 8.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255, 255));
        RenderUtil.drawLine(scaledResolution.getScaledWidth() / 2.0f + 4.0f, scaledResolution.getScaledHeight() / 2.0f + 4.0f, scaledResolution.getScaledWidth() / 2.0f + 8.0f, scaledResolution.getScaledHeight() / 2.0f + 8.0f, 1.0f, ColorUtil.toRGBA(255, 255, 255, 255));
    }
    
    @SubscribeEvent
    public void onClientChatReceived(final ClientChatReceivedEvent clientChatReceivedEvent) {
        if (this.chattime.getValue()) {
            clientChatReceivedEvent.setMessage(new TextComponentString(String.valueOf(new StringBuilder().append(ChatFormatting.LIGHT_PURPLE).append("[").append(ChatFormatting.DARK_PURPLE).append(new SimpleDateFormat("HH:mm").format(new Date())).append(ChatFormatting.LIGHT_PURPLE).append("]").append(ChatFormatting.RESET).append(" "))).appendSibling(clientChatReceivedEvent.getMessage()));
        }
    }
    
    public void renderGreeter() {
        final int scaledWidth = this.renderer.scaledWidth;
        String value = "";
        if (this.greeter.getValue()) {
            value = String.valueOf(new StringBuilder().append(value).append(MathUtil.getTimeOfDay()).append(HUD.mc.player.getDisplayNameString()));
        }
        if (ClickGui.getInstance().rainbow.getValue()) {
            if (ClickGui.getInstance().rainbowModeHud.getValue() == ClickGui.rainbowMode.Static) {
                this.renderer.drawString(value, scaledWidth / 2.0f - this.renderer.getStringWidth(value) / 2.0f + 2.0f, 2.0f, ColorUtil.rainbow(ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
            }
            else {
                final int[] array = { 1 };
                final char[] charArray = value.toCharArray();
                float n = 0.0f;
                for (final char c : charArray) {
                    this.renderer.drawString(String.valueOf(c), scaledWidth / 2.0f - this.renderer.getStringWidth(value) / 2.0f + 2.0f + n, 2.0f, ColorUtil.rainbow(array[0] * ClickGui.getInstance().rainbowHue.getValue()).getRGB(), true);
                    n += this.renderer.getStringWidth(String.valueOf(c));
                    ++array[0];
                }
            }
        }
        else {
            this.renderer.drawString(value, scaledWidth / 2.0f - this.renderer.getStringWidth(value) / 2.0f + 2.0f, 2.0f, this.color, true);
        }
    }
    
    @SubscribeEvent
    public void onSettingChange(final ClientEvent clientEvent) {
        if (clientEvent.getStage() == 2 && this.equals(clientEvent.getSetting().getFeature())) {
            OyVey.commandManager.setClientMessage(this.getCommandMessage());
        }
    }
    
    public Map<String, Integer> getTextRadarPlayers() {
        return EntityUtil.getTextRadarPlayers();
    }
    
    public void drawPlayer() {
        final EntityPlayerSP player = HUD.mc.player;
        GlStateManager.pushMatrix();
        GlStateManager.color(1.0f, 1.0f, 1.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.enableAlpha();
        GlStateManager.shadeModel(7424);
        GlStateManager.enableAlpha();
        GlStateManager.enableDepth();
        GlStateManager.rotate(0.0f, 0.0f, 5.0f, 0.0f);
        GlStateManager.enableColorMaterial();
        GlStateManager.pushMatrix();
        GlStateManager.translate((float)(this.playerViewerX.getValue() + 25), (float)(this.playerViewerY.getValue() + 25), 50.0f);
        GlStateManager.scale(-50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue(), 50.0f * this.playerScale.getValue());
        GlStateManager.rotate(180.0f, 0.0f, 0.0f, 1.0f);
        GlStateManager.rotate(135.0f, 0.0f, 1.0f, 0.0f);
        RenderHelper.enableStandardItemLighting();
        GlStateManager.rotate(-135.0f, 0.0f, 1.0f, 0.0f);
        GlStateManager.rotate(-(float)Math.atan(this.playerViewerY.getValue() / 40.0f) * 20.0f, 1.0f, 0.0f, 0.0f);
        GlStateManager.translate(0.0f, 0.0f, 0.0f);
        final RenderManager getRenderManager = HUD.mc.getRenderManager();
        getRenderManager.setPlayerViewY(180.0f);
        getRenderManager.setRenderShadow(false);
        try {
            getRenderManager.renderEntity((Entity)player, 0.0, 0.0, 0.0, 0.0f, 1.0f, false);
        }
        catch (Exception ex) {}
        getRenderManager.setRenderShadow(true);
        GlStateManager.popMatrix();
        RenderHelper.disableStandardItemLighting();
        GlStateManager.disableRescaleNormal();
        GlStateManager.setActiveTexture(OpenGlHelper.lightmapTexUnit);
        GlStateManager.disableTexture2D();
        GlStateManager.setActiveTexture(OpenGlHelper.defaultTexUnit);
        GlStateManager.depthFunc(515);
        GlStateManager.resetColor();
        GlStateManager.disableDepth();
        GlStateManager.popMatrix();
    }
    
    public enum RenderingMode
    {
        Length, 
        ABC;
    }
}

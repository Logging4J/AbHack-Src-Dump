//Deobfuscated with https://github.com/SimplyProgrammer/Minecraft-Deobfuscator3000 using mappings "C:\Users\L4J\Desktop\deobf workspace\1.12 stable mappings"!

//Decompiled by Procyon!

package me.abHack.features.modules.misc;

import me.abHack.features.modules.*;
import net.minecraft.entity.player.*;
import net.minecraftforge.event.entity.player.*;
import net.minecraftforge.fml.common.eventhandler.*;
import net.minecraft.tileentity.*;
import net.minecraft.world.*;
import net.minecraft.item.*;
import net.minecraft.inventory.*;
import me.abHack.features.modules.client.*;
import java.awt.*;
import me.abHack.util.*;
import net.minecraft.client.renderer.*;
import net.minecraft.util.*;
import net.minecraft.nbt.*;
import java.util.concurrent.*;
import java.util.*;
import me.abHack.event.events.*;

public class ShulkerViewer extends Module
{
    private static /* synthetic */ ShulkerViewer INSTANCE;
    private /* synthetic */ int textRadarY;
    public /* synthetic */ Map<EntityPlayer, Timer> playerTimers;
    private static final /* synthetic */ ResourceLocation SHULKER_GUI_TEXTURE;
    public /* synthetic */ Map<EntityPlayer, ItemStack> spiedPlayers;
    
    public static ShulkerViewer getInstance() {
        if (ShulkerViewer.INSTANCE == null) {
            ShulkerViewer.INSTANCE = new ShulkerViewer();
        }
        return ShulkerViewer.INSTANCE;
    }
    
    @SubscribeEvent(priority = EventPriority.HIGHEST)
    public void makeTooltip(final ItemTooltipEvent itemTooltipEvent) {
    }
    
    public static void displayInv(final ItemStack itemStack, final String s) {
        try {
            final Item getItem = itemStack.getItem();
            final TileEntityShulkerBox tileEntityShulkerBox = new TileEntityShulkerBox();
            tileEntityShulkerBox.blockType = ((ItemShulkerBox)getItem).getBlock();
            tileEntityShulkerBox.setWorld((World)ShulkerViewer.mc.world);
            ItemStackHelper.loadAllItems(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"), tileEntityShulkerBox.items);
            tileEntityShulkerBox.readFromNBT(itemStack.getTagCompound().getCompoundTag("BlockEntityTag"));
            tileEntityShulkerBox.setCustomName((s == null) ? itemStack.getDisplayName() : s);
            final IInventory inventory;
            new Thread(() -> {
                try {
                    Thread.sleep(200L);
                }
                catch (InterruptedException ex) {}
                ShulkerViewer.mc.player.displayGUIChest(inventory);
            }).start();
        }
        catch (Exception ex2) {}
    }
    
    public void renderShulkerToolTip(final ItemStack itemStack, final int n, final int n2, final String s) {
        final NBTTagCompound getTagCompound = itemStack.getTagCompound();
        final NBTTagCompound getCompoundTag;
        if (getTagCompound != null && getTagCompound.hasKey("BlockEntityTag", 10) && (getCompoundTag = getTagCompound.getCompoundTag("BlockEntityTag")).hasKey("Items", 9)) {
            GlStateManager.enableTexture2D();
            GlStateManager.disableLighting();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
            GlStateManager.enableBlend();
            GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
            ShulkerViewer.mc.getTextureManager().bindTexture(ShulkerViewer.SHULKER_GUI_TEXTURE);
            RenderUtil.drawTexturedRect(n, n2, 0, 0, 176, 16, 500);
            RenderUtil.drawTexturedRect(n, n2 + 16, 0, 16, 176, 57, 500);
            RenderUtil.drawTexturedRect(n, n2 + 16 + 54, 0, 160, 176, 8, 500);
            GlStateManager.disableDepth();
            this.renderer.drawStringWithShadow((s == null) ? itemStack.getDisplayName() : s, (float)(n + 8), (float)(n2 + 6), ColorUtil.toRGBA(new Color(ClickGui.getInstance().red.getValue(), ClickGui.getInstance().green.getValue(), ClickGui.getInstance().blue.getValue(), 200)));
            GlStateManager.enableDepth();
            RenderHelper.enableGUIStandardItemLighting();
            GlStateManager.enableRescaleNormal();
            GlStateManager.enableColorMaterial();
            GlStateManager.enableLighting();
            final NonNullList withSize = NonNullList.withSize(27, (Object)ItemStack.EMPTY);
            ItemStackHelper.loadAllItems(getCompoundTag, withSize);
            for (int i = 0; i < withSize.size(); ++i) {
                final int n3 = n + i % 9 * 18 + 8;
                final int n4 = n2 + i / 9 * 18 + 18;
                final ItemStack itemStack2 = (ItemStack)withSize.get(i);
                ShulkerViewer.mc.getItemRenderer().itemRenderer.zLevel = 501.0f;
                RenderUtil.itemRender.renderItemAndEffectIntoGUI(itemStack2, n3, n4);
                RenderUtil.itemRender.renderItemOverlayIntoGUI(ShulkerViewer.mc.fontRenderer, itemStack2, n3, n4, (String)null);
                ShulkerViewer.mc.getItemRenderer().itemRenderer.zLevel = 0.0f;
            }
            GlStateManager.disableLighting();
            GlStateManager.disableBlend();
            GlStateManager.color(1.0f, 1.0f, 1.0f, 1.0f);
        }
    }
    
    public ShulkerViewer() {
        super("ShulkerViewer", "Several tweaks for ShulkerViewer.", Category.MISC, true, false, false);
        this.spiedPlayers = new ConcurrentHashMap<EntityPlayer, ItemStack>();
        this.playerTimers = new ConcurrentHashMap<EntityPlayer, Timer>();
        this.textRadarY = 0;
        this.setInstance();
    }
    
    @Override
    public void onUpdate() {
        if (fullNullCheck()) {
            return;
        }
        for (final EntityPlayer entityPlayer : ShulkerViewer.mc.world.playerEntities) {
            if (entityPlayer != null && entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox) {
                if (ShulkerViewer.mc.player == entityPlayer) {
                    continue;
                }
                this.spiedPlayers.put(entityPlayer, entityPlayer.getHeldItemMainhand());
            }
        }
    }
    
    @Override
    public void onRender2D(final Render2DEvent render2DEvent) {
        if (fullNullCheck()) {
            return;
        }
        final int n = -3;
        int n2 = 124;
        this.textRadarY = 0;
        for (final EntityPlayer entityPlayer : ShulkerViewer.mc.world.playerEntities) {
            if (this.spiedPlayers.get(entityPlayer) == null) {
                continue;
            }
            if (entityPlayer.getHeldItemMainhand() == null || !(entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox)) {
                final Timer timer = this.playerTimers.get(entityPlayer);
                if (timer == null) {
                    final Timer timer2 = new Timer();
                    timer2.reset();
                    this.playerTimers.put(entityPlayer, timer2);
                }
                else if (timer.passedS(3.0)) {
                    continue;
                }
            }
            else {
                final Timer timer3;
                if (entityPlayer.getHeldItemMainhand().getItem() instanceof ItemShulkerBox && (timer3 = this.playerTimers.get(entityPlayer)) != null) {
                    timer3.reset();
                    this.playerTimers.put(entityPlayer, timer3);
                }
            }
            this.renderShulkerToolTip(this.spiedPlayers.get(entityPlayer), n, n2, entityPlayer.getName());
            n2 += 78;
            this.textRadarY = n2 - 10 - 114 + 2;
        }
    }
    
    static {
        SHULKER_GUI_TEXTURE = new ResourceLocation("textures/gui/container/shulker_box.png");
        ShulkerViewer.INSTANCE = new ShulkerViewer();
    }
    
    private void setInstance() {
        ShulkerViewer.INSTANCE = this;
    }
}
